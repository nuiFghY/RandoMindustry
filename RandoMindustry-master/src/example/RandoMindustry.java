package example;

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
        Log.info("uwu?");
    }


    public void loadSettings(){
        ui.settings.addCategory(Core.bundle.get("settingShow"),"RandoMindustry-uwu",S -> {
            S.sliderPref("RandomSeed", 1, 0, 114514, 1, i -> i + "x");
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