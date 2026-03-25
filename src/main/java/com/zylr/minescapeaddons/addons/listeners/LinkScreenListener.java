package com.zylr.minescapeaddons.addons.listeners;

import com.zylr.minescapeaddons.addons.MinescapeAddons;
import com.zylr.minescapeaddons.addons.gui.screens.BrowserScreen;
import com.zylr.minescapeaddons.addons.gui.widgets.BrowserWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Listener that intercepts ConfirmLinkScreen to open links in the in-game browser
 */
@EventBusSubscriber(modid = MinescapeAddons.MOD_ID, value = Dist.CLIENT)
public class LinkScreenListener {

    private static final Map<Screen, String> capturedUrls = new HashMap<>();

    @SubscribeEvent
    public static void onScreenInit(ScreenEvent.Init.Pre event) {
        if (event.getScreen() instanceof ConfirmLinkScreen confirmLinkScreen) {
            try {
                var urlField = ConfirmLinkScreen.class.getDeclaredField("url");
                urlField.setAccessible(true);
                String url = (String) urlField.get(confirmLinkScreen);
                capturedUrls.put(event.getScreen(), url);
            } catch (Exception e) {
                System.err.println("[PIPBrowser] Failed to get URL from ConfirmLinkScreen: " + e.getMessage());
            }
        }
    }

    @SubscribeEvent
    public static void onScreenInitPost(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof ConfirmLinkScreen) {
            String url = capturedUrls.get(event.getScreen());
            if (url != null) {
                Screen screen = event.getScreen();

                screen.children().stream()
                    .filter(widget -> widget instanceof Button)
                    .map(widget -> (Button) widget)
                    .filter(button -> button.getMessage().equals(CommonComponents.GUI_YES))
                    .findFirst()
                    .ifPresent(yesButton -> {
                        int x = yesButton.getX();
                        int y = yesButton.getY();
                        int width = yesButton.getWidth();
                        int height = yesButton.getHeight();

                        try {
                            var removeWidgetMethod = Screen.class.getDeclaredMethod("removeWidget", net.minecraft.client.gui.components.events.GuiEventListener.class);
                            removeWidgetMethod.setAccessible(true);
                            removeWidgetMethod.invoke(screen, yesButton);
                        } catch (Exception e) {
                            System.err.println("[PIPBrowser] Failed to remove button: " + e.getMessage());
                        }

                        Button newYesButton = Button.builder(CommonComponents.GUI_YES, (button) -> {
                            Minecraft minecraft = Minecraft.getInstance();
                            if (url != null && (url.startsWith("http://") || url.startsWith("https://"))) {
                                if (MinescapeAddons.getInstance().resizableClassic.getBrowserWidget() == null) {
                                    MinescapeAddons.getInstance().resizableClassic.setBrowserWidget(new BrowserWidget(0, 0, 267, 150, url));
                                    MinescapeAddons.getInstance().resizableClassic.fillHudList();
                                }

                                // Open the BrowserScreen with the URL, which will create a new tab
                                minecraft.setScreen(null);
                                minecraft.setScreen(new BrowserScreen(url));
                            } else {
                                minecraft.setScreen(null);
                            }
                            capturedUrls.remove(screen);
                        }).bounds(x, y, width, height).build();

                        try {
                            var methods = Screen.class.getDeclaredMethods();
                            for (var method : methods) {
                                if (method.getName().equals("addRenderableWidget") && method.getParameterCount() == 1) {
                                    method.setAccessible(true);
                                    method.invoke(screen, newYesButton);
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            System.err.println("[PIPBrowser] Failed to add button: " + e.getMessage());
                        }
                    });
            }
        }
    }

    @SubscribeEvent
    public static void onScreenClosed(ScreenEvent.Closing event) {
        // Clean up the captured URL when the screen is closed
        capturedUrls.remove(event.getScreen());
    }
}

