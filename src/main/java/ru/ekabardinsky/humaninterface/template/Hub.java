package ru.ekabardinsky.humaninterface.template;

/**
 * This class models the Hub
 * (central cylinder)
 */
public class Hub extends AbstractFibrousSurface {

    private HubMature mature;

    /**
     * Constructor of the Hub
     */
    public Hub(HubMature mature, AbstractFibre fibre) {
        setMature(mature);
        setFibre(fibre);
        setNumberOfLayers(mature.getNumberOfLayers());
    }


    public void setMature(HubMature mature) {
        this.mature = mature;
    }


    /**
     * Hub radius
     * numberOfSegmentInPerimeter (from mature) * fibre length/(2 * pi)
     *
     * @return
     */
    public double getRadius() {
        return getMature().getNumberOfSegmentInPerimeter() * getFibre().getLength() / (2 * Math.PI);///*getFibre().getLength()*getNumberOfLayers();
    }

    /**
     * Hub length
     * numberOfSegmentInLength (from mature) * fibre diameter
     *
     * @return
     */
    public double getLength() {
        return getMature().getNumberOfSegmentInLength() * getFibre().getDiameter();//getNumberOfSegmentsByLayer()*getFibre().getDiameter();
    }


    public HubMature getMature() {
        return mature;
    }


    /**
     * Total length of the fiber for each layer in m
     * numberOfSegments * fibre length;
     */
    @Override
    public double getFibreLenghtByLayer() {
        return getNumberOfSegmentsByLayer() * getFibre().getLength();
    }

    /**
     * Total mass (hub is only a fibrous surface so the mass is only the mass of the fibers)
     */
    @Override
    public double getMass() {
        return getMassFibers();
    }

    /**
     * number of segment = number of segment of mature
     */
    @Override
    public double getNumberOfSegmentsByLayer() {
        return getMature().getNumberOfSegmentsByLayer();
    }


    /**
     * Area of the hub
     * A = 2 * pi * radius * length
     *
     * @return
     */
    @Override
    public double getArea() {
        return 2 * Math.PI * getRadius() * getLength();

    }

    /**
     * Calculate the inner volume of the hub
     * 2 * pi * radius * length
     *
     * @return
     */
    public double getVolume() {
        return 2 * Math.PI * getRadius() * getLength();
    }

}
