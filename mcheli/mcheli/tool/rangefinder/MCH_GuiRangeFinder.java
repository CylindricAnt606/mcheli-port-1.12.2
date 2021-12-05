/*     */ package mcheli.mcheli.tool.rangefinder;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_KeyName;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.gui.MCH_Gui;
/*     */ import mcheli.tool.rangefinder.MCH_ItemRangeFinder;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_GuiRangeFinder
/*     */   extends MCH_Gui
/*     */ {
/*     */   public MCH_GuiRangeFinder(Minecraft minecraft) {
/*  29 */     super(minecraft);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  35 */     super.func_73866_w_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDrawGui(EntityPlayer player) {
/*  46 */     return MCH_ItemRangeFinder.canUse(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawGui(EntityPlayer player, boolean isThirdPersonView) {
/*  51 */     if (isThirdPersonView)
/*     */       return; 
/*  53 */     this; GL11.glLineWidth(scaleFactor);
/*     */     
/*  55 */     if (!isDrawGui(player))
/*     */       return; 
/*  57 */     GL11.glDisable(3042);
/*     */     
/*  59 */     if (MCH_ItemRangeFinder.isUsingScope(player))
/*     */     {
/*  61 */       drawRF(player);
/*     */     }
/*     */   }
/*     */   
/*     */   void drawRF(EntityPlayer player) {
/*  66 */     GL11.glEnable(3042);
/*  67 */     GL11.glColor4f(0.0F, 0.0F, 0.0F, 1.0F);
/*  68 */     int srcBlend = GL11.glGetInteger(3041);
/*  69 */     int dstBlend = GL11.glGetInteger(3040);
/*  70 */     GL11.glBlendFunc(770, 771);
/*     */     
/*  72 */     W_McClient.MOD_bindTexture("textures/gui/rangefinder.png");
/*  73 */     double size = 512.0D; while (true) {
/*  74 */       if (size < this.field_146294_l || size < this.field_146295_m) { size *= 2.0D; continue; }
/*  75 */        drawTexturedModalRectRotate(-(size - this.field_146294_l) / 2.0D, -(size - this.field_146295_m) / 2.0D, size, size, 0.0D, 0.0D, 256.0D, 256.0D, 0.0F);
/*     */       
/*  77 */       GL11.glBlendFunc(srcBlend, dstBlend);
/*  78 */       GL11.glDisable(3042);
/*     */       
/*  80 */       double factor = size / 512.0D;
/*  81 */       double SCALE_FACTOR = scaleFactor * factor;
/*  82 */       double CX = (this.field_146297_k.field_71443_c / 2);
/*  83 */       double CY = (this.field_146297_k.field_71440_d / 2);
/*     */ 
/*     */       
/*  86 */       double px = (CX - 80.0D * SCALE_FACTOR) / SCALE_FACTOR;
/*  87 */       double py = (CY + 55.0D * SCALE_FACTOR) / SCALE_FACTOR;
/*     */       
/*  89 */       GL11.glPushMatrix();
/*  90 */       GL11.glScaled(factor, factor, factor);
/*     */       
/*  92 */       ItemStack item = player.func_71045_bC();
/*  93 */       int damage = (int)((item.func_77958_k() - item.func_77960_j()) / item.func_77958_k() * 100.0D);
/*     */ 
/*     */       
/*  96 */       drawDigit(String.format("%3d", new Object[] { Integer.valueOf(damage) }), (int)px, (int)py, 13, (damage > 0) ? -15663328 : -61424);
/*     */       
/*  98 */       if (damage <= 0) {
/*     */         
/* 100 */         drawString("Please craft", (int)px + 40, (int)py + 0, -65536);
/* 101 */         drawString("redstone", (int)px + 40, (int)py + 10, -65536);
/*     */       } 
/*     */ 
/*     */       
/* 105 */       px = (CX - 20.0D * SCALE_FACTOR) / SCALE_FACTOR;
/* 106 */       if (damage > 0) {
/*     */         
/* 108 */         Vec3 vs = Vec3.func_72443_a(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/* 109 */         Vec3 ve = MCH_Lib.Rot2Vec3(player.field_70177_z, player.field_70125_A);
/* 110 */         ve = vs.func_72441_c(ve.field_72450_a * 300.0D, ve.field_72448_b * 300.0D, ve.field_72449_c * 300.0D);
/* 111 */         MovingObjectPosition mop = player.field_70170_p.func_72901_a(vs, ve, true);
/*     */         
/* 113 */         if (mop != null && mop.field_72313_a != MovingObjectPosition.MovingObjectType.MISS) {
/*     */           
/* 115 */           int range = (int)player.func_70011_f(mop.field_72307_f.field_72450_a, mop.field_72307_f.field_72448_b, mop.field_72307_f.field_72449_c);
/* 116 */           drawDigit(String.format("%4d", new Object[] { Integer.valueOf(range) }), (int)px, (int)py, 13, -15663328);
/*     */         }
/*     */         else {
/*     */           
/* 120 */           drawDigit(String.format("----", new Object[0]), (int)px, (int)py, 13, -61424);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 125 */       py -= 4.0D;
/* 126 */       px -= 80.0D;
/* 127 */       func_73734_a((int)px, (int)py, (int)px + 30, (int)py + 2, -15663328);
/* 128 */       func_73734_a((int)px, (int)py, (int)px + MCH_ItemRangeFinder.rangeFinderUseCooldown / 2, (int)py + 2, -61424);
/*     */ 
/*     */       
/* 131 */       drawString(String.format("x%.1f", new Object[] { Float.valueOf(MCH_ItemRangeFinder.zoom) }), (int)px, (int)py - 20, -1);
/*     */ 
/*     */       
/* 134 */       px += 130.0D;
/* 135 */       int mode = MCH_ItemRangeFinder.mode;
/* 136 */       drawString(">", (int)px, (int)py - 30 + mode * 10, -1);
/* 137 */       px += 10.0D;
/* 138 */       drawString("Players/Vehicles", (int)px, (int)py - 30, (mode == 0) ? -1 : -12566464);
/* 139 */       drawString("Monsters/Mobs", (int)px, (int)py - 20, (mode == 1) ? -1 : -12566464);
/* 140 */       drawString("Mark Point", (int)px, (int)py - 10, (mode == 2) ? -1 : -12566464);
/*     */       
/* 142 */       GL11.glPopMatrix();
/*     */ 
/*     */       
/* 145 */       px = (CX - 160.0D * SCALE_FACTOR) / scaleFactor;
/* 146 */       py = (CY - 100.0D * SCALE_FACTOR) / scaleFactor;
/* 147 */       if (px < 10.0D) px = 10.0D; 
/* 148 */       if (py < 10.0D) py = 10.0D;
/*     */       
/* 150 */       String s = "Spot      : " + MCH_KeyName.getDescOrName(MCH_Config.KeyAttack.prmInt);
/* 151 */       drawString(s, (int)px, (int)py + 0, -1);
/* 152 */       s = "Zoom in   : " + MCH_KeyName.getDescOrName(MCH_Config.KeyZoom.prmInt);
/* 153 */       drawString(s, (int)px, (int)py + 10, (MCH_ItemRangeFinder.zoom < 10.0F) ? -1 : -12566464);
/* 154 */       s = "Zoom out : " + MCH_KeyName.getDescOrName(MCH_Config.KeySwWeaponMode.prmInt);
/* 155 */       drawString(s, (int)px, (int)py + 20, (MCH_ItemRangeFinder.zoom > 1.2F) ? -1 : -12566464);
/* 156 */       s = "Mode      : " + MCH_KeyName.getDescOrName(MCH_Config.KeyFlare.prmInt);
/* 157 */       drawString(s, (int)px, (int)py + 30, -1);
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tool\rangefinder\MCH_GuiRangeFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */