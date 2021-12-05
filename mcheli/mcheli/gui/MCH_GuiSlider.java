/*     */ package mcheli.mcheli.gui;
/*     */ 
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.wrapper.W_GuiButton;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_GuiSlider
/*     */   extends W_GuiButton
/*     */ {
/*     */   private float currentSlider;
/*     */   private boolean isMousePress;
/*     */   public String stringFormat;
/*  17 */   public float valueMin = 0.0F;
/*  18 */   public float valueMax = 1.0F;
/*  19 */   public float valueStep = 0.1F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MCH_GuiSlider(int gui_id, int posX, int posY, int sliderWidth, int sliderHeight, String string_format, float defaultSliderPos, float minVal, float maxVal, float step) {
/*  25 */     super(gui_id, posX, posY, sliderWidth, sliderHeight, "");
/*     */     
/*  27 */     this.stringFormat = string_format;
/*  28 */     this.valueMin = minVal;
/*  29 */     this.valueMax = maxVal;
/*  30 */     this.valueStep = step;
/*     */     
/*  32 */     setSliderValue(defaultSliderPos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_146114_a(boolean p_146114_1_) {
/*  41 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146119_b(Minecraft mc, int x, int y) {
/*  49 */     if (isVisible()) {
/*     */       
/*  51 */       if (this.isMousePress) {
/*     */         
/*  53 */         this.currentSlider = (x - this.field_146128_h + 4) / (this.field_146120_f - 8);
/*     */         
/*  55 */         if (this.currentSlider < 0.0F)
/*     */         {
/*  57 */           this.currentSlider = 0.0F;
/*     */         }
/*     */         
/*  60 */         if (this.currentSlider > 1.0F)
/*     */         {
/*  62 */           this.currentSlider = 1.0F;
/*     */         }
/*     */ 
/*     */         
/*  66 */         this.currentSlider = normalizeValue(denormalizeValue(this.currentSlider));
/*     */         
/*  68 */         updateDisplayString();
/*     */       } 
/*     */       
/*  71 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  72 */       func_73729_b(this.field_146128_h + (int)(this.currentSlider * (this.field_146120_f - 8)), this.field_146129_i, 0, 66, 4, 20);
/*  73 */       func_73729_b(this.field_146128_h + (int)(this.currentSlider * (this.field_146120_f - 8)) + 4, this.field_146129_i, 196, 66, 4, 20);
/*     */       
/*  75 */       if (!MCH_Key.isKeyDown(-100))
/*     */       {
/*  77 */         func_146118_a(x, y);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDisplayString() {
/*  84 */     this.field_146126_j = String.format(this.stringFormat, new Object[] { Float.valueOf(denormalizeValue(this.currentSlider)) });
/*     */   }
/*     */   
/*     */   public void setSliderValue(float f) {
/*  88 */     this.currentSlider = normalizeValue(f);
/*  89 */     updateDisplayString();
/*     */   }
/*     */   
/*     */   public float getSliderValue() {
/*  93 */     return denormalizeValue(this.currentSlider);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSliderValueInt(int digit) {
/*  98 */     int d = 1;
/*  99 */     while (digit > 0) {
/*     */       
/* 101 */       d *= 10;
/* 102 */       digit--;
/*     */     } 
/* 104 */     int n = (int)(denormalizeValue(this.currentSlider) * d);
/* 105 */     return n / d;
/*     */   }
/*     */ 
/*     */   
/*     */   public float normalizeValue(float f) {
/* 110 */     return MathHelper.func_76131_a((snapToStepClamp(f) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public float denormalizeValue(float f) {
/* 114 */     return snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.func_76131_a(f, 0.0F, 1.0F));
/*     */   }
/*     */   
/*     */   public float snapToStepClamp(float f) {
/* 118 */     f = snapToStep(f);
/* 119 */     return MathHelper.func_76131_a(f, this.valueMin, this.valueMax);
/*     */   }
/*     */   
/*     */   protected float snapToStep(float f) {
/* 123 */     if (this.valueStep > 0.0F)
/*     */     {
/* 125 */       f = this.valueStep * Math.round(f / this.valueStep);
/*     */     }
/*     */     
/* 128 */     return f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_146116_c(Minecraft mc, int x, int y) {
/* 136 */     if (super.func_146116_c(mc, x, y)) {
/*     */       
/* 138 */       this.currentSlider = (x - this.field_146128_h + 4) / (this.field_146120_f - 8);
/*     */       
/* 140 */       if (this.currentSlider < 0.0F)
/*     */       {
/* 142 */         this.currentSlider = 0.0F;
/*     */       }
/*     */       
/* 145 */       if (this.currentSlider > 1.0F)
/*     */       {
/* 147 */         this.currentSlider = 1.0F;
/*     */       }
/*     */       
/* 150 */       updateDisplayString();
/* 151 */       this.isMousePress = true;
/* 152 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 156 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146118_a(int p_146118_1_, int p_146118_2_) {
/* 165 */     this.isMousePress = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gui\MCH_GuiSlider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */