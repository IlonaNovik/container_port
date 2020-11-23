package GUI;

import GUI.Containers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Storage {
    public int maxStoredContainers; // maximum allowed containers in storage
    public int maxDaysExplosives;   // maximum days allowed to store explosives
    public int maxDaysLiquidToxic;  // maximum days allowed to store toxic liquid
    public int maxDaysGranular;     // maximum days allowed to store granular
    public ArrayList<Container> storedContainers = new ArrayList<>();

    /** Storage constructor */
    public Storage(int maxStoredContainers, int maxDaysExplosives, int maxDaysLiquidToxic,
                   int maxDaysGranular){
        this.maxStoredContainers = maxStoredContainers;
        this.maxDaysExplosives = maxDaysExplosives;
        this.maxDaysLiquidToxic = maxDaysLiquidToxic;
        this.maxDaysGranular = maxDaysGranular;
    }

    /** Store single container */
    public void storeContainer(Container container){
        container.setArriveDate(LocalDate.now());
        storedContainers.add(container);
    }

    /** Store multiple containers */
    public void storeContainers(ArrayList<Container> containers){
        for(Container c : containers){
            c.setArriveDate(LocalDate.now());
        }
        storedContainers.addAll(containers);
    }

    /** Load single container on ship */
    public void loadContainer(int id, GUI.Ship ship) throws Exception {
        Container containerToLoad = getContainerById(storedContainers, id); // get container by id
        ship.loadContainer(containerToLoad);
        storedContainers.remove(containerToLoad);
        System.out.println("Container has been loaded on ship " + ship.toString());
    }

    /** Utilize single container */
    public void utilize(int id){
        Container containerToUtilize = getContainerById(storedContainers, id); // get container by id

        // If container exists utilize and send warning to sender
        if(containerToUtilize != null){
            storedContainers.remove(containerToUtilize);
            LocalDate today = LocalDate.now();
            IrresponsibleSenderWithDangerousGoods warn = new IrresponsibleSenderWithDangerousGoods(containerToUtilize,
                    containerToUtilize.getArriveDate(), today);
            containerToUtilize.sender.warnings.add(warn);
            System.out.println(containerToUtilize + " has been utilized");
        }else{
            System.out.println("Container id " + id + " does not exist in store");
        }
    }

    /** Search container by id */
    private Container getContainerById(ArrayList<Container> containers, int id){
        for(Container c : containers) {
            if(c.id == id){
                return c;
            }
        }
        return null;
    }

    // https://stackoverflow.com/questions/4258700/collections-sort-with-multiple-fields
    public void sortContainers() {
        if(storedContainers.size() > 0) {
            Collections.sort(storedContainers, new Comparator<Container>() {
                public int compare(final Container c1, final Container c2) {
                    if (c1.getArriveDate().compareTo(c2.getArriveDate()) == 0) {
                        return c1.sender.lastName.compareTo(c2.sender.lastName);
                    } else {
                        return c1.getArriveDate().compareTo(c2.getArriveDate());
                    }

                }
            });
        }
    }

    public void getStorageStatus(){
        storedContainers.forEach( (n) -> { System.out.println(n); } );
    }
}
