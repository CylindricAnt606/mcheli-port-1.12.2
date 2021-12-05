/*    */ package mcheli.mcheli.vehicle;
/*    */ 
/*    */ import com.google.common.io.ByteArrayDataInput;
/*    */ import mcheli.vehicle.MCH_EntityVehicle;
/*    */ import mcheli.vehicle.MCH_PacketVehiclePlayerControl;
/*    */ import mcheli.weapon.MCH_WeaponParam;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_VehiclePacketHandler
/*    */ {
/*    */   public static void onPacket_PlayerControl(EntityPlayer player, ByteArrayDataInput data) {
/* 15 */     if (!(player.field_70154_o instanceof MCH_EntityVehicle)) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 20 */     if (player.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 26 */     MCH_PacketVehiclePlayerControl pc = new MCH_PacketVehiclePlayerControl();
/* 27 */     pc.readData(data);
/*    */     
/* 29 */     MCH_EntityVehicle vehicle = (MCH_EntityVehicle)player.field_70154_o;
/*    */ 
/*    */     
/* 32 */     if (pc.isUnmount == 1) {
/*    */       
/* 34 */       vehicle.unmountEntity();
/*    */     }
/* 36 */     else if (pc.isUnmount == 2) {
/*    */       
/* 38 */       vehicle.unmountCrew();
/*    */     }
/*    */     else {
/*    */       
/* 42 */       if (pc.switchSearchLight) vehicle.setSearchLight(!vehicle.isSearchLightON()); 
/* 43 */       if (pc.switchCameraMode > 0) vehicle.switchCameraMode(player, pc.switchCameraMode - 1); 
/* 44 */       if (pc.switchWeapon >= 0) vehicle.switchWeapon((Entity)player, pc.switchWeapon); 
/* 45 */       if (pc.useWeapon) {
/*    */         
/* 47 */         MCH_WeaponParam prm = new MCH_WeaponParam();
/* 48 */         prm.entity = (Entity)vehicle;
/* 49 */         prm.user = (Entity)player;
/* 50 */         prm.setPosAndRot(pc.useWeaponPosX, pc.useWeaponPosY, pc.useWeaponPosZ, 0.0F, 0.0F);
/* 51 */         prm.option1 = pc.useWeaponOption1;
/* 52 */         prm.option2 = pc.useWeaponOption2;
/* 53 */         vehicle.useCurrentWeapon(prm);
/*    */       } 
/*    */       
/* 56 */       if (vehicle.isPilot((Entity)player)) {
/*    */         
/* 58 */         vehicle.throttleUp = pc.throttleUp;
/* 59 */         vehicle.throttleDown = pc.throttleDown;
/* 60 */         vehicle.moveLeft = pc.moveLeft;
/* 61 */         vehicle.moveRight = pc.moveRight;
/*    */       } 
/*    */       
/* 64 */       if (pc.useFlareType > 0) vehicle.useFlare(pc.useFlareType);
/*    */ 
/*    */       
/* 67 */       if (pc.unhitchChainId >= 0) {
/*    */         
/* 69 */         Entity e = player.field_70170_p.func_73045_a(pc.unhitchChainId);
/* 70 */         if (e instanceof mcheli.chain.MCH_EntityChain)
/*    */         {
/* 72 */           e.func_70106_y();
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 77 */       if (pc.openGui) vehicle.openGui(player); 
/* 78 */       if (pc.switchHatch > 0) vehicle.foldHatch((pc.switchHatch == 2));
/*    */       
/* 80 */       if (pc.isUnmount == 3) vehicle.unmountAircraft(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\vehicle\MCH_VehiclePacketHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */