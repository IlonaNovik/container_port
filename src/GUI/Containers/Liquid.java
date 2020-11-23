package GUI.Containers;

import GUI.Containers.Basic;
import GUI.Sender;

public class Liquid extends Basic {
    public int capacityLiters;
    public boolean placedInColdStore;

    public Liquid(Sender sender, String tare, int netWeight, int containerWeight, int capacityLiters,
                  boolean placedInColdStore) {
        super(sender, tare, netWeight, containerWeight);
        this.capacityLiters = capacityLiters;
        this.placedInColdStore = placedInColdStore;
    }

    public String toString() {
//        String result = "Container type Liquid, sender: " + sender.toString() + ", tare: " + tare + ", capacity: " +
//                capacityLiters + "L";
//        if (placedInColdStore) {
//            result += ", can be placed in cold store";
//        }
//        return result;
        return "Container type Liquid id " + id;
    }


}
