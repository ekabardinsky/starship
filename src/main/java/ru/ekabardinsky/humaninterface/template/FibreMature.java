package ru.ekabardinsky.humaninterface.template;

import ru.ekabardinsky.humaninterface.common.StarshipParameters;

public class FibreMature extends AbstractFibre {
    // Constructor
    public FibreMature() {
        setLength(StarshipParameters.getFiberLenghtMature());
        setFibreRadius(StarshipParameters.getFiberRadiusMature());
        setLumenRadius(StarshipParameters.getLumenRadiusMature());
    }


}
