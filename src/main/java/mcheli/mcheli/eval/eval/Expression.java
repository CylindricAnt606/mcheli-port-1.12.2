/*     */ package mcheli.mcheli.eval.eval;
/*     */ 
/*     */ import mcheli.eval.eval.ExpRuleFactory;
/*     */ import mcheli.eval.eval.Rule;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.func.Function;
/*     */ import mcheli.eval.eval.oper.Operator;
/*     */ import mcheli.eval.eval.ref.Refactor;
/*     */ import mcheli.eval.eval.repl.Replace;
/*     */ import mcheli.eval.eval.srch.Search;
/*     */ import mcheli.eval.eval.var.Variable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Expression
/*     */ {
/*     */   public Variable var;
/*     */   public Function func;
/*     */   public Operator oper;
/*     */   public Search srch;
/*     */   public Replace repl;
/*     */   protected AbstractExpression ae;
/*     */   
/*     */   public static mcheli.eval.eval.Expression parse(String str) {
/*  51 */     return ExpRuleFactory.getDefaultRule().parse(str);
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
/*     */   public void setVariable(Variable var) {
/*  68 */     this.var = var;
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
/*     */   public void setFunction(Function func) {
/*  84 */     this.func = func;
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
/*     */   public void setOperator(Operator oper) {
/*  99 */     this.oper = oper;
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
/*     */   public abstract long evalLong();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract double evalDouble();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Object eval();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void optimizeLong(Variable paramVariable);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void optimizeDouble(Variable paramVariable);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void optimize(Variable paramVariable, Operator paramOperator);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void search(Search paramSearch);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void refactorName(Refactor paramRefactor);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void refactorFunc(Refactor paramRefactor, Rule paramRule);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract mcheli.eval.eval.Expression dup();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 264 */     if (obj instanceof mcheli.eval.eval.Expression) {
/* 265 */       AbstractExpression e = ((mcheli.eval.eval.Expression)obj).ae;
/* 266 */       if (this.ae == null && e == null)
/* 267 */         return true; 
/* 268 */       if (this.ae == null || e == null)
/* 269 */         return false; 
/* 270 */       return this.ae.equals(e);
/*     */     } 
/* 272 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 282 */     if (this.ae == null)
/* 283 */       return 0; 
/* 284 */     return this.ae.hashCode();
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
/*     */   public boolean same(mcheli.eval.eval.Expression obj) {
/* 300 */     AbstractExpression e = obj.ae;
/* 301 */     if (this.ae == null) {
/* 302 */       return (e == null);
/*     */     }
/* 304 */     return this.ae.same(e);
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
/*     */   public boolean isEmpty() {
/* 317 */     return (this.ae == null);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 321 */     if (this.ae == null)
/* 322 */       return ""; 
/* 323 */     return this.ae.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\Expression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */