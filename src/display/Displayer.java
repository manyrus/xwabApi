 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import api.xwab.XwabUser;
import java.util.ArrayList;
import api.xwab.observation.Observer;
import java.util.Date;
/**
 *
 * @author manyrus
 */
public class Displayer implements Observer {
    ArrayList<XwabUser> users = new ArrayList<XwabUser>();
    
    public void addUser(String userNick) {
        XwabUser user = new XwabUser();
        user.setNick(userNick);
        users.add(user);
        
    }
    
    public void addUser(XwabUser user) {
        users.add(user);
        
    }
    
    private void display(String userNick, boolean status) {
        System.out.println(userNick+" теперь "+(status?"онлайн":"оффлайн")+"("+new Date()+")");
    }
    
    @Override
    public void notify(XwabUser user, boolean status) {
        this.display(user.getUserNick(), status);
    }

    @Override
    public ArrayList<XwabUser> getUsers() {
        return users;
    }

    
}
