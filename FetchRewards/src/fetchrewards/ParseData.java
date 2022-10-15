/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fetchrewards;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.*;


/**
 *
 * @author Tomy Li He
 */
public class ParseData {

    // Method to 
    public static String parse(String response) {
        JSONArray items = new JSONArray(response);
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        JSONArray sortedByListIdArr = new JSONArray();
        
        // Remove any items where "name" is blank or null
        for(int i = 0; i < items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            if(item.isNull("name") || item.getString("name") == "") {
                continue;           
            }else{               
                jsonValues.add(item);
            }
          
        }
        // Sort results by "listId" and then by "name" for matching "listId"
        Collections.sort(jsonValues, new Comparator<JSONObject>(){            
            @Override
            public int compare(JSONObject o1, JSONObject o2) {               
                               
                int valObj1 = o1.getInt("listId");
                int valObj2 = o2.getInt("listId");               
                int secondComp = Integer.compare(valObj1, valObj2);
                
                // If the same listId, sort by the name
                if (secondComp == 0){
                    String[] valObj3 = o1.getString("name").split(" ");
                    String[] valObj4 = o2.getString("name").split(" ");
                    int val3 = Integer.parseInt(valObj3[1]);
                    int val4 = Integer.parseInt(valObj4[1]);
                    
                    return Integer.compare(val3, val4);
                }else {
                    return secondComp;
                }
            }            
        });       
        
        for (int i = 0 ; i < jsonValues.size(); i++){
            sortedByListIdArr.put(jsonValues.get(i));
        }
        // Initialize output string 
        String output = new String();
        
        // Iterate through sorted JSON Array and append item values to output string
        for (int i = 0; i < sortedByListIdArr.length(); i++){
            JSONObject sortedItem = sortedByListIdArr.getJSONObject(i);
            int id = sortedItem.getInt("id");
            int listId = sortedItem.getInt("listId");
            String name = sortedItem.getString("name");
            output = output + "List ID: " + listId + "  Item ID: " + id + " Item Name: " + name + "\n";
        }
        
        return output;
    }
  
}
