/*     */ package mcheli.mcheli.wrapper;
/*     */ 
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.event.CommandEvent;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingAttackEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityInteractEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class W_EventHook
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onEvent_entitySpawn(EntityJoinWorldEvent event) {
/*  63 */     entitySpawn(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void entitySpawn(EntityJoinWorldEvent event) {}
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEvent_livingHurtEvent(LivingHurtEvent event) {
/*  71 */     livingHurtEvent(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void livingHurtEvent(LivingHurtEvent event) {}
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEvent_livingAttackEvent(LivingAttackEvent event) {
/*  79 */     livingAttackEvent(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void livingAttackEvent(LivingAttackEvent event) {}
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEvent_entityInteractEvent(EntityInteractEvent event) {
/*  87 */     entityInteractEvent(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void entityInteractEvent(EntityInteractEvent event) {}
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEvent_entityCanUpdate(EntityEvent.CanUpdate event) {
/*  95 */     entityCanUpdate(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void entityCanUpdate(EntityEvent.CanUpdate event) {}
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEvent_commandEvent(CommandEvent event) {
/* 103 */     commandEvent(event);
/*     */   }
/*     */   
/*     */   public void commandEvent(CommandEvent event) {}
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_EventHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */