/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.particles.MCH_ParticleParam;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import mcheli.weapon.MCH_BulletModel;
/*     */ import mcheli.weapon.MCH_DefaultBulletModels;
/*     */ import mcheli.weapon.MCH_EntityBaseBullet;
/*     */ import mcheli.weapon.MCH_EntityBomb;
/*     */ import mcheli.wrapper.W_Blocks;
/*     */ import mcheli.wrapper.W_MovingObjectPosition;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityMarkerRocket
/*     */   extends MCH_EntityBaseBullet {
/*     */   public int countDown;
/*     */   
/*     */   public MCH_EntityMarkerRocket(World par1World) {
/*  24 */     super(par1World);
/*  25 */     setMarkerStatus(0);
/*  26 */     this.countDown = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityMarkerRocket(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/*  33 */     super(par1World, posX, posY, posZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*  34 */     setMarkerStatus(0);
/*  35 */     this.countDown = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  40 */     super.func_70088_a();
/*  41 */     func_70096_w().func_75682_a(28, Byte.valueOf((byte)0));
/*     */   }
/*     */   
/*     */   public void setMarkerStatus(int n) {
/*  45 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/*  47 */       func_70096_w().func_75692_b(28, Byte.valueOf((byte)n));
/*     */     }
/*     */   }
/*     */   
/*     */   public int getMarkerStatus() {
/*  52 */     return func_70096_w().func_75683_a(28);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  63 */     int status = getMarkerStatus();
/*     */     
/*  65 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/*  67 */       if (getInfo() == null)
/*     */       {
/*  69 */         super.func_70071_h_();
/*     */       }
/*  71 */       if (getInfo() != null && !(getInfo()).disableSmoke)
/*     */       {
/*  73 */         if (status != 0)
/*     */         {
/*     */           
/*  76 */           if (status == 1)
/*     */           {
/*  78 */             super.func_70071_h_();
/*  79 */             spawnParticle((getInfo()).trajectoryParticleName, 3, 5.0F * (getInfo()).smokeSize * 0.5F);
/*     */           }
/*     */           else
/*     */           {
/*  83 */             float gb = this.field_70146_Z.nextFloat() * 0.3F;
/*  84 */             spawnParticle("explode", 5, (10 + this.field_70146_Z.nextInt(4)), this.field_70146_Z.nextFloat() * 0.2F + 0.8F, gb, gb, (this.field_70146_Z.nextFloat() - 0.5F) * 0.7F, 0.3F + this.field_70146_Z.nextFloat() * 0.3F, (this.field_70146_Z.nextFloat() - 0.5F) * 0.7F);
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  94 */     else if (status == 0 || func_70090_H()) {
/*     */       
/*  96 */       func_70106_y();
/*     */     }
/*  98 */     else if (status == 1) {
/*     */       
/* 100 */       super.func_70071_h_();
/*     */ 
/*     */     
/*     */     }
/* 104 */     else if (this.countDown > 0) {
/*     */       
/* 106 */       this.countDown--;
/* 107 */       if (this.countDown == 40) {
/*     */         
/* 109 */         int num = 6 + this.field_70146_Z.nextInt(2);
/* 110 */         for (int i = 0; i < num; i++)
/*     */         {
/* 112 */           MCH_EntityBomb e = new MCH_EntityBomb(this.field_70170_p, this.field_70165_t + ((this.field_70146_Z.nextFloat() - 0.5F) * 15.0F), (260.0F + this.field_70146_Z.nextFloat() * 10.0F + (i * 30)), this.field_70161_v + ((this.field_70146_Z.nextFloat() - 0.5F) * 15.0F), 0.0D, -0.5D, 0.0D, 0.0F, 90.0F, 4.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 119 */           e.setName(getName());
/*     */           
/* 121 */           e.explosionPower = 3 + this.field_70146_Z.nextInt(2);
/* 122 */           e.explosionPowerInWater = 0;
/* 123 */           e.setPower(30);
/* 124 */           e.piercing = 0;
/* 125 */           e.shootingAircraft = this.shootingAircraft;
/* 126 */           e.shootingEntity = this.shootingEntity;
/*     */           
/* 128 */           this.field_70170_p.func_72838_d((Entity)e);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 134 */       func_70106_y();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnParticle(String name, int num, float size, float r, float g, float b, float mx, float my, float mz) {
/* 142 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 144 */       if (name.isEmpty() || num < 1 || num > 50)
/* 145 */         return;  double x = (this.field_70165_t - this.field_70169_q) / num;
/* 146 */       double y = (this.field_70163_u - this.field_70167_r) / num;
/* 147 */       double z = (this.field_70161_v - this.field_70166_s) / num;
/* 148 */       for (int i = 0; i < num; i++) {
/*     */         
/* 150 */         MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", this.field_70169_q + x * i, this.field_70167_r + y * i, this.field_70166_s + z * i);
/*     */         
/* 152 */         prm.motionX = mx;
/* 153 */         prm.motionY = my;
/* 154 */         prm.motionZ = mz;
/* 155 */         prm.size = size + this.field_70146_Z.nextFloat();
/* 156 */         prm.setColor(1.0F, r, g, b);
/* 157 */         prm.isEffectWind = true;
/* 158 */         MCH_ParticlesUtil.spawnParticle(prm);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition m, float damageFactor) {
/* 166 */     if (this.field_70170_p.field_72995_K)
/* 167 */       return;  if (m.field_72308_g != null || W_MovingObjectPosition.isHitTypeEntity(m))
/*     */       return; 
/* 169 */     int x = m.field_72311_b;
/* 170 */     int y = m.field_72312_c;
/* 171 */     int z = m.field_72309_d;
/*     */     
/* 173 */     switch (m.field_72310_e) {
/*     */       case 0:
/* 175 */         y--; break;
/* 176 */       case 1: y++; break;
/* 177 */       case 2: z--; break;
/* 178 */       case 3: z++; break;
/* 179 */       case 4: x--; break;
/* 180 */       case 5: x++;
/*     */         break;
/*     */     } 
/* 183 */     if (this.field_70170_p.func_147437_c(x, y, z)) {
/*     */       
/* 185 */       if (MCH_Config.Explosion_FlamingBlock.prmBool)
/*     */       {
/* 187 */         W_WorldFunc.setBlock(this.field_70170_p, x, y, z, (Block)W_Blocks.field_150480_ab);
/*     */       }
/*     */       
/* 190 */       int noAirBlockCount = 0;
/* 191 */       for (int i = y + 1; i < 256; i++) {
/*     */         
/* 193 */         if (!this.field_70170_p.func_147437_c(x, i, z)) {
/*     */           
/* 195 */           noAirBlockCount++;
/* 196 */           if (noAirBlockCount >= 5) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 203 */       if (noAirBlockCount < 5)
/*     */       {
/* 205 */         setMarkerStatus(2);
/* 206 */         func_70107_b(x + 0.5D, y + 1.1D, z + 0.5D);
/* 207 */         this.field_70169_q = this.field_70165_t;
/* 208 */         this.field_70167_r = this.field_70163_u;
/* 209 */         this.field_70166_s = this.field_70161_v;
/* 210 */         this.countDown = 100;
/*     */       }
/*     */       else
/*     */       {
/* 214 */         func_70106_y();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 219 */       func_70106_y();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_BulletModel getDefaultBulletModel() {
/* 226 */     return MCH_DefaultBulletModels.Rocket;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityMarkerRocket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */