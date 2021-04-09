package weaponshop;

/**
 *
 * @authors Max Grossman / Mondi Koci
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

//(int fill, String str, int pos, String border, boolean newline)
    public String PrettyPrint(String border){
       return 
       Pretty.UI(64, "Name: " + this.weaponName, 3, border, true) + 
       Pretty.UI(64, "Range: " + this.range, 3, border, true)  + 
       Pretty.UI(64, "Damage: " + this.damage, 3, border, true)  + 
       Pretty.UI(64, "Weight: " + this.weight, 3, border, true)  + 
       Pretty.UI(64, "Cost: " + this.cost, 3, border, true);
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
