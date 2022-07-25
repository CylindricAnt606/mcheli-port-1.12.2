/*     */ package mcheli.mcheli.lweapon;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import mcheli.MCH_ClientTickHandlerBase;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.lweapon.MCH_ItemLightWeaponBase;
/*     */ import mcheli.lweapon.MCH_PacketLightWeaponPlayerControl;
/*     */ import mcheli.weapon.MCH_WeaponGuidanceSystem;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_Network;
/*     */ import mcheli.wrapper.W_PacketBase;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.glu.GLU;
/*     */ 
/*     */ public class MCH_ClientLightWeaponTickHandler extends MCH_ClientTickHandlerBase {
/*  30 */   private static FloatBuffer screenPos = BufferUtils.createFloatBuffer(3);
/*  31 */   private static FloatBuffer screenPosBB = BufferUtils.createFloatBuffer(3);
/*  32 */   private static FloatBuffer matModel = BufferUtils.createFloatBuffer(16);
/*  33 */   private static FloatBuffer matProjection = BufferUtils.createFloatBuffer(16);
/*  34 */   private static IntBuffer matViewport = BufferUtils.createIntBuffer(16);
/*     */   
/*     */   protected boolean isHeldItem = false;
/*     */   protected boolean isBeforeHeldItem = false;
/*  38 */   protected EntityPlayer prevThePlayer = null;
/*  39 */   protected ItemStack prevItemStack = null;
/*     */   
/*     */   public MCH_Key KeyAttack;
/*     */   
/*     */   public MCH_Key KeyUseWeapon;
/*     */   public MCH_Key KeySwWeaponMode;
/*     */   public MCH_Key KeyZoom;
/*     */   public MCH_Key KeyCameraMode;
/*     */   public MCH_Key[] Keys;
/*     */   protected static MCH_WeaponBase weapon;
/*     */   public static int reloadCount;
/*     */   public static int lockonSoundCount;
/*     */   public static int weaponMode;
/*     */   public static int selectedZoom;
/*  53 */   public static Entity markEntity = null;
/*  54 */   public static Vec3 markPos = Vec3.func_72443_a(0.0D, 0.0D, 0.0D);
/*     */   
/*  56 */   public static MCH_WeaponGuidanceSystem gs = new MCH_WeaponGuidanceSystem();
/*  57 */   public static double lockRange = 120.0D;
/*     */ 
/*     */   
/*     */   public MCH_ClientLightWeaponTickHandler(Minecraft minecraft, MCH_Config config) {
/*  61 */     super(minecraft);
/*  62 */     updateKeybind(config);
/*     */     
/*  64 */     this; gs.canLockInAir = false;
/*  65 */     this; gs.canLockOnGround = false;
/*  66 */     this; gs.canLockInWater = false;
/*  67 */     this; gs.setLockCountMax(40);
/*  68 */     this; gs.lockRange = 120.0D;
/*     */     
/*  70 */     this; lockonSoundCount = 0;
/*  71 */     initWeaponParam(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void markEntity(Entity entity, double x, double y, double z) {
/*  76 */     if (gs.getLockingEntity() == entity) {
/*     */       
/*  78 */       GL11.glGetFloat(2982, matModel);
/*  79 */       GL11.glGetFloat(2983, matProjection);
/*  80 */       GL11.glGetInteger(2978, matViewport);
/*  81 */       GLU.gluProject((float)x, (float)y, (float)z, matModel, matProjection, matViewport, screenPos);
/*     */       
/*  83 */       MCH_AircraftInfo i = (entity instanceof MCH_EntityAircraft) ? ((MCH_EntityAircraft)entity).getAcInfo() : null;
/*  84 */       float w = (i != null) ? i.markerWidth : ((entity.field_70130_N > entity.field_70131_O) ? entity.field_70130_N : entity.field_70131_O);
/*  85 */       float h = (i != null) ? i.markerHeight : entity.field_70131_O;
/*  86 */       GLU.gluProject((float)x + w, (float)y + h, (float)z + w, matModel, matProjection, matViewport, screenPosBB);
/*     */       
/*  88 */       markEntity = entity;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Vec3 getMartEntityPos() {
/*  93 */     if (gs.getLockingEntity() == markEntity && markEntity != null)
/*     */     {
/*  95 */       return Vec3.func_72443_a(screenPos.get(0), screenPos.get(1), screenPos.get(2));
/*     */     }
/*  97 */     return null;
/*     */   }
/*     */   
/*     */   public static Vec3 getMartEntityBBPos() {
/* 101 */     if (gs.getLockingEntity() == markEntity && markEntity != null)
/*     */     {
/* 103 */       return Vec3.func_72443_a(screenPosBB.get(0), screenPosBB.get(1), screenPosBB.get(2));
/*     */     }
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initWeaponParam(EntityPlayer player) {
/* 110 */     this; reloadCount = 0;
/* 111 */     this; weaponMode = 0;
/* 112 */     this; selectedZoom = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateKeybind(MCH_Config config) {
/* 117 */     this.KeyAttack = new MCH_Key(MCH_Config.KeyAttack.prmInt);
/* 118 */     this.KeyUseWeapon = new MCH_Key(MCH_Config.KeyUseWeapon.prmInt);
/* 119 */     this.KeySwWeaponMode = new MCH_Key(MCH_Config.KeySwWeaponMode.prmInt);
/* 120 */     this.KeyZoom = new MCH_Key(MCH_Config.KeyZoom.prmInt);
/* 121 */     this.KeyCameraMode = new MCH_Key(MCH_Config.KeyCameraMode.prmInt);
/*     */     
/* 123 */     this.Keys = new MCH_Key[] { this.KeyAttack, this.KeyUseWeapon, this.KeySwWeaponMode, this.KeyZoom, this.KeyCameraMode };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onTick(boolean inGUI) {
/* 131 */     for (MCH_Key k : this.Keys) k.update();
/*     */     
/* 133 */     this.isBeforeHeldItem = this.isHeldItem;
/*     */     
/* 135 */     EntityClientPlayerMP entityClientPlayerMP = this.mc.field_71439_g;
/* 136 */     if (this.prevThePlayer == null || this.prevThePlayer != entityClientPlayerMP) {
/*     */       
/* 138 */       initWeaponParam((EntityPlayer)entityClientPlayerMP);
/* 139 */       this.prevThePlayer = (EntityPlayer)entityClientPlayerMP;
/*     */     } 
/*     */     
/* 142 */     ItemStack is = (entityClientPlayerMP != null) ? entityClientPlayerMP.func_70694_bm() : null;
/*     */     
/* 144 */     if (entityClientPlayerMP == null || ((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof mcheli.gltd.MCH_EntityGLTD || ((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntityAircraft)
/*     */     {
/*     */ 
/*     */       
/* 148 */       is = null;
/*     */     }
/*     */     
/* 151 */     this; if (gs.getLockingEntity() == null)
/*     */     {
/* 153 */       markEntity = null;
/*     */     }
/*     */     
/* 156 */     if (is != null && is.func_77973_b() instanceof MCH_ItemLightWeaponBase) {
/*     */       
/* 158 */       MCH_ItemLightWeaponBase lweapon = (MCH_ItemLightWeaponBase)is.func_77973_b();
/*     */       
/* 160 */       if (this.prevItemStack == null || (!this.prevItemStack.func_77969_a(is) && !this.prevItemStack.func_77977_a().equals(is.func_77977_a()))) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 165 */         initWeaponParam((EntityPlayer)entityClientPlayerMP);
/*     */         
/* 167 */         this; weapon = MCH_WeaponCreator.createWeapon(((EntityPlayer)entityClientPlayerMP).field_70170_p, MCH_ItemLightWeaponBase.getName(is), Vec3.func_72443_a(0.0D, 0.0D, 0.0D), 0.0F, 0.0F, null, false);
/*     */ 
/*     */ 
/*     */         
/* 171 */         this; if (weapon != null) { this; if (weapon.getInfo() != null) { this; if (weapon.getGuidanceSystem() != null) {
/*     */               
/* 173 */               this; this; gs = weapon.getGuidanceSystem();
/*     */             }  }
/*     */            }
/*     */       
/* 177 */       }  this; if (weapon != null) { this; if (gs == null)
/*     */           return;  } else { return; }
/* 179 */        this; gs.setWorld(((EntityPlayer)entityClientPlayerMP).field_70170_p);
/* 180 */       this; gs.lockRange = lockRange;
/*     */       
/* 182 */       if (entityClientPlayerMP.func_71057_bx() > 10) {
/*     */         
/* 184 */         this; selectedZoom %= (weapon.getInfo()).zoom.length;
/* 185 */         this; W_Reflection.setCameraZoom((weapon.getInfo()).zoom[selectedZoom]);
/*     */       }
/*     */       else {
/*     */         
/* 189 */         W_Reflection.restoreCameraZoom();
/*     */       } 
/*     */       
/* 192 */       if (is.func_77960_j() < is.func_77958_k()) {
/*     */         
/* 194 */         if (entityClientPlayerMP.func_71057_bx() > 10) {
/*     */           
/* 196 */           this; gs.lock((Entity)entityClientPlayerMP);
/* 197 */           this; if (gs.getLockCount() > 0) {
/*     */             
/* 199 */             this; if (lockonSoundCount > 0)
/*     */             {
/* 201 */               this; lockonSoundCount--;
/*     */             }
/*     */             else
/*     */             {
/* 205 */               this; lockonSoundCount = 7;
/* 206 */               this; this; this; lockonSoundCount = (int)(lockonSoundCount * (1.0D - gs.getLockCount() / gs.getLockCountMax()));
/*     */               
/* 208 */               this; if (lockonSoundCount < 3) {
/*     */                 
/* 210 */                 this; lockonSoundCount = 2;
/*     */               } 
/*     */               
/* 213 */               W_McClient.MOD_playSoundFX("lockon", 1.0F, 1.0F);
/*     */             }
/*     */           
/*     */           } 
/*     */         } else {
/*     */           
/* 219 */           W_Reflection.restoreCameraZoom();
/* 220 */           this; gs.clearLock();
/*     */         } 
/* 222 */         this; reloadCount = 0;
/*     */       }
/*     */       else {
/*     */         
/* 226 */         this; lockonSoundCount = 0;
/* 227 */         if (W_EntityPlayer.hasItem((EntityPlayer)entityClientPlayerMP, (Item)lweapon.bullet) && entityClientPlayerMP.func_71052_bv() <= 0) {
/*     */           
/* 229 */           this; if (reloadCount == 10)
/*     */           {
/* 231 */             W_McClient.MOD_playSoundFX("fim92_reload", 1.0F, 1.0F);
/*     */           }
/*     */           
/* 234 */           int RELOAD_CNT = 40;
/*     */           
/* 236 */           this; if (reloadCount < 40)
/*     */           {
/* 238 */             this; reloadCount++;
/* 239 */             this; if (reloadCount == 40)
/*     */             {
/* 241 */               onCompleteReload();
/*     */             }
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 247 */           this; reloadCount = 0;
/*     */         } 
/*     */         
/* 250 */         this; gs.clearLock();
/*     */       } 
/*     */       
/* 253 */       if (!inGUI) playerControl((EntityPlayer)entityClientPlayerMP, is, (MCH_ItemLightWeaponBase)is.func_77973_b());
/*     */       
/* 255 */       this.isHeldItem = MCH_ItemLightWeaponBase.isHeld((EntityPlayer)entityClientPlayerMP);
/*     */     }
/*     */     else {
/*     */       
/* 259 */       this; lockonSoundCount = 0;
/* 260 */       this; reloadCount = 0;
/* 261 */       this.isHeldItem = false;
/*     */     } 
/*     */     
/* 264 */     if (this.isBeforeHeldItem != this.isHeldItem) {
/*     */       
/* 266 */       MCH_Lib.DbgLog(true, "LWeapon cancel", new Object[0]);
/* 267 */       if (!this.isHeldItem) {
/*     */         
/* 269 */         if (getPotionNightVisionDuration((EntityPlayer)entityClientPlayerMP) < 250) {
/*     */           
/* 271 */           MCH_PacketLightWeaponPlayerControl pc = new MCH_PacketLightWeaponPlayerControl();
/* 272 */           pc.camMode = 1;
/* 273 */           W_Network.sendToServer((W_PacketBase)pc);
/*     */           
/* 275 */           entityClientPlayerMP.func_70618_n(Potion.field_76439_r.func_76396_c());
/*     */         } 
/* 277 */         W_Reflection.restoreCameraZoom();
/*     */       } 
/*     */     } 
/*     */     
/* 281 */     this.prevItemStack = is;
/*     */     
/* 283 */     this; gs.update();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onCompleteReload() {
/* 288 */     MCH_PacketLightWeaponPlayerControl pc = new MCH_PacketLightWeaponPlayerControl();
/* 289 */     pc.cmpReload = 1;
/* 290 */     W_Network.sendToServer((W_PacketBase)pc);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playerControl(EntityPlayer player, ItemStack is, MCH_ItemLightWeaponBase item) {
/* 296 */     MCH_PacketLightWeaponPlayerControl pc = new MCH_PacketLightWeaponPlayerControl();
/* 297 */     boolean send = false;
/*     */ 
/*     */     
/* 300 */     boolean autoShot = false;
/* 301 */     if (MCH_Config.LWeaponAutoFire.prmBool)
/*     */     {
/* 303 */       if (is.func_77960_j() < is.func_77958_k() && gs.isLockComplete())
/*     */       {
/* 305 */         autoShot = true;
/*     */       }
/*     */     }
/*     */ 
/*     */     
/* 310 */     if (this.KeySwWeaponMode.isKeyDown()) { this; if (weapon.numMode > 1) {
/*     */         
/* 312 */         this; this; this; weaponMode = (weaponMode + 1) % weapon.numMode;
/* 313 */         W_McClient.MOD_playSoundFX("pi", 0.5F, 0.9F);
/*     */       }  }
/*     */ 
/*     */     
/* 317 */     if (this.KeyAttack.isKeyPress() || autoShot) {
/*     */       
/* 319 */       boolean result = false;
/* 320 */       if (is.func_77960_j() < is.func_77958_k())
/*     */       {
/*     */         
/* 323 */         if (gs.isLockComplete()) {
/*     */           
/* 325 */           boolean canFire = true;
/* 326 */           if (weaponMode > 0 && gs.getTargetEntity() != null) {
/*     */             
/* 328 */             double dx = (gs.getTargetEntity()).field_70165_t - player.field_70165_t;
/* 329 */             double dz = (gs.getTargetEntity()).field_70161_v - player.field_70161_v;
/* 330 */             canFire = (Math.sqrt(dx * dx + dz * dz) >= 40.0D);
/*     */           } 
/* 332 */           if (canFire) {
/*     */             
/* 334 */             pc.useWeapon = true;
/* 335 */             pc.useWeaponOption1 = W_Entity.getEntityId(gs.lastLockEntity);
/* 336 */             this; pc.useWeaponOption2 = weaponMode;
/* 337 */             pc.useWeaponPosX = player.field_70165_t;
/* 338 */             pc.useWeaponPosY = player.field_70163_u;
/* 339 */             pc.useWeaponPosZ = player.field_70161_v;
/* 340 */             gs.clearLock();
/* 341 */             send = true;
/* 342 */             result = true;
/*     */           } 
/*     */         } 
/*     */       }
/* 346 */       if (this.KeyAttack.isKeyDown())
/*     */       {
/* 348 */         if (!result && player.func_71057_bx() > 5)
/*     */         {
/* 350 */           playSoundNG();
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 357 */     if (this.KeyZoom.isKeyDown()) {
/*     */       
/* 359 */       this; int prevZoom = selectedZoom;
/* 360 */       this; this; this; selectedZoom = (selectedZoom + 1) % (weapon.getInfo()).zoom.length;
/* 361 */       this; if (prevZoom != selectedZoom)
/*     */       {
/* 363 */         playSound("zoom", 0.5F, 1.0F);
/*     */       }
/*     */     } 
/* 366 */     if (this.KeyCameraMode.isKeyDown()) {
/*     */       
/* 368 */       PotionEffect pe = player.func_70660_b(Potion.field_76439_r);
/* 369 */       MCH_Lib.DbgLog(true, "LWeapon NV %s", new Object[] { (pe != null) ? "ON->OFF" : "OFF->ON" });
/* 370 */       if (pe != null) {
/*     */         
/* 372 */         player.func_70618_n(Potion.field_76439_r.func_76396_c());
/* 373 */         pc.camMode = 1;
/* 374 */         send = true;
/* 375 */         W_McClient.MOD_playSoundFX("pi", 0.5F, 0.9F);
/*     */ 
/*     */       
/*     */       }
/* 379 */       else if (player.func_71057_bx() > 60) {
/*     */         
/* 381 */         pc.camMode = 2;
/* 382 */         send = true;
/* 383 */         W_McClient.MOD_playSoundFX("pi", 0.5F, 0.9F);
/*     */       }
/*     */       else {
/*     */         
/* 387 */         playSoundNG();
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 398 */     if (send)
/*     */     {
/* 400 */       W_Network.sendToServer((W_PacketBase)pc);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getPotionNightVisionDuration(EntityPlayer player) {
/* 406 */     PotionEffect cpe = player.func_70660_b(Potion.field_76439_r);
/* 407 */     return (player == null || cpe == null) ? 0 : cpe.func_76459_b();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\lweapon\MCH_ClientLightWeaponTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */