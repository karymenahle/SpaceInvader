/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/**
 *
 * @author karymenahleacosta
 */
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author karymenahleacosta
 */
public class Laser extends Item{

    private int width;
    private int height;
    private Game game;
    private int speed;
    private int direction;
    
    public Laser(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.direction = 4;
        this.speed = 15;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    
    public int getDirection() {
        return direction;
    }

    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void setDirection(int direction) {
       this.direction = direction;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void tick() {

          
              setY(getY() - this.getSpeed());
        
 
         
          //reset x if colision
// reset x position and y position if colision
 
        if (getY() + 80 >= game.getHeight()) {
            setY(game.getHeight() - 80);
            game.setLasershoot(true);
        }
        else if (getY() <= -20) {
            setY(-20);
            game.setLasershoot(true);
      }
       
          
          
      }

    public boolean intersecta(Enemigo obj){
     return obj instanceof Enemigo  && getPerimetro().intersects(((Enemigo) obj).getPerimetro());
     }
           
    public Rectangle getPerimetro() {
     return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
            
    @Override
    public void render(Graphics g) {
            g.drawImage(Assets.laser, getX(), getY(), getWidth(), getHeight(), null);

        
    }
}
