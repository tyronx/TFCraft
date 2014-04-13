package TFC.GUI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import TFC.Reference;
import TFC.Containers.ContainerSkills;
import TFC.Core.TFC_Core;
import TFC.Core.TFC_Textures;
import TFC.Core.Player.FoodStatsTFC;
import TFC.Core.Player.PlayerInventory;
import TFC.Core.Util.StringUtil;

public class GuiHealth extends GuiContainerTFC
{
	public static ResourceLocation GuiTex = new ResourceLocation(Reference.ModID, Reference.AssetPathGui + "gui_health.png");
	protected EntityPlayer player;
	public GuiHealth(EntityPlayer player)
	{
		super(new ContainerSkills(player), 176, 104);
		this.setDrawInventory(false);
		this.player = player;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) 
	{
		fontRenderer.drawString(StringUtil.localize("gui.food.fruit"), 5, 13, 0, false);
		fontRenderer.drawString(StringUtil.localize("gui.food.vegetable"), 5, 23, 0, false);
		fontRenderer.drawString(StringUtil.localize("gui.food.grain"), 5, 33, 0, false);
		fontRenderer.drawString(StringUtil.localize("gui.food.protein"), 5, 43, 0, false);
		fontRenderer.drawString(StringUtil.localize("gui.food.dairy"), 5, 53, 0, false);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		this.drawGui(GuiTex);
		int w = (width - this.xSize) / 2;
		int h = (height - this.ySize) / 2;
		FoodStatsTFC food = TFC_Core.getPlayerFoodStats(player);
		int fr = (int)food.nutrFruit;
		drawTexturedModalRect(w+55, h-20, 0, 106, (int)food.nutrFruit, 6);
		drawTexturedModalRect(w+55, h-10, 0, 106, (int)food.nutrVeg, 6);
		drawTexturedModalRect(w+55, h, 0, 106, (int)food.nutrGrain, 6);
		drawTexturedModalRect(w+55, h+10, 0, 106, (int)food.nutrProtein, 6);
		drawTexturedModalRect(w+55, h+20, 0, 106, (int)food.nutrDairy, 6);	
	}

	@Override
	public void initGui()
	{
		super.initGui();
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		buttonList.clear();
		buttonList.add(new GuiInventoryButton(0, guiLeft+176, guiTop-31, 25, 20, 
				0, 86, 25, 20, StringUtil.localize("gui.Inventory.Inventory"), TFC_Textures.GuiInventory));
		buttonList.add(new GuiInventoryButton(1, guiLeft+176, guiTop-12, 25, 20, 
				0, 86, 25, 20, StringUtil.localize("gui.Inventory.Skills"), TFC_Textures.GuiSkills));
		buttonList.add(new GuiInventoryButton(2, guiLeft+176, guiTop+7, 25, 20, 
				0, 86, 25, 20, StringUtil.localize("gui.Calendar.Calendar"), TFC_Textures.GuiCalendar));
		buttonList.add(new GuiInventoryButton(3, guiLeft+176, guiTop+26, 25, 20, 
				0, 86, 25, 20, StringUtil.localize("gui.Inventory.Health"), TFC_Textures.GuiHealth));
	}
	
	@Override
	protected void drawGui(ResourceLocation rl)
	{
		if(rl != null)
		{
			bindTexture(rl);
			guiLeft = (width - xSize) / 2;
			guiTop = ((height - ySize) / 2)-34;
			drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		}
		if(drawInventory)
			PlayerInventory.drawInventory(this, width, height, ySize-PlayerInventory.invYSize);
	}

	@Override
	protected void actionPerformed(GuiButton guibutton)
	{
		if (guibutton.id == 0)
			Minecraft.getMinecraft().displayGuiScreen(new GuiInventoryTFC(Minecraft.getMinecraft().thePlayer));
		else if (guibutton.id == 1)
			Minecraft.getMinecraft().displayGuiScreen(new GuiSkills(Minecraft.getMinecraft().thePlayer));
		else if (guibutton.id == 2)
			Minecraft.getMinecraft().displayGuiScreen(new GuiCalendar(Minecraft.getMinecraft().thePlayer));
	}
}
