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
/*     */ 
/*     */ public abstract class Col2Expression
/*     */   extends AbstractExpression
/*     */ {
/*     */   public AbstractExpression expl;
/*     */   public AbstractExpression expr;
/*     */   
/*     */   public static AbstractExpression create(AbstractExpression exp, String string, int pos, AbstractExpression x, AbstractExpression y) {
/*  29 */     mcheli.eval.eval.exp.Col2Expression n = (mcheli.eval.eval.exp.Col2Expression)exp;
/*  30 */     n.setExpression(x, y);
/*  31 */     n.setPos(string, pos);
/*  32 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Col2Expression() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Col2Expression(mcheli.eval.eval.exp.Col2Expression from, ShareExpValue s) {
/*  45 */     super(from, s);
/*  46 */     if (from.expl != null)
/*  47 */       this.expl = from.expl.dup(s); 
/*  48 */     if (from.expr != null) {
/*  49 */       this.expr = from.expr.dup(s);
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
/*     */   public final void setExpression(AbstractExpression x, AbstractExpression y) {
/*  61 */     this.expl = x;
/*  62 */     this.expr = y;
/*     */   }
/*     */   
/*     */   protected final int getCols() {
/*  66 */     return 2;
/*     */   }
/*     */   
/*     */   protected final int getFirstPos() {
/*  70 */     return this.expl.getFirstPos();
/*     */   }
/*     */   
/*     */   public long evalLong() {
/*  74 */     return operateLong(this.expl.evalLong(), this.expr.evalLong());
/*     */   }
/*     */   
/*     */   public double evalDouble() {
/*  78 */     return operateDouble(this.expl.evalDouble(), this.expr.evalDouble());
/*     */   }
/*     */   
/*     */   public Object evalObject() {
/*  82 */     return operateObject(this.expl.evalObject(), this.expr.evalObject());
/*     */   }
/*     */   
/*     */   protected abstract long operateLong(long paramLong1, long paramLong2);
/*     */   
/*     */   protected abstract double operateDouble(double paramDouble1, double paramDouble2);
/*     */   
/*     */   protected abstract Object operateObject(Object paramObject1, Object paramObject2);
/*     */   
/*     */   protected void search() {
/*  92 */     this.share.srch.search(this);
/*  93 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/*  96 */     if (this.share.srch.search2_begin(this))
/*     */       return; 
/*  98 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 101 */     this.expl.search();
/* 102 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 105 */     if (this.share.srch.search2_2(this))
/*     */       return; 
/* 107 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 110 */     this.expr.search();
/* 111 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 114 */     this.share.srch.search2_end(this);
/*     */   }
/*     */   
/*     */   protected AbstractExpression replace() {
/* 118 */     this.expl = this.expl.replace();
/* 119 */     this.expr = this.expr.replace();
/* 120 */     return this.share.repl.replace2(this);
/*     */   }
/*     */   
/*     */   protected AbstractExpression replaceVar() {
/* 124 */     this.expl = this.expl.replaceVar();
/* 125 */     this.expr = this.expr.replaceVar();
/* 126 */     return this.share.repl.replaceVar2(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 130 */     if (obj instanceof mcheli.eval.eval.exp.Col2Expression) {
/* 131 */       mcheli.eval.eval.exp.Col2Expression e = (mcheli.eval.eval.exp.Col2Expression)obj;
/* 132 */       if (getClass() == e.getClass()) {
/* 133 */         return (this.expl.equals(e.expl) && this.expr.equals(e.expr));
/*     */       }
/*     */     } 
/* 136 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 140 */     return getClass().hashCode() ^ this.expl.hashCode() ^ this.expr.hashCode() * 2;
/*     */   }
/*     */   
/*     */   public void dump(int n) {
/* 144 */     StringBuffer sb = new StringBuffer();
/* 145 */     for (int i = 0; i < n; i++) {
/* 146 */       sb.append(' ');
/*     */     }
/* 148 */     sb.append(getOperator());
/* 149 */     System.out.println(sb.toString());
/* 150 */     this.expl.dump(n + 1);
/* 151 */     this.expr.dump(n + 1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 155 */     StringBuffer sb = new StringBuffer();
/* 156 */     if (this.expl.getPriority() < this.prio) {
/* 157 */       sb.append(this.share.paren.getOperator());
/* 158 */       sb.append(this.expl.toString());
/* 159 */       sb.append(this.share.paren.getEndOperator());
/*     */     } else {
/* 161 */       sb.append(this.expl.toString());
/*     */     } 
/* 163 */     sb.append(toStringLeftSpace());
/* 164 */     sb.append(getOperator());
/* 165 */     sb.append(' ');
/* 166 */     if (this.expr.getPriority() < this.prio) {
/* 167 */       sb.append(this.share.paren.getOperator());
/* 168 */       sb.append(this.expr.toString());
/* 169 */       sb.append(this.share.paren.getEndOperator());
/*     */     } else {
/* 171 */       sb.append(this.expr.toString());
/*     */     } 
/* 173 */     return sb.toString();
/*     */   }
/*     */   
/*     */   protected String toStringLeftSpace() {
/* 177 */     return " ";
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\Col2Expression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */