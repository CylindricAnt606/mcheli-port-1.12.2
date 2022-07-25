/*     */ package mcheli.mcheli.block;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_IRecipeList;
/*     */ import mcheli.MCH_ItemRecipe;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_RenderAircraft;
/*     */ import mcheli.block.MCH_CurrentRecipe;
/*     */ import mcheli.block.MCH_DraftingTableCreatePacket;
/*     */ import mcheli.block.MCH_DraftingTableGuiContainer;
/*     */ import mcheli.gui.MCH_GuiSliderVertical;
/*     */ import mcheli.helicopter.MCH_HeliInfoManager;
/*     */ import mcheli.plane.MCP_PlaneInfoManager;
/*     */ import mcheli.tank.MCH_TankInfoManager;
/*     */ import mcheli.vehicle.MCH_VehicleInfoManager;
/*     */ import mcheli.wrapper.W_GuiButton;
/*     */ import mcheli.wrapper.W_GuiContainer;
/*     */ import mcheli.wrapper.W_KeyBinding;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_ScaledResolution;
/*     */ import mcheli.wrapper.modelloader.W_ModelCustom;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.crafting.IRecipe;
/*     */ import net.minecraft.item.crafting.ShapedRecipes;
/*     */ import net.minecraft.item.crafting.ShapelessRecipes;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_DraftingTableGui
/*     */   extends W_GuiContainer
/*     */ {
/*     */   private final EntityPlayer thePlayer;
/*     */   private int scaleFactor;
/*     */   private MCH_GuiSliderVertical listSlider;
/*     */   private GuiButton buttonCreate;
/*     */   private GuiButton buttonNext;
/*     */   private GuiButton buttonPrev;
/*     */   private GuiButton buttonNextPage;
/*     */   private GuiButton buttonPrevPage;
/*     */   private int drawFace;
/*     */   private int buttonClickWait;
/*     */   public static final int RECIPE_HELI = 0;
/*     */   public static final int RECIPE_PLANE = 1;
/*     */   public static final int RECIPE_VEHICLE = 2;
/*     */   public static final int RECIPE_TANK = 3;
/*     */   public static final int RECIPE_ITEM = 4;
/*     */   public MCH_IRecipeList currentList;
/*     */   public MCH_CurrentRecipe current;
/*     */   public static final int BUTTON_HELI = 10;
/*     */   public static final int BUTTON_PLANE = 11;
/*     */   public static final int BUTTON_VEHICLE = 12;
/*     */   public static final int BUTTON_TANK = 13;
/*     */   public static final int BUTTON_ITEM = 14;
/*     */   public static final int BUTTON_NEXT = 20;
/*     */   public static final int BUTTON_PREV = 21;
/*     */   public static final int BUTTON_CREATE = 30;
/*     */   public static final int BUTTON_SELECT = 40;
/*     */   public static final int BUTTON_NEXT_PAGE = 50;
/*     */   public static final int BUTTON_PREV_PAGE = 51;
/*     */   public List<List<GuiButton>> screenButtonList;
/*  76 */   public int screenId = 0;
/*     */   
/*     */   public static final int SCREEN_MAIN = 0;
/*     */   public static final int SCREEN_LIST = 1;
/*  80 */   public static float modelZoom = 1.0F;
/*  81 */   public static float modelRotX = 0.0F;
/*  82 */   public static float modelRotY = 0.0F;
/*  83 */   public static float modelPosX = 0.0F;
/*  84 */   public static float modelPosY = 0.0F;
/*     */ 
/*     */   
/*     */   public MCH_DraftingTableGui(EntityPlayer player, int posX, int posY, int posZ) {
/*  88 */     super((Container)new MCH_DraftingTableGuiContainer(player, posX, posY, posZ));
/*  89 */     this.thePlayer = player;
/*  90 */     this.field_146999_f = 400;
/*  91 */     this.field_147000_g = 240;
/*     */     
/*  93 */     this.screenButtonList = new ArrayList<List<GuiButton>>();
/*     */     
/*  95 */     this.drawFace = 0;
/*     */     
/*  97 */     this.buttonClickWait = 0;
/*     */     
/*  99 */     MCH_Lib.DbgLog(player.field_70170_p, "MCH_DraftingTableGui.MCH_DraftingTableGui", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/* 104 */     super.func_73866_w_();
/*     */     
/* 106 */     this.field_146292_n.clear();
/* 107 */     this.screenButtonList.clear();
/* 108 */     this.screenButtonList.add(new ArrayList<GuiButton>());
/* 109 */     this.screenButtonList.add(new ArrayList<GuiButton>());
/*     */     
/* 111 */     List<GuiButton> list = null;
/*     */ 
/*     */ 
/*     */     
/* 115 */     list = this.screenButtonList.get(0);
/*     */     
/* 117 */     GuiButton btnHeli = new GuiButton(10, this.field_147003_i + 20, this.field_147009_r + 20, 90, 20, "Helicopter List");
/* 118 */     GuiButton btnPlane = new GuiButton(11, this.field_147003_i + 20, this.field_147009_r + 40, 90, 20, "Plane List");
/* 119 */     GuiButton btnVehicle = new GuiButton(12, this.field_147003_i + 20, this.field_147009_r + 60, 90, 20, "Vehicle List");
/* 120 */     GuiButton btnTank = new GuiButton(13, this.field_147003_i + 20, this.field_147009_r + 80, 90, 20, "Tank List");
/* 121 */     GuiButton btnItem = new GuiButton(14, this.field_147003_i + 20, this.field_147009_r + 100, 90, 20, "Item List");
/* 122 */     btnHeli.field_146124_l = (MCH_HeliInfoManager.getInstance().getRecipeListSize() > 0);
/* 123 */     btnPlane.field_146124_l = (MCP_PlaneInfoManager.getInstance().getRecipeListSize() > 0);
/* 124 */     btnVehicle.field_146124_l = (MCH_VehicleInfoManager.getInstance().getRecipeListSize() > 0);
/* 125 */     btnTank.field_146124_l = (MCH_TankInfoManager.getInstance().getRecipeListSize() > 0);
/* 126 */     btnItem.field_146124_l = (MCH_ItemRecipe.getInstance().getRecipeListSize() > 0);
/* 127 */     list.add(btnHeli);
/* 128 */     list.add(btnPlane);
/* 129 */     list.add(btnVehicle);
/* 130 */     list.add(btnTank);
/* 131 */     list.add(btnItem);
/*     */     
/* 133 */     this.buttonCreate = new GuiButton(30, this.field_147003_i + 120, this.field_147009_r + 89, 50, 20, "Create");
/* 134 */     this.buttonPrev = new GuiButton(21, this.field_147003_i + 120, this.field_147009_r + 111, 36, 20, "<<");
/* 135 */     this.buttonNext = new GuiButton(20, this.field_147003_i + 155, this.field_147009_r + 111, 35, 20, ">>");
/* 136 */     list.add(this.buttonCreate);
/* 137 */     list.add(this.buttonPrev);
/* 138 */     list.add(this.buttonNext);
/*     */     
/* 140 */     this.buttonPrevPage = new GuiButton(51, this.field_147003_i + 210, this.field_147009_r + 210, 60, 20, "Prev Page");
/* 141 */     this.buttonNextPage = new GuiButton(50, this.field_147003_i + 270, this.field_147009_r + 210, 60, 20, "Next Page");
/* 142 */     list.add(this.buttonPrevPage);
/* 143 */     list.add(this.buttonNextPage);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     list = this.screenButtonList.get(1);
/*     */ 
/*     */     
/* 151 */     for (int y = 0, j = 0; y < 3; y++) {
/*     */       
/* 153 */       for (int x = 0; x < 2; x++, j++) {
/*     */         
/* 155 */         int px = this.field_147003_i + 30 + x * 140;
/* 156 */         int py = this.field_147009_r + 40 + y * 70;
/* 157 */         list.add(new GuiButton(40 + j, px, py, 45, 20, "Select"));
/*     */       } 
/*     */     } 
/*     */     
/* 161 */     this.listSlider = new MCH_GuiSliderVertical(0, this.field_147003_i + 360, this.field_147009_r + 20, 20, 200, "", 0.0F, 0.0F, 0.0F, 1.0F);
/* 162 */     list.add(this.listSlider);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     for (int i = 0; i < this.screenButtonList.size(); i++) {
/*     */       
/* 169 */       list = this.screenButtonList.get(i);
/* 170 */       for (int k = 0; k < list.size(); k++)
/*     */       {
/* 172 */         this.field_146292_n.add(list.get(k));
/*     */       }
/*     */     } 
/*     */     
/* 176 */     switchScreen(0);
/*     */     
/* 178 */     initModelTransform();
/* 179 */     modelRotX = 180.0F;
/* 180 */     modelRotY = 90.0F;
/* 181 */     if (MCH_ItemRecipe.getInstance().getRecipeListSize() > 0) {
/*     */       
/* 183 */       switchRecipeList((MCH_IRecipeList)MCH_ItemRecipe.getInstance());
/*     */     }
/* 185 */     else if (MCH_HeliInfoManager.getInstance().getRecipeListSize() > 0) {
/*     */       
/* 187 */       switchRecipeList((MCH_IRecipeList)MCH_HeliInfoManager.getInstance());
/*     */     }
/* 189 */     else if (MCP_PlaneInfoManager.getInstance().getRecipeListSize() > 0) {
/*     */       
/* 191 */       switchRecipeList((MCH_IRecipeList)MCP_PlaneInfoManager.getInstance());
/*     */     }
/* 193 */     else if (MCH_VehicleInfoManager.getInstance().getRecipeListSize() > 0) {
/*     */       
/* 195 */       switchRecipeList((MCH_IRecipeList)MCH_VehicleInfoManager.getInstance());
/*     */     }
/* 197 */     else if (MCH_TankInfoManager.getInstance().getRecipeListSize() > 0) {
/*     */       
/* 199 */       switchRecipeList((MCH_IRecipeList)MCH_TankInfoManager.getInstance());
/*     */     }
/*     */     else {
/*     */       
/* 203 */       switchRecipeList((MCH_IRecipeList)MCH_ItemRecipe.getInstance());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initModelTransform() {
/* 209 */     modelRotX = 0.0F;
/* 210 */     modelRotY = 0.0F;
/* 211 */     modelPosX = 0.0F;
/* 212 */     modelPosY = 0.0F;
/* 213 */     modelZoom = 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateListSliderSize(int listSize) {
/* 218 */     int s = listSize / 2;
/* 219 */     if (listSize % 2 != 0) s++; 
/* 220 */     if (s > 3) {
/*     */       
/* 222 */       this.listSlider.valueMax = (s - 3);
/*     */     }
/*     */     else {
/*     */       
/* 226 */       this.listSlider.valueMax = 0.0F;
/*     */     } 
/* 228 */     this.listSlider.setSliderValue(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void switchScreen(int id) {
/* 233 */     this.screenId = id;
/*     */     
/* 235 */     for (int i = 0; i < this.field_146292_n.size(); i++)
/*     */     {
/* 237 */       W_GuiButton.setVisible(this.field_146292_n.get(i), false);
/*     */     }
/*     */     
/* 240 */     if (id < this.screenButtonList.size()) {
/*     */       
/* 242 */       List<GuiButton> list = this.screenButtonList.get(id);
/* 243 */       for (GuiButton b : list)
/*     */       {
/* 245 */         W_GuiButton.setVisible(b, true);
/*     */       }
/*     */     } 
/* 248 */     if (getScreenId() == 0 && this.current != null && this.current.getDescMaxPage() > 1) {
/*     */       
/* 250 */       W_GuiButton.setVisible(this.buttonNextPage, true);
/* 251 */       W_GuiButton.setVisible(this.buttonPrevPage, true);
/*     */     }
/*     */     else {
/*     */       
/* 255 */       W_GuiButton.setVisible(this.buttonNextPage, false);
/* 256 */       W_GuiButton.setVisible(this.buttonPrevPage, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentRecipe(MCH_CurrentRecipe currentRecipe) {
/* 262 */     modelPosX = 0.0F;
/* 263 */     modelPosY = 0.0F;
/*     */     
/* 265 */     if (this.current == null || currentRecipe == null || !this.current.recipe.func_77571_b().func_77969_a(currentRecipe.recipe.func_77571_b()))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 272 */       this.drawFace = 0;
/*     */     }
/*     */     
/* 275 */     this.current = currentRecipe;
/*     */     
/* 277 */     if (getScreenId() == 0 && this.current != null && this.current.getDescMaxPage() > 1) {
/*     */       
/* 279 */       W_GuiButton.setVisible(this.buttonNextPage, true);
/* 280 */       W_GuiButton.setVisible(this.buttonPrevPage, true);
/*     */     }
/*     */     else {
/*     */       
/* 284 */       W_GuiButton.setVisible(this.buttonNextPage, false);
/* 285 */       W_GuiButton.setVisible(this.buttonPrevPage, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_IRecipeList getCurrentList() {
/* 291 */     return this.currentList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void switchRecipeList(MCH_IRecipeList list) {
/* 296 */     if (getCurrentList() != list) {
/*     */       
/* 298 */       setCurrentRecipe(new MCH_CurrentRecipe(list, 0));
/* 299 */       this.currentList = list;
/* 300 */       updateListSliderSize(list.getRecipeListSize());
/*     */     }
/*     */     else {
/*     */       
/* 304 */       this.listSlider.setSliderValue((this.current.index / 2));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73876_c() {
/* 310 */     super.func_73876_c();
/*     */     
/* 312 */     MCH_DraftingTableGuiContainer container = (MCH_DraftingTableGuiContainer)this.field_147002_h;
/* 313 */     this.buttonCreate.field_146124_l = false;
/* 314 */     if (!container.func_75139_a(container.outputSlotIndex).func_75216_d())
/*     */     {
/* 316 */       if (MCH_Lib.canPlayerCreateItem(this.current.recipe, this.thePlayer.field_71071_by))
/*     */       {
/* 318 */         this.buttonCreate.field_146124_l = true;
/*     */       }
/*     */     }
/*     */     
/* 322 */     if (this.thePlayer.field_71075_bZ.field_75098_d)
/*     */     {
/* 324 */       this.buttonCreate.field_146124_l = true;
/*     */     }
/*     */     
/* 327 */     if (this.buttonClickWait > 0) this.buttonClickWait--;
/*     */   
/*     */   }
/*     */   
/*     */   public void func_146281_b() {
/* 332 */     super.func_146281_b();
/* 333 */     MCH_Lib.DbgLog(this.thePlayer.field_70170_p, "MCH_DraftingTableGui.onGuiClosed", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton button) {
/* 338 */     super.func_146284_a(button);
/*     */     
/* 340 */     if (this.buttonClickWait > 0)
/*     */       return; 
/* 342 */     if (!button.field_146124_l)
/*     */       return; 
/* 344 */     this.buttonClickWait = 3;
/*     */     
/* 346 */     int index = 0;
/* 347 */     int page = this.current.getDescCurrentPage();
/*     */     
/* 349 */     switch (button.field_146127_k) {
/*     */       
/*     */       case 30:
/* 352 */         MCH_DraftingTableCreatePacket.send(this.current.recipe);
/*     */         break;
/*     */       case 21:
/* 355 */         if (this.current.isCurrentPageTexture())
/*     */         {
/* 357 */           page = 0;
/*     */         }
/* 359 */         index = this.current.index - 1;
/* 360 */         if (index < 0) index = getCurrentList().getRecipeListSize() - 1; 
/* 361 */         setCurrentRecipe(new MCH_CurrentRecipe(getCurrentList(), index));
/* 362 */         this.current.setDescCurrentPage(page);
/*     */         break;
/*     */       case 20:
/* 365 */         if (this.current.isCurrentPageTexture())
/*     */         {
/* 367 */           page = 0;
/*     */         }
/* 369 */         index = (this.current.index + 1) % getCurrentList().getRecipeListSize();
/* 370 */         setCurrentRecipe(new MCH_CurrentRecipe(getCurrentList(), index));
/* 371 */         this.current.setDescCurrentPage(page);
/*     */         break;
/*     */       case 10:
/* 374 */         initModelTransform();
/* 375 */         modelRotX = 180.0F;
/* 376 */         modelRotY = 90.0F;
/* 377 */         switchRecipeList((MCH_IRecipeList)MCH_HeliInfoManager.getInstance());
/* 378 */         switchScreen(1);
/*     */         break;
/*     */       case 11:
/* 381 */         initModelTransform();
/* 382 */         modelRotX = 90.0F;
/* 383 */         modelRotY = 180.0F;
/* 384 */         switchRecipeList((MCH_IRecipeList)MCP_PlaneInfoManager.getInstance());
/* 385 */         switchScreen(1);
/*     */         break;
/*     */       case 13:
/* 388 */         initModelTransform();
/* 389 */         modelRotX = 180.0F;
/* 390 */         modelRotY = 90.0F;
/* 391 */         switchRecipeList((MCH_IRecipeList)MCH_TankInfoManager.getInstance());
/* 392 */         switchScreen(1);
/*     */         break;
/*     */       case 12:
/* 395 */         initModelTransform();
/* 396 */         modelRotX = 180.0F;
/* 397 */         modelRotY = 90.0F;
/* 398 */         switchRecipeList((MCH_IRecipeList)MCH_VehicleInfoManager.getInstance());
/* 399 */         switchScreen(1);
/*     */         break;
/*     */       case 14:
/* 402 */         switchRecipeList((MCH_IRecipeList)MCH_ItemRecipe.getInstance());
/* 403 */         switchScreen(1);
/*     */         break;
/*     */       case 50:
/* 406 */         if (this.current != null)
/*     */         {
/* 408 */           this.current.switchNextPage();
/*     */         }
/*     */         break;
/*     */       case 51:
/* 412 */         if (this.current != null)
/*     */         {
/* 414 */           this.current.switchPrevPage();
/*     */         }
/*     */         break;
/*     */       case 40:
/*     */       case 41:
/*     */       case 42:
/*     */       case 43:
/*     */       case 44:
/*     */       case 45:
/* 423 */         index = (int)this.listSlider.getSliderValue() * 2 + button.field_146127_k - 40;
/* 424 */         if (index < getCurrentList().getRecipeListSize()) {
/*     */           
/* 426 */           setCurrentRecipe(new MCH_CurrentRecipe(getCurrentList(), index));
/* 427 */           switchScreen(0);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_73869_a(char par1, int keycode) {
/* 435 */     if (keycode == 1 || keycode == W_KeyBinding.getKeyCode((Minecraft.func_71410_x()).field_71474_y.field_151445_Q))
/*     */     {
/* 437 */       if (getScreenId() == 0) {
/*     */         
/* 439 */         this.field_146297_k.field_71439_g.func_71053_j();
/*     */       }
/*     */       else {
/*     */         
/* 443 */         switchScreen(0);
/*     */       } 
/*     */     }
/* 446 */     if (getScreenId() == 0) {
/*     */       
/* 448 */       if (keycode == 205)
/*     */       {
/* 450 */         func_146284_a(this.buttonNext);
/*     */       }
/* 452 */       if (keycode == 203)
/*     */       {
/* 454 */         func_146284_a(this.buttonPrev);
/*     */       }
/*     */     }
/* 457 */     else if (getScreenId() == 1) {
/*     */       
/* 459 */       if (keycode == 200)
/*     */       {
/* 461 */         this.listSlider.scrollDown(1.0F);
/*     */       }
/* 463 */       if (keycode == 208)
/*     */       {
/* 465 */         this.listSlider.scrollUp(1.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146979_b(int mx, int my) {
/* 472 */     super.func_146979_b(mx, my);
/*     */     
/* 474 */     float z = this.field_73735_i;
/* 475 */     this.field_73735_i = 0.0F;
/*     */     
/* 477 */     GL11.glEnable(3042);
/*     */     
/* 479 */     if (getScreenId() == 0) {
/*     */       
/* 481 */       ArrayList<String> list = new ArrayList<String>();
/* 482 */       if (this.current != null)
/*     */       {
/* 484 */         if (this.current.isCurrentPageTexture()) {
/*     */           
/* 486 */           GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
/* 487 */           this.field_146297_k.func_110434_K().func_110577_a(this.current.getCurrentPageTexture());
/* 488 */           drawTexturedModalRect(210, 20, 170, 190, 0, 0, 340, 380);
/*     */         }
/* 490 */         else if (this.current.isCurrentPageAcInfo()) {
/*     */           
/* 492 */           int COLOR = -9491968;
/* 493 */           for (int i = 0; i < this.current.infoItem.size(); i++)
/*     */           {
/* 495 */             this.field_146289_q.func_78276_b(this.current.infoItem.get(i), 210, 40 + 10 * i, -9491968);
/* 496 */             String data = this.current.infoData.get(i);
/* 497 */             if (!data.isEmpty())
/*     */             {
/* 499 */               this.field_146289_q.func_78276_b(data, 280, 40 + 10 * i, -9491968);
/*     */             }
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 505 */           W_McClient.MOD_bindTexture("textures/gui/drafting_table.png");
/* 506 */           drawTexturedModalRect(340, 215, 45, 15, 400, 60, 90, 30);
/* 507 */           if (mx >= 350 && mx <= 400 && my >= 214 && my <= 230) {
/*     */             
/* 509 */             boolean lb = Mouse.isButtonDown(0);
/* 510 */             boolean rb = Mouse.isButtonDown(1);
/* 511 */             boolean mb = Mouse.isButtonDown(2);
/* 512 */             list.add((lb ? (String)EnumChatFormatting.AQUA : "") + "Mouse left button drag : Rotation model");
/* 513 */             list.add((rb ? (String)EnumChatFormatting.AQUA : "") + "Mouse right button drag : Zoom model");
/* 514 */             list.add((mb ? (String)EnumChatFormatting.AQUA : "") + "Mouse middle button drag : Move model");
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 519 */       drawString(this.current.displayName, 120, 20, -1);
/* 520 */       drawItemRecipe(this.current.recipe, 121, 34);
/* 521 */       if (list.size() > 0)
/*     */       {
/* 523 */         drawHoveringText(list, mx - 30, my - 0, this.field_146289_q);
/*     */       }
/*     */     } 
/* 526 */     if (getScreenId() == 1) {
/*     */       
/* 528 */       int index = 2 * (int)this.listSlider.getSliderValue();
/*     */       
/* 530 */       int i = 0; int r;
/* 531 */       for (r = 0; r < 3; r++) {
/*     */         
/* 533 */         for (int c = 0; c < 2; c++) {
/*     */           
/* 535 */           if (index + i < getCurrentList().getRecipeListSize()) {
/*     */             
/* 537 */             int rx = 110 + 140 * c;
/* 538 */             int ry = 20 + 70 * r;
/* 539 */             String s = getCurrentList().getRecipe(index + i).func_77571_b().func_82833_r();
/* 540 */             drawCenteredString(s, rx, ry, -1);
/*     */           } 
/* 542 */           i++;
/*     */         } 
/*     */       } 
/*     */       
/* 546 */       W_McClient.MOD_bindTexture("textures/gui/drafting_table.png");
/* 547 */       i = 0;
/* 548 */       for (r = 0; r < 3; r++) {
/*     */         
/* 550 */         for (int c = 0; c < 2; c++) {
/*     */           
/* 552 */           if (index + i < getCurrentList().getRecipeListSize()) {
/*     */             
/* 554 */             int rx = 80 + 140 * c - 1;
/* 555 */             int ry = 30 + 70 * r - 1;
/* 556 */             func_73729_b(rx, ry, 400, 0, 75, 54);
/*     */           } 
/* 558 */           i++;
/*     */         } 
/*     */       } 
/*     */       
/* 562 */       i = 0;
/* 563 */       for (r = 0; r < 3; r++) {
/*     */         
/* 565 */         for (int c = 0; c < 2; c++) {
/*     */           
/* 567 */           if (index + i < getCurrentList().getRecipeListSize()) {
/*     */             
/* 569 */             int rx = 80 + 140 * c;
/* 570 */             int ry = 30 + 70 * r;
/* 571 */             drawItemRecipe(getCurrentList().getRecipe(index + i), rx, ry);
/*     */           } 
/* 573 */           i++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146984_a(Slot p_146984_1_, int p_146984_2_, int p_146984_3_, int p_146984_4_) {
/* 581 */     if (getScreenId() != 1)
/*     */     {
/* 583 */       super.func_146984_a(p_146984_1_, p_146984_2_, p_146984_3_, p_146984_4_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int getScreenId() {
/* 589 */     return this.screenId;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawItemRecipe(IRecipe recipe, int x, int y) {
/* 594 */     if (recipe == null)
/* 595 */       return;  if (recipe.func_77571_b() == null)
/* 596 */       return;  if (recipe.func_77571_b().func_77973_b() == null)
/*     */       return; 
/* 598 */     if (recipe instanceof ShapedRecipes) {
/*     */       
/* 600 */       ShapedRecipes rcp = (ShapedRecipes)recipe;
/* 601 */       int RH = rcp.field_77577_c;
/* 602 */       for (int h = 0; h < RH; h++) {
/*     */         
/* 604 */         for (int w = 0; w < rcp.field_77576_b; w++)
/*     */         {
/* 606 */           int IDX = h * RH + w;
/* 607 */           if (IDX < rcp.field_77574_d.length)
/*     */           {
/* 609 */             drawItemStack(rcp.field_77574_d[IDX], x + w * 18, y + h * 18);
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/* 614 */     } else if (recipe instanceof ShapelessRecipes) {
/*     */       
/* 616 */       ShapelessRecipes rcp = (ShapelessRecipes)recipe;
/* 617 */       for (int i = 0; i < rcp.field_77579_b.size(); i++)
/*     */       {
/* 619 */         drawItemStack(rcp.field_77579_b.get(i), x + i % 3 * 18, y + i / 3 * 18);
/*     */       }
/*     */     } 
/* 622 */     drawItemStack(recipe.func_77571_b(), x + 54 + 3, y + 18);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146274_d() {
/* 627 */     super.func_146274_d();
/*     */     
/* 629 */     int dx = Mouse.getEventDX();
/* 630 */     int dy = Mouse.getEventDY();
/* 631 */     if (getScreenId() == 0 && Mouse.getX() > this.field_146297_k.field_71443_c / 2) {
/*     */       
/* 633 */       if (Mouse.isButtonDown(0) && (dx != 0 || dy != 0)) {
/*     */         
/* 635 */         modelRotX = (float)(modelRotX - dy / 2.0D);
/* 636 */         modelRotY = (float)(modelRotY - dx / 2.0D);
/* 637 */         if (modelRotX > 360.0F) modelRotX -= 360.0F; 
/* 638 */         if (modelRotX < -360.0F) modelRotX += 360.0F; 
/* 639 */         if (modelRotY > 360.0F) modelRotY -= 360.0F; 
/* 640 */         if (modelRotY < -360.0F) modelRotY += 360.0F; 
/*     */       } 
/* 642 */       if (Mouse.isButtonDown(2) && (dx != 0 || dy != 0)) {
/*     */         
/* 644 */         modelPosX = (float)(modelPosX + dx / 2.0D);
/* 645 */         modelPosY = (float)(modelPosY - dy / 2.0D);
/* 646 */         if (modelRotX > 1000.0F) modelRotX = 1000.0F; 
/* 647 */         if (modelRotX < -1000.0F) modelRotX = -1000.0F; 
/* 648 */         if (modelRotY > 1000.0F) modelRotY = 1000.0F; 
/* 649 */         if (modelRotY < -1000.0F) modelRotY = -1000.0F; 
/*     */       } 
/* 651 */       if (Mouse.isButtonDown(1) && dy != 0) {
/*     */         
/* 653 */         modelZoom = (float)(modelZoom + dy / 100.0D);
/* 654 */         if (modelZoom < 0.1D)
/*     */         {
/* 656 */           modelZoom = 0.1F;
/*     */         }
/* 658 */         if (modelZoom > 10.0F)
/*     */         {
/* 660 */           modelZoom = 10.0F;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 665 */     int wheel = Mouse.getEventDWheel();
/* 666 */     if (wheel != 0)
/*     */     {
/* 668 */       if (getScreenId() == 1) {
/*     */         
/* 670 */         if (wheel > 0)
/*     */         {
/* 672 */           this.listSlider.scrollDown(1.0F);
/*     */         }
/* 674 */         else if (wheel < 0)
/*     */         {
/* 676 */           this.listSlider.scrollUp(1.0F);
/*     */         }
/*     */       
/* 679 */       } else if (getScreenId() == 0) {
/*     */         
/* 681 */         if (wheel > 0) {
/*     */           
/* 683 */           func_146284_a(this.buttonPrev);
/*     */         }
/* 685 */         else if (wheel < 0) {
/*     */           
/* 687 */           func_146284_a(this.buttonNext);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 695 */     GL11.glEnable(3042);
/* 696 */     GL11.glBlendFunc(770, 771);
/* 697 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 698 */     if (getScreenId() == 0) {
/*     */       
/* 700 */       super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 705 */       List inventory = this.field_147002_h.field_75151_b;
/* 706 */       this.field_147002_h.field_75151_b = new ArrayList();
/*     */       
/* 708 */       super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */       
/* 710 */       this.field_147002_h.field_75151_b = inventory;
/*     */     } 
/*     */     
/* 713 */     if (getScreenId() == 0 && this.current.isCurrentPageModel()) {
/*     */       
/* 715 */       RenderHelper.func_74520_c();
/* 716 */       drawModel(partialTicks);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawModel(float partialTicks) {
/* 722 */     W_ModelCustom model = this.current.getModel();
/*     */     
/* 724 */     double scl = 162.0D / ((MathHelper.func_76135_e(model.size) < 0.01D) ? 0.01D : model.size);
/*     */     
/* 726 */     this.field_146297_k.func_110434_K().func_110577_a(this.current.getModelTexture());
/*     */ 
/*     */     
/* 729 */     GL11.glPushMatrix();
/* 730 */     double cx = (model.maxX - model.minX) * 0.5D + model.minX;
/* 731 */     double cy = (model.maxY - model.minY) * 0.5D + model.minY;
/* 732 */     double cz = (model.maxZ - model.minZ) * 0.5D + model.minZ;
/* 733 */     if (this.current.modelRot == 0) {
/*     */       
/* 735 */       GL11.glTranslated(cx * scl, cz * scl, 0.0D);
/*     */     }
/*     */     else {
/*     */       
/* 739 */       GL11.glTranslated(cz * scl, cy * scl, 0.0D);
/*     */     } 
/* 741 */     GL11.glTranslated(((this.field_147003_i + 300) + modelPosX), ((this.field_147009_r + 110) + modelPosY), 550.0D);
/*     */     
/* 743 */     GL11.glRotated(modelRotX, 1.0D, 0.0D, 0.0D);
/* 744 */     GL11.glRotated(modelRotY, 0.0D, 1.0D, 0.0D);
/*     */     
/* 746 */     GL11.glScaled(scl * modelZoom, scl * modelZoom, -scl * modelZoom);
/*     */     
/* 748 */     GL11.glDisable(32826);
/* 749 */     GL11.glDisable(2896);
/* 750 */     GL11.glEnable(3008);
/*     */     
/* 752 */     GL11.glEnable(3042);
/*     */     
/* 754 */     int faceNum = model.getFaceNum();
/*     */ 
/*     */     
/* 757 */     if (this.drawFace < faceNum * 2) {
/*     */       
/* 759 */       GL11.glColor4d(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D, 1.0D);
/* 760 */       GL11.glDisable(3553);
/* 761 */       GL11.glPolygonMode(1032, 6913);
/*     */       
/* 763 */       float lw = GL11.glGetFloat(2849);
/* 764 */       GL11.glLineWidth(1.0F);
/*     */       
/* 766 */       model.renderAll(this.drawFace - faceNum, this.drawFace);
/*     */       
/* 768 */       MCH_RenderAircraft.renderCrawlerTrack(null, this.current.getAcInfo(), partialTicks);
/*     */       
/* 770 */       GL11.glLineWidth(lw);
/*     */       
/* 772 */       GL11.glPolygonMode(1032, 6914);
/* 773 */       GL11.glEnable(3553);
/*     */     } 
/* 775 */     if (this.drawFace >= faceNum) {
/*     */       
/* 777 */       GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
/*     */       
/* 779 */       model.renderAll(0, this.drawFace - faceNum);
/*     */       
/* 781 */       MCH_RenderAircraft.renderCrawlerTrack(null, this.current.getAcInfo(), partialTicks);
/*     */     } 
/* 783 */     GL11.glEnable(32826);
/* 784 */     GL11.glEnable(2896);
/*     */     
/* 786 */     GL11.glPopMatrix();
/*     */ 
/*     */     
/* 789 */     if (this.drawFace < 10000000)
/*     */     {
/* 791 */       this.drawFace = (int)(this.drawFace + 20.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float var1, int var2, int var3) {
/* 797 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
/* 798 */     this.scaleFactor = w_ScaledResolution.func_78325_e();
/*     */     
/* 800 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 802 */     float z = this.field_73735_i;
/* 803 */     this.field_73735_i = 0.0F;
/* 804 */     W_McClient.MOD_bindTexture("textures/gui/drafting_table.png");
/*     */     
/* 806 */     if (getScreenId() == 0)
/*     */     {
/* 808 */       func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */     }
/* 810 */     if (getScreenId() == 1) {
/*     */       
/* 812 */       func_73729_b(this.field_147003_i, this.field_147009_r, 0, this.field_147000_g, this.field_146999_f, this.field_147000_g);
/*     */ 
/*     */       
/* 815 */       List<GuiButton> list = this.screenButtonList.get(1);
/* 816 */       int index = (int)this.listSlider.getSliderValue() * 2;
/* 817 */       for (int i = 0; i < 6; i++)
/*     */       {
/* 819 */         W_GuiButton.setVisible(list.get(i), (index + i < getCurrentList().getRecipeListSize()));
/*     */       }
/*     */     } 
/*     */     
/* 823 */     this.field_73735_i = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73729_b(int par1, int par2, int par3, int par4, int par5, int par6) {
/* 828 */     float w = 0.001953125F;
/* 829 */     float h = 0.001953125F;
/* 830 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 831 */     tessellator.func_78382_b();
/* 832 */     tessellator.func_78374_a((par1 + 0), (par2 + par6), this.field_73735_i, ((par3 + 0) * w), ((par4 + par6) * h));
/* 833 */     tessellator.func_78374_a((par1 + par5), (par2 + par6), this.field_73735_i, ((par3 + par5) * w), ((par4 + par6) * h));
/* 834 */     tessellator.func_78374_a((par1 + par5), (par2 + 0), this.field_73735_i, ((par3 + par5) * w), ((par4 + 0) * h));
/* 835 */     tessellator.func_78374_a((par1 + 0), (par2 + 0), this.field_73735_i, ((par3 + 0) * w), ((par4 + 0) * h));
/* 836 */     tessellator.func_78381_a();
/*     */   }
/*     */   
/*     */   public void drawTexturedModalRect(int dx, int dy, int dw, int dh, int u, int v, int tw, int th) {
/* 840 */     float w = 0.001953125F;
/* 841 */     float h = 0.001953125F;
/* 842 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 843 */     tessellator.func_78382_b();
/* 844 */     tessellator.func_78374_a((dx + 0), (dy + dh), this.field_73735_i, ((u + 0) * w), ((v + th) * h));
/* 845 */     tessellator.func_78374_a((dx + dw), (dy + dh), this.field_73735_i, ((u + tw) * w), ((v + th) * h));
/* 846 */     tessellator.func_78374_a((dx + dw), (dy + 0), this.field_73735_i, ((u + tw) * w), ((v + 0) * h));
/* 847 */     tessellator.func_78374_a((dx + 0), (dy + 0), this.field_73735_i, ((u + 0) * w), ((v + 0) * h));
/* 848 */     tessellator.func_78381_a();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\block\MCH_DraftingTableGui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */