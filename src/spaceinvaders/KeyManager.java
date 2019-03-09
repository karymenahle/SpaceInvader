/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author karymenahleacosta
 */
public class KeyManager implements KeyListener {
    public boolean up; // para mover hacia arriba
    public boolean down; // para mover hacia abajo
    public boolean left; // para mover hacia la izquierda
    public boolean right; // para mover a la derecha
    public boolean enter; //Para ver si esta oprimida la tecla space
    public boolean pause; //para poner el juego en pausa
    public boolean again; //para reiniciar el juego
    public boolean save; //para guardar el juego en un archivo 
    public boolean load; //para cargar el juego de un archivo
    public boolean shoot;
    
    private boolean keys[]; //para guardar los movimientos
    
    public KeyManager(){
    keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
       //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
       //set true to every key pressed
    keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
       //set false to every key released
   keys[e.getKeyCode()] = false;
    }
    
    public void tick(){
        
       // to enable o disable every key 
    left = keys[KeyEvent.VK_LEFT];
    right = keys[KeyEvent.VK_RIGHT];
    enter = keys[KeyEvent.VK_ENTER];
    pause = keys[KeyEvent.VK_P]; //tecla P para poner en pausa
    again = keys[KeyEvent.VK_R]; //Tecla R para restart
    save = keys[KeyEvent.VK_G]; //Tecla G para guardar juego
    load = keys[KeyEvent.VK_C]; //Tecla C para cargar juego
    shoot=keys[KeyEvent.VK_SPACE];
    }
    
}

