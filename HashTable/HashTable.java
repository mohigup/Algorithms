
import java.util.ArrayList;


public class HashTable {


   private static class ListNode {
      String key;
      int value;
      ListNode next;  
                     
   }

   private ListNode[] table;  

   private int count;  

   
   
   public HashTable() {
      table = new ListNode[64];
   }

   
  
   public HashTable(int initialSize) {
      if (initialSize <= 0)
         throw new IllegalArgumentException("Illegal table size");
      table = new ListNode[initialSize];
   }

   
   
   
   public void increase(String key, int value) {
      
      assert key != null : "The key must be non-null";
      
      int bucket = hash(key); 
      //System.out.println("bucked assigned"+bucket);
      ListNode list = table[bucket]; 
      while (list != null) {
            
         if (list.key.equals(key))
         {
        	 //System.out.println("found match for key ="+key);
        	 break;
         }
            
         list = list.next;
      }
      
     
      
      if (list != null) {
        
    	 // System.out.println("As found match previosly the old count for key "+key+" is "+get(key));
         list.value = list.value + 1;
         //System.out.println("new value for the key "+list.key+" is "+list.value);
      }
      else {
             //not required..change size of table to max value
         if (count >= 0.75*table.length) {
              
            resize();
            bucket = hash(key);  
         }
         ListNode newNode = new ListNode();
         //System.out.println("no found match previosly the old count for key "+key+" is "+get(key));
         newNode.key = key;
         newNode.value = value;
         newNode.next = table[bucket];
         table[bucket] = newNode;
         count++;  // Count the newly added key.
        // System.out.println("new value for key "+key+" is "+get(key));
      }
   }
   
   public void increase(String key){
		increase(key, 1);
	}
	

   

   public int get(String key) {
      
      int bucket = hash(key); 
      
      ListNode list = table[bucket];  
      while (list != null) {
           
         if (list.key.equals(key))
            return list.value;
         list = list.next;  
      }
      
    
      return 0;  
   }

   
  
   public void remove(String key) {  
      
      int bucket = hash(key);  
      
      if (table[bucket] == null) {
            
         return; 
      }
      
      if (table[bucket].key.equals(key)) {
           
         table[bucket] = table[bucket].next;
         count--; 
         return;
      }
      
      
      
      ListNode prev = table[bucket];  
      ListNode curr = prev.next;  
      while (curr != null && ! curr.key.equals(key)) {
         curr = curr.next;
         prev = curr;
      }
      
      
      
      if (curr != null) {
         prev.next = curr.next;
         count--;  // Record new number of items in the table.
      }
   }

   
  
   public boolean containsKey(String key) {
      
      int bucket = hash(key);  
      
      ListNode list = table[bucket];  
      while (list != null) {
            
         if (list.key.equals(key))
            return true;
         list = list.next;
      }
      
      
      
      return false;
   }

  
   public int size() {
      return count;
   }

   public ArrayList<String> listAllKeys(){	
	   ListNode t = null;
		ArrayList<String> keys = new ArrayList<String>();
		for(int i =0; i< table.length; i++){
			if(table[i] != null){
				t = table[i];
				while(t!=null){
					keys.add(t.key);
					t = t.next;
				}
				
			}
				
		}
		return keys;
	}
   
   public void printKeyVal(){		
	   ListNode t = null;
		for(int i =0; i< table.length; i++){
			if(table[i] != null){
				 t = table[i];
				while(t!=null){
					System.out.print("[" + t.key + ", " + t.value+"] ");
					t = t.next;
				}
			System.out.println();
				
			}
				
		}
	}
   

   private int hash(String key) {
	   int hash = 0;
	   for (int i = 0; i < key.length(); i++)
	       hash = (31 * hash + key.charAt(i));


	   return (Math.abs(hash)) % table.length;
   }

   
  //not required have to remove
   private void resize() {
      ListNode[] newtable = new ListNode[table.length*2];
      for (int i = 0; i < table.length; i++) {
             
         ListNode list = table[i]; 
         while (list != null) {
              
            ListNode next = list.next;  //
            int hash = (Math.abs(list.key.hashCode())) % newtable.length;
             
            list.next = newtable[hash];
            newtable[hash] = list;
            list = next;  
         }
      }
      table = newtable; 
   } 

} 