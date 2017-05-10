package ru.ekabardinsky.humaninterface.common;

public class StarshipParameters {
    //GRAVITY INTERNAL SURFACE
    public static double g0 = 9.81;

    //PHYSICAL CONSTANTS
    public static final double planckCte = 6.62607004e-34;//J.s Planck constant
    public static final double speedLight = 299792458.0;//m.s-1 speed of light
    public static final double avogadroNumber = 6.02214129e23;// mol-1 Avogadro number
    public static final double molarMassC = 12.0107e-3;//kg/mol molar mass carbon
    public static final double molarMassH = 1.00794e-3;//kg/mol molar mass hydrogen
    public static final double molarMassO = 15.9994e-3;//kg/mol molar mass oxygen
    //private static final double molarMassN=14.0067-3;//kg/mol molar mass nitrogen
    public static final double GasCteDryAir = 287.058;//J.kg-1.K-1 Gas constant for dry air
    public static final double p0 = 101325;//Pa at sea level at +15Í
    public static final double T0 = 273.15 + 15;//K
    public static final double Cp = 1004;//J.kg-1.K-1 Heat capacity of air at p cte
    public static final double h20InAtmosphere = 0.01; //kg/kg
    public static final double h20InSeaWater = 0.965; //kg/kg http://www.seafriends.org.nz/oceano/seawater.htm
    public static final double h20InLumenLiquid = 0.99; //kg/kg
    public static final double h20InWood = 0.5; //kg/kg

    public static double getGasctedryair() {
        return GasCteDryAir;
    }

    //atmosphere composition
    public static final double n2InAtmosphere = 0.773085034;
    public static final double o2InAtmosphere = 0.207411368;
    public static final double arInAtmosphere = 0.009207283;
    public static final double cO2InAtmosphere = 0.000396012; //kg/kg 396ppm
    public static final double h2OInAtmosphere = 0.009900304;
    //CO2 rate
    public static final double cO2InSeaWater = 90 / 1e6; //kg/kg 90 mg/kg
    public static final double cO2InLumenLiquid = 15000 / 1e6; //kg/kg
    public static final double cO2InWood = 0.5; //kg/kg

    //PHOTOSYNTHETIC EFFICIENCY
    private static double flowLight = 300.0;//Watt/m2
    private static double enthalpySynthesis = 465e3; //J/mol
    private static double absorptionYield = 0.7;//proportion of incident energy absorbed by photosystem
    private static double optimalWaveLength = 640.0e-9;//m
    private static double quantumYieldForCO2Fixation = 0.1;//alpha = mol CO2 fixed by mol photon 640 nm absorbed (http://www.plantcell.org/content/24/5/1711.full)

    //DENSITY VALUE
    private static double rhoWater = 1000.0; //kg.m-3
    private static double rhoWood = 1300.0; //kg.m-3
    private static double rhoOcean = 1025.0; //kg.m-3

    //PARALLELE TENSILE STRENGHT WOOD
    private static double tensileStrenghtInMPa = 770.0; //Pa (400 MPa)
    public static double tensileStrenght = tensileStrenghtInMPa * 1e6; //Pa (400 MPa)

    //Dimension cells layer
    private static double cellLayerThickness = 100e-6;//m

    //DIMENSION FIBRE MAX
    private static double lumenRadiusMature = 0.4;//m
    private static double fiberRadiusMature = 1.2; //m
    private static double fiberLenghtMature = 100.0; //m

    //DIMENSION FIBRE INIT
    private static double lumenRadiusInit = 0.01;//m
    private static double fiberRadiusInit = 0.01; //m
    private static double fiberLenghtInit = 0.01; //m

    //DIMENSION CYLINDER MAX
    private static double cylinderRadiusMature = 5000.0; //m
    private static double cylinderLenghtMature = 10000.0; //m
    //DEEPNESS OF OCEAN
    private static double oceanDepth = 24.0; //m

    //DIMENSION HUB MAX
    private static double hubRadiusMature = 450.0; //m
    private static double hubLenghtMature = 4000.0; //m
    private static int hubNumberOfLayers = 1;

    //DIMENSION TROPOSKEIN SURFACES
    private static int troposkeinNumberOfLayers = 2;

    public static double getG0() {
        return g0;
    }

    public static void setG0(double g0) {
        StarshipParameters.g0 = g0;
    }

    public static double getPlanckcte() {
        return planckCte;
    }

    public static double getFlowLight() {
        return flowLight;
    }

    public static void setFlowLight(double flowLight) {
        StarshipParameters.flowLight = flowLight;
    }

    public static double getEnthalpySynthesis() {
        return enthalpySynthesis;
    }

    public static void setEnthalpySynthesis(double enthalpySynthesis) {
        StarshipParameters.enthalpySynthesis = enthalpySynthesis;
    }

    public static double getAbsorptionYield() {
        return absorptionYield;
    }

    public static void setAbsorptionYield(double absorptionYield) {
        StarshipParameters.absorptionYield = absorptionYield;
    }

    public static double getOptimalWaveLength() {
        return optimalWaveLength;
    }

    public static void setOptimalWaveLength(double optimalWaveLength) {
        StarshipParameters.optimalWaveLength = optimalWaveLength;
    }

    public static double getQuantumYieldForCO2Fixation() {
        return quantumYieldForCO2Fixation;
    }

    public static void setQuantumYieldForCO2Fixation(double quantumYieldForCO2Fixation) {
        StarshipParameters.quantumYieldForCO2Fixation = quantumYieldForCO2Fixation;
    }

    public static double getRhoWater() {
        return rhoWater;
    }

    public static void setRhoWater(double rhoWater) {
        StarshipParameters.rhoWater = rhoWater;
    }

    public static double getRhoWood() {
        return rhoWood;
    }

    public static void setRhoWood(double rhoWood) {
        StarshipParameters.rhoWood = rhoWood;
    }

    public static double getRhoOcean() {
        return rhoOcean;
    }

    public static void setRhoOcean(double rhoOcean) {
        StarshipParameters.rhoOcean = rhoOcean;
    }

    public static double getTensileStrenght() {
        return tensileStrenght;
    }

    public static void setTensileStrenght(double tensileStrenght) {
        StarshipParameters.tensileStrenght = tensileStrenght;
    }

    public static double getCellLayerThickness() {
        return cellLayerThickness;
    }

    public static void setCellLayerThickness(double cellLayerThickness) {
        StarshipParameters.cellLayerThickness = cellLayerThickness;
    }

    public static double getLumenRadiusMature() {
        return lumenRadiusMature;
    }

    public static void setLumenRadiusMature(double lumenRadiusMature) {
        StarshipParameters.lumenRadiusMature = lumenRadiusMature;
    }

    public static double getFiberRadiusMature() {
        return fiberRadiusMature;
    }

    public static void setFiberRadiusMature(double fiberRadiusMature) {
        StarshipParameters.fiberRadiusMature = fiberRadiusMature;
    }

    public static double getFiberLenghtMature() {
        return fiberLenghtMature;
    }

    public static void setFiberLenghtMature(double fiberLenghtMature) {
        StarshipParameters.fiberLenghtMature = fiberLenghtMature;
    }

    public static double getLumenRadiusInit() {
        return lumenRadiusInit;
    }

    public static void setLumenRadiusInit(double lumenRadiusInit) {
        StarshipParameters.lumenRadiusInit = lumenRadiusInit;
    }

    public static double getFiberRadiusInit() {
        return fiberRadiusInit;
    }

    public static void setFiberRadiusInit(double fiberRadiusInit) {
        StarshipParameters.fiberRadiusInit = fiberRadiusInit;
    }

    public static double getFiberLenghtInit() {
        return fiberLenghtInit;
    }

    public static void setFiberLenghtInit(double fiberLenghtInit) {
        StarshipParameters.fiberLenghtInit = fiberLenghtInit;
    }

    public static double getCylinderRadiusMature() {
        return cylinderRadiusMature;
    }

    public static void setCylinderRadiusMature(double cylinderRadiusMature) {
        StarshipParameters.cylinderRadiusMature = cylinderRadiusMature;
    }

    public static double getCylinderLenghtMature() {
        return cylinderLenghtMature;
    }

    public static void setCylinderLenghtMature(double cylinderLenghtMature) {
        StarshipParameters.cylinderLenghtMature = cylinderLenghtMature;
    }

    public static double getOceanDepth() {
        return oceanDepth;
    }

    public static void setOceanDepth(double oceanDepth) {
        StarshipParameters.oceanDepth = oceanDepth;
    }

    public static double getHubRadiusMature() {
        return hubRadiusMature;
    }

    public static void setHubRadiusMature(double hubRadiusMature) {
        StarshipParameters.hubRadiusMature = hubRadiusMature;
    }

    public static double getHubLenghtMature() {
        return hubLenghtMature;
    }

    public static void setHubLenghtMature(double hubLenghtMature) {
        StarshipParameters.hubLenghtMature = hubLenghtMature;
    }

    public static int getHubNumberOfLayers() {
        return hubNumberOfLayers;
    }

    public static void setHubNumberOfLayers(int hubNumberOfLayers) {
        StarshipParameters.hubNumberOfLayers = hubNumberOfLayers;
    }

    public static int getTroposkeinNumberOfLayers() {
        return troposkeinNumberOfLayers;
    }

    public static void setTroposkeinNumberOfLayers(int troposkeinNumberOfLayers) {
        StarshipParameters.troposkeinNumberOfLayers = troposkeinNumberOfLayers;
    }

    public static double getT0() {
        // TODO Auto-generated method stub
        return T0;
    }

    public static double getP0() {
        // TODO Auto-generated method stub
        return p0;
    }

    public static double getCp() {
        // TODO Auto-generated method stub
        return Cp;
    }

    // + getter and setter


}
