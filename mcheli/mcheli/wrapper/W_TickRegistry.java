/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import mcheli.wrapper.W_TickHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_TickRegistry
/*    */ {
/*    */   public static void registerTickHandler(W_TickHandler handler, Side side) {
/* 15 */     FMLCommonHandler.instance().bus().register(handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_TickRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */