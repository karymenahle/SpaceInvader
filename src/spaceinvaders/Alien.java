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
    
    private Game game;
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
      if (!game.isPausa()){
      }
      
    }

    @Override
    public void render(Graphics g) {
       
    }
    
    
}
