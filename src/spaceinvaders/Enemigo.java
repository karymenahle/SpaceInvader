/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Stack;

/**
 *
 * @author gerardogarzafox
 */
public class Enemigo extends Item{

    private int width;
    private int height;
    private Game game;
    private boolean alive;
    private Animation Alien;
    private int direction;


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
        this.direction = 1;
    }
    
    public void setDirection(int dir){
        this.direction = dir;
    }
    
    public int getDirection(){
        return direction;
    }

    /**
     * 
     * @return 
     */
    
    public boolean isAlive(){
        return alive;
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
        setY(getY()+getHeight());
        setDirection(getDirection()*-1);
    }

    /**
     *
     */
    @Override
    public void tick() {
        this.Alien.tick();
        if(isAlive()){
          setX(getX()+getDirection());  
        }
        

        if(!isAlive()){
            setWidth(0);
            setHeight(0);
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
       
    /**
     *
     * @param g
     */
    @Override
    public void render(Graphics g) { 
        g.drawImage(Alien.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);   
    }
}

