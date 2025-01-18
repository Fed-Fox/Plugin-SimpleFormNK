package ru.fedfox.SimpleForms.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.ConfigSection;
import ru.fedfox.SimpleForms.Main;
import ru.fedfox.SimpleForms.Util.FormConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class sformopen extends Command {



    public sformopen() {
        super("sformopen", Main.messages.getString("command-description"), "has't permission", new String[]{"sfopen", "sfo"});
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("form", CommandParamType.STRING),
                CommandParameter.newType("player", CommandParamType.TARGET)
        });
    }

    @Override
    public boolean execute(CommandSender s, String str, String[] args) {
        if (s.hasPermission("sfo.command.use")){
            if (args.length < 1){
                s.sendMessage(Main.messages.getString("usage"));
            }else if (args[0].length() < 1){
                s.sendMessage(Main.messages.getString("usage"));
            }else if (args[1].length() < 1){
                s.sendMessage(Main.messages.getString("usage"));
            }else{
                Player pl = s.getServer().getPlayer(args[1]);
                if (pl != null){
                    if (!Main.forms.getKeys().contains(args[0])){
                        s.sendMessage(Main.messages.getString("no-found-form").replace("%form", args[0]));
                    }else{
                        if (Main.forms.getString(args[0]+".type").equals("Simple")){
                            String FormName = Main.forms.getString(args[0]+".name");
                            String FormContent = Main.forms.getString(args[0]+".content");
                            ArrayList<String> FormButtonList = new ArrayList<>();
                            ConfigSection FormSect= Main.forms.getSection(args[0]);
                            List<Map<String, Object>> FormsButtonsList = FormSect.getList("buttons");
                            int i = 0;
                            for (Map<String, Object> buttname : FormsButtonsList) {
                                FormButtonList.add(i, (String) buttname.get("name"));
                                i++;
                            }
                            pl.showFormWindow(FormConstructor.createSimpleForm(FormName, FormContent, FormButtonList));
                        }
                    }
                }else{
                    s.sendMessage(Main.messages.getString("no-found-player").replace("%player", args[1]));
                }
            }
        }else{
            s.sendMessage(Main.messages.getString("no-permission"));
        }
        return false;
    }
}
