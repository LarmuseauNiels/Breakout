package be.brickrevolution.model;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Block implements IBlock {

    private Position positie;
    private int height;
    private int width;
    private int collor;
    private Modifier modifier;

    public Block(Position positie, int height, int width, int collor) {
        this.setCollor(collor);
        this.setHeight(height);
        this.setWidth(width);
        this.setPosition(positie);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getCollor() {
        return collor;
    }

    @Override
    public void setCollor(int collor) {
        this.collor = collor;
    }

    @Override
    public Position getPosition() {
        return this.positie;
    }

    @Override
    public void setPosition(Position positie) {
        this.positie = positie;
    }

    @Override
    public JsonObjectBuilder getJson() {
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("pos", this.positie.json())
                .add("width", this.width)
                .add("height", this.height)
                .add("collor", this.collor);
        int iconid = 0;
        if (this.modifier != null) {
            iconid = this.modifier.geticonid();
        }
        json.add("property", iconid);
        return json;
    }

    @Override
    public Modifier getModifier() {
        return modifier;
    }

    @Override
    public void setModifier(Modifier modifier) {
        this.modifier = modifier;
    }
}
