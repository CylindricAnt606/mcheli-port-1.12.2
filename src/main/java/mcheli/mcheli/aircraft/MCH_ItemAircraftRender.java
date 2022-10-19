/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import mcheli.MCH_ModelManager;
/*     */ import mcheli.aircraft.MCH_AircraftInfo;
/*     */ import mcheli.aircraft.MCH_ItemAircraft;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_ItemAircraftRender
/*     */   implements IItemRenderer
/*     */ {
/*     */   public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
/*  22 */     if (item != null && item.func_77973_b() instanceof MCH_ItemAircraft) {
/*     */       
/*  24 */       MCH_AircraftInfo info = ((MCH_ItemAircraft)item.func_77973_b()).getAircraftInfo();
/*  25 */       if (info == null) return false;
/*     */       
/*  27 */       if (info != null && info.name.equalsIgnoreCase("mh-60l_dap"))
/*     */       {
/*  29 */         return (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.ENTITY || type == IItemRenderer.ItemRenderType.INVENTORY);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  35 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
/*  42 */     return (type == IItemRenderer.ItemRenderType.ENTITY || type == IItemRenderer.ItemRenderType.INVENTORY);
/*     */   }
/*     */   
/*  45 */   float size = 0.1F;
/*  46 */   float x = 0.1F;
/*  47 */   float y = 0.1F;
/*  48 */   float z = 0.1F;
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
/*  53 */     boolean isRender = true;
/*  54 */     GL11.glPushMatrix();
/*     */     
/*  56 */     GL11.glEnable(2884);
/*     */     
/*  58 */     W_McClient.MOD_bindTexture("textures/helicopters/mh-60l_dap.png");
/*     */     
/*  60 */     switch (null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
/*     */       
/*     */       case 1:
/*  63 */         GL11.glEnable(32826);
/*  64 */         GL11.glEnable(2903);
/*  65 */         GL11.glScalef(0.1F, 0.1F, 0.1F);
/*  66 */         MCH_ModelManager.render("helicopters", "mh-60l_dap");
/*  67 */         GL11.glDisable(32826);
/*     */         break;
/*     */       case 2:
/*  70 */         GL11.glEnable(32826);
/*  71 */         GL11.glEnable(2903);
/*  72 */         GL11.glTranslatef(0.0F, 0.005F, -0.165F);
/*  73 */         GL11.glScalef(0.1F, 0.1F, 0.1F);
/*     */         
/*  75 */         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
/*  76 */         GL11.glRotatef(90.0F, 0.0F, -1.0F, 0.0F);
/*  77 */         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
/*  78 */         MCH_ModelManager.render("helicopters", "mh-60l_dap");
/*  79 */         GL11.glDisable(32826);
/*     */         break;
/*     */       case 3:
/*  82 */         GL11.glEnable(32826);
/*  83 */         GL11.glEnable(2903);
/*  84 */         GL11.glTranslatef(0.3F, 0.5F, -0.5F);
/*  85 */         GL11.glScalef(0.1F, 0.1F, 0.1F);
/*     */         
/*  87 */         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
/*  88 */         GL11.glRotatef(140.0F, 0.0F, 1.0F, 0.0F);
/*  89 */         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
/*  90 */         MCH_ModelManager.render("helicopters", "mh-60l_dap");
/*  91 */         GL11.glDisable(32826);
/*     */         break;
/*     */       case 4:
/*  94 */         GL11.glTranslatef(this.x, this.y, this.z);
/*  95 */         GL11.glScalef(this.size, this.size, this.size);
/*  96 */         MCH_ModelManager.render("helicopters", "mh-60l_dap");
/*     */         break;
/*     */       
/*     */       default:
/* 100 */         isRender = false;
/*     */         break;
/*     */     } 
/*     */     
/* 104 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_ItemAircraftRender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */