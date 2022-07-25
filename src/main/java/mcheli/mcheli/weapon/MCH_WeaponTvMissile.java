/*     */ package mcheli.mcheli.weapon;
/*     */ import mcheli.weapon.MCH_EntityTvMissile;
/*     */ import mcheli.weapon.MCH_WeaponBase;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponParam;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_WeaponTvMissile extends MCH_WeaponBase {
/*  13 */   protected MCH_EntityTvMissile lastShotTvMissile = null;
/*  14 */   protected Entity lastShotEntity = null;
/*     */   
/*     */   protected boolean isTVGuided = false;
/*     */   
/*     */   public MCH_WeaponTvMissile(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/*  19 */     super(w, v, yaw, pitch, nm, wi);
/*  20 */     this.power = 32;
/*  21 */     this.acceleration = 2.0F;
/*  22 */     this.explosionPower = 4;
/*  23 */     this.interval = -100;
/*  24 */     if (w.field_72995_K) this.interval -= 10; 
/*  25 */     this.numMode = 2;
/*  26 */     this.lastShotEntity = null;
/*  27 */     this.lastShotTvMissile = null;
/*  28 */     this.isTVGuided = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  34 */     String opt = "";
/*  35 */     if (getCurrentMode() == 0) opt = " [TV]"; 
/*  36 */     if (getCurrentMode() == 2) opt = " [TA]"; 
/*  37 */     return super.getName() + opt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update(int countWait) {
/*  43 */     super.update(countWait);
/*     */     
/*  45 */     if (!this.worldObj.field_72995_K) {
/*     */ 
/*     */       
/*  48 */       if (this.isTVGuided)
/*     */       {
/*     */         
/*  51 */         if (this.tick <= 9) {
/*     */           
/*  53 */           if (this.tick % 3 == 0)
/*     */           {
/*  55 */             if (this.lastShotTvMissile != null && !this.lastShotTvMissile.field_70128_L && this.lastShotEntity != null && !this.lastShotEntity.field_70128_L)
/*     */             {
/*     */               
/*  58 */               MCH_PacketNotifyTVMissileEntity.send(W_Entity.getEntityId(this.lastShotEntity), W_Entity.getEntityId((Entity)this.lastShotTvMissile));
/*     */             }
/*     */           }
/*     */ 
/*     */           
/*  63 */           if (this.tick == 9) {
/*     */             
/*  65 */             this.lastShotEntity = null;
/*  66 */             this.lastShotTvMissile = null;
/*     */           } 
/*     */         } 
/*     */       }
/*  70 */       if (this.tick <= 2)
/*     */       {
/*  72 */         if (this.lastShotEntity instanceof MCH_EntityAircraft)
/*     */         {
/*  74 */           ((MCH_EntityAircraft)this.lastShotEntity).setTVMissile(this.lastShotTvMissile);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shot(MCH_WeaponParam prm) {
/*  82 */     if (this.worldObj.field_72995_K) {
/*  83 */       return shotClient(prm.entity, prm.user);
/*     */     }
/*  85 */     return shotServer(prm);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean shotClient(Entity entity, Entity user) {
/*  91 */     this.optionParameter2 = 0;
/*  92 */     this.optionParameter1 = getCurrentMode();
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean shotServer(MCH_WeaponParam prm) {
/*  99 */     float yaw = prm.user.field_70177_z + this.fixRotationYaw;
/* 100 */     float pitch = prm.user.field_70125_A + this.fixRotationPitch;
/*     */     
/* 102 */     double tX = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/* 104 */     double tZ = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/* 106 */     double tY = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/*     */     
/* 108 */     this.isTVGuided = (prm.option1 == 0);
/* 109 */     float acr = this.acceleration;
/* 110 */     if (!this.isTVGuided)
/*     */     {
/* 112 */       acr = (float)(acr * 1.5D);
/*     */     }
/*     */     
/* 115 */     MCH_EntityTvMissile e = new MCH_EntityTvMissile(this.worldObj, prm.posX, prm.posY, prm.posZ, tX, tY, tZ, yaw, pitch, acr);
/*     */     
/* 117 */     e.setName(this.name);
/* 118 */     e.setParameterFromWeapon(this, prm.entity, prm.user);
/*     */     
/* 120 */     this.lastShotEntity = prm.entity;
/* 121 */     this.lastShotTvMissile = e;
/*     */     
/* 123 */     this.worldObj.func_72838_d((Entity)e);
/*     */     
/* 125 */     playSound(prm.entity);
/*     */     
/* 127 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponTvMissile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */