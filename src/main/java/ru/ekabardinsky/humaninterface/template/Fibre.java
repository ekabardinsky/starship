package ru.ekabardinsky.humaninterface.template;

import ru.ekabardinsky.humaninterface.common.StarshipParameters;

import java.text.DecimalFormat;
import java.util.HashMap;


public class Fibre extends AbstractFibre {
    //Adult state
    private FibreMature mature;
    //To record all the elements con
    // sumptions during the grown process
    private HashMap<String, Double> logBook;

    private static final DecimalFormat df = new DecimalFormat("0.0");

    // Constructor
    public Fibre(FibreMature mature) {
        setMature(mature);
        setFibreRadius(StarshipParameters.getFiberRadiusInit());
        setLumenRadius(StarshipParameters.getLumenRadiusInit());
        setLength(StarshipParameters.getFiberLenghtInit());
    }

    public FibreMature getMature() {
        return mature;
    }

    public void setMature(FibreMature mature) {
        this.mature = mature;
    }

    public HashMap<String, Double> getLogBook() {
        if (logBook == null) logBook = new HashMap<String, Double>();
        return logBook;
    }


/*
    @Override
	public String toJSONString() {
		// TODO Auto-generated method stub
		return null;
	}*/

    public boolean growth(double time) {
        if (isMature()) return false;
        //physical constants
        //Energy income : flowLight * time *  area lumen
        double power = StarshipParameters.getFlowLight() * getPhotosyntheticArea();
        log("powerIncome", power);
        //Mass synthesized by a given energy
        // nCO2 + nH2O - (+ light: 1/0.1 photon) --> nCH2O + nO2
        double powerAbsorbed = power * StarshipParameters.getAbsorptionYield();
        log("powerAbsorbed", powerAbsorbed);
        log("heatLoss", power - powerAbsorbed);
        double nPhotonAbsorbed = powerAbsorbed / (StarshipParameters.getPlanckcte() * StarshipParameters.speedLight / StarshipParameters.getOptimalWaveLength());
        double molCO2Fixed = nPhotonAbsorbed * StarshipParameters.getQuantumYieldForCO2Fixation() / StarshipParameters.avogadroNumber;

        double dM = time * molCO2Fixed * (StarshipParameters.molarMassC + StarshipParameters.molarMassH * 2 + StarshipParameters.molarMassO);
        // breakdown of the synthesized mass :

        //1) First : increase the radiusLumen to is maximum
        dM = growthLumen(dM);

        //2) Then (with the remaining mass), increase the fibreRadius to is maximum
        dM = growthRadius(dM);

        //3) Last (with the remaining mass), increase fibreLenght to is maximum
        dM = growthLength(dM);
        return true;
    }

    private void log(String key, double value) {
        //getLogBook().put(key, value);
        if (!getLogBook().containsKey(key))
            getLogBook().put(key, value);
        else getLogBook().put(key, getLogBook().get(key) + value);

    }

    public static DecimalFormat getDf() {
        return df;
    }

    public boolean isMature() {
        return this.equals(mature);
    }

    //Increase lumenRadius using a given mass dM. After that, fixes the fibreRadius. Return the remaining mass
    private double growthLumen(double dM) {
        if (dM <= 0) return 0;
        double Rl = getLumenRadius();
        double Rmax = getMature().getLumenRadius();
        //mass of the only cells layer for a increasing radius
        double MbydR = 2 * Math.PI * getWoodDensity() * getLength() * getCellLayerThickness();
        double deltaM = (Rmax - Rl) * MbydR;
        double dR = dM < deltaM ? dM / MbydR : (Rmax - Rl);
        setLumenRadius(Rl + dR);
        if (dR > 0) {
            //fix fibreRadius
            double Rf = getFibreRadius();
            Rf = Math.sqrt(Math.pow(Rf, 2) + 2 * Rl * dR + Math.pow(dR, 2));
            setFibreRadius(Rf);
        }
        return dM < deltaM ? 0 : dM - deltaM;
    }


    //Increase fibreRadius using a given mass dM, return the remaining mass
    private double growthRadius(double dM) {
        if (dM <= 0) return 0;
        //current radius
        double Rf = getFibreRadius();
        //current length
        double Lf = getLength();
        //wood density
        double rhoWood = getWoodDensity();
        //maximum radius
        double Rfmax = getMature().getFibreRadius();
        //current wood mass
        double M = getWoodMass();
        //maximum wood mass having a mature radius but for the CURRENT length
        double Mmax = getMature().getWoodArea() * rhoWood * Lf;
        //calculate remaining mass
        double ddM = M + dM > Mmax ? (M + dM - Mmax) : 0;
        //Increasing of the radius (in respect to a maximum Rfmax)
        double dR = ddM > 0 ? Rfmax - Rf : Math.sqrt(Math.pow(Rf, 2) + dM / (Math.PI * rhoWood * Lf)) - Rf;
        // set the new radius
        setFibreRadius(Rf + dR);
        //return remaining mass
        return ddM;
    }

    //Increase fibreLenght using a given mass dM, return the remaining mass
    private double growthLength(double dM) {
        if (dM <= 0) return 0;
        //current length
        double Lf = getLength();
        //wood density
        double rhoWood = getWoodDensity();
        //maximum length
        double Lfmax = getMature().getLength();
        //current wood mass
        double M = getWoodMass();
        //maximum wood mass
        double Mmax = getMature().getWoodMass();
        //calculate remaining mass
        double ddM = M + dM > Mmax ? (M + dM - Mmax) : 0;
        //Increasing of the length (in respect to a maximum Lfmax)
        double dL = ddM > 0 ? Lfmax - Lf : dM / (getWoodArea() * rhoWood);
        // set the new length
        setLength(Lf + dL);
        //return remaining mass
        return ddM;
    }

    public void setLogBook(HashMap<String, Double> logBook) {
        this.logBook = logBook;
    }

}