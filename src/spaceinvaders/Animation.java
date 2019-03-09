/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;

/**
 *
 * @author Electel
 */
public class Animation {
    private int speed; //velocidad
    private int index; //controla la animacion de manera fluida
    private int staticIndex; //controlar animacion de los bricks
    private long lastTime; //actualizacion del ultimo sprite del brick
    private long timer; //contador
    private boolean nxtblock; //siguiente brick
    private BufferedImage[] frames; //donde se guardan las imagenes y el index las cambia
    
    /**
     *
     * @param frames
     * @param speed
     */
    public Animation(BufferedImage[] frames, int speed){
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
        staticIndex = 0;
        this.nxtblock = false;
    }
    
    /**
     *
     */
    public void nxtBlck(){
        this.staticIndex++;
    }
    
    /**
     *
     * @param sI
     */
    public void setStaticIndex(int sI){
        this.staticIndex = sI;
    }
    
    /**
     *
     * @return
     */
    public int getStaticIndex(){
        return staticIndex;
    }
    
    /**
     *
     */
    public void setNext(){
        setStaticIndex(getStaticIndex()+1);
    }
    
    /**
     *
     * @return
     */
    public BufferedImage getBlockFrame(){
        return frames[staticIndex];
    }
    
    /**
     *
     * @return
     */
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    /**
     *
     * @param block
     */
    public void isNxtBlock(boolean block){
        this.nxtblock = block;
    }

    /**
     *
     */
    public void tick(){
        
    timer += System.currentTimeMillis() - lastTime; //tiempo de la animacion
    lastTime = System.currentTimeMillis(); //ultimo tiempo de la animacion
    if( timer > speed){ //si el tiempo es mayor a speed
        index++; //se le aumenta 1 a index
        timer = 0; //se recetea el tiempo
        if(index >= frames.length){ //si index es mayor o igual a la longitud de frames
            index = 0; //se actualiza index 
        }
    }
    
    if (nxtblock){
        staticIndex++;
        isNxtBlock(!nxtblock);
    }

    }
        
}
