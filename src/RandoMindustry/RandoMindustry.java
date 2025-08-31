package RandoMindustry;

import arc.Core;
import arc.Events;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.layout.Table;
import arc.util.Log;
import arc.util.Scaling;
import arc.util.Strings;
import arc.util.Time;
import mindustry.ctype.UnlockableContent;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.ClassMap;
import mindustry.mod.Mod;
import mindustry.mod.Mods;
import mindustry.ui.Styles;
import mindustry.ui.dialogs.BaseDialog;

import static mindustry.Vars.mods;
import static mindustry.Vars.ui;
@SuppressWarnings("unused")
public class RandoMindustry extends Mod {

    public static Mods.LoadedMod RandomAmmo;

    public RandoMindustry() {
        Log.info("RandoMindustry");
    }

    public int g,s,b,q,w;

    public void loadSettings(){
        ui.settings.addCategory(Core.bundle.get("settingShow"),"randomindustry-Rand",S -> {
            S.sliderPref("RandomSeed5", 0, 0, 9, 1, i -> {
                w = i;
                return "[gold]" + w + "[white]    ";
            });
            S.sliderPref("RandomSeed4", 0, 0, 9, 1, i -> {
                q = i;
                return  " [gold]" + q + "[white]   ";
            });
            S.sliderPref("RandomSeed3", 0, 0, 9, 1, i -> {
                b = i;
                return "  [gold]" + b + "[white]  ";
            });
            S.sliderPref("RandomSeed2", 0, 0, 9, 1, i -> {
                s = i;
                return "   [gold]" + s + "[white] ";
            });
            S.sliderPref("RandomSeed1", 0, 0, 9, 1, i -> {
                g = i;
                return "    [gold]" + g + "[white]";
            });
        });
    }

    @Override
    public void init(){
        loadSettings();
        //Updated.meta.hidden = Core.settings.getInt("UpdateLevel") == 0;

        Events.on(ClientLoadEvent.class, e -> {
            ROverride.load();
        });
    }
}