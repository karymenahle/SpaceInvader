/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Graphics;

/**
 *
 * @author karymenahleacosta
 */
public class Alien extends Item{
    
    boolean moveRight;
    boolean moveLeft;
    boolean isVisible;

    public Alien(int x, int y) {
        super(x, y);
        moveLeft=false;
        moveRight=true;
        isVisible=true;
    }

    @Override
    public void tick() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
