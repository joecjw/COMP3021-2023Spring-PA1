package hk.ust.comp3021.resource;

import hk.ust.comp3021.person.User;

import java.util.*;

public class Comment {
    public enum CommentType {
        COMMENT_OF_COMMENT,
        COMMENT_OF_PAPER;
    }

    private final String commentID;

    private Date creationTime;

    private String content;

    private User creator;

    private CommentType type;

    private String commentObjId;

    private final ArrayList<Comment> attachedComments = new ArrayList<>();

    public Comment(String commentID, Date creationTime, String content, User creator, CommentType type, String commentObjId) {
        this.commentID = commentID;
        this.creationTime = creationTime;
        this.content = content;
        this.creator = creator;
        this.type = type;
        this.commentObjId = commentObjId;
    }

    public String getCommentID() {
        return commentID;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(String creatorID) {
        this.creator = creator;
    }

    public CommentType getType() {
        return type;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public String getCommentObjId() {
        return commentObjId;
    }

    public void setCommentObjId(String commentObjId) {
        this.commentObjId = commentObjId;
    }

    public ArrayList<Comment> getAttachedComments() {
        return this.attachedComments;
    }

    /**
     * Attention: You may need to define more methods to update or access the field of the class `User`
     * Feel free to define more method but remember not
     * (1) removing the fields or methods in our skeleton.
     * (2) changing the type signature of `public` methods
     * (3) changing the modifiers of the fields and methods, e.g., changing a modifier from "private" to "public"
     */
    public void yourMethod() {

    }
}
