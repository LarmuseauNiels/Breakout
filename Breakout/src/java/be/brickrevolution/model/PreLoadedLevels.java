package be.brickrevolution.model;

import be.brickrevolution.data.data.LevelRepository;
import java.util.ArrayList;

public class PreLoadedLevels {
    private int ind;
    private ArrayList<BasicLevel> levels = new ArrayList<>();
    
    public PreLoadedLevels(BasicLevelMaker lm) {
        LevelRepository lr = LevelRepository.getInstance();
        int levelnmr = 2;
        while(lr.nextAvaible("m" + levelnmr)){
           levels.add(lm.MakeLevel("m" + levelnmr)); 
           levelnmr++;
        }
        ind = -1;
    }
    
    public BasicLevel next() {
        ind++;
        if(ind<2){
        return levels.get(ind);
        }
        else{
            return levels.get(0);
        }
    }

    
    
}
