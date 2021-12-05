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
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class W_WavefrontObject
/*     */   extends W_ModelCustom
/*     */ {
/*  32 */   private static Pattern vertexPattern = Pattern.compile("(v( (\\-){0,1}\\d+\\.\\d+){3,4} *\\n)|(v( (\\-){0,1}\\d+\\.\\d+){3,4} *$)");
/*  33 */   private static Pattern vertexNormalPattern = Pattern.compile("(vn( (\\-){0,1}\\d+\\.\\d+){3,4} *\\n)|(vn( (\\-){0,1}\\d+\\.\\d+){3,4} *$)");
/*  34 */   private static Pattern textureCoordinatePattern = Pattern.compile("(vt( (\\-){0,1}\\d+\\.\\d+){2,3} *\\n)|(vt( (\\-){0,1}\\d+\\.\\d+){2,3} *$)");
/*  35 */   private static Pattern face_V_VT_VN_Pattern = Pattern.compile("(f( \\d+/\\d+/\\d+){3,4} *\\n)|(f( \\d+/\\d+/\\d+){3,4} *$)");
/*  36 */   private static Pattern face_V_VT_Pattern = Pattern.compile("(f( \\d+/\\d+){3,4} *\\n)|(f( \\d+/\\d+){3,4} *$)");
/*  37 */   private static Pattern face_V_VN_Pattern = Pattern.compile("(f( \\d+//\\d+){3,4} *\\n)|(f( \\d+//\\d+){3,4} *$)");
/*  38 */   private static Pattern face_V_Pattern = Pattern.compile("(f( \\d+){3,4} *\\n)|(f( \\d+){3,4} *$)");
/*     */   
/*  40 */   private static Pattern groupObjectPattern = Pattern.compile("([go]( [-\\$\\w\\d]+) *\\n)|([go]( [-\\$\\w\\d]+) *$)");
/*     */   
/*     */   private static Matcher vertexMatcher;
/*     */   private static Matcher vertexNormalMatcher;
/*     */   private static Matcher textureCoordinateMatcher;
/*     */   private static Matcher face_V_VT_VN_Matcher;
/*  46 */   public ArrayList<W_Vertex> vertices = new ArrayList<W_Vertex>(); private static Matcher face_V_VT_Matcher; private static Matcher face_V_VN_Matcher; private static Matcher face_V_Matcher; private static Matcher groupObjectMatcher;
/*  47 */   public ArrayList<W_Vertex> vertexNormals = new ArrayList<W_Vertex>();
/*  48 */   public ArrayList<W_TextureCoordinate> textureCoordinates = new ArrayList<W_TextureCoordinate>();
/*  49 */   public ArrayList<W_GroupObject> groupObjects = new ArrayList<W_GroupObject>();
/*     */   
/*     */   private W_GroupObject currentGroupObject;
/*     */   
/*     */   private String fileName;
/*     */   
/*     */   public W_WavefrontObject(ResourceLocation resource) throws ModelFormatException {
/*  56 */     this.fileName = resource.toString();
/*     */ 
/*     */     
/*     */     try {
/*  60 */       IResource res = Minecraft.func_71410_x().func_110442_L().func_110536_a(resource);
/*  61 */       loadObjModel(res.func_110527_b());
/*     */     }
/*  63 */     catch (IOException e) {
/*     */       
/*  65 */       throw new ModelFormatException("IO Exception reading model format", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public W_WavefrontObject(String fileName, URL resource) throws ModelFormatException {
/*  73 */     this.fileName = fileName;
/*     */ 
/*     */     
/*     */     try {
/*  77 */       loadObjModel(resource.openStream());
/*     */     }
/*  79 */     catch (IOException e) {
/*     */       
/*  81 */       throw new ModelFormatException("IO Exception reading model format", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public W_WavefrontObject(String filename, InputStream inputStream) throws ModelFormatException {
/*  87 */     this.fileName = filename;
/*  88 */     loadObjModel(inputStream);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean containsPart(String partName) {
/*  93 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/*  95 */       if (partName.equalsIgnoreCase(groupObject.name))
/*     */       {
/*  97 */         return true;
/*     */       }
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadObjModel(InputStream inputStream) throws ModelFormatException {
/* 105 */     BufferedReader reader = null;
/*     */     
/* 107 */     String currentLine = null;
/* 108 */     int lineCount = 0;
/*     */ 
/*     */     
/*     */     try {
/* 112 */       reader = new BufferedReader(new InputStreamReader(inputStream));
/*     */       
/* 114 */       while ((currentLine = reader.readLine()) != null) {
/*     */         
/* 116 */         lineCount++;
/* 117 */         currentLine = currentLine.replaceAll("\\s+", " ").trim();
/*     */         
/* 119 */         if (currentLine.startsWith("#") || currentLine.length() == 0) {
/*     */           continue;
/*     */         }
/*     */         
/* 123 */         if (currentLine.startsWith("v ")) {
/*     */           
/* 125 */           W_Vertex vertex = parseVertex(currentLine, lineCount);
/* 126 */           if (vertex != null) {
/*     */             
/* 128 */             checkMinMax(vertex);
/* 129 */             this.vertices.add(vertex);
/*     */           }  continue;
/*     */         } 
/* 132 */         if (currentLine.startsWith("vn ")) {
/*     */           
/* 134 */           W_Vertex vertex = parseVertexNormal(currentLine, lineCount);
/* 135 */           if (vertex != null)
/*     */           {
/* 137 */             this.vertexNormals.add(vertex); } 
/*     */           continue;
/*     */         } 
/* 140 */         if (currentLine.startsWith("vt ")) {
/*     */           
/* 142 */           W_TextureCoordinate textureCoordinate = parseTextureCoordinate(currentLine, lineCount);
/* 143 */           if (textureCoordinate != null)
/*     */           {
/* 145 */             this.textureCoordinates.add(textureCoordinate); } 
/*     */           continue;
/*     */         } 
/* 148 */         if (currentLine.startsWith("f ")) {
/*     */ 
/*     */           
/* 151 */           if (this.currentGroupObject == null)
/*     */           {
/* 153 */             this.currentGroupObject = new W_GroupObject("Default");
/*     */           }
/*     */           
/* 156 */           W_Face face = parseFace(currentLine, lineCount);
/*     */           
/* 158 */           if (face != null)
/*     */           {
/* 160 */             this.currentGroupObject.faces.add(face); } 
/*     */           continue;
/*     */         } 
/* 163 */         if ((currentLine.startsWith("g ") | currentLine.startsWith("o ")) != 0) {
/*     */           
/* 165 */           if (currentLine.charAt(2) != '$')
/*     */             continue; 
/* 167 */           W_GroupObject group = parseGroupObject(currentLine, lineCount);
/*     */           
/* 169 */           if (group != null)
/*     */           {
/* 171 */             if (this.currentGroupObject != null)
/*     */             {
/* 173 */               this.groupObjects.add(this.currentGroupObject);
/*     */             }
/*     */           }
/*     */           
/* 177 */           this.currentGroupObject = group;
/*     */         } 
/*     */       } 
/*     */       
/* 181 */       this.groupObjects.add(this.currentGroupObject);
/*     */     }
/* 183 */     catch (IOException e) {
/*     */       
/* 185 */       throw new ModelFormatException("IO Exception reading model format", e);
/*     */     }
/*     */     finally {
/*     */       
/* 189 */       checkMinMaxFinal();
/*     */       
/*     */       try {
/* 192 */         reader.close();
/*     */       }
/* 194 */       catch (IOException e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 201 */         inputStream.close();
/*     */       }
/* 203 */       catch (IOException e) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAll() {
/* 213 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 215 */     if (this.currentGroupObject != null) {
/*     */       
/* 217 */       tessellator.func_78371_b(this.currentGroupObject.glDrawingMode);
/*     */     }
/*     */     else {
/*     */       
/* 221 */       tessellator.func_78371_b(4);
/*     */     } 
/* 223 */     tessellateAll(tessellator);
/*     */     
/* 225 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tessellateAll(Tessellator tessellator) {
/* 230 */     for (W_GroupObject groupObject : this.groupObjects)
/*     */     {
/* 232 */       groupObject.render(tessellator);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderOnly(String... groupNames) {
/* 239 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 241 */       for (String groupName : groupNames) {
/*     */         
/* 243 */         if (groupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 245 */           groupObject.render();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void tessellateOnly(Tessellator tessellator, String... groupNames) {
/* 252 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 254 */       for (String groupName : groupNames) {
/*     */         
/* 256 */         if (groupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 258 */           groupObject.render(tessellator);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderPart(String partName) {
/* 267 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 269 */       if (partName.equalsIgnoreCase(groupObject.name))
/*     */       {
/* 271 */         groupObject.render();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void tessellatePart(Tessellator tessellator, String partName) {
/* 277 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 279 */       if (partName.equalsIgnoreCase(groupObject.name))
/*     */       {
/* 281 */         groupObject.render(tessellator);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAllExcept(String... excludedGroupNames) {
/* 289 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 291 */       boolean skipPart = false;
/* 292 */       for (String excludedGroupName : excludedGroupNames) {
/*     */         
/* 294 */         if (excludedGroupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 296 */           skipPart = true;
/*     */         }
/*     */       } 
/* 299 */       if (!skipPart)
/*     */       {
/* 301 */         groupObject.render();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tessellateAllExcept(Tessellator tessellator, String... excludedGroupNames) {
/* 309 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 311 */       boolean exclude = false;
/* 312 */       for (String excludedGroupName : excludedGroupNames) {
/*     */         
/* 314 */         if (excludedGroupName.equalsIgnoreCase(groupObject.name))
/*     */         {
/* 316 */           exclude = true;
/*     */         }
/*     */       } 
/* 319 */       if (!exclude)
/*     */       {
/* 321 */         groupObject.render(tessellator);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private W_Vertex parseVertex(String line, int lineCount) throws ModelFormatException {
/* 328 */     W_Vertex vertex = null;
/*     */     
/* 330 */     if (isValidVertexLine(line)) {
/*     */       
/* 332 */       line = line.substring(line.indexOf(" ") + 1);
/* 333 */       String[] tokens = line.split(" ");
/*     */ 
/*     */       
/*     */       try {
/* 337 */         if (tokens.length == 2)
/*     */         {
/* 339 */           return new W_Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]));
/*     */         }
/* 341 */         if (tokens.length == 3)
/*     */         {
/* 343 */           return new W_Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
/*     */         }
/*     */       }
/* 346 */       catch (NumberFormatException e) {
/*     */         
/* 348 */         throw new ModelFormatException(String.format("Number formatting error at line %d", new Object[] { Integer.valueOf(lineCount) }), e);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 353 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     } 
/*     */     
/* 356 */     return vertex;
/*     */   }
/*     */ 
/*     */   
/*     */   private W_Vertex parseVertexNormal(String line, int lineCount) throws ModelFormatException {
/* 361 */     W_Vertex vertexNormal = null;
/*     */     
/* 363 */     if (isValidVertexNormalLine(line)) {
/*     */       
/* 365 */       line = line.substring(line.indexOf(" ") + 1);
/* 366 */       String[] tokens = line.split(" ");
/*     */ 
/*     */       
/*     */       try {
/* 370 */         if (tokens.length == 3) {
/* 371 */           return new W_Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
/*     */         }
/* 373 */       } catch (NumberFormatException e) {
/*     */         
/* 375 */         throw new ModelFormatException(String.format("Number formatting error at line %d", new Object[] { Integer.valueOf(lineCount) }), e);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 380 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     } 
/*     */     
/* 383 */     return vertexNormal;
/*     */   }
/*     */ 
/*     */   
/*     */   private W_TextureCoordinate parseTextureCoordinate(String line, int lineCount) throws ModelFormatException {
/* 388 */     W_TextureCoordinate textureCoordinate = null;
/*     */     
/* 390 */     if (isValidTextureCoordinateLine(line)) {
/*     */       
/* 392 */       line = line.substring(line.indexOf(" ") + 1);
/* 393 */       String[] tokens = line.split(" ");
/*     */ 
/*     */       
/*     */       try {
/* 397 */         if (tokens.length == 2)
/* 398 */           return new W_TextureCoordinate(Float.parseFloat(tokens[0]), 1.0F - Float.parseFloat(tokens[1])); 
/* 399 */         if (tokens.length == 3) {
/* 400 */           return new W_TextureCoordinate(Float.parseFloat(tokens[0]), 1.0F - Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
/*     */         }
/* 402 */       } catch (NumberFormatException e) {
/*     */         
/* 404 */         throw new ModelFormatException(String.format("Number formatting error at line %d", new Object[] { Integer.valueOf(lineCount) }), e);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 409 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     } 
/*     */     
/* 412 */     return textureCoordinate;
/*     */   }
/*     */ 
/*     */   
/*     */   private W_Face parseFace(String line, int lineCount) throws ModelFormatException {
/* 417 */     W_Face face = null;
/*     */     
/* 419 */     if (isValidFaceLine(line)) {
/*     */       
/* 421 */       face = new W_Face();
/*     */       
/* 423 */       String trimmedLine = line.substring(line.indexOf(" ") + 1);
/* 424 */       String[] tokens = trimmedLine.split(" ");
/* 425 */       String[] subTokens = null;
/*     */       
/* 427 */       if (tokens.length == 3) {
/*     */         
/* 429 */         if (this.currentGroupObject.glDrawingMode == -1)
/*     */         {
/* 431 */           this.currentGroupObject.glDrawingMode = 4;
/*     */         }
/* 433 */         else if (this.currentGroupObject.glDrawingMode != 4)
/*     */         {
/* 435 */           throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Invalid number of points for face (expected 4, found " + tokens.length + ")");
/*     */         }
/*     */       
/* 438 */       } else if (tokens.length == 4) {
/*     */         
/* 440 */         if (this.currentGroupObject.glDrawingMode == -1) {
/*     */           
/* 442 */           this.currentGroupObject.glDrawingMode = 7;
/*     */         }
/* 444 */         else if (this.currentGroupObject.glDrawingMode != 7) {
/*     */           
/* 446 */           throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Invalid number of points for face (expected 3, found " + tokens.length + ")");
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 451 */       if (isValidFace_V_VT_VN_Line(line))
/*     */       {
/* 453 */         face.vertices = new W_Vertex[tokens.length];
/* 454 */         face.textureCoordinates = new W_TextureCoordinate[tokens.length];
/* 455 */         face.vertexNormals = new W_Vertex[tokens.length];
/*     */         
/* 457 */         for (int i = 0; i < tokens.length; i++) {
/*     */           
/* 459 */           subTokens = tokens[i].split("/");
/*     */           
/* 461 */           face.vertices[i] = this.vertices.get(Integer.parseInt(subTokens[0]) - 1);
/* 462 */           face.textureCoordinates[i] = this.textureCoordinates.get(Integer.parseInt(subTokens[1]) - 1);
/* 463 */           face.vertexNormals[i] = this.vertexNormals.get(Integer.parseInt(subTokens[2]) - 1);
/*     */         } 
/*     */         
/* 466 */         face.faceNormal = face.calculateFaceNormal();
/*     */       
/*     */       }
/* 469 */       else if (isValidFace_V_VT_Line(line))
/*     */       {
/* 471 */         face.vertices = new W_Vertex[tokens.length];
/* 472 */         face.textureCoordinates = new W_TextureCoordinate[tokens.length];
/*     */         
/* 474 */         for (int i = 0; i < tokens.length; i++) {
/*     */           
/* 476 */           subTokens = tokens[i].split("/");
/*     */           
/* 478 */           face.vertices[i] = this.vertices.get(Integer.parseInt(subTokens[0]) - 1);
/* 479 */           face.textureCoordinates[i] = this.textureCoordinates.get(Integer.parseInt(subTokens[1]) - 1);
/*     */         } 
/*     */         
/* 482 */         face.faceNormal = face.calculateFaceNormal();
/*     */       
/*     */       }
/* 485 */       else if (isValidFace_V_VN_Line(line))
/*     */       {
/* 487 */         face.vertices = new W_Vertex[tokens.length];
/* 488 */         face.vertexNormals = new W_Vertex[tokens.length];
/*     */         
/* 490 */         for (int i = 0; i < tokens.length; i++) {
/*     */           
/* 492 */           subTokens = tokens[i].split("//");
/*     */           
/* 494 */           face.vertices[i] = this.vertices.get(Integer.parseInt(subTokens[0]) - 1);
/* 495 */           face.vertexNormals[i] = this.vertexNormals.get(Integer.parseInt(subTokens[1]) - 1);
/*     */         } 
/*     */         
/* 498 */         face.faceNormal = face.calculateFaceNormal();
/*     */       
/*     */       }
/* 501 */       else if (isValidFace_V_Line(line))
/*     */       {
/* 503 */         face.vertices = new W_Vertex[tokens.length];
/*     */         
/* 505 */         for (int i = 0; i < tokens.length; i++)
/*     */         {
/* 507 */           face.vertices[i] = this.vertices.get(Integer.parseInt(tokens[i]) - 1);
/*     */         }
/*     */         
/* 510 */         face.faceNormal = face.calculateFaceNormal();
/*     */       }
/*     */       else
/*     */       {
/* 514 */         throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 519 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     } 
/*     */     
/* 522 */     return face;
/*     */   }
/*     */ 
/*     */   
/*     */   private W_GroupObject parseGroupObject(String line, int lineCount) throws ModelFormatException {
/* 527 */     W_GroupObject group = null;
/*     */     
/* 529 */     if (isValidGroupObjectLine(line)) {
/*     */       
/* 531 */       String trimmedLine = line.substring(line.indexOf(" ") + 1);
/*     */       
/* 533 */       if (trimmedLine.length() > 0)
/*     */       {
/* 535 */         group = new W_GroupObject(trimmedLine);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 540 */       throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
/*     */     } 
/*     */     
/* 543 */     return group;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidVertexLine(String line) {
/* 553 */     if (vertexMatcher != null)
/*     */     {
/* 555 */       vertexMatcher.reset();
/*     */     }
/*     */     
/* 558 */     vertexMatcher = vertexPattern.matcher(line);
/* 559 */     return vertexMatcher.matches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidVertexNormalLine(String line) {
/* 569 */     if (vertexNormalMatcher != null)
/*     */     {
/* 571 */       vertexNormalMatcher.reset();
/*     */     }
/*     */     
/* 574 */     vertexNormalMatcher = vertexNormalPattern.matcher(line);
/* 575 */     return vertexNormalMatcher.matches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidTextureCoordinateLine(String line) {
/* 585 */     if (textureCoordinateMatcher != null)
/*     */     {
/* 587 */       textureCoordinateMatcher.reset();
/*     */     }
/*     */     
/* 590 */     textureCoordinateMatcher = textureCoordinatePattern.matcher(line);
/* 591 */     return textureCoordinateMatcher.matches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidFace_V_VT_VN_Line(String line) {
/* 601 */     if (face_V_VT_VN_Matcher != null)
/*     */     {
/* 603 */       face_V_VT_VN_Matcher.reset();
/*     */     }
/*     */     
/* 606 */     face_V_VT_VN_Matcher = face_V_VT_VN_Pattern.matcher(line);
/* 607 */     return face_V_VT_VN_Matcher.matches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidFace_V_VT_Line(String line) {
/* 617 */     if (face_V_VT_Matcher != null)
/*     */     {
/* 619 */       face_V_VT_Matcher.reset();
/*     */     }
/*     */     
/* 622 */     face_V_VT_Matcher = face_V_VT_Pattern.matcher(line);
/* 623 */     return face_V_VT_Matcher.matches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidFace_V_VN_Line(String line) {
/* 633 */     if (face_V_VN_Matcher != null)
/*     */     {
/* 635 */       face_V_VN_Matcher.reset();
/*     */     }
/*     */     
/* 638 */     face_V_VN_Matcher = face_V_VN_Pattern.matcher(line);
/* 639 */     return face_V_VN_Matcher.matches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidFace_V_Line(String line) {
/* 649 */     if (face_V_Matcher != null)
/*     */     {
/* 651 */       face_V_Matcher.reset();
/*     */     }
/*     */     
/* 654 */     face_V_Matcher = face_V_Pattern.matcher(line);
/* 655 */     return face_V_Matcher.matches();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidFaceLine(String line) {
/* 665 */     return (isValidFace_V_VT_VN_Line(line) || isValidFace_V_VT_Line(line) || isValidFace_V_VN_Line(line) || isValidFace_V_Line(line));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isValidGroupObjectLine(String line) {
/* 675 */     if (groupObjectMatcher != null)
/*     */     {
/* 677 */       groupObjectMatcher.reset();
/*     */     }
/*     */     
/* 680 */     groupObjectMatcher = groupObjectPattern.matcher(line);
/* 681 */     return groupObjectMatcher.matches();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 687 */     return "obj";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAllLine(int startLine, int maxLine) {
/* 694 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 696 */     tessellator.func_78371_b(1);
/*     */     
/* 698 */     renderAllLine(tessellator, startLine, maxLine);
/*     */     
/* 700 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderAllLine(Tessellator tessellator, int startLine, int maxLine) {
/* 705 */     int lineCnt = 0;
/* 706 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 708 */       if (groupObject.faces.size() > 0)
/*     */       {
/* 710 */         for (W_Face face : groupObject.faces) {
/*     */           
/* 712 */           for (int i = 0; i < face.vertices.length / 3; i++) {
/*     */             
/* 714 */             W_Vertex v1 = face.vertices[i * 3 + 0];
/* 715 */             W_Vertex v2 = face.vertices[i * 3 + 1];
/* 716 */             W_Vertex v3 = face.vertices[i * 3 + 2];
/*     */             
/* 718 */             lineCnt++;
/* 719 */             if (lineCnt > maxLine)
/* 720 */               return;  tessellator.func_78377_a(v1.x, v1.y, v1.z);
/* 721 */             tessellator.func_78377_a(v2.x, v2.y, v2.z);
/*     */             
/* 723 */             lineCnt++;
/* 724 */             if (lineCnt > maxLine)
/* 725 */               return;  tessellator.func_78377_a(v2.x, v2.y, v2.z);
/* 726 */             tessellator.func_78377_a(v3.x, v3.y, v3.z);
/*     */             
/* 728 */             lineCnt++;
/* 729 */             if (lineCnt > maxLine)
/* 730 */               return;  tessellator.func_78377_a(v3.x, v3.y, v3.z);
/* 731 */             tessellator.func_78377_a(v1.x, v1.y, v1.z);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVertexNum() {
/* 741 */     return this.vertices.size();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFaceNum() {
/* 747 */     return getVertexNum() / 3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderAll(int startFace, int maxFace) {
/* 753 */     if (startFace < 0) startFace = 0;
/*     */     
/* 755 */     Tessellator tessellator = Tessellator.field_78398_a;
/*     */     
/* 757 */     tessellator.func_78371_b(4);
/*     */     
/* 759 */     renderAll(tessellator, startFace, maxFace);
/*     */     
/* 761 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderAll(Tessellator tessellator, int startFace, int maxLine) {
/* 766 */     int faceCnt = 0;
/* 767 */     for (W_GroupObject groupObject : this.groupObjects) {
/*     */       
/* 769 */       if (groupObject.faces.size() > 0)
/*     */       {
/* 771 */         for (W_Face face : groupObject.faces) {
/*     */           
/* 773 */           faceCnt++;
/* 774 */           if (faceCnt < startFace)
/* 775 */             continue;  if (faceCnt > maxLine)
/* 776 */             return;  face.addFaceForRender(tessellator);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\modelloader\W_WavefrontObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */