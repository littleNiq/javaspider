package project.aargs;

/**
 * Created by Naq on 2018/7/15.
 */
public class Answer {

    String question;
    String author;
    String follower;
    String voteuper;
    String commenter;

    public Answer(String question, String author, String follower, String voteuper, String commenter) {
        this.question = question;
        this.author = author;
        this.follower = follower;
        this.voteuper = voteuper;
        this.commenter = commenter;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getVoteuper() {
        return voteuper;
    }

    public void setVoteuper(String voteuper) {
        this.voteuper = voteuper;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }
}
