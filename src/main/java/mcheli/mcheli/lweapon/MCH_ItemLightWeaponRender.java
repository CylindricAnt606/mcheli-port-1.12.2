/*     */ package mcheli.mcheli.lweapon;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_ModelManager;
/*     */ import mcheli.lweapon.MCH_ItemLightWeaponBase;
/*     */ import mcheli.wrapper.W_Lib;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_ItemLightWeaponRender
/*     */   implements IItemRenderer
/*     */ {
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
/*  22 */     return (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
/*  28 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean useCurrentWeapon() {
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
/*  39 */     boolean isRender = false;
/*  40 */     if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.EQUIPPED) {
/*     */       
/*  42 */       isRender = true;
/*  43 */       if (data[1] instanceof EntityPlayer) {
/*     */         
/*  45 */         EntityPlayer player = (EntityPlayer)data[1];
/*  46 */         if (MCH_ItemLightWeaponBase.isHeld(player) && W_Lib.isFirstPerson() && W_Lib.isClientPlayer((Entity)player))
/*     */         {
/*     */ 
/*     */           
/*  50 */           isRender = false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  55 */     if (isRender)
/*     */     {
/*  57 */       renderItem(item, (Entity)W_Lib.castEntityLivingBase(data[1]), (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static void renderItem(ItemStack pitem, Entity entity, boolean isFirstPerson) {
/*  66 */     if (pitem == null || pitem.func_77973_b() == null)
/*     */       return; 
/*  68 */     String name = MCH_ItemLightWeaponBase.getName(pitem);
/*     */     
/*  70 */     GL11.glEnable(32826);
/*  71 */     GL11.glEnable(2903);
/*  72 */     GL11.glPushMatrix();
/*     */     
/*  74 */     if (MCH_Config.SmoothShading.prmBool)
/*     */     {
/*  76 */       GL11.glShadeModel(7425);
/*     */     }
/*     */     
/*  79 */     GL11.glEnable(2884);
/*     */     
/*  81 */     W_McClient.MOD_bindTexture("textures/lweapon/" + name + ".png");
/*     */     
/*  83 */     if (isFirstPerson) {
/*     */       
/*  85 */       GL11.glTranslatef(0.0F, 0.005F, -0.165F);
/*  86 */       GL11.glScalef(2.0F, 2.0F, 2.0F);
/*  87 */       GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
/*  88 */       GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
/*  89 */       GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
/*     */     }
/*     */     else {
/*     */       
/*  93 */       GL11.glTranslatef(0.3F, 0.3F, 0.0F);
/*  94 */       GL11.glScalef(2.0F, 2.0F, 2.0F);
/*  95 */       GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
/*  96 */       GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
/*  97 */       GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
/*     */     } 
/*     */     
/* 100 */     MCH_ModelManager.render("lweapons", name);
/*     */     
/* 102 */     GL11.glShadeModel(7424);
/* 103 */     GL11.glPopMatrix();
/* 104 */     GL11.glDisable(32826);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\lweapon\MCH_ItemLightWeaponRender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */