/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Electel
 */
public class Bomb extends Item{

    private int width;
    private int height;
    private Game game;
    private int speed;

    private Animation bomba; //la animacion de la pelota (esta girando)
    private boolean bVisible; //
    
    public Bomb(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 2;
        this.bomba = new Animation(Assets.BombImages, 100); //se inicializa con la imagen principal 
        this.bVisible = true;
    }
    public boolean isVisible(){
        return bVisible;
    }
    public void changeVisibility(boolean b){
        this.bVisible = b;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    

    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {
        this.bomba.tick();

                setY(getY()+getSpeed());
    
    }
    
    public boolean intersecta(Player obj){
     return obj instanceof Player  && getPerimetro().intersects(((Player) obj).getPerimetro());
     }
           
    public Rectangle getPerimetro() {
     return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
            
    @Override
    public void render(Graphics g) {
        if(isVisible()){
          g.drawImage(bomba.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);  
        }
        
    }
}

