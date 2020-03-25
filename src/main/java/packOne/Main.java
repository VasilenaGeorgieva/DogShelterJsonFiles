package packOne;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private ArrayList<Dog> dogs = new ArrayList<>();
    private ArrayList<Worker> workers = new ArrayList<>();
    private ArrayList<Adopter> adopters = new ArrayList<>();

    private JSONArray dogsInShelter = new JSONArray();

    public static void main(String[] args) throws IOException{
        Main app = new Main();
        app.init();
    }

    public static void saveToJSONFile(String name, long id, LocalDate birthday, String species, JSONArray jsonArray) {
        JSONObject dogdetail = new JSONObject();
        dogdetail.put("name:", name);
        dogdetail.put("id:", id);
        dogdetail.put("birthday:", birthday);
        dogdetail.put("species:", species);

        JSONObject dogReal = new JSONObject();
        dogReal.put("Dog: ", dogdetail);

        jsonArray.add(dogReal);

        try (FileWriter file = new FileWriter("C:\\Users\\Hello\\Desktop\\inf.json")) {

            file.write(jsonArray.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToAFile(Object puppy) throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Hello\\Desktop\\inf.json");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(puppy);

        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.println("dog added successfully.");
    }

    public void init() throws IOException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Press a number and press ENTER to select an activity");
        System.out.println("1. Accept a new dog");
        System.out.println("2. Add a worker at the shelter");
        System.out.println("3. Register a new adopter");
        System.out.println("4. Change dog assignment to a worker");
        System.out.println("5. Register an adoption");
        System.out.println("6. Revert an adoption");
        System.out.println("How many steps do you want to make?");

        int steps = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < steps; i++) {
            int number = Integer.parseInt(scanner.nextLine());

            switch (number) {
                case 1:
                    addDog();
                    break;
                case 2:
                    addWorker();
                    break;
                case 3:
                    addAdopter();
                    break;
                case 4:
                    changeDogAssignment();
                    break;
                case 5:
                    registerAdoption();
                    break;
                case 6:
                    revertAdoption();
                    break;
                default:
                    init();
            }
        }
    }
    public void addDog() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please what is the name of the dog?");
        String name = scanner.nextLine();
        System.out.println("What is the dog's birthday?");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yy/MM/dd");
        LocalDate birthdayOfDog = LocalDate.parse(scanner.nextLine(), dateFormat);
        System.out.println("What kind of species is the dog?");
        String species = scanner.nextLine();//error
        Dog dog = new Dog(name, birthdayOfDog, species);
        dogs.add(dog);
        saveToJSONFile(name, dog.getId(), birthdayOfDog, species, dogsInShelter);
    }
    public void addWorker() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please what is the name of the worker?");
        String name = scanner.nextLine();
        Worker worker = new Worker(name, dogs);
        workers.add(worker);
    }
    public void addAdopter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please what is the name of the adopter?");
        String name = scanner.nextLine();
        Adopter adopter = new Adopter(name, dogs);
        adopters.add(adopter);
    }
    public void changeDogAssignment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of the dog whose assignment you want to change.");
        int index = Integer.parseInt(scanner.nextLine());
        Dog needAChange = dogs.get(index);
        needAChange.setWorkerID(0);
        System.out.println("Please enter the new assignment to this dog.");
        needAChange.setWorkerID(Integer.parseInt(scanner.nextLine()));
        System.out.println("Thank you!");
    }
    public void registerAdoption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of the dog whose assignment you want to adopt.");
        int index = Integer.parseInt(scanner.nextLine());
        Dog needAChange = dogs.get(index);
        int indexOfWorker = needAChange.getWorkerID();
        needAChange.setWorkerID(0);
        workers.remove(workers.get(indexOfWorker));
        System.out.println("Enter the index of the adopter.");
        int adopterIndex = Integer.parseInt(scanner.nextLine());
        needAChange.setAdopterID(adopters.get(adopterIndex).getId());
        System.out.println("Thanks!");

    }
    public void revertAdoption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the index of the dog whose assignment you want to remove.");
        int index = Integer.parseInt(scanner.nextLine());
        Dog needAChange = dogs.get(index);
        int indexOfAdopter = needAChange.getAdopterID();
        needAChange.setAdopterID(0);
        adopters.remove(adopters.get(indexOfAdopter));
        System.out.println("Enter the index of the worker you want to take care of this dog.");
        int workerIndex = Integer.parseInt(scanner.nextLine());
        needAChange.setWorkerID(adopters.get(workerIndex).getId());
        System.out.println("Thanks!");
    }
}
