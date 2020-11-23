package GUI.Containers;

import GUI.Sender;

public class LiquidToxic extends Toxic {
    public int capacityLiters;
    public boolean placedInColdStore;
    public String construction;
    public String cover;
    public boolean protectionRequired;


    public LiquidToxic(Sender sender, String tare, int netWeight, int containerWeight, boolean isolated,
                       String alarmType, String construction, boolean protectionRequired, String cover,
                       int capacityLiters, boolean placedInColdStore) {
        super(sender, tare, netWeight, containerWeight, isolated, alarmType);
        this.construction = construction;
        this.protectionRequired = protectionRequired;
        this.cover = cover;
        this.capacityLiters = capacityLiters;
        this.placedInColdStore = placedInColdStore;
    }

    public LiquidToxic(Sender sender, String tare, int netWeight, int containerWeight, boolean isolated, String alarmType,
                       int constantTemperature, String construction, boolean protectionRequired, String cover,
                       int capacityLiters, boolean placedInColdStore) {
        super(sender, tare, netWeight, containerWeight, isolated, alarmType, constantTemperature);
        this.construction = construction;
        this.protectionRequired = protectionRequired;
        this.cover = cover;
        this.capacityLiters = capacityLiters;
        this.placedInColdStore = placedInColdStore;
    }

    @Override
    public String toString() {
//        String result = "Container type Liquid Toxic, sender: " + sender.toString() + ", tare: " + tare + ", made of " + construction +
//                ", cover: " + cover;
//        if (protectionRequired) {
//            result += ", protection required";
//        }
//        return result;
        return "Container type Liquid Toxic id " + id;
    }
}
