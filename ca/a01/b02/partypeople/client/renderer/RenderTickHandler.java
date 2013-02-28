package ca.a01.b02.partypeople.client.renderer;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class RenderTickHandler implements ITickHandler {

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {
        GLHelper.drawString("Krebskopf", 10, 15);
    }

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.RENDER);
    }

    @Override
    public String getLabel() {
        return "PartyPeople: Render Tick";
    }
}
