/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import mcheli.wrapper.W_KeyBinding;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ import org.lwjgl.input.Mouse;
/*    */ 
/*    */ public class MCH_Key {
/*    */   public int key;
/*    */   private boolean isPress;
/*    */   private boolean isBeforePress;
/*    */   
/*    */   public MCH_Key(int k) {
/* 14 */     this.key = k;
/* 15 */     this.isPress = false;
/* 16 */     this.isBeforePress = false;
/*    */   }
/*    */   
/*    */   public boolean isKeyDown() {
/* 20 */     return (!this.isBeforePress && this.isPress == true);
/*    */   }
/*    */   
/*    */   public boolean isKeyPress() {
/* 24 */     return this.isPress;
/*    */   }
/*    */   
/*    */   public boolean isKeyUp() {
/* 28 */     return (this.isBeforePress == true && !this.isPress);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 33 */     if (this.key == 0)
/* 34 */       return;  this.isBeforePress = this.isPress;
/* 35 */     if (this.key >= 0) {
/*    */       
/* 37 */       this.isPress = Keyboard.isKeyDown(this.key);
/*    */     }
/*    */     else {
/*    */       
/* 41 */       this.isPress = Mouse.isButtonDown(this.key + 100);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isKeyDown(int key) {
/* 47 */     if (key > 0) return Keyboard.isKeyDown(key); 
/* 48 */     if (key < 0) return Mouse.isButtonDown(key + 100); 
/* 49 */     return false;
/*    */   }
/*    */   
/*    */   public static boolean isKeyDown(KeyBinding keyBind) {
/* 53 */     return isKeyDown(W_KeyBinding.getKeyCode(keyBind));
/*    */   }
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_Key.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */