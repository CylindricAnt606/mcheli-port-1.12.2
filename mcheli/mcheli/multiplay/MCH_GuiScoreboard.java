/*     */ package mcheli.mcheli.multiplay;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import mcheli.multiplay.MCH_ContainerScoreboard;
/*     */ import mcheli.multiplay.MCH_GuiScoreboard_Base;
/*     */ import mcheli.multiplay.MCH_GuiScoreboard_CreateTeam;
/*     */ import mcheli.multiplay.MCH_GuiScoreboard_Main;
/*     */ import mcheli.multiplay.MCH_IGuiScoreboard;
/*     */ import mcheli.wrapper.W_GuiButton;
/*     */ import mcheli.wrapper.W_GuiContainer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiTextField;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MCH_GuiScoreboard
/*     */   extends W_GuiContainer implements MCH_IGuiScoreboard {
/*     */   public final EntityPlayer thePlayer;
/*  25 */   private int lastTeamNum = 0;
/*     */   private MCH_GuiScoreboard_Base.SCREEN_ID screenID;
/*     */   private Map<MCH_GuiScoreboard_Base.SCREEN_ID, MCH_GuiScoreboard_Base> listScreen;
/*     */   
/*     */   public MCH_GuiScoreboard(EntityPlayer player) {
/*  30 */     super((Container)new MCH_ContainerScoreboard(player));
/*  31 */     this.thePlayer = player;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  36 */     Keyboard.enableRepeatEvents(true);
/*  37 */     super.func_73866_w_();
/*     */     
/*  39 */     this.field_146292_n.clear();
/*  40 */     this.field_146293_o.clear();
/*     */     
/*  42 */     this.field_147003_i = 0;
/*  43 */     this.field_147009_r = 0;
/*     */     
/*  45 */     this.listScreen = new HashMap<MCH_GuiScoreboard_Base.SCREEN_ID, MCH_GuiScoreboard_Base>();
/*  46 */     this.listScreen.put(MCH_GuiScoreboard_Base.SCREEN_ID.MAIN, new MCH_GuiScoreboard_Main(this, this.thePlayer));
/*  47 */     this.listScreen.put(MCH_GuiScoreboard_Base.SCREEN_ID.CREATE_TEAM, new MCH_GuiScoreboard_CreateTeam(this, this.thePlayer));
/*  48 */     for (MCH_GuiScoreboard_Base s : this.listScreen.values())
/*     */     {
/*  50 */       s.initGui(this.field_146292_n, (GuiScreen)this);
/*     */     }
/*     */     
/*  53 */     this.lastTeamNum = this.field_146297_k.field_71441_e.func_96441_U().func_96525_g().size();
/*     */     
/*  55 */     switchScreen(MCH_GuiScoreboard_Base.SCREEN_ID.MAIN);
/*     */   }
/*     */   
/*     */   public void func_73876_c() {
/*  59 */     super.func_73876_c();
/*     */     
/*  61 */     int nowTeamNum = this.field_146297_k.field_71441_e.func_96441_U().func_96525_g().size();
/*  62 */     if (this.lastTeamNum != nowTeamNum) {
/*     */       
/*  64 */       this.lastTeamNum = nowTeamNum;
/*  65 */       func_73866_w_();
/*     */     } 
/*     */     
/*  68 */     for (MCH_GuiScoreboard_Base s : this.listScreen.values()) {
/*     */ 
/*     */       
/*     */       try {
/*  72 */         s.updateScreenButtons(this.field_146292_n);
/*  73 */         s.func_73876_c();
/*     */       }
/*  75 */       catch (Exception e) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void switchScreen(MCH_GuiScoreboard_Base.SCREEN_ID id) {
/*  83 */     for (MCH_GuiScoreboard_Base b : this.listScreen.values())
/*     */     {
/*  85 */       b.leaveScreen();
/*     */     }
/*     */     
/*  88 */     this.screenID = id;
/*     */     
/*  90 */     getCurrentScreen().onSwitchScreen();
/*     */   }
/*     */   
/*     */   private MCH_GuiScoreboard_Base getCurrentScreen() {
/*  94 */     return this.listScreen.get(this.screenID);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setVisible(Object g, boolean v) {
/*  99 */     if (g instanceof GuiButton) ((GuiButton)g).field_146125_m = v; 
/* 100 */     if (g instanceof GuiTextField) ((GuiTextField)g).func_146189_e(v);
/*     */   
/*     */   }
/*     */   
/*     */   protected void func_73869_a(char c, int code) {
/* 105 */     getCurrentScreen().keyTypedScreen(c, code);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
/*     */     try {
/* 112 */       for (MCH_GuiScoreboard_Base s : this.listScreen.values())
/*     */       {
/* 114 */         s.mouseClickedScreen(p_73864_1_, p_73864_2_, p_73864_3_);
/*     */       }
/* 116 */       super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
/*     */     }
/* 118 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton btn) {
/* 125 */     if (btn != null && btn.field_146124_l)
/*     */     {
/* 127 */       getCurrentScreen().actionPerformedScreen(btn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146276_q_() {}
/*     */ 
/*     */   
/*     */   public void func_146278_c(int p_146278_1_) {
/* 136 */     GL11.glDisable(2896);
/* 137 */     GL11.glDisable(2912);
/* 138 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146979_b(int x, int y) {
/* 144 */     getCurrentScreen().drawGuiContainerForegroundLayerScreen(x, y);
/*     */     
/* 146 */     for (Object o : this.field_146292_n) {
/*     */       
/* 148 */       if (o instanceof W_GuiButton) {
/*     */         
/* 150 */         W_GuiButton btn = (W_GuiButton)o;
/* 151 */         if (btn.isOnMouseOver() && btn.hoverStringList != null) {
/*     */           
/* 153 */           drawHoveringText(btn.hoverStringList, x, y, this.field_146289_q);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawList(Minecraft mc, FontRenderer fontRendererObj, boolean mng) {
/* 162 */     MCH_GuiScoreboard_Base.drawList(mc, fontRendererObj, mng);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3) {
/* 168 */     getCurrentScreen().func_146976_a(par1, par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146280_a(Minecraft p_146280_1_, int p_146280_2_, int p_146280_3_) {
/* 173 */     super.func_146280_a(p_146280_1_, p_146280_2_, p_146280_3_);
/* 174 */     for (MCH_GuiScoreboard_Base s : this.listScreen.values())
/*     */     {
/* 176 */       s.func_146280_a(p_146280_1_, p_146280_2_, p_146280_3_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_GuiScoreboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */