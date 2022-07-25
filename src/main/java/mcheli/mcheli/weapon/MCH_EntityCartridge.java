/*     */ package mcheli.mcheli.weapon;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.weapon.MCH_Cartridge;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_MovingObjectPosition;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_EntityCartridge
/*     */   extends W_Entity
/*     */ {
/*     */   public final String texture_name;
/*     */   public final IModelCustom model;
/*     */   private final float bound;
/*     */   private final float gravity;
/*     */   private final float scale;
/*     */   private int countOnUpdate;
/*     */   public float targetYaw;
/*     */   public float targetPitch;
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static void spawnCartridge(World world, MCH_Cartridge cartridge, double x, double y, double z, double mx, double my, double mz, float yaw, float pitch) {
/*  36 */     if (cartridge != null) {
/*     */       
/*  38 */       mcheli.weapon.MCH_EntityCartridge entityFX = new mcheli.weapon.MCH_EntityCartridge(world, cartridge, x, y, z, mx + (world.field_73012_v.nextFloat() - 0.5D) * 0.07D, my, mz + (world.field_73012_v.nextFloat() - 0.5D) * 0.07D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  44 */       entityFX.field_70126_B = yaw;
/*  45 */       entityFX.field_70177_z = yaw;
/*  46 */       entityFX.targetYaw = yaw;
/*  47 */       entityFX.field_70127_C = pitch;
/*  48 */       entityFX.field_70125_A = pitch;
/*  49 */       entityFX.targetPitch = pitch;
/*     */       
/*  51 */       float cy = yaw + cartridge.yaw;
/*  52 */       float cp = pitch + cartridge.pitch;
/*     */       
/*  54 */       double tX = (-MathHelper.func_76126_a(cy / 180.0F * 3.1415927F) * MathHelper.func_76134_b(cp / 180.0F * 3.1415927F));
/*     */       
/*  56 */       double tZ = (MathHelper.func_76134_b(cy / 180.0F * 3.1415927F) * MathHelper.func_76134_b(cp / 180.0F * 3.1415927F));
/*     */       
/*  58 */       double tY = -MathHelper.func_76126_a(cp / 180.0F * 3.1415927F);
/*     */       
/*  60 */       double d = MathHelper.func_76133_a(tX * tX + tY * tY + tZ * tZ);
/*  61 */       if (Math.abs(d) > 0.001D) {
/*     */         
/*  63 */         entityFX.field_70159_w += tX * cartridge.acceleration / d;
/*  64 */         entityFX.field_70181_x += tY * cartridge.acceleration / d;
/*  65 */         entityFX.field_70179_y += tZ * cartridge.acceleration / d;
/*     */       } 
/*     */       
/*  68 */       world.func_72838_d((Entity)entityFX);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityCartridge(World par1World, MCH_Cartridge c, double x, double y, double z, double mx, double my, double mz) {
/*  77 */     super(par1World);
/*  78 */     func_70080_a(x, y, z, 0.0F, 0.0F);
/*  79 */     this.field_70159_w = mx;
/*  80 */     this.field_70181_x = my;
/*  81 */     this.field_70179_y = mz;
/*  82 */     this.texture_name = c.name;
/*  83 */     this.model = c.model;
/*  84 */     this.bound = c.bound;
/*  85 */     this.gravity = c.gravity;
/*  86 */     this.scale = c.scale;
/*  87 */     this.countOnUpdate = 0;
/*     */   }
/*     */   public float getScale() {
/*  90 */     return this.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  95 */     this.field_70169_q = this.field_70165_t;
/*  96 */     this.field_70167_r = this.field_70163_u;
/*  97 */     this.field_70166_s = this.field_70161_v;
/*  98 */     this.field_70126_B = this.field_70177_z;
/*  99 */     this.field_70127_C = this.field_70125_A;
/*     */     
/* 101 */     if (this.countOnUpdate < MCH_Config.AliveTimeOfCartridge.prmInt) {
/*     */       
/* 103 */       this.countOnUpdate++;
/*     */     }
/*     */     else {
/*     */       
/* 107 */       func_70106_y();
/*     */     } 
/*     */     
/* 110 */     this.field_70159_w *= 0.98D;
/* 111 */     this.field_70179_y *= 0.98D;
/* 112 */     this.field_70181_x += this.gravity;
/*     */     
/* 114 */     move();
/*     */   }
/*     */ 
/*     */   
/*     */   public void rotation() {
/* 119 */     if (this.field_70177_z < this.targetYaw - 3.0F) {
/*     */       
/* 121 */       this.field_70177_z += 10.0F;
/* 122 */       if (this.field_70177_z > this.targetYaw)
/*     */       {
/* 124 */         this.field_70177_z = this.targetYaw;
/*     */       }
/*     */     }
/* 127 */     else if (this.field_70177_z > this.targetYaw + 3.0F) {
/*     */       
/* 129 */       this.field_70177_z -= 10.0F;
/* 130 */       if (this.field_70177_z < this.targetYaw)
/*     */       {
/* 132 */         this.field_70177_z = this.targetYaw;
/*     */       }
/*     */     } 
/*     */     
/* 136 */     if (this.field_70125_A < this.targetPitch) {
/*     */       
/* 138 */       this.field_70125_A += 10.0F;
/* 139 */       if (this.field_70125_A > this.targetPitch)
/*     */       {
/* 141 */         this.field_70125_A = this.targetPitch;
/*     */       }
/*     */     }
/* 144 */     else if (this.field_70125_A > this.targetPitch) {
/*     */       
/* 146 */       this.field_70125_A -= 10.0F;
/* 147 */       if (this.field_70125_A < this.targetPitch)
/*     */       {
/* 149 */         this.field_70125_A = this.targetPitch;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void move() {
/* 156 */     Vec3 vec1 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 157 */     Vec3 vec2 = W_WorldFunc.getWorldVec3(this.field_70170_p, this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */ 
/*     */ 
/*     */     
/* 161 */     MovingObjectPosition m = W_WorldFunc.clip(this.field_70170_p, vec1, vec2);
/*     */     
/* 163 */     double d = Math.max(Math.abs(this.field_70159_w), Math.abs(this.field_70181_x));
/* 164 */     d = Math.max(d, Math.abs(this.field_70179_y));
/*     */     
/* 166 */     if (W_MovingObjectPosition.isHitTypeTile(m)) {
/*     */       
/* 168 */       func_70107_b(m.field_72307_f.field_72450_a, m.field_72307_f.field_72448_b, m.field_72307_f.field_72449_c);
/*     */       
/* 170 */       this.field_70159_w += d * (this.field_70146_Z.nextFloat() - 0.5F) * 0.10000000149011612D;
/* 171 */       this.field_70181_x += d * (this.field_70146_Z.nextFloat() - 0.5F) * 0.10000000149011612D;
/* 172 */       this.field_70179_y += d * (this.field_70146_Z.nextFloat() - 0.5F) * 0.10000000149011612D;
/*     */       
/* 174 */       if (d > 0.10000000149011612D) {
/*     */         
/* 176 */         this.targetYaw += (float)(d * (this.field_70146_Z.nextFloat() - 0.5F) * 720.0D);
/* 177 */         this.targetPitch = (float)(d * (this.field_70146_Z.nextFloat() - 0.5F) * 720.0D);
/*     */       }
/*     */       else {
/*     */         
/* 181 */         this.targetPitch = 0.0F;
/*     */       } 
/*     */       
/* 184 */       switch (m.field_72310_e) {
/*     */         
/*     */         case 0:
/* 187 */           if (this.field_70181_x > 0.0D) this.field_70181_x = -this.field_70181_x * this.bound; 
/*     */           break;
/*     */         case 1:
/* 190 */           if (this.field_70181_x < 0.0D) this.field_70181_x = -this.field_70181_x * this.bound; 
/* 191 */           this.targetPitch *= 0.3F;
/*     */           break;
/*     */         case 2:
/* 194 */           if (this.field_70179_y > 0.0D) { this.field_70179_y = -this.field_70179_y * this.bound; break; }
/* 195 */            this.field_70161_v += this.field_70179_y;
/*     */           break;
/*     */         case 3:
/* 198 */           if (this.field_70179_y < 0.0D) { this.field_70179_y = -this.field_70179_y * this.bound; break; }
/* 199 */            this.field_70161_v += this.field_70179_y;
/*     */           break;
/*     */         case 4:
/* 202 */           if (this.field_70159_w > 0.0D) { this.field_70159_w = -this.field_70159_w * this.bound; break; }
/* 203 */            this.field_70165_t += this.field_70159_w;
/*     */           break;
/*     */         case 5:
/* 206 */           if (this.field_70159_w < 0.0D) { this.field_70159_w = -this.field_70159_w * this.bound; break; }
/* 207 */            this.field_70165_t += this.field_70159_w;
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } else {
/* 213 */       this.field_70165_t += this.field_70159_w;
/* 214 */       this.field_70163_u += this.field_70181_x;
/* 215 */       this.field_70161_v += this.field_70179_y;
/* 216 */       if (d > 0.05000000074505806D)
/*     */       {
/* 218 */         rotation();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound var1) {}
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound var1) {}
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityCartridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */