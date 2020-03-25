package packOne;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Adopter {
    private int id;
    private String name;
    private ArrayList<Dog> adoptedDogsID;

    public Adopter(String name, ArrayList<Dog> adoptedDogsID) {
        this.id = idInitialization();
        this.name = name;
        this.adoptedDogsID = adoptedDogsID;
    }

    private void adoptDog(Dog dogToAdd, Adopter adopter) {
        adoptedDogsID.add(dogToAdd);
        dogToAdd.setAdopterID(adopter.id);
    }

    private void removeAdoptedDog(Dog dogToAdd, Adopter adopter) {
        adoptedDogsID.remove(dogToAdd);
        dogToAdd.setAdopterID(0);
    }

    private int idInitialization() {
        Random random = new Random();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");

        LocalDateTime today = LocalDateTime.now();

        String[] todayisTheDay = today.format(formatter).split("/");

        String finalId = "";

        for (int i = 0; i < todayisTheDay.length; i++) {
            finalId += todayisTheDay[i];
        }

        for (int i = 0; i < 4; i++) {
            finalId += random.nextInt(9);
        }

        try {
            //return Integer.parseInt(finalId);
            //System.out.println(finalId);// here is the id
            return Integer.parseInt(finalId);
        } catch (NumberFormatException e) {
            System.out.println("Error!");
            return 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Dog> getAdoptedDogsID() {
        return adoptedDogsID;
    }

    public void setAdoptedDogsID(ArrayList<Dog> adoptedDogsID) {
        this.adoptedDogsID = adoptedDogsID;
    }
}
