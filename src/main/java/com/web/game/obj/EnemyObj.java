package com.web.game.obj;

import com.web.game.PlaneWar;
import com.web.game.utils.GameUtils;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class EnemyObj extends GameObj {

    public EnemyObj() {
    }

    public EnemyObj(Image img, int x, int y, int width, int height, double speed, PlaneWar frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;

        //我方飛機與敵機碰撞檢測
        if (this.getRec().intersects(this.frame.planeObj.getRec())) {
            //破關失敗
            PlaneWar.state = 3;

        }

        //敵方飛機若超出視窗範圍→消失(即其y座標 > 800(即視窗高度))；改變其座標(-200,200)並清除
        if (y > 800) {
            this.x = -200;
            this.y = 200;
            GameUtils.removeList.add(this);
        }

        //敵機被炮彈擊中：消失前移動到(-200,200) 我方砲彈(-100,100)
        for (ShellObj shellObj : GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())) {
                BlastObj blastObj = new BlastObj(x,y);
                GameUtils.blastObjList.add(blastObj);
                GameUtils.removeList.add(blastObj);

                shellObj.setX(-100);
                shellObj.setY(100);
                this.x = -200;
                this.y = 200;
                GameUtils.removeList.add(shellObj);
                GameUtils.removeList.add(this);
                PlaneWar.score++;
            }

        }
    }

    @Override
    public Rectangle getRec() {//每個物件所在的矩形範圍
        return super.getRec();
    }

}
