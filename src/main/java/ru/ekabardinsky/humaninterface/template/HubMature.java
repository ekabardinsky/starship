package ru.ekabardinsky.humaninterface.template;

import ru.ekabardinsky.humaninterface.common.StarshipParameters;

public class HubMature extends AbstractFibrousSurface {
    private double radius;
    private double lenght;

    public HubMature(FibreMature fibre) {
        setRadius(StarshipParameters.getHubRadiusMature());
        setLenght(StarshipParameters.getHubLenghtMature());
        setNumberOfLayers(StarshipParameters.getHubNumberOfLayers());
        setFibre(fibre);
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Fibre length by layer
     * numberOfSegments * fibre length
     */
    @Override
    public double getFibreLenghtByLayer() {
        return getSegmentNumber() * getFibre().getLength();//getNumberOfSegmentsByLayer()*getFibersLength() ;                 !!!!!!!!!!!!
    }

    @Override
    public double getMass() {
        return getMassFibers();
    }

    /**
     * Number of segment constituting the overall surface
     * numberOfSegmentInLength * numberOfSegmentInPerimeter
     */
    @Override
    public double getNumberOfSegmentsByLayer() {
        return getNumberOfSegmentInLength() * getNumberOfSegmentInPerimeter();
    }

    /**
     * Calculate the number of segment in length
     * length / fibre diameter
     * <p>
     * like this :
     * ||||||||||||||||||||||||
     */
    public double getNumberOfSegmentInLength() {
        return lenght / getFibre().getDiameter();
    }

    /**
     * Calculate the number of segment in perimeter
     * 2 * pi * radius / fibre length
     * <p>
     * like this :
     * ===================
     */
    public double getNumberOfSegmentInPerimeter() {
        return 2 * Math.PI * radius / getLenght();
    }

    @Override
    public double getArea() {
        return 2 * Math.PI * getRadius() * getLenght();

    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    public double getRadius() {
        return radius;
    }


}
