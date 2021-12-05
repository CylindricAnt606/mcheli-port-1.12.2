/*     */ package mcheli.mcheli.wrapper;
/*     */ 
/*     */ import mcheli.wrapper.W_Sound;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.SoundHandler;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class W_SoundUpdater
/*     */ {
/*     */   protected final SoundHandler theSoundHnadler;
/*     */   protected W_Sound es;
/*     */   
/*     */   public W_SoundUpdater(Minecraft minecraft, Entity entity) {
/*  24 */     this.theSoundHnadler = minecraft.func_147118_V();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initEntitySound(String name) {
/*  32 */     this.es = new W_Sound(new ResourceLocation("mcheli", name), 1.0F, 1.0F);
/*     */     
/*  34 */     this.es.setRepeat(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidSound() {
/*  40 */     return (this.es != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playEntitySound(String name, Entity entity, float volume, float pitch, boolean par5) {
/*  49 */     if (isValidSound()) { this.es.setSoundParam(entity, volume, pitch); this.theSoundHnadler.func_147682_a((ISound)this.es); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopEntitySound(Entity entity) {
/*  56 */     if (isValidSound()) this.theSoundHnadler.func_147683_b((ISound)this.es);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEntitySoundPlaying(Entity entity) {
/*  64 */     return isValidSound() ? this.theSoundHnadler.func_147692_c((ISound)this.es) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntitySoundPitch(Entity entity, float pitch) {
/*  71 */     if (isValidSound()) this.es.setPitch(pitch);
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEntitySoundVolume(Entity entity, float volume) {
/*  77 */     if (isValidSound()) this.es.setVolume(volume);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSoundLocation(Entity entity) {
/*  85 */     if (isValidSound()) this.es.setPosition(entity);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSoundLocation(double x, double y, double z) {
/*  93 */     if (isValidSound()) this.es.setPosition(x, y, z);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void _updateSoundLocation(Entity entityListener, Entity entity) {
/* 100 */     if (isValidSound()) this.es.setPosition(entity); 
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\wrapper\W_SoundUpdater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */