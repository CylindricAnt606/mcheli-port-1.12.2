/*    */ package mcheli.mcheli.parachute;
/*    */ 
/*    */ import mcheli.parachute.MCH_EntityParachute;
/*    */ import mcheli.wrapper.W_Item;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_ItemParachute extends W_Item {
/*    */   public MCH_ItemParachute(int par1) {
/* 12 */     super(par1);
/* 13 */     this.field_77777_bU = 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
/* 21 */     if (!world.field_72995_K && player.field_70154_o == null)
/*    */     {
/* 23 */       if (!player.field_70122_E) {
/*    */         
/* 25 */         double x = player.field_70165_t + 0.5D;
/* 26 */         double y = player.field_70163_u + 3.5D;
/* 27 */         double z = player.field_70161_v + 0.5D;
/* 28 */         MCH_EntityParachute entity = new MCH_EntityParachute(world, x, y, z);
/* 29 */         entity.field_70177_z = player.field_70177_z;
/* 30 */         entity.field_70159_w = player.field_70159_w;
/* 31 */         entity.field_70181_x = player.field_70181_x;
/* 32 */         entity.field_70179_y = player.field_70179_y;
/* 33 */         entity.field_70143_R = player.field_70143_R;
/* 34 */         player.field_70143_R = 0.0F;
/* 35 */         entity.user = (Entity)player;
/* 36 */         entity.setType(1);
/*    */         
/* 38 */         world.func_72838_d((Entity)entity);
/*    */       } 
/*    */     }
/*    */     
/* 42 */     if (!player.field_71075_bZ.field_75098_d)
/*    */     {
/* 44 */       item.field_77994_a--;
/*    */     }
/*    */     
/* 47 */     return item;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\parachute\MCH_ItemParachute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */