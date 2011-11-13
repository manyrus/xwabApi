/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import api.xwab.AuthorizedUser;
import api.xwab.exception.AuthenticationException;
import api.xwab.observation.UserObservervable;
        
/**
 *
 * @author manyrus
 */
public class MainClass {
    public static void main(String[] args){
        AuthorizedUser au = new AuthorizedUser();
        au.setId(0);
        au.setPass("");
        try {
            System.out.println(au.getNewPostsCount());
        }catch(AuthenticationException e) {
            if(e.getCode()==AuthenticationException.WRONG_ID) {
                System.err.println(e.getDescription());
            } else if(e.getCode() == AuthenticationException.WRONG_PASSWORD) {
                System.err.println(e.getDescription());
            }
        }
        
        /**
        UserObservervable uo = new UserObservervable();
        Displayer displayer = new Displayer();
        displayer.addUser("manyrus");
        uo.add(displayer);
        uo.beginObservation();
        System.err.println("Следим в потоке!");
         * */
        /**
        XwabStatistics statistics = new XwabStatistics();
        statistics.setRefreshTime(20000);//время живучести кэша
        statistics.getPostsCount();//получаем данные
        statistics.getPostsCount();//получаем данные(уже из кеша, живучесть - 20сек.)
         * */
    }
}
