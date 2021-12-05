/*     */ package mcheli.mcheli.multiplay;
/*     */ 
/*     */ import cpw.mods.fml.common.Loader;
/*     */ import cpw.mods.fml.common.ModContainer;
/*     */ import cpw.mods.fml.relauncher.CoreModManager;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.zip.ZipEntry;
/*     */ import javax.imageio.ImageIO;
/*     */ import mcheli.MCH_Config;
/*     */ import mcheli.MCH_FileSearch;
/*     */ import mcheli.MCH_Lib;
/*     */ import mcheli.MCH_OStream;
/*     */ import mcheli.multiplay.MCH_PacketLargeData;
/*     */ import mcheli.multiplay.MCH_PacketModList;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.texture.TextureUtil;
/*     */ import net.minecraft.client.shader.Framebuffer;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class MCH_MultiplayClient
/*     */ {
/*     */   private static IntBuffer pixelBuffer;
/*     */   private static int[] pixelValues;
/*     */   private static MCH_OStream dataOutputStream;
/*     */   
/*     */   public static void startSendImageData() {
/*  56 */     Minecraft mc = Minecraft.func_71410_x();
/*  57 */     sendScreenShot(mc.field_71443_c, mc.field_71440_d, mc.func_147110_a());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendScreenShot(int displayWidth, int displayHeight, Framebuffer framebufferMc) {
/*     */     try {
/*  64 */       if (OpenGlHelper.func_148822_b()) {
/*     */         
/*  66 */         displayWidth = framebufferMc.field_147622_a;
/*  67 */         displayHeight = framebufferMc.field_147620_b;
/*     */       } 
/*     */       
/*  70 */       int k = displayWidth * displayHeight;
/*     */       
/*  72 */       if (pixelBuffer == null || pixelBuffer.capacity() < k) {
/*     */         
/*  74 */         pixelBuffer = BufferUtils.createIntBuffer(k);
/*  75 */         pixelValues = new int[k];
/*     */       } 
/*     */       
/*  78 */       GL11.glPixelStorei(3333, 1);
/*  79 */       GL11.glPixelStorei(3317, 1);
/*  80 */       pixelBuffer.clear();
/*     */       
/*  82 */       if (OpenGlHelper.func_148822_b()) {
/*     */         
/*  84 */         GL11.glBindTexture(3553, framebufferMc.field_147617_g);
/*  85 */         GL11.glGetTexImage(3553, 0, 32993, 33639, pixelBuffer);
/*     */       }
/*     */       else {
/*     */         
/*  89 */         GL11.glReadPixels(0, 0, displayWidth, displayHeight, 32993, 33639, pixelBuffer);
/*     */       } 
/*     */       
/*  92 */       pixelBuffer.get(pixelValues);
/*  93 */       TextureUtil.func_147953_a(pixelValues, displayWidth, displayHeight);
/*  94 */       BufferedImage bufferedimage = null;
/*     */       
/*  96 */       if (OpenGlHelper.func_148822_b()) {
/*     */         
/*  98 */         bufferedimage = new BufferedImage(framebufferMc.field_147621_c, framebufferMc.field_147618_d, 1);
/*  99 */         int l = framebufferMc.field_147620_b - framebufferMc.field_147618_d;
/*     */         
/* 101 */         for (int i1 = l; i1 < framebufferMc.field_147620_b; i1++)
/*     */         {
/* 103 */           for (int j1 = 0; j1 < framebufferMc.field_147621_c; j1++)
/*     */           {
/* 105 */             bufferedimage.setRGB(j1, i1 - l, pixelValues[i1 * framebufferMc.field_147622_a + j1]);
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 111 */         bufferedimage = new BufferedImage(displayWidth, displayHeight, 1);
/* 112 */         bufferedimage.setRGB(0, 0, displayWidth, displayHeight, pixelValues, 0, displayWidth);
/*     */       } 
/*     */       
/* 115 */       dataOutputStream = new MCH_OStream();
/* 116 */       ImageIO.write(bufferedimage, "png", (OutputStream)dataOutputStream);
/*     */     }
/* 118 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void readImageData(DataOutputStream dos) throws IOException {
/* 125 */     dataOutputStream.write(dos);
/*     */   }
/*     */   
/*     */   public static void sendImageData() {
/* 129 */     if (dataOutputStream != null) {
/*     */       
/* 131 */       MCH_PacketLargeData.send();
/*     */       
/* 133 */       if (dataOutputStream.isDataEnd())
/*     */       {
/* 135 */         dataOutputStream = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static double getPerData() {
/* 141 */     return (dataOutputStream == null) ? -1.0D : (dataOutputStream.index / dataOutputStream.size());
/*     */   }
/*     */   
/* 144 */   private static List<String> modList = new ArrayList<String>();
/*     */ 
/*     */   
/*     */   public static void readModList(String playerName) {
/* 148 */     modList = new ArrayList<String>();
/*     */     
/* 150 */     modList.add(EnumChatFormatting.RED + "###### " + playerName + " ######");
/*     */     
/* 152 */     String[] classFileNameList = System.getProperty("java.class.path").split(File.pathSeparator);
/* 153 */     for (String classFileName : classFileNameList) {
/*     */       
/* 155 */       MCH_Lib.DbgLog(true, "java.class.path=" + classFileName, new Object[0]);
/* 156 */       if (classFileName.length() > 1) {
/*     */         
/* 158 */         File javaClassFile = new File(classFileName);
/* 159 */         if (javaClassFile.getAbsolutePath().toLowerCase().indexOf("versions") >= 0)
/*     */         {
/* 161 */           modList.add(EnumChatFormatting.AQUA + "# Client class=" + javaClassFile.getName() + " : file size= " + javaClassFile.length());
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 169 */     modList.add(EnumChatFormatting.YELLOW + "=== ActiveModList ===");
/* 170 */     for (ModContainer mod : Loader.instance().getActiveModList())
/*     */     {
/* 172 */       modList.add("" + mod + "  [" + mod.getModId() + "]  " + mod.getName() + "[" + mod.getDisplayVersion() + "]  " + mod.getSource().getName());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (CoreModManager.getAccessTransformers().size() > 0) {
/*     */       
/* 183 */       modList.add(EnumChatFormatting.YELLOW + "=== AccessTransformers ===");
/* 184 */       for (String s : CoreModManager.getAccessTransformers())
/*     */       {
/* 186 */         modList.add(s);
/*     */       }
/*     */     } 
/*     */     
/* 190 */     if (CoreModManager.getLoadedCoremods().size() > 0) {
/*     */       
/* 192 */       modList.add(EnumChatFormatting.YELLOW + "=== LoadedCoremods ===");
/* 193 */       for (String s : CoreModManager.getLoadedCoremods())
/*     */       {
/* 195 */         modList.add(s);
/*     */       }
/*     */     } 
/*     */     
/* 199 */     if (CoreModManager.getReparseableCoremods().size() > 0) {
/*     */       
/* 201 */       modList.add(EnumChatFormatting.YELLOW + "=== ReparseableCoremods ===");
/* 202 */       for (String s : CoreModManager.getReparseableCoremods())
/*     */       {
/* 204 */         modList.add(s);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 209 */     Minecraft mc = Minecraft.func_71410_x();
/* 210 */     MCH_FileSearch search = new MCH_FileSearch();
/* 211 */     File[] files = search.listFiles((new File(mc.field_71412_D, "mods")).getAbsolutePath(), "*.jar");
/* 212 */     modList.add(EnumChatFormatting.YELLOW + "=== Manifest ===");
/* 213 */     for (File file : files) {
/*     */       
/*     */       try {
/* 216 */         String jarPath = file.getCanonicalPath();
/* 217 */         JarFile jarFile = new JarFile(jarPath);
/* 218 */         Enumeration<JarEntry> jarEntries = jarFile.entries();
/* 219 */         String manifest = "";
/* 220 */         while (jarEntries.hasMoreElements()) {
/*     */           
/* 222 */           ZipEntry zipEntry = jarEntries.nextElement();
/* 223 */           if (zipEntry.getName().equalsIgnoreCase("META-INF/MANIFEST.MF") && !zipEntry.isDirectory()) {
/*     */             
/* 225 */             InputStream is = jarFile.getInputStream(zipEntry);
/* 226 */             BufferedReader br = new BufferedReader(new InputStreamReader(is));
/* 227 */             String line = br.readLine();
/* 228 */             while (line != null) {
/*     */               
/* 230 */               line = line.replace(" ", "").trim();
/* 231 */               if (!line.isEmpty())
/*     */               {
/* 233 */                 manifest = manifest + " [" + line + "]";
/*     */               }
/* 235 */               line = br.readLine();
/*     */             } 
/* 237 */             is.close();
/*     */             break;
/*     */           } 
/*     */         } 
/* 241 */         jarFile.close();
/* 242 */         if (!manifest.isEmpty())
/*     */         {
/* 244 */           modList.add(file.getName() + manifest);
/*     */         }
/* 246 */       } catch (Exception e) {
/* 247 */         modList.add(file.getName() + " : Read Manifest failed.");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 253 */     search = new MCH_FileSearch();
/* 254 */     files = search.listFiles((new File(mc.field_71412_D, "mods")).getAbsolutePath(), "*.litemod");
/* 255 */     modList.add(EnumChatFormatting.LIGHT_PURPLE + "=== LiteLoader ===");
/* 256 */     for (File file : files) {
/*     */       
/*     */       try {
/* 259 */         String jarPath = file.getCanonicalPath();
/* 260 */         JarFile jarFile = new JarFile(jarPath);
/* 261 */         Enumeration<JarEntry> jarEntries = jarFile.entries();
/* 262 */         String litemod_json = "";
/* 263 */         while (jarEntries.hasMoreElements()) {
/*     */           
/* 265 */           ZipEntry zipEntry = jarEntries.nextElement();
/* 266 */           String fname = zipEntry.getName().toLowerCase();
/* 267 */           if (zipEntry.isDirectory())
/* 268 */             continue;  if (fname.equals("litemod.json")) {
/*     */             
/* 270 */             InputStream is = jarFile.getInputStream(zipEntry);
/* 271 */             BufferedReader br = new BufferedReader(new InputStreamReader(is));
/* 272 */             String line = br.readLine();
/* 273 */             while (line != null) {
/*     */               
/* 275 */               line = line.replace(" ", "").trim();
/* 276 */               if (line.toLowerCase().indexOf("name") >= 0) {
/*     */                 
/* 278 */                 litemod_json = litemod_json + " [" + line + "]";
/*     */                 break;
/*     */               } 
/* 281 */               line = br.readLine();
/*     */             } 
/* 283 */             is.close();
/*     */             
/*     */             continue;
/*     */           } 
/* 287 */           int index = fname.lastIndexOf("/");
/* 288 */           if (index >= 0)
/*     */           {
/* 290 */             fname = fname.substring(index + 1);
/*     */           }
/* 292 */           if (fname.indexOf("litemod") >= 0 && fname.endsWith("class")) {
/*     */             
/* 294 */             fname = zipEntry.getName();
/* 295 */             if (index >= 0)
/*     */             {
/* 297 */               fname = fname.substring(index + 1);
/*     */             }
/* 299 */             litemod_json = litemod_json + " [" + fname + "]";
/*     */           } 
/*     */         } 
/*     */         
/* 303 */         jarFile.close();
/* 304 */         if (!litemod_json.isEmpty())
/*     */         {
/* 306 */           modList.add(file.getName() + litemod_json);
/*     */         }
/* 308 */       } catch (Exception e) {
/* 309 */         modList.add(file.getName() + " : Read LiteLoader failed.");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void sendModsInfo(String playerName, int id) {
/* 318 */     if (MCH_Config.DebugLog) {
/*     */       
/* 320 */       modList.clear();
/* 321 */       readModList(playerName);
/*     */     } 
/* 323 */     MCH_PacketModList.send(modList, id);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\multiplay\MCH_MultiplayClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */