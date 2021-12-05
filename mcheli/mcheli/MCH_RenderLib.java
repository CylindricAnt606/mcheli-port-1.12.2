/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.util.Vec3;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_RenderLib
/*    */ {
/*    */   public static void drawLine(Vec3[] points, int color) {
/* 15 */     drawLine(points, color, 1, 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void drawLine(Vec3[] points, int color, int mode, int width) {
/* 20 */     int prevWidth = GL11.glGetInteger(2849);
/* 21 */     GL11.glDisable(3553);
/* 22 */     GL11.glEnable(3042);
/* 23 */     GL11.glBlendFunc(770, 771);
/* 24 */     GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color >> 0 & 0xFF), (byte)(color >> 24 & 0xFF));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     GL11.glLineWidth(width);
/*    */     
/* 31 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 32 */     tessellator.func_78371_b(mode);
/* 33 */     for (Vec3 v : points)
/*    */     {
/* 35 */       tessellator.func_78377_a(v.field_72450_a, v.field_72448_b, v.field_72449_c);
/*    */     }
/* 37 */     tessellator.func_78381_a();
/*    */     
/* 39 */     GL11.glEnable(3553);
/* 40 */     GL11.glDisable(3042);
/* 41 */     GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
/* 42 */     GL11.glLineWidth(prevWidth);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_RenderLib.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */