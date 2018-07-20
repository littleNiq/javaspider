package project.args;

/**
 * Created by Naq on 2018/7/13.
 */
public class Questions {

    String title;

    int follows;

    String visitor;

    String author;

    String date;

    public Questions(String title, int follower_count, String visitor, String author, String date) {
        this.title = title;
        this.follows = follower_count;
        this.author = author;
        this.date = date;
        this.visitor = visitor;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
