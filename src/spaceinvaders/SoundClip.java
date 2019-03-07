/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundClip {

    private AudioInputStream sound;	// AudioInputStream object
    private Clip clip;			// Audio clip
    private boolean loop;		// Boolean to allow looping

    /**
     * Default constructor
     */
    public SoundClip() {
	try {
	    clip = AudioSystem.getClip();
	} catch (LineUnavailableException e) {
	}
    }

    /**
     * Constructor with <b>path</b> parameter
     *
     * @param path Path to specified sound file
     * @param loop Boolean to allow looping
     */
    public SoundClip(String path, float offset, boolean loop) {
	this();	    // Calls default constructor

	// Attempt to get resource and save as URL
	URL url = null;
	try {
	    url = this.getClass().getResource(path);
	} catch (Exception e) {
	    System.out.println("" + path + " doesn't exist!");
	}

	// Attempt to load sound
	try {
	    sound = AudioSystem.getAudioInputStream(url);
	    clip.open(sound);
	} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
	    System.err.println("Error loading file! " + e);
	}

	// Modify volume of sound
	FloatControl fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	fc.setValue(offset);

	this.loop = loop;
    }

    /**
     * Method to start playback
     */
    public void play() {
	clip.stop();	// Stop sound if already playing
	if (sound == null) {	// If no sound is loaded, stop here
	    return;
	}
	clip.setFramePosition(0);

	// Loop when specified
	clip.loop((loop) ? Clip.LOOP_CONTINUOUSLY : 0);
    }
    public void stop(){
        clip.stop();
    }
}

