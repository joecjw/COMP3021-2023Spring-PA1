package hk.ust.comp3021.actions;

import hk.ust.comp3021.action.UploadPaperAction;
import hk.ust.comp3021.person.Researcher;
import hk.ust.comp3021.resource.Paper;
import hk.ust.comp3021.person.User;
import hk.ust.comp3021.utils.TestKind;
import hk.ust.comp3021.MiniMendeleyEngine;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UploadPaperActionTest {

    @Tag(TestKind.PUBLIC)
    @Test
    void testUploadPaperAction_IsSuccessCheck() {
        MiniMendeleyEngine engine = new MiniMendeleyEngine();
        String userID = "User_" + engine.getUsers().size();
        User user = engine.processUserRegister(userID, "testUser", new Date());
        String bibFilePath = "resources/bibdata/PAUploadData1.bib";
        UploadPaperAction action = new UploadPaperAction("Action_1", user, new Date(), bibFilePath);
        engine.processUploadPaperAction(user, action);
        assertTrue(action.getActionResult());
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testUploadPaperAction_CheckPaperSet() {
        MiniMendeleyEngine engine = new MiniMendeleyEngine();
        String userID = "User_" + engine.getUsers().size();
        User user = engine.processUserRegister(userID, "testUser", new Date());
        String bibFilePath = "resources/bibdata/PAUploadData1.bib";
        UploadPaperAction action = new UploadPaperAction("Action_1", user, new Date(), bibFilePath);
        engine.processUploadPaperAction(user, action);
        Set<String> uploadedPaperIDs = action.getUploadedPapers().keySet();
        assertEquals(uploadedPaperIDs.size(), 3);
        assertTrue(uploadedPaperIDs.contains("Chase1990"));
        assertTrue(uploadedPaperIDs.contains("Hutchison1973"));
        assertTrue(uploadedPaperIDs.contains("Jones1979"));
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testUploadPaperAction_CheckPaperBase() {
        MiniMendeleyEngine engine = new MiniMendeleyEngine();
        int originalPaperNumber = engine.getPaperBase().size();
        String userID = "User_" + engine.getUsers().size();
        User user = engine.processUserRegister(userID, "testUser", new Date());
        String bibFilePath = "resources/bibdata/PAUploadData1.bib";
        UploadPaperAction action = new UploadPaperAction("Action_1", user, new Date(), bibFilePath);
        engine.processUploadPaperAction(user, action);
        int currentPaperNumber = engine.getPaperBase().size();
        assertEquals(currentPaperNumber, originalPaperNumber + 3);
    }
}
