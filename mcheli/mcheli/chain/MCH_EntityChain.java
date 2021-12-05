/*     */ package mcheli.mcheli.chain;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.wrapper.W_Entity;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
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
/*     */ 
/*     */ public class MCH_EntityChain
/*     */   extends W_Entity
/*     */ {
/*     */   private int isServerTowEntitySearchCount;
/*     */   public Entity towEntity;
/*     */   public Entity towedEntity;
/*     */   public String towEntityUUID;
/*     */   public String towedEntityUUID;
/*     */   private int chainLength;
/*     */   private boolean isTowing;
/*     */   
/*     */   public MCH_EntityChain(World world) {
/*  39 */     super(world);
/*  40 */     this.field_70156_m = true;
/*  41 */     func_70105_a(1.0F, 1.0F);
/*  42 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*     */     
/*  44 */     this.towEntity = null;
/*  45 */     this.towedEntity = null;
/*  46 */     this.towEntityUUID = "";
/*  47 */     this.towedEntityUUID = "";
/*  48 */     this.isTowing = false;
/*  49 */     this.field_70158_ak = true;
/*     */     
/*  51 */     setChainLength(4);
/*     */     
/*  53 */     this.isServerTowEntitySearchCount = 50;
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_EntityChain(World par1World, double par2, double par4, double par6) {
/*  58 */     this(par1World);
/*  59 */     func_70107_b(par2, par4 + this.field_70129_M, par6);
/*  60 */     this.field_70159_w = 0.0D;
/*  61 */     this.field_70181_x = 0.0D;
/*  62 */     this.field_70179_y = 0.0D;
/*  63 */     this.field_70169_q = par2;
/*  64 */     this.field_70167_r = par4;
/*  65 */     this.field_70166_s = par6;
/*     */   }
/*     */   protected boolean func_70041_e_() {
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   protected void func_70088_a() {
/*  72 */     func_70096_w().func_75682_a(30, Integer.valueOf(0));
/*  73 */     func_70096_w().func_75682_a(31, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70114_g(Entity par1Entity) {
/*  82 */     return par1Entity.field_70121_D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70046_E() {
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource d, float par2) {
/* 100 */     return false;
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
/*     */   
/*     */   public void setChainLength(int n) {
/* 123 */     if (n > 15) n = 15; 
/* 124 */     if (n < 3) n = 3; 
/* 125 */     this.chainLength = n;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChainLength() {
/* 130 */     return this.chainLength;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/* 135 */     super.func_70106_y();
/* 136 */     playDisconnectTowingEntity();
/* 137 */     this.isTowing = false;
/* 138 */     this.towEntity = null;
/* 139 */     this.towedEntity = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTowingEntity() {
/* 144 */     return (this.isTowing && !this.field_70128_L && this.towedEntity != null && this.towEntity != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTowEntity(Entity towedEntity, Entity towEntity) {
/* 155 */     if (towedEntity != null && towEntity != null && !towedEntity.field_70128_L && !towEntity.field_70128_L && !W_Entity.isEqual(towedEntity, towEntity)) {
/*     */ 
/*     */ 
/*     */       
/* 159 */       this.isTowing = true;
/* 160 */       this.towedEntity = towedEntity;
/* 161 */       this.towEntity = towEntity;
/* 162 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 164 */         func_70096_w().func_75692_b(30, Integer.valueOf(W_Entity.getEntityId(towedEntity)));
/* 165 */         func_70096_w().func_75692_b(31, Integer.valueOf(W_Entity.getEntityId(towEntity)));
/*     */         
/* 167 */         this.isServerTowEntitySearchCount = 0;
/*     */       } 
/*     */       
/* 170 */       if (towEntity instanceof MCH_EntityAircraft)
/*     */       {
/* 172 */         ((MCH_EntityAircraft)towEntity).setTowChainEntity(this);
/*     */       }
/* 174 */       if (towedEntity instanceof MCH_EntityAircraft)
/*     */       {
/* 176 */         ((MCH_EntityAircraft)towedEntity).setTowedChainEntity(this);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 182 */       this.isTowing = false;
/* 183 */       this.towedEntity = null;
/* 184 */       this.towEntity = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void searchTowingEntity() {
/* 191 */     if ((this.towedEntity == null || this.towEntity == null) && !this.towedEntityUUID.isEmpty() && !this.towEntityUUID.isEmpty() && this.field_70121_D != null) {
/*     */ 
/*     */ 
/*     */       
/* 195 */       List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(32.0D, 32.0D, 32.0D));
/*     */ 
/*     */       
/* 198 */       if (list != null)
/*     */       {
/* 200 */         for (int i = 0; i < list.size(); i++) {
/*     */           
/* 202 */           Entity entity = list.get(i);
/* 203 */           String uuid = entity.getPersistentID().toString();
/* 204 */           if (this.towedEntity == null && uuid.compareTo(this.towedEntityUUID) == 0) {
/*     */             
/* 206 */             this.towedEntity = entity;
/*     */           
/*     */           }
/* 209 */           else if (this.towEntity == null && uuid.compareTo(this.towEntityUUID) == 0) {
/*     */             
/* 211 */             this.towEntity = entity;
/*     */ 
/*     */           
/*     */           }
/* 215 */           else if (this.towEntity != null && this.towedEntity != null) {
/*     */             
/* 217 */             setTowEntity(this.towedEntity, this.towEntity);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 228 */     super.func_70071_h_();
/*     */     
/* 230 */     if (this.towedEntity == null || this.towedEntity.field_70128_L || this.towEntity == null || this.towEntity.field_70128_L) {
/*     */       
/* 232 */       this.towedEntity = null;
/* 233 */       this.towEntity = null;
/* 234 */       this.isTowing = false;
/*     */     } 
/*     */     
/* 237 */     if (this.towedEntity != null)
/*     */     {
/* 239 */       this.towedEntity.field_70143_R = 0.0F;
/*     */     }
/*     */     
/* 242 */     this.field_70169_q = this.field_70165_t;
/* 243 */     this.field_70167_r = this.field_70163_u;
/* 244 */     this.field_70166_s = this.field_70161_v;
/*     */     
/* 246 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 248 */       onUpdate_Client();
/*     */     }
/*     */     else {
/*     */       
/* 252 */       onUpdate_Server();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate_Client() {
/* 259 */     if (!isTowingEntity())
/*     */     {
/* 261 */       setTowEntity(this.field_70170_p.func_73045_a(func_70096_w().func_75679_c(30)), this.field_70170_p.func_73045_a(func_70096_w().func_75679_c(31)));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 266 */     double d4 = this.field_70165_t + this.field_70159_w;
/* 267 */     double d5 = this.field_70163_u + this.field_70181_x;
/* 268 */     double d11 = this.field_70161_v + this.field_70179_y;
/* 269 */     func_70107_b(d4, d5, d11);
/*     */     
/* 271 */     if (this.field_70122_E) {
/*     */       
/* 273 */       this.field_70159_w *= 0.5D;
/* 274 */       this.field_70181_x *= 0.5D;
/* 275 */       this.field_70179_y *= 0.5D;
/*     */     } 
/*     */     
/* 278 */     this.field_70159_w *= 0.99D;
/* 279 */     this.field_70181_x *= 0.95D;
/* 280 */     this.field_70179_y *= 0.99D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate_Server() {
/* 285 */     if (this.isServerTowEntitySearchCount > 0) {
/*     */       
/* 287 */       searchTowingEntity();
/*     */       
/* 289 */       if (this.towEntity != null && this.towedEntity != null)
/*     */       {
/* 291 */         this.isServerTowEntitySearchCount = 0;
/*     */       }
/*     */       else
/*     */       {
/* 295 */         this.isServerTowEntitySearchCount--;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 300 */     else if (this.towEntity == null || this.towedEntity == null) {
/*     */       
/* 302 */       func_70106_y();
/*     */     } 
/*     */ 
/*     */     
/* 306 */     this.field_70181_x -= 0.01D;
/*     */     
/* 308 */     if (!this.isTowing) {
/*     */       
/* 310 */       this.field_70159_w *= 0.8D;
/* 311 */       this.field_70181_x *= 0.8D;
/* 312 */       this.field_70179_y *= 0.8D;
/*     */     } 
/*     */     
/* 315 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */     
/* 317 */     if (isTowingEntity()) {
/*     */       
/* 319 */       func_70107_b(this.towEntity.field_70165_t, this.towEntity.field_70163_u + 2.0D, this.towEntity.field_70161_v);
/* 320 */       updateTowingEntityPosRot();
/*     */     } 
/*     */     
/* 323 */     this.field_70159_w *= 0.99D;
/* 324 */     this.field_70181_x *= 0.95D;
/* 325 */     this.field_70179_y *= 0.99D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTowingEntityPosRot() {
/* 332 */     double dx = this.towedEntity.field_70165_t - this.towEntity.field_70165_t;
/* 333 */     double dy = this.towedEntity.field_70163_u - this.towEntity.field_70163_u;
/* 334 */     double dz = this.towedEntity.field_70161_v - this.towEntity.field_70161_v;
/* 335 */     double dist = MathHelper.func_76133_a(dx * dx + dy * dy + dz * dz);
/*     */     
/* 337 */     float DIST = getChainLength();
/* 338 */     float MAX_DIST = (getChainLength() + 2);
/* 339 */     if (dist > DIST) {
/*     */       
/* 341 */       this.towedEntity.field_70177_z = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI) + 90.0F;
/* 342 */       this.towedEntity.field_70126_B = this.towedEntity.field_70177_z;
/*     */       
/* 344 */       double tmp = dist - DIST;
/* 345 */       float accl = 0.001F;
/* 346 */       this.towedEntity.field_70159_w -= dx * accl / tmp;
/* 347 */       this.towedEntity.field_70181_x -= dy * accl / tmp;
/* 348 */       this.towedEntity.field_70179_y -= dz * accl / tmp;
/* 349 */       if (dist > MAX_DIST)
/*     */       {
/* 351 */         this.towedEntity.func_70107_b(this.towEntity.field_70165_t + dx * MAX_DIST / dist, this.towEntity.field_70163_u + dy * MAX_DIST / dist, this.towEntity.field_70161_v + dz * MAX_DIST / dist);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playDisconnectTowingEntity() {
/* 361 */     W_WorldFunc.MOD_playSoundEffect(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, "chain_ct", 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound nbt) {
/* 367 */     if (this.isTowing && this.towEntity != null && !this.towEntity.field_70128_L && this.towedEntity != null && !this.towedEntity.field_70128_L) {
/*     */       
/* 369 */       nbt.func_74778_a("TowEntityUUID", this.towEntity.getPersistentID().toString());
/* 370 */       nbt.func_74778_a("TowedEntityUUID", this.towedEntity.getPersistentID().toString());
/* 371 */       nbt.func_74768_a("ChainLength", getChainLength());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound nbt) {
/* 378 */     this.towEntityUUID = nbt.func_74779_i("TowEntityUUID");
/* 379 */     this.towedEntityUUID = nbt.func_74779_i("TowedEntityUUID");
/* 380 */     setChainLength(nbt.func_74762_e("ChainLength"));
/*     */   }
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 384 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_130002_c(EntityPlayer player) {
/* 389 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\chain\MCH_EntityChain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */