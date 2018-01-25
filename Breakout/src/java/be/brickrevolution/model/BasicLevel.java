package be.brickrevolution.model;

import be.brickrevolution.Playfield.IPlayfield;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

public class BasicLevel implements Level {

    private ArrayList<Block> blocks = new ArrayList<>();
    private Difficulty diff;

    public BasicLevel(ArrayList<Block> blocks, Difficulty diff) {
        this.blocks = blocks;
        this.diff = diff;
    }

    @Override
    public Block getblock(int index) {
        return this.getBlocks().get(index);
    }

    @Override
    public int getLength() {
        return this.getBlocks().size();
    }

    @Override
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    @Override
    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public void removeBlock(int index) {
        this.blocks.remove(index);
    }

    @Override
    public JsonArrayBuilder getJson() {
        JsonArrayBuilder blocksBuilder = Json.createArrayBuilder();
        for (int i = 0; i < this.blocks.size(); i++) {//checks if it hits blocks
            blocksBuilder.add(this.getblock(i).getJson());
        }
        return blocksBuilder;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public void checkLevel(IPlayfield pf) {
        for (int i = 0; i < this.getLength(); i++) {
            Position p = this.getblock(i).getPosition();
            double maxX = p.getX() + this.getblock(i).getWidth();
            double minX = p.getX();
            double maxY = p.getY();
            double minY = p.getY() - this.getblock(i).getHeight();
            if (pf.getBall().collideswidth(maxX, minX, maxY, minY)) {

                if (this.getblock(i).getModifier() != null) {
                    pf.getModifiers().addModifier(this.getblock(i).getModifier());
                }
                pf.getG().sendRemBlock(i);
                this.removeBlock(i);
                if (((pf.getBall().getBallminX()) + (pf.getBall().getSize() / 2)) > minX && (pf.getBall().getBallminX() + (pf.getBall().getSize() / 2)) < maxX) {
                    pf.getBall().mirrorHorizontal();
                } else {
                    pf.getBall().mirrorVertical();
                }
                pf.addScoreForBlockBreak();
            }
        }
    }

}
