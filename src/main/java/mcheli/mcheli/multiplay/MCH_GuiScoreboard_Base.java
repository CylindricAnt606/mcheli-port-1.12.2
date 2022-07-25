/*     */ package mcheli.mcheli.multiplay;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import mcheli.multiplay.MCH_ContainerScoreboard;
/*     */ import mcheli.multiplay.MCH_IGuiScoreboard;
/*     */ import mcheli.wrapper.W_GuiContainer;
/*     */ import mcheli.wrapper.W_ScaledResolution;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiPlayerInfo;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiTextField;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.scoreboard.Score;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MCH_GuiScoreboard_Base
/*     */   extends W_GuiContainer
/*     */ {
/*     */   public List<Gui> listGui;
/*     */   public static final int BUTTON_ID_SHUFFLE = 256;
/*     */   public static final int BUTTON_ID_CREATE_TEAM = 512;
/*     */   public static final int BUTTON_ID_CREATE_TEAM_OK = 528;
/*     */   public static final int BUTTON_ID_CREATE_TEAM_CANCEL = 544;
/*     */   public static final int BUTTON_ID_CREATE_TEAM_FF = 560;
/*     */   public static final int BUTTON_ID_CREATE_TEAM_NEXT_C = 576;
/*     */   public static final int BUTTON_ID_CREATE_TEAM_PREV_C = 577;
/*     */   public static final int BUTTON_ID_JUMP_SPAWN_POINT = 768;
/*     */   public static final int BUTTON_ID_SWITCH_PVP = 1024;
/*     */   public static final int BUTTON_ID_DESTORY_ALL = 1280;
/*     */   private MCH_IGuiScoreboard screen_switcher;
/*     */   
/*     */   public MCH_GuiScoreboard_Base(MCH_IGuiScoreboard switcher, EntityPlayer player) {
/*  49 */     super((Container)new MCH_ContainerScoreboard(player));
/*  50 */     this.screen_switcher = switcher;
/*  51 */     this.field_146297_k = Minecraft.func_71410_x();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {}
/*     */ 
/*     */   
/*     */   public void initGui(List<Gui> buttonList, GuiScreen parents) {
/*  60 */     this.listGui = new ArrayList<Gui>();
/*     */     
/*  62 */     this.field_146297_k = Minecraft.func_71410_x();
/*  63 */     this.field_146289_q = this.field_146297_k.field_71466_p;
/*  64 */     this.field_146294_l = parents.field_146294_l;
/*  65 */     this.field_146295_m = parents.field_146295_m;
/*  66 */     func_73866_w_();
/*     */     
/*  68 */     for (Gui b : this.listGui) {
/*     */       
/*  70 */       if (b instanceof GuiButton) buttonList.add(b); 
/*     */     } 
/*  72 */     this.field_146292_n.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setVisible(Object g, boolean v) {
/*  77 */     if (g instanceof GuiButton) ((GuiButton)g).field_146125_m = v; 
/*  78 */     if (g instanceof GuiTextField) ((GuiTextField)g).func_146189_e(v);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreenButtons(List list) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {}
/*     */ 
/*     */   
/*     */   public int getTeamNum() {
/*  92 */     return this.field_146297_k.field_71441_e.func_96441_U().func_96525_g().size();
/*     */   }
/*     */   
/*     */   protected void acviveScreen() {}
/*     */   
/*     */   public void onSwitchScreen() {
/*  98 */     for (Gui b : this.listGui)
/*     */     {
/* 100 */       setVisible(b, true);
/*     */     }
/* 102 */     acviveScreen();
/*     */   }
/*     */   
/*     */   public void leaveScreen() {
/* 106 */     for (Gui b : this.listGui)
/*     */     {
/* 108 */       setVisible(b, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyTypedScreen(char c, int code) {
/* 114 */     func_73869_a(c, code);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClickedScreen(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
/*     */     try {
/* 120 */       func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
/*     */     }
/* 122 */     catch (Exception e) {
/*     */       
/* 124 */       if (p_73864_3_ == 0)
/*     */       {
/* 126 */         for (int l = 0; l < this.field_146292_n.size(); l++) {
/*     */           
/* 128 */           GuiButton guibutton = this.field_146292_n.get(l);
/*     */           
/* 130 */           if (guibutton.func_146116_c(this.field_146297_k, p_73864_1_, p_73864_2_)) {
/*     */             
/* 132 */             guibutton.func_146113_a(this.field_146297_k.func_147118_V());
/* 133 */             func_146284_a(guibutton);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawGuiContainerForegroundLayerScreen(int param1, int param2) {
/* 141 */     func_146979_b(param1, param2);
/*     */   }
/*     */   
/*     */   protected void actionPerformedScreen(GuiButton btn) {
/* 145 */     func_146284_a(btn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void switchScreen(SCREEN_ID id) {
/* 150 */     this.screen_switcher.switchScreen(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getScoreboradWidth(Minecraft mc) {
/* 155 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
/* 156 */     int ScaledWidth = w_ScaledResolution.func_78326_a() - 40;
/* 157 */     int width = ScaledWidth * 3 / 4 / (mc.field_71441_e.func_96441_U().func_96525_g().size() + 1);
/* 158 */     if (width > 150)
/*     */     {
/* 160 */       width = 150;
/*     */     }
/* 162 */     return width;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getScoreBoardLeft(Minecraft mc, int teamNum, int teamIndex) {
/* 167 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
/* 168 */     int ScaledWidth = w_ScaledResolution.func_78326_a();
/* 169 */     return (int)((ScaledWidth / 2) + (getScoreboradWidth(mc) + 10) * (-(teamNum) / 2.0D + teamIndex));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawList(Minecraft mc, FontRenderer fontRendererObj, boolean mng) {
/* 175 */     ArrayList<ScorePlayerTeam> teamList = new ArrayList<ScorePlayerTeam>();
/* 176 */     teamList.add(null);
/* 177 */     for (Object team : mc.field_71441_e.func_96441_U().func_96525_g())
/*     */     {
/* 179 */       teamList.add((ScorePlayerTeam)team);
/*     */     }
/* 181 */     Collections.sort(teamList, (Comparator<? super ScorePlayerTeam>)new Object());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     for (int i = 0; i < teamList.size(); i++) {
/*     */       
/* 190 */       if (mng) { drawPlayersList(mc, fontRendererObj, teamList.get(i), 1 + i, 1 + teamList.size()); }
/* 191 */       else { drawPlayersList(mc, fontRendererObj, teamList.get(i), i, teamList.size()); }
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void drawPlayersList(Minecraft mc, FontRenderer fontRendererObj, ScorePlayerTeam team, int teamIndex, int teamNum) {
/* 197 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
/* 198 */     int ScaledWidth = w_ScaledResolution.func_78326_a();
/* 199 */     int ScaledHeight = w_ScaledResolution.func_78328_b();
/* 200 */     ScoreObjective scoreobjective = mc.field_71441_e.func_96441_U().func_96539_a(0);
/*     */     
/* 202 */     NetHandlerPlayClient nethandlerplayclient = mc.field_71439_g.field_71174_a;
/* 203 */     List<GuiPlayerInfo> list = nethandlerplayclient.field_147303_b;
/* 204 */     int MaxPlayers = (list.size() / 5 + 1) * 5;
/* 205 */     MaxPlayers = (MaxPlayers < 10) ? 10 : MaxPlayers;
/* 206 */     if (MaxPlayers > nethandlerplayclient.field_147304_c)
/*     */     {
/* 208 */       MaxPlayers = nethandlerplayclient.field_147304_c;
/*     */     }
/*     */ 
/*     */     
/* 212 */     int width = getScoreboradWidth(mc);
/*     */     
/* 214 */     int listLeft = getScoreBoardLeft(mc, teamNum, teamIndex);
/* 215 */     int listTop = ScaledHeight / 2 - (MaxPlayers * 9 + 10) / 2;
/* 216 */     func_73734_a(listLeft - 1, listTop - 1 - 18, listLeft + width, listTop + 9 * MaxPlayers, -2147483648);
/*     */ 
/*     */     
/* 219 */     String teamName = ScorePlayerTeam.func_96667_a((Team)team, (team == null) ? "No team" : team.func_96661_b());
/* 220 */     int teamNameX = listLeft + width / 2 - fontRendererObj.func_78256_a(teamName) / 2;
/* 221 */     fontRendererObj.func_78261_a(teamName, teamNameX, listTop - 18, -1);
/*     */ 
/*     */     
/* 224 */     String ff_onoff = "FriendlyFire : " + ((team == null) ? "ON" : (team.func_96665_g() ? "ON" : "OFF"));
/* 225 */     int ff_onoffX = listLeft + width / 2 - fontRendererObj.func_78256_a(ff_onoff) / 2;
/* 226 */     fontRendererObj.func_78261_a(ff_onoff, ff_onoffX, listTop - 9, -1);
/*     */     
/* 228 */     int drawY = 0;
/* 229 */     for (int i = 0; i < MaxPlayers; i++) {
/*     */       
/* 231 */       int x = listLeft;
/* 232 */       int y = listTop + drawY * 9;
/* 233 */       int rectY = listTop + i * 9;
/* 234 */       func_73734_a(x, rectY, x + width - 1, rectY + 8, 553648127);
/* 235 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 236 */       GL11.glEnable(3008);
/*     */       
/* 238 */       if (i < list.size()) {
/*     */ 
/*     */         
/* 241 */         GuiPlayerInfo guiplayerinfo = list.get(i);
/*     */         
/* 243 */         String playerName = guiplayerinfo.field_78831_a;
/* 244 */         ScorePlayerTeam steam = mc.field_71441_e.func_96441_U().func_96509_i(playerName);
/* 245 */         if ((steam == null && team == null) || (steam != null && team != null && steam.func_142054_a((Team)team))) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 250 */           drawY++;
/*     */           
/* 252 */           fontRendererObj.func_78261_a(playerName, x, y, -1);
/*     */           
/* 254 */           if (scoreobjective != null) {
/*     */             
/* 256 */             int j4 = x + fontRendererObj.func_78256_a(playerName) + 5;
/* 257 */             int k4 = x + width - 12 - 5;
/*     */             
/* 259 */             if (k4 - j4 > 5) {
/*     */               
/* 261 */               Score score = scoreobjective.func_96682_a().func_96529_a(guiplayerinfo.field_78831_a, scoreobjective);
/* 262 */               String s1 = EnumChatFormatting.YELLOW + "" + score.func_96652_c();
/* 263 */               fontRendererObj.func_78261_a(s1, k4 - fontRendererObj.func_78256_a(s1), y, 16777215);
/*     */             } 
/*     */           } 
/*     */           
/* 267 */           drawResponseTime(x + width - 12, y, guiplayerinfo.field_78829_b);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   } public static void drawResponseTime(int x, int y, int responseTime) {
/*     */     byte b2;
/* 273 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 274 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(field_110324_m);
/*     */ 
/*     */     
/* 277 */     if (responseTime < 0) {
/*     */       
/* 279 */       b2 = 5;
/*     */     }
/* 281 */     else if (responseTime < 150) {
/*     */       
/* 283 */       b2 = 0;
/*     */     }
/* 285 */     else if (responseTime < 300) {
/*     */       
/* 287 */       b2 = 1;
/*     */     }
/* 289 */     else if (responseTime < 600) {
/*     */       
/* 291 */       b2 = 2;
/*     */     }
/* 293 */     else if (responseTime < 1000) {
/*     */       
/* 295 */       b2 = 3;
/*     */     }
/*     */     else {
/*     */       
/* 299 */       b2 = 4;
/*     */     } 
/*     */     
/* 302 */     static_drawTexturedModalRect(x, y, 0, 176 + b2 * 8, 10, 8, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void static_drawTexturedModalRect(int x, int y, int x2, int y2, int x3, int y3, double zLevel) {
/* 307 */     float f = 0.00390625F;
/* 308 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 309 */     tessellator.func_78382_b();
/* 310 */     tessellator.func_78374_a((x + 0), (y + y3), zLevel, ((x2 + 0) * 0.00390625F), ((y2 + y3) * 0.00390625F));
/* 311 */     tessellator.func_78374_a((x + x3), (y + y3), zLevel, ((x2 + x3) * 0.00390625F), ((y2 + y3) * 0.00390625F));
/* 312 */     tessellator.func_78374_a((x + x3), (y + 0), zLevel, ((x2 + x3) * 0.00390625F), ((y2 + 0) * 0.00390625F));
/* 313 */     tessellator.func_78374_a((x + 0), (y + 0), zLevel, ((x2 + 0) * 0.00390625F), ((y2 + 0) * 0.00390625F));
/* 314 */     tessellator.func_78381_a();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_GuiScoreboard_Base.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */