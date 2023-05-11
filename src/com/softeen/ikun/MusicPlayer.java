package com.softeen.ikun;


import com.softeen.ikun.tools.Utils;
import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import java.io.FileInputStream;
import java.io.IOException;


public class MusicPlayer extends Thread{

    public static String BG_MUSIC = "bg1.mp3";

    public static String ATTACK_MUSIC = "attack.mp3";

    public static String DIE_MUSIC = "die.mp3";

    /**
     * BOSS闪现技能音效
     */
    public static String BOSS_SKILL_MUSIC1 = "flash4.mp3";

    /**
     * BOSS技能音效
     */
    public static String BOSS_SKILL_MUSIC2 = "skill1.mp3";

    /**
     * 玩家技能音效
     */
    public static String HERO_SKILL_MUSIC1 = "skill.mp3";

    /**
     * 玩家闪现音效
     */
    public static String HERO_SKILL_MUSIC2 = "flash4.mp3";

    /**
     * 玩家大招音效
     */
    public static String HERO_SKILL_MUSIC3 = "joke.mp3";


    Player player;

    boolean loop;

    String music;

    float volume;

    private FileInputStream musicStream;


    public MusicPlayer(String music, boolean loop){
        this.loop = loop;
        this.music = music;

        try {
            musicStream = Utils.getFileStream(music);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public MusicPlayer(String music, boolean loop, float volume){
        this.loop = loop;
        this.music = music;
        this.volume = volume;

        try {
            musicStream = Utils.getFileStream(music);
            changeVolume(volume);//设置音量为20%
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    //修改音量大小
    void changeVolume(float volume) throws IOException, UnsupportedAudioFileException, LineUnavailableException {


        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicStream);
        AudioFormat baseFormat = audioInputStream.getFormat();
        AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
                baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream2);
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = gainControl.getMinimum();
            float max = gainControl.getMaximum();
            gainControl.setValue((max - min) * volume + min);
        }
        clip.start();

    }

    @Override
    public void run() {
        try {
            do {
                player = new Player(musicStream);
                player.play();
            } while (loop);
        } catch (Exception e) {
        }
    }



}
