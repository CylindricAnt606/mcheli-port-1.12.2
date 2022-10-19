/*     */ package mcheli.mcheli.weapon;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.weapon.MCH_EntityTorpedo;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponParam;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_WeaponTorpedo extends MCH_WeaponBase {
/*     */   public MCH_WeaponTorpedo(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/*  15 */     super(w, v, yaw, pitch, nm, wi);
/*  16 */     this.acceleration = 0.5F;
/*  17 */     this.explosionPower = 8;
/*  18 */     this.power = 35;
/*  19 */     this.interval = -100;
/*  20 */     if (w.field_72995_K) this.interval -= 10;
/*     */   
/*     */   }
/*     */   
/*     */   public boolean shot(MCH_WeaponParam prm) {
/*  25 */     if (getInfo() != null) {
/*     */       
/*  27 */       if ((getInfo()).isGuidedTorpedo)
/*     */       {
/*  29 */         return shotGuided(prm);
/*     */       }
/*     */ 
/*     */       
/*  33 */       return shotNoGuided(prm);
/*     */     } 
/*     */     
/*  36 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean shotNoGuided(MCH_WeaponParam prm) {
/*  41 */     if (this.worldObj.field_72995_K) return true;
/*     */     
/*  43 */     float yaw = prm.rotYaw;
/*  44 */     float pitch = prm.rotPitch;
/*     */     
/*  46 */     double mx = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/*  48 */     double mz = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/*  50 */     double my = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/*     */     
/*  52 */     mx = mx * (getInfo()).acceleration + prm.entity.field_70159_w;
/*  53 */     my = my * (getInfo()).acceleration + prm.entity.field_70181_x;
/*  54 */     mz = mz * (getInfo()).acceleration + prm.entity.field_70179_y;
/*  55 */     this.acceleration = MathHelper.func_76133_a(mx * mx + my * my + mz * mz);
/*     */     
/*  57 */     MCH_EntityTorpedo e = new MCH_EntityTorpedo(this.worldObj, prm.posX, prm.posY, prm.posZ, mx, my, mz, yaw, 0.0F, this.acceleration);
/*     */     
/*  59 */     e.setName(this.name);
/*     */     
/*  61 */     e.setParameterFromWeapon(this, prm.entity, prm.user);
/*     */     
/*  63 */     e.field_70159_w = mx;
/*  64 */     e.field_70181_x = my;
/*  65 */     e.field_70179_y = mz;
/*  66 */     e.accelerationInWater = (getInfo() != null) ? (getInfo()).accelerationInWater : 1.0D;
/*     */     
/*  68 */     this.worldObj.func_72838_d((Entity)e);
/*     */     
/*  70 */     playSound(prm.entity);
/*     */     
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean shotGuided(MCH_WeaponParam prm) {
/*  77 */     float yaw = prm.user.field_70177_z;
/*  78 */     float pitch = prm.user.field_70125_A;
/*     */     
/*  80 */     Vec3 v = MCH_Lib.RotVec3(0.0D, 0.0D, 1.0D, -yaw, -pitch, -prm.rotRoll);
/*     */     
/*  82 */     double tX = v.field_72450_a;
/*  83 */     double tZ = v.field_72449_c;
/*  84 */     double tY = v.field_72448_b;
/*     */     
/*  86 */     double dist = MathHelper.func_76133_a(tX * tX + tY * tY + tZ * tZ);
/*     */ 
/*     */     
/*  89 */     if (this.worldObj.field_72995_K) {
/*     */       
/*  91 */       tX = tX * 100.0D / dist;
/*  92 */       tY = tY * 100.0D / dist;
/*  93 */       tZ = tZ * 100.0D / dist;
/*     */     }
/*     */     else {
/*     */       
/*  97 */       tX = tX * 150.0D / dist;
/*  98 */       tY = tY * 150.0D / dist;
/*  99 */       tZ = tZ * 150.0D / dist;
/*     */     } 
/*     */     
/* 102 */     Vec3 src = W_WorldFunc.getWorldVec3(this.worldObj, prm.user.field_70165_t, prm.user.field_70163_u, prm.user.field_70161_v);
/* 103 */     Vec3 dst = W_WorldFunc.getWorldVec3(this.worldObj, prm.user.field_70165_t + tX, prm.user.field_70163_u + tY, prm.user.field_70161_v + tZ);
/* 104 */     MovingObjectPosition m = W_WorldFunc.clip(this.worldObj, src, dst);
/*     */     
/* 106 */     if (m != null && W_MovingObjectPosition.isHitTypeTile(m) && MCH_Lib.isBlockInWater(this.worldObj, m.field_72311_b, m.field_72312_c, m.field_72309_d)) {
/*     */ 
/*     */       
/* 109 */       if (!this.worldObj.field_72995_K) {
/*     */         
/* 111 */         double mx = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */         
/* 113 */         double mz = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */         
/* 115 */         double my = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/*     */         
/* 117 */         mx = mx * (getInfo()).acceleration + prm.entity.field_70159_w;
/* 118 */         my = my * (getInfo()).acceleration + prm.entity.field_70181_x;
/* 119 */         mz = mz * (getInfo()).acceleration + prm.entity.field_70179_y;
/* 120 */         this.acceleration = MathHelper.func_76133_a(mx * mx + my * my + mz * mz);
/*     */         
/* 122 */         MCH_EntityTorpedo e = new MCH_EntityTorpedo(this.worldObj, prm.posX, prm.posY, prm.posZ, prm.entity.field_70159_w, prm.entity.field_70181_x, prm.entity.field_70179_y, yaw, 0.0F, this.acceleration);
/*     */ 
/*     */ 
/*     */         
/* 126 */         e.setName(this.name);
/*     */         
/* 128 */         e.setParameterFromWeapon(this, prm.entity, prm.user);
/*     */         
/* 130 */         e.targetPosX = m.field_72307_f.field_72450_a;
/* 131 */         e.targetPosY = m.field_72307_f.field_72448_b;
/* 132 */         e.targetPosZ = m.field_72307_f.field_72449_c;
/* 133 */         e.field_70159_w = mx;
/* 134 */         e.field_70181_x = my;
/* 135 */         e.field_70179_y = mz;
/* 136 */         e.accelerationInWater = (getInfo() != null) ? (getInfo()).accelerationInWater : 1.0D;
/*     */         
/* 138 */         this.worldObj.func_72838_d((Entity)e);
/*     */         
/* 140 */         playSound(prm.entity);
/*     */       } 
/*     */       
/* 143 */       return true;
/*     */     } 
/* 145 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponTorpedo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */