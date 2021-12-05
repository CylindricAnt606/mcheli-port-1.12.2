/*    */ package mcheli.mcheli.wrapper;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import mcheli.MCH_Lib;
/*    */ import mcheli.MCH_OutputFile;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.Item;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class W_LanguageRegistry
/*    */ {
/* 17 */   private static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addName(Object objectToName, String name) {
/* 24 */     addNameForObject(objectToName, "en_US", name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void addNameForObject(Object o, String lang, String name) {
/* 34 */     addNameForObject(o, lang, name, "", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public static void addNameForObject(Object o, String lang, String name, String key, String desc) {
/* 39 */     if (o == null) {
/*    */       return;
/*    */     }
/*    */     
/* 43 */     if (!map.containsKey(lang))
/*    */     {
/* 45 */       map.put(lang, new ArrayList<String>());
/*    */     }
/*    */     
/* 48 */     if (o instanceof Item)
/*    */     {
/* 50 */       ((ArrayList<String>)map.get(lang)).add(((Item)o).func_77658_a() + ".name=" + name);
/*    */     }
/* 52 */     if (o instanceof Block) {
/*    */       
/* 54 */       ((ArrayList<String>)map.get(lang)).add(((Block)o).func_149739_a() + ".name=" + name);
/*    */     }
/* 56 */     else if (o instanceof net.minecraft.stats.Achievement) {
/*    */ 
/*    */       
/* 59 */       ((ArrayList<String>)map.get(lang)).add("achievement." + key + "=" + name);
/* 60 */       ((ArrayList<String>)map.get(lang)).add("achievement." + key + ".desc=" + desc);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void updateLang(String filePath) {
/* 73 */     for (String key : map.keySet()) {
/*    */       
/* 75 */       ArrayList<String> list = map.get(key);
/* 76 */       MCH_OutputFile file = new MCH_OutputFile();
/* 77 */       if (file.openUTF8(filePath + key + ".lang")) {
/*    */         
/* 79 */         for (String s : list)
/*    */         {
/* 81 */           file.writeLine(s);
/*    */         }
/* 83 */         MCH_Lib.Log("[mcheli] Update lang:" + file.file.getAbsolutePath(), new Object[0]);
/* 84 */         file.close();
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 89 */     map = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_LanguageRegistry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */