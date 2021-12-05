/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_Vec3
/*    */ {
/*    */   public static void rotateAroundZ(float par1, Vec3 vOut) {
/* 11 */     float f1 = MathHelper.func_76134_b(par1);
/* 12 */     float f2 = MathHelper.func_76126_a(par1);
/* 13 */     double d0 = vOut.field_72450_a * f1 + vOut.field_72448_b * f2;
/* 14 */     double d1 = vOut.field_72448_b * f1 - vOut.field_72450_a * f2;
/* 15 */     double d2 = vOut.field_72449_c;
/* 16 */     vOut.field_72450_a = d0;
/* 17 */     vOut.field_72448_b = d1;
/* 18 */     vOut.field_72449_c = d2;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Vec3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */