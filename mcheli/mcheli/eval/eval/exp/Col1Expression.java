/*     */ package mcheli.mcheli.eval.eval.exp;
/*     */ 
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.ShareExpValue;
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
/*     */ public abstract class Col1Expression
/*     */   extends AbstractExpression
/*     */ {
/*     */   protected AbstractExpression exp;
/*     */   
/*     */   public static AbstractExpression create(AbstractExpression exp, String string, int pos, AbstractExpression x) {
/*  27 */     mcheli.eval.eval.exp.Col1Expression n = (mcheli.eval.eval.exp.Col1Expression)exp;
/*  28 */     n.setExpression(x);
/*  29 */     n.setPos(string, pos);
/*  30 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Col1Expression() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected Col1Expression(mcheli.eval.eval.exp.Col1Expression from, ShareExpValue s) {
/*  40 */     super(from, s);
/*  41 */     if (from.exp != null) {
/*  42 */       this.exp = from.exp.dup(s);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpression(AbstractExpression x) {
/*  52 */     this.exp = x;
/*     */   }
/*     */   
/*     */   protected final int getCols() {
/*  56 */     return 1;
/*     */   }
/*     */   
/*     */   protected final int getFirstPos() {
/*  60 */     return this.exp.getFirstPos();
/*     */   }
/*     */   
/*     */   public long evalLong() {
/*  64 */     return operateLong(this.exp.evalLong());
/*     */   }
/*     */   
/*     */   public double evalDouble() {
/*  68 */     return operateDouble(this.exp.evalDouble());
/*     */   }
/*     */   
/*     */   protected abstract long operateLong(long paramLong);
/*     */   
/*     */   protected abstract double operateDouble(double paramDouble);
/*     */   
/*     */   protected void search() {
/*  76 */     this.share.srch.search(this);
/*  77 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/*  80 */     if (this.share.srch.search1_begin(this))
/*     */       return; 
/*  82 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/*  85 */     this.exp.search();
/*  86 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/*  89 */     this.share.srch.search1_end(this);
/*     */   }
/*     */   
/*     */   protected AbstractExpression replace() {
/*  93 */     this.exp = this.exp.replace();
/*  94 */     return this.share.repl.replace1(this);
/*     */   }
/*     */   
/*     */   protected AbstractExpression replaceVar() {
/*  98 */     this.exp = this.exp.replaceVar();
/*  99 */     return this.share.repl.replaceVar1(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 103 */     if (obj instanceof mcheli.eval.eval.exp.Col1Expression) {
/* 104 */       mcheli.eval.eval.exp.Col1Expression e = (mcheli.eval.eval.exp.Col1Expression)obj;
/* 105 */       if (getClass() == e.getClass()) {
/* 106 */         if (this.exp == null)
/* 107 */           return (e.exp == null); 
/* 108 */         if (e.exp == null)
/* 109 */           return false; 
/* 110 */         return this.exp.equals(e.exp);
/*     */       } 
/*     */     } 
/* 113 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 117 */     return getClass().hashCode() ^ this.exp.hashCode();
/*     */   }
/*     */   
/*     */   public void dump(int n) {
/* 121 */     StringBuffer sb = new StringBuffer();
/* 122 */     for (int i = 0; i < n; i++) {
/* 123 */       sb.append(' ');
/*     */     }
/* 125 */     sb.append(getOperator());
/* 126 */     System.out.println(sb.toString());
/* 127 */     if (this.exp != null) {
/* 128 */       this.exp.dump(n + 1);
/*     */     }
/*     */   }
/*     */   
/*     */   public String toString() {
/* 133 */     if (this.exp == null) {
/* 134 */       return getOperator();
/*     */     }
/* 136 */     StringBuffer sb = new StringBuffer();
/* 137 */     if (this.exp.getPriority() > this.prio) {
/* 138 */       sb.append(getOperator());
/* 139 */       sb.append(this.exp.toString());
/* 140 */     } else if (this.exp.getPriority() == this.prio) {
/* 141 */       sb.append(getOperator());
/* 142 */       sb.append(' ');
/* 143 */       sb.append(this.exp.toString());
/*     */     } else {
/* 145 */       sb.append(getOperator());
/* 146 */       sb.append(this.share.paren.getOperator());
/* 147 */       sb.append(this.exp.toString());
/* 148 */       sb.append(this.share.paren.getEndOperator());
/*     */     } 
/* 150 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\Col1Expression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */