/**
  * Copyright 2018 bejson.com 
  */
package project.args;

/**
 * Auto-generated: 2018-07-13 13:52:0
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private String type;
    private Target target;
    private String attached_info;
    private Source source_topic;

    public Source getSource_topic() {
        return source_topic;
    }

    public void setSource_topic(Source source_topic) {
        this.source_topic = source_topic;
    }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setTarget(Target target) {
         this.target = target;
     }
     public Target getTarget() {
         return target;
     }

    public void setAttached_info(String attached_info) {
         this.attached_info = attached_info;
     }
     public String getAttached_info() {
         return attached_info;
     }

}