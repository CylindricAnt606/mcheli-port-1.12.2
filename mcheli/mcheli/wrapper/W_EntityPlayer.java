/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import mcheli.wrapper.W_EntityPlayerSP;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public abstract class W_EntityPlayer
/*    */   extends EntityPlayer {
/*    */   public W_EntityPlayer(World par1World, EntityPlayer player) {
/* 17 */     super(par1World, player.func_146103_bH());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void closeScreen(Entity p) {
/* 24 */     if (p != null)
/*    */     {
/* 26 */       if (p.field_70170_p.field_72995_K) {
/*    */         
/* 28 */         W_EntityPlayerSP.closeScreen(p);
/*    */ 
/*    */       
/*    */       }
/* 32 */       else if (p instanceof EntityPlayerMP) {
/*    */         
/* 34 */         ((EntityPlayerMP)p).func_71053_j();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean hasItem(EntityPlayer player, Item item) {
/* 43 */     return (item != null && player.field_71071_by.func_146028_b(item));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean consumeInventoryItem(EntityPlayer player, Item item) {
/* 51 */     return (item != null && player.field_71071_by.func_146026_a(item));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addChatMessage(EntityPlayer player, String s) {
/* 59 */     player.func_145747_a((IChatComponent)new ChatComponentText(s));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static EntityItem dropPlayerItemWithRandomChoice(EntityPlayer player, ItemStack item, boolean b1, boolean b2) {
/* 67 */     return player.func_146097_a(item, b1, b2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isPlayer(Entity entity) {
/* 74 */     if (entity instanceof EntityPlayer)
/*    */     {
/* 76 */       return true;
/*    */     }
/* 78 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_EntityPlayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */