package api.xwab.observation;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;
import java.util.Map;
import api.xwab.XwabUser;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author manyrus
 */
public class UserObservervable implements Runnable {

    private long sleepTime = 1000;
    private HashMap<Observer, HashMap<XwabUser, Boolean>> observers = new HashMap<Observer, HashMap<XwabUser, Boolean>>();
    
    private void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }
    
    public void add(Observer oa) {
        HashMap<XwabUser, Boolean> userCache = new HashMap<XwabUser, Boolean>();
        for (XwabUser user : oa.getUsers()) {
            userCache.put(user, null);
        }
        observers.put(oa, userCache);
    }

    public void beginObservation() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            for (Map.Entry<Observer, HashMap<XwabUser, Boolean>> observer : observers.entrySet()) {
                for (Map.Entry<XwabUser, Boolean> user : observer.getValue().entrySet()) {
                    boolean status = user.getKey().isOnline();
                    if (user.getValue() == null || status != user.getValue()) {
                        HashMap<XwabUser, Boolean> userCache = new HashMap<XwabUser, Boolean>();
                        userCache.put(user.getKey(), status);
                        observers.put(observer.getKey(), userCache);
                        observer.getKey().notify(user.getKey(), status);
                    }
                }
            }
            
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(UserObservervable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
