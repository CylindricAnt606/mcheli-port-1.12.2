/*     */ package mcheli.mcheli.eval.eval;
/*     */ import mcheli.eval.eval.Rule;
/*     */ import mcheli.eval.eval.exp.AbstractExpression;
/*     */ import mcheli.eval.eval.exp.BitNotExpression;
/*     */ import mcheli.eval.eval.exp.DivExpression;
/*     */ import mcheli.eval.eval.exp.FieldExpression;
/*     */ import mcheli.eval.eval.exp.IfExpression;
/*     */ import mcheli.eval.eval.exp.IncBeforeExpression;
/*     */ import mcheli.eval.eval.exp.LetExpression;
/*     */ import mcheli.eval.eval.exp.LetMinusExpression;
/*     */ import mcheli.eval.eval.exp.LetPlusExpression;
/*     */ import mcheli.eval.eval.exp.LetPowerExpression;
/*     */ import mcheli.eval.eval.exp.LetShiftLeftExpression;
/*     */ import mcheli.eval.eval.exp.LetShiftRightExpression;
/*     */ import mcheli.eval.eval.exp.LetShiftRightLogicalExpression;
/*     */ import mcheli.eval.eval.exp.ModExpression;
/*     */ import mcheli.eval.eval.exp.ShiftLeftExpression;
/*     */ import mcheli.eval.eval.exp.SignMinusExpression;
/*     */ import mcheli.eval.eval.lex.LexFactory;
/*     */ import mcheli.eval.eval.rule.AbstractRule;
/*     */ import mcheli.eval.eval.rule.Col1AfterRule;
/*     */ import mcheli.eval.eval.rule.Col2RightJoinRule;
/*     */ import mcheli.eval.eval.rule.Col2Rule;
/*     */ import mcheli.eval.eval.rule.IfRule;
/*     */ import mcheli.eval.eval.rule.PrimaryRule;
/*     */ import mcheli.eval.eval.rule.ShareRuleValue;
/*     */ import mcheli.eval.eval.rule.SignRule;
/*     */ 
/*     */ public class ExpRuleFactory {
/*     */   public static mcheli.eval.eval.ExpRuleFactory getInstance() {
/*  31 */     if (me == null) {
/*  32 */       me = new mcheli.eval.eval.ExpRuleFactory();
/*     */     }
/*  34 */     return me;
/*     */   }
/*     */ 
/*     */   
/*     */   private static mcheli.eval.eval.ExpRuleFactory me;
/*     */   
/*     */   protected Rule rule;
/*     */   
/*     */   protected AbstractRule topRule;
/*     */   protected LexFactory defaultLexFactory;
/*     */   
/*     */   public static Rule getDefaultRule() {
/*  46 */     return getInstance().getRule();
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
/*     */   public static Rule getJavaRule() {
/*  59 */     return JavaRuleFactory.getInstance().getRule();
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
/*     */   public ExpRuleFactory() {
/*  71 */     ShareRuleValue share = new ShareRuleValue();
/*  72 */     share.lexFactory = getLexFactory();
/*  73 */     init(share);
/*  74 */     this.rule = (Rule)share;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rule getRule() {
/*  83 */     return this.rule;
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
/*     */   protected void init(ShareRuleValue share) {
/* 101 */     AbstractRule rule = null;
/*     */     
/* 103 */     rule = add(rule, createCommaRule(share));
/* 104 */     rule = add(rule, createLetRule(share));
/* 105 */     rule = add(rule, createIfRule(share));
/* 106 */     rule = add(rule, createOrRule(share));
/* 107 */     rule = add(rule, createAndRule(share));
/* 108 */     rule = add(rule, createBitOrRule(share));
/* 109 */     rule = add(rule, createBitXorRule(share));
/* 110 */     rule = add(rule, createBitAndRule(share));
/* 111 */     rule = add(rule, createEqualRule(share));
/* 112 */     rule = add(rule, createGreaterRule(share));
/* 113 */     rule = add(rule, createShiftRule(share));
/* 114 */     rule = add(rule, createPlusRule(share));
/* 115 */     rule = add(rule, createMultRule(share));
/* 116 */     rule = add(rule, createSignRule(share));
/* 117 */     rule = add(rule, createPowerRule(share));
/* 118 */     rule = add(rule, createCol1AfterRule(share));
/* 119 */     rule = add(rule, createPrimaryRule(share));
/* 120 */     this.topRule.initPriority(1);
/* 121 */     share.topRule = this.topRule;
/*     */ 
/*     */     
/* 124 */     initFuncArgRule(share);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initFuncArgRule(ShareRuleValue share) {
/* 133 */     AbstractRule argRule = share.funcArgRule = createFuncArgRule(share);
/*     */     
/* 135 */     String[] a_opes = argRule.getOperators();
/* 136 */     String[] t_opes = this.topRule.getOperators();
/*     */     
/* 138 */     boolean match = false; int i;
/* 139 */     label20: for (i = 0; i < a_opes.length; i++) {
/* 140 */       for (int j = 0; j < t_opes.length; j++) {
/* 141 */         if (a_opes[i].equals(t_opes[j])) {
/* 142 */           match = true;
/*     */           break label20;
/*     */         } 
/*     */       } 
/*     */     } 
/* 147 */     if (match) {
/*     */       
/* 149 */       argRule.nextRule = this.topRule.nextRule;
/*     */     } else {
/*     */       
/* 152 */       argRule.nextRule = this.topRule;
/*     */     } 
/* 154 */     argRule.prio = this.topRule.prio;
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
/*     */   protected final AbstractRule add(AbstractRule rule, AbstractRule r) {
/* 167 */     if (r == null)
/* 168 */       return rule; 
/* 169 */     if (this.topRule == null) {
/* 170 */       this.topRule = r;
/*     */     }
/* 172 */     if (rule != null) {
/* 173 */       rule.nextRule = r;
/*     */     }
/* 175 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createCommaRule(ShareRuleValue share) {
/* 185 */     Col2Rule col2Rule = new Col2Rule(share);
/* 186 */     col2Rule.addExpression(createCommaExpression());
/* 187 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createCommaExpression() {
/* 191 */     return (AbstractExpression)new CommaExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createLetRule(ShareRuleValue share) {
/* 201 */     Col2RightJoinRule col2RightJoinRule = new Col2RightJoinRule(share);
/* 202 */     col2RightJoinRule.addExpression(createLetExpression());
/* 203 */     col2RightJoinRule.addExpression(createLetMultExpression());
/* 204 */     col2RightJoinRule.addExpression(createLetDivExpression());
/* 205 */     col2RightJoinRule.addExpression(createLetModExpression());
/* 206 */     col2RightJoinRule.addExpression(createLetPlusExpression());
/* 207 */     col2RightJoinRule.addExpression(createLetMinusExpression());
/* 208 */     col2RightJoinRule.addExpression(createLetShiftLeftExpression());
/* 209 */     col2RightJoinRule.addExpression(createLetShiftRightExpression());
/* 210 */     col2RightJoinRule.addExpression(createLetShiftRightLogicalExpression());
/* 211 */     col2RightJoinRule.addExpression(createLetAndExpression());
/* 212 */     col2RightJoinRule.addExpression(createLetOrExpression());
/* 213 */     col2RightJoinRule.addExpression(createLetXorExpression());
/* 214 */     col2RightJoinRule.addExpression(createLetPowerExpression());
/* 215 */     return (AbstractRule)col2RightJoinRule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetExpression() {
/* 219 */     return (AbstractExpression)new LetExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetMultExpression() {
/* 223 */     return (AbstractExpression)new LetMultExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetDivExpression() {
/* 227 */     return (AbstractExpression)new LetDivExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetModExpression() {
/* 231 */     return (AbstractExpression)new LetModExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetPlusExpression() {
/* 235 */     return (AbstractExpression)new LetPlusExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetMinusExpression() {
/* 239 */     return (AbstractExpression)new LetMinusExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetShiftLeftExpression() {
/* 243 */     return (AbstractExpression)new LetShiftLeftExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetShiftRightExpression() {
/* 247 */     return (AbstractExpression)new LetShiftRightExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetShiftRightLogicalExpression() {
/* 251 */     return (AbstractExpression)new LetShiftRightLogicalExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetAndExpression() {
/* 255 */     return (AbstractExpression)new LetAndExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetOrExpression() {
/* 259 */     return (AbstractExpression)new LetOrExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetXorExpression() {
/* 263 */     return (AbstractExpression)new LetXorExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLetPowerExpression() {
/* 267 */     return (AbstractExpression)new LetPowerExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createIfRule(ShareRuleValue share) {
/* 277 */     IfRule me = new IfRule(share);
/* 278 */     me.addExpression(me.cond = createIfExpression());
/* 279 */     return (AbstractRule)me;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createIfExpression() {
/* 283 */     return (AbstractExpression)new IfExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createOrRule(ShareRuleValue share) {
/* 293 */     Col2Rule col2Rule = new Col2Rule(share);
/* 294 */     col2Rule.addExpression(createOrExpression());
/* 295 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createOrExpression() {
/* 299 */     return (AbstractExpression)new OrExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createAndRule(ShareRuleValue share) {
/* 309 */     Col2Rule col2Rule = new Col2Rule(share);
/* 310 */     col2Rule.addExpression(createAndExpression());
/* 311 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createAndExpression() {
/* 315 */     return (AbstractExpression)new AndExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createBitOrRule(ShareRuleValue share) {
/* 325 */     Col2Rule col2Rule = new Col2Rule(share);
/* 326 */     col2Rule.addExpression(createBitOrExpression());
/* 327 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createBitOrExpression() {
/* 331 */     return (AbstractExpression)new BitOrExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createBitXorRule(ShareRuleValue share) {
/* 341 */     Col2Rule col2Rule = new Col2Rule(share);
/* 342 */     col2Rule.addExpression(createBitXorExpression());
/* 343 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createBitXorExpression() {
/* 347 */     return (AbstractExpression)new BitXorExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createBitAndRule(ShareRuleValue share) {
/* 357 */     Col2Rule col2Rule = new Col2Rule(share);
/* 358 */     col2Rule.addExpression(createBitAndExpression());
/* 359 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createBitAndExpression() {
/* 363 */     return (AbstractExpression)new BitAndExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createEqualRule(ShareRuleValue share) {
/* 373 */     Col2Rule col2Rule = new Col2Rule(share);
/* 374 */     col2Rule.addExpression(createEqualExpression());
/* 375 */     col2Rule.addExpression(createNotEqualExpression());
/* 376 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createEqualExpression() {
/* 380 */     return (AbstractExpression)new EqualExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createNotEqualExpression() {
/* 384 */     return (AbstractExpression)new NotEqualExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createGreaterRule(ShareRuleValue share) {
/* 394 */     Col2Rule col2Rule = new Col2Rule(share);
/* 395 */     col2Rule.addExpression(createLessThanExpression());
/* 396 */     col2Rule.addExpression(createLessEqualExpression());
/* 397 */     col2Rule.addExpression(createGreaterThanExpression());
/* 398 */     col2Rule.addExpression(createGreaterEqualExpression());
/* 399 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLessThanExpression() {
/* 403 */     return (AbstractExpression)new LessThanExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createLessEqualExpression() {
/* 407 */     return (AbstractExpression)new LessEqualExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createGreaterThanExpression() {
/* 411 */     return (AbstractExpression)new GreaterThanExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createGreaterEqualExpression() {
/* 415 */     return (AbstractExpression)new GreaterEqualExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createShiftRule(ShareRuleValue share) {
/* 425 */     Col2Rule col2Rule = new Col2Rule(share);
/* 426 */     col2Rule.addExpression(createShiftLeftExpression());
/* 427 */     col2Rule.addExpression(createShiftRightExpression());
/* 428 */     col2Rule.addExpression(createShiftRightLogicalExpression());
/* 429 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createShiftLeftExpression() {
/* 433 */     return (AbstractExpression)new ShiftLeftExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createShiftRightExpression() {
/* 437 */     return (AbstractExpression)new ShiftRightExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createShiftRightLogicalExpression() {
/* 441 */     return (AbstractExpression)new ShiftRightLogicalExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createPlusRule(ShareRuleValue share) {
/* 451 */     Col2Rule col2Rule = new Col2Rule(share);
/* 452 */     col2Rule.addExpression(createPlusExpression());
/* 453 */     col2Rule.addExpression(createMinusExpression());
/* 454 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createPlusExpression() {
/* 458 */     return (AbstractExpression)new PlusExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createMinusExpression() {
/* 462 */     return (AbstractExpression)new MinusExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createMultRule(ShareRuleValue share) {
/* 472 */     Col2Rule col2Rule = new Col2Rule(share);
/* 473 */     col2Rule.addExpression(createMultExpression());
/* 474 */     col2Rule.addExpression(createDivExpression());
/* 475 */     col2Rule.addExpression(createModExpression());
/* 476 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createMultExpression() {
/* 480 */     return (AbstractExpression)new MultExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createDivExpression() {
/* 484 */     return (AbstractExpression)new DivExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createModExpression() {
/* 488 */     return (AbstractExpression)new ModExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createSignRule(ShareRuleValue share) {
/* 498 */     SignRule signRule = new SignRule(share);
/* 499 */     signRule.addExpression(createSignPlusExpression());
/* 500 */     signRule.addExpression(createSignMinusExpression());
/* 501 */     signRule.addExpression(createBitNotExpression());
/* 502 */     signRule.addExpression(createNotExpression());
/* 503 */     signRule.addExpression(createIncBeforeExpression());
/* 504 */     signRule.addExpression(createDecBeforeExpression());
/* 505 */     return (AbstractRule)signRule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createSignPlusExpression() {
/* 509 */     return (AbstractExpression)new SignPlusExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createSignMinusExpression() {
/* 513 */     return (AbstractExpression)new SignMinusExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createBitNotExpression() {
/* 517 */     return (AbstractExpression)new BitNotExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createNotExpression() {
/* 521 */     return (AbstractExpression)new NotExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createIncBeforeExpression() {
/* 525 */     return (AbstractExpression)new IncBeforeExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createDecBeforeExpression() {
/* 529 */     return (AbstractExpression)new DecBeforeExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createPowerRule(ShareRuleValue share) {
/* 539 */     Col2Rule col2Rule = new Col2Rule(share);
/* 540 */     col2Rule.addExpression(createPowerExpression());
/* 541 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createPowerExpression() {
/* 545 */     return (AbstractExpression)new PowerExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createCol1AfterRule(ShareRuleValue share) {
/* 555 */     Col1AfterRule me = new Col1AfterRule(share);
/* 556 */     me.addExpression(me.func = createFunctionExpression());
/* 557 */     me.addExpression(me.array = createArrayExpression());
/* 558 */     me.addExpression(createIncAfterExpression());
/* 559 */     me.addExpression(createDecAfterExpression());
/* 560 */     me.addExpression(me.field = createFieldExpression());
/* 561 */     return (AbstractRule)me;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createFunctionExpression() {
/* 565 */     return (AbstractExpression)new FunctionExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createArrayExpression() {
/* 569 */     return (AbstractExpression)new ArrayExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createIncAfterExpression() {
/* 573 */     return (AbstractExpression)new IncAfterExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createDecAfterExpression() {
/* 577 */     return (AbstractExpression)new DecAfterExpression();
/*     */   }
/*     */   
/*     */   protected AbstractExpression createFieldExpression() {
/* 581 */     return (AbstractExpression)new FieldExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createPrimaryRule(ShareRuleValue share) {
/* 591 */     PrimaryRule primaryRule = new PrimaryRule(share);
/* 592 */     primaryRule.addExpression(createParenExpression());
/*     */ 
/*     */     
/* 595 */     return (AbstractRule)primaryRule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createParenExpression() {
/* 599 */     return (AbstractExpression)new ParenExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractRule createFuncArgRule(ShareRuleValue share) {
/* 609 */     Col2Rule col2Rule = new Col2Rule(share);
/* 610 */     col2Rule.addExpression(createFuncArgExpression());
/* 611 */     return (AbstractRule)col2Rule;
/*     */   }
/*     */   
/*     */   protected AbstractExpression createFuncArgExpression() {
/* 615 */     return (AbstractExpression)new FuncArgExpression();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected LexFactory getLexFactory() {
/* 621 */     if (this.defaultLexFactory == null) {
/* 622 */       this.defaultLexFactory = new LexFactory();
/*     */     }
/* 624 */     return this.defaultLexFactory;
/*     */   }
/*     */ }


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\eval\eval\ExpRuleFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */