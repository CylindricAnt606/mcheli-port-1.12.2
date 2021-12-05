/*     */ package mcheli.mcheli.wrapper.modelloader;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import mcheli.wrapper.modelloader.W_Face;
/*     */ import mcheli.wrapper.modelloader.W_GroupObject;
/*     */ import mcheli.wrapper.modelloader.W_ModelCustom;
/*     */ import mcheli.wrapper.modelloader.W_TextureCoordinate;
/*     */ import mcheli.wrapper.modelloader.W_Vertex;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.resources.IResource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.ModelFormatException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class W_MetasequoiaObject
/*     */   extends W_ModelCustom
/*     */ {
/*  31 */   public ArrayList<W_Vertex> vertices = new ArrayList<W_Vertex>();
/*  32 */   public ArrayList<W_GroupObject> groupObjects = new ArrayList<W_GroupObject>();
/*  33 */   private W_GroupObject currentGroupObject = null;
/*     */   private String fileName;
/*  35 */   private int vertexNum = 0;
/*  36 */   private int faceNum = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public W_MetasequoiaObject(ResourceLocation resource) throws ModelFormatException {
/*  41 */     this.fileName = resource.toString();
/*     */ 
/*     */     
/*     */     try {
/*  45 */       IResource res = Minecraft.func_71410_x().func_110442_L().func_110536_a(resource);
/*  46 */       loadObjModel(res.func_110527_b());
/*     */     }
/*  48 */     catch (IOException e) {
/*     */       
/*  50 */       throw new ModelFormatException("IO Exception reading model format:" + this.fileName, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public W_MetasequoiaObject(String fileName, URL resource) throws ModelFormatException {
/*  58 */     this.fileName = fileName;
/*     */ 
/*     */     
/*     */     try {
/*  62 */       loadObjModel(resource.openStream());
/*     */     }
/*  64 */     catch (IOException e) {
/*     */       
/*  66 */       throw new ModelFormatException("IO Exception reading model format:" + this.fileName, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public W_MetasequoiaObject(String filename, InputStream inputStream) throws ModelFormatException {
/*  72 */     this.fileName = filename;
/*  73 */     loadObjModel(inputStream);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsPart(String partName) {
/*  78 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/*  80 */       if (partName.equalsIgnoreCase(groupObject.name))
/*     */       {
/*  82 */         return true;
/*     */       }
/*     */     } 
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadObjModel(InputStream inputStream) throws ModelFormatException {
/*  90 */     BufferedReader reader = null;
/*     */     
/*  92 */     String currentLine = null;
/*  93 */     int lineCount = 0;
/*     */ 
/*     */     
/*     */     try {
/*  97 */       reader = new BufferedReader(new InputStreamReader(inputStream));
/*     */       
/*  99 */       while ((currentLine = reader.readLine()) != null) {
/*     */         
/* 101 */         lineCount++;
/* 102 */         currentLine = currentLine.replaceAll("\\s+", " ").trim();
/*     */ 
/*     */         
/* 105 */         if (isValidGroupObjectLine(currentLine))
/*     */         {
/* 107 */           W_GroupObject group = parseGroupObject(currentLine, lineCount);
/* 108 */           if (group == null) {
/*     */             continue;
/*     */           }
/*     */ 
/*     */           
/* 113 */           group.glDrawingMode = 4;
/*     */           
/* 115 */           this.vertices.clear();
/* 116 */           int vertexNum = 0;
/*     */           
/* 118 */           boolean mirror = false;
/*     */           
/* 120 */           double facet = Math.cos(0.785398163375D);
/* 121 */           boolean shading = false;
/*     */ 
/*     */           
/* 124 */           while ((currentLine = reader.readLine()) != null) {
/*     */             
/* 126 */             lineCount++;
/* 127 */             currentLine = currentLine.replaceAll("\\s+", " ").trim();
/*     */             
/* 129 */             if (currentLine.equalsIgnoreCase("mirror 1"))
/*     */             {
/* 131 */               mirror = true;
/*     */             }
/* 133 */             if (currentLine.equalsIgnoreCase("shading 1"))
/*     */             {
/* 135 */               shading = true;
/*     */             }
/*     */             
/* 138 */             String[] s = currentLine.split(" ");
/* 139 */             if (s.length == 2 && s[0].equalsIgnoreCase("facet"))
/*     */             {
/* 141 */               facet = Math.cos(Double.parseDouble(s[1]) * 3.1415926535D / 180.0D);
/*     */             }
/*     */             
/* 144 */             if (isValidVertexLine(currentLine)) {
/*     */               
/* 146 */               vertexNum = Integer.valueOf(currentLine.split(" ")[1]).intValue();
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           
/* 152 */           if (vertexNum > 0) {
/*     */             
/* 154 */             while ((currentLine = reader.readLine()) != null) {
/*     */               
/* 156 */               lineCount++;
/* 157 */               currentLine = currentLine.replaceAll("\\s+", " ").trim();
/*     */               
/* 159 */               String[] s = currentLine.split(" ");
/* 160 */               if (s.length == 3) {
/*     */                 
/* 162 */                 W_Vertex v = new W_Vertex(Float.valueOf(s[0]).floatValue() / 100.0F, Float.valueOf(s[1]).floatValue() / 100.0F, Float.valueOf(s[2]).floatValue() / 100.0F);
/*     */ 
/*     */ 
/*     */                 
/* 166 */                 checkMinMax(v);
/* 167 */                 this.vertices.add(v);
/*     */                 
/* 169 */                 vertexNum--;
/*     */                 
/* 171 */                 if (vertexNum <= 0) {
/*     */                   break;
/*     */                 }
/*     */                 continue;
/*     */               } 
/* 176 */               if (s.length > 0)
/*     */               {
/* 178 */                 throw new ModelFormatException("format error : " + this.fileName + " : line=" + lineCount);
/*     */               }
/*     */             } 
/*     */             
/* 182 */             int faceNum = 0;
/*     */             
/* 184 */             while ((currentLine = reader.readLine()) != null) {
/*     */               
/* 186 */               lineCount++;
/* 187 */               currentLine = currentLine.replaceAll("\\s+", " ").trim();
/*     */               
/* 189 */               if (isValidFaceLine(currentLine)) {
/*     */                 
/* 191 */                 faceNum = Integer.valueOf(currentLine.split(" ")[1]).intValue();
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 196 */             if (faceNum > 0) {
/*     */               
/* 198 */               while ((currentLine = reader.readLine()) != null) {
/*     */                 
/* 200 */                 lineCount++;
/* 201 */                 currentLine = currentLine.replaceAll("\\s+", " ").trim();
/*     */                 
/* 203 */                 String[] s = currentLine.split(" ");
/* 204 */                 if (s.length > 2) {
/*     */                   
/* 206 */                   if (Integer.valueOf(s[0]).intValue() >= 3) {
/*     */                     
/* 208 */                     W_Face[] faces = parseFace(currentLine, lineCount, mirror);
/* 209 */                     for (W_Face face : faces)
/*     */                     {
/* 211 */                       group.faces.add(face);
/*     */                     }
/*     */                   } 
/* 214 */                   faceNum--;
/* 215 */                   if (faceNum <= 0) {
/*     */                     break;
/*     */                   }
/*     */                   continue;
/*     */                 } 
/* 220 */                 if (s.length > 2 && Integer.valueOf(s[0]).intValue() != 3)
/*     */                 {
/* 222 */                   throw new ModelFormatException("found face is not triangle : " + this.fileName + " : line=" + lineCount);
/*     */                 }
/*     */               } 
/*     */               
/* 226 */               calcVerticesNormal(group, shading, facet);
/*     */             } 
/*     */           } 
/* 229 */           this.vertexNum += this.vertices.size();
/* 230 */           this.faceNum += group.faces.size();
/* 231 */           this.vertices.clear();
/*     */           
/* 233 */           this.groupObjects.add(group);
/*     */         }
/*     */       
/*     */       } 
/* 237 */     } catch (IOException e) {
/*     */       
/* 239 */       throw new ModelFormatException("IO Exception reading model format : " + this.fileName, e);
/*     */     }
/*     */     finally {
/*     */       
/* 243 */       checkMinMaxFinal();
/* 244 */       this.vertices = null;
/*     */       
/*     */       try {
/* 247 */         reader.close();
/*     */       }
/* 249 */       catch (IOException e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 256 */         inputStream.close();
/*     */       }
/* 258 */       catch (IOException e) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void calcVerticesNormal(W_GroupObject group, boolean shading, double facet) {
/* 268 */     for (W_Face f : group.faces) {
/*     */       
/* 270 */       f.vertexNormals = new W_Vertex[f.verticesID.length];
/* 271 */       for (int i = 0; i < f.verticesID.length; i++) {
/*     */         
/* 273 */         W_Vertex vn = getVerticesNormalFromFace(f.faceNormal, f.verticesID[i], group, (float)facet);
/* 274 */         vn.normalize();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 283 */         if (shading) {
/*     */           
/* 285 */           if ((f.faceNormal.x * vn.x + f.faceNormal.y * vn.y + f.faceNormal.z * vn.z) >= facet)
/*     */           {
/* 287 */             f.vertexNormals[i] = vn;
/*     */           }
/*     */           else
/*     */           {
/* 291 */             f.vertexNormals[i] = f.faceNormal;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 296 */           f.vertexNormals[i] = f.faceNormal;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public W_Vertex getVerticesNormalFromFace(W_Vertex faceNormal, int verticesID, W_GroupObject group, float facet) {
/* 304 */     W_Vertex v = new W_Vertex(0.0F, 0.0F, 0.0F);
/*     */     
/* 306 */     for (W_Face f : group.faces) {
/*     */       
/* 308 */       for (int id : f.verticesID) {
/*     */         
/* 310 */         if (id == verticesID) {
/*     */           
/* 312 */           if (f.faceNormal.x * faceNormal.x + f.faceNormal.y * faceNormal.y + f.faceNormal.z * faceNormal.z >= facet)
/*     */           {
/* 314 */             v.add(f.faceNormal);
/*     */           }
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 321 */     v.normalize();
/*     */     
/* 323 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAll() {
/* 330 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 332 */     if (this.currentGroupObject != null) {
/*     */       
/* 334 */       tessellator.func_78371_b(this.currentGroupObject.glDrawingMode);
/*     */     }
/*     */     else {
/*     */       
/* 338 */       tessellator.func_78371_b(4);
/*     */     } 
/* 340 */     tessellateAll(tessellator);
/*     */     
/* 342 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tessellateAll(Tessellator tessellator) {
/* 347 */     for (W_GroupObject groupObject : this.groupObjects)
/*     */     {
/* 349 */       groupObject.render(tessellator);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderOnly(String... groupNames) {
/* 356 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 358 */       for (String groupName : groupNames) {
/*     */         
/* 360 */         if (groupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 362 */           groupObject.render();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void tessellateOnly(Tessellator tessellator, String... groupNames) {
/* 369 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 371 */       for (String groupName : groupNames) {
/*     */         
/* 373 */         if (groupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 375 */           groupObject.render(tessellator);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderPart(String partName) {
/* 384 */     if (partName.charAt(0) == '$') {
/*     */       
/* 386 */       for (int i = 0; i < this.groupObjects.size(); i++) {
/*     */         
/* 388 */         W_GroupObject groupObject = this.groupObjects.get(i);
/* 389 */         if (partName.equalsIgnoreCase(groupObject.name)) {
/*     */           
/* 391 */           groupObject.render();
/*     */           
/* 393 */           i++;
/* 394 */           for (; i < this.groupObjects.size(); i++)
/*     */           {
/* 396 */             groupObject = this.groupObjects.get(i);
/* 397 */             if (groupObject.name.charAt(0) == '$') {
/*     */               break;
/*     */             }
/*     */             
/* 401 */             groupObject.render();
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 408 */       for (W_GroupObject groupObject : this.groupObjects) {
/*     */         
/* 410 */         if (partName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 412 */           groupObject.render();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void tessellatePart(Tessellator tessellator, String partName) {
/* 419 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 421 */       if (partName.equalsIgnoreCase(groupObject.name))
/*     */       {
/* 423 */         groupObject.render(tessellator);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAllExcept(String... excludedGroupNames) {
/* 431 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 433 */       boolean skipPart = false;
/* 434 */       for (String excludedGroupName : excludedGroupNames) {
/*     */         
/* 436 */         if (excludedGroupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 438 */           skipPart = true;
/*     */         }
/*     */       } 
/* 441 */       if (!skipPart)
/*     */       {
/* 443 */         groupObject.render();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tessellateAllExcept(Tessellator tessellator, String... excludedGroupNames) {
/* 451 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 453 */       boolean exclude = false;
/* 454 */       for (String excludedGroupName : excludedGroupNames) {
/*     */         
/* 456 */         if (excludedGroupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 458 */           exclude = true;
/*     */         }
/*     */       } 
/* 461 */       if (!exclude)
/*     */       {
/* 463 */         groupObject.render(tessellator);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private W_Face[] parseFace(String line, int lineCount, boolean mirror) {
/* 470 */     String[] s = line.split("[ VU)(M]+");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 476 */     int vnum = Integer.valueOf(s[0]).intValue();
/* 477 */     if (vnum != 3 && vnum != 4)
/*     */     {
/* 479 */       return new W_Face[0];
/*     */     }
/*     */     
/* 482 */     if (vnum == 3) {
/*     */       
/* 484 */       W_Face face = new W_Face();
/* 485 */       face.verticesID = new int[] { Integer.valueOf(s[3]).intValue(), Integer.valueOf(s[2]).intValue(), Integer.valueOf(s[1]).intValue() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 492 */       face.vertices = new W_Vertex[] { this.vertices.get(face.verticesID[0]), this.vertices.get(face.verticesID[1]), this.vertices.get(face.verticesID[2]) };
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 497 */       if (s.length >= 11) {
/*     */         
/* 499 */         face.textureCoordinates = new W_TextureCoordinate[] { new W_TextureCoordinate(Float.valueOf(s[9]).floatValue(), Float.valueOf(s[10]).floatValue()), new W_TextureCoordinate(Float.valueOf(s[7]).floatValue(), Float.valueOf(s[8]).floatValue()), new W_TextureCoordinate(Float.valueOf(s[5]).floatValue(), Float.valueOf(s[6]).floatValue()) };
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 507 */         face.textureCoordinates = new W_TextureCoordinate[] { new W_TextureCoordinate(0.0F, 0.0F), new W_TextureCoordinate(0.0F, 0.0F), new W_TextureCoordinate(0.0F, 0.0F) };
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 513 */       face.faceNormal = face.calculateFaceNormal();
/*     */       
/* 515 */       return new W_Face[] { face };
/*     */     } 
/*     */ 
/*     */     
/* 519 */     W_Face face1 = new W_Face();
/* 520 */     face1.verticesID = new int[] { Integer.valueOf(s[3]).intValue(), Integer.valueOf(s[2]).intValue(), Integer.valueOf(s[1]).intValue() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 527 */     face1.vertices = new W_Vertex[] { this.vertices.get(face1.verticesID[0]), this.vertices.get(face1.verticesID[1]), this.vertices.get(face1.verticesID[2]) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 533 */     if (s.length >= 12) {
/*     */       
/* 535 */       face1.textureCoordinates = new W_TextureCoordinate[] { new W_TextureCoordinate(Float.valueOf(s[10]).floatValue(), Float.valueOf(s[11]).floatValue()), new W_TextureCoordinate(Float.valueOf(s[8]).floatValue(), Float.valueOf(s[9]).floatValue()), new W_TextureCoordinate(Float.valueOf(s[6]).floatValue(), Float.valueOf(s[7]).floatValue()) };
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 543 */       face1.textureCoordinates = new W_TextureCoordinate[] { new W_TextureCoordinate(0.0F, 0.0F), new W_TextureCoordinate(0.0F, 0.0F), new W_TextureCoordinate(0.0F, 0.0F) };
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 550 */     face1.faceNormal = face1.calculateFaceNormal();
/*     */ 
/*     */     
/* 553 */     W_Face face2 = new W_Face();
/* 554 */     face2.verticesID = new int[] { Integer.valueOf(s[4]).intValue(), Integer.valueOf(s[3]).intValue(), Integer.valueOf(s[1]).intValue() };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 561 */     face2.vertices = new W_Vertex[] { this.vertices.get(face2.verticesID[0]), this.vertices.get(face2.verticesID[1]), this.vertices.get(face2.verticesID[2]) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 567 */     if (s.length >= 14) {
/*     */       
/* 569 */       face2.textureCoordinates = new W_TextureCoordinate[] { new W_TextureCoordinate(Float.valueOf(s[12]).floatValue(), Float.valueOf(s[13]).floatValue()), new W_TextureCoordinate(Float.valueOf(s[10]).floatValue(), Float.valueOf(s[11]).floatValue()), new W_TextureCoordinate(Float.valueOf(s[6]).floatValue(), Float.valueOf(s[7]).floatValue()) };
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 577 */       face2.textureCoordinates = new W_TextureCoordinate[] { new W_TextureCoordinate(0.0F, 0.0F), new W_TextureCoordinate(0.0F, 0.0F), new W_TextureCoordinate(0.0F, 0.0F) };
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 583 */     face2.faceNormal = face2.calculateFaceNormal();
/*     */     
/* 585 */     return new W_Face[] { face1, face2 };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidGroupObjectLine(String line) {
/* 593 */     String[] s = line.split(" ");
/*     */     
/* 595 */     if (s.length < 2 || !s[0].equals("Object"))
/*     */     {
/* 597 */       return false;
/*     */     }
/*     */     
/* 600 */     if (s[1].length() < 4 || s[1].charAt(0) != '"')
/*     */     {
/* 602 */       return false;
/*     */     }
/*     */     
/* 605 */     return true;
/*     */   }
/*     */   
/*     */   private W_GroupObject parseGroupObject(String line, int lineCount) throws ModelFormatException {
/* 609 */     W_GroupObject group = null;
/*     */     
/* 611 */     if (isValidGroupObjectLine(line)) {
/*     */       
/* 613 */       String[] s = line.split(" ");
/* 614 */       String trimmedLine = s[1].substring(1, s[1].length() - 1);
/*     */       
/* 616 */       if (trimmedLine.length() > 0)
/*     */       {
/* 618 */         group = new W_GroupObject(trimmedLine);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 623 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     } 
/*     */     
/* 626 */     return group;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidVertexLine(String line) {
/* 632 */     String[] s = line.split(" ");
/*     */     
/* 634 */     if (!s[0].equals("vertex")) return false;
/*     */     
/* 636 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidFaceLine(String line) {
/* 646 */     String[] s = line.split(" ");
/*     */     
/* 648 */     if (!s[0].equals("face")) return false;
/*     */     
/* 650 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 656 */     return "mqo";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAllLine(int startLine, int maxLine) {
/* 663 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 665 */     tessellator.func_78371_b(1);
/*     */     
/* 667 */     renderAllLine(tessellator, startLine, maxLine);
/*     */     
/* 669 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderAllLine(Tessellator tessellator, int startLine, int maxLine) {
/* 674 */     int lineCnt = 0;
/* 675 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 677 */       if (groupObject.faces.size() > 0)
/*     */       {
/* 679 */         for (W_Face face : groupObject.faces) {
/*     */           
/* 681 */           for (int i = 0; i < face.vertices.length / 3; i++) {
/*     */             
/* 683 */             W_Vertex v1 = face.vertices[i * 3 + 0];
/* 684 */             W_Vertex v2 = face.vertices[i * 3 + 1];
/* 685 */             W_Vertex v3 = face.vertices[i * 3 + 2];
/*     */             
/* 687 */             lineCnt++;
/* 688 */             if (lineCnt > maxLine)
/* 689 */               return;  tessellator.func_78377_a(v1.x, v1.y, v1.z);
/* 690 */             tessellator.func_78377_a(v2.x, v2.y, v2.z);
/*     */             
/* 692 */             lineCnt++;
/* 693 */             if (lineCnt > maxLine)
/* 694 */               return;  tessellator.func_78377_a(v2.x, v2.y, v2.z);
/* 695 */             tessellator.func_78377_a(v3.x, v3.y, v3.z);
/*     */             
/* 697 */             lineCnt++;
/* 698 */             if (lineCnt > maxLine)
/* 699 */               return;  tessellator.func_78377_a(v3.x, v3.y, v3.z);
/* 700 */             tessellator.func_78377_a(v1.x, v1.y, v1.z);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVertexNum() {
/* 710 */     return this.vertexNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFaceNum() {
/* 716 */     return this.faceNum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAll(int startFace, int maxFace) {
/* 722 */     if (startFace < 0) startFace = 0;
/*     */     
/* 724 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 726 */     tessellator.func_78371_b(4);
/*     */     
/* 728 */     renderAll(tessellator, startFace, maxFace);
/*     */     
/* 730 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderAll(Tessellator tessellator, int startFace, int maxLine) {
/* 735 */     int faceCnt = 0;
/* 736 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 738 */       if (groupObject.faces.size() > 0)
/*     */       {
/* 740 */         for (W_Face face : groupObject.faces) {
/*     */           
/* 742 */           faceCnt++;
/* 743 */           if (faceCnt < startFace)
/* 744 */             continue;  if (faceCnt > maxLine)
/* 745 */             return;  face.addFaceForRender(tessellator);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\modelloader\W_MetasequoiaObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */