/*     */ package mcheli.mcheli.tool;
/*     */ 
/*     */ import mcheli.MCH_ClientTickHandlerBase;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_Key;
/*     */ import mcheli.tool.rangefinder.MCH_ItemRangeFinder;
/*     */ import mcheli.wrapper.W_McClient;
/*     */ import mcheli.wrapper.W_Reflection;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MCH_ClientToolTickHandler
/*     */   extends MCH_ClientTickHandlerBase
/*     */ {
/*     */   public MCH_Key KeyUseItem;
/*     */   public MCH_Key KeyZoomIn;
/*     */   public MCH_Key KeyZoomOut;
/*     */   public MCH_Key KeySwitchMode;
/*     */   public MCH_Key[] Keys;
/*     */   
/*     */   public MCH_ClientToolTickHandler(Minecraft minecraft, MCH_Config config) {
/*  26 */     super(minecraft);
/*  27 */     updateKeybind(config);
/*     */   }
/*     */   
/*     */   public void updateKeybind(MCH_Config config) {
/*  31 */     this.KeyUseItem = new MCH_Key(MCH_Config.KeyAttack.prmInt);
/*  32 */     this.KeyZoomIn = new MCH_Key(MCH_Config.KeyZoom.prmInt);
/*  33 */     this.KeyZoomOut = new MCH_Key(MCH_Config.KeySwWeaponMode.prmInt);
/*  34 */     this.KeySwitchMode = new MCH_Key(MCH_Config.KeyFlare.prmInt);
/*  35 */     this.Keys = new MCH_Key[] { this.KeyUseItem, this.KeyZoomIn, this.KeyZoomOut, this.KeySwitchMode };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onTick(boolean inGUI) {
/*  43 */     for (MCH_Key k : this.Keys) k.update();
/*     */     
/*  45 */     onTick_ItemWrench(inGUI, (EntityPlayer)this.mc.field_71439_g);
/*  46 */     onTick_ItemRangeFinder(inGUI, (EntityPlayer)this.mc.field_71439_g);
/*     */   }
/*     */   
/*     */   private void onTick_ItemRangeFinder(boolean inGUI, EntityPlayer player) {
/*  50 */     if (MCH_ItemRangeFinder.rangeFinderUseCooldown > 0)
/*     */     {
/*  52 */       MCH_ItemRangeFinder.rangeFinderUseCooldown--;
/*     */     }
/*     */     
/*  55 */     ItemStack itemStack = null;
/*  56 */     if (player != null) {
/*     */       
/*  58 */       itemStack = this.mc.field_71439_g.func_71045_bC();
/*  59 */       if (itemStack != null && itemStack.func_77973_b() instanceof MCH_ItemRangeFinder) {
/*     */         
/*  61 */         boolean usingItem = (player.func_71057_bx() > 8 && MCH_ItemRangeFinder.canUse(player));
/*     */         
/*  63 */         if (!MCH_ItemRangeFinder.continueUsingItem && usingItem)
/*     */         {
/*  65 */           MCH_ItemRangeFinder.onStartUseItem();
/*     */         }
/*     */         
/*  68 */         if (usingItem) {
/*     */           
/*  70 */           if (this.KeyUseItem.isKeyDown())
/*     */           {
/*  72 */             ((MCH_ItemRangeFinder)itemStack.func_77973_b()).spotEntity(player, itemStack);
/*     */           }
/*     */           
/*  75 */           if (this.KeyZoomIn.isKeyPress() && MCH_ItemRangeFinder.zoom < 10.0F) {
/*     */             
/*  77 */             MCH_ItemRangeFinder.zoom += MCH_ItemRangeFinder.zoom / 10.0F;
/*  78 */             if (MCH_ItemRangeFinder.zoom > 10.0F)
/*     */             {
/*  80 */               MCH_ItemRangeFinder.zoom = 10.0F;
/*     */             }
/*  82 */             W_McClient.MOD_playSoundFX("zoom", 0.05F, 1.0F);
/*  83 */             W_Reflection.setCameraZoom(MCH_ItemRangeFinder.zoom);
/*     */           } 
/*  85 */           if (this.KeyZoomOut.isKeyPress() && MCH_ItemRangeFinder.zoom > 1.2F) {
/*     */             
/*  87 */             MCH_ItemRangeFinder.zoom -= MCH_ItemRangeFinder.zoom / 10.0F;
/*  88 */             if (MCH_ItemRangeFinder.zoom < 1.2F)
/*     */             {
/*  90 */               MCH_ItemRangeFinder.zoom = 1.2F;
/*     */             }
/*  92 */             W_McClient.MOD_playSoundFX("zoom", 0.05F, 0.9F);
/*  93 */             W_Reflection.setCameraZoom(MCH_ItemRangeFinder.zoom);
/*     */           } 
/*  95 */           if (this.KeySwitchMode.isKeyDown()) {
/*     */             
/*  97 */             W_McClient.MOD_playSoundFX("lockon", 1.0F, 0.9F);
/*  98 */             MCH_ItemRangeFinder.mode = (MCH_ItemRangeFinder.mode + 1) % 3;
/*  99 */             if (this.mc.func_71356_B() && MCH_ItemRangeFinder.mode == 0)
/*     */             {
/* 101 */               MCH_ItemRangeFinder.mode = 1;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 108 */     if (MCH_ItemRangeFinder.continueUsingItem)
/*     */     {
/* 110 */       if (itemStack == null || !(itemStack.func_77973_b() instanceof MCH_ItemRangeFinder))
/*     */       {
/* 112 */         MCH_ItemRangeFinder.onStopUseItem();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void onTick_ItemWrench(boolean inGUI, EntityPlayer player) {
/* 118 */     if (player == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 123 */     ItemStack itemStack = player.func_71045_bC();
/* 124 */     if (itemStack != null && itemStack.func_77973_b() instanceof mcheli.tool.MCH_ItemWrench) {
/*     */       
/* 126 */       int maxdm = itemStack.func_77958_k();
/* 127 */       int dm = itemStack.func_77960_j();
/* 128 */       if (dm <= maxdm) {
/*     */         
/* 130 */         ItemStack renderItemstack = W_Reflection.getItemRenderer_ItemToRender();
/* 131 */         if (renderItemstack == null || itemStack.func_77973_b() == renderItemstack.func_77973_b())
/*     */         {
/* 133 */           W_Reflection.setItemRenderer_ItemToRender(player.field_71071_by.func_70448_g());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\tool\MCH_ClientToolTickHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */