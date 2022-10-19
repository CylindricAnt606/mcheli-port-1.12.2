/*     */ package mcheli.mcheli.eval.eval.exp;
/*     */ 
/*     */ import mcheli.eval.eval.Expression;
/*     */ import mcheli.eval.eval.Rule;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.OptimizeDouble;
/*     */ import mcheli.eval.eval.exp.OptimizeLong;
/*     */ import mcheli.eval.eval.exp.OptimizeObject;
/*     */ import mcheli.eval.eval.exp.Replace4RefactorGetter;
/*     */ import mcheli.eval.eval.exp.Search4RefactorName;
/*     */ import mcheli.eval.eval.func.Function;
/*     */ import mcheli.eval.eval.func.InvokeFunction;
/*     */ import mcheli.eval.eval.oper.JavaExOperator;
/*     */ import mcheli.eval.eval.oper.Operator;
/*     */ import mcheli.eval.eval.ref.Refactor;
/*     */ import mcheli.eval.eval.repl.Replace;
/*     */ import mcheli.eval.eval.srch.Search;
/*     */ import mcheli.eval.eval.var.MapVariable;
/*     */ import mcheli.eval.eval.var.Variable;
/*     */ 
/*     */ public class ShareExpValue extends Expression {
/*     */   public AbstractExpression paren;
/*     */   
/*     */   public void setAbstractExpression(AbstractExpression ae) {
/*  25 */     this.ae = ae;
/*     */   }
/*     */   
/*     */   public void initVar() {
/*  29 */     if (this.var == null) {
/*  30 */       this.var = (Variable)new MapVariable();
/*     */     }
/*     */   }
/*     */   
/*     */   public void initOper() {
/*  35 */     if (this.oper == null) {
/*  36 */       this.oper = (Operator)new JavaExOperator();
/*     */     }
/*     */   }
/*     */   
/*     */   public void initFunc() {
/*  41 */     if (this.func == null)
/*     */     {
/*  43 */       this.func = (Function)new InvokeFunction();
/*     */     }
/*     */   }
/*     */   
/*     */   public long evalLong() {
/*  48 */     initVar();
/*  49 */     initFunc();
/*  50 */     return this.ae.evalLong();
/*     */   }
/*     */   
/*     */   public double evalDouble() {
/*  54 */     initVar();
/*  55 */     initFunc();
/*  56 */     return this.ae.evalDouble();
/*     */   }
/*     */   
/*     */   public Object eval() {
/*  60 */     initVar();
/*  61 */     initOper();
/*  62 */     initFunc();
/*  63 */     return this.ae.evalObject();
/*     */   }
/*     */   
/*     */   public void optimizeLong(Variable var) {
/*  67 */     optimize(var, (Replace)new OptimizeLong());
/*     */   }
/*     */   
/*     */   public void optimizeDouble(Variable var) {
/*  71 */     optimize(var, (Replace)new OptimizeDouble());
/*     */   }
/*     */   
/*     */   public void optimize(Variable var, Operator oper) {
/*  75 */     Operator bak = this.oper;
/*  76 */     this.oper = oper;
/*     */     try {
/*  78 */       optimize(var, (Replace)new OptimizeObject());
/*     */     } finally {
/*  80 */       this.oper = bak;
/*     */     } 
/*     */   }
/*     */   protected void optimize(Variable var, Replace repl) {
/*     */     MapVariable mapVariable;
/*  85 */     Variable bak = this.var;
/*  86 */     if (var == null) {
/*  87 */       mapVariable = new MapVariable();
/*     */     }
/*  89 */     this.var = (Variable)mapVariable;
/*  90 */     this.repl = repl;
/*     */     try {
/*  92 */       this.ae = this.ae.replace();
/*     */     } finally {
/*  94 */       this.var = bak;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void search(Search srch) {
/*  99 */     if (srch == null) {
/* 100 */       throw new NullPointerException();
/*     */     }
/* 102 */     this.srch = srch;
/* 103 */     this.ae.search();
/*     */   }
/*     */   
/*     */   public void refactorName(Refactor ref) {
/* 107 */     if (ref == null) {
/* 108 */       throw new NullPointerException();
/*     */     }
/* 110 */     this.srch = (Search)new Search4RefactorName(ref);
/* 111 */     this.ae.search();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void refactorFunc(Refactor ref, Rule rule) {
/* 117 */     if (ref == null) {
/* 118 */       throw new NullPointerException();
/*     */     }
/* 120 */     this.repl = (Replace)new Replace4RefactorGetter(ref, rule);
/* 121 */     this.ae.replace();
/*     */   }
/*     */   
/*     */   public boolean same(Expression obj) {
/* 125 */     if (obj instanceof mcheli.eval.eval.exp.ShareExpValue) {
/* 126 */       AbstractExpression p = ((mcheli.eval.eval.exp.ShareExpValue)obj).paren;
/* 127 */       return (this.paren.same(p) && super.same(obj));
/*     */     } 
/* 129 */     return false;
/*     */   }
/*     */   
/*     */   public Expression dup() {
/* 133 */     mcheli.eval.eval.exp.ShareExpValue n = new mcheli.eval.eval.exp.ShareExpValue();
/* 134 */     n.ae = this.ae.dup(n);
/* 135 */     n.paren = this.paren.dup(n);
/* 136 */     return n;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\ShareExpValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */