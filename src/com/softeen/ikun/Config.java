package com.softeen.ikun;

import java.awt.*;

public class Config {
    /**
     * 游戏窗口是否可以改变大小
     */
    public static final boolean FRAME_RESIZABLE = false;

    /**
     * 游戏窗口的宽度
     */
    public static final int FRAME_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    /**
     * 游戏窗口的高度
     */
    public static final int FRAME_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * 游戏窗口的标题
     */
    public static final String FRAME_TITLE = "三国战纪";

    /**
     * 游戏窗口的图标
     */
    public static final String FRAME_ICON = "head.png";


    /**
     * 游戏窗口的背景音乐
     */
    public static final String FRAME_MUSIC = "bg1.mp3";

    /**
     * 游戏窗口的背景音乐是否循环播放
     */
    public static final boolean FRAME_MUSIC_LOOP = true;

    /**
     * 游戏窗口的背景音乐的音量
     */
    public static final float FRAME_MUSIC_VOLUME = 0.5f;

    /**
     * 游戏窗口的背景音乐是否开启
     */
    public static final boolean FRAME_MUSIC_ON = true;

    /**
     * 玩家移动速度
     */
    public static final int PLAYER_SPEED = 15;


}
