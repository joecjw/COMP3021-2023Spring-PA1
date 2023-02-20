package hk.ust.comp3021.action;

import hk.ust.comp3021.resource.Paper;
import hk.ust.comp3021.person.User;
import java.util.*;

public class SearchPaperAction extends Action{
    public enum SearchKind {
        ID,
        TITLE,
        AUTHOR,
        JOURNAL
    };

    private String searchContent;
    private SearchKind kind;

    private final ArrayList<Paper> actionResult = new ArrayList<>();

    public SearchPaperAction(String id, User user, Date time, String searchContent, SearchKind kind) {
        //TODO: complete the definition of the constructor. Define the class as the subclass of Action.
        super(id, user, time, ActionType.SEARCH_PAPER);
        this.searchContent = searchContent;
        this.kind = kind;
    }

    //You may need the following methods to set or get the fields of AddCommentAction
    //Plz DO NOT change the following methods. Otherwise, your test cases can fail even if your implementation is correect.
    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public SearchKind getKind() {
        return kind;
    }

    public void setKind(SearchKind kind) {
        this.kind = kind;
    }

    public ArrayList<Paper> getActionResult() {
        return actionResult;
    }

    public void appendToActionResult(Paper paper) {
        this.actionResult.add(paper);
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
