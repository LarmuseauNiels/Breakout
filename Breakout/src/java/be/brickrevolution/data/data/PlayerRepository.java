package be.brickrevolution.data.data;

import be.brickrevolution.model.Player;
import java.sql.ResultSet;
import java.util.List;

public interface PlayerRepository {

    public List<Player> getPlayers();

    public void addPlayer(Player player);

    public Player getPlayerByUsername(String username);

    public Player getPlayerByEmail(String email);

    public void updateSinglePlayerHighscore(int score, String username);

    public void updateMultiPlayerHighscore(int scoreplayer1, int scoreplayer2, String playerUuid);

    public List<Player> getSinglePlayerHighscores();
    public List<Player> getMultiPlayerHighscores();
}
