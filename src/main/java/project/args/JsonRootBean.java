/**
  * Copyright 2018 bejson.com 
  */
package project.args;
import java.util.List;

/**
 * Auto-generated: 2018-07-13 13:52:0
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class JsonRootBean {

    private Paging paging;
    private List<Data> data;
    public void setPaging(Paging paging) {
         this.paging = paging;
     }
     public Paging getPaging() {
         return paging;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

}