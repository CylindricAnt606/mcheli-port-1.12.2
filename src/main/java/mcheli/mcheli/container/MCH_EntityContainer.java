/*     */ package mcheli.mcheli.container;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_IEntityCanRideAircraft;
/*     */ import mcheli.aircraft.MCH_SeatRackInfo;
/*     */ import mcheli.multiplay.MCH_Multiplay;
/*     */ import mcheli.wrapper.W_AxisAlignedBB;
/*     */ import mcheli.wrapper.W_Block;
/*     */ import mcheli.wrapper.W_Blocks;
/*     */ import mcheli.wrapper.W_EntityContainer;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_EntityContainer
/*     */   extends W_EntityContainer
/*     */   implements MCH_IEntityCanRideAircraft
/*     */ {
/*     */   private boolean field_70279_a;
/*     */   private double speedMultiplier;
/*     */   private int boatPosRotationIncrements;
/*     */   private double boatX;
/*     */   private double boatY;
/*     */   private double boatZ;
/*     */   private double boatYaw;
/*     */   private double boatPitch;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityX;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityY;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityZ;
/*     */   
/*     */   public MCH_EntityContainer(World par1World) {
/*  50 */     super(par1World);
/*  51 */     this.speedMultiplier = 0.07D;
/*  52 */     this.field_70156_m = true;
/*  53 */     func_70105_a(2.0F, 1.0F);
/*  54 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*  55 */     this.field_70138_W = 0.6F;
/*  56 */     this.field_70178_ae = true;
/*  57 */     this.field_70155_l = 2.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_EntityContainer(World par1World, double par2, double par4, double par6) {
/*  62 */     this(par1World);
/*  63 */     func_70107_b(par2, par4 + this.field_70129_M, par6);
/*  64 */     this.field_70159_w = 0.0D;
/*  65 */     this.field_70181_x = 0.0D;
/*  66 */     this.field_70179_y = 0.0D;
/*  67 */     this.field_70169_q = par2;
/*  68 */     this.field_70167_r = par4;
/*  69 */     this.field_70166_s = par6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70041_e_() {
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  83 */     this.field_70180_af.func_75682_a(17, new Integer(0));
/*  84 */     this.field_70180_af.func_75682_a(18, new Integer(1));
/*  85 */     this.field_70180_af.func_75682_a(19, new Integer(0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70114_g(Entity par1Entity) {
/*  94 */     return par1Entity.field_70121_D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_70046_E() {
/* 102 */     return this.field_70121_D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70104_M() {
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 116 */     return 54;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInvName() {
/* 121 */     return "Container " + super.getInvName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 129 */     return -0.3D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource ds, float damage) {
/* 137 */     if (func_85032_ar())
/*     */     {
/* 139 */       return false;
/*     */     }
/*     */     
/* 142 */     if (this.field_70170_p.field_72995_K || this.field_70128_L)
/*     */     {
/* 144 */       return false;
/*     */     }
/*     */     
/* 147 */     damage = MCH_Config.applyDamageByExternal((Entity)this, ds, damage);
/*     */     
/* 149 */     if (!MCH_Multiplay.canAttackEntity(ds, (Entity)this))
/*     */     {
/* 151 */       return false;
/*     */     }
/*     */     
/* 154 */     if (ds.func_76346_g() instanceof EntityPlayer && ds.func_76355_l().equalsIgnoreCase("player")) {
/*     */       
/* 156 */       MCH_Lib.DbgLog(this.field_70170_p, "MCH_EntityContainer.attackEntityFrom:damage=%.1f:%s", new Object[] { Float.valueOf(damage), ds.func_76355_l() });
/* 157 */       W_WorldFunc.MOD_playSoundAtEntity((Entity)this, "hit", 1.0F, 1.3F);
/* 158 */       setDamageTaken(getDamageTaken() + (int)(damage * 20.0F));
/*     */     }
/*     */     else {
/*     */       
/* 162 */       return false;
/*     */     } 
/* 164 */     setForwardDirection(-getForwardDirection());
/* 165 */     setTimeSinceHit(10);
/* 166 */     func_70018_K();
/* 167 */     boolean flag = (ds.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)ds.func_76346_g()).field_71075_bZ.field_75098_d);
/*     */     
/* 169 */     if (flag || getDamageTaken() > 40.0F) {
/*     */       
/* 171 */       if (!flag)
/*     */       {
/* 173 */         dropItemWithOffset((Item)MCH_MOD.itemContainer, 1, 0.0F);
/*     */       }
/*     */       
/* 176 */       func_70106_y();
/*     */     } 
/* 178 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70057_ab() {
/* 188 */     setForwardDirection(-getForwardDirection());
/* 189 */     setTimeSinceHit(10);
/* 190 */     setDamageTaken(getDamageTaken() * 11);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/* 198 */     return !this.field_70128_L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 209 */     this.boatPosRotationIncrements = par9 + 10;
/*     */     
/* 211 */     this.boatX = par1;
/* 212 */     this.boatY = par3;
/* 213 */     this.boatZ = par5;
/* 214 */     this.boatYaw = par7;
/* 215 */     this.boatPitch = par8;
/* 216 */     this.field_70159_w = this.velocityX;
/* 217 */     this.field_70181_x = this.velocityY;
/* 218 */     this.field_70179_y = this.velocityZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double par1, double par3, double par5) {
/* 228 */     this.velocityX = this.field_70159_w = par1;
/* 229 */     this.velocityY = this.field_70181_x = par3;
/* 230 */     this.velocityZ = this.field_70179_y = par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 238 */     super.func_70071_h_();
/*     */     
/* 240 */     if (getTimeSinceHit() > 0)
/*     */     {
/* 242 */       setTimeSinceHit(getTimeSinceHit() - 1);
/*     */     }
/*     */     
/* 245 */     if (getDamageTaken() > 0.0F)
/*     */     {
/* 247 */       setDamageTaken(getDamageTaken() - 1);
/*     */     }
/*     */     
/* 250 */     this.field_70169_q = this.field_70165_t;
/* 251 */     this.field_70167_r = this.field_70163_u;
/* 252 */     this.field_70166_s = this.field_70161_v;
/* 253 */     byte b0 = 5;
/* 254 */     double d0 = 0.0D;
/*     */     
/* 256 */     for (int i = 0; i < b0; i++) {
/*     */       
/* 258 */       double d1 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (i + 0) / b0 - 0.125D;
/* 259 */       double d2 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (i + 1) / b0 - 0.125D;
/* 260 */       AxisAlignedBB axisalignedbb = W_AxisAlignedBB.getAABB(this.field_70121_D.field_72340_a, d1, this.field_70121_D.field_72339_c, this.field_70121_D.field_72336_d, d2, this.field_70121_D.field_72334_f);
/*     */       
/* 262 */       if (this.field_70170_p.func_72830_b(axisalignedbb, Material.field_151586_h)) {
/*     */         
/* 264 */         d0 += 1.0D / b0;
/*     */       }
/* 266 */       else if (this.field_70170_p.func_72830_b(axisalignedbb, Material.field_151587_i)) {
/*     */         
/* 268 */         d0 += 1.0D / b0;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 273 */     double d3 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */ 
/*     */ 
/*     */     
/* 277 */     if (d3 > 0.2625D) {
/*     */       
/* 279 */       double d4 = Math.cos(this.field_70177_z * Math.PI / 180.0D);
/* 280 */       double d1 = Math.sin(this.field_70177_z * Math.PI / 180.0D);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     if (this.field_70170_p.field_72995_K) {
/*     */       
/* 288 */       if (this.boatPosRotationIncrements > 0)
/*     */       {
/* 290 */         double d4 = this.field_70165_t + (this.boatX - this.field_70165_t) / this.boatPosRotationIncrements;
/* 291 */         double d5 = this.field_70163_u + (this.boatY - this.field_70163_u) / this.boatPosRotationIncrements;
/* 292 */         double d11 = this.field_70161_v + (this.boatZ - this.field_70161_v) / this.boatPosRotationIncrements;
/* 293 */         double d10 = MathHelper.func_76138_g(this.boatYaw - this.field_70177_z);
/* 294 */         this.field_70177_z = (float)(this.field_70177_z + d10 / this.boatPosRotationIncrements);
/* 295 */         this.field_70125_A = (float)(this.field_70125_A + (this.boatPitch - this.field_70125_A) / this.boatPosRotationIncrements);
/* 296 */         this.boatPosRotationIncrements--;
/* 297 */         func_70107_b(d4, d5, d11);
/* 298 */         func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */       }
/*     */       else
/*     */       {
/* 302 */         double d4 = this.field_70165_t + this.field_70159_w;
/* 303 */         double d5 = this.field_70163_u + this.field_70181_x;
/* 304 */         double d11 = this.field_70161_v + this.field_70179_y;
/* 305 */         func_70107_b(d4, d5, d11);
/*     */         
/* 307 */         if (this.field_70122_E) {
/*     */           
/* 309 */           float groundSpeed = 0.9F;
/* 310 */           this.field_70159_w *= 0.8999999761581421D;
/*     */           
/* 312 */           this.field_70179_y *= 0.8999999761581421D;
/*     */         } 
/*     */         
/* 315 */         this.field_70159_w *= 0.99D;
/* 316 */         this.field_70181_x *= 0.95D;
/* 317 */         this.field_70179_y *= 0.99D;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 322 */       if (d0 < 1.0D) {
/*     */         
/* 324 */         double d = d0 * 2.0D - 1.0D;
/* 325 */         this.field_70181_x += 0.04D * d;
/*     */       }
/*     */       else {
/*     */         
/* 329 */         if (this.field_70181_x < 0.0D)
/*     */         {
/* 331 */           this.field_70181_x /= 2.0D;
/*     */         }
/*     */         
/* 334 */         this.field_70181_x += 0.007D;
/*     */       } 
/*     */       
/* 337 */       double d4 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */       
/* 339 */       if (d4 > 0.35D) {
/*     */         
/* 341 */         double d = 0.35D / d4;
/* 342 */         this.field_70159_w *= d;
/* 343 */         this.field_70179_y *= d;
/* 344 */         d4 = 0.35D;
/*     */       } 
/*     */       
/* 347 */       if (d4 > d3 && this.speedMultiplier < 0.35D) {
/*     */         
/* 349 */         this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;
/*     */         
/* 351 */         if (this.speedMultiplier > 0.35D)
/*     */         {
/* 353 */           this.speedMultiplier = 0.35D;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 358 */         this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;
/*     */         
/* 360 */         if (this.speedMultiplier < 0.07D)
/*     */         {
/* 362 */           this.speedMultiplier = 0.07D;
/*     */         }
/*     */       } 
/*     */       
/* 366 */       if (this.field_70122_E) {
/*     */         
/* 368 */         float groundSpeed = 0.9F;
/* 369 */         this.field_70159_w *= 0.8999999761581421D;
/*     */         
/* 371 */         this.field_70179_y *= 0.8999999761581421D;
/*     */       } 
/*     */       
/* 374 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */       
/* 376 */       this.field_70159_w *= 0.99D;
/* 377 */       this.field_70181_x *= 0.95D;
/* 378 */       this.field_70179_y *= 0.99D;
/*     */       
/* 380 */       this.field_70125_A = 0.0F;
/* 381 */       double d5 = this.field_70177_z;
/* 382 */       double d11 = this.field_70169_q - this.field_70165_t;
/* 383 */       double d10 = this.field_70166_s - this.field_70161_v;
/*     */       
/* 385 */       if (d11 * d11 + d10 * d10 > 0.001D)
/*     */       {
/* 387 */         d5 = (float)(Math.atan2(d10, d11) * 180.0D / Math.PI);
/*     */       }
/*     */       
/* 390 */       double d12 = MathHelper.func_76138_g(d5 - this.field_70177_z);
/*     */       
/* 392 */       if (d12 > 5.0D)
/*     */       {
/* 394 */         d12 = 5.0D;
/*     */       }
/*     */       
/* 397 */       if (d12 < -5.0D)
/*     */       {
/* 399 */         d12 = -5.0D;
/*     */       }
/*     */       
/* 402 */       this.field_70177_z = (float)(this.field_70177_z + d12);
/* 403 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */       
/* 405 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 407 */         List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(0.2D, 0.0D, 0.2D));
/*     */ 
/*     */         
/* 410 */         if (list != null && !list.isEmpty())
/*     */         {
/* 412 */           for (int l = 0; l < list.size(); l++) {
/*     */             
/* 414 */             Entity entity = list.get(l);
/*     */             
/* 416 */             if (entity.func_70104_M() && entity instanceof mcheli.container.MCH_EntityContainer)
/*     */             {
/* 418 */               entity.func_70108_f((Entity)this);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 423 */         if (MCH_Config.Collision_DestroyBlock.prmBool)
/*     */         {
/* 425 */           for (int l = 0; l < 4; l++) {
/*     */             
/* 427 */             int i1 = MathHelper.func_76128_c(this.field_70165_t + ((l % 2) - 0.5D) * 0.8D);
/* 428 */             int j1 = MathHelper.func_76128_c(this.field_70161_v + ((l / 2) - 0.5D) * 0.8D);
/*     */             
/* 430 */             for (int k1 = 0; k1 < 2; k1++) {
/*     */               
/* 432 */               int l1 = MathHelper.func_76128_c(this.field_70163_u) + k1;
/*     */               
/* 434 */               if (W_WorldFunc.isEqualBlock(this.field_70170_p, i1, l1, j1, W_Block.getSnowLayer())) {
/*     */                 
/* 436 */                 this.field_70170_p.func_147468_f(i1, l1, j1);
/*     */               }
/* 438 */               else if (W_WorldFunc.isEqualBlock(this.field_70170_p, i1, l1, j1, W_Blocks.field_150392_bi)) {
/*     */                 
/* 440 */                 W_WorldFunc.destroyBlock(this.field_70170_p, i1, l1, j1, true);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 454 */     super.func_70014_b(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 462 */     super.func_70037_a(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 468 */     return 2.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_130002_c(EntityPlayer player) {
/* 476 */     if (player != null)
/*     */     {
/* 478 */       openInventory(player);
/*     */     }
/* 480 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamageTaken(int par1) {
/* 488 */     this.field_70180_af.func_75692_b(19, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDamageTaken() {
/* 496 */     return this.field_70180_af.func_75679_c(19);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeSinceHit(int par1) {
/* 504 */     this.field_70180_af.func_75692_b(17, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTimeSinceHit() {
/* 512 */     return this.field_70180_af.func_75679_c(17);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForwardDirection(int par1) {
/* 520 */     this.field_70180_af.func_75692_b(18, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getForwardDirection() {
/* 528 */     return this.field_70180_af.func_75679_c(18);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRideAircraft(MCH_EntityAircraft ac, int seatID, MCH_SeatRackInfo info) {
/* 534 */     for (String s : info.names) {
/*     */       
/* 536 */       if (s.equalsIgnoreCase("container"))
/*     */       {
/* 538 */         return (ac.field_70154_o == null && this.field_70154_o == null);
/*     */       }
/*     */     } 
/* 541 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSkipNormalRender() {
/* 546 */     return this.field_70154_o instanceof mcheli.aircraft.MCH_EntitySeat;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\container\MCH_EntityContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */