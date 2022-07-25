/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.EvalException;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ import mcheli.eval.eval.exp.WordExpression;
/*    */ import mcheli.eval.eval.lex.Lex;
/*    */ import mcheli.eval.util.NumberUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NumberExpression
/*    */   extends WordExpression
/*    */ {
/*    */   public static AbstractExpression create(Lex lex, int prio) {
/* 25 */     mcheli.eval.eval.exp.NumberExpression numberExpression = new mcheli.eval.eval.exp.NumberExpression(lex.getWord());
/* 26 */     numberExpression.setPos(lex.getString(), lex.getPos());
/* 27 */     numberExpression.setPriority(prio);
/* 28 */     ((AbstractExpression)numberExpression).share = lex.getShare();
/* 29 */     return (AbstractExpression)numberExpression;
/*    */   }
/*    */   
/*    */   public NumberExpression(String str) {
/* 33 */     super(str);
/*    */   }
/*    */   
/*    */   protected NumberExpression(mcheli.eval.eval.exp.NumberExpression from, ShareExpValue s) {
/* 37 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 41 */     return (AbstractExpression)new mcheli.eval.eval.exp.NumberExpression(this, s);
/*    */   }
/*    */   
/*    */   public static mcheli.eval.eval.exp.NumberExpression create(AbstractExpression from, String word) {
/* 45 */     mcheli.eval.eval.exp.NumberExpression n = new mcheli.eval.eval.exp.NumberExpression(word);
/* 46 */     n.string = from.string;
/* 47 */     n.pos = from.pos;
/* 48 */     n.prio = from.prio;
/* 49 */     n.share = from.share;
/* 50 */     return n;
/*    */   }
/*    */   
/*    */   public long evalLong() {
/*    */     try {
/* 55 */       return NumberUtil.parseLong(this.word);
/* 56 */     } catch (Exception e) {
/*    */       
/*    */       try {
/* 59 */         return Long.parseLong(this.word);
/* 60 */       } catch (Exception exception) {
/*    */         
/*    */         try {
/* 63 */           return (long)Double.parseDouble(this.word);
/* 64 */         } catch (Exception exception1) {
/* 65 */           throw new EvalException(2003, this.word, this.string, this.pos, exception1);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   public double evalDouble() {
/*    */     try {
/* 72 */       return Double.parseDouble(this.word);
/* 73 */     } catch (Exception e) {
/*    */       try {
/* 75 */         return NumberUtil.parseLong(this.word);
/* 76 */       } catch (Exception e2) {
/*    */         
/* 78 */         throw new EvalException(2003, this.word, this.string, this.pos, e);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Object evalObject() {
/*    */     try {
/* 85 */       return new Long(NumberUtil.parseLong(this.word));
/* 86 */     } catch (Exception e) {
/*    */       
/*    */       try {
/* 89 */         return Long.valueOf(this.word);
/* 90 */       } catch (Exception exception) {
/*    */         
/*    */         try {
/* 93 */           return Double.valueOf(this.word);
/* 94 */         } catch (Exception exception1) {
/* 95 */           throw new EvalException(2003, this.word, this.string, this.pos, exception1);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\NumberExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */