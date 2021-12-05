/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Col2OpeExpression
/*    */   extends Col2Expression
/*    */ {
/*    */   protected Col2OpeExpression() {}
/*    */   
/*    */   protected Col2OpeExpression(Col2Expression from, ShareExpValue s) {
/* 19 */     super(from, s);
/*    */   }
/*    */   
/*    */   protected final long operateLong(long vl, long vr) {
/* 23 */     throw new RuntimeException("この関数が呼ばれてはいけない");
/*    */   }
/*    */   
/*    */   protected final double operateDouble(double vl, double vr) {
/* 27 */     throw new RuntimeException("この関数が呼ばれてはいけない");
/*    */   }
/*    */   
/*    */   protected final Object operateObject(Object vl, Object vr) {
/* 31 */     throw new RuntimeException("この関数が呼ばれてはいけない");
/*    */   }
/*    */   
/*    */   protected AbstractExpression replace() {
/* 35 */     this.expl = this.expl.replace();
/* 36 */     this.expr = this.expr.replace();
/* 37 */     return this.share.repl.replace2(this);
/*    */   }
/*    */   
/*    */   protected AbstractExpression replaceVar() {
/* 41 */     this.expl = this.expl.replaceVar();
/* 42 */     this.expr = this.expr.replaceVar();
/* 43 */     return this.share.repl.replaceVar2(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\Col2OpeExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */