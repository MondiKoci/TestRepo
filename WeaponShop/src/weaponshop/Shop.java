package weaponshop;

/**
 *
 * @authors Max Grossman / Mondi Koci
 */

class Shop {

    private int maxItems;       // records the max size of the table
    private int numItems;      // records number of items in the list
    private ShopItem[] table; //hashtable itself
    private double loadFactor = 0.80;

    public Shop()
    {
        maxItems = 97;
        numItems = 0;
        table = new ShopItem[maxItems];
    }
    
    //Hash Function
    public int hashFunction(String name){
        int value = 0, weight = 1;
        for (int i = 0; i < name.length(); i++) {
            value += (name.charAt(i) - 'a' + 1 ) * weight;
            weight++;            
        }
        return value % maxItems;
    }
    
    //Insert items using quadratic probing
    public boolean put(Weapon item,int quantity)
    {
        if(numItems/maxItems >= loadFactor) return false;
        int count = 1;
        int startLoc = hashFunction(item.weaponName);
        int loc = startLoc;
        if(loc < 0)return false;
        while(table[loc] != null && table[loc].item != null){
            loc = (startLoc + count * count) % maxItems;
            count++;
        }
        //Check if locaiton is null or it contains a deleted (null) item
        if(table[loc] == null || table[loc].item == null){
            table[loc] = new ShopItem(item, quantity);
            return true;
        }
        table[loc].numberInStock++;
        return true;
    }
    
    //Update Inventory
    public void updateInventory(int position, int newStock){
        table[position].numberInStock = newStock;
    }
    
    //Search for an item
    public int search(String name){
        int count = 1;
            int startLoc = hashFunction(name);
            int loc = startLoc;
            //check if location is less than 0
            if(loc < 0 || loc > maxItems){return -1;}
            while(table[loc] != null && table[loc].item.weaponName.compareTo(name) != 0){
                loc = (startLoc + count * count) % maxItems;
                count++;
            }
            if(table[loc] == null)
                return -1;
            return loc;    
    }
    
    //Delete an item from the shop using item name
    public boolean delete(String name){
        int loc = search(name);
        if(loc == -1) return false;
        //Store an empty object
        table[loc] = new ShopItem();
        return true;
    }
    

    public int buy(String key)
    {
        int location = search(key); //gets location in table 
        if(location == -1) return -1; //Item does not exist
        if(table[location].numberInStock > 0){
            table[location].numberInStock--;
            return location; // Item found return position
        }
        return -2; //Item out of stock
    }
    
    public Weapon getItem(int index){
        return table[index].item;
    }

    public void printTable()
    {
        int count = 0;
        for (int x = 0; x < numItems; x++)
        {
                System.out.println("Name: " +table[x].item.weaponName+"   Damage:"+table[x].item.damage+"    Cost:"+table[x].item.cost+"     Quantity in stock:"+table[x].numberInStock);
        }
    }

    @Override
    public String toString(){
        String output = Pretty.fill(64, "-") + "\n" + Pretty.UI(64, "Shop Inventory: ", 3, "%", true) +  Pretty.UI(64, "", 3, "%", true);
        for (int i=0;  i< maxItems; i++){
            if(table[i] != null){
                output +=
                Pretty.UI(64, Pretty.fill(32, "-"), 3, "%", true) +
                table[i].item.PrettyPrint(64, "%", 32, ":", "-") +
                Pretty.UI(64, Pretty.UI(32, "In Stock: " +table[i].numberInStock, 3, ":", false), 3, "%", true) +
                Pretty.UI(64, Pretty.fill(32, "-"), 3, "%", true) +
                Pretty.UI(64, "", 3, "%", true);
            }
        }
        return output + 
        Pretty.UI(64, "", 3, "%", true) +
        Pretty.fill(64, "-") + "\n";

    }


    public String itemList(){
        String output = Pretty.UI(64, Pretty.fill(45, "-"), 3, "%", true);
        output +=
                Pretty.UI(64, 
                    Pretty.UI(15," Name", 1, "|", false) +
                    Pretty.UI(15,"Price", 3, "", false) +
                    Pretty.UI(15,"Stock ", 2, "|", false), 
                3, "%", true) +
                Pretty.UI(64, Pretty.fill(45, "-"), 3, "%", true);

        for (int i=0;  i< maxItems; i++){
            if(table[i] != null){
                output +=
                Pretty.UI(64, 
                    Pretty.UI(15," " +  table[i].item.weaponName, 1, "|", false) +
                    Pretty.UI(15,"$"+ table[i].item.cost, 3, "", false) +
                    Pretty.UI(15,"x"+ table[i].numberInStock + " ", 2, "|", false), 
                3, "%", true);
            
            }
        }
        return output + 
        Pretty.UI(64, Pretty.fill(45, "-"), 3, "%", true);
    }

    


    
}
