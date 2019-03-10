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
    private boolean shooting;
    
    public Laser(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.direction = 4;
        this.speed = 10;
        this.shooting = true;
    }
    
    public boolean isShooting(){
        return shooting;
    }
    
    public void canShoot(){
        this.shooting = true;
    }
    public void cantShoot(){
        this.shooting = false;
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
    
    public void destroy(){
        setX(0);
        setY(0);
        setHeight(0);
        setWidth(0);
    }

    @Override
    public void tick() {
          //Si se presiona space empieza el juego
          
              if (game.isPausa() == false){
        //Solo se mueve a la derecha o a la izquierda 
          
                   
                   setY(getY() - this.getSpeed()); 
                
              
          
              
        
          
          
         
          //reset x if colision
// reset x position and y position if colision
 
        if (getY() >= game.getHeight()) {
     
          //  game.setLasershoot(true);
            canShoot();
        }
       
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
