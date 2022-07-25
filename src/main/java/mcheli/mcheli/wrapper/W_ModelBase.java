/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import cpw.mods.fml.common.FMLLog;
/*    */ import mcheli.wrapper.modelloader.W_MqoModelLoader;
/*    */ import mcheli.wrapper.modelloader.W_ObjModelLoader;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.AdvancedModelLoader;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ import net.minecraftforge.client.model.IModelCustomLoader;
/*    */ import net.minecraftforge.client.model.ModelFormatException;
/*    */ 
/*    */ public abstract class W_ModelBase
/*    */   extends ModelBase {
/* 15 */   private static IModelCustomLoader objLoader = (IModelCustomLoader)new W_ObjModelLoader();
/* 16 */   private static IModelCustomLoader mqoLoader = (IModelCustomLoader)new W_MqoModelLoader();
/*    */ 
/*    */   
/*    */   public static IModelCustom loadModel(String name) throws IllegalArgumentException, ModelFormatException {
/* 20 */     ResourceLocation resource = new ResourceLocation("mcheli", name);
/* 21 */     String path = resource.func_110623_a();
/* 22 */     int i = path.lastIndexOf('.');
/* 23 */     if (i == -1) {
/*    */       
/* 25 */       FMLLog.severe("The resource name %s is not valid", new Object[] { resource });
/* 26 */       throw new IllegalArgumentException("The resource name is not valid");
/*    */     } 
/*    */     
/* 29 */     String test = path.substring(i);
/*    */     
/* 31 */     if (path.substring(i).equalsIgnoreCase(".mqo"))
/*    */     {
/*    */       
/* 34 */       return mqoLoader.loadInstance(resource);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 39 */     if (path.substring(i).equalsIgnoreCase(".obj"))
/*    */     {
/*    */       
/* 42 */       return objLoader.loadInstance(resource);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 50 */     return AdvancedModelLoader.loadModel(resource);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_ModelBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */