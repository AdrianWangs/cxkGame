package com.softeen.ikun;

import com.softeen.ikun.model.Ball;
import com.softeen.ikun.model.Enemy;
import com.softeen.ikun.model.Hero;
import com.softeen.ikun.model.Sprite;
import com.softeen.ikun.tools.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.softeen.ikun.Config.*;

public class GamePanel extends JPanel {

    String[] maps = {
            "ikun/background.jpg",
            "bg.jpg",
            "bg0.jpg",
            "bg1.jpg",
            "bg2.jpg",
            "bg3.jpg",
            "bg4.png",
            "bg5.png",
            "bg6.png",
            "bg7.png",
            "bg8.png",
    };

    public int mapIndex = 0;

    Hero hero = new Hero(GamePanel.this);

//    List<Enemy> enemies = new ArrayList<>();

    CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<>();




    /**
     * 更新玩家图像
     */
    TimerTask updateHeroTask = new TimerTask() {
        @Override
        public void run() {
            hero.updateImage();

            enemies.forEach(enemy-> enemy.updateImage());

            repaint();
        }
    };

    /**
     * 生成敌人
     */
    TimerTask generateEnemyTask = new TimerTask() {
        @Override
        public void run() {
            Enemy enemy = new Enemy(GamePanel.this,enemies);
            new Thread(enemy).start();
            enemies.add(enemy);
        }
    };

    /**
     * 检测碰撞
     */
    TimerTask detectCollisionTask = new TimerTask() {
        @Override
        public void run() {

            //判断子弹是否击中敌人
            hero.balls.forEach(ball -> {
                enemies.forEach(enemy -> {
                    if (ball.isCollision(enemy)) {

                        //击退敌人
                        ball.knockBack(enemy);
                        hero.balls.remove(ball);

                        //TODO: 击中敌人
                        enemy.setHp(enemy.getHp()-10);
                        if (enemy.getHp() <= 0) {
                            enemies.remove(enemy);
                        }


                        hero.setMp(hero.getMp()+10);

                        if (hero.getMp() == PLAYER_MP_MAX) {

                        }


                    }
                });
            });
        }
    };

    Timer timer = new Timer();


    /**
     * 上一张地图
     */
    public void prevMap(){

        if(mapIndex < 0){
            return;
        }

        mapIndex--;

    }

    /**
     * 下一张地图
     */
    public void nextMap(){

        if(mapIndex >= maps.length){
            return;
        }

        mapIndex++;
    }


    GamePanel(){

        setBackground(Color.GREEN);

        //添加键盘监听器
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                hero.control(e.getKeyChar());
                repaint();
            }
        });

        timer.schedule(updateHeroTask, 0, 100);
        timer.schedule(generateEnemyTask, 0, 1000);
        timer.schedule(detectCollisionTask, 0, 100);


        //设置焦点
        setFocusable(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image img = Utils.loading(maps[mapIndex]);
        g.drawImage(img, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);

        hero.draw(g);
        hero.balls.forEach(ball -> ball.draw(g));
        enemies.forEach(enemy -> enemy.draw(g));

    }






}
