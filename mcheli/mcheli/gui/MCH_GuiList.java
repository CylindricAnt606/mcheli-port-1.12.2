/*     */ package mcheli.mcheli.gui;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.gui.MCH_GuiListItem;
/*     */ import mcheli.gui.MCH_GuiSliderVertical;
/*     */ import mcheli.wrapper.W_GuiButton;
/*     */ import net.minecraft.client.Minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_GuiList
/*     */   extends W_GuiButton
/*     */ {
/*     */   public List<MCH_GuiListItem> listItems;
/*     */   public MCH_GuiSliderVertical scrollBar;
/*     */   public final int maxRowNum;
/*     */   public MCH_GuiListItem lastPushItem;
/*     */   
/*     */   public MCH_GuiList(int id, int maxRow, int posX, int posY, int w, int h, String name) {
/*  25 */     super(id, posX, posY, w, h, "");
/*  26 */     this.maxRowNum = (maxRow > 0) ? maxRow : 1;
/*  27 */     this.listItems = new ArrayList<MCH_GuiListItem>();
/*  28 */     this.scrollBar = new MCH_GuiSliderVertical(0, posX + w - 20, posY, 20, h, name, 0.0F, 0.0F, 0.0F, 1.0F);
/*  29 */     this.lastPushItem = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146112_a(Minecraft mc, int x, int y) {
/*  34 */     if (isVisible()) {
/*     */       
/*  36 */       func_73734_a(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, -2143272896);
/*     */       
/*  38 */       this.scrollBar.func_146112_a(mc, x, y);
/*     */       
/*  40 */       for (int i = 0; i < this.maxRowNum; i++) {
/*     */         
/*  42 */         if (i + getStartRow() >= this.listItems.size())
/*     */           break; 
/*  44 */         MCH_GuiListItem item = this.listItems.get(i + getStartRow());
/*     */         
/*  46 */         item.draw(mc, x, y, this.field_146128_h, this.field_146129_i + 5 + 20 * i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItem(MCH_GuiListItem item) {
/*  53 */     this.listItems.add(item);
/*  54 */     int listNum = this.listItems.size();
/*  55 */     this.scrollBar.valueMax = (listNum > this.maxRowNum) ? (listNum - this.maxRowNum) : 0.0F;
/*     */   }
/*     */   
/*     */   public MCH_GuiListItem getItem(int i) {
/*  59 */     return (i < getItemNum()) ? this.listItems.get(i) : null;
/*     */   }
/*     */   
/*     */   public int getItemNum() {
/*  63 */     return this.listItems.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void scrollUp(float a) {
/*  68 */     if (isVisible())
/*     */     {
/*  70 */       this.scrollBar.scrollUp(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public void scrollDown(float a) {
/*  75 */     if (isVisible())
/*     */     {
/*  77 */       this.scrollBar.scrollDown(a);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getStartRow() {
/*  82 */     int startRow = (int)this.scrollBar.getSliderValue();
/*  83 */     return (startRow >= 0) ? startRow : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146119_b(Minecraft mc, int x, int y) {
/*  88 */     if (isVisible())
/*     */     {
/*  90 */       this.scrollBar.func_146119_b(mc, x, y);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_146116_c(Minecraft mc, int x, int y) {
/*  96 */     boolean b = false;
/*  97 */     if (isVisible()) {
/*     */       
/*  99 */       b |= this.scrollBar.func_146116_c(mc, x, y);
/*     */       
/* 101 */       for (int i = 0; i < this.maxRowNum; i++) {
/*     */         
/* 103 */         if (i + getStartRow() >= this.listItems.size())
/*     */           break; 
/* 105 */         MCH_GuiListItem item = this.listItems.get(i + getStartRow());
/*     */         
/* 107 */         if (item.mousePressed(mc, x, y)) {
/*     */           
/* 109 */           this.lastPushItem = item;
/* 110 */           b = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 114 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146118_a(int x, int y) {
/* 119 */     if (isVisible()) {
/*     */       
/* 121 */       this.scrollBar.func_146118_a(x, y);
/* 122 */       for (MCH_GuiListItem item : this.listItems) item.mouseReleased(x, y); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\gui\MCH_GuiList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */