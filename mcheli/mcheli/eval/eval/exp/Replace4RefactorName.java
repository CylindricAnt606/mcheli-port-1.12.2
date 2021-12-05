/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.EvalException;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2Expression;
/*    */ import mcheli.eval.eval.exp.FieldExpression;
/*    */ import mcheli.eval.eval.exp.FunctionExpression;
/*    */ import mcheli.eval.eval.exp.VariableExpression;
/*    */ import mcheli.eval.eval.exp.WordExpression;
/*    */ import mcheli.eval.eval.ref.Refactor;
/*    */ import mcheli.eval.eval.repl.ReplaceAdapter;
/*    */ 
/*    */ 
/*    */ public class Replace4RefactorName
/*    */   extends ReplaceAdapter
/*    */ {
/*    */   protected Refactor ref;
/*    */   
/*    */   Replace4RefactorName(Refactor ref) {
/* 20 */     this.ref = ref;
/*    */   }
/*    */   
/*    */   protected void var(VariableExpression exp) {
/* 24 */     String name = this.ref.getNewName(null, exp.getWord());
/* 25 */     if (name != null) {
/* 26 */       exp.setWord(name);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void field(FieldExpression exp) {
/* 31 */     AbstractExpression exp1 = exp.expl;
/* 32 */     Object obj = exp1.getVariable();
/* 33 */     if (obj == null) {
/* 34 */       throw new EvalException(2104, toString(), exp1.string, exp1.pos, null);
/*    */     }
/*    */     
/* 37 */     AbstractExpression exp2 = exp.expr;
/* 38 */     String name = this.ref.getNewName(obj, exp2.getWord());
/* 39 */     if (name != null) {
/* 40 */       exp2.setWord(name);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void func(FunctionExpression exp) {
/* 45 */     Object obj = null;
/* 46 */     if (exp.target != null) {
/* 47 */       obj = exp.target.getVariable();
/*    */     }
/* 49 */     String name = this.ref.getNewFuncName(obj, exp.name);
/* 50 */     if (name != null) {
/* 51 */       exp.name = name;
/*    */     }
/*    */   }
/*    */   
/*    */   public AbstractExpression replace0(WordExpression exp) {
/* 56 */     if (exp instanceof VariableExpression)
/* 57 */       var((VariableExpression)exp); 
/* 58 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replace2(Col2Expression exp) {
/* 62 */     if (exp instanceof FieldExpression)
/* 63 */       field((FieldExpression)exp); 
/* 64 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replaceFunc(FunctionExpression exp) {
/* 68 */     func(exp);
/* 69 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replaceVar(AbstractExpression exp) {
/* 73 */     if (exp instanceof VariableExpression) {
/* 74 */       var((VariableExpression)exp);
/* 75 */     } else if (exp instanceof FieldExpression) {
/* 76 */       field((FieldExpression)exp);
/* 77 */     } else if (exp instanceof FunctionExpression) {
/* 78 */       func((FunctionExpression)exp);
/* 79 */     }  return exp;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\Replace4RefactorName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */