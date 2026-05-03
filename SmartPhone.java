
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmartPhone {
    
    // Class fields
    private String brand;
    private String model;
    private String os;
    private double storageCapacity;
    private double storageCapacityInMB;
    private int usedStorage;
    private int volume;
    private int batteryPercentage;
    private boolean isOn;
    private boolean isCallInProgress;
    private List<String> photoGallery = new ArrayList<>();
    
    // Static scanner for shared input handling
    private static final Scanner read = new Scanner(System.in);

    // Default Constructor
    public SmartPhone() { 
        this.brand = "iPhone";
        this.model = "X";
        this.os = "iOS";
        this.storageCapacity = 64;
        this.storageCapacityInMB = this.storageCapacity * 1000;
        this.isCallInProgress = false;
        this.volume = 16;
        this.batteryPercentage = 0;
        this.isOn = false;
        
        System.out.println("\n[SYSTEM]: Default Smartphone created!");
        DisplayAttributes();
    }
    
    // Constructor with full attributes
    public SmartPhone(String brand, String model, String os, int storageCapacity) {
        this.brand = brand;
        this.model = model;
        this.os = os;
        this.storageCapacity = storageCapacity;
        this.storageCapacityInMB = this.storageCapacity * 1000;
        this.isCallInProgress = false;
        this.volume = 16;
        this.batteryPercentage = 0;
        this.isOn = false;
        
        System.out.println("\n[SYSTEM]: Custom Smartphone created!");
        DisplayAttributes();
    }
    
    // Constructor with storage only
    public SmartPhone(int storageCapacity) {
        this.brand = "iPhone";
        this.model = "X";
        this.os = "iOS";
        this.storageCapacity = storageCapacity;
        this.storageCapacityInMB = this.storageCapacity * 1000;
        this.isCallInProgress = false;
        this.volume = 16;
        this.batteryPercentage = 0;
        this.isOn = false;
        
        System.out.println("\n[SYSTEM]: Smartphone created with custom storage!");
        DisplayAttributes();
    }

    // --- Core Logic Methods ---

    public void ChargingPhone() {
        if (this.batteryPercentage >= 100) {
            System.out.println("\n[SYSTEM]: The device is already fully charged (100%)!");
        } else {
            this.batteryPercentage = 100;
            System.out.println("\n[SYSTEM]: Charging... Device is now fully charged!");
            if (isOn) DisplayAttributes();
        }
    }
    
    public void SwitchPower() {
        if (this.batteryPercentage > 0) {
            this.isOn = !this.isOn;
            System.out.println("\n[SYSTEM]: The device is now turned " + (this.isOn ? "ON!" : "OFF!"));
            if (this.isOn) {
                DisplayAttributes();
            }
        } else {
            System.out.println("\n[SYSTEM]: Battery is empty! Please charge your device.");
            this.isOn = false;
        }
    }

    private void useBattery(int amount) {
        if (!isOn) return;
        this.batteryPercentage -= amount;
        if (this.batteryPercentage <= 0) {
            this.batteryPercentage = 0;
            this.isOn = false;
            System.out.println("\n[SYSTEM]: Battery critically low! Device is shutting down...");
        }
    }

    public void AdjustVolume() {
        if (!this.isOn) {
            System.out.println("\n[SYSTEM]: Please turn on the phone first");
            return;
        }

        boolean isOpen = true;
        do {
            System.out.println("\n+---------------------------+");
            System.out.println("|      VOLUME CONTROL       |");
            System.out.println("+---------------------------+");
            System.out.println("  Current Volume: " + volume);
            System.out.println("  [1] Volume Up");
            System.out.println("  [2] Volume Down");
            System.out.println("  [3] Back to Menu");
            System.out.println("+---------------------------+");
            System.out.print("Select: ");
            
            try {
                int response = Integer.parseInt(read.nextLine());
                if (response == 1) {
                    if (volume >= 16) System.out.println("\n[SYSTEM]: Maximum volume reached!");
                    else {
                        volume += 1;
                        useBattery(1);
                    }
                } else if (response == 2) {
                    if (volume <= 0) System.out.println("\n[SYSTEM]: Muted!");
                    else {
                        volume -= 1;
                        useBattery(1);
                    }
                } else if (response == 3) {
                    isOpen = false;
                } else {
                    System.out.println("\n[SYSTEM]: Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("\n[SYSTEM]: Please enter a valid number.");
            }
            if (!isOn) break;
        } while (isOpen);
    }

    public void UseCamera() {
        if (!this.isOn) {
            System.out.println("\n[SYSTEM]: Please turn on the phone first");
            return;
        }

        boolean appOpened = true;
        do {
            System.out.println("\n+---------------------------+");
            System.out.println("|          CAMERA           |");
            System.out.println("+---------------------------+");
            System.out.println("  [1] Take Photo (5MB)");
            System.out.println("  [2] Record Video (15MB)");
            System.out.println("  [3] Exit");
            System.out.println("+---------------------------+");
            System.out.print("Enter your choice: ");
            
            try {
                int cameraChoice = Integer.parseInt(read.nextLine());
                if (cameraChoice == 1) {
                    System.out.print("Enter filename: ");
                    String filename = read.nextLine();
                    StorePhotos(filename, 5);
                    useBattery(2);
                } else if (cameraChoice == 2) {
                    System.out.print("Enter filename: ");
                    String filename = read.nextLine();
                    StoreVideos(filename, 15);
                    useBattery(5);
                } else if (cameraChoice == 3) {
                    appOpened = false;
                } else {
                    System.out.println("\n[SYSTEM]: Invalid Choice!");
                }
            } catch (Exception e) {
                System.out.println("\n[SYSTEM]: Please enter a valid number.");
            }
            if (!isOn) break;
        } while (appOpened);
    }

    private void StorePhotos(String filename, int size) {
        if (this.usedStorage + size <= this.storageCapacityInMB) {
            this.photoGallery.add(filename + ".jpg");
            this.usedStorage += size;
            System.out.println("\n[SYSTEM]: Photo saved: " + filename + ".jpg");
        } else {
            System.out.println("\n[ERROR]: Not enough storage!");
        }
    }

    private void StoreVideos(String filename, int size) {
        if (this.usedStorage + size <= this.storageCapacityInMB) {
            this.photoGallery.add(filename + ".mp4");
            this.usedStorage += size;
            System.out.println("\n[SYSTEM]: Video saved: " + filename + ".mp4");
        } else {
            System.out.println("\n[ERROR]: Not enough storage!");
        }
    }

    public void ShowPhoneGallery() {
        if (!this.isOn) {
            System.out.println("\n[SYSTEM]: Please turn on the phone first!");
            return;
        }

        while (true) {
            double availableGB = (this.storageCapacityInMB - this.usedStorage) / 1000.0;
            System.out.println("\n=========== GALLERY ===========");
            if (photoGallery.isEmpty()) System.out.println("  (Empty)");
            else {
                for (int i = 0; i < photoGallery.size(); i++) {
                    System.out.println("  [" + (i + 1) + "] " + photoGallery.get(i));
                }
            }
            System.out.println("-------------------------------");
            System.out.println(" Used: " + usedStorage + " MB");
            System.out.println(" Free: " + String.format("%.2f", availableGB) + " GB");
            System.out.println("\n [1-N] View Info | [D] Delete | [B] Back");
            System.out.print("Select: ");
            
            String input = read.nextLine().toUpperCase();
            if (input.equals("B")) break;
            
            if (input.equals("D")) {
                if (photoGallery.isEmpty()) {
                    System.out.println("[SYSTEM]: Gallery is empty.");
                    continue;
                }
                System.out.print("Enter number to delete: ");
                try {
                    int idx = Integer.parseInt(read.nextLine()) - 1;
                    if (idx >= 0 && idx < photoGallery.size()) {
                        String removed = photoGallery.remove(idx);
                        int freed = removed.endsWith(".mp4") ? 15 : 5;
                        usedStorage -= freed;
                        System.out.println("[SYSTEM]: Deleted " + removed);
                    } else {
                        System.out.println("[SYSTEM]: Invalid index.");
                    }
                } catch (Exception e) {
                    System.out.println("[SYSTEM]: Invalid input.");
                }
            }
            useBattery(1);
            if (!isOn) break;
        }
    }

    public void Calculator() {
        if (!this.isOn) {
            System.out.println("\n[SYSTEM]: Please turn on the phone first!");
            return;
        }

        boolean appOpened = true;
        do {
            System.out.println("\n+---------------------------+");
            System.out.println("|        CALCULATOR         |");
            System.out.println("+---------------------------+");
            System.out.println("  [1] + | [2] - | [3] * | [4] /");
            System.out.println("  [5] Exit");
            System.out.println("+---------------------------+");
            System.out.print("Select: ");
            
            try {
                int op = Integer.parseInt(read.nextLine());
                if (op == 5) break;
                if (op < 1 || op > 4) continue;

                System.out.print("Num 1: ");
                double n1 = Double.parseDouble(read.nextLine());
                System.out.print("Num 2: ");
                double n2 = Double.parseDouble(read.nextLine());

                switch (op) {
                    case 1: System.out.println("Result: " + (n1 + n2)); break;
                    case 2: System.out.println("Result: " + (n1 - n2)); break;
                    case 3: System.out.println("Result: " + (n1 * n2)); break;
                    case 4: 
                        if (n2 == 0) System.out.println("[ERROR]: Divide by zero!");
                        else System.out.println("Result: " + (n1 / n2)); 
                        break;
                }
                useBattery(1);
            } catch (Exception e) {
                System.out.println("\n[SYSTEM]: Invalid input!");
            }
            if (!isOn) break;
        } while (appOpened);
    }

    public void MakeCall() {
        if (!this.isOn) {
            System.out.println("\n[SYSTEM]: Please turn on the phone first!");
            return;
        }

        while (true) {
            System.out.println("\n+---------------------------+");
            System.out.println("|          PHONE            |");
            System.out.println("+---------------------------+");
            System.out.println("  Status: " + (isCallInProgress ? "ON CALL" : "IDLE"));
            System.out.println("  [1] Dial  [2] End  [3] Back");
            System.out.println("+---------------------------+");
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(read.nextLine());
                if (choice == 1) {
                    if (!isCallInProgress && batteryPercentage >= 5) {
                        isCallInProgress = true;
                        useBattery(5);
                        System.out.println("\n[DIALING]...");
                    } else if (isCallInProgress) {
                        System.out.println("\n[SYSTEM]: Call already active.");
                    } else {
                        System.out.println("\n[SYSTEM]: Low battery!");
                    }
                } else if (choice == 2) {
                    isCallInProgress = false;
                    System.out.println("\n[SYSTEM]: Call ended.");
                } else return;
            } catch (Exception e) {
                System.out.println("\n[SYSTEM]: Invalid input!");
            }
            if (!isOn) break;
        }
    }

    public void DisplayAttributes() {
        System.out.println("\n================================");
        System.out.println("        DEVICE INFORMATION      ");
        System.out.println("================================");
        System.out.println(" Brand      : " + brand);
        System.out.println(" Model      : " + model);
        System.out.println(" OS         : " + os);
        System.out.println(" Power      : " + (isOn ? "ON" : "OFF"));
        System.out.println(" Storage    : " + storageCapacity + " GB");
        System.out.println(" Volume     : " + volume + "/16");
        System.out.println(" Battery    : " + batteryPercentage + "%");
        System.out.println(" Call Status: " + (isCallInProgress ? "Active" : "Idle"));
        System.out.println("================================");
    }

    // --- Getters & Setters ---
    public boolean isOn() { return isOn; }
}