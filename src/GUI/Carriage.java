package GUI;

import GUI.Containers.Container;

import java.util.ArrayList;

public class Carriage {
    public int maxContainersAmount;   // max amount of containers can be loaded at once
    public ArrayList<Container> loadedContainers = new ArrayList<>();

    /** Carriage constructor */
    public Carriage(int maxContainersAmount){
        this.maxContainersAmount = maxContainersAmount;
    }

    /** Load single container */
    public void loadContainer(Container container){
        loadedContainers.add(container);
    }

    /** Load multiple containers */
    public void loadContainers(ArrayList<Container> containers){
        loadedContainers.addAll(containers);
    }
}
