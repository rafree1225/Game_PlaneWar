package com.web.game.obj;

import com.web.game.PlaneWar;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaneObj extends GameObj {

    public PlaneObj() {
    }

    public PlaneObj(Image img, int x, int y, int width, int height, double speed, PlaneWar frame) {
        super(img, x, y, width, height, speed, frame);

        this.frame.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                PlaneObj.super.x = e.getX();
                PlaneObj.super.y = e.getY();
            }

        });

    }

    @Override
    public Image getImg() {
        return super.getImg();
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);

        //我方飛機與敵方Boss的碰撞檢測
        if (this.frame.bossObj != null && this.getRec().intersects(this.frame.bossObj.getRec())) {
            //破關失敗
            PlaneWar.state = 3;
        }

    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    public void resetPosition() {

        this.x = 100;
        this.y = 300;
    }

}
