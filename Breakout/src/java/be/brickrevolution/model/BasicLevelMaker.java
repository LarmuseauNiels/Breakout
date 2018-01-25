package be.brickrevolution.model;

import be.brickrevolution.data.data.AmplifierRepo;
import be.brickrevolution.data.data.DifficultyRepo;
import be.brickrevolution.data.data.LevelRepository;
import java.util.ArrayList;

public class BasicLevelMaker implements LevelMaker {

    @Override
    public BasicLevel MakeLevel(String l) {
        LevelRepository lr = LevelRepository.getInstance();
        String diffname = lr.getDiffname(l);
        Difficulty diff = DifficultyRepo.getInstance().getDifficulty(diffname);
        BlockFactory bf = new BlockFactory(AmplifierRepo.getInstance().getModifiers(1), AmplifierRepo.getInstance().getModifiers(0), diff.getModifierchance(), diff.getChanceforpositive());
        ArrayList<Block> blocks = lr.getLevel(l, bf);
        BasicLevel level = new BasicLevel(blocks, diff);
        return level;
    }

}
