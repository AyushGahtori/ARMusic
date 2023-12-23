package io.synople.csmusic.model;

import java.util.ArrayList;
import java.util.List;

// method blocks create lists of forblocks, note blocks, and if blocks
public class MethodBlock extends Block {
    public int methodNum; // 0 is main method.
    public List<Block> list; //address of List this block is in

    public MethodBlock(int m) {
        methodNum = m;
        list = new ArrayList<Block>();
    }

    public void addBlock(Block b) {
        list.add(b);
    }

    public void delBlock(Block b) {
        list.remove(b);
    }

    public List<Block> getList() {
        return list;
    }

    public int size() {
        return list.size();
    }

    public String toString() {
        return "M" + methodNum;
    }
}
