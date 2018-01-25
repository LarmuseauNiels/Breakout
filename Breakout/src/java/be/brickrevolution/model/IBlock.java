package be.brickrevolution.model;

import javax.json.JsonObjectBuilder;

public interface IBlock {

    public Position getPosition();

    public void setPosition(Position positie);

    public int getHeight();

    public void setHeight(int height);

    public int getWidth();

    public void setWidth(int width);

    public int getCollor();

    public void setCollor(int collor);

    public JsonObjectBuilder getJson();

    public Modifier getModifier();

    public void setModifier(Modifier modifier);

}
