/*     */ package mcheli.mcheli.vehicle;
/*     */ 
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_ViewEntityDummy;
/*     */ import mcheli.aircraft.MCH_AircraftClientTickHandler;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.aircraft.MCH_PacketPlayerControlBase;
/*     */ import mcheli.vehicle.MCH_EntityVehicle;
/*     */ import mcheli.vehicle.MCH_PacketVehiclePlayerControl;
/*     */ import mcheli.vehicle.MCH_VehicleInfo;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_Network;
/*     */ import mcheli.wrapper.W_PacketBase;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_ClientVehicleTickHandler extends MCH_AircraftClientTickHandler {
/*     */   public MCH_Key KeySwitchMode;
/*     */   public MCH_Key KeySwitchHovering;
/*     */   
/*     */   public MCH_ClientVehicleTickHandler(Minecraft minecraft, MCH_Config config) {
/*  29 */     super(minecraft, config);
/*  30 */     updateKeybind(config);
/*     */   }
/*     */   public MCH_Key KeyZoom; public MCH_Key KeyExtra; public MCH_Key[] Keys;
/*     */   public void updateKeybind(MCH_Config config) {
/*  34 */     super.updateKeybind(config);
/*  35 */     this.KeySwitchMode = new MCH_Key(MCH_Config.KeySwitchMode.prmInt);
/*  36 */     this.KeySwitchHovering = new MCH_Key(MCH_Config.KeySwitchHovering.prmInt);
/*  37 */     this.KeyZoom = new MCH_Key(MCH_Config.KeyZoom.prmInt);
/*  38 */     this.KeyExtra = new MCH_Key(MCH_Config.KeyExtra.prmInt);
/*     */     
/*  40 */     this.Keys = new MCH_Key[] { this.KeyUp, this.KeyDown, this.KeyRight, this.KeyLeft, this.KeySwitchMode, this.KeySwitchHovering, this.KeyUseWeapon, this.KeySwWeaponMode, this.KeySwitchWeapon1, this.KeySwitchWeapon2, this.KeyZoom, this.KeyCameraMode, this.KeyUnmount, this.KeyUnmountForce, this.KeyFlare, this.KeyExtra, this.KeyGUI };
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
/*     */   protected void update(EntityPlayer player, MCH_EntityVehicle vehicle, MCH_VehicleInfo info) {
/*  52 */     if (info != null)
/*     */     {
/*  54 */       setRotLimitPitch(info.minRotationPitch, info.maxRotationPitch, (Entity)player);
/*     */     }
/*     */     
/*  57 */     vehicle.updateCameraRotate(player.field_70177_z, player.field_70125_A);
/*  58 */     vehicle.updateRadar(5);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onTick(boolean inGUI) {
/*  63 */     for (MCH_Key k : this.Keys) k.update();
/*     */     
/*  65 */     this.isBeforeRiding = this.isRiding;
/*     */     
/*  67 */     EntityClientPlayerMP entityClientPlayerMP = this.mc.field_71439_g;
/*  68 */     MCH_EntityVehicle vehicle = null;
/*  69 */     boolean isPilot = true;
/*     */     
/*  71 */     if (entityClientPlayerMP != null)
/*     */     {
/*  73 */       if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityVehicle) {
/*     */         
/*  75 */         vehicle = (MCH_EntityVehicle)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*     */       }
/*  77 */       else if (((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntitySeat) {
/*     */         
/*  79 */         MCH_EntitySeat seat = (MCH_EntitySeat)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*  80 */         if (seat.getParent() instanceof MCH_EntityVehicle) {
/*     */           
/*  82 */           isPilot = false;
/*  83 */           vehicle = (MCH_EntityVehicle)seat.getParent();
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  89 */     if (vehicle != null && vehicle.getAcInfo() != null) {
/*     */       
/*  91 */       MCH_Lib.disableFirstPersonItemRender(entityClientPlayerMP.func_71045_bC());
/*     */       
/*  93 */       update((EntityPlayer)entityClientPlayerMP, vehicle, vehicle.getVehicleInfo());
/*     */ 
/*     */ 
/*     */       
/*  97 */       MCH_ViewEntityDummy viewEntityDummy = MCH_ViewEntityDummy.getInstance((World)this.mc.field_71441_e);
/*  98 */       viewEntityDummy.update(vehicle.camera);
/*     */       
/* 100 */       if (!inGUI) {
/*     */         
/* 102 */         if (!vehicle.isDestroyed())
/*     */         {
/* 104 */           playerControl((EntityPlayer)entityClientPlayerMP, vehicle, isPilot);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 109 */         playerControlInGUI((EntityPlayer)entityClientPlayerMP, vehicle, isPilot);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       MCH_Lib.setRenderViewEntity((EntityLivingBase)viewEntityDummy);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 122 */       this.isRiding = true;
/*     */     }
/*     */     else {
/*     */       
/* 126 */       this.isRiding = false;
/*     */     } 
/*     */ 
/*     */     
/* 130 */     if (this.isBeforeRiding || !this.isRiding)
/*     */     {
/*     */ 
/*     */       
/* 134 */       if (this.isBeforeRiding && !this.isRiding) {
/*     */         
/* 136 */         MCH_Lib.enableFirstPersonItemRender();
/* 137 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)entityClientPlayerMP);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void playerControlInGUI(EntityPlayer player, MCH_EntityVehicle vehicle, boolean isPilot) {
/* 143 */     commonPlayerControlInGUI(player, (MCH_EntityAircraft)vehicle, isPilot, (MCH_PacketPlayerControlBase)new MCH_PacketVehiclePlayerControl());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playerControl(EntityPlayer player, MCH_EntityVehicle vehicle, boolean isPilot) {
/* 149 */     MCH_PacketVehiclePlayerControl pc = new MCH_PacketVehiclePlayerControl();
/* 150 */     boolean send = false;
/*     */     
/* 152 */     send = commonPlayerControl(player, (MCH_EntityAircraft)vehicle, isPilot, (MCH_PacketPlayerControlBase)pc);
/*     */ 
/*     */     
/* 155 */     if (this.KeyExtra.isKeyDown())
/*     */     {
/* 157 */       if (vehicle.getTowChainEntity() != null) {
/*     */         
/* 159 */         playSoundOK();
/* 160 */         pc.unhitchChainId = W_Entity.getEntityId((Entity)vehicle.getTowChainEntity());
/* 161 */         send = true;
/*     */       }
/*     */       else {
/*     */         
/* 165 */         playSoundNG();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 170 */     if (!this.KeySwitchHovering.isKeyDown())
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 177 */       if (this.KeySwitchMode.isKeyDown());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 183 */     if (this.KeyZoom.isKeyDown())
/*     */     {
/* 185 */       if (vehicle.canZoom()) {
/*     */         
/* 187 */         vehicle.zoomCamera();
/* 188 */         playSound("zoom", 0.5F, 1.0F);
/*     */       }
/* 190 */       else if (vehicle.getAcInfo().haveHatch()) {
/*     */         
/* 192 */         if (vehicle.canFoldHatch()) {
/*     */           
/* 194 */           pc.switchHatch = 2;
/* 195 */           send = true;
/*     */         }
/* 197 */         else if (vehicle.canUnfoldHatch()) {
/*     */           
/* 199 */           pc.switchHatch = 1;
/* 200 */           send = true;
/*     */         }
/*     */         else {
/*     */           
/* 204 */           playSoundNG();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 209 */     if (send)
/*     */     {
/* 211 */       W_Network.sendToServer((W_PacketBase)pc);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\vehicle\MCH_ClientVehicleTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */