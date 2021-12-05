/*    */ package mcheli.mcheli.flare;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.wrapper.W_ModelBase;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_ModelFlare extends W_ModelBase {
/*    */   public ModelRenderer model;
/*    */   
/*    */   public MCH_ModelFlare() {
/* 14 */     int SIZE = 4;
/* 15 */     this.model = (new ModelRenderer((ModelBase)this, 0, 0)).func_78787_b(4, 4);
/* 16 */     this.model.func_78790_a(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderModel(double yaw, double pitch, float par7) {
/* 22 */     this.model.func_78785_a(par7);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\flare\MCH_ModelFlare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */