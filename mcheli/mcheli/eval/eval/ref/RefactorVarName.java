/*    */ package mcheli.mcheli.eval.eval.ref;
/*    */ 
/*    */ import mcheli.eval.eval.ref.RefactorAdapter;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RefactorVarName
/*    */   extends RefactorAdapter
/*    */ {
/*    */   protected Class targetClass;
/*    */   protected String oldName;
/*    */   protected String newName;
/*    */   
/*    */   public RefactorVarName(Class targetClass, String oldName, String newName) {
/* 19 */     this.targetClass = targetClass;
/* 20 */     this.oldName = oldName;
/* 21 */     this.newName = newName;
/* 22 */     if (oldName == null || newName == null) {
/* 23 */       throw new NullPointerException();
/*    */     }
/*    */   }
/*    */   
/*    */   public String getNewName(Object target, String name) {
/* 28 */     if (!name.equals(this.oldName))
/* 29 */       return null; 
/* 30 */     if (this.targetClass == null) {
/* 31 */       if (target == null) {
/* 32 */         return this.newName;
/*    */       }
/* 34 */     } else if (target != null && this.targetClass.isAssignableFrom(target.getClass())) {
/*    */       
/* 36 */       return this.newName;
/*    */     } 
/* 38 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\ref\RefactorVarName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */