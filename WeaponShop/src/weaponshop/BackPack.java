package weaponshop;

/**
 *
 * @authors Max Grossman / Mondi Koci
 */
public class BackPack {
  
        private LinkedList[] table;
        public double weight;
        private int numItems;
        int maxSize = 30;
        double maxWeight = 90;
    
    
        public  BackPack(){
            table = new LinkedList[maxSize];
            numItems = 0;
            weight = 0;
        }
       
        //NOTE THE needle will object where to stop searching
        //NOTE THE hashFrom will always be a string consisting of all attributes in a weapon to use while hashing
        private WeaponNode location(Object needle, String hashFrom){ 
            int loc = this.hashFunction(hashFrom);
            if (this.table[loc] == null) return null;
            return this.table[loc].search(needle);
            
        }

        public boolean hasRoom(double inputWeight){
            return (numItems < maxSize && maxWeight >= (this.weight + inputWeight ));
        }
    
    
        public boolean add(Weapon wep){
            if (!this.hasRoom(wep.weight)) return false;
            int loc = this.hashFunction(wep.getID());
            
            if(this.table[loc] == null)
                this.table[loc] = new LinkedList();
            
            this.table[loc].addFront(wep);
            numItems++;
            weight += wep.weight;
            return true;
        }
    
        //NOTE THE ID will always be a string consisting of all attributes in a weapon!
        public int hashFunction(String ID){
            int value=0;
            for (int index = 0; index < ID.length(); index++){
                value += (ID.charAt(index)+1) * (19*(index*index));
            }
            return value % maxSize ;
        }
    
        public Weapon retrieve(String name, int range, int damage, double weight, double cost){
            String ID = name + range + damage + weight + cost;
            WeaponNode found = this.location(ID, ID);
            return (found == null) ? null : found.data;
        }
    
        public boolean delete(String name, int range, int damage, double weight, double cost){
            String ID = name + range + damage + weight + cost;
            WeaponNode found = this.location(ID, ID);
            if (found == null) return false; 
            found = this.table[this.hashFunction(ID)].delete(ID);
            numItems--;
            this.weight -= found.data.weight;
            return true;
        }
       
       
        public void printTable(){
            System.out.println("Hash Table Contents");
            for (int x=0;x<maxSize;x++){
                System.out.println(x+"] "+table[x]);
            }
            System.out.println("");
        }
    }


