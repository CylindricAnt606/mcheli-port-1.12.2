/*     */ package mcheli.mcheli.command;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import mcheli.gui.MCH_Gui;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ChatLine;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_GuiTitle
/*     */   extends MCH_Gui
/*     */ {
/*  26 */   private final List chatLines = new ArrayList();
/*  27 */   private int prevPlayerTick = 0;
/*  28 */   private int restShowTick = 0;
/*  29 */   private int showTick = 0;
/*  30 */   private float colorAlpha = 0.0F;
/*  31 */   private int position = 0;
/*     */   
/*     */   private static Minecraft s_minecraft;
/*     */ 
/*     */   
/*     */   public MCH_GuiTitle(Minecraft minecraft) {
/*  37 */     super(minecraft);
/*  38 */     s_minecraft = minecraft;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  44 */     super.func_73866_w_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrawGui(EntityPlayer player) {
/*  55 */     if (this.restShowTick > 0 && this.chatLines.size() > 0)
/*     */     {
/*  57 */       if (player != null && player.field_70170_p != null) {
/*     */         
/*  59 */         if (this.prevPlayerTick != player.field_70173_aa) {
/*     */           
/*  61 */           this.showTick++;
/*  62 */           this.restShowTick--;
/*     */         } 
/*  64 */         this.prevPlayerTick = player.field_70173_aa;
/*     */       } 
/*     */     }
/*  67 */     return (this.restShowTick > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
/*  72 */     this; GL11.glLineWidth((scaleFactor * 2));
/*  73 */     GL11.glDisable(3042);
/*  74 */     this; if (scaleFactor <= 0) { this; scaleFactor = 1; }
/*     */     
/*  76 */     this.colorAlpha = 1.0F;
/*  77 */     if (this.restShowTick > 20 && this.showTick < 5)
/*     */     {
/*  79 */       this.colorAlpha = 0.2F * this.showTick;
/*     */     }
/*  81 */     if (this.showTick > 0 && this.restShowTick < 5)
/*     */     {
/*  83 */       this.colorAlpha = 0.2F * this.restShowTick;
/*     */     }
/*     */     
/*  86 */     drawChat();
/*     */   }
/*     */ 
/*     */   
/*     */   private String func_146235_b(String s) {
/*  91 */     return (Minecraft.func_71410_x()).field_71474_y.field_74344_o ? s : EnumChatFormatting.func_110646_a(s);
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_146233_a() {
/*  96 */     short short1 = 320;
/*  97 */     byte b0 = 40;
/*  98 */     return MathHelper.func_76141_d(this.field_146297_k.field_71474_y.field_96692_F * (short1 - b0) + b0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupTitle(IChatComponent chatComponent, int showTime, int pos) {
/* 103 */     int displayTime = 20;
/* 104 */     int line = 0;
/* 105 */     this.chatLines.clear();
/*     */     
/* 107 */     this.position = pos;
/* 108 */     this.showTick = 0;
/* 109 */     this.restShowTick = showTime;
/*     */     
/* 111 */     int k = MathHelper.func_76141_d(func_146233_a() / this.field_146297_k.field_71474_y.field_96691_E);
/* 112 */     int l = 0;
/* 113 */     ChatComponentText chatcomponenttext = new ChatComponentText("");
/* 114 */     ArrayList<ChatComponentText> arraylist = Lists.newArrayList();
/* 115 */     ArrayList<IChatComponent> arraylist1 = Lists.newArrayList((Iterable)chatComponent);
/*     */     
/* 117 */     for (int i1 = 0; i1 < arraylist1.size(); i1++) {
/*     */       
/* 119 */       IChatComponent ichatcomponent1 = arraylist1.get(i1);
/*     */       
/* 121 */       String[] splitLine = (ichatcomponent1.func_150261_e() + "").split("\n");
/* 122 */       int lineCnt = 0;
/* 123 */       for (String sLine : splitLine) {
/*     */         
/* 125 */         String s = func_146235_b(ichatcomponent1.func_150256_b().func_150218_j() + sLine);
/* 126 */         int j1 = this.field_146297_k.field_71466_p.func_78256_a(s);
/* 127 */         ChatComponentText chatcomponenttext1 = new ChatComponentText(s);
/* 128 */         chatcomponenttext1.func_150255_a(ichatcomponent1.func_150256_b().func_150232_l());
/* 129 */         boolean flag1 = false;
/*     */         
/* 131 */         if (l + j1 > k) {
/*     */           
/* 133 */           String s1 = this.field_146297_k.field_71466_p.func_78262_a(s, k - l, false);
/* 134 */           String s2 = (s1.length() < s.length()) ? s.substring(s1.length()) : null;
/*     */           
/* 136 */           if (s2 != null && s2.length() > 0) {
/*     */             
/* 138 */             int k1 = s1.lastIndexOf(" ");
/*     */             
/* 140 */             if (k1 >= 0 && this.field_146297_k.field_71466_p.func_78256_a(s.substring(0, k1)) > 0) {
/*     */               
/* 142 */               s1 = s.substring(0, k1);
/* 143 */               s2 = s.substring(k1);
/*     */             } 
/*     */             
/* 146 */             ChatComponentText chatcomponenttext2 = new ChatComponentText(s2);
/* 147 */             chatcomponenttext2.func_150255_a(ichatcomponent1.func_150256_b().func_150232_l());
/* 148 */             arraylist1.add(i1 + 1, chatcomponenttext2);
/*     */           } 
/*     */           
/* 151 */           j1 = this.field_146297_k.field_71466_p.func_78256_a(s1);
/* 152 */           chatcomponenttext1 = new ChatComponentText(s1);
/* 153 */           chatcomponenttext1.func_150255_a(ichatcomponent1.func_150256_b().func_150232_l());
/* 154 */           flag1 = true;
/*     */         } 
/*     */         
/* 157 */         if (l + j1 <= k) {
/*     */           
/* 159 */           l += j1;
/* 160 */           chatcomponenttext.func_150257_a((IChatComponent)chatcomponenttext1);
/*     */         }
/*     */         else {
/*     */           
/* 164 */           flag1 = true;
/*     */         } 
/*     */         
/* 167 */         if (flag1) {
/*     */           
/* 169 */           arraylist.add(chatcomponenttext);
/* 170 */           l = 0;
/* 171 */           chatcomponenttext = new ChatComponentText("");
/*     */         } 
/*     */         
/* 174 */         lineCnt++;
/* 175 */         if (lineCnt < splitLine.length) {
/*     */           
/* 177 */           arraylist.add(chatcomponenttext);
/* 178 */           l = 0;
/* 179 */           chatcomponenttext = new ChatComponentText("");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 184 */     arraylist.add(chatcomponenttext);
/*     */ 
/*     */     
/* 187 */     Iterator<ChatComponentText> iterator = arraylist.iterator();
/* 188 */     for (; iterator.hasNext(); 
/* 189 */       this.chatLines.add(new ChatLine(displayTime, ichatcomponent2, line)))
/*     */     {
/* 191 */       IChatComponent ichatcomponent2 = (IChatComponent)iterator.next();
/*     */     }
/*     */     
/* 194 */     while (this.chatLines.size() > 100)
/*     */     {
/* 196 */       this.chatLines.remove(this.chatLines.size() - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_146243_b() {
/* 202 */     short short1 = 180;
/* 203 */     byte b0 = 20;
/* 204 */     return MathHelper.func_76141_d(this.field_146297_k.field_71474_y.field_96694_H * (short1 - b0) + b0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawChat() {
/* 209 */     float charAlpha = this.field_146297_k.field_71474_y.field_74357_r * 0.9F + 0.1F;
/*     */     
/* 211 */     float scale = this.field_146297_k.field_71474_y.field_96691_E * 2.0F;
/* 212 */     GL11.glPushMatrix();
/* 213 */     float posY = 0.0F;
/* 214 */     switch (this.position) {
/*     */ 
/*     */       
/*     */       default:
/* 218 */         this; posY = (this.field_146297_k.field_71440_d / 2 / scaleFactor) - this.chatLines.size() / 2.0F * 9.0F * scale;
/*     */         break;
/*     */       case 1:
/* 221 */         posY = 0.0F;
/*     */         break;
/*     */       case 2:
/* 224 */         this; posY = (this.field_146297_k.field_71440_d / scaleFactor) - this.chatLines.size() * 9.0F * scale;
/*     */         break;
/*     */       case 3:
/* 227 */         this; posY = (this.field_146297_k.field_71440_d / 3 / scaleFactor) - this.chatLines.size() / 2.0F * 9.0F * scale;
/*     */         break;
/*     */       case 4:
/* 230 */         this; posY = (this.field_146297_k.field_71440_d * 2 / 3 / scaleFactor) - this.chatLines.size() / 2.0F * 9.0F * scale;
/*     */         break;
/*     */     } 
/* 233 */     GL11.glTranslatef(0.0F, posY, 0.0F);
/* 234 */     GL11.glScalef(scale, scale, 1.0F);
/*     */     
/* 236 */     for (int i = 0; i < this.chatLines.size(); i++) {
/*     */       
/* 238 */       ChatLine chatline = this.chatLines.get(i);
/*     */       
/* 240 */       if (chatline != null) {
/*     */         
/* 242 */         int alpha = (int)(255.0F * charAlpha * this.colorAlpha);
/*     */         
/* 244 */         int y = i * 9;
/* 245 */         func_73734_a(0, y + 9, this.field_146297_k.field_71443_c, y, alpha / 2 << 24);
/* 246 */         GL11.glEnable(3042);
/* 247 */         String s = chatline.func_151461_a().func_150254_d();
/* 248 */         this; int sw = this.field_146297_k.field_71443_c / 2 / scaleFactor - this.field_146297_k.field_71466_p.func_78256_a(s);
/* 249 */         sw = (int)(sw / scale);
/* 250 */         this.field_146297_k.field_71466_p.func_78261_a(s, sw, y + 1, 16777215 + (alpha << 24));
/* 251 */         GL11.glDisable(3008);
/*     */       } 
/*     */     } 
/*     */     
/* 255 */     GL11.glTranslatef(-3.0F, 0.0F, 0.0F);
/*     */     
/* 257 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\command\MCH_GuiTitle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */