/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import mcheli.wrapper.W_EntityRenderer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.ItemRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MCH_ItemRendererDummy
/*    */   extends ItemRenderer
/*    */ {
/*    */   protected static Minecraft mc;
/*    */   protected static ItemRenderer backupItemRenderer;
/*    */   protected static mcheli.MCH_ItemRendererDummy instance;
/*    */   
/*    */   public MCH_ItemRendererDummy(Minecraft par1Minecraft) {
/* 21 */     super(par1Minecraft);
/* 22 */     this; mc = par1Minecraft;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_78440_a(float par1) {
/* 27 */     if (mc.field_71439_g == null) {
/*    */       
/* 29 */       super.func_78440_a(par1);
/*    */     }
/* 31 */     else if (!(mc.field_71439_g.field_70154_o instanceof mcheli.aircraft.MCH_EntityAircraft) && !(mc.field_71439_g.field_70154_o instanceof mcheli.uav.MCH_EntityUavStation) && !(mc.field_71439_g.field_70154_o instanceof mcheli.gltd.MCH_EntityGLTD)) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 39 */       super.func_78440_a(par1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void enableDummyItemRenderer() {
/* 45 */     if (instance == null) instance = new mcheli.MCH_ItemRendererDummy(Minecraft.func_71410_x()); 
/* 46 */     if (!(mc.field_71460_t.field_78516_c instanceof mcheli.MCH_ItemRendererDummy))
/*    */     {
/* 48 */       backupItemRenderer = mc.field_71460_t.field_78516_c;
/*    */     }
/*    */     
/* 51 */     W_EntityRenderer.setItemRenderer(mc, instance);
/*    */   }
/*    */   
/*    */   public static void disableDummyItemRenderer() {
/* 55 */     if (backupItemRenderer != null)
/*    */     {
/* 57 */       W_EntityRenderer.setItemRenderer(mc, backupItemRenderer);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ItemRendererDummy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */