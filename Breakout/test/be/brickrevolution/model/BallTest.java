
package be.brickrevolution.model;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BallTest {
    
    public BallTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void ballmovesright(){
       Ball b = new Ball(new Position(500, 500), 1, 0, 5, 1);
       b.nextPosition();
       assertTrue("ball moves right when direction 0 instead whent to " + b.getPositie().getX(), b.getPositie().getX() > 500);
    }
    
     @Test
    public void ballmovesleft(){
       Ball b = new Ball(new Position(500, 500), 1, 180, 5, 1);
       b.nextPosition();
       assertTrue("ball moves left when direction 180 instead whent to " + b.getPositie().getX(), b.getPositie().getX() < 500);
    }
    
    @Test
    public void ballmovesup(){
       Ball b = new Ball(new Position(500, 500), 1, 90, 5, 1);
       b.nextPosition();
       assertTrue("ball moves up when direction 90 instead whent to " + b.getPositie().getY(), b.getPositie().getY() > 500);
    }
    @Test
    public void ballmovesdown(){
       Ball b = new Ball(new Position(500, 500), 1, 270, 5, 1);
       b.nextPosition();
       assertTrue("ball moves down when direction 270 instead whent to " + b.getPositie().getY(), b.getPositie().getY() < 500);
    }
    @Test
    public void ballMirrorsDirectionVertical(){
       Ball b = new Ball(new Position(500, 500), 1, 45, 5, 1);
       b.mirrorVertical();
       assertTrue("ball mirrors angle vertically " + b.getDirection(), b.getDirection() == 135);
    }
    @Test
    public void ballMirrorsDirectionHorizontally(){
       Ball b = new Ball(new Position(500, 500), 1, 45, 5, 1);
       b.mirrorHorizontal();
       assertTrue("ball mirrors angle horizontaly " + b.getDirection(), b.getDirection() == 315);
    }
    @Test
    public void testCollision(){
        Ball b = new Ball(new Position(500, 500), 1, 45, 5, 1);
        assertTrue("collides on left side ",b.collideswidth(499,449,499,449));
        assertTrue("collides on inside ",b.collideswidth(501,499,501,449));
        assertTrue("no collision object below ball",!(b.collideswidth(494,480,494,480)));
        assertTrue("no collision object above ball",!(b.collideswidth(520,506,520,506)));

    }
   // @Test
    public void testCollor(){
        Collor c = new Collor(1);
        assertTrue("hex " + c.getMiddlehex(),false);
    }
    
    public class Collor{
        private String lighthex;
        private String middlehex;
        private String darkhex;

        public Collor(int col){
            if (col%2==1) {addcollorhexlight();}
            else{addcollorhexdark();}
            if (col/2 == 1||col/2 == 3) {addcollorhexlight();}
            else{addcollorhexdark();}
            if (col>=4) {addcollorhexlight();}
            else{addcollorhexdark();}
            }
        private void addcollorhexlight(){
        lighthex += "f0";
        middlehex += "c6";
        darkhex += "9b";
        }
        private void addcollorhexdark() {
        lighthex += "00";
        middlehex += "0a";
        darkhex += "0e";
        }
        public String getLighthex() {return lighthex; }
        public String getMiddlehex() {return middlehex;}
        public String getDarkhex() {return darkhex;}
    }
}
