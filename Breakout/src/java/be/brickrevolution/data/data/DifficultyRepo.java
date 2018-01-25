package be.brickrevolution.data.data;

import be.brickrevolution.data.util.MySqlConnection;
import be.brickrevolution.model.Difficulty;
import be.brickrevolution.util.BreakoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DifficultyRepo {

    private static final DifficultyRepo REPO = new DifficultyRepo();

    public static DifficultyRepo getInstance() {
        return REPO;
    }
    private static final String SQL_SELECT_DIFFICULTY = "select * from difficulty\n"
            + "where naam = ?";

    public Difficulty getDifficulty(String name) {
        try (Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_SELECT_DIFFICULTY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prep.setString(1, name);

            try (ResultSet rs = prep.executeQuery()) {
                Difficulty d;
                if (rs.next()) {
                    d = new Difficulty(rs.getInt("palletwidth"), rs.getDouble("palletspeed"), rs.getInt("ballwidth"), rs.getDouble("ballspeed"),
                            rs.getInt("botskill"), rs.getInt("botresponcivenes"), rs.getInt("modifierchance"), rs.getInt("chanceforpositive"));
                    return d;
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            throw new BreakoutException("Unable to get Difficulty from database.", ex);
        }
    }
}
