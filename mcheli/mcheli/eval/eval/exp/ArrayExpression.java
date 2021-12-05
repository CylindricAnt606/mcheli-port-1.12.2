/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.EvalException;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.Col2OpeExpression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArrayExpression
/*    */   extends Col2OpeExpression
/*    */ {
/*    */   public ArrayExpression() {
/* 15 */     setOperator("[");
/* 16 */     setEndOperator("]");
/*    */   }
/*    */   
/*    */   protected ArrayExpression(mcheli.eval.eval.exp.ArrayExpression from, ShareExpValue s) {
/* 20 */     super((Col2Expression)from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 24 */     return (AbstractExpression)new mcheli.eval.eval.exp.ArrayExpression(this, s);
/*    */   }
/*    */   
/*    */   public long evalLong() {
/*    */     try {
/* 29 */       return this.share.var.evalLong(getVariable());
/* 30 */     } catch (EvalException e) {
/* 31 */       throw e;
/* 32 */     } catch (Exception e) {
/* 33 */       throw new EvalException(2201, toString(), this.string, this.pos, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public double evalDouble() {
/*    */     try {
/* 40 */       return this.share.var.evalDouble(getVariable());
/* 41 */     } catch (EvalException e) {
/* 42 */       throw e;
/* 43 */     } catch (Exception e) {
/* 44 */       throw new EvalException(2201, toString(), this.string, this.pos, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Object evalObject() {
/* 50 */     return getVariable();
/*    */   }
/*    */   
/*    */   protected Object getVariable() {
/* 54 */     Object obj = this.expl.getVariable();
/* 55 */     if (obj == null) {
/* 56 */       throw new EvalException(2104, this.expl.toString(), this.string, this.pos, null);
/*    */     }
/*    */     
/* 59 */     int index = (int)this.expr.evalLong();
/*    */     try {
/* 61 */       return this.share.var.getObject(obj, index);
/* 62 */     } catch (EvalException e) {
/* 63 */       throw e;
/* 64 */     } catch (Exception e) {
/* 65 */       throw new EvalException(2201, toString(), this.string, this.pos, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void let(Object val, int pos) {
/* 71 */     Object obj = this.expl.getVariable();
/* 72 */     if (obj == null) {
/* 73 */       throw new EvalException(2104, this.expl.toString(), this.string, pos, null);
/*    */     }
/*    */     
/* 76 */     int index = (int)this.expr.evalLong();
/*    */     try {
/* 78 */       this.share.var.setValue(obj, index, val);
/* 79 */     } catch (EvalException e) {
/* 80 */       throw e;
/* 81 */     } catch (Exception e) {
/* 82 */       throw new EvalException(2202, toString(), this.string, pos, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected AbstractExpression replaceVar() {
/* 88 */     this.expl = this.expl.replaceVar();
/* 89 */     this.expr = this.expr.replace();
/* 90 */     return this.share.repl.replaceVar2(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 94 */     StringBuffer sb = new StringBuffer();
/* 95 */     sb.append(this.expl.toString());
/* 96 */     sb.append('[');
/* 97 */     sb.append(this.expr.toString());
/* 98 */     sb.append(']');
/* 99 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\ArrayExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */