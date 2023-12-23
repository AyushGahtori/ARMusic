package io.synople.csmusic;

import io.synople.csmusic.model.*;

import java.util.ArrayList;
import java.util.List;

public class MusicCompiler {
    // This class should essentially be our compiler.
    private List<Block> blocks;
    private List<List<NoteBlock>> notes;

    public MusicCompiler(List<Block> blocksIn) {
        blocks = blocksIn;
        notes = new ArrayList<>();
    }

    public List<List<NoteBlock>> compile() {
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            blockToNote(b);
        }
        return notes;
    }

    private void blockToNote(Block bIn) {
        if (bIn instanceof NoteBlock) {
            addNoteBlockToNotes((NoteBlock) bIn);
        }

        if (bIn instanceof IfBlock) {
            addIfBlockToNotes((IfBlock) bIn);
        }

        if (bIn instanceof ForBlock) {
            addForBlockToNotes((ForBlock) bIn);
        }

        if (bIn instanceof MethodBlock) {
            addMethodBlockToNotes((MethodBlock) bIn);
        }
    }

    private void addNoteBlockToNotes(NoteBlock bIn) {
        ArrayList<NoteBlock> arr = new ArrayList<>();
        arr.add(bIn);
        notes.add(arr);
    }

    private void addIfBlockToNotes(IfBlock blockIn) {
        if (blockIn.getExpr().equals("CHORD") || blockIn.getExpr().equals("RANDOM")) {
            notes.add(blockIn.getNoteBlocks());
        }
    }

    private void addMethodBlockToNotes(MethodBlock blockIn) {
        List<Block> list = blockIn.getList();
        for (int i = 0; i < list.size(); i++) {
            blockToNote(list.get(i));
        }
    }

    private void addForBlockToNotes(ForBlock blockIn) {
        int loops = blockIn.getLoops();
        if (blockIn.hasMethod) {
            MethodBlock method = blockIn.getMethod();
            for (int i = 0; i < loops; i++) {
                addMethodBlockToNotes(method);
            }
        } else {
            for (int i = 0; i < loops; i++) {
                notes.addAll(blockIn.getNotes());
            }
        }

    }

}
