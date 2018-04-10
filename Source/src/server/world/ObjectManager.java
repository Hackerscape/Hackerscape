package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	@SuppressWarnings("static-access")
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	@SuppressWarnings("static-access")
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	@SuppressWarnings("static-access")
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	@SuppressWarnings("unused")
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {

//START OF HOME OBJECT

c.getPA().checkObjectSpawn(4874, 3098, 3500, 1, 10);//Stall level 1
c.getPA().checkObjectSpawn(4875, 3097, 3500, 1, 10);//Stall level 25
c.getPA().checkObjectSpawn(4876, 3096, 3500, 0, 10);//Stall level 50
c.getPA().checkObjectSpawn(4877, 3095, 3500, 0, 10);//Stall level 75
c.getPA().checkObjectSpawn(4878, 3094, 3500, 0, 10);//Stall level 90
c.getPA().checkObjectSpawn(2149, 3083, 3499, 0, 10);//Summoning Obelisk
c.getPA().checkObjectSpawn(6552, 3094, 3506, 2, 10);//Ancient Magic
c.getPA().checkObjectSpawn(409, 3091, 3506, 2, 10);//Gold Altar
c.getPA().checkObjectSpawn(-1, 3098, 3501, -1, 10);//Lunar Magic
c.getPA().checkObjectSpawn(411, 3098, 3506, 2, 10);//Curse Prayers
c.getPA().checkObjectSpawn(7965, 2524, 4774, 1, 10);//Patch
c.getPA().checkObjectSpawn(75, 3097, 3498, 0, 10);//CkeyChest

c.getPA().checkObjectSpawn(12356, 3089, 3483, 1, 10);//Recipe For Disaster Portal
c.getPA().checkObjectSpawn(12356, 2533, 4775, 1, 10);//Recipe For Disaster Portal
c.getPA().checkObjectSpawn(2403, 3087, 3483, 4, 10);//Recipe For Disaster Chest
c.getPA().checkObjectSpawn(2403, 2532, 4775, 4, 10);//Recipe For Disaster Chest

c.getPA().checkObjectSpawn(7315, 3072, 3504, 0, 10); //Funpk Portals
c.getPA().checkObjectSpawn(1032, 3072, 3505, 2, 10); //Funpk Sign
c.getPA().checkObjectSpawn(1032, 3071, 3505, 1, 10); //Funpk Sign
c.getPA().checkObjectSpawn(1032, 3071, 3504, 1, 10); //Funpk Sign
c.getPA().checkObjectSpawn(1032, 3071, 3503, 1, 10); //Funpk Sign
c.getPA().checkObjectSpawn(1032, 3072, 3503, 4, 10); //Funpk Sign

c.getPA().checkObjectSpawn(-1, 3084, 3502, -1, 10);//Removing Home Well
c.getPA().checkObjectSpawn(-1, 3085, 3506, -1, 10);//Removing Home Wild Sign
c.getPA().checkObjectSpawn(-1, 3088, 3509, -1, 10);//Removing Home Cart
c.getPA().checkObjectSpawn(-1, 3090, 3503, -1, 10);//Removing Home Tree
c.getPA().checkObjectSpawn(-1, 3095, 3498, -1, 10);//Removing Home Bank Table
c.getPA().checkObjectSpawn(-1, 3098, 3496, -1, 10);//Removing Home Bank Deposit
c.getPA().checkObjectSpawn(-1, 3092, 3496, -1, 10);//Removing Home Bank Chair

//END OF HOME OBJECT

	c.getPA().checkObjectSpawn(4151, 2605, 3153, 1, 10); //portal home FunPk
		c.getPA().checkObjectSpawn(3192, 2602, 3155, 1, 10); //barrel FunPk
		c.getPA().checkObjectSpawn(2619, 2602, 3156, 1, 10); //barrel FunPk
		c.getPA().checkObjectSpawn(1032, 2605, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2603, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3155, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3153, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2536, 4778, 0, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2537, 4777, 1, 10); //warning sign donor
		c.getPA().checkObjectSpawn(1032, 2536, 4776, 2, 10); //warning sign donor
		c.getPA().checkObjectSpawn(7315, 2536, 4777, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(7316, 2605, 3153, 0, 10); //funpk portals
		c.getPA().checkObjectSpawn(11356, 2530, 4778, 0, 10); //frost dragon portals
		c.getPA().checkObjectSpawn(8972, 2474, 3440, 0, 10); //Strykeworms portal
		c.getPA().checkObjectSpawn(194, 2423, 3525, 0, 10); //Dungeoneering Rock
		c.getPA().checkObjectSpawn(16081, 1879, 4620, 0, 10); //Dungeoneering lvl 1 tele
		c.getPA().checkObjectSpawn(16078, 1869, 4622, 0, 10); //Dungeoneering Rope
		c.getPA().checkObjectSpawn(2930, 2383, 4714, 3, 10); //Dungeoneering Boss 1 door
		c.getPA().checkObjectSpawn(1032, 2382, 4714, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(79, 3044, 5105, 1, 10); //dungie blocker
		c.getPA().checkObjectSpawn(10778, 2867, 9530, 1, 10); //dung floor 4 portal
		c.getPA().checkObjectSpawn(7272, 3233, 9316, 1, 10); //dung floor 5 portal
		c.getPA().checkObjectSpawn(4408, 2869, 9949, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(410, 1860, 4625, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(6552, 1859, 4617, 1, 10); //dung floor 6 portalEND
		c.getPA().checkObjectSpawn(7318, 2772, 4454, 1, 10); //dung floor 7 portalEND
		c.getPA().checkObjectSpawn(4412, 1919, 4640, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3048, 5233, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2980, 5111, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2867, 9527, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3234, 9327, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2387, 4721, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2429, 4680, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2790, 9328, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3060, 5209, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3229, 9312, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2864, 9950, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2805, 4440, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2744, 4453, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 3017, 5243, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(4412, 2427, 9411, 0, 10); //escape ladder
		c.getPA().checkObjectSpawn(2465, 2422, 9429, 0, 10); //escape ladder
                c.getPA().checkObjectSpawn(2094, 3032, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2094, 3033, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3034, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2091, 3035, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3036, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2092, 3037, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3038, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2103, 3039, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3040, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2097, 3041, 9836, 0, 10);
                c.getPA().checkObjectSpawn(2105, 3042, 9836, 0, 10);
		c.getPA().checkObjectSpawn(2105, 3043, 9836, 0, 10);
                c.getPA().checkObjectSpawn(14859, 3044, 9836, 0, 10);
		c.getPA().checkObjectSpawn(14859, 3045, 9836, 0, 10);
                c.getPA().checkObjectSpawn(3044, 3036, 9831, -1, 10);
		c.getPA().checkObjectSpawn(2213, 3037, 9835, -1, 10);
                c.getPA().checkObjectSpawn(2783, 3034, 9832, 0, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3495, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3079, 3501, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3080, 3501, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2598, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4779, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4778, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2598, 4777, 1, 10);
		c.getPA().checkObjectSpawn(2286, 2598, 4778, 1, 10);
	
		c.getPA().checkObjectSpawn(14859, 2839, 3439, 0, 10);//runite ore skilling.
	        c.getPA().checkObjectSpawn(14859, 2520, 4773, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4775, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(14859, 2518, 4774, 0, 10);//runite ore donor.
		c.getPA().checkObjectSpawn(13617, 2527, 4770, 2, 10); //Barrelportal donor	
		
		c.getPA().checkObjectSpawn(13615, 2525, 4770, 2, 10); // hill giants donor
		c.getPA().checkObjectSpawn(13620, 2523, 4770, 2, 10); // steel drags donor
		c.getPA().checkObjectSpawn(13619, 2521, 4770, 2, 10); // tormented demons donor


		c.getPA().checkObjectSpawn(6163, 2029, 4527, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2029, 4529, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2029, 4531, 1, 10); 

		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4780, 1, 10);
		c.getPA().checkObjectSpawn(2213, 2516, 4775, 1, 10);
		c.getPA().checkObjectSpawn(1530, 3093, 3487, 1, 10);

                                          //X     Y     ID -> ID X Y
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1278, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
                c.getPA().checkObjectSpawn(2728, 2519, 4779, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2429, 9416, 0, 10);//cooking range dung!
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		c.getPA().checkObjectSpawn(2728, 2611, 3399, 4, 10);//cooking range Fishing guild!
		c.getPA().checkObjectSpawn(320, 3048, 10342, 0, 10);
                c.getPA().checkObjectSpawn(320, 2528, 4773, 0, 10);
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);
		c.getPA().checkObjectSpawn(2213, 3239, 9571, 0, 10);
		c.getPA().checkObjectSpawn(2213, 3240, 9571, 0, 10);
		c.getPA().checkObjectSpawn(2213, 3241, 9571, 0, 10);
	 if (c.heightLevel == 0) {
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		 }else{
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
	}
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	@SuppressWarnings("static-access")
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}