package weaponshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeaponShop {
    /**
     *
     * @authors Max Grossman / Mondi Koci / Frederic Knoestah
     */

    static Shop ShopManager;
    static Player player;
    
    // Helper method
    public static void promptEnterKey() {
        System.out.println("\n\nPress \"ENTER\" to go back...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    //Add item to the shop
    public static void addItem() {
        Scanner sc = new Scanner(System.in);
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
                System.out.println("Enter a new weapon name or 'end' to exit");
                weaponName = sc.next();
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

    // Delete Item from the shop
    public static void deleteItem() {
        Scanner sc = new Scanner(System.in);
        String itemName;
        System.out.println(Pretty.fill(64, "*"));
        System.out.println(ShopManager.itemList());
        System.out.println(Pretty.fill(64, "*"));
        
        System.out.print("Enter the NAME of the item you want to delete ('end' to exit): ");
        itemName  = sc.next();
        
        while(itemName.compareTo("end") != 0){
            if(ShopManager.delete(itemName)){
                System.out.println("Item deleted!");
            }else{
                System.out.println("Item was not found");
            }
            System.out.print("Enter the NAME of the item you want to delete ('end' to exit): ");
            itemName = sc.next();
        }
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
   
    
    public static void purchaseWeapon(){
        purchaseWeapon("Please enter the name of a Item You want to purchase (or 'end' to go back):");
    }

    public static boolean purchaseWeapon(String errMsg) {
       

        String input = getValidShopWeapon(purchaseMenu(), errMsg);
        if(input == null) return false;
        String shopPurchaseAttempt = ShopManager.virtualBuy(input, player.money);
        if(!ShopManager.virtualResult()) return purchaseWeapon(shopPurchaseAttempt + "  (or 'end' to go back):");
        Weapon wep = ShopManager.getItem(ShopManager.search(input));
        String backpackPurchaseAttempt = player.virtualBuy(wep);
        if(!player.virtualResult()) return purchaseWeapon(backpackPurchaseAttempt + "  (or 'end' to go back):");
        ShopManager.buy(input);
        player.buy(wep);
        return purchaseWeapon(shopPurchaseAttempt +", " +
            backpackPurchaseAttempt + "\n" +
            "Please enter the name of a Item You want to purchase (or 'end' to go back):"
            );
    }

    public static void viewPlayer() {



        System.out.print(player.backpack.itemList());
        promptEnterKey();
    }

    public static void REMOVEME() {
        ShopManager.put(new Weapon("Axe", 2, 2, 3, 400), 5);
        ShopManager.put(new Weapon("Sword", 2, 2, 500, 4), 5);
        ShopManager.put(new Weapon("Crossbow", 6, 89, 3, 4), 5);
        ShopManager.put(new Weapon("Knife", 2, 2, 3, 7), 5);
    }

    // ============== VALIDATE USER INPUT ==================
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

    public static String getValidShopWeapon(String message, String errorMsg){
        Scanner sc = new Scanner(System.in);
        return getValidShopWeapon(sc, message, errorMsg);
    }

    public static String getValidShopWeapon(Scanner sc, String message, String errorMsg){
        System.out.print(message);
        System.out.print(errorMsg + "\n");
        String name = sc.nextLine();
        if(name.compareTo("end") == 0) { return null; }
        int result = ShopManager.search(name);
        if(result == -1) return getValidShopWeapon(sc, message, "Item \"" + name +"\" Not found in shop please try again (or 'end' to go back):");
        return name;
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
    
    // Buy weapon menu
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
        Pretty.fill(64, "*") + "\n";
    }


    public static String viewPlayerString(){
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
        Pretty.fill(64, "*") + "\n";
    }

    




    public static void runGame(Scanner sc) {
        String menu = mainMenu();
        sc.nextLine();
        int choice = getInteger(sc, menu, 6);
        while(choice != 6){
            if(choice == 1){addItem();}
            if(choice == 2){deleteItem();}
            if(choice == 3){buyItem(sc);}
            if(choice == 4){viewBackpack();}
            if(choice == 5){viewPlayer();}
           
            choice = getInteger(sc, menu, 6);
        }
    }

    //MAIN
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShopManager = new Shop();
        String pname;
        System.out.println("Please enter Player name:");
        pname = sc.next();
        player = new Player(pname, 45);
        runGame(sc);
    }

}
