package com.softeen.ikun;


import com.softeen.ikun.tools.Utils;
import javazoom.jl.player.Player;


public class MusicPlayer extends Thread{

    public static String BG_MUSIC = "ikun.mp3";

    public static String ATTACK_MUSIC = "attack.mp3";

    public static String DIE_MUSIC = "die.mp3";

    public static String SKILL_MUSIC = "skill1.mp3";


    Player player;

    boolean loop;

    String music;

    public MusicPlayer(String music, boolean loop, float volume){
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
