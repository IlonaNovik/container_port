package GUI.Containers;

import GUI.Containers.Basic;
import GUI.Sender;

public class Heavy extends Basic {
    public int capacity = 42100;
    public boolean transformable = true;


    public Heavy(Sender sender, String tare, int netWeight, int containerWeight) {
        super(sender, tare, netWeight, containerWeight);
    }

    @Override
    public String toString() {
        //return "Container type Heavy, sender: " + sender.toString() + ", tare: " + tare + ", max capacity " + capacity + " kg";

        return "Container type Heavy id " + id;
    }
}
