package weaponshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeaponShop {
    static Shop ShopManager;
    static Player player;

    /**
     *
     * @authors Max Grossman / Mondi Koci
     */

    public static void promptEnterKey() {
        System.out.println("\n\nPress \"ENTER\" to go back...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void addWeapon(Scanner sc) {
        String weaponName; int weaponRange;int weaponDamage;double weaponWeight;double weaponCost;
        int quantity;
        System.out.println("============= ADD WEAPONS TO THE SHOP =============");
        System.out.println("Please enter a name for a wepon or 'end' to exit.");
        weaponName = sc.next();
        while(weaponName.compareTo("end") != 0){
            int location = ShopManager.search(weaponName);
            if(location != -1){
                System.out.println("Weapon already exists in the shop"
                        + "\nEnter the quantity to be added in stock.");
                quantity = getInteger(sc, "Please enter the quantity in stock:");
                ShopManager.updateInventory(location, quantity);
                System.out.println("Inventory updated.");
                System.out.println("Enter 'Y' to continue adding weapons ir 'N' to exit");
                String choice = sc.next();
                if(choice.compareTo("Y") == 0){
                    addWeapon(sc);
                }
                break;
            }else{
                weaponRange = getInteger(sc, "Please enter the Range of the Weapon (0-10):");
                weaponDamage = getInteger(sc, "Please enter the Damage of the Weapon:");
                weaponWeight = getDouble(sc, "Please enter the Weight of the Weapon (in pounds):");
                weaponCost = getDouble(sc, "Please enter the Cost of the Weapon:");
                Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
                quantity = getInteger(sc, "Please enter the quantity in stock:");
                ShopManager.put(w, quantity);
                System.out.println("Weapon added to the shop!\n");
                System.out.print("");
                System.out.println("Enter a new weapon name or 'end' to exit");
                weaponName = sc.next();
            }
            
        }  
    }

    // Anchor Logic
    public static void deleteItem() {
    }

    public static void buyItem(Scanner sc) {
        int choice = getInteger(sc, buyMenu(), 3);
        while(choice != 3)
        {
            if(choice == 1){
                System.out.print(ShopManager);
                promptEnterKey();
            }
            if(choice == 2){}
            choice = getInteger(sc, buyMenu(), 3);
        }
    }

    public static void viewBackpack() {
        System.out.print(player.backpack);
        promptEnterKey();
    }
    
    public static void purchaseWeapon() {
        
    }

    public static void viewPlayer() {
    }

    public static void REMOVEME() {
        ShopManager.put(new Weapon("Axe", 2, 2, 3, 4), 5);
        ShopManager.put(new Weapon("Sword", 2, 2, 5, 4), 5);
        ShopManager.put(new Weapon("Crossbow", 6, 2, 3, 4), 5);
        ShopManager.put(new Weapon("Knife", 2, 2, 3, 7), 5);
    }

    // ============== VALIDATE USER INPUT ==================
    // Get integer input

    public static int getInteger(Scanner sc, String message, int max) {
        return getIntegerHelper(sc, message, max, "Please Enter a Input from the list above:");
    }

    private static int getIntegerHelper(Scanner sc, String message, int max, String errorMsg) {
        System.out.print(message);
        System.out.print(errorMsg + "\n");
        int choice = 0;
        String t = sc.nextLine();
        try {
            choice = Integer.parseInt(t);
            if (choice > 0 && choice <= max)
                return choice;
            return getIntegerHelper(sc, message, max, "Number must be in valid range:");
        } catch (NumberFormatException f) {
            return getIntegerHelper(sc, message, max, "Please select a choice from the menu above: ");
        }
    }


    public static int getInteger(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            sc.nextLine(); // clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextInt();
    }

    public static double getDouble(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextDouble()) {
            sc.nextLine(); // clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextDouble();
    }
    // =================STRING MENUES======================

    // Main Menu
    public static String mainMenu() {
        String s = "====WELCOME TO THE WORLD OF PEACECRAFT====\n";
        s += "1: Add Items to the shop \n";
        s += "2: Delete Items from the shop \n";
        s += "3: Buy from the shop \n";
        s += "4: View backpack \n";
        s += "5: View Player\n";
        s += "6: exit\n";
        return s;
    }

    public static String buyMenu() {
        return "\n" + Pretty.UI(64, "~ Buy From Shop ~") + 
        "\n1: View Shop Showroom\n" + 
        "2: Purchase Weapon\n"+ 
        "3: Go Back\n";
    }

    




    public static void runGame(Scanner sc) {
        String menu = mainMenu();
        sc.nextLine();
        int choice = getInteger(sc, menu, 6);//!CHange to 6
        while(choice != 6){
            if(choice == 1){addWeapon(sc);}
            if(choice == 2){deleteItem();}
            if(choice == 3){buyItem(sc);}
            if(choice == 4){viewBackpack();}
            if(choice == 5){viewPlayer();}
            if(choice == 7){REMOVEME();}//!REMOVE

            choice = getInteger(sc, menu, 6);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShopManager = new Shop();
        String pname;
        System.out.println("Please enter Player name:");
        pname = sc.next();
        player = new Player(pname, 45);
        runGame(sc);
        // player.printCharacter();

    }

}
