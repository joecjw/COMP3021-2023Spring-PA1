package hk.ust.comp3021.person;

import hk.ust.comp3021.resource.Paper;
import java.util.*;

public class Researcher extends Person{
    private final ArrayList<Paper> papers = new ArrayList<>();

    public Researcher(String id, String pName) {
        super(id, pName);
    }

    public ArrayList<Paper> getPapers() {
        return papers;
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
