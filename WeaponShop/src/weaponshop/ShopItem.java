/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weaponshop;

/**
 *
 * @author Bristle
 */
public class ShopItem {
    Weapon item;
    int numberInStock;
    
    public ShopItem(Weapon w, int nInStock){
        item=w;
        numberInStock=nInStock;
    }
}
