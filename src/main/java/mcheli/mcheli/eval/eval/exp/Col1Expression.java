 package mcheli.mcheli.eval.eval.exp;

 import mcheli.mcheli.eval.eval.exp.AbstractExpression;
 import mcheli.mcheli.eval.eval.exp.ShareExpValue;

 public abstract class Col1Expression extends AbstractExpression
 {
   protected AbstractExpression exp;

   public static AbstractExpression create(AbstractExpression exp, String string, int pos, AbstractExpression x) {
     mcheli.mcheli.eval.eval.exp.Col1Expression n = (mcheli.mcheli.eval.eval.exp.Col1Expression)exp;
     n.setExpression(x);
     n.setPos(string, pos);
     return n;
   }

   protected Col1Expression() {}

   protected Col1Expression(mcheli.mcheli.eval.eval.exp.Col1Expression from, ShareExpValue s) {
     super(from, s);
     if (from.exp != null) {
       this.exp = from.exp.dup(s);
     }
   }

   public void setExpression(AbstractExpression x) {
     this.exp = x;
   }

   protected final int getCols() {
     return 1;
   }

   protected final int getFirstPos() {
     return this.exp.getFirstPos();
   }

   public long evalLong() {
     return operateLong(this.exp.evalLong());
   }

   public double evalDouble() {
     return operateDouble(this.exp.evalDouble());
   }

   protected abstract long operateLong(long paramLong);

   protected abstract double operateDouble(double paramDouble);

   protected void search() {
     this.share.srch.search(this);
     if (this.share.srch.end()) {
       return;
     }
     if (this.share.srch.search1_begin(this))
       return;
     if (this.share.srch.end()) {
       return;
     }
     this.exp.search();
     if (this.share.srch.end()) {
       return;
     }
     this.share.srch.search1_end(this);
   }

   protected AbstractExpression replace() {
     this.exp = this.exp.replace();
     return this.share.repl.replace1(this);
   }

   protected AbstractExpression replaceVar() {
     this.exp = this.exp.replaceVar();
     return this.share.repl.replaceVar1(this);
   }

   public boolean equals(Object obj) {
     if (obj instanceof mcheli.eval.eval.exp.Col1Expression) {
       mcheli.eval.eval.exp.Col1Expression e = (mcheli.eval.eval.exp.Col1Expression)obj;
       if (getClass() == e.getClass()) {
         if (this.exp == null)
           return (e.exp == null);
         if (e.exp == null)
           return false;
         return this.exp.equals(e.exp);
       }
     }
     return false;
   }

   public int hashCode() {
     return getClass().hashCode() ^ this.exp.hashCode();
   }

   public void dump(int n) {
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < n; i++) {
       sb.append(' ');
     }
     sb.append(getOperator());
     System.out.println(sb.toString());
     if (this.exp != null) {
       this.exp.dump(n + 1);
     }
   }

   public String toString() {
     if (this.exp == null) {
       return getOperator();
     }
     StringBuffer sb = new StringBuffer();
     if (this.exp.getPriority() > this.prio) {
       sb.append(getOperator());
       sb.append(this.exp.toString());
     } else if (this.exp.getPriority() == this.prio) {
       sb.append(getOperator());
       sb.append(' ');
       sb.append(this.exp.toString());
     } else {
       sb.append(getOperator());
       sb.append(this.share.paren.getOperator());
       sb.append(this.exp.toString());
       sb.append(this.share.paren.getEndOperator());
     }
     return sb.toString();
   }
 }