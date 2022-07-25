/*    */ package mcheli.mcheli.weapon;
/*    */ import mcheli.wrapper.ChatMessageComponent;
/*    */ import mcheli.wrapper.W_EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ChunkCoordinates;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_DummyEntityPlayer extends W_EntityPlayer {
/*    */   public MCH_DummyEntityPlayer(World p_i45324_1_, EntityPlayer player) {
/* 11 */     super(p_i45324_1_, player);
/*    */   }
/*    */   public void func_145747_a(IChatComponent var1) {}
/*    */   
/*    */   public boolean func_70003_b(int var1, String var2) {
/* 16 */     return false;
/*    */   } public ChunkCoordinates func_82114_b() {
/* 18 */     return null;
/*    */   }
/*    */   
/*    */   public void sendChatToPlayer(ChatMessageComponent chatmessagecomponent) {}
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\weapon\MCH_DummyEntityPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */