package mcheli.mcheli.aircraft;

import mcheli.aircraft.MCH_EntityAircraft;
import mcheli.aircraft.MCH_SeatRackInfo;

public interface MCH_IEntityCanRideAircraft {
  boolean isSkipNormalRender();
  
  boolean canRideAircraft(MCH_EntityAircraft paramMCH_EntityAircraft, int paramInt, MCH_SeatRackInfo paramMCH_SeatRackInfo);
}


/* Location:              C:\Users\danie\Desktop\Mod Porting Tools\MC1.7.10_mcheli_1.0.3.jar!\mcheli\mcheli\aircraft\MCH_IEntityCanRideAircraft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */