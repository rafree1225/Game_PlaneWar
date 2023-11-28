package com.web.game.obj;

import com.web.game.utils.GameUtils;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class BlastObj extends GameObj {

    public BlastObj(int x, int y) {
        super(x, y);

    }

    static Image[] pic = new Image[16];  // 有16張爆炸圖
    int blastCount = 0;  // 以計算每架敵機被擊中爆炸的次數(只顯示一次)

    static {
        try {
            for (int i = 0; i < pic.length; i++) {
                pic[i] = new ImageIcon(GameUtils.class.getResource("/imgs/blasts/b" + (i + 1) + ".png")).getImage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BlastObj(GameObj enemy) {
        // 將爆炸的座標設置為敵機的中心，然後減去爆炸圖像的一半大小來確保爆炸發生在敵機的中心
        super(
                enemy.getX() + enemy.getWidth() / 2 - pic[0].getWidth(null) / 2,
                enemy.getY() + enemy.getHeight() / 2 - pic[0].getHeight(null) / 2
        );
    }

    @Override
    public void paintSelf(Graphics gImage) {
        if (blastCount < 16) {
            super.img = pic[blastCount];
            super.paintSelf(gImage);
            blastCount++;
        }
    }
}
