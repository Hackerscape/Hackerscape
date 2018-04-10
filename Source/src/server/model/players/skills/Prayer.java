package server.model.players.skills;


import server.Config;
import server.Server;
import server.model.players.Client;
import server.util.Misc;
@SuppressWarnings("unused")
public class Prayer {

	Client c;
	
	public int[][] bonesExp = {{526,11},{532,29},{534,70},{536,175},{6729,300},{18830,760}};	
	
	public Prayer(Client c) {
		this.c = c;
	}
	
	public void buryBone(int id, int slot) {
		if(System.currentTimeMillis() - c.buryDelay > 1500) {
			c.getItems().deleteItem(id, slot, 1);
			c.sendMessage("You bury the bones.");
			c.getPA().addSkillXP(getExp(id)*Config.PRAYER_EXPERIENCE,5);
			c.buryDelay = System.currentTimeMillis();
			c.startAnimation(827);
			//handleZombie();
		}	
	}
		/*public void handleZombie() {
		int random = Misc.random(50);
		if(random == 50) {
			if(c.combatLevel >= 3 && c.combatLevel <= 25) {
				Server.npcHandler.spawnNpc(c, 419, c.getX(), c.getY()-1, 0, 0, 120, 5, 50, 50, true, false);
			} else if(c.combatLevel >= 26 && c.combatLevel <= 50) {
				Server.npcHandler.spawnNpc(c, 421, c.getX(), c.getY()-1, 0, 0, 120, 8, 75, 75, true, false);
			} else if(c.combatLevel >= 51 && c.combatLevel <= 99) {
				Server.npcHandler.spawnNpc(c, 422, c.getX(), c.getY()-1, 0, 0, 120, 13, 120, 120, true, false);
			} else if(c.combatLevel >= 100 && c.combatLevel < 126) {
				Server.npcHandler.spawnNpc(c, 423, c.getX(), c.getY()-1, 0, 0, 120, 18, 175, 175, true, false);
			} else if(c.combatLevel == 126) {
				Server.npcHandler.spawnNpc(c, 424, c.getX(), c.getY()-1, 0, 0, 120, 18, 210, 210, true, false);
			}
	}
	}*/
	public void bonesOnAltar(int id) {
		c.getItems().deleteItem(id, c.getItems().getItemSlot(id), 1);
		c.sendMessage("The gods are pleased with your offering.");
		c.getPA().addSkillXP(getExp(id)*2*Config.PRAYER_EXPERIENCE, 5);
		//handleZombie();
	}
	
	public boolean isBone(int id) {
		for (int j = 0; j < bonesExp.length; j++)
			if (bonesExp[j][0] == id)
				return true;
		return false;
	}
	
	public int getExp(int id) {
		for (int j = 0; j < bonesExp.length; j++) {
			if (bonesExp[j][0] == id)
				return bonesExp[j][1];
		}
		return 0;
	}
}