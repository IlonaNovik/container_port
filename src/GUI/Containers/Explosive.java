package GUI.Containers;

import GUI.Sender;

public class Explosive extends Heavy {
    public String material;

    public Explosive(Sender sender, String tare, int netWeight, int containerWeight, String material) {
        super(sender, tare, netWeight, containerWeight);
        this.material = material;
    }

    @Override
    public String toString() {
        //return "Container type Explosives, sender: " + sender.toString() + ", tare: " + tare + ", made of " + material;
        return "Container type Explosive id " + id;
    }
}
