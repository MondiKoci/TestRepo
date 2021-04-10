package weaponshop;

/**
 *
 * @authors Max Grossman / Mondi Koci / Frederic Knoestah
 */
public class ShopItem {
    Weapon item;
    public int numberInStock;
    
    //Empty object to be used as a placeholder for deleted items
    public ShopItem(){}
    
    public ShopItem(Weapon w, int nInStock){
        item=w;
        numberInStock=nInStock;
    }
}
