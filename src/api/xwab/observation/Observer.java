package api.xwab.observation;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import api.xwab.XwabUser;

/**
 *
 * @author manyrus
 */
public interface Observer {
    public ArrayList<XwabUser> getUsers();
    public void notify(XwabUser user, boolean status);
}
