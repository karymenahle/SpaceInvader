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
 * @author karymenahleacosta
 */
public class Player extends Item{
private int direction;
private int width;
private int height;
private Game game;
private int speed;
private int lives;
private boolean bGrow; //booleana que sirve para identificar si esta la van pequeña o grande

    public Player(int x, int y, int direction, int width, int height, Game game) {
        super(x, y);
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.game = game;
        this.speed = 6;
        this.lives = 3; //Se inicilializan las vidas como 3
        this.bGrow = false;
    }

    public int getDirection() {
        return direction;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public int getLives(){
        return lives;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void setLives(int life){
        this.lives = life;
    }
    public boolean isbGrow(){
       return bGrow;
    }
    public void setbGrow(boolean b){
        bGrow = b;
    }
    
    @Override 
      public void tick(){

          //Si se presiona space empieza el juego
          if (game.isStart()){
              if (game.isPausa() == false){
        //Solo se mueve a la derecha o a la izquierda 
        
          if(game.getKeyManager().left){
          setX(getX() - getSpeed());
          }
          
          if(game.getKeyManager().right){
          setX(getX() + getSpeed());
          }
          
         
          //reset x if colision
       if (getX() + getWidth() >= game.getWidth()){
       setX(game.getWidth() - getWidth());
       }   
      //colision with left wall
      else if (getX() <= 0){
       setX(0); 
        }
       
       //change size if collision with powerup
       if(isbGrow()){
           setWidth(200);
       }else{
           setWidth(150);
       }
          }
          }
      }
    
      //Se hacen dos rectangulos para cuando intesecte con un lado la direccion sea hacia la derecha y con el otro lado a la izquierda
      
      //primer rectangulo es del lado izquierdo 
       public Rectangle getPerimetro() {
         return new Rectangle(getX(), getY(), getWidth()/2, getHeight());
        }
       
      //segundo rectangulo es del lado derecho 
       public Rectangle getPerimetro2() {
         return new Rectangle(getX()+getWidth()/2, getY(), getWidth()/2, getHeight());
        }
       
       //intesecta con el lado izquierdo
       public boolean intersecta(Laser obj){
            return obj instanceof Laser  && getPerimetro().intersects(((Laser) obj).getPerimetro());
            }
       //intersecta con el lado derecho
       public boolean intersecta2(Laser obj){
        return obj instanceof Laser  && getPerimetro2().intersects(((Laser) obj).getPerimetro());
            }

       
    //To paint the item
     @Override 
    public void render(Graphics g){
        if(bGrow){
            g.drawImage(Assets.playerGrow, getX(), getY(), getWidth(), getHeight(), null);
        }else{
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
         }
        
        
        //draws player lives
        for(int i = 0; i < getLives();i++){
            g.drawImage(Assets.lives, 15+35*i, game.getHeight()-40, 30, 30, null);
        }
    }

}
