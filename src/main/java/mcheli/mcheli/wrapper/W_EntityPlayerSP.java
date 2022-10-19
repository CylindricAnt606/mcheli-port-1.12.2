/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ public class W_EntityPlayerSP
/*    */ {
/*    */   public static void closeScreen(Entity p) {
/* 10 */     if (p instanceof EntityPlayerSP)
/*    */     {
/* 12 */       ((EntityPlayerSP)p).func_71053_j();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_EntityPlayerSP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */