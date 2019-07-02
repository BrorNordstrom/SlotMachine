/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachinecw;

import java.awt.Image;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author Andreas Nordstrom
 */
public class Symbol implements ISymbol {
    
    private int value;
    private String image;
 
    public int getValue(){
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public ImageIcon getImage(){
        ImageIcon image;
        try {
            image = new ImageIcon(ImageIO.read(new File("Images\\" + this.image + ".png")).getScaledInstance(80,80, Image.SCALE_SMOOTH));
            return image;
    // This reads the image files in the folder created containing the images
    // The sizes of the images are scaled to 80x80
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
public void setImage(String image) {
    this.image = image;
}

public boolean equals(Symbol s) {
    boolean equalto =  this.getValue() == s.getValue();
    return equalto;
}
}
