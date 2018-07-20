/**
 * Copyright 2018 bejson.com
 */
package project.pargs;

/**
 * Auto-generated: 2018-07-14 22:39:7
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Target {

    private long updated;

    private String title;

    private long created;

    private Column column;

    private String image_url;
    private String excerpt_title;


    private Author author;
    private String url;
    private String excerpt;
    private long id;
    private Question question;
    private long updated_time;
    private String content;
    private int comment_count;
    private long created_time;
    private String type;
    private String thumbnail;
    private Thumbnail_extra_info thumbnail_extra_info;
    private int voteup_count;

    public long getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(long updated_time) {
        this.updated_time = updated_time;
    }

    public long getCreated_time() {
        return created_time;
    }

    public void setCreated_time(long created_time) {
        this.created_time = created_time;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Thumbnail_extra_info getThumbnail_extra_info() {
        return thumbnail_extra_info;
    }

    public void setThumbnail_extra_info(Thumbnail_extra_info thumbnail_extra_info) {
        this.thumbnail_extra_info = thumbnail_extra_info;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setUpdated(long updated) {
        this.updated = updated;
    }

    public long getUpdated() {
        return updated;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getCreated() {
        return created;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public Column getColumn() {
        return column;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setExcerpt_title(String excerpt_title) {
        this.excerpt_title = excerpt_title;
    }

    public String getExcerpt_title() {
        return excerpt_title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setVoteup_count(int voteup_count) {
        this.voteup_count = voteup_count;
    }

    public int getVoteup_count() {
        return voteup_count;
    }

}