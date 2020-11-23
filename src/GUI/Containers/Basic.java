package GUI.Containers;

import GUI.Sender;

public class Basic extends Container {
    public int capacity = 21680;

    public Basic(Sender sender, String tare, int netWeight, int containerWeight) {
        super(sender, tare, netWeight, containerWeight);
    }

    @Override
    public String toString() {
        //return "Container type Basic, sender: " + sender.toString() + ", tare: " + tare;
        return "Container type Basic id " + id;
    }
}
