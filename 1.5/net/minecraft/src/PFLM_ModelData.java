package net.minecraft.src;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;

public class PFLM_ModelData implements MMM_IModelCaps, Modchu_IModelCaps {

	public MMM_ModelDuo modelMain;
	public MMM_ModelDuo modelFATT;
	public EntityLiving owner;
	private static Map<String, Integer> caps;
	public String modelArmorName = null;
	public boolean localFlag = false;
	public boolean isActivated = false;
	public boolean isPlayer = false;
	public boolean isWait = false;
	public boolean isSitting = false;
	public boolean isSleeping = false;
	public boolean isWaitFSetFlag = false;
	public boolean shortcutKeysAction = false;
	public boolean shortcutKeysActionInitFlag = true;
	public boolean changeModelFlag = true;
	public boolean resetHandedness = true;
	public float isWaitF = 0.0F;
	public float modelScale = 0.0F;
	public int isWaitTime = 0;
	public int skinMode = 0;
	public int initFlag = 0;
	public int runActionNumber = 0;
	public int handedness = 0;
	public int maidColor = 0;
	public boolean motionResetFlag = false;
	public boolean mushroomConfusionLeft = false;
	public boolean mushroomConfusionRight = false;
	public boolean mushroomConfusionFront = false;
	public boolean mushroomConfusionBack = false;
	public boolean motionSetFlag = false;
	public boolean mushroomBack = false;
	public boolean mushroomForward = false;
	public boolean mushroomKeyBindResetFlag = false;
	public boolean mushroomKeyBindSetFlag = false;
	public int mushroomConfusionType = 0;
	public int mushroomConfusionCount = 0;
	public final int mushroomConfusionTypeMax = 4;
	public boolean mushroomLeft = false;
	public boolean mushroomRight = false;
/*//b173delete
	public boolean keyBindForwardPressed;
	public boolean keyBindBackPressed;
	public boolean keyBindLeftPressed;
	public boolean keyBindRightPressed;
	public KeyBinding keyBindForward;
	public KeyBinding keyBindBack;
	public KeyBinding keyBindLeft;
	public KeyBinding keyBindRight;
	public boolean mushroomConfusionFlag = false;
*///b173delete
    private Minecraft mc = Minecraft.getMinecraft();
    private boolean isInventory = false;

	public PFLM_ModelData(RenderLiving renderLiving) {
		modelMain = new MMM_ModelDuo(renderLiving);
		modelMain.isModelAlphablend = mod_PFLM_PlayerFormLittleMaid.AlphaBlend;
		modelMain.textureInner = new String [4];
		modelMain.textureOuter = new String [4];
		modelFATT = new MMM_ModelDuo(renderLiving);
		modelFATT.isModelAlphablend = mod_PFLM_PlayerFormLittleMaid.AlphaBlend;
		modelFATT.textureInner = new String [4];
		modelFATT.textureOuter = new String [4];
	}


	static {
		caps = new HashMap<String, Integer>();
		caps.put("isLookSuger", caps_isLookSuger);
		caps.put("isWait", caps_isWait);
		caps.put("isOpenInv", caps_isOpenInv);
		caps.put("isCamouflage", caps_isCamouflage);
		caps.put("isPlanter", caps_isPlanter);
		caps.put("entityIdFactor", caps_entityIdFactor);
		caps.put("height", caps_height);
		caps.put("width", caps_width);
		caps.put("YOffset", caps_YOffset);
		caps.put("mountedYOffset", caps_mountedYOffset);
		caps.put("dominantArm", caps_dominantArm);
		caps.put("HeadMount", caps_HeadMount);
		caps.put("Items", caps_Items);
		caps.put("Actions", caps_Actions);
		caps.put("Inventory", caps_Inventory);
		caps.put("interestedAngle", caps_interestedAngle);
	}

	/**
	 * setLivingAnimationsLM 呼び出し前に呼ばれる。
	 */
	public void setLivingAnimationsBefore(MMM_ModelBiped model, EntityLiving entityliving, float f, float f1, float f2) {
	}

	/**
	 * setLivingAnimationsLM 呼び出し後に呼ばれる。
	 */
	public void setLivingAnimationsAfter(MMM_ModelBiped model, EntityLiving entityliving, float f, float f1, float f2) {
		boolean isRiding = model.getCapsValueBoolean(caps_isRiding);
		model.setCapsValue(caps_isRiding, !isRiding ? model.getCapsValueBoolean(caps_isSitting) : isRiding);
		if (entityliving != null) ;else return;
		model.setCapsValue(caps_settingShowParts);
	}

	/**
	 * setRotationAnglesLM 呼び出し前に呼ばれる。
	 */
	public void setRotationAnglesBefore(MMM_ModelBiped model, float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
	}

	/**
	 * setRotationAnglesLM 呼び出し後に呼ばれる。
	 */
	public void setRotationAnglesAfter(MMM_ModelBiped model, float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		if (entity != null) ;else return;
		if (model.getCapsValueBoolean(caps_firstPerson)) ((MultiModelBaseBiped) model).setRotationAnglesfirstPerson(f, f1, f2, f3, f4, f5, entity);
		if (model.getCapsValueBoolean(caps_shortcutKeysAction)) {
			((MultiModelBaseBiped) model).action(entity, model.getCapsValueInt(caps_runActionNumber));
			if (model.getCapsValueBoolean(caps_actionFlag)) {
				model.setCapsValue(caps_actionSpeed, 0.0F);
				model.setCapsValue(caps_actionFlag, false);
			}
		}
	}

	@Override
	public Map<String, Integer> getModelCaps() {
		return caps;
	}

	@Override
	public Object getCapsValue(String pCapsName, Object ...pArg) {
		return getCapsValue(caps.get(pCapsName), pArg);
	}

	@Override
	public int getCapsValueInt(int pIndex, Object ...pArg) {
		Integer li = (Integer)getCapsValue(pIndex, pArg);
		return li == null ? 0 : li;
	}

	@Override
	public float getCapsValueFloat(int pIndex, Object ...pArg) {
		Float lf = (Float)getCapsValue(pIndex, pArg);
		return lf == null ? 0F : lf;
	}

	@Override
	public double getCapsValueDouble(int pIndex, Object ...pArg) {
		Double ld = (Double)getCapsValue(pIndex, pArg);
		return ld == null ? 0D : ld;
	}

	@Override
	public boolean getCapsValueBoolean(int pIndex, Object ...pArg) {
		Boolean lb = (Boolean)getCapsValue(pIndex, pArg);
		return lb == null ? false : lb;
	}

	@Override
	public boolean setCapsValue(int pIndex, Object... pArg) {
		switch (pIndex) {
		case caps_dominantArm:
			if (pArg != null
			&& pArg.length > 0
			&& pArg[0] != null) setHandedness((Integer) pArg[0]);
			return true;
		case caps_isOpenInv:
			if (pArg != null
			&& pArg.length > 0
			&& pArg[0] != null) setIsInventory((Boolean) pArg[0]);
			return true;
		case caps_isPlayer:
			if (pArg != null
			&& pArg.length > 0
			&& pArg[0] != null) setIsPlayer((Boolean) pArg[0]);
			return true;
		case caps_isWait:
			if (pArg != null
			&& pArg.length > 0
			&& pArg[0] != null) setIsWait((Boolean) pArg[0]);
			return true;
		case caps_isSitting:
			if (pArg != null
			&& pArg.length > 0
			&& pArg[0] != null) setIsSitting((Boolean) pArg[0]);
			return true;
		case caps_isSleeping:
			if (pArg != null
			&& pArg.length > 0
			&& pArg[0] != null) setIsSleeping((Boolean) pArg[0]);
			return true;
		}

		return false;
	}

	@Override
	public boolean setCapsValue(String pCapsName, Object... pArg) {
		return setCapsValue(caps.get(pCapsName), pArg);
	}

	@Override
	public Object getCapsValue(int pIndex, Object ...pArg) {
		int li = 0;

		switch (pIndex) {
		case caps_dominantArm:
			return getHandedness();
		case caps_isLookSuger:
			return getIsLookSuger();
		case caps_entityIdFactor:
			return getEntityIdFactor();
		case caps_isOpenInv:
			return getIsInventory();
		case caps_Inventory:
			return getInventory();
		case caps_maidColor:
			return getMaidColor();
		case caps_texture:
			if (pArg != null
			&& pArg.length > 1
			&& pArg[0] != null
			&& pArg[1] != null) return getTexture((String) pArg[0], (Integer) pArg[1]);
		case caps_isPlayer:
			return getIsPlayer();
		case caps_isWait:
			return getIsWait();
		case caps_isCamouflage:
			return isCamouflage();
		case caps_isPlanter:
			return isPlanter();
		case caps_height:
			return owner.height;
		case caps_width:
			return owner.width;
		case caps_YOffset:
			return owner.yOffset;
		case caps_HeadMount:
			return ((EntityPlayer) owner).inventory.getStackInSlot(17);
		case caps_Items:
			return getItems();
		case caps_Actions:
			return getActions();
		}

		return null;
	}

	private EnumAction[] getActions() {
		EnumAction[] lactions = new EnumAction[2];
		ItemStack litemstack = ((EntityPlayer) owner).inventory.getCurrentItem();
		lactions[getHandedness()] = litemstack != null && ((EntityPlayer) owner).getItemInUseCount() > 0 ? litemstack.getItemUseAction() : null;
		return lactions;
	}

	private ItemStack[] getItems() {
		ItemStack[] itemStack = new ItemStack[2];
		itemStack[getHandedness()] = ((EntityPlayer) owner).inventory.getCurrentItem();
		return itemStack;
	}

	private boolean isPlanter() {
		return false;
	}

	private boolean isCamouflage() {
		return false;
	}

	private boolean getIsWait() {
		return isWait;
	}

	private void setIsWait(boolean b) {
		isWait = b;
	}

    private boolean getIsSitting()
    {
    	return isSitting;
    }

    private void setIsSitting(boolean b)
    {
    	isSitting = b;
    }

    private boolean getIsSleeping()
    {
    	return isSleeping;
    }

    private void setIsSleeping(boolean b)
    {
    	isSleeping = b;
    }

	private boolean getIsPlayer() {
		return isPlayer;
	}

	private void setIsPlayer(boolean b) {
		isPlayer = b;
	}

	private boolean getIsInventory() {
		return isInventory;
	}

	private void setIsInventory(boolean b) {
		isInventory = b;
	}

	private int getHandedness() {
		return handedness;
	}

    private void setHandedness(int i) {
    	handedness = i;
    }

    private boolean getIsLookSuger() {
    	ItemStack itemstack = ((EntityPlayer) owner).inventory.getCurrentItem();
    	if (itemstack != null) {
    		Item item = itemstack.getItem();
    		if (item == Item.sugar) return true;
    	}
    	return false;
    }

    private float getOnGround(Object o)
    {
    	if (o != null) {
    		Class c = o.getClass();
    		if (c == Float.class
    				| c == float.class) {
    			return (Float) o;
    		}
    		Object o1 = Modchu_Reflect.getFieldObject(c, "onGround", o);
    		if (o1 != null) return (Float) o1;
    	}
    	return 0.0F;
    }

    private float getEntityIdFactor() {
    	return (float)owner.entityId * 70;
    }

    private Object getInventory() {
    	return ((EntityPlayer) owner).inventory;
    }

    private int getMaidColor() {
    	if (owner instanceof EntityPlayer) {
    		if (mc.currentScreen != null
    				&& mc.currentScreen instanceof PFLM_Gui) {
    			return mod_PFLM_PlayerFormLittleMaid.maidColor;
    		}
    		return maidColor;
    	} else {
    		if (mc.currentScreen != null) ;else return 0;
    		if (mc.currentScreen instanceof PFLM_Gui) return PFLM_Gui.setColor;
    		if (mc.currentScreen instanceof PFLM_GuiModelSelect) return ((PFLM_GuiModelSelect) mc.currentScreen).modelColor;
    		if (mc.currentScreen instanceof PFLM_GuiOthersPlayerIndividualCustomize) return PFLM_GuiOthersPlayerIndividualCustomize.othersMaidColor;
    		if (mc.currentScreen instanceof PFLM_GuiOthersPlayer) return mod_PFLM_PlayerFormLittleMaid.othersMaidColor;
    	}
    	return 0;
    }

    private String getTexture(String s, int i) {
    	if (owner instanceof EntityPlayer) {
    		return mod_PFLM_PlayerFormLittleMaid.textureManagerGetTextureName(s, i);
    	}
    	return null;
    }
}
