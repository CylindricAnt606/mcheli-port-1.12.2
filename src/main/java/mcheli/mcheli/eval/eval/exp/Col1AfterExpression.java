/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col1Expression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Col1AfterExpression
/*    */   extends Col1Expression
/*    */ {
/*    */   protected Col1AfterExpression() {}
/*    */   
/*    */   protected Col1AfterExpression(Col1Expression from, ShareExpValue s) {
/* 16 */     super(from, s);
/*    */   }
/*    */   
/*    */   protected AbstractExpression replace() {
/* 20 */     this.exp = this.exp.replaceVar();
/* 21 */     return this.share.repl.replaceVar1(this);
/*    */   }
/*    */   
/*    */   protected AbstractExpression replaceVar() {
/* 25 */     return replace();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 29 */     if (this.exp == null) {
/* 30 */       return getOperator();
/*    */     }
/* 32 */     StringBuffer sb = new StringBuffer();
/* 33 */     if (this.exp.getPriority() > this.prio) {
/* 34 */       sb.append(this.exp.toString());
/* 35 */       sb.append(getOperator());
/* 36 */     } else if (this.exp.getPriority() == this.prio) {
/* 37 */       sb.append(this.exp.toString());
/* 38 */       sb.append(' ');
/* 39 */       sb.append(getOperator());
/*    */     } else {
/* 41 */       sb.append(this.share.paren.getOperator());
/* 42 */       sb.append(this.exp.toString());
/* 43 */       sb.append(this.share.paren.getEndOperator());
/* 44 */       sb.append(getOperator());
/*    */     } 
/* 46 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\Col1AfterExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */