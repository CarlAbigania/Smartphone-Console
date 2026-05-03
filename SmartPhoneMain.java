
import java.util.Scanner;

public class SmartPhoneMain {
    
    private static final Scanner read = new Scanner(System.in);
    private static SmartPhone smartphone = new SmartPhone();

    public static void main(String[] args) {
        System.out.println("Welcome to Smartphone Simulator!");
        
        while (true) {
            if (smartphone.isOn()) {
                showOnMenu();
            } else {
                showOffMenu();
            }
        }
    }

    private static void showOnMenu() {
        System.out.println("\n+-----------------------------------+");
        System.out.println("|          SMARTPHONE MENU          |");
        System.out.println("+-----------------------------------+");
        System.out.println("  [1] Create New Device");
        System.out.println("  [2] Device Info");
        System.out.println("  [3] Charge Battery");
        System.out.println("  [4] Power Button");
        System.out.println("  [5] Volume Settings");
        System.out.println("  [6] Camera");
        System.out.println("  [7] Photo Gallery");
        System.out.println("  [8] Calculator");
        System.out.println("  [9] Phone / Contacts");
        System.out.println("  [10] Shutdown Simulator");
        System.out.println("+-----------------------------------+");
        System.out.print("Select action: ");

        int choice = getSafeInt();

        switch (choice) {
            case 1: createDeviceFlow(); break;
            case 2: smartphone.DisplayAttributes(); break;
            case 3: smartphone.ChargingPhone(); break;
            case 4: smartphone.SwitchPower(); break;
            case 5: smartphone.AdjustVolume(); break;
            case 6: smartphone.UseCamera(); break;
            case 7: smartphone.ShowPhoneGallery(); break;
            case 8: smartphone.Calculator(); break;
            case 9: smartphone.MakeCall(); break;
            case 10: 
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("\n[!] Invalid selection.");
        }
    }

    private static void showOffMenu() {
        System.out.println("\n+-----------------------------------+");
        System.out.println("|          DEVICE IS OFF            |");
        System.out.println("+-----------------------------------+");
        System.out.println("  [1] Create New Device");
        System.out.println("  [2] View Specs");
        System.out.println("  [3] Charge Battery");
        System.out.println("  [4] Power Button");
        System.out.println("  [5] Exit Simulator");
        System.out.println("+-----------------------------------+");
        System.out.print("Select action: ");

        int choice = getSafeInt();

        switch (choice) {
            case 1: createDeviceFlow(); break;
            case 2: smartphone.DisplayAttributes(); break;
            case 3: smartphone.ChargingPhone(); break;
            case 4: smartphone.SwitchPower(); break;
            case 5: 
                System.out.println("Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("\n[!] Invalid selection.");
        }
    }

    private static void createDeviceFlow() {
        System.out.println("\n+-------------------------------+");
        System.out.println("|       CREATE NEW DEVICE       |");
        System.out.println("+-------------------------------+");
        System.out.println("  [1] Default (iPhone X, 64GB)");
        System.out.println("  [2] Custom All (Brand/Model/OS/Storage)");
        System.out.println("  [3] Custom Storage Only");
        System.out.println("  [4] Cancel");
        System.out.println("+-------------------------------+");
        System.out.print("Choice: ");

        int choice = getSafeInt();

        switch (choice) {
            case 1:
                smartphone = new SmartPhone();
                break;
            case 2:
                System.out.print("Enter Brand: ");
                String brand = read.nextLine();
                System.out.print("Enter Model: ");
                String model = read.nextLine();
                System.out.print("Enter OS: ");
                String os = read.nextLine();
                int storage = selectStorage();
                smartphone = new SmartPhone(brand, model, os, storage);
                break;
            case 3:
                int sOnly = selectStorage();
                smartphone = new SmartPhone(sOnly);
                break;
            case 4:
                break;
            default:
                System.out.println("\n[!] Invalid choice.");
        }
    }

    private static int selectStorage() {
        System.out.println("\nSelect Storage Capacity:");
        System.out.println("  [1] 64 GB  | [2] 128 GB | [3] 256 GB");
        System.out.print("Choice: ");
        int choice = getSafeInt();
        switch (choice) {
            case 1: return 64;
            case 2: return 128;
            case 3: return 256;
            default:
                System.out.println("Defaulting to 64 GB.");
                return 64;
        }
    }

    private static int getSafeInt() {
        try {
            String input = read.nextLine();
            return Integer.parseInt(input);
        } catch (Exception e) {
            return -1;
        }
    }
}