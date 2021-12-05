/*    */ package mcheli.mcheli;
/*    */ 
/*    */ import mcheli.wrapper.W_KeyBinding;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MCH_KeyName
/*    */ {
/*    */   private final int value;
/*    */   private final String name;
/*    */   private final String description;
/*    */   
/*    */   private MCH_KeyName(int v, String n, String d) {
/* 16 */     this.value = v;
/* 17 */     this.name = n.toUpperCase();
/* 18 */     this.description = d;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getValue(String name) {
/* 23 */     String n = name.toUpperCase();
/* 24 */     for (mcheli.MCH_KeyName key : list) {
/*    */       
/* 26 */       if (key.name.compareTo(n) == 0)
/*    */       {
/* 28 */         return key.value;
/*    */       }
/*    */     } 
/* 31 */     return 0;
/*    */   }
/*    */   
/*    */   public static String getName(int value) {
/* 35 */     for (mcheli.MCH_KeyName key : list) {
/*    */       
/* 37 */       if (key.value == value)
/*    */       {
/* 39 */         return key.name;
/*    */       }
/*    */     } 
/* 42 */     return "";
/*    */   }
/*    */   
/*    */   public static String getDescription(int value) {
/* 46 */     for (mcheli.MCH_KeyName key : list) {
/*    */       
/* 48 */       if (key.value == value)
/*    */       {
/* 50 */         return key.description;
/*    */       }
/*    */     } 
/* 53 */     return "";
/*    */   }
/*    */   
/*    */   public static String getDescOrName(int value) {
/* 57 */     for (mcheli.MCH_KeyName key : list) {
/*    */       
/* 59 */       if (key.value == value)
/*    */       {
/* 61 */         return key.description.isEmpty() ? key.name : key.description;
/*    */       }
/*    */     } 
/* 64 */     return "";
/*    */   }
/*    */   
/*    */   public static String getDescOrName(KeyBinding key) {
/* 68 */     return getDescOrName(W_KeyBinding.getKeyCode(key));
/*    */   }
/*    */   
/* 71 */   private static mcheli.MCH_KeyName[] list = new mcheli.MCH_KeyName[] { new mcheli.MCH_KeyName(0, "NONE", ""), new mcheli.MCH_KeyName(1, "ESCAPE", "Escape"), new mcheli.MCH_KeyName(2, "1", ""), new mcheli.MCH_KeyName(3, "2", ""), new mcheli.MCH_KeyName(4, "3", ""), new mcheli.MCH_KeyName(5, "4", ""), new mcheli.MCH_KeyName(6, "5", ""), new mcheli.MCH_KeyName(7, "6", ""), new mcheli.MCH_KeyName(8, "7", ""), new mcheli.MCH_KeyName(9, "8", ""), new mcheli.MCH_KeyName(10, "9", ""), new mcheli.MCH_KeyName(11, "0", ""), new mcheli.MCH_KeyName(12, "MINUS", "-"), new mcheli.MCH_KeyName(13, "EQUALS", "="), new mcheli.MCH_KeyName(14, "BACK", "Backspace"), new mcheli.MCH_KeyName(15, "TAB", "Tab"), new mcheli.MCH_KeyName(16, "Q", ""), new mcheli.MCH_KeyName(17, "W", ""), new mcheli.MCH_KeyName(18, "E", ""), new mcheli.MCH_KeyName(19, "R", ""), new mcheli.MCH_KeyName(20, "T", ""), new mcheli.MCH_KeyName(21, "Y", ""), new mcheli.MCH_KeyName(22, "U", ""), new mcheli.MCH_KeyName(23, "I", ""), new mcheli.MCH_KeyName(24, "O", ""), new mcheli.MCH_KeyName(25, "P", ""), new mcheli.MCH_KeyName(26, "LBRACKET", "["), new mcheli.MCH_KeyName(27, "RBRACKET", "]"), new mcheli.MCH_KeyName(28, "RETURN", "Enter"), new mcheli.MCH_KeyName(29, "LCONTROL", "L Control"), new mcheli.MCH_KeyName(30, "A", ""), new mcheli.MCH_KeyName(31, "S", ""), new mcheli.MCH_KeyName(32, "D", ""), new mcheli.MCH_KeyName(33, "F", ""), new mcheli.MCH_KeyName(34, "G", ""), new mcheli.MCH_KeyName(35, "H", ""), new mcheli.MCH_KeyName(36, "J", ""), new mcheli.MCH_KeyName(37, "K", ""), new mcheli.MCH_KeyName(38, "L", ""), new mcheli.MCH_KeyName(39, "SEMICOLON", " ;"), new mcheli.MCH_KeyName(40, "APOSTROPHE", "'"), new mcheli.MCH_KeyName(41, "GRAVE", "`"), new mcheli.MCH_KeyName(42, "LSHIFT", "L Shift"), new mcheli.MCH_KeyName(43, "BACKSLASH", "\\"), new mcheli.MCH_KeyName(44, "Z", ""), new mcheli.MCH_KeyName(45, "X", ""), new mcheli.MCH_KeyName(46, "C", ""), new mcheli.MCH_KeyName(47, "V", ""), new mcheli.MCH_KeyName(48, "B", ""), new mcheli.MCH_KeyName(49, "N", ""), new mcheli.MCH_KeyName(50, "M", ""), new mcheli.MCH_KeyName(51, "COMMA", ","), new mcheli.MCH_KeyName(52, "PERIOD", "."), new mcheli.MCH_KeyName(53, "SLASH", "/"), new mcheli.MCH_KeyName(54, "RSHIFT", "R Shift"), new mcheli.MCH_KeyName(55, "MULTIPLY", ""), new mcheli.MCH_KeyName(56, "LMENU", "L Menu/Alt"), new mcheli.MCH_KeyName(57, "SPACE", ""), new mcheli.MCH_KeyName(58, "CAPITAL", "Caps Lock"), new mcheli.MCH_KeyName(59, "F1", ""), new mcheli.MCH_KeyName(60, "F2", ""), new mcheli.MCH_KeyName(61, "F3", ""), new mcheli.MCH_KeyName(62, "F4", ""), new mcheli.MCH_KeyName(63, "F5", ""), new mcheli.MCH_KeyName(64, "F6", ""), new mcheli.MCH_KeyName(65, "F7", ""), new mcheli.MCH_KeyName(66, "F8", ""), new mcheli.MCH_KeyName(67, "F9", ""), new mcheli.MCH_KeyName(68, "F10", ""), new mcheli.MCH_KeyName(69, "NUMLOCK", "Number Lock"), new mcheli.MCH_KeyName(70, "SCROLL", "Scroll Lock"), new mcheli.MCH_KeyName(71, "NUMPAD7", ""), new mcheli.MCH_KeyName(72, "NUMPAD8", ""), new mcheli.MCH_KeyName(73, "NUMPAD9", ""), new mcheli.MCH_KeyName(74, "SUBTRACT", ""), new mcheli.MCH_KeyName(75, "NUMPAD4", ""), new mcheli.MCH_KeyName(76, "NUMPAD5", ""), new mcheli.MCH_KeyName(77, "NUMPAD6", ""), new mcheli.MCH_KeyName(78, "ADD", ""), new mcheli.MCH_KeyName(79, "NUMPAD1", ""), new mcheli.MCH_KeyName(80, "NUMPAD2", ""), new mcheli.MCH_KeyName(81, "NUMPAD3", ""), new mcheli.MCH_KeyName(82, "NUMPAD0", ""), new mcheli.MCH_KeyName(83, "DECIMAL", ""), new mcheli.MCH_KeyName(87, "F11", ""), new mcheli.MCH_KeyName(88, "F12", ""), new mcheli.MCH_KeyName(100, "F13", ""), new mcheli.MCH_KeyName(101, "F14", ""), new mcheli.MCH_KeyName(102, "F15", ""), new mcheli.MCH_KeyName(112, "KANA", ""), new mcheli.MCH_KeyName(121, "CONVERT", ""), new mcheli.MCH_KeyName(123, "NOCONVERT", ""), new mcheli.MCH_KeyName(125, "YEN", "¥"), new mcheli.MCH_KeyName(141, "NUMPADEQUALS", ""), new mcheli.MCH_KeyName(144, "CIRCUMFLEX", "^"), new mcheli.MCH_KeyName(145, "AT", "@"), new mcheli.MCH_KeyName(146, "COLON", " :"), new mcheli.MCH_KeyName(147, "UNDERLINE", "_"), new mcheli.MCH_KeyName(148, "KANJI", ""), new mcheli.MCH_KeyName(149, "STOP", ""), new mcheli.MCH_KeyName(150, "AX", ""), new mcheli.MCH_KeyName(151, "UNLABLED", ""), new mcheli.MCH_KeyName(156, "NUMPADENTER", ""), new mcheli.MCH_KeyName(157, "RCONTROL", "R Control"), new mcheli.MCH_KeyName(179, "NUMPADCOMMA", ""), new mcheli.MCH_KeyName(181, "DIVIDE", ""), new mcheli.MCH_KeyName(183, "SYSRQ", ""), new mcheli.MCH_KeyName(184, "RMENU", "R Menu/Alt"), new mcheli.MCH_KeyName(197, "PAUSE", ""), new mcheli.MCH_KeyName(199, "HOME", ""), new mcheli.MCH_KeyName(200, "UP", "Up Arrow"), new mcheli.MCH_KeyName(201, "PRIOR", "Page Up"), new mcheli.MCH_KeyName(203, "LEFT", "Left Arrow"), new mcheli.MCH_KeyName(205, "RIGHT", "Right Arrow"), new mcheli.MCH_KeyName(207, "END", ""), new mcheli.MCH_KeyName(208, "DOWN", "Down Arrow"), new mcheli.MCH_KeyName(209, "NEXT", "Page Down"), new mcheli.MCH_KeyName(210, "INSERT", ""), new mcheli.MCH_KeyName(211, "DELETE", ""), new mcheli.MCH_KeyName(219, "LMETA", "LWIN [3]"), new mcheli.MCH_KeyName(220, "RMETA", "RWIN [3]"), new mcheli.MCH_KeyName(221, "APPS", ""), new mcheli.MCH_KeyName(222, "POWER", ""), new mcheli.MCH_KeyName(223, "SLEEP", ""), new mcheli.MCH_KeyName(-100, "BUTTON0", "Left Click"), new mcheli.MCH_KeyName(-99, "BUTTON1", "Right Click"), new mcheli.MCH_KeyName(-98, "BUTTON2", "Middle Click"), new mcheli.MCH_KeyName(-97, "BUTTON3", ""), new mcheli.MCH_KeyName(-96, "BUTTON4", ""), new mcheli.MCH_KeyName(-95, "BUTTON5", ""), new mcheli.MCH_KeyName(-94, "BUTTON6", ""), new mcheli.MCH_KeyName(-93, "BUTTON7", ""), new mcheli.MCH_KeyName(-92, "BUTTON8", ""), new mcheli.MCH_KeyName(-91, "BUTTON9", ""), new mcheli.MCH_KeyName(-90, "BUTTON10", ""), new mcheli.MCH_KeyName(-89, "BUTTON11", ""), new mcheli.MCH_KeyName(-88, "BUTTON12", ""), new mcheli.MCH_KeyName(-87, "BUTTON13", ""), new mcheli.MCH_KeyName(-86, "BUTTON14", ""), new mcheli.MCH_KeyName(-85, "BUTTON15", "") };
/*    */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_KeyName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */