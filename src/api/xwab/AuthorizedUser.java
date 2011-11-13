/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api.xwab;

import api.xwab.dealer.HttpApiDealer;
import api.xwab.exception.AuthenticationException;
import api.xwab.storage.KeyStorage;

/**
 *
 * @author manyrus
 */
public class AuthorizedUser extends HttpApiDealer{
    private int id;
    private String pass;
    private int newPostsCount;
    private long refreshTime = 1800000;
    private KeyStorage storage= new KeyStorage();
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }
    
    public int getNewPostsCount() throws AuthenticationException {
        if(storage.isPut("refreshTime") && storage.needInUpdate("refreshTime") == false) {
            return newPostsCount;
        }
        
        String response = this.getNewPostsCountResponse(id, pass);
        if(response.equals("error password")) {
            throw new AuthenticationException("Wrong password.", AuthenticationException.WRONG_PASSWORD);
        } else if(response.equals("error user")) {
            throw new AuthenticationException("Wrong id.", AuthenticationException.WRONG_ID);
        } else {
            newPostsCount = new Integer(response);
        }
        
        storage.put("postsCountRefreshTime", System.currentTimeMillis() + refreshTime);
        return newPostsCount;
    }
}
