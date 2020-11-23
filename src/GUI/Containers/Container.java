package GUI.Containers;

import GUI.Sender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class Container {
    private static List<Container> extent = new ArrayList<>();

    public int id;
    public Sender sender;
    public String tare;
    public int netWeight;
    public int grossWeight;
    private LocalDate arriveDate;

    /** Container constructor */
    public Container(Sender sender, String tare, int netWeight, int containerWeight) {
        this.id = setId();
        this.sender = sender;
        this.tare = tare;
        this.netWeight = netWeight;
        this.grossWeight = netWeight + containerWeight;

        extent.add(this);
    }

    public void setArriveDate(LocalDate arriveDate) {
        this.arriveDate = arriveDate;
    }

    public LocalDate getArriveDate() {
        return arriveDate;
    }

    /** Set unique id for each container */
    private static int setId(){
        return 10000 + extent.size() + 1;
    }
}
