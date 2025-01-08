package ru.fedfox.SimpleForms.Util;

import cn.nukkit.event.Listener;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;

import java.util.ArrayList;
import java.util.HashMap;

public class FormConstructor implements Listener {

    public static FormWindowSimple createSimpleForm(String FormTitle, String FormContent, ArrayList<String> namebutt){
        FormWindowSimple form = new FormWindowSimple(FormTitle, FormContent);
        for (String ButtonName : namebutt){
            ElementButton button = new ElementButton(ButtonName);
            form.addButton(button);
        }
        return form;
    }

}
