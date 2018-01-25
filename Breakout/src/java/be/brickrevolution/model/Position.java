package be.brickrevolution.model;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x >= 0 && x <= 1000) {
            this.x = x;
        } else {
            this.x = this.x;
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (y >= 0 && y <= 1000) {
            this.y = y;
        } else {
            this.y = this.y;
        }
    }

    public void moveRight(double amount) {
        this.setX(x + amount);
    }

    public void moveLeft(double amount) {
        this.setX(x - amount);
    }

    public JsonObjectBuilder json() {
        JsonObjectBuilder json = Json.createObjectBuilder();
        json.add("x", Math.round(this.x))
                .add("y", Math.round(668 - this.y));
        return json;
    }

}
