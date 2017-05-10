package ru.ekabardinsky.humaninterface.template;

import ru.ekabardinsky.humaninterface.common.StarshipParameters;

public class Cylinder extends AbstractFibrousSurface {

    private double oceanDepth;
    private CylinderMature mature;


    public Cylinder(CylinderMature mature, AbstractFibre fibre) {
        setMature(mature);
        setOceanDepth(StarshipParameters.getOceanDepth());
        setFibre(fibre);
        setNumberOfLayers(mature.getNumberOfLayers());
    }

    // g0 from StarshipParameters
    // angularSpeed = sqrt(g0/radius)
    private double getAngularSpeed() {
        return Math.sqrt(StarshipParameters.getG0() / getRadius());
    }

    public double getLength() {
        // TODO Auto-generated method stub
        return getMature().getNumberOfSegmentsByLength()
                * getFibre().getDiameter();///getSegmentNumber()*getFibre().getDiameter();
    }

    public double getRadius() {
        // TODO Auto-generated method stub
        return getMature().getNumberOfSegmentsInPerimeter()
                * getFibre().getLength() / (2 * Math.PI);
    }

    //volume * rhoOcean (from StarshipParameters)
    public double getOceanMass() {
        return getVolume() * StarshipParameters.getRhoOcean();
    }

    // rint = radius * fibre diameter * numberOfLayer
    // rext = rint - oceanDepth
    // vint = pi*rint^2 * length
    // vext = pi*rext^2 * length
    public double getOceanVolume() {
        double rint = getRadius() * getFibre().getDiameter() * getNumberOfLayers();
        double rext = rint - getOceanDepth();
        double vint = Math.PI * rint * rint * getLength();
        double vext = Math.PI * rext * rext * getLength();
        return vext < vint ? 0 : (vext - vint);
    }

    /**
     * Wall tensile in Pa (N/m2)
     * w2 = angularSpeed^2
     * eFibre = numberOfLayers * fibre diameter
     * fibreWeight = w2 * (radius - eFibre/2)
     * oceanWeight = w2 * (radius - eFibre  - oceanDepth/2) * oceanMass
     * weight= fibreWeight + oceanWeight
     * woodArea= numberOfSegmentsByLayer * numberOfLayers * woodArea of fibre
     */
    public double getTensileInWood() {
        double w2 = getAngularSpeed() * getAngularSpeed();
        double eFibre = getNumberOfLayers() * getFibre().getDiameter();
        double fibreWeight = w2 * (getRadius() - eFibre / 2);
        double oceanWeight = w2 * (getRadius() - eFibre - getOceanDepth() / 2) * getOceanMass();
        double weight = fibreWeight + oceanWeight;
        double woodArea = getNumberOfSegmentsByLayer() * getNumberOfLayers() * getFibre().getWoodArea();
        return weight / woodArea;
    }

    public double getOceanDepth() {
        return oceanDepth;
    }

    public void setOceanDepth(double oceanDepth) {
        this.oceanDepth = oceanDepth;
    }

    public CylinderMature getMature() {
        return mature;
    }

    public void setMature(CylinderMature mature) {
        this.mature = mature;
    }

    public double getArea() {
        return 2 * Math.PI * getRadius() * getLength();
    }

    public double getVolume() {
        return 2 * Math.PI * getRadius() * getLength();
    }

    @Override
    public double getFibreLenghtByLayer() {
        // TODO Auto-generated method stub
        return getNumberOfSegmentsByLayer() * getFibre().getLength();
    }

    /*
        @Override
        public double getMass() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public double getNumberOfSegmentsByLayer() {
            // TODO Auto-generated method stub
            return 0;
        }*/
    @Override
    public double getMass() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getNumberOfSegmentsByLayer() {
        // TODO Auto-generated method stub
        return 0;
    }

}
