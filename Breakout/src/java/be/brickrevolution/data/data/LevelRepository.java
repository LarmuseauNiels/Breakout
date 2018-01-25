package be.brickrevolution.data.data;

import be.brickrevolution.data.util.MySqlConnection;
import be.brickrevolution.model.Block;
import be.brickrevolution.model.BlockFactory;
import be.brickrevolution.model.Position;
import be.brickrevolution.util.BreakoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LevelRepository {

    private static final LevelRepository REPO = new LevelRepository();

    public static LevelRepository getInstance() {
        return REPO;
    }

    private static final String SQL_SELECT_LEVEL = "select * from level\n"
            + "left join posities as p on level.id = p.levelid \n"
            + "left join blok on p.blokid = blok.idblok\n"
            + "where level.naam = ?";
    private static final String SQL_SELECT_JUSTLEVEL = "select * from level\n"
            + "where naam = ?";

    public ArrayList<Block> getLevel(String naam, BlockFactory bf) {
        try (Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_SELECT_LEVEL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prep.setString(1, naam);

            try (ResultSet rs = prep.executeQuery()) {
                ArrayList<Block> blocks = new ArrayList<>();
                while (rs.next()) {
                    int x = rs.getInt("xcord");
                    int y = rs.getInt("ycord");
                    int width = rs.getInt("width");
                    int height = rs.getInt("height");
                    int color = rs.getInt("color");
                    blocks.add(bf.createBlock(new Position(x, y), height, width, color));
                }
                return blocks;
            }
        } catch (SQLException ex) {
            throw new BreakoutException("Unable to get level from database.", ex);
        }
    }

    public boolean nextAvaible(String naam) {
        try (Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_SELECT_JUSTLEVEL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prep.setString(1, naam);

            try (ResultSet rs = prep.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            throw new BreakoutException("Unable to get level from database.", ex);
        }
    }

    public String getDiffname(String naam) {
        try (Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_SELECT_JUSTLEVEL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prep.setString(1, naam);

            try (ResultSet rs = prep.executeQuery()) {
                String s = "easy";
                if (rs.next()) {
                    s = rs.getString("difficulty");
                }
                return s;
            }
        } catch (SQLException ex) {
            throw new BreakoutException("Unable to get difficulty name from database.", ex);
        }
    }
}