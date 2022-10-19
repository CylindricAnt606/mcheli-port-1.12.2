/*     */ package mcheli.mcheli.weapon;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.weapon.MCH_BulletModel;
/*     */ import mcheli.weapon.MCH_DefaultBulletModels;
/*     */ import mcheli.weapon.MCH_DummyEntityPlayer;
/*     */ import mcheli.weapon.MCH_EntityBaseBullet;
/*     */ import mcheli.wrapper.W_Blocks;
/*     */ import mcheli.wrapper.W_Item;
/*     */ import mcheli.wrapper.W_WorldFunc;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MCH_EntityDispensedItem extends MCH_EntityBaseBullet {
/*     */   public MCH_EntityDispensedItem(World par1World) {
/*  21 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_EntityDispensedItem(World par1World, double posX, double posY, double posZ, double targetX, double targetY, double targetZ, float yaw, float pitch, double acceleration) {
/*  28 */     super(par1World, posX, posY, posZ, targetX, targetY, targetZ, yaw, pitch, acceleration);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  34 */     super.func_70071_h_();
/*     */     
/*  36 */     if (getInfo() != null && !(getInfo()).disableSmoke)
/*     */     {
/*  38 */       spawnParticle((getInfo()).trajectoryParticleName, 3, 7.0F * (getInfo()).smokeSize);
/*     */     }
/*     */     
/*  41 */     if (!this.field_70170_p.field_72995_K && getInfo() != null) {
/*     */       
/*  43 */       if (this.acceleration < 1.0E-4D) {
/*     */         
/*  45 */         this.field_70159_w *= 0.999D;
/*  46 */         this.field_70179_y *= 0.999D;
/*     */       } 
/*     */       
/*  49 */       if (func_70090_H()) {
/*     */         
/*  51 */         this.field_70159_w *= (getInfo()).velocityInWater;
/*  52 */         this.field_70181_x *= (getInfo()).velocityInWater;
/*  53 */         this.field_70179_y *= (getInfo()).velocityInWater;
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
/*     */     
/*  85 */     onUpdateBomblet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onImpact(MovingObjectPosition m, float damageFactor) {
/*  92 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/*  94 */       this.field_70121_D.field_72337_e += 2000.0D;
/*  95 */       this.field_70121_D.field_72338_b += 2000.0D;
/*     */       
/*  97 */       EntityPlayer player = null;
/*  98 */       Item item = null;
/*  99 */       int itemDamage = 0;
/* 100 */       if (m != null && getInfo() != null) {
/*     */         
/* 102 */         if (this.shootingAircraft instanceof EntityPlayer)
/*     */         {
/* 104 */           player = (EntityPlayer)this.shootingAircraft;
/*     */         }
/* 106 */         if (this.shootingEntity instanceof EntityPlayer)
/*     */         {
/* 108 */           player = (EntityPlayer)this.shootingEntity;
/*     */         }
/*     */         
/* 111 */         item = (getInfo()).dispenseItem;
/* 112 */         itemDamage = (getInfo()).dispenseDamege;
/*     */       } 
/*     */       
/* 115 */       if (player != null && !player.field_70128_L && item != null) {
/*     */         
/* 117 */         MCH_DummyEntityPlayer mCH_DummyEntityPlayer = new MCH_DummyEntityPlayer(this.field_70170_p, player);
/* 118 */         ((EntityPlayer)mCH_DummyEntityPlayer).field_70125_A = 90.0F;
/* 119 */         int RNG = (getInfo()).dispenseRange - 1;
/* 120 */         for (int x = -RNG; x <= RNG; x++) {
/*     */           
/* 122 */           for (int y = -RNG; y <= RNG; y++) {
/*     */             
/* 124 */             if (y >= 0 && y < 256)
/*     */             {
/* 126 */               for (int z = -RNG; z <= RNG; z++) {
/*     */                 
/* 128 */                 int dist = x * x + y * y + z * z;
/* 129 */                 if (dist <= RNG * RNG)
/*     */                 {
/* 131 */                   if (dist <= 0.5D * RNG * RNG) {
/*     */                     
/* 133 */                     useItemToBlock(m.field_72311_b + x, m.field_72312_c + y, m.field_72309_d + z, item, itemDamage, (EntityPlayer)mCH_DummyEntityPlayer);
/*     */                   }
/* 135 */                   else if (this.field_70146_Z.nextInt(2) == 0) {
/*     */                     
/* 137 */                     useItemToBlock(m.field_72311_b + x, m.field_72312_c + y, m.field_72309_d + z, item, itemDamage, (EntityPlayer)mCH_DummyEntityPlayer);
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 145 */       func_70106_y();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void useItemToBlock(int x, int y, int z, Item item, int itemDamage, EntityPlayer dummyPlayer) {
/* 150 */     dummyPlayer.field_70165_t = x + 0.5D;
/* 151 */     dummyPlayer.field_70163_u = y + 2.5D;
/* 152 */     dummyPlayer.field_70161_v = z + 0.5D;
/* 153 */     dummyPlayer.field_70177_z = this.field_70146_Z.nextInt(360);
/* 154 */     Block block = W_WorldFunc.getBlock(this.field_70170_p, x, y, z);
/* 155 */     Material blockMat = W_WorldFunc.getBlockMaterial(this.field_70170_p, x, y, z);
/* 156 */     if (block != W_Blocks.field_150350_a && blockMat != Material.field_151579_a)
/*     */     {
/* 158 */       if (item == W_Item.getItemByName("water_bucket")) {
/*     */         
/* 160 */         if (MCH_Config.Collision_DestroyBlock.prmBool)
/*     */         {
/* 162 */           if (blockMat == Material.field_151581_o) {
/*     */             
/* 164 */             this.field_70170_p.func_147468_f(x, y, z);
/*     */           }
/* 166 */           else if (blockMat == Material.field_151587_i) {
/*     */             
/* 168 */             int metadata = this.field_70170_p.func_72805_g(x, y, z);
/* 169 */             if (metadata == 0)
/*     */             {
/* 171 */               W_WorldFunc.setBlock(this.field_70170_p, x, y, z, W_Blocks.field_150343_Z);
/*     */             }
/* 173 */             else if (metadata <= 4)
/*     */             {
/* 175 */               W_WorldFunc.setBlock(this.field_70170_p, x, y, z, W_Blocks.field_150347_e);
/*     */             }
/*     */           
/*     */           } 
/*     */         }
/* 180 */       } else if (!item.onItemUseFirst(new ItemStack(item, 1, itemDamage), dummyPlayer, this.field_70170_p, x, y, z, 1, x, y, z)) {
/*     */         
/* 182 */         if (!item.func_77648_a(new ItemStack(item, 1, itemDamage), dummyPlayer, this.field_70170_p, x, y, z, 1, x, y, z))
/*     */         {
/* 184 */           item.func_77659_a(new ItemStack(item, 1, itemDamage), this.field_70170_p, dummyPlayer);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void sprinkleBomblet() {
/* 192 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 194 */       mcheli.weapon.MCH_EntityDispensedItem e = new mcheli.weapon.MCH_EntityDispensedItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70159_w, this.field_70181_x, this.field_70179_y, this.field_70146_Z.nextInt(360), 0.0F, this.acceleration);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 199 */       e.setParameterFromWeapon(this, this.shootingAircraft, this.shootingEntity);
/* 200 */       e.setName(getName());
/*     */       
/* 202 */       float MOTION = 1.0F;
/* 203 */       float RANDOM = (getInfo()).bombletDiff;
/* 204 */       e.field_70159_w = this.field_70159_w * 1.0D + ((this.field_70146_Z.nextFloat() - 0.5F) * RANDOM);
/* 205 */       e.field_70181_x = this.field_70181_x * 1.0D / 2.0D + ((this.field_70146_Z.nextFloat() - 0.5F) * RANDOM / 2.0F);
/* 206 */       e.field_70179_y = this.field_70179_y * 1.0D + ((this.field_70146_Z.nextFloat() - 0.5F) * RANDOM);
/* 207 */       e.setBomblet();
/*     */       
/* 209 */       this.field_70170_p.func_72838_d((Entity)e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_BulletModel getDefaultBulletModel() {
/* 216 */     return MCH_DefaultBulletModels.Bomb;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_EntityDispensedItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */