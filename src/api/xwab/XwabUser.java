/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api.xwab;

import api.xwab.storage.KeyStorage;
import api.xwab.dealer.HttpApiDealer;
/**
 *
 * @author manyrus
 */
public class XwabUser extends HttpApiDealer{
    private String nick;
    private long refreshTime = 10000;
    private boolean onlineStatus = false;
    private KeyStorage storage= new KeyStorage();
    
    public String getUserNick() {
        return nick;
    }
    
    public boolean isOnline() {
        if(storage.isPut("refreshTime") && storage.needInUpdate("refreshTime") == false) {
            return onlineStatus;
        }
        if(this.getOnlineUsers().indexOf(nick) == -1) {
            onlineStatus = false;
        } else {
            onlineStatus = true;
        }
        storage.put("refreshTime", System.currentTimeMillis() + refreshTime);
        return onlineStatus;
    }
    
    
    
    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }
    
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    
}
