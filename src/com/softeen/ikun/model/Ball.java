package com.softeen.ikun.model;

import com.softeen.ikun.GamePanel;
import com.softeen.ikun.tools.Utils;

import static com.softeen.ikun.Config.*;

public class Ball extends Sprite implements Runnable{

    int speed_x;

    int speed_y;

    Hero hero;

    /**
     * 初始化篮球
     * @param panel 面板对象
     * @param direction 发射方向
     *                  true为右
     *                  false为左
     * @param x 发射时的玩家x坐标
     * @param y 发射时的玩家y坐标
     */
    public Ball(GamePanel panel,boolean direction,int x,int y,Hero hero){

        super(panel);
        this.hero=hero;
        if (direction){
            speed_x = ATTACK_SPEED;
        }else {
            speed_x = -ATTACK_SPEED;
        }

        setX(x);
        setY(y);

    }


    /**
     * 初始化篮球，释放技能时调用
     * @param panel 面板对象
     * @param angle 发射角度
     *              其值代表与x轴的夹角，比如0度代表水平向右，90度代表竖直向上
     * @param x
     * @param y
     * @param hero
     */
    public Ball(GamePanel panel, double angle, int x, int y, Hero hero) {
        super(panel);
        this.hero = hero;


        // 计算球的速度分量
        double speedX = ATTACK_SPEED * Math.cos(angle);
        double speedY = ATTACK_SPEED * Math.sin(angle);

        setX(x);
        setY(y);

        // 设置球的速度分量
        this.speed_x = (int) speedX;
        this.speed_y = (int) speedY;
    }



    @Override
    public void init() {

        setImg(Utils.loading("ikun/ball.png"));



        setImageWidth(getImg().getWidth());
        setImageHeight(getImg().getHeight());

    }



    @Override
    public void update() {

    }


    @Override
    public void destroy() {
        hero.balls.remove(this);
    }

    @Override
    public void run() {


        while (!isDeath()){
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (getGamePanel().pause){
                continue;
            }

            setX(getX() + speed_x);
            setY(getY() + speed_y);


            onCollision();


            if (getX() < getWidth() || getX() > FRAME_WIDTH){
                setDeath(true);
            }

            if (getY() < getHeight() || getY() > FRAME_HEIGHT){
                setDeath(true);
            }



        }
        destroy();
    }


    /**
     * 碰撞检测
     */
    public void onCollision(){

        if (getGamePanel().boss != null){
            if (this.isCollision(getGamePanel().boss)) {

                //销毁篮球
                setDeath(true);

                getGamePanel().boss.setHp(getGamePanel().boss.getHp()-10);
                if (getGamePanel().boss.getHp() <= 0) {
                    getGamePanel().boss.setDeath(true);
                }
                hero.setMp(hero.getMp()+10);

                return;

            }
        }

        getGamePanel().enemies.forEach(enemy -> {
            if (this.isCollision(enemy)) {

                //击退敌人
                this.knockBack(enemy);
                setDeath(true);

                enemy.setHp(enemy.getHp()-10);
                hero.setMp(hero.getMp()+5);
            }
        });
    }


    /**
     * 击退敌人
     */
    public void knockBack(Enemy enemy) {

        // 计算击退方向
        int direction = enemy.getX() > getX() ? 1 : -1;

        // 计算击退后的位置
        int x = enemy.getX() + ATTACK_KNOCK_BACK_DISTANCE * direction;

        // 限制击退后的位置
        if (x < 0) {
            x = 0;
        } else if (x > FRAME_WIDTH - enemy.getImg().getWidth()) {
            x = FRAME_WIDTH - enemy.getImg().getWidth();
        }

        // 设置击退后的位置
        enemy.setX(x);

    }


}
