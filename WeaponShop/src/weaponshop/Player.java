
package weaponshop;

/**
 *
 * @authors Max Grossman / Mondi Koci / Frederic Knoestah
 */
class Player
    {
        public String name;
        public BackPack backpack;
        private boolean virtualSuccess;
        public int numItems;
        public double money;

        public Player(String n, double m)
        {
            name = n;
            money = m;
            numItems = 0;
            virtualSuccess = false;
            backpack = new BackPack();
        }

        public boolean buy(Weapon w)
        {
            if(backpack.add(w)){
                numItems++;
                withdraw(w.cost);
                return true;
            }
            return false;
        }

        

        public boolean virtualResult(){ 
            return this.virtualSuccess; 
        }
        
        public String virtualBuy(Weapon wep){
            this.virtualSuccess = false;
            if(wep.cost > this.money){ return "Weapon too expensive. Please try again";}
            if(!backpack.hasSize()){ return "Backpack is full!";}
            if(!backpack.hasWeight(wep.weight)){ return  wep.weaponName + " will Overweigh Backpack! ("+wep.weight+"lbs)\nPlease try again";}
            this.virtualSuccess = true;
            return wep.weaponName + " has been added to Backpack.";
        }
        





        public void withdraw(double amt)
        {
            money = money - amt;
        }

        public boolean inventoryFull()
        {
            return (numItems == 10) ;
        }

        public void printCharacter()
        {
            System.out.println(" Name:"+name+"\n Money:"+money);
            printBackpack();
        }

        public void printBackpack()
        {
             System.out.println(" "+name+", you own "+numItems+" Weapons:");
            for (int x = 0; x < numItems; x++)
            {
                // System.out.println(" "+backpack[x].weaponName);
            }
            System.out.println();
        }


        @Override
        public String toString(){
            return
            Pretty.fill(64,  "-") + "\n" +
            Pretty.UI(64, name, 3, "|", true) +
            Pretty.fill(64,  "-") + "\n" +
            Pretty.UI(64, "", 3, "|", true) +
            Pretty.UI(64, "         " + name + "'s Backpack:", 1, "|", true) +
            backpack.itemList(64, "|", 34, "|","-") +
            Pretty.UI(64, "Backpack Total Items: " + backpack.getNumItems() + " / " + backpack.getMaxSize() + "              ", 2, "|", true) +
            Pretty.UI(64, "Backpack Total Weight: " + backpack.getWeight()+ " / " + backpack.getMaxWeight() + "              ", 2, "|", true) +
            Pretty.UI(64, "", 3, "|", true) +
            Pretty.UI(64, "", 3, "|", true) +
            Pretty.UI(64, " " + name + "'s Coin(s): $" + money, 1, "|", true) +
            Pretty.UI(64, "", 3, "|", true) +
            Pretty.fill(64, "-") + "\n";
        }
    }