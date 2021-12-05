/*     */ package mcheli.mcheli.multiplay;
/*     */ 
/*     */ import java.util.List;
/*     */ import mcheli.MCH_ServerSettings;
/*     */ import mcheli.multiplay.MCH_GuiScoreboard_Base;
/*     */ import mcheli.multiplay.MCH_IGuiScoreboard;
/*     */ import mcheli.multiplay.MCH_PacketIndMultiplayCommand;
/*     */ import mcheli.wrapper.W_GuiButton;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ public class MCH_GuiScoreboard_Main
/*     */   extends MCH_GuiScoreboard_Base {
/*     */   private W_GuiButton buttonSwitchPVP;
/*     */   
/*     */   public MCH_GuiScoreboard_Main(MCH_IGuiScoreboard switcher, EntityPlayer player) {
/*  17 */     super(switcher, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  22 */     super.func_73866_w_();
/*     */     
/*  24 */     if (this.buttonSwitchPVP != null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  29 */     this.field_147003_i = 0;
/*  30 */     this.field_147009_r = 0;
/*  31 */     int WIDTH = getScoreboradWidth(this.field_146297_k) * 3 / 4;
/*  32 */     if (WIDTH < 80) WIDTH = 80; 
/*  33 */     int LEFT = getScoreBoardLeft(this.field_146297_k, getTeamNum() + 1, 0) / 4;
/*     */     
/*  35 */     this.buttonSwitchPVP = new W_GuiButton(1024, LEFT, 80, WIDTH, 20, "");
/*  36 */     this.listGui.add(this.buttonSwitchPVP);
/*     */     
/*  38 */     W_GuiButton btn = new W_GuiButton(256, LEFT, 100, WIDTH, 20, "Team shuffle");
/*  39 */     btn.addHoverString("Shuffle all players.");
/*  40 */     this.listGui.add(btn);
/*     */     
/*  42 */     this.listGui.add(new W_GuiButton(512, LEFT, 120, WIDTH, 20, "New team"));
/*     */     
/*  44 */     btn = new W_GuiButton(768, LEFT, 140, WIDTH, 20, "Jump spawn pos");
/*  45 */     btn.addHoverString("Teleport all players -> spawn point.");
/*  46 */     this.listGui.add(btn);
/*     */     
/*  48 */     btn = new W_GuiButton(1280, LEFT, 160, WIDTH, 20, "Destroy All");
/*  49 */     btn.addHoverString("Destroy all aircraft and vehicle.");
/*  50 */     this.listGui.add(btn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_73869_a(char c, int code) {
/*  55 */     if (code == 1)
/*     */     {
/*  57 */       this.field_146297_k.field_71439_g.func_71053_j();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreenButtons(List list) {
/*  63 */     for (Object o : list) {
/*     */       
/*  65 */       GuiButton button = (GuiButton)o;
/*  66 */       if (button.field_146127_k == 1024)
/*     */       {
/*  68 */         button.field_146126_j = "PVP : " + (MCH_ServerSettings.enablePVP ? "ON" : "OFF");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton btn) {
/*  75 */     if (btn != null && btn.field_146124_l)
/*     */     {
/*  77 */       switch (btn.field_146127_k) {
/*     */         
/*     */         case 256:
/*  80 */           MCH_PacketIndMultiplayCommand.send(256, "");
/*     */           break;
/*     */         case 768:
/*  83 */           MCH_PacketIndMultiplayCommand.send(512, "");
/*     */           break;
/*     */         case 512:
/*  86 */           switchScreen(MCH_GuiScoreboard_Base.SCREEN_ID.CREATE_TEAM);
/*     */           break;
/*     */         case 1024:
/*  89 */           MCH_PacketIndMultiplayCommand.send(1024, "");
/*     */           break;
/*     */         case 1280:
/*  92 */           MCH_PacketIndMultiplayCommand.send(1280, "");
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawGuiContainerForegroundLayerScreen(int x, int y) {
/* 100 */     super.drawGuiContainerForegroundLayerScreen(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3) {
/* 106 */     drawList(this.field_146297_k, this.field_146289_q, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_GuiScoreboard_Main.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */