/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.aircraft.MCH_AircraftCommonGui;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_GuiCommon
/*    */   extends MCH_AircraftCommonGui
/*    */ {
/* 14 */   public int hitCount = 0;
/*    */ 
/*    */   
/*    */   public MCH_GuiCommon(Minecraft minecraft) {
/* 18 */     super(minecraft);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDrawGui(EntityPlayer player) {
/* 23 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
/* 28 */     this; GL11.glLineWidth(scaleFactor);
/*    */ 
/*    */     
/* 31 */     drawHitBullet(this.hitCount, 15, -805306369);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTick() {
/* 36 */     super.onTick();
/* 37 */     if (this.hitCount > 0) this.hitCount--;
/*    */   
/*    */   }
/*    */   
/*    */   public void hitBullet() {
/* 42 */     this.hitCount = 15;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_GuiCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */