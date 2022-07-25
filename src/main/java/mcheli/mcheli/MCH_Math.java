/*      */ package mcheli.mcheli;
/*      */ 
/*      */ import net.minecraft.util.MathHelper;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MCH_Math
/*      */ {
/*   11 */   public static float PI = 3.1415927F;
/*   12 */   public static mcheli.MCH_Math instance = new mcheli.MCH_Math();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FVector3D privateNewVec3D(float x, float y, float z) {
/*   18 */     FVector3D v = new FVector3D(this);
/*   19 */     v.x = x;
/*   20 */     v.y = y;
/*   21 */     v.z = z;
/*   22 */     return v;
/*      */   }
/*   24 */   public static FVector3D newVec3D() { return instance.privateNewVec3D(0.0F, 0.0F, 0.0F); } public static FVector3D newVec3D(float x, float y, float z) {
/*   25 */     return instance.privateNewVec3D(x, y, z);
/*      */   }
/*      */   
/*      */   private FQuat privateNewQuat() {
/*   29 */     FQuat q = new FQuat(this);
/*   30 */     QuatIdentity(q);
/*   31 */     return new FQuat(this);
/*      */   } public static FQuat newQuat() {
/*   33 */     return instance.privateNewQuat();
/*      */   }
/*      */   
/*      */   private FMatrix privateNewMatrix() {
/*   37 */     FMatrix m = new FMatrix(this);
/*   38 */     MatIdentity(m);
/*   39 */     return m;
/*      */   } public static FMatrix newMatrix() {
/*   41 */     return instance.privateNewMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static FQuat EulerToQuatTestNG(float yaw, float pitch, float roll) {
/*   46 */     FVector3D axis = newVec3D();
/*      */     
/*   48 */     float rot = VecNormalize(axis);
/*      */     
/*   50 */     FQuat dqtn = newQuat();
/*      */     
/*   52 */     QuatRotation(dqtn, rot, axis.x, axis.y, axis.z);
/*      */     
/*   54 */     return dqtn;
/*      */   }
/*      */   
/*      */   public static FMatrix EulerToMatrix(float yaw, float pitch, float roll) {
/*   58 */     FMatrix m = newMatrix();
/*      */     
/*   60 */     MatTurnZ(m, roll / 180.0F * PI);
/*   61 */     MatTurnX(m, pitch / 180.0F * PI);
/*   62 */     MatTurnY(m, yaw / 180.0F * PI);
/*      */     
/*   64 */     return m;
/*      */   }
/*      */   
/*      */   public static FQuat EulerToQuat(float yaw, float pitch, float roll) {
/*   68 */     FQuat dqtn = newQuat();
/*   69 */     MatrixToQuat(dqtn, EulerToMatrix(yaw, pitch, roll));
/*   70 */     return dqtn;
/*      */   }
/*      */ 
/*      */   
/*      */   public static FVector3D QuatToEuler(FQuat q) {
/*   75 */     FMatrix m = QuatToMatrix(q);
/*   76 */     return MatrixToEuler(m);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static FVector3D MatrixToEuler(FMatrix m) {
/*   82 */     float xx = m.m00;
/*   83 */     float xy = m.m01;
/*   84 */     float xz = m.m02;
/*      */     
/*   86 */     float yy = m.m11;
/*      */     
/*   88 */     float zx = m.m20;
/*   89 */     float zy = m.m21;
/*   90 */     float zz = m.m22;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   95 */     float b = (float)-Math.asin(zy);
/*   96 */     float cosB = Cos(b);
/*      */     
/*   98 */     if (Math.abs(cosB) >= 1.0E-4D) {
/*      */       
/*  100 */       c = Atan2(zx, zz);
/*  101 */       float xy_cos = xy / cosB;
/*  102 */       if (xy_cos > 1.0F) {
/*      */         
/*  104 */         xy_cos = 1.0F;
/*      */       }
/*  106 */       else if (xy_cos < -1.0F) {
/*      */         
/*  108 */         xy_cos = -1.0F;
/*      */       } 
/*  110 */       a = (float)Math.asin(xy_cos);
/*  111 */       if (Float.isNaN(a))
/*      */       {
/*  113 */         a = 0.0F;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  118 */       c = Atan2(-xz, xx);
/*  119 */       a = 0.0F;
/*      */     } 
/*      */     
/*  122 */     float a = (float)(a * 180.0D / PI);
/*  123 */     b = (float)(b * 180.0D / PI);
/*  124 */     float c = (float)(c * 180.0D / PI);
/*      */     
/*  126 */     if (yy < 0.0F) a = 180.0F - a;
/*      */     
/*  128 */     return newVec3D(-b, -c, -a);
/*      */   } public float atan2(float y, float x) {
/*  130 */     return Atan2(y, x);
/*      */   } public static float SIGN(float x) {
/*  132 */     return (x >= 0.0F) ? 1.0F : -1.0F;
/*      */   }
/*      */   public static float NORM(float a, float b, float c, float d) {
/*  135 */     return (float)Math.sqrt((a * a + b * b + c * c + d * d));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void QuatNormalize(FQuat q) {
/*  140 */     float r = NORM(q.w, q.x, q.y, q.z);
/*  141 */     if (MathHelper.func_76135_e(r) > 1.0E-4D) {
/*      */       
/*  143 */       q.w /= r;
/*  144 */       q.x /= r;
/*  145 */       q.y /= r;
/*  146 */       q.z /= r;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean MatrixToQuat(FQuat q, FMatrix m) {
/*  152 */     q.w = (m.m00 + m.m11 + m.m22 + 1.0F) / 4.0F;
/*  153 */     q.x = (m.m00 - m.m11 - m.m22 + 1.0F) / 4.0F;
/*  154 */     q.y = (-m.m00 + m.m11 - m.m22 + 1.0F) / 4.0F;
/*  155 */     q.z = (-m.m00 - m.m11 + m.m22 + 1.0F) / 4.0F;
/*  156 */     if (q.w < 0.0F) q.w = 0.0F; 
/*  157 */     if (q.x < 0.0F) q.x = 0.0F; 
/*  158 */     if (q.y < 0.0F) q.y = 0.0F; 
/*  159 */     if (q.z < 0.0F) q.z = 0.0F; 
/*  160 */     q.w = (float)Math.sqrt(q.w);
/*  161 */     q.x = (float)Math.sqrt(q.x);
/*  162 */     q.y = (float)Math.sqrt(q.y);
/*  163 */     q.z = (float)Math.sqrt(q.z);
/*  164 */     if (q.w >= q.x && q.w >= q.y && q.w >= q.z) {
/*  165 */       q.w *= 1.0F;
/*  166 */       q.x *= SIGN(m.m21 - m.m12);
/*  167 */       q.y *= SIGN(m.m02 - m.m20);
/*  168 */       q.z *= SIGN(m.m10 - m.m01);
/*  169 */     } else if (q.x >= q.w && q.x >= q.y && q.x >= q.z) {
/*  170 */       q.w *= SIGN(m.m21 - m.m12);
/*  171 */       q.x *= 1.0F;
/*  172 */       q.y *= SIGN(m.m10 + m.m01);
/*  173 */       q.z *= SIGN(m.m02 + m.m20);
/*  174 */     } else if (q.y >= q.w && q.y >= q.x && q.y >= q.z) {
/*  175 */       q.w *= SIGN(m.m02 - m.m20);
/*  176 */       q.x *= SIGN(m.m10 + m.m01);
/*  177 */       q.y *= 1.0F;
/*  178 */       q.z *= SIGN(m.m21 + m.m12);
/*  179 */     } else if (q.z >= q.w && q.z >= q.x && q.z >= q.y) {
/*  180 */       q.w *= SIGN(m.m10 - m.m01);
/*  181 */       q.x *= SIGN(m.m20 + m.m02);
/*  182 */       q.y *= SIGN(m.m21 + m.m12);
/*  183 */       q.z *= 1.0F;
/*      */     } else {
/*  185 */       QuatIdentity(q);
/*  186 */       return false;
/*      */     } 
/*      */     
/*  189 */     correctQuat(q);
/*      */     
/*  191 */     float r = NORM(q.w, q.x, q.y, q.z);
/*  192 */     q.w /= r;
/*  193 */     q.x /= r;
/*  194 */     q.y /= r;
/*  195 */     q.z /= r;
/*      */     
/*  197 */     correctQuat(q);
/*      */     
/*  199 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void correctQuat(FQuat q) {
/*  204 */     if (Float.isNaN(q.w) || Float.isInfinite(q.w))
/*      */     {
/*  206 */       q.w = 0.0F;
/*      */     }
/*  208 */     if (Float.isNaN(q.x) || Float.isInfinite(q.x))
/*      */     {
/*  210 */       q.x = 0.0F;
/*      */     }
/*  212 */     if (Float.isNaN(q.y) || Float.isInfinite(q.y))
/*      */     {
/*  214 */       q.y = 0.0F;
/*      */     }
/*  216 */     if (Float.isNaN(q.z) || Float.isInfinite(q.z))
/*      */     {
/*  218 */       q.z = 0.0F;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static FQuat motionTest(int x, int y, FQuat prevQtn) {
/*  225 */     FVector3D axis = newVec3D();
/*  226 */     FQuat dqtn = newQuat();
/*      */ 
/*      */     
/*  229 */     int dx = x;
/*  230 */     int dy = y;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  235 */     axis.x = 2.0F * PI * dy / 200.0F;
/*  236 */     axis.y = 2.0F * PI * dx / 200.0F;
/*  237 */     axis.z = 0.0F;
/*      */ 
/*      */     
/*  240 */     float rot = VecNormalize(axis);
/*      */ 
/*      */     
/*  243 */     QuatRotation(dqtn, rot, axis.x, axis.y, axis.z);
/*      */ 
/*      */     
/*  246 */     return QuatMult(dqtn, prevQtn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float Sin(float rad) {
/*  324 */     return (float)Math.sin(rad);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float Cos(float rad) {
/*  330 */     return (float)Math.cos(rad);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float Tan(float rad) {
/*  336 */     return (float)Math.tan(rad);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float Floor(float x) {
/*  341 */     return (float)Math.floor(x);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float Atan(float x) {
/*  347 */     return (float)Math.atan(x);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float Atan2(float y, float x) {
/*  353 */     return (float)Math.atan2(y, x);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float Fabs(float x) {
/*  359 */     return (x >= 0.0F) ? x : -x;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float Sqrt(float x) {
/*  365 */     return (float)Math.sqrt(x);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float InvSqrt(float x) {
/*  371 */     return 1.0F / (float)Math.sqrt(x);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static float Pow(float a, float b) {
/*  377 */     return (float)Math.pow(a, b);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float VecNormalize(FVector3D lpV) {
/*  388 */     float len2 = lpV.x * lpV.x + lpV.y * lpV.y + lpV.z * lpV.z;
/*  389 */     float length = Sqrt(len2);
/*  390 */     if (length == 0.0F)
/*      */     {
/*  392 */       return 0.0F;
/*      */     }
/*      */     
/*  395 */     float invLength = 1.0F / length;
/*  396 */     lpV.x *= invLength;
/*  397 */     lpV.y *= invLength;
/*  398 */     lpV.z *= invLength;
/*      */     
/*  400 */     return length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float Vec2DNormalize(FVector2D lpV) {
/*  410 */     float len2 = lpV.x * lpV.x + lpV.y * lpV.y;
/*  411 */     float length = Sqrt(len2);
/*      */     
/*  413 */     if (length == 0.0F)
/*      */     {
/*  415 */       return 0.0F;
/*      */     }
/*      */     
/*  418 */     float invLength = 1.0F / length;
/*  419 */     lpV.x *= invLength;
/*  420 */     lpV.y *= invLength;
/*      */     
/*  422 */     return length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FVector3D MatVector(FMatrix lpM, FVector3D lpV) {
/*  434 */     FVector3D lpS = newVec3D();
/*      */ 
/*      */     
/*  437 */     float x = lpV.x;
/*  438 */     float y = lpV.y;
/*  439 */     float z = lpV.z;
/*      */     
/*  441 */     lpS.x = lpM.m00 * x + lpM.m01 * y + lpM.m02 * z + lpM.m03;
/*  442 */     lpS.y = lpM.m10 * x + lpM.m11 * y + lpM.m12 * z + lpM.m13;
/*  443 */     lpS.z = lpM.m20 * x + lpM.m21 * y + lpM.m22 * z + lpM.m23;
/*      */     
/*  445 */     return lpS;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FVector3D MatDirection(FMatrix lpM, FVector3D lpDir) {
/*  458 */     FVector3D lpSDir = newVec3D();
/*      */ 
/*      */     
/*  461 */     float x = lpDir.x;
/*  462 */     float y = lpDir.y;
/*  463 */     float z = lpDir.z;
/*      */     
/*  465 */     lpSDir.x = lpM.m00 * x + lpM.m01 * y + lpM.m02 * z;
/*  466 */     lpSDir.y = lpM.m10 * x + lpM.m11 * y + lpM.m12 * z;
/*  467 */     lpSDir.z = lpM.m20 * x + lpM.m21 * y + lpM.m22 * z;
/*      */     
/*  469 */     return lpSDir;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatIdentity(FMatrix lpM) {
/*  480 */     lpM.m01 = lpM.m02 = lpM.m03 = lpM.m10 = lpM.m12 = lpM.m13 = lpM.m20 = lpM.m21 = lpM.m23 = lpM.m30 = lpM.m31 = lpM.m32 = 0.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  485 */     lpM.m00 = lpM.m11 = lpM.m22 = lpM.m33 = 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatCopy(FMatrix lpMa, FMatrix lpMb) {
/*  495 */     lpMa.m00 = lpMb.m00;
/*  496 */     lpMa.m10 = lpMb.m10;
/*  497 */     lpMa.m20 = lpMb.m20;
/*  498 */     lpMa.m30 = lpMb.m30;
/*      */     
/*  500 */     lpMa.m01 = lpMb.m01;
/*  501 */     lpMa.m11 = lpMb.m11;
/*  502 */     lpMa.m21 = lpMb.m21;
/*  503 */     lpMa.m31 = lpMb.m31;
/*      */     
/*  505 */     lpMa.m02 = lpMb.m02;
/*  506 */     lpMa.m12 = lpMb.m12;
/*  507 */     lpMa.m22 = lpMb.m22;
/*  508 */     lpMa.m32 = lpMb.m32;
/*      */     
/*  510 */     lpMa.m03 = lpMb.m03;
/*  511 */     lpMa.m13 = lpMb.m13;
/*  512 */     lpMa.m23 = lpMb.m23;
/*  513 */     lpMa.m33 = lpMb.m33;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatTranslate(FMatrix m, float x, float y, float z) {
/*  531 */     float m30 = m.m30;
/*  532 */     float m31 = m.m31;
/*  533 */     float m32 = m.m32;
/*  534 */     float m33 = m.m33;
/*      */     
/*  536 */     m.m00 += m30 * x;
/*  537 */     m.m01 += m31 * x;
/*  538 */     m.m02 += m32 * x;
/*  539 */     m.m03 += m33 * x;
/*      */     
/*  541 */     m.m10 += m30 * y;
/*  542 */     m.m11 += m31 * y;
/*  543 */     m.m12 += m32 * y;
/*  544 */     m.m13 += m33 * y;
/*      */     
/*  546 */     m.m20 += m30 * z;
/*  547 */     m.m21 += m31 * z;
/*  548 */     m.m22 += m32 * z;
/*  549 */     m.m23 += m33 * z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatMove(FMatrix m, float x, float y, float z) {
/*  565 */     m.m03 += m.m00 * x + m.m01 * y + m.m02 * z;
/*  566 */     m.m13 += m.m10 * x + m.m11 * y + m.m12 * z;
/*  567 */     m.m23 += m.m20 * x + m.m21 * y + m.m22 * z;
/*  568 */     m.m33 += m.m30 * x + m.m31 * y + m.m32 * z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatRotateX(FMatrix m, float rad) {
/*  587 */     if (rad > 2.0F * PI || rad < -2.0F * PI)
/*      */     {
/*  589 */       rad -= 2.0F * PI * (int)(rad / 2.0F * PI);
/*      */     }
/*      */     
/*  592 */     float cosA = Cos(rad);
/*  593 */     float sinA = Sin(rad);
/*      */     
/*  595 */     float tmp1 = m.m10;
/*  596 */     float tmp2 = m.m20;
/*  597 */     m.m10 = cosA * tmp1 - sinA * tmp2;
/*  598 */     m.m20 = sinA * tmp1 + cosA * tmp2;
/*      */     
/*  600 */     tmp1 = m.m11;
/*  601 */     tmp2 = m.m21;
/*  602 */     m.m11 = cosA * tmp1 - sinA * tmp2;
/*  603 */     m.m21 = sinA * tmp1 + cosA * tmp2;
/*      */     
/*  605 */     tmp1 = m.m12;
/*  606 */     tmp2 = m.m22;
/*  607 */     m.m12 = cosA * tmp1 - sinA * tmp2;
/*  608 */     m.m22 = sinA * tmp1 + cosA * tmp2;
/*      */     
/*  610 */     tmp1 = m.m13;
/*  611 */     tmp2 = m.m23;
/*  612 */     m.m13 = cosA * tmp1 - sinA * tmp2;
/*  613 */     m.m23 = sinA * tmp1 + cosA * tmp2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatRotateY(FMatrix m, float rad) {
/*  632 */     if (rad > 2.0F * PI || rad < -2.0F * PI)
/*      */     {
/*  634 */       rad -= 2.0F * PI * (int)(rad / 2.0F * PI);
/*      */     }
/*      */     
/*  637 */     float cosA = Cos(rad);
/*  638 */     float sinA = Sin(rad);
/*      */     
/*  640 */     float tmp1 = m.m00;
/*  641 */     float tmp2 = m.m20;
/*  642 */     m.m00 = cosA * tmp1 + sinA * tmp2;
/*  643 */     m.m20 = -sinA * tmp1 + cosA * tmp2;
/*      */     
/*  645 */     tmp1 = m.m01;
/*  646 */     tmp2 = m.m21;
/*  647 */     m.m01 = cosA * tmp1 + sinA * tmp2;
/*  648 */     m.m21 = -sinA * tmp1 + cosA * tmp2;
/*      */     
/*  650 */     tmp1 = m.m02;
/*  651 */     tmp2 = m.m22;
/*  652 */     m.m02 = cosA * tmp1 + sinA * tmp2;
/*  653 */     m.m22 = -sinA * tmp1 + cosA * tmp2;
/*      */     
/*  655 */     tmp1 = m.m03;
/*  656 */     tmp2 = m.m23;
/*  657 */     m.m03 = cosA * tmp1 + sinA * tmp2;
/*  658 */     m.m23 = -sinA * tmp1 + cosA * tmp2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatRotateZ(FMatrix m, float rad) {
/*  677 */     if (rad > 2.0F * PI || rad < -2.0F * PI)
/*      */     {
/*  679 */       rad -= 2.0F * PI * (int)(rad / 2.0F * PI);
/*      */     }
/*      */     
/*  682 */     float cosA = Cos(rad);
/*  683 */     float sinA = Sin(rad);
/*      */     
/*  685 */     float tmp1 = m.m00;
/*  686 */     float tmp2 = m.m10;
/*  687 */     m.m00 = cosA * tmp1 - sinA * tmp2;
/*  688 */     m.m10 = sinA * tmp1 + cosA * tmp2;
/*      */     
/*  690 */     tmp1 = m.m01;
/*  691 */     tmp2 = m.m11;
/*  692 */     m.m01 = cosA * tmp1 - sinA * tmp2;
/*  693 */     m.m11 = sinA * tmp1 + cosA * tmp2;
/*      */     
/*  695 */     tmp1 = m.m02;
/*  696 */     tmp2 = m.m12;
/*  697 */     m.m02 = cosA * tmp1 - sinA * tmp2;
/*  698 */     m.m12 = sinA * tmp1 + cosA * tmp2;
/*      */     
/*  700 */     tmp1 = m.m03;
/*  701 */     tmp2 = m.m13;
/*  702 */     m.m03 = cosA * tmp1 - sinA * tmp2;
/*  703 */     m.m13 = sinA * tmp1 + cosA * tmp2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatTurnX(FMatrix m, float rad) {
/*  721 */     if (rad > 2.0F * PI || rad < -2.0F * PI)
/*      */     {
/*  723 */       rad -= 2.0F * PI * (int)(rad / 2.0F * PI);
/*      */     }
/*      */     
/*  726 */     float cosA = Cos(rad);
/*  727 */     float sinA = Sin(rad);
/*      */     
/*  729 */     float tmp1 = m.m01;
/*  730 */     float tmp2 = m.m02;
/*  731 */     m.m01 = cosA * tmp1 + sinA * tmp2;
/*  732 */     m.m02 = -sinA * tmp1 + cosA * tmp2;
/*      */     
/*  734 */     tmp1 = m.m11;
/*  735 */     tmp2 = m.m12;
/*  736 */     m.m11 = cosA * tmp1 + sinA * tmp2;
/*  737 */     m.m12 = -sinA * tmp1 + cosA * tmp2;
/*      */     
/*  739 */     tmp1 = m.m21;
/*  740 */     tmp2 = m.m22;
/*  741 */     m.m21 = cosA * tmp1 + sinA * tmp2;
/*  742 */     m.m22 = -sinA * tmp1 + cosA * tmp2;
/*      */     
/*  744 */     tmp1 = m.m31;
/*  745 */     tmp2 = m.m32;
/*  746 */     m.m31 = cosA * tmp1 + sinA * tmp2;
/*  747 */     m.m32 = -sinA * tmp1 + cosA * tmp2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatTurnY(FMatrix m, float rad) {
/*  765 */     if (rad > 2.0F * PI || rad < -2.0F * PI)
/*      */     {
/*  767 */       rad -= 2.0F * PI * (int)(rad / 2.0F * PI);
/*      */     }
/*      */     
/*  770 */     float cosA = Cos(rad);
/*  771 */     float sinA = Sin(rad);
/*      */     
/*  773 */     float tmp1 = m.m00;
/*  774 */     float tmp2 = m.m02;
/*  775 */     m.m00 = cosA * tmp1 - sinA * tmp2;
/*  776 */     m.m02 = sinA * tmp1 + cosA * tmp2;
/*      */     
/*  778 */     tmp1 = m.m10;
/*  779 */     tmp2 = m.m12;
/*  780 */     m.m10 = cosA * tmp1 - sinA * tmp2;
/*  781 */     m.m12 = sinA * tmp1 + cosA * tmp2;
/*      */     
/*  783 */     tmp1 = m.m20;
/*  784 */     tmp2 = m.m22;
/*  785 */     m.m20 = cosA * tmp1 - sinA * tmp2;
/*  786 */     m.m22 = sinA * tmp1 + cosA * tmp2;
/*      */     
/*  788 */     tmp1 = m.m30;
/*  789 */     tmp2 = m.m32;
/*  790 */     m.m30 = cosA * tmp1 - sinA * tmp2;
/*  791 */     m.m32 = sinA * tmp1 + cosA * tmp2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatTurnZ(FMatrix m, float rad) {
/*  809 */     if (rad > 2.0F * PI || rad < -2.0F * PI)
/*      */     {
/*  811 */       rad -= 2.0F * PI * (int)(rad / 2.0F * PI);
/*      */     }
/*      */     
/*  814 */     float cosA = Cos(rad);
/*  815 */     float sinA = Sin(rad);
/*      */     
/*  817 */     float tmp1 = m.m00;
/*  818 */     float tmp2 = m.m01;
/*  819 */     m.m00 = cosA * tmp1 + sinA * tmp2;
/*  820 */     m.m01 = -sinA * tmp1 + cosA * tmp2;
/*      */     
/*  822 */     tmp1 = m.m10;
/*  823 */     tmp2 = m.m11;
/*  824 */     m.m10 = cosA * tmp1 + sinA * tmp2;
/*  825 */     m.m11 = -sinA * tmp1 + cosA * tmp2;
/*      */     
/*  827 */     tmp1 = m.m20;
/*  828 */     tmp2 = m.m21;
/*  829 */     m.m20 = cosA * tmp1 + sinA * tmp2;
/*  830 */     m.m21 = -sinA * tmp1 + cosA * tmp2;
/*      */     
/*  832 */     tmp1 = m.m30;
/*  833 */     tmp2 = m.m31;
/*  834 */     m.m30 = cosA * tmp1 + sinA * tmp2;
/*  835 */     m.m31 = -sinA * tmp1 + cosA * tmp2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatScale(FMatrix lpM, float scalex, float scaley, float scalez) {
/*  852 */     lpM.m00 = scalex * lpM.m00;
/*  853 */     lpM.m01 = scalex * lpM.m01;
/*  854 */     lpM.m02 = scalex * lpM.m02;
/*  855 */     lpM.m03 = scalex * lpM.m03;
/*      */     
/*  857 */     lpM.m10 = scaley * lpM.m10;
/*  858 */     lpM.m11 = scaley * lpM.m11;
/*  859 */     lpM.m12 = scaley * lpM.m12;
/*  860 */     lpM.m13 = scaley * lpM.m13;
/*      */     
/*  862 */     lpM.m20 = scalez * lpM.m20;
/*  863 */     lpM.m21 = scalez * lpM.m21;
/*  864 */     lpM.m22 = scalez * lpM.m22;
/*  865 */     lpM.m23 = scalez * lpM.m23;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void MatSize(FMatrix lpM, float scalex, float scaley, float scalez) {
/*  882 */     lpM.m00 = scalex * lpM.m00;
/*  883 */     lpM.m01 = scaley * lpM.m01;
/*  884 */     lpM.m02 = scalez * lpM.m02;
/*      */     
/*  886 */     lpM.m10 = scalex * lpM.m10;
/*  887 */     lpM.m11 = scaley * lpM.m11;
/*  888 */     lpM.m12 = scalez * lpM.m12;
/*      */     
/*  890 */     lpM.m20 = scalex * lpM.m20;
/*  891 */     lpM.m21 = scaley * lpM.m21;
/*  892 */     lpM.m22 = scalez * lpM.m22;
/*      */     
/*  894 */     lpM.m30 = scalex * lpM.m30;
/*  895 */     lpM.m31 = scaley * lpM.m31;
/*  896 */     lpM.m32 = scalez * lpM.m32;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FQuat QuatMult(FQuat lpP, FQuat lpQ) {
/*  907 */     FQuat lpR = newQuat();
/*      */ 
/*      */ 
/*      */     
/*  911 */     float pw = lpP.w, px = lpP.x, py = lpP.y, pz = lpP.z;
/*  912 */     float qw = lpQ.w, qx = lpQ.x, qy = lpQ.y, qz = lpQ.z;
/*      */     
/*  914 */     lpR.w = pw * qw - px * qx - py * qy - pz * qz;
/*  915 */     lpR.x = pw * qx + px * qw + py * qz - pz * qy;
/*  916 */     lpR.y = pw * qy - px * qz + py * qw + pz * qx;
/*  917 */     lpR.z = pw * qz + px * qy - py * qx + pz * qw;
/*      */     
/*  919 */     return lpR;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void QuatAdd(FQuat q_out, FQuat q) {
/*  925 */     q_out.w += q.w;
/*  926 */     q_out.x += q.x;
/*  927 */     q_out.y += q.y;
/*  928 */     q_out.z += q.z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static FMatrix QuatToMatrix(FQuat lpQ) {
/*  937 */     FMatrix lpM = newMatrix();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  943 */     float qw = lpQ.w, qx = lpQ.x, qy = lpQ.y, qz = lpQ.z;
/*      */     
/*  945 */     float x2 = 2.0F * qx * qx;
/*  946 */     float y2 = 2.0F * qy * qy;
/*  947 */     float z2 = 2.0F * qz * qz;
/*      */     
/*  949 */     float xy = 2.0F * qx * qy;
/*  950 */     float yz = 2.0F * qy * qz;
/*  951 */     float zx = 2.0F * qz * qx;
/*      */     
/*  953 */     float wx = 2.0F * qw * qx;
/*  954 */     float wy = 2.0F * qw * qy;
/*  955 */     float wz = 2.0F * qw * qz;
/*      */     
/*  957 */     lpM.m00 = 1.0F - y2 - z2;
/*  958 */     lpM.m01 = xy - wz;
/*  959 */     lpM.m02 = zx + wy;
/*  960 */     lpM.m03 = 0.0F;
/*      */     
/*  962 */     lpM.m10 = xy + wz;
/*  963 */     lpM.m11 = 1.0F - z2 - x2;
/*  964 */     lpM.m12 = yz - wx;
/*  965 */     lpM.m13 = 0.0F;
/*      */     
/*  967 */     lpM.m20 = zx - wy;
/*  968 */     lpM.m21 = yz + wx;
/*  969 */     lpM.m22 = 1.0F - x2 - y2;
/*  970 */     lpM.m23 = 0.0F;
/*      */     
/*  972 */     lpM.m30 = lpM.m31 = lpM.m32 = 0.0F;
/*  973 */     lpM.m33 = 1.0F;
/*      */     
/*  975 */     return lpM;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void QuatRotation(FQuat lpQ, float rad, float ax, float ay, float az) {
/*  988 */     float hrad = 0.5F * rad;
/*  989 */     float s = Sin(hrad);
/*      */     
/*  991 */     lpQ.w = Cos(hrad);
/*  992 */     lpQ.x = s * ax;
/*  993 */     lpQ.y = s * ay;
/*  994 */     lpQ.z = s * az;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void QuatIdentity(FQuat lpQ) {
/* 1004 */     lpQ.w = 1.0F;
/* 1005 */     lpQ.x = 0.0F;
/* 1006 */     lpQ.y = 0.0F;
/* 1007 */     lpQ.z = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void QuatCopy(FQuat lpTo, FQuat lpFrom) {
/* 1016 */     lpTo.w = lpFrom.w;
/* 1017 */     lpTo.x = lpFrom.x;
/* 1018 */     lpTo.y = lpFrom.y;
/* 1019 */     lpTo.z = lpFrom.z;
/*      */   }
/*      */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\MCH_Math.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */