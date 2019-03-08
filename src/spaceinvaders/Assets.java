/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.image.BufferedImage;

/**
 *
 * @author karymenahleacosta
 */
public class Assets {
    
    public static BufferedImage background; // to store background image 
    public static BufferedImage player; // to store the player image
    public static BufferedImage pause; //variable de pausa
    public static BufferedImage gameover; //variable de que perdio
    public static BufferedImage win; //variable de que gano 
    public static SoundClip song; //cancion del juego
    public static BufferedImage laser;
    
    public static BufferedImage BallImages[]; 
    public static BufferedImage ballSprites; // to store the sprites 
    
    
    public static BufferedImage AlienImages[];
    public static BufferedImage alienSprites;
    public static BufferedImage BombImages[];
/**
* initializing the images of the game */
public static void init() {

background = ImageLoader.loadImage("/Images/background.jpg"); //fondo del juego
player = ImageLoader.loadImage("/Images/player.png"); //la van
pause = ImageLoader.loadImage("/Images/pause.png"); //imagen que indica pausa
gameover = ImageLoader.loadImage("/Images/GameO.jpg"); //imagen de gameover
win = ImageLoader.loadImage("/Images/win.png");//imagen de Win
song = new SoundClip("/sounds/Megalovania.wav", -3f,true); //cancion del juego
ballSprites = ImageLoader.loadImage("/Images/SpriteBall.png");//imagenes de ball
SpriteSheet ballspritesheet = new SpriteSheet(ballSprites);//sprite de ball
laser = ImageLoader.loadImage("/Images/laser.png");


    alienSprites = ImageLoader.loadImage("/Images/SpaceInvaders.png");
    SpriteSheet alienSpriteSheet = new SpriteSheet(alienSprites);

    AlienImages = new BufferedImage[14];
    for(int k = 0; k < 14; k++){
        AlienImages[k] = alienSpriteSheet.crop(0+16*k,17,15,15);
        k++;
    }
    //se corta la imagen de los sprites de los bricks

    player = alienSpriteSheet.crop(0,32,17,18);
    //se corta la imagen de los sprites de ball
    BallImages = new BufferedImage[8];
    for (int i = 0; i < 8; i++){
        BallImages[i] = ballspritesheet.crop(0+40*i,0,32,32);
    
    }
    BombImages = new BufferedImage[2];
    BombImages[0] = alienSpriteSheet.crop(48,0,8,16);
    BombImages[1] = alienSpriteSheet.crop(56,0,8,16);

}

}

