package br.unb.shooter.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicController {
    
    private static final MusicController instance = new MusicController();
    
    // singleton
    public static MusicController getInstance() {
        return instance;
    }
    
    Music music;
    Sound sound;
    
    public MusicController() {
        music = Gdx.audio.newMusic(Gdx.files.internal("tela1wraped.wav"));
        sound = Gdx.audio.newSound(Gdx.files.internal("playershoot.wav"));
    }
    
    public void start(String path) {
        music = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.play();
    }
    
    public void pause() {
        music.pause();
    }
    
    public void stop() {
        music.stop();
    }
    
    public void shoot() {
        sound.play();
    }
    
    public Music getMusic() {
        return music;
    }
}
