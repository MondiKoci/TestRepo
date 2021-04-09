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
 * @authors Max Grossman / Mondi Koci
 */

    public static  void promptEnterKey(){
        System.out.println("Press \"ENTER\" to go back...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void addItems(){
        System.out.println("HELLO");

        promptEnterKey();
    }
    public static void deleteItem(){}
    public static void buyItem(){}
    public static void viewBackpack(){}
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
            if(choice == 1){addItems();}
            if(choice == 2){deleteItem();}
            if(choice == 3){buyItem();}
            if(choice == 4){viewBackpack();}
            if(choice == 5){viewPlayer();}

            choice = getInteger(sc, menu, 6);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pname;
        System.out.println("Please enter Player name:");
        pname=sc.next();
        Player player = new Player(pname,45);
        Shop shop = new Shop();
        runGame(sc);
        player.printCharacter();

    }
    
}
