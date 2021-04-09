package weaponshop;

/**
 *
 * @authors Max Grossman / Mondi Koci
 */
public class BackPack {
  
        private LinkedList[] table;
        private double weight;
        private int numItems;
        private boolean virtualSuccess;
        private int maxSize = 30;
        private double maxWeight = 90;
    
    
        public BackPack(){
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

      
        private boolean canAdd(double inputWeight){
            return (hasSize() && hasWeight(inputWeight));
        }


        public boolean hasSize(){return numItems < maxSize; }
        public boolean hasWeight(double inputWeight){ return maxWeight >= (this.weight + inputWeight); }

        
    
    
        public boolean add(Weapon wep){
            if (!this.canAdd(wep.weight)) return false;
            int loc = this.hashFunction(wep.getID());
            
            if(this.table[loc] == null)
                this.table[loc] = new LinkedList();
            
            this.table[loc].addFront(wep);
            this.virtualSuccess = false;
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
       
        @Override
        public String toString(){
            String output = Pretty.fill(64, "-") + "\n" + Pretty.UI(64, "Backpack's Contents: ", 3, "|", true) +  Pretty.UI(64, "", 3, "|", true);
            for (int i=0;  i< maxSize; i++){
                if(table[i] != null){
                    output += table[i].PrettyPrint(64, "|", 32, "*");
                }
            }

            return output + 
            Pretty.UI(64, "", 3, "|", true) +
            Pretty.UI(64, "Backpack Total Items: " + this.numItems + " / " + this.maxSize + " ", 2, "|", true) +
            Pretty.UI(64, "Backpack Total Weight: " + this.weight + " / " + this.maxWeight + " ", 2, "|", true) +
            Pretty.fill(64, "-") + "\n";

        }


        public String itemList(){
            return itemList(64, "%", 34, "|","-");
        }

        public String itemList(int outerSize, String outerBorder, int innerSize, String innerBorder, String underline){
            String output = Pretty.UI(outerSize, Pretty.fill(innerSize, underline), 3, outerBorder, true) +
            Pretty.UI(outerSize, 
                Pretty.UI(innerSize/2," Name", 1, "|", false) +
                Pretty.UI(innerSize/2,  "Weight ", 2, "|", false), 
            3, outerBorder, true) +
            Pretty.UI(outerSize, Pretty.fill(innerSize, underline), 3, outerBorder, true);
            int count = 0;
            for (int i=0;  i< maxSize; i++){
                if(table[i] != null){
                    output += table[i].listPrint(outerSize, outerBorder, innerSize, innerBorder);
                    count++;
                }
            }
            
            if(count == 0)
                output+= Pretty.UI(outerSize, Pretty.UI(innerSize, "Empty", 3, innerBorder, false), 3, outerBorder, true);
            
                return output + 
            Pretty.UI(outerSize, Pretty.fill(innerSize, underline), 3, outerBorder, true);
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public int getNumItems() {
            return numItems;
        }

        public void setNumItems(int numItems) {
            this.numItems = numItems;
        }

        public int getMaxSize() {
            return maxSize;
        }

        public double getMaxWeight() {
            return maxWeight;
        }
      

    }


