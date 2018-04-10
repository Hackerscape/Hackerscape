package server.model.players.packets;

import server.util.Misc;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.Player;
import server.Server;


/**
 * Clicking an item, bury bone, eat food etc
 **/
@SuppressWarnings("unused")
public class ClickItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
			
		}
                /*if(itemId == 8007) {
				if(!c.InDung);
					c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
				if (itemId == 15098 && System.currentTimeMillis() - c.diceDelay >= 1200) { //Dice Bag ID
				if (c.clanId >= 0);
				Server.clanChat.playerMessageToClan(c.playerId, "I Have Rolled A "+ Misc.random(100) +" On The Percentile Dice ", c.clanId);
				c.startAnimation(11900);
				c.gfx0(2075);
				c.diceDelay = System.currentTimeMillis();
			} else {
			if (c.clanId != -1)
			return;

			}*/

                if(itemId == 8008) {
				if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
				if (itemId > 15085 && itemId < 15102){
			c.useDice(itemId, false);
		}
		if (itemId == 15084){
			c.getDH().sendDialogues(106, 4289);
		}
				if(itemId == 15707) {
                   c.getPA().startTeleport(2417, 3526, 0, "dungtele");
				   c.sendMessage("Your Ring of Kinship takes you to Dungeoneering.");
                }
              if(itemId == 8009) {
			  if(!c.InDung);
					c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
            if(itemId == 8010) {
			if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
          if(itemId == 8011) {
		  if(!c.InDung);
				c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
				}
          if(itemId == 8012) {
		  if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
          if(itemId == 8013) {
		  if(!c.InDung);
                   c.sendMessage("Teletabbing is Disabled, Use the Teleporting Interface.");
                }
		if(itemId == 4447) {	
			c.getPA().addSkillXP(3000, 24);
			c.sendMessage("You rub the lamp and feel yourself further in the arts of dungeoneering.");
			c.getItems().deleteItem(4447, 1);	
		}
		if(itemId == 15262) {
			c.getItems().addItem(18016, 10000);
			c.getItems().deleteItem(15262, 1);
		}

if(itemId == 962) {
c.getItems().deleteItem(962, 1);
c.getItems().addItem(c.getPA().randomCracker(), 1);
c.sendMessage("You just opened this cracker!");
}
			
		if (itemId == 15272) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(15272, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 23;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3] + 10);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Rocktail.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}
		if (itemId == 2528) {
		c.getPA().showInterface(2808);
		c.lampxp = 2500000;
		c.lampused = 2528;
		}
		if (itemId == 11850) {
		c.getItems().deleteItem(11850,1);
		c.getItems().addItem(4724,1);
		c.getItems().addItem(4726,1);
		c.getItems().addItem(4728,1);
		c.getItems().addItem(4730,1);
		}
		if (itemId == 11852) {
		c.getItems().deleteItem(11852,1);
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		}
		if (itemId == 11854) {
		c.getItems().deleteItem(11854,1);
		c.getItems().addItem(4745,1);
		c.getItems().addItem(4747,1);
		c.getItems().addItem(4749,1);
		c.getItems().addItem(4751,1);
		}
		if (itemId == 11856) {
		c.getItems().deleteItem(11856,1);
		c.getItems().addItem(4732,1);
		c.getItems().addItem(4734,1);
		c.getItems().addItem(4736,1);
		c.getItems().addItem(4738,1);
		}
		if (itemId == 11848) {
		c.getItems().deleteItem(11848,1);
		c.getItems().addItem(4716,1);
		c.getItems().addItem(4718,1);
		c.getItems().addItem(4720,1);
		c.getItems().addItem(4722,1);
		}
		if (itemId == 11846) {
		c.getItems().deleteItem(11846,1);
		c.getItems().addItem(4708,1);
		c.getItems().addItem(4710,1);
		c.getItems().addItem(4712,1);
		c.getItems().addItem(4714,1);
		}
		//Begin artifacts by Hirukos
		if (itemId >= 14876 && itemId <= 14892) {
			int a = itemId;
			String YEYAF = "<col=1532693>You Exchanged Your Artifact For</col> ";
			if (a == 14876){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000000);
			c.sendMessage(YEYAF + "<col=1532693>10 million Coins!</col>");
			}
			if (a == 14877){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,2000000);
			c.sendMessage(YEYAF + "<col=1532693>2 million Coins!</col>");
			}
			if (a == 14878){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1500000);
			c.sendMessage(YEYAF + "<col=1532693>1.5 million Coins!</col>");
			}
			if (a == 14879){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1000000);
			c.sendMessage(YEYAF + "<col=1532693>1 million Coins!</col>");
			}
			if (a == 14880){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,800000);
			c.sendMessage(YEYAF + "<col=1532693>800,000 Coins!</col>");
			}
			if (a == 14881){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,600000);
			c.sendMessage(YEYAF + "<col=1532693>600,000 Coins!</col>");
			}
			if (a == 14882){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,540000);
			c.sendMessage(YEYAF + "<col=1532693>540,000 Coins!</col>");
			}
			if (a == 14883){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,400000);
			c.sendMessage(YEYAF + "<col=1532693>400,000 Coins!</col>");
			}
			if (a == 14884){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,300000);
			c.sendMessage(YEYAF + "<col=1532693>300,000 Coins!</col>");
			}
			if (a == 14885){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,200000);
			c.sendMessage(YEYAF + "<col=1532693>200,000 Coins!</col>");
			}
			if (a == 14886){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,150000);
			c.sendMessage(YEYAF + "<col=1532693>150,000 Coins!</col>");
			}
			if (a == 14887){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,100000);
			c.sendMessage(YEYAF + "<col=1532693>100,000 Coins!</col>");
			}
			if (a == 14888){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,80000);
			c.sendMessage(YEYAF + "<col=1532693>80,000 Coins!</col>");
			}
			if (a == 14889){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,60000);
			c.sendMessage(YEYAF + "<col=1532693>60,000 Coins!</col>");
			}
			if (a == 14890){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,40000);
			c.sendMessage(YEYAF + "<col=1532693>40,000 Coins!</col>");
			}
			if (a == 14891){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,20000);
			c.sendMessage(YEYAF + "<col=1532693>20,000 Coins!</col>");
			} 
			if (a == 14892){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000);
			c.sendMessage(YEYAF + "<col=1532693>10,000 Coins!</col>");
			}
			
		}
		//End of artifacts By Hirukos
		
		
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		if (c.getHerblore().isUnidHerb(itemId))
			c.getHerblore().handleHerbClick(itemId);
		if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);
		if (c.getPrayer().isBone(itemId))
			c.getPrayer().buryBone(itemId, itemSlot);
		if (itemId == 952) {
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			} else if(c.inArea(2986, 3370, 3013, 3388)) {
				c.teleTimer = 3;
				c.newLocation = 7;
			}
		}
	}

}
