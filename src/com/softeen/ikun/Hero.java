package com.softeen.ikun;

import com.softeen.ikun.tools.Utils;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import static com.softeen.ikun.Config.PLAYER_SPEED;

/**
 * 英雄类
 */
public class Hero extends Sprite{

    /**
     * 英雄的生命值
     */
    int hp;

    /**
     * 英雄的怒气值
     */
    int mp;

    /**
     * 向右移动的图片动画帧
     */
    BufferedImage[] imagesRight;

    /**
     * 向左移动的图片动画帧
     */
    BufferedImage[] imagesLeft;


    /**
     * 向右攻击的图片动画帧
     */
    BufferedImage[] attackRight = new BufferedImage[]{
            Utils.loading("ikun/r4.png")
    };

    /**
     * 向左攻击的图片动画帧
     */
    BufferedImage[] attackLeft = new BufferedImage[]{
            Utils.loading("ikun/l4.png")
    };

    Timer timer = new Timer();

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            //切换回原来的图片动画帧
            setImages(images == attackRight ? imagesRight : imagesLeft);
        }
    };


    Hero(GamePanel gamePanel) {
        super(gamePanel);
    }


    @Override
    public void init() {

        imagesRight = new BufferedImage[]{
                Utils.loading("ikun/r1.png"),
                Utils.loading("ikun/r2.png"),
                Utils.loading("ikun/r3.png"),
        };

        imagesLeft = new BufferedImage[]{
                Utils.loading("ikun/l1.png"),
                Utils.loading("ikun/l2.png"),
                Utils.loading("ikun/l3.png"),
        };

        setImages(imagesRight);
        setImageHeight(getImg().getHeight() * 3 / 2);
        setImageWidth(getImg().getWidth() * 3 / 2);

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {

    }

    @Override
    public void destroy() {

    }

    /**
     * 向左移动
     */
    public void moveLeft(){

        if (images != imagesLeft){
            setImages(imagesLeft);
        }

        x -= PLAYER_SPEED;

        if (getX() < -getImageWidth()){

            setX(Config.FRAME_WIDTH + getImageWidth());
            //切换上一张地图
            getGamePanel().prevMap();
        }


    }

    /**
     * 向右移动
     */
    public void moveRight(){

        if (images != imagesRight){
            setImages(imagesRight);
        }

        setX(getX() + PLAYER_SPEED);

        if (getX() > Config.FRAME_WIDTH + getImageWidth()){
            setX(-getImageWidth());
            //切换下一张地图
            getGamePanel().nextMap();
        }

    }

    /**
     * 向上移动
     */
    public void moveUp(){
        setY(getY() - PLAYER_SPEED);

        if (getY() < -getImageHeight()){
            setY(Config.FRAME_HEIGHT + getImageHeight());
        }
    }


    /**
     * 向下移动
     */
    public void moveDown(){

        setY(getY() + PLAYER_SPEED);

        if (getY() > Config.FRAME_HEIGHT + getImageHeight()){
            setY(-getImageHeight());
        }

    }


    public void attack(){

        //切换到攻击的图片动画帧
        setImages(images == imagesRight ? attackRight : attackLeft);

        timer.schedule(timerTask, 100);

        MusicPlayer musicPlayer;

        try {
            musicPlayer = new MusicPlayer(
                "attack.mp3",
                false,
                0.5f
            );

            musicPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }


        //TODO 周围的敌人受到攻击

    }


    /**
     * 玩家控制行为
     * <ul>
     *     <li>w:上</li>
     *     <li>s:下</li>
     *     <li>a:左</li>
     *     <li>d:右</li>
     *     <li>j:攻击</li>
     *     <li>l:防御</li>
     * </ul>
     * @param code 键盘按键的编码
     */
    public void control(char code){


        switch (code){
            case 'w':
                moveUp();
                break;
            case 's':
                moveDown();
                break;
            case 'a':
                moveLeft();
                break;
            case 'd':
                moveRight();
                break;
            case 'j':
                attack();
                break;
            case 'l':
                System.out.println("防御");
                break;

        }

    }


}




