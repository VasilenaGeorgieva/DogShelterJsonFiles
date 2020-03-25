package packOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Dog implements Serializable {
    private long id;
    private String name;
    private LocalDate birthday;
    private String species;
    private int AdopterID;
    private int WorkerID;

    public Dog(String name, LocalDate birthday, String species) {
        this.name = name;
        this.birthday = birthday;
        this.species = species;
        this.id = idInitialization(birthday);
    }

    public int getAge(LocalDate birthday, LocalDate now) {
        Period diff = Period.between(birthday, now);
        return diff.getYears();
    }

    private long idInitialization(LocalDate birthday) {
        Random random = new Random();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");

        String[] birthdayToString = birthday.format(formatter).split("/");

        String finalId = "";

        for (int i = 0; i < birthdayToString.length; i++) {
            finalId += birthdayToString[i];
        }

        //System.out.println(finalId); //prints the id without 4 more digits

        for (int i = 0; i < 4; i++) {
            finalId += random.nextInt(9);
        }

        try {
            //return Integer.parseInt(finalId);
            //System.out.println(finalId);// here is the id
            return Long.parseLong(finalId);// it should be a string !!!
        } catch (NumberFormatException e) {
            System.out.println("Error!");
            return 0;
        }
    }

    public long getId() {
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAdopterID() {
        return AdopterID;
    }

    public void setAdopterID(int adopterID) {
        AdopterID = adopterID;
    }

    public int getWorkerID() {
        return WorkerID;
    }

    public void setWorkerID(int workerID) {
        WorkerID = workerID;
    }
}
