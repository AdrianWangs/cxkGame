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
    public static final String FRAME_MUSIC = "ikun.mp3";

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

    /**
     * 攻击物发送速度
     */
    public static final int ATTACK_SPEED = 5;

    /**
     * 玩家默认血量
     */
    public static final int PLAYER_HP = 1000;

    /**
     * 玩家最大蓝量
     */
    public static final int PLAYER_MP_MAX = 100;

    /**
     * 敌人移动速度
     */
    public static final int ENEMY_SPEED = 2;

    /**
     * 敌人默认血量
     */
    public static final int ENEMY_HP = 100;

    /**
     * 敌人血条宽度
     */
    public static final int ENEMY_HP_WIDTH = 100;

    /**
     * 敌人血条高度
     */
    public static final int ENEMY_HP_HEIGHT = 10;

    /**
     * 敌人血条相对敌人的偏移横坐标
     */
    public static final int ENEMY_HP_X = 0;

    /**
     * 敌人血条相对敌人的偏移纵坐标
     */
    public static final int ENEMY_HP_Y = -20;

    /**
     * 释放技能时篮球的个数
     */
    public static final int BASKETBALL_NUM = 36;

    /**
     * 攻击击退距离
     */
    public static final int ATTACK_KNOCK_BACK_DISTANCE = 10;

    /**
     * 血条和蓝条的最大宽度
     */
    public static final int HP_MP_BAR_MAX_WIDTH = 200;


}
