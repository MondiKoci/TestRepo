package weaponshop;

import java.util.Scanner;

public class WeaponShop {
    
/**
 *
 * @authors Max Grossman / Mondi Koci
 */




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
        
    
public static void addWeapons(ArrayManager h, Scanner sc)
{
    System.out.println("***********WELCOME TO THE WEAPON ADDING MENU*********");
    String weaponName; int weaponRange; int weaponDamage; double weaponWeight; double weaponCost;
    int quantity;
    System.out.print("Please enter the NAME of the Weapon ('end' to quit):");
    weaponName=sc.next();
    while (weaponName.compareTo("end") != 0)
    {
        weaponRange= getInteger(sc,"Please enter the Range of the Weapon (0-10):"); 
        weaponDamage=getInteger(sc,"Please enter the Damage of the Weapon:"); 
        weaponWeight= getDouble(sc,"Please enter the Weight of the Weapon (in pounds):");
        weaponCost=getDouble(sc,"Please enter the Cost of the Weapon:");
        Weapon w = new Weapon(weaponName, weaponRange, weaponDamage, weaponWeight, weaponCost);
        quantity=getInteger(sc,"Please enter the quantity in stock:"); 
        h.put(w,quantity);
        System.out.print("Please enter the NAME of another Weapon ('end' to quit):");
        weaponName = sc.next();
    }
}



public static void showRoomMenu(ArrayManager ht,Player p){
    System.out.println("WELCOME TO THE SHOWROOM!!!!");
    ht.printTable();
    System.out.println("You have "+p.money+" money.");
    System.out.println("Please select a weapon to buy('end' to quit):");
}
        
public static void showRoom(ArrayManager ht, Player p,Scanner sc)
{
    String choice;
    showRoomMenu(ht,p);
    choice=sc.next();
    while (choice.compareTo("end") != 0 && !p.inventoryFull())
    {
        ShopItem si = ht.get(choice);
        if (si != null)
        {

                p.buy(si.item);
                p.withdraw(si.item.cost);
                si.numberInStock--;

        }
        else
        {
            System.out.println(" ** "+choice+" not found!! **" );
        }
        showRoomMenu(ht,p);
        choice = sc.next();
    }
    System.out.println("");
}




    public static void main(String[] args) {
        
        BackPack b = new BackPack();
        
        Weapon w1 = new Weapon("drde1", 5, 4, 9, 9); 
       
        Weapon w2 = new Weapon("l3uw", 3, 1, 1, 4);

        Weapon w3 = new Weapon("Bta", 6, 2, 4, 4);

        Weapon w4 = new Weapon("gfdf", 9, 6, 1, 4);

        System.out.println(b.hashFunction(w1.getID()));
        System.out.println(b.hashFunction(w2.getID()));
        System.out.println(b.hashFunction(w3.getID()));


       
       

        System.out.println(b.add(w1));
        System.out.println(b.add(w2));
        System.out.println(b.add(w3));
        System.out.println(b.add(w4));
        b.printTable();

        System.out.println(b.delete("l3uw", 3, 1, 1, 4));
        //b.printTable();

        System.out.println(b.retrieve("drde1", 5, 4, 9, 9));
        System.out.println(b.retrieve("l3uw", 3, 1, 1, 4));
        System.out.println(b.retrieve("Bta", 6, 2, 4, 4));
        System.out.println(b.retrieve("gfdf", 9, 6, 1, 4));
        






         /*
        Scanner sc = new Scanner(System.in);
            String pname;
            System.out.println("Please enter Player name:");
            pname=sc.next();
            Player pl= new Player(pname, 45);
            ArrayManager ht= new ArrayManager(101);
            addWeapons(ht,sc);
            showRoom(ht, pl,sc);
            pl.printCharacter();
        */
    }
    
}
