package be.brickrevolution.data.data;

public class Repositories {

    private static final PlayerRepository playerRepository = new MySqlPlayerRepository();

    public static PlayerRepository getPlayerRepository() {
        return playerRepository;
    }

    private Repositories() {
    }

}
