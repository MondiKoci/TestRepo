package weaponshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeaponShop {
    static Shop ShopManager;
    static int counter = 0;
    
/**
 *
 * @authors Max Grossman / Mondi Koci
 */

    public static  void promptEnterKey(){
        System.out.println("\n\nPress \"ENTER\" to go back...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
    public static void addWeapon(Scanner sc, int counter){
        String weaponName; int weaponRange; int weaponDamage; double weaponWeight; double weaponCost;
        int quantity;
        if(counter == 0){
            System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
            System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
        }
        else
            System.out.print("Please enter the NAME of a new Weapon ('end' to quit):");
        
        weaponName=sc.next();
        if(weaponName.compareTo("end") == 0){
            counter = 0;
            runGame(sc);
        }
        int loc = ShopManager.search(weaponName);
        if(loc != -1){
            System.out.println("Weapon with weapon name '" + weaponName + "' is already in store");
            quantity = getInteger(sc, "Please enter the quantity in stock");
            ShopManager.updateInventory(loc, quantity);
            addWeapon(sc, counter);
        }
        
        weaponRange= getInteger(sc,"Please enter the Range of the Weapon (0-10):"); 
        weaponDamage=getInteger(sc,"Please enter the Damage of the Weapon:"); 
        weaponWeight= getDouble(sc,"Please enter the Weight of the Weapon (in pounds):");
        weaponCost=getDouble(sc,"Please enter the Cost of the Weapon:");
        Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
        quantity=getInteger(sc,"Please enter the quantity in stock:"); 
        ShopManager.put(w,quantity);
        counter++;
        addWeapon(sc, counter);
    }

    public static void deleteItem(){}
    public static void buyItem(){}
    public static void viewBackpack(){
        BackPack b = new BackPack();
        b.add(new Weapon("Axe", 2,2,3,4));
        b.add(new Weapon("Sword", 2,2,5,4));
        b.add(new Weapon("Crossbow", 6,2,3,4));
        b.add(new Weapon("Knife", 2,2,3,7));
        System.out.print(b);
        promptEnterKey();

    }
    public static void viewPlayer(){}





    //============== VALIDATE USER INPUT ==================
    //Get integer input
    public static int getInteger(Scanner sc, String message, int max){
        System.out.print(message);
        int choice = 0;

           boolean x = true;
           while(x){
               String t = sc.nextLine();
               try{
                   choice = Integer.parseInt(t);
                   if(choice < 0 || choice > max){
                    System.out.print(message);
                    System.out.println(choice + "Invalid selection.");
                    x = true;
                   }else{
                      x = false;
                   }
               }catch(NumberFormatException f){
                System.out.print(message);
                System.out.println("Please select a choice from the menu above: ");
               }
            }
        return choice;
        }

    public static int getInteger(Scanner sc,String message){
        System.out.print(message);
        while (!sc.hasNextInt()) 
        {
            sc.nextLine(); //clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextInt();
    }

    public static double getDouble(Scanner sc,String message){
        System.out.print(message);
        while (!sc.hasNextDouble()) 
        {
            sc.nextLine(); //clear the invalid input ...
            System.out.print(message);
        }
        return sc.nextDouble();
    }
    //=================STRING MENUES======================

    //Main Menu
    public static String mainMenu(){
        String s = "====WELCOME TO THE WORLD OF PEACECRAFT====\n";
        s += "1: Add Items to the shop \n";
        s += "2: Delete Items from the shop \n";
        s += "3: Buy from the shop \n";
        s += "4: View backpack \n";
        s += "5: View Player\n";
        s += "6: exit\n";
        return s;
    }

    public static void runGame(Scanner sc){
        String menu = mainMenu();
        int choice = getInteger(sc, menu, 6);

        while(choice != 6){
            if(choice == 1){addWeapon(sc, counter);}
            if(choice == 2){deleteItem();}
            if(choice == 3){buyItem();}
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
        pname=sc.next();
        Player player = new Player(pname,45);
        Shop shop = new Shop();
        runGame(sc);
        player.printCharacter();

    }
    
}
