package GUI.Containers;

import GUI.Sender;

public class Cooling extends Heavy {
    public boolean transformable = false;
    public int temperature;


    public Cooling(Sender sender, String tare, int netWeight, int containerWeight, int temperature) throws Exception {
        super(sender, tare, netWeight, containerWeight);
        this.temperature = setTemperature(temperature);
    }

    public int setTemperature(int degrees) throws Exception {
        if (degrees > -65 && degrees < 40)
            return degrees;
        else
            throw new Exception("Temperature can be set in range -65°C - 40°C");
    }

    @Override
    public String toString() {
//        return "Container type Cooling, sender: " + sender.toString() + ", tare: " + tare +
//                ", temperature : " + temperature + "°C";
        return "Container type Cooling id " + id;
    }
}
