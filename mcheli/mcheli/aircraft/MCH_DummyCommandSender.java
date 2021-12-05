/*    */ package mcheli.mcheli.aircraft;
/*    */ 
/*    */ import net.minecraft.command.ICommandManager;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.util.ChunkCoordinates;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MCH_DummyCommandSender
/*    */   implements ICommandSender {
/* 12 */   public static mcheli.aircraft.MCH_DummyCommandSender instance = new mcheli.aircraft.MCH_DummyCommandSender();
/*    */ 
/*    */   
/*    */   public static void execCommand(String s) {
/* 16 */     ICommandManager icommandmanager = MinecraftServer.func_71276_C().func_71187_D();
/* 17 */     icommandmanager.func_71556_a(instance, s);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_70005_c_() {
/* 23 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IChatComponent func_145748_c_() {
/* 29 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_145747_a(IChatComponent p_145747_1_) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ChunkCoordinates func_82114_b() {
/* 46 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public World func_130014_f_() {
/* 52 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_DummyCommandSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */