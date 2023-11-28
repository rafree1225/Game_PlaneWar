package com.web.game;

import com.web.game.obj.BgObj;
import com.web.game.obj.BossObj;
import com.web.game.obj.BulletObj;
import com.web.game.obj.EnemyObj;
import com.web.game.obj.PlaneObj;
import com.web.game.obj.ShellObj;
import com.web.game.utils.GameUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class PlaneWar extends JFrame {

    //遊戲狀態：0.未開始 1.遊戲中 2.暫停 3.破關失敗 4.破關成功
    public static int state = 0;

    //solve文字閃爍(圖片雙緩存)
    Image offScreenImage = null;
    //視窗寬度
    int width = 800;
    //視窗高度
    int height = 800;

    //遊戲得分
    public static int score = 0;

    //遊戲的重繪次數(以解決砲彈發射太快;從1開始，因為至少繪製1次)
    int count = 1;

    //敵機出現的數量(以控制Boss出現時機)
    int enemyCount = 0;

    //背景圖物件
    BgObj bgObj = new BgObj(GameUtils.bgImg, 0, 0, 2);

    //飛機物件
    public PlaneObj planeObj = new PlaneObj(GameUtils.planeImg, 100, 200, 100, 70, 0, this);

    //砲彈物件
    ShellObj shellObj = new ShellObj(GameUtils.shellImg, planeObj.getX(), planeObj.getY(), 14, 29, 3, this);

    //敵方Boss物件
    public BossObj bossObj = null;

    //視窗設定
    public void launch() {

        //視窗是否可見
        this.setVisible(true);

        //視窗大小
        this.setSize(800, 800);

        //視窗位置
        this.setLocationRelativeTo(null);

        //視窗標題
        this.setTitle("ItBroccoli小遊戲──空中大戰");

        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);

        //遊戲的點擊啟動事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1) {
                    if (state == 0) {
                        state = 1;
                        repaint();
                    } else if (state == 3 || state == 4) {  // 破關失敗或破關成功
                        restartGame();
                    }
                }
            }
        });

        //暫停與繼續遊戲：按空白鍵操控
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32) {

                    switch (state) {
                        case 1:
                            state = 2;
                            break;

                        case 2:
                            state = 1;
                            break;
                        default:

                    }

                }
            }

        });

        //重複繪製
        while (true) {
            if (state == 1) {
                createObj();
                repaint();
            }

            try {
                Thread.sleep(25);//單位:25毫秒→1秒運行 repaint()40次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void paint(Graphics g) {

        if (offScreenImage == null) {
            offScreenImage = createImage(width, height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0, 0, width, height);

        if (state == 0) {//未開始
            gImage.drawImage(GameUtils.bgImg, 0, 0, null);
            gImage.drawImage(GameUtils.planeImg, 100, 100, null);//我方飛機會在正中間
            GameUtils.drawWord(gImage, "點擊開始遊戲", Color.yellow, 50, 250, 200); //文字會在正中間
            GameUtils.drawWord(gImage, "按空白鍵暫停/繼續遊戲", Color.yellow, 30, 250, 250);//文字會在正中間
        }

        if (state == 1) {//已開始

            bgObj.paintSelf(gImage);
            GameUtils.gameObjList.addAll(GameUtils.blastObjList);

            //運行中
            for (int i = 0; i < GameUtils.gameObjList.size(); i++) {
                GameUtils.gameObjList.get(i).paintSelf(gImage);
            }
            GameUtils.gameObjList.removeAll(GameUtils.removeList);

        }

        if (state == 3) {//破關失敗
            gImage.drawImage(GameUtils.blastImg, planeObj.getX() - 35, planeObj.getY() - 50, null);
            GameUtils.drawWord(gImage, "Game Over", Color.red, 50, 250, 200);

        }

        if (state == 4) {//破關成功
            gImage.drawImage(GameUtils.blastImg, bossObj.getX() + 30, bossObj.getY(), null);
            GameUtils.drawWord(gImage, "恭喜破關", Color.PINK, 50, 250, 200);

        }

        GameUtils.drawWord(gImage, score + "分", Color.white, 50, 30, 100);
        g.drawImage(offScreenImage, 0, 0, null);
        count++;
        System.out.println(GameUtils.gameObjList.size());

    }

    void createObj() {
        //我方砲彈生成速度：繪製20次後才產生1顆砲彈
        if (count % 20 == 0) {
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg, planeObj.getX(), planeObj.getY() - 10, 14, 29, 5, this));
            GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }

        //敵方飛機生成速度：繪製40次後才產生3架飛機
        if (count % 40 == 0) {
            GameUtils.enemyObjList.add(new EnemyObj(GameUtils.enemyImg, (int) (Math.random() * 3 * 50), 0, 49, 36, 5, this));
            GameUtils.gameObjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size() - 1));
            enemyCount++;
        }

        //敵方Boss砲彈生成速度：繪製40次後才產生1顆砲彈
        if (count % 40 == 0 && bossObj != null) {
            GameUtils.bulletObjList.add(new BulletObj(GameUtils.bulletImg, bossObj.getX(), bossObj.getY() + 10, 15, 25, 5, this));
            GameUtils.gameObjList.add(GameUtils.bulletObjList.get(GameUtils.bulletObjList.size() - 1));
        }
        //敵方Boss生成速度：敵方飛機大於10架後Boss才出現
        if (enemyCount > 10 && bossObj == null) {
            bossObj = new BossObj(GameUtils.bossImg, 200, 10, 155, 100, 5, this);
            GameUtils.gameObjList.add(bossObj);

        }

    }

    public void restartGame() {
        state = 1;
        score = 0;
        count = 1;
        enemyCount = 0;
        bossObj = null;
        GameUtils.gameObjList.clear();
        GameUtils.shellObjList.clear();
        GameUtils.enemyObjList.clear();
        GameUtils.bulletObjList.clear();
        GameUtils.blastObjList.clear();
        GameUtils.removeList.clear();

        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);

        planeObj.resetPosition();  // 如果飛機的位置在遊戲過程中改變了，您可能需要一個方法來重置它

        repaint();
    }

    public static void main(String[] args) {
        PlaneWar planeWar = new PlaneWar();
        planeWar.launch();

    }

}
