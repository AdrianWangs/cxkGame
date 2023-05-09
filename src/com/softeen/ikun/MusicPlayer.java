package com.softeen.ikun;


import com.softeen.ikun.tools.Utils;
import javazoom.jl.player.Player;


public class MusicPlayer extends Thread{


    Player player;

    boolean loop;

    String music;

    MusicPlayer(String music, boolean loop, float volume){
        this.loop = loop;
        this.music = music;
    }

    @Override
    public void run() {
        try {
            do {
                player = new Player(Utils.getFileStream(music));
                player.play();
            } while (loop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
