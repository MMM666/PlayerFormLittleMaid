package net.minecraft.src;

import java.awt.image.BufferedImage;
import java.util.HashMap;
/*//FMLdelete
import net.minecraft.client.renderer.entity.*;
*///FMLdelete
public class PFLM_RenderPlayerSmart extends RenderPlayer {

	public static boolean firstPersonHandResetFlag;
	public static boolean resetFlag;
	public static boolean textureResetFlag;
    public static ModelPlayerFormLittleMaidSmart modelBasicOrig[];
	public static HashMap playerData = new HashMap();

	public static void clearPlayers() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	public static Object[] checkimage(BufferedImage bufferedimage) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	public static PFLM_ModelDataSmart getPlayerData(
			EntityClientPlayerMP thePlayer) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

}
