/*     */ package mcheli.mcheli.eval.eval.lex;
/*     */ 
/*     */ import java.util.List;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.ShareExpValue;
/*     */ import mcheli.eval.util.CharUtil;
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
/*     */ public class Lex
/*     */ {
/*     */   protected List[] opeList;
/*     */   protected String string;
/*  34 */   protected int pos = 0;
/*     */ 
/*     */   
/*  37 */   protected int len = 0;
/*     */ 
/*     */   
/*  40 */   protected int type = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TYPE_WORD = 2147483632;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TYPE_NUM = 2147483633;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TYPE_OPE = 2147483634;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TYPE_STRING = 2147483635;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TYPE_CHAR = 2147483636;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TYPE_EOF = 2147483647;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int TYPE_ERR = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String ope;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ShareExpValue expShare;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String SPC_CHAR;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String NUMBER_CHAR;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Lex(String str, List[] lists, AbstractExpression paren, ShareExpValue exp)
/*     */   {
/* 112 */     this.SPC_CHAR = " \t\r\n";
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
/* 159 */     this.NUMBER_CHAR = "._";
/*     */     this.string = str;
/*     */     this.opeList = lists;
/*     */     this.expShare = exp;
/*     */     if (this.expShare.paren == null)
/*     */       this.expShare.paren = paren; 
/*     */   }
/*     */   
/*     */   protected boolean isSpace(int pos) {
/*     */     if (pos >= this.string.length())
/*     */       return true; 
/*     */     char c = this.string.charAt(pos);
/*     */     return (this.SPC_CHAR.indexOf(c) >= 0);
/*     */   }
/*     */   
/*     */   protected boolean isSpecialNumber(int pos) {
/* 175 */     if (pos >= this.string.length())
/* 176 */       return false; 
/* 177 */     char c = this.string.charAt(pos);
/* 178 */     return (this.NUMBER_CHAR.indexOf(c) >= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isNumberTop(int pos) {
/*     */     if (pos >= this.string.length()) {
/*     */       return false;
/*     */     }
/*     */     char c = this.string.charAt(pos);
/*     */     return ('0' <= c && c <= '9');
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String isOperator(int pos) {
/* 194 */     for (int i = this.opeList.length - 1; i >= 0; i--) {
/* 195 */       if (pos + i < this.string.length()) {
/*     */         
/* 197 */         List<String> list = this.opeList[i];
/* 198 */         if (list != null)
/* 199 */           for (int j = 0; j < list.size(); j++) {
/* 200 */             String ope = list.get(j);
/* 201 */             int k = 0; while (true) { if (k <= i) {
/* 202 */                 char c = this.string.charAt(pos + k);
/* 203 */                 char o = ope.charAt(k);
/* 204 */                 if (c != o)
/*     */                   break;  k++; continue;
/*     */               } 
/* 207 */               return ope; }
/*     */           
/*     */           }  
/*     */       } 
/* 211 */     }  return null;
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
/*     */   protected boolean isStringTop(int pos) {
/* 223 */     if (pos >= this.string.length())
/* 224 */       return false; 
/* 225 */     char c = this.string.charAt(pos);
/* 226 */     return (c == '"');
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
/*     */   protected boolean isStringEnd(int pos) {
/* 238 */     return isStringTop(pos);
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
/*     */   protected boolean isCharTop(int pos) {
/* 250 */     if (pos >= this.string.length())
/* 251 */       return false; 
/* 252 */     char c = this.string.charAt(pos);
/* 253 */     return (c == '\'');
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
/*     */   protected boolean isCharEnd(int pos) {
/* 265 */     return isCharTop(pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void check() {
/* 276 */     for (; isSpace(this.pos); this.pos++) {
/* 277 */       if (this.pos >= this.string.length()) {
/*     */         
/* 279 */         this.type = Integer.MAX_VALUE;
/* 280 */         this.len = 0;
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 286 */     if (isStringTop(this.pos)) {
/* 287 */       processString();
/*     */       
/*     */       return;
/*     */     } 
/* 291 */     if (isCharTop(this.pos)) {
/* 292 */       processChar();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 297 */     String ope = isOperator(this.pos);
/* 298 */     if (ope != null) {
/* 299 */       this.type = 2147483634;
/* 300 */       this.ope = ope;
/* 301 */       this.len = ope.length();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 306 */     boolean number = isNumberTop(this.pos);
/* 307 */     this.type = number ? 2147483633 : 2147483632;
/* 308 */     this.len = 1;
/* 309 */     for (; !isSpace(this.pos + this.len); this.len++) {
/*     */       
/* 311 */       if (!number || !isSpecialNumber(this.pos + this.len))
/*     */       {
/* 313 */         if (isOperator(this.pos + this.len) != null)
/*     */           break;  } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void processString() {
/* 319 */     int[] ret = new int[1];
/* 320 */     this.type = 2147483635;
/* 321 */     this.len = 1; while (true) {
/* 322 */       this.len += getCharLen(this.pos + this.len, ret);
/* 323 */       if (this.pos + this.len >= this.string.length()) {
/* 324 */         this.type = Integer.MAX_VALUE;
/*     */         break;
/*     */       } 
/* 327 */       if (isStringEnd(this.pos + this.len)) {
/* 328 */         this.len++;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void processChar() {
/* 335 */     int[] ret = new int[1];
/* 336 */     this.type = 2147483636;
/* 337 */     this.len = 1; while (true) {
/* 338 */       this.len += getCharLen(this.pos + this.len, ret);
/* 339 */       if (this.pos + this.len >= this.string.length()) {
/* 340 */         this.type = Integer.MAX_VALUE;
/*     */         break;
/*     */       } 
/* 343 */       if (isCharEnd(this.pos + this.len)) {
/* 344 */         this.len++;
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int getCharLen(int pos, int[] ret) {
/* 351 */     CharUtil.escapeChar(this.string, pos, this.string.length(), ret);
/* 352 */     return ret[0];
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
/*     */   public mcheli.eval.eval.lex.Lex next() {
/* 364 */     this.pos += this.len;
/* 365 */     check();
/* 366 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 375 */     return this.type;
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
/*     */   public String getOperator() {
/* 387 */     return this.ope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOperator(String ope) {
/* 398 */     if (this.type == 2147483634)
/* 399 */       return this.ope.equals(ope); 
/* 400 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWord() {
/* 409 */     return this.string.substring(this.pos, this.pos + this.len);
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
/*     */   public String getString() {
/* 421 */     return this.string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPos() {
/* 430 */     return this.pos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShareExpValue getShare() {
/* 440 */     return this.expShare;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\lex\Lex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */