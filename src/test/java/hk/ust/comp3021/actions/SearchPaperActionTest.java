package hk.ust.comp3021.actions;

import hk.ust.comp3021.action.SearchPaperAction;
import hk.ust.comp3021.action.SearchPaperAction.SearchKind;
import hk.ust.comp3021.person.User;
import hk.ust.comp3021.resource.Paper;
import hk.ust.comp3021.utils.TestKind;
import hk.ust.comp3021.MiniMendeleyEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SearchPaperActionTest {
    @Tag(TestKind.PUBLIC)
    @Test
    void testSearchPaperAction_SearchByAuthor() {
        MiniMendeleyEngine engine = new MiniMendeleyEngine();
        String userID = "User_" + engine.getUsers().size();
        User user = engine.processUserRegister(userID, "testUser", new Date());

        SearchPaperAction action1 = new SearchPaperAction("Action_1", user, new Date(),"Thomas Reps", SearchKind.AUTHOR);
        ArrayList<Paper> searchResult1 = engine.processSearchPaperAction(user, action1);

        assertEquals(searchResult1.size(), 6);

        SearchPaperAction action2 = new SearchPaperAction("Action_1", user, new Date(),"Thomas Hall", SearchKind.AUTHOR);
        ArrayList<Paper> searchResult2 = engine.processSearchPaperAction(user, action2);

        assertEquals(searchResult2.size(), 0);
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testSearchPaperAction_SearchByTitle() {
        MiniMendeleyEngine engine = new MiniMendeleyEngine();
        String userID = "User_" + engine.getUsers().size();
        User user = engine.processUserRegister(userID, "testUser", new Date());

        SearchPaperAction action1 = new SearchPaperAction("Action_1", user, new Date(),"Towards a Shape Analysis for Graph Transformation Systems", SearchKind.TITLE);
        ArrayList<Paper> searchResult1 = engine.processSearchPaperAction(user, action1);

        assertEquals(searchResult1.size(), 1);
        assertEquals(searchResult1.get(0).getPaperID(), "Steenken2010");
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testSearchPaperAction_SearchByJournal() {
        MiniMendeleyEngine engine = new MiniMendeleyEngine();
        String userID = "User_" + engine.getUsers().size();
        User user = engine.processUserRegister(userID, "testUser", new Date());

        String journalName = "Proceedings of the ACM SIGPLAN Conference on Programming Language Design and Implementation (PLDI)";
        SearchPaperAction action1 = new SearchPaperAction("Action_1", user, new Date(),journalName, SearchKind.JOURNAL);
        ArrayList<Paper> searchResult1 = engine.processSearchPaperAction(user, action1);
        assertEquals(searchResult1.size(), 4);
    }
}
