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
    static int counter = 0;

    /**
     *
     * @authors Max Grossman / Mondi Koci
     */

    public static void promptEnterKey() {
        System.out.println("\n\nPress \"ENTER\" to go back...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void addWeapon(Scanner sc, int counter) {
        String weaponName;
        int weaponRange;
        int weaponDamage;
        double weaponWeight;
        double weaponCost;
        int quantity;
        if (counter == 0) {
            System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
            System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
        } else
            System.out.print("Please enter the NAME of a new Weapon ('end' to quit):");

        weaponName = sc.next();
        if (weaponName.compareTo("end") == 0) {
            counter = 0;
            return;
        }
        int loc = ShopManager.search(weaponName);
        if (loc != -1) {
            System.out.println("Weapon with weapon name '" + weaponName + "' is already in store");
            quantity = getInteger(sc, "Please enter the quantity in stock");
            ShopManager.updateInventory(loc, quantity);
            addWeapon(sc, counter);
        }

        weaponRange = getInteger(sc, "Please enter the Range of the Weapon (0-10):");
        weaponDamage = getInteger(sc, "Please enter the Damage of the Weapon:");
        weaponWeight = getDouble(sc, "Please enter the Weight of the Weapon (in pounds):");
        weaponCost = getDouble(sc, "Please enter the Cost of the Weapon:");
        Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
        quantity = getInteger(sc, "Please enter the quantity in stock:");
        ShopManager.put(w, quantity);
        counter++;
        System.out.println("Enter 'Y' to enter another weapon or 'N' to exit");
        String choice = sc.next();
        if(choice.compareTo("Y") == 0)addWeapon(sc, counter);
        else{return;}
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
            if(choice == 2){purchaseWeapon();}
            choice = getInteger(sc, buyMenu(), 3);
        }
    }

    public static void viewBackpack() {
        System.out.print(player.backpack);
        promptEnterKey();
    }
    
    public static void purchaseWeapon() {
        
        
        System.out.print(purchaseMenu());

        promptEnterKey();
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

    //public static String getValidShopWeapon(Scanner sc, String message, String errorMsg){


    //}





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
        "\n1: View Shop Show Room\n" + 
        "2: Purchase Weapon\n"+ 
        "3: Go Back\n";
    }

    public static String purchaseMenu(){
        return  
        Pretty.fill(64, "*") + "\n" +
        Pretty.UI(64, "", 3, "%", true) + 
        Pretty.UI(64, "        Shop Inventory:", 1, "%", true) +
        ShopManager.itemList() +
        Pretty.UI(64, "", 3, "%", true) + 
        Pretty.UI(64, "", 3, "%", true) +
        Pretty.UI(64, "         " + player.name + "'s Backpack:", 1, "%", true) +
        player.backpack.itemList() +
        Pretty.UI(64, "Backpack Total Items: " + player.backpack.getNumItems() + " / " + player.backpack.getMaxSize() + "              ", 2, "%", true) +
        Pretty.UI(64, "Backpack Total Weight: " + player.backpack.getWeight()+ " / " + player.backpack.getMaxWeight() + "              ", 2, "%", true) +
        Pretty.UI(64, "", 3, "%", true) +
        Pretty.UI(64, "", 3, "%", true) +
        Pretty.UI(64, " " + player.name + "'s Coin(s): $" + player.money, 1, "%", true) +
        Pretty.fill(64, "*") + "\n" +
        "Please enter the name of a Item You want to purchase (or 'end' to go back):" + "\n";
    }

    




    public static void runGame(Scanner sc) {
        REMOVEME();//!Remove when submission
        String menu = mainMenu();
        sc.nextLine();
        int choice = getInteger(sc, menu, 6);//!CHange to 6
        while(choice != 6){
            if(choice == 1){addWeapon(sc, counter);}
            if(choice == 2){deleteItem();}
            if(choice == 3){buyItem(sc);}
            if(choice == 4){viewBackpack();}
            if(choice == 5){viewPlayer();}
           

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
