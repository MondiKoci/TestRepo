package weaponshop;

public class WeaponNode {
    public Weapon data;
    public WeaponNode next;

    public WeaponNode(Weapon data){
        this.data = data;
        this.next = null;
    }
    

}