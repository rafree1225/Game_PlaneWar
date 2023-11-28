
package com.web.game.obj;

import com.web.game.PlaneWar;
import com.web.game.utils.GameUtils;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


public class ShellObj extends GameObj{

    public ShellObj() {
    }

    public ShellObj(Image img, int x, int y, int width, int height, double speed, PlaneWar frame) {
        super(img, x, y, width, height, speed, frame);
    }
    

    @Override
    public Rectangle getRec() {
        return super.getRec(); 
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage); 
        y -= speed;
        
        //我方砲彈若超出視窗範圍→消失(即其y座標 < 0)；改變其座標(-100,100)並清除
        if(y < 0){
             this.x = -100;
             this.y = 100;
             GameUtils.removeList.add(this);
        }
        
    }

    @Override
    public Image getImg() {
        return super.getImg(); 
    }
    
    
    
    
}
