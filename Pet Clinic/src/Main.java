import java.util.Scanner;
import java.util.*;
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static PetDAO dao = new PetDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Pet Clinic Management =====\n" +
                    "1- Add Pet\n" +
                    "2- View All Pets\n" +
                    "3- Search Pets\n" +
                    "4- Update Pet\n" +
                    "5- Delete Pet\n" +
                    "0- Exit");

            int choice = safeInput();
            switch (choice) {
                case 1 -> addPetMenu();
                case 2 -> viewPets();
                case 3 -> searchPetsMenu();
                case 4 -> updatePetMenu();
                case 5 -> deletePetMenu();
                case 0 -> {
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice, try again!");
            }
        }
    }

    private static void addPetMenu() {
        System.out.print("Enter pet name: ");
        String name = scanner.next();

        System.out.print("Enter pet type: ");
        String type = scanner.next();

        System.out.print("Enter new age (positive number): ");
        int age = safeInput();
        while(age <= 0){
            System.out.print("Enter positive number: ");
            age = safeInput();
        }

        System.out.print("Enter owner's name: ");
        String owner = scanner.next();

        System.out.print("Enter owner's phone: ");
        String phone = scanner.next();

        dao.addPet(new Pet(name, type, age, owner,phone));
    }

    private static void viewPets() {
        List<Pet> pets = dao.getAllPets();
        if (pets.isEmpty()) {
            System.out.println("No pets found.");
            return;
        }
        for(Pet pet : pets) System.out.println(pet);
    }

    private static void searchPetsMenu() {
        System.out.print("Enter keyword to search by name, type, or owner: ");
        String keyword = scanner.next();
        List<Pet> pets = dao.searchPets(keyword);
        if (pets.isEmpty()) {
            System.out.println("No matching pets found.");
            return;
        }
        for(Pet pet : pets) System.out.println(pet);
    }

    private static void updatePetMenu() {
        viewPets();
        System.out.print("Enter pet ID to update: ");
        int id = safeInput();

        System.out.print("Enter new name: ");
        String name = scanner.next();
        System.out.print("Enter new type: ");
        String type = scanner.next();


        System.out.print("Enter new age (positive number): ");
        int age = safeInput();
        while(age <= 0){
            System.out.print("Enter positive number: ");
            age = safeInput();
        }


        System.out.print("Enter new owner's name: ");
        String owner = scanner.next();

        System.out.print("Enter owner's phone: ");
        String phone = scanner.next();

        dao.updatePet(new Pet(id, name, type, age, owner, phone));

    }

    private static void deletePetMenu() {
        viewPets();
        System.out.print("Enter pet ID to delete: ");
        int id = safeInput();
        dao.deletePet(id);
    }

    private static int safeInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input, enter a number: ");
            scanner.next();
        }

        return scanner.nextInt();
    }
}
