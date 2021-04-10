package weaponshop;

/**
 *
 * @authors Max Grossman / Mondi Koci / Frederic Knoestah
 */

class Weapon
    {
        public String weaponName; 
        public int range;
        public int damage;
        public double weight;
        public double cost;

        public Weapon(String n, int rang, int dam, double w, double c)
        {
            weaponName = n;
            damage = dam;
            range = rang;
            weight = w;
            cost = c;
        }
        
        public Weapon(){
            weaponName = null;
            damage = -1;
            range = -1;
            weight = -1;
            cost = -1;
        }

    public String getID(){
        return (this.weaponName == null ) ? null : this.weaponName + this.range + this.damage  + this.weight + this.cost;
    }
    
    public boolean compareTo(Object obj){ 
        if(obj == null) return false;
        if (obj.getClass() == this.getClass()){ 
            Weapon other = (Weapon)obj; //Cast
            if(other.getID() == null || this.getID() == null)
                return this.getID() == other.getID(); 
            return this.getID().compareTo(other.getID()) == 0; 
        }
        if (obj.getClass() == "".getClass()){ 
            if (this.getID() == null) return false; 
            return this.getID().compareTo((String)obj) == 0; 
        }
        return false;
    }


    public String PrettyPrint(int outerSize, String outerBorder, int innerSize, String innerBorder){
        return PrettyPrint(outerSize, outerBorder, innerSize, innerBorder, innerBorder);
    }


//(int fill, String str, int pos, String border, boolean newline)
    public String PrettyPrint(int outerSize, String outerBorder, int innerSize, String innerBorder, String underline) {
       return 
       Pretty.UI(outerSize,
       Pretty.UI(innerSize, innerBorder + " " + this.weaponName + " " + innerBorder, 3, innerBorder, false), 
       3, outerBorder, true) + 
       
        Pretty.UI(outerSize,
            Pretty.UI(innerSize,
                Pretty.fill((innerBorder + " " + this.weaponName + " " + innerBorder).length(), underline),
            3, innerBorder, false),
       3, outerBorder, true) + 

       Pretty.UI(outerSize,
       Pretty.UI(innerSize, "", 3, innerBorder, false),
       3, outerBorder, true) + 

       Pretty.UI(outerSize,
       Pretty.UI(innerSize, "Range: " + this.range, 3, innerBorder, false),
       3, outerBorder, true) + 

       Pretty.UI(outerSize,
       Pretty.UI(innerSize, "Damage: " + this.damage, 3, innerBorder, false),
       3, outerBorder, true) + 

       Pretty.UI(outerSize,
       Pretty.UI(innerSize, "Weight: " + this.weight, 3, innerBorder, false),
       3, outerBorder, true) + 
       
       Pretty.UI(outerSize,
       Pretty.UI(innerSize, "Cost: " + this.cost, 3, innerBorder, false),
       3, outerBorder, true);
    }

    @Override
    public String toString(){
        return (this.weaponName == null ) ? "" : 
        "Name: " + this.weaponName + 
        " || Range: " + this.range + 
        " || Damage: " + this.damage + 
        " || Weight: " + this.weight + 
        " || Cost: " + this.cost;
    }
}
