package be.brickrevolution.Playfield;

import be.brickrevolution.model.ActiveModifiers;
import be.brickrevolution.model.Ball;
import be.brickrevolution.model.BasicLevel;
import be.brickrevolution.model.Pallet;


public interface IPlayfield {

    public void reset();

    public void movePallet(Pallet pallet, boolean totheright);

    public void doGameUpdates();

    public void addScoreForBlockBreak();

    /* getters and setters*/
    public Ball getBall();

    public void setBall(Ball ball);

    public BasicLevel getLevel();

    public void setLevel(BasicLevel testlevel);

    public Pallet getBotumPallet();

    public void setBotumPallet(Pallet botumPallet);

    public Pallet getTopPallet();

    public void setTopPallet(Pallet topPallet);

    public int getPlayfieldwidth();

    public int getPlayfieldheight();

    public int getPlayer1score();

    public void setPlayer1score(int player1score);

    public Game getG();

    public void setG(Game g);

    public int getPlayerScore(int i);

    public int getTime();

    public void setPlayerUuid(String string);

    public int getPlayerthathitlast();

    public ActiveModifiers getModifiers();

    public void setPlayerthathitlast(int playerthathitlast);

    public void setDifficulty(String string);
}
