package GUI;

import GUI.Containers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ship {
    private static List<Ship> extent = new ArrayList<>();

    public int id;
    public String name;
    public Port homePort;
    public Port rootLocation;
    public Port targetLocation;
    public ArrayList<Container> loadedContainers = new ArrayList<>();

    public int maxToxicExplosive;  // the maximum number of containers with toxic or explosive cargo
                                   //              that can be loaded within the ship

    public int maxHeavy;           //  maximum number of heavy containers
    public int maxCooling;         // the maximum number of containers requiring connection to the electricity
                                   //              network
    public int maxAmount;          // maximum number of all containers
    public int maxWeight;          // the maximum weight capacity of the vessel

    /** Constructor Ship */
    public Ship(String name, Port homePort, Port rootLocation, Port targetLocation, int maxToxicExplosive,
                int maxHeavy, int maxCooling, int maxAmount, int maxWeight){
        this.id = setId();
        this.name = name;
        this.homePort = homePort;
        this.rootLocation = rootLocation;
        this.targetLocation = targetLocation;
        this.maxToxicExplosive = maxToxicExplosive;
        this.maxHeavy = maxHeavy;
        this.maxCooling = maxCooling;
        this.maxAmount = maxAmount;
        this.maxWeight = maxWeight;

        extent.add(this);
    }

    /** Set unique id for every ship */
    private static int setId(){
        return 10000 + extent.size() + 1;
    }

    /** Load single container on ship */
    public void loadContainer(Container container) throws Exception {
        if (!exists(container)){            // checking if this container has been loaded already

            // Counting amounts of containers on ship with restrictions
            int currentWeightLoaded = 0;
            int currentToxicExplosive = 0;
            int currentHeavy = 0;
            int currentCooling = 0;

            for(Container c : loadedContainers){
                currentWeightLoaded += c.grossWeight;
                if(c instanceof Toxic || c instanceof Explosive){
                    currentToxicExplosive += 1;
                }
                if(c instanceof Heavy){
                    currentHeavy += 1;
                }
                if(c instanceof Cooling){
                    currentCooling += 1;
                }
            }

            // Validate if restricted containers don't exceed maximum amounts allowed on ship
            if(currentWeightLoaded + container.grossWeight > maxWeight)
                throw new Exception("Maximum weight of loaded containers exceeded, container id " +
                        container.id + " cannot be loaded");

            else if(loadedContainers.size() + 1 > maxAmount)
                throw new Exception("Maximum amount of loaded containers exceeded, container id " +
                        container.id + " cannot be loaded");

            else if(container instanceof Toxic && currentToxicExplosive + 1 > maxToxicExplosive
                    || container instanceof Explosive && currentToxicExplosive + 1 > maxToxicExplosive){
                throw new Exception(
                        "Maximum amount of loaded toxic and explosive containers exceeded, container id " +
                                container.id + "  cannot be loaded"
                );
            }else if(container instanceof Heavy && currentHeavy + 1 > maxHeavy){
                throw new Exception(
                        "Maximum amount of loaded heavy containers exceeded, container id " +
                                container.id + "  cannot be loaded"
                );
            }else if(container instanceof Cooling && currentCooling + 1 > maxCooling){
                throw new Exception(
                        "Maximum amount of loaded cooling containers exceeded, container id " +
                                container.id + "  cannot be loaded"
                );
            }else{
                loadedContainers.add(container);
                System.out.println(container.toString() + " has been loaded on the ship " + name);
            }
        }else{
            throw new Exception(container.toString() + " has been loaded already"
            );
        }
    }

    /** Unload and store single container by id */
    public void unloadContainer(int id, Storage storage) throws Exception {
        Container containerToLoad = getContainerById(loadedContainers, id); // get container object by id

        // If container found, validate sender's warnings and capacity of storage
        if(containerToLoad != null){
            if (containerToLoad.sender.warnings.size() == 2) {
                System.out.println(containerToLoad + " cannot be stored due to warnings");
            } else if (storage.storedContainers.size() + 1 > storage.maxStoredContainers) {
                System.out.println(containerToLoad + " cannot be stored, storage is full");
            } else {
                storage.storeContainer(containerToLoad);
                loadedContainers.remove(containerToLoad);
                System.out.println(containerToLoad + " stored successfully");
            }
        }else{
            System.out.println("Container id " + id + " does not exist on the ship");
        }
    }

    /** Unload on carriage single container by id */
    public void unloadContainer(int id, Carriage carriage) throws Exception {
        Container containerToLoad = getContainerById(loadedContainers, id); // get container object by id
        if(containerToLoad != null){
            carriage.loadContainer(containerToLoad);
            loadedContainers.remove(containerToLoad);
            System.out.println(containerToLoad + " loaded on carriage successfully");
        }else{
            System.out.println("Container id " + id + " does not exist on the ship");
        }
    }

    /** Unload all containers from ship to storage */
    public void unloadContainers(Storage storage) throws Exception {
        ArrayList<Container> containersToLoad = new ArrayList<>(); // arraylist for containers allowed to store
        ArrayList<Container> containersNotLoaded = new ArrayList<>(); // arraylist for containers not allowed to store

        // Validate if container's sender has any warnings
        for (Container c: loadedContainers){
            if(c.sender.warnings.size() < 2){
                containersToLoad.add(c);
            }else{
                containersNotLoaded.add(c);
            }
        }

        // Validate storage capacity and load all if no warnings
        if (containersNotLoaded.size() == 0){
            if (storage.storedContainers.size() + loadedContainers.size() < storage.maxStoredContainers){
                storage.storeContainers(loadedContainers);
                loadedContainers.removeAll(loadedContainers);
                System.out.println("All containers have been unloaded and stored successfully");
            }else{
                throw new Exception("Not enough place in storage, only " +
                        (storage.maxStoredContainers - storage.storedContainers.size()) + " containers can be stored");
            }
        }else{
            // If all senders have warnings, reject all containers to store
            if (containersNotLoaded.size() == loadedContainers.size()){
                StringBuilder temp = new StringBuilder();
                for (Container c: containersNotLoaded){
                    temp.append(c.id).append(" sender: ").append(c.sender.toString()).append(", ");
                }
                System.out.println("Containers: " + temp.substring(0, temp.length() - 2) +
                        " cannot be stored due to warnings");
            }else{
                // Validate storage capacity and load only containers with no warning, remainder stays on ship
                if (storage.storedContainers.size() + containersToLoad.size() < storage.maxStoredContainers){
                    storage.storeContainers(containersToLoad);
                    loadedContainers.removeAll(containersToLoad);
                    StringBuilder temp = new StringBuilder();

                    for (Container c: containersNotLoaded){
                        temp.append(c.id).append(" sender: ").append(c.sender.toString()).append(", ");
                    }
                    System.out.println("Containers have been unloaded and stored.\nContainers: "
                            + temp.substring(0, temp.length() - 2) + " cannot be stored due to warnings");
                }else {
                    throw new Exception("Not enough place in storage, only " +
                            (storage.maxStoredContainers - storage.storedContainers.size()) + " containers can be stored");
                }
            }
        }
    }

    /** Unload all containers from ship on carriage */
    public void unloadContainers(Carriage carriage) throws Exception {
        carriage.loadContainers(loadedContainers);
        loadedContainers.removeAll(loadedContainers);
        System.out.println("Containers have been unloaded on carriage");
    }

    /** Function for validating if container already loaded on ship */
    private boolean exists(Container container){
        boolean exists = false;
        for(Container c : loadedContainers) {
            if (container.id == c.id) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /** Function for searching container by id */
    private Container getContainerById(ArrayList<Container> containers, int id){
        for(Container c : containers) {
            if(c.id == id){
                return c;
            }
        }
        return null;
    }
    public void getShipInfo(){
        System.out.println("*******************************");
        System.out.println("Ship " + name);
        System.out.println("Containers loaded:  ");
        loadedContainers.forEach( (n) -> { System.out.println(n); } );
        System.out.println("*******************************");
    }

    public void sortContainers() {
        if(loadedContainers.size() > 0) {
            Collections.sort(loadedContainers, (c1, c2) -> {
                return c1.grossWeight - c2.grossWeight; // Ascending
            });
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
