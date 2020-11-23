package GUI.Containers;

import GUI.Sender;

public class Granular extends Toxic {
    public boolean bagHandels;
    public boolean roofOpening;

    public Granular(Sender sender, String tare, int netWeight, int containerWeight,
                    boolean isolated, String alarmType, boolean bagHandels, boolean roofOpening) {
        super(sender, tare, netWeight, containerWeight, isolated, alarmType);
        this.bagHandels = bagHandels;
        this.roofOpening = roofOpening;
    }

    @Override
    public String toString() {
//        String result = "Container type Granular, sender: " + sender.toString() + ", tare: " + tare;
//        if (bagHandels) {
//            result += ", bag handles available";
//        }
//
//        if(roofOpening){
//            result += " material can be loaded threw roof";
//        }
//        return result;
        return "Container type Granular id " + id;
    }
}
