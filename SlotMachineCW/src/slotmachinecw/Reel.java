/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachinecw;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Andreas Nordstrom
 */
public class Reel {
    ArrayList <Symbol> reelSymbols;
    String[] symbolnames = {"cherry", "lemon", "plum", "watermelon", "bell", "redseven"};
// Array list created with the names of symbols
    
    public Reel() {
        reelSymbols = new ArrayList<Symbol>();
        // Creates a new symbol object that will then be added to the first array
        
    for (int i = 0; i < 6; i++){
    reelSymbols.add(new Symbol());
    reelSymbols.get(i).setImage(symbolnames[i]);
    // Uses the symbol name from array to initialise the images
    reelSymbols.get(i).setValue(i+ 2);
    }
    }
    public Symbol[] spin() {
        Collections.shuffle(reelSymbols);
    return reelSymbols.toArray(new Symbol[reelSymbols.size()]);
    //Shuffles the arraylist and returns it as an array
    }
}
