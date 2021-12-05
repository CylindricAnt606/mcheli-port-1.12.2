/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.stats.Achievement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_Achievement
/*    */ {
/*    */   public static Achievement registerAchievement(String par1, String par2Str, int par3, int par4, Item par5Item, Achievement par6Achievement) {
/* 11 */     return (new Achievement(par1, par2Str, par3, par4, par5Item, par6Achievement)).func_75966_h().func_75971_g();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Achievement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */