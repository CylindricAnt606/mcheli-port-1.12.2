/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraftforge.client.IItemRenderer;
/*    */ import net.minecraftforge.client.MinecraftForgeClient;
/*    */ 
/*    */ public class W_MinecraftForgeClient
/*    */ {
/*    */   public static void registerItemRenderer(Item item, IItemRenderer renderer) {
/* 10 */     if (item != null)
/*    */     {
/*    */       
/* 13 */       MinecraftForgeClient.registerItemRenderer(item, renderer);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_MinecraftForgeClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */