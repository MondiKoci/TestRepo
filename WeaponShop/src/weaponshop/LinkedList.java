package weaponshop;

public class LinkedList {
     private WeaponNode head;

    public LinkedList(){
        head = null;
    }



    public void addFront(Weapon wep){
        WeaponNode newWeaponNode = new WeaponNode(wep);
        newWeaponNode.next = head;
        head = newWeaponNode;
        
    }


    public WeaponNode search(Object ID){
        WeaponNode scan, prev;
        scan = prev = head;

        while(scan != null && !scan.data.compareTo(ID))
            scan = scan.next;
        return scan;
    }


    public WeaponNode delete(Object ID){
        WeaponNode scan, prev;
        scan = prev = head;

        /*Scan through items until:
            - We reach the end of the list (scan item is null)
            - We reach the item we want to delete (scan.data is the value we are looking for)

            We want to keep track of the previous node with a prev variable
        */
        while(scan != null && !scan.data.compareTo(ID)){
            prev = scan;
            scan = scan.next;
        }

       //If we reach the end of the list the item was not found (return false) we are done.
        if(scan == null){
            return null;
        }
        WeaponNode output = scan;

        //If the scan item is the first item, Drop the first items reference, If not...
        if(scan == head){
            head = head.next; //or head = scan.next (same thing)
            return output;
        }
        //... Then drop the item by connecting prev's next to scan's next
       
        prev.next = scan.next;
        return output;
        

    }



    @Override
    public String toString(){
        String s= "";
        WeaponNode curr = head;
        while(curr != null){
         
            
            
            s += (curr.next == null) ? " (" +curr.data + ")" : " (" +curr.data + "),";
            curr=curr.next;
        }
        return s + " ]";
    }
    
}