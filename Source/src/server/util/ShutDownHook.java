package server.util;

import server.model.players.Client;
import server.model.players.PlayerSave;
import server.Server;

@SuppressWarnings("unused")
public class ShutDownHook extends Thread {

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		System.out.println("Shutdown thread run.");
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				server.model.players.PlayerSave.saveGame(c);			
			}		
		}
		System.out.println("Shutting down...");
	}

}