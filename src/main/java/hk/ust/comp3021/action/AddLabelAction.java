package hk.ust.comp3021.action;

import hk.ust.comp3021.person.User;
import java.util.Date;

public class AddLabelAction extends Action{
    private String labelStr;

    private String paperID;

    private boolean actionResult;

    public AddLabelAction(String id, User user, Date time, String labelStr, String paperID) {
        //TODO: complete the definition of the constructor. Define the class as the subclass of Action.
        super(id, user, time, ActionType.ADD_LABEL);
        this.labelStr = labelStr;
        this.paperID = paperID;
    }

    //You may need the following methods to set or get the fields of AddCommentAction
    //Plz DO NOT change the following methods. Otherwise, your test cases can fail even if your implementation is correect.
    public String getLabelStr() {
        return labelStr;
    }

    public void setLabelStr(String labelStr) {
        this.labelStr = labelStr;
    }

    public String getPaperID() {
        return paperID;
    }

    public void setPaperID(String paperID) {
        this.paperID = paperID;
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
