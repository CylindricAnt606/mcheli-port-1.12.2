/*     */ package mcheli.mcheli.eval.eval.exp;
/*     */ 
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Col3Expression
/*     */   extends AbstractExpression
/*     */ {
/*     */   protected AbstractExpression exp1;
/*     */   protected AbstractExpression exp2;
/*     */   protected AbstractExpression exp3;
/*     */   
/*     */   public static AbstractExpression create(AbstractExpression exp, String string, int pos, AbstractExpression x, AbstractExpression y, AbstractExpression z) {
/*  32 */     mcheli.eval.eval.exp.Col3Expression n = (mcheli.eval.eval.exp.Col3Expression)exp;
/*  33 */     n.setExpression(x, y, z);
/*  34 */     n.setPos(string, pos);
/*  35 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Col3Expression() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Col3Expression(mcheli.eval.eval.exp.Col3Expression from, ShareExpValue s) {
/*  51 */     super(from, s);
/*  52 */     if (from.exp1 != null)
/*  53 */       this.exp1 = from.exp1.dup(s); 
/*  54 */     if (from.exp2 != null)
/*  55 */       this.exp2 = from.exp2.dup(s); 
/*  56 */     if (from.exp3 != null) {
/*  57 */       this.exp3 = from.exp3.dup(s);
/*     */     }
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
/*     */   public final void setExpression(AbstractExpression x, AbstractExpression y, AbstractExpression z) {
/*  72 */     this.exp1 = x;
/*  73 */     this.exp2 = y;
/*  74 */     this.exp3 = z;
/*     */   }
/*     */   
/*     */   protected final int getCols() {
/*  78 */     return 3;
/*     */   }
/*     */   
/*     */   protected int getFirstPos() {
/*  82 */     return this.exp1.getFirstPos();
/*     */   }
/*     */   
/*     */   protected void search() {
/*  86 */     this.share.srch.search(this);
/*  87 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/*  90 */     if (this.share.srch.search3_begin(this))
/*     */       return; 
/*  92 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/*  95 */     this.exp1.search();
/*  96 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/*  99 */     if (this.share.srch.search3_2(this))
/*     */       return; 
/* 101 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 104 */     this.exp2.search();
/* 105 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 108 */     if (this.share.srch.search3_3(this))
/*     */       return; 
/* 110 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 113 */     this.exp3.search();
/* 114 */     if (this.share.srch.end()) {
/*     */       return;
/*     */     }
/* 117 */     this.share.srch.search3_end(this);
/*     */   }
/*     */   
/*     */   protected AbstractExpression replace() {
/* 121 */     this.exp1 = this.exp1.replace();
/* 122 */     this.exp2 = this.exp2.replace();
/* 123 */     this.exp3 = this.exp3.replace();
/* 124 */     return this.share.repl.replace3(this);
/*     */   }
/*     */   
/*     */   protected AbstractExpression replaceVar() {
/* 128 */     this.exp1 = this.exp1.replace();
/* 129 */     this.exp2 = this.exp2.replaceVar();
/* 130 */     this.exp3 = this.exp3.replaceVar();
/* 131 */     return this.share.repl.replaceVar3(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 135 */     if (obj instanceof mcheli.eval.eval.exp.Col3Expression) {
/* 136 */       mcheli.eval.eval.exp.Col3Expression e = (mcheli.eval.eval.exp.Col3Expression)obj;
/* 137 */       if (getClass() == e.getClass()) {
/* 138 */         return (this.exp1.equals(e.exp1) && this.exp2.equals(e.exp2) && this.exp3.equals(e.exp3));
/*     */       }
/*     */     } 
/*     */     
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 146 */     return getClass().hashCode() ^ this.exp1.hashCode() ^ this.exp2.hashCode() * 2 ^ this.exp3.hashCode() * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void dump(int n) {
/* 151 */     StringBuffer sb = new StringBuffer();
/* 152 */     for (int i = 0; i < n; i++) {
/* 153 */       sb.append(' ');
/*     */     }
/* 155 */     sb.append(getOperator());
/* 156 */     System.out.println(sb.toString());
/* 157 */     this.exp1.dump(n + 1);
/* 158 */     this.exp2.dump(n + 1);
/* 159 */     this.exp3.dump(n + 1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 163 */     StringBuffer sb = new StringBuffer();
/* 164 */     if (this.exp1.getPriority() <= this.prio || this.exp1.getCols() >= 2) {
/* 165 */       sb.append(this.share.paren.getOperator());
/* 166 */       sb.append(this.exp1.toString());
/* 167 */       sb.append(this.share.paren.getEndOperator());
/*     */     } else {
/* 169 */       sb.append(this.exp1.toString());
/*     */     } 
/* 171 */     sb.append(' ');
/* 172 */     sb.append(getOperator());
/* 173 */     sb.append(' ');
/* 174 */     if (this.exp2.getPriority() <= this.prio || this.exp2.getCols() >= 2) {
/* 175 */       sb.append(this.share.paren.getOperator());
/* 176 */       sb.append(this.exp2.toString());
/* 177 */       sb.append(this.share.paren.getEndOperator());
/*     */     } else {
/* 179 */       sb.append(this.exp2.toString());
/*     */     } 
/* 181 */     sb.append(' ');
/* 182 */     sb.append(getEndOperator());
/* 183 */     sb.append(' ');
/* 184 */     if (this.exp3.getPriority() <= this.prio || this.exp3.getCols() >= 2) {
/* 185 */       sb.append(this.share.paren.getOperator());
/* 186 */       sb.append(this.exp3.toString());
/* 187 */       sb.append(this.share.paren.getEndOperator());
/*     */     } else {
/* 189 */       sb.append(this.exp3.toString());
/*     */     } 
/* 191 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\exp\Col3Expression.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */