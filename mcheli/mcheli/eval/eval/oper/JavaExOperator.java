/*     */ package mcheli.mcheli.eval.eval.oper;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import mcheli.eval.eval.oper.Operator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JavaExOperator
/*     */   implements Operator
/*     */ {
/*     */   boolean inLong(Object x) {
/*  19 */     if (x instanceof Long)
/*  20 */       return true; 
/*  21 */     if (x instanceof Integer)
/*  22 */       return true; 
/*  23 */     if (x instanceof Short)
/*  24 */       return true; 
/*  25 */     if (x instanceof Byte)
/*  26 */       return true; 
/*  27 */     if (x instanceof BigInteger)
/*  28 */       return true; 
/*  29 */     if (x instanceof BigDecimal)
/*  30 */       return true; 
/*  31 */     return false;
/*     */   }
/*     */   
/*     */   long l(Object x) {
/*  35 */     return ((Number)x).longValue();
/*     */   }
/*     */   
/*     */   boolean inDouble(Object x) {
/*  39 */     if (x instanceof Double)
/*  40 */       return true; 
/*  41 */     if (x instanceof Float)
/*  42 */       return true; 
/*  43 */     return false;
/*     */   }
/*     */   
/*     */   double d(Object x) {
/*  47 */     return ((Number)x).doubleValue();
/*     */   }
/*     */   
/*     */   Object n(long n, Object x) {
/*  51 */     if (x instanceof Long) {
/*  52 */       return new Long(n);
/*     */     }
/*  54 */     if (x instanceof Double) {
/*  55 */       return new Double(n);
/*     */     }
/*  57 */     if (x instanceof Integer) {
/*  58 */       return new Integer((int)n);
/*     */     }
/*  60 */     if (x instanceof Short) {
/*  61 */       return new Short((short)(int)n);
/*     */     }
/*  63 */     if (x instanceof Byte) {
/*  64 */       return new Byte((byte)(int)n);
/*     */     }
/*  66 */     if (x instanceof Float) {
/*  67 */       return new Float((float)n);
/*     */     }
/*  69 */     if (x instanceof BigInteger) {
/*  70 */       return BigInteger.valueOf(n);
/*     */     }
/*  72 */     if (x instanceof BigDecimal) {
/*  73 */       return BigDecimal.valueOf(n);
/*     */     }
/*  75 */     if (x instanceof String) {
/*  76 */       return String.valueOf(n);
/*     */     }
/*  78 */     return new Long(n);
/*     */   }
/*     */   
/*     */   Object n(long n, Object x, Object y) {
/*  82 */     if (x instanceof Byte || y instanceof Byte) {
/*  83 */       return new Byte((byte)(int)n);
/*     */     }
/*  85 */     if (x instanceof Short || y instanceof Short) {
/*  86 */       return new Short((short)(int)n);
/*     */     }
/*  88 */     if (x instanceof Integer || y instanceof Integer) {
/*  89 */       return new Integer((int)n);
/*     */     }
/*  91 */     if (x instanceof Long || y instanceof Long) {
/*  92 */       return new Long(n);
/*     */     }
/*  94 */     if (x instanceof BigInteger || y instanceof BigInteger) {
/*  95 */       return BigInteger.valueOf(n);
/*     */     }
/*  97 */     if (x instanceof BigDecimal || y instanceof BigDecimal) {
/*  98 */       return BigDecimal.valueOf(n);
/*     */     }
/* 100 */     if (x instanceof Float || y instanceof Float) {
/* 101 */       return new Float((float)n);
/*     */     }
/* 103 */     if (x instanceof Double || y instanceof Double) {
/* 104 */       return new Double(n);
/*     */     }
/* 106 */     if (x instanceof String || y instanceof String) {
/* 107 */       return String.valueOf(n);
/*     */     }
/* 109 */     return new Long(n);
/*     */   }
/*     */   
/*     */   Object n(double n, Object x) {
/* 113 */     if (x instanceof Float) {
/* 114 */       return new Float(n);
/*     */     }
/* 116 */     if (x instanceof String) {
/* 117 */       return String.valueOf(n);
/*     */     }
/* 119 */     return new Double(n);
/*     */   }
/*     */   
/*     */   Object n(double n, Object x, Object y) {
/* 123 */     if (x instanceof Float || y instanceof Float) {
/* 124 */       return new Float(n);
/*     */     }
/* 126 */     if (x instanceof Number || y instanceof Number) {
/* 127 */       return new Double(n);
/*     */     }
/* 129 */     if (x instanceof String || y instanceof String) {
/* 130 */       return String.valueOf(n);
/*     */     }
/* 132 */     return new Double(n);
/*     */   }
/*     */   
/*     */   Object nn(long n, Object x) {
/* 136 */     if (x instanceof BigDecimal) {
/* 137 */       return BigDecimal.valueOf(n);
/*     */     }
/* 139 */     if (x instanceof BigInteger) {
/* 140 */       return BigInteger.valueOf(n);
/*     */     }
/* 142 */     return new Long(n);
/*     */   }
/*     */   
/*     */   Object nn(long n, Object x, Object y) {
/* 146 */     if (x instanceof BigDecimal || y instanceof BigDecimal) {
/* 147 */       return BigDecimal.valueOf(n);
/*     */     }
/* 149 */     if (x instanceof BigInteger || y instanceof BigInteger) {
/* 150 */       return BigInteger.valueOf(n);
/*     */     }
/* 152 */     return new Long(n);
/*     */   }
/*     */   
/*     */   Object nn(double n, Object x) {
/* 156 */     if (inLong(x)) {
/* 157 */       return new Long((long)n);
/*     */     }
/* 159 */     return new Double(n);
/*     */   }
/*     */   
/*     */   Object nn(double n, Object x, Object y) {
/* 163 */     if (inLong(x) && inLong(y)) {
/* 164 */       return new Long((long)n);
/*     */     }
/* 166 */     return new Double(n);
/*     */   }
/*     */   
/*     */   RuntimeException undefined(Object x) {
/* 170 */     String c = null;
/* 171 */     if (x != null)
/* 172 */       c = x.getClass().getName(); 
/* 173 */     return new RuntimeException("未定義単項演算：" + c);
/*     */   }
/*     */   
/*     */   RuntimeException undefined(Object x, Object y) {
/* 177 */     String c1 = null, c2 = null;
/* 178 */     if (x != null)
/* 179 */       c1 = x.getClass().getName(); 
/* 180 */     if (y != null)
/* 181 */       c2 = y.getClass().getName(); 
/* 182 */     return new RuntimeException("未定義二項演算：" + c1 + " , " + c2);
/*     */   }
/*     */   
/*     */   public Object power(Object x, Object y) {
/* 186 */     if (x == null && y == null)
/* 187 */       return null; 
/* 188 */     return nn(Math.pow(d(x), d(y)), x, y);
/*     */   }
/*     */   
/*     */   public Object signPlus(Object x) {
/* 192 */     return x;
/*     */   }
/*     */   
/*     */   public Object signMinus(Object x) {
/* 196 */     if (x == null)
/* 197 */       return null; 
/* 198 */     if (inLong(x)) {
/* 199 */       return n(-l(x), x);
/*     */     }
/* 201 */     if (inDouble(x)) {
/* 202 */       return n(-d(x), x);
/*     */     }
/* 204 */     if (x instanceof Boolean) {
/* 205 */       return x;
/*     */     }
/*     */     
/* 208 */     throw undefined(x);
/*     */   }
/*     */   
/*     */   public Object plus(Object x, Object y) {
/* 212 */     if (x == null && y == null)
/* 213 */       return null; 
/* 214 */     if (inLong(x) && inLong(y)) {
/* 215 */       return nn(l(x) + l(y), x, y);
/*     */     }
/* 217 */     if (inDouble(x) && inDouble(y)) {
/* 218 */       return nn(d(x) + d(y), x, y);
/*     */     }
/* 220 */     if (x instanceof String || y instanceof String) {
/* 221 */       return String.valueOf(x) + String.valueOf(y);
/*     */     }
/* 223 */     if (x instanceof Character || y instanceof Character) {
/* 224 */       return String.valueOf(x) + String.valueOf(y);
/*     */     }
/* 226 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object minus(Object x, Object y) {
/* 230 */     if (x == null && y == null)
/* 231 */       return null; 
/* 232 */     if (inLong(x) && inLong(y)) {
/* 233 */       return nn(l(x) - l(y), x, y);
/*     */     }
/* 235 */     if (inDouble(x) && inDouble(y)) {
/* 236 */       return nn(d(x) - d(y), x, y);
/*     */     }
/* 238 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object mult(Object x, Object y) {
/* 242 */     if (x == null && y == null)
/* 243 */       return null; 
/* 244 */     if (inLong(x) && inLong(y)) {
/* 245 */       return nn(l(x) * l(y), x, y);
/*     */     }
/* 247 */     if (inDouble(x) && inDouble(y)) {
/* 248 */       return nn(d(x) * d(y), x, y);
/*     */     }
/*     */ 
/*     */     
/* 252 */     String s = null;
/* 253 */     int ct = 0;
/* 254 */     boolean str = false;
/* 255 */     if (x instanceof String && y instanceof Number) {
/* 256 */       s = (String)x;
/* 257 */       ct = (int)l(y);
/* 258 */       str = true;
/* 259 */     } else if (y instanceof String && x instanceof Number) {
/* 260 */       s = (String)y;
/* 261 */       ct = (int)l(x);
/* 262 */       str = true;
/*     */     } 
/* 264 */     if (str) {
/* 265 */       StringBuffer sb = new StringBuffer(s.length() * ct);
/* 266 */       for (int i = 0; i < ct; i++) {
/* 267 */         sb.append(s);
/*     */       }
/* 269 */       return sb.toString();
/*     */     } 
/*     */     
/* 272 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object div(Object x, Object y) {
/* 276 */     if (x == null && y == null)
/* 277 */       return null; 
/* 278 */     if (inLong(x) && inLong(y)) {
/* 279 */       return nn(l(x) / l(y), x);
/*     */     }
/* 281 */     if (inDouble(x) && inDouble(y)) {
/* 282 */       return nn(d(x) / d(y), x);
/*     */     }
/*     */ 
/*     */     
/* 286 */     if (x instanceof String && y instanceof String) {
/* 287 */       String s = (String)x;
/* 288 */       String r = (String)y;
/* 289 */       return s.split(r);
/*     */     } 
/*     */     
/* 292 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object mod(Object x, Object y) {
/* 296 */     if (x == null && y == null)
/* 297 */       return null; 
/* 298 */     if (inLong(x) && inLong(y)) {
/* 299 */       return nn(l(x) % l(y), x);
/*     */     }
/* 301 */     if (inDouble(x) && inDouble(y)) {
/* 302 */       return nn(d(x) % d(y), x);
/*     */     }
/* 304 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object bitNot(Object x) {
/* 308 */     if (x == null)
/* 309 */       return null; 
/* 310 */     if (x instanceof Number) {
/* 311 */       return n(l(x) ^ 0xFFFFFFFFFFFFFFFFL, x);
/*     */     }
/* 313 */     throw undefined(x);
/*     */   }
/*     */   
/*     */   public Object shiftLeft(Object x, Object y) {
/* 317 */     if (x == null && y == null)
/* 318 */       return null; 
/* 319 */     if (inLong(x) && inLong(y)) {
/* 320 */       return n(l(x) << (int)l(y), x);
/*     */     }
/* 322 */     if (inDouble(x) && inDouble(y)) {
/* 323 */       return n(d(x) * Math.pow(2.0D, d(y)), x);
/*     */     }
/* 325 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object shiftRight(Object x, Object y) {
/* 329 */     if (x == null && y == null)
/* 330 */       return null; 
/* 331 */     if (inLong(x) && inLong(y)) {
/* 332 */       return n(l(x) >> (int)l(y), x);
/*     */     }
/* 334 */     if (inDouble(x) && inDouble(y)) {
/* 335 */       return n(d(x) / Math.pow(2.0D, d(y)), x);
/*     */     }
/* 337 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object shiftRightLogical(Object x, Object y) {
/* 341 */     if (x == null && y == null)
/* 342 */       return null; 
/* 343 */     if (x instanceof Byte && y instanceof Number) {
/* 344 */       return n((l(x) & 0xFFL) >>> (int)l(y), x);
/*     */     }
/* 346 */     if (x instanceof Short && y instanceof Number) {
/* 347 */       return n((l(x) & 0xFFFFL) >>> (int)l(y), x);
/*     */     }
/* 349 */     if (x instanceof Integer && y instanceof Number) {
/* 350 */       return n((l(x) & 0xFFFFFFFFL) >>> (int)l(y), x);
/*     */     }
/* 352 */     if (inLong(x) && y instanceof Number) {
/* 353 */       return n(l(x) >>> (int)l(y), x);
/*     */     }
/* 355 */     if (inDouble(x) && y instanceof Number) {
/* 356 */       double t = d(x);
/* 357 */       if (t < 0.0D)
/* 358 */         t = -t; 
/* 359 */       return n(t / Math.pow(2.0D, d(y)), x);
/*     */     } 
/* 361 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object bitAnd(Object x, Object y) {
/* 365 */     if (x == null && y == null)
/* 366 */       return null; 
/* 367 */     if (x instanceof Number && y instanceof Number) {
/* 368 */       return n(l(x) & l(y), x);
/*     */     }
/* 370 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object bitOr(Object x, Object y) {
/* 374 */     if (x == null && y == null)
/* 375 */       return null; 
/* 376 */     if (x instanceof Number && y instanceof Number) {
/* 377 */       return n(l(x) | l(y), x);
/*     */     }
/* 379 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object bitXor(Object x, Object y) {
/* 383 */     if (x == null && y == null)
/* 384 */       return null; 
/* 385 */     if (x instanceof Number && y instanceof Number) {
/* 386 */       return n(l(x) ^ l(y), x);
/*     */     }
/* 388 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object not(Object x) {
/* 392 */     if (x == null)
/* 393 */       return null; 
/* 394 */     if (x instanceof Boolean) {
/* 395 */       return ((Boolean)x).booleanValue() ? Boolean.FALSE : Boolean.TRUE;
/*     */     }
/* 397 */     if (x instanceof Number) {
/* 398 */       if (l(x) == 0L) {
/* 399 */         return Boolean.TRUE;
/*     */       }
/* 401 */       return Boolean.FALSE;
/*     */     } 
/*     */     
/* 404 */     throw undefined(x);
/*     */   }
/*     */   
/*     */   int compare(Object x, Object y) {
/* 408 */     if (x == null && y == null)
/* 409 */       return 0; 
/* 410 */     if (x == null && y != null)
/* 411 */       return -1; 
/* 412 */     if (x != null && y == null) {
/* 413 */       return 1;
/*     */     }
/* 415 */     if (inLong(x) && inLong(y)) {
/* 416 */       long c = l(x) - l(y);
/* 417 */       if (c == 0L)
/* 418 */         return 0; 
/* 419 */       if (c < 0L) {
/* 420 */         return -1;
/*     */       }
/* 422 */       return 1;
/*     */     } 
/* 424 */     if (x instanceof Number && y instanceof Number) {
/* 425 */       double n = d(x) - d(y);
/* 426 */       if (n == 0.0D)
/* 427 */         return 0; 
/* 428 */       return (n < 0.0D) ? -1 : 1;
/*     */     } 
/*     */ 
/*     */     
/* 432 */     Class<?> xc = x.getClass();
/* 433 */     Class<?> yc = y.getClass();
/* 434 */     if (xc.isAssignableFrom(yc) && x instanceof Comparable) {
/* 435 */       return ((Comparable<Object>)x).compareTo(y);
/*     */     }
/* 437 */     if (yc.isAssignableFrom(xc) && y instanceof Comparable) {
/* 438 */       return -((Comparable)y).compareTo((T)x);
/*     */     }
/* 440 */     if (x.equals(y))
/* 441 */       return 0; 
/* 442 */     throw undefined(x, y);
/*     */   }
/*     */   
/*     */   public Object equal(Object x, Object y) {
/* 446 */     return (compare(x, y) == 0) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */   
/*     */   public Object notEqual(Object x, Object y) {
/* 450 */     return (compare(x, y) != 0) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */   
/*     */   public Object lessThan(Object x, Object y) {
/* 454 */     return (compare(x, y) < 0) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */   
/*     */   public Object lessEqual(Object x, Object y) {
/* 458 */     return (compare(x, y) <= 0) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */   
/*     */   public Object greaterThan(Object x, Object y) {
/* 462 */     return (compare(x, y) > 0) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */   
/*     */   public Object greaterEqual(Object x, Object y) {
/* 466 */     return (compare(x, y) >= 0) ? Boolean.TRUE : Boolean.FALSE;
/*     */   }
/*     */   
/*     */   public boolean bool(Object x) {
/* 470 */     if (x == null)
/* 471 */       return false; 
/* 472 */     if (x instanceof Boolean)
/* 473 */       return ((Boolean)x).booleanValue(); 
/* 474 */     if (x instanceof Number)
/* 475 */       return (((Number)x).longValue() != 0L); 
/* 476 */     return Boolean.valueOf(x.toString()).booleanValue();
/*     */   }
/*     */   
/*     */   public Object inc(Object x, int inc) {
/* 480 */     if (x == null)
/* 481 */       return null; 
/* 482 */     if (inLong(x)) {
/* 483 */       return n(l(x) + inc, x);
/*     */     }
/* 485 */     if (inDouble(x)) {
/* 486 */       return n(d(x) + inc, x);
/*     */     }
/* 488 */     throw undefined(x);
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\oper\JavaExOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */