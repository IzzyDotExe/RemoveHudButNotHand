package ca.blutopia.removegui;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Option;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.client.gui.components.OptionsList;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class configScreen extends Screen {

    private final removegui mod;

    private OptionsList optionlist;
    private Checkbox checkbox;
    private Checkbox checkbox2;

    public configScreen(removegui main) {
        super(new TextComponent("Remove Gui but not hand settings"));
        mod = main;
    }

    @Override
    protected  void init() {

        this.checkbox2 = new Checkbox(20, 20, 20, 20, new TextComponent("Remove HUD but not Hand"), ModConfigHolder.COMMON.toggleMod.get(), true ) {
            @Override
            public void onPress() {
                boolean togglemod = ModConfigHolder.COMMON.toggleMod.get();

                if (togglemod) {
                    ModConfigHolder.COMMON.toggleMod.set(false);
                } else {
                    ModConfigHolder.COMMON.toggleMod.set(true);
                }
            }
        };

        this.checkbox = new Checkbox(20, 20, 20, 20, new TextComponent("Outline Blocks when looking at them"), ModConfigHolder.COMMON.highlightBlocks.get(), true) {

            @Override
            public void onPress() {

                boolean highlight = ModConfigHolder.COMMON.highlightBlocks.get();

                if (highlight) {
                    ModConfigHolder.COMMON.highlightBlocks.set(false);
                } else {
                    ModConfigHolder.COMMON.highlightBlocks.set(true);
                }


                super.onPress();
            }
        };

        this.optionlist = new OptionsList(
                Minecraft.getInstance(),
                this.width, this.height,
                24, this.height - 32,
                25
        );

        this.addWidget(this.optionlist);

        optionlist.addBig(new Option("Outline Blocks") {
            @Override
            public AbstractWidget createButton(Options p_91719_, int p_91720_, int p_91721_, int p_91722_) {
                return checkbox;
            }
        });

        optionlist.addBig(new Option("Remove HUD but not Hand") {
            @Override
            public AbstractWidget createButton(Options p_91719_, int p_91720_, int p_91721_, int p_91722_) {
                return checkbox2;
            }
        });

        this.addWidget(checkbox);
        this.addRenderableWidget(new Button((this.width - 200)/2, this.height - 26, 200, 20, new TextComponent("Done"), button -> this.onClose()));

    }

    @Override
    public void render(PoseStack stack, int mousex, int mousey, float partialTicks) {
        this.renderBackground(stack);

        this.optionlist.render(stack, mousex, mousey, partialTicks);

        drawCenteredString(stack, this.font, this.title.getString(),
                this.width / 2, 8, 0xFFFFFF);

        super.render(stack, mousex, mousey, partialTicks);

    }

}

