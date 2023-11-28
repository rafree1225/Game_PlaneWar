package com.web.game.obj;

import com.web.game.PlaneWar;
import com.web.game.utils.GameUtils;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class BulletObj extends GameObj {

    public BulletObj(Image img, int x, int y, int width, int height, double speed, PlaneWar frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;

        //敵方子彈與我方飛機的碰撞檢測
        if (this.getRec().intersects(this.frame.planeObj.getRec())) {
            //破關失敗
            PlaneWar.state = 3;

        }
        
        //敵方子彈若超出視窗範圍→消失(即其y座標 > 600(即視窗高度))；改變其座標(-300,300)並清除
        if(y > 600){
             this.x = -300;
             this.y = 300;
             GameUtils.removeList.add(this);
        }

    }

    @Override
    public Rectangle getRec() {
        return super.getRec(); //To change body of generated methods, choose Tools | Templates.
    }

}
