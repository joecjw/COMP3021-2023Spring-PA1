package hk.ust.comp3021.action;

import hk.ust.comp3021.person.User;
import java.util.*;

public class Action {

    enum ActionType {
        ADD_COMMENT,
        ADD_LABEL,
        DOWNLOAD_PAPER,
        UPLOAD_PAPER,
        SEARCH_PAPER
    };

    private String id;

    /**
     * Hint: When you need to initialize the field or set the value of the field
     * you can just use `new Date()` to set the time to be the current time stamp.
     */
    private Date time;

    private User user;

    private ActionType actionType;

    public Action(String id, User user, Date time, ActionType actionType) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.actionType = actionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
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
