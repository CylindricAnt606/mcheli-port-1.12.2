/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.io.File;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import mcheli.MCH_MOD;
/*     */ import mcheli.wrapper.W_ModelBase;
/*     */ import mcheli.wrapper.W_ResourcePath;
/*     */ import mcheli.wrapper.modelloader.W_ModelCustom;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraftforge.client.model.IModelCustom;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MCH_ModelManager extends W_ModelBase {
/*  18 */   private static mcheli.MCH_ModelManager instance = new mcheli.MCH_ModelManager();
/*     */   
/*     */   private static HashMap<String, IModelCustom> map;
/*     */   
/*     */   private static ModelRenderer defaultModel;
/*     */   private static boolean forceReloadMode = false;
/*     */   
/*     */   private MCH_ModelManager() {
/*  26 */     this; map = new HashMap<String, IModelCustom>();
/*  27 */     this; defaultModel = null;
/*  28 */     this; defaultModel = new ModelRenderer((ModelBase)this, 0, 0);
/*  29 */     this; defaultModel.func_78790_a(-5.0F, -5.0F, -5.0F, 10, 10, 10, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setForceReloadMode(boolean b) {
/*  34 */     forceReloadMode = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IModelCustom load(String path, String name) {
/*  39 */     if (name == null || name.isEmpty()) return null; 
/*  40 */     return load(path + "/" + name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IModelCustom load(String name) {
/*  46 */     if (name == null || name.isEmpty())
/*     */     {
/*  48 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  52 */     IModelCustom obj = map.get(name);
/*  53 */     if (obj != null)
/*     */     {
/*  55 */       if (forceReloadMode) {
/*     */         
/*  57 */         map.remove(name);
/*     */       }
/*     */       else {
/*     */         
/*  61 */         return obj;
/*     */       } 
/*     */     }
/*     */     
/*  65 */     IModelCustom model = null;
/*     */     
/*     */     try {
/*  68 */       String filePathMqo = "/assets/mcheli/models/" + name + ".mqo";
/*  69 */       String filePathObj = "/assets/mcheli/models/" + name + ".obj";
/*  70 */       String filePathTcn = "/assets/mcheli/models/" + name + ".tcn";
/*     */       
/*  72 */       if ((new File(MCH_MOD.sourcePath + filePathMqo)).exists())
/*     */       {
/*  74 */         filePathMqo = W_ResourcePath.getModelPath() + "models/" + name + ".mqo";
/*  75 */         model = W_ModelBase.loadModel(filePathMqo);
/*     */       }
/*  77 */       else if ((new File(MCH_MOD.sourcePath + filePathObj)).exists())
/*     */       {
/*  79 */         filePathObj = W_ResourcePath.getModelPath() + "models/" + name + ".obj";
/*  80 */         model = W_ModelBase.loadModel(filePathObj);
/*     */       }
/*  82 */       else if ((new File(MCH_MOD.sourcePath + filePathTcn)).exists())
/*     */       {
/*  84 */         filePathTcn = W_ResourcePath.getModelPath() + "models/" + name + ".tcn";
/*  85 */         model = W_ModelBase.loadModel(filePathTcn);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*  92 */     catch (Exception e) {
/*     */       
/*  94 */       e.printStackTrace();
/*  95 */       model = null;
/*     */     } 
/*  97 */     if (model != null) {
/*     */       
/*  99 */       map.put(name, model);
/* 100 */       return model;
/*     */     } 
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void render(String path, String name) {
/* 164 */     render(path + "/" + name);
/*     */   }
/*     */   
/*     */   public static void render(String name) {
/* 168 */     IModelCustom model = map.get(name);
/* 169 */     if (model != null) {
/*     */       
/* 171 */       model.renderAll();
/*     */     }
/* 173 */     else if (defaultModel != null) {
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderPart(String name, String partName) {
/* 180 */     IModelCustom model = map.get(name);
/* 181 */     if (model != null)
/*     */     {
/* 183 */       model.renderPart(partName);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderLine(String path, String name, int startLine, int maxLine) {
/* 189 */     IModelCustom model = map.get(path + "/" + name);
/* 190 */     if (model instanceof W_ModelCustom)
/*     */     {
/* 192 */       ((W_ModelCustom)model).renderAllLine(startLine, maxLine);
/*     */     }
/*     */   }
/*     */   
/*     */   public static void render(String path, String name, int startFace, int maxFace) {
/* 197 */     IModelCustom model = map.get(path + "/" + name);
/* 198 */     if (model instanceof W_ModelCustom)
/*     */     {
/* 200 */       ((W_ModelCustom)model).renderAll(startFace, maxFace);
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getVertexNum(String path, String name) {
/* 205 */     IModelCustom model = map.get(path + "/" + name);
/* 206 */     if (model instanceof W_ModelCustom)
/*     */     {
/* 208 */       return ((W_ModelCustom)model).getVertexNum();
/*     */     }
/* 210 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static W_ModelCustom get(String path, String name) {
/* 215 */     IModelCustom model = map.get(path + "/" + name);
/* 216 */     if (model instanceof W_ModelCustom)
/*     */     {
/* 218 */       return (W_ModelCustom)model;
/*     */     }
/* 220 */     return null;
/*     */   }
/*     */   
/* 223 */   private static Random rand = new Random();
/*     */   
/*     */   public static W_ModelCustom getRandome() {
/* 226 */     int size = map.size();
/* 227 */     for (int i = 0; i < 10; i++) {
/*     */       
/* 229 */       int idx = 0;
/* 230 */       int index = rand.nextInt(size);
/* 231 */       for (IModelCustom model : map.values()) {
/*     */         
/* 233 */         if (idx >= index && model instanceof W_ModelCustom)
/*     */         {
/* 235 */           return (W_ModelCustom)model;
/*     */         }
/* 237 */         idx++;
/*     */       } 
/*     */     } 
/* 240 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean containsModel(String path, String name) {
/* 245 */     return containsModel(path + "/" + name);
/*     */   }
/*     */   
/*     */   public static boolean containsModel(String name) {
/* 249 */     return map.containsKey(name);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ModelManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */