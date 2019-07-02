/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slotmachinecw;
import javax.swing.ImageIcon;
/**
 *
 * @author Andreas Nordstrom
 */
public interface ISymbol {
    
    // Below I am creating my setters and getters
    
    public void setImage(String image);
    
    public ImageIcon getImage();
    
    public void setValue(int value);
    
    public int getValue();

       
}