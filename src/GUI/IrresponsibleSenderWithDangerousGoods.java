package GUI;

import GUI.Ship;
import GUI.Containers.Container;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IrresponsibleSenderWithDangerousGoods {
    private static List<Ship> extent = new ArrayList<>();

    public int id;
    public Container container;
    public LocalDate arriveDate;
    public LocalDate utilizationDate;

    /** Warning constructor */
    public IrresponsibleSenderWithDangerousGoods(Container container, LocalDate arriveDate, LocalDate utilizationDate){
        this.id = setId();
        this.container = container;
        this.arriveDate = arriveDate;
        this.utilizationDate = utilizationDate;
    }

    /** Set unique id for each warning */
    private static int setId(){
        return 10000 + extent.size() + 1;
    }

    @Override
    public String toString() {
        return "Warning id " + id + " date " + utilizationDate.toString();
    }
}
