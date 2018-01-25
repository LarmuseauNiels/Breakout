package be.brickrevolution.data.data;

import be.brickrevolution.data.util.MySqlConnection;
import be.brickrevolution.model.Modifier;
import be.brickrevolution.util.BreakoutException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AmplifierRepo {

    private static final AmplifierRepo REPO = new AmplifierRepo();

    public static AmplifierRepo getInstance() {
        return REPO;
    }

    private static final String SQL_SELECT_MODIFIERS = "select * from amplifier\n"
            + "where effect = ?";

    public ArrayList<Modifier> getModifiers(int effect) {
        try (Connection con = MySqlConnection.getConnection();
                PreparedStatement prep = con.prepareStatement(SQL_SELECT_MODIFIERS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            prep.setInt(1, effect);
            try (ResultSet rs = prep.executeQuery()) {
                ArrayList<Modifier> m = new ArrayList<>();
                while (rs.next()) {
                    int timeinticks = rs.getInt("time");
                    double ballspeedamplifier = rs.getDouble("ballspeedamplifier");
                    double ballwidthamplifier = rs.getDouble("ballwidthamplifier");
                    double palletwidthamplifier = rs.getDouble("palletwidthamplifier");
                    int iconid = rs.getInt("icon");
                    m.add(new Modifier(timeinticks, ballspeedamplifier, ballwidthamplifier, palletwidthamplifier, iconid));
                }
                return m;
            }
        } catch (SQLException ex) {
            throw new BreakoutException("Unable to get modifiers from database.", ex);
        }
    }
}
