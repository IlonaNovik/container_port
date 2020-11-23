package GUI.Containers;

import GUI.Containers.Heavy;
import GUI.Sender;

import java.util.Optional;

public abstract class Toxic extends Heavy {
    public boolean isolated;
    public String alarmType;
    public Integer constantTemperature = null;

    public Toxic(Sender sender, String tare, int netWeight, int containerWeight,
                 boolean isolated, String alarmType) {
        super(sender, tare, netWeight, containerWeight);
        this.alarmType = alarmType;
        this.isolated = isolated;
    }

    public Toxic(Sender sender, String tare, int netWeight, int containerWeight,
                 boolean isolated, String alarmType, int constantTemperature) {
        super(sender, tare, netWeight, containerWeight);
        this.constantTemperature = constantTemperature;
        this.alarmType = alarmType;
        this.isolated = isolated;
    }

    @Override
    public String toString() {
//        String result = "Container type Toxic, sender: " + sender.toString() + ", tare: " + tare + ", alarm type: " + alarmType;
//        if (isolated) {
//            result += ", isolated";
//        }
//
//        if(constantTemperature != null){
//            result += ", temperature " + constantTemperature + "°C";
//        }
//        return result;
        return "Container type Toxic id " + id;
    }
}
