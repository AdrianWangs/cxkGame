package com.softeen.ikun;

import com.softeen.ikun.tools.Utils;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.softeen.ikun.Config.*;


public class GameFrame extends JFrame {


    public static JPanel gamePanel = new GamePanel();

    public GameFrame(){

        // 设置窗口
        setFrame();

        // 设置窗口背景音乐
        setMusic();
    }



    public void setFrame() {

        // 设置窗口标题
        setTitle(FRAME_TITLE);
        // 设置窗口图标
        setIconImage(Utils.loading(FRAME_ICON));
        // 设置窗口大小
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        // 设置窗口居中
        setLocationRelativeTo(null);
        // 设置窗口大小不可变
        setResizable(FRAME_RESIZABLE);
        // 设置窗口关闭无效
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //确认关闭窗口
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "是否退出游戏？", "退出游戏", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });


        // 设置窗口背景图片，自适应窗口大小
        setContentPane(gamePanel);

    }


    public void setMusic(){
        // 设置窗口背景音乐
        MusicPlayer musicPlayer = new MusicPlayer(FRAME_MUSIC, FRAME_MUSIC_LOOP);
        musicPlayer.start();

    }

    public void start(){
        setVisible(true);
    }


}
