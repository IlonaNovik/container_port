package GUI;

import GUI.Containers.*;

import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        /** Port Haifa initialized */
        Storage storagePortHaifa = new Storage(20, 5, 10, 14);
        Carriage carriagePortHaifa = new Carriage(10);
        Port haifa = new Port("Haifa", "Israel", "Haifa", storagePortHaifa, carriagePortHaifa);

        /** Port Said initialized */
        Storage storagePortSaid = new Storage(20, 5, 10, 14);
        Carriage carriagePortSaid = new Carriage(10);
        Port said = new Port("Said", "Egipt", "Aleksandria", storagePortSaid, carriagePortSaid);

        /** Ship Black Mamba initialized */
        Ship blackMamba = new Ship("Black Mamba", haifa, haifa, said, 3, 4, 2,
                25, 150000);


        /** Senders initialized */
        Sender sarah = new Sender("Sarah", "Parker", "63548745214",
                "Ul. Mokotowska 23, 00-520 Warszawa", LocalDate.parse("1963-02-14"));
        Sender jack = new Sender("Jack", "Russel", "86545365414",
                "Ul. Jasna 18, 00-501 Warszawa", LocalDate.parse("1986-02-14"));

        /** Containers initialized */
        Basic b = new Basic(sarah, "Kom", 2403, 1600);
        Basic b1 = new Basic(sarah, "Fruits", 3540, 1600);
        Granular g = new Granular(sarah, "Motek", 5410, 1600, false,
                "sygnal", true, false);
        Heavy h = new Heavy(jack, "Last", 1600, 1600);
        Cooling c = new Cooling(jack, "Fruits", 1800, 1600, 10);
        Explosive e = new Explosive(jack, "Motek", 13690, 1600,
                "durable polyethylene");

        /** Load containers on ship Black Mamba */
        blackMamba.loadContainer(b);
        blackMamba.loadContainer(b1);
        blackMamba.loadContainer(g);
        blackMamba.loadContainer(h);
        blackMamba.loadContainer(c);
        blackMamba.loadContainer(e);

        /** Sort containers on Black Mamba */
        System.out.println(blackMamba.loadedContainers);
        blackMamba.sortContainers();
        System.out.println(blackMamba.loadedContainers);

        /** Ship Black Mamba arrived to Port Said */
        said.shipIn(blackMamba);

        /** See all arrived ships to Port Said */
        System.out.println(said.arrivedShips);

        /** See all containers loaded on Black Mamba ship */
        System.out.println("Ship " + blackMamba.toString() + ": " + blackMamba.loadedContainers);

        /** Unload single container from Black Mamba to Port Said storage */
        blackMamba.unloadContainer(10001, said.storage);

        System.out.println(said.toString() + " Storage " + ": " + said.storage.storedContainers);
        System.out.println("Ship " + blackMamba.toString() + ": " + blackMamba.loadedContainers);

        /** Unload containers from Black Mamba to Port Said storage */
        blackMamba.unloadContainers(said.storage);

        System.out.println(said.toString() + " Storage " + ": " + said.storage.storedContainers);
        System.out.println("Ship " + blackMamba.toString() + ": " + blackMamba.loadedContainers);

        said.storage.utilize(10004);
        said.storage.utilize(10005);
        System.out.println(said.toString() + " Storage " + ": " + said.storage.storedContainers); // storage after utilization

        System.out.println(jack.warnings);

        Basic b2 = new Basic(jack, "Fruits", 3540, 1600);
        Basic b3 = new Basic(jack, "Kom", 3540, 9000);
        Cooling k = new Cooling(sarah, "Fair", 3540, 1600, 26);

        blackMamba.loadContainer(b2);
        blackMamba.loadContainer(b3);
        blackMamba.loadContainer(k);

        System.out.println("Ship: " + blackMamba.toString() + ": " + blackMamba.loadedContainers);
        blackMamba.unloadContainers(said.storage);

        System.out.println("Ship: " + blackMamba.toString() + ": " + blackMamba.loadedContainers);
        System.out.println(said.toString() + " Storage " + ": " + said.storage.storedContainers); // storage after utilization


        Ship jenny = new Ship("Jenny", haifa, haifa, said, 3, 4, 2,
                25, 150000);
        Sender russell = new Sender("Russell", "Crowe", "71545365414",
                "Ul. Kazimierzowska 23, 02-255 Warszawa", LocalDate.parse("1971-02-14"));

        Basic b4 = new Basic(russell, "Fruits", 3540, 1600);
        Basic b5 = new Basic(russell, "Kom", 3540, 1600);
        Cooling c1 = new Cooling(russell, "Fair", 3540, 1600, 26);

        jenny.loadContainer(b4);
        jenny.loadContainer(b5);
        jenny.loadContainer(c1);

        System.out.println("Ship " + jenny.toString() + ": " + jenny.loadedContainers);


        said.shipIn(jenny);
        System.out.println(said.arrivedShips);

        jenny.unloadContainers(said.carriage);

        System.out.println(said.toString() + " Storage " + ": " + said.storage.storedContainers);
        System.out.println("Ship " + jenny.toString() + ": " + jenny.loadedContainers);

//        said.shipOut(blackMamba);
        System.out.println(said.arrivedShips);

        said.storage.loadContainer(10001, jenny);

        System.out.println(said.toString() + " Storage " + ": " + said.storage.storedContainers);
        System.out.println("Ship " + jenny.toString() + ": " + jenny.loadedContainers);

//        blackMamba.getShipInfo();
//        said.storage.getStorageStatus();


        System.out.println(said.storage.storedContainers.get(0).getArriveDate());
        said.storage.storedContainers.get(0).setArriveDate(LocalDate.parse("2019-01-08"));
        System.out.println(said.storage.storedContainers.get(0).getArriveDate());
        said.storage.storedContainers.get(1).setArriveDate(LocalDate.parse("2018-01-08"));

        System.out.println(said.arrivedShips);
        said.sortShips();

        System.out.println(said.storage.storedContainers);
        said.storage.sortContainers();
        System.out.println(said.storage.storedContainers);

    }
}
