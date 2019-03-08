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
    public static BufferedImage grow; //imagen del poder
    public static BufferedImage lives; //las imagenes de vidas
    public static BufferedImage pause; //variable de pausa
    public static BufferedImage gameover; //variable de que perdio
    public static BufferedImage win; //variable de que gano 
    public static SoundClip song; //cancion del juego
    public static BufferedImage laser;
    
    public static BufferedImage BallImages[]; 
    public static BufferedImage ballSprites; // to store the sprites 
    
    public static BufferedImage BrickImages[];     // pictures to go down
    public static BufferedImage brickSprites; // to store the sprites 
/**
* initializing the images of the game */
public static void init() {
background = ImageLoader.loadImage("/Images/background.jpg"); //fondo del juego
player = ImageLoader.loadImage("/Images/player.png"); //la van
pause = ImageLoader.loadImage("/Images/pause.png"); //imagen que indica pausa
gameover = ImageLoader.loadImage("/Images/GameO.jpg"); //imagen de gameover
win = ImageLoader.loadImage("/Images/win.png");//imagen de Win
grow = ImageLoader.loadImage("/Images/GreenPower.png");//frasco de poder verde
lives = ImageLoader.loadImage("/Images/Heisenberg.png"); //imagen de las vidas que es la cara de hank
song = new SoundClip("/sounds/Megalovania.wav", -3f,true); //cancion del juego
brickSprites = ImageLoader.loadImage("/Images/Meth.png"); //imagenes de bricks que son meths
SpriteSheet spritesheet = new SpriteSheet(brickSprites); //sprite de bricks
ballSprites = ImageLoader.loadImage("/Images/SpriteBall.png");//imagenes de ball
SpriteSheet ballspritesheet = new SpriteSheet(ballSprites);//sprite de ball
laser = ImageLoader.loadImage("/Images/laser.png");

//se corta la imagen de los sprites de los bricks
BrickImages = new BufferedImage[5];       
BrickImages[0] = spritesheet.crop(0,0,256,150);
BrickImages[1] = spritesheet.crop(256,0,256,150);
BrickImages[3] = spritesheet.crop(0,155,256,150);
BrickImages[2] = spritesheet.crop(256,155,256,150);

//se corta la imagen de los sprites de ball
BallImages = new BufferedImage[8];
for (int i = 0; i < 8; i++){
    BallImages[i] = ballspritesheet.crop(0+40*i,0,32,32);
}

}

}

