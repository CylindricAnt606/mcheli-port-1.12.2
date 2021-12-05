/*     */ package mcheli.mcheli.eval.eval.var;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import mcheli.eval.eval.var.Variable;
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
/*     */ public class MapVariable
/*     */   implements Variable
/*     */ {
/*     */   protected Map map;
/*     */   
/*     */   public MapVariable() {
/*  28 */     this(new HashMap<Object, Object>());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapVariable(Map varMap) {
/*  38 */     this.map = varMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMap(Map varMap) {
/*  59 */     this.map = varMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getMap() {
/*  68 */     return this.map;
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
/*     */   public void setValue(Object name, Object obj) {
/*  81 */     this.map.put(name, obj);
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
/*     */   public Object getObject(Object name) {
/*  95 */     return this.map.get(name);
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
/*     */   public long evalLong(Object val) {
/* 107 */     return ((Number)val).longValue();
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
/*     */   public double evalDouble(Object val) {
/* 119 */     return ((Number)val).doubleValue();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getObject(Object array, int index) {
/* 136 */     return Array.get(array, index);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Object array, int index, Object val) {
/* 154 */     Array.set(array, index, val);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getObject(Object obj, String field) {
/*     */     try {
/* 172 */       Class<?> c = obj.getClass();
/* 173 */       Field f = c.getField(field);
/* 174 */       return f.get(obj);
/* 175 */     } catch (RuntimeException e) {
/* 176 */       throw e;
/* 177 */     } catch (Exception e) {
/*     */       
/* 179 */       throw new RuntimeException(e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(Object obj, String field, Object val) {
/*     */     try {
/* 199 */       Class<?> c = obj.getClass();
/* 200 */       Field f = c.getField(field);
/* 201 */       f.set(obj, val);
/* 202 */     } catch (RuntimeException e) {
/* 203 */       throw e;
/* 204 */     } catch (Exception e) {
/* 205 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\var\MapVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */