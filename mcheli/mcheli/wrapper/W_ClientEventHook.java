/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.client.event.MouseEvent;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_ClientEventHook
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onEvent_MouseEvent(MouseEvent event) {
/* 20 */     mouseEvent(event);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseEvent(MouseEvent event) {}
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onEvent_renderLivingEventSpecialsPre(RenderLivingEvent.Specials.Pre event) {
/* 28 */     renderLivingEventSpecialsPre(event);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderLivingEventSpecialsPre(RenderLivingEvent.Specials.Pre event) {}
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onEvent_renderLivingEventSpecialsPost(RenderLivingEvent.Specials.Post event) {
/* 36 */     renderLivingEventSpecialsPost(event);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderLivingEventSpecialsPost(RenderLivingEvent.Specials.Post event) {}
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onEvent_renderLivingEventPre(RenderLivingEvent.Pre event) {
/* 45 */     renderLivingEventPre(event);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderLivingEventPre(RenderLivingEvent.Pre event) {}
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onEvent_renderLivingEventPost(RenderLivingEvent.Post event) {
/* 53 */     renderLivingEventPost(event);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderLivingEventPost(RenderLivingEvent.Post event) {}
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onEvent_renderPlayerPre(RenderPlayerEvent.Pre event) {
/* 62 */     renderPlayerPre(event);
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderPlayerPre(RenderPlayerEvent.Pre event) {}
/*    */   
/*    */   @SubscribeEvent
/*    */   public void Event_renderPlayerPost(RenderPlayerEvent.Post event) {
/* 70 */     renderPlayerPost(event);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderPlayerPost(RenderPlayerEvent.Post event) {}
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onEvent_WorldEventUnload(WorldEvent.Unload event) {
/* 80 */     worldEventUnload(event);
/*    */   }
/*    */ 
/*    */   
/*    */   public void worldEventUnload(WorldEvent.Unload event) {}
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onEvent_EntityJoinWorldEvent(EntityJoinWorldEvent event) {
/* 88 */     entityJoinWorldEvent(event);
/*    */   }
/*    */   
/*    */   public void entityJoinWorldEvent(EntityJoinWorldEvent event) {}
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_ClientEventHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */