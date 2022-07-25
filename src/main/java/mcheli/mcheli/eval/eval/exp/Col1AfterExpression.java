 package mcheli.mcheli.eval.eval.exp;

 import mcheli.mcheli.eval.eval.exp.AbstractExpression;
 import mcheli.mcheli.eval.eval.exp.Col1Expression;
 import mcheli.mcheli.eval.eval.exp.ShareExpValue;




 public abstract class Col1AfterExpression
   extends Col1Expression
 {
   protected Col1AfterExpression() {}

   protected Col1AfterExpression(Col1Expression from, ShareExpValue s) {
     super(from, s);
   }

   protected AbstractExpression replace() {
     this.exp = this.exp.replaceVar();
     return this.share.repl.replaceVar1(this);
   }

   protected AbstractExpression replaceVar() {
     return replace();
   }

   public String toString() {
     if (this.exp == null) {
       return getOperator();
     }
     StringBuffer sb = new StringBuffer();
     if (this.exp.getPriority() > this.prio) {
       sb.append(this.exp.toString());
       sb.append(getOperator());
     } else if (this.exp.getPriority() == this.prio) {
       sb.append(this.exp.toString());
       sb.append(' ');
       sb.append(getOperator());
     } else {
       sb.append(this.share.paren.getOperator());
       sb.append(this.exp.toString());
       sb.append(this.share.paren.getEndOperator());
       sb.append(getOperator());
     }
     return sb.toString();
   }
 }