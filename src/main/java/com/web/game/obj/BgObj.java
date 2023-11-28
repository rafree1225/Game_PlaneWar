package com.web.game.obj;

import java.awt.Graphics;
import java.awt.Image;

public class BgObj extends GameObj {

    public BgObj() {
        super();

    }

    public BgObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage); // 繪製當前的背景圖片

        // 計算並繪製第二張背景圖片的位置，以便創建滾動效果
        int secondBgY = y - img.getHeight(null);
        gImage.drawImage(img, x, secondBgY, null);

        y += speed;

        // 當背景圖片滾動超出視窗大小時，重設位置
        if (y >= img.getHeight(null)) {
            y = 0;
        }
    }

}
