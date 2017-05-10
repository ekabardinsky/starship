package ru.ekabardinsky.humaninterface.template;

import ru.ekabardinsky.humaninterface.common.StarshipParameters;

public abstract class AbstractFibre implements Cloneable {
    //Current dimensions
    private double lumenRadius;//m
    private double fibreRadius;//m
    private double length;//m

    public double getWoodDensity() {
        return StarshipParameters.getRhoWood();
    }

    public double getCellLayerThickness() {
        return StarshipParameters.getCellLayerThickness();
    }

    // pi*lumenRadius^2
    public double getLumenArea() {
        return Math.PI * Math.pow(getLumenRadius(), 2.0);
    }

    // pi*fibreRadius^2 - lumenArea
    public double getWoodArea() {
        return Math.PI * Math.pow(getFibreRadius(), 2.0) - getLumenArea();
    }

    // linearLumenMass + LinearWoodMass
    public double getLinearMass() {
        return getLinearLumenMass() + getLinearWoodMass();
    }

    //lumenArea * rhoWater
    public double getLinearLumenMass() {
        return getLumenArea() * StarshipParameters.getRhoWater();
    }

    //woodArea * rhoWood
    public double getLinearWoodMass() {
        return getWoodArea() * StarshipParameters.getRhoWood();
    }


    // woodArea * length * woodDensity
    public double getWoodMass() {
        return getWoodArea() * getLength() * getWoodDensity();
    }

    //2 * pi * lumenRadius * length
    public double getPhotosyntheticArea() {
        return 2 * Math.PI * getLumenRadius() * getLength();
    }

		/*public double getWoodDensity() {
			return StarshipParametrs.get
		}*/

    public double getLumenRadius() {
        return lumenRadius;
    }

    public void setLumenRadius(double lumenRadius) {
        this.lumenRadius = lumenRadius;
    }

    public double getFibreRadius() {
        return fibreRadius;
    }

    public void setFibreRadius(double fibreRadius) {
        this.fibreRadius = fibreRadius;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(fibreRadius);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(length);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lumenRadius);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public boolean qequals(double a, double b, double threeshold) {

        return Math.abs((b - a) / a) < threeshold;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        double threeshold = 1e-3;

        AbstractFibre o = (AbstractFibre) obj;
        if (!qequals(fibreRadius, o.fibreRadius, threeshold)) {
            return false;
        }
        if (!qequals(length, o.length, threeshold)) {
            return false;
        }
        if (!qequals(lumenRadius, o.lumenRadius, threeshold)) {
            return false;
        }
        return true;
    }

    public double getDiameter() {
        return fibreRadius * 2;
    }

}
