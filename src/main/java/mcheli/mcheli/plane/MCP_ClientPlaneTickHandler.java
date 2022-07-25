/*     */ package mcheli.mcheli.plane;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_ViewEntityDummy;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.aircraft.MCH_PacketPlayerControlBase;
/*     */ import mcheli.aircraft.MCH_SeatInfo;
/*     */ import mcheli.plane.MCP_EntityPlane;
/*     */ import mcheli.plane.MCP_PlanePacketPlayerControl;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCP_ClientPlaneTickHandler extends MCH_AircraftClientTickHandler {
/*     */   public MCH_Key KeySwitchMode;
/*     */   public MCH_Key KeyEjectSeat;
/*     */   
/*     */   public MCP_ClientPlaneTickHandler(Minecraft minecraft, MCH_Config config) {
/*  26 */     super(minecraft, config);
/*  27 */     updateKeybind(config);
/*     */   }
/*     */   public MCH_Key KeyZoom; public MCH_Key[] Keys;
/*     */   public void updateKeybind(MCH_Config config) {
/*  31 */     super.updateKeybind(config);
/*  32 */     this.KeySwitchMode = new MCH_Key(MCH_Config.KeySwitchMode.prmInt);
/*  33 */     this.KeyEjectSeat = new MCH_Key(MCH_Config.KeySwitchHovering.prmInt);
/*  34 */     this.KeyZoom = new MCH_Key(MCH_Config.KeyZoom.prmInt);
/*     */     
/*  36 */     this.Keys = new MCH_Key[] { this.KeyUp, this.KeyDown, this.KeyRight, this.KeyLeft, this.KeySwitchMode, this.KeyEjectSeat, this.KeyUseWeapon, this.KeySwWeaponMode, this.KeySwitchWeapon1, this.KeySwitchWeapon2, this.KeyZoom, this.KeyCameraMode, this.KeyUnmount, this.KeyUnmountForce, this.KeyFlare, this.KeyExtra, this.KeyFreeLook, this.KeyGUI, this.KeyGearUpDown, this.KeyPutToRack, this.KeyDownFromRack };
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
/*     */   protected void update(EntityPlayer player, MCP_EntityPlane plane) {
/*  50 */     if (plane.getIsGunnerMode((Entity)player)) {
/*     */       
/*  52 */       MCH_SeatInfo seatInfo = plane.getSeatInfo((Entity)player);
/*  53 */       if (seatInfo != null)
/*     */       {
/*  55 */         setRotLimitPitch(seatInfo.minPitch, seatInfo.maxPitch, (Entity)player);
/*     */       }
/*     */     } 
/*     */     
/*  59 */     plane.updateRadar(10);
/*  60 */     plane.updateCameraRotate(player.field_70177_z, player.field_70125_A);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTick(boolean inGUI) {
/*  65 */     for (MCH_Key k : this.Keys) k.update();
/*     */     
/*  67 */     this.isBeforeRiding = this.isRiding;
/*     */     
/*  69 */     EntityClientPlayerMP entityClientPlayerMP = this.mc.field_71439_g;
/*     */ 
/*     */     
/*  72 */     MCP_EntityPlane plane = null;
/*  73 */     boolean isPilot = true;
/*  74 */     if (entityClientPlayerMP != null)
/*     */     {
/*  76 */       if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCP_EntityPlane) {
/*     */         
/*  78 */         plane = (MCP_EntityPlane)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*     */       }
/*  80 */       else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntitySeat) {
/*     */         
/*  82 */         MCH_EntitySeat seat = (MCH_EntitySeat)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*  83 */         if (seat.getParent() instanceof MCP_EntityPlane)
/*     */         {
/*  85 */           isPilot = false;
/*  86 */           plane = (MCP_EntityPlane)seat.getParent();
/*     */         }
/*     */       
/*  89 */       } else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityUavStation) {
/*     */         
/*  91 */         MCH_EntityUavStation uavStation = (MCH_EntityUavStation)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*  92 */         if (uavStation.getControlAircract() instanceof MCP_EntityPlane)
/*     */         {
/*  94 */           plane = (MCP_EntityPlane)uavStation.getControlAircract();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  99 */     if (plane != null && plane.getAcInfo() != null) {
/*     */       
/* 101 */       update((EntityPlayer)entityClientPlayerMP, plane);
/*     */       
/* 103 */       MCH_ViewEntityDummy viewEntityDummy = MCH_ViewEntityDummy.getInstance((World)this.mc.field_71441_e);
/* 104 */       viewEntityDummy.update(plane.camera);
/*     */       
/* 106 */       if (!inGUI) {
/*     */         
/* 108 */         if (!plane.isDestroyed())
/*     */         {
/* 110 */           playerControl((EntityPlayer)entityClientPlayerMP, plane, isPilot);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 115 */         playerControlInGUI((EntityPlayer)entityClientPlayerMP, plane, isPilot);
/*     */       } 
/*     */       
/* 118 */       boolean hideHand = true;
/*     */       
/* 120 */       if ((isPilot && plane.isAlwaysCameraView()) || plane.getIsGunnerMode((Entity)entityClientPlayerMP) || plane.getCameraId() > 0) {
/*     */         
/* 122 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)viewEntityDummy);
/*     */       }
/*     */       else {
/*     */         
/* 126 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)entityClientPlayerMP);
/* 127 */         if (!isPilot && plane.getCurrentWeaponID((Entity)entityClientPlayerMP) < 0)
/*     */         {
/* 129 */           hideHand = false;
/*     */         }
/*     */       } 
/*     */       
/* 133 */       if (hideHand)
/*     */       {
/* 135 */         MCH_Lib.disableFirstPersonItemRender(entityClientPlayerMP.func_71045_bC());
/*     */       }
/*     */       
/* 138 */       this.isRiding = true;
/*     */     }
/*     */     else {
/*     */       
/* 142 */       this.isRiding = false;
/*     */     } 
/*     */ 
/*     */     
/* 146 */     if (!this.isBeforeRiding && this.isRiding && plane != null) {
/*     */       
/* 148 */       MCH_ViewEntityDummy.getInstance((World)this.mc.field_71441_e).func_70107_b(plane.field_70165_t, plane.field_70163_u + 0.5D, plane.field_70161_v);
/*     */     
/*     */     }
/* 151 */     else if (this.isBeforeRiding && !this.isRiding) {
/*     */       
/* 153 */       MCH_Lib.enableFirstPersonItemRender();
/* 154 */       MCH_Lib.setRenderViewEntity((EntityLivingBase)entityClientPlayerMP);
/* 155 */       W_Reflection.setCameraRoll(0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playerControlInGUI(EntityPlayer player, MCP_EntityPlane plane, boolean isPilot) {
/* 161 */     commonPlayerControlInGUI(player, (MCH_EntityAircraft)plane, isPilot, (MCH_PacketPlayerControlBase)new MCP_PlanePacketPlayerControl());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playerControl(EntityPlayer player, MCP_EntityPlane plane, boolean isPilot) {
/* 167 */     MCP_PlanePacketPlayerControl pc = new MCP_PlanePacketPlayerControl();
/* 168 */     boolean send = false;
/* 169 */     MCP_EntityPlane mCP_EntityPlane = plane;
/*     */     
/* 171 */     send = commonPlayerControl(player, (MCH_EntityAircraft)plane, isPilot, (MCH_PacketPlayerControlBase)pc);
/*     */     
/* 173 */     if (isPilot) {
/*     */ 
/*     */       
/* 176 */       if (this.KeySwitchMode.isKeyDown())
/*     */       {
/* 178 */         if (mCP_EntityPlane.getIsGunnerMode((Entity)player) && mCP_EntityPlane.canSwitchCameraPos()) {
/*     */           
/* 180 */           pc.switchMode = 0;
/* 181 */           mCP_EntityPlane.switchGunnerMode(false);
/* 182 */           send = true;
/*     */           
/* 184 */           mCP_EntityPlane.setCameraId(1);
/*     */         }
/* 186 */         else if (mCP_EntityPlane.getCameraId() > 0) {
/*     */ 
/*     */           
/* 189 */           mCP_EntityPlane.setCameraId(mCP_EntityPlane.getCameraId() + 1);
/* 190 */           if (mCP_EntityPlane.getCameraId() >= mCP_EntityPlane.getCameraPosNum())
/*     */           {
/* 192 */             mCP_EntityPlane.setCameraId(0);
/*     */           }
/*     */         }
/* 195 */         else if (mCP_EntityPlane.canSwitchGunnerMode()) {
/*     */           
/* 197 */           pc.switchMode = mCP_EntityPlane.getIsGunnerMode((Entity)player) ? 0 : 1;
/* 198 */           mCP_EntityPlane.switchGunnerMode(!mCP_EntityPlane.getIsGunnerMode((Entity)player));
/* 199 */           send = true;
/* 200 */           mCP_EntityPlane.setCameraId(0);
/*     */         
/*     */         }
/* 203 */         else if (mCP_EntityPlane.canSwitchCameraPos()) {
/*     */           
/* 205 */           mCP_EntityPlane.setCameraId(1);
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 210 */           playSoundNG();
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 215 */       if (this.KeyExtra.isKeyDown())
/*     */       {
/* 217 */         if (plane.canSwitchVtol())
/*     */         {
/* 219 */           boolean currentMode = plane.getNozzleStat();
/* 220 */           if (!currentMode) { pc.switchVtol = 1; }
/* 221 */           else { pc.switchVtol = 0; }
/* 222 */            plane.swithVtolMode(!currentMode);
/* 223 */           send = true;
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 228 */           playSoundNG();
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 236 */     else if (this.KeySwitchMode.isKeyDown()) {
/*     */       
/* 238 */       if (plane.canSwitchGunnerModeOtherSeat(player)) {
/*     */         
/* 240 */         plane.switchGunnerModeOtherSeat(player);
/* 241 */         send = true;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 246 */         playSoundNG();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 252 */     if (this.KeyZoom.isKeyDown()) {
/*     */ 
/*     */       
/* 255 */       boolean isUav = (plane.isUAV() && !plane.getAcInfo().haveHatch() && !plane.getPlaneInfo().haveWing());
/* 256 */       if (plane.getIsGunnerMode((Entity)player) || isUav) {
/*     */         
/* 258 */         plane.zoomCamera();
/* 259 */         playSound("zoom", 0.5F, 1.0F);
/*     */       }
/* 261 */       else if (isPilot) {
/*     */         
/* 263 */         if (plane.getAcInfo().haveHatch()) {
/*     */           
/* 265 */           if (plane.canFoldHatch())
/*     */           {
/* 267 */             pc.switchHatch = 2;
/* 268 */             send = true;
/*     */           }
/* 270 */           else if (plane.canUnfoldHatch())
/*     */           {
/* 272 */             pc.switchHatch = 1;
/* 273 */             send = true;
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 278 */         else if (plane.canFoldWing()) {
/*     */           
/* 280 */           pc.switchHatch = 2;
/* 281 */           send = true;
/*     */         }
/* 283 */         else if (plane.canUnfoldWing()) {
/*     */           
/* 285 */           pc.switchHatch = 1;
/* 286 */           send = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 292 */     if (this.KeyEjectSeat.isKeyDown() && plane.canEjectSeat((Entity)player)) {
/*     */       
/* 294 */       pc.ejectSeat = true;
/* 295 */       send = true;
/*     */     } 
/*     */     
/* 298 */     if (send)
/*     */     {
/* 300 */       W_Network.sendToServer((W_PacketBase)pc);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\plane\MCP_ClientPlaneTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */