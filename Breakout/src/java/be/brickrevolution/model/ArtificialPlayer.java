package be.brickrevolution.model;

import java.util.Random;

public class ArtificialPlayer {

    private int skill;
    private Pallet pallet;
    private Ball b;
    Random rand;
    private int stabilize;
    private boolean miss;
    private boolean direction;
    private int reactiontime;

    public ArtificialPlayer(Pallet p, Ball b, int skill, int ticktstoreact) {
        this.pallet = p;
        this.skill = skill;
        this.b = b;
        rand = new Random();
        miss = false;
        this.reactiontime = ticktstoreact;
    }

    public void move() {
        double centreball = this.b.getPositie().getX() + (this.b.getSize() / 2);
        double centrepallet = this.pallet.getPositie().getX() + (this.pallet.getWidth() / 2);
        if (stabilize == reactiontime) {
            miss = rand.nextInt(1000) < skill;
            direction = centreball < centrepallet && !miss;
            stabilize = 0;
        } else {
            stabilize++;
        }
        movePallet(direction);

    }

    private void movePallet(boolean left) {
        if (left) {
            this.pallet.moveLeft();
        } else {
            this.pallet.moveRight();
        }
    }

}
