package com.softeen.ikun.model;

import com.softeen.ikun.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * 精灵类
 * <p color="red">
 *  用于表示游戏中的各种元素
 *  比如英雄、敌人、子弹、道具等
 * </p>
 */
public abstract class Sprite {

    /**
     * 精灵的X坐标
     */
    public int x = 100;

    /**
     * 精灵的Y坐标
     */
    public int y = 100;

    /**
     * 精灵的宽度
     */
    public int width;

    /**
     * 精灵的高度
     */
    public int height;

    /**
     * 精灵的图片
     */
    List<BufferedImage> images;

    /**
     * 当前角色的图片
     */
    BufferedImage img;

    /**
     * 精灵的图片的索引
     */
    public int imageIndex = 0;

    /**
     * 精灵的图片的总数
     */
    public int imageCount;

    /**
     * 精灵的图片的宽度
     */
    public int imageWidth;

    /**
     * 精灵的图片的高度
     */
    public int imageHeight;

    /**
     * 其所在的游戏面板
     */
    private GamePanel gamePanel;

    /**
     * 当前精灵对象是否死亡
     */
    private boolean death = false;

    Sprite(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    public abstract void init();

    public abstract void update();


    public abstract void destroy();

    public boolean isCollision(Sprite sprite) {


        if (sprite == null) {
            return false;
        }


        Rectangle r1 = new Rectangle(x, y, imageWidth, imageHeight);
        Rectangle r2 = new Rectangle(sprite.x, sprite.y, sprite.imageWidth, sprite.imageHeight);


        return r1.intersects(r2);
    }


    public List<BufferedImage> getImages() {
        return images;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void setImages(List<BufferedImage> images) {
        this.images = images;
        imageCount = images.size();
        img = images.get(imageIndex >= imageCount ? imageCount - 1 : imageIndex);
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }

    public int getImageCount() {
        return imageCount;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }


    public void draw(Graphics g) {
        g.drawImage(img,x,y,imageWidth,imageHeight,null);
    }



    /**
     * 更新图片
     */
    public void updateImage(){

        imageIndex++;
        if (imageIndex >= imageCount){
            imageIndex = 0;
        }
        img = images.get(imageIndex);

    }



}
