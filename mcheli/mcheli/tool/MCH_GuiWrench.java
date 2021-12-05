/*    */ package mcheli.mcheli.tool;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.aircraft.MCH_EntityAircraft;
/*    */ import mcheli.gui.MCH_Gui;
/*    */ import mcheli.tool.MCH_ItemWrench;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_GuiWrench
/*    */   extends MCH_Gui
/*    */ {
/*    */   public MCH_GuiWrench(Minecraft minecraft) {
/* 21 */     super(minecraft);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_73866_w_() {
/* 27 */     super.func_73866_w_();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_73868_f() {
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDrawGui(EntityPlayer player) {
/* 38 */     return (player != null && player.field_70170_p != null && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof MCH_ItemWrench);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
/* 46 */     if (isThirdPersonView)
/*    */       return; 
/* 48 */     this; GL11.glLineWidth(scaleFactor);
/*    */     
/* 50 */     if (!isDrawGui(player))
/*    */       return; 
/* 52 */     GL11.glDisable(3042);
/*    */     
/* 54 */     MCH_EntityAircraft ac = ((MCH_ItemWrench)player.func_71045_bC().func_77973_b()).getMouseOverAircraft(player);
/* 55 */     if (ac != null && ac.getMaxHP() > 0) {
/*    */       
/* 57 */       int color = (ac.getHP() / ac.getMaxHP() > 0.3D) ? -14101432 : -2161656;
/* 58 */       drawHP(color, -15433180, ac.getHP(), ac.getMaxHP());
/*    */     } 
/*    */   }
/*    */   
/*    */   void drawHP(int color, int colorBG, int hp, int hpmax) {
/* 63 */     int posX = this.centerX;
/* 64 */     int posY = this.centerY + 20;
/*    */     
/* 66 */     int WID = 20;
/* 67 */     int INV = 10;
/*    */     
/* 69 */     func_73734_a(posX - 20, posY + 20 + 1, posX - 20 + 40, posY + 20 + 1 + 1 + 3 + 1, colorBG);
/*    */ 
/*    */     
/* 72 */     if (hp > hpmax)
/*    */     {
/* 74 */       hp = hpmax;
/*    */     }
/*    */     
/* 77 */     float hpp = hp / hpmax;
/* 78 */     func_73734_a(posX - 20 + 1, posY + 20 + 1 + 1, posX - 20 + 1 + (int)(38.0D * hpp), posY + 20 + 1 + 1 + 3, color);
/*    */ 
/*    */     
/* 81 */     int hppn = (int)(hpp * 100.0F);
/* 82 */     if (hp < hpmax && hppn >= 100)
/*    */     {
/* 84 */       hppn = 99;
/*    */     }
/* 86 */     drawCenteredString(String.format("%d %%", new Object[] { Integer.valueOf(hppn) }), posX, posY + 30, color);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tool\MCH_GuiWrench.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */