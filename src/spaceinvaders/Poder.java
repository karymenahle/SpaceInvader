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
public class Poder extends Item {
    /**
 *
 * @author karymenahleacosta
 */

private int width;
private int height;
private Game game;
private int speed; //velocidad con la que cae
private boolean drop; //el poder cae 
private boolean color;//color del poder (si es que hay varios poderes de diferentes colores)

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public Poder(int x, int y, int width, int height, Game game) {
        super(x, y);        
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 2; //se inicializa a 2
        this.drop = true; //se inicializa con true
        this.color = true; //se inicializa con true
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }
    
    /**
     *
     * @return
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @param speed
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     *
     */
    public void isDropping(){
        this.drop = !this.drop;
    }
    
    /**
     *
     */
    public void changeColor(){
        this.color = !this.color;
    }
  
    /**
     *
     */
    @Override 
      public void tick(){
        //Solo se mueve hacia abajo
       if(drop){
          setY(getY() + getSpeed());  
       }
        
      }
    
    /**
     *
     * @return
     */
    public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), getWidth(), getHeight());
        }
           
    /**
     *
     * @param obj
     * @return
     */
    public boolean intersect(Player obj){
            return obj instanceof Player  && getPerimetro().intersects(((Player) obj).getPerimetro());
            }

       
    //To paint the item

    /**
     *
     * @param g
     */
     @Override 
    public void render(Graphics g){
        if(color){
           g.drawImage(Assets.grow, getX(), getY(), getWidth(), getHeight(), null); 
        }
        
    
    }
}

