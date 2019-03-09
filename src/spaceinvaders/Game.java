/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author karymenahleacosta
 */
public class Game implements Runnable {

private BufferStrategy bs; // to have several buffers when displaying private Graphics g; // to paint objects
private Display display; // to display in the game
String title; // title of the window
private int width;// width of the window
private int height;// height of the window
private Thread thread;
private int x; //to move image
private int direction; // to set the direction of the player
private Player player; // to use a player
private LinkedList<Bomb> bomb;
private Laser laser; //To use the ball
private boolean lasershoot;
private LinkedList<Enemigo> enemigo;

private KeyManager keyManager; //to manage the keyboard
private int score; // puntaje
private String num; //to display score
private boolean pausa;//to pause the game
private int state; //to know if 1=running 2= endgame 3= pause 4= win 5=gameover
private int TotalAlien;//to keep track of total bricks
private int Win;//to keep score of destroyed bricks
    /**
     *
     * @param title
     * @param width
     * @param height
     */
    public Game(String title, int width, int height) { 
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        enemigo = new LinkedList<Enemigo>(); //lista que despliega los bricks
        bomb = new LinkedList<Bomb>();
        score = 0; //puntaje es 0 cuando inicia el juego
        num = "Score:"+score; //string que despliega en la pantalla el puntaje
        this.pausa = false;// se inicializa la variable en falso por que no esta en pausa
        this.state = 0; //Se inicializa en 0 que significa que el juego todavia no empieza
        this.Win = 0; // se inicializa como 0 cuando inicia el juego porque todavia no destruye ningun brick
        this.TotalAlien = 0; //se inicializa como 0 y ya despues se asignan los bricks con un for
        this.lasershoot=true;
    }

    /**
     *
     * @return pause
     */
    public boolean isPausa() {
        return pausa;
    }

    /**
     *
     * @param pausa
     */
    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    /**
     *
     * @return
     */
    public String getNum() {
        return num;
    }

    /**
     *
     * @param num
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }
    
    private int getDirection() {
        return direction;
    }
    
    private void setDirection(int i) {
       this.direction = i;
    }
    
    /**
     *
     * @return
     */
    public int getWidth(){
        return width;
    }
    
    /**
     *
     * @return
     */
    public int getHeight(){
        return height;
    }
    
    /**
     *
     */

    
    /**
     *
     * @return
     */
    public KeyManager getKeyManager() {
            return keyManager;
    }

    public boolean isLasershoot() {
        return lasershoot;
    }

    public void setLasershoot(boolean lasershoot) {
        this.lasershoot = lasershoot;
    }

    public void setState(int state) {
        this.state = state;
    }

    
    /**
     *
     * @return
     */
    
    private int getTotalAlien() {
        return TotalAlien;
    }
    
    private void setTotalAlien(int i) {
       this.TotalAlien = i;
    }
    private int getWin() {
        return Win;
    }
    
    private void setWin(int i) {
       this.Win = i;
    }
    
    /**
     *this returns the state of our game
     * @return
     */
    public int getState(){
        return state;
    }
    /**
     * here we initialize the game
     */
    private void init() { 
        display = new Display(title, getWidth(), getHeight());
        //we add our assets from our Assets class
        Assets.init(); 

        //Para cargar el juego guardado se tiene que oprimir la letra "C"
        if(getKeyManager().load && state==1){
            loadGame();
        }
        
        Assets.song.play();//we play Megalovania by toby fox
        //we add the player

        player = new Player(getWidth()/2-48, getHeight()-60, 1, 32,38 , this);

        //we add the laser but with no size         
        laser = new Laser(0, 0, 0, 0, 0, this);
        //we create an alien matrix
        for(int j = 1; j <= 4; j++) {
            for (int i = 1; i <= 6; i++) {
                 enemigo.add(new Enemigo(getWidth()-30 - 100*i ,5 + 60*j, 40, 40, this));  
                 setTotalAlien(getTotalAlien()+1);
                 bomb.add( new Bomb(100,getHeight()+100,8,16,this)); 
            } 
        }
        display.getJframe().addKeyListener(keyManager);
    }

    private void tick() {
        keyManager.tick();
        //para guardar el juego se tiene que oprimir la letra "G"
        if(getKeyManager().save && state == 1){
            saveGame();
        }
        //Para cargar el juego guardado se tiene que oprimir la letra "C"
        if(getKeyManager().load && state==1){
            loadGame();
        }

        if (getKeyManager().pause && !isPausa()){
            state=(state == 1 ? 3:1);
            setPausa(true);
        }
        else if (!getKeyManager().pause){
            setPausa(false);
        }
        
        //pause logic
       if (state !=3){
            //advancing player with colition
            player.tick();
            laser.tick();         

              
             //we actualize the bricks for rendering
             for (int i = 0; i < enemigo.size(); i++) {
               Enemigo marciano =  enemigo.get(i);
               marciano.tick();
               Bomb beam = bomb.get(i);
               beam.tick();
               if(marciano.getX()>getWidth()-marciano.getWidth() || marciano.getX()<-2){
                    for(int j = 0; j < enemigo.size(); j++){
                        Enemigo alien = enemigo.get(j);
                        alien.SwitchLayer();
                    }
                }
               if(marciano.intersecta(laser)){
                  Assets.alienExplosion.play();
                  marciano.changeAlive(); 
                  laser.destroy();
                  laser.canShoot();
                  setWin(getWin()+1);
                  //actualize score
                  setScore(getScore() + 100);
                  setNum("Score: "+ getScore());
             }
               if(marciano.intersect(player) || marciano.getY()+marciano.getHeight()>getHeight()){
                   Assets.deadPlayer.play();
                    player.loseLife();
                    setScore(getScore() - 50);
                    setNum("Score: "+ getScore());
                    player.setX(320);
                    laser.setX(370);
                    laser.setY(-100); 
                    for(int k = 0; k <enemigo.size();k++){
                        Enemigo xenomorph = enemigo.get(k);
                        xenomorph.reset();
                        Bomb granada = bomb.get(k);
                        granada.setY(getHeight()+100);
                    }
             }
               int iNum = (int) (Math.random() * 1000);
               if(iNum > 995 && marciano.isAlive() && state == 1 && !beam.isActive()){
                   Assets.alienBeam.play();
                   beam.setX(marciano.getX());
                   beam.setY(marciano.getY());
                   beam.setActive(true);
               }
               if(beam.intersecta(player)){
                   Assets.deadPlayer.play();
                   player.loseLife();
                   setScore(getScore() - 50);
                   setNum("Score: "+ getScore());
                   beam.setActive(false);
                   player.setX(320);
                   laser.setY(-100);
                   for(int k = 0; k <enemigo.size();k++){
                        Enemigo xenomorph = enemigo.get(k);
                        xenomorph.reset();
                        Bomb granada = bomb.get(k);
                        granada.setY(getHeight()+100);
                   }
               }
             


                }

            if (getKeyManager().shoot && laser.isShooting()&& state == 1){
                Assets.laserSound.play();
            laser = new Laser( player.getX() + 11, player.getY()-10, 1, 10, 10, this);
            laser.cantShoot();
            }
            if(laser.getY()<0){
                laser.canShoot();
            }

             //logic for when the player loses a live

             if(laser.getY() > getHeight() && player.getLives() > 0 ){
                 player.loseLife();
                 setScore(getScore() - 50);
                 setNum("Score: "+ getScore());
                 player.setX(320);
                 laser.setX(370);
                 laser.setY(player.getY() - 40); 
             }
             //sets our lose ocndition
             else if (player.getLives() == 0){ 
                 state=5;
                 player.setSpeed(0);
                 laser.cantShoot();
                
             }
             //sets our win condition
             if(getTotalAlien() == getWin()){
                 state = 4;
                 player.setSpeed(0);
                 laser.cantShoot();

             }
             //restart the game
             if(getKeyManager().again){ //R is pressed 
                if(state == 4 || state == 5){//if win or game over

                    state = 1;

                    //si el juego se reinicia se actualizan las variables a como estaban en un principio en init
                    setScore(0);
                    setNum("Score:"+score);

                    player.setX(320);
                    player.setY(getHeight()-100);
                    player.setLives(3);
                    player.setSpeed(4);
                    Assets.song.play();
                    laser.setX(0); 
                    laser.setY(0);
                    //Se vuelve a desplegar la matriz de bricksfor(int j = 1; j <= 4; j++) {
                  for(int j = 1; j <= 4; j++) {
                for (int i = 1; i <= 6; i++) {
                 enemigo.add(new Enemigo(getWidth()-30 - 100*i ,5 + 60*j, 40, 40, this));  
                 setTotalAlien(getTotalAlien()+1);
                 bomb.add( new Bomb(100,getHeight()+100,8,16,this)); 
            } 
        }
        
                   //Si se reinicia el juego el anterior juego guardado se elimina y se guarda uno nuevo desde el inicio
                   saveGame();
                   
                }
             }
    }
    }

/**
 * renders elements in canvas
 */
private void render() {
    // get the buffer strategy from the display
    bs = display.getCanvas().getBufferStrategy();
    /* if it is null, we define one with 3 buffers to display images of the game, if not null, then we display every image of the game but after clearing the Rectanlge, getting the graphic object from the buffer strategy element.
    show the graphic and dispose it to the trash system
    */
    if (bs == null) {
        display.getCanvas().createBufferStrategy(3); 
    }
    else
    {
        //we add images
        Graphics g = bs.getDrawGraphics();
        g.drawImage(Assets.background, 0, 0, width, height, null); 
        player.render(g);//render the player
        
        laser.render(g);
        //loopfor rendering all bricks
        for (int i = 0; i < enemigo.size(); i++) {
            Enemigo ET =  enemigo.get(i);
            ET.render(g);
            
            if(ET.getTime()>0&&!ET.isAlive()){
               g.setColor(Color.WHITE);
               g.drawString("+100", ET.getX(), ET.getY()); 
               ET.setTime(ET.getTime()-1);
            }
            Bomb boom=bomb.get(i);
            boom.render(g);
        }
        //pause case
        if (state == 3) {
            g.drawImage(Assets.pause, width / 2 - 98, height / 2 - 27, 196, 54, null);
            Assets.song.play();
        }
        //win case
        if (state == 4 ) {
            g.drawImage(Assets.win, width / 2 - 112, height / 2 - 32, 224, 64, null);
        } 
        //game over case
        if (state == 5 ) { 
               g.drawImage(Assets.gameover, 0, 0, getWidth(), getHeight(), null); 
               Assets.song.stop();
               setPausa(true);  
        }
        
        //draw score
        g.setColor(Color.WHITE);
        g.drawString(num, 700, 20);
        //g.drawString("this is something I want people to <p color=\"#FFFFFF\">NOTICE</p>", 700, 50);
        //draw ball
        laser.render(g);
        bs.show();
        g.dispose();
    }
}

    /**
     *for runing our game
     */
    public synchronized void start() { 
        if (state == 0) {
            state = 1;
            thread = new Thread(this); 
            thread.start();
         }
    }

    /**
     *In this function we manage time
     */
    @Override
public void run() {
    init();
    //Frames per second
    int fps = 50;
    //time for each thick in nano segs
    double timeTick = 1000000000 / fps;
    //initializing delta
    double delta = 0;
    // define now to use inside the loop
    long now;
    //initializing last time to the computer time in nanosecs
    long lastTime = System.nanoTime();  
 //To change body of generated methods, choose Tools | Templates.
  while (state != 2 ) {
	    now = System.nanoTime();
	    delta += (now - lastTime) / timeTick;
	    lastTime = now;
	    
	    if (delta >= 1) {
		tick();
		render();
		delta--;
	    }
	}	
	// Game over loop
	while (state == 2 ) {
	    now = System.nanoTime();
	    delta += (now - lastTime) / timeTick;
	    lastTime = now;
	    
	    if (delta >= 1) {
		render();
		delta--;
	    }
        }
   stop(); 
}

    /**
     *stops our game
     */
    public synchronized void stop() { 
       state = 2;
        try {
            thread.join();
        } 
        catch (InterruptedException ie) {
            ie.printStackTrace(); 
           }
    } 

      

   //se crea un archivo en donde se guardan diferentes variables del juego en curso para asi guardar una partida
   //con la letra "G" 
   private void saveGame(){
    try{
    FileWriter fw = new FileWriter("save.txt");
    
    fw.write(String.valueOf(player.getX())+ "\n");   
    fw.write(String.valueOf(player.getY())+ "\n");
    fw.write(String.valueOf(player.getLives())+ "\n");

    
    fw.write(String.valueOf(getScore())+ "\n");
    
    fw.write(String.valueOf(laser.getX())+ "\n");
    fw.write(String.valueOf(laser.getY())+ "\n");
    fw.write(String.valueOf(laser.getDirection())+ "\n");
    fw.write(String.valueOf(laser.getSpeed())+ "\n");
    
    fw.write(String.valueOf(getWin())+"\n");

            for(int i = 0; i < enemigo.size(); i++){
                 Enemigo marciano =  enemigo.get(i);
                 Bomb beam = bomb.get(i);
                     fw.write(String.valueOf(marciano.getX())+"\n");
                     fw.write(String.valueOf(marciano.getY())+"\n");
                     fw.write(String.valueOf(marciano.isShowpoints())+"\n");
                     fw.write(String.valueOf(marciano.isAlive())+"\n");
                     
                     fw.write(String.valueOf(beam.getX())+"\n");
                     fw.write(String.valueOf(beam.getY())+"\n");
  
                    
            }

    //Se cierra el archivo
            fw.close();
        }
    //si hay alguna excepcion o error
    catch (IOException ex){
        ex.printStackTrace();
    }
      }
    
    //Sirve para leer un archivo  
   //en este caso el archivo que se guardo anteriormente con el metodo "save"
   //se lee el archivo y carga el juego (las variables) en donde se guardo
    private void loadGame(){
    
        try{
        BufferedReader br = new BufferedReader(new FileReader("save.txt"));
        
        player.setX(Integer.parseInt(br.readLine()));
        player.setY(Integer.parseInt(br.readLine()));
        player.setLives(Integer.parseInt(br.readLine()));

        
        setScore(Integer.parseInt(br.readLine()));
        
        laser.setX(Integer.parseInt(br.readLine()));
        laser.setY(Integer.parseInt(br.readLine()));
        laser.setDirection(Integer.parseInt(br.readLine()));
        laser.setSpeed(Integer.parseInt(br.readLine()));
        

        setWin(Integer.parseInt(br.readLine()));
        
        //Bricks
            for(int i = 0; i < enemigo.size(); i++){
                 Enemigo marciano =  enemigo.get(i);
                 Bomb beam = bomb.get(i);
                     marciano.setX(Integer.parseInt(br.readLine()));
                     marciano.setY(Integer.parseInt(br.readLine()));
                     marciano.setShowpoints(Boolean.parseBoolean(br.readLine()));
                     marciano.setAlive(Boolean.parseBoolean(br.readLine()));
                     
                     beam.setY(Integer.parseInt(br.readLine()));
                     beam.setY(Integer.parseInt(br.readLine()));
 
            }
        br.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
} 
   