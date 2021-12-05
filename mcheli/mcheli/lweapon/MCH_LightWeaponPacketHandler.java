/*    */ package mcheli.mcheli.lweapon;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.lweapon.MCH_ItemLightWeaponBase;
/*    */ import mcheli.lweapon.MCH_PacketLightWeaponPlayerControl;
/*    */ import mcheli.weapon.MCH_WeaponBase;
/*    */ import mcheli.weapon.MCH_WeaponCreator;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import mcheli.wrapper.W_EntityPlayer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_LightWeaponPacketHandler
/*    */ {
/*    */   public static void onPacket_PlayerControl(EntityPlayer player, ByteArrayDataInput data) {
/* 28 */     if (player.field_70170_p.field_72995_K)
/*    */       return; 
/* 30 */     MCH_PacketLightWeaponPlayerControl pc = new MCH_PacketLightWeaponPlayerControl();
/* 31 */     pc.readData(data);
/*    */     
/* 33 */     if (pc.camMode == 1)
/*    */     {
/* 35 */       player.func_82170_o(Potion.field_76439_r.func_76396_c());
/*    */     }
/*    */     
/* 38 */     ItemStack is = player.func_70694_bm();
/* 39 */     if (is == null)
/* 40 */       return;  if (!(is.func_77973_b() instanceof MCH_ItemLightWeaponBase))
/*    */       return; 
/* 42 */     MCH_ItemLightWeaponBase lweapon = (MCH_ItemLightWeaponBase)is.func_77973_b();
/*    */     
/* 44 */     if (pc.camMode == 2 && MCH_ItemLightWeaponBase.isHeld(player))
/*    */     {
/* 46 */       player.func_70690_d(new PotionEffect(Potion.field_76439_r.func_76396_c(), 255, 0, false));
/*    */     }
/*    */     
/* 49 */     if (pc.camMode > 0) MCH_Lib.DbgLog(false, "MCH_LightWeaponPacketHandler NV=%s", new Object[] { (pc.camMode == 2) ? "ON" : "OFF" });
/*    */     
/* 51 */     if (pc.useWeapon && is.func_77960_j() < is.func_77958_k()) {
/*    */       
/* 53 */       String name = MCH_ItemLightWeaponBase.getName(player.func_70694_bm());
/* 54 */       MCH_WeaponBase w = MCH_WeaponCreator.createWeapon(player.field_70170_p, name, Vec3.func_72443_a(0.0D, 0.0D, 0.0D), 0.0F, 0.0F, null, false);
/*    */       
/* 56 */       MCH_WeaponParam prm = new MCH_WeaponParam();
/* 57 */       prm.entity = (Entity)player;
/* 58 */       prm.user = (Entity)player;
/* 59 */       prm.setPosAndRot(pc.useWeaponPosX, pc.useWeaponPosY, pc.useWeaponPosZ, player.field_70177_z, player.field_70125_A);
/*    */       
/* 61 */       prm.option1 = pc.useWeaponOption1;
/* 62 */       prm.option2 = pc.useWeaponOption2;
/*    */       
/* 64 */       w.shot(prm);
/*    */       
/* 66 */       if (!player.field_71075_bZ.field_75098_d)
/*    */       {
/* 68 */         if (is.func_77958_k() == 1)
/*    */         {
/* 70 */           is.field_77994_a--;
/*    */         }
/*    */       }
/*    */       
/* 74 */       if (is.func_77958_k() > 1)
/*    */       {
/* 76 */         is.func_77964_b(is.func_77958_k());
/*    */       }
/*    */     }
/* 79 */     else if (pc.cmpReload > 0) {
/*    */       
/* 81 */       if (is.func_77960_j() > 1)
/*    */       {
/* 83 */         if (W_EntityPlayer.hasItem(player, (Item)lweapon.bullet)) {
/*    */           
/* 85 */           if (!player.field_71075_bZ.field_75098_d)
/*    */           {
/* 87 */             W_EntityPlayer.consumeInventoryItem(player, (Item)lweapon.bullet);
/*    */           }
/* 89 */           is.func_77964_b(0);
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\lweapon\MCH_LightWeaponPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */