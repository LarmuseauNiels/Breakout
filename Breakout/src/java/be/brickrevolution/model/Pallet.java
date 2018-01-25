package be.brickrevolution.model;

import be.brickrevolution.Playfield.IPlayfield;
import static java.lang.Math.atan2;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Pallet {

    private Position positie;
    private double speed;
    private double width;

    public Pallet(Position positie, double speed, int width) {
        this.setPositie(positie);
        this.setSpeed(speed);
        this.setWidth(width);
    }

    public void checkPallet(IPlayfield pf) {
        int palletheight = 30;
        Position pp = this.getPositie();
        if (pf.getBall().collideswidth(this.getWidth() + pp.getX(), pp.getX(), pp.getY(), pp.getY() - palletheight)) {
            double y;
            if (pp.getY() < (pf.getPlayfieldheight() / 2)) {
                y = pp.getY() - 100;
            } else {
                y = pp.getY() + 100;
            }
            pf.getBall().setDirection((int) Math.round(Math.toDegrees(atan2(pf.getBall().getPositie().getY() - y, pf.getBall().getPositie().getX() - (pp.getX() + (this.getWidth() / 2))))));
            setLastHit(pf);
        }
    }

    public void setLastHit(IPlayfield pf) {
        if (this == pf.getBotumPallet()) {
            pf.setPlayerthathitlast(1);
        } else {
            pf.setPlayerthathitlast(2);
        }
    }

    public Position getPositie() {
        return positie;
    }

    public void setPositie(Position positie) {
        this.positie = positie;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void moveLeft() {
        if (this.getPositie().getX() - speed > 29) {
            positie.moveLeft(speed);
        }
    }

    public void moveRight() {
        if (this.getPositie().getX() + speed + this.width < 895) {
            positie.moveRight(speed);
        }
    }

    public String getImg() {//todo get sprite URLs
        String Result = "/img/x";

        return Result;
    }

    public JsonObjectBuilder getJson() {
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("pos", this.positie.json())
                .add("breete", Math.round(this.width))
                .add("img", getImg());
        return json;
    }

}
