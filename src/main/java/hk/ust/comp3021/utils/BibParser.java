package hk.ust.comp3021.utils;

import hk.ust.comp3021.resource.Paper;
import java.io.File;
import java.util.*;

public class BibParser {
    private String bibfilePath;

    private boolean isErr;
    private HashMap<String, Paper> result;

    public BibParser(String bibfilePath) {
        //TODO: complete the definition of the constructor
        this.bibfilePath = bibfilePath;
        this.result = new HashMap<>();
    }


    /**
     * Extract the papers from the content of a bib file.
     * * If any exception throws, please set the field `isErr` to true. Otherwise, `isErr` is false.
     *
     * Please note that the string after @ is exactly the key of the paper.
     * For example:
     * @article{Bourdoncle1993,
     *    abstract = {Abstract interpretation ...},
     *    author = {François Bourdoncle},
     *    isbn = {9783540573166},
     *    issn = {16113349},
     *    journal = {Lecture Notes in Computer Science ...},
     *    pages = {128-141},
     *    title = {Efficient chaotic iteration strategies with widenings},
     *    volume = {735 LNCS},
     *    year = {1993},
     * }
     * The key of the paper should be Bourdoncle1993.
     *
     * Hint: The function might be quite verbose if you just define this method to achieve the functionality.
     *       You can try to define several helper methods to process different kinds of lines in the bib file,
     *       and then invoke them in this method.
     */
    public void parse() {
        //TODO: complete the definition of the method `parse`
        try {
            File file = new File(this.bibfilePath);
            Scanner myReader = new Scanner(file);
            String data = myReader.nextLine();
            while (myReader.hasNextLine()) {
                if(data.contains("@")){
                    String key = this.parsePaperID(data);
                    Paper paper = new Paper(key);
                    data = myReader.nextLine();
                    while(!data.contains("@") && !data.isEmpty()){
                        switch (getPaperField(data)) {
                            case "abstract":
                                paper.setAbsContent(parseAbsContent(data));
                                break;

                            case "author":
                                paper.setAuthors(parseAuthors(data));
                                break;

                            case "doi":
                                paper.setDoi(parseDoi(data));
                                break;

                            case "url":
                                paper.setUrl(parseUrl(data));
                                break;

                            case "journal":
                                paper.setJournal(parseJournal(data));
                                break;

                            case "year":
                                paper.setYear(parseYear(data));
                                break;

                            case "title":
                                paper.setTitle(parseTitle(data));
                                break;

                            case "keywords":
                                paper.setKeywords(parseKeywords(data));
                                break;

                            case "null":
                                break;

                            default:
                        }
                        if(myReader.hasNextLine()){
                            data = myReader.nextLine();
                        } else {
                            break;
                        }
                    }
                    this.result.put(key, paper);
                }
            }
            myReader.close();
        } catch (Exception e) {
            this.isErr = true;
        }
    }


    /**
     * Attention: You may need to define more methods to update or access the field of the class `User`
     * Feel free to define more method but remember not
     * (1) removing the fields or methods in our skeleton.
     * (2) changing the type signature of `public` methods
     * (3) changing the modifiers of the fields and methods, e.g., changing a modifier from "private" to "public"
     */
    public boolean getErrStatus() {
        return this.isErr;
    }

    public HashMap<String, Paper> getResult(){
        return this.result;
    }

    public String getPaperField(String data){
        if(data.contains("abstract")){
            return "abstract";
        } else if(data.contains("author")){
            return "author";
        } else if(data.contains("doi")){
            return "doi";
        } else if(data.contains("journal")){
            return "journal";
        } else if(data.contains("keywords")){
            return "keywords";
        } else if(data.contains("title")){
            return "title";
        } else if(data.contains("url")){
            return "url";
        } else if(data.contains("year")){
            return "year";
        } else {
            return "null";
        }
    }

    public String parsePaperID(String data) {
        String[] tokens = data.split("[{]",2);
        tokens[1] = tokens[1].trim();
        String result = tokens[1].substring(0,tokens[1].length()-1);
        return result;
    }

    public String parseDoi(String data) {
        String[] tokens = data.split("[{]",2);
        tokens[1] = tokens[1].trim();
        String result = tokens[1].substring(0,tokens[1].length()-2);
        return result;
    }

    public ArrayList<String> parseAuthors(String data) {
        String[] tokens = data.split("[{]",2);
        tokens[1] = tokens[1].trim();
        tokens[1] = tokens[1].substring(0,tokens[1].length()-2);
        String[] authors = tokens[1].split("and");
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < authors.length; i++){
            result.add(authors[i].trim());
        }
        return result;
    }

    public String parseTitle(String data) {
        String[] tokens = data.split("[{]",2);
        tokens[1] = tokens[1].trim();
        String result = tokens[1].substring(0,tokens[1].length()-2);
        return result;
    }

    public String parseJournal(String data) {
        String[] tokens = data.split("[{]",2);
        tokens[1] = tokens[1].trim();
        String result = tokens[1].substring(0,tokens[1].length()-2);
        return result;
    }

    public ArrayList<String> parseKeywords(String data) {
        String[] tokens = data.split("[{]",2);
        tokens[1] = tokens[1].trim();
        tokens[1] = tokens[1].substring(0,tokens[1].length()-2);
        String[] keywords = tokens[1].split(",");
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < keywords.length; i++){
            result.add(keywords[i].trim());
        }
        return result;
    }

    public int parseYear(String data) {
        String[] tokens = data.split("[{]",2);
        tokens[1] = tokens[1].trim();
        String result = tokens[1].substring(0,tokens[1].length()-2);
        return Integer.parseInt(result);
    }

    public String parseUrl(String data) {
        String[] tokens = data.split("[{]",2);
        tokens[1] = tokens[1].trim();
        String result = tokens[1].substring(0,tokens[1].length()-2);
        return result;
    }

    public String parseAbsContent(String data) {
        String[] tokens = data.split("[{]",2);
        tokens[1] = tokens[1].trim();
        String result = tokens[1].substring(0,tokens[1].length()-2);
        return result;
    }

}
