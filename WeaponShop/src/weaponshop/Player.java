
package weaponshop;

/**
 *
 * @authors Max Grossman / Mondi Koci
 */
class Player
    {
        public String name;
        public BackPack backpack;
        public int numItems;
        public double money;

        public Player(String n, double m)
        {
            name = n;
            money = m;
            numItems = 0;
            backpack = new BackPack();
        }

        public void buy(Weapon w)
        {
            backpack.add(w);
            System.out.println(w.weaponName+" bought...");
            
            numItems++;
            System.out.println(numItems);
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

