/*     */ package mcheli.mcheli.eval.util;
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
/*     */ public class NumberUtil
/*     */ {
/*     */   public static long parseLong(String str) {
/*  42 */     if (str == null)
/*  43 */       return 0L; 
/*  44 */     str = str.trim();
/*  45 */     int len = str.length();
/*  46 */     if (len <= 0) {
/*  47 */       return 0L;
/*     */     }
/*     */     
/*  50 */     switch (str.charAt(len - 1)) {
/*     */       case '.':
/*     */       case 'L':
/*     */       case 'l':
/*  54 */         len--;
/*     */         break;
/*     */     } 
/*     */     
/*  58 */     if (len >= 3 && str.charAt(0) == '0') {
/*  59 */       switch (str.charAt(1)) {
/*     */         case 'B':
/*     */         case 'b':
/*  62 */           return parseLongBin(str, 2, len - 2);
/*     */         case 'O':
/*     */         case 'o':
/*  65 */           return parseLongOct(str, 2, len - 2);
/*     */         case 'X':
/*     */         case 'x':
/*  68 */           return parseLongHex(str, 2, len - 2);
/*     */       } 
/*     */     }
/*  71 */     return parseLongDec(str, 0, len);
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
/*     */   public static long parseLongBin(String str) {
/*  86 */     if (str == null)
/*  87 */       return 0L; 
/*  88 */     return parseLongBin(str, 0, str.length());
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
/*     */   public static long parseLongBin(String str, int pos, int len) {
/* 107 */     long ret = 0L;
/* 108 */     for (int i = 0; i < len; i++) {
/* 109 */       ret *= 2L;
/* 110 */       char c = str.charAt(pos++);
/* 111 */       switch (c) {
/*     */         case '0':
/*     */           break;
/*     */         case '1':
/* 115 */           ret++;
/*     */           break;
/*     */         default:
/* 118 */           throw new NumberFormatException(str.substring(pos, len));
/*     */       } 
/*     */     } 
/* 121 */     return ret;
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
/*     */   public static long parseLongOct(String str) {
/* 136 */     if (str == null)
/* 137 */       return 0L; 
/* 138 */     return parseLongOct(str, 0, str.length());
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
/*     */   public static long parseLongOct(String str, int pos, int len) {
/* 157 */     long ret = 0L;
/* 158 */     for (int i = 0; i < len; i++) {
/* 159 */       ret *= 8L;
/* 160 */       char c = str.charAt(pos++);
/* 161 */       switch (c) {
/*     */         case '0':
/*     */           break;
/*     */         case '1':
/*     */         case '2':
/*     */         case '3':
/*     */         case '4':
/*     */         case '5':
/*     */         case '6':
/*     */         case '7':
/* 171 */           ret += (c - 48);
/*     */           break;
/*     */         default:
/* 174 */           throw new NumberFormatException(str.substring(pos, len));
/*     */       } 
/*     */     } 
/* 177 */     return ret;
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
/*     */   public static long parseLongDec(String str) {
/* 192 */     if (str == null)
/* 193 */       return 0L; 
/* 194 */     return parseLongDec(str, 0, str.length());
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
/*     */   public static long parseLongDec(String str, int pos, int len) {
/* 213 */     long ret = 0L;
/* 214 */     for (int i = 0; i < len; i++) {
/* 215 */       ret *= 10L;
/* 216 */       char c = str.charAt(pos++);
/* 217 */       switch (c) {
/*     */         case '0':
/*     */           break;
/*     */         case '1':
/*     */         case '2':
/*     */         case '3':
/*     */         case '4':
/*     */         case '5':
/*     */         case '6':
/*     */         case '7':
/*     */         case '8':
/*     */         case '9':
/* 229 */           ret += (c - 48);
/*     */           break;
/*     */         default:
/* 232 */           throw new NumberFormatException(str.substring(pos, len));
/*     */       } 
/*     */     } 
/* 235 */     return ret;
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
/*     */   public static long parseLongHex(String str) {
/* 250 */     if (str == null)
/* 251 */       return 0L; 
/* 252 */     return parseLongHex(str, 0, str.length());
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
/*     */   public static long parseLongHex(String str, int pos, int len) {
/* 271 */     long ret = 0L;
/* 272 */     for (int i = 0; i < len; i++) {
/* 273 */       ret *= 16L;
/* 274 */       char c = str.charAt(pos++);
/* 275 */       switch (c) {
/*     */         case '0':
/*     */           break;
/*     */         case '1':
/*     */         case '2':
/*     */         case '3':
/*     */         case '4':
/*     */         case '5':
/*     */         case '6':
/*     */         case '7':
/*     */         case '8':
/*     */         case '9':
/* 287 */           ret += (c - 48);
/*     */           break;
/*     */         case 'a':
/*     */         case 'b':
/*     */         case 'c':
/*     */         case 'd':
/*     */         case 'e':
/*     */         case 'f':
/* 295 */           ret += (c - 97 + 10);
/*     */           break;
/*     */         case 'A':
/*     */         case 'B':
/*     */         case 'C':
/*     */         case 'D':
/*     */         case 'E':
/*     */         case 'F':
/* 303 */           ret += (c - 65 + 10);
/*     */           break;
/*     */         default:
/* 306 */           throw new NumberFormatException(str.substring(pos, len));
/*     */       } 
/*     */     } 
/* 309 */     return ret;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eva\\util\NumberUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */