/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.Rule;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.Col2OpeExpression;
/*    */ import mcheli.eval.eval.exp.FieldExpression;
/*    */ import mcheli.eval.eval.exp.VariableExpression;
/*    */ import mcheli.eval.eval.exp.WordExpression;
/*    */ import mcheli.eval.eval.ref.Refactor;
/*    */ import mcheli.eval.eval.repl.ReplaceAdapter;
/*    */ import mcheli.eval.eval.rule.ShareRuleValue;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Replace4RefactorGetter
/*    */   extends ReplaceAdapter
/*    */ {
/*    */   protected Refactor ref;
/*    */   protected ShareRuleValue rule;
/*    */   
/*    */   Replace4RefactorGetter(Refactor ref, Rule rule) {
/* 22 */     this.ref = ref;
/* 23 */     this.rule = (ShareRuleValue)rule;
/*    */   }
/*    */   
/*    */   protected AbstractExpression var(VariableExpression exp) {
/* 27 */     String name = this.ref.getNewName(null, exp.getWord());
/* 28 */     if (name == null) {
/* 29 */       return (AbstractExpression)exp;
/*    */     }
/* 31 */     return this.rule.parse(name, exp.share);
/*    */   }
/*    */   
/*    */   protected AbstractExpression field(FieldExpression exp) {
/* 35 */     AbstractExpression exp1 = exp.expl;
/* 36 */     Object obj = exp1.getVariable();
/* 37 */     if (obj == null) {
/* 38 */       return (AbstractExpression)exp;
/*    */     }
/* 40 */     AbstractExpression exp2 = exp.expr;
/* 41 */     String name = this.ref.getNewName(obj, exp2.getWord());
/* 42 */     if (name == null) {
/* 43 */       return (AbstractExpression)exp;
/*    */     }
/* 45 */     exp.expr = this.rule.parse(name, exp2.share);
/* 46 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replace0(WordExpression exp) {
/* 50 */     if (exp instanceof VariableExpression)
/* 51 */       return var((VariableExpression)exp); 
/* 52 */     return (AbstractExpression)exp;
/*    */   }
/*    */   
/*    */   public AbstractExpression replace2(Col2OpeExpression exp) {
/* 56 */     if (exp instanceof FieldExpression)
/* 57 */       return field((FieldExpression)exp); 
/* 58 */     return (AbstractExpression)exp;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\Replace4RefactorGetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */