package hk.ust.comp3021.actions;

import hk.ust.comp3021.action.DownloadPaperAction;
import hk.ust.comp3021.action.UploadPaperAction;
import hk.ust.comp3021.person.User;
import hk.ust.comp3021.resource.Paper;
import hk.ust.comp3021.utils.TestKind;
import hk.ust.comp3021.MiniMendeleyEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.io.File;

public class DownloadPaperActionTest {
    @Tag(TestKind.PUBLIC)
    @Test
    void testDownloadPaperAction_ActionsSize() {
        MiniMendeleyEngine engine = new MiniMendeleyEngine();
        String userID = "User_" + engine.getUsers().size();
        User user = engine.processUserRegister(userID, "testUser", new Date());

        int originalSize = engine.getActions().size();
        String bibFilePath = "resources/bibdata/PADownloadTest.bib";
        DownloadPaperAction action = new DownloadPaperAction("Action_1", user, new Date(), bibFilePath);
        action.appendPapers("Ramalingam2002");
        action.appendPapers("Bourdoncle1993");
        engine.processDownloadPaperAction(user, action);

        int currentSize = engine.getActions().size();
        assertEquals(currentSize, originalSize + 1);
    }

    @Tag(TestKind.PUBLIC)
    @Test
    void testDownloadPaperAction_FileExist() {
        MiniMendeleyEngine engine = new MiniMendeleyEngine();
        String userID = "User_" + engine.getUsers().size();
        User user = engine.processUserRegister(userID, "testUser", new Date());

        String bibFilePath = "resources/bibdata/PADownloadTest.bib";
        DownloadPaperAction action = new DownloadPaperAction("Action_1", user, new Date(), bibFilePath);
        action.appendPapers("Ramalingam2002");
        action.appendPapers("Bourdoncle1993");
        engine.processDownloadPaperAction(user, action);
        File file = new File(bibFilePath);
        assertTrue(file.exists());
    }
}
