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
public class XwabStatistics extends HttpApiDealer{
    private long refreshTime = 10000;
    private String postsCounts = "0";
    private KeyStorage storage= new KeyStorage();
    
    @Override
    public String getPostsCount() {
        if(storage.isPut("postsCountRefreshTime") && storage.needInUpdate("postsCountRefreshTime") == false) {
            return postsCounts;
        }
        postsCounts = super.getPostsCount();
        storage.put("postsCountRefreshTime", System.currentTimeMillis() + refreshTime);
        return postsCounts;
    }
    
    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }
}
