/*    */ package mcheli.mcheli.wrapper.modelloader;
/*    */ 
/*    */ import java.net.URL;
/*    */ import mcheli.wrapper.modelloader.W_WavefrontObject;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.model.IModelCustom;
/*    */ import net.minecraftforge.client.model.IModelCustomLoader;
/*    */ import net.minecraftforge.client.model.ModelFormatException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_ObjModelLoader
/*    */   implements IModelCustomLoader
/*    */ {
/*    */   public String getType() {
/* 16 */     return "OBJ model";
/*    */   }
/*    */   
/* 19 */   private static final String[] types = new String[] { "obj" };
/*    */ 
/*    */   
/*    */   public String[] getSuffixes() {
/* 23 */     return types;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IModelCustom loadInstance(ResourceLocation resource) throws ModelFormatException {
/* 29 */     return (IModelCustom)new W_WavefrontObject(resource);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IModelCustom loadInstance(String resourceName, URL resource) throws ModelFormatException {
/* 35 */     return (IModelCustom)new W_WavefrontObject(resourceName, resource);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\modelloader\W_ObjModelLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */