public class ElectricalAappliance {

    int chargeLevel;
    boolean switchedOn;

    public boolean switchedOn(){
        return this.switchedOn;
    }
    public void addCharge(){
        this.chargeLevel = this.chargeLevel+1;
    }
    public int getChargeLevel(){
        return this.chargeLevel;
    }

}
