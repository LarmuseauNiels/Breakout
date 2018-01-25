package be.brickrevolution.Playfield;

import be.brickrevolution.data.data.Repositories;
import be.brickrevolution.model.ActiveModifiers;
import be.brickrevolution.model.Ball;
import be.brickrevolution.model.BasicLevel;
import be.brickrevolution.model.BasicLevelMaker;
import be.brickrevolution.model.Pallet;
import be.brickrevolution.model.Position;
import be.brickrevolution.model.PreLoadedLevels;

public class PlayfieldMP implements IPlayfield {
    private Ball ball;
    private BasicLevel level;
    private Pallet botumPallet;
    private Pallet topPallet;
    private final int playfieldwidth = 924;
    private final int playfieldheight = 668;
    private int playerscore[] = new int[3];
    private int timeinticks;
    private int playerthathitlast = 0;
    private Game g;
    private ActiveModifiers modifiers = new ActiveModifiers();
    private int levelID = 1;
    BasicLevelMaker lm;
    private PreLoadedLevels pll;
    private String playerUuid;
    private final Position ballstarPosition = new Position(50, 400);
    private final Position botumPalletStartPosition = new Position(450, 60);
    private final Position topPalletStartPosition = new Position(450, 640);

    public PlayfieldMP(Game g) {
        this.g = g;
        lm = new BasicLevelMaker();
        this.level = lm.MakeLevel("m1");
        this.ball = new Ball(ballstarPosition, this.level.getDiff().getBallspeed(), -45, this.level.getDiff().getBallwidth(), 1);
        this.botumPallet = new Pallet(botumPalletStartPosition, this.level.getDiff().getPalletspeed(), this.level.getDiff().getPalletwidth());
        this.topPallet = new Pallet(topPalletStartPosition, this.level.getDiff().getPalletspeed(), this.level.getDiff().getPalletwidth());
        this.playerscore[1] = 0;
        this.playerscore[2] = 0;
        this.timeinticks = 4 * 3600;
        this.pll = new PreLoadedLevels(lm);
    }

    @Override
    public void reset() {
    }

    @Override
    public void movePallet(Pallet pallet, boolean totheright) {
        if (totheright) {
            pallet.moveRight();
        } else {
            pallet.moveLeft();
        }
    }

    @Override
    public void doGameUpdates() {
        checkcollisions();
        this.getModifiers().runupdate(this);
        this.ball.nextPosition();
        this.getG().sendChange();
        this.checktime();
    }

    private void checktime() {
        if (this.timeinticks > 0) {
            this.timeinticks--;
        } else {
            endgame();
        }
        if (this.timeinticks % 3600 == 0) {
            this.newlevel();
        }
    }

    private void newlevel() {
        this.level = pll.next();
        this.g.reload();
    }

    private void endgame() {
        this.g.setGameover(true);
        this.g.stop();
        if (playerscore[1] > playerscore[2]) {
            this.g.sendText("Player 1 (blauw) Geeft gewonnen!", true);
        } else {
            if (playerscore[1] < playerscore[2]) {
                this.g.sendText("Player 2 (rood) Geeft gewonnen!", true);
            } else {
                this.g.sendText("Times up!, gelijk", true);
            }
        }
        Repositories.getPlayerRepository().updateMultiPlayerHighscore(playerscore[1], playerscore[2], playerUuid);
        this.g.sendUFO();
        this.g.setWaitforcontinue(true);
    }

    private void checkcollisions() {
        this.botumPallet.checkPallet(this);
        this.topPallet.checkPallet(this);
        this.checkfield();
        this.level.checkLevel(this);
    }

    private void checkfield() {
        if (this.ball.getPositie().getX() < 30) {
            this.ball.mirrorVertical();
        }
        if (this.ball.getPositie().getX() > this.playfieldwidth - 30 - ball.getSize()) {
            this.ball.mirrorVertical();
        }
        if (this.ball.getPositie().getY() < 30 + ball.getSize()) {
            if (isGoal(this.ball.getPositie().getX())) {
                this.player2goal();
            } else {
                this.ball.mirrorHorizontal();
            }
        }
        if (this.ball.getPositie().getY() > this.playfieldheight - 30) {
            if (isGoal(this.ball.getPositie().getX())) {
                this.player1goal();
            } else {
                this.ball.mirrorHorizontal();
            }
        }
    }

    private boolean isGoal(double x) {
        return (((132 < x) && (x < 264)) || ((396 < x) && (x < 528)) || ((660 < x) && (x < 792)));
    }

    @Override
    public void addScoreForBlockBreak() {
        this.playerscore[playerthathitlast] += 100;
        this.getG().sendMPScore();
    }

    private void player1goal() {
        this.playerscore[1] += 1000;
        this.ball.setPositie(new Position(50, 400));
        this.getG().sendMPScore();
    }

    private void player2goal() {
        this.playerscore[2] += 1000;
        this.ball.setPositie(new Position(50, 400));
        this.getG().sendMPScore();
    }
    /* getters and setters
     * @return 
     */
    @Override
    public Ball getBall() {
        return ball;
    }

    @Override
    public void setBall(Ball ball) {
        this.ball = ball;
    }

    @Override
    public BasicLevel getLevel() {
        return level;
    }

    @Override
    public void setLevel(BasicLevel level) {
        this.level = level;
    }

    @Override
    public Pallet getBotumPallet() {
        return botumPallet;
    }

    @Override
    public void setBotumPallet(Pallet botumPallet) {
        this.botumPallet = botumPallet;
    }

    @Override
    public Pallet getTopPallet() {
        return topPallet;
    }

    @Override
    public void setTopPallet(Pallet topPallet) {
        this.topPallet = topPallet;
    }

    @Override
    public int getPlayfieldwidth() {
        return playfieldwidth;
    }

    @Override
    public int getPlayfieldheight() {
        return playfieldheight;
    }

    @Override
    public Game getG() {
        return g;
    }

    @Override
    public void setG(Game g) {
        this.g = g;
    }

    @Override
    public int getPlayer1score() {
        return playerscore[1];
    }

    @Override
    public int getPlayerScore(int i) {
        return playerscore[i];
    }

    @Override
    public void setPlayer1score(int player1score) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTime() {
        return this.timeinticks;
    }

    @Override
    public void setPlayerUuid(String uuid) {
        playerUuid = uuid;
    }

    @Override
    public int getPlayerthathitlast() {
        return playerthathitlast;
    }

    @Override
    public ActiveModifiers getModifiers() {
        return modifiers;
    }

    @Override
    public void setPlayerthathitlast(int playerthathitlast) {
        this.playerthathitlast = playerthathitlast;
    }

    @Override
    public void setDifficulty(String string) {
    }
}
