/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent;
/*    */ import cpw.mods.fml.common.network.internal.FMLProxyPacket;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Queue;
/*    */ import mcheli.wrapper.W_Reflection;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_ServerTickHandler
/*    */ {
/* 18 */   HashMap<String, Integer> rcvMap = new HashMap<String, Integer>();
/* 19 */   HashMap<String, Integer> sndMap = new HashMap<String, Integer>();
/* 20 */   int sndPacketNum = 0;
/* 21 */   int rcvPacketNum = 0;
/*    */   
/*    */   int tick;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onServerTickEvent(TickEvent.ServerTickEvent event) {
/* 27 */     if (event.phase == TickEvent.Phase.START);
/*    */ 
/*    */ 
/*    */     
/* 31 */     if (event.phase == TickEvent.Phase.END);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void onServerTickPre() {
/* 39 */     this.tick++;
/* 40 */     List<NetworkManager> list = W_Reflection.getNetworkManagers();
/* 41 */     if (list != null)
/*    */     {
/* 43 */       for (int i = 0; i < list.size(); i++) {
/*    */         
/* 45 */         Queue queue = W_Reflection.getReceivedPacketsQueue(list.get(i));
/* 46 */         if (queue != null) {
/*    */           
/* 48 */           putMap(this.rcvMap, queue.iterator());
/* 49 */           this.rcvPacketNum += queue.size();
/*    */         } 
/* 51 */         queue = W_Reflection.getSendPacketsQueue(list.get(i));
/* 52 */         if (queue != null) {
/*    */           
/* 54 */           putMap(this.sndMap, queue.iterator());
/* 55 */           this.sndPacketNum += queue.size();
/*    */         } 
/*    */       } 
/*    */     }
/* 59 */     if (this.tick >= 20) {
/*    */ 
/*    */ 
/*    */       
/* 63 */       this.tick = 0;
/* 64 */       this.rcvPacketNum = this.sndPacketNum = 0;
/* 65 */       this.rcvMap.clear();
/* 66 */       this.sndMap.clear();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void putMap(HashMap<String, Integer> map, Iterator iterator) {
/* 72 */     while (iterator.hasNext()) {
/*    */       
/* 74 */       Object o = iterator.next();
/* 75 */       String key = o.getClass().getName().toString();
/* 76 */       if (key.startsWith("net.minecraft.")) {
/*    */         
/* 78 */         key = "Minecraft";
/*    */       }
/* 80 */       else if (o instanceof FMLProxyPacket) {
/*    */         
/* 82 */         FMLProxyPacket p = (FMLProxyPacket)o;
/* 83 */         key = p.channel();
/*    */       }
/*    */       else {
/*    */         
/* 87 */         key = "Unknown!";
/*    */       } 
/*    */       
/* 90 */       if (map.containsKey(key)) {
/*    */         
/* 92 */         map.put(key, Integer.valueOf(1 + ((Integer)map.get(key)).intValue()));
/*    */         
/*    */         continue;
/*    */       } 
/* 96 */       map.put(key, Integer.valueOf(1));
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onServerTickPost() {}
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ServerTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */