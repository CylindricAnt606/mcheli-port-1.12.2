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
/*     */ public class CharUtil
/*     */ {
/*     */   public static String escapeString(String str) {
/*  16 */     return escapeString(str, 0, str.length());
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
/*     */   public static String escapeString(String str, int pos, int len) {
/*  34 */     StringBuffer sb = new StringBuffer(len);
/*  35 */     int end_pos = pos + len;
/*  36 */     int[] ret = new int[1];
/*  37 */     while (pos < end_pos) {
/*  38 */       char c = escapeChar(str, pos, end_pos, ret);
/*  39 */       if (ret[0] <= 0)
/*     */         break; 
/*  41 */       sb.append(c);
/*  42 */       pos += ret[0];
/*     */     } 
/*  44 */     return sb.toString();
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
/*     */   public static char escapeChar(String str, int pos, int end_pos, int[] ret) {
/*     */     long code;
/*     */     int i;
/*  64 */     if (pos >= end_pos) {
/*  65 */       ret[0] = 0;
/*  66 */       return Character.MIN_VALUE;
/*     */     } 
/*  68 */     char c = str.charAt(pos);
/*  69 */     if (c != '\\') {
/*  70 */       ret[0] = 1;
/*  71 */       return c;
/*     */     } 
/*     */     
/*  74 */     if (++pos >= end_pos) {
/*  75 */       ret[0] = 1;
/*  76 */       return c;
/*     */     } 
/*     */     
/*  79 */     ret[0] = 2;
/*  80 */     c = str.charAt(pos);
/*  81 */     switch (c) {
/*     */       case '0':
/*     */       case '1':
/*     */       case '2':
/*     */       case '3':
/*     */       case '4':
/*     */       case '5':
/*     */       case '6':
/*     */       case '7':
/*  90 */         code = (c - 48);
/*  91 */         for (i = 1; i < 3 && ++pos < end_pos; i++) {
/*  92 */           c = str.charAt(pos);
/*  93 */           if (c < '0' || c > '7')
/*     */             break; 
/*  95 */           ret[0] = ret[0] + 1;
/*  96 */           code *= 8L;
/*  97 */           code += (c - 48);
/*     */         } 
/*  99 */         return (char)(int)code;
/*     */       
/*     */       case 'b':
/* 102 */         return '\b';
/*     */       case 'f':
/* 104 */         return '\f';
/*     */       case 'n':
/* 106 */         return '\n';
/*     */       case 'r':
/* 108 */         return '\r';
/*     */       case 't':
/* 110 */         return '\t';
/*     */       case 'u':
/* 112 */         code = 0L;
/* 113 */         for (i = 0; i < 4 && ++pos < end_pos; i++) {
/* 114 */           c = str.charAt(pos);
/* 115 */           if ('0' <= c && c <= '9') {
/* 116 */             ret[0] = ret[0] + 1;
/* 117 */             code *= 16L;
/* 118 */             code += (c - 48);
/* 119 */           } else if ('a' <= c && c <= 'f') {
/* 120 */             ret[0] = ret[0] + 1;
/* 121 */             code *= 16L;
/* 122 */             code += (c - 97 + 10);
/* 123 */           } else if ('A' <= c && c <= 'F') {
/* 124 */             ret[0] = ret[0] + 1;
/* 125 */             code *= 16L;
/* 126 */             code += (c - 65 + 10);
/*     */           } else {
/*     */             break;
/*     */           } 
/* 130 */         }  return (char)(int)code;
/*     */     } 
/*     */ 
/*     */     
/* 134 */     return c;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eva\\util\CharUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */