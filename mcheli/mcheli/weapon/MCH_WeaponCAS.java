/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.weapon.MCH_EntityA10;
/*     */ import mcheli.weapon.MCH_WeaponBase;
/*     */ import mcheli.weapon.MCH_WeaponInfo;
/*     */ import mcheli.weapon.MCH_WeaponParam;
/*     */ import mcheli.wrapper.W_MovingObjectPosition;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class MCH_WeaponCAS
/*     */   extends MCH_WeaponBase
/*     */ {
/*     */   private double targetPosX;
/*     */   private double targetPosY;
/*     */   private double targetPosZ;
/*     */   public int direction;
/*     */   
/*     */   public MCH_WeaponCAS(World w, Vec3 v, float yaw, float pitch, String nm, MCH_WeaponInfo wi) {
/*  26 */     super(w, v, yaw, pitch, nm, wi);
/*  27 */     this.acceleration = 4.0F;
/*  28 */     this.explosionPower = 2;
/*  29 */     this.power = 32;
/*  30 */     this.interval = -300;
/*  31 */     if (w.field_72995_K) this.interval -= 10;
/*     */     
/*  33 */     this.targetPosX = 0.0D;
/*  34 */     this.targetPosY = 0.0D;
/*  35 */     this.targetPosZ = 0.0D;
/*  36 */     this.direction = 0;
/*  37 */     this.startTick = 0;
/*  38 */     this.cntAtk = 3;
/*  39 */     this.shooter = null;
/*  40 */     this.user = null;
/*     */   }
/*     */   private int startTick; private int cntAtk; private Entity shooter;
/*     */   public Entity user;
/*     */   
/*     */   public void update(int countWait) {
/*  46 */     super.update(countWait);
/*  47 */     if (!this.worldObj.field_72995_K && this.cntAtk < 3 && countWait != 0)
/*     */     {
/*  49 */       if (this.tick == this.startTick) {
/*     */         
/*  51 */         double x = 0.0D, z = 0.0D;
/*  52 */         if (this.cntAtk >= 1) {
/*     */           
/*  54 */           double sign = (this.cntAtk == 1) ? 1.0D : -1.0D;
/*  55 */           if (this.direction == 0 || this.direction == 2) {
/*     */             
/*  57 */             this; x = rand.nextDouble() * 10.0D * sign;
/*  58 */             this; z = (rand.nextDouble() - 0.5D) * 10.0D;
/*     */           } 
/*  60 */           if (this.direction == 1 || this.direction == 3) {
/*     */             
/*  62 */             this; z = rand.nextDouble() * 10.0D * sign;
/*  63 */             this; x = (rand.nextDouble() - 0.5D) * 10.0D;
/*     */           } 
/*     */         } 
/*  66 */         spawnA10(this.targetPosX + x, this.targetPosY + 20.0D, this.targetPosZ + z);
/*     */         
/*  68 */         this.startTick = this.tick + 45;
/*  69 */         this.cntAtk++;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void modifyParameters() {
/*  77 */     if (this.interval > -250)
/*     */     {
/*  79 */       this.interval = -250;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTargetPosition(double x, double y, double z) {
/*  85 */     this.targetPosX = x;
/*  86 */     this.targetPosY = y;
/*  87 */     this.targetPosZ = z;
/*     */   }
/*     */ 
/*     */   
/*     */   public void spawnA10(double x, double y, double z) {
/*  92 */     double mX = 0.0D;
/*  93 */     double mY = 0.0D;
/*  94 */     double mZ = 0.0D;
/*  95 */     int SPEED = 3;
/*  96 */     if (this.direction == 0) { mZ += 3.0D; z -= 90.0D; }
/*  97 */      if (this.direction == 1) { mX -= 3.0D; x += 90.0D; }
/*  98 */      if (this.direction == 2) { mZ -= 3.0D; z += 90.0D; }
/*  99 */      if (this.direction == 3) { mX += 3.0D; x -= 90.0D; }
/*     */     
/* 101 */     MCH_EntityA10 a10 = new MCH_EntityA10(this.worldObj, x, y, z);
/* 102 */     a10.setWeaponName(this.name);
/*     */     
/* 104 */     a10.field_70126_B = a10.field_70177_z = (90 * this.direction);
/* 105 */     a10.field_70159_w = mX;
/* 106 */     a10.field_70181_x = mY;
/* 107 */     a10.field_70179_y = mZ;
/* 108 */     a10.direction = this.direction;
/* 109 */     a10.shootingEntity = this.user;
/* 110 */     a10.shootingAircraft = this.shooter;
/* 111 */     a10.explosionPower = this.explosionPower;
/* 112 */     a10.power = this.power;
/* 113 */     a10.acceleration = this.acceleration;
/*     */     
/* 115 */     this.worldObj.func_72838_d((Entity)a10);
/*     */     
/* 117 */     W_WorldFunc.MOD_playSoundEffect(this.worldObj, x, y, z, "a-10_snd", 150.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shot(MCH_WeaponParam prm) {
/* 125 */     float yaw = prm.user.field_70177_z;
/* 126 */     float pitch = prm.user.field_70125_A;
/* 127 */     double tX = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/* 129 */     double tZ = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/* 131 */     double tY = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/*     */     
/* 133 */     double dist = MathHelper.func_76133_a(tX * tX + tY * tY + tZ * tZ);
/*     */ 
/*     */     
/* 136 */     if (this.worldObj.field_72995_K) {
/*     */       
/* 138 */       tX = tX * 80.0D / dist;
/* 139 */       tY = tY * 80.0D / dist;
/* 140 */       tZ = tZ * 80.0D / dist;
/*     */     }
/*     */     else {
/*     */       
/* 144 */       tX = tX * 150.0D / dist;
/* 145 */       tY = tY * 150.0D / dist;
/* 146 */       tZ = tZ * 150.0D / dist;
/*     */     } 
/*     */     
/* 149 */     Vec3 src = W_WorldFunc.getWorldVec3(this.worldObj, prm.entity.field_70165_t, prm.entity.field_70163_u + 2.0D, prm.entity.field_70161_v);
/* 150 */     Vec3 dst = W_WorldFunc.getWorldVec3(this.worldObj, prm.entity.field_70165_t + tX, prm.entity.field_70163_u + tY + 2.0D, prm.entity.field_70161_v + tZ);
/* 151 */     MovingObjectPosition m = W_WorldFunc.clip(this.worldObj, src, dst);
/*     */     
/* 153 */     if (m != null && W_MovingObjectPosition.isHitTypeTile(m)) {
/*     */ 
/*     */       
/* 156 */       this.targetPosX = m.field_72307_f.field_72450_a;
/* 157 */       this.targetPosY = m.field_72307_f.field_72448_b;
/* 158 */       this.targetPosZ = m.field_72307_f.field_72449_c;
/*     */ 
/*     */       
/* 161 */       this.direction = (int)MCH_Lib.getRotate360((yaw + 45.0F)) / 90;
/* 162 */       this; this.direction += rand.nextBoolean() ? -1 : 1;
/* 163 */       this.direction %= 4;
/* 164 */       if (this.direction < 0) this.direction += 4;
/*     */       
/* 166 */       this.user = prm.user;
/* 167 */       this.shooter = prm.entity;
/*     */       
/* 169 */       if (prm.entity != null) playSoundClient(prm.entity, 1.0F, 1.0F);
/*     */       
/* 171 */       this.startTick = 50;
/* 172 */       this.cntAtk = 0;
/*     */       
/* 174 */       return true;
/*     */     } 
/* 176 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shot(Entity user, double px, double py, double pz, int option1, int option2) {
/* 187 */     float yaw = user.field_70177_z;
/* 188 */     float pitch = user.field_70125_A;
/* 189 */     double tX = (-MathHelper.func_76126_a(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/* 191 */     double tZ = (MathHelper.func_76134_b(yaw / 180.0F * 3.1415927F) * MathHelper.func_76134_b(pitch / 180.0F * 3.1415927F));
/*     */     
/* 193 */     double tY = -MathHelper.func_76126_a(pitch / 180.0F * 3.1415927F);
/*     */     
/* 195 */     double dist = MathHelper.func_76133_a(tX * tX + tY * tY + tZ * tZ);
/*     */ 
/*     */     
/* 198 */     if (this.worldObj.field_72995_K) {
/*     */       
/* 200 */       tX = tX * 80.0D / dist;
/* 201 */       tY = tY * 80.0D / dist;
/* 202 */       tZ = tZ * 80.0D / dist;
/*     */     }
/*     */     else {
/*     */       
/* 206 */       tX = tX * 120.0D / dist;
/* 207 */       tY = tY * 120.0D / dist;
/* 208 */       tZ = tZ * 120.0D / dist;
/*     */     } 
/*     */     
/* 211 */     Vec3 src = W_WorldFunc.getWorldVec3(this.worldObj, px, py, pz);
/* 212 */     Vec3 dst = W_WorldFunc.getWorldVec3(this.worldObj, px + tX, py + tY, pz + tZ);
/* 213 */     MovingObjectPosition m = W_WorldFunc.clip(this.worldObj, src, dst);
/*     */     
/* 215 */     if (W_MovingObjectPosition.isHitTypeTile(m)) {
/*     */       
/* 217 */       if (this.worldObj.field_72995_K) {
/*     */         
/* 219 */         double dx = m.field_72307_f.field_72450_a - px;
/* 220 */         double dy = m.field_72307_f.field_72448_b - py;
/* 221 */         double dz = m.field_72307_f.field_72449_c - pz;
/*     */         
/* 223 */         if (Math.sqrt(dx * dx + dz * dz) < 20.0D)
/*     */         {
/* 225 */           return false;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 230 */       this.targetPosX = m.field_72307_f.field_72450_a;
/* 231 */       this.targetPosY = m.field_72307_f.field_72448_b;
/* 232 */       this.targetPosZ = m.field_72307_f.field_72449_c;
/*     */ 
/*     */       
/* 235 */       this.direction = (int)MCH_Lib.getRotate360((yaw + 45.0F)) / 90;
/* 236 */       this; this.direction += rand.nextBoolean() ? -1 : 1;
/* 237 */       this.direction %= 4;
/* 238 */       if (this.direction < 0) this.direction += 4;
/*     */       
/* 240 */       this.user = user;
/* 241 */       this.shooter = null;
/*     */       
/* 243 */       this.tick = 0;
/* 244 */       this.startTick = 50;
/* 245 */       this.cntAtk = 0;
/*     */       
/* 247 */       return true;
/*     */     } 
/* 249 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_WeaponCAS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */