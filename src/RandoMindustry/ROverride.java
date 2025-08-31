package RandoMindustry;

import arc.Core;
import arc.math.Mathf;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.entities.bullet.*;
import mindustry.world.blocks.defense.turrets.*;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.max;

public class ROverride {
    public static int size;
    public static float maxRange;
    public static Seq<BulletType> b = new Seq();
    // 加载模组的方法
    public static void load() {
        int n = Core.settings.getInt("RandomSeed1") + Core.settings.getInt("RandomSeed2") * 10 + Core.settings.getInt("RandomSeed3") * 100 + Core.settings.getInt("RandomSeed4") * 1000 + Core.settings.getInt("RandomSeed5") * 10000;
        Mathf.rand.setSeed(n);
        Vars.content.units().each(u -> {
            u.weapons.each(w -> {
                b.add(w.bullet.copy());
                size++;
            });
        });
        Vars.content.blocks().each(B -> {
            if(B instanceof Turret){
                    if(B instanceof ItemTurret){
                        ((ItemTurret)B).ammoTypes.each((item, ammo) -> {
                            b.add(ammo.copy());
                            size++;
                        });
                    }else if(B instanceof LiquidTurret){
                        ((LiquidTurret)B).ammoTypes.each((item, ammo) -> {
                            b.add(ammo.copy());
                            size++;
                        });
                    }else if(B instanceof LaserTurret){
                        b.add(((LaserTurret)B).shootType.copy());
                        size++;
                    } else if(B instanceof ContinuousLiquidTurret){
                        b.add(((ContinuousLiquidTurret)B).shootType.copy());
                        size++;
                    }else if(B instanceof ContinuousTurret){
                        b.add(((ContinuousTurret)B).shootType.copy());
                        size++;
                    }else if(B instanceof PowerTurret){
                        b.add(((PowerTurret)B).shootType.copy());
                        size++;
                    }
                }
        });
        Mathf.random();
        Vars.content.units().each(u -> {
            maxRange = 0f;
            u.weapons.each(w -> {
                w.bullet = b.get((int)(Mathf.random()*size));
                maxRange = max(w.bullet.range,maxRange);
            });
            u.range = u.maxRange = maxRange;
        });
        Vars.content.blocks().each(B -> {
            if(B instanceof Turret){
                if(B instanceof ItemTurret){
                    ((ItemTurret)B).ammoTypes.each((item, ammo) -> {
                        ((ItemTurret)B).ammoTypes.put(item,b.get((int)(Mathf.random()*size)));
                    });
                }else if(B instanceof LaserTurret){
                    ((LaserTurret)B).shootType = b.get((int)(Mathf.random()*size));
                }else if(B instanceof LiquidTurret) {
                    ((LiquidTurret) B).ammoTypes.each((item, ammo) -> {
                        ((LiquidTurret) B).ammoTypes.put(item, b.get((int)(Mathf.random()*size)));
                    });
                }else if(B instanceof ContinuousLiquidTurret){
                    ((ContinuousLiquidTurret)B).shootType = b.get((int)(Mathf.random()*size));
                }else if(B instanceof ContinuousTurret){
                    ((ContinuousTurret)B).shootType = b.get((int)(Mathf.random()*size));
                }else if(B instanceof PowerTurret){
                    ((PowerTurret)B).shootType = b.get((int)(Mathf.random()*size));
                }
            }
        });
    }
}