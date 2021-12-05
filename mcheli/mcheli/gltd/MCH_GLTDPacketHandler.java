/*    */ package mcheli.mcheli.gltd;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import mcheli.gltd.MCH_EntityGLTD;
/*    */ import mcheli.gltd.MCH_PacketGLTDPlayerControl;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_GLTDPacketHandler
/*    */ {
/*    */   public static void onPacket_GLTDPlayerControl(EntityPlayer player, ByteArrayDataInput data) {
/* 13 */     if (!(player.field_70154_o instanceof MCH_EntityGLTD)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 18 */     if (player.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 24 */     MCH_PacketGLTDPlayerControl pc = new MCH_PacketGLTDPlayerControl();
/* 25 */     pc.readData(data);
/*    */     
/* 27 */     MCH_EntityGLTD gltd = (MCH_EntityGLTD)player.field_70154_o;
/*    */     
/* 29 */     if (pc.unmount) {
/*    */       
/* 31 */       if (gltd.field_70153_n != null)
/*    */       {
/* 33 */         gltd.field_70153_n.func_70078_a(null);
/*    */       }
/*    */     }
/*    */     else {
/*    */       
/* 38 */       if (pc.switchCameraMode >= 0) gltd.camera.setMode(0, pc.switchCameraMode); 
/* 39 */       if (pc.switchWeapon >= 0) gltd.switchWeapon(pc.switchWeapon); 
/* 40 */       if (pc.useWeapon)
/*    */       {
/* 42 */         gltd.useCurrentWeapon(pc.useWeaponOption1, pc.useWeaponOption2);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gltd\MCH_GLTDPacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */