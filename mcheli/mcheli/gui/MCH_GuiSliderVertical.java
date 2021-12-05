/*     */ package mcheli.mcheli.gui;
/*     */ 
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.wrapper.W_GuiButton;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_GuiSliderVertical
/*     */   extends W_GuiButton
/*     */ {
/*     */   private float currentSlider;
/*     */   private boolean isMousePress;
/*  20 */   public float valueMin = 0.0F;
/*  21 */   public float valueMax = 1.0F;
/*  22 */   public float valueStep = 0.1F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_GuiSliderVertical(int gui_id, int posX, int posY, int sliderWidth, int sliderHeight, String string, float defaultSliderPos, float minVal, float maxVal, float step) {
/*  28 */     super(gui_id, posX, posY, sliderWidth, sliderHeight, string);
/*     */     
/*  30 */     this.valueMin = minVal;
/*  31 */     this.valueMax = maxVal;
/*  32 */     this.valueStep = step;
/*     */     
/*  34 */     setSliderValue(defaultSliderPos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_146114_a(boolean p_146114_1_) {
/*  43 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrollUp(float a) {
/*  48 */     if (isVisible())
/*     */     {
/*  50 */       if (!this.isMousePress)
/*     */       {
/*  52 */         setSliderValue(getSliderValue() + this.valueStep * a);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void scrollDown(float a) {
/*  58 */     if (isVisible())
/*     */     {
/*  60 */       if (!this.isMousePress)
/*     */       {
/*  62 */         setSliderValue(getSliderValue() - this.valueStep * a);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146119_b(Minecraft mc, int x, int y) {
/*  72 */     if (isVisible()) {
/*     */       
/*  74 */       if (this.isMousePress) {
/*     */ 
/*     */         
/*  77 */         this.currentSlider = (y - this.field_146129_i + 4) / (this.field_146121_g - 8);
/*     */         
/*  79 */         if (this.currentSlider < 0.0F)
/*     */         {
/*  81 */           this.currentSlider = 0.0F;
/*     */         }
/*     */         
/*  84 */         if (this.currentSlider > 1.0F)
/*     */         {
/*  86 */           this.currentSlider = 1.0F;
/*     */         }
/*     */ 
/*     */         
/*  90 */         this.currentSlider = normalizeValue(denormalizeValue(this.currentSlider));
/*     */       } 
/*     */       
/*  93 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */       
/*  96 */       func_73729_b(this.field_146128_h, this.field_146129_i + (int)(this.currentSlider * (this.field_146121_g - 8)), 66, 0, 20, 4);
/*  97 */       func_73729_b(this.field_146128_h, this.field_146129_i + (int)(this.currentSlider * (this.field_146121_g - 8)) + 4, 66, 196, 20, 4);
/*     */       
/*  99 */       if (!MCH_Key.isKeyDown(-100))
/*     */       {
/* 101 */         func_146118_a(x, y);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSliderValue(float f) {
/* 108 */     this.currentSlider = normalizeValue(f);
/*     */   }
/*     */   
/*     */   public float getSliderValue() {
/* 112 */     return denormalizeValue(this.currentSlider);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSliderValueInt(int digit) {
/* 117 */     int d = 1;
/* 118 */     while (digit > 0) {
/*     */       
/* 120 */       d *= 10;
/* 121 */       digit--;
/*     */     } 
/* 123 */     int n = (int)(denormalizeValue(this.currentSlider) * d);
/* 124 */     return n / d;
/*     */   }
/*     */ 
/*     */   
/*     */   public float normalizeValue(float f) {
/* 129 */     return MathHelper.func_76131_a((snapToStepClamp(f) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public float denormalizeValue(float f) {
/* 133 */     return snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.func_76131_a(f, 0.0F, 1.0F));
/*     */   }
/*     */   
/*     */   public float snapToStepClamp(float f) {
/* 137 */     f = snapToStep(f);
/* 138 */     return MathHelper.func_76131_a(f, this.valueMin, this.valueMax);
/*     */   }
/*     */   
/*     */   protected float snapToStep(float f) {
/* 142 */     if (this.valueStep > 0.0F)
/*     */     {
/* 144 */       f = this.valueStep * Math.round(f / this.valueStep);
/*     */     }
/*     */     
/* 147 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_146116_c(Minecraft mc, int x, int y) {
/* 155 */     if (super.func_146116_c(mc, x, y)) {
/*     */       
/* 157 */       this.currentSlider = (y - this.field_146129_i + 4) / (this.field_146121_g - 8);
/*     */       
/* 159 */       if (this.currentSlider < 0.0F)
/*     */       {
/* 161 */         this.currentSlider = 0.0F;
/*     */       }
/*     */       
/* 164 */       if (this.currentSlider > 1.0F)
/*     */       {
/* 166 */         this.currentSlider = 1.0F;
/*     */       }
/*     */       
/* 169 */       this.isMousePress = true;
/* 170 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146118_a(int p_146118_1_, int p_146118_2_) {
/* 183 */     this.isMousePress = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146112_a(Minecraft mc, int x, int y) {
/* 189 */     if (isVisible()) {
/*     */       
/* 191 */       FontRenderer fontrenderer = mc.field_71466_p;
/* 192 */       mc.func_110434_K().func_110577_a(new ResourceLocation("mcheli", "textures/gui/widgets.png"));
/* 193 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 194 */       setOnMouseOver((x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g));
/* 195 */       int k = func_146114_a(isOnMouseOver());
/* 196 */       enableBlend();
/* 197 */       func_73729_b(this.field_146128_h, this.field_146129_i, 46 + k * 20, 0, this.field_146120_f, this.field_146121_g / 2);
/*     */ 
/*     */       
/* 200 */       func_73729_b(this.field_146128_h, this.field_146129_i + this.field_146121_g / 2, 46 + k * 20, 200 - this.field_146121_g / 2, this.field_146120_f, this.field_146121_g / 2);
/*     */ 
/*     */       
/* 203 */       func_146119_b(mc, x, y);
/* 204 */       int l = 14737632;
/*     */       
/* 206 */       if (this.packedFGColour != 0) {
/*     */         
/* 208 */         l = this.packedFGColour;
/*     */       }
/* 210 */       else if (!this.field_146124_l) {
/*     */         
/* 212 */         l = 10526880;
/*     */       }
/* 214 */       else if (isOnMouseOver()) {
/*     */         
/* 216 */         l = 16777120;
/*     */       } 
/*     */       
/* 219 */       func_73732_a(fontrenderer, this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, l);
/*     */       
/* 221 */       mc.func_110434_K().func_110577_a(field_146122_a);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gui\MCH_GuiSliderVertical.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */