package ru.ekabardinsky.humaninterface.template;


public abstract class AbstractFibrousSurface {
    private AbstractFibre fibre;
    private int numberOfLayers;

    public abstract double getFibreLenghtByLayer();///////                !!!!!!!!!!!! abstract

    public abstract double getMass();

    public abstract double getNumberOfSegmentsByLayer();

    public abstract double getArea();

    //fibreLenghtByLayer * linearMass of the fiber
    public double getMassFibersByLayer() {
        return getFibreLenghtByLayer() * getFibre().getLinearMass();//getFibreLenghtByLayer();
    }

    //numberOfLayers * massFibersByLayer
    public double getMassFibers() {
        return getNumberOfLayers() * getMassFibersByLayer();//getNumberOfSegmentsByLayer()*getMassFibersByLayer();
    }

    //numberOfLayers * fibreLenghtByLayer
    public double getFibersLength() {
        return getNumberOfSegmentsByLayer() * getFibreLenghtByLayer();
    }

    public AbstractFibre getFibre() {
        return fibre;
    }

    public void setFibre(AbstractFibre fibre) {
        this.fibre = fibre;
    }

    public int getNumberOfLayers() {
        return numberOfLayers;
    }

    public void setNumberOfLayers(int numberOfLayers) {
        this.numberOfLayers = numberOfLayers;
    }

    // numberOfLayers * numberOfSegmentsByLayer
    public double getSegmentNumber() {
        return getNumberOfLayers() * getNumberOfSegmentsByLayer();//getNumberOfSegmentsByLayer()*getNumberOfSegmentsByLayer();
    }

    // number of segment * photosyntetical area of a fiber
    public double getPhotosyntheticArea() {
        return getSegmentNumber() * getPhotosyntheticArea();//getNumberOfSegmentsByLayer()*getPhotosyntheticArea();                 !!!!!!!!!!!!
    }
}