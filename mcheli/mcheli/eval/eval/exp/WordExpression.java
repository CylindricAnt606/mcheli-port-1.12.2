/*    */ package mcheli.mcheli.eval.eval.exp;
/*    */ 
/*    */ import mcheli.eval.eval.exp.AbstractExpression;
/*    */ import mcheli.eval.eval.exp.ShareExpValue;
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
/*    */ public abstract class WordExpression
/*    */   extends AbstractExpression
/*    */ {
/*    */   protected String word;
/*    */   
/*    */   protected WordExpression(String str) {
/* 21 */     this.word = str;
/*    */   }
/*    */   
/*    */   protected WordExpression(mcheli.eval.eval.exp.WordExpression from, ShareExpValue s) {
/* 25 */     super(from, s);
/* 26 */     this.word = from.word;
/*    */   }
/*    */   
/*    */   protected String getWord() {
/* 30 */     return this.word;
/*    */   }
/*    */   
/*    */   protected void setWord(String word) {
/* 34 */     this.word = word;
/*    */   }
/*    */   
/*    */   protected int getCols() {
/* 38 */     return 0;
/*    */   }
/*    */   
/*    */   protected int getFirstPos() {
/* 42 */     return this.pos;
/*    */   }
/*    */   
/*    */   protected void search() {
/* 46 */     this.share.srch.search(this);
/* 47 */     if (this.share.srch.end())
/*    */       return; 
/* 49 */     this.share.srch.search0(this);
/*    */   }
/*    */   
/*    */   protected AbstractExpression replace() {
/* 53 */     return this.share.repl.replace0(this);
/*    */   }
/*    */   
/*    */   protected AbstractExpression replaceVar() {
/* 57 */     return this.share.repl.replaceVar0(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 61 */     if (obj instanceof mcheli.eval.eval.exp.WordExpression) {
/* 62 */       mcheli.eval.eval.exp.WordExpression e = (mcheli.eval.eval.exp.WordExpression)obj;
/* 63 */       if (getClass() == e.getClass()) {
/* 64 */         return this.word.equals(e.word);
/*    */       }
/*    */     } 
/* 67 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 71 */     return this.word.hashCode();
/*    */   }
/*    */   
/*    */   public void dump(int n) {
/* 75 */     StringBuffer sb = new StringBuffer();
/* 76 */     for (int i = 0; i < n; i++) {
/* 77 */       sb.append(' ');
/*    */     }
/* 79 */     sb.append(this.word);
/* 80 */     System.out.println(sb.toString());
/*    */   }
/*    */   
/*    */   public String toString() {
/* 84 */     return this.word;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\WordExpression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */