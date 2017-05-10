package ru.ekabardinsky.humaninterface.template;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Body {
    public static final double YEAR = 3600.0 * 24 * 365;

    private double age;
    //New properties (23/03/2017)
    private Fibre fibre;
    private Hub hub;
    private Cylinder cylinder;
    private TroposkeinSurface sternSide;
    private TroposkeinSurface bowSide;
    private Atmosphere atmosphere;

    private static final DecimalFormat df = new DecimalFormat("0.0");

    // Constructor
    public Body() {
        setAge(0.0);
        //Building of a the fundamental element: Fibre
        Fibre fibre = new Fibre(new FibreMature());
        setFibre(fibre);

        //building of the 4 elements of the body, with fibre as property
        // Cylinder and Hub need to be set with a mature state
        //(resp. CylinderMature and HubMature)
        Cylinder cylinder = new Cylinder(new CylinderMature(fibre.getMature()), fibre);
        Hub hub = new Hub(new HubMature(fibre.getMature()), fibre);

        //TroposkeinSurface need Cylinder and Hub
        TroposkeinSurface sternSide = new TroposkeinSurface(cylinder, hub, fibre);
        TroposkeinSurface bowSide = new TroposkeinSurface(cylinder, hub, fibre);

        //let's add a atmosphere inside
        Atmosphere atmosphere = new Atmosphere(cylinder, sternSide, bowSide);

        //Finally, we set the properties of Body with this 4 fibrous elements + atmosphere
        setCylinder(cylinder);
        setHub(hub);
        setSternSide(sternSide);
        setBowSide(bowSide);
        setAtmosphere(atmosphere);

    }

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }

    public Cylinder getCylinder() {
        return cylinder;
    }

    public void setCylinder(Cylinder cylinder) {
        this.cylinder = cylinder;
    }

    public TroposkeinSurface getSternSide() {
        return sternSide;
    }

    public void setSternSide(TroposkeinSurface sternSide) {
        this.sternSide = sternSide;
    }

    public TroposkeinSurface getBowSide() {
        return bowSide;
    }

    public void setBowSide(TroposkeinSurface bowSide) {
        this.bowSide = bowSide;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Fibre getFibre() {
        return fibre;
    }

    public static DecimalFormat getDf() {
        return df;
    }


    public void setFibre(Fibre fibre) {
        this.fibre = fibre;
    }

    /**
     * Fiber mass (water + wood) of the 4 fibrous elements of the body  :
     * 1) cylinder;
     * 2) hub
     * 3) sternSide;
     * 4) bowSide;
     */
    public double getMassFibers() {
        double mass = getCylinder().getMassFibers();
        mass += getHub().getMassFibers();
        mass += getSternSide().getMassFibers();
        mass += getBowSide().getMassFibers();
        return mass;
    }

    /**
     * Addition of the area of the 4 fibrous elements of the body
     * 1) cylinder;
     * 2) hub
     * 3) sternSide;
     * 4) bowSide;
     */
    public double getArea() {
        double mass = getCylinder().getArea();
        mass += getHub().getArea();
        mass += getSternSide().getArea();
        mass += getBowSide().getArea();
        return mass;
    }

    /**
     * internal volume of the body (atmosphere+ocean)
     * cylinder volume - hub volume - sternSide - bowSide
     */
    public double getInnerVolume() {
        double volume = getCylinder().getVolume();
        volume -= getHub().getVolume();
        volume -= getSternSide().getVolume();
        volume -= getBowSide().getVolume();
        return volume;
    }

    /**
     * Total fibres length (in all elements in all layers)
     * cylinder, hub, sterSide, bowSide
     */
    public double getFibreLength() {
        double length = getCylinder().getLength();
        length += getHub().getLength();
        length += getSternSide().getLenght();
        length += getBowSide().getLenght();
        return length;
    }

    /**
     * Total number of segment (in all elements in all layers)
     * cylinder, hub, sterSide, bowSide
     */
    public double getSegmentNumber() {
        double segment = getCylinder().getSegmentNumber();
        segment += getHub().getSegmentNumber();
        segment += getSternSide().getSegmentNumber();
        segment += getBowSide().getSegmentNumber();
        return segment;
    }

    /**
     * Total mass of the body, mass fiber + ocean (in the cylinder)
     */
    public double getMass() {
        double mass = getCylinder().getMassFibers();
        mass += getHub().getMassFibers();
        mass += getSternSide().getMassFibers();
        mass += getBowSide().getMassFibers();
        return mass;
    }

    public Body growth(double time) {
        if (!isMature()) {
            addTime(time);
            fibre.growth(time);
        }
        return this;
    }

    private void addTime(double time) {
        age += time;
    }

    public boolean isMature() {
        //System.out.println(getAgeInYear() +  " " +  df.format(fibre.getLength()) +  " " +   fibre.isMature()+  " " + new Boolean( fibre.getLength()== fibre.getMature().getLength()).toString());
        return fibre.isMature();
    }


    private String getAgeInYear() {
        return df.format(age / YEAR);
    }


    //Accessor - mutator methods
    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public ArrayList<ProfilPoint> getProfil() {
        ArrayList<ProfilPoint> pps = new ArrayList<ProfilPoint>();
        ProfilPoint pp, x2, x3;
        double lCylinder = cylinder.getLength();
        double rCylinder = cylinder.getRadius();
        double lHub = hub.getLength();
        double rHub = hub.getRadius();
        int i = 0;
        pp = new ProfilPoint(i++, 0.0, 0.0);
        pps.add(pp);
        Map<Double, Double> bowInversed = new TreeMap(Collections.reverseOrder());
        bowInversed.putAll(bowSide.getProfil());
        x2 = new ProfilPoint(0, lCylinder - bowSide.getProfil().lastEntry().getKey(), rCylinder - rHub);
        ///part 1
        for (Entry<Double, Double> p : bowInversed.entrySet()) {
            pp = new ProfilPoint(i++, x2.getX() + p.getKey(), x2.getY() - p.getValue());
            pps.add(pp);
        }
        x3 = new ProfilPoint(i++, x2.getX() - lHub, x2.getY());
        ///pps.add(x3);
        ///part 2
        for (Entry<Double, Double> p : sternSide.getProfil().entrySet()) {
            pp = new ProfilPoint(i++, x3.getX() - p.getKey(), x3.getY() - p.getValue());
            pps.add(pp);
        }
        pps.add(new ProfilPoint(i++, 10000, 0));

        return pps;

    }

}
