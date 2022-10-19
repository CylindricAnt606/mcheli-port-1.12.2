/*     */ package mcheli.mcheli.wrapper;
/*     */ 
/*     */ import cpw.mods.fml.common.ObfuscationReflectionHelper;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.PlayerControllerMP;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.NetworkSystem;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class W_Reflection
/*     */ {
/*     */   public static RenderManager getRenderManager(Render render) {
/*     */     try {
/*  27 */       return (RenderManager)ObfuscationReflectionHelper.getPrivateValue(Render.class, render, new String[] { "field_76990_c", "renderManager" });
/*     */     }
/*  29 */     catch (Exception e) {
/*     */       
/*  31 */       e.printStackTrace();
/*     */       
/*  33 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void restoreDefaultThirdPersonDistance() {
/*  38 */     setThirdPersonDistance(4.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setThirdPersonDistance(float dist) {
/*  43 */     if (dist < 0.1D)
/*     */       return; 
/*     */     try {
/*  46 */       Minecraft mc = Minecraft.func_71410_x();
/*  47 */       ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.field_71460_t, Float.valueOf(dist), new String[] { "field_78490_B", "thirdPersonDistance" });
/*     */     }
/*  49 */     catch (Exception e) {
/*     */       
/*  51 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getThirdPersonDistance() {
/*     */     try {
/*  59 */       Minecraft mc = Minecraft.func_71410_x();
/*  60 */       return ((Float)ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, mc.field_71460_t, new String[] { "field_78490_B", "thirdPersonDistance" })).floatValue();
/*     */     }
/*  62 */     catch (Exception e) {
/*     */       
/*  64 */       e.printStackTrace();
/*     */       
/*  66 */       return 4.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCameraRoll(float roll) {
/*     */     try {
/*  73 */       roll = MathHelper.func_76142_g(roll);
/*     */       
/*  75 */       Minecraft mc = Minecraft.func_71410_x();
/*  76 */       ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, (Minecraft.func_71410_x()).field_71460_t, Float.valueOf(roll), new String[] { "field_78495_O", "camRoll" });
/*     */       
/*  78 */       ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, (Minecraft.func_71410_x()).field_71460_t, Float.valueOf(roll), new String[] { "field_78505_P", "prevCamRoll" });
/*     */     }
/*  80 */     catch (Exception e) {
/*     */       
/*  82 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getPrevCameraRoll() {
/*     */     try {
/*  89 */       Minecraft mc = Minecraft.func_71410_x();
/*  90 */       return ((Float)ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, (Minecraft.func_71410_x()).field_71460_t, new String[] { "field_78505_P", "prevCamRoll" })).floatValue();
/*     */     }
/*  92 */     catch (Exception e) {
/*     */       
/*  94 */       e.printStackTrace();
/*     */       
/*  96 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void restoreCameraZoom() {
/* 101 */     setCameraZoom(1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCameraZoom(float zoom) {
/*     */     try {
/* 107 */       Minecraft mc = Minecraft.func_71410_x();
/* 108 */       ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.field_71460_t, Float.valueOf(zoom), new String[] { "field_78503_V", "cameraZoom" });
/*     */     
/*     */     }
/* 111 */     catch (Exception e) {
/*     */       
/* 113 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setItemRenderer(ItemRenderer r) {
/*     */     try {
/* 123 */       Minecraft mc = Minecraft.func_71410_x();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 134 */     catch (Exception e) {
/*     */       
/* 136 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCreativeDigSpeed(int n) {
/*     */     try {
/* 144 */       Minecraft mc = Minecraft.func_71410_x();
/* 145 */       ObfuscationReflectionHelper.setPrivateValue(PlayerControllerMP.class, mc.field_71442_b, Integer.valueOf(n), new String[] { "field_78781_i", "blockHitDelay" });
/*     */     }
/* 147 */     catch (Exception e) {
/*     */       
/* 149 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemRenderer getItemRenderer() {
/* 155 */     return (Minecraft.func_71410_x()).field_71460_t.field_78516_c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setItemRenderer_ItemToRender(ItemStack itemToRender) {
/*     */     try {
/* 162 */       ObfuscationReflectionHelper.setPrivateValue(ItemRenderer.class, getItemRenderer(), itemToRender, new String[] { "field_78453_b", "itemToRender" });
/*     */     }
/* 164 */     catch (Exception e) {
/*     */       
/* 166 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack getItemRenderer_ItemToRender() {
/*     */     try {
/* 174 */       ItemStack itemstack = (ItemStack)ObfuscationReflectionHelper.getPrivateValue(ItemRenderer.class, getItemRenderer(), new String[] { "field_78453_b", "itemToRender" });
/*     */       
/* 176 */       return itemstack;
/* 177 */     } catch (Exception e) {
/*     */       
/* 179 */       e.printStackTrace();
/*     */       
/* 181 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setItemRendererProgress(float equippedProgress) {
/*     */     try {
/* 188 */       ObfuscationReflectionHelper.setPrivateValue(ItemRenderer.class, getItemRenderer(), Float.valueOf(equippedProgress), new String[] { "field_78454_c", "equippedProgress" });
/*     */ 
/*     */     
/*     */     }
/* 192 */     catch (Exception e) {
/*     */       
/* 194 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setBoundingBox(Entity entity, AxisAlignedBB bb) {
/*     */     try {
/* 202 */       ObfuscationReflectionHelper.setPrivateValue(Entity.class, entity, bb, new String[] { "field_70121_D", "boundingBox" });
/*     */     }
/* 204 */     catch (Exception e) {
/*     */       
/* 206 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List getNetworkManagers() {
/*     */     try {
/* 214 */       List list = (List)ObfuscationReflectionHelper.getPrivateValue(NetworkSystem.class, MinecraftServer.func_71276_C().func_147137_ag(), new String[] { "field_151272_f", "networkManagers" });
/*     */       
/* 216 */       return list;
/* 217 */     } catch (Exception e) {
/*     */       
/* 219 */       e.printStackTrace();
/*     */       
/* 221 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Queue getReceivedPacketsQueue(NetworkManager nm) {
/*     */     try {
/* 228 */       Queue queue = (Queue)ObfuscationReflectionHelper.getPrivateValue(NetworkManager.class, nm, new String[] { "field_150748_i", "receivedPacketsQueue" });
/*     */       
/* 230 */       return queue;
/* 231 */     } catch (Exception e) {
/*     */       
/* 233 */       e.printStackTrace();
/*     */       
/* 235 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Queue getSendPacketsQueue(NetworkManager nm) {
/*     */     try {
/* 242 */       Queue queue = (Queue)ObfuscationReflectionHelper.getPrivateValue(NetworkManager.class, nm, new String[] { "field_150745_j", "outboundPacketsQueue" });
/*     */       
/* 244 */       return queue;
/* 245 */     } catch (Exception e) {
/*     */       
/* 247 */       e.printStackTrace();
/*     */       
/* 249 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_Reflection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */