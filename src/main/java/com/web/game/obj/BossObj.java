
package com.web.game.obj;

import com.web.game.PlaneWar;
import com.web.game.utils.GameUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


public class BossObj extends GameObj{

    //生命值
    int life = 10;
  
    public BossObj(Image img, int x, int y, int width, int height, double speed, PlaneWar frame) {
        super(img, x, y, width, height, speed, frame);
    }
    
    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage); 
        
        if(x > 800 || x < -50){// Boss碰到視窗邊界
            
            speed = -speed;//速度正負交替→移動方向改變
        
        }
        x += speed;
        
        for(ShellObj shellObj : GameUtils.shellObjList){
            //打中Boss，生命值減少，碰撞到的砲彈消失
            if(this.getRec().intersects(shellObj.getRec())){
                shellObj.setX(-100);
                shellObj.setY(100);
                GameUtils.removeList.add(shellObj);
                life--;
            }
            
            if(life <= 0){
                //Boss的生命值歸0，破關成功
                PlaneWar.state=4;
            
            }
        
        }
        
        //生命值血條的白色背景
        gImage.setColor(Color.white);
        gImage.fillRect(20, 40, 100, 10);
        
        //生命值血條的繪製
        gImage.setColor(Color.red);
        gImage.fillRect(20, 40, life*100/10, 10);
    }

    @Override
    public Rectangle getRec() {
        return super.getRec(); 
    }

   
    
    
    
}
