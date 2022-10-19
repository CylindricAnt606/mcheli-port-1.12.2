/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import mcheli.MCH_ClientTickHandlerBase;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.aircraft.MCH_EntityAircraft;
/*     */ import mcheli.aircraft.MCH_EntitySeat;
/*     */ import mcheli.aircraft.MCH_PacketSeatPlayerControl;
/*     */ import mcheli.wrapper.W_Network;
/*     */ import mcheli.wrapper.W_PacketBase;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ public class MCH_ClientSeatTickHandler
/*     */   extends MCH_ClientTickHandlerBase
/*     */ {
/*     */   protected boolean isRiding = false;
/*     */   protected boolean isBeforeRiding = false;
/*     */   public MCH_Key KeySwitchNextSeat;
/*     */   public MCH_Key KeySwitchPrevSeat;
/*     */   
/*     */   public MCH_ClientSeatTickHandler(Minecraft minecraft, MCH_Config config) {
/*  27 */     super(minecraft);
/*  28 */     updateKeybind(config);
/*     */   }
/*     */   public MCH_Key KeyParachuting; public MCH_Key KeyFreeLook; public MCH_Key KeyUnmountForce; public MCH_Key[] Keys;
/*     */   
/*     */   public void updateKeybind(MCH_Config config) {
/*  33 */     this.KeySwitchNextSeat = new MCH_Key(MCH_Config.KeyExtra.prmInt);
/*  34 */     this.KeySwitchPrevSeat = new MCH_Key(MCH_Config.KeyGUI.prmInt);
/*  35 */     this.KeyParachuting = new MCH_Key(MCH_Config.KeySwitchHovering.prmInt);
/*  36 */     this.KeyUnmountForce = new MCH_Key(42);
/*  37 */     this.KeyFreeLook = new MCH_Key(MCH_Config.KeyFreeLook.prmInt);
/*     */     
/*  39 */     this.Keys = new MCH_Key[] { this.KeySwitchNextSeat, this.KeySwitchPrevSeat, this.KeyParachuting, this.KeyUnmountForce, this.KeyFreeLook };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onTick(boolean inGUI) {
/*  49 */     for (MCH_Key k : this.Keys) k.update();
/*     */     
/*  51 */     this.isBeforeRiding = this.isRiding;
/*     */     
/*  53 */     EntityClientPlayerMP entityClientPlayerMP = this.mc.field_71439_g;
/*     */ 
/*     */     
/*  56 */     if (entityClientPlayerMP != null && ((EntityPlayer)entityClientPlayerMP).field_70154_o instanceof MCH_EntitySeat) {
/*     */       
/*  58 */       MCH_EntitySeat seat = (MCH_EntitySeat)((EntityPlayer)entityClientPlayerMP).field_70154_o;
/*  59 */       if (seat.getParent() == null || seat.getParent().getAcInfo() == null)
/*  60 */         return;  MCH_EntityAircraft ac = seat.getParent();
/*     */       
/*  62 */       if (!inGUI)
/*     */       {
/*  64 */         if (!ac.isDestroyed())
/*     */         {
/*  66 */           playerControl((EntityPlayer)entityClientPlayerMP, seat, ac);
/*     */         }
/*     */       }
/*     */ 
/*     */       
/*  71 */       this.isRiding = true;
/*     */     }
/*     */     else {
/*     */       
/*  75 */       this.isRiding = false;
/*     */     } 
/*     */ 
/*     */     
/*  79 */     if (this.isBeforeRiding != this.isRiding)
/*     */     {
/*  81 */       if (!this.isRiding)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/*  86 */         MCH_Lib.setRenderViewEntity((EntityLivingBase)entityClientPlayerMP);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void playerControlInGUI(EntityPlayer player, MCH_EntitySeat seat, MCH_EntityAircraft ac) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void playerControl(EntityPlayer player, MCH_EntitySeat seat, MCH_EntityAircraft ac) {
/* 101 */     MCH_PacketSeatPlayerControl pc = new MCH_PacketSeatPlayerControl();
/* 102 */     boolean send = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     if (this.KeyFreeLook.isKeyDown())
/*     */     {
/* 114 */       if (ac.canSwitchGunnerFreeLook(player))
/*     */       {
/*     */         
/* 117 */         ac.switchGunnerFreeLookMode();
/*     */       }
/*     */     }
/*     */     
/* 121 */     if (this.KeyParachuting.isKeyDown())
/*     */     {
/* 123 */       if (ac.canParachuting((Entity)player)) {
/*     */ 
/*     */         
/* 126 */         pc.parachuting = true;
/* 127 */         send = true;
/*     */       }
/* 129 */       else if (ac.canRepelling((Entity)player)) {
/*     */ 
/*     */         
/* 132 */         pc.parachuting = true;
/* 133 */         send = true;
/*     */       }
/*     */       else {
/*     */         
/* 137 */         playSoundNG();
/*     */       } 
/*     */     }
/*     */     
/* 141 */     if (send)
/*     */     {
/* 143 */       W_Network.sendToServer((W_PacketBase)pc);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_ClientSeatTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */