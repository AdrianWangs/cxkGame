package com.softeen.ikun;

import com.softeen.ikun.tools.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

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

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            hero.updateImage();
            repaint();
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

        timer.schedule(task, 0, 100);

        //设置焦点
        setFocusable(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Image img = Utils.loading(maps[mapIndex]);
        g.drawImage(img, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);

        hero.draw(g);
    }




}
