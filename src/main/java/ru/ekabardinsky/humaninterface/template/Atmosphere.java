package ru.ekabardinsky.humaninterface.template;

import ru.ekabardinsky.humaninterface.common.StarshipParameters;

import java.util.Map.Entry;

public class Atmosphere {
    private Cylinder cylinder;
    private TroposkeinSurface sternSide;
    private TroposkeinSurface bowSide;


    public TroposkeinSurface getSternSide() {
        return sternSide;
    }

    public Atmosphere(Cylinder cylinder, TroposkeinSurface sternSide,
                      TroposkeinSurface bowSide) {
        setCylinder(cylinder);
        setSternSide(sternSide);
        setSternSide(bowSide);
    }

    public Cylinder getCylinder() {
        return cylinder;
    }

    //(getter and setter to be added)

    public void setCylinder(Cylinder cylinder) {
        this.cylinder = cylinder;
    }

    public void setSternSide(TroposkeinSurface sternSide) {
        this.sternSide = sternSide;
    }

    /**
     * Atmosphere mass with respect to the pressure gradient
     *
     * @return
     */
    public double getMass() {
        double m = 0;//mass
        double PI = Math.PI;
        double L = getCylinder().getLength(), l, x, x1, x2 = 0, y, y1, y2 = 0;
        double R = getCylinder().getRadius();
        double g0 = StarshipParameters.getG0();//gravity at level sea
        double Rs = StarshipParameters.getGasctedryair();
        double T0 = StarshipParameters.getT0(), T = T0;
        double p0 = StarshipParameters.getP0(), p = p0;
        double cp = StarshipParameters.getCp();
        double v = 0;//volume
        double rho;

        for (Entry<Double, Double> e : getSternSide().getProfil().entrySet()) {
            x1 = x2;
            y1 = y2;
            x2 = e.getKey();
            y2 = e.getValue();
            y = (y1 + y2) / 2;
            x = (x1 + x2) / 2;
            l = L - 2 * x;
            T = T0 * Math.pow(p / p0, Rs / cp);
            p = p0 * Math.exp(-(g0 / (Rs * T)) * y * (1 - y / (2 * R)));
            rho = p / (Rs * T);
            v = 2 * PI * y * l * (y2 - y1); //volume
            m += v * rho;
        }
        return m;
    }


    public TroposkeinSurface getBowSide() {
        return bowSide;
    }

    public void setBowSide(TroposkeinSurface bowSide) {
        this.bowSide = bowSide;
    }

    public double getVolume() {
        double PI = Math.PI;
        double L = getCylinder().getLength(), l, x, x1, x2 = 0, y, y1, y2 = 0;
        double o = getCylinder().getOceanVolume();
        double v = 0;//volume
        double xmax = getSternSide().getConcavity();
        for (Entry<Double, Double> e : getSternSide().getProfil().entrySet()) {
            x1 = x2;
            y1 = y2;
            x2 = e.getKey();
            y2 = e.getValue();
            y = (y1 + y2) / 2;
            x = (x1 + x2) / 2;
            l = L - 2 * (xmax - x);
            v += 2 * PI * y * l * (y2 - y1); //volume
        }
        return v - o;
    }

}
