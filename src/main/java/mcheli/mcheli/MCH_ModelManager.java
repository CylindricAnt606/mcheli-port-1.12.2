 package mcheli.mcheli;

 import java.io.File;
 import java.util.HashMap;
 import java.util.Random;
 import mcheli.mcheli.MCH_MOD;
 import mcheli.mcheli.wrapper.W_ModelBase;
 import mcheli.mcheli.wrapper.W_ResourcePath;
 import mcheli.mcheli.wrapper.modelloader.W_ModelCustom;
 import net.minecraft.client.model.ModelBase;
 import net.minecraft.client.model.ModelRenderer;
 import net.minecraftforge.fml.relauncher.Side;
 import net.minecraftforge.fml.relauncher.SideOnly;

 @SideOnly(Side.CLIENT)
 public class MCH_ModelManager extends W_ModelBase {
   private static mcheli.mcheli.MCH_ModelManager instance = new mcheli.mcheli.MCH_ModelManager();
   
   private static HashMap<String, IModelCustom> map;
   
   private static ModelRenderer defaultModel;
   private static boolean forceReloadMode = false;
   
   private MCH_ModelManager() {
      map = new HashMap<String, IModelCustom>();
      defaultModel = null;
      defaultModel = new ModelRenderer((ModelBase)this, 0, 0);
      defaultModel.func_78790_a(-5.0F, -5.0F, -5.0F, 10, 10, 10, 0.0F);
   }
 
   
   public static void setForceReloadMode(boolean b) {
     forceReloadMode = b;
   }
   
   public static IModelCustom load(String path, String name) {
     if (name == null || name.isEmpty()) return null; 
     return load(path + "/" + name);
   }
   
   public static IModelCustom load(String name) {
     if (name == null || name.isEmpty())
     {
       return null;
     }
 
     
     IModelCustom obj = map.get(name);
     if (obj != null)
     {
       if (forceReloadMode) {
         
         map.remove(name);
       }
       else {
         
         return obj;
       } 
     }
     
     IModelCustom model = null;
     
     try {
       String filePathMqo = "/assets/mcheli/models/" + name + ".mqo";
       String filePathObj = "/assets/mcheli/models/" + name + ".obj";
       String filePathTcn = "/assets/mcheli/models/" + name + ".tcn";
       
       if ((new File(MCH_MOD.sourcePath + filePathMqo)).exists())
       {
         filePathMqo = W_ResourcePath.getModelPath() + "models/" + name + ".mqo";
         model = W_ModelBase.loadModel(filePathMqo);
       }
       else if ((new File(MCH_MOD.sourcePath + filePathObj)).exists())
       {
         filePathObj = W_ResourcePath.getModelPath() + "models/" + name + ".obj";
         model = W_ModelBase.loadModel(filePathObj);
       }
       else if ((new File(MCH_MOD.sourcePath + filePathTcn)).exists())
       {
         filePathTcn = W_ResourcePath.getModelPath() + "models/" + name + ".tcn";
         model = W_ModelBase.loadModel(filePathTcn);
         
       }
       
     }
     catch (Exception e) {
       
       e.printStackTrace();
       model = null;
     } 
     if (model != null) {
       
       map.put(name, model);
       return model;
     } 
     return null;
   }
   
   public static void render(String path, String name) {
     render(path + "/" + name);
   }
   
   public static void render(String name) {
     IModelCustom model = map.get(name);
     if (model != null) {
       
       model.renderAll();
     }
     else if (defaultModel != null) {
     
     } 
   }
   
   public static void renderPart(String name, String partName) {
     IModelCustom model = map.get(name);
     if (model != null)
     {
       model.renderPart(partName);
     }
   }
 
   
   public static void renderLine(String path, String name, int startLine, int maxLine) {
     IModelCustom model = map.get(path + "/" + name);
     if (model instanceof W_ModelCustom)
     {
       ((W_ModelCustom)model).renderAllLine(startLine, maxLine);
     }
   }
   
   public static void render(String path, String name, int startFace, int maxFace) {
     IModelCustom model = map.get(path + "/" + name);
     if (model instanceof W_ModelCustom)
     {
       ((W_ModelCustom)model).renderAll(startFace, maxFace);
     }
   }
   
   public static int getVertexNum(String path, String name) {
     IModelCustom model = map.get(path + "/" + name);
     if (model instanceof W_ModelCustom)
     {
       return ((W_ModelCustom)model).getVertexNum();
     }
     return 0;
   }
 
   
   public static W_ModelCustom get(String path, String name) {
     IModelCustom model = map.get(path + "/" + name);
     if (model instanceof W_ModelCustom)
     {
       return (W_ModelCustom)model;
     }
     return null;
   }
   
   private static Random rand = new Random();
   
   public static W_ModelCustom getRandome() {
     int size = map.size();
     for (int i = 0; i < 10; i++) {
       
       int idx = 0;
       int index = rand.nextInt(size);
       for (IModelCustom model : map.values()) {
         
         if (idx >= index && model instanceof W_ModelCustom)
         {
           return (W_ModelCustom)model;
         }
         idx++;
       } 
     } 
     return null;
   }
   
   public static boolean containsModel(String path, String name) {
     return containsModel(path + "/" + name);
   }
   
   public static boolean containsModel(String name) {
     return map.containsKey(name);
   }
 }
