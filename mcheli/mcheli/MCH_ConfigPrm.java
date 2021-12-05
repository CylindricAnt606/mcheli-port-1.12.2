/*     */ package mcheli.mcheli;
/*     */ 
/*     */ import mcheli.MCH_Lib;
/*     */ 
/*     */ public class MCH_ConfigPrm
/*     */ {
/*     */   public final int type;
/*     */   public final String name;
/*   9 */   public int prmInt = 0;
/*  10 */   public String prmString = "";
/*     */   public boolean prmBool = false;
/*  12 */   public double prmDouble = 0.0D;
/*  13 */   public String desc = "";
/*  14 */   public int prmIntDefault = 0;
/*  15 */   public String validVer = "";
/*     */ 
/*     */   
/*     */   public String toString() {
/*  19 */     if (this.type == 0) return String.valueOf(this.prmInt); 
/*  20 */     if (this.type == 1) return this.prmString; 
/*  21 */     if (this.type == 2) return String.valueOf(this.prmBool);
/*     */     
/*  23 */     if (this.type == 3) return String.format("%.2f", new Object[] { Double.valueOf(this.prmDouble) }).replace(',', '.'); 
/*  24 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public MCH_ConfigPrm(String parameterName, int defaultParameter) {
/*  29 */     this.prmInt = defaultParameter;
/*  30 */     this.prmIntDefault = defaultParameter;
/*  31 */     this.type = 0;
/*  32 */     this.name = parameterName;
/*     */   }
/*     */   
/*     */   public MCH_ConfigPrm(String parameterName, String defaultParameter) {
/*  36 */     this.prmString = defaultParameter;
/*  37 */     this.type = 1;
/*  38 */     this.name = parameterName;
/*     */   }
/*     */   
/*     */   public MCH_ConfigPrm(String parameterName, boolean defaultParameter) {
/*  42 */     this.prmBool = defaultParameter;
/*  43 */     this.type = 2;
/*  44 */     this.name = parameterName;
/*     */   }
/*     */   
/*     */   public MCH_ConfigPrm(String parameterName, double defaultParameter) {
/*  48 */     this.prmDouble = defaultParameter;
/*  49 */     this.type = 3;
/*  50 */     this.name = parameterName;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compare(String s) {
/*  55 */     return this.name.equalsIgnoreCase(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValidVer(String configVer) {
/*  60 */     if (configVer.length() >= 5 && this.validVer.length() >= 5) {
/*     */       
/*  62 */       String[] configVerSplit = configVer.split("\\.");
/*  63 */       String[] validVerSplit = this.validVer.split("\\.");
/*  64 */       if (configVerSplit.length == 3 && validVerSplit.length == 3)
/*     */       {
/*  66 */         for (int i = 0; i < 3; i++) {
/*     */           
/*  68 */           int n1 = Integer.valueOf(configVerSplit[i].replaceAll("[a-zA-Z-_]", "").trim()).intValue();
/*  69 */           int n2 = Integer.valueOf(validVerSplit[i].replaceAll("[a-zA-Z-_]", "").trim()).intValue();
/*     */           
/*  71 */           if (n1 > n2)
/*     */           {
/*  73 */             return true;
/*     */           }
/*     */           
/*  76 */           if (n1 < n2)
/*     */           {
/*  78 */             return false;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrm(int n) {
/*  88 */     if (this.type == 0) this.prmInt = n; 
/*     */   }
/*     */   
/*     */   public void setPrm(String s) {
/*  92 */     if (this.type == 0) this.prmInt = Integer.parseInt(s); 
/*  93 */     if (this.type == 1) this.prmString = s; 
/*  94 */     if (this.type == 2) {
/*     */       
/*  96 */       s = s.toLowerCase();
/*  97 */       if (s.compareTo("true") == 0) this.prmBool = true; 
/*  98 */       if (s.compareTo("false") == 0) this.prmBool = false; 
/*     */     } 
/* 100 */     if (this.type == 3 && !s.isEmpty()) this.prmDouble = MCH_Lib.parseDouble(s); 
/*     */   }
/*     */   
/*     */   public void setPrm(boolean b) {
/* 104 */     if (this.type == 2) this.prmBool = b; 
/*     */   }
/*     */   
/*     */   public void setPrm(double f) {
/* 108 */     if (this.type == 3) this.prmDouble = f; 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_ConfigPrm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */