/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartObject implements Serializable{
    private Map<String, Integer> items;

    public CartObject() {
    }

    public Map<String, Integer> getItems() {
        return items;
    }

  
    public void addItemstoCart(String itemID, int quantity){
        //1.check item existed
        if (itemID == null){
            return;
        }
        if(itemID.trim().isEmpty()){
            return;
        }
        if(quantity <= 0){
            return;
        }
        //2.check items exited
         if(this.items == null){
             this.items=new HashMap<>();
         }//end item has already existed
         //3.check item existed in the 
         if(this.items.containsKey(itemID)){
             quantity= quantity + this.items.get(itemID);
         }//end item has already existed
         //4.put item to items
         this.items.put(itemID, quantity);
         
    }
    public void removeItemFromCart(String itemID){
        //1. check item existed
        if(itemID == null){
            return;
        }
        if(itemID.trim().isEmpty()){
            return;
        }
        //2.check items exited
        if(this.items == null){
            return;
        }
        //3.check item existed in items
        if(this.items.containsKey(itemID)){
            this.items.remove(itemID);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
        
    }
            
    

}