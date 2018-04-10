package server.model.players.packets;

import server.model.items.UseItem;
import server.model.players.Client;
import server.model.players.PacketType;

public class ItemOnObject implements PacketType {

	@SuppressWarnings("unused")
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		
		int a = c.getInStream().readUnsignedWord();
		int objectId = c.getInStream().readSignedWordBigEndian();
		int objectY = c.getInStream().readSignedWordBigEndianA();
		int b = c.getInStream().readUnsignedWord();
		int objectX = c.getInStream().readSignedWordBigEndianA();
		int itemId = c.getInStream().readUnsignedWord();
		UseItem.ItemonObject(c, objectId, objectX, objectY, itemId);

		if (itemId == 12158 && objectId == 2149) { // Gold Charm
				c.sendMessage("You used the gold charm on the obelisk!");
				c.getItems().deleteItem(12158, c.getItems().getItemSlot(12158), 1);
				c.getPA().addSkillXP(5500, 22);
			}
		if (itemId == 12159 && objectId == 2149) { // Green Charm
				c.sendMessage("You used the green charm on the obelisk!");
				c.getItems().deleteItem(12159, c.getItems().getItemSlot(12159), 1);
				c.getPA().addSkillXP(10200, 22);
			}
		if (itemId == 12160 && objectId == 2149) { // Crimson Charm
				c.sendMessage("You used the crimson charm on the obelisk!");
				c.getItems().deleteItem(12160, c.getItems().getItemSlot(12160), 1);
				c.getPA().addSkillXP(15200, 22);
			}
		if (itemId == 12163 && objectId == 2149) { // Blue Charm
				c.sendMessage("You used the blue charm on the obelisk!");
				c.getItems().deleteItem(12163, c.getItems().getItemSlot(12163), 1);
				c.getPA().addSkillXP(25700, 22);
			}

}
}
