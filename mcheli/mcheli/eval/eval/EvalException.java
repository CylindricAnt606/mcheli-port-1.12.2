/*     */ package mcheli.mcheli.eval.eval;
/*     */ 
/*     */ import mcheli.eval.eval.lex.Lex;
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
/*     */ public class EvalException
/*     */   extends RuntimeException
/*     */ {
/*     */   private static final long serialVersionUID = 4174576689426433715L;
/*     */   public static final int PARSE_NOT_FOUND_END_OP = 1001;
/*     */   public static final int PARSE_INVALID_OP = 1002;
/*     */   public static final int PARSE_INVALID_CHAR = 1003;
/*     */   public static final int PARSE_END_OF_STR = 1004;
/*     */   public static final int PARSE_STILL_EXIST = 1005;
/*     */   public static final int PARSE_NOT_FUNC = 1101;
/*     */   public static final int EXP_FORBIDDEN_CALL = 2001;
/*     */   public static final int EXP_NOT_VARIABLE = 2002;
/*     */   public static final int EXP_NOT_NUMBER = 2003;
/*     */   public static final int EXP_NOT_LET = 2004;
/*     */   public static final int EXP_NOT_VAR_VALUE = 2101;
/*     */   public static final int EXP_NOT_LET_VAR = 2102;
/*     */   public static final int EXP_NOT_DEF_VAR = 2103;
/*     */   public static final int EXP_NOT_DEF_OBJ = 2104;
/*     */   public static final int EXP_NOT_ARR_VALUE = 2201;
/*     */   public static final int EXP_NOT_LET_ARR = 2202;
/*     */   public static final int EXP_NOT_FLD_VALUE = 2301;
/*     */   public static final int EXP_NOT_LET_FIELD = 2302;
/*     */   public static final int EXP_FUNC_CALL_ERROR = 2401;
/*     */   protected int msg_code;
/*     */   protected String[] msg_opt;
/*     */   protected String string;
/* 108 */   protected int pos = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String word;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EvalException(int msg, Lex lex) {
/* 123 */     this(msg, null, lex);
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
/*     */   public EvalException(int msg, String[] opt, Lex lex) {
/* 138 */     this.msg_code = msg;
/* 139 */     this.msg_opt = opt;
/* 140 */     if (lex != null) {
/* 141 */       this.string = lex.getString();
/* 142 */       this.pos = lex.getPos();
/* 143 */       this.word = lex.getWord();
/*     */     } 
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
/*     */   public EvalException(int msg, String word, String string, int pos, Throwable e) {
/* 162 */     while (e != null && 
/* 163 */       e.getClass() == RuntimeException.class && e.getCause() != null) {
/* 164 */       e = e.getCause();
/*     */     }
/*     */ 
/*     */     
/* 168 */     if (e != null)
/* 169 */       initCause(e); 
/* 170 */     this.msg_code = msg;
/* 171 */     this.string = string;
/* 172 */     this.pos = pos;
/* 173 */     this.word = word;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getErrorCode() {
/* 183 */     return this.msg_code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getOption() {
/* 193 */     return this.msg_opt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWord() {
/* 203 */     return this.word;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString() {
/* 213 */     return this.string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPos() {
/* 223 */     return this.pos;
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
/*     */   public static String getErrCodeMessage(int code) {
/* 235 */     switch (code) {
/*     */       case 1001:
/* 237 */         return "演算子「%0」が在りません。";
/*     */       case 1002:
/* 239 */         return "演算子の文法エラーです。";
/*     */       case 1003:
/* 241 */         return "未対応の識別子です。";
/*     */       case 1004:
/* 243 */         return "式の解釈の途中で文字列が終了しています。";
/*     */       case 1005:
/* 245 */         return "式の解釈が終わりましたが文字列が残っています。";
/*     */       case 1101:
/* 247 */         return "関数として使用できません。";
/*     */       case 2001:
/* 249 */         return "禁止されているメソッドを呼び出しました。";
/*     */       case 2002:
/* 251 */         return "変数として使用できません。";
/*     */       case 2003:
/* 253 */         return "数値として使用できません。";
/*     */       case 2004:
/* 255 */         return "代入できません。";
/*     */       case 2101:
/* 257 */         return "変数の値が取得できません。";
/*     */       case 2102:
/* 259 */         return "変数に代入できません。";
/*     */       case 2103:
/* 261 */         return "変数が未定義です。";
/*     */       case 2104:
/* 263 */         return "オブジェクトが未定義です。";
/*     */       case 2201:
/* 265 */         return "配列の値が取得できません。";
/*     */       case 2202:
/* 267 */         return "配列に代入できません。";
/*     */       case 2301:
/* 269 */         return "フィールドの値が取得できません。";
/*     */       case 2302:
/* 271 */         return "フィールドに代入できません。";
/*     */       case 2401:
/* 273 */         return "関数の呼び出しに失敗しました。";
/*     */     } 
/* 275 */     return "エラーが発生しました。";
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
/*     */   public String getDefaultFormat(String msgFmt) {
/* 289 */     StringBuffer fmt = new StringBuffer(128);
/* 290 */     fmt.append(msgFmt);
/*     */     
/* 292 */     boolean bWord = false;
/* 293 */     if (this.word != null && this.word.length() > 0) {
/* 294 */       bWord = true;
/* 295 */       if (this.word.equals(this.string))
/* 296 */         bWord = false; 
/*     */     } 
/* 298 */     if (bWord) {
/* 299 */       fmt.append(" word=「%w」");
/*     */     }
/*     */     
/* 302 */     if (this.pos >= 0) {
/* 303 */       fmt.append(" pos=%p");
/*     */     }
/* 305 */     if (this.string != null) {
/* 306 */       fmt.append(" string=「%s」");
/*     */     }
/* 308 */     if (getCause() != null) {
/* 309 */       fmt.append(" cause by %e");
/*     */     }
/*     */     
/* 312 */     return fmt.toString();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 316 */     String msg = getErrCodeMessage(this.msg_code);
/* 317 */     String fmt = getDefaultFormat(msg);
/* 318 */     return toString(fmt);
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
/*     */   public String toString(String fmt) {
/* 362 */     StringBuffer sb = new StringBuffer(256);
/* 363 */     int len = fmt.length();
/* 364 */     for (int i = 0; i < len; i++) {
/* 365 */       char c = fmt.charAt(i);
/* 366 */       if (c != '%') {
/* 367 */         sb.append(c);
/*     */       } else {
/* 369 */         int n; if (i + 1 >= len) {
/* 370 */           sb.append(c);
/*     */           break;
/*     */         } 
/* 373 */         c = fmt.charAt(++i);
/* 374 */         switch (c) {
/*     */           case '0':
/*     */           case '1':
/*     */           case '2':
/*     */           case '3':
/*     */           case '4':
/*     */           case '5':
/*     */           case '6':
/*     */           case '7':
/*     */           case '8':
/*     */           case '9':
/* 385 */             n = c - 48;
/* 386 */             if (this.msg_opt != null && n < this.msg_opt.length) {
/* 387 */               sb.append(this.msg_opt[n]);
/*     */             }
/*     */             break;
/*     */           case 'c':
/* 391 */             sb.append(this.msg_code);
/*     */             break;
/*     */           case 'w':
/* 394 */             sb.append(this.word);
/*     */             break;
/*     */           case 'p':
/* 397 */             sb.append(this.pos);
/*     */             break;
/*     */           case 's':
/* 400 */             sb.append(this.string);
/*     */             break;
/*     */           case 'e':
/* 403 */             sb.append(getCause());
/*     */             break;
/*     */           case '%':
/* 406 */             sb.append('%');
/*     */             break;
/*     */           default:
/* 409 */             sb.append('%');
/* 410 */             sb.append(c);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 415 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\EvalException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */