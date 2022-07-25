/*     */ package mcheli.mcheli.gui;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_ScaledResolution;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public abstract class MCH_Gui
/*     */   extends GuiScreen
/*     */ {
/*  24 */   protected int centerX = 0;
/*  25 */   protected int centerY = 0;
/*  26 */   protected Random rand = new Random();
/*     */   
/*     */   protected float smoothCamPartialTicks;
/*     */   public static int scaleFactor;
/*     */   
/*     */   public MCH_Gui(Minecraft minecraft) {
/*  32 */     this.field_146297_k = minecraft;
/*  33 */     this.smoothCamPartialTicks = 0.0F;
/*  34 */     this.field_73735_i = -110.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  40 */     super.func_73866_w_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onTick() {}
/*     */ 
/*     */   
/*     */   public abstract boolean isDrawGui(EntityPlayer paramEntityPlayer);
/*     */ 
/*     */   
/*     */   public abstract void drawGui(EntityPlayer paramEntityPlayer, boolean paramBoolean);
/*     */   
/*     */   public void func_73863_a(int par1, int par2, float partialTicks) {
/*  59 */     this.smoothCamPartialTicks = partialTicks;
/*     */     
/*  61 */     W_ScaledResolution w_ScaledResolution = new W_ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
/*  62 */     this; scaleFactor = w_ScaledResolution.func_78325_e();
/*     */ 
/*     */     
/*  65 */     if (!this.field_146297_k.field_71474_y.field_74319_N) {
/*     */       
/*  67 */       this; this.field_146294_l = this.field_146297_k.field_71443_c / scaleFactor;
/*  68 */       this; this.field_146295_m = this.field_146297_k.field_71440_d / scaleFactor;
/*  69 */       this.centerX = this.field_146294_l / 2;
/*  70 */       this.centerY = this.field_146295_m / 2;
/*     */       
/*  72 */       GL11.glPushMatrix();
/*  73 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/*  75 */       if (this.field_146297_k.field_71439_g != null) drawGui((EntityPlayer)this.field_146297_k.field_71439_g, (this.field_146297_k.field_71474_y.field_74320_O != 0));
/*     */       
/*  77 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  78 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
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
/*     */   public void drawTexturedModalRectRotate(double left, double top, double width, double height, double uLeft, double vTop, double uWidth, double vHeight, float rot) {
/*  92 */     GL11.glPushMatrix();
/*     */     
/*  94 */     GL11.glTranslated(left + width / 2.0D, top + height / 2.0D, 0.0D);
/*  95 */     GL11.glRotatef(rot, 0.0F, 0.0F, 1.0F);
/*     */     
/*  97 */     float f = 0.00390625F;
/*  98 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  99 */     tessellator.func_78382_b();
/* 100 */     tessellator.func_78374_a(-width / 2.0D, height / 2.0D, this.field_73735_i, uLeft * 0.00390625D, (vTop + vHeight) * 0.00390625D);
/* 101 */     tessellator.func_78374_a(width / 2.0D, height / 2.0D, this.field_73735_i, (uLeft + uWidth) * 0.00390625D, (vTop + vHeight) * 0.00390625D);
/* 102 */     tessellator.func_78374_a(width / 2.0D, -height / 2.0D, this.field_73735_i, (uLeft + uWidth) * 0.00390625D, vTop * 0.00390625D);
/* 103 */     tessellator.func_78374_a(-width / 2.0D, -height / 2.0D, this.field_73735_i, uLeft * 0.00390625D, vTop * 0.00390625D);
/* 104 */     tessellator.func_78381_a();
/*     */     
/* 106 */     GL11.glPopMatrix();
/*     */   }
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
/*     */   public void drawTexturedRect(double left, double top, double width, double height, double uLeft, double vTop, double uWidth, double vHeight, double textureWidth, double textureHeight) {
/* 119 */     float fx = (float)(1.0D / textureWidth);
/* 120 */     float fy = (float)(1.0D / textureHeight);
/* 121 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 122 */     tessellator.func_78382_b();
/* 123 */     tessellator.func_78374_a(left, top + height, this.field_73735_i, uLeft * fx, (vTop + vHeight) * fy);
/* 124 */     tessellator.func_78374_a(left + width, top + height, this.field_73735_i, (uLeft + uWidth) * fx, (vTop + vHeight) * fy);
/* 125 */     tessellator.func_78374_a(left + width, top, this.field_73735_i, (uLeft + uWidth) * fx, vTop * fy);
/* 126 */     tessellator.func_78374_a(left, top, this.field_73735_i, uLeft * fx, vTop * fy);
/* 127 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawLineStipple(double[] line, int color, int factor, int pattern) {
/* 132 */     GL11.glEnable(2852);
/* 133 */     GL11.glLineStipple(factor, (short)pattern);
/* 134 */     drawLine(line, color);
/* 135 */     GL11.glDisable(2852);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawLine(double[] line, int color) {
/* 140 */     drawLine(line, color, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawString(String s, int x, int y, int color) {
/* 145 */     func_73731_b(this.field_146297_k.field_71466_p, s, x, y, color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawDigit(String s, int x, int y, int interval, int color) {
/* 150 */     GL11.glEnable(3042);
/* 151 */     GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color & 0xFF), (byte)(color >> 24 & 0xFF));
/* 152 */     int srcBlend = GL11.glGetInteger(3041);
/* 153 */     int dstBlend = GL11.glGetInteger(3040);
/* 154 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 156 */     W_McClient.MOD_bindTexture("textures/gui/digit.png");
/* 157 */     for (int i = 0; i < s.length(); i++) {
/*     */       
/* 159 */       char c = s.charAt(i);
/* 160 */       if (c >= '0' && c <= '9')
/*     */       {
/* 162 */         func_73729_b(x + interval * i, y, 16 * (c - 48), 0, 16, 16);
/*     */       }
/* 164 */       if (c == '-')
/*     */       {
/* 166 */         func_73729_b(x + interval * i, y, 160, 0, 16, 16);
/*     */       }
/*     */     } 
/*     */     
/* 170 */     GL11.glBlendFunc(srcBlend, dstBlend);
/* 171 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawCenteredString(String s, int x, int y, int color) {
/* 176 */     func_73732_a(this.field_146297_k.field_71466_p, s, x, y, color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawLine(double[] line, int color, int mode) {
/* 181 */     GL11.glPushMatrix();
/*     */     
/* 183 */     GL11.glEnable(3042);
/* 184 */     GL11.glDisable(3553);
/* 185 */     GL11.glBlendFunc(770, 771);
/* 186 */     GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color >> 0 & 0xFF), (byte)(color >> 24 & 0xFF));
/*     */ 
/*     */ 
/*     */     
/* 190 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 191 */     tessellator.func_78371_b(mode);
/* 192 */     for (int i = 0; i < line.length; i += 2)
/*     */     {
/* 194 */       tessellator.func_78377_a(line[i + 0], line[i + 1], this.field_73735_i);
/*     */     }
/* 196 */     tessellator.func_78381_a();
/*     */     
/* 198 */     GL11.glEnable(3553);
/* 199 */     GL11.glDisable(3042);
/*     */     
/* 201 */     GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
/* 202 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawPoints(double[] points, int color, int pointWidth) {
/* 207 */     int prevWidth = GL11.glGetInteger(2833);
/*     */     
/* 209 */     GL11.glPushMatrix();
/*     */     
/* 211 */     GL11.glEnable(3042);
/* 212 */     GL11.glDisable(3553);
/* 213 */     GL11.glBlendFunc(770, 771);
/* 214 */     GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color >> 0 & 0xFF), (byte)(color >> 24 & 0xFF));
/*     */ 
/*     */ 
/*     */     
/* 218 */     GL11.glPointSize(pointWidth);
/* 219 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 220 */     tessellator.func_78371_b(0);
/* 221 */     for (int i = 0; i < points.length; i += 2)
/*     */     {
/* 223 */       tessellator.func_78377_a(points[i], points[i + 1], 0.0D);
/*     */     }
/* 225 */     tessellator.func_78381_a();
/*     */     
/* 227 */     GL11.glEnable(3553);
/* 228 */     GL11.glDisable(3042);
/*     */     
/* 230 */     GL11.glPopMatrix();
/* 231 */     GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
/* 232 */     GL11.glPointSize(prevWidth);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawPoints(ArrayList<Double> points, int color, int pointWidth) {
/* 237 */     int prevWidth = GL11.glGetInteger(2833);
/*     */     
/* 239 */     GL11.glPushMatrix();
/*     */     
/* 241 */     GL11.glEnable(3042);
/* 242 */     GL11.glDisable(3553);
/* 243 */     GL11.glBlendFunc(770, 771);
/* 244 */     GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color >> 0 & 0xFF), (byte)(color >> 24 & 0xFF));
/*     */ 
/*     */ 
/*     */     
/* 248 */     GL11.glPointSize(pointWidth);
/* 249 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 250 */     tessellator.func_78371_b(0);
/* 251 */     for (int i = 0; i < points.size(); i += 2)
/*     */     {
/* 253 */       tessellator.func_78377_a(((Double)points.get(i)).doubleValue(), ((Double)points.get(i + 1)).doubleValue(), 0.0D);
/*     */     }
/* 255 */     tessellator.func_78381_a();
/*     */     
/* 257 */     GL11.glEnable(3553);
/* 258 */     GL11.glDisable(3042);
/*     */     
/* 260 */     GL11.glPopMatrix();
/* 261 */     GL11.glColor4b((byte)-1, (byte)-1, (byte)-1, (byte)-1);
/* 262 */     GL11.glPointSize(prevWidth);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gui\MCH_Gui.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */