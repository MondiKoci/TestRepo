
package weaponshop;

/**
 *
 * @authors Max Grossman / Mondi Koci / Frederic Knoestah
 */
class Player
    {
        public String name;
        public BackPack backpack;
        public boolean virtualSuccess;
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

        public String buy(Weapon w)
        {
            String resultMsg = ";";// virtualAdd(w);
            if(virtualResult()){
                backpack.add(w);
                numItems++;
                withdraw(w.cost);
            }
            return resultMsg;
        }

        private boolean virtualResult(){ 
            this.virtualSuccess = false;
            return this.virtualSuccess; 
        }
        
        private String virtualBuy(Weapon wep){
            if(wep.cost > this.money){ return "Weapon too expensive";}
            if(!backpack.hasSize()){ return "Backpack is full!";}
            if(!backpack.hasWeight(wep.weight)){ return "Backpack Overweight";}
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
    }