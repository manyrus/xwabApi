/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api.xwab.storage;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author manyrus
 */
public class KeyStorage {
    Map<String, Long> storage = new HashMap<String, Long>();
    
    public void put(String key, long updateTime) {
        storage.put(key, updateTime);
    }
    
    public boolean needInUpdate(String key) {
        if(new Date().after(new Date(storage.get(key)))) {
            return true;
        }
        return false;
    }
    
    public boolean isPut(String key) {
        if(storage.get(key) == null) {
            return false;
        }
        return true;
    }
}
