package io.synople.csmusic.model;

import java.util.UUID;

public class Block {
    public String id = UUID.randomUUID().toString();
    public int colorStatus = 0; // 0 is not played, 1 is played, 2 is active
}
