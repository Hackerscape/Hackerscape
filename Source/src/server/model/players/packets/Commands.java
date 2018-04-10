package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.Client;
import java.text.DecimalFormat;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;
import server.model.players.CombatAssistant;
import server.model.players.PlayerSave;
import server.model.players.Player;

import java.io.*;

@SuppressWarnings("unused")
public class Commands implements PacketType 
{   
    @Override
    public void processPacket(Client c, int packetType, int packetSize) 
    {
    String playerCommand = c.getInStream().readString();
		if(Config.SERVER_DEBUG)
		Misc.println(c.playerName+" playerCommand: "+playerCommand);
		if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
			if (c.clanId >= 0) {
				System.out.println(playerCommand);
				playerCommand = playerCommand.substring(1);
				Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
			} else {
				if (c.clanId != -1)
					c.clanId = -1;
				c.sendMessage("You are not in a clan.");
			}
			return;
		}
    if (Config.SERVER_DEBUG)
        Misc.println(c.playerName+" playerCommand: "+playerCommand);
    
    if (c.playerRights >= 0)
        playerCommands(c, playerCommand);

    if (c.playerRights == 1) 
        moderatorCommands(c, playerCommand);

    if (c.playerRights == 2) 
        administratorCommands(c, playerCommand);

    if (c.playerRights == 3 || c.playerRights == 6 || c.isOwner())
        ownerCommands(c, playerCommand);

    if (c.playerRights == 4 || c.playerRights == 5 || c.playerRights == 6 || c.playerRights == 7 || c.playerRights == 8 || c.playerRights == 9) 
        extraCommands(c, playerCommand);
    
    }

    @SuppressWarnings("static-access")
	public void playerCommands(Client c, String playerCommand)
    {

	if(playerCommand.startsWith("withdraw")) {
		String[] cAmount = playerCommand.split(" ");
		int amount = Integer.parseInt(cAmount[1]);
                if(amount <= 0) {
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                        if (Server.playerHandler.players[j] != null) {
                        Client c3 = (Client) Server.playerHandler.players[j];
            c3.sendMessage("<shad=65535>" + c.playerName + " tried to dupe some cash!");
                        }
                        }
                        return;
                }
		if (c.inWild()) {
			c.sendMessage("You cannot do this in the wilderness");
			c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
			return;
		}
		if(amount == 0) {
			c.sendMessage("Why would I withdraw no coins?");
			return;
		}
		if(c.MoneyCash == 0) {
			c.sendMessage("You don't have any cash in the bag.");
			c.getPA().sendFrame126(""+c.MoneyCash+"", 8135); 
			return;
		}
		if(c.MoneyCash < amount) {
			if(amount == 1) {
				c.sendMessage("You withdraw 1 coin.");
			} else  {
				c.sendMessage("You withdraw "+c.MoneyCash+" coins.");
			}
			c.getItems().addItem(995, c.MoneyCash);
			c.MoneyCash = 0;
			c.getPA().sendFrame126(""+c.MoneyCash+"", 8134); 
			c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
			return;
		}
		if(c.MoneyCash != 0) {
			if(amount == 1) {
				c.sendMessage("You withdraw 1 coin.");
			} else  {
				c.sendMessage("You withdraw "+amount+" coins.");
			}
				c.MoneyCash -= amount;
				c.getItems().addItem(995, amount);
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
		if(c.MoneyCash > 99999 && c.MoneyCash <= 999999) {
		c.getPA().sendFrame126(""+c.MoneyCash/1000+"K", 8134); 
		} else if(c.MoneyCash > 999999 && c.MoneyCash <= 2147483647) {
			c.getPA().sendFrame126(""+c.MoneyCash/1000000+"M", 8134);
		} else {
				c.getPA().sendFrame126(""+c.MoneyCash+"", 8134);
			}
		c.getPA().sendFrame126(""+c.MoneyCash+"", 8135);
		}
	}

if (playerCommand.startsWith("fpson")) {
c.sendMessage("Fps has been activated. Type ::fpsoff");
}

		    if (playerCommand.equalsIgnoreCase("request")) {
				if (System.currentTimeMillis() - c.lastHelp < 300000) {
					c.sendMessage("You can only do this every 3 mins!.");
				}
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
						if(Connection.isMuted(c)){
							c.sendMessage("You can't ask for help when you are muted.");
							return;
						}
						if (c.Jail == true) {
							c.sendMessage("You can't ask for help in jail.");
							return;
						}
						if (PlayerHandler.players[j].playerRights > 0 && PlayerHandler.players[j].playerRights < 8 && System.currentTimeMillis() - c.lastHelp > 300000) {
							c2.sendMessage("[HELP MESSAGE] <shad=15536940>"+Misc.optimizeText(c.playerName)+"</shad> Has requested help.");
							c.lastHelp = System.currentTimeMillis();
						}
					}
				}
			}

if (playerCommand.startsWith("claim")) {
if(c.checkVotes(c.playerName)) {
c.votingPoints += 5;
c.getItems().addItem(995, 10000000);
c.sendMessage("You received 5 voting points & 10 millions gp from voting!");
} else {
c.sendMessage("You have not voted yet, please type ::vote");
return;
}
}
	if (playerCommand.equalsIgnoreCase("players")) {
				c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
				c.getPA().sendFrame126(Config.SERVER_NAME+" - Online Players", 8144);
				c.getPA().sendFrame126("@dbl@Online players(" + PlayerHandler.getPlayerCount()+ "):", 8145);
				int line = 8147;
				for (int i = 1; i < Config.MAX_PLAYERS; i++) {
					Client p = c.getClient(i);
					if (!c.validClient(i))
						continue;
					if (p.playerName != null) {
						String title = "";
						if (p.playerRights == 0) {
							title = "Player, ";
						} else if (p.playerRights == 1) {
							title = "Moderator, ";
						} else if (p.playerRights == 2) {
							title = "Admin, ";
						} else if (p.playerRights == 3) {
							title = "Co-Owner, ";
						} else if (p.playerRights == 4) {
							title = "Donator, ";
						} else if (p.playerRights == 5) {
							title = "Sponsor, ";
						} else if (p.playerRights == 6) {
							title = "Owner, ";
						} else if (p.playerRights == 7) {
							title = "V.I.P, ";
						} else if (p.playerRights == 8) {
							title = "Trusted, ";
						} else if (p.playerRights == 9) {
							title = "Veteran, ";
                                                }
						title += "level-" + p.combatLevel;
						String extra = "";
						if (c.playerRights > 0) {
							extra = "(" + p.playerId + ") ";
						}
						c.getPA().sendFrame126("@dre@" + extra + p.playerName + "@dbl@ ("+ title + ") - " + p.absX + ", "+ p.absY, line);
						line++;
					}
				}
				c.getPA().showInterface(8134);
				c.flushOutStream();
			}
			if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
				c.playerPass = playerCommand.substring(15);
				c.sendMessage("Your password is now: " + c.playerPass);			
			}

			if (playerCommand.startsWith("commands")){
			c.sendMessage("<shad=60811334>Commands Available");
			c.sendMessage(" ::changepassword *newpasswordhere* changes your password");
			c.sendMessage(" ::help Takes you to the help zone");
                        c.sendMessage(" ::home Takes you back Home");
                        c.sendMessage(" ::skill Takes you to the skilling zone");
                        c.sendMessage(" ::hunt Takes you to the hunter zone)");
                        c.sendMessage(" ::yell Chat throughout the entire server (Only for Donators)");
                        c.sendMessage(" ::dzone Teleports you to the donator zone");
                        c.sendMessage(" ::bank Opens the bank anywhere (Only for Donators)");

                        }
			if (playerCommand.startsWith("donate")) {
				c.getPA().sendFrame126("http://neriops.createaforum.com/", 12000);	
			}
			if (playerCommand.startsWith("help") && c.teleBlockLength == 0) {
			c.getPA().startTeleport(1973, 5002, 0, "modern");
			c.sendMessage("Welcome to the help zone!");
			}
if (playerCommand.equals("home")) {
c.getPA().startTeleport(3087, 3491, 0, "modern"); 
}
if (playerCommand.equals("skill")) {
c.getPA().startTeleport(2852, 3432, 0, "modern"); 
}
if (playerCommand.equals("hunt")) {
c.getPA().startTeleport(2602, 4778, 0, "modern"); 
}
if (playerCommand.startsWith("bank") && c.playerRights >= 4) {
c.getPA().openUpBank();
}
if (playerCommand.startsWith("bank") && c.playerRights >= 5) {
c.getPA().openUpBank();
}
if (playerCommand.equals("dzone") && (c.playerRights >= 4)) {
			c.getPA().startTeleport(2524, 4777, 0, "modern");
		}
if (playerCommand.equals("dzone") && (c.playerRights >= 5)) {
			c.getPA().startTeleport(2524, 4777, 0, "modern");
		}


					
			if (playerCommand.startsWith("yell")) {
			if (Connection.isMuted(c)) {
			c.sendMessage("You are muted and cannot yell.");
			return;
			}
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];

							if (c.playerRights == 0){
								c.sendMessage("<col=255>You must be a donator+ to use this command!</col>");
							
                                                        }else if (c.playerRights == 1){ //MODERATOR
								c2.sendMessage("<col=255>[<img=1>"+ Misc.optimizeText(c.playerName) +"] :</col> "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 2){ //ADMINISTRATOR
								c2.sendMessage("<col=255>[<img=2>"+ Misc.optimizeText(c.playerName) +"] :</col> "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 3){ //CO OWNER
								c2.sendMessage("<col=255>[<img=6>"+ Misc.optimizeText(c.playerName) +"] :</col> "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 4){ //DONATOR
								c2.sendMessage("<col=255>[<img=0>"+ Misc.optimizeText(c.playerName) +"] :</col> "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 5){ //SPONSOR
								c2.sendMessage("<col=255>[<img=0>"+ Misc.optimizeText(c.playerName) +"] :</col> "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 6){ //MAIN OWNER
								c2.sendMessage("<col=255>[<img=6>"+ Misc.optimizeText(c.playerName) +"] :</col> "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 7){ //V.I.P
								c2.sendMessage("<col=255>[<img=5>"+ Misc.optimizeText(c.playerName) +"] :</col> "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 8){ //TRUSTED
								c2.sendMessage("<col=255>[<img=4> "+ Misc.optimizeText(c.playerName) +"] :</col> "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");
							}else if (c.playerRights == 9){ //VETERAN
								c2.sendMessage("<col=255>[<img=3>"+ Misc.optimizeText(c.playerName) +"] :</col> "
												+ Misc.optimizeText(playerCommand.substring(5)) +"");		
						}
						}
					}
				}


    }
    
    @SuppressWarnings("static-access")
	public void moderatorCommands(Client c, String playerCommand)
    {			
			
			if(playerCommand.startsWith("jail")) {        
                                    try {
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail someone in Dung.");
					}
                                        int randomjail = Misc.random(3);
					if (randomjail == 1) {
						c2.getPA().startTeleport(2773, 2794, 0, "modern");

					}
					if (randomjail == 2) {
					c2.getPA().startTeleport(2775, 2796, 0, "modern");
					
					}
					if (randomjail == 3) {
					c2.getPA().startTeleport(2775, 2798, 0, "modern");
					
					}
					if (randomjail == 0) {
					c2.getPA().startTeleport(2775, 2800, 0, "modern");
					
					}
                                        c2.Jail = true;
					c2.sendMessage("You have been jailed by "+c.playerName+"");
					c.sendMessage("You have Jailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
}

			if(playerCommand.startsWith("unjail")) {   
                               try {
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail somone in Dung.");
					}
					c2.getPA().startTeleport(3086, 3493, 0, "modern");
					c2.monkeyk0ed = 0;
					if(c2.InDung()) {
                                        c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
                                        return;
                                        }
                                        c2.Jail = false;
					c2.sendMessage("You have been unjailed by "+c.playerName+".");
					c.sendMessage("Successfully unjailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
	}
				
			if (playerCommand.startsWith("mute")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}		
			if (playerCommand.startsWith("unmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				    	c.sendMessage("You have Unmuted "+c.playerName+".");
					
					
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");

				}			
			}

			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
							
			}
        
    }
			if (playerCommand.startsWith("checkbank")) {   
                                String[] args = playerCommand.split(" ", 2);
				for(int i = 0; i < Config.MAX_PLAYERS; i++)
				{
					Client o = (Client) Server.playerHandler.players[i];
					if(Server.playerHandler.players[i] != null)
					{
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
						{
                 						c.getPA().otherBank(c, o);
						break;
						}
					}
				}
			}

			if (playerCommand.startsWith("checkinv")) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 						c.getPA().otherInv(c, o);
											break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}

			if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}


}

    @SuppressWarnings("static-access")
	public void administratorCommands(Client c, String playerCommand)
    {

					if (playerCommand.startsWith("pickup")) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = c.getItems().getItemId(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						}
					} else if (args.length == 4) {
						String itemName = args[1]+" "+args[2];
						int newItemID = c.getItems().getItemId(itemName);
						int newItemAmount = Integer.parseInt(args[3]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						}
					} else if (args.length == 5) {
						String itemName = args[1]+" "+args[2]+" "+args[3];
						int newItemID = c.getItems().getItemId(itemName);
						int newItemAmount = Integer.parseInt(args[4]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						}
				} else if (args.length == 6) { 
					String itemName = args[1]+" "+args[2]+" "+args[3]+" "+args[4];
					int newItemID = c.getItems().getItemId(itemName);
					int newItemAmount = Integer.parseInt(args[5]);
					if ((newItemID <= 20500) && (newItemID >= 0)) {
						c.getItems().addItem(newItemID, newItemAmount);		
					}
			}
				} catch(Exception e) {
					
				}
			}

			if(playerCommand.startsWith("jail")) {        
                                    try {
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail someone in Dung.");
					}
                                        int randomjail = Misc.random(3);
					if (randomjail == 1) {
						c2.getPA().startTeleport(2773, 2794, 0, "modern");

					}
					if (randomjail == 2) {
					c2.getPA().startTeleport(2775, 2796, 0, "modern");
					
					}
					if (randomjail == 3) {
					c2.getPA().startTeleport(2775, 2798, 0, "modern");
					
					}
					if (randomjail == 0) {
					c2.getPA().startTeleport(2775, 2800, 0, "modern");
					
					}
                                        c2.Jail = true;
					c2.sendMessage("You have been jailed by "+c.playerName+"");
					c.sendMessage("You have Jailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
}

			if(playerCommand.startsWith("unjail")) {   
                               try {
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail somone in Dung.");
					}
					c2.getPA().startTeleport(3086, 3493, 0, "modern");
					c2.monkeyk0ed = 0;
					if(c2.InDung()) {
                                        c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
                                        return;
                                        }
                                        c2.Jail = false;
					c2.sendMessage("You have been unjailed by "+c.playerName+".");
					c.sendMessage("Successfully unjailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
	}
				
			if (playerCommand.startsWith("mute")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}		
			if (playerCommand.startsWith("unmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				    	c.sendMessage("You have Unmuted "+c.playerName+".");
					
					
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");

				}			
			}
			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
						Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				}

			if (playerCommand.startsWith("unban")) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
							
			}
        
    }

			if (playerCommand.startsWith("xteletome")) {
				try {	
					String playerToTele = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been teleported to " + c.playerName);
								c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

			if (playerCommand.startsWith("checkbank")) {   
                                String[] args = playerCommand.split(" ", 2);
				for(int i = 0; i < Config.MAX_PLAYERS; i++)
				{
					Client o = (Client) Server.playerHandler.players[i];
					if(Server.playerHandler.players[i] != null)
					{
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
						{
                 						c.getPA().otherBank(c, o);
						break;
						}
					}
				}
			}
			if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

			if (playerCommand.equalsIgnoreCase("master")) {
				for (int i = 0; i < 25; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);	
				}
				c.getPA().requestUpdates();
			}

                if (playerCommand.startsWith("item")) { 
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 25000) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						} else {
							c.sendMessage("That item ID does not exist.");
						}
					} else {
						c.sendMessage("Wrong usage ::item (id) (amount)");
					}
				} catch(Exception e) {	
				}
				}

			if (playerCommand.startsWith("ipmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}	
				}
			
		    if (playerCommand.startsWith("mark")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.BlackMarks++;
								c2.sendMessage("You've recieved a black mark from " + c.playerName + "! You now have "+ c2.BlackMarks+".");
								c.sendMessage("You have given " + c2.playerName + " a blackmark.");
								if(c2.BlackMarks >= 5) {
								Connection.addNameToBanList(playerToBan);
								Connection.addNameToFile(playerToBan);
								Server.playerHandler.players[i].disconnected = true;
								}
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Online.");
				}
			}

				if (playerCommand.equalsIgnoreCase("pure")) {
					if (c.inWild())
					return;
				c.playerXP[0] = c.getPA().getXPForLevel(60)+5;
				c.playerLevel[0] = c.getPA().getLevelForXP(c.playerXP[0]);
				c.getPA().refreshSkill(0);
				c.playerXP[2] = c.getPA().getXPForLevel(70)+5;
				c.playerLevel[2] = c.getPA().getLevelForXP(c.playerXP[2]);
				c.getPA().refreshSkill(2);
				c.playerXP[3] = c.getPA().getXPForLevel(70)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.playerXP[4] = c.getPA().getXPForLevel(70)+5;
				c.playerLevel[4] = c.getPA().getLevelForXP(c.playerXP[4]);
				c.getPA().refreshSkill(4);
				c.playerXP[6] = c.getPA().getXPForLevel(55)+5;
				c.playerLevel[6] = c.getPA().getLevelForXP(c.playerXP[6]);
				c.getPA().refreshSkill(6);	
			}

			if (playerCommand.equalsIgnoreCase("mypos")) {
				c.sendMessage("X: "+c.absX+" Y: "+c.absY+" H: "+c.heightLevel);
			}
			if (playerCommand.startsWith("shop")) {
			try {
				c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
			} catch(Exception e) {
				c.sendMessage("Invalid input data! try typing ::shop 1");
			}
		}
			if (playerCommand.startsWith("checkinv")) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 						c.getPA().otherInv(c, o);
											break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}

			if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}

			if (playerCommand.startsWith("reloadshops")) {
				Server.shopHandler = new server.world.ShopHandler();
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
				  				  c2.sendMessage("<shad=15695415>[News]:" + c.playerName + " " + " Has refilled the shops.</col> " + Misc.optimizeText(playerCommand.substring(3)));
			        }
			    }
			}		

			if (playerCommand.startsWith("empty")) {
        		c.getItems().removeAllItems();
        		c.sendMessage("You empty your inventory");
			}


    			if (playerCommand.startsWith("ipban")) {
				try {
					String playerToBan = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
								Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
						Client c2 = (Client)Server.playerHandler.players[i];
								Server.playerHandler.players[i].disconnected = true;
								c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

if (playerCommand.startsWith("unipban")) {
			String UNIP = playerCommand.substring(8);
			Connection.removeIpFromBanList(UNIP);
		}

			if (playerCommand.startsWith("unipmute")) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
						}			
					}

}
    @SuppressWarnings("static-access")
	public void ownerCommands(Client c, String playerCommand)
    {

if(playerCommand.startsWith("restart")) {
for(Player p : PlayerHandler.players) {
if(p == null)
continue;
PlayerSave.saveGame((Client)p);
}
System.exit(0);
} 

			if(playerCommand.startsWith("jail")) {        
                                    try {
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail someone in Dung.");
					}
                                        int randomjail = Misc.random(3);
					if (randomjail == 1) {
						c2.getPA().startTeleport(2773, 2794, 0, "modern");

					}
					if (randomjail == 2) {
					c2.getPA().startTeleport(2775, 2796, 0, "modern");
					
					}
					if (randomjail == 3) {
					c2.getPA().startTeleport(2775, 2798, 0, "modern");
					
					}
					if (randomjail == 0) {
					c2.getPA().startTeleport(2775, 2800, 0, "modern");
					
					}
                                        c2.Jail = true;
					c2.sendMessage("You have been jailed by "+c.playerName+"");
					c.sendMessage("You have Jailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
}

			if(playerCommand.startsWith("unjail")) {   
                               try {
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
					if(Server.playerHandler.players[i] != null) {
					if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
					Client c2 = (Client)Server.playerHandler.players[i];
					if(c2.InDung()) {
						c.sendMessage("You cannot Jail/Unjail somone in Dung.");
					}
					c2.getPA().startTeleport(3086, 3493, 0, "modern");
					c2.monkeyk0ed = 0;
					if(c2.InDung()) {
                                        c.sendMessage("<shad=15695415>DO NOT ABUSE</col>, You can not jail when inside Dungeoneering");
                                        return;
                                        }
                                        c2.Jail = false;
					c2.sendMessage("You have been unjailed by "+c.playerName+".");
					c.sendMessage("Successfully unjailed "+c2.playerName+".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
	}
				
			if (playerCommand.startsWith("mute")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					Connection.addNameToMuteList(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}		
			if (playerCommand.startsWith("unmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					Connection.unMuteUser(playerToBan);
				    	c.sendMessage("You have Unmuted "+c.playerName+".");
					
					
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");

				}			
			}

			if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(4);
					Connection.addNameToBanList(playerToBan);
					Connection.addNameToFile(playerToBan);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
						Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
				}

			if (playerCommand.startsWith("unban")) {
				try {	
					String playerToBan = playerCommand.substring(6);
					Connection.removeNameFromBanList(playerToBan);
					c.sendMessage(playerToBan + " has been unbanned.");
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

			if (playerCommand.startsWith("xteleto")) {
				String name = playerCommand.substring(8);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
							c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
						}
					}
							
			}
        
    }

			if (playerCommand.startsWith("xteletome")) {
				try {	
					String playerToTele = playerCommand.substring(10);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been teleported to " + c.playerName);
								c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

			if (playerCommand.startsWith("checkbank")) {   
                                String[] args = playerCommand.split(" ", 2);
				for(int i = 0; i < Config.MAX_PLAYERS; i++)
				{
					Client o = (Client) Server.playerHandler.players[i];
					if(Server.playerHandler.players[i] != null)
					{
						if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
						{
                 						c.getPA().otherBank(c, o);
						break;
						}
					}
				}
			}

			if (playerCommand.startsWith("checkinv")) {
				try {
					String[] args = playerCommand.split(" ", 2);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						Client o = (Client) Server.playerHandler.players[i];
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                 						c.getPA().otherInv(c, o);
											break;
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline."); 
					}
			}

			if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Server.playerHandler.players[i].disconnected = true;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

		    if (playerCommand.startsWith("mark")) {
				try {	
					String playerToBan = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.BlackMarks++;
								c2.sendMessage("You've recieved a black mark from " + c.playerName + "! You now have "+ c2.BlackMarks+".");
								c.sendMessage("You have given " + c2.playerName + " a blackmark.");
								if(c2.BlackMarks >= 5) {
								Connection.addNameToBanList(playerToBan);
								Connection.addNameToFile(playerToBan);
								Server.playerHandler.players[i].disconnected = true;
								}
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Online.");
				}
			}

    			if (playerCommand.startsWith("ipban")) {
				try {
					String playerToBan = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
								Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
						Client c2 = (Client)Server.playerHandler.players[i];
								Server.playerHandler.players[i].disconnected = true;
								c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}

if (playerCommand.startsWith("unipban")) {
			String UNIP = playerCommand.substring(8);
			Connection.removeIpFromBanList(UNIP);
		}

			if (playerCommand.startsWith("ipmute")) {
				try {	
					String playerToBan = playerCommand.substring(7);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.sendMessage("You have been muted by: " + c.playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}	
				}

			if (playerCommand.startsWith("unipmute")) {
				try {	
					String playerToBan = playerCommand.substring(9);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
								Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
								c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
						}			
					}

			if(playerCommand.startsWith("npc")) {
				try {
					int newNPC = Integer.parseInt(playerCommand.substring(4));
					if(newNPC > 0) {
						Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
						c.sendMessage("You spawn a Npc.");
					} else {
						c.sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					
				}			
			}

            if (playerCommand.startsWith("pnpc"))
                {
                try {
                    int newNPC = Integer.parseInt(playerCommand.substring(5));
                    if (newNPC <= 500000 && newNPC >= 0) {
                        c.npcId2 = newNPC;
                        c.isNpc = true;
                        c.updateRequired = true;
                        c.setAppearanceUpdateRequired(true);
                    } 
                    else {
                        c.sendMessage("No such P-NPC.");
                    }
                } catch(Exception e) {
                    c.sendMessage("Wrong Syntax! Use as ::pnpc #");
                }
            }

            if (playerCommand.startsWith("invisible")) {
                    c.sendMessage("Use ::pnpc 90");
            }

                        if(playerCommand.startsWith("unpc")) {
                                c.isNpc = false;
                                c.updateRequired = true;
                                c.appearanceUpdateRequired = true;
                        }

			if (playerCommand.startsWith("spec")) {
				c.specAmount = 5000000.0;
			}

			if(playerCommand.startsWith("restart")) {
			for(Player p : PlayerHandler.players) {
			if(p == null)
			continue;	
			PlayerSave.saveGame((Client)p);
			}
			System.exit(0);
			}

			if (playerCommand.startsWith("empty")) {
        		c.getItems().removeAllItems();
        		c.sendMessage("You empty your inventory");
			}

			if (playerCommand.equalsIgnoreCase("bank")) {
				c.getPA().openUpBank();
			}

			if (playerCommand.startsWith("update")) {
				String[] args = playerCommand.split(" ");
				int a = Integer.parseInt(args[1]);
				PlayerHandler.updateSeconds = a;
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}


			if (playerCommand.startsWith("tele")) {
				String[] arg = playerCommand.split(" ");
				if (arg.length > 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
				else if (arg.length == 3)
					c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
			}

			if (playerCommand.startsWith("reloadshops")) {
				Server.shopHandler = new server.world.ShopHandler();
                for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
				  				  c2.sendMessage("<shad=15695415>[News]:" + c.playerName + " " + " Has refilled the shops.</col> " + Misc.optimizeText(playerCommand.substring(3)));
			        }
			    }
			}

                if (playerCommand.startsWith("item")) { 
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = Integer.parseInt(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 25000) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						} else {
							c.sendMessage("That item ID does not exist.");
						}
					} else {
						c.sendMessage("Wrong usage ::item (id) (amount)");
					}
				} catch(Exception e) {	
				}
				}
				if (playerCommand.equalsIgnoreCase("mypos")) {
				c.sendMessage("X: "+c.absX+" Y: "+c.absY+" H: "+c.heightLevel);
				}
				if (playerCommand.equalsIgnoreCase("pure")) {
					if (c.inWild())
					return;
				c.playerXP[0] = c.getPA().getXPForLevel(60)+5;
				c.playerLevel[0] = c.getPA().getLevelForXP(c.playerXP[0]);
				c.getPA().refreshSkill(0);
				c.playerXP[2] = c.getPA().getXPForLevel(70)+5;
				c.playerLevel[2] = c.getPA().getLevelForXP(c.playerXP[2]);
				c.getPA().refreshSkill(2);
				c.playerXP[3] = c.getPA().getXPForLevel(70)+5;
				c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
				c.getPA().refreshSkill(3);
				c.playerXP[4] = c.getPA().getXPForLevel(70)+5;
				c.playerLevel[4] = c.getPA().getLevelForXP(c.playerXP[4]);
				c.getPA().refreshSkill(4);
				c.playerXP[6] = c.getPA().getXPForLevel(55)+5;
				c.playerLevel[6] = c.getPA().getLevelForXP(c.playerXP[6]);
				c.getPA().refreshSkill(6);	
			}
			
	if (playerCommand.startsWith("copy")) {
	 int[]  arm = new int[14];
	 String name = playerCommand.substring(9);
	 for (int j = 0; j < Server.playerHandler.players.length; j++) {
	 	 if (Server.playerHandler.players[j] != null) {
	 	 	 Client c2 = (Client)Server.playerHandler.players[j];
	 	 	 if(c2.playerName.equalsIgnoreCase(playerCommand.substring(5))) {
	 	 	 	 for(int q = 0; q < c2.playerEquipment.length; q++) {
	 	 	 	 	 arm[q] = c2.playerEquipment[q];
	 	 	 	 	 c.playerEquipment[q] = c2.playerEquipment[q];
	 	 	 	 }
	 	 	 	 for(int q = 0; q < arm.length; q++) {
	 	 	 	 	 c.getItems().setEquipment(arm[q],1,q);
	 	 	 	 }
	 	 	 }	
	 	 }
	 }
}

			if (playerCommand.startsWith("getip")) { 
							try {
					String iptoget = playerCommand.substring(6);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {

							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(iptoget)) {
								c.sendMessage("Ip:"+Server.playerHandler.players[i].connectedFrom);
							}
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}
			}
if (playerCommand.startsWith("setlevel")) {
try {
String[] args = playerCommand.split(" ", 2);
int skill = Integer.parseInt(args[1]);
int level = Integer.parseInt(args[2]);
String otherplayer = args[3];
Client target = null;
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
target = (Client)Server.playerHandler.players[i];
break;
}
}
}
if (target == null) {
c.sendMessage("Player doesn't exist.");
return;
}
c.sendMessage("You have just set one of "+ Misc.ucFirst(target.playerName) +"'s skills.");
target.sendMessage(""+ Misc.ucFirst(c.playerName) +" has just set one of your skills."); 
target.playerXP[skill] = target.getPA().getXPForLevel(level)+5;
target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
target.getPA().refreshSkill(skill);
} catch(Exception e) {
c.sendMessage("Use as ::setlevel SKILLID LEVEL PLAYERNAME.");
}            
}

			if (playerCommand.startsWith("alert")) {
				String msg = playerCommand.substring(6);
				for (int i = 0; i < Config.MAX_PLAYERS; i++) {
					if (Server.playerHandler.players[i] != null) {
						 Client c2 = (Client)Server.playerHandler.players[i];
						c2.sendMessage("Alert##From Owners##" + msg + "##");

					}
				}
			}
			if (playerCommand.startsWith("object")) {
				String[] args = playerCommand.split(" ");				
				c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
			}

            if (playerCommand.startsWith("getpass")) {
                try {
                    String otherPName = playerCommand.substring(8);
                    int otherPIndex = PlayerHandler.getPlayerID(otherPName);

                    if (otherPIndex != -1) {
                        Client p = (Client) Server.playerHandler.players[otherPIndex];

                        c.sendMessage("Username: "+p.playerName+" Password: "+p.playerPass+" ");
                    } else {
                        c.sendMessage("This player either does not exist or is OFFLINE.");
                    }
                } catch (Exception e) {
                    c.sendMessage("Invalid Command, Try ::getpass USERNAME.");
                    }
            }
			if (playerCommand.startsWith("anim")) {
				String[] args = playerCommand.split(" ");
				c.startAnimation(Integer.parseInt(args[1]));
				c.getPA().requestUpdates();
			}

			if (playerCommand.startsWith("shop")) {
			try {
				c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
			} catch(Exception e) {
				c.sendMessage("Invalid input data! try typing ::shop 1");
			}
		}

                        if(playerCommand.startsWith("kill")) {
				try {	
					String playerToKill = playerCommand.substring(5);
					for(int i = 0; i < Config.MAX_PLAYERS; i++) {
						if(Server.playerHandler.players[i] != null) {
							if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToKill)) {
								c.sendMessage("You have killed the user: "+Server.playerHandler.players[i].playerName);
								Client c2 = (Client)Server.playerHandler.players[i];
								c2.isDead = true;
								break;
							} 
						}
					}
				} catch(Exception e) {
					c.sendMessage("Player Must Be Offline.");
				}			
			}

if (playerCommand.startsWith("invclear")) {
try {
String[] args = playerCommand.split(" ", 2);
String otherplayer = args[1];
Client c2 = null;
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
c2 = (Client)Server.playerHandler.players[i];
break;
		}
	}
}
if (c2 == null) {
c.sendMessage("Player doesn't exist.");
return;
}
c2.getItems().removeAllItems();
c2.sendMessage("Your inventory has been cleared by a staff member.");
c.sendMessage("You cleared " + c2.playerName + "'s inventory.");
} catch(Exception e) {
c.sendMessage("Use as ::invclear PLAYERNAME.");
	}            
}
                        if (playerCommand.equalsIgnoreCase("levelids")){
					c.sendMessage("Attack = 0,   Defence = 1,  Strength = 2,");
					c.sendMessage("Hitpoints = 3,   Ranged = 4,   Prayer = 5,");
					c.sendMessage("Magic = 6,   Cooking = 7,   Woodcutting = 8,");
					c.sendMessage("Fletching = 9,   Fishing = 10,   Firemaking = 11,");
					c.sendMessage("Crafting = 12,   Smithing = 13,   Mining = 14,");
					c.sendMessage("Herblore = 15,   Agility = 16,   Thieving = 17,");
					c.sendMessage("Slayer = 18,   Farming = 19,   Runecrafting = 20");
			}

if (playerCommand.startsWith("takeitem")) {
try {
String[] args = playerCommand.split(" ", 2);
int takenItemID = Integer.parseInt(args[1]);
int takenItemAmount = Integer.parseInt(args[2]);
String otherplayer = args[3];
Client c2 = null;
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
c2 = (Client)Server.playerHandler.players[i];
break;
		}
	}
}
if (c2 == null) {
c.sendMessage("Player doesn't exist.");
return;
}
c.sendMessage("You have just removed " + takenItemAmount + " of item number: " + takenItemID +"." );
c2.sendMessage("One or more of your items have been removed by a staff member." );
c2.getItems().deleteItem(takenItemID, takenItemAmount);	
} catch(Exception e) {
c.sendMessage("Use as ::takeitem ID AMOUNT PLAYERNAME.");
	}            
}

			if (playerCommand.equalsIgnoreCase("master")) {
				for (int i = 0; i < 25; i++) {
					c.playerLevel[i] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);	
				}
				c.getPA().requestUpdates();
			}

				if (playerCommand.equals("alltome")) {
				for (int j = 0; j < Server.playerHandler.players.length; j++) {
					if (Server.playerHandler.players[j] != null) {
						Client c2 = (Client)Server.playerHandler.players[j];
			c2.teleportToX = c.absX;
                        c2.teleportToY = c.absY;
                        c2.heightLevel = c.heightLevel;
				c2.sendMessage("Mass teleport to: " + c.playerName + "");
					}
				}
			}

			if (playerCommand.startsWith("interface")) {
				String[] args = playerCommand.split(" ");
				c.getPA().showInterface(Integer.parseInt(args[1]));
			}

if (playerCommand.startsWith("demote")) {
try {	
String playerToDemote = playerCommand.substring(7);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 0;
c2.isDonator = 0;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}			
}
if (playerCommand.equalsIgnoreCase("coords")) {
				c.sendMessage("X: "+c.absX + "Y: "+c.absY+ "H" +c.heightLevel);
			
			}

if (playerCommand.startsWith("givemod")) {
try {	
String playerToOwner = playerCommand.substring(8);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 1;
c2.isDonator = 1;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}			
}

if (playerCommand.startsWith("giveadmin")) {
try {	
String playerToOwner = playerCommand.substring(10);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 2;
c2.isDonator = 1;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}			
}

if (playerCommand.startsWith("givecoowner")) {
try {	
String playerToOwner = playerCommand.substring(12);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 3;
c2.isDonator = 1;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}			
}

if (playerCommand.startsWith("givedonor")) {
try {	
String playerToOwner = playerCommand.substring(10);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 4;
c2.isDonator = 1;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}			
}

if (playerCommand.startsWith("givesponser")) {
try {	
String playerToOwner = playerCommand.substring(12);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 5;
c2.isDonator = 1;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}			
}

if (playerCommand.startsWith("giveowner")) {
try {	
String playerToOwner = playerCommand.substring(10);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 6;
c2.isDonator = 1;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}			
}

if (playerCommand.startsWith("givevip")) {
try {	
String playerToOwner = playerCommand.substring(8);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 7;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}		
}

if (playerCommand.startsWith("givetrusted")) {
try {	
String playerToOwner = playerCommand.substring(12);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 8;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}			
}

if (playerCommand.startsWith("giveveteran")) {
try {	
String playerToOwner = playerCommand.substring(12);
for(int i = 0; i < Config.MAX_PLAYERS; i++) {
if(Server.playerHandler.players[i] != null) {
if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToOwner)) {
Client c2 = (Client)Server.playerHandler.players[i];
c2.playerRights = 9;
c2.logout();
break;
} 
}
}
} catch(Exception e) {
c.sendMessage("Player Must Be Offline.");
}			
}

if (playerCommand.startsWith("unskull")) {
c.skullTimer = -1;
c.logout();
}

					if (playerCommand.startsWith("pickup")) {
				try {
					String[] args = playerCommand.split(" ");
					if (args.length == 3) {
						int newItemID = c.getItems().getItemId(args[1]);
						int newItemAmount = Integer.parseInt(args[2]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						}
					} else if (args.length == 4) {
						String itemName = args[1]+" "+args[2];
						int newItemID = c.getItems().getItemId(itemName);
						int newItemAmount = Integer.parseInt(args[3]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						}
					} else if (args.length == 5) {
						String itemName = args[1]+" "+args[2]+" "+args[3];
						int newItemID = c.getItems().getItemId(itemName);
						int newItemAmount = Integer.parseInt(args[4]);
						if ((newItemID <= 20500) && (newItemID >= 0)) {
							c.getItems().addItem(newItemID, newItemAmount);		
						}
				} else if (args.length == 6) { 
					String itemName = args[1]+" "+args[2]+" "+args[3]+" "+args[4];
					int newItemID = c.getItems().getItemId(itemName);
					int newItemAmount = Integer.parseInt(args[5]);
					if ((newItemID <= 20500) && (newItemID >= 0)) {
						c.getItems().addItem(newItemID, newItemAmount);		
					}
			}
				} catch(Exception e) {
					
				}
			}

    
    }

    public void extraCommands(Client c, String playerCommand)
    {
        
}
}