/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.EvalException;
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
/*    */ import mcheli.eval.eval.exp.WordExpression;
/*    */ import mcheli.eval.eval.lex.Lex;
/*    */ import mcheli.eval.util.CharUtil;
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
/*    */ 
/*    */ public class CharExpression
/*    */   extends WordExpression
/*    */ {
/*    */   public static AbstractExpression create(Lex lex, int prio) {
/* 26 */     String str = lex.getWord();
/* 27 */     str = CharUtil.escapeString(str, 1, str.length() - 2);
/* 28 */     mcheli.eval.eval.exp.CharExpression charExpression = new mcheli.eval.eval.exp.CharExpression(str);
/* 29 */     charExpression.setPos(lex.getString(), lex.getPos());
/* 30 */     charExpression.setPriority(prio);
/* 31 */     ((AbstractExpression)charExpression).share = lex.getShare();
/* 32 */     return (AbstractExpression)charExpression;
/*    */   }
/*    */   
/*    */   public CharExpression(String str) {
/* 36 */     super(str);
/* 37 */     setOperator("'");
/* 38 */     setEndOperator("'");
/*    */   }
/*    */   
/*    */   protected CharExpression(mcheli.eval.eval.exp.CharExpression from, ShareExpValue s) {
/* 42 */     super(from, s);
/*    */   }
/*    */   
/*    */   public AbstractExpression dup(ShareExpValue s) {
/* 46 */     return (AbstractExpression)new mcheli.eval.eval.exp.CharExpression(this, s);
/*    */   }
/*    */   
/*    */   public static mcheli.eval.eval.exp.CharExpression create(AbstractExpression from, String word) {
/* 50 */     mcheli.eval.eval.exp.CharExpression n = new mcheli.eval.eval.exp.CharExpression(word);
/* 51 */     n.string = from.string;
/* 52 */     n.pos = from.pos;
/* 53 */     n.prio = from.prio;
/* 54 */     n.share = from.share;
/* 55 */     return n;
/*    */   }
/*    */   
/*    */   public long evalLong() {
/*    */     try {
/* 60 */       return this.word.charAt(0);
/* 61 */     } catch (Exception e) {
/* 62 */       throw new EvalException(2003, this.word, this.string, this.pos, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public double evalDouble() {
/*    */     try {
/* 69 */       return this.word.charAt(0);
/* 70 */     } catch (Exception e) {
/* 71 */       throw new EvalException(2003, this.word, this.string, this.pos, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Object evalObject() {
/* 77 */     return new Character(this.word.charAt(0));
/*    */   }
/*    */   
/*    */   public String toString() {
/* 81 */     StringBuffer sb = new StringBuffer();
/* 82 */     sb.append(getOperator());
/* 83 */     sb.append(this.word);
/* 84 */     sb.append(getEndOperator());
/* 85 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\CharExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */