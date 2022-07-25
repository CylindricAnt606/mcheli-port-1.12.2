/*     */ package mcheli.mcheli.aircraft;
/*     */ 
/*     */ import com.google.common.io.ByteArrayDataInput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import mcheli.MCH_Packet;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MCH_PacketPlayerControlBase
/*     */   extends MCH_Packet
/*     */ {
/*  38 */   public byte isUnmount = 0;
/*  39 */   public byte switchMode = -1;
/*  40 */   public byte switchCameraMode = 0;
/*  41 */   public byte switchWeapon = -1;
/*  42 */   public byte useFlareType = 0;
/*     */   public boolean useWeapon = false;
/*  44 */   public int useWeaponOption1 = 0;
/*  45 */   public int useWeaponOption2 = 0;
/*  46 */   public double useWeaponPosX = 0.0D;
/*  47 */   public double useWeaponPosY = 0.0D;
/*  48 */   public double useWeaponPosZ = 0.0D;
/*     */   public boolean throttleUp = false;
/*     */   public boolean throttleDown = false;
/*     */   public boolean moveLeft = false;
/*     */   public boolean moveRight = false;
/*     */   public boolean openGui;
/*  54 */   public byte switchHatch = 0;
/*  55 */   public byte switchFreeLook = 0;
/*  56 */   public byte switchGear = 0;
/*     */   public boolean ejectSeat = false;
/*  58 */   public byte putDownRack = 0;
/*     */ 
/*     */   
/*     */   public boolean switchSearchLight = false;
/*     */ 
/*     */   
/*     */   public boolean useBrake = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public void readData(ByteArrayDataInput data) {
/*     */     try {
/*  70 */       short bf = data.readShort();
/*  71 */       this.useWeapon = getBit(bf, 0);
/*  72 */       this.throttleUp = getBit(bf, 1);
/*  73 */       this.throttleDown = getBit(bf, 2);
/*  74 */       this.moveLeft = getBit(bf, 3);
/*  75 */       this.moveRight = getBit(bf, 4);
/*  76 */       this.switchSearchLight = getBit(bf, 5);
/*  77 */       this.ejectSeat = getBit(bf, 6);
/*  78 */       this.openGui = getBit(bf, 7);
/*  79 */       this.useBrake = getBit(bf, 8);
/*     */       
/*  81 */       bf = (short)data.readByte();
/*  82 */       this.putDownRack = (byte)(bf >> 6 & 0x3);
/*  83 */       this.isUnmount = (byte)(bf >> 4 & 0x3);
/*  84 */       this.useFlareType = (byte)(bf >> 0 & 0xF);
/*     */       
/*  86 */       this.switchMode = data.readByte();
/*  87 */       this.switchWeapon = data.readByte();
/*  88 */       if (this.useWeapon) {
/*     */         
/*  90 */         this.useWeaponOption1 = data.readInt();
/*  91 */         this.useWeaponOption2 = data.readInt();
/*  92 */         this.useWeaponPosX = data.readDouble();
/*  93 */         this.useWeaponPosY = data.readDouble();
/*  94 */         this.useWeaponPosZ = data.readDouble();
/*     */       } 
/*     */       
/*  97 */       bf = (short)data.readByte();
/*  98 */       this.switchCameraMode = (byte)(bf >> 6 & 0x3);
/*  99 */       this.switchHatch = (byte)(bf >> 4 & 0x3);
/* 100 */       this.switchFreeLook = (byte)(bf >> 2 & 0x3);
/* 101 */       this.switchGear = (byte)(bf >> 0 & 0x3);
/*     */     }
/* 103 */     catch (Exception e) {
/*     */       
/* 105 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeData(DataOutputStream dos) {
/*     */     try {
/* 115 */       short bf = 0;
/* 116 */       bf = setBit(bf, 0, this.useWeapon);
/* 117 */       bf = setBit(bf, 1, this.throttleUp);
/* 118 */       bf = setBit(bf, 2, this.throttleDown);
/* 119 */       bf = setBit(bf, 3, this.moveLeft);
/* 120 */       bf = setBit(bf, 4, this.moveRight);
/* 121 */       bf = setBit(bf, 5, this.switchSearchLight);
/* 122 */       bf = setBit(bf, 6, this.ejectSeat);
/* 123 */       bf = setBit(bf, 7, this.openGui);
/* 124 */       bf = setBit(bf, 8, this.useBrake);
/* 125 */       dos.writeShort(bf);
/*     */       
/* 127 */       bf = (short)(byte)((this.putDownRack & 0x3) << 6 | (this.isUnmount & 0x3) << 4 | this.useFlareType & 0xF);
/*     */ 
/*     */ 
/*     */       
/* 131 */       dos.writeByte(bf);
/*     */       
/* 133 */       dos.writeByte(this.switchMode);
/* 134 */       dos.writeByte(this.switchWeapon);
/* 135 */       if (this.useWeapon) {
/*     */         
/* 137 */         dos.writeInt(this.useWeaponOption1);
/* 138 */         dos.writeInt(this.useWeaponOption2);
/* 139 */         dos.writeDouble(this.useWeaponPosX);
/* 140 */         dos.writeDouble(this.useWeaponPosY);
/* 141 */         dos.writeDouble(this.useWeaponPosZ);
/*     */       } 
/*     */       
/* 144 */       bf = (short)(byte)((this.switchCameraMode & 0x3) << 6 | (this.switchHatch & 0x3) << 4 | (this.switchFreeLook & 0x3) << 2 | (this.switchGear & 0x3) << 0);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 149 */       dos.writeByte(bf);
/*     */     }
/* 151 */     catch (IOException e) {
/*     */       
/* 153 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_PacketPlayerControlBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */