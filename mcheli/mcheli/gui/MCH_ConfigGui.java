/*     */ package mcheli.mcheli.gui;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_ClientCommonTickHandler;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_PacketNotifyInfoReloaded;
/*     */ import mcheli.gui.MCH_ConfigGuiContainer;
/*     */ import mcheli.gui.MCH_GuiList;
/*     */ import mcheli.gui.MCH_GuiListItem;
/*     */ import mcheli.gui.MCH_GuiListItemKeyBind;
/*     */ import mcheli.gui.MCH_GuiOnOffButton;
/*     */ import mcheli.gui.MCH_GuiSlider;
/*     */ import mcheli.multiplay.MCH_GuiTargetMarker;
/*     */ import mcheli.weapon.MCH_WeaponInfoManager;
/*     */ import mcheli.wrapper.W_GuiButton;
/*     */ import mcheli.wrapper.W_GuiContainer;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_ScaledResolution;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_ConfigGui
/*     */   extends W_GuiContainer
/*     */ {
/*     */   private final EntityPlayer thePlayer;
/*     */   private int scaleFactor;
/*     */   private MCH_GuiOnOffButton buttonMouseInv;
/*     */   private MCH_GuiOnOffButton buttonStickModeHeli;
/*     */   private MCH_GuiOnOffButton buttonStickModePlane;
/*     */   private MCH_GuiOnOffButton buttonHideKeyBind;
/*     */   private MCH_GuiOnOffButton buttonShowHUDTP;
/*     */   private MCH_GuiOnOffButton buttonSmoothShading;
/*     */   private MCH_GuiOnOffButton buttonShowEntityMarker;
/*     */   private MCH_GuiOnOffButton buttonMarkThroughWall;
/*     */   private MCH_GuiOnOffButton buttonReplaceCamera;
/*     */   private MCH_GuiOnOffButton buttonNewExplosion;
/*     */   private MCH_GuiSlider sliderEntityMarkerSize;
/*     */   private MCH_GuiSlider sliderBlockMarkerSize;
/*     */   private MCH_GuiSlider sliderSensitivity;
/*     */   private MCH_GuiSlider[] sliderHitMark;
/*     */   private MCH_GuiOnOffButton buttonTestMode;
/*     */   private MCH_GuiOnOffButton buttonThrottleHeli;
/*     */   private MCH_GuiOnOffButton buttonThrottlePlane;
/*     */   private MCH_GuiOnOffButton buttonThrottleTank;
/*     */   private MCH_GuiOnOffButton buttonFlightSimMode;
/*     */   private MCH_GuiOnOffButton buttonSwitchWeaponWheel;
/*     */   private W_GuiButton buttonReloadAircraftInfo;
/*     */   private W_GuiButton buttonReloadWeaponInfo;
/*     */   private W_GuiButton buttonReloadAllHUD;
/*     */   public List<W_GuiButton> listControlButtons;
/*     */   public List<W_GuiButton> listRenderButtons;
/*     */   public List<W_GuiButton> listKeyBindingButtons;
/*     */   public List<W_GuiButton> listDevelopButtons;
/*     */   public MCH_GuiList keyBindingList;
/*     */   public int waitKeyButtonId;
/*     */   public int waitKeyAcceptCount;
/*     */   public static final int BUTTON_RENDER = 50;
/*     */   public static final int BUTTON_KEY_BINDING = 51;
/*     */   public static final int BUTTON_PREV_CONTROL = 52;
/*     */   public static final int BUTTON_DEVELOP = 55;
/*     */   public static final int BUTTON_KEY_LIST = 53;
/*     */   public static final int BUTTON_KEY_RESET_ALL = 54;
/*     */   public static final int BUTTON_KEY_LIST_BASE = 200;
/*     */   public static final int BUTTON_KEY_RESET_BASE = 300;
/*     */   public static final int BUTTON_DEV_RELOAD_AC = 400;
/*     */   public static final int BUTTON_DEV_RELOAD_WEAPON = 401;
/*     */   public static final int BUTTON_DEV_RELOAD_HUD = 402;
/*     */   public static final int BUTTON_SAVE_CLOSE = 100;
/*     */   public static final int BUTTON_CANCEL = 101;
/*  83 */   public int currentScreenId = 0;
/*     */   
/*     */   public static final int SCREEN_CONTROLS = 0;
/*     */   public static final int SCREEN_RENDER = 1;
/*     */   public static final int SCREEN_KEY_BIND = 2;
/*     */   public static final int SCREEN_DEVELOP = 3;
/*  89 */   private int ignoreButtonCounter = 0;
/*     */ 
/*     */   
/*     */   public MCH_ConfigGui(EntityPlayer player) {
/*  93 */     super((Container)new MCH_ConfigGuiContainer(player));
/*  94 */     this.thePlayer = player;
/*  95 */     this.field_146999_f = 330;
/*  96 */     this.field_147000_g = 200;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/* 101 */     super.func_73866_w_();
/* 102 */     this.field_146292_n.clear();
/*     */     
/* 104 */     int x1 = this.field_147003_i + 10;
/* 105 */     int x2 = this.field_147003_i + 10 + 150 + 10;
/* 106 */     int y = this.field_147009_r;
/*     */     
/* 108 */     int DY = 25;
/*     */ 
/*     */ 
/*     */     
/* 112 */     this.listControlButtons = new ArrayList<W_GuiButton>();
/*     */     
/* 114 */     this.buttonMouseInv = new MCH_GuiOnOffButton(0, x1, y + 25, 150, 20, "Invert Mouse : ");
/* 115 */     this.sliderSensitivity = new MCH_GuiSlider(0, x1, y + 50, 150, 20, "Sensitivity : %.1f", 0.0F, 0.0F, 30.0F, 0.1F);
/*     */     
/* 117 */     this.buttonFlightSimMode = new MCH_GuiOnOffButton(0, x1, y + 75, 150, 20, "Mouse Flight Sim Mode : ");
/* 118 */     this.buttonSwitchWeaponWheel = new MCH_GuiOnOffButton(0, x1, y + 100, 150, 20, "Switch Weapon Wheel : ");
/* 119 */     this.listControlButtons.add(new W_GuiButton(50, x1, y + 125, 150, 20, "Render Settings >>"));
/* 120 */     this.listControlButtons.add(new W_GuiButton(51, x1, y + 150, 150, 20, "Key Binding >>"));
/* 121 */     this.listControlButtons.add(new W_GuiButton(55, x2, y + 150, 150, 20, "Development >>"));
/* 122 */     this.buttonTestMode = new MCH_GuiOnOffButton(0, x1, y + 175, 150, 20, "Test Mode : ");
/*     */     
/* 124 */     this.buttonStickModeHeli = new MCH_GuiOnOffButton(0, x2, y + 25, 150, 20, "Stick Mode Heli : ");
/* 125 */     this.buttonStickModePlane = new MCH_GuiOnOffButton(0, x2, y + 50, 150, 20, "Stick Mode Plane : ");
/* 126 */     this.buttonThrottleHeli = new MCH_GuiOnOffButton(0, x2, y + 75, 150, 20, "Throttle Down Heli : ");
/* 127 */     this.buttonThrottlePlane = new MCH_GuiOnOffButton(0, x2, y + 100, 150, 20, "Throttle Down Plane : ");
/* 128 */     this.buttonThrottleTank = new MCH_GuiOnOffButton(0, x2, y + 125, 150, 20, "Throttle Down Tank : ");
/*     */     
/* 130 */     this.listControlButtons.add(this.buttonMouseInv);
/* 131 */     this.listControlButtons.add(this.buttonStickModeHeli);
/* 132 */     this.listControlButtons.add(this.buttonStickModePlane);
/* 133 */     this.listControlButtons.add(this.sliderSensitivity);
/* 134 */     this.listControlButtons.add(this.buttonThrottleHeli);
/* 135 */     this.listControlButtons.add(this.buttonThrottlePlane);
/* 136 */     this.listControlButtons.add(this.buttonThrottleTank);
/* 137 */     this.listControlButtons.add(this.buttonTestMode);
/* 138 */     this.listControlButtons.add(this.buttonFlightSimMode);
/* 139 */     this.listControlButtons.add(this.buttonSwitchWeaponWheel);
/*     */     
/* 141 */     for (W_GuiButton w_GuiButton : this.listControlButtons)
/*     */     {
/* 143 */       this.field_146292_n.add(w_GuiButton);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.listRenderButtons = new ArrayList<W_GuiButton>();
/*     */     
/* 150 */     this.buttonShowHUDTP = new MCH_GuiOnOffButton(0, x1, y + 25, 150, 20, "Show HUD Third Person : ");
/* 151 */     this.buttonHideKeyBind = new MCH_GuiOnOffButton(0, x1, y + 50, 150, 20, "Hide Key Binding : ");
/*     */     
/* 153 */     this.sliderHitMark = new MCH_GuiSlider[] { new MCH_GuiSlider(0, x1 + 0, y + 125, 75, 20, "Alpha:%.0f", 0.0F, 0.0F, 255.0F, 16.0F), new MCH_GuiSlider(0, x1 + 75, y + 75, 75, 20, "Red:%.0f", 0.0F, 0.0F, 255.0F, 16.0F), new MCH_GuiSlider(0, x1 + 75, y + 100, 75, 20, "Green:%.0f", 0.0F, 0.0F, 255.0F, 16.0F), new MCH_GuiSlider(0, x1 + 75, y + 125, 75, 20, "Blue:%.0f", 0.0F, 0.0F, 255.0F, 16.0F) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     this.buttonReplaceCamera = new MCH_GuiOnOffButton(0, x1, y + 150, 150, 20, "Change Camera Pos : ");
/* 161 */     this.listRenderButtons.add(new W_GuiButton(52, x1, y + 175, 90, 20, "Controls <<"));
/*     */     
/* 163 */     this.buttonSmoothShading = new MCH_GuiOnOffButton(0, x2, y + 25, 150, 20, "Smooth Shading : ");
/* 164 */     this.buttonShowEntityMarker = new MCH_GuiOnOffButton(0, x2, y + 50, 150, 20, "Show Entity Maker : ");
/* 165 */     this.sliderEntityMarkerSize = new MCH_GuiSlider(0, x2 + 30, y + 75, 120, 20, "Entity Marker Size:%.0f", 10.0F, 0.0F, 30.0F, 1.0F);
/* 166 */     this.sliderBlockMarkerSize = new MCH_GuiSlider(0, x2 + 60, y + 100, 90, 20, "Block Marker Size:%.0f", 10.0F, 0.0F, 20.0F, 1.0F);
/* 167 */     this.buttonMarkThroughWall = new MCH_GuiOnOffButton(0, x2 + 30, y + 100, 120, 20, "Mark Through Wall : ");
/* 168 */     this.buttonNewExplosion = new MCH_GuiOnOffButton(0, x2, y + 150, 150, 20, "Default Explosion : ");
/*     */     
/* 170 */     this.listRenderButtons.add(this.buttonShowHUDTP);
/* 171 */     for (int i = 0; i < this.sliderHitMark.length; ) { this.listRenderButtons.add(this.sliderHitMark[i]); i++; }
/* 172 */      this.listRenderButtons.add(this.buttonSmoothShading);
/* 173 */     this.listRenderButtons.add(this.buttonHideKeyBind);
/* 174 */     this.listRenderButtons.add(this.buttonShowEntityMarker);
/*     */     
/* 176 */     this.listRenderButtons.add(this.buttonReplaceCamera);
/* 177 */     this.listRenderButtons.add(this.buttonNewExplosion);
/* 178 */     this.listRenderButtons.add(this.sliderEntityMarkerSize);
/* 179 */     this.listRenderButtons.add(this.sliderBlockMarkerSize);
/*     */     
/* 181 */     for (W_GuiButton w_GuiButton : this.listRenderButtons)
/*     */     {
/* 183 */       this.field_146292_n.add(w_GuiButton);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     this.listKeyBindingButtons = new ArrayList<W_GuiButton>();
/*     */     
/* 191 */     this.waitKeyButtonId = 0;
/* 192 */     this.waitKeyAcceptCount = 0;
/*     */     
/* 194 */     this.keyBindingList = new MCH_GuiList(53, 7, x1, y + 25 - 2, 310, 150, "");
/* 195 */     this.listKeyBindingButtons.add(this.keyBindingList);
/*     */     
/* 197 */     this.listKeyBindingButtons.add(new W_GuiButton(52, x1, y + 175, 90, 20, "Controls <<"));
/* 198 */     this.listKeyBindingButtons.add(new W_GuiButton(54, x1 + 90, y + 175, 60, 20, "Reset All"));
/*     */     
/* 200 */     int id = 200;
/* 201 */     int idr = 300;
/* 202 */     MCH_GuiListItemKeyBind[] listKeyBindItems = { new MCH_GuiListItemKeyBind(200, 300, x1, "Up", MCH_Config.KeyUp), new MCH_GuiListItemKeyBind(201, 301, x1, "Down", MCH_Config.KeyDown), new MCH_GuiListItemKeyBind(202, 302, x1, "Right", MCH_Config.KeyRight), new MCH_GuiListItemKeyBind(203, 303, x1, "Left", MCH_Config.KeyLeft), new MCH_GuiListItemKeyBind(204, 304, x1, "Switch Gunner", MCH_Config.KeySwitchMode), new MCH_GuiListItemKeyBind(205, 305, x1, "Switch Hovering", MCH_Config.KeySwitchHovering), new MCH_GuiListItemKeyBind(206, 306, x1, "Switch Weapon1", MCH_Config.KeySwitchWeapon1), new MCH_GuiListItemKeyBind(207, 307, x1, "Switch Weapon2", MCH_Config.KeySwitchWeapon2), new MCH_GuiListItemKeyBind(208, 308, x1, "Switch Weapon Mode", MCH_Config.KeySwWeaponMode), new MCH_GuiListItemKeyBind(209, 309, x1, "Zoom / Fold Wing", MCH_Config.KeyZoom), new MCH_GuiListItemKeyBind(210, 310, x1, "Camera Mode", MCH_Config.KeyCameraMode), new MCH_GuiListItemKeyBind(211, 311, x1, "Unmount Mobs", MCH_Config.KeyUnmount), new MCH_GuiListItemKeyBind(212, 312, x1, "Flare", MCH_Config.KeyFlare), new MCH_GuiListItemKeyBind(213, 313, x1, "Vtol / Drop / Fold Blade", MCH_Config.KeyExtra), new MCH_GuiListItemKeyBind(214, 314, x1, "Third Person Distance Up", MCH_Config.KeyCameraDistUp), new MCH_GuiListItemKeyBind(215, 315, x1, "Third Person Distance Down", MCH_Config.KeyCameraDistDown), new MCH_GuiListItemKeyBind(216, 316, x1, "Switch Free Look", MCH_Config.KeyFreeLook), new MCH_GuiListItemKeyBind(217, 317, x1, "Open GUI", MCH_Config.KeyGUI), new MCH_GuiListItemKeyBind(218, 318, x1, "Gear Up Down", MCH_Config.KeyGearUpDown), new MCH_GuiListItemKeyBind(219, 319, x1, "Put entity in the rack", MCH_Config.KeyPutToRack), new MCH_GuiListItemKeyBind(220, 320, x1, "Drop entity from the rack", MCH_Config.KeyDownFromRack), new MCH_GuiListItemKeyBind(221, 321, x1, "[MP]Score board", MCH_Config.KeyScoreboard), new MCH_GuiListItemKeyBind(222, 322, x1, "[MP][OP]Multiplay manager", MCH_Config.KeyMultiplayManager) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     for (MCH_GuiListItemKeyBind item : listKeyBindItems)
/*     */     {
/* 229 */       this.keyBindingList.addItem((MCH_GuiListItem)item);
/*     */     }
/*     */     
/* 232 */     for (W_GuiButton w_GuiButton : this.listKeyBindingButtons)
/*     */     {
/* 234 */       this.field_146292_n.add(w_GuiButton);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 239 */     this.listDevelopButtons = new ArrayList<W_GuiButton>();
/*     */     
/* 241 */     if (Minecraft.func_71410_x().func_71356_B()) {
/*     */       
/* 243 */       this.buttonReloadAircraftInfo = new W_GuiButton(400, x1, y + 50, 150, 20, "Reload aircraft setting");
/* 244 */       this.buttonReloadWeaponInfo = new W_GuiButton(401, x1, y + 75, 150, 20, "Reload All Weapons");
/* 245 */       this.buttonReloadAllHUD = new W_GuiButton(402, x1, y + 100, 150, 20, "Reload All HUD");
/* 246 */       this.listDevelopButtons.add(this.buttonReloadAircraftInfo);
/* 247 */       this.listDevelopButtons.add(this.buttonReloadWeaponInfo);
/* 248 */       this.listDevelopButtons.add(this.buttonReloadAllHUD);
/*     */     } 
/*     */     
/* 251 */     this.listDevelopButtons.add(new W_GuiButton(52, x1, y + 175, 90, 20, "Controls <<"));
/*     */     
/* 253 */     for (W_GuiButton w_GuiButton : this.listDevelopButtons)
/*     */     {
/* 255 */       this.field_146292_n.add(w_GuiButton);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 260 */     this.field_146292_n.add(new GuiButton(100, x2, y + 175, 80, 20, "Save & Close"));
/* 261 */     this.field_146292_n.add(new GuiButton(101, x2 + 90, y + 175, 60, 20, "Cancel"));
/*     */     
/* 263 */     switchScreen(0);
/* 264 */     applySwitchScreen();
/*     */     
/* 266 */     getAllStatusFromConfig();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canButtonClick() {
/* 271 */     return (this.ignoreButtonCounter <= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void getAllStatusFromConfig() {
/* 276 */     this.buttonMouseInv.setOnOff(MCH_Config.InvertMouse.prmBool);
/* 277 */     this.buttonStickModeHeli.setOnOff(MCH_Config.MouseControlStickModeHeli.prmBool);
/* 278 */     this.buttonStickModePlane.setOnOff(MCH_Config.MouseControlStickModePlane.prmBool);
/* 279 */     this.sliderSensitivity.setSliderValue((float)MCH_Config.MouseSensitivity.prmDouble);
/* 280 */     this.buttonShowHUDTP.setOnOff(MCH_Config.DisplayHUDThirdPerson.prmBool);
/* 281 */     this.buttonSmoothShading.setOnOff(MCH_Config.SmoothShading.prmBool);
/* 282 */     this.buttonHideKeyBind.setOnOff(MCH_Config.HideKeybind.prmBool);
/* 283 */     this.buttonShowEntityMarker.setOnOff(MCH_Config.DisplayEntityMarker.prmBool);
/* 284 */     this.buttonMarkThroughWall.setOnOff(MCH_Config.DisplayMarkThroughWall.prmBool);
/* 285 */     this.sliderEntityMarkerSize.setSliderValue((float)MCH_Config.EntityMarkerSize.prmDouble);
/* 286 */     this.sliderBlockMarkerSize.setSliderValue((float)MCH_Config.BlockMarkerSize.prmDouble);
/* 287 */     this.buttonReplaceCamera.setOnOff(MCH_Config.ReplaceRenderViewEntity.prmBool);
/* 288 */     this.buttonNewExplosion.setOnOff(MCH_Config.DefaultExplosionParticle.prmBool);
/* 289 */     this.sliderHitMark[0].setSliderValue(MCH_Config.hitMarkColorAlpha * 255.0F);
/* 290 */     this.sliderHitMark[1].setSliderValue((MCH_Config.hitMarkColorRGB >> 16 & 0xFF));
/* 291 */     this.sliderHitMark[2].setSliderValue((MCH_Config.hitMarkColorRGB >> 8 & 0xFF));
/* 292 */     this.sliderHitMark[3].setSliderValue((MCH_Config.hitMarkColorRGB >> 0 & 0xFF));
/* 293 */     this.buttonThrottleHeli.setOnOff(MCH_Config.AutoThrottleDownHeli.prmBool);
/* 294 */     this.buttonThrottlePlane.setOnOff(MCH_Config.AutoThrottleDownPlane.prmBool);
/* 295 */     this.buttonThrottleTank.setOnOff(MCH_Config.AutoThrottleDownTank.prmBool);
/* 296 */     this.buttonTestMode.setOnOff(MCH_Config.TestMode.prmBool);
/* 297 */     this.buttonFlightSimMode.setOnOff(MCH_Config.MouseControlFlightSimMode.prmBool);
/* 298 */     this.buttonSwitchWeaponWheel.setOnOff(MCH_Config.SwitchWeaponWithMouseWheel.prmBool);
/*     */   }
/*     */   
/*     */   public void saveAndApplyConfig() {
/* 302 */     int n = 0;
/* 303 */     MCH_Config.InvertMouse.setPrm(this.buttonMouseInv.getOnOff());
/* 304 */     MCH_Config.MouseControlStickModeHeli.setPrm(this.buttonStickModeHeli.getOnOff());
/* 305 */     MCH_Config.MouseControlStickModePlane.setPrm(this.buttonStickModePlane.getOnOff());
/* 306 */     MCH_Config.MouseControlFlightSimMode.setPrm(this.buttonFlightSimMode.getOnOff());
/* 307 */     MCH_Config.SwitchWeaponWithMouseWheel.setPrm(this.buttonSwitchWeaponWheel.getOnOff());
/* 308 */     MCH_Config.MouseSensitivity.setPrm(this.sliderSensitivity.getSliderValueInt(1));
/* 309 */     MCH_Config.DisplayHUDThirdPerson.setPrm(this.buttonShowHUDTP.getOnOff());
/* 310 */     MCH_Config.SmoothShading.setPrm(this.buttonSmoothShading.getOnOff());
/* 311 */     MCH_Config.HideKeybind.setPrm(this.buttonHideKeyBind.getOnOff());
/* 312 */     MCH_Config.DisplayEntityMarker.setPrm(this.buttonShowEntityMarker.getOnOff());
/* 313 */     MCH_Config.DisplayMarkThroughWall.setPrm(this.buttonMarkThroughWall.getOnOff());
/* 314 */     MCH_Config.EntityMarkerSize.setPrm(this.sliderEntityMarkerSize.getSliderValueInt(1));
/* 315 */     MCH_Config.BlockMarkerSize.setPrm(this.sliderBlockMarkerSize.getSliderValueInt(1));
/* 316 */     MCH_Config.ReplaceRenderViewEntity.setPrm(this.buttonReplaceCamera.getOnOff());
/* 317 */     MCH_Config.DefaultExplosionParticle.setPrm(this.buttonNewExplosion.getOnOff());
/*     */     
/* 319 */     float a = this.sliderHitMark[0].getSliderValue();
/* 320 */     int r = (int)this.sliderHitMark[1].getSliderValue();
/* 321 */     int g = (int)this.sliderHitMark[2].getSliderValue();
/* 322 */     int b = (int)this.sliderHitMark[3].getSliderValue();
/* 323 */     MCH_Config.hitMarkColorAlpha = a / 255.0F;
/* 324 */     MCH_Config.hitMarkColorRGB = r << 16 | g << 8 | b;
/* 325 */     MCH_Config.HitMarkColor.setPrm(String.format("%d, %d, %d, %d", new Object[] { Integer.valueOf((int)a), Integer.valueOf(r), Integer.valueOf(g), Integer.valueOf(b) }));
/*     */     
/* 327 */     boolean b1 = MCH_Config.AutoThrottleDownHeli.prmBool;
/* 328 */     boolean b2 = MCH_Config.AutoThrottleDownPlane.prmBool;
/* 329 */     MCH_Config.AutoThrottleDownHeli.setPrm(this.buttonThrottleHeli.getOnOff());
/* 330 */     MCH_Config.AutoThrottleDownPlane.setPrm(this.buttonThrottlePlane.getOnOff());
/* 331 */     MCH_Config.AutoThrottleDownTank.setPrm(this.buttonThrottleTank.getOnOff());
/* 332 */     if (b1 != MCH_Config.AutoThrottleDownHeli.prmBool || b2 != MCH_Config.AutoThrottleDownPlane.prmBool)
/*     */     {
/* 334 */       sendClientSettings();
/*     */     }
/*     */     
/* 337 */     for (int i = 0; i < this.keyBindingList.getItemNum(); i++)
/*     */     {
/* 339 */       ((MCH_GuiListItemKeyBind)this.keyBindingList.getItem(i)).applyKeycode();
/*     */     }
/* 341 */     MCH_ClientCommonTickHandler.instance.updatekeybind(MCH_MOD.config);
/*     */     
/* 343 */     MCH_Config.TestMode.setPrm(this.buttonTestMode.getOnOff());
/*     */     
/* 345 */     MCH_MOD.config.write();
/*     */   }
/*     */ 
/*     */   
/*     */   public void switchScreen(int screenID) {
/* 350 */     this.waitKeyButtonId = 0;
/* 351 */     this.currentScreenId = screenID;
/*     */     
/* 353 */     for (W_GuiButton b : this.listControlButtons) b.setVisible(false); 
/* 354 */     for (W_GuiButton b : this.listRenderButtons) b.setVisible(false); 
/* 355 */     for (W_GuiButton b : this.listKeyBindingButtons) b.setVisible(false); 
/* 356 */     for (W_GuiButton b : this.listDevelopButtons) b.setVisible(false);
/*     */     
/* 358 */     this.ignoreButtonCounter = 3;
/*     */   }
/*     */   
/*     */   public void applySwitchScreen() {
/* 362 */     switch (this.currentScreenId) {
/*     */       
/*     */       case 1:
/* 365 */         for (W_GuiButton b : this.listRenderButtons) b.setVisible(true); 
/*     */         return;
/*     */       case 3:
/* 368 */         for (W_GuiButton b : this.listDevelopButtons) b.setVisible(true); 
/*     */         return;
/*     */       case 2:
/* 371 */         for (W_GuiButton b : this.listKeyBindingButtons) b.setVisible(true);
/*     */         
/*     */         return;
/*     */     } 
/* 375 */     for (W_GuiButton b : this.listControlButtons) b.setVisible(true);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendClientSettings() {
/* 382 */     if (this.field_146297_k.field_71439_g != null) {
/*     */       
/* 384 */       MCH_EntityAircraft ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)this.field_146297_k.field_71439_g);
/* 385 */       if (ac != null) {
/*     */         
/* 387 */         int seatId = ac.getSeatIdByEntity((Entity)this.field_146297_k.field_71439_g);
/* 388 */         if (seatId == 0)
/*     */         {
/* 390 */           ac.updateClientSettings(seatId);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73869_a(char a, int code) {
/* 398 */     if (this.waitKeyButtonId != 0) {
/*     */       
/* 400 */       if (code != 1)
/*     */       {
/* 402 */         super.func_73869_a(a, code);
/*     */       }
/* 404 */       acceptKeycode(code);
/* 405 */       this.waitKeyButtonId = 0;
/*     */     }
/*     */     else {
/*     */       
/* 409 */       super.func_73869_a(a, code);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_73864_a(int par1, int par2, int par3) {
/* 415 */     super.func_73864_a(par1, par2, par3);
/* 416 */     if (this.waitKeyButtonId != 0 && this.waitKeyAcceptCount == 0) {
/*     */       
/* 418 */       acceptKeycode(par3 - 100);
/* 419 */       this.waitKeyButtonId = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void acceptKeycode(int code) {
/* 425 */     if (code != 1)
/*     */     {
/* 427 */       if (this.field_146297_k.field_71462_r instanceof mcheli.gui.MCH_ConfigGui) {
/*     */ 
/*     */         
/* 430 */         MCH_GuiListItemKeyBind kb = (MCH_GuiListItemKeyBind)this.keyBindingList.getItem(this.waitKeyButtonId - 200);
/* 431 */         if (kb != null)
/*     */         {
/* 433 */           kb.setKeycode(code);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146274_d() {
/* 441 */     super.func_146274_d();
/*     */     
/* 443 */     if (this.waitKeyButtonId != 0)
/*     */       return; 
/* 445 */     int var16 = Mouse.getEventDWheel();
/* 446 */     if (var16 != 0)
/*     */     {
/* 448 */       if (var16 > 0) {
/*     */         
/* 450 */         this.keyBindingList.scrollDown(2.0F);
/*     */       }
/* 452 */       else if (var16 < 0) {
/*     */         
/* 454 */         this.keyBindingList.scrollUp(2.0F);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73876_c() {
/* 461 */     super.func_73876_c();
/* 462 */     if (this.waitKeyAcceptCount > 0) this.waitKeyAcceptCount--; 
/* 463 */     if (this.ignoreButtonCounter > 0) {
/*     */       
/* 465 */       this.ignoreButtonCounter--;
/* 466 */       if (this.ignoreButtonCounter == 0)
/*     */       {
/* 468 */         applySwitchScreen();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146281_b() {
/* 475 */     super.func_146281_b(); } protected void func_146284_a(GuiButton button) {
/*     */     try {
/*     */       MCH_GuiListItem item;
/*     */       int i;
/*     */       MCH_EntityAircraft ac;
/*     */       List<MCH_EntityAircraft> list;
/*     */       int j;
/* 482 */       super.func_146284_a(button);
/* 483 */       if (!button.field_146124_l)
/*     */         return; 
/* 485 */       if (this.waitKeyButtonId != 0)
/* 486 */         return;  if (!canButtonClick())
/*     */         return; 
/* 488 */       switch (button.field_146127_k) {
/*     */         
/*     */         case 50:
/* 491 */           switchScreen(1);
/*     */           break;
/*     */         case 51:
/* 494 */           switchScreen(2);
/*     */           break;
/*     */         case 52:
/* 497 */           switchScreen(0);
/*     */           break;
/*     */         case 55:
/* 500 */           switchScreen(3);
/*     */           break;
/*     */         case 100:
/* 503 */           saveAndApplyConfig();
/* 504 */           this.field_146297_k.field_71439_g.func_71053_j();
/*     */           break;
/*     */         case 101:
/* 507 */           this.field_146297_k.field_71439_g.func_71053_j();
/*     */           break;
/*     */         case 53:
/* 510 */           item = this.keyBindingList.lastPushItem;
/* 511 */           if (item != null) {
/*     */             
/* 513 */             MCH_GuiListItemKeyBind kb = (MCH_GuiListItemKeyBind)item;
/* 514 */             if (kb.lastPushButton != null) {
/*     */               
/* 516 */               int kb_num = this.keyBindingList.getItemNum();
/*     */               
/* 518 */               if (kb.lastPushButton.field_146127_k >= 200 && kb.lastPushButton.field_146127_k < 200 + kb_num) {
/*     */                 
/* 520 */                 this.waitKeyButtonId = kb.lastPushButton.field_146127_k;
/* 521 */                 this.waitKeyAcceptCount = 5;
/*     */               }
/* 523 */               else if (kb.lastPushButton.field_146127_k >= 300 && kb.lastPushButton.field_146127_k < 300 + kb_num) {
/*     */                 
/* 525 */                 kb.resetKeycode();
/*     */               } 
/*     */               
/* 528 */               kb.lastPushButton = null;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 54:
/* 533 */           for (i = 0; i < this.keyBindingList.getItemNum(); i++)
/*     */           {
/* 535 */             ((MCH_GuiListItemKeyBind)this.keyBindingList.getItem(i)).resetKeycode();
/*     */           }
/*     */           break;
/*     */         
/*     */         case 402:
/* 540 */           MCH_MOD.proxy.reloadHUD();
/*     */         
/*     */         case 400:
/* 543 */           ac = MCH_EntityAircraft.getAircraft_RiddenOrControl((Entity)this.thePlayer);
/* 544 */           if (ac != null && ac.getAcInfo() != null) {
/*     */             
/* 546 */             String name = (ac.getAcInfo()).name;
/* 547 */             MCH_Lib.DbgLog(true, "MCH_BaseInfo.reload : " + name, new Object[0]);
/* 548 */             List<MCH_EntityAircraft> list1 = this.field_146297_k.field_71441_e.field_72996_f;
/* 549 */             for (int k = 0; k < list1.size(); k++) {
/*     */               
/* 551 */               if (list1.get(k) instanceof MCH_EntityAircraft) {
/*     */                 
/* 553 */                 ac = list1.get(k);
/* 554 */                 if (ac.getAcInfo() != null && (ac.getAcInfo()).name.equals(name)) {
/*     */                   
/* 556 */                   ac.getAcInfo().reload();
/* 557 */                   ac.changeType(name);
/* 558 */                   ac.onAcInfoReloaded();
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             
/* 563 */             MCH_PacketNotifyInfoReloaded.sendRealodAc();
/*     */           } 
/* 565 */           this.field_146297_k.field_71439_g.func_71053_j();
/*     */           break;
/*     */         case 401:
/* 568 */           MCH_Lib.DbgLog(true, "MCH_BaseInfo.reload all weapon info.", new Object[0]);
/* 569 */           MCH_PacketNotifyInfoReloaded.sendRealodAllWeapon();
/* 570 */           MCH_WeaponInfoManager.reload();
/* 571 */           list = this.field_146297_k.field_71441_e.field_72996_f;
/* 572 */           for (j = 0; j < list.size(); j++) {
/*     */             
/* 574 */             if (list.get(j) instanceof MCH_EntityAircraft) {
/*     */               
/* 576 */               ac = list.get(j);
/* 577 */               if (ac.getAcInfo() != null) {
/*     */                 
/* 579 */                 ac.getAcInfo().reload();
/* 580 */                 ac.changeType((ac.getAcInfo()).name);
/* 581 */                 ac.onAcInfoReloaded();
/*     */               } 
/*     */             } 
/*     */           } 
/* 585 */           this.field_146297_k.field_71439_g.func_71053_j();
/*     */           break;
/*     */       } 
/*     */     
/* 589 */     } catch (Exception e) {
/*     */       
/* 591 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/* 597 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146979_b(int par1, int par2) {
/* 602 */     super.func_146979_b(par1, par2);
/*     */     
/* 604 */     drawString("MC Helicopter MOD Options", 10, 10, 16777215);
/*     */     
/* 606 */     if (this.currentScreenId == 0) {
/*     */       
/* 608 */       drawString("< Controls >", 170, 10, 16777215);
/*     */     }
/* 610 */     else if (this.currentScreenId == 1) {
/*     */       
/* 612 */       drawString("< Render >", 170, 10, 16777215);
/*     */       
/* 614 */       drawString("Hit Mark", 10, 75, 16777215);
/* 615 */       int color = 0;
/* 616 */       color |= (int)this.sliderHitMark[0].getSliderValue() << 24;
/* 617 */       color |= (int)this.sliderHitMark[1].getSliderValue() << 16;
/* 618 */       color |= (int)this.sliderHitMark[2].getSliderValue() << 8;
/* 619 */       color |= (int)this.sliderHitMark[3].getSliderValue() << 0;
/* 620 */       drawSampleHitMark(40, 105, color);
/*     */       
/* 622 */       double size = this.sliderEntityMarkerSize.getSliderValue();
/* 623 */       double x = 170.0D + (30.0D - size) / 2.0D;
/* 624 */       double y = (this.sliderEntityMarkerSize.field_146129_i - this.sliderEntityMarkerSize.getHeight());
/* 625 */       double[] ls = { x + size, y, x, y, x + size / 2.0D, y + size };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 631 */       drawLine(ls, -65536, 4);
/*     */       
/* 633 */       size = this.sliderBlockMarkerSize.getSliderValue();
/* 634 */       x = 185.0D;
/* 635 */       y = this.sliderBlockMarkerSize.field_146129_i;
/* 636 */       color = -65536;
/*     */       
/* 638 */       GL11.glPushMatrix();
/*     */       
/* 640 */       GL11.glEnable(3042);
/* 641 */       GL11.glDisable(3553);
/* 642 */       GL11.glBlendFunc(770, 771);
/* 643 */       GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color >> 0 & 0xFF), (byte)(color >> 24 & 0xFF));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 648 */       Tessellator.field_78398_a.func_78371_b(1);
/* 649 */       MCH_GuiTargetMarker.drawRhombus(Tessellator.field_78398_a, 15, x, y, this.field_73735_i, size, color);
/* 650 */       Tessellator.field_78398_a.func_78381_a();
/*     */       
/* 652 */       GL11.glEnable(3553);
/* 653 */       GL11.glDisable(3042);
/*     */       
/* 655 */       GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
/* 656 */       GL11.glPopMatrix();
/*     */     }
/* 658 */     else if (this.currentScreenId == 2) {
/*     */       
/* 660 */       drawString("< Key Binding >", 170, 10, 16777215);
/*     */       
/* 662 */       if (this.waitKeyButtonId != 0)
/*     */       {
/* 664 */         func_73734_a(30, 30, this.field_146999_f - 30, this.field_147000_g - 30, -533712848);
/*     */         
/* 666 */         String msg = "Please ant key or mouse button.";
/* 667 */         int w = getStringWidth(msg);
/* 668 */         drawString(msg, (this.field_146999_f - w) / 2, this.field_147000_g / 2 - 4, 16777215);
/*     */       }
/*     */     
/* 671 */     } else if (this.currentScreenId == 3) {
/*     */       
/* 673 */       drawString("< Development >", 170, 10, 16777215);
/* 674 */       drawString("Single player only!", 10, 30, 16711680);
/*     */       
/* 676 */       if (this.buttonReloadAircraftInfo != null && this.buttonReloadAircraftInfo.isOnMouseOver()) {
/*     */         
/* 678 */         drawString("The following items are not reload.", 170, 30, 16777215);
/* 679 */         String[] ignoreItems = MCH_AircraftInfo.getCannotReloadItem();
/*     */         
/* 681 */         int y = 10;
/* 682 */         for (String s : ignoreItems) {
/*     */           
/* 684 */           drawString("  " + s, 170, 30 + y, 16777215);
/* 685 */           y += 10;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float var1, int var2, int var3) {
/* 693 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
/* 694 */     this.scaleFactor = w_ScaledResolution.func_78325_e();
/*     */     
/* 696 */     W_McClient.MOD_bindTexture("textures/gui/config.png");
/* 697 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 698 */     int x = (this.field_146294_l - this.field_146999_f) / 2;
/* 699 */     int y = (this.field_146295_m - this.field_147000_g) / 2;
/* 700 */     drawTexturedModalRectRotate(x, y, this.field_146999_f, this.field_147000_g, 0.0D, 0.0D, this.field_146999_f, this.field_147000_g, 0.0F, 512.0D, 256.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawSampleHitMark(int x, int y, int color) {
/* 705 */     int cx = x;
/* 706 */     int cy = y;
/* 707 */     int IVX = 10;
/* 708 */     int IVY = 10;
/* 709 */     int SZX = 5;
/* 710 */     int SZY = 5;
/* 711 */     double[] ls = { (cx - IVX), (cy - IVY), (cx - SZX), (cy - SZY), (cx - IVX), (cy + IVY), (cx - SZX), (cy + SZY), (cx + IVX), (cy - IVY), (cx + SZX), (cy - SZY), (cx + IVX), (cy + IVY), (cx + SZX), (cy + SZY) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 718 */     drawLine(ls, color, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawLine(double[] line, int color, int mode) {
/* 723 */     GL11.glPushMatrix();
/*     */     
/* 725 */     GL11.glEnable(3042);
/* 726 */     GL11.glDisable(3553);
/* 727 */     GL11.glBlendFunc(770, 771);
/* 728 */     GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color >> 0 & 0xFF), (byte)(color >> 24 & 0xFF));
/*     */ 
/*     */ 
/*     */     
/* 732 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 733 */     tessellator.func_78371_b(mode);
/* 734 */     for (int i = 0; i < line.length; i += 2)
/*     */     {
/* 736 */       tessellator.func_78377_a(line[i + 0], line[i + 1], this.field_73735_i);
/*     */     }
/* 738 */     tessellator.func_78381_a();
/*     */     
/* 740 */     GL11.glEnable(3553);
/* 741 */     GL11.glDisable(3042);
/*     */     
/* 743 */     GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
/* 744 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTexturedModalRectRotate(double left, double top, double width, double height, double uLeft, double vTop, double uWidth, double vHeight, float rot, double texWidth, double texHeight) {
/* 758 */     GL11.glPushMatrix();
/*     */     
/* 760 */     GL11.glTranslated(left + width / 2.0D, top + height / 2.0D, 0.0D);
/* 761 */     GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F);
/*     */     
/* 763 */     float fw = (float)(1.0D / texWidth);
/* 764 */     float fh = (float)(1.0D / texHeight);
/* 765 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 766 */     tessellator.func_78382_b();
/* 767 */     tessellator.func_78374_a(-width / 2.0D, height / 2.0D, this.field_73735_i, uLeft * fw, (vTop + vHeight) * fh);
/* 768 */     tessellator.func_78374_a(width / 2.0D, height / 2.0D, this.field_73735_i, (uLeft + uWidth) * fw, (vTop + vHeight) * fh);
/* 769 */     tessellator.func_78374_a(width / 2.0D, -height / 2.0D, this.field_73735_i, (uLeft + uWidth) * fw, vTop * fh);
/* 770 */     tessellator.func_78374_a(-width / 2.0D, -height / 2.0D, this.field_73735_i, uLeft * fw, vTop * fh);
/* 771 */     tessellator.func_78381_a();
/*     */     
/* 773 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gui\MCH_ConfigGui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */