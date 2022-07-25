/*     */ package mcheli.mcheli.eval.eval.exp;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mcheli.eval.eval.EvalException;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.Col1Expression;
/*     */ import mcheli.eval.eval.exp.FieldExpression;
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
/*     */ public class FunctionExpression
/*     */   extends Col1Expression
/*     */ {
/*     */   protected AbstractExpression target;
/*     */   String name;
/*     */   
/*     */   public static AbstractExpression create(AbstractExpression x, AbstractExpression args, int prio, ShareExpValue share) {
/*     */     AbstractExpression obj;
/*  31 */     if (x instanceof mcheli.eval.eval.exp.VariableExpression) {
/*  32 */       obj = null;
/*  33 */     } else if (x instanceof FieldExpression) {
/*  34 */       FieldExpression fieldExpression = (FieldExpression)x;
/*  35 */       obj = fieldExpression.expl;
/*  36 */       x = fieldExpression.expr;
/*     */     } else {
/*  38 */       throw new EvalException(1101, x.toString(), x.string, x.pos, null);
/*     */     } 
/*     */     
/*  41 */     String name = x.getWord();
/*  42 */     mcheli.eval.eval.exp.FunctionExpression f = new mcheli.eval.eval.exp.FunctionExpression(obj, name);
/*  43 */     f.setExpression(args);
/*  44 */     f.setPos(x.string, x.pos);
/*  45 */     f.setPriority(prio);
/*  46 */     f.share = share;
/*  47 */     return (AbstractExpression)f;
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
/*     */   public FunctionExpression() {
/*  64 */     setOperator("(");
/*  65 */     setEndOperator(")");
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
/*     */   public FunctionExpression(AbstractExpression obj, String word) {
/*  77 */     this();
/*  78 */     this.target = obj;
/*  79 */     this.name = word;
/*     */   }
/*     */   
/*     */   protected FunctionExpression(mcheli.eval.eval.exp.FunctionExpression from, ShareExpValue s) {
/*  83 */     super(from, s);
/*  84 */     if (from.target != null) {
/*  85 */       this.target = from.target.dup(s);
/*     */     }
/*  87 */     this.name = from.name;
/*     */   }
/*     */   
/*     */   public AbstractExpression dup(ShareExpValue s) {
/*  91 */     return (AbstractExpression)new mcheli.eval.eval.exp.FunctionExpression(this, s);
/*     */   }
/*     */   
/*     */   public long evalLong() {
/*  95 */     Object obj = null;
/*  96 */     if (this.target != null) {
/*  97 */       obj = this.target.getVariable();
/*     */     }
/*  99 */     List args = evalArgsLong();
/*     */     try {
/* 101 */       Long[] arr = new Long[args.size()];
/* 102 */       return this.share.func.evalLong(obj, this.name, (Long[])args.toArray((Object[])arr));
/* 103 */     } catch (EvalException e) {
/* 104 */       throw e;
/* 105 */     } catch (Throwable e) {
/* 106 */       throw new EvalException(2401, this.name, this.string, this.pos, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double evalDouble() {
/* 112 */     Object obj = null;
/* 113 */     if (this.target != null) {
/* 114 */       obj = this.target.getVariable();
/*     */     }
/* 116 */     List args = evalArgsDouble();
/*     */     try {
/* 118 */       Double[] arr = new Double[args.size()];
/* 119 */       return this.share.func.evalDouble(obj, this.name, (Double[])args.toArray((Object[])arr));
/*     */     }
/* 121 */     catch (EvalException e) {
/* 122 */       throw e;
/* 123 */     } catch (Throwable e) {
/* 124 */       throw new EvalException(2401, this.name, this.string, this.pos, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object evalObject() {
/* 130 */     Object obj = null;
/* 131 */     if (this.target != null) {
/* 132 */       obj = this.target.getVariable();
/*     */     }
/* 134 */     List args = evalArgsObject();
/*     */     try {
/* 136 */       Object[] arr = new Object[args.size()];
/* 137 */       return this.share.func.evalObject(obj, this.name, args.toArray(arr));
/* 138 */     } catch (EvalException e) {
/* 139 */       throw e;
/* 140 */     } catch (Throwable e) {
/* 141 */       throw new EvalException(2401, this.name, this.string, this.pos, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private List evalArgsLong() {
/* 147 */     List args = new ArrayList();
/* 148 */     if (this.exp != null) {
/* 149 */       this.exp.evalArgsLong(args);
/*     */     }
/* 151 */     return args;
/*     */   }
/*     */   
/*     */   private List evalArgsDouble() {
/* 155 */     List args = new ArrayList();
/* 156 */     if (this.exp != null) {
/* 157 */       this.exp.evalArgsDouble(args);
/*     */     }
/* 159 */     return args;
/*     */   }
/*     */   
/*     */   private List evalArgsObject() {
/* 163 */     List args = new ArrayList();
/* 164 */     if (this.exp != null) {
/* 165 */       this.exp.evalArgsObject(args);
/*     */     }
/* 167 */     return args;
/*     */   }
/*     */   
/*     */   protected Object getVariable() {
/* 171 */     return evalObject();
/*     */   }
/*     */   
/*     */   protected long operateLong(long val) {
/* 175 */     throw new RuntimeException("この関数が呼ばれてはいけない。サブクラスで実装要");
/*     */   }
/*     */   
/*     */   protected double operateDouble(double val) {
/* 179 */     throw new RuntimeException("この関数が呼ばれてはいけない。サブクラスで実装要");
/*     */   }
/*     */   
/*     */   protected void search() {
/* 183 */     this.share.srch.search((AbstractExpression)this);
/* 184 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 187 */     if (this.share.srch.searchFunc_begin(this))
/*     */       return; 
/* 189 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 192 */     if (this.target != null) {
/* 193 */       this.target.search();
/* 194 */       if (this.share.srch.end()) {
/*     */         return;
/*     */       }
/*     */     } 
/* 198 */     if (this.share.srch.searchFunc_2(this))
/*     */       return; 
/* 200 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 203 */     if (this.exp != null) {
/* 204 */       this.exp.search();
/* 205 */       if (this.share.srch.end()) {
/*     */         return;
/*     */       }
/*     */     } 
/* 209 */     this.share.srch.searchFunc_end(this);
/*     */   }
/*     */   
/*     */   protected AbstractExpression replace() {
/* 213 */     if (this.target != null) {
/* 214 */       this.target = this.target.replace();
/*     */     }
/* 216 */     if (this.exp != null) {
/* 217 */       this.exp = this.exp.replace();
/*     */     }
/* 219 */     return this.share.repl.replaceFunc(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 223 */     if (obj instanceof mcheli.eval.eval.exp.FunctionExpression) {
/* 224 */       mcheli.eval.eval.exp.FunctionExpression e = (mcheli.eval.eval.exp.FunctionExpression)obj;
/* 225 */       return (this.name.equals(e.name) && equals(this.target, e.target) && equals(this.exp, e.exp));
/*     */     } 
/*     */     
/* 228 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean equals(AbstractExpression e1, AbstractExpression e2) {
/* 232 */     if (e1 == null) {
/* 233 */       return (e2 == null);
/*     */     }
/* 235 */     if (e2 == null)
/* 236 */       return false; 
/* 237 */     return e1.equals(e2);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 241 */     int t = (this.target != null) ? this.target.hashCode() : 0;
/* 242 */     int a = (this.exp != null) ? this.exp.hashCode() : 0;
/* 243 */     return this.name.hashCode() ^ t ^ a * 2;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 247 */     StringBuffer sb = new StringBuffer();
/* 248 */     if (this.target != null) {
/* 249 */       sb.append(this.target.toString());
/* 250 */       sb.append('.');
/*     */     } 
/* 252 */     sb.append(this.name);
/* 253 */     sb.append('(');
/* 254 */     if (this.exp != null) {
/* 255 */       sb.append(this.exp.toString());
/*     */     }
/* 257 */     sb.append(')');
/* 258 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\FunctionExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */