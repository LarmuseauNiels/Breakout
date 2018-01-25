package be.brickrevolution.model;

import java.util.ArrayList;
import javax.json.JsonArrayBuilder;

public interface Level {

    public Block getblock(int index);

    public int getLength();

    public ArrayList<Block> getBlocks();

    public void setBlocks(ArrayList<Block> blocks);

    public void removeBlock(int index);

    public JsonArrayBuilder getJson();
}
