package GUI;

import GUI.Containers.Container;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Port {
    public String name;
    public String country;
    public String city;
    public Storage storage;
    public Carriage carriage;
    public ArrayList<Ship> arrivedShips = new ArrayList<>();

    /** Port constructor */
    public Port(String name, String country, String city, Storage storage, Carriage carriage){
        this.name= name;
        this.country = country;
        this.city = city;
        this.storage = storage;
        this.carriage = carriage;
    }

    /** Ship arrives to port */
    public void shipIn(Ship ship){
        arrivedShips.add(ship);
        System.out.println("Ship " + ship.toString() + " arrived to port");
    }

    /** Ship departs from port */
    public void shipOut(Ship ship){
        arrivedShips.remove(ship);
        System.out.println("Ship " + ship.toString() + " departed");
    }

    public void sortShips(){
        // https://stackoverflow.com/questions/8432581/how-to-sort-a-listobject-alphabetically-using-object-name-field
        if (arrivedShips.size() > 0) {
            Collections.sort(arrivedShips, new Comparator<Ship>() {
                @Override
                public int compare(final Ship ship1, final Ship ship2) {
                    return ship2.name.compareTo(ship1.name);
                }
            });
        }

    }

//    public void saveCurrentState(){
//        try{
//            var oos = new ObjectOutputStream(new FileOutputStream("portState.txt"));
//
//            oos.writeObject(osoba);
//
////            ois = new ObjectInputStream(new FileInputStream("oosFile.txt"));
////
////            Osoba o = (Osoba)ois.readObject();
////            System.out.println(o);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(oos != null)
//                oos.close();
//            if(ois != null)
//                ois.close();
//        }
//    }

    @Override
    public String toString() {
        return "Port " + name;
    }
}
