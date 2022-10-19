/*     */ package mcheli.mcheli.gltd;
/*     */ 
/*     */ import mcheli.MCH_ClientTickHandlerBase;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_ViewEntityDummy;
/*     */ import mcheli.gltd.MCH_EntityGLTD;
/*     */ import mcheli.gltd.MCH_PacketGLTDPlayerControl;
/*     */ import mcheli.wrapper.W_Network;
/*     */ import mcheli.wrapper.W_PacketBase;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class MCH_ClientGLTDTickHandler
/*     */   extends MCH_ClientTickHandlerBase
/*     */ {
/*     */   protected boolean isRiding = false;
/*     */   protected boolean isBeforeRiding = false;
/*     */   public MCH_Key KeyUseWeapon;
/*     */   public MCH_Key KeySwitchWeapon1;
/*     */   public MCH_Key KeySwitchWeapon2;
/*     */   
/*     */   public MCH_ClientGLTDTickHandler(Minecraft minecraft, MCH_Config config) {
/*  29 */     super(minecraft);
/*  30 */     updateKeybind(config);
/*     */   }
/*     */   public MCH_Key KeySwWeaponMode; public MCH_Key KeyZoom; public MCH_Key KeyCameraMode; public MCH_Key KeyUnmount; public MCH_Key KeyUnmount_1_6; public MCH_Key[] Keys;
/*     */   public void updateKeybind(MCH_Config config) {
/*  34 */     this.KeyUseWeapon = new MCH_Key(MCH_Config.KeyUseWeapon.prmInt);
/*  35 */     this.KeySwitchWeapon1 = new MCH_Key(MCH_Config.KeySwitchWeapon1.prmInt);
/*  36 */     this.KeySwitchWeapon2 = new MCH_Key(MCH_Config.KeySwitchWeapon2.prmInt);
/*  37 */     this.KeySwWeaponMode = new MCH_Key(MCH_Config.KeySwWeaponMode.prmInt);
/*  38 */     this.KeyZoom = new MCH_Key(MCH_Config.KeyZoom.prmInt);
/*  39 */     this.KeyCameraMode = new MCH_Key(MCH_Config.KeyCameraMode.prmInt);
/*  40 */     this.KeyUnmount = new MCH_Key(MCH_Config.KeyUnmount.prmInt);
/*  41 */     this.KeyUnmount_1_6 = new MCH_Key(42);
/*     */     
/*  43 */     this.Keys = new MCH_Key[] { this.KeyUseWeapon, this.KeySwWeaponMode, this.KeySwitchWeapon1, this.KeySwitchWeapon2, this.KeyZoom, this.KeyCameraMode, this.KeyUnmount, this.KeyUnmount_1_6 };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateGLTD(EntityPlayer player, MCH_EntityGLTD gltd) {
/*  52 */     if (player.field_70125_A < -70.0F) player.field_70125_A = -70.0F; 
/*  53 */     if (player.field_70125_A > 70.0F) player.field_70125_A = 70.0F;
/*     */     
/*  55 */     float yaw = gltd.field_70177_z;
/*  56 */     if (player.field_70177_z < yaw - 70.0F) player.field_70177_z = yaw - 70.0F; 
/*  57 */     if (player.field_70177_z > yaw + 70.0F) player.field_70177_z = yaw + 70.0F;
/*     */     
/*  59 */     gltd.camera.rotationYaw = player.field_70177_z;
/*  60 */     gltd.camera.rotationPitch = player.field_70125_A;
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
/*  71 */     MCH_ViewEntityDummy viewEntityDummy = null;
/*     */     
/*  73 */     if (entityClientPlayerMP != null && ((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityGLTD) {
/*     */       
/*  75 */       MCH_EntityGLTD gltd = (MCH_EntityGLTD)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*     */       
/*  77 */       updateGLTD((EntityPlayer)entityClientPlayerMP, gltd);
/*     */       
/*  79 */       MCH_Lib.disableFirstPersonItemRender(entityClientPlayerMP.func_71045_bC());
/*     */       
/*  81 */       viewEntityDummy = MCH_ViewEntityDummy.getInstance((World)this.mc.field_71441_e);
/*  82 */       viewEntityDummy.update(gltd.camera);
/*     */       
/*  84 */       if (!inGUI) playerControl((EntityPlayer)entityClientPlayerMP, gltd);
/*     */ 
/*     */       
/*  87 */       MCH_Lib.setRenderViewEntity((EntityLivingBase)viewEntityDummy);
/*     */       
/*  89 */       this.isRiding = true;
/*     */     }
/*     */     else {
/*     */       
/*  93 */       this.isRiding = false;
/*     */     } 
/*     */ 
/*     */     
/*  97 */     if (this.isBeforeRiding != this.isRiding)
/*     */     {
/*  99 */       if (this.isRiding) {
/*     */         
/* 101 */         if (viewEntityDummy != null)
/*     */         {
/* 103 */           viewEntityDummy.field_70169_q = viewEntityDummy.field_70165_t;
/* 104 */           viewEntityDummy.field_70167_r = viewEntityDummy.field_70163_u;
/* 105 */           viewEntityDummy.field_70166_s = viewEntityDummy.field_70161_v;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 110 */         MCH_Lib.enableFirstPersonItemRender();
/* 111 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)entityClientPlayerMP);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playerControl(EntityPlayer player, MCH_EntityGLTD gltd) {
/* 120 */     MCH_PacketGLTDPlayerControl pc = new MCH_PacketGLTDPlayerControl();
/* 121 */     boolean send = false;
/*     */     
/* 123 */     if (this.KeyUnmount.isKeyDown()) {
/*     */       
/* 125 */       pc.unmount = true;
/* 126 */       send = true;
/*     */     } 
/*     */ 
/*     */     
/* 130 */     if (!this.KeySwitchWeapon1.isKeyDown() || !this.KeySwitchWeapon2.isKeyDown())
/*     */     {
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
/* 142 */       if (this.KeyUseWeapon.isKeyPress())
/*     */       {
/*     */         
/* 145 */         if (gltd.useCurrentWeapon(0, 0)) {
/*     */           
/* 147 */           pc.useWeapon = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 154 */           send = true;
/*     */         }
/* 156 */         else if (this.KeyUseWeapon.isKeyDown()) {
/*     */           
/* 158 */           playSoundNG();
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 164 */     float prevZoom = gltd.camera.getCameraZoom();
/* 165 */     if (this.KeyZoom.isKeyPress() && !this.KeySwWeaponMode.isKeyPress())
/*     */     {
/* 167 */       gltd.zoomCamera(0.1F * gltd.camera.getCameraZoom());
/*     */     }
/* 169 */     if (!this.KeyZoom.isKeyPress() && this.KeySwWeaponMode.isKeyPress())
/*     */     {
/* 171 */       gltd.zoomCamera(-0.1F * gltd.camera.getCameraZoom());
/*     */     }
/* 173 */     if (prevZoom != gltd.camera.getCameraZoom())
/*     */     {
/* 175 */       playSound("zoom", 0.1F, (prevZoom < gltd.camera.getCameraZoom()) ? 1.0F : 0.85F);
/*     */     }
/*     */ 
/*     */     
/* 179 */     if (this.KeyCameraMode.isKeyDown()) {
/*     */       
/* 181 */       int beforeMode = gltd.camera.getMode(0);
/* 182 */       gltd.camera.setMode(0, gltd.camera.getMode(0) + 1);
/* 183 */       int mode = gltd.camera.getMode(0);
/* 184 */       if (mode != beforeMode) {
/*     */         
/* 186 */         pc.switchCameraMode = (byte)mode;
/* 187 */         playSoundOK();
/* 188 */         send = true;
/*     */       } 
/*     */     } 
/*     */     
/* 192 */     if (send)
/*     */     {
/* 194 */       W_Network.sendToServer((W_PacketBase)pc);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gltd\MCH_ClientGLTDTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */