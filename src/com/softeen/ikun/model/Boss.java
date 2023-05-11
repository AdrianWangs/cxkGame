package com.softeen.ikun.model;

import com.softeen.ikun.GamePanel;
import com.softeen.ikun.MusicPlayer;
import com.softeen.ikun.tools.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import static com.softeen.ikun.Config.*;
import static com.softeen.ikun.MusicPlayer.*;

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
     * BOSS攻击图像
     */
    private List<BufferedImage> bossLeftAttackImages;
    private List<BufferedImage> bossRightAttackImages;


    /**
     * BOSS技能图像
     */
    private List<BufferedImage> bossSkillImages;

    /**
     * Boss闪现技能图像
     */
    private List<BufferedImage> bossFlashImages;

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
        bossLeftAttackImages = new ArrayList<>();
        bossRightAttackImages = new ArrayList<>();
        bossSkillImages = new ArrayList<>();
        bossFlashImages = new ArrayList<>(){
            {
                add(Utils.loading("gy_run.gif"));
                add(Utils.loading("gykill.gif"));
            }

        };


        for (int i = 0; i < 8; i++) {
            bossLeftImages.add(Utils.loading("h_L" + i + ".png"));
            bossRightImages.add(Utils.loading("h_R" + i + ".png"));
        }

        for (int i = 0; i < 6; i++) {
            bossLeftAttackImages.add(Utils.loading("attack/a1-L" + i + ".png"));
            bossRightAttackImages.add(Utils.loading("attack/a1-R" + i + ".png"));
        }

        for (int i = 0; i < 26; i++) {
            bossSkillImages.add(Utils.loading("skill/frame_" + i + ".png"));
        }

        //默认是向左的图像
        setImages(bossLeftImages);

        // 设置BOSS的初始随机位置
        setX(FRAME_WIDTH - 100);
        setY(Utils.randNum(20, FRAME_HEIGHT - 20 - getImg().getHeight()));

        System.out.println(getImg());

        setImageWidth(getImg().getWidth());
        setImageHeight(getImg().getHeight());
    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {

        //弹窗提示游戏胜利
        JOptionPane.showMessageDialog(getGamePanel(), "恭喜你，通关成功！");

        getGamePanel().boss = null;

    }

    @Override
    public void run() {

        while (!isDeath()) {

            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (getGamePanel().pause){
                continue;
            }

            Hero hero = getGamePanel().hero;

            int random = Utils.randNum(0, BOSS_BLINK_PROBABILITY);


            //随机闪现
            if (random == 0) {
                skill1(hero);
            }


            // 判断BOSS是否已经靠近英雄
            if (this.isCollision(hero)) {
                // 靠近英雄，开始攻击
                attack(hero);
            } else {
                // 远离英雄，开始移动
                move(hero);
            }

        }

        // 销毁BOSS
        destroy();
    }

    /**
     * 攻击英雄
     * @param hero 英雄对象
     */
    private void attack(Hero hero) {


        int random = Utils.randNum(0, BOSS_ATTACK_PROBABILITY);

        if (random == 0) {
            skill2(hero);
            return;
        }

        // 切换至攻击图像序列
        if (getImages() == bossLeftImages) {
            setImages(bossLeftAttackImages);
        } else {
            setImages(bossRightAttackImages);
        }


        // 延迟一段时间后开始攻击
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 检测是否命中英雄，并造成伤害
        if (hero.isCollision(this)) {
            hero.setFrozen(true); // 禁止英雄移动
            for (int i = 0; i < 3; i++) {
                hero.setHp(hero.getHp() - Utils.randNum(BOSS_ATTACK, BOSS_ATTACK * 2)); // 随机减少英雄血量，每次减少20-30
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            hero.setFrozen(false); // 解除英雄移动禁止
        }

        // 切换回行走图像序列
        if (getX()-hero.getX() < 0){
            setImages(bossRightImages);
        }else {
            setImages(bossLeftImages);
        }

    }


    /**
     * 技能1
     * <p color="yellow">
     *  闪现到英雄身边
     * </p>
     * @param hero 英雄对象
     */
    public void skill1(Hero hero){


        new Thread(new MusicPlayer(HERO_SKILL_MUSIC1, false,1f)).start();

        int x = hero.getX() - 100; // BOSS新的 X 坐标
        int y = hero.getY(); // BOSS新的 Y 坐标
        setX(x);
        setY(y);
        setImages(bossFlashImages);
        try {
            Thread.sleep(600); // 播放技能动画
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setImages(bossRightImages); // 恢复普通攻击图像序列

    }

    /**
     * 技能2
     * <p color="yellow">
     *  造成一次性用户当前血量20%的伤害
     * </p>
     * @param hero 英雄对象
     */
    public void skill2(Hero hero){


        // 切换至攻击图像序列
        setImages(bossSkillImages);

        setImageWidth(getImg().getWidth()*3/2);
        setImageHeight(getImg().getHeight()*3/2);

        new Thread(new MusicPlayer(BOSS_SKILL_MUSIC2,false,1f)).start();


        // 延迟一段时间后开始攻击
        try {
            Thread.sleep(BOSS_ATTACK_DELAY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 检测是否命中英雄，并造成伤害
        if (hero.isCollision(this)) {
            hero.setFrozen(true); // 禁止英雄移动
            int damage = (int) (hero.getHp() * 0.2); // 对用户造成当前生命值20%的伤害
            hero.setHp(hero.getHp() - damage);


            /*播放动画*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            hero.setFrozen(false); // 解除英雄移动禁止
        }

        // 切换回行走图像序列
        if (getX()-hero.getX() < 0){
            setImages(bossRightImages);
        }else {
            setImages(bossLeftImages);
        }

        setImageWidth(getImg().getWidth());
        setImageHeight(getImg().getHeight());


    }



    /**
     * 靠近玩家
     * @param hero 玩家
     */
    private void move(Hero hero) {

        System.out.println("BOSS移动");

        if (getX()-hero.getX() < 0){
            setImages(bossRightImages);
            setX(getX()+speed);
        }else {
            setImages(bossLeftImages);
            setX(getX()-speed);
        }

        if (getY()-hero.getY() < 0){
            setY(getY()+speed);
        }else {
            setY(getY()-speed);
        }

    }

    /**
     * 绘制BOSS的血条
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


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;

        if (hp <= 0) {
            // BOSS死亡
            setDeath(true);
        }

    }
}