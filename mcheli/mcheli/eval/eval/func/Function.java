package mcheli.mcheli.eval.eval.func;

public interface Function {
  long evalLong(Object paramObject, String paramString, Long[] paramArrayOfLong) throws Throwable;
  
  double evalDouble(Object paramObject, String paramString, Double[] paramArrayOfDouble) throws Throwable;
  
  Object evalObject(Object paramObject, String paramString, Object[] paramArrayOfObject) throws Throwable;
}


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\func\Function.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */