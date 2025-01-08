package ru.fedfox.SimpleForms.Events;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.ConfigSection;
import ru.fedfox.SimpleForms.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormRespondEvent implements Listener {

    @EventHandler
    public void PRE(PlayerFormRespondedEvent e){
        if (e.getWindow() instanceof FormWindowSimple) {
            if (!e.wasClosed()) {
                if (e.getResponse() == null)return;
                Player p = e.getPlayer();
                List<String> allformsid = new ArrayList<>();
                for (String key : Main.forms.getKeys()){
                    if (!key.contains(".")){
                        allformsid.add(key);
                    }
                }

                for (String formid : allformsid){
                    if (Main.forms.getString(formid+".name").equals(((FormWindowSimple) e.getWindow()).getTitle())){
                        ConfigSection FormSect= Main.forms.getSection(formid);
                        List<Map<String, Object>> FormsButtonsList = FormSect.getList("buttons");
                        for (Map<String, Object> buttname : FormsButtonsList) {
                            if (buttname.get("name").equals(((FormResponseSimple) e.getResponse()).getClickedButton().getText())){
                                if (buttname.containsKey("cmd")){
                                    if (buttname.get("cmd").toString().contains("@log")){
                                        p.getServer().dispatchCommand(p.getServer().getConsoleSender(), buttname.get("cmd").toString().replace("@log ", "").replace("%p", p.getName()));
                                    }else{
                                        p.getServer().dispatchCommand(p, buttname.get("cmd").toString().replace("%p", p.getName()));
                                    }
                                }
                                if (buttname.containsKey("message")){
                                    p.sendMessage(buttname.get("message").toString().replace("%p", p.getName()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
