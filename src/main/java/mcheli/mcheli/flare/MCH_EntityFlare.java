/*     */ package mcheli.mcheli.flare;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import mcheli.particles.MCH_ParticleParam;
/*     */ import mcheli.particles.MCH_ParticlesUtil;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityFlare extends W_Entity implements IEntityAdditionalSpawnData {
/*  19 */   public double gravity = -0.013D;
/*  20 */   public double airResistance = 0.992D;
/*     */   
/*     */   public float size;
/*     */   public int fuseCount;
/*     */   
/*     */   public MCH_EntityFlare(World par1World) {
/*  26 */     super(par1World);
/*  27 */     func_70105_a(1.0F, 1.0F);
/*  28 */     this.field_70126_B = this.field_70177_z;
/*  29 */     this.field_70127_C = this.field_70125_A;
/*  30 */     this.size = 6.0F;
/*  31 */     this.fuseCount = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityFlare(World par1World, double pX, double pY, double pZ, double mX, double mY, double mZ, float size, int fuseCount) {
/*  40 */     this(par1World);
/*  41 */     func_70012_b(pX, pY, pZ, 0.0F, 0.0F);
/*  42 */     this.field_70129_M = 0.0F;
/*  43 */     this.field_70159_w = mX;
/*  44 */     this.field_70181_x = mY;
/*  45 */     this.field_70179_y = mZ;
/*  46 */     this.size = size;
/*  47 */     this.fuseCount = fuseCount;
/*     */   }
/*     */   
/*     */   public boolean func_85032_ar() {
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_70112_a(double par1) {
/*  60 */     double d1 = this.field_70121_D.func_72320_b() * 4.0D;
/*  61 */     d1 *= 64.0D;
/*  62 */     return (par1 < d1 * d1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/*  67 */     super.func_70106_y();
/*  68 */     if (this.fuseCount > 0 && this.field_70170_p.field_72995_K) {
/*     */       
/*  70 */       this.fuseCount = 0;
/*     */       
/*  72 */       int num = 20;
/*  73 */       for (int i = 0; i < 20; i++) {
/*     */         
/*  75 */         double x = (this.field_70146_Z.nextDouble() - 0.5D) * 10.0D;
/*  76 */         double y = (this.field_70146_Z.nextDouble() - 0.5D) * 10.0D;
/*  77 */         double z = (this.field_70146_Z.nextDouble() - 0.5D) * 10.0D;
/*  78 */         MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", this.field_70165_t + x, this.field_70163_u + y, this.field_70161_v + z);
/*  79 */         prm.age = 200 + this.field_70146_Z.nextInt(100);
/*  80 */         prm.size = (20 + this.field_70146_Z.nextInt(25));
/*  81 */         prm.motionX = (this.field_70146_Z.nextDouble() - 0.5D) * 0.45D;
/*  82 */         prm.motionY = (this.field_70146_Z.nextDouble() - 0.5D) * 0.01D;
/*  83 */         prm.motionZ = (this.field_70146_Z.nextDouble() - 0.5D) * 0.45D;
/*  84 */         prm.a = this.field_70146_Z.nextFloat() * 0.1F + 0.85F;
/*  85 */         prm.b = this.field_70146_Z.nextFloat() * 0.2F + 0.5F;
/*  86 */         prm.g = prm.b + 0.05F;
/*  87 */         prm.r = prm.b + 0.1F;
/*  88 */         MCH_ParticlesUtil.spawnParticle(prm);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeSpawnData(ByteBuf buffer) {
/*     */     try {
/*  98 */       buffer.writeByte(this.fuseCount);
/*     */     }
/* 100 */     catch (Exception e) {
/*     */       
/* 102 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSpawnData(ByteBuf additionalData) {
/*     */     try {
/* 111 */       this.fuseCount = additionalData.readByte();
/*     */     }
/* 113 */     catch (Exception e) {
/*     */       
/* 115 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 124 */     if (this.fuseCount > 0 && this.field_70173_aa >= this.fuseCount) {
/*     */       
/* 126 */       func_70106_y();
/*     */     }
/* 128 */     else if (!this.field_70170_p.field_72995_K && !this.field_70170_p.func_72899_e((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v)) {
/*     */       
/* 130 */       func_70106_y();
/*     */ 
/*     */     
/*     */     }
/* 134 */     else if (this.field_70173_aa > 300 && !this.field_70170_p.field_72995_K) {
/*     */       
/* 136 */       func_70106_y();
/*     */     }
/*     */     else {
/*     */       
/* 140 */       super.func_70071_h_();
/*     */       
/* 142 */       if (!this.field_70170_p.field_72995_K)
/*     */       {
/* 144 */         onUpdateCollided();
/*     */       }
/*     */       
/* 147 */       this.field_70165_t += this.field_70159_w;
/* 148 */       this.field_70163_u += this.field_70181_x;
/* 149 */       this.field_70161_v += this.field_70179_y;
/*     */       
/* 151 */       if (this.field_70170_p.field_72995_K) {
/*     */         
/* 153 */         int num = 2;
/* 154 */         double x = (this.field_70165_t - this.field_70169_q) / 2.0D;
/* 155 */         double y = (this.field_70163_u - this.field_70167_r) / 2.0D;
/* 156 */         double z = (this.field_70161_v - this.field_70166_s) / 2.0D;
/* 157 */         for (int i = 0; i < 2; i++) {
/*     */           
/* 159 */           MCH_ParticleParam prm = new MCH_ParticleParam(this.field_70170_p, "smoke", this.field_70169_q + x * i, this.field_70167_r + y * i, this.field_70166_s + z * i);
/*     */           
/* 161 */           prm.size = 6.0F + this.field_70146_Z.nextFloat();
/* 162 */           if (this.size < 5.0F) {
/*     */             
/* 164 */             prm.a = (float)(prm.a * 0.75D);
/* 165 */             if (this.field_70146_Z.nextInt(2) == 0)
/*     */               continue; 
/* 167 */           }  if (this.fuseCount > 0) {
/*     */             
/* 169 */             prm.a = this.field_70146_Z.nextFloat() * 0.1F + 0.85F;
/* 170 */             prm.b = this.field_70146_Z.nextFloat() * 0.1F + 0.5F;
/* 171 */             prm.g = prm.b + 0.05F;
/* 172 */             prm.r = prm.b + 0.1F;
/*     */           } 
/* 174 */           MCH_ParticlesUtil.spawnParticle(prm);
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */       
/* 181 */       this.field_70181_x += this.gravity;
/* 182 */       this.field_70159_w *= this.airResistance;
/* 183 */       this.field_70179_y *= this.airResistance;
/*     */       
/* 185 */       if (func_70090_H() && !this.field_70170_p.field_72995_K)
/*     */       {
/* 187 */         func_70106_y();
/*     */       }
/* 189 */       if (this.field_70122_E && !this.field_70170_p.field_72995_K)
/*     */       {
/* 191 */         func_70106_y();
/*     */       }
/*     */       
/* 194 */       func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onUpdateCollided() {
/* 201 */     Vec3 vec3 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 202 */     Vec3 vec31 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 203 */     MovingObjectPosition mop = W_WorldFunc.clip(this.field_70170_p, vec3, vec31);
/* 204 */     vec3 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 205 */     vec31 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */     
/* 207 */     if (mop != null) {
/*     */       
/* 209 */       vec31 = W_WorldFunc.getWorldVec3(this.field_70170_p, mop.field_72307_f.field_72450_a, mop.field_72307_f.field_72448_b, mop.field_72307_f.field_72449_c);
/* 210 */       onImpact(mop);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
/* 219 */     if (!this.field_70170_p.field_72995_K)
/*     */     {
/* 221 */       func_70106_y();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 230 */     par1NBTTagCompound.func_74782_a("direction", (NBTBase)func_70087_a(new double[] { this.field_70159_w, this.field_70181_x, this.field_70179_y }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 239 */     func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/* 247 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_70111_Y() {
/* 252 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/* 260 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 266 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\flare\MCH_EntityFlare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */