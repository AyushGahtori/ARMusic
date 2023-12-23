package io.synople.csmusic.model;

import java.util.ArrayList;
import java.util.List;

public class ForBlock extends Block {
    public int loops;
    public List<List<NoteBlock>> noteBlocks;
    public MethodBlock method;
    public boolean hasMethod;

    public ForBlock() {
        this(2);
    }

    public ForBlock(int loops) {
        this(loops, new ArrayList<>());
    }

    public ForBlock(int loopsIn, List<List<NoteBlock>> noteBlocksIn) {
        loops = loopsIn;
        noteBlocks = noteBlocksIn;
        hasMethod = false;
    }

    public ForBlock(int loopsIn, MethodBlock methodIn) {
        loops = loopsIn;
        method = methodIn;
        hasMethod = true;
    }

    public int getLoops() {
        return loops;
    }

    public void setLoops(int n) {
        loops = n;
    }

    public MethodBlock getMethod() {
        return method;
    }

    public List<List<NoteBlock>> getNotes() {
        return noteBlocks;
    }

}
