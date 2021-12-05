/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import mcheli.MCH_ModelManager;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_TEST_ModelRenderer
/*    */   extends ModelRenderer
/*    */ {
/*    */   public MCH_TEST_ModelRenderer(ModelBase par1ModelBase) {
/* 15 */     super(par1ModelBase);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_78785_a(float par1) {
/* 20 */     GL11.glPushMatrix();
/* 21 */     GL11.glScaled(0.2D, -0.2D, 0.2D);
/* 22 */     MCH_ModelManager.render("helicopters", "ah-64");
/* 23 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_TEST_ModelRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */