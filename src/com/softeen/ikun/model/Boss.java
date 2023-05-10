package com.softeen.ikun.model;

import com.softeen.ikun.GamePanel;
import com.softeen.ikun.tools.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.softeen.ikun.Config.*;


public class Boss extends Sprite implements Runnable {

    /**
     * BOSS的生命值
     */
    private int hp = BOSS_HP;

    /**
     * BOSS的移动速度
     */
    private final int speed = BOSS_SPEED;

    /**
     * BOSS的动作图像
     */
    private List<BufferedImage> bossLeftImages;

    private List<BufferedImage> bossRightImages;


    /**
     * BOSS血条的宽度
     */
    private final int hpBarWidth = ENEMY_HP_WIDTH * 2;

    /**
     * BOSS血条的高度
     */
    private final int hpBarHeight = ENEMY_HP_HEIGHT;


    /**
     * BOSS血条的Y坐标
     */
    private final int hpBarY = ENEMY_HP_Y;

    public Boss(GamePanel gamePanel) {
        super(gamePanel);
    }

    @Override
    public void init() {
        bossLeftImages = new ArrayList<>();
        bossRightImages = new ArrayList<>();

//        for (int i = 1; i <= 16; i++) {
//            bossImages.add(Utils.loading("boss/" + i + ".png"));
//        }
//        setImages(bossImages);

        // 设置BOSS的初始随机位置
        setX(FRAME_WIDTH - 100);
        setY(Utils.randNum(20, FRAME_HEIGHT - 20 - getImg().getHeight()));

        setImageWidth(getImg().getWidth());
        setImageHeight(getImg().getHeight());
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {
    }

    @Override
    public void run() {
        while (!isDeath()) {

            // 每秒钟更新一次BOSS的X和Y坐标
            int randomUpdateInterval = Utils.randNum(1, 3) * 1000;
            try {
                Thread.sleep(randomUpdateInterval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 判断BOSS是否已经靠近英雄
            Hero hero = getGamePanel().hero;
            if (getX() > hero.getX() + hero.getImageWidth()) {
                setX(getX() - speed);
            }

            // BOSS逐渐靠近英雄，速度较慢
            if (getX() < FRAME_WIDTH - 200) {
                setX(getX() - speed / 2);
            }

            // 被攻击后执行的操作
            onAttacked();

            // 判断是否到达左侧边界，如果到达则销毁BOSS
            if (getX() < getWidth()) {
                setDeath(true);
            }
        }

        // 销毁BOSS
        destroy();
    }

    /**
     * 英雄被攻击后执行的操作
     */
    private void onAttacked() {
        Hero hero = getGamePanel().hero;
        if (hero.isCollision(this)) {
            // 随机减少英雄血量，减少20-100
            hero.setHp(hero.getHp() - Utils.randNum(20, 100));
            setDeath(true);
        }

        if (hero.getHp() <= 0) {
            // 游戏结束，弹出提示框
            getGamePanel().timer.cancel();
            JOptionPane.showMessageDialog(getGamePanel(), "游戏结束");
            System.exit(0);
        }
    }

    /**
     * 绘制BOSS的血条
     *
     * @param g 画笔对象
     */
    private void drawHpBar(Graphics g) {
        // 计算血条的长度
        int hpWidth = (int) (hp / (double) BOSS_HP * hpBarWidth);
        // 绘制血条的背景框
        g.setColor(Color.BLACK);
        g.drawRect(getX() - hpBarWidth / 2 + getImageWidth() / 2, getY() + hpBarY, hpBarWidth, hpBarHeight);

        g.setColor(Color.RED);
        g.fillRect(getX() - hpBarWidth / 2 + getImageWidth() / 2, getY() + hpBarY, hpWidth, hpBarHeight);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        // 绘制血条
        drawHpBar(g);
    }
}