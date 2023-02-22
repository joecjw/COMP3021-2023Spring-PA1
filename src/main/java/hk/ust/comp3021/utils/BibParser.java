package hk.ust.comp3021.utils;

import hk.ust.comp3021.resource.Paper;
import java.util.*;

public class BibParser {
    private String bibfilePath;

    private boolean isErr;
    private HashMap<String, Paper> result;

    public BibParser(String bibfilePath) {
        //TODO: complete the definition of the constructor
        this.bibfilePath = bibfilePath;
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
            Scanner myReader = new Scanner(this.bibfilePath);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains("@")){
                    String key = data;
                    Paper paper = new Paper(key);
                    data = myReader.nextLine();

                    while(!data.contains("@") && !data.isEmpty()){
                        switch (getPaperField(data)) {
                            case "abstract": paper.setAbsContent(data);

                            case "author":  ArrayList<String> authors = new ArrayList<>();
                                            String[] authorsString = data.split(" ");
                                            for(String s : authorsString){
                                                    authors.add(s);
                                            }
                                            paper.setAuthors(authors);

                            case "doi": paper.setDoi(data);

                            case "url": paper.setUrl(data);

                            case "journal": paper.setJournal(data);

                            case "year": paper.setYear(Integer.parseInt(data));

                            case "title": paper.setTitle(data);

                            case "keywords":ArrayList<String> keywords = new ArrayList<>();
                                            String[] keywordsString = data.split(",");
                                            for(String s : keywordsString){
                                                keywords.add(s);
                                            }
                                            paper.setKeywords(keywords);

                            case "null": myReader.nextLine();

                            default :    myReader.nextLine();
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
        }

        else if(data.contains("author")){
            return "author";
        }

        else if(data.contains("doi")){
            return "doi";
        }

        else if(data.contains("journal")){
            return "journal";
        }

        else if(data.contains("keywords")){
            return "keywords";
        }

        else if(data.contains("title")){
            return "title";
        }

        else if(data.contains("url")){
            return "url";
        }

        else if(data.contains("year")){
            return "year";
        }

        else {
            return "null";
        }
    }
}
