package ru.ekabardinsky.humaninterface.template;

import ru.ekabardinsky.humaninterface.common.StarshipParameters;

public class CylinderMature extends AbstractFibrousSurface {


    private double radius;
    private double length;

    public CylinderMature(FibreMature fibre) {
        setRadius(StarshipParameters.getCylinderRadiusMature());
        setLength(StarshipParameters.getCylinderLenghtMature());
        setFibre(fibre);

        setCylinderNumberOfLayers();
    }

    private void setCylinderNumberOfLayers() {
        double weightOcean = StarshipParameters.getG0() * 2 * Math.PI * getLength() * StarshipParameters.getOceanDepth() * StarshipParameters.getRhoOcean();
        double weightFibreByLayer = StarshipParameters.getG0() * this.getMassFibersByLayer();
        double areaWoodByLayer = this.getNumberOfSegmentsByLayer() * getFibre().getWoodArea();

        int numberOfLayers = (int) Math.ceil(weightOcean / (StarshipParameters.tensileStrenght * areaWoodByLayer - weightFibreByLayer));
        setNumberOfLayers(numberOfLayers);
    }

    @Override
    public double getFibreLenghtByLayer() {
        return getNumberOfSegmentsByLayer() * getFibre().getLength();
    }


    @Override
    public double getMass() {

        return getMassFibers();
    }

    @Override
    public double getNumberOfSegmentsByLayer() {
        return getNumberOfSegmentsByLength() * getNumberOfSegmentsInPerimeter();
    }

    public double getNumberOfSegmentsByLength() {
        return getLength() / (getFibre().getDiameter());
    }

    public double getNumberOfSegmentsInPerimeter() {
        return 2 * Math.PI * getRadius() / getFibre().getLength();
    }

    @Override
    public double getArea() {

        return this.getArea();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

}
