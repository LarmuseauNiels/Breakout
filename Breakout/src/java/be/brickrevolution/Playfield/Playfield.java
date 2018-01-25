package be.brickrevolution.Playfield;

import be.brickrevolution.data.data.LevelRepository;
import be.brickrevolution.data.data.Repositories;
import be.brickrevolution.model.ArtificialPlayer;
import be.brickrevolution.model.Ball;
import be.brickrevolution.model.BasicLevel;
import be.brickrevolution.model.BasicLevelMaker;
import be.brickrevolution.model.ActiveModifiers;
import be.brickrevolution.model.Pallet;
import be.brickrevolution.model.Position;

public class Playfield implements IPlayfield {

    private Ball ball;
    private BasicLevel level;
    private Pallet botumPallet;
    private Pallet topPallet;
    private final int playfieldwidth = 924;
    private final int playfieldheight = 668;
    private int player1score;
    private Game g;
    private ActiveModifiers modifiers = new ActiveModifiers();
    private int levelID = 1;
    private ArtificialPlayer bot;
    public boolean mp = false;
    BasicLevelMaker lm;
    private String playerUuid;
    private String difficulty = "m";

    private final Position ballstarPosition = new Position(50, 400);
    private final Position botumPalletStartPosition = new Position(450, 60);
    private final Position topPalletStartPosition = new Position(450, 640);

    public Playfield(Game g) {
        this.g = g;
        lm = new BasicLevelMaker();

        this.player1score = 0;
    }

    @Override
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        this.level = lm.MakeLevel("s" + difficulty + this.levelID);
        this.ball = new Ball(ballstarPosition, this.level.getDiff().getBallspeed(), -45, this.level.getDiff().getBallwidth(), 1);
        this.botumPallet = new Pallet(botumPalletStartPosition, this.level.getDiff().getPalletspeed(), this.level.getDiff().getPalletwidth());
        this.topPallet = new Pallet(topPalletStartPosition, this.level.getDiff().getPalletspeed(), this.level.getDiff().getPalletwidth());
        bot = new ArtificialPlayer(this.topPallet, this.ball, this.level.getDiff().getBotskill(), this.level.getDiff().getBotresponciveness());
    }

    @Override
    public void reset() {
        LevelRepository lr = LevelRepository.getInstance();
        if (lr.nextAvaible("s" + this.difficulty + (this.levelID+1))) {
            levelID++;
            this.getModifiers().clear();
            this.level = lm.MakeLevel("s" + this.difficulty + this.levelID);
            this.ball = new Ball(new Position(50, 400), this.level.getDiff().getBallspeed(), -45, this.level.getDiff().getBallwidth(), 1);
            this.botumPallet = new Pallet(botumPalletStartPosition, this.level.getDiff().getPalletspeed(), this.level.getDiff().getPalletwidth());
            this.topPallet = new Pallet(topPalletStartPosition, this.level.getDiff().getPalletspeed(), this.level.getDiff().getPalletwidth());
            bot = new ArtificialPlayer(this.topPallet, this.ball, this.level.getDiff().getBotskill(), this.level.getDiff().getBotresponciveness());
            this.g.laatlevelin();
        } else {
            if (levelID < 0) {
                this.g.sendText("Game over, your score is:" + player1score, true);
            } else {
                this.g.sendText("Levels over, your score is:" + player1score, true);
            }
            Repositories.getPlayerRepository().updateSinglePlayerHighscore(player1score, playerUuid);
            this.g.sendUFO();
            this.g.setGameover(true);
        }
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
        if (!this.mp) {
            this.bot.move();
        }

        this.getG().sendChange();
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
        }//leftwall
        if (this.ball.getPositie().getX() > this.playfieldwidth - 30 - ball.getSize()) {
            this.ball.mirrorVertical();
        }//rightwall
        if (this.ball.getPositie().getY() < 30 + ball.getSize()) {
            if (isGoal(this.ball.getPositie().getX())) {
                this.lose();
            } else {
                this.ball.mirrorHorizontal();
            }
        }
        if (this.ball.getPositie().getY() > this.playfieldheight - 30) {
            if (isGoal(this.ball.getPositie().getX())) {
                this.win();
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
        this.player1score += 100;
        this.getG().sendScore();
    }

    private void win() {
        this.g.stop();
        this.g.sendText("Gewonnen, score: " + this.player1score, false);
        this.g.setWaitforcontinue(true);
    }

    private void lose() {
        this.g.stop();
        this.g.sendText("Verloren, score: " + this.player1score + " ", false);
        this.levelID = -1;
        this.g.setWaitforcontinue(true);
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
    public int getPlayer1score() {
        return player1score;
    }

    @Override
    public void setPlayer1score(int player1score) {
        this.player1score = player1score;
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
    public int getPlayerScore(int i) {
        return player1score;
    }

    @Override
    public int getTime() {
        return 0;
    }

    @Override
    public void setPlayerUuid(String uuid) {
        playerUuid = uuid;
    }

    @Override
    public int getPlayerthathitlast() {
        return 1;
    }

    @Override
    public ActiveModifiers getModifiers() {
        return modifiers;
    }

    @Override
    public void setPlayerthathitlast(int playerthathitlast) {
    }
}
