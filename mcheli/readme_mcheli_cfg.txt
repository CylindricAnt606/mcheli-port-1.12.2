MC Heli MOD [SSP/SMP]

mcheli.cfg の各項目について説明を記載する。
マルチの場合、多くの項目はサーバ側の設定が優先される。

いつくかの項目は機体のRキーで開く補給画面のMOD Optionから起動したまま変更可能である。
対象の設定には◆マークを付けた。

また、シングルプレイかマルチでOP権限があれば、/mcheli reconfig コマンドで起動したまま
mcheli.cfg を再読み込みできる。

--------------------------------------------------------------------------------------------------------------------

Q : ブロックの破壊や着火を防ぐには？
A : Explosion_DestroyBlock, Explosion_FlamingBlock, Collision_DestroyBlock を false に設定してください。
　　さらに BulletBreakableBlocks を - にしてください。

Q : 航空機からだとモブやモンスターが描画範囲外で表示されない
A : MobRenderDistanceWeight を 3.00 くらいにしてください。描画距離が3倍に伸びます。

Q : 燃料、弾薬を無限にしたい
A : InfinityAmmo, InfinityFuel を true にしてください。

--------------------------------------------------------------------------------------------------------------------

TestMode = false
	モデル開発者用の設定で、true にするとモデルの当たり判定や座席の位置が表示される。
	ゲームを起動中でも機体のRキーで開く補給画面の MOD Option から変更可能。
	◆MOD option > Test Mode
EnableCommand = true
	ヘリMOD用のコマンド(/mcheli ???) の有効無効設定、false にすると使用できなくなる。

PlaceableOnSpongeOnly = false
	true にすると機体をスポンジの上のみに設置できるようにする。マルチでの設置制限用。
ItemDamage = true
	機体をアイテム化したときダメージを引き継ぐかどうかの設定。falseにすると引き継がず、再設置でHPが回復する。
ItemFuel = true
	機体をアイテム化したとき燃料を引き継ぐかどうかの設定。falseにすると引き継がず、再設置で燃料が空になる。
AutoRepairHP = 0.50
	機体の自然回復を行うHPの割合で、0.5は50%を意味し、50%以上HPが残っている場合だけ自然回復する。
	0.0の場合常に回復し、1.0以上の値を設定すると自然回復しなくなる。

Explosion_DestroyBlock = true
	爆発によるブロックの破壊の有効無効設定で、falseにするとブロックが爆発によって壊れなくなる。
Explosion_FlamingBlock = true
	爆発によるブロックへの着火の有効無効設定で、falseにするとブロック火がつかなくなる。
BulletBreakableBlocks = glass_pane, stained_glass_pane, tallgrass, double_plant, yellow_flower, red_flower, vine, wheat, reeds, waterlily
	銃弾が当たった際に破壊するブロック名。 - にするなど、存在しないブロック名の身にするとブロックが破壊されなくなる。
Collision_DestroyBlock = true
	車両がブロックにぶつかった際に破壊するかどうかの設定で、false にするとブロックが破壊されなくなる。
Collision_Car_BreakableBlock = double_plant, glass_pane,stained_glass_pane
	乗用車など比較的軽い車両がブロックにぶつかった際に破壊するブロック名。
Collision_Car_BreakableMaterial = cactus, cake, gourd, leaves, vine, plants
	乗用車など比較的軽い車両がブロックにぶつかった際に破壊するブロックのマテリアル名。
Collision_Tank_BreakableBlock = nether_brick_fence
	戦車など重い車両がブロックにぶつかった際に破壊するブロック名。
Collision_Tank_BreakableMaterial = cactus, cake, carpet, circuits, glass, gourd, leaves, vine, wood, plants
	戦車など重い車両がブロックにぶつかった際に破壊するブロックのマテリアル名。
Collision_EntityDamage = true
	車両がエンティティにぶつかった際にダメージを与えるかどうかの設定で、falseでダメージを無効化できる
Collision_EntityTankDamage = false
	戦車同士がぶつかった際にダメージを与えるかどうかの設定で、trueで戦車にもダメージを与えられるようになる

InfinityAmmo = false
	true にすると弾が無限になる。
InfinityFuel = false
	true にすると燃料がなくても操縦できる。
DismountAll = false
	true にすると機体から降りたときに他のモブも全員降ろす(プレイヤーを除く)。
	
MountMinecartHeli = true
MountMinecartPlane = true
MountMinecartVehicle = false
MountMinecartTank = true
	true にすると機体がマインカートに乗る。ヘリコプター、固定翼機、地上兵器、車両で個別に設定できる。
PreventingBroken = false
	true にすると、設置したらアイテム化できなくなる。
DropItemInCreativeMode = false
	true にすると、クリエイティブモードで機体をたたいた場合にアイテム化する。
BreakableOnlyPickaxe = false
	true にすると、つるはしだけでアイテム化できるようになる。

AllHeliSpeed = 1.00
AllPlaneSpeed = 1.00
AllTankSpeed = 1.00
	全機体の速度の倍率。1.0より大きくすると早くなる。
	1.0より小さくすると遅くなる。

HurtResistantTime = 0.00
	モブにダメージを与えた後の無敵時間の倍率。
	0.00 は無敵時間なし。
StingerLockRange = 320.00
	携帯兵器のロック可能距離。
	描画範囲を超えた値も設定できるが、描画範囲外のエンティティはロックできない。
RangeFinderSpotDist = 400
	レンジファインダーのスポット最大距離。
	大きな値を設定しても、マイクラ自体の仕様で制限される。
RangeFinderSpotTime = 15
	レンジファインダーのスポットマークの表示時間(秒)。
RangeFinderConsume = true
	false にするとレンジファインダーを使用時にバッテリを消費しなくなる。
EnablePutRackInFlying = true
	false にすると、飛行中に機体の積み込みができなくなる。
EnableDebugBoundingBox = true
	false にすると、Ctrl + B で表示される当たり判定（白い四角）が表示されなくなる。
	マルチプレイでの使用を制限したい場合に使う。
	
--------------------------------------------------------------------------------------------------------------------

InvertMouse = false
	true にすると、機体操縦時のマウスの上下を反転する。
	◆MOD option > Invert Mouse
MouseSensitivity = 10.00
	マウス感度の倍率。
	◆MOD option > Sensitivity
MouseControlStickModeHeli = false
MouseControlStickModePlane = false
	true にすると、操縦桿がデフォルトの位置に戻らなくなる。
	◆MOD option > Stick Mode Heli
	◆MOD option > Stick Mode Plane
;MouseControlFlightSimMode = true ( Yaw:key, Roll=mouse )
MouseControlFlightSimMode = false
	true にすると、固定翼機のみマウスでの操作がフライトシミュレータに近くなる。
	◆MOD option > Mouse Flight Sim Mode
AutoThrottleDownHeli = true
AutoThrottleDownPlane = false
AutoThrottleDownTank = false
	trueにすると、Wキーを押していないとスロットルが下がる。false にするとスロットルは自動で下がらない。
	◆MOD option > Throttle Down Heli
	◆MOD option > Throttle Down Plane
	◆MOD option > Throttle Down Tank
SwitchWeaponWithMouseWheel = true
	false にすると、マウススクロールによる武器切り替えができなくなる。
	◆MOD option > Switch Weapon Wheel
	
LWeaponAutoFire = false
	true にすると携帯兵器のみロックオンが完了した時点で発射される。

;DisableItemRender = 0 ~ 3 (1 = Recommended)
DisableItemRender = 1
	機体に乗った時のアイテム表示を無効化する設定。
	0で表示される。非表示にする場合は1以降を設定する。
	ほかのMODと競合する場合は1以外を設定してみると治ることがある。
HideKeybind = false
	画面に表示される操作キーの表示を消す。
	◆MOD option > Render Settings > Hide Key Binding
RenderDistanceWeight = 10.00
	機体の表示距離の倍率。大きくすると表示距離が伸びるが、
	マップがロードされている範囲くらいまでしか伸びない。
MobRenderDistanceWeight = 1.00
	モブの描画距離の倍率を伸ばす。航空機の乗っていると
	モブとの距離が遠すぎて表示されないことがあるので、
	この設定で伸ばすとよい。2.00で2倍。
	
CreativeTabIconItem = fuel
CreativeTabIconHeli = ah-64
CreativeTabIconPlane = f22a
CreativeTabIconTank = merkava_mk4
CreativeTabIconVehicle = mk15
	クリエイティブタブの表示アイテム。お好みで。
DisableShader = false
	true にすると、カメラモードの白黒モードが無効になる。
DefaultExplosionParticle = false
	true にすると、爆発エフェクトをバニラのものに戻す。
	ヘリMODの爆発エフェクトが重いときに使用する。
	◆MOD option > Render Settings > Default Explosion
AliveTimeOfCartridge = 200
	空薬莢の表示時間。重い場合は小さくすること。
;HitMarkColor = Alpha, Red, Green, Blue
HitMarkColor = 255, 255, 0, 0
	エンティティにダメージを与えたときのヒットマークの色。
	◆MOD option > Render Settings > Red/Green/Blue/Alpha
SmoothShading = true
	false にすると、機体のモデルを滑らかに表示する機能を無効化できる。
	描画が重い場合に使う。
	◆MOD option > Render Settings > Smooth Shading
	
EnableModEntityRender = true
	false にすると、機体に乗ったモブの描画処理が変わり、
	機体に合わせて傾くことができなくなる。
	描画系MODと競合する場合に使用する。
DisableRenderLivingSpecials = true
	false にすると、同じ機体に乗っているモブの名前表示がされなくなる。
	前席のプレイヤー名が後席のプレイヤーの視界に入って邪魔になるのを防ぐ設定。
	描画系MODと競合する場合に使用する。
DisplayHUDThirdPerson = false
	true にすると、3人称視点でもHUDが表示される。
	◆MOD option > Render Settings > Show HUD Third Person
DisableThirdPersonCameraDistChange = false
	3人称視点での距離変更機能(PageUp,PageDownキー)を無効化する。
	マルチで制限したい場合に使用する。
EnableReplaceTextureManager = true
	false にすると、カメラモードの白黒モード時にモブが白くなくなる。
	描画系MODと競合する場合に使用する。
DisplayEntityMarker = true
	false にすると、味方マーク/スポットマークの表示をOFFにできる。
	マルチで制限したい場合に使用する。
	◆MOD option > Render Settings > Show HUD Third Person
	注意：マルチプレイ中はMOD option画面から変更しても反映されない。
	反映するにはサーバ側のmcheli.cfgを変更して /mcheli reconfig コマンドを使用すること。
EntityMarkerSize = 10.00
	味方マーク/スポットマークの表示サイズ。
	◆MOD option > Render Settings > Entity Marker Size
BlockMarkerSize = 6.00
	ブロックマークの表示サイズ。
	◆MOD option > Render Settings > Block Marker Size
ReplaceRenderViewEntity = true
	false にすると、カメラ位置の変更機能が無効になり、常にプレイヤー目線になる。
	描画系MODと競合する場合に使用する。
	◆MOD option > Render Settings > Change Camera Pos
	
--------------------------------------------------------------------------------------------------------------------

ItemRecipe_Fuel = "ICI", "III", I, iron_ingot, C, coal
ItemRecipe_GLTD = " B ", "IDI", "IRI", B, iron_block, I, iron_ingot, D, diamond, R, redstone
ItemRecipe_Chain = "I I", "III", "I I", I, iron_ingot
ItemRecipe_Parachute = "WWW", "S S", " W ", W, wool, S, string
ItemRecipe_Container = "CCI", C, chest, I, iron_ingot
ItemRecipe_UavStation = "III", "IDI", "IRI", I, iron_ingot, D, diamond, R, redstone_block
ItemRecipe_UavStation2 = "IDI", "IRI", I, iron_ingot, D, diamond, R, redstone
ItemRecipe_DraftingTable = "R  ", "PCP", "F F", R, redstone, C, crafting_table, P, planks, F, fence
ItemRecipe_Wrench = " I ", " II", "I  ", I, iron_ingot
ItemRecipe_RangeFinder = "III", "RGR", "III", I, iron_ingot, G, glass, R, redstone
ItemRecipe_Stinger = "G  ", "III", "RI ", G, glass, I, iron_ingot, R, redstone
ItemRecipe_StingerMissile = "R  ", " I ", "  G", G, gunpowder, I, iron_ingot, R, redstone
ItemRecipe_Javelin = "III", "GR ", G, glass, I, iron_ingot, R, redstone
ItemRecipe_JavelinMissile = " R ", " I ", " G ", G, gunpowder, I, iron_ingot, R, redstone

--------------------------------------------------------------------------------------------------------------------

DamageVsEntity = 1.0
	MCHeliの機体 → マインカートなどHeliMOD以外のエンティティ に与えるダメージの倍率。
DamageVsLiving = 1.0
	MCHeliの機体 → モブ に与えるダメージの倍率。
DamageVsPlayer = 1.0
	MCHeliの機体 → プレイヤー に与えるダメージの倍率。
	
DamageVsMCHeliAircraft = 1.0
	MCHeliの機体 → MCHeliの ヘリコプターまたは固定翼機 に与えるダメージの倍率。
DamageVsMCHeliTank = 1.0
	MCHeliの機体 → MCHeliの 戦車 に与えるダメージの倍率。
DamageVsMCHeliVehicle = 1.0
	MCHeliの機体 → MCHeliの 地上兵器 に与えるダメージの倍率。
DamageVsMCHeliOther = 1.0
	MCHeliの機体 → MCHeliの 上記以外のエンティティ に与えるダメージの倍率。

DamageMCHeliAircraftByExternal = 1.0
	モブや他のMOD、プレイヤーなど → MCHeliのヘリコプターまたは固定翼機 に与えるダメージの倍率。
DamageMCHeliTankByExternal = 1.0
	モブや他のMOD、プレイヤーなど → MCHeliの戦車 に与えるダメージの倍率。
DamageMCHeliVehicleByExternal = 1.0
	モブや他のMOD、プレイヤーなど → MCHeliの地上兵器 に与えるダメージの倍率。
DamageMCHeliOtherByExternal = 1.0
	モブや他のMOD、プレイヤーなど → MCHeliの他のエンティティ に与えるダメージの倍率。
	
DamageVsEntity = 3.0, flansmod
DamageMCHeliAircraftByExternal = 0.5, flansmod
DamageMCHeliVehicleByExternal = 0.5, flansmod
	DamageVsEntity にエンティティのクラス名を入れると、特定のエンティティのダメージ倍率を設定できる。

IgnoreBulletHit = flansmod.common.guns.EntityBullet
	指定した名前のエンティティに弾が当たらないようにする。

--------------------------------------------------------------------------------------------------------------------

;CommandPermission = commandName(eg, modlist, status, fill...):playerName1, playerName2, playerName3...
;CommandPermission = modlist :example1, example2
;CommandPermission = status :  example2
OP権限がないプレイヤーにも /mcheli のコマンドを使用できるようにする。
CommandPermission = 許可するコマンド名 :  プレイヤー名1, プレイヤー名2 ...
注意1：この設定で許可しても PermissionsEx などのプラグインで無効化されることがある。
注意2：コマンドのうち fill は1.8のfillコマンドの強化版で、ブロック置き換え上限が300万である。
　　　うかつに許可を出すとマップを真っ平らにされる懸念がある。許可はよく検討すること。
注意3：コマンドのうち killentity,removeentity,attackentity はプレイヤーには効果がないものの、
　　　ロードされている全エンティティを殺すことができる。許可はよく検討すること。

--------------------------------------------------------------------------------------------------------------------

[Key config]
http://minecraft.gamepedia.com/Key_codes

KeyUp = 17
KeyDown = 31
KeyRight = 32
KeyLeft = 30
KeySwitchGunner = 35
KeySwitchHovering = 57
KeySwitchWeapon1 = -98
KeySwitchWeapon2 = 34
KeySwitchWeaponMode = 45
KeyZoom = 44
KeyCameraMode = 46
KeyUnmountMob = 21
KeyFlare = 47
KeyExtra = 33
KeyCameraDistanceUp = 201
KeyCameraDistanceDown = 209
KeyFreeLook = 29
KeyGUI = 19
KeyGearUpDown = 48
KeyPutToRack = 36
KeyDownFromRack = 22
KeyScoreboard = 38
KeyMultiplayManager = 50
	キー設定。機体のRキーの補給画面のMOD Optionから変更可能なので、
	そこで設定することを推奨する。
	◆MOD option > Key Binding
