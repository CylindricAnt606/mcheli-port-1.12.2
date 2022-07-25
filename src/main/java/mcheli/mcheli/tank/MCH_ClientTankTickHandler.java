/*     */ package mcheli.mcheli.tank;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_ViewEntityDummy;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.aircraft.MCH_PacketPlayerControlBase;
/*     */ import mcheli.aircraft.MCH_SeatInfo;
/*     */ import mcheli.tank.MCH_EntityTank;
/*     */ import mcheli.tank.MCH_TankPacketPlayerControl;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_ClientTankTickHandler extends MCH_AircraftClientTickHandler {
/*     */   public MCH_Key KeySwitchMode;
/*     */   
/*     */   public MCH_ClientTankTickHandler(Minecraft minecraft, MCH_Config config) {
/*  25 */     super(minecraft, config);
/*  26 */     updateKeybind(config);
/*     */   }
/*     */   public MCH_Key KeyZoom; public MCH_Key[] Keys;
/*     */   public void updateKeybind(MCH_Config config) {
/*  30 */     super.updateKeybind(config);
/*  31 */     this.KeySwitchMode = new MCH_Key(MCH_Config.KeySwitchMode.prmInt);
/*  32 */     this.KeyZoom = new MCH_Key(MCH_Config.KeyZoom.prmInt);
/*     */     
/*  34 */     this.Keys = new MCH_Key[] { this.KeyUp, this.KeyDown, this.KeyRight, this.KeyLeft, this.KeySwitchMode, this.KeyUseWeapon, this.KeySwWeaponMode, this.KeySwitchWeapon1, this.KeySwitchWeapon2, this.KeyZoom, this.KeyCameraMode, this.KeyUnmount, this.KeyUnmountForce, this.KeyFlare, this.KeyExtra, this.KeyFreeLook, this.KeyGUI, this.KeyGearUpDown, this.KeyBrake, this.KeyPutToRack, this.KeyDownFromRack };
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
/*     */   protected void update(EntityPlayer player, MCH_EntityTank tank) {
/*  48 */     if (tank.getIsGunnerMode((Entity)player)) {
/*     */       
/*  50 */       MCH_SeatInfo seatInfo = tank.getSeatInfo((Entity)player);
/*  51 */       if (seatInfo != null)
/*     */       {
/*  53 */         setRotLimitPitch(seatInfo.minPitch, seatInfo.maxPitch, (Entity)player);
/*     */       }
/*     */     } 
/*     */     
/*  57 */     tank.updateRadar(10);
/*  58 */     tank.updateCameraRotate(player.field_70177_z, player.field_70125_A);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTick(boolean inGUI) {
/*  63 */     for (MCH_Key k : this.Keys) k.update();
/*     */     
/*  65 */     this.isBeforeRiding = this.isRiding;
/*     */     
/*  67 */     EntityClientPlayerMP entityClientPlayerMP = this.mc.field_71439_g;
/*     */ 
/*     */     
/*  70 */     MCH_EntityTank tank = null;
/*  71 */     boolean isPilot = true;
/*  72 */     if (entityClientPlayerMP != null)
/*     */     {
/*  74 */       if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityTank) {
/*     */         
/*  76 */         tank = (MCH_EntityTank)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*     */       }
/*  78 */       else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntitySeat) {
/*     */         
/*  80 */         MCH_EntitySeat seat = (MCH_EntitySeat)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*  81 */         if (seat.getParent() instanceof MCH_EntityTank)
/*     */         {
/*  83 */           isPilot = false;
/*  84 */           tank = (MCH_EntityTank)seat.getParent();
/*     */         }
/*     */       
/*  87 */       } else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityUavStation) {
/*     */         
/*  89 */         MCH_EntityUavStation uavStation = (MCH_EntityUavStation)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*  90 */         if (uavStation.getControlAircract() instanceof MCH_EntityTank)
/*     */         {
/*  92 */           tank = (MCH_EntityTank)uavStation.getControlAircract();
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  97 */     if (tank != null && tank.getAcInfo() != null) {
/*     */       
/*  99 */       update((EntityPlayer)entityClientPlayerMP, tank);
/*     */       
/* 101 */       MCH_ViewEntityDummy viewEntityDummy = MCH_ViewEntityDummy.getInstance((World)this.mc.field_71441_e);
/* 102 */       viewEntityDummy.update(tank.camera);
/*     */       
/* 104 */       if (!inGUI) {
/*     */         
/* 106 */         if (!tank.isDestroyed())
/*     */         {
/* 108 */           playerControl((EntityPlayer)entityClientPlayerMP, tank, isPilot);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 113 */         playerControlInGUI((EntityPlayer)entityClientPlayerMP, tank, isPilot);
/*     */       } 
/*     */       
/* 116 */       boolean hideHand = true;
/*     */       
/* 118 */       if ((isPilot && tank.isAlwaysCameraView()) || tank.getIsGunnerMode((Entity)entityClientPlayerMP) || tank.getCameraId() > 0) {
/*     */         
/* 120 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)viewEntityDummy);
/*     */       }
/*     */       else {
/*     */         
/* 124 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)entityClientPlayerMP);
/* 125 */         if (!isPilot && tank.getCurrentWeaponID((Entity)entityClientPlayerMP) < 0)
/*     */         {
/* 127 */           hideHand = false;
/*     */         }
/*     */       } 
/*     */       
/* 131 */       if (hideHand)
/*     */       {
/* 133 */         MCH_Lib.disableFirstPersonItemRender(entityClientPlayerMP.func_71045_bC());
/*     */       }
/*     */       
/* 136 */       this.isRiding = true;
/*     */     }
/*     */     else {
/*     */       
/* 140 */       this.isRiding = false;
/*     */     } 
/*     */ 
/*     */     
/* 144 */     if (!this.isBeforeRiding && this.isRiding && tank != null) {
/*     */       
/* 146 */       MCH_ViewEntityDummy.getInstance((World)this.mc.field_71441_e).func_70107_b(tank.field_70165_t, tank.field_70163_u + 0.5D, tank.field_70161_v);
/*     */     
/*     */     }
/* 149 */     else if (this.isBeforeRiding && !this.isRiding) {
/*     */       
/* 151 */       MCH_Lib.enableFirstPersonItemRender();
/* 152 */       MCH_Lib.setRenderViewEntity((EntityLivingBase)entityClientPlayerMP);
/* 153 */       W_Reflection.setCameraRoll(0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playerControlInGUI(EntityPlayer player, MCH_EntityTank tank, boolean isPilot) {
/* 159 */     commonPlayerControlInGUI(player, (MCH_EntityAircraft)tank, isPilot, (MCH_PacketPlayerControlBase)new MCH_TankPacketPlayerControl());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playerControl(EntityPlayer player, MCH_EntityTank tank, boolean isPilot) {
/* 165 */     MCH_TankPacketPlayerControl pc = new MCH_TankPacketPlayerControl();
/* 166 */     boolean send = false;
/* 167 */     MCH_EntityTank mCH_EntityTank = tank;
/*     */     
/* 169 */     send = commonPlayerControl(player, (MCH_EntityAircraft)tank, isPilot, (MCH_PacketPlayerControlBase)pc);
/*     */     
/* 171 */     if ((mCH_EntityTank.getAcInfo()).defaultFreelook && pc.switchFreeLook > 0)
/*     */     {
/* 173 */       pc.switchFreeLook = 0;
/*     */     }
/*     */     
/* 176 */     if (isPilot) {
/*     */ 
/*     */       
/* 179 */       if (this.KeySwitchMode.isKeyDown())
/*     */       {
/* 181 */         if (mCH_EntityTank.getIsGunnerMode((Entity)player) && mCH_EntityTank.canSwitchCameraPos())
/*     */         {
/* 183 */           pc.switchMode = 0;
/* 184 */           mCH_EntityTank.switchGunnerMode(false);
/* 185 */           send = true;
/*     */           
/* 187 */           mCH_EntityTank.setCameraId(1);
/*     */         }
/* 189 */         else if (mCH_EntityTank.getCameraId() > 0)
/*     */         {
/*     */           
/* 192 */           mCH_EntityTank.setCameraId(mCH_EntityTank.getCameraId() + 1);
/* 193 */           if (mCH_EntityTank.getCameraId() >= mCH_EntityTank.getCameraPosNum())
/*     */           {
/* 195 */             mCH_EntityTank.setCameraId(0);
/*     */           }
/*     */         }
/* 198 */         else if (mCH_EntityTank.canSwitchGunnerMode())
/*     */         {
/* 200 */           pc.switchMode = mCH_EntityTank.getIsGunnerMode((Entity)player) ? 0 : 1;
/* 201 */           mCH_EntityTank.switchGunnerMode(!mCH_EntityTank.getIsGunnerMode((Entity)player));
/* 202 */           send = true;
/* 203 */           mCH_EntityTank.setCameraId(0);
/*     */         
/*     */         }
/* 206 */         else if (mCH_EntityTank.canSwitchCameraPos())
/*     */         {
/* 208 */           mCH_EntityTank.setCameraId(1);
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 213 */           playSoundNG();
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 221 */     else if (this.KeySwitchMode.isKeyDown()) {
/*     */       
/* 223 */       if (tank.canSwitchGunnerModeOtherSeat(player)) {
/*     */         
/* 225 */         tank.switchGunnerModeOtherSeat(player);
/* 226 */         send = true;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 231 */         playSoundNG();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 237 */     if (this.KeyZoom.isKeyDown()) {
/*     */ 
/*     */       
/* 240 */       boolean isUav = (tank.isUAV() && !tank.getAcInfo().haveHatch());
/* 241 */       if (tank.getIsGunnerMode((Entity)player) || isUav) {
/*     */         
/* 243 */         tank.zoomCamera();
/* 244 */         playSound("zoom", 0.5F, 1.0F);
/*     */       }
/* 246 */       else if (isPilot) {
/*     */         
/* 248 */         if (tank.getAcInfo().haveHatch())
/*     */         {
/* 250 */           if (tank.canFoldHatch()) {
/*     */             
/* 252 */             pc.switchHatch = 2;
/* 253 */             send = true;
/*     */           }
/* 255 */           else if (tank.canUnfoldHatch()) {
/*     */             
/* 257 */             pc.switchHatch = 1;
/* 258 */             send = true;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 264 */     if (send)
/*     */     {
/* 266 */       W_Network.sendToServer((W_PacketBase)pc);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tank\MCH_ClientTankTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */