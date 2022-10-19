/*    */ package mcheli.mcheli.particles;
/*    */ 
/*    */ import mcheli.particles.MCH_EntityParticleBase;
/*    */ import mcheli.wrapper.W_Block;
/*    */ import mcheli.wrapper.W_McClient;
/*    */ import mcheli.wrapper.W_WorldFunc;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_EntityParticleSplash
/*    */   extends MCH_EntityParticleBase
/*    */ {
/*    */   public MCH_EntityParticleSplash(World par1World, double x, double y, double z, double mx, double my, double mz) {
/* 17 */     super(par1World, x, y, z, mx, my, mz);
/* 18 */     this.field_70552_h = this.field_70553_i = this.field_70551_j = this.field_70146_Z.nextFloat() * 0.3F + 0.7F;
/* 19 */     setParticleScale(this.field_70146_Z.nextFloat() * 0.5F + 5.0F);
/* 20 */     setParticleMaxAge((int)(80.0D / (this.field_70146_Z.nextFloat() * 0.8D + 0.2D)) + 2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70071_h_() {
/* 26 */     this.field_70169_q = this.field_70165_t;
/* 27 */     this.field_70167_r = this.field_70163_u;
/* 28 */     this.field_70166_s = this.field_70161_v;
/*    */     
/* 30 */     if (this.field_70546_d < this.field_70547_e) {
/*    */       
/* 32 */       func_70536_a((int)(8.0D * this.field_70546_d / this.field_70547_e));
/* 33 */       this.field_70546_d++;
/*    */     }
/*    */     else {
/*    */       
/* 37 */       func_70106_y();
/*    */     } 
/*    */     
/* 40 */     this.field_70181_x -= 0.05999999865889549D;
/* 41 */     Block block = W_WorldFunc.getBlock(this.field_70170_p, (int)(this.field_70165_t + 0.5D), (int)(this.field_70163_u + 0.5D), (int)(this.field_70161_v + 0.5D));
/* 42 */     boolean beforeInWater = W_Block.func_149680_a(block, W_Block.getWater());
/*    */     
/* 44 */     func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*    */     
/* 46 */     block = W_WorldFunc.getBlock(this.field_70170_p, (int)(this.field_70165_t + 0.5D), (int)(this.field_70163_u + 0.5D), (int)(this.field_70161_v + 0.5D));
/* 47 */     boolean nowInWater = W_Block.func_149680_a(block, W_Block.getWater());
/*    */ 
/*    */     
/* 50 */     if (this.field_70181_x < -0.6D && !beforeInWater && nowInWater) {
/*    */       
/* 52 */       double p = -this.field_70181_x * 10.0D;
/*    */       
/* 54 */       for (int i = 0; i < p; i++)
/*    */       {
/* 56 */         this.field_70170_p.func_72869_a("splash", this.field_70165_t + 0.5D + (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, this.field_70163_u + this.field_70146_Z.nextDouble(), this.field_70161_v + 0.5D + (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, 4.0D, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 64 */         this.field_70170_p.func_72869_a("bubble", this.field_70165_t + 0.5D + (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, this.field_70163_u - this.field_70146_Z.nextDouble(), this.field_70161_v + 0.5D + (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D, -0.5D, (this.field_70146_Z.nextDouble() - 0.5D) * 2.0D);
/*    */ 
/*    */ 
/*    */       
/*    */       }
/*    */ 
/*    */ 
/*    */     
/*    */     }
/* 73 */     else if (this.field_70122_E) {
/*    */       
/* 75 */       func_70106_y();
/*    */     } 
/* 77 */     this.field_70159_w *= 0.9D;
/* 78 */     this.field_70179_y *= 0.9D;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_70539_a(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
/* 83 */     W_McClient.MOD_bindTexture("textures/particles/smoke.png");
/*    */     
/* 85 */     float f6 = this.field_94054_b / 8.0F;
/* 86 */     float f7 = f6 + 0.125F;
/* 87 */     float f8 = 0.0F;
/* 88 */     float f9 = 1.0F;
/* 89 */     float f10 = 0.1F * this.field_70544_f;
/*    */     
/* 91 */     float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * par2 - field_70556_an);
/* 92 */     float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * par2 - field_70554_ao);
/* 93 */     float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * par2 - field_70555_ap);
/* 94 */     float f14 = 1.0F;
/* 95 */     par1Tessellator.func_78369_a(this.field_70552_h * f14, this.field_70553_i * f14, this.field_70551_j * f14, this.field_82339_as);
/* 96 */     par1Tessellator.func_78374_a((f11 - par3 * f10 - par6 * f10), (f12 - par4 * f10), (f13 - par5 * f10 - par7 * f10), f7, f9);
/* 97 */     par1Tessellator.func_78374_a((f11 - par3 * f10 + par6 * f10), (f12 + par4 * f10), (f13 - par5 * f10 + par7 * f10), f7, f8);
/* 98 */     par1Tessellator.func_78374_a((f11 + par3 * f10 + par6 * f10), (f12 + par4 * f10), (f13 + par5 * f10 + par7 * f10), f6, f8);
/* 99 */     par1Tessellator.func_78374_a((f11 + par3 * f10 - par6 * f10), (f12 - par4 * f10), (f13 + par5 * f10 - par7 * f10), f6, f9);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\particles\MCH_EntityParticleSplash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */