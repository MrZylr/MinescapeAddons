package com.zylr.minescapeaddons.addons.gui.widgets;

import com.zylr.minescapeaddons.addons.config.Config;
import com.zylr.minescapeaddons.addons.gui.screens.settings.MainSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.widget.list.ExtendedList;
import net.minecraftforge.fml.client.gui.widget.ModListWidget;

public class SettingsListWidget extends ExtendedList<SettingsListWidget.SettingsEntry> {
    private Minecraft mc;
    MainSettings parent;

    public SettingsListWidget(MainSettings parent, int settingsWidth, int settingsHeight, int topIn, int bottomIn) {
        super(parent.getMinecraft(), settingsWidth, settingsHeight, topIn, bottomIn, parent.getMinecraft().fontRenderer.FONT_HEIGHT*2);
        this.parent = parent;
        this.mc = parent.getMinecraft();
        this.refreshList();
    }

    @Override
    protected int getScrollbarPosition()
    {
        return parent.getSettingsWidth();
    }

    @Override
    public int getRowWidth() { return parent.getSettingsWidth(); }

    public void refreshList() {
        this.clearEntries();
        parent.buildModList(this::addEntry, setting->new SettingsEntry(setting, this.parent));
//        for (Config value : Config.values()) {
//            this.addEntry(new SettingsEntry(value, parent));
//        }
    }

    public class SettingsEntry extends ExtendedList.AbstractListEntry<SettingsEntry> {
        private MainSettings parent;
        Config setting;

        public SettingsEntry(Config setting, MainSettings parent) {
            this.parent = parent;
            this.setting = setting;
        }

        @Override
        public void render(int entryIdx, int top, int left, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean p_194999_5_, float partialTicks) {
            FontRenderer font = mc.fontRenderer;

            font.drawString(setting.getName(), left +3, top +2, parent.getSettingsFontColor());
        }

//        @Override
//        public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_)
//        {
//            parent.setSelected(this);
//            ModListWidget.this.setSelected(this);
//            return false;
//        }

        public Config getSetting() {return setting; }
    }
}
