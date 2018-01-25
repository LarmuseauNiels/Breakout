package be.brickrevolution.model;

import java.util.ArrayList;
import java.util.Random;

public class BlockFactory {

    Random rand = new Random();
    private ArrayList<Modifier> PositiveModifiers;
    private ArrayList<Modifier> NegativeModifiers;
    private int modifierchance;
    private int chanceforpositive;

    public BlockFactory(ArrayList<Modifier> PositiveModifiers, ArrayList<Modifier> NegativeModifiers, int modifierchance, int chanceforpositive) {
        this.PositiveModifiers = PositiveModifiers;
        this.NegativeModifiers = NegativeModifiers;
        this.modifierchance = modifierchance;
        this.chanceforpositive = chanceforpositive;
    }

    public Block createBlock(Position positie, int width, int height, int collor) {
        Block result = new Block(positie, width, height, collor);
        if (rand.nextInt(100) < modifierchance) {
            result.setModifier(new Modifier(newmodifier()));
        }
        return result;
    }

    private Modifier newmodifier() {
        if (rand.nextInt(100) < this.chanceforpositive) {
            int index = rand.nextInt(this.PositiveModifiers.size());
            return this.PositiveModifiers.get(index);
        } else {
            int index = rand.nextInt(this.NegativeModifiers.size());
            return this.NegativeModifiers.get(index);
        }
    }
}
