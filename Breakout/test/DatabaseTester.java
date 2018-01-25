/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import be.brickrevolution.data.data.MySqlPlayerRepository;
import be.brickrevolution.data.data.PlayerRepository;
import be.brickrevolution.data.util.MySqlConnection;
import be.brickrevolution.util.BreakoutException;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author steve
 */
public class DatabaseTester {
    
    public DatabaseTester() {
    }
    
    @Test
    public void testConnection(){
        try {
            PlayerRepository players = MySqlPlayerRepository.getInstance();
            
        } catch (BreakoutException ex){
            fail("Failed to create connection");
        }
        
        
        
        
    }
}
