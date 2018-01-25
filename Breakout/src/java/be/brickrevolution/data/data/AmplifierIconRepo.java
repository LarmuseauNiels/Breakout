package be.brickrevolution.data.data;

import be.brickrevolution.data.util.MySqlConnection;
import be.brickrevolution.util.BreakoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AmplifierIconRepo {

    private static final AmplifierIconRepo REPO = new AmplifierIconRepo();

    public static AmplifierIconRepo getInstance() {
        return REPO;
    }
    private static final String SQL_SELECT_ICON = "select * from amplifiericon\n"
            + "where id = ?";

    public String getICON(int id) {
        try (Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_SELECT_ICON, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prep.setInt(1, id);
            try (ResultSet rs = prep.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("icon");
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            throw new BreakoutException("Unable to get icon from database.", ex);
        }
    }
}
