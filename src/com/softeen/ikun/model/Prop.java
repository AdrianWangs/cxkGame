package com.softeen.ikun.model;

import com.softeen.ikun.GamePanel;
import com.softeen.ikun.tools.Utils;

import java.awt.image.BufferedImage;

public class Prop extends Sprite{



    int type;



    /**
     * 生成掉落物
     * @param gamePanel 面板对象
     * @param x x坐标
     * @param y y坐标
     * @param type 掉落物类型
     */
    Prop(GamePanel gamePanel, int x, int y,int type) {
        super(gamePanel);
        init();

        setX(x);
        setY(y);

        this.type = type;

        init();

    }

    @Override
    public void init() {


        System.out.println("生成");

        setImg(Utils.loading("food/"+ type +".png"));

        setImageHeight(getImg().getHeight());
        setImageWidth(getImg().getWidth());



    }

    @Override
    public void update() {

    }

    @Override
    public void destroy() {

    }
}
