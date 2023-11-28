package com.web.game.utils;

import com.web.game.obj.BlastObj;
import com.web.game.obj.BulletObj;
import com.web.game.obj.EnemyObj;
import com.web.game.obj.GameObj;
import com.web.game.obj.ShellObj;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class GameUtils {

   
    //背景圖片
    public static Image bgImg = new ImageIcon(GameUtils.class.getResource("/imgs/sky.png")).getImage();
    //Boss圖片
    public static Image bossImg = new ImageIcon(GameUtils.class.getResource("/imgs/boss.png")).getImage();
    //爆炸圖片
    public static Image blastImg = new ImageIcon(GameUtils.class.getResource("/imgs/blast.png")).getImage();
    //我方飛機圖片
    public static Image planeImg = new ImageIcon(GameUtils.class.getResource("/imgs/plane.png")).getImage();
    //我方砲彈圖片
    public static Image shellImg = new ImageIcon(GameUtils.class.getResource("/imgs/shell.png")).getImage();
    //敵方子彈圖片
    public static Image bulletImg = new ImageIcon(GameUtils.class.getResource("/imgs/bullet.png")).getImage();
    //敵方飛機圖片
    public static Image enemyImg = new ImageIcon(GameUtils.class.getResource("/imgs/enemy.png")).getImage();
     
    
    //所有遊戲物件的集合
    public static List<GameObj> gameObjList = new ArrayList<>();

    //碰撞後要刪除元素的集合
    public static List<GameObj> removeList = new ArrayList<>();

    //我方砲彈集合
    public static List<ShellObj> shellObjList = new ArrayList<>();

    //敵方子彈集合
    public static List<BulletObj> bulletObjList = new ArrayList<>();

    //敵機集合
    public static List<EnemyObj> enemyObjList = new ArrayList<>();

    //爆炸集合
    public static List<BlastObj> blastObjList = new ArrayList<>();

    //繪製字串
    public static void drawWord(Graphics gImage, String str, Color color, int size, int x, int y) {

        gImage.setColor(color);
        gImage.setFont(new Font("仿宋", Font.BOLD, size));
        gImage.drawString(str, x, y);

    }

}
