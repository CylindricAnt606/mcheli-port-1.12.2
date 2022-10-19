/*     */ package mcheli.mcheli.helicopter;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_ViewEntityDummy;
/*     */ import mcheli.aircraft.MCH_AircraftClientTickHandler;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.aircraft.MCH_PacketPlayerControlBase;
/*     */ import mcheli.aircraft.MCH_SeatInfo;
/*     */ import mcheli.helicopter.MCH_EntityHeli;
/*     */ import mcheli.helicopter.MCH_HeliPacketPlayerControl;
/*     */ import mcheli.uav.MCH_EntityUavStation;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_PacketBase;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_ClientHeliTickHandler extends MCH_AircraftClientTickHandler {
/*     */   public MCH_Key KeySwitchMode;
/*     */   public MCH_Key KeySwitchHovering;
/*     */   
/*     */   public MCH_ClientHeliTickHandler(Minecraft minecraft, MCH_Config config) {
/*  29 */     super(minecraft, config);
/*  30 */     updateKeybind(config);
/*     */   }
/*     */   public MCH_Key KeyZoom; public MCH_Key[] Keys;
/*     */   
/*     */   public void updateKeybind(MCH_Config config) {
/*  35 */     super.updateKeybind(config);
/*  36 */     this.KeySwitchMode = new MCH_Key(MCH_Config.KeySwitchMode.prmInt);
/*  37 */     this.KeySwitchHovering = new MCH_Key(MCH_Config.KeySwitchHovering.prmInt);
/*  38 */     this.KeyZoom = new MCH_Key(MCH_Config.KeyZoom.prmInt);
/*     */     
/*  40 */     this.Keys = new MCH_Key[] { this.KeyUp, this.KeyDown, this.KeyRight, this.KeyLeft, this.KeySwitchMode, this.KeySwitchHovering, this.KeyUseWeapon, this.KeySwWeaponMode, this.KeySwitchWeapon1, this.KeySwitchWeapon2, this.KeyZoom, this.KeyCameraMode, this.KeyUnmount, this.KeyUnmountForce, this.KeyFlare, this.KeyExtra, this.KeyFreeLook, this.KeyGUI, this.KeyGearUpDown, this.KeyPutToRack, this.KeyDownFromRack };
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
/*     */   protected void update(EntityPlayer player, MCH_EntityHeli heli, boolean isPilot) {
/*  53 */     if (heli.getIsGunnerMode((Entity)player)) {
/*     */       
/*  55 */       MCH_SeatInfo seatInfo = heli.getSeatInfo((Entity)player);
/*  56 */       if (seatInfo != null)
/*     */       {
/*  58 */         setRotLimitPitch(seatInfo.minPitch, seatInfo.maxPitch, (Entity)player);
/*     */       }
/*     */     } 
/*     */     
/*  62 */     heli.updateCameraRotate(player.field_70177_z, player.field_70125_A);
/*  63 */     heli.updateRadar(5);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTick(boolean inGUI) {
/*  68 */     for (MCH_Key k : this.Keys) k.update();
/*     */     
/*  70 */     this.isBeforeRiding = this.isRiding;
/*     */     
/*  72 */     EntityClientPlayerMP entityClientPlayerMP = this.mc.field_71439_g;
/*  73 */     MCH_EntityHeli heli = null;
/*  74 */     boolean isPilot = true;
/*     */     
/*  76 */     if (entityClientPlayerMP != null)
/*     */     {
/*  78 */       if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityHeli) {
/*     */         
/*  80 */         heli = (MCH_EntityHeli)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*     */       }
/*  82 */       else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntitySeat) {
/*     */         
/*  84 */         MCH_EntitySeat seat = (MCH_EntitySeat)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*  85 */         if (seat.getParent() instanceof MCH_EntityHeli)
/*     */         {
/*  87 */           isPilot = false;
/*  88 */           heli = (MCH_EntityHeli)seat.getParent();
/*     */         }
/*     */       
/*  91 */       } else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityUavStation) {
/*     */         
/*  93 */         MCH_EntityUavStation uavStation = (MCH_EntityUavStation)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*  94 */         if (uavStation.getControlAircract() instanceof MCH_EntityHeli)
/*     */         {
/*  96 */           heli = (MCH_EntityHeli)uavStation.getControlAircract();
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 102 */     if (heli != null && heli.getAcInfo() != null) {
/*     */       
/* 104 */       update((EntityPlayer)entityClientPlayerMP, heli, isPilot);
/*     */       
/* 106 */       MCH_ViewEntityDummy viewEntityDummy = MCH_ViewEntityDummy.getInstance((World)this.mc.field_71441_e);
/* 107 */       viewEntityDummy.update(heli.camera);
/*     */       
/* 109 */       if (!inGUI) {
/*     */         
/* 111 */         if (!heli.isDestroyed())
/*     */         {
/* 113 */           playerControl((EntityPlayer)entityClientPlayerMP, heli, isPilot);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 118 */         playerControlInGUI((EntityPlayer)entityClientPlayerMP, heli, isPilot);
/*     */       } 
/*     */       
/* 121 */       boolean hideHand = true;
/*     */       
/* 123 */       if ((isPilot && heli.isAlwaysCameraView()) || heli.getIsGunnerMode((Entity)entityClientPlayerMP)) {
/*     */         
/* 125 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)viewEntityDummy);
/*     */       }
/*     */       else {
/*     */         
/* 129 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)entityClientPlayerMP);
/* 130 */         if (!isPilot && heli.getCurrentWeaponID((Entity)entityClientPlayerMP) < 0)
/*     */         {
/* 132 */           hideHand = false;
/*     */         }
/*     */       } 
/*     */       
/* 136 */       if (hideHand)
/*     */       {
/* 138 */         MCH_Lib.disableFirstPersonItemRender(entityClientPlayerMP.func_71045_bC());
/*     */       }
/*     */       
/* 141 */       this.isRiding = true;
/*     */     }
/*     */     else {
/*     */       
/* 145 */       this.isRiding = false;
/*     */     } 
/*     */ 
/*     */     
/* 149 */     if (this.isBeforeRiding || !this.isRiding)
/*     */     {
/*     */ 
/*     */       
/* 153 */       if (this.isBeforeRiding && !this.isRiding) {
/*     */         
/* 155 */         W_Reflection.setCameraRoll(0.0F);
/* 156 */         MCH_Lib.enableFirstPersonItemRender();
/* 157 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)entityClientPlayerMP);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void playerControlInGUI(EntityPlayer player, MCH_EntityHeli heli, boolean isPilot) {
/* 163 */     commonPlayerControlInGUI(player, (MCH_EntityAircraft)heli, isPilot, (MCH_PacketPlayerControlBase)new MCH_HeliPacketPlayerControl());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playerControl(EntityPlayer player, MCH_EntityHeli heli, boolean isPilot) {
/* 169 */     MCH_HeliPacketPlayerControl pc = new MCH_HeliPacketPlayerControl();
/* 170 */     boolean send = false;
/*     */     
/* 172 */     send = commonPlayerControl(player, (MCH_EntityAircraft)heli, isPilot, (MCH_PacketPlayerControlBase)pc);
/*     */     
/* 174 */     if (isPilot) {
/*     */ 
/*     */       
/* 177 */       if (this.KeyExtra.isKeyDown())
/*     */       {
/* 179 */         if (heli.getTowChainEntity() != null) {
/*     */           
/* 181 */           playSoundOK();
/* 182 */           pc.unhitchChainId = W_Entity.getEntityId((Entity)heli.getTowChainEntity());
/* 183 */           send = true;
/*     */         }
/* 185 */         else if (heli.canSwitchFoldBlades()) {
/*     */           
/* 187 */           if (heli.isFoldBlades()) {
/*     */             
/* 189 */             heli.unfoldBlades();
/* 190 */             pc.switchFold = 0;
/*     */           }
/*     */           else {
/*     */             
/* 194 */             heli.foldBlades();
/* 195 */             pc.switchFold = 1;
/*     */           } 
/* 197 */           send = true;
/* 198 */           playSoundOK();
/*     */         }
/*     */         else {
/*     */           
/* 202 */           playSoundNG();
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 207 */       if (this.KeySwitchHovering.isKeyDown())
/*     */       {
/* 209 */         if (heli.canSwitchHoveringMode())
/*     */         {
/* 211 */           pc.switchMode = heli.isHoveringMode() ? 2 : 3;
/* 212 */           heli.switchHoveringMode(!heli.isHoveringMode());
/* 213 */           send = true;
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 218 */           playSoundNG();
/*     */         }
/*     */       
/*     */       }
/* 222 */       else if (this.KeySwitchMode.isKeyDown())
/*     */       {
/* 224 */         if (heli.canSwitchGunnerMode())
/*     */         {
/* 226 */           pc.switchMode = heli.getIsGunnerMode((Entity)player) ? 0 : 1;
/* 227 */           heli.switchGunnerMode(!heli.getIsGunnerMode((Entity)player));
/* 228 */           send = true;
/*     */         
/*     */         }
/*     */         else
/*     */         {
/* 233 */           playSoundNG();
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 241 */     else if (this.KeySwitchMode.isKeyDown()) {
/*     */       
/* 243 */       if (heli.canSwitchGunnerModeOtherSeat(player)) {
/*     */         
/* 245 */         heli.switchGunnerModeOtherSeat(player);
/* 246 */         send = true;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 251 */         playSoundNG();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 257 */     if (this.KeyZoom.isKeyDown()) {
/*     */ 
/*     */       
/* 260 */       boolean isUav = (heli.isUAV() && !heli.getAcInfo().haveHatch());
/* 261 */       if (heli.getIsGunnerMode((Entity)player) || isUav) {
/*     */         
/* 263 */         heli.zoomCamera();
/* 264 */         playSound("zoom", 0.5F, 1.0F);
/*     */       
/*     */       }
/* 267 */       else if (isPilot) {
/*     */         
/* 269 */         if (heli.getAcInfo().haveHatch())
/*     */         {
/* 271 */           if (heli.canFoldHatch()) {
/*     */             
/* 273 */             pc.switchHatch = 2;
/* 274 */             send = true;
/*     */           }
/* 276 */           else if (heli.canUnfoldHatch()) {
/*     */             
/* 278 */             pc.switchHatch = 1;
/* 279 */             send = true;
/*     */           }
/*     */           else {
/*     */             
/* 283 */             playSoundNG();
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 289 */     if (send)
/*     */     {
/* 291 */       W_Network.sendToServer((W_PacketBase)pc);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\helicopter\MCH_ClientHeliTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */