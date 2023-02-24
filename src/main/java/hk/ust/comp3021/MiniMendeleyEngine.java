package hk.ust.comp3021;

import hk.ust.comp3021.action.*;
import hk.ust.comp3021.person.Researcher;
import hk.ust.comp3021.person.User;
import hk.ust.comp3021.resource.Comment;
import hk.ust.comp3021.resource.Label;
import hk.ust.comp3021.resource.Paper;
import hk.ust.comp3021.resource.Comment.*;
import hk.ust.comp3021.action.SearchPaperAction.SearchKind;
import hk.ust.comp3021.utils.BibExporter;
import hk.ust.comp3021.utils.BibParser;
import hk.ust.comp3021.utils.UserRegister;

import java.util.*;

public class MiniMendeleyEngine {
    private final String defaultBibFilePath = "resources/bibdata/PAData.bib";
    private final HashMap<String, Paper> paperBase = new HashMap<>();

    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Researcher> researchers = new ArrayList<>();

    private final ArrayList<Comment> comments = new ArrayList<>();

    private final ArrayList<Label> labels = new ArrayList<>();

    private final ArrayList<Action> actions = new ArrayList<>();

    public MiniMendeleyEngine() {
        populatePaperBaseWithDefaultBibFile();
    }

    public void populatePaperBaseWithDefaultBibFile() {
        User user = new User("User_0", "root_user", new Date());
        users.add(user);
        UploadPaperAction action = new UploadPaperAction("Action_0",user, new Date() , defaultBibFilePath);
        processUploadPaperAction(user, action);
        paperBase.putAll(action.getUploadedPapers());
    }

    public String getDefaultBibFilePath() {
        return defaultBibFilePath;
    }

    public HashMap<String, Paper> getPaperBase() {
        return paperBase;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Researcher> getResearchers() {
        return researchers;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    /**
     * Create a new user and add the user to the field `users`
     * You may need to remove the `return null` from the skeleton.
     *
     * @param id: The id of the user
     * @param name: The name of the user
     * @param date: The creation date
     * @return: The created User object
     */
    public User processUserRegister(String id, String name, Date date) {
        //TODO: complete the definition of the method `processUserRegister`
        UserRegister userRegister = new UserRegister(id, name, date);
        User newUser = userRegister.register();
        users.add(newUser);
        return newUser;
    }

    /**
     * Add a new comment with a given AddCommentAction. You need to:
     * (1) Create the corresponding kind of the comment object.
     *     The comment id is "Comment_x", where x is equal to the number of comments in the field `comments`.
     * (2) Add the comment object to the field `comments`
     * (3) Update the comment list in the commented paper or comment
     * (4) Update the comment list in the current user.
     * (5) If the comment is added successfully, please set action result to be true in `action`.
     *     Otherwise, please set action result to be false and the return value to be null.
     * (6) No matter the action succeeds or not, you need to add the action to `actions`.
     * You may need to remove the `return null` from the skeleton.
     *
     * @param curUser: Current user
     * @param action: AddCommentAction action
     * @return: The created comment
     */
    public Comment processAddCommentAction(User curUser, AddCommentAction action) {
        //TODO: complete the definition of the method `processAddCommentAction`
        Comment newComment = new Comment("Comment_" + this.comments.size(), new Date(), action.getCommentStr(), curUser,
                                            action.getCommentType(), action.getObjectId());

        actions.add(action);
        if(!this.comments.add(newComment)){
            action.setActionResult(false);
            return null;
        }

        if(newComment.getType().equals(CommentType.COMMENT_OF_PAPER)) {
            if(!paperBase.get(newComment.getCommentObjId()).getComments().add(newComment)){
                action.setActionResult(false);
                return  null;
            }
        }
        else if(newComment.getType().equals(CommentType.COMMENT_OF_COMMENT)){
            for(int i = 0; i < this.comments.size(); i++){
                if(newComment.getCommentObjId().equals(comments.get(i).getCommentID())){
                    if(!comments.get(i).getAttachedComments().add(newComment)){
                        action.setActionResult(false);
                        return null;
                    }
                }
            }
        }

        if(!curUser.getUserComments().add(newComment)){
            action.setActionResult(false);
            return null;
        }

        action.setActionResult(true);
        return newComment;
    }

    /**
     * Add a label to a paper with a given AddLabelAction. You need to:
     * (1) Create a new label. The label id is "Label_x", where x is the number of labels in the field `labels`.
     *     Add the label to the field `labels`.
     * (2) Update the label list of the paper
     * (3) Update the label list of the current user.
     * (4) If the label is added successfully, please set action result to be true in `action`.
     *     Otherwise, please set action result to be false and the return value to be null.
     * (5) No matter the action succeeds or not, you need to add the action to `actions`.
     * You may need to remove the `return null` from the skeleton.
     *
     * @param curUser: Current user
     * @param action: AddLabelAction action
     * @return: The created label
     */
    public Label processAddLabelAction(User curUser, AddLabelAction action) {
        //TODO: complete the definition of the method `processAddLabelAction`
        Label newLabel = new Label("Label_" + this.labels.size(), action.getPaperID(),
                                                    new Date(), action.getLabelStr(), curUser);

        actions.add(action);
        if(!this.labels.add(newLabel)){
            action.setActionResult(false);
            return null;
        }

        if(!paperBase.get(newLabel.getPaperID()).getLabels().add(newLabel)){
            action.setActionResult(false);
            return  null;
        }

        if(!curUser.getUserLabels().add(newLabel)){
            action.setActionResult(false);
            return null;
        }

        action.setActionResult(true);
        return newLabel;
    }

    /**
     * Download the specified papers to a bib file with a given DownloadPaperAction action. You need to:
     * (1) Create a bibfile according to `action`
     * (2) Dump the paper info to the bibfile
     * (3) If the papers are exported successfully, please set action result to be true in `action`.
     *     Otherwise, please set action result to be false.
     * (4) No matter the action succeeds or not, you need to add the action to `actions`.
     *
     * @param curUser: Current user
     * @param action: DownloadPaperAction action
     */
    public void processDownloadPaperAction(User curUser, DownloadPaperAction action) {
        //TODO: complete the definition of the method `processDownloadPaperAction`
        HashMap<String, Paper> tagetPapers = new HashMap<>();

        for(String downLoadPaper : action.getPaper()){
            boolean isPaperAbsent = true;
            for(String key : this.paperBase.keySet()){
                if(this.paperBase.get(key).getPaperID().equals(downLoadPaper)){
                    tagetPapers.put(key, this.paperBase.get(key));
                    isPaperAbsent = false;
                }
            }
            if(isPaperAbsent == true){
                action.setActionResult(false);
                return;
            }
        }

        BibExporter bibExporter = new BibExporter(tagetPapers, action.getDownloadPath());
        bibExporter.export();

        if(bibExporter.getErrStatus() == true){
            action.setActionResult(false);
        }
        else {
            action.setActionResult(true);
        }
        actions.add(action);
    }

    /**
     * Upload the papers from a bib file to `paperBase`. You need to
     * (1) Add all the papers to the field `paperBase`.
     * (2) Add all the authors to the field `researchers`
     * (3) Update the paper list of the researchers by adding the uploaded papers.
     *     If the researcher does not exist in `researchers`, which is identified according to his or her name,
     *     you need to create a new research object, of which the id is "Researcher_x",
     *     x is the number of the researchers in `researchers`.
     * (4) If the papers are uploaded successfully, please set action result to be true in `action`.
     *     Otherwise, please set action result to be false.
     * (5) No matter the action succeeds or not, you need to add the action to `actions`.
     *
     * @param curUser: Current user
     * @param action: UploadPaperAction action
     */
    public void processUploadPaperAction(User curUser, UploadPaperAction action) {
        //TODO: complete the definition of the method `processUploadPaperAction`
        BibParser bibParser = new BibParser(action.getBibfilePath());
        bibParser.parse();

        this.actions.add(action);
        if(bibParser.getErrStatus() == true){
            action.setActionResult(false);
            return;
        }

        try {
            this.paperBase.putAll(bibParser.getResult());
            action.setUploadedPapers(bibParser.getResult());
            for (String key : this.paperBase.keySet()) {
                for (String author : this.paperBase.get(key).getAuthors()) {
                    boolean existedResearcher = false;
                    Researcher updateResearcher = null;
                    for (Researcher researcher : this.researchers) {
                        if (researcher.getName().equals(author)) {
                            existedResearcher = true;
                            updateResearcher = researcher;
                        }
                    }
                    if (existedResearcher == true) {
                        updateResearcher.getPapers().add(this.paperBase.get(key));
                    } else {
                        Researcher newResearcher = new Researcher("Researcher_" + String.valueOf(researchers.size()), author);
                        newResearcher.getPapers().add(this.paperBase.get(key));
                        this.researchers.add(newResearcher);
                    }
                }
            }
        } catch (Exception e) {
            action.setActionResult(false);
            return;
        }

        action.setActionResult(true);
    }


    /**
     * Search the papers with a given SearchPaperAction action.
     * You need to support four kinds of search mode.
     * No matter the action succeeds or not, you need to add the action to `actions`.
     * You may need to remove the `return null` from the skeleton.
     *
     * @param curUser: Current user
     * @param action: SearchPaperAction actioin
     * @return: The searched papers. If not paper found, please return an empty ArrayList.
     */
    public ArrayList<Paper> processSearchPaperAction(User curUser, SearchPaperAction action) {
        //TODO: complete the definition of the method `processSearchPaperAction`
        actions.add(action);
        switch (action.getKind()){
            case ID:
                for(String key : this.paperBase.keySet()){
                    if(this.paperBase.get(key).getPaperID().equals(action.getSearchContent())){
                        action.appendToActionResult(this.paperBase.get(key));
                    }
                }
                return action.getActionResult();

            case TITLE:
                for(String key : this.paperBase.keySet()){
                    if(this.paperBase.get(key).getTitle().equals(action.getSearchContent())){
                        action.appendToActionResult(this.paperBase.get(key));
                    }
                }
                return action.getActionResult();

            case AUTHOR:
                for(String key : this.paperBase.keySet()){
                    if(this.paperBase.get(key).getAuthors().equals(action.getSearchContent())){
                        action.appendToActionResult(this.paperBase.get(key));
                    }
                }
                return action.getActionResult();

            case JOURNAL:
                for(String key : this.paperBase.keySet()){
                    if(this.paperBase.get(key).getJournal().equals(action.getSearchContent())){
                        action.appendToActionResult(this.paperBase.get(key));
                    }
                }
                return action.getActionResult();

            default:
                return action.getActionResult();
        }
    }


    public User userInterfaceForUserCreation() {
        System.out.println("Please enter your name.");
        Scanner scan2 = new Scanner(System.in);
        if (scan2.hasNextLine()) {
            String name = scan2.nextLine();
            System.out.println("Create the account with the name: " + name);
            String userID = "User_" + users.size();
            User curUser = processUserRegister(userID, name, new Date());
            System.out.println("Account created!");
            return curUser;
        }
        return null;
    }

    public void userInterfaceForPaperSearch(User curUser) {
        System.out.println("Please specify the search kind:");
        System.out.println("  1: Search by ID");
        System.out.println("  2: Search by title");
        System.out.println("  3: Search by author");
        System.out.println("  4: Search by journal");
        while (true) {
            Scanner scan3 = new Scanner(System.in);
            if (scan3.hasNextInt()) {
                int k = scan3.nextInt();
                if (k < 1 || k > 4) {
                    System.out.println("You should enter 1~4.");
                } else {
                    System.out.println("Please specify the search word:");
                    Scanner scan4 = new Scanner(System.in);
                    if (scan4.hasNextLine()) {
                        String word = scan4.nextLine();
                        SearchPaperAction action = new SearchPaperAction("Action_" + actions.size(),
                                curUser, new Date(), word, SearchKind.values()[k - 1]);
                        processSearchPaperAction(curUser, action);

                        if (action.getActionResult().size() > 0) {
                            System.out.println("Paper found! The paper IDs are as follows:");
                            for (Paper paper : action.getActionResult()) {
                                System.out.println(paper.getPaperID());
                            }
                        } else {
                            System.out.println("Paper not found!");
                        }
                        break;
                    }
                }
            }
        }
    }

    public void userInterfaceForPaperUpload(User curUser) {
        System.out.println("Please specify the absolute path of the bib file:");
        Scanner scan5 = new Scanner(System.in);
        if (scan5.hasNextLine()) {
            String name = scan5.nextLine();
            UploadPaperAction action = new UploadPaperAction("Action_" + actions.size(), curUser, new Date(), name);
            processUploadPaperAction(curUser, action);
            if (action.getActionResult()) {
                System.out.println("Succeed! The uploaded papers are as follows:");
                for (String id : action.getUploadedPapers().keySet()) {
                    System.out.println(id);
                }
            } else {
                System.out.println("Fail! You need to specify an existing bib file.");
            }
        }
    }

    public void userInterfaceForPaperDownload(User curUser) {
        System.out.println("Please specify the absolute path of the bib file:");
        Scanner scan6 = new Scanner(System.in);
        if (scan6.hasNextLine()) {
            String path = scan6.nextLine();
            DownloadPaperAction action = new DownloadPaperAction("Action_" + actions.size(), curUser, new Date(), path);
            System.out.println("Please enter the paper ID line by line and end with END");
            while (true) {
                Scanner scan7 = new Scanner(System.in);
                if (scan7.hasNextLine()) {
                    String name = scan7.nextLine();
                    if (name.equals("END")) {
                        break;
                    } else {
                        action.appendPapers(name);
                    }
                }
            }
            processDownloadPaperAction(curUser, action);
            if (action.getActionResult()) {
                System.out.println("Succeed! The downloaded paper is stored in your specified file.");
            } else {
                System.out.println("Fail! Some papers not found!");
            }
        }
    }

    public void userInterfaceForAddLabel(User curUser) {
        System.out.println("Please specify the paper ID:");
        Scanner scan8 = new Scanner(System.in);
        if (scan8.hasNextLine()) {
            String paperID = scan8.nextLine();
            System.out.println("Please specify the label");
            Scanner scan9 = new Scanner(System.in);
            if (scan9.hasNextLine()) {
                String newlabel = scan9.nextLine();
                AddLabelAction action = new AddLabelAction("Action_" + actions.size(), curUser, new Date(), newlabel, paperID);
                processAddLabelAction(curUser, action);

                if (action.getActionResult()) {
                    System.out.println("Succeed! The label is added.");
                } else {
                    System.out.println("Fail!");
                }
            }
        }
    }

    public void userInterfaceForAddComment(User curUser) {
        System.out.println("Please specify the commented object ID:");
        Scanner scan10 = new Scanner(System.in);
        if (scan10.hasNextLine()) {
            String objID = scan10.nextLine();
            System.out.println("Please specify the comment");
            Scanner scan11 = new Scanner(System.in);
            if (scan11.hasNextLine()) {
                String newCommentStr = scan11.nextLine();
                CommentType t = null;
                if (objID.startsWith("Comment")) {
                    t = CommentType.COMMENT_OF_COMMENT;
                } else {
                    t = CommentType.COMMENT_OF_PAPER;
                }
                AddCommentAction action = new AddCommentAction("Action_" + actions.size(), curUser, new Date(), newCommentStr, t, objID);
                processAddCommentAction(curUser, action);

                if (action.getActionResult()) {
                    System.out.println("Succeed! The comment is added.");
                } else {
                    System.out.println("Fail!");
                }
            }
        }
    }

    public void userInterface() {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("MiniMendeley is running...");
        System.out.println("Initial paper base has been populated!");
        User curUser = null;

        while (true) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Please select the following operations with the corresponding numbers:");
            System.out.println("  0: Register an account");
            System.out.println("  1: Search papers");
            System.out.println("  2: Upload papers");
            System.out.println("  3: Download papers");
            System.out.println("  4: Add labels");
            System.out.println("  5: Add comments");
            System.out.println("  6: Exit");
            System.out.println("----------------------------------------------------------------------");
            Scanner scan1 = new Scanner(System.in);
            if (scan1.hasNextInt()) {
                int i = scan1.nextInt();
                if (i < 0 || i > 6) {
                    System.out.println("You should enter 0~6.");
                    continue;
                }
                if (curUser == null && i != 0) {
                    System.out.println("You need to register an account first.");
                    continue;
                }
                switch (i) {
                    case 0 -> {
                        curUser = userInterfaceForUserCreation();
                    }
                    case 1 -> {
                        userInterfaceForPaperSearch(curUser);
                    }
                    case 2 -> {
                        userInterfaceForPaperUpload(curUser);
                    }
                    case 3 -> {
                        userInterfaceForPaperDownload(curUser);
                    }
                    case 4 -> {
                        userInterfaceForAddLabel(curUser);
                    }
                    case 5 -> {
                        userInterfaceForAddComment(curUser);
                    }
                    default -> {
                    }
                }
                if (i == 6) break;
            } else {
                System.out.println("You should enter integer 0~6.");
            }
        }
    }

    /**
     * Attention: You may need to define more methods to update or access the field of the class `User`
     * Feel free to define more method but remember not
     * (1) removing the fields or methods in our skeleton.
     * (2) changing the type signature of `public` methods
     * (3) changing the modifiers of the fields and methods, e.g., changing a modifier from "private" to "public"
     */
    public void printPaperBase() {
        for(Map.Entry<String, Paper> set : this.paperBase.entrySet()){
            System.out.println(set.getValue().getPaperID());
            System.out.println(set.getValue().getAuthors());
            System.out.println(set.getValue().getJournal());
            System.out.println(set.getValue().getTitle());
            System.out.println(set.getValue().getAbsContent());
            System.out.println(set.getValue().getDoi());
            System.out.println(set.getValue().getKeywords());
            System.out.println(set.getValue().getUrl());
            System.out.println(set.getValue().getYear());
            System.out.println();
        }
    }
}
