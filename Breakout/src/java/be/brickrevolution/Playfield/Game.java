package be.brickrevolution.Playfield;

import be.brickrevolution.websocked.Websocket;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.websocket.Session;

public class Game {

    private final int tps = 60;
    private boolean isrunning;
    private Session sessie;
    private Websocket web;
    private JsonObjectBuilder jsonMaker;
    private IPlayfield pf;
    private betterloop bl;
    private boolean waitforcontinue = false;
    private boolean gameover = false;

    public Game(Session sessie, Websocket web) {
        this.sessie = sessie;
        this.web = web;
        this.isrunning = false;
    }

    public void start() {
        if (!gameover) {
            if (waitforcontinue) {
                this.pf.reset();
                setWaitforcontinue(false);
            } else {
                if (!isrunning) {
                    isrunning = true;
                    bl = new betterloop();
                    bl.start();
                }
            }
        }
    }

    public void stop() {
        if (isrunning) {
            isrunning = false;
        }
    }

    private class betterloop extends Thread {

        @Override
        public void run() {
            long lastTime = System.nanoTime();
            double amountOfTicks = tps;
            double ns = 1000000000;
            double delta = 0;
            while (isrunning) {
                long now = System.nanoTime();
                delta += ((now - lastTime) / ns) * amountOfTicks;
                lastTime = now;
                while (delta >= (1)) {
                    doGameUpdates();
                    delta--;
                }
            }
        }
    }

    private void doGameUpdates() {
        this.pf.doGameUpdates();
    }

    //JSON Makers
    public void visualupdate() {
        jsonMaker = Json.createObjectBuilder();
        this.jsonMaker.add("action", "visualupdate");
        this.jsonMaker.add("blocks", this.pf.getLevel().getJson());
        this.jsonMaker.add("ball", this.pf.getBall().getJson());
        this.jsonMaker.add("bpallet", this.pf.getBotumPallet().getJson());
        this.jsonMaker.add("tpallet", this.pf.getTopPallet().getJson());
        String result = jsonMaker.build().toString();
        this.web.sendToSession(this.getSessie(), result);
    }
    
    public void laatlevelin() {
        jsonMaker = Json.createObjectBuilder();
        this.jsonMaker.add("action", "load");
        this.jsonMaker.add("blocks", this.pf.getLevel().getJson());
        this.jsonMaker.add("ball", this.pf.getBall().getJson());
        this.jsonMaker.add("bpallet", this.pf.getBotumPallet().getJson());
        this.jsonMaker.add("tpallet", this.pf.getTopPallet().getJson());
        String result = jsonMaker.build().toString();
        this.web.sendToSession(this.getSessie(), result);
    }

    public void reload() {
        jsonMaker = Json.createObjectBuilder();
        this.jsonMaker.add("action", "reload");
        this.jsonMaker.add("blocks", this.pf.getLevel().getJson());
        this.jsonMaker.add("ball", this.pf.getBall().getJson());
        this.jsonMaker.add("bpallet", this.pf.getBotumPallet().getJson());
        this.jsonMaker.add("tpallet", this.pf.getTopPallet().getJson());
        String result = jsonMaker.build().toString();
        this.web.sendToSession(this.getSessie(), result);
    }

    public void sendUFO() {
        jsonMaker = Json.createObjectBuilder();
        this.jsonMaker.add("action", "ufo");
        String result = jsonMaker.build().toString();
        this.web.sendToSession(this.getSessie(), result);
    }

    public void sendChange() {
        jsonMaker.add("action", "move")
                .add("movement", Json.createObjectBuilder()
                        .add("ball", this.pf.getBall().getPositie().json())
                        .add("bpallet", this.pf.getBotumPallet().getPositie().json())
                        .add("tpallet", this.pf.getTopPallet().getPositie().json()))
                .add("time", this.pf.getTime());
        String result = jsonMaker.build().toString();
        this.web.sendToSession(getSessie(), result);
    }

    public void sendScore() {
        jsonMaker.add("action", "goal")
                .add("player1", this.pf.getPlayer1score());
        String result = jsonMaker.build().toString();
        this.web.sendToSession(getSessie(), result);
    }

    public void sendMPScore() {
        jsonMaker.add("action", "goal")
                .add("player1", this.pf.getPlayerScore(1))
                .add("player2", this.pf.getPlayerScore(2));
        String result = jsonMaker.build().toString();
        this.web.sendToSession(sessie, result);
    }

    public void sendText(String text, boolean noLoad) {
        jsonMaker.add("action", "showtext")
                .add("text", text)
                .add("noLoad", noLoad);
        String result = jsonMaker.build().toString();
        this.web.sendToSession(getSessie(), result);
    }

    public void sendRemBlock(int id) {
        jsonMaker.add("action", "remblock")
                .add("blockid", id);
        String result = jsonMaker.build().toString();
        this.web.sendToSession(getSessie(), result);
    }

    /* Getters and setters*/
    public IPlayfield getPlayfield() {
        return pf;
    }

    public void setWaitforcontinue(boolean waitforcontinue) {
        this.waitforcontinue = waitforcontinue;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    public void setPlayfield(IPlayfield pf) {
        this.pf = pf;
    }

    public Session getSessie() {
        return sessie;
    }

}
