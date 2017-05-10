package ru.ekabardinsky.humaninterface.template;

import java.util.Map.Entry;
import java.util.TreeMap;

import de.jtem.ellipticFunctions.Jacobi;
import ru.ekabardinsky.humaninterface.common.StarshipParameters;

public class TroposkeinSurface extends AbstractFibrousSurface {
    private Cylinder cylinder;
    private Hub hub;
    private double tense;
    private TreeMap<Double, Double> profil;

    public Hub getHub() {
        return hub;
    }

    public Cylinder getCylinder() {
        return cylinder;
    }


    public double getTense() {
        return tense;
    }

    public void setTense(double tense) {
        this.tense = tense;
    }


    public TroposkeinSurface(Cylinder cylinder, Hub hub, AbstractFibre fibre) {
        setCylinder(cylinder);
        setHub(hub);
        setFibre(fibre);
        setNumberOfLayers(StarshipParameters.getTroposkeinNumberOfLayers());
    }

    public void setCylinder(Cylinder cylinder) {
        this.cylinder = cylinder;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }

    @Override
    public double getMass() {
        return getMassFibers();
    }

    @Override
    public double getNumberOfSegmentsByLayer() {
        double n = 0, y1, y2 = getHub().getRadius(), d = getFibre().getDiameter(), PI = Math.PI;
        TreeMap<Double, Double> p = getProfil();
        for (Entry<Double, Double> e : p.entrySet()) {
            y1 = y2;
            y2 = e.getValue();
            n += PI * (y1 + y2) / d;
        }

        return n;
    }

    @Override
    public double getArea() {
        double a = 0, x1, y1, x2 = 0, y2 = getHub().getRadius(), PI = Math.PI;
        for (Entry<Double, Double> e : getProfil().entrySet()) {
            x1 = x2;
            y1 = y2;
            x2 = e.getKey();
            y2 = e.getValue();
            //area increment : area of a truncated cone
            //S = pi נ( R + r ) נsqrt[h⠫ (R - r)❍
            a += PI * (y1 + y2) * Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        }
        return a;


    }

    public double getLenght() {
        double x, y, l = 0;
        for (Entry<Double, Double> e : getProfil().entrySet()) {
            x = e.getKey();
            y = e.getValue();
            l += Math.sqrt(x * x + y * y);
        }
        return l;
    }

    public double getConcavity() {
        double x, xmax = 0;
        for (Entry<Double, Double> e : getProfil().entrySet()) {
            x = e.getKey();
            xmax = xmax < x ? x : xmax;
        }
        return xmax;
    }

    public double getHeigth() {
        double y, ymax = getHub().getRadius();
        for (Entry<Double, Double> e : getProfil().entrySet()) {
            y = e.getValue();
            ymax = ymax < y ? y : ymax;
        }
        return ymax - getHub().getRadius();
    }

    public double getVolume() {
        double v = 0, x1, y1, x2 = 0, y2 = getHub().getRadius(), PI = Math.PI;
        for (Entry<Double, Double> e : getProfil().entrySet()) {
            x1 = x2;
            y1 = y2;
            x2 = e.getKey();
            y2 = e.getValue();
            //volume increment : volume of a truncated cone
            // V = (h x pi/3) x ( R⠫ r⠫ R x r )
            v += (PI / 3) * (x2 - x1) * (y1 * y1 + y2 * y2 + y1 * y2); //volume increment
        }
        return v;
    }

    public TreeMap<Double, Double> getProfil() {
        if (!fitProfil()) setProfil(calcProfil());
        return profil;
    }

    /**
     * Do we need to calculate the profil ?
     * This method assess
     * if the radius of the troposkein corresponds
     * to that of the cylinder
     *
     * @return
     */
    private boolean fitProfil() {
        if (profil == null) return false;
        double ymax = profil.lastEntry().getValue();
        double R = getCylinder().getRadius();
        if (Math.abs((R - ymax) / R) > 1e-3) return false;
        return true;
    }

    public void setProfil(TreeMap<Double, Double> profil) {
        this.profil = profil;
    }

    /**
     * Iterative calculus of the troposkein profile
     * This method is a bit tricky, using a elliptic function (Jacobi.sinus)
     * and 3 nested loops to find the right value
     * At last, we tweak the x-y values to fit the hub and cylinder
     * in length and in radius
     *
     * @return
     */
    public TreeMap<Double, Double> calcProfil() {
        TreeMap<Double, Double> p0 = null, p = null;
        double s0 = getFibre().getLength();
        double R = getCylinder().getRadius();
        double L = getCylinder().getLength();
        double r = getHub().getRadius();
        double l = getHub().getLength();
        double mu = getFibre().getLinearMass();
        double g0 = StarshipParameters.getG0();
        double T = Math.sqrt((R - r) * (R - r) + (L - l) * (L - l) / 4) * mu * g0 / 2, T0 = 0, e, d, k, x, dx, x0, y, y0, dy, ds;
        //выводим  в консоль
        //System.out.println(" s0:" + s0+" R:" +R+ " L:" +L+ " r:" +r+ " l:" +l+ " mu:"+mu + " g0:" +g0 );
        while (Math.abs((T0 - T) / T) > 1e-6) {
            p0 = new TreeMap<Double, Double>();
            T0 = T;
            T = 0;
            e = T0 / (g0 * mu * R);
            d = 2 * Math.pow(1 + e, 0.5) / (R * e);
            k = Math.pow(1 + e, -0.5);
            x0 = 0;
            x = 0;
            y = 0;
            dx = s0;
            dy = s0;
            while (dy > 0) {
                p0.put(x, y);
                x0 = x;
                y0 = y;
                dx = s0 / Math.sqrt((1 + Math.pow(dy / dx, 2)));
                double diff = 2;
                while (Math.abs(diff - 1) > 1e-6) {
                    x = x0 + dx;
                    y = R * Jacobi.sn(d * x, k);
                    diff = s0 / Math.sqrt(Math.pow(y - y0, 2) + Math.pow(x - x0, 2));
                    dx = dx * diff;
                }
                dx = x - x0;
                dy = y - y0;
                if (y0 >= r) {
                    ds = Math.sqrt(dx * dx + dy * dy);
                    T += (y + dy / 2) * (g0 / R) * mu * ds;
                }
            }
        }
        setTense(T);

        dy = R - p0.lastEntry().getValue();
        dx = 0.5 * (L - l) - p0.lastEntry().getKey();
        p = new TreeMap<Double, Double>();
        p.put(0.0, r);
        y0 = 0;
        for (Entry<Double, Double> en : p0.entrySet()) {
            x = en.getKey();
            y = en.getValue();
            if (y >= r) {
                if (y0 == 0.0) {
                    y0 = y;
                    p.put(x + dx, r);
                }
                p.put(x + dx, y + dy);
            }
        }
        return p;
    }


    @Override
    public double getFibreLenghtByLayer() {
        return getNumberOfSegmentsByLayer() * getFibre().getLength();
    }


}
