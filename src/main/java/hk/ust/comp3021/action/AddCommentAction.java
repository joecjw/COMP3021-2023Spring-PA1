package hk.ust.comp3021.action;

import hk.ust.comp3021.person.User;
import hk.ust.comp3021.resource.Comment.*;

import java.util.Date;

public class AddCommentAction {
    private String commentStr;
    private CommentType commentType;
    private String objectId;
    private boolean actionResult;


    public AddCommentAction(String id, User user, Date time, String commentStr, CommentType commentType, String objectId) {
        //TODO: complete the definition of the constructor. Define the class as the subclass of Action.
    }

    //You may need the following methods to set or get the fields of AddCommentAction
    //Plz DO NOT change the following methods. Otherwise, your test cases can fail even if your implementation is correect.
    public String getCommentStr() {
        return commentStr;
    }

    public void setCommentStr(String commentStr) {
        this.commentStr = commentStr;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean getActionResult() {
        return actionResult;
    }

    public void setActionResult(boolean actionResult) {
        this.actionResult = actionResult;
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
