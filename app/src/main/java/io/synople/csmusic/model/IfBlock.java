package io.synople.csmusic.model;

import java.util.ArrayList;
import java.util.List;

public class IfBlock extends Block {
    public static final String[] expression = {"CHORD", "RANDOM"};
    public String expr;
    public List<NoteBlock> list;

    // default constructor sets expr to empty string and list to empty array list
    public IfBlock() {
        expr = "";
        list = new ArrayList<>();
    }

    // constructor takes @param listIn of noteblocks and sets expr value based on evaluation of listIn
    public IfBlock(List<NoteBlock> listIn) {
        // if statement followed by a chord
        this();

        if (listIn.size() == 3) {
            list = listIn;
            expr = expression[0];
        }
        // if statement followed by a rando
        else if (listIn.size() == 1) {
            list = listIn;
            if (list.get(0).isRandom) {
                expr = expression[1];
            }
        }
    }

    public String getExpr() {
        return expr;
    }

    public List<NoteBlock> getNoteBlocks() {
        return list;
    }
}
