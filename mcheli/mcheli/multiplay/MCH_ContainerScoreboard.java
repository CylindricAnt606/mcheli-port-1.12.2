/*    */ package mcheli.mcheli.multiplay;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ 
/*    */ 
/*    */ public class MCH_ContainerScoreboard
/*    */   extends Container
/*    */ {
/*    */   public final EntityPlayer thePlayer;
/*    */   
/*    */   public MCH_ContainerScoreboard(EntityPlayer player) {
/* 13 */     this.thePlayer = player;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75145_c(EntityPlayer player) {
/* 19 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_ContainerScoreboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */