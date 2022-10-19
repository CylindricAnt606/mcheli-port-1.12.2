/*     */ package mcheli.mcheli.multiplay;
/*     */ 
/*     */ import mcheli.multiplay.MCH_GuiScoreboard_Base;
/*     */ import mcheli.multiplay.MCH_IGuiScoreboard;
/*     */ import mcheli.multiplay.MCH_PacketIndMultiplayCommand;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_ScaledResolution;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiTextField;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MCH_GuiScoreboard_CreateTeam
/*     */   extends MCH_GuiScoreboard_Base
/*     */ {
/*     */   private GuiButton buttonCreateTeamOK;
/*     */   private GuiButton buttonCreateTeamFF;
/*     */   private GuiTextField editCreateTeamName;
/*     */   private static boolean friendlyFire = true;
/*  21 */   private int lastTeamColor = 0;
/*     */   
/*  23 */   private static final String[] colorNames = new String[] { "RESET", "BLACK", "DARK_BLUE", "DARK_GREEN", "DARK_AQUA", "DARK_RED", "DARK_PURPLE", "GOLD", "GRAY", "DARK_GRAY", "BLUE", "GREEN", "AQUA", "RED", "LIGHT_PURPLE", "YELLOW" };
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
/*     */   public MCH_GuiScoreboard_CreateTeam(MCH_IGuiScoreboard switcher, EntityPlayer player) {
/*  45 */     super(switcher, player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  50 */     super.func_73866_w_();
/*  51 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
/*  52 */     int factor = (w_ScaledResolution.func_78325_e() > 0) ? w_ScaledResolution.func_78325_e() : 1;
/*     */     
/*  54 */     this.field_147003_i = 0;
/*  55 */     this.field_147009_r = 0;
/*  56 */     int x = this.field_146297_k.field_71443_c / 2 / factor;
/*  57 */     int y = this.field_146297_k.field_71440_d / 2 / factor;
/*     */     
/*  59 */     GuiButton buttonCTNextC = new GuiButton(576, x + 40, y - 20, 40, 20, ">");
/*  60 */     GuiButton buttonCTPrevC = new GuiButton(577, x - 80, y - 20, 40, 20, "<");
/*  61 */     this.buttonCreateTeamFF = new GuiButton(560, x - 80, y + 20, 160, 20, "");
/*  62 */     this.buttonCreateTeamOK = new GuiButton(528, x - 80, y + 60, 80, 20, "OK");
/*  63 */     GuiButton buttonCTCancel = new GuiButton(544, x + 0, y + 60, 80, 20, "Cancel");
/*  64 */     this.editCreateTeamName = new GuiTextField(this.field_146289_q, x - 80, y - 55, 160, 20);
/*  65 */     this.editCreateTeamName.func_146180_a("");
/*  66 */     this.editCreateTeamName.func_146193_g(-1);
/*  67 */     this.editCreateTeamName.func_146203_f(16);
/*  68 */     this.editCreateTeamName.func_146195_b(true);
/*     */     
/*  70 */     this.listGui.add(buttonCTNextC);
/*  71 */     this.listGui.add(buttonCTPrevC);
/*  72 */     this.listGui.add(this.buttonCreateTeamFF);
/*  73 */     this.listGui.add(this.buttonCreateTeamOK);
/*  74 */     this.listGui.add(buttonCTCancel);
/*  75 */     this.listGui.add(this.editCreateTeamName);
/*     */   }
/*     */   
/*     */   public void func_73876_c() {
/*  79 */     String teamName = this.editCreateTeamName.func_146179_b();
/*  80 */     this.buttonCreateTeamOK.field_146124_l = (teamName.length() > 0 && teamName.length() <= 16);
/*     */     
/*  82 */     this.editCreateTeamName.func_146178_a();
/*     */     
/*  84 */     this.buttonCreateTeamFF.field_146126_j = "Friendly Fire : " + (friendlyFire ? "ON" : "OFF");
/*     */   }
/*     */ 
/*     */   
/*     */   public void acviveScreen() {
/*  89 */     this.editCreateTeamName.func_146180_a("");
/*  90 */     this.editCreateTeamName.func_146195_b(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_73869_a(char c, int code) {
/*  95 */     if (code == 1) {
/*     */       
/*  97 */       switchScreen(MCH_GuiScoreboard_Base.SCREEN_ID.MAIN);
/*     */     }
/*     */     else {
/*     */       
/* 101 */       this.editCreateTeamName.func_146201_a(c, code);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_73864_a(int p_73864_1_, int p_73864_2_, int p_73864_3_) {
/* 107 */     this.editCreateTeamName.func_146192_a(p_73864_1_, p_73864_2_, p_73864_3_);
/* 108 */     super.func_73864_a(p_73864_1_, p_73864_2_, p_73864_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton btn) {
/* 113 */     if (btn != null && btn.field_146124_l) {
/*     */       String teamName;
/* 115 */       switch (btn.field_146127_k) {
/*     */         
/*     */         case 528:
/* 118 */           teamName = this.editCreateTeamName.func_146179_b();
/* 119 */           if (teamName.length() > 0 && teamName.length() <= 16) {
/*     */             
/* 121 */             MCH_PacketIndMultiplayCommand.send(768, "scoreboard teams add " + teamName);
/* 122 */             MCH_PacketIndMultiplayCommand.send(768, "scoreboard teams option " + teamName + " color " + colorNames[this.lastTeamColor]);
/*     */             
/* 124 */             MCH_PacketIndMultiplayCommand.send(768, "scoreboard teams option " + teamName + " friendlyfire " + friendlyFire);
/*     */           } 
/*     */           
/* 127 */           switchScreen(MCH_GuiScoreboard_Base.SCREEN_ID.MAIN);
/*     */           break;
/*     */         case 544:
/* 130 */           switchScreen(MCH_GuiScoreboard_Base.SCREEN_ID.MAIN);
/*     */           break;
/*     */         case 560:
/* 133 */           friendlyFire = !friendlyFire;
/*     */           break;
/*     */         case 576:
/* 136 */           this.lastTeamColor++;
/* 137 */           if (this.lastTeamColor >= colorNames.length)
/*     */           {
/* 139 */             this.lastTeamColor = 0;
/*     */           }
/*     */           break;
/*     */         case 577:
/* 143 */           this.lastTeamColor--;
/* 144 */           if (this.lastTeamColor < 0)
/*     */           {
/* 146 */             this.lastTeamColor = colorNames.length - 1;
/*     */           }
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3) {
/* 156 */     drawList(this.field_146297_k, this.field_146289_q, true);
/*     */     
/* 158 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
/* 159 */     int factor = (w_ScaledResolution.func_78325_e() > 0) ? w_ScaledResolution.func_78325_e() : 1;
/* 160 */     W_McClient.MOD_bindTexture("textures/gui/mp_new_team.png");
/* 161 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 162 */     int x = (this.field_146297_k.field_71443_c / factor - 222) / 2;
/* 163 */     int y = (this.field_146297_k.field_71440_d / factor - 200) / 2;
/* 164 */     func_73729_b(x, y, 0, 0, 222, 200);
/*     */     
/* 166 */     x = this.field_146297_k.field_71443_c / 2 / factor;
/* 167 */     y = this.field_146297_k.field_71440_d / 2 / factor;
/* 168 */     drawCenteredString("Create team", x, y - 85, -1);
/* 169 */     drawCenteredString("Team name", x, y - 70, -1);
/*     */     
/* 171 */     EnumChatFormatting ecf = EnumChatFormatting.func_96300_b(colorNames[this.lastTeamColor]);
/* 172 */     drawCenteredString(ecf + "Team Color" + ecf, x, y - 13, -1);
/*     */     
/* 174 */     this.editCreateTeamName.func_146194_f();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_GuiScoreboard_CreateTeam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */