package be.brickrevolution.model;

import be.brickrevolution.Playfield.IPlayfield;
import java.util.ArrayList;

public class ActiveModifiers {

    private ArrayList<Modifier> modifiers;

    public ActiveModifiers() {
        modifiers = new ArrayList<>();
    }

    public void addModifier(Modifier m) {
        modifiers.add(m);
    }

    public void clear() {
        modifiers = new ArrayList<>();
    }

    public void runupdate(IPlayfield pf) {
        ArrayList<Modifier> toremove = new ArrayList<>();
        modifiers.forEach((modifier) -> {
            if (modifier.getExecuted()) {
                if (modifier.getTimeinticks() == 0) {
                    modifier.lowertimeinticks();
                    modifier.undo(pf);
                    toremove.add(modifier);
                } else {
                    modifier.lowertimeinticks();
                }
            } else {
                modifier.execute(pf);
            }
        });
        toremove.forEach((modifier) -> {
            modifiers.remove(modifier);
        });
    }
}
