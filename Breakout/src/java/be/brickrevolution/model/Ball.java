package be.brickrevolution.model;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Ball {

    private Position positie;
    private double speed;
    private double size;
    private int color;
    private int direction;

    private final double collisionprecision = 0;
    private double ballmaxX;
    private double ballminX;
    private double ballmaxY;
    private double ballminY;

    public Ball(Position positie, double speed, int direction, int size, int color) {
        this.setPositie(positie);
        this.setColor(color);
        this.setDirection(direction);
        this.setSize(size);
        this.setSpeed(speed);
    }

    public void nextPosition() {
        double offsetY = Math.sin(Math.toRadians(this.direction)) * (double) this.speed;
        double offsetX = Math.cos(Math.toRadians(this.direction)) * (double) this.speed;
        this.positie.setY(this.positie.getY() + offsetY);
        this.positie.setX(this.positie.getX() + offsetX);
        this.calcbounds();
    }

    private void calcbounds() {
        this.ballmaxX = this.positie.getX() + this.size + this.collisionprecision;
        this.ballminX = this.positie.getX() - this.collisionprecision;
        this.ballmaxY = this.positie.getY() + this.collisionprecision;
        this.ballminY = this.positie.getY() - this.size - this.collisionprecision;
    }

    public boolean collideswidth(double maxX, double minX, double maxY, double minY) {
        return (this.getBallminX() < maxX && this.getBallmaxX() > minX && this.getBallminY() < maxY && this.getBallmaxY() > minY);
    }

    public void mirrorHorizontal() {
        this.direction = 360 - this.direction;
    }

    public void mirrorVertical() {
        if (this.direction < 180) {
            this.direction = 180 - this.direction;
        } else {
            this.direction = 360 - (this.direction - 180);
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
        this.calcbounds();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int collor) {
        this.color = collor;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    private String getImg() {//todo get sprite URLs
        String Result = "/img/x";

        return Result;
    }

    public JsonObjectBuilder getJson() {
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("pos", this.positie.json())
                .add("size", Math.round(this.size))
                .add("img", getImg());
        return json;
    }

    public double getBallmaxX() {
        return ballmaxX;
    }

    public double getBallminX() {
        return ballminX;
    }

    public double getBallmaxY() {
        return ballmaxY;
    }

    public double getBallminY() {
        return ballminY;
    }
}
