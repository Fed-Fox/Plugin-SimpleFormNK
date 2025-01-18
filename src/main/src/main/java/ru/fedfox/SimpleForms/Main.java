package ru.fedfox.SimpleForms;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import ru.fedfox.SimpleForms.Commands.sformopen;
import ru.fedfox.SimpleForms.Events.FormRespondEvent;

import java.io.File;

public class Main extends PluginBase implements Listener {

    public static Config messages, forms;

    @Override
    public void onEnable(){

        this.saveResource("forms.yml", false);
        this.saveResource("messages.yml", false);
        forms = new Config(new File(getDataFolder(), "forms.yml"), Config.YAML);
        messages = new Config(new File(getDataFolder(), "messages.yml"), Config.YAML);

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new FormRespondEvent(), this);
        this.getServer().getCommandMap().register("", new sformopen());

    }
}
