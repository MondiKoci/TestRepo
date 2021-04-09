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
        while(table[loc] != null && !table[loc].item.compareTo(item)){
            loc = (startLoc + count * count) % maxItems;
            count++;
        }
        if(table[loc] == null){
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
    

    public ShopItem get(String key)
    {
        int location = search(key); //gets location in table 
        return location == -1 ? null : table[location];
    }

    public void printTable()
    {
        int count = 0;
        for (int x = 0; x < numItems; x++)
        {
                System.out.println("Name: " +table[x].item.weaponName+"   Damage:"+table[x].item.damage+"    Cost:"+table[x].item.cost+"     Quantity in stock:"+table[x].numberInStock);
        }
    }
}
