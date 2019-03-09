/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerardogarzafox
 */
public class Enemigo extends Item{

    private int width;
    private int height;
    private int initialX;
    private int initialY;
    private Game game;
    private boolean alive;
    private boolean showpoints;
    private Animation Alien;
    private int direction;
    private int time;

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param game
     */
    public Enemigo(int x, int y, int width, int height, Game game) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.game = game;
        this.Alien = new Animation(Assets.AlienImages, 100);
        this.alive = true;
        this.direction = 2;
        this.initialX = x;
        this.initialY = y;
        this.showpoints = false;
        this.time = 15;
    }
    
    public void setTime(int t){
        this.time=t;
    }
    
    public int getTime(){
        return time;
    }
        
    public void reset(){
        setX(initialX);
        setY(initialY);
    }
    public void setDirection(int dir){
        this.direction = dir;
    }
    
    public int getDirection(){
        return direction;
    }
    public void showScore(){
        this.showpoints=true;
    }

    public boolean isShowpoints() {
        return showpoints;
    }

    public void setShowpoints(boolean showpoints) {
        this.showpoints = showpoints;
    }

    /**
     * 
     * @return 
     */
    
    public boolean isAlive(){
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    public void changeAlive(){
        this.alive = !this.alive;
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
    public int getHeight() {
        return height;
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
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    public void SwitchLayer(){
      if (!game.isPausa()){
            setY(getY()+getHeight());
            setDirection(getDirection()*-1);
      }
    }

    /**
     *
     */
    @Override
    public void tick() {
         if (!game.isPausa()){
        this.Alien.tick();
        if(isAlive()){
          setX(getX()+getDirection());  
        }


        if(!isAlive()){
            setWidth(0);
            setHeight(0);
        }
         }
    }
    

    


    
 
       //actualiza nuestra animacion de bloque
    

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
    public boolean intersecta(Laser obj){
        return obj instanceof Laser  && getPerimetro().intersects(((Laser) obj).getPerimetro());
     }
    public boolean intersect (Player obj){
        return obj instanceof Player && getPerimetro().intersects(((Player)obj).getPerimetro());
    }
       
    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g) { 
        

           g.drawImage(Alien.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);  
        
        
    }
}

