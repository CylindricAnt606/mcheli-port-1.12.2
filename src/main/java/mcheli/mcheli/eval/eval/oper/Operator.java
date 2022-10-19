package mcheli.mcheli.eval.eval.oper;

public interface Operator {
  Object power(Object paramObject1, Object paramObject2);
  
  Object signPlus(Object paramObject);
  
  Object signMinus(Object paramObject);
  
  Object plus(Object paramObject1, Object paramObject2);
  
  Object minus(Object paramObject1, Object paramObject2);
  
  Object mult(Object paramObject1, Object paramObject2);
  
  Object div(Object paramObject1, Object paramObject2);
  
  Object mod(Object paramObject1, Object paramObject2);
  
  Object bitNot(Object paramObject);
  
  Object shiftLeft(Object paramObject1, Object paramObject2);
  
  Object shiftRight(Object paramObject1, Object paramObject2);
  
  Object shiftRightLogical(Object paramObject1, Object paramObject2);
  
  Object bitAnd(Object paramObject1, Object paramObject2);
  
  Object bitOr(Object paramObject1, Object paramObject2);
  
  Object bitXor(Object paramObject1, Object paramObject2);
  
  Object not(Object paramObject);
  
  Object equal(Object paramObject1, Object paramObject2);
  
  Object notEqual(Object paramObject1, Object paramObject2);
  
  Object lessThan(Object paramObject1, Object paramObject2);
  
  Object lessEqual(Object paramObject1, Object paramObject2);
  
  Object greaterThan(Object paramObject1, Object paramObject2);
  
  Object greaterEqual(Object paramObject1, Object paramObject2);
  
  boolean bool(Object paramObject);
  
  Object inc(Object paramObject, int paramInt);
}


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\oper\Operator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */