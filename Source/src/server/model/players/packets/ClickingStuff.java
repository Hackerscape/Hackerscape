package server.model.players.packets;

import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;


/**
 * Clicking stuff (interfaces)
 **/
public class ClickingStuff implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		if (c.inTrade) {
			if (!c.acceptedTrade) {
				Client o = (Client) Server.playerHandler.players[c.tradeWith];
				o.tradeAccepted = false;
				c.tradeAccepted = false;
				o.tradeStatus = 0;
				c.tradeStatus = 0;
				c.tradeConfirmed = false;
				c.tradeConfirmed2 = false;
				//c.sendMessage("@red@Trade has been declined.");
				//o.sendMessage("@red@Other player has declined the trade.");
				Misc.println("trade reset");
				c.getTradeAndDuel().declineTrade();
			}
		}
		//if (c.isBanking)
		//c.isBanking = false;

		Client o = (Client) Server.playerHandler.players[c.duelingWith];
			if (c.duelStatus == 5) {
				//c.sendMessage("This glitch has been fixed by Ardi, sorry sir.");
				return;
			}
		if(o != null) {
			if(c.duelStatus >= 1 && c.duelStatus <= 4) {
				c.getTradeAndDuel().declineDuel();
				o.getTradeAndDuel().declineDuel();
			}
		}
		
		if(c.duelStatus == 6) {
			c.getTradeAndDuel().claimStakedItems();		
		}
	
	}
		
}