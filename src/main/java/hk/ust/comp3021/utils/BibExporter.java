package hk.ust.comp3021.utils;

import hk.ust.comp3021.resource.Paper;
import java.util.HashMap;

public class BibExporter {
    private HashMap<String, Paper> papers;

    private String bibFile;

    private boolean isErr;

    public BibExporter(HashMap<String, Paper> papers, String bibFile) {
        //TODO: complete the definition of the constructor
    }


    /**
     * Return a string object which is the tokenized string of the field `papers`.
     * Hint: You may need to utilize the method `toString` of the class `Paper`
     * You may need to remove the `return null` in the skeleton.
     */

    public String generate(){
        //TODO: complete the definition of the method `export`
        return null;
    }


    /**
     * Export the content of the paper to a bib file, of which the full name is bibFile.
     * If any exception throws, please set the field `isErr` to true. Otherwise, `isErr` is false.
     * Hint: You may need to utilize the method `generate` to get the tokenized string of the field `papers`.
     */
    public void export() {
        //TODO: complete the definition of the method `export`
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
