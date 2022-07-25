/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent;
/*    */ import mcheli.wrapper.ITickHandler;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class W_TickHandler
/*    */   implements ITickHandler
/*    */ {
/*    */   protected Minecraft mc;
/*    */   
/*    */   public W_TickHandler(Minecraft m) {
/* 27 */     this.mc = m;
/*    */   }
/*    */   
/*    */   public void onPlayerTickPre(EntityPlayer player) {}
/*    */   
/*    */   public void onPlayerTickPost(EntityPlayer player) {}
/*    */   
/*    */   public void onRenderTickPre(float partialTicks) {}
/*    */   
/*    */   public void onRenderTickPost(float partialTicks) {}
/*    */   
/*    */   public void onTickPre() {}
/*    */   
/*    */   public void onTickPost() {}
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
/* 44 */     if (event.phase == TickEvent.Phase.START)
/*    */     {
/* 46 */       onPlayerTickPre(event.player);
/*    */     }
/* 48 */     if (event.phase == TickEvent.Phase.END)
/*    */     {
/* 50 */       onPlayerTickPost(event.player);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onClientTickEvent(TickEvent.ClientTickEvent event) {
/* 57 */     if (event.phase == TickEvent.Phase.START)
/*    */     {
/* 59 */       onTickPre();
/*    */     }
/* 61 */     if (event.phase == TickEvent.Phase.END)
/*    */     {
/* 63 */       onTickPost();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRenderTickEvent(TickEvent.RenderTickEvent event) {
/* 71 */     if (event.phase == TickEvent.Phase.START)
/*    */     {
/* 73 */       onRenderTickPre(event.renderTickTime);
/*    */     }
/* 75 */     if (event.phase == TickEvent.Phase.END)
/*    */     {
/* 77 */       onRenderTickPost(event.renderTickTime);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_TickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */