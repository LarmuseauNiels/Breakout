package be.brickrevolution.data.data;

import be.brickrevolution.data.util.MySqlConnection;
import be.brickrevolution.model.Player;
import be.brickrevolution.util.BreakoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPlayerRepository implements PlayerRepository{
    // Singleton : 
    private static final MySqlPlayerRepository REPO = new MySqlPlayerRepository();

    public static MySqlPlayerRepository getInstance() {
        return REPO;
    }

    private static final String SQL_SELECT_ALL_PlAYERS = "select * from player";
    private static final String SQL_ADD_PLAYER = "insert into player(username, password, email,uuid) values(?, ?, ?, ?)";
    private static final String SQL_SELECT_PLAYER_BY_USERNAME = "select * from player where username = ?";
    private static final String SQL_SELECT_PLAYER_BY_EMAIL = "select * from player where email=?";
    private static final String SQL_UPDATE_SINGLEPLAYER_HIGHSCORE = "update player set singleplayerhighscore = ? where uuid = ? and singleplayerhighscore < ?";
    private static final String SQL_UPDATE_MULTIPLAYER_HIGHSCORE = "update player set multiplayerhighscoreplayer1 = ?, multiplayerhighscoreplayer2 = ? where uuid = ? and multiplayerhighscoreplayer1 < ? and multiplayerhighscoreplayer2 < ? ";
    private static final String SQL_SELECT_SINGLEPLAYERHIGHSCORE = "select username, singleplayerhighscore from player where singleplayerhighscore !=0 ORDER BY singleplayerhighscore desc";
    private static final String SQL_SELECT_MULTIPLAYERHIGHSCORE = "select username, multiplayerhighscoreplayer1,multiplayerhighscoreplayer2 from player where multiplayerhighscoreplayer1 !=0 and multiplayerhighscoreplayer2 !=0 ORDER BY multiplayerhighscoreplayer1 desc";
            
            private static final String FIELD_IDPLAYER = "idplayer";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_SINGLEPLAYERHIGHSCORE = "singleplayerhighscore";
    private static final String FIELD_MULTIPLAYERHIGHSCOREPLAYER1 = "multiplayerhighscoreplayer1";
    private static final String FIELD_MULTIPLAYERHIGHSCOREPLAYER2 = "multiplayerhighscoreplayer2";
    private static final String FIELD_UUID = "uuid";
    
    @Override
    public List<Player> getPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public void addPlayer(Player player) {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_ADD_PLAYER, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            prep.setString(1, player.getUsername());
            prep.setString(2, player.getPassword());
            prep.setString(3, player.getEmail());
            prep.setString(4, player.getUuid());
           
            
            prep.executeUpdate();
                    
            try(ResultSet rs = prep.getGeneratedKeys())
            {
                int newPlayerId = -1;
                
                if (rs.next())
                {
                    newPlayerId = rs.getInt(1);
                }
                
                if (newPlayerId < 0)
                {
                    throw new BreakoutException("Unable to add player to database.");
                }
                
                player.setIdplayer(newPlayerId);
            }
        }
        catch (SQLException ex)
        {
            throw new BreakoutException("Unable to add player to database.", ex);
        }
    } 
    public List<Player> getSinglePlayerHighscores(){
    try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_SELECT_SINGLEPLAYERHIGHSCORE, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            List<Player> players = new ArrayList<>();
            
            try(ResultSet rs = prep.executeQuery())
            {
                

                while (rs.next())
                {
                    Player player = this.SPHighscoreResultset2Player(rs);
                    players.add(player);
                }

                return players;
            }
        }
        catch(SQLException ex)
        {
            throw new BreakoutException("Unable to get highscores from database.", ex);
        }
    }
    public List<Player> getMultiPlayerHighscores(){
    try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_SELECT_MULTIPLAYERHIGHSCORE, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            List<Player> players = new ArrayList<>();
            
            try(ResultSet rs = prep.executeQuery())
            {
                

                while (rs.next())
                {
                    Player player = this.MPHighscoreResultset2Player(rs);
                    players.add(player);
                }

                return players;
            }
        }
        catch(SQLException ex)
        {
            throw new BreakoutException("Unable to get highscores from database.", ex);
        }
    }
    
    
    
    @Override
    public Player getPlayerByUsername(String username)
    {
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_SELECT_PLAYER_BY_USERNAME, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            prep.setString(1, username);
            
            try(ResultSet rs = prep.executeQuery())
            {
                Player player = null;

                if(rs.next())
                {
                    player = this.resultSet2Player(rs);

                }

                return player;
            }
        }
        catch(SQLException ex)
        {
            throw new BreakoutException("Unable to get player from database.", ex);
        }
    }
    @Override
    public Player getPlayerByEmail(String email){
        try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_SELECT_PLAYER_BY_EMAIL, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            prep.setString(1, email);
            
            try(ResultSet rs = prep.executeQuery())
            {
                Player player = null;

                if(rs.next())
                {
                    player = this.resultSet2Player(rs);

                }

                return player;
            }
        }
        catch(SQLException ex)
        {
            throw new BreakoutException("Unable to get player from database.", ex);
        }
    };
    
    
    private Player resultSet2Player(ResultSet rs) throws SQLException
    {
        int idplayer = rs.getInt(FIELD_IDPLAYER);
        String username = rs.getString(FIELD_USERNAME);
        String password = rs.getString(FIELD_PASSWORD);
        String email = rs.getString(FIELD_EMAIL);
        int singleplayerhighscore = rs.getInt(FIELD_SINGLEPLAYERHIGHSCORE);
        int multiplayerhighscoreplayer1 = rs.getInt(FIELD_MULTIPLAYERHIGHSCOREPLAYER1);
        int multiplayerhighscoreplayer2 = rs.getInt(FIELD_MULTIPLAYERHIGHSCOREPLAYER2);
        String uuid = rs.getString(FIELD_UUID);
         
        

        Player player = new Player(idplayer, username, password, email, singleplayerhighscore,multiplayerhighscoreplayer1,multiplayerhighscoreplayer2,uuid);

        return player;
    }
    
    private Player SPHighscoreResultset2Player(ResultSet rs) throws SQLException{
        String username = rs.getString(FIELD_USERNAME);
        int singleplayerhighscore = rs.getInt(FIELD_SINGLEPLAYERHIGHSCORE);
        Player player = new Player(username, singleplayerhighscore);
        return player;
    }
    private Player MPHighscoreResultset2Player(ResultSet rs) throws SQLException{
        String username = rs.getString(FIELD_USERNAME);
        int multiplayerhighscoreplayer1 = rs.getInt(FIELD_MULTIPLAYERHIGHSCOREPLAYER1);
        int multiplayerhighscoreplayer2 = rs.getInt(FIELD_MULTIPLAYERHIGHSCOREPLAYER2);
        Player player = new Player(username, multiplayerhighscoreplayer1,multiplayerhighscoreplayer2);
        return player;
    }

    @Override
    public void updateSinglePlayerHighscore(int score, String username){
    try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_UPDATE_SINGLEPLAYER_HIGHSCORE, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            prep.setInt(1, score);
            prep.setString(2, username);
            prep.setInt(3, score);
            prep.executeUpdate();
            
        }
        catch (SQLException ex)
        {
            throw new BreakoutException("Unable to add player to database.", ex);
        }
    } 
    @Override
    public void updateMultiPlayerHighscore(int scorePlayer1,int scorePlayer2, String uuid){
    try(Connection con = MySqlConnection.getConnection();
            PreparedStatement prep = con.prepareStatement(SQL_UPDATE_MULTIPLAYER_HIGHSCORE, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            prep.setInt(1, scorePlayer1);
            prep.setInt(2, scorePlayer2);
            prep.setString(3, uuid);
            prep.setInt(4, scorePlayer1);
            prep.setInt(5, scorePlayer2);
            prep.executeUpdate();
            
        }
        catch (SQLException ex)
        {
            throw new BreakoutException("Unable to update multiplayer score.", ex);
        }
    } 
    
}
