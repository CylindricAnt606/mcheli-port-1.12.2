/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import mcheli.MCH_ClientTickHandlerBase;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.MCH_PacketIndOpenScreen;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_PacketPlayerControlBase;
/*     */ import mcheli.aircraft.MCH_PacketSeatPlayerControl;
/*     */ import mcheli.wrapper.W_Network;
/*     */ import mcheli.wrapper.W_PacketBase;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ public abstract class MCH_AircraftClientTickHandler
/*     */   extends MCH_ClientTickHandlerBase
/*     */ {
/*     */   protected boolean isRiding = false;
/*     */   protected boolean isBeforeRiding = false;
/*     */   public MCH_Key KeyUp;
/*     */   public MCH_Key KeyDown;
/*     */   public MCH_Key KeyRight;
/*     */   public MCH_Key KeyLeft;
/*     */   public MCH_Key KeyUseWeapon;
/*     */   public MCH_Key KeySwitchWeapon1;
/*     */   public MCH_Key KeySwitchWeapon2;
/*     */   public MCH_Key KeySwWeaponMode;
/*     */   public MCH_Key KeyUnmount;
/*     */   public MCH_Key KeyUnmountForce;
/*     */   public MCH_Key KeyExtra;
/*     */   public MCH_Key KeyFlare;
/*     */   public MCH_Key KeyCameraMode;
/*     */   public MCH_Key KeyFreeLook;
/*     */   public MCH_Key KeyGUI;
/*     */   public MCH_Key KeyGearUpDown;
/*     */   public MCH_Key KeyPutToRack;
/*     */   public MCH_Key KeyDownFromRack;
/*     */   public MCH_Key KeyBrake;
/*     */   
/*     */   public MCH_AircraftClientTickHandler(Minecraft minecraft, MCH_Config config) {
/*  44 */     super(minecraft);
/*  45 */     updateKeybind(config);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateKeybind(MCH_Config config) {
/*  50 */     this.KeyUp = new MCH_Key(MCH_Config.KeyUp.prmInt);
/*  51 */     this.KeyDown = new MCH_Key(MCH_Config.KeyDown.prmInt);
/*  52 */     this.KeyRight = new MCH_Key(MCH_Config.KeyRight.prmInt);
/*  53 */     this.KeyLeft = new MCH_Key(MCH_Config.KeyLeft.prmInt);
/*  54 */     this.KeyUseWeapon = new MCH_Key(MCH_Config.KeyUseWeapon.prmInt);
/*  55 */     this.KeySwitchWeapon1 = new MCH_Key(MCH_Config.KeySwitchWeapon1.prmInt);
/*  56 */     this.KeySwitchWeapon2 = new MCH_Key(MCH_Config.KeySwitchWeapon2.prmInt);
/*  57 */     this.KeySwWeaponMode = new MCH_Key(MCH_Config.KeySwWeaponMode.prmInt);
/*  58 */     this.KeyUnmount = new MCH_Key(MCH_Config.KeyUnmount.prmInt);
/*  59 */     this.KeyUnmountForce = new MCH_Key(42);
/*  60 */     this.KeyExtra = new MCH_Key(MCH_Config.KeyExtra.prmInt);
/*  61 */     this.KeyFlare = new MCH_Key(MCH_Config.KeyFlare.prmInt);
/*  62 */     this.KeyCameraMode = new MCH_Key(MCH_Config.KeyCameraMode.prmInt);
/*  63 */     this.KeyFreeLook = new MCH_Key(MCH_Config.KeyFreeLook.prmInt);
/*  64 */     this.KeyGUI = new MCH_Key(MCH_Config.KeyGUI.prmInt);
/*  65 */     this.KeyGearUpDown = new MCH_Key(MCH_Config.KeyGearUpDown.prmInt);
/*  66 */     this.KeyPutToRack = new MCH_Key(MCH_Config.KeyPutToRack.prmInt);
/*  67 */     this.KeyDownFromRack = new MCH_Key(MCH_Config.KeyDownFromRack.prmInt);
/*  68 */     this.KeyBrake = new MCH_Key(MCH_Config.KeySwitchHovering.prmInt);
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected void commonPlayerControlInGUI(EntityPlayer player, MCH_EntityAircraft ac, boolean isPilot, MCH_PacketPlayerControlBase pc) {}
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
/*     */   public boolean commonPlayerControl(EntityPlayer player, MCH_EntityAircraft ac, boolean isPilot, MCH_PacketPlayerControlBase pc) {
/* 100 */     if (Keyboard.isKeyDown(MCH_Config.KeyFreeLook.prmInt)) {
/*     */       
/* 102 */       if (this.KeyGUI.isKeyDown() || this.KeyExtra.isKeyDown())
/*     */       {
/* 104 */         MCH_PacketSeatPlayerControl psc = new MCH_PacketSeatPlayerControl();
/* 105 */         if (isPilot) {
/*     */           
/* 107 */           psc.switchSeat = (byte)(this.KeyGUI.isKeyDown() ? 1 : 2);
/*     */         }
/*     */         else {
/*     */           
/* 111 */           ac.keepOnRideRotation = true;
/* 112 */           psc.switchSeat = 3;
/*     */         } 
/* 114 */         W_Network.sendToServer((W_PacketBase)psc);
/* 115 */         return false;
/*     */       }
/*     */     
/* 118 */     } else if (!isPilot && ac.getSeatNum() > 1) {
/*     */       
/* 120 */       MCH_PacketSeatPlayerControl psc = new MCH_PacketSeatPlayerControl();
/*     */       
/* 122 */       if (this.KeyGUI.isKeyDown()) {
/*     */         
/* 124 */         psc.switchSeat = 1;
/* 125 */         W_Network.sendToServer((W_PacketBase)psc);
/* 126 */         return false;
/*     */       } 
/* 128 */       if (this.KeyExtra.isKeyDown()) {
/*     */         
/* 130 */         psc.switchSeat = 2;
/* 131 */         W_Network.sendToServer((W_PacketBase)psc);
/* 132 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 136 */     boolean send = false;
/*     */     
/* 138 */     if (this.KeyCameraMode.isKeyDown())
/*     */     {
/* 140 */       if (ac.haveSearchLight()) {
/*     */         
/* 142 */         if (ac.canSwitchSearchLight((Entity)player))
/*     */         {
/* 144 */           pc.switchSearchLight = true;
/* 145 */           playSoundOK();
/* 146 */           send = true;
/*     */         }
/*     */       
/* 149 */       } else if (ac.canSwitchCameraMode()) {
/*     */         
/* 151 */         int beforeMode = ac.getCameraMode(player);
/* 152 */         ac.switchCameraMode(player);
/* 153 */         int mode = ac.getCameraMode(player);
/* 154 */         if (mode != beforeMode)
/*     */         {
/* 156 */           pc.switchCameraMode = (byte)(mode + 1);
/* 157 */           playSoundOK();
/* 158 */           send = true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 163 */         playSoundNG();
/*     */       } 
/*     */     }
/*     */     
/* 167 */     if (this.KeyUnmount.isKeyDown() && !ac.isDestroyed() && ac.func_70302_i_() > 0 && !isPilot)
/*     */     {
/* 169 */       MCH_PacketIndOpenScreen.send(3);
/*     */     }
/*     */     
/* 172 */     if (isPilot) {
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
/* 183 */       if (this.KeyUnmount.isKeyDown()) {
/*     */ 
/*     */         
/* 186 */         pc.isUnmount = 2;
/* 187 */         send = true;
/*     */       } 
/*     */       
/* 190 */       if (this.KeyPutToRack.isKeyDown()) {
/*     */         
/* 192 */         ac.checkRideRack();
/* 193 */         if (ac.canRideRack())
/*     */         {
/* 195 */           pc.putDownRack = 3;
/* 196 */           send = true;
/*     */         }
/* 198 */         else if (ac.canPutToRack())
/*     */         {
/* 200 */           pc.putDownRack = 1;
/* 201 */           send = true;
/*     */         }
/*     */       
/* 204 */       } else if (this.KeyDownFromRack.isKeyDown()) {
/*     */         
/* 206 */         if (ac.field_70154_o != null) {
/*     */           
/* 208 */           pc.isUnmount = 3;
/* 209 */           send = true;
/*     */         }
/* 211 */         else if (ac.canDownFromRack()) {
/*     */           
/* 213 */           pc.putDownRack = 2;
/* 214 */           send = true;
/*     */         } 
/*     */       } 
/*     */       
/* 218 */       if (this.KeyGearUpDown.isKeyDown() && ac.getAcInfo().haveLandingGear())
/*     */       {
/* 220 */         if (ac.canFoldLandingGear()) {
/*     */           
/* 222 */           pc.switchGear = 1;
/* 223 */           send = true;
/*     */         }
/* 225 */         else if (ac.canUnfoldLandingGear()) {
/*     */           
/* 227 */           pc.switchGear = 2;
/* 228 */           send = true;
/*     */         } 
/*     */       }
/*     */       
/* 232 */       if (this.KeyFreeLook.isKeyDown())
/*     */       {
/* 234 */         if (ac.canSwitchFreeLook()) {
/*     */           
/* 236 */           pc.switchFreeLook = ac.isFreeLookMode() ? 2 : 1;
/*     */           
/* 238 */           send = true;
/*     */         } 
/*     */       }
/*     */       
/* 242 */       if (this.KeyGUI.isKeyDown()) {
/*     */         
/* 244 */         pc.openGui = true;
/* 245 */         send = true;
/*     */       } 
/*     */ 
/*     */       
/* 249 */       if (ac.isRepelling()) {
/*     */         
/* 251 */         pc.throttleDown = ac.throttleDown = false;
/* 252 */         pc.throttleUp = ac.throttleUp = false;
/* 253 */         pc.moveRight = ac.moveRight = false;
/* 254 */         pc.moveLeft = ac.moveLeft = false;
/*     */ 
/*     */       
/*     */       }
/* 258 */       else if (ac.hasBrake() && this.KeyBrake.isKeyPress()) {
/*     */         
/* 260 */         send |= this.KeyBrake.isKeyDown();
/*     */         
/* 262 */         pc.throttleDown = ac.throttleDown = false;
/* 263 */         pc.throttleUp = ac.throttleUp = false;
/* 264 */         double dx = ac.field_70165_t - ac.field_70169_q;
/* 265 */         double dz = ac.field_70161_v - ac.field_70166_s;
/* 266 */         double dist = dx * dx + dz * dz;
/* 267 */         if (ac.getCurrentThrottle() <= 0.03D && dist < 0.01D) {
/*     */           
/* 269 */           pc.moveRight = ac.moveRight = false;
/* 270 */           pc.moveLeft = ac.moveLeft = false;
/*     */         } 
/* 272 */         pc.useBrake = true;
/*     */       }
/*     */       else {
/*     */         
/* 276 */         send |= this.KeyBrake.isKeyUp();
/*     */         
/* 278 */         MCH_Key[] dKey = { this.KeyUp, this.KeyDown, this.KeyRight, this.KeyLeft };
/* 279 */         for (MCH_Key k : dKey) {
/*     */           
/* 281 */           if (k.isKeyDown() || k.isKeyUp()) {
/*     */             
/* 283 */             send = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 287 */         pc.throttleDown = ac.throttleDown = this.KeyDown.isKeyPress();
/* 288 */         pc.throttleUp = ac.throttleUp = this.KeyUp.isKeyPress();
/* 289 */         pc.moveRight = ac.moveRight = this.KeyRight.isKeyPress();
/* 290 */         pc.moveLeft = ac.moveLeft = this.KeyLeft.isKeyPress();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 296 */     if (!ac.isDestroyed() && this.KeyFlare.isKeyDown())
/*     */     {
/*     */       
/* 299 */       if (ac.getSeatIdByEntity((Entity)player) <= 1)
/*     */       {
/* 301 */         if (ac.canUseFlare() && ac.useFlare(ac.getCurrentFlareType())) {
/*     */           
/* 303 */           pc.useFlareType = (byte)ac.getCurrentFlareType();
/* 304 */           ac.nextFlareType();
/* 305 */           send = true;
/*     */         }
/*     */         else {
/*     */           
/* 309 */           playSoundNG();
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 315 */     if (!ac.isDestroyed() && !ac.isPilotReloading())
/*     */     {
/*     */       
/* 318 */       if (this.KeySwitchWeapon1.isKeyDown() || this.KeySwitchWeapon2.isKeyDown() || getMouseWheel() != 0) {
/*     */ 
/*     */         
/* 321 */         if (getMouseWheel() > 0) {
/*     */           
/* 323 */           pc.switchWeapon = (byte)ac.getNextWeaponID((Entity)player, -1);
/*     */         }
/*     */         else {
/*     */           
/* 327 */           pc.switchWeapon = (byte)ac.getNextWeaponID((Entity)player, 1);
/*     */         } 
/* 329 */         setMouseWheel(0);
/* 330 */         ac.switchWeapon((Entity)player, pc.switchWeapon);
/* 331 */         send = true;
/*     */       
/*     */       }
/* 334 */       else if (this.KeySwWeaponMode.isKeyDown()) {
/*     */         
/* 336 */         ac.switchCurrentWeaponMode((Entity)player);
/*     */       
/*     */       }
/* 339 */       else if (this.KeyUseWeapon.isKeyPress()) {
/*     */ 
/*     */         
/* 342 */         if (ac.useCurrentWeapon((Entity)player)) {
/*     */           
/* 344 */           pc.useWeapon = true;
/* 345 */           pc.useWeaponOption1 = ac.getCurrentWeapon((Entity)player).getLastUsedOptionParameter1();
/* 346 */           pc.useWeaponOption2 = ac.getCurrentWeapon((Entity)player).getLastUsedOptionParameter2();
/* 347 */           pc.useWeaponPosX = ac.field_70169_q;
/* 348 */           pc.useWeaponPosY = ac.field_70167_r;
/* 349 */           pc.useWeaponPosZ = ac.field_70166_s;
/* 350 */           send = true;
/*     */         } 
/*     */       } 
/*     */     }
/* 354 */     return (send || player.field_70173_aa % 100 == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_AircraftClientTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */