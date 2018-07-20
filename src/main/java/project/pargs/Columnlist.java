package project.pargs;

/**
 * Created by Naq on 2018/7/14.
 */
public class Columnlist {

    String title;

    String author;

    String voteup_count;

    String comment_count;

    public Columnlist(String title, String author, String voteup_count, String comment_count) {
        this.author = author;
        this.title = title;
        this.voteup_count = voteup_count;
        this.comment_count = comment_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVoteup_count() {
        return voteup_count;
    }

    public void setVoteup_count(String voteup_count) {
        this.voteup_count = voteup_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }
}
