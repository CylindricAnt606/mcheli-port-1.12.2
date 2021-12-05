/*     */ package mcheli.mcheli.throwable;
/*     */ 
/*     */ import mcheli.MCH_Explosion;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.particles.MCH_ParticleParam;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import mcheli.throwable.MCH_ThrowableInfo;
/*     */ import mcheli.throwable.MCH_ThrowableInfoManager;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.projectile.EntityThrowable;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityThrowable
/*     */   extends EntityThrowable {
/*     */   private static final int DATAID_NAME = 31;
/*     */   private int countOnUpdate;
/*     */   private MCH_ThrowableInfo throwableInfo;
/*     */   public double boundPosX;
/*     */   public double boundPosY;
/*     */   public double boundPosZ;
/*     */   public MovingObjectPosition lastOnImpact;
/*     */   public int noInfoCount;
/*     */   
/*     */   public MCH_EntityThrowable(World par1World) {
/*  30 */     super(par1World);
/*  31 */     init();
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_EntityThrowable(World par1World, EntityLivingBase par2EntityLivingBase, float acceleration) {
/*  36 */     super(par1World, par2EntityLivingBase);
/*  37 */     this.field_70159_w *= acceleration;
/*  38 */     this.field_70181_x *= acceleration;
/*  39 */     this.field_70179_y *= acceleration;
/*     */     
/*  41 */     init();
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_EntityThrowable(World par1World, double par2, double par4, double par6) {
/*  46 */     super(par1World, par2, par4, par6);
/*  47 */     init();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityThrowable(World p_i1777_1_, double x, double y, double z, float yaw, float pitch) {
/*  53 */     this(p_i1777_1_);
/*  54 */     func_70105_a(0.25F, 0.25F);
/*  55 */     func_70012_b(x, y, z, yaw, pitch);
/*  56 */     this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
/*  57 */     this.field_70163_u -= 0.10000000149011612D;
/*  58 */     this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
/*  59 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*  60 */     this.field_70129_M = 0.0F;
/*  61 */     float f = 0.4F;
/*  62 */     this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
/*  63 */     this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
/*  64 */     this.field_70181_x = (-MathHelper.func_76126_a((this.field_70125_A + func_70183_g()) / 180.0F * 3.1415927F) * f);
/*  65 */     func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, func_70182_d(), 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() {
/*  72 */     this.lastOnImpact = null;
/*  73 */     this.countOnUpdate = 0;
/*  74 */     setInfo(null);
/*  75 */     this.noInfoCount = 0;
/*     */     
/*  77 */     func_70096_w().func_75682_a(31, new String(""));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/*  82 */     String s = (getInfo() != null) ? (getInfo()).name : "null";
/*  83 */     MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityThrowable.setDead(%s)", new Object[] { s });
/*     */     
/*  85 */     super.func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  91 */     this.boundPosX = this.field_70165_t;
/*  92 */     this.boundPosY = this.field_70163_u;
/*  93 */     this.boundPosZ = this.field_70161_v;
/*     */     
/*  95 */     if (getInfo() != null) {
/*     */       
/*  97 */       Block block = W_WorldFunc.getBlock(this.field_70170_p, (int)(this.field_70165_t + 0.5D), (int)this.field_70163_u, (int)(this.field_70161_v + 0.5D));
/*  98 */       Material mat = W_WorldFunc.getBlockMaterial(this.field_70170_p, (int)(this.field_70165_t + 0.5D), (int)this.field_70163_u, (int)(this.field_70161_v + 0.5D));
/*  99 */       if (block != null && mat == Material.field_151586_h) {
/*     */         
/* 101 */         this.field_70181_x += (getInfo()).gravityInWater;
/*     */       }
/*     */       else {
/*     */         
/* 105 */         this.field_70181_x += (getInfo()).gravity;
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     super.func_70071_h_();
/*     */     
/* 111 */     if (this.lastOnImpact != null) {
/*     */       
/* 113 */       boundBullet(this.lastOnImpact);
/* 114 */       func_70107_b(this.boundPosX + this.field_70159_w, this.boundPosY + this.field_70181_x, this.boundPosZ + this.field_70179_y);
/*     */ 
/*     */ 
/*     */       
/* 118 */       this.lastOnImpact = null;
/*     */     } 
/*     */     
/* 121 */     this.countOnUpdate++;
/*     */     
/* 123 */     if (this.countOnUpdate >= 2147483632) {
/*     */       
/* 125 */       func_70106_y();
/*     */       
/*     */       return;
/*     */     } 
/* 129 */     if (getInfo() == null) {
/*     */       
/* 131 */       String s = func_70096_w().func_75681_e(31);
/* 132 */       if (!s.isEmpty())
/*     */       {
/* 134 */         setInfo(MCH_ThrowableInfoManager.get(s));
/*     */       }
/*     */       
/* 137 */       if (getInfo() == null) {
/*     */         
/* 139 */         this.noInfoCount++;
/* 140 */         if (this.noInfoCount > 10)
/*     */         {
/* 142 */           func_70106_y();
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 148 */     if (this.field_70128_L)
/*     */       return; 
/* 150 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 152 */       if (this.countOnUpdate == (getInfo()).timeFuse)
/*     */       {
/* 154 */         if ((getInfo()).explosion > 0) {
/*     */           
/* 156 */           MCH_Explosion.newExplosion(this.field_70170_p, null, null, this.field_70165_t, this.field_70163_u, this.field_70161_v, (getInfo()).explosion, (getInfo()).explosion, true, true, false, true, 0);
/*     */ 
/*     */           
/* 159 */           func_70106_y();
/*     */           
/*     */           return;
/*     */         } 
/*     */       }
/* 164 */       if (this.countOnUpdate >= (getInfo()).aliveTime) {
/*     */         
/* 166 */         func_70106_y();
/*     */ 
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/* 172 */     } else if (this.countOnUpdate >= (getInfo()).timeFuse) {
/*     */       
/* 174 */       if ((getInfo()).explosion <= 0)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 180 */         for (int i = 0; i < (getInfo()).smokeNum; i++) {
/*     */           
/* 182 */           float y = ((getInfo()).smokeVelocityVertical >= 0.0F) ? 0.2F : -0.2F;
/*     */           
/* 184 */           float r = (getInfo()).smokeColor.r * 0.9F + this.field_70146_Z.nextFloat() * 0.1F;
/* 185 */           float g = (getInfo()).smokeColor.g * 0.9F + this.field_70146_Z.nextFloat() * 0.1F;
/* 186 */           float b = (getInfo()).smokeColor.b * 0.9F + this.field_70146_Z.nextFloat() * 0.1F;
/* 187 */           if ((getInfo()).smokeColor.r == (getInfo()).smokeColor.g)
/*     */           {
/* 189 */             g = r;
/*     */           }
/* 191 */           if ((getInfo()).smokeColor.r == (getInfo()).smokeColor.b)
/*     */           {
/* 193 */             b = r;
/*     */           }
/* 195 */           if ((getInfo()).smokeColor.g == (getInfo()).smokeColor.b)
/*     */           {
/* 197 */             b = g;
/*     */           }
/*     */           
/* 200 */           spawnParticle("explode", 4, (getInfo()).smokeSize + this.field_70146_Z.nextFloat() * (getInfo()).smokeSize / 3.0F, r, g, b, (getInfo()).smokeVelocityHorizontal * (this.field_70146_Z.nextFloat() - 0.5F), (getInfo()).smokeVelocityVertical * this.field_70146_Z.nextFloat(), (getInfo()).smokeVelocityHorizontal * (this.field_70146_Z.nextFloat() - 0.5F));
/*     */         } 
/*     */       }
/*     */     } 
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
/*     */   public void spawnParticle(String name, int num, float size, float r, float g, float b, float mx, float my, float mz) {
/* 215 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 217 */       if (name.isEmpty() || num < 1)
/* 218 */         return;  double x = (this.field_70165_t - this.field_70169_q) / num;
/* 219 */       double y = (this.field_70163_u - this.field_70167_r) / num;
/* 220 */       double z = (this.field_70161_v - this.field_70166_s) / num;
/* 221 */       for (int i = 0; i < num; i++) {
/*     */         
/* 223 */         MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", this.field_70169_q + x * i, 1.0D + this.field_70167_r + y * i, this.field_70166_s + z * i);
/*     */         
/* 225 */         prm.setMotion(mx, my, mz);
/* 226 */         prm.size = size;
/* 227 */         prm.setColor(1.0F, r, g, b);
/* 228 */         prm.isEffectWind = true;
/* 229 */         prm.toWhite = true;
/*     */         
/* 231 */         MCH_ParticlesUtil.spawnParticle(prm);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float func_70185_h() {
/* 239 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void boundBullet(MovingObjectPosition m) {
/* 244 */     float bound = (getInfo()).bound;
/*     */     
/* 246 */     switch (m.field_72310_e) {
/*     */       
/*     */       case 0:
/*     */       case 1:
/* 250 */         this.field_70159_w *= 0.8999999761581421D;
/* 251 */         this.field_70179_y *= 0.8999999761581421D;
/* 252 */         this.boundPosY = m.field_72307_f.field_72448_b;
/* 253 */         if ((m.field_72310_e == 0 && this.field_70181_x > 0.0D) || (m.field_72310_e == 1 && this.field_70181_x < 0.0D)) {
/*     */           
/* 255 */           this.field_70181_x = -this.field_70181_x * bound;
/*     */           
/*     */           break;
/*     */         } 
/* 259 */         this.field_70181_x = 0.0D;
/*     */         break;
/*     */       
/*     */       case 2:
/* 263 */         if (this.field_70179_y > 0.0D) this.field_70179_y = -this.field_70179_y * bound;
/*     */         
/*     */         break;
/*     */       case 3:
/* 267 */         if (this.field_70179_y < 0.0D) this.field_70179_y = -this.field_70179_y * bound;
/*     */         
/*     */         break;
/*     */       case 4:
/* 271 */         if (this.field_70159_w > 0.0D) this.field_70159_w = -this.field_70159_w * bound;
/*     */         
/*     */         break;
/*     */       case 5:
/* 275 */         if (this.field_70159_w < 0.0D) this.field_70159_w = -this.field_70159_w * bound;
/*     */         
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70184_a(MovingObjectPosition m) {
/* 286 */     if (getInfo() != null)
/*     */     {
/* 288 */       this.lastOnImpact = m;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_ThrowableInfo getInfo() {
/* 294 */     return this.throwableInfo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInfo(MCH_ThrowableInfo info) {
/* 299 */     this.throwableInfo = info;
/* 300 */     if (info != null)
/*     */     {
/* 302 */       if (!this.field_70170_p.field_72995_K)
/*     */       {
/* 304 */         func_70096_w().func_75692_b(31, new String(info.name));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\throwable\MCH_EntityThrowable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */