/*    */ package mcheli.mcheli.debug;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.wrapper.W_ModelBase;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_ModelTest
/*    */   extends W_ModelBase {
/*    */   public ModelRenderer test;
/*    */   
/*    */   public MCH_ModelTest() {
/* 15 */     int SIZE = 10;
/* 16 */     this.test = new ModelRenderer((ModelBase)this, 0, 0);
/* 17 */     this.test.func_78790_a(-5.0F, -5.0F, -5.0F, 10, 10, 10, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderModel(double yaw, double pitch, float par7) {
/* 23 */     this.test.func_78785_a(par7);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\debug\MCH_ModelTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */