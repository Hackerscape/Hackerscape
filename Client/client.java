import java.applet.AppletContext;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.zip.*;
import java.lang.reflect.Method;
import sign.signlink;
import javax.swing.*;

@SuppressWarnings({ "unused", "serial" })
public class client extends RSApplet {

	public int colorOfSplat = 0;
	public String hitText = "";
	public Sprite hitSprite = null;
	public int splatX = 0;
	public int splatY = 0;
	public int fadeOffset = 0;
	public int hitmarkWait = 0;
	public boolean addY = false;
	
	public Sprite[] bigSplats = new Sprite[3]; 
	//public Sprite[] combatIcons = new Sprite[10]; 
	//public Sprite[] combatIcons = new Sprite[4];


	public boolean 
				is459 = false,
				is474 = false,
				is480 = false,
				is508 = false,
				is525 = true,
				is562 = false,
				idToggle = true, //TRUE WHILE CODING FOR ALL IDS
				hitbarToggle = true,
				menuToggle = true,
				namesToggle = false,
				hitmarks = true;
	public static boolean 
				newDamage = true,
				normalLogin = true,
				newHits = true;

public int interfaceButtonAction = 0;

public String getRank(int i){
switch(i){
case 1:
return "Sir";
case 2:
return "Lady";
case 3:
return "Boss";
case 4:
return "Donor";
case 5:
return "Dicer";
case 6:
return "Trusted";
case 7:
return "Owner";
case 8:
return "Moderator";
}
return "";
}

//public static boolean newDamage = true, newHits = true, normalLogin = true;
				 public int getXPForLevel(int i)
    {
        int j = 0;
        int k = 0;
        for(int l = 1; l <= i; l++)
        {
            j = (int)((double)j + Math.floor((double)l + 300D * Math.pow(2D, (double)l / 7D)));
            if(l >= i)
                return k;
            k = (int)Math.floor(j / 4);
        }

        return 0;
    }
	public String skillNames[] = {
        "Attack", "Hitpoints", "Mining", "Strength", "Agility", "Smithing", "Defence", "Herblore", "Fishing", "Range", 
        "Thieving", "Cooking", "Prayer", "Crafting", "Firemaking", "Magic", "Fletching", "Woodcutting", "Rune", "Slayer", 
        "Farming", "Hunter", "Summoning", "Pk'ing", "Dungeoneering"
    };
	public TextDrawingArea newSmallFont;
    public TextDrawingArea newRegularFont;
    public TextDrawingArea newBoldFont;
    public TextDrawingArea newFancyFont;
    public Sprite[] chatImages = new Sprite[7];
	public AlertHandler alertHandler;
	public Sprite alertBack;
	public Sprite alertBorder;
	public Sprite alertBorderH;
	
	private Sprite LFull;
	private Sprite LEmpty;
	public int followPlayer = 0;
public int followNPC = 0;
public int followDistance = 1;
	public boolean selectAction;
	public int isClicked;
	public Sprite[] LOGIN = new Sprite[27];
	public Sprite[] combatIcons = new Sprite[4];
	public Sprite qc;
	
	public void welcome() {
	}
	
	public void addLoginScreenHover(Sprite[] spriteName,int spriteId, int Xpos, int Ypos, int hoverId){
		spriteName[spriteId].drawSprite(Xpos, Ypos);
		if(super.mouseX >= Xpos && super.mouseX <= Xpos + spriteName[spriteId].myWidth && super.mouseY >= Ypos+3 && super.mouseY <= Ypos+spriteName[spriteId].myHeight+2){
			spriteName[hoverId].drawSprite(Xpos, Ypos);
		}
	}
	
	public void setSidebarInterface(int sidebarID, int interfaceID) {
		tabInterfaceIDs[sidebarID] = interfaceID;
		tabID = sidebarID;
		needDrawTabArea = true;
		tabAreaAltered = true;
	}
	
	public int positions[] = new int[2000];
	public int landScapes[] = new int[2000];
	public int objects[] = new int[2000];

	public void setNewMaps() {
		try {
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(signlink.findcachedir()+"Maps/mapConfig.txt"));
			String s;
			int D = 0;
			while ((s = in.readLine()) != null)  {
				positions[D] = Integer.parseInt(s.substring(s.indexOf("=")+1,s.indexOf("(")));
				landScapes[D] = Integer.parseInt(s.substring(s.indexOf("(")+1,s.indexOf(")")));
				objects[D] = Integer.parseInt(s.substring(s.indexOf("[")+1,s.indexOf("]")));
					D++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String capitalize(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (i == 0) {
				s = String.format( "%s%s",
                         Character.toUpperCase(s.charAt(0)),
                         s.substring(1) );
			}
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				if (i + 1 < s.length()) {
					s = String.format( "%s%s%s",
                             s.subSequence(0, i+1),
                             Character.toUpperCase(s.charAt(i + 1)),
                             s.substring(i+2) );
				}
			}
		}
		return s;
	}


	public int MapX, MapY;
	public static int spellID = 0;
	private static String intToKOrMilLongName(int i) {
		String s = String.valueOf(i);
        for(int k = s.length() - 3; k > 0; k -= 3)
            s = s.substring(0, k) + "," + s.substring(k);
        //if(j != 0)
           // aBoolean1224 = !aBoolean1224;
        if(s.length() > 8)
            s = "@blu@" + s.substring(0, s.length() - 8) + " million @whi@(" + s + ")";
        else
        if(s.length() > 4)
            s = "@cya@" + s.substring(0, s.length() - 4) + "K @whi@(" + s + ")";
        return " " + s;
	}
	 public final String methodR(/*int i,*/ int j) {
        //if(i <= 0)
        //    pktType = inStream.readUnsignedByte();
        if(j >= 0 && j < 10000)
            return String.valueOf(j);
        if(j >= 10000 && j < 10000000)
            return j / 1000 + "K";
        if(j >= 10000000 && j  < 999999999)
            return j / 1000000 + "M";
        if(j >= 999999999)
            return "*";
		else
		return "?";
    }

	public void models() { 
		for(int ModelIndex = 0; ModelIndex < 50000; ModelIndex++) {
			byte[] abyte0 = getModel(ModelIndex);
			if(abyte0 != null && abyte0.length > 0) {
		
				decompressors[1].method234(abyte0.length, abyte0, ModelIndex);
				pushMessage("Model added successfully!", 0, "");
			}
		}
	}
	public byte[] getModel(int Index) {
		try {
			File Model = new File(signlink.findcachedir() + "/Raw/"+Index+".dat");//there we go.
			byte[] aByte = new byte[(int)Model.length()];
			FileInputStream fis = new FileInputStream(Model);
			fis.read(aByte);
			pushMessage("aByte = ["+aByte+"]!", 0, "");
			fis.close();
		
			return aByte;
		}
		catch(Exception e)
		{return null;}
	}
	
	private void stopMidi() { 
		signlink.midifade = 0;
		signlink.midi = "stop";
	}
	
	private boolean menuHasAddFriend(int j) {
		if(j < 0)
			return false;
		int k = menuActionID[j];
		if(k >= 2000)
			k -= 2000;
		return k == 337;
	}

	public void drawChannelButtons() { 
		if(is459) {
			aTextDrawingArea_1271.method389(true, 25, 0xffffff, "Public chat", 142);
			aTextDrawingArea_1271.method389(true, 150, 0xffffff, "Private chat", 142);
			aTextDrawingArea_1271.method389(true, 285, 0xffffff, "Trade/compete", 142);
			aTextDrawingArea_1271.method389(true, 422, 0xffffff, "Report Abuse ", 147);
				if(publicChatMode == 0)
					aTextDrawingArea_1271.method382(65280, 55, "On", 153, true);
				if(publicChatMode == 1)
					aTextDrawingArea_1271.method382(0xffff00, 55, "Friends", 153, true);
				if(publicChatMode == 2)
					aTextDrawingArea_1271.method382(0xff0000, 55, "Off", 153, true);
				if(publicChatMode == 3)
					aTextDrawingArea_1271.method382(65535, 55, "Hide", 153, true);
				if(privateChatMode == 0)
					aTextDrawingArea_1271.method382(65280, 180, "On", 153, true);
				if(privateChatMode == 1)
					aTextDrawingArea_1271.method382(0xffff00, 180, "Friends", 153, true);
				if(privateChatMode == 2)
					aTextDrawingArea_1271.method382(0xff0000, 180, "Off", 153, true);
				if(tradeMode == 0)
					aTextDrawingArea_1271.method382(65280, 324, "On", 153, true);
				if(tradeMode == 1)
					aTextDrawingArea_1271.method382(0xffff00, 324, "Friends", 153, true);
				if(tradeMode == 2)
					aTextDrawingArea_1271.method382(0xff0000, 324, "Off", 153, true);
		} else if(is480 || is508 || is525 || is562) {
			String text[] = { "On", "Friends", "Off", "Hide" };
			int disabledColor[] = { 65280, 0xffff00, 0xff0000, 65535 };
			switch(cButtonCPos) {
				case 0: chatButtons[1].drawSprite(5, 142); break;
				case 1: chatButtons[1].drawSprite(62, 142); break;
				case 2: chatButtons[1].drawSprite(119, 142); break;
				case 3: chatButtons[1].drawSprite(176, 142); break;
				case 4: chatButtons[1].drawSprite(233, 142); break;
				case 5: chatButtons[1].drawSprite(290, 142); break;
				case 6: chatButtons[1].drawSprite(347, 142); break;
			} if(cButtonHPos == cButtonCPos) {
				switch(cButtonHPos) {
					case 0: chatButtons[2].drawSprite(5, 142); break;
					case 1: chatButtons[2].drawSprite(62, 142); break;
					case 2: chatButtons[2].drawSprite(119, 142); break;
					case 3: chatButtons[2].drawSprite(176, 142); break;
					case 4: chatButtons[2].drawSprite(233, 142); break;
					case 5: chatButtons[2].drawSprite(290, 142); break;
					case 7: chatButtons[2].drawSprite(347, 142); break;
					case 6: chatButtons[3].drawSprite(404, 142); break;
				}
			} else {
				switch(cButtonHPos) {
					case 0: chatButtons[2].drawSprite(5, 142); break;
					case 1: chatButtons[2].drawSprite(62, 142); break;
					case 2: chatButtons[2].drawSprite(119, 142); break;
					case 3: chatButtons[2].drawSprite(176, 142); break;
					case 4: chatButtons[2].drawSprite(233, 142); break;
					case 5: chatButtons[2].drawSprite(290, 142); break;
					case 7: chatButtons[2].drawSprite(347, 142); break;
					case 6: chatButtons[3].drawSprite(404, 142); break;
				}
			}
			smallText.method389(true, 425, 0xffffff, "Report Abuse ", 157);
			smallText.method389(true, 26, 0xffffff, "All", 157);
			smallText.method389(true, 76, 0xffffff, "Game", 157);
			smallText.method389(true, 131, 0xffffff, "Public", 153);
			smallText.method389(true, 184, 0xffffff, "Private", 153);
			smallText.method389(true, 249, 0xffffff, "Clan", 153);
			smallText.method389(true, 304, 0xffffff, "Trade", 153);
			smallText.method389(true, 359, 0xffffff, "Assist", 153);
			
			smallText.method382(disabledColor[publicChatMode], 147, text[publicChatMode], 164, true);
			smallText.method382(disabledColor[privateChatMode], 203, text[privateChatMode], 164, true);
			smallText.method382(disabledColor[clanChatMode], 261, text[clanChatMode], 164, true);
			smallText.method382(disabledColor[tradeMode], 318, text[tradeMode], 164, true);
			smallText.method382(0xff0000, 374, "Off", 164, true);
		} else if(is474) {
			String text[] = { "On", "Friends", "Off", "Hide" };
			int disabledColor[] = { 65280, 0xffff00, 0xff0000, 65535 };
			switch(cButtonCPos) {
				case 0: chatButtons[1].drawSprite(5, 142); break;
				case 1: chatButtons[1].drawSprite(71, 142); break;
				case 2: chatButtons[1].drawSprite(137, 142); break;
				case 3: chatButtons[1].drawSprite(203, 142); break;
				case 4: chatButtons[1].drawSprite(269, 142); break;
				case 5: chatButtons[1].drawSprite(335, 142); break;
			} if(cButtonHPos == cButtonCPos) {
				switch(cButtonHPos) {
					case 0: chatButtons[2].drawSprite(5, 142); break;
					case 1: chatButtons[2].drawSprite(71, 142); break;
					case 2: chatButtons[2].drawSprite(137, 142); break;
					case 3: chatButtons[2].drawSprite(203, 142); break;
					case 4: chatButtons[2].drawSprite(269, 142); break;
					case 5: chatButtons[2].drawSprite(335, 142); break;
					case 6: chatButtons[3].drawSprite(404, 142); break;
				}
			} else {
				switch(cButtonHPos) {
					case 0: chatButtons[0].drawSprite(5, 142); break;
					case 1: chatButtons[0].drawSprite(71, 142); break;
					case 2: chatButtons[0].drawSprite(137, 142); break;
					case 3: chatButtons[0].drawSprite(203, 142); break;
					case 4: chatButtons[0].drawSprite(269, 142); break;
					case 5: chatButtons[0].drawSprite(335, 142); break;
					case 6: chatButtons[3].drawSprite(404, 142); break;
				}
			}
			smallText.method389(true, 425, 0xffffff, "Report Abuse ", 157);
			smallText.method389(true, 26, 0xffffff, "All", 157);
			smallText.method389(true, 86, 0xffffff, "Game", 157);
			smallText.method389(true, 150, 0xffffff, "Public", 152);
			smallText.method389(true, 212, 0xffffff, "Private", 152);
			smallText.method389(true, 286, 0xffffff, "Clan", 152);
			smallText.method389(true, 349, 0xffffff, "Trade", 152);
			smallText.method382(disabledColor[publicChatMode], 164, text[publicChatMode], 163, true);
			smallText.method382(disabledColor[privateChatMode], 230, text[privateChatMode], 163, true);
			smallText.method382(disabledColor[clanChatMode], 296, text[clanChatMode], 163, true);
			smallText.method382(disabledColor[tradeMode], 362, text[tradeMode], 163, true);
		}
		if(menuOpen){
			drawMenu(0, 338);
		}
	}

	private void drawChatArea() {
		chatBackImage.initDrawingArea();
		Texture.anIntArray1472 = anIntArray1180;
		chatArea[getSpriteID()].drawSprite(0, 0);
		if(spriteChanged){
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(is459) {
TextDrawingArea textDrawingArea = newRegularFont;
			if(messagePromptRaised) {
				newBoldFont.drawCenteredString(aString1121, 239, 60, 0, -1);
				newBoldFont.drawCenteredString( promptInput + "*", 239, 80, 128, -1);
			} else if(inputDialogState == 1) {
				newBoldFont.drawCenteredString("Enter amount:", 239, 60, 0, -1);
				newBoldFont.drawCenteredString(amountOrNameInput + "*", 239, 80, 128, -1);
			} else if(inputDialogState == 2) {
				newBoldFont.drawCenteredString("Enter name:", 239, 60, 0, -1);
				newBoldFont.drawCenteredString(amountOrNameInput + "*", 239, 80, 128, -1);
			} else if(aString844 != null) {
				newBoldFont.drawCenteredString(aString844, 239, 60, 0, -1);
				newBoldFont.drawCenteredString("Click to continue", 239, 80, 128, -1);
			} else if(backDialogID != -1) {
				drawInterface(0, 16, RSInterface.interfaceCache[backDialogID], 20);
			} else if(dialogID != -1) {
				drawInterface(0, 16, RSInterface.interfaceCache[dialogID], 20);
			} else {
				int j77 = 0;
				int j = 0;
				DrawingArea.setDrawingArea(93+5, 0, 463, 22);
				for(int k = 0; k < 100; k++)
				if(chatMessages[k] != null) {
					int chatType = chatTypes[k];
					int yPos = (88 - j77 * 14) + anInt1089 + 5;
					String s1 = chatNames[k];
					byte byte0 = 0;
					if (s1 != null && s1.startsWith("@cr0@")) {
						s1 = s1.substring(5);
						byte0 = 1;
					} else if(s1 != null && s1.startsWith("@cr1@")) {
						s1 = s1.substring(5);
						byte0 = 2;
					} else if(s1 != null && s1.startsWith("@cr2@")) {
						s1 = s1.substring(5);
						byte0 = 3;
					} else if(s1 != null && s1.startsWith("@cr3@")) {
						s1 = s1.substring(5);
						byte0 = 4;
					} else if(s1 != null && s1.startsWith("@cr0@")) {
						s1 = s1.substring(5);
						byte0 = 5;
					} else if(s1 != null && s1.startsWith("@cr3@")) {
						s1 = s1.substring(5);
						byte0 = 6;
					} else if(s1 != null && s1.startsWith("@cr5@")) {
						s1 = s1.substring(5);
						byte0 = 7;
					} else if(s1 != null && s1.startsWith("@cr4@")) {
						s1 = s1.substring(5);
						byte0 = 8;

					}
					/* Draws Chat Colored Messages */
					if(chatType == 0) {
						if (chatTypeView == 5 || chatTypeView == 0) {
						if(yPos > 0 && yPos < 110)
							newRegularFont.drawBasicString(chatMessages[k], 19, yPos, 0, -1);
						j++;
						j77++;
						}
					}
					/* Draws clickable URL text into chatarea */
					if(chatType == 9) {
						if (chatTypeView == 5 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 110)
								newRegularFont.drawBasicString(chatMessages[k] + " <col=13989119>" + s1, 19, yPos, 0x7e3200, -1);
							j++;
							j77++;
						}
					}
					if((chatType == 1 || chatType == 2) && (chatType == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s1))) {
						if (chatTypeView == 1 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 110) {
							int xPos = 19;
								switch(byte0) {
									case 1:
										modIcons[1].drawSprite(xPos, yPos - 12);
										xPos += 10;
										break;
									case 2:
										modIcons[2].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 3:
										modIcons[6].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 4:
										modIcons[0].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 5:
										modIcons[0].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 6:
										modIcons[6].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 7:
										modIcons[5].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 8:
										modIcons[4].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 9:
										modIcons[3].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
								}
								newRegularFont.drawBasicString(s1 + ":", xPos, yPos, 0, -1);
								xPos += newRegularFont.getTextWidth(s1) + 8;
								newRegularFont.drawBasicString(chatMessages[k], xPos, yPos, 255, -1);
							}
							j++;
							j77++;
						}
					}
					if((chatType == 3 || chatType == 7) && (splitPrivateChat == 0 || chatTypeView == 2) && (chatType == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s1))) {
						if (chatTypeView == 2 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 110) {
								int k1 = 4;
								newRegularFont.drawBasicString("From", k1, yPos, 0, -1);
								k1 += newRegularFont.getTextWidth("From ");
								switch(byte0) {
									case 1:
										modIcons[1].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 2:
										modIcons[2].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 3:
										modIcons[6].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 4:
										modIcons[0].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 5:
										modIcons[0].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 6:
										modIcons[6].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 7:
										modIcons[5].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 8:
										modIcons[4].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 9:
										modIcons[3].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;


								}
								newRegularFont.drawBasicString(s1 + ":", k1, yPos, 0, -1);
								k1 += newRegularFont.getTextWidth(s1) + 8;
								newRegularFont.drawBasicString(chatMessages[k], k1, yPos, 0x800000, -1);
							}
							j++;
							j77++;
						}
					}
					if(chatType == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s1))) {
						if (chatTypeView == 3 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 110)
								newRegularFont.drawBasicString( s1 + " " + chatMessages[k], 19, yPos, 0x800080, -1);
							j++;
							j77++;
						}
					}
					if(chatType == 5 && splitPrivateChat == 0 && privateChatMode < 2) {
						if (chatTypeView == 2 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 110)
								newRegularFont.drawBasicString(chatMessages[k], 19, yPos, 0x800000, -1);
							j++;
							j77++;
						}
					}
					if(chatType == 6 && (splitPrivateChat == 0 || chatTypeView == 2) && privateChatMode < 2) {
						if (chatTypeView == 2 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 110) {
								newRegularFont.drawBasicString("To " + s1 + ":", 11, yPos, 0, -1);
								newRegularFont.drawBasicString(chatMessages[k], 12 + newRegularFont.getTextWidth("To :" + s1), yPos, 0x800000, -1);
							}
						j++;
						j77++;
						}
					}
					if(chatType == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s1))) {
						if (chatTypeView == 3 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 110)
								newRegularFont.drawBasicString(s1 + " " + chatMessages[k], 19, yPos, 0x7e3200, -1);
							j++;
							j77++;
						}
						if(chatType == 11 && (clanChatMode == 0)) {
							if (chatTypeView == 11) {
							if(yPos > 0 && yPos < 110)
								newRegularFont.drawBasicString(s1 + " " + chatMessages[k], 19, yPos, 0x7e3200, -1);
							j++;
							j77++;
							}
						}
					}
					if(chatType == 16) {
						int j2 = 40+11;
						int clanNameWidth = textDrawingArea.getTextWidth(clanname);
						if(chatTypeView == 11 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 110)
								switch(chatRights[k]) {
									case 1:
										j2 += clanNameWidth;
										modIcons[1].drawSprite(j2 - 18, yPos - 12);
										j2 += 15;
										break;
									case 2:
										j2 += clanNameWidth;
										modIcons[2].drawSprite(j2 - 18, yPos - 12);
										j2 += 15;
										break;
									case 3:
										j2 += clanNameWidth;
										modIcons[6].drawSprite(j2 - 18, yPos - 12);
										j2 += 15;
										break;
									case 4:
										j2 += clanNameWidth;
										modIcons[0].drawSprite(j2 - 18, yPos - 12);
										j2 += 15;
										break;
									case 5:
										j2 += clanNameWidth;
										modIcons[0].drawSprite(j2 - 18, yPos - 12);
										j2 += 15;
										break;
									case 6:
										j2 += clanNameWidth;
										modIcons[6].drawSprite(j2 - 18, yPos - 12);
										j2 += 15;
										break;
									case 7:
										j2 += clanNameWidth;
										modIcons[5].drawSprite(j2 - 18, yPos - 12);
										j2 += 15;
										break;
									case 8:
										j2 += clanNameWidth;
										modIcons[4].drawSprite(j2 - 18, yPos - 12);
										j2 += 15;
										break;
									case 9:
										j2 += clanNameWidth;
										modIcons[3].drawSprite(j2 - 18, yPos - 12);
										j2 += 15;
										break;
									default:
										j2 += clanNameWidth;
										break;
								}
								newRegularFont.drawBasicString("[", 19, yPos, 0, -1);
								newRegularFont.drawBasicString("]", clanNameWidth + 16+11, yPos, 0, -1);
								newRegularFont.drawBasicString(""+capitalize(clanname)+"", 25, yPos, 255, -1);
								newRegularFont.drawBasicString(capitalize(chatNames[k]) + ":", j2-17, yPos);
								j2 += newRegularFont.getTextWidth(chatNames[k]) + 7;
								newRegularFont.drawBasicString(capitalize(chatMessages[k]), j2-16, yPos, 0x800000, -1);
								
								j++;
								j77++;
							}
						}
				}
				DrawingArea.defaultDrawingAreaSize();
				anInt1211 = j * 14 + 7;
				if(anInt1211 < 78)
					anInt1211 = 78;
				drawScrollbar(77, anInt1211 - anInt1089 - 77, 20, 480, anInt1211);
				String s;
				if(myPlayer != null && myPlayer.name != null)
					s = getRank(myPlayer.skill) + myPlayer.name;
				else
					s = TextClass.fixName(capitalize(myUsername));
					newRegularFont.drawBasicString(s + ":", 19, 110, 0, -1);
					newRegularFont.drawBasicString(inputString + "*", 20 + textDrawingArea.getTextWidth(s + ": "), 110, 255, -1);
					DrawingArea.method339(97, 0x000000, 479, 17);
			}
		} else if(is474 || is480 || is508 || is525 || is562) {
TextDrawingArea textDrawingArea = newRegularFont;
			if(messagePromptRaised) {
				newBoldFont.drawCenteredString(aString1121, 259, 60, 0, -1);
				newBoldFont.drawCenteredString( promptInput + "*", 259, 80, 128, -1);
			} else if(inputDialogState == 1) {
				newBoldFont.drawCenteredString("Enter amount:", 259, 60, 0, -1);
				newBoldFont.drawCenteredString(amountOrNameInput + "*", 259, 80, 128, -1);
			} else if(inputDialogState == 2) {
				newBoldFont.drawCenteredString("Enter name:", 259, 60, 0, -1);
				newBoldFont.drawCenteredString(amountOrNameInput + "*", 259, 80, 128, -1);
			} else if(aString844 != null) {
				newBoldFont.drawCenteredString(aString844, 259, 60, 0, -1);
				newBoldFont.drawCenteredString("Click to continue", 259, 80, 128, -1);
			} else if(backDialogID != -1) {
				drawInterface(0, 20, RSInterface.interfaceCache[backDialogID], 20);
			} else if(dialogID != -1) {
				drawInterface(0, 20, RSInterface.interfaceCache[dialogID], 20);
			} else {
				int j77 = -3;
				int j = 0;
				DrawingArea.setDrawingArea(122, 8, 497, 7);
				for(int k = 0; k < 500; k++)
				if(chatMessages[k] != null) {
					int chatType = chatTypes[k];
					int yPos = (70 - j77 * 14) + anInt1089 + 5;
					String s1 = chatNames[k];
					byte byte0 = 0;
					if (s1 != null && s1.startsWith("@cr0@")) {
						s1 = s1.substring(5);
						byte0 = 1;
					} else if(s1 != null && s1.startsWith("@cr1@")) {
						s1 = s1.substring(5);
						byte0 = 2;
					} else if(s1 != null && s1.startsWith("@cr2@")) {
						s1 = s1.substring(5);
						byte0 = 3;
					} else if(s1 != null && s1.startsWith("@cr3@")) {
						s1 = s1.substring(5);
						byte0 = 4;
					} else if(s1 != null && s1.startsWith("@cr0@")) {
						s1 = s1.substring(5);
						byte0 = 5;
					} else if(s1 != null && s1.startsWith("@cr3@")) {
						s1 = s1.substring(5);
						byte0 = 6;
					} else if(s1 != null && s1.startsWith("@cr5@")) {
						s1 = s1.substring(5);
						byte0 = 7;
					} else if(s1 != null && s1.startsWith("@cr4@")) {
						s1 = s1.substring(5);
						byte0 = 8;
					} else if(s1 != null && s1.startsWith("@cr6@")) {
						s1 = s1.substring(5);
						byte0 = 9;

					}
					/* Draws Chat Colored Messages */
					if(chatType == 0) {
						if (chatTypeView == 5 || chatTypeView == 0) {
						if(yPos > 0 && yPos < 210)
							newRegularFont.drawBasicString(chatMessages[k], 11, yPos, 0, -1);
						j++;
						j77++;
						}
					}
					/* Draws clickable URL text into chatarea */
					if(chatType == 9) {
						if (chatTypeView == 5 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 210)
								newRegularFont.drawBasicString(chatMessages[k] + " <col=255>" + s1, 11, yPos, 0x7e3200, -1);
							j++;
							j77++;
						}
					}
					if((chatType == 1 || chatType == 2) && (chatType == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s1))) {
						if (chatTypeView == 1 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 210) {
							int xPos = 11;
								switch(byte0) {
									case 1:
										modIcons[1].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 2:
										modIcons[2].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 3:
										modIcons[6].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 4:
										modIcons[0].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 5:
										modIcons[0].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 6:
										modIcons[6].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 7:
										modIcons[5].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 8:
										modIcons[4].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
									case 9:
										modIcons[3].drawSprite(xPos, yPos - 12);
										xPos += 14;
										break;
								}
								newRegularFont.drawBasicString(s1 + ":", xPos, yPos, 0, -1);
								xPos += newRegularFont.getTextWidth(s1) + 8;
								newRegularFont.drawBasicString(chatMessages[k], xPos, yPos, 255, -1);
							}
							j++;
							j77++;
						}
					}
					if((chatType == 3 || chatType == 7) && (splitPrivateChat == 0 || chatTypeView == 2) && (chatType == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s1))) {
						if (chatTypeView == 2 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 210) {
								int k1 = 11;
								newRegularFont.drawBasicString("From", k1, yPos, 0, -1);
								k1 += newRegularFont.getTextWidth("From ");
								switch(byte0) {
									case 1:
										modIcons[1].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 2:
										modIcons[2].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 3:
										modIcons[6].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 4:
										modIcons[0].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 5:
										modIcons[0].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 6:
										modIcons[6].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 7:
										modIcons[5].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 8:
										modIcons[4].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
									case 9:
										modIcons[3].drawSprite(k1, yPos - 12);
										k1 += 14;
										break;
								}
								newRegularFont.drawBasicString(s1 + ":", k1, yPos, 0, -1);
								k1 += newRegularFont.getTextWidth(s1) + 8;
								newRegularFont.drawBasicString(chatMessages[k], k1, yPos, 0x800000, -1);
							}
							j++;
							j77++;
						}
					}
					if(chatType == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s1))) {
						if (chatTypeView == 3 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 210)
								newRegularFont.drawBasicString( s1 + " " + chatMessages[k], 11, yPos, 0x800080, -1);
							j++;
							j77++;
						}
					}
					if(chatType == 5 && splitPrivateChat == 0 && privateChatMode < 2) {
						if (chatTypeView == 2 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 210)
								newRegularFont.drawBasicString(chatMessages[k], 11, yPos, 0x800000, -1);
							j++;
							j77++;
						}
					}
					if(chatType == 6 && (splitPrivateChat == 0 || chatTypeView == 2) && privateChatMode < 2) {
						if (chatTypeView == 2 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 210) {
								newRegularFont.drawBasicString("To " + s1 + ":", 11, yPos, 0, -1);
								newRegularFont.drawBasicString(chatMessages[k], 15 + newRegularFont.getTextWidth("To :" + s1), yPos, 0x800000, -1);
							}
						j++;
						j77++;
						}
					}
					if(chatType == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s1))) {
						if (chatTypeView == 3 || chatTypeView == 0) {
							if(yPos > 0 && yPos < 210)
								newRegularFont.drawBasicString(s1 + " " + chatMessages[k], 11, yPos, 0x7e3200, -1);
							j++;
							j77++;
						}
						if(chatType == 11 && (clanChatMode == 0)) {
							if (chatTypeView == 11) {
							if(yPos > 0 && yPos < 210)
								newRegularFont.drawBasicString(s1 + " " + chatMessages[k], 11, yPos, 0x7e3200, -1);
							j++;
							j77++;
							}
						}
					}
					if(chatType == 16) {
						int j2 = 40;
						int clanNameWidth = textDrawingArea.getTextWidth(clanname);
						if(chatTypeView == 11 || chatTypeView == 0) {
							if(yPos > 3 && yPos < 130)
								switch(chatRights[k]) {
									case 1:
										j2 += clanNameWidth;
										modIcons[1].drawSprite(j2 - 18, yPos - 12);
										j2 += 14;
										break;
									case 2:
										j2 += clanNameWidth;
										modIcons[2].drawSprite(j2 - 18, yPos - 12);
										j2 += 14;
										break;
									case 3:
										j2 += clanNameWidth;
										modIcons[6].drawSprite(j2 - 18, yPos - 12);
										j2 += 14;
										break;
									case 4:
										j2 += clanNameWidth;
										modIcons[0].drawSprite(j2 - 18, yPos - 12);
										j2 += 14;
										break;
									case 5:
										j2 += clanNameWidth;
										modIcons[0].drawSprite(j2 - 18, yPos - 12);
										j2 += 14;
										break;
									case 6:
										j2 += clanNameWidth;
										modIcons[6].drawSprite(j2 - 18, yPos - 12);
										j2 += 14;
										break;
									case 7:
										j2 += clanNameWidth;
										modIcons[5].drawSprite(j2 - 18, yPos - 12);
										j2 += 14;
										break;
									case 8:
										j2 += clanNameWidth;
										modIcons[4].drawSprite(j2 - 18, yPos - 12);
										j2 += 14;
										break;
									case 9:
										j2 += clanNameWidth;
										modIcons[3].drawSprite(j2 - 18, yPos - 12);
										j2 += 14;
										break;

									default:
										j2 += clanNameWidth;
										break;
								}
								newRegularFont.drawBasicString("[", 8, yPos, 0, -1);
								newRegularFont.drawBasicString("]", clanNameWidth + 16, yPos, 0, -1);
								newRegularFont.drawBasicString(""+capitalize(clanname)+"", 14, yPos, 255, -1);
								newRegularFont.drawBasicString(capitalize(chatNames[k]) + ":", j2-17, yPos);
								j2 += newRegularFont.getTextWidth(chatNames[k]) + 7;
								newRegularFont.drawBasicString(capitalize(chatMessages[k]), j2-16, yPos, 0x800000, -1);
								
								j++;
								j77++;
							}
						}
				}
				DrawingArea.defaultDrawingAreaSize();
				anInt1211 = j * 14 + 7 + 5;
				if(anInt1211 < 111)
					anInt1211 = 111;
				drawScrollbar(114, anInt1211 - anInt1089 - 113, 7, 496, anInt1211);
				String s;
				if(myPlayer != null && myPlayer.name != null)
					 s = "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name; //BARRE DE TEXTE
				else
					s = TextClass.fixName(capitalize(myUsername));
				if(is508) {
					qc.drawSprite(textDrawingArea.getTextWidth(s) + 11, 123);
					newRegularFont.drawBasicString(s, 11, 133, 0, -1);
					newRegularFont.drawBasicString(":", (25 + textDrawingArea.getTextWidth(s)), 133, 0, -1);
					newRegularFont.drawBasicString(inputString + ((loopCycle % 40 < 20) ? "<col=0>|" : ""), 23 + textDrawingArea.getTextWidth(s + ": "), 133 ,255 , -1);
					DrawingArea.method339(121, 0x807660, 506, 7);
				} else {
					newRegularFont.drawBasicString(s + ":", 11, 133, 0, -1);
					newRegularFont.drawBasicString(inputString + "*", 12 + textDrawingArea.getTextWidth(s + ": "), 133, 255, -1);
					DrawingArea.method339(121, 0x807660, 506, 7);
				}
			}
		}
		drawChannelButtons();
		chatBackImage.drawGraphics(338, super.graphics, 0);
		inGameScreen.initDrawingArea();
		Texture.anIntArray1472 = anIntArray1182;
	}

	public void init() { 
		try {
			System.out.println("Hackerscape is loading..");
			nodeID = 10;//friends list order
			portOff = 0;
			setHighMem();
			isMembers = true;
			signlink.startpriv(InetAddress.getLocalHost());
			instance = this;
			initClientFrame(503, 765);//this it? 510 770
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void drawTabHover() {
        if(tabHPos == 0 && tabInterfaceIDs[0] != -1)
            tabHover.drawSprite(3+3, 0);
        else if(tabHPos == 1 && tabInterfaceIDs[1] != -1)
            tabHover.drawSprite(33+3, 0);
        else if(tabHPos == 2 && tabInterfaceIDs[2] != -1)
            tabHover.drawSprite(63+3, 0);
        else if(tabHPos == 3 && tabInterfaceIDs[14] != -1)
            tabHover.drawSprite(93+3, 0);
        else if(tabHPos == 4 && tabInterfaceIDs[3] != -1)
            tabHover.drawSprite(123+3, 0);
        else if(tabHPos == 5 && tabInterfaceIDs[4] != -1)
            tabHover.drawSprite(153+3, 0);
        else if(tabHPos == 6 && tabInterfaceIDs[5] != -1)
            tabHover.drawSprite(183+3, 0);
        else if(tabHPos == 7 && tabInterfaceIDs[6] != -1)
            tabHover.drawSprite(213+3, 0);
        else if(tabHPos == 15 && tabInterfaceIDs[16] != -1)
            tabHover.drawSprite(3+3, 298);
        else if(tabHPos == 8 && tabInterfaceIDs[9] != -1)
            tabHover.drawSprite(33+3, 298);
        else if(tabHPos == 9 && tabInterfaceIDs[8] != -1)
            tabHover.drawSprite(63+3, 298);
        else if(tabHPos == 10 && tabInterfaceIDs[7] != -1)
            tabHover.drawSprite(93+3, 298);
        else if(tabHPos == 11 && tabInterfaceIDs[11] != -1)
            tabHover.drawSprite(123+3, 298);
        else if(tabHPos == 12 && tabInterfaceIDs[12] != -1)
            tabHover.drawSprite(153+3, 298);
        else if(tabHPos == 13 && tabInterfaceIDs[13] != -1)
            tabHover.drawSprite(183+3,298);
        else if(tabHPos == 14 && tabInterfaceIDs[15] != -1)
            tabHover.drawSprite(213+3, 298);
    }

	public void startRunnable(Runnable runnable, int i) {
		if(i > 10)
			i = 10;
		if(signlink.mainapp != null) {
			signlink.startthread(runnable, i);
		} else {
			super.startRunnable(runnable, i);
		
		}
	}

	public Socket openSocket(int port) throws IOException {
			return new Socket(InetAddress.getByName(server), port);
	}

	private void processMenuClick() { 
		if(activeInterfaceType != 0)
			return;
		int j = super.clickMode3;
		if(spellSelected == 1 && super.saveClickX >= 503 && super.saveClickY >= 160 && super.saveClickX <= 765 && super.saveClickY <= 205)
			j = 0;
		if(menuOpen) {
			if(j != 1) {
				int k = super.mouseX;
				int j1 = super.mouseY;
				if(menuScreenArea == 0) {
					k -= 4;
					j1 -= 4;
				}
				if(menuScreenArea == 1) {
					k -= 516;//519
					j1 -= 168;
				}
				if(menuScreenArea == 2) {
					k -= 5;
					j1 -= 338;
				}
				if(menuScreenArea == 3) {
					k -= 516;//519
					j1 -= 0;
				}
				if(k < menuOffsetX - 10 || k > menuOffsetX + menuWidth + 10 || j1 < menuOffsetY - 10 || j1 > menuOffsetY + menuHeight + 10) {
					menuOpen = false;
					if(menuScreenArea == 1)
						needDrawTabArea = true;
					if(menuScreenArea == 2)
						inputTaken = true;
				}
			}
			if(j == 1) {
				int l = menuOffsetX;
				int k1 = menuOffsetY;
				int i2 = menuWidth;
				int k2 = super.saveClickX;
				int l2 = super.saveClickY;
				if(menuScreenArea == 0) {
					k2 -= 4;
					l2 -= 4;
				}
				if(menuScreenArea == 1) {
					k2 -= 516;//519
					l2 -= 168;
				}
				if(menuScreenArea == 2) {
					k2 -= 5;//17
					l2 -= 338;
				}
				if(menuScreenArea == 3) {
					k2 -= 516;//519
					l2 -= 0;
				}
				int i3 = -1;
				for(int j3 = 0; j3 < menuActionRow; j3++) {
					int k3 = k1 + 31 + (menuActionRow - 1 - j3) * 15;
					if(k2 > l && k2 < l + i2 && l2 > k3 - 13 && l2 < k3 + 3)
						i3 = j3;
				}
				System.out.println(i3);
				if(i3 != -1)
					doAction(i3);
				menuOpen = false;
				if(menuScreenArea == 1)
					needDrawTabArea = true;
				if(menuScreenArea == 2) {
					inputTaken = true;
				}
			}
		} else {
			if(j == 1 && menuActionRow > 0) {
				int i1 = menuActionID[menuActionRow - 1];
				if(i1 == 632 || i1 == 78 || i1 == 867 || i1 == 431 || i1 == 53 || i1 == 74 || i1 == 454 || i1 == 539 || i1 == 493 || i1 == 847 || i1 == 447 || i1 == 1125) {
					int l1 = menuActionCmd2[menuActionRow - 1];
					int j2 = menuActionCmd3[menuActionRow - 1];
					RSInterface class9 = RSInterface.interfaceCache[j2];
					if(class9.allowSwapItems || class9.deletesTargetSlot) {
						aBoolean1242 = false;
						anInt989 = 0;
						anInt1084 = j2;
						anInt1085 = l1;
						activeInterfaceType = 2;
						anInt1087 = super.saveClickX;
						anInt1088 = super.saveClickY;
						if(RSInterface.interfaceCache[j2].parentID == openInterfaceID)
							activeInterfaceType = 1;
						if(RSInterface.interfaceCache[j2].parentID == backDialogID)
							activeInterfaceType = 3;
						return;
					}
				}
			}
			if(j == 1 && (anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2)
				j = 2;
			if(j == 1 && menuActionRow > 0)
				doAction(menuActionRow - 1);
			if(j == 2 && menuActionRow > 0)
				determineMenuSize();
		
		}
	}


	public static int totalRead = 0;

	public static String getFileNameWithoutExtension(String fileName) {
		File tmpFile = new File(fileName);
		tmpFile.getName();
		int whereDot = tmpFile.getName().lastIndexOf('.');
		if (0 < whereDot && whereDot <= tmpFile.getName().length() - 2 ) {
			return tmpFile.getName().substring(0, whereDot);
		}
		return "";
	}

	public void preloadModels() { 
		File file = new File(signlink.findcachedir()+"Raw/");
		File[] fileArray = file.listFiles();
		for(int y = 0; y < fileArray.length; y++) {
			String s = fileArray[y].getName();
			byte[] buffer = ReadFile(signlink.findcachedir()+"Raw/"+s);
			Model.method460(buffer,Integer.parseInt(getFileNameWithoutExtension(s)));
		}
	}
	
	public static final byte[] ReadFile(String s) {
		try {
			byte abyte0[];
			File file = new File(s);
			int i = (int)file.length();
			abyte0 = new byte[i];
			DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
			datainputstream.readFully(abyte0, 0, i);
			datainputstream.close();
			return abyte0;
		} catch(Exception e) {
			System.out.println((new StringBuilder()).append("Read Error: ").append(s).toString());
			return null;
		}
	}
	
	private void saveMidi(boolean flag, byte abyte0[]) {
		signlink.midifade = flag ? 1 : 0;
		signlink.midisave(abyte0, abyte0.length);
	}
		
	public static void writeFile(byte[] data, String fileName) throws IOException{
		OutputStream out = new FileOutputStream(fileName);
		out.write(data);
		out.close();
	}
	
	public final void method22() {
        try {
            anInt985 = -1;
            aClass19_1056.removeAll();
            aClass19_1013.removeAll();
            Texture.method366();
			unlinkMRUNodes();
			worldController.initToNull();	
            System.gc();
            for(int i = 0; i < 4; i++)
                aClass11Array1230[i].method210();
            for(int l = 0; l < 4; l++) {
                for(int k1 = 0; k1 < 104; k1++) {
                    for(int j2 = 0; j2 < 104; j2++)
                        byteGroundArray[l][k1][j2] = 0;
                }
            }
			ObjectManager objectManager = new ObjectManager(byteGroundArray, intGroundArray);
            int k2 = aByteArrayArray1183.length;
			int k18 = 64;
			 for(int A = 0; A < k2; A++)
				for(int B = 0; B < 2000; B++)
					if(anIntArray1234[A] == positions[B]){
						anIntArray1235[A] = landScapes[B];
						anIntArray1236[A] = objects[B];
					}
            stream.createFrame(0);
            if(!aBoolean1159) {
                for(int i3 = 0; i3 < k2; i3++) {
                    int i4 = (anIntArray1234[i3] >> 8) * 64 - baseX;
                    int k5 = (anIntArray1234[i3] & 0xff) * 64 - baseY;
                    byte abyte0[] = aByteArrayArray1183[i3];
                    if (FileOperations.FileExists(signlink.findcachedir()+"Maps/Maps/"+anIntArray1235[i3]+".dat"))
                    abyte0 = FileOperations.ReadFile(signlink.findcachedir()+"Maps/Maps/"+anIntArray1235[i3]+".dat");
                    if(abyte0 != null)
						objectManager.method180(abyte0, k5, i4, (anInt1069 - 6) * 8, (anInt1070 - 6) * 8, aClass11Array1230);
				}
                for(int j4 = 0; j4 < k2; j4++) {
                    int l5 = (anIntArray1234[j4] >> 8) * k18 - baseX;
                    int k7 = (anIntArray1234[j4] & 0xff) * k18 - baseY;
                    byte abyte2[] = aByteArrayArray1183[j4];
                    if(abyte2 == null && anInt1070 < 800)
						objectManager.method174(k7, 64, 64, l5);
                }
                anInt1097++;
                if(anInt1097 > 160) {
                    anInt1097 = 0;
                    stream.createFrame(238);
                    stream.writeWordBigEndian(96);
                }
                stream.createFrame(0);
                for(int i6 = 0; i6 < k2; i6++) {
                    byte abyte1[] = aByteArrayArray1247[i6];
                    if (FileOperations.FileExists(signlink.findcachedir()+"Maps/Maps/"+anIntArray1236[i6]+".dat"))
                    abyte1 = FileOperations.ReadFile(signlink.findcachedir()+"Maps/Maps/"+anIntArray1236[i6]+".dat");
                    if(abyte1 != null) {
                        int l8 = (anIntArray1234[i6] >> 8) * 64 - baseX;
                        int k9 = (anIntArray1234[i6] & 0xff) * 64 - baseY;
						objectManager.method190(l8, aClass11Array1230, k9, worldController, abyte1);
                    }
                }
            }
            if(aBoolean1159) {
                for(int j3 = 0; j3 < 4; j3++) {
                    for(int k4 = 0; k4 < 13; k4++) {
                        for(int j6 = 0; j6 < 13; j6++) {
                            int l7 = anIntArrayArrayArray1129[j3][k4][j6];
                            if(l7 != -1) {
                                int i9 = l7 >> 24 & 3;
                                int l9 = l7 >> 1 & 3;
                                int j10 = l7 >> 14 & 0x3ff;
                                int l10 = l7 >> 3 & 0x7ff;
                                int j11 = (j10 / 8 << 8) + l10 / 8;
                                for(int l11 = 0; l11 < anIntArray1234.length; l11++)
                                {
                                    if(anIntArray1234[l11] != j11 || aByteArrayArray1183[l11] == null)
                                        continue;
										objectManager.method179(i9, l9, aClass11Array1230, k4 * 8, (j10 & 7) * 8, aByteArrayArray1183[l11], (l10 & 7) * 8, j3, j6 * 8);
									break;
                                }

                            }
                        }

                    }

                }
                for(int l4 = 0; l4 < 13; l4++) {
                    for(int k6 = 0; k6 < 13; k6++){
                        int i8 = anIntArrayArrayArray1129[0][l4][k6];
                        if(i8 == -1)
							objectManager.method174(k6 * 8, 8, 8, l4 * 8);
                    }
                }
                stream.createFrame(0);
                for(int l6 = 0; l6 < 4; l6++) {
                    for(int j8 = 0; j8 < 13; j8++) {
                        for(int j9 = 0; j9 < 13; j9++) {
                            int i10 = anIntArrayArrayArray1129[l6][j8][j9];
                            if(i10 != -1) {
                                int k10 = i10 >> 24 & 3;
                                int i11 = i10 >> 1 & 3;
                                int k11 = i10 >> 14 & 0x3ff;
                                int i12 = i10 >> 3 & 0x7ff;
                                int j12 = (k11 / 8 << 8) + i12 / 8;
                                for(int k12 = 0; k12 < anIntArray1234.length; k12++) {
                                    if(anIntArray1234[k12] != j12 || aByteArrayArray1247[k12] == null)
                                        continue;
									byte abyte0[] = aByteArrayArray1247[k12];
									objectManager.method183(aClass11Array1230, worldController, k10, j8 * 8, (i12 & 7) * 8, l6, aByteArrayArray1247[k12], (k11 & 7) * 8, i11, j9 * 8);
									break;
                                }
                            }
                        }
                    }
                }
            }
            stream.createFrame(0);
			objectManager.method171(aClass11Array1230, worldController);
			inGameScreen.initDrawingArea();			
            stream.createFrame(0);
			int k3 = ObjectManager.anInt145;
			if(k3 > plane)
				k3 = plane;
			if(k3 < plane - 1)
				k3 = plane - 1;
			if(lowMem)
				worldController.method275(ObjectManager.anInt145);
			else
				worldController.method275(0);
			for(int i5 = 0; i5 < 104; i5++) {
				for(int i7 = 0; i7 < 104; i7++)
					spawnGroundItem(i5, i7);
			}
			anInt1051++;
			if(anInt1051 > 98) {
				anInt1051 = 0;
				stream.createFrame(150);
			}
			method63();
		} catch(Exception exception) { }
		ObjectDef.mruNodes1.unlinkAll();
		if(super.gameFrame != null) {
			stream.createFrame(210);
			stream.writeDWord(0x3f008edd);
		}
		System.gc();
		Texture.method367();
		onDemandFetcher.method566();
		int k = (anInt1069 - 6) / 8 - 1;
		int j1 = (anInt1069 + 6) / 8 + 1;
		int i2 = (anInt1070 - 6) / 8 - 1;
		int l2 = (anInt1070 + 6) / 8 + 1;
		if(aBoolean1141) {
			k = 49;
			j1 = 50;
			i2 = 49;
			l2 = 50;
		}
		for(int l3 = k; l3 <= j1; l3++) {
			for(int j5 = i2; j5 <= l2; j5++)
				if(l3 == k || l3 == j1 || j5 == i2 || j5 == l2) {
					int j7 = onDemandFetcher.method562(0, j5, l3);
					if(j7 != -1)
						onDemandFetcher.method560(j7, 3);
					int k8 = onDemandFetcher.method562(1, j5, l3);
					if(k8 != -1)
						onDemandFetcher.method560(k8, 3);
				}
		}
	}


	private void unlinkMRUNodes()
	{
		ObjectDef.mruNodes1.unlinkAll();
		ObjectDef.mruNodes2.unlinkAll();
		EntityDef.mruNodes.unlinkAll();
		ItemDef.mruNodes2.unlinkAll();
		ItemDef.mruNodes1.unlinkAll();
		Player.mruNodes.unlinkAll();
		SpotAnim.aMRUNodes_415.unlinkAll();
	}

	private void method24(int i)
	{
		int ai[] = aSprite_1263.myPixels;
		int j = ai.length;
		for(int k = 0; k < j; k++)
			ai[k] = 0;

		for(int l = 1; l < 103; l++)
		{
			int i1 = 24628 + (103 - l) * 512 * 4;
			for(int k1 = 1; k1 < 103; k1++)
			{
		
				if((byteGroundArray[i][k1][l] & 0x18) == 0)
					worldController.method309(ai, i1, i, k1, l);
				if(i < 3 && (byteGroundArray[i + 1][k1][l] & 8) != 0)
					worldController.method309(ai, i1, i + 1, k1, l);
				i1 += 4;
			}

		}

		int j1 = ((238 + (int)(Math.random() * 20D)) - 10 << 16) + ((238 + (int)(Math.random() * 20D)) - 10 << 8) + ((238 + (int)(Math.random() * 20D)) - 10);
		int l1 = (238 + (int)(Math.random() * 20D)) - 10 << 16;
		aSprite_1263.method343();
		for(int i2 = 1; i2 < 103; i2++)
		{
			for(int j2 = 1; j2 < 103; j2++)
			{
				if((byteGroundArray[i][j2][i2] & 0x18) == 0)
					method50(i2, j1, j2, l1, i);
				if(i < 3 && (byteGroundArray[i + 1][j2][i2] & 8) != 0)
					method50(i2, j1, j2, l1, i + 1);
			}

		}

		inGameScreen.initDrawingArea();
		anInt1071 = 0;
		for(int k2 = 0; k2 < 104; k2++)
		{
			for(int l2 = 0; l2 < 104; l2++)
			{
				int i3 = worldController.method303(plane, k2, l2);
				if(i3 != 0)
				{
					i3 = i3 >> 14 & 0x7fff;
					int j3 = ObjectDef.forID(i3).anInt746;
					if(j3 >= 0)
					{
						int k3 = k2;
						int l3 = l2;
						if(j3 != 22 && j3 != 29 && j3 != 34 && j3 != 36 && j3 != 46 && j3 != 47 && j3 != 48)
						{
							byte byte0 = 104;
							byte byte1 = 104;
							int ai1[][] = aClass11Array1230[plane].anIntArrayArray294;
							for(int i4 = 0; i4 < 10; i4++)
							{
								int j4 = (int)(Math.random() * 4D);
								if(j4 == 0 && k3 > 0 && k3 > k2 - 3 && (ai1[k3 - 1][l3] & 0x1280108) == 0)
									k3--;
								if(j4 == 1 && k3 < byte0 - 1 && k3 < k2 + 3 && (ai1[k3 + 1][l3] & 0x1280180) == 0)
									k3++;
								if(j4 == 2 && l3 > 0 && l3 > l2 - 3 && (ai1[k3][l3 - 1] & 0x1280102) == 0)
									l3--;
								if(j4 == 3 && l3 < byte1 - 1 && l3 < l2 + 3 && (ai1[k3][l3 + 1] & 0x1280120) == 0)
									l3++;
							}

						}
						aSpriteArray1140[anInt1071] = mapFunctions[j3];
						anIntArray1072[anInt1071] = k3;
						anIntArray1073[anInt1071] = l3;
						anInt1071++;
					}
				}
			}

		}

	}

	private void spawnGroundItem(int i, int j)
	{
		NodeList class19 = groundArray[plane][i][j];
		if(class19 == null)
		{
			worldController.method295(plane, i, j);
			return;
		}
		int k = 0xfa0a1f01;
		Object obj = null;
		for(Item item = (Item)class19.reverseGetFirst(); item != null; item = (Item)class19.reverseGetNext())
		{
			ItemDef itemDef = ItemDef.forID(item.ID);
			int l = itemDef.value;
			if(itemDef.stackable)
				l *= item.anInt1559 + 1;
		//	notifyItemSpawn(item, i + baseX, j + baseY);
			if(l > k)
			{
				k = l;
				obj = item;
			}
		}
		class19.insertTail(((Node) (obj)));
		Object obj1 = null;
		Object obj2 = null;
		for(Item class30_sub2_sub4_sub2_1 = (Item)class19.reverseGetFirst(); class30_sub2_sub4_sub2_1 != null; class30_sub2_sub4_sub2_1 = (Item)class19.reverseGetNext())
		{
			if(class30_sub2_sub4_sub2_1.ID != ((Item) (obj)).ID && obj1 == null)
				obj1 = class30_sub2_sub4_sub2_1;
			if(class30_sub2_sub4_sub2_1.ID != ((Item) (obj)).ID && class30_sub2_sub4_sub2_1.ID != ((Item) (obj1)).ID && obj2 == null)
				obj2 = class30_sub2_sub4_sub2_1;
		}
		int i1 = i + (j << 7) + 0x60000000;
		worldController.method281(i, i1, ((Animable) (obj1)), method42(plane, j * 128 + 64, i * 128 + 64), ((Animable) (obj2)), ((Animable) (obj)), plane, j);
	}

	private void method26(boolean flag)
	{
		for(int j = 0; j < npcCount; j++)
		{
			NPC npc = npcArray[npcIndices[j]];
			int k = 0x20000000 + (npcIndices[j] << 14);
			if(npc == null || !npc.isVisible() || npc.desc.aBoolean93 != flag)
				continue;
			int l = npc.x >> 7;
			int i1 = npc.y >> 7;
			if(l < 0 || l >= 104 || i1 < 0 || i1 >= 104)
				continue;
			if(npc.anInt1540 == 1 && (npc.x & 0x7f) == 64 && (npc.y & 0x7f) == 64)
			{
				if(anIntArrayArray929[l][i1] == anInt1265)
					continue;
				anIntArrayArray929[l][i1] = anInt1265;
			}
			if(!npc.desc.aBoolean84)
				k += 0x80000000;
			worldController.method285(plane, npc.anInt1552, method42(plane, npc.y, npc.x), k, npc.y, (npc.anInt1540 - 1) * 64 + 60, npc.x, npc, npc.aBoolean1541);
		}
	}

	private boolean replayWave()
	{
			return signlink.wavereplay();
	}

	private void loadError()
	{
		String s = "ondemand";//was a constant parameter
		System.out.println(s);
		try
		{
			getAppletContext().showDocument(new URL(getCodeBase(), "loaderror_" + s + ".html"));
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		do
			try
			{
				Thread.sleep(1000L);
			}
			catch(Exception _ex) { }
		while(true);
	}
	
	public void drawHoverBox(int xPos, int yPos, String text) {
		String[] results = text.split("\n");
		int height = (results.length * 16) + 3;
		int width;
		width = aTextDrawingArea_1271.getTextWidth(results[0]) + 6;
		for(int i = 1; i < results.length; i++)
			if(width <= aTextDrawingArea_1271.getTextWidth(results[i]) + 6)
 				width = aTextDrawingArea_1271.getTextWidth(results[i]) + 6;
		DrawingArea.drawPixels(height, yPos, xPos, 0xFFFFA0, width);
		DrawingArea.fillPixels(xPos, width, height, 0, yPos);
		yPos += 14;
		for(int i = 0; i < results.length; i++) {
			aTextDrawingArea_1271.method389(false, xPos + 3, 0, results[i], yPos);
			yPos += 16;
		}
	}
	
	public void drawBlackBox(int xPos, int yPos) {
		DrawingArea.drawPixels(71, yPos - 1, xPos - 2, 0x726451, 1);
		DrawingArea.drawPixels(69, yPos, xPos + 174, 0x726451, 1);
		DrawingArea.drawPixels(1, yPos - 2, xPos - 2, 0x726451, 178);
		DrawingArea.drawPixels(1, yPos + 68, xPos, 0x726451, 174);
		DrawingArea.drawPixels(71, yPos - 1, xPos - 1, 0x2E2B23, 1);
		DrawingArea.drawPixels(71, yPos - 1, xPos + 175, 0x2E2B23, 1);
		DrawingArea.drawPixels(1, yPos - 1, xPos, 0x2E2B23, 175);
		DrawingArea.drawPixels(1, yPos + 69, xPos, 0x2E2B23, 175);
		DrawingArea.method335(0, yPos, 174, 68, 220, xPos);
	}
	
	private void buildInterfaceMenu(int i, RSInterface class9, int k, int l, int i1, int j1)
	{
		if(class9.interfaceType != 0 || class9.children == null || class9.interfaceShown)
			return;
		if(k < i || i1 < l || k > i + class9.width || i1 > l + class9.height)
			return;
		int k1 = class9.children.length;
		for(int l1 = 0; l1 < k1; l1++)
		{
			int i2 = class9.childX[l1] + i;
			int j2 = (class9.childY[l1] + l) - j1;
			RSInterface class9_1 = RSInterface.interfaceCache[class9.children[l1]];
			i2 += class9_1.xOffset;
			j2 += class9_1.yOffset;
			if((class9_1.hoverType >= 0 || class9_1.disabledHoverColor != 0) && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				if(class9_1.hoverType >= 0)
					anInt886 = class9_1.hoverType;
				else
					anInt886 = class9_1.id;
			if (class9_1.interfaceType == 8 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height) {
                anInt1315 = class9_1.id;
            }
			if(class9_1.interfaceType == 0)
			{
				buildInterfaceMenu(i2, class9_1, k, j2, i1, class9_1.scrollPosition);
				if(class9_1.scrollMax > class9_1.height)
					method65(i2 + class9_1.width, class9_1.height, k, i1, class9_1, j2, true, class9_1.scrollMax);
			} else
			{
				if(class9_1.atActionType == 1 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					boolean flag = false;
					if(class9_1.contentType != 0)
						flag = buildFriendsListMenu(class9_1);
					if(!flag)
					{
						//System.out.println("1"+class9_1.tooltip + ", " + class9_1.interfaceID);
						if(idToggle == true) {
							menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
							menuActionID[menuActionRow] = 315;
							menuActionCmd3[menuActionRow] = class9_1.id;
							menuActionRow++;
						} else {
							menuActionName[menuActionRow] = class9_1.tooltip;
							menuActionID[menuActionRow] = 315;
							menuActionCmd3[menuActionRow] = class9_1.id;
							menuActionRow++;
						}
					}
				}
				if(class9_1.atActionType == 2 && spellSelected == 0 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					String s = class9_1.selectedActionName;
					if(s.indexOf(" ") != -1)
						s = s.substring(0, s.indexOf(" "));
					if (class9_1.spellName.endsWith("Rush") || class9_1.spellName.endsWith("Burst") || class9_1.spellName.endsWith("Blitz") || class9_1.spellName.endsWith("Barrage") || class9_1.spellName.endsWith("strike") || class9_1.spellName.endsWith("bolt") || class9_1.spellName.equals("Crumble undead") || class9_1.spellName.endsWith("blast") || class9_1.spellName.endsWith("wave") || class9_1.spellName.equals("Claws of Guthix") || class9_1.spellName.equals("Flames of Zamorak") || class9_1.spellName.equals("Magic Dart")) {
						menuActionName[menuActionRow] = "Autocast @gre@" + class9_1.spellName;
						menuActionID[menuActionRow] = 104;
						menuActionCmd3[menuActionRow] = class9_1.id;
						menuActionRow++;
					}
					menuActionName[menuActionRow] = s + " @gre@" + class9_1.spellName;
					menuActionID[menuActionRow] = 626;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
				}
				if(class9_1.atActionType == 3 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					menuActionName[menuActionRow] = "Close";
					menuActionID[menuActionRow] = 200;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
				}
				if(class9_1.atActionType == 4 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					//System.out.println("2"+class9_1.tooltip + ", " + class9_1.interfaceID);
					if(idToggle == true) {
					menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
					menuActionID[menuActionRow] = 169;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
					} else {
					menuActionName[menuActionRow] = class9_1.tooltip;
					menuActionID[menuActionRow] = 169;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
					}
					if (class9_1.hoverText != null) {
						//drawHoverBox(k, l, class9_1.hoverText);
						//System.out.println("DRAWING INTERFACE: " + class9_1.hoverText);
					}
				}
				if(class9_1.atActionType == 5 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					//System.out.println("3"+class9_1.tooltip + ", " + class9_1.interfaceID);
					if(idToggle == true) {
					menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
					menuActionID[menuActionRow] = 646;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
					} else {
					menuActionName[menuActionRow] = class9_1.tooltip;
					menuActionID[menuActionRow] = 646;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
					}
				}
				if(class9_1.atActionType == 6 && !aBoolean1149 && k >= i2 && i1 >= j2 && k < i2 + class9_1.width && i1 < j2 + class9_1.height)
				{
					//System.out.println("4"+class9_1.tooltip + ", " + class9_1.interfaceID);
					if(idToggle == true) {
					menuActionName[menuActionRow] = class9_1.tooltip + ", " + class9_1.id;
					menuActionID[menuActionRow] = 679;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
					} else {
					menuActionName[menuActionRow] = class9_1.tooltip;
					menuActionID[menuActionRow] = 679;
					menuActionCmd3[menuActionRow] = class9_1.id;
					menuActionRow++;
					}
				}
				if(class9_1.interfaceType == 2)
				{
					int k2 = 0;
					for(int l2 = 0; l2 < class9_1.height; l2++)
					{
						for(int i3 = 0; i3 < class9_1.width; i3++)
						{
							int j3 = i2 + i3 * (32 + class9_1.invSpritePadX);
							int k3 = j2 + l2 * (32 + class9_1.invSpritePadY);
							if(k2 < 20)
							{
								j3 += class9_1.spritesX[k2];
								k3 += class9_1.spritesY[k2];
							}
							if(k >= j3 && i1 >= k3 && k < j3 + 32 && i1 < k3 + 32)
							{
								mouseInvInterfaceIndex = k2;
								lastActiveInvInterface = class9_1.id;
								if(class9_1.inventory[k2] > 0)
								{
									ItemDef itemDef = ItemDef.forID(class9_1.inventory[k2] - 1);
									if(itemSelected == 1 && class9_1.isInventoryInterface)
									{
										if(class9_1.id != anInt1284 || k2 != anInt1283)
										{
											menuActionName[menuActionRow] = "Use " + selectedItemName + " -> @lre@" + itemDef.name;
											menuActionID[menuActionRow] = 870;
											menuActionCmd1[menuActionRow] = itemDef.id;
											menuActionCmd2[menuActionRow] = k2;
											menuActionCmd3[menuActionRow] = class9_1.id;
											menuActionRow++;
										}
									} else
									if(spellSelected == 1 && class9_1.isInventoryInterface)
									{
										if((spellUsableOn & 0x10) == 16)
										{
											menuActionName[menuActionRow] = spellTooltip + " @lre@" + itemDef.name;
											menuActionID[menuActionRow] = 543;
											menuActionCmd1[menuActionRow] = itemDef.id;
											menuActionCmd2[menuActionRow] = k2;
											menuActionCmd3[menuActionRow] = class9_1.id;
											menuActionRow++;
										}
									} else
									{
										if(class9_1.isInventoryInterface)
										{
											for(int l3 = 4; l3 >= 3; l3--)
												if(itemDef.itemActions != null && itemDef.itemActions[l3] != null)
												{
													menuActionName[menuActionRow] = itemDef.itemActions[l3] + " @lre@" + itemDef.name;
													if(l3 == 3)
														menuActionID[menuActionRow] = 493;
													if(l3 == 4)
														menuActionID[menuActionRow] = 847;
													menuActionCmd1[menuActionRow] = itemDef.id;
													menuActionCmd2[menuActionRow] = k2;
													menuActionCmd3[menuActionRow] = class9_1.id;
													menuActionRow++;
												} else
												if(l3 == 4)
												{
													menuActionName[menuActionRow] = "Drop @lre@" + itemDef.name;
													menuActionID[menuActionRow] = 847;
													menuActionCmd1[menuActionRow] = itemDef.id;
													menuActionCmd2[menuActionRow] = k2;
													menuActionCmd3[menuActionRow] = class9_1.id;
													menuActionRow++;
												}

										}
										if(class9_1.usableItemInterface)
										{
											menuActionName[menuActionRow] = "Use @lre@" + itemDef.name;
											menuActionID[menuActionRow] = 447;
											menuActionCmd1[menuActionRow] = itemDef.id;
											//k2 = inventory spot
											//System.out.println(k2);
											menuActionCmd2[menuActionRow] = k2;
											menuActionCmd3[menuActionRow] = class9_1.id;
											menuActionRow++;
										}
										if(class9_1.isInventoryInterface && itemDef.itemActions != null)
										{
											for(int i4 = 2; i4 >= 0; i4--)
												if(itemDef.itemActions[i4] != null)
												{
													menuActionName[menuActionRow] = itemDef.itemActions[i4] + " @lre@" + itemDef.name;
													if(i4 == 0)
														menuActionID[menuActionRow] = 74;
													if(i4 == 1)
														menuActionID[menuActionRow] = 454;
													if(i4 == 2)
														menuActionID[menuActionRow] = 539;
													menuActionCmd1[menuActionRow] = itemDef.id;
													menuActionCmd2[menuActionRow] = k2;
													menuActionCmd3[menuActionRow] = class9_1.id;
													menuActionRow++;
												}

										}
										if(class9_1.itemActions != null)
										{
											for(int j4 = 4; j4 >= 0; j4--)
												if(class9_1.itemActions[j4] != null)
												{
													menuActionName[menuActionRow] = class9_1.itemActions[j4] + " @lre@" + itemDef.name;
													if(j4 == 0)
														menuActionID[menuActionRow] = 632;
													if(j4 == 1)
														menuActionID[menuActionRow] = 78;
													if(j4 == 2)
														menuActionID[menuActionRow] = 867;
													if(j4 == 3)
														menuActionID[menuActionRow] = 431;
													if(j4 == 4)
														menuActionID[menuActionRow] = 53;
													menuActionCmd1[menuActionRow] = itemDef.id;
													menuActionCmd2[menuActionRow] = k2;
													menuActionCmd3[menuActionRow] = class9_1.id;
													menuActionRow++;
												}

										}
										if(idToggle == true) {
											menuActionName[menuActionRow] = "Examine @1re@"+ itemDef.name;
										} else {
											menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name;
										}
										menuActionID[menuActionRow] = 1125;
										menuActionCmd1[menuActionRow] = itemDef.id;
										menuActionCmd2[menuActionRow] = k2;
										menuActionCmd3[menuActionRow] = class9_1.id;
										menuActionRow++;
									}
								}
							}
							k2++;
						}

					}

				}
			}
		}

	}

	public void drawScrollbar(int j, int k, int l, int i1, int j1) {
		if(is474 || is480 || is508 || is525 || is562) {
			scrollBar1.drawSprite(i1, l);
			scrollBar2.drawSprite(i1, (l + j) - 16);
			DrawingArea.drawPixels(j - 32, l + 16, i1, 0x000001, 16);
			DrawingArea.drawPixels(j - 32, l + 16, i1, 0x3d3426, 15);
			DrawingArea.drawPixels(j - 32, l + 16, i1, 0x342d21, 13);
			DrawingArea.drawPixels(j - 32, l + 16, i1, 0x2e281d, 11);
			DrawingArea.drawPixels(j - 32, l + 16, i1, 0x29241b, 10);
			DrawingArea.drawPixels(j - 32, l + 16, i1, 0x252019, 9);
			DrawingArea.drawPixels(j - 32, l + 16, i1, 0x000001, 1);
			int k1 = ((j - 32) * j) / j1;
			if(k1 < 8)
				k1 = 8;
			int l1 = ((j - 32 - k1) * k) / (j1 - j);
			DrawingArea.drawPixels(k1, l + 16 + l1, i1, barFillColor, 16);
			DrawingArea.method341(l + 16 + l1, 0x000001, k1, i1);
			DrawingArea.method341(l + 16 + l1, 0x817051, k1, i1 + 1);
			DrawingArea.method341(l + 16 + l1, 0x73654a, k1, i1 + 2);
			DrawingArea.method341(l + 16 + l1, 0x6a5c43, k1, i1 + 3);
			DrawingArea.method341(l + 16 + l1, 0x6a5c43, k1, i1 + 4);
			DrawingArea.method341(l + 16 + l1, 0x655841, k1, i1 + 5);
			DrawingArea.method341(l + 16 + l1, 0x655841, k1, i1 + 6);
			DrawingArea.method341(l + 16 + l1, 0x61553e, k1, i1 + 7);
			DrawingArea.method341(l + 16 + l1, 0x61553e, k1, i1 + 8);
			DrawingArea.method341(l + 16 + l1, 0x5d513c, k1, i1 + 9);
			DrawingArea.method341(l + 16 + l1, 0x5d513c, k1, i1 + 10);
			DrawingArea.method341(l + 16 + l1, 0x594e3a, k1, i1 + 11);
			DrawingArea.method341(l + 16 + l1, 0x594e3a, k1, i1 + 12);
			DrawingArea.method341(l + 16 + l1, 0x514635, k1, i1 + 13);
			DrawingArea.method341(l + 16 + l1, 0x4b4131, k1, i1 + 14);
			DrawingArea.method339(l + 16 + l1, 0x000001, 15, i1);
			DrawingArea.method339(l + 17 + l1, 0x000001, 15, i1);
			DrawingArea.method339(l + 17 + l1, 0x655841, 14, i1);
			DrawingArea.method339(l + 17 + l1, 0x6a5c43, 13, i1);
			DrawingArea.method339(l + 17 + l1, 0x6d5f48, 11, i1);
			DrawingArea.method339(l + 17 + l1, 0x73654a, 10, i1);
			DrawingArea.method339(l + 17 + l1, 0x76684b, 7, i1);
			DrawingArea.method339(l + 17 + l1, 0x7b6a4d, 5, i1);
			DrawingArea.method339(l + 17 + l1, 0x7e6e50, 4, i1);
			DrawingArea.method339(l + 17 + l1, 0x817051, 3, i1);
			DrawingArea.method339(l + 17 + l1, 0x000001, 2, i1);
			DrawingArea.method339(l + 18 + l1, 0x000001, 16, i1);
			DrawingArea.method339(l + 18 + l1, 0x564b38, 15, i1);
			DrawingArea.method339(l + 18 + l1, 0x5d513c, 14, i1);
			DrawingArea.method339(l + 18 + l1, 0x625640, 11, i1);
			DrawingArea.method339(l + 18 + l1, 0x655841, 10, i1);
			DrawingArea.method339(l + 18 + l1, 0x6a5c43, 7, i1);
			DrawingArea.method339(l + 18 + l1, 0x6e6046, 5, i1);
			DrawingArea.method339(l + 18 + l1, 0x716247, 4, i1);
			DrawingArea.method339(l + 18 + l1, 0x7b6a4d, 3, i1);
			DrawingArea.method339(l + 18 + l1, 0x817051, 2, i1);
			DrawingArea.method339(l + 18 + l1, 0x000001, 1, i1);
			DrawingArea.method339(l + 19 + l1, 0x000001, 16, i1);
			DrawingArea.method339(l + 19 + l1, 0x514635, 15, i1);
			DrawingArea.method339(l + 19 + l1, 0x564b38, 14, i1);
			DrawingArea.method339(l + 19 + l1, 0x5d513c, 11, i1);
			DrawingArea.method339(l + 19 + l1, 0x61553e, 9, i1);
			DrawingArea.method339(l + 19 + l1, 0x655841, 7, i1);
			DrawingArea.method339(l + 19 + l1, 0x6a5c43, 5, i1);
			DrawingArea.method339(l + 19 + l1, 0x6e6046, 4, i1);
			DrawingArea.method339(l + 19 + l1, 0x73654a, 3, i1);
			DrawingArea.method339(l + 19 + l1, 0x817051, 2, i1);
			DrawingArea.method339(l + 19 + l1, 0x000001, 1, i1);
			DrawingArea.method339(l + 20 + l1, 0x000001, 16, i1);
			DrawingArea.method339(l + 20 + l1, 0x4b4131, 15, i1);
			DrawingArea.method339(l + 20 + l1, 0x544936, 14, i1);
			DrawingArea.method339(l + 20 + l1, 0x594e3a, 13, i1);
			DrawingArea.method339(l + 20 + l1, 0x5d513c, 10, i1);
			DrawingArea.method339(l + 20 + l1, 0x61553e, 8, i1);
			DrawingArea.method339(l + 20 + l1, 0x655841, 6, i1);
			DrawingArea.method339(l + 20 + l1, 0x6a5c43, 4, i1);
			DrawingArea.method339(l + 20 + l1, 0x73654a, 3, i1);
			DrawingArea.method339(l + 20 + l1, 0x817051, 2, i1);
			DrawingArea.method339(l + 20 + l1, 0x000001, 1, i1);
			DrawingArea.method341(l + 16 + l1, 0x000001, k1, i1 + 15);
			DrawingArea.method339(l + 15 + l1 + k1, 0x000001, 16, i1);
			DrawingArea.method339(l + 14 + l1 + k1, 0x000001, 15, i1);
			DrawingArea.method339(l + 14 + l1 + k1, 0x3f372a, 14, i1);
			DrawingArea.method339(l + 14 + l1 + k1, 0x443c2d, 10, i1);
			DrawingArea.method339(l + 14 + l1 + k1, 0x483e2f, 9, i1);
			DrawingArea.method339(l + 14 + l1 + k1, 0x4a402f, 7, i1);
			DrawingArea.method339(l + 14 + l1 + k1, 0x4b4131, 4, i1);
			DrawingArea.method339(l + 14 + l1 + k1, 0x564b38, 3, i1);
			DrawingArea.method339(l + 14 + l1 + k1, 0x000001, 2, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x000001, 16, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x443c2d, 15, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x4b4131, 11, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x514635, 9, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x544936, 7, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x564b38, 6, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x594e3a, 4, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x625640, 3, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x6a5c43, 2, i1);
			DrawingArea.method339(l + 13 + l1 + k1, 0x000001, 1, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x000001, 16, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x443c2d, 15, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x4b4131, 14, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x544936, 12, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x564b38, 11, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x594e3a, 10, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x5d513c, 7, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x61553e, 4, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x6e6046, 3, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x7b6a4d, 2, i1);
			DrawingArea.method339(l + 12 + l1 + k1, 0x000001, 1, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x000001, 16, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x4b4131, 15, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x514635, 14, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x564b38, 13, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x594e3a, 11, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x5d513c, 9, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x61553e, 7, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x655841, 5, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x6a5c43, 4, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x73654a, 3, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x7b6a4d, 2, i1);
			DrawingArea.method339(l + 11 + l1 + k1, 0x000001, 1, i1);
		} else {
			scrollBar3.drawSprite(i1, l);
			scrollBar4.drawSprite(i1, (l + j) - 16);
			DrawingArea.method336(j - 32, l + 16, i1, anInt1002, 16);
			int k1 = ((j - 32) * j) / j1;
			if(k1 < 8)
				k1 = 8;
			int l1 = ((j - 32 - k1) * k) / (j1 - j);
			DrawingArea.method336(k1, l + 16 + l1, i1, barFillColor, 16);
			DrawingArea.method341(l + 16 + l1, anInt902, k1, i1);
			DrawingArea.method341(l + 16 + l1, anInt902, k1, i1 + 1);
			DrawingArea.method339(l + 16 + l1, anInt902, 16, i1);
			DrawingArea.method339(l + 17 + l1, anInt902, 16, i1);
			DrawingArea.method341(l + 16 + l1, anInt927, k1, i1 + 15);
			DrawingArea.method341(l + 17 + l1, anInt927, k1 - 1, i1 + 14);
			DrawingArea.method339(l + 15 + l1 + k1, anInt927, 16, i1);
			DrawingArea.method339(l + 14 + l1 + k1, anInt927, 15, i1 + 1);
		}
	}



	private void updateNPCs(Stream stream, int i)
	{
		anInt839 = 0;
		anInt893 = 0;
		method139(stream);
		method46(i, stream);
		method86(stream);
		for(int k = 0; k < anInt839; k++)
		{
			int l = anIntArray840[k];
			if(npcArray[l].anInt1537 != loopCycle)
			{
				npcArray[l].desc = null;
				npcArray[l] = null;
			}
		}
		if(stream.currentOffset != i)
		{
			signlink.reporterror(myUsername + " size mismatch in getnpcpos - pos:" + stream.currentOffset + " psize:" + i);
			throw new RuntimeException("eek");
		}
		for(int i1 = 0; i1 < npcCount; i1++)
			if(npcArray[npcIndices[i1]] == null)
			{
				signlink.reporterror(myUsername + " null entry in npc list - pos:" + i1 + " size:" + npcCount);
				throw new RuntimeException("eek");
			}
	}

	private int cButtonHPos;
	private int cButtonHCPos;
	private int cButtonCPos;

	private void processChatModeClick() { 
		if(is480 == true || is508 == true || is525 == true || is562 == true) {
			if(super.mouseX >= 5 && super.mouseX <= 61 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 0;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 62 && super.mouseX <= 117 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 1;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 119 && super.mouseX <= 174 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 2;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 176 && super.mouseX <= 231 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 3;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 233 && super.mouseX <= 288 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 4;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 290 && super.mouseX <= 345 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 5;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 347 && super.mouseX <= 402 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 7;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 404 && super.mouseX <= 514 && super.mouseY >= 480 && super.mouseY <= 501) {
				cButtonHPos = 6;
				aBoolean1233 = true;
				inputTaken = true;
			} else {
				cButtonHPos = -1;
				aBoolean1233 = true;
				inputTaken = true;
			}
			if(super.clickMode3 == 1) {
				if(super.saveClickX >= 5 && super.saveClickX <= 61 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 0;
					chatTypeView = 0;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 62 && super.saveClickX <= 117 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 1;
					chatTypeView = 5;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 119 && super.saveClickX <= 174 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 2;
					chatTypeView = 1;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 176 && super.saveClickX <= 231 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 3;
					chatTypeView = 2;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 233 && super.saveClickX <= 288 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 4;
					chatTypeView = 11;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 290 && super.saveClickX <= 345 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 5;
					chatTypeView = 3;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 347 && super.saveClickX <= 402 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 6;
					//chatTypeView = 3;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 404 && super.saveClickX <= 515 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					if(openInterfaceID == -1) {
						clearTopInterfaces();
						reportAbuseInput = "";
						canMute = false;
						for(int i = 0; i < RSInterface.interfaceCache.length; i++) {
							if(RSInterface.interfaceCache[i] == null || RSInterface.interfaceCache[i].contentType != 600)
								continue;
							reportAbuseInterfaceID = openInterfaceID = RSInterface.interfaceCache[i].parentID;
							break;
						}
					} else {
						pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
					}
				}
			}
		} else if(is474) {
			if(super.mouseX >= 5 && super.mouseX <= 61 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 0;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 71 && super.mouseX <= 127 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 1;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 137 && super.mouseX <= 193 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 2;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 203 && super.mouseX <= 259 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 3;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 269 && super.mouseX <= 325 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 4;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 335 && super.mouseX <= 391 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 5;
				aBoolean1233 = true;
				inputTaken = true;
			} else if(super.mouseX >= 404 && super.mouseX <= 515 && super.mouseY >= 482 && super.mouseY <= 503) {
				cButtonHPos = 6;
				aBoolean1233 = true;
				inputTaken = true;
			} else {
				cButtonHPos = -1;
				aBoolean1233 = true;
				inputTaken = true;
			}
			if(super.clickMode3 == 1) {
				if(super.saveClickX >= 5 && super.saveClickX <= 61 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 0;
					chatTypeView = 0;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 71 && super.saveClickX <= 127 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 1;
					chatTypeView = 5;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 137 && super.saveClickX <= 193 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 2;
					chatTypeView = 1;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 203 && super.saveClickX <= 259 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 3;
					chatTypeView = 2;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 269 && super.saveClickX <= 325 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 4;
					chatTypeView = 11;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 335 && super.saveClickX <= 391 && super.saveClickY >= 482 && super.saveClickY <= 505) {
					cButtonCPos = 5;
					chatTypeView = 3;
					aBoolean1233 = true;
					inputTaken = true;
				} else if(super.saveClickX >= 404 && super.saveClickX <= 515 && super.saveClickY >= 482 && super.saveClickY <= 505) {
						if(openInterfaceID == -1) {
							clearTopInterfaces();
							reportAbuseInput = "";
							canMute = false;
							for(int i = 0; i < RSInterface.interfaceCache.length; i++) {
								if(RSInterface.interfaceCache[i] == null || RSInterface.interfaceCache[i].contentType != 600)
									continue;
								reportAbuseInterfaceID = openInterfaceID = RSInterface.interfaceCache[i].parentID;
								break;
							}
						} else {
							pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
					}
				}
			}
		} else {
			if(super.clickMode3 == 1) {
				if(super.saveClickX >= 6 && super.saveClickX <= 106 && super.saveClickY >= 467 && super.saveClickY <= 499)
				{
					publicChatMode = (publicChatMode + 1) % 4;
					aBoolean1233 = true;
					inputTaken = true;
					stream.createFrame(95);
					stream.writeWordBigEndian(publicChatMode);
					stream.writeWordBigEndian(privateChatMode);
					stream.writeWordBigEndian(tradeMode);
				}
				if(super.saveClickX >= 135 && super.saveClickX <= 235 && super.saveClickY >= 467 && super.saveClickY <= 499)
				{
					privateChatMode = (privateChatMode + 1) % 3;
					aBoolean1233 = true;
					inputTaken = true;
					stream.createFrame(95);
					stream.writeWordBigEndian(publicChatMode);
					stream.writeWordBigEndian(privateChatMode);
					stream.writeWordBigEndian(tradeMode);
				}
				if(super.saveClickX >= 273 && super.saveClickX <= 373 && super.saveClickY >= 467 && super.saveClickY <= 499)
				{
					tradeMode = (tradeMode + 1) % 3;
					aBoolean1233 = true;
					inputTaken = true;
					stream.createFrame(95);
					stream.writeWordBigEndian(publicChatMode);
					stream.writeWordBigEndian(privateChatMode);
					stream.writeWordBigEndian(tradeMode);
				}
				if(super.saveClickX >= 404 && super.saveClickX <= 515 && super.saveClickY >= 482 && super.saveClickY <= 505) {
						if(openInterfaceID == -1) {
							clearTopInterfaces();
							reportAbuseInput = "";
							canMute = false;
							for(int i = 0; i < RSInterface.interfaceCache.length; i++) {
								if(RSInterface.interfaceCache[i] == null || RSInterface.interfaceCache[i].contentType != 600)
									continue;
								reportAbuseInterfaceID = openInterfaceID = RSInterface.interfaceCache[i].parentID;
								break;
							}
						} else {
							pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
					}
				}
			}
		}
	}

	private void method33(int i)
	{
		int j = Varp.cache[i].anInt709;
		if(j == 0)
			return;
		int k = variousSettings[i];
		if(j == 1)
		{
			if(k == 1)
				Texture.method372(0.90000000000000002D);
			if(k == 2)
				Texture.method372(0.80000000000000004D);
			if(k == 3)
				Texture.method372(0.69999999999999996D);
			if(k == 4)
				Texture.method372(0.59999999999999998D);
			ItemDef.mruNodes1.unlinkAll();
			welcomeScreenRaised = true;
		}
		if(j == 3)
		{
			boolean flag1 = musicEnabled;
			if(k == 0)
			{
				adjustVolume(musicEnabled, 0);
				musicEnabled = true;
			}
			if(k == 1)
			{
				adjustVolume(musicEnabled, -400);
				musicEnabled = true;
			}
			if(k == 2)
			{
				adjustVolume(musicEnabled, -800);
				musicEnabled = true;
			}
			if(k == 3)
			{
				adjustVolume(musicEnabled, -1200);
				musicEnabled = true;
			}
			if(k == 4)
				musicEnabled = false;
			if(musicEnabled != flag1 && !lowMem)
			{
				if(musicEnabled)
				{
					nextSong = currentSong;
					songChanging = true;
					onDemandFetcher.method558(2, nextSong);
				} else
				{
					stopMidi();
				}
				prevSong = 0;
			}
		}
		if(j == 4)
		{
			if(k == 0)
			{
				aBoolean848 = true;
				setWaveVolume(0);
			}
			if(k == 1)
			{
				aBoolean848 = true;
				setWaveVolume(-400);
			}
			if(k == 2)
			{
				aBoolean848 = true;
				setWaveVolume(-800);
			}
			if(k == 3)
			{
				aBoolean848 = true;
				setWaveVolume(-1200);
			}
			if(k == 4)
				aBoolean848 = false;
		}
		if(j == 5)
			anInt1253 = k;
		if(j == 6)
			anInt1249 = k;
		if(j == 8)
		{
			splitPrivateChat = k;
			inputTaken = true;
		}
		if(j == 9)
			anInt913 = k;
	}

/*	   void noSoakHit1(int i, int j, int k, int l, int i1, String s)
    {
        if(i > loopCycle)
        {
            k -= 30;
            int j1 = i - loopCycle;
            int k1 = (int)Math.round((double)j1 * 3.5D);
            int l1 = Math.round(j1 / 6);
            hitMark[5].drawTransparent(j - 8, (k - 12) + l1, k1);
            hitMark[5].drawTransparent(j - 8, (k - 12) + l1, k1);
            hitMark[5].drawTransparent(j - 8, (k - 12) + l1, k1);
            smallText.drawText(0, s, k + 4 + l1, j);
            smallText.drawText(0xffffff, s, k + 3 + l1, j - 1);
        }
    }

    void noSoakHit10(int i, int j, int k, int l, int i1, String s)
    {
        if(i > loopCycle)
        {
            k -= 30;
            int j1 = i - loopCycle;
            int k1 = (int)Math.round((double)j1 * 3.5D);
            int l1 = Math.round(j1 / 6);
            hitMark[l].drawTransparent(j - 12, (k - 12) + l1, k1);
            hitMark[l].drawTransparent(j - 12, (k - 12) + l1, k1);
            hitMark[l].drawTransparent(j - 12, (k - 12) + l1, k1);
            smallText.drawText(0, s, k + 4 + l1, j);
            smallText.drawText(0xffffff, s, k + 3 + l1, j - 1);
        }
    }

    void noSoakHit100(int i, int j, int k, int l, int i1, String s)
    {
        if(i > loopCycle)
        {
            k -= 30;
            int j1 = i - loopCycle;
            int k1 = (int)Math.round((double)j1 * 3.5D);
            int l1 = Math.round(j1 / 6);
            hitMark[l].drawTransparent(j - 20, (k - 13) + l1, k1);
            hitMark[l].drawTransparent(j - 20, (k - 13) + l1, k1);
            hitMark[l].drawTransparent(j - 20, (k - 13) + l1, k1);
            smallText.drawText(0, s, k + 4 + l1, j);
            smallText.drawText(0xffffff, s, k + 3 + l1, j - 1);
        }
    }

    void noSoakHitMax(int i, int j, int k, int l, String s)
    {
        if(i > loopCycle)
        {
            k -= 30;
            int i1 = i - loopCycle;
            int j1 = (int)Math.round((double)i1 * 3.5D);
            int k1 = Math.round(i1 / 6);
            hitMark[4].drawTransparent(j - 20, (k - 13) + k1, j1);
            hitMark[4].drawTransparent(j - 20, (k - 13) + k1, j1);
            hitMark[4].drawTransparent(j - 20, (k - 13) + k1, j1);
            chatTextDrawingArea.drawText(0xffffff, s, k + 3 + k1, j - 1);
        }
    }

    void soakHit1(int i, int j, int k, int l, int i1, String s, String s1)
    {
        if(i > loopCycle)
        {
            k -= 30;
            int j1 = i - loopCycle;
            int k1 = (int)Math.round((double)j1 * 3.5D);
            int l1 = Math.round(j1 / 6);
            hitMark[i1].drawTransparent(j - 29, (k - 13) + l1, k1);
            hitMark[i1].drawTransparent(j - 29, (k - 13) + l1, k1);
            hitMark[i1].drawTransparent(j - 29, (k - 13) + l1, k1);
            smallText.drawText(0, s, k + 4 + l1, j - 9);
            smallText.drawText(0xffffff, s, k + 3 + l1, j - 10);
            hitMark[6].drawTransparent(j + 24, (k - 11) + l1, k1);
            hitMark[6].drawTransparent(j + 24, (k - 11) + l1, k1);
            hitMark[6].drawTransparent(j + 24, (k - 11) + l1, k1);
            smallText.drawText(0, s1, k + 5 + l1, j + 32);
            smallText.drawText(0xffffff, s1, k + 4 + l1, j + 31);
        }
    }

    void soakHit(int i, int j, int k, int l, int i1, String s, String s1)
    {
        if(i > loopCycle)
        {
            k -= 30;
            int j1 = i - loopCycle;
            int k1 = (int)Math.round((double)j1 * 3.5D);
            int l1 = Math.round(j1 / 6);
            hitMark[i1].drawTransparent(j - 29, (k - 13) + l1, k1);
            hitMark[i1].drawTransparent(j - 29, (k - 13) + l1, k1);
            hitMark[i1].drawTransparent(j - 29, (k - 13) + l1, k1);
            smallText.drawText(0, s, k + 4 + l1, j - 9);
            smallText.drawText(0xffffff, s, k + 3 + l1, j - 10);
            hitMark[0].drawTransparent(j + 24, (k - 11) + l1, k1);
            hitMark[0].drawTransparent(j + 24, (k - 11) + l1, k1);
            hitMark[0].drawTransparent(j + 24, (k - 11) + l1, k1);
            smallText.drawText(0, s1, k + 5 + l1, j + 38);
            smallText.drawText(0xffffff, s1, k + 4 + l1, j + 37);
        }
    }

    void soakHitMax(int i, int j, int k, int l, String s, String s1)
    {
        if(i > loopCycle)
        {
            k -= 30;
            int i1 = i - loopCycle;
            int j1 = (int)Math.round((double)i1 * 3.5D);
            int k1 = Math.round(i1 / 6);
            hitMark[4].drawTransparent(j - 29, (k - 13) + k1, j1);
            hitMark[4].drawTransparent(j - 29, (k - 13) + k1, j1);
            hitMark[4].drawTransparent(j - 29, (k - 13) + k1, j1);
            chatTextDrawingArea.drawText(0xffffff, s, k + 4 + k1, j - 10);
            hitMark[0].drawTransparent(j + 24, (k - 11) + k1, j1);
            hitMark[0].drawTransparent(j + 24, (k - 11) + k1, j1);
            hitMark[0].drawTransparent(j + 24, (k - 11) + k1, j1);
            smallText.drawText(0, s1, k + 5 + k1, j + 38);
            smallText.drawText(0xffffff, s1, k + 4 + k1, j + 37);
        }
    }*/
	void block(int cycle, int x, int y) {
		if (cycle > loopCycle) {
			y -= 30;
			int difference = (cycle - loopCycle);
			int trans = (int) Math.round((double) difference * 3.5);
			int height = (int) Math.round(difference / 6);
			combatIcons[3].drawTransparent((x - 12), (y - 12 + height), trans);
		}
	}


	void hit10(int cycle, int x, int y, int type, int type2, String damage) {
		if (cycle > loopCycle) {
			y -= 30;
			int difference = (cycle - loopCycle);
			int trans = (int) Math.round((double) difference * 3.5);
			int height = (int) Math.round(difference / 6);
			combatIcons[type2].drawTransparent((x - 30), (y - 12 + height), trans);
			hitMark[type].drawTransparent((x - 12), (y - 12 + height), trans);
			smallText.drawText(0, damage, (y + 4 + height), x);
			smallText.drawText(0xffffff, damage, (y + 3 + height), (x - 1));
		}
	}

	void hit100(int cycle, int x, int y, int type, int type2, String damage) {
		if (cycle > loopCycle) {
			y -= 30;
			int difference = (cycle - loopCycle);
			int trans = (int) Math.round((double) difference * 3.5);
			int height = (int) Math.round(difference / 6);
			combatIcons[type2].drawTransparent((x - 35), (y - 11 + height), trans);
			hitMark[0].drawTransparent((x - 20), (y - 13 + height), trans);
			smallText.drawText(0, damage, (y + 4 + height), x);
			smallText.drawText(0xffffff, damage, (y + 3 + height), (x - 1));
		}
	}
	
	void hit400(int cycle, int x, int y, int type, int type2, String damage) {
		if (cycle > loopCycle) {
			y -= 30;
			int difference = (cycle - loopCycle);
			int trans = (int) Math.round((double) difference * 3.5);
			int height = (int) Math.round(difference / 6);
			combatIcons[type2].drawTransparent((x - 35), (y - 11 + height), trans);
			hitMark[2].drawTransparent((x - 20), (y - 13 + height), trans);
			smallText.drawText(0, damage, (y + 4 + height), x);
			smallText.drawText(0xffffff, damage, (y + 3 + height), (x - 1));
		}
	}
	
	private Sprite HPBarFull;
	private Sprite HPBarEmpty;
	
	private void updateEntities() { 

		try{
			int anInt974 = 0;
			for(int j = -1; j < playerCount + npcCount; j++) {
			Object obj;
			if(j == -1)
				obj = myPlayer;
			else
			if(j < playerCount)
				obj = playerArray[playerIndices[j]];
			else
				obj = npcArray[npcIndices[j - playerCount]];
			if(obj == null || !((Entity) (obj)).isVisible())
				continue;
			if(obj instanceof NPC) {
				EntityDef entityDef = ((NPC)obj).desc;
				
				if(namesToggle == true) {
					String s = entityDef.name;
					s = s + combatDiffColor(myPlayer.combatLevel, entityDef.combatLevel) + " (level-" + entityDef.combatLevel + ")";
						if(entityDef.combatLevel != 0) {
							npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height+15);
							smallText.method382(0xFFFF33, spriteDrawX, s, spriteDrawY-8, true);
						} else if(entityDef.combatLevel == 0) {
								npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height+15);
								smallText.method382(0xFFFF33, spriteDrawX, entityDef.name, spriteDrawY-8, true);
						}
				} else if(namesToggle == false) {
				
				}
				
				if(entityDef.childrenIDs != null)
					entityDef = entityDef.method161();
				if(entityDef == null)
					continue;
			}
			if(namesToggle == true) {
					if(j < playerCount) {
						int l = 45;
						Player player = (Player)obj;
						if(player.headIcon >= 0) {
							npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
							if(spriteDrawX > -1) {
								if (player.skullIcon < 2) {
									skullIcons[player.skullIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
									l += 25;//25
								}
								/*if (player.headIcon < 7) {
									headIcons[player.headIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
									l += 20;//20
								}*/
								if(player.headIcon < 18)  {
								headIcons[player.headIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
								l += 26;
								}
							}
						}
						if(j >= 0 && anInt855 == 10 && anInt933 == playerIndices[j]) {
							npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
							if(spriteDrawX > -1)
								headIconsHint[player.hintIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
								l += 30;
						}
						npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
						int col = 0x3399ff;
							newSmallFont.drawCenteredString(player.name, spriteDrawX, spriteDrawY-8, col, 100);

					} else {
						EntityDef entityDef_1 = ((NPC)obj).desc;
						if(entityDef_1.anInt75 >= 0 && entityDef_1.anInt75 < headIcons.length) {
							npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
							if(spriteDrawX > -1)
								headIcons[entityDef_1.anInt75].drawSprite(spriteDrawX - 12, spriteDrawY - 50);
						}
						if(anInt855 == 1 && anInt1222 == npcIndices[j - playerCount] && loopCycle % 20 < 10) {
							npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
							if(spriteDrawX > -1)
								headIconsHint[0].drawSprite(spriteDrawX - 12, spriteDrawY - 50);
						}
					}
				} else if(namesToggle == false) {
					if(j < playerCount) {
							int l = 30;
							Player player = (Player)obj;
							if(player.headIcon >= 0) {
								npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
								if(spriteDrawX > -1) {
									if (player.skullIcon < 2) {
										skullIcons[player.skullIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
										l += 25;
									}
									/*if (player.headIcon < 7) {
										headIcons[player.headIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
										l += 18;
									}*/
								if(player.headIcon < 18)  {
								headIcons[player.headIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
								l += 26;
								}
								}
							}
							if(j >= 0 && anInt855 == 10 && anInt933 == playerIndices[j]) {
								npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
								if(spriteDrawX > -1)
									headIconsHint[player.hintIcon].drawSprite(spriteDrawX - 12, spriteDrawY - l);
							}
							npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					
						} else {
							EntityDef entityDef_1 = ((NPC)obj).desc;
							if(entityDef_1.anInt75 >= 0 && entityDef_1.anInt75 < headIcons.length) {
								npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
								if(spriteDrawX > -1)
									headIcons[entityDef_1.anInt75].drawSprite(spriteDrawX - 12, spriteDrawY - 30);
							}
							if(anInt855 == 1 && anInt1222 == npcIndices[j - playerCount] && loopCycle % 20 < 10) {
								npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
								if(spriteDrawX > -1)
									headIconsHint[0].drawSprite(spriteDrawX - 12, spriteDrawY - 30);
							}
						}
					}
			if(((Entity) (obj)).textSpoken != null && (j >= playerCount || publicChatMode == 0 || publicChatMode == 3 || publicChatMode == 1 && isFriendOrSelf(((Player)obj).name)))
			{
				npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height);
				if(spriteDrawX > -1 && anInt974 < anInt975)
				{
					anIntArray979[anInt974] = chatTextDrawingArea.method384(((Entity) (obj)).textSpoken) / 2;
					anIntArray978[anInt974] = chatTextDrawingArea.anInt1497;
					anIntArray976[anInt974] = spriteDrawX;
					anIntArray977[anInt974] = spriteDrawY;
					anIntArray980[anInt974] = ((Entity) (obj)).anInt1513;
					anIntArray981[anInt974] = ((Entity) (obj)).anInt1531;
					anIntArray982[anInt974] = ((Entity) (obj)).textCycle;
					aStringArray983[anInt974++] = ((Entity) (obj)).textSpoken;
					if(anInt1249 == 0 && ((Entity) (obj)).anInt1531 >= 1 && ((Entity) (obj)).anInt1531 <= 3)
					{
						anIntArray978[anInt974] += 10;
						anIntArray977[anInt974] += 5;
					}
					if(anInt1249 == 0 && ((Entity) (obj)).anInt1531 == 4)
						anIntArray979[anInt974] = 60;
					if(anInt1249 == 0 && ((Entity) (obj)).anInt1531 == 5)
						anIntArray978[anInt974] += 5;
				}
			}
			if(((Entity) (obj)).loopCycleStatus > loopCycle)
			{
				try{
					npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height + 15);
					if(spriteDrawX > -1)
					{
						int i1 = (((Entity) (obj)).currentHealth * 30) / ((Entity) (obj)).maxHealth;
							if(i1 > 30) {
								i1 = 30;
							}
						int HpPercent = (((Entity) (obj)).currentHealth * 56) / ((Entity) (obj)).maxHealth;
							if (HpPercent > 56) {
								HpPercent = 56;
							}
						//if(namesToggle) {              
                        //       newSmallFont.drawCenteredString((new StringBuilder()).append(((Entity) (Entity) obj).currentHealth).append("/").append(((Entity) (Entity) obj).maxHealth).toString(), spriteDrawX, spriteDrawY - 19, 0x3399ff, 100);
                        //}//draws HP above head
						//HPBar crap
						if(!hitbarToggle){
							DrawingArea.method336(5, spriteDrawY - 3, spriteDrawX - 15, 65280, i1);
							DrawingArea.method336(5, spriteDrawY - 3, (spriteDrawX - 15) + i1, 0xff0000, 30 - i1);
						} else {
							HPBarEmpty.drawSprite(spriteDrawX - 28, spriteDrawY - 5);//3
							HPBarFull = new Sprite(sign.signlink.findcachedir() + "Sprites/Player/HP 0.PNG", HpPercent, 7);
							HPBarFull.drawSprite(spriteDrawX - 28, spriteDrawY - 5);
						}
					}
				}catch(Exception e){ }
				}
				applyTrans = 0;
				for (int j1 = 0; j1 < 4; j1++) {
					if (((Entity) (obj)).hitsLoopCycle[j1] > loopCycle) {
						npcScreenPos(((Entity) (obj)), ((Entity) (obj)).height / 2);
						applyTrans = 200;
						if (spriteDrawX > -1) {

							if (j1 > 0) {
								spriteDrawY += (19 * j1);
							}
							if (((Entity) (obj)).hitArray[j1] == 0 || ((Entity) (obj)).hitType[j1] == 3) {
								block(((Entity) (obj)).hitsLoopCycle[j1], spriteDrawX, spriteDrawY);
							} else {
								if (newHits) {
									if (((Entity) (obj)).hitArray[j1] > 399) {
										hit400(((Entity) (obj)).hitsLoopCycle[j1], spriteDrawX, spriteDrawY, ((Entity) (obj)).hitMarkTypes[j1], ((Entity) (obj)).hitType[j1], String.valueOf(((Entity) (obj)).hitArray[j1]));
									} else if (((Entity) (obj)).hitArray[j1] < 100) {
										hit10(((Entity) (obj)).hitsLoopCycle[j1], spriteDrawX, spriteDrawY, ((Entity) (obj)).hitMarkTypes[j1], ((Entity) (obj)).hitType[j1], String.valueOf(((Entity) (obj)).hitArray[j1]));
									} else {
										hit100(((Entity) (obj)).hitsLoopCycle[j1], spriteDrawX, spriteDrawY, ((Entity) (obj)).hitMarkTypes[j1], ((Entity) (obj)).hitType[j1], String.valueOf(((Entity) (obj)).hitArray[j1]));
									}
								} else {
									hitMarks[((Entity) (obj)).hitMarkTypes[j1]].drawTranSprite(spriteDrawX - 12, spriteDrawY - 12, 0);
									smallText.drawText(0, String.valueOf(((Entity) (obj)).hitArray[j1]), spriteDrawY + 4, spriteDrawX);
									smallText.drawText(0xffffff, String.valueOf(((Entity) (obj)).hitArray[j1]), spriteDrawY + 3, spriteDrawX - 1);
								}
							}
						}
					}
				}
			}
			for(int k = 0; k < anInt974; k++) {
				int k1 = anIntArray976[k];
				int l1 = anIntArray977[k];
				int j2 = anIntArray979[k];
				int k2 = anIntArray978[k];
				boolean flag = true;
				while(flag) 
				{
					flag = false;
					for(int l2 = 0; l2 < k; l2++)
						if(l1 + 2 > anIntArray977[l2] - anIntArray978[l2] && l1 - k2 < anIntArray977[l2] + 2 && k1 - j2 < anIntArray976[l2] + anIntArray979[l2] && k1 + j2 > anIntArray976[l2] - anIntArray979[l2] && anIntArray977[l2] - anIntArray978[l2] < l1)
						{
							l1 = anIntArray977[l2] - anIntArray978[l2];
							flag = true;
						}

				}
				spriteDrawX = anIntArray976[k];
				spriteDrawY = anIntArray977[k] = l1;
				String s = aStringArray983[k];
				if(anInt1249 == 0)
				{
					int i3 = 0xffff00;
					if(anIntArray980[k] < 6)
						i3 = anIntArray965[anIntArray980[k]];
					if(anIntArray980[k] == 6)
						i3 = anInt1265 % 20 >= 10 ? 0xffff00 : 0xff0000;
					if(anIntArray980[k] == 7)
						i3 = anInt1265 % 20 >= 10 ? 65535 : 255;
					if(anIntArray980[k] == 8)
						i3 = anInt1265 % 20 >= 10 ? 0x80ff80 : 45056;
					if(anIntArray980[k] == 9) {
						int j3 = 150 - anIntArray982[k];
						if(j3 < 50)
							i3 = 0xff0000 + 1280 * j3;
						else
						if(j3 < 100)
							i3 = 0xffff00 - 0x50000 * (j3 - 50);
						else
						if(j3 < 150)
							i3 = 65280 + 5 * (j3 - 100);
					}
					if(anIntArray980[k] == 10) {
						int k3 = 150 - anIntArray982[k];
						if(k3 < 50)
							i3 = 0xff0000 + 5 * k3;
						else
						if(k3 < 100)
							i3 = 0xff00ff - 0x50000 * (k3 - 50);
						else
						if(k3 < 150)
							i3 = (255 + 0x50000 * (k3 - 100)) - 5 * (k3 - 100);
					}
					if(anIntArray980[k] == 11) {
						int l3 = 150 - anIntArray982[k];
						if(l3 < 50)
							i3 = 0xffffff - 0x50005 * l3;
						else
						if(l3 < 100)
							i3 = 65280 + 0x50005 * (l3 - 50);
						else
						if(l3 < 150)
							i3 = 0xffffff - 0x50000 * (l3 - 100);
					}
					if(anIntArray981[k] == 0) {
						chatTextDrawingArea.drawText(0, s, spriteDrawY + 1, spriteDrawX);
						chatTextDrawingArea.drawText(i3, s, spriteDrawY, spriteDrawX);
					}
					if(anIntArray981[k] == 1) {
						chatTextDrawingArea.method386(0, s, spriteDrawX, anInt1265, spriteDrawY + 1);
						chatTextDrawingArea.method386(i3, s, spriteDrawX, anInt1265, spriteDrawY);
					}
					if(anIntArray981[k] == 2) {
						chatTextDrawingArea.method387(spriteDrawX, s, anInt1265, spriteDrawY + 1, 0);
						chatTextDrawingArea.method387(spriteDrawX, s, anInt1265, spriteDrawY, i3);
					}
					if(anIntArray981[k] == 3) {
						chatTextDrawingArea.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY + 1, spriteDrawX, 0);
						chatTextDrawingArea.method388(150 - anIntArray982[k], s, anInt1265, spriteDrawY, spriteDrawX, i3);
					}
					if(anIntArray981[k] == 4) {
						int i4 = chatTextDrawingArea.method384(s);
						int k4 = ((150 - anIntArray982[k]) * (i4 + 100)) / 150;
						DrawingArea.setDrawingArea(334, spriteDrawX - 50, spriteDrawX + 50, 0);
						chatTextDrawingArea.method385(0, s, spriteDrawY + 1, (spriteDrawX + 50) - k4);
						chatTextDrawingArea.method385(i3, s, spriteDrawY, (spriteDrawX + 50) - k4);
						DrawingArea.defaultDrawingAreaSize();
					}
					if(anIntArray981[k] == 5) {
						int j4 = 150 - anIntArray982[k];
						int l4 = 0;
						if(j4 < 25)
							l4 = j4 - 25;
						else
						if(j4 > 125)
							l4 = j4 - 125;
						DrawingArea.setDrawingArea(spriteDrawY + 5, 0, 512, spriteDrawY - chatTextDrawingArea.anInt1497 - 1);
						chatTextDrawingArea.drawText(0, s, spriteDrawY + 1 + l4, spriteDrawX);
						chatTextDrawingArea.drawText(i3, s, spriteDrawY + l4, spriteDrawX);
						DrawingArea.defaultDrawingAreaSize();
					}
				} else {
					chatTextDrawingArea.drawText(0, s, spriteDrawY + 1, spriteDrawX);
					chatTextDrawingArea.drawText(0xffff00, s, spriteDrawY, spriteDrawX);
				}
			}
		} catch(Exception e){ }
	}

	private void delFriend(long l)
	{
		try
		{
			if(l == 0L)
				return;
			for(int i = 0; i < friendsCount; i++)
			{
				if(friendsListAsLongs[i] != l)
					continue;
				friendsCount--;
				needDrawTabArea = true;
				for(int j = i; j < friendsCount; j++)
				{
					friendsList[j] = friendsList[j + 1];
					friendsNodeIDs[j] = friendsNodeIDs[j + 1];
					friendsListAsLongs[j] = friendsListAsLongs[j + 1];
				}

				stream.createFrame(215);
				stream.writeQWord(l);
				break;
			}
		}
		catch(RuntimeException runtimeexception)
		{
			signlink.reporterror("18622, " + false + ", " + l + ", " + runtimeexception.toString());
			throw new RuntimeException();
		}
	}
	
	public void drawSideIcons(){
		if(is480 || is508 || is525) {
			/* Top sideIcons */
			if(tabInterfaceIDs[0] != -1)//attack
				sideIcons[0].drawSprite(10+3, 4);
			if(tabInterfaceIDs[1] != -1)//stat
				sideIcons[1].drawSprite(43+3, 4);
			if(tabInterfaceIDs[2] != -1)//quest
				sideIcons[2].drawSprite(76+3, 3);
			if(tabInterfaceIDs[3] != -1)//inventory
				sideIcons[3].drawSprite(111+3, 5);
			if(tabInterfaceIDs[4] != -1)//equipment
				sideIcons[4].drawSprite(140+3, 1);
			if(tabInterfaceIDs[5] != -1)//prayer
				sideIcons[5].drawSprite(174+3, 1);
			if(tabInterfaceIDs[6] != -1)//magic
				sideIcons[6].drawSprite(208+3, 4);
			/* Bottom sideIcons */
			if(tabInterfaceIDs[7] != -1)//friend chat
				sideIcons[7].drawSprite(11+3, 303);
			if(tabInterfaceIDs[8] != -1)//friends
				sideIcons[8].drawSprite(46+3, 306);
			if(tabInterfaceIDs[9] != -1)//ignore
				sideIcons[9].drawSprite(79+3, 306);
			if(tabInterfaceIDs[10] != -1)//logout
				sideIcons[10].drawSprite(113+3, 302);
			if(tabInterfaceIDs[11] != -1)//options
				sideIcons[11].drawSprite(145+3, 304);
			if(tabInterfaceIDs[12] != -1)//emotes
				sideIcons[12].drawSprite(181+3, 302);
			if(tabInterfaceIDs[13] != -1)//music
				sideIcons[13].drawSprite(213+3, 303);
		} else if(is474){
			/** Top sideIcons */
			if(tabInterfaceIDs[0] != -1)///attack
				sideIcons[0].drawSprite(10+3, 4);
			if(tabInterfaceIDs[1] != -1)///stat
				sideIcons[1].drawSprite(43+3, 4);
			if(tabInterfaceIDs[2] != -1)///quest
				sideIcons[2].drawSprite(76+3, 3);
			if(tabInterfaceIDs[3] != -1)///inventory
				sideIcons[3].drawSprite(111+3, 5);
			if(tabInterfaceIDs[4] != -1)///equipment
				sideIcons[4].drawSprite(140+3, 1);
			if(tabInterfaceIDs[5] != -1)///prayer
				sideIcons[5].drawSprite(174+3, 1);
			if(tabInterfaceIDs[6] != -1)///magic
				sideIcons[6].drawSprite(208+3, 4);
			/** Bottom sideIcons */
			if(tabInterfaceIDs[7] != -1)///clan
				sideIcons[7].drawSprite(11+3, 303);
			if(tabInterfaceIDs[8] != -1)///friends
				sideIcons[8].drawSprite(46+3, 306);
			if(tabInterfaceIDs[9] != -1)///ignore
				sideIcons[9].drawSprite(79+3, 306);
			if(tabInterfaceIDs[10] != -1)///logout
				sideIcons[10].drawSprite(113+3, 302);
			if(tabInterfaceIDs[11] != -1)///options
				sideIcons[11].drawSprite(145+3, 304);
			if(tabInterfaceIDs[12] != -1)///emotes
				sideIcons[12].drawSprite(181+3, 302);
			if(tabInterfaceIDs[13] != -1)///music
				sideIcons[13].drawSprite(213+3, 303);
		} else if(is562){
		   /* Top sideIcons */
        if(tabInterfaceIDs[0] != -1)
            newSideIcons[0].drawSprite(8+3, 8);
        if(tabInterfaceIDs[1] != -1)
            newSideIcons[1].drawSprite(37+3, 8);
        if(tabInterfaceIDs[2] != -1)
            newSideIcons[2].drawSprite(67+3, 8);
        if(tabInterfaceIDs[14] != -1)
            newSideIcons[3].drawSprite(97+3, 8);
        if(tabInterfaceIDs[3] != -1)
            newSideIcons[4].drawSprite(127+3, 8);
        if(tabInterfaceIDs[4] != -1)
            newSideIcons[5].drawSprite(159+3, 8);
        if(tabInterfaceIDs[5] != -1)
            newSideIcons[6].drawSprite(187+3, 8);
        if(tabInterfaceIDs[6] != -1)
            newSideIcons[7].drawSprite(217+3, 8);
        /* Bottom sideIcons */
		if(tabInterfaceIDs[10] != -1)
			newSideIcons[15].drawSprite(8+3, 306);
        if(tabInterfaceIDs[9] != -1)
            newSideIcons[8].drawSprite(38+3, 306);
        if(tabInterfaceIDs[8] != -1)
            newSideIcons[9].drawSprite(70+3, 306);
        if(tabInterfaceIDs[7] != -1)
            newSideIcons[13].drawSprite(97+3, 306);
        if(tabInterfaceIDs[11] != -1)
            newSideIcons[10].drawSprite(127+3, 306);
        if(tabInterfaceIDs[12] != -1)
            newSideIcons[11].drawSprite(157+3, 306);
        if(tabInterfaceIDs[13] != -1)
            newSideIcons[12].drawSprite(187+3, 306);
        if(tabInterfaceIDs[15] != -1)
            newSideIcons[14].drawSprite(216+3, 307);
		} else {
		/** Top sideIcons */
			if(tabInterfaceIDs[0] != -1)///attack
				sIcons459[0].drawSprite(32, 11);
			if(tabInterfaceIDs[1] != -1)///stat
				sIcons459[1].drawSprite(57, 9);
			if(tabInterfaceIDs[2] != -1)///quest
				sIcons459[2].drawSprite(86, 9);
			if(tabInterfaceIDs[3] != -1)///inventory
				sIcons459[3].drawSprite(116, 4);
			if(tabInterfaceIDs[4] != -1)///equipment
				sIcons459[4].drawSprite(155, 5);
			if(tabInterfaceIDs[5] != -1)///prayer
				sIcons459[5].drawSprite(183, 5);
			if(tabInterfaceIDs[6] != -1)///magic
				sIcons459[6].drawSprite(212, 10);
			/** Bottom sideIcons */
			if(tabInterfaceIDs[7] != -1)///clan
				sideIcons[7].drawSprite(31, 300);
			if(tabInterfaceIDs[8] != -1)///friends
				sIcons459[7].drawSprite(57, 306);
			if(tabInterfaceIDs[9] != -1)///ignore
				sIcons459[8].drawSprite(85, 308);
			if(tabInterfaceIDs[10] != -1)///logout
				sIcons459[9].drawSprite(121, 302);
			if(tabInterfaceIDs[11] != -1)///options
				sIcons459[10].drawSprite(157, 305);
			if(tabInterfaceIDs[12] != -1)///emotes
				sIcons459[11].drawSprite(187, 302);
			if(tabInterfaceIDs[13] != -1)///music
				sIcons459[12].drawSprite(211, 302);
		}
	}
	public void drawRedStones() { 
		if(is474 || is480 || is508 || is525) {
			if(tabInterfaceIDs[tabID] != -1) {
				switch(tabID) {
					case 0:
						redStones[0].drawSprite(3+3, 0);
						break;
					case 1:
						redStones[4].drawSprite(41+3, 0);
						break;
					case 2:
						redStones[4].drawSprite(74+3, 0);
						break;
					case 3:
						redStones[4].drawSprite(107+3, 0);
						break;
					case 4:
						redStones[4].drawSprite(140+3, 0);
						break;
					case 5:
						redStones[4].drawSprite(173+3, 0);
						break;
					case 6:
						redStones[1].drawSprite(206+3, 0);
						break;
					case 7:
						redStones[2].drawSprite(3+3, 298);
						break;
					case 8:
						redStones[4].drawSprite(41+3, 298);
						break;
					case 9:
						redStones[4].drawSprite(74+3, 298);
						break;
					case 10:
						redStones[4].drawSprite(107+3, 298);
						break;
					case 11:
						redStones[4].drawSprite(140+3, 298);
						break;
					case 12:
						redStones[4].drawSprite(173+3, 298);
						break;
					case 13:
						redStones[3].drawSprite(206+3, 298);
						break;
				}
			}
		} else if(is562){
			 drawTabHover();
			if(tabInterfaceIDs[tabID] != -1)
			{
            if(tabID == 0)
                tabClicked.drawSprite(2+3, 0);
            if(tabID == 1)
                tabClicked.drawSprite(32+3, 0);
            if(tabID == 2)
                tabClicked.drawSprite(62+3, 0);
            if(tabID == 14)
                tabClicked.drawSprite(92+3, 0);
            if(tabID == 3)
                tabClicked.drawSprite(122+3, 0);
            if(tabID == 4)
                tabClicked.drawSprite(152+3, 0);
            if(tabID == 5)
                tabClicked.drawSprite(182+3, 0);
            if(tabID == 6)
                tabClicked.drawSprite(212+3, 0);
            if(tabID == 16)
                tabClicked.drawSprite(2+3, 298);
            if(tabID == 8)
                tabClicked.drawSprite(32+3, 298);
            if(tabID == 9)
                tabClicked.drawSprite(62+3, 298);
            if(tabID == 7)
                tabClicked.drawSprite(92+3, 298);
            if(tabID == 11)
                tabClicked.drawSprite(122+3, 298);
            if(tabID == 12)
                tabClicked.drawSprite(152+3, 298);
            if(tabID == 13)
                tabClicked.drawSprite(182+3, 298);
            if(tabID == 15)
                tabClicked.drawSprite(212+3, 298);
        }
		} else {
			if(tabInterfaceIDs[tabID] != -1) {
				switch(tabID) {
					case 0:
						redStones[5].drawSprite(16+7, 1);
						break;
					case 1:
						redStones[13].drawSprite(48+7, 1);
						break;
					case 2:
						redStones[13].drawSprite(75+7, 1);
						break;
					case 3:
						redStones[7].drawSprite(103+7, 0);
						break;
					case 4:
						redStones[14].drawSprite(146+7, 1);
						break;
					case 5:
						redStones[14].drawSprite(174+7, 1);
						break;
					case 6:
						redStones[6].drawSprite(202+7, 2);
						break;
						
					case 7:
						redStones[12].drawSprite(22, 296);
						break;
					case 8:
						redStones[9].drawSprite(54, 297);
						break;
					case 9:
						redStones[9].drawSprite(82, 297);
						break;
					case 10:
						redStones[10].drawSprite(110, 299);
						break;
					case 11:
						redStones[8].drawSprite(153, 297);
						break;
					case 12:
						redStones[8].drawSprite(181, 297);
						break;
					case 13:
						redStones[11].drawSprite(209, 297);
						break;
				}
			}
		}
	}
	
	private void drawTabArea() { 
		inventoryBackImage.initDrawingArea();
		Texture.anIntArray1472 = anIntArray1181;
		tabArea[getSpriteID()].drawSprite(0, 0);
		if(spriteChanged)
			needDrawTabArea = true;
			tabAreaAltered = true;
		if(invOverlayInterfaceID == -1) {
			drawRedStones();
			drawSideIcons();
		}
		if(is474 || is480 || is508 || is525 || is562) {
			if(invOverlayInterfaceID != -1)
				drawInterface(0, 32, RSInterface.interfaceCache[invOverlayInterfaceID], 37);
			else if(tabInterfaceIDs[tabID] != -1)
				drawInterface(0, 32, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], 37);
		} else if(is562){
		 if(tabHPos == 0 && tabInterfaceIDs[0] != -1)
            tabHover.drawSprite(3, 0);
        else if(tabHPos == 1 && tabInterfaceIDs[1] != -1)
            tabHover.drawSprite(33, 0);
        else if(tabHPos == 2 && tabInterfaceIDs[2] != -1)
            tabHover.drawSprite(63, 0);
        else if(tabHPos == 3 && tabInterfaceIDs[14] != -1)
            tabHover.drawSprite(93, 0);
        else if(tabHPos == 4 && tabInterfaceIDs[3] != -1)
            tabHover.drawSprite(123, 0);
        else if(tabHPos == 5 && tabInterfaceIDs[4] != -1)
            tabHover.drawSprite(153, 0);
        else if(tabHPos == 6 && tabInterfaceIDs[5] != -1)
            tabHover.drawSprite(183, 0);
        else if(tabHPos == 7 && tabInterfaceIDs[6] != -1)
            tabHover.drawSprite(213, 0);
        else if(tabHPos == 15 && tabInterfaceIDs[16] != -1)
            tabHover.drawSprite(3, 298);
        else if(tabHPos == 8 && tabInterfaceIDs[9] != -1)
            tabHover.drawSprite(33, 298);
        else if(tabHPos == 9 && tabInterfaceIDs[8] != -1)
            tabHover.drawSprite(63, 298);
        else if(tabHPos == 10 && tabInterfaceIDs[7] != -1)
            tabHover.drawSprite(93, 298);
        else if(tabHPos == 11 && tabInterfaceIDs[11] != -1)
            tabHover.drawSprite(123, 298);
        else if(tabHPos == 12 && tabInterfaceIDs[12] != -1)
            tabHover.drawSprite(153, 298);
        else if(tabHPos == 13 && tabInterfaceIDs[13] != -1)
            tabHover.drawSprite(183,298);
        else if(tabHPos == 14 && tabInterfaceIDs[15] != -1)
            tabHover.drawSprite(213, 298);
		} else {
			if(invOverlayInterfaceID != -1)
				drawInterface(0, 38, RSInterface.interfaceCache[invOverlayInterfaceID], 37);
			else if(tabInterfaceIDs[tabID] != -1)
				drawInterface(0, 38, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], 37);
		}
		if(menuOpen)
			drawMenu(516, 168);
		inventoryBackImage.drawGraphics(168, super.graphics, 516);//519
		inGameScreen.initDrawingArea();
		Texture.anIntArray1472 = anIntArray1182;
	}

	private void method37(int j) {
		if(!lowMem) {
			if(Texture.anIntArray1480[17] >= j) {
		
				Background background = Texture.aBackgroundArray1474s[17];
				int k = background.anInt1452 * background.anInt1453 - 1;
				//fire cape apparently?
				int j1 = background.anInt1452 * anInt945 * 2;
				byte abyte0[] = background.aByteArray1450;
				byte abyte3[] = aByteArray912;
				for(int i2 = 0; i2 <= k; i2++)
					abyte3[i2] = abyte0[i2 - j1 & k];

				background.aByteArray1450 = abyte3;
				aByteArray912 = abyte0;
				Texture.method370(17);
				anInt854++;
				if(anInt854 > 1235) {
					anInt854 = 0;
					stream.createFrame(226);
					stream.writeWordBigEndian(0);
					int l2 = stream.currentOffset;
					stream.writeWord(58722);
					stream.writeWordBigEndian(240);
					stream.writeWord((int)(Math.random() * 65536D));
					stream.writeWordBigEndian((int)(Math.random() * 256D));
					if((int)(Math.random() * 2D) == 0)
						stream.writeWord(51825);
					stream.writeWordBigEndian((int)(Math.random() * 256D));
					stream.writeWord((int)(Math.random() * 65536D));
					stream.writeWord(7130);
					stream.writeWord((int)(Math.random() * 65536D));
					stream.writeWord(61657);
					stream.writeBytes(stream.currentOffset - l2);
				}
			}
			if(Texture.anIntArray1480[24] >= j) {
				Background background_1 = Texture.aBackgroundArray1474s[24];
				int l = background_1.anInt1452 * background_1.anInt1453 - 1;
				int k1 = background_1.anInt1452 * anInt945 * 2;
				byte abyte1[] = background_1.aByteArray1450;
				byte abyte4[] = aByteArray912;
				for(int j2 = 0; j2 <= l; j2++)
					abyte4[j2] = abyte1[j2 - k1 & l];

				background_1.aByteArray1450 = abyte4;
				aByteArray912 = abyte1;
				Texture.method370(24);
			}
			if(Texture.anIntArray1480[34] >= j) {
				Background background_2 = Texture.aBackgroundArray1474s[34];
				int i1 = background_2.anInt1452 * background_2.anInt1453 - 1;
				int l1 = background_2.anInt1452 * anInt945 * 2;
				byte abyte2[] = background_2.aByteArray1450;
				byte abyte5[] = aByteArray912;
				for(int k2 = 0; k2 <= i1; k2++)
					abyte5[k2] = abyte2[k2 - l1 & i1];

				background_2.aByteArray1450 = abyte5;
				aByteArray912 = abyte2;
				Texture.method370(34);
			}
			if(Texture.anIntArray1480[40] >= j)
            {
				Background background_2 = Texture.aBackgroundArray1474s[40];
				int i1 = background_2.anInt1452 * background_2.anInt1453 - 1;
				int l1 = background_2.anInt1452 * anInt945 * 2;
				byte abyte2[] = background_2.aByteArray1450;
				byte abyte5[] = aByteArray912;
				for(int k2 = 0; k2 <= i1; k2++)
					abyte5[k2] = abyte2[k2 - l1 & i1];

				background_2.aByteArray1450 = abyte5;
				aByteArray912 = abyte2;
				Texture.method370(40);
		
            }
		}
	}

	private void method38() { 
		for(int i = -1; i < playerCount; i++) {
			int j;
			if(i == -1)
				j = myPlayerIndex;
			else
				j = playerIndices[i];
			Player player = playerArray[j];
			if(player != null && player.textCycle > 0) {
				player.textCycle--;
				if(player.textCycle == 0)
					player.textSpoken = null;
			}
		}
		for(int k = 0; k < npcCount; k++) {
			int l = npcIndices[k];
			NPC npc = npcArray[l];
			if(npc != null && npc.textCycle > 0) {
				npc.textCycle--;
				if(npc.textCycle == 0)
					npc.textSpoken = null;
			}
		}
	}

	private void calcCameraPos() { 
		
		int i = anInt1098 * 128 + 64;
		int j = anInt1099 * 128 + 64;
		int k = method42(plane, j, i) - anInt1100;
		if(xCameraPos < i) {
			xCameraPos += anInt1101 + ((i - xCameraPos) * anInt1102) / 1000;
			if(xCameraPos > i)
				xCameraPos = i;
		}
		if(xCameraPos > i) {
			xCameraPos -= anInt1101 + ((xCameraPos - i) * anInt1102) / 1000;
			if(xCameraPos < i)
				xCameraPos = i;
		}
		if(zCameraPos < k) {
			zCameraPos += anInt1101 + ((k - zCameraPos) * anInt1102) / 1000;
			if(zCameraPos > k)
				zCameraPos = k;
		}
		if(zCameraPos > k) {
			zCameraPos -= anInt1101 + ((zCameraPos - k) * anInt1102) / 1000;
			if(zCameraPos < k)
				zCameraPos = k;
		}
		if(yCameraPos < j) {
			yCameraPos += anInt1101 + ((j - yCameraPos) * anInt1102) / 1000;
			if(yCameraPos > j)
				yCameraPos = j;
		}
		if(yCameraPos > j) {
			yCameraPos -= anInt1101 + ((yCameraPos - j) * anInt1102) / 1000;
			if(yCameraPos < j)
				yCameraPos = j;
		}
		i = anInt995 * 128 + 64;
		j = anInt996 * 128 + 64;
		k = method42(plane, j, i) - anInt997;
		int l = i - xCameraPos;
		int i1 = k - zCameraPos;
		int j1 = j - yCameraPos;
		int k1 = (int)Math.sqrt(l * l + j1 * j1);
		int l1 = (int)(Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
		int i2 = (int)(Math.atan2(l, j1) * -325.94900000000001D) & 0x7ff;
		if(l1 < 128)
			l1 = 128;
		if(l1 > 383)
			l1 = 383;
		if(yCameraCurve < l1) {
			yCameraCurve += anInt998 + ((l1 - yCameraCurve) * anInt999) / 1000;
			if(yCameraCurve > l1)
				yCameraCurve = l1;
		}
		if(yCameraCurve > l1) {
			yCameraCurve -= anInt998 + ((yCameraCurve - l1) * anInt999) / 1000;
			if(yCameraCurve < l1)
				yCameraCurve = l1;
		}
		int j2 = i2 - xCameraCurve;
		if(j2 > 1024)
			j2 -= 2048;
		if(j2 < -1024)
			j2 += 2048;
		if(j2 > 0) {
			xCameraCurve += anInt998 + (j2 * anInt999) / 1000;
			xCameraCurve &= 0x7ff;
		}
		if(j2 < 0) {
			xCameraCurve -= anInt998 + (-j2 * anInt999) / 1000;
			xCameraCurve &= 0x7ff;
		}
		int k2 = i2 - xCameraCurve;
		if(k2 > 1024)
			k2 -= 2048;
		if(k2 < -1024)
			k2 += 2048;
		if(k2 < 0 && j2 > 0 || k2 > 0 && j2 < 0)
			xCameraCurve = i2;
	}

	private void drawMenu(int xOffSet, int yOffSet) {
		if(menuToggle == false) {
			int xPos = menuOffsetX - (xOffSet - 4);
			int yPos = (-yOffSet + 4) + menuOffsetY;
			int menuW = menuWidth;
			int menuH = menuHeight + 1;
			int color = 0x5d5447;
			needDrawTabArea = true;
			inputTaken = true;
			tabAreaAltered = true;
			//DrawingArea.drawPixels(height, yPos, xPos, color, width);
			//DrawingArea.fillPixels(xPos, width, height, color, yPos);
			DrawingArea.drawPixels(menuH, yPos, xPos, color, menuW);
			DrawingArea.drawPixels(16, yPos + 1, xPos + 1, 0, menuW - 2);
			DrawingArea.fillPixels(xPos + 1, menuW - 2, menuH - 19, 0, yPos + 18);
			chatTextDrawingArea.method385(color, "Choose Option", yPos + 14, xPos + 3);
			int mouseX = super.mouseX - (xOffSet);
			int mouseY = (-yOffSet) + super.mouseY;
			for(int l1 = 0; l1 < menuActionRow; l1++) {
				int textY = yPos + 31 + (menuActionRow - 1 - l1) * 15;
				int disColor = 0xffffff;
				if(mouseX > xPos && mouseX < xPos + menuW && mouseY > textY - 13 && mouseY < textY + 3)
					disColor = 0xffff00;
				chatTextDrawingArea.method389(true, xPos + 3, disColor, menuActionName[l1], textY);
			}
		} else if(menuToggle == true) {
			int xPos = menuOffsetX - (xOffSet - 4);
			int yPos = (-yOffSet + 4) + menuOffsetY;
			int menuW = menuWidth;
			int menuH = menuHeight + 1;
			needDrawTabArea = true;
			inputTaken = true;
			tabAreaAltered = true;
			//DrawingArea.drawPixels(height, yPos, xPos, color, width);
			//DrawingArea.fillPixels(xPos, width, height, color, yPos);
			DrawingArea.drawPixels(menuH - 4, yPos + 2, xPos, 0x706a5e, menuW);
			DrawingArea.drawPixels(menuH - 2, yPos + 1, xPos + 1, 0x706a5e, menuW - 2);
			DrawingArea.drawPixels(menuH, yPos, xPos + 2, 0x706a5e, menuW - 4);
			DrawingArea.drawPixels(menuH - 2, yPos + 1, xPos + 3, 0x2d2822, menuW - 6);
			DrawingArea.drawPixels(menuH - 4, yPos + 2, xPos + 2, 0x2d2822, menuW - 4);
			DrawingArea.drawPixels(menuH - 6, yPos + 3, xPos + 1, 0x2d2822, menuW - 2);
			DrawingArea.drawPixels(menuH - 22, yPos + 19, xPos + 2, 0x524a3d, menuW - 4);
			DrawingArea.drawPixels(menuH - 22, yPos + 20, xPos + 3, 0x524a3d, menuW - 6);
			DrawingArea.drawPixels(menuH - 23, yPos + 20, xPos + 3, 0x2b271c, menuW - 6);
			DrawingArea.fillPixels(xPos + 3, menuW - 6, 1, 0x2a291b, yPos + 2);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x2a261b, yPos + 3);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x252116, yPos + 4);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x211e15, yPos + 5);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x1e1b12, yPos + 6);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x1a170e, yPos + 7);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 2, 0x15120b, yPos + 8);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x100d08, yPos + 10);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 11);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x080703, yPos + 12);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 13);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x070802, yPos + 14);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 15);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x070802, yPos + 16);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x090a04, yPos + 17);
			DrawingArea.fillPixels(xPos + 2, menuW - 4, 1, 0x2a291b, yPos + 18);
			DrawingArea.fillPixels(xPos + 3, menuW - 6, 1, 0x564943, yPos + 19);
			chatTextDrawingArea.method385(0xc6b895, "Choose Option", yPos + 14, xPos + 3);
			int j1 = super.mouseX;
			int k1 = super.mouseY;
			int mouseX = super.mouseX - (xOffSet);
			int mouseY = (-yOffSet) + super.mouseY;
			for(int l1 = 0; l1 < menuActionRow; l1++) {
				int textY = yPos + 31 + (menuActionRow - 1 - l1) * 15;
				int disColor = 0xc6b895;
				if(mouseX > xPos && mouseX < xPos + menuW && mouseY > textY - 13 && mouseY < textY + 3) {
					DrawingArea.drawPixels(15, textY - 11, xPos + 3, 0x6f695d, menuWidth - 6);
					disColor = 0xeee5c6;
				}
				chatTextDrawingArea.method389(true, xPos + 3, disColor, menuActionName[l1], textY);
			}
		}
	}

	private void addFriend(long l) {
		try {
			if(l == 0L)
				return;
			if(friendsCount >= 100 && anInt1046 != 1) {
				pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
				return;
			}
			if(friendsCount >= 200) {
				pushMessage("Your friendlist is full. Max of 100 for free users, and 200 for members", 0, "");
				return;
			}
			String s = TextClass.fixName(TextClass.nameForLong(l));
			for(int i = 0; i < friendsCount; i++)
				if(friendsListAsLongs[i] == l) {
					pushMessage(s + " is already on your friend list", 0, "");
					return;
				}
			for(int j = 0; j < ignoreCount; j++)
				if(ignoreListAsLongs[j] == l) {
					pushMessage("Please remove " + s + " from your ignore list first", 0, "");
					return;
				}

			if(s.equals(myPlayer.name)) {
				return;
			} else {
				friendsList[friendsCount] = s;
				friendsListAsLongs[friendsCount] = l;
				friendsNodeIDs[friendsCount] = 0;
				friendsCount++;
				needDrawTabArea = true;
				stream.createFrame(188);
				stream.writeQWord(l);
				return;
			}
		} catch(RuntimeException runtimeexception) {
			signlink.reporterror("15283, " + (byte)68 + ", " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}

	private int method42(int i, int j, int k) {
		int l = k >> 7;
		int i1 = j >> 7;
		if(l < 0 || i1 < 0 || l > 103 || i1 > 103)
			return 0;
		int j1 = i;
		if(j1 < 3 && (byteGroundArray[1][l][i1] & 2) == 2)
			j1++;
		int k1 = k & 0x7f;
		int l1 = j & 0x7f;
		int i2 = intGroundArray[j1][l][i1] * (128 - k1) + intGroundArray[j1][l + 1][i1] * k1 >> 7;
		int j2 = intGroundArray[j1][l][i1 + 1] * (128 - k1) + intGroundArray[j1][l + 1][i1 + 1] * k1 >> 7;
		return i2 * (128 - l1) + j2 * l1 >> 7;
	}

	private static String intToKOrMil(int j) {
		if(j < 0x186a0)
			return String.valueOf(j);
		if(j < 0x989680)
			return j / 1000 + "K";
		else
			return j / 0xf4240 + "M";
	}
	public int canWalkDelay = 0;
     public int getDis(int coordX1, int coordY1, int coordX2, int coordY2)
    {
        int deltaX = coordX2 - coordX1;
        int deltaY = coordY2 - coordY1;
        return ((int)Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
    }
	 public int random(int range)
    {
        return (int)(Math.random() * range);
    }
   public boolean withinDistance(int x1, int y1, int x2, int y2, int dis)
    {
        for (int i = 0; i <= dis; i++)
        {
			try{
            if ((x1 + i) == x2 && ((y1 + i) == y2 || (y1 - i) == y2 || y1 == y2))
                return true;
            else
            if ((x1 - i) == x2 && ((x1 + i) == y2 || (y1 - i) == y2 || y1 == y2))
                return true;
            else
            if (x1 == x2 && ((x1 + i) == y2 || (y1 - i) == y2 || y1 == y2))
                return true;
								} catch(Exception ex){
		System.out.println("Exception in following, method : WithingDistance");
		}
        }
        return false;
    }

	private void resetLogout() { 
		try {
			if(socketStream != null)
				socketStream.close();
		}
		catch(Exception _ex) { }
		socketStream = null;
		alertHandler.alert = null;
			loggedIn = false;
			runClicked = true;
			musicOrb = false;
			restOrb = false;
			followPlayer = 0;
followNPC = 0;
followDistance = 1;
			prayClicked = false;
			xpClicked = false;
			drawXpBar = false;
		loginScreenState = 0;
 	    //myUsername = "";
 	    //myPassword = "";
		unlinkMRUNodes();
		worldController.initToNull();
		for(int i = 0; i < 4; i++)
			aClass11Array1230[i].method210();
		System.gc();
		stopMidi();
		currentSong = -1;
		nextSong = -1;
		prevSong = 0;
	}

	private void method45() { 
		 
		
		aBoolean1031 = true;
		for(int j = 0; j < 7; j++) {
			anIntArray1065[j] = -1;
			for(int k = 0; k < IdentityKit.length; k++) {
				if(IdentityKit.cache[k].aBoolean662 || IdentityKit.cache[k].anInt657 != j + (aBoolean1047 ? 0 : 7))
					continue;
				anIntArray1065[j] = k;
				break;
			}
		}
	}

	private void method46(int i, Stream stream) {
		while(stream.bitPosition + 21 < i * 8) {
			int k = stream.readBits(14);
			if(k == 16383)
				break;
			if(npcArray[k] == null)
				npcArray[k] = new NPC();
			NPC npc = npcArray[k];
			npcIndices[npcCount++] = k;
			npc.anInt1537 = loopCycle;
			int l = stream.readBits(5);
			if(l > 15)
				l -= 32;
			int i1 = stream.readBits(5);
			if(i1 > 15)
				i1 -= 32;
			int j1 = stream.readBits(1);
			npc.desc = EntityDef.forID(stream.readBits(14));//NPC FIX
			int k1 = stream.readBits(1);
			if(k1 == 1)
				anIntArray894[anInt893++] = k;
			npc.anInt1540 = npc.desc.aByte68;
			npc.anInt1504 = npc.desc.anInt79;
			npc.anInt1554 = npc.desc.anInt67;
			npc.anInt1555 = npc.desc.anInt58;
			npc.anInt1556 = npc.desc.anInt83;
			npc.anInt1557 = npc.desc.anInt55;
			npc.anInt1511 = npc.desc.anInt77;
			npc.setPos(myPlayer.smallX[0] + i1, myPlayer.smallY[0] + l, j1 == 1);
		}
		stream.finishBitAccess();
	}

	public void processGameLoop() { 
		 
		
		if(rsAlreadyLoaded || loadingError || genericLoadingError)
			return;
		loopCycle++;
		if(!loggedIn)
			processLoginScreenInput();
		else
			mainGameProcessor();
		processOnDemandQueue();
	}

	private void method47(boolean flag) {
		if(myPlayer.x >> 7 == destX && myPlayer.y >> 7 == destY)
			destX = 0;
		int j = playerCount;
		if(flag)
			j = 1;
		for(int l = 0; l < j; l++) {
			Player player;
			int i1;
			if(flag) {
				player = myPlayer;
				i1 = myPlayerIndex << 14;
			} else {
				player = playerArray[playerIndices[l]];
				i1 = playerIndices[l] << 14;
			}
			if(player == null || !player.isVisible())
				continue;
			player.aBoolean1699 = (lowMem && playerCount > 50 || playerCount > 200) && !flag && player.anInt1517 == player.anInt1511;
			int j1 = player.x >> 7;
			int k1 = player.y >> 7;
			if(j1 < 0 || j1 >= 104 || k1 < 0 || k1 >= 104)
				continue;
			if(player.aModel_1714 != null && loopCycle >= player.anInt1707 && loopCycle < player.anInt1708) {
				player.aBoolean1699 = false;
				player.anInt1709 = method42(plane, player.y, player.x);
				worldController.method286(plane, player.y, player, player.anInt1552, player.anInt1722, player.x, player.anInt1709, player.anInt1719, player.anInt1721, i1, player.anInt1720);
				continue;
			}
			if((player.x & 0x7f) == 64 && (player.y & 0x7f) == 64) {
				if(anIntArrayArray929[j1][k1] == anInt1265)
					continue;
				anIntArrayArray929[j1][k1] = anInt1265;
			}
			player.anInt1709 = method42(plane, player.y, player.x);
			worldController.method285(plane, player.anInt1552, player.anInt1709, i1, player.y, 60, player.x, player, player.aBoolean1541);
		}
	}

	private boolean promptUserForInput(RSInterface class9) {
		int j = class9.contentType;
		if(anInt900 == 2) {
			if(j == 201) {
				inputTaken = true;
				inputDialogState = 0;
				messagePromptRaised = true;
				promptInput = "";
				friendsListAction = 1;
				aString1121 = "Enter name of friend to add to list";
			}
			if(j == 202) {
				inputTaken = true;
				inputDialogState = 0;
				messagePromptRaised = true;
				promptInput = "";
				friendsListAction = 2;
				aString1121 = "Enter name of friend to delete from list";
			}
		}
		if(j == 205) {
			anInt1011 = 250;
			return true;
		}
		if(j == 501) {
			inputTaken = true;
			inputDialogState = 0;
			messagePromptRaised = true;
			promptInput = "";
			friendsListAction = 4;
			aString1121 = "Enter name of player to add to list";
		}
		if(j == 502) {
			inputTaken = true;
			inputDialogState = 0;
			messagePromptRaised = true;
			promptInput = "";
			friendsListAction = 5;
			aString1121 = "Enter name of player to delete from list";
		}
		if(j == 550) {
			inputTaken = true;
			inputDialogState = 0;
			messagePromptRaised = true;
			promptInput = "";
			friendsListAction = 6;
			aString1121 = "Enter the name of the chat you wish to join";
		}
		if(j >= 300 && j <= 313) {
			int k = (j - 300) / 2;
			int j1 = j & 1;
			int i2 = anIntArray1065[k];
			if(i2 != -1) {
				do {
					if(j1 == 0 && --i2 < 0)
						i2 = IdentityKit.length - 1;
					if(j1 == 1 && ++i2 >= IdentityKit.length)
						i2 = 0;
				} while(IdentityKit.cache[i2].aBoolean662 || IdentityKit.cache[i2].anInt657 != k + (aBoolean1047 ? 0 : 7));
				anIntArray1065[k] = i2;
				aBoolean1031 = true;
			}
		}
		if(j >= 314 && j <= 323) {
			int l = (j - 314) / 2;
			int k1 = j & 1;
			int j2 = anIntArray990[l];
			if(k1 == 0 && --j2 < 0)
				j2 = anIntArrayArray1003[l].length - 1;
			if(k1 == 1 && ++j2 >= anIntArrayArray1003[l].length)
				j2 = 0;
			anIntArray990[l] = j2;
			aBoolean1031 = true;
		}
		if(j == 324 && !aBoolean1047) {
			aBoolean1047 = true;
			method45();
		}
		if(j == 325 && aBoolean1047) {
			aBoolean1047 = false;
			method45();
		}
		if(j == 326) {
			stream.createFrame(101);
			stream.writeWordBigEndian(aBoolean1047 ? 0 : 1);
			for(int i1 = 0; i1 < 7; i1++)
				stream.writeWordBigEndian(anIntArray1065[i1]);

			for(int l1 = 0; l1 < 5; l1++)
				stream.writeWordBigEndian(anIntArray990[l1]);

			return true;
		}
		if(j == 613)
			canMute = !canMute;
		if(j >= 601 && j <= 612) {
			clearTopInterfaces();
			if(reportAbuseInput.length() > 0) {
				stream.createFrame(218);
				stream.writeQWord(TextClass.longForName(reportAbuseInput));
				stream.writeWordBigEndian(j - 601);
				stream.writeWordBigEndian(canMute ? 1 : 0);
			}
		}
		return false;
	}

	private void method49(Stream stream) {
		for(int j = 0; j < anInt893; j++) {
			int k = anIntArray894[j];
			Player player = playerArray[k];
			int l = stream.readUnsignedByte();
			if((l & 0x40) != 0)
				l += stream.readUnsignedByte() << 8;
			method107(l, k, stream, player);
		}
	}

	private void method50(int i, int k, int l, int i1, int j1) {
		int k1 = worldController.method300(j1, l, i);
		if(k1 != 0) {
			int l1 = worldController.method304(j1, l, i, k1);
			int k2 = l1 >> 6 & 3;
			int i3 = l1 & 0x1f;
			int k3 = k;
			if(k1 > 0)
				k3 = i1;
			int ai[] = aSprite_1263.myPixels;
			int k4 = 24624 + l * 4 + (103 - i) * 512 * 4;
			int i5 = k1 >> 14 & 0x7fff;
			ObjectDef class46_2 = ObjectDef.forID(i5);
			if(class46_2.anInt758 != -1) {
				Background background_2 = mapScenes[class46_2.anInt758];
				if(background_2 != null) {
					int i6 = (class46_2.anInt744 * 4 - background_2.anInt1452) / 2;
					int j6 = (class46_2.anInt761 * 4 - background_2.anInt1453) / 2;
					background_2.drawBackground(48 + l * 4 + i6, 48 + (104 - i - class46_2.anInt761) * 4 + j6);
				}
			} else {
				if(i3 == 0 || i3 == 2)
					if(k2 == 0) {
						ai[k4] = k3;
						ai[k4 + 512] = k3;
						ai[k4 + 1024] = k3;
						ai[k4 + 1536] = k3;
					} else if(k2 == 1) {
						ai[k4] = k3;
						ai[k4 + 1] = k3;
						ai[k4 + 2] = k3;
						ai[k4 + 3] = k3;
					} else if(k2 == 2) {
						ai[k4 + 3] = k3;
						ai[k4 + 3 + 512] = k3;
						ai[k4 + 3 + 1024] = k3;
						ai[k4 + 3 + 1536] = k3;
					} else if(k2 == 3) {
						ai[k4 + 1536] = k3;
						ai[k4 + 1536 + 1] = k3;
						ai[k4 + 1536 + 2] = k3;
						ai[k4 + 1536 + 3] = k3;
					}
				if(i3 == 3)
					if(k2 == 0)
						ai[k4] = k3;
					else if(k2 == 1)
						ai[k4 + 3] = k3;
					else if(k2 == 2)
						ai[k4 + 3 + 1536] = k3;
					else if(k2 == 3)
						ai[k4 + 1536] = k3;
				if(i3 == 2)
					if(k2 == 3) {
						ai[k4] = k3;
						ai[k4 + 512] = k3;
						ai[k4 + 1024] = k3;
						ai[k4 + 1536] = k3;
					} else if(k2 == 0) {
						ai[k4] = k3;
						ai[k4 + 1] = k3;
						ai[k4 + 2] = k3;
						ai[k4 + 3] = k3;
					} else if(k2 == 1) {
						ai[k4 + 3] = k3;
						ai[k4 + 3 + 512] = k3;
						ai[k4 + 3 + 1024] = k3;
						ai[k4 + 3 + 1536] = k3;
					} else if(k2 == 2) {
						ai[k4 + 1536] = k3;
						ai[k4 + 1536 + 1] = k3;
						ai[k4 + 1536 + 2] = k3;
						ai[k4 + 1536 + 3] = k3;
					}
			}
		}
		k1 = worldController.method302(j1, l, i);
		if(k1 != 0) {
			int i2 = worldController.method304(j1, l, i, k1);
			int l2 = i2 >> 6 & 3;
			int j3 = i2 & 0x1f;
			int l3 = k1 >> 14 & 0x7fff;
			ObjectDef class46_1 = ObjectDef.forID(l3);
			if(class46_1.anInt758 != -1) {
				Background background_1 = mapScenes[class46_1.anInt758];
				if(background_1 != null) {
					int j5 = (class46_1.anInt744 * 4 - background_1.anInt1452) / 2;
					int k5 = (class46_1.anInt761 * 4 - background_1.anInt1453) / 2;
					background_1.drawBackground(48 + l * 4 + j5, 48 + (104 - i - class46_1.anInt761) * 4 + k5);
				}
			} else if(j3 == 9) {
				int l4 = 0xeeeeee;
				if(k1 > 0)
					l4 = 0xee0000;
				int ai1[] = aSprite_1263.myPixels;
				int l5 = 24624 + l * 4 + (103 - i) * 512 * 4;
				if(l2 == 0 || l2 == 2) {
					ai1[l5 + 1536] = l4;
					ai1[l5 + 1024 + 1] = l4;
					ai1[l5 + 512 + 2] = l4;
					ai1[l5 + 3] = l4;
				} else {
					ai1[l5] = l4;
					ai1[l5 + 512 + 1] = l4;
					ai1[l5 + 1024 + 2] = l4;
					ai1[l5 + 1536 + 3] = l4;
				}
			}
		}
		k1 = worldController.method303(j1, l, i);
		if(k1 != 0) {
			int j2 = k1 >> 14 & 0x7fff;
			ObjectDef class46 = ObjectDef.forID(j2);
			if(class46.anInt758 != -1) {
				Background background = mapScenes[class46.anInt758];
				if(background != null) {
					int i4 = (class46.anInt744 * 4 - background.anInt1452) / 2;
					int j4 = (class46.anInt761 * 4 - background.anInt1453) / 2;
					background.drawBackground(48 + l * 4 + i4, 48 + (104 - i - class46.anInt761) * 4 + j4);
				}
			}
		}
	}

	private void loadTitleScreen() { 
		if(normalLogin == true)
			titleBox = new Background(titleStreamLoader, "titlebox", 0);
		else
			titleBox1 = new Sprite("Login/Random/TITLEBOX");
		titleButton = new Background(titleStreamLoader, "titlebutton", 0);
		aBackgroundArray1152s = new Background[12];
		int j = 0;
		try {
			j = Integer.parseInt(getParameter("fl_icon"));
		} catch(Exception _ex) {
		}
		if(j == 0) {
			for(int k = 0; k < 12; k++)
				aBackgroundArray1152s[k] = new Background(titleStreamLoader, "runes", k);

		} else {
			for(int l = 0; l < 12; l++)
				aBackgroundArray1152s[l] = new Background(titleStreamLoader, "runes", 12 + (l & 3));

		}
		aSprite_1201 = new Sprite(128, 265);
		aSprite_1202 = new Sprite(128, 265);
		System.arraycopy(leftSideFlame.anIntArray315, 0, aSprite_1201.myPixels, 0, 33920);

		System.arraycopy(rightSideFlame.anIntArray315, 0, aSprite_1202.myPixels, 0, 33920);

		anIntArray851 = new int[256];
		for(int k1 = 0; k1 < 64; k1++)
			anIntArray851[k1] = k1 * 0x40000;

		for(int l1 = 0; l1 < 64; l1++)
			anIntArray851[l1 + 64] = 0xff0000 + 1024 * l1;

		for(int i2 = 0; i2 < 64; i2++)
			anIntArray851[i2 + 128] = 0xffff00 + 4 * i2;

		for(int j2 = 0; j2 < 64; j2++)
			anIntArray851[j2 + 192] = 0xffffff;

		anIntArray852 = new int[256];
		for(int k2 = 0; k2 < 64; k2++)
			anIntArray852[k2] = k2 * 1024;

		for(int l2 = 0; l2 < 64; l2++)
			anIntArray852[l2 + 64] = 65280 + 4 * l2;

		for(int i3 = 0; i3 < 64; i3++)
			anIntArray852[i3 + 128] = 65535 + 0x40000 * i3;

		for(int j3 = 0; j3 < 64; j3++)
			anIntArray852[j3 + 192] = 0xffffff;

		anIntArray853 = new int[256];
		for(int k3 = 0; k3 < 64; k3++)
			anIntArray853[k3] = k3 * 4;

		for(int l3 = 0; l3 < 64; l3++)
			anIntArray853[l3 + 64] = 255 + 0x40000 * l3;

		for(int i4 = 0; i4 < 64; i4++)
			anIntArray853[i4 + 128] = 0xff00ff + 1024 * i4;

		for(int j4 = 0; j4 < 64; j4++)
			anIntArray853[j4 + 192] = 0xffffff;

		anIntArray850 = new int[256];
		anIntArray1190 = new int[32768];
		anIntArray1191 = new int[32768];
		randomizeBackground(null);
		anIntArray828 = new int[32768];
		anIntArray829 = new int[32768];
		if(!aBoolean831) {
			drawFlames = true;
			aBoolean831 = true;
			startRunnable(this, 2);
		}
	}

	private static void setHighMem() { 
		WorldController.lowMem = true;
		Texture.lowMem = false;
		lowMem = false;
		ObjectManager.lowMem = false;
		ObjectDef.lowMem = false;
	}

	public static void main(String args[]) {
		try {
			nodeID = 10;
			portOff = 0;
			setHighMem(); //sets high or low detail
			isMembers = true;
			signlink.storeid = 32;
			signlink.startpriv(InetAddress.getLocalHost());
			instance = new client();
			instance.createClientFrame(503, 765);//client frame size
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static client instance;

private void loadingStages()
    {
        if(lowMem && loadingStage == 2 && ObjectManager.anInt131 != plane)
        {
            inGameScreen.initDrawingArea();
            loadingPleaseWait.drawSprite(8,9);;
            inGameScreen.drawGraphics(4, super.graphics, 4);
            loadingStage = 1;
            aLong824 = System.currentTimeMillis();
        }
        if(loadingStage == 1)
        {
            int j = method54();
            if(j != 0 && System.currentTimeMillis() - aLong824 > 0x57e40L)
            {
                signlink.reporterror(myUsername + " glcfb " + aLong1215 + "," + j + "," + lowMem + "," + decompressors[0] + "," + onDemandFetcher.getNodeCount() + "," + plane + "," + anInt1069 + "," + anInt1070);
                aLong824 = System.currentTimeMillis();
            }
        }
        if(loadingStage == 2 && plane != anInt985)
        {
            anInt985 = plane;
            method24(plane);
        }
    }

	private int method54() { 
		for(int i = 0; i < aByteArrayArray1183.length; i++) {
			if(aByteArrayArray1183[i] == null && anIntArray1235[i] != -1)
				return -1;
			if(aByteArrayArray1247[i] == null && anIntArray1236[i] != -1)
				return -2;
		}
		boolean flag = true;
		for(int j = 0; j < aByteArrayArray1183.length; j++) {
			byte abyte0[] = aByteArrayArray1247[j];
			if(abyte0 != null) {
				int k = (anIntArray1234[j] >> 8) * 64 - baseX;
				int l = (anIntArray1234[j] & 0xff) * 64 - baseY;
				if(aBoolean1159) {
					k = 10;
					l = 10;
				}
				flag &= ObjectManager.method189(k, abyte0, l);
			}
		}
		if(!flag)
			return -3;
		if(aBoolean1080) {
			return -4;
		} else {
			loadingStage = 2;
			ObjectManager.anInt131 = plane;
			method22();
			stream.createFrame(121);
			return 0;
		}
	}

	private void method55()
	{
		for(Animable_Sub4 class30_sub2_sub4_sub4 = (Animable_Sub4)aClass19_1013.reverseGetFirst(); class30_sub2_sub4_sub4 != null; class30_sub2_sub4_sub4 = (Animable_Sub4)aClass19_1013.reverseGetNext())
			if(class30_sub2_sub4_sub4.anInt1597 != plane || loopCycle > class30_sub2_sub4_sub4.anInt1572)
				class30_sub2_sub4_sub4.unlink();
			else
			if(loopCycle >= class30_sub2_sub4_sub4.anInt1571)
			{
				if(class30_sub2_sub4_sub4.anInt1590 > 0)
				{
					NPC npc = npcArray[class30_sub2_sub4_sub4.anInt1590 - 1];
					if(npc != null && npc.x >= 0 && npc.x < 13312 && npc.y >= 0 && npc.y < 13312)
						class30_sub2_sub4_sub4.method455(loopCycle, npc.y, method42(class30_sub2_sub4_sub4.anInt1597, npc.y, npc.x) - class30_sub2_sub4_sub4.anInt1583, npc.x);
				}
				if(class30_sub2_sub4_sub4.anInt1590 < 0)
				{
					int j = -class30_sub2_sub4_sub4.anInt1590 - 1;
					Player player;
					if(j == unknownInt10)
						player = myPlayer;
					else
						player = playerArray[j];
					if(player != null && player.x >= 0 && player.x < 13312 && player.y >= 0 && player.y < 13312)
						class30_sub2_sub4_sub4.method455(loopCycle, player.y, method42(class30_sub2_sub4_sub4.anInt1597, player.y, player.x) - class30_sub2_sub4_sub4.anInt1583, player.x);
				}
				class30_sub2_sub4_sub4.method456(anInt945);
				worldController.method285(plane, class30_sub2_sub4_sub4.anInt1595, (int)class30_sub2_sub4_sub4.aDouble1587, -1, (int)class30_sub2_sub4_sub4.aDouble1586, 60, (int)class30_sub2_sub4_sub4.aDouble1585, class30_sub2_sub4_sub4, false);
			}

	}

	public AppletContext getAppletContext()
	{
		if(signlink.mainapp != null)
			return signlink.mainapp.getAppletContext();
		else
			return super.getAppletContext();
	}

	private void drawLogo() { 
		byte abyte0[] = titleStreamLoader.getDataForName("title.dat");
		Sprite sprite = new Sprite(abyte0, this);
		if(normalLogin == true) {
			leftSideFlame.initDrawingArea();
			sprite.method346(0, 0);
			rightSideFlame.initDrawingArea();
			sprite.method346(-637, 0);
			aRSImageProducer_1107.initDrawingArea();
			sprite.method346(-128, 0);
			aRSImageProducer_1108.initDrawingArea();
			sprite.method346(-202, -371);
			loginScreenArea.initDrawingArea();
			sprite.method346(-202, -171);
			gameLogo.initDrawingArea();
			sprite.method346(0, -265);
			aRSImageProducer_1113.initDrawingArea();
			sprite.method346(-562, -265);
			aRSImageProducer_1114.initDrawingArea();
			sprite.method346(-128, -171);
			aRSImageProducer_1115.initDrawingArea();
			sprite.method346(-562, -171);
			int ai[] = new int[sprite.myWidth];
			for(int j = 0; j < sprite.myHeight; j++) {
				for(int k = 0; k < sprite.myWidth; k++)
					ai[k] = sprite.myPixels[(sprite.myWidth - k - 1) + sprite.myWidth * j];
				System.arraycopy(ai, 0, sprite.myPixels, sprite.myWidth * j, sprite.myWidth);
			}
		if(normalLogin == true) {
			/*leftSideFlame.initDrawingArea();
			sprite.method346(382, 0);
			rightSideFlame.initDrawingArea();
			sprite.method346(-255, 0);
			aRSImageProducer_1107.initDrawingArea();
			sprite.method346(254, 0);
			aRSImageProducer_1108.initDrawingArea();
			sprite.method346(180, -371);
			loginScreenArea.initDrawingArea();
			sprite.method346(180, -171);
			gameLogo.initDrawingArea();
			sprite.method346(382, -265);
			aRSImageProducer_1113.initDrawingArea();
			sprite.method346(-180, -265);
			aRSImageProducer_1114.initDrawingArea();
			sprite.method346(254, -171);
			aRSImageProducer_1115.initDrawingArea();
			sprite.method346(-180, -171);*/
			sprite = new Sprite(titleStreamLoader, "logo", 0);
		        aRSImageProducer_1107.initDrawingArea();
			//sprite.drawSprite(382 - sprite.myWidth / 2 - 128, 18);
		}
		} else {
			int ai[] = new int[sprite.myWidth];
			for(int j = 0; j < sprite.myHeight; j++) {
				for(int k = 0; k < sprite.myWidth; k++)
					ai[k] = sprite.myPixels[(sprite.myWidth - k - 1) + sprite.myWidth * j];
				System.arraycopy(ai, 0, sprite.myPixels, sprite.myWidth * j, sprite.myWidth);
			}
			LFull = new Sprite("Login/Random/LFull.png");
			LEmpty = new Sprite("Login/Random/LEmpty.png");
			Sprite logo = new Sprite(sign.signlink.findcachedir()+"/Sprites/Logo.PNG");
			logo.drawSprite(385-174-174, 14+25);
			loginScreenArea.initDrawingArea();
			sprite.method346(0, 0);
		}
		sprite = null;
		Object obj = null;
		Object obj1 = null;
		System.gc();
	}

    private void processOnDemandQueue()
    {
        do
        {
            OnDemandData onDemandData;
            do
            {
                onDemandData = onDemandFetcher.getNextNode();
                if(onDemandData == null)
                    return;
                if(onDemandData.dataType == 0)
                {
                    Model.method460(onDemandData.buffer, onDemandData.ID);
                    needDrawTabArea = true;
                    if(backDialogID != -1)
                    	inputTaken = true;
                }
                //if(onDemandData.dataType == 1 && onDemandData.buffer != null)
                   // Class36.method529(onDemandData.buffer, onDemandData.ID);
                if(onDemandData.dataType == 2 && onDemandData.ID == nextSong && onDemandData.buffer != null)
                    saveMidi(songChanging, onDemandData.buffer);
                if(onDemandData.dataType == 3 && loadingStage == 1)
                {
					//System.out.println(onDemandData.ID);
					try {
					writeFile(onDemandData.buffer, "./maps/" + onDemandData.ID + ".dat");
					} catch (Exception e) {}
                    for(int i = 0; i < aByteArrayArray1183.length; i++)
                    {
                        if(anIntArray1235[i] == onDemandData.ID)
                        {
                            aByteArrayArray1183[i] = onDemandData.buffer;
                            if(onDemandData.buffer == null) {
								anIntArray1235[i] = -1;
							}
                            break;
                        }
                        if(anIntArray1236[i] != onDemandData.ID)
                            continue;
                        aByteArrayArray1247[i] = onDemandData.buffer;
                        if(onDemandData.buffer == null) {
                            anIntArray1236[i] = -1;
						}
                        break;
                    }

                }
            } while(onDemandData.dataType != 93 || !onDemandFetcher.method564(onDemandData.ID));
            ObjectManager.method173(new Stream(onDemandData.buffer), onDemandFetcher);
        } while(true);
    }

	private void calcFlamesPosition()
	{
		char c = '\u0100';
		for(int j = 10; j < 117; j++)
		{
			int k = (int)(Math.random() * 100D);
			if(k < 50)
				anIntArray828[j + (c - 2 << 7)] = 255;
		}
		for(int l = 0; l < 100; l++)
		{
			int i1 = (int)(Math.random() * 124D) + 2;
			int k1 = (int)(Math.random() * 128D) + 128;
			int k2 = i1 + (k1 << 7);
			anIntArray828[k2] = 192;
		}

		for(int j1 = 1; j1 < c - 1; j1++)
		{
			for(int l1 = 1; l1 < 127; l1++)
			{
				int l2 = l1 + (j1 << 7);
				anIntArray829[l2] = (anIntArray828[l2 - 1] + anIntArray828[l2 + 1] + anIntArray828[l2 - 128] + anIntArray828[l2 + 128]) / 4;
			}

		}

		anInt1275 += 128;
		if(anInt1275 > anIntArray1190.length)
		{
			anInt1275 -= anIntArray1190.length;
			int i2 = (int)(Math.random() * 12D);
			randomizeBackground(aBackgroundArray1152s[i2]);
		}
		for(int j2 = 1; j2 < c - 1; j2++)
		{
			for(int i3 = 1; i3 < 127; i3++)
			{
				int k3 = i3 + (j2 << 7);
				int i4 = anIntArray829[k3 + 128] - anIntArray1190[k3 + anInt1275 & anIntArray1190.length - 1] / 5;
				if(i4 < 0)
					i4 = 0;
				anIntArray828[k3] = i4;
			}

		}

		System.arraycopy(anIntArray969, 1, anIntArray969, 0, c - 1);

		anIntArray969[c - 1] = (int)(Math.sin((double)loopCycle / 14D) * 16D + Math.sin((double)loopCycle / 15D) * 14D + Math.sin((double)loopCycle / 16D) * 12D);
		if(anInt1040 > 0)
			anInt1040 -= 4;
		if(anInt1041 > 0)
			anInt1041 -= 4;
		if(anInt1040 == 0 && anInt1041 == 0)
		{
			int l3 = (int)(Math.random() * 2000D);
			if(l3 == 0)
				anInt1040 = 1024;
			if(l3 == 1)
				anInt1041 = 1024;
		}
	}

	private boolean saveWave(byte abyte0[], int i)
	{
		return abyte0 == null || signlink.wavesave(abyte0, i);
	}

	private void method60(int i)
	{
		RSInterface class9 = RSInterface.interfaceCache[i];
		for(int j = 0; j < class9.children.length; j++)
		{
			if(class9.children[j] == -1)
				break;
			RSInterface class9_1 = RSInterface.interfaceCache[class9.children[j]];
			if(class9_1.interfaceType == 1)
				method60(class9_1.id);
			class9_1.animationLength = 0;
			class9_1.animationDelay = 0;
		}
	}

	private void drawHeadIcon()
	{
		if(anInt855 != 2)
			return;
		calcEntityScreenPos((anInt934 - baseX << 7) + anInt937, anInt936 * 2, (anInt935 - baseY << 7) + anInt938);
		if(spriteDrawX > -1 && loopCycle % 20 < 10)
			headIconsHint[0].drawSprite(spriteDrawX - 12, spriteDrawY - 28);
	}

	private void mainGameProcessor()
	{
		if(anInt1104 > 1)
			anInt1104--;
		if(anInt1011 > 0)
			anInt1011--;
		if (anInt1500 != 0 || anInt1044 != 0 || anInt1129 != 0) {
            if (anInt1501 < 100) {
                anInt1501++;
                if (anInt1501 == 100) {
                    if (anInt1500 != 0) {
                        inputTaken = true;
                    }
                    if (anInt1044 != 0) {
                        needDrawTabArea = true;
                    }
                }
            }
        } else if (anInt1501 > 0) {
            anInt1501--;
        }
		for(int j = 0; j < 5; j++)
			if(!parsePacket())
				break;
		if(!loggedIn)
			return;
			try{
			 canWalkDelay--;
        if (followNPC > 0)
        {
            NPC n = npcArray[followNPC];
            if (n != null)
            {
                if (!withinDistance(myPlayer.smallX[0], myPlayer.smallY[0], n.smallX[0], n.smallY[0], followDistance))
                {
                    doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, n.smallY[0], myPlayer.smallX[0], false, n.smallX[0]);
                }
            }
        }
        else if (followPlayer > 0 && canWalkDelay <= 0)
        {
            Player p = playerArray[followPlayer];
            if (p != null)
            {
                int dis = getDis(myPlayer.smallX[0], myPlayer.smallY[0], p.smallX[0], p.smallY[0]);
                if (dis > followDistance)
                {
                    doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], followDistance, 0, p.smallY[0], myPlayer.smallX[0], false, p.smallX[0]);
                    canWalkDelay = 30;
                }
                else if (dis == 0)
                {
                    int rnd = random(4);
                    if (rnd == 0)
                    {
                        doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, p.smallY[0], myPlayer.smallX[0], false, p.smallX[0] - 2);
                    }
                    else if (rnd == 1)
                    {
                        doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, p.smallY[0], myPlayer.smallX[0], false, p.smallX[0] + 2);
                    }
                    else if (rnd == 2)
                    {
                        doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, p.smallY[0] - 2, myPlayer.smallX[0], false, p.smallX[0]);
                    }
                    else if (rnd == 3)
                    {
                        doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, p.smallY[0] + 2, myPlayer.smallX[0], false, p.smallX[0]);
                    }
                    canWalkDelay = 60;
                }
            }
        }
		} catch(Exception ex){
		System.out.println("Exception in following, method : in process.)");
		}
		synchronized(mouseDetection.syncObject)
		{
			if(flagged)
			{
				if(super.clickMode3 != 0 || mouseDetection.coordsIndex >= 40)
				{
					stream.createFrame(45);
					stream.writeWordBigEndian(0);
					int j2 = stream.currentOffset;
					int j3 = 0;
					for(int j4 = 0; j4 < mouseDetection.coordsIndex; j4++)
					{
						if(j2 - stream.currentOffset >= 240)
							break;
						j3++;
						int l4 = mouseDetection.coordsY[j4];
						if(l4 < 0)
							l4 = 0;
						else
						if(l4 > 502)
							l4 = 502;
						int k5 = mouseDetection.coordsX[j4];
						if(k5 < 0)
							k5 = 0;
						else
						if(k5 > 764)
							k5 = 764;
						int i6 = l4 * 765 + k5;
						if(mouseDetection.coordsY[j4] == -1 && mouseDetection.coordsX[j4] == -1)
						{
							k5 = -1;
							l4 = -1;
							i6 = 0x7ffff;
						}
						if(k5 == anInt1237 && l4 == anInt1238)
						{
							if(anInt1022 < 2047)
								anInt1022++;
						} else
						{
							int j6 = k5 - anInt1237;
							anInt1237 = k5;
							int k6 = l4 - anInt1238;
							anInt1238 = l4;
							if(anInt1022 < 8 && j6 >= -32 && j6 <= 31 && k6 >= -32 && k6 <= 31)
							{
								j6 += 32;
								k6 += 32;
								stream.writeWord((anInt1022 << 12) + (j6 << 6) + k6);
								anInt1022 = 0;
							} else
							if(anInt1022 < 8)
							{
								stream.writeDWordBigEndian(0x800000 + (anInt1022 << 19) + i6);
								anInt1022 = 0;
							} else
							{
								stream.writeDWord(0xc0000000 + (anInt1022 << 19) + i6);
								anInt1022 = 0;
							}
						}
					}

					stream.writeBytes(stream.currentOffset - j2);
					if(j3 >= mouseDetection.coordsIndex)
					{
						mouseDetection.coordsIndex = 0;
					} else
					{
						mouseDetection.coordsIndex -= j3;
						for(int i5 = 0; i5 < mouseDetection.coordsIndex; i5++)
						{
							mouseDetection.coordsX[i5] = mouseDetection.coordsX[i5 + j3];
							mouseDetection.coordsY[i5] = mouseDetection.coordsY[i5 + j3];
						}

					}
				}
			} else
			{
				mouseDetection.coordsIndex = 0;
			}
		}
		if(super.clickMode3 != 0)
		{
			long l = (super.aLong29 - aLong1220) / 50L;
			if(l > 4095L)
				l = 4095L;
			aLong1220 = super.aLong29;
			int k2 = super.saveClickY;
			if(k2 < 0)
				k2 = 0;
			else
			if(k2 > 502)
				k2 = 502;
			int k3 = super.saveClickX;
			if(k3 < 0)
				k3 = 0;
			else
			if(k3 > 764)
				k3 = 764;
			int k4 = k2 * 765 + k3;
			int j5 = 0;
			if(super.clickMode3 == 2)
				j5 = 1;
			int l5 = (int)l;
			stream.createFrame(241);
			stream.writeDWord((l5 << 20) + (j5 << 19) + k4);
		}
		if(anInt1016 > 0)
			anInt1016--;
		if(super.keyArray[1] == 1 || super.keyArray[2] == 1 || super.keyArray[3] == 1 || super.keyArray[4] == 1)
			aBoolean1017 = true;
		if(aBoolean1017 && anInt1016 <= 0)
		{
			anInt1016 = 20;
			aBoolean1017 = false;
			stream.createFrame(86);
			stream.writeWord(anInt1184);
			stream.method432(minimapInt1);
		}
		if(super.awtFocus && !aBoolean954)
		{
			aBoolean954 = true;
			stream.createFrame(3);
			stream.writeWordBigEndian(1);
		}
		if(!super.awtFocus && aBoolean954)
		{
			aBoolean954 = false;
			stream.createFrame(3);
			stream.writeWordBigEndian(0);
		}
		loadingStages();
		method115();
		method90();
		anInt1009++;
		if(anInt1009 > 750)
			dropClient();
		method114();
		method95();
		method38();
		
		anInt945++;
		if(crossType != 0)
		{
			crossIndex += 20;
			if(crossIndex >= 400)
				crossType = 0;
		}
		if(atInventoryInterfaceType != 0)
		{
			atInventoryLoopCycle++;
			if(atInventoryLoopCycle >= 15)
			{
				if(atInventoryInterfaceType == 2)
					needDrawTabArea = true;
				if(atInventoryInterfaceType == 3)
					inputTaken = true;
				atInventoryInterfaceType = 0;
			}
		}
		if(activeInterfaceType != 0)
		{
			anInt989++;
			if(super.mouseX > anInt1087 + 5 || super.mouseX < anInt1087 - 5 || super.mouseY > anInt1088 + 5 || super.mouseY < anInt1088 - 5)
				aBoolean1242 = true;
			if(super.clickMode2 == 0)
			{
				if(activeInterfaceType == 2)
					needDrawTabArea = true;
				if(activeInterfaceType == 3)
					inputTaken = true;
				activeInterfaceType = 0;
				if(aBoolean1242 && anInt989 >= 5)
				{
					lastActiveInvInterface = -1;
					processRightClick();
					if(lastActiveInvInterface == anInt1084 && mouseInvInterfaceIndex != anInt1085)
					{
						RSInterface class9 = RSInterface.interfaceCache[anInt1084];
						int j1 = 0;
						if(anInt913 == 1 && class9.contentType == 206)
							j1 = 1;
						if(class9.inventory[mouseInvInterfaceIndex] <= 0)
							j1 = 0;
						if(class9.deletesTargetSlot)
						{
							int l2 = anInt1085;
							int l3 = mouseInvInterfaceIndex;
							class9.inventory[l3] = class9.inventory[l2];
							class9.inventoryValue[l3] = class9.inventoryValue[l2];
							class9.inventory[l2] = -1;
							class9.inventoryValue[l2] = 0;
						} else
						if(j1 == 1)
						{
							int i3 = anInt1085;
							for(int i4 = mouseInvInterfaceIndex; i3 != i4;)
								if(i3 > i4)
								{
									class9.swapInventoryItems(i3, i3 - 1);
									i3--;
								} else
								if(i3 < i4)
								{
									class9.swapInventoryItems(i3, i3 + 1);
									i3++;
								}

						} else
						{
							class9.swapInventoryItems(anInt1085, mouseInvInterfaceIndex);
						}
						stream.createFrame(214);
						stream.method433(anInt1084);
						stream.method424(j1);
						stream.method433(anInt1085);
						stream.method431(mouseInvInterfaceIndex);
					}
				} else
				if((anInt1253 == 1 || menuHasAddFriend(menuActionRow - 1)) && menuActionRow > 2)
					determineMenuSize();
				else
				if(menuActionRow > 0)
					doAction(menuActionRow - 1);
				atInventoryLoopCycle = 10;
				super.clickMode3 = 0;
			}
		}
		if(WorldController.anInt470 != -1)
		{
			int k = WorldController.anInt470;
			int k1 = WorldController.anInt471;
			boolean flag = doWalkTo(0, 0, 0, 0, myPlayer.smallY[0], 0, 0, k1, myPlayer.smallX[0], true, k);
			WorldController.anInt470 = -1;
			if(flag)
			{
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 1;
				crossIndex = 0;
			}
		}
		if(super.clickMode3 == 1 && aString844 != null)
		{
			aString844 = null;
			inputTaken = true;
			super.clickMode3 = 0;
		}
		processMenuClick();
		processMainScreenClick();
		processTabClick();
		processChatModeClick();
		if(super.clickMode2 == 1 || super.clickMode3 == 1)
			anInt1213++;
		if (anInt1500 != 0 || anInt1044 != 0 || anInt1129 != 0) {
			if (anInt1501 < 100) {
				anInt1501++;
				if (anInt1501 == 100) {
					if (anInt1500 != 0) {
						inputTaken = true;
					}
					if (anInt1044 != 0) {
						needDrawTabArea = true;
					}
				}
			}
		} else if (anInt1501 > 0) {
			anInt1501--;
		}
		if(loadingStage == 2)
			method108();
		if(loadingStage == 2 && aBoolean1160)
			calcCameraPos();
		for(int i1 = 0; i1 < 5; i1++)
			anIntArray1030[i1]++;

		method73();
		super.idleTime++;
		if(super.idleTime > 4500)
		{
			anInt1011 = 250;
			super.idleTime -= 500;
			stream.createFrame(202);
		}
		anInt988++;
		if(anInt988 > 500)
		{
			anInt988 = 0;
			int l1 = (int)(Math.random() * 8D);
			if((l1 & 1) == 1)
				anInt1278 += anInt1279;
			if((l1 & 2) == 2)
				anInt1131 += anInt1132;
			if((l1 & 4) == 4)
				anInt896 += anInt897;
		}
		if(anInt1278 < -50)
			anInt1279 = 2;
		if(anInt1278 > 50)
			anInt1279 = -2;
		if(anInt1131 < -55)
			anInt1132 = 2;
		if(anInt1131 > 55)
			anInt1132 = -2;
		if(anInt896 < -40)
			anInt897 = 1;
		if(anInt896 > 40)
			anInt897 = -1;
		anInt1254++;
		if(anInt1254 > 500)
		{
			anInt1254 = 0;
			int i2 = (int)(Math.random() * 8D);
			if((i2 & 1) == 1)
				minimapInt2 += anInt1210;
			if((i2 & 2) == 2)
				minimapInt3 += anInt1171;
		}
		if(minimapInt2 < -60)
			anInt1210 = 2;
		if(minimapInt2 > 60)
			anInt1210 = -2;
		if(minimapInt3 < -20)
			anInt1171 = 1;
		if(minimapInt3 > 10)
			anInt1171 = -1;
		anInt1010++;
		if(anInt1010 > 50)
			stream.createFrame(0);
		try
		{
			if(socketStream != null && stream.currentOffset > 0)
			{
				socketStream.queueBytes(stream.currentOffset, stream.buffer);
				stream.currentOffset = 0;
				anInt1010 = 0;
			}
		} catch(IOException _ex) {
			dropClient();
		} catch(Exception exception) {
			resetLogout();
		}
	}

	private void method63()
	{
		Class30_Sub1 class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetFirst();
		for(; class30_sub1 != null; class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetNext())
			if(class30_sub1.anInt1294 == -1) {
				class30_sub1.anInt1302 = 0;
				method89(class30_sub1);
			} else {
				class30_sub1.unlink();
			}

	}

	private void resetImageProducers()
	{
		if(aRSImageProducer_1107 != null)
			return;
		super.fullGameScreen = null;
		chatBackImage = null;
		mapBackImage = null;
		inventoryBackImage = null;
		inGameScreen = null;
		//aRSImageProducer_1123 = null;
		aRSImageProducer_1124 = null;
		aRSImageProducer_1125 = null;
		
		leftSideFlame = new RSImageProducer(128, 265, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		rightSideFlame = new RSImageProducer(128, 265, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1107 = new RSImageProducer(509, 171, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1108 = new RSImageProducer(360, 132, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		if(normalLogin == true)  {
			loginScreenArea = new RSImageProducer(360, 200, getGameComponent());
			DrawingArea.setAllPixelsToZero();
		} else if(normalLogin == false)  {
			loginScreenArea = new RSImageProducer(765, 503, getGameComponent());
			DrawingArea.setAllPixelsToZero();
		}
		gameLogo = new RSImageProducer(202, 238, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1113 = new RSImageProducer(203, 238, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1114 = new RSImageProducer(74, 94, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		aRSImageProducer_1115 = new RSImageProducer(75, 94, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		if(titleStreamLoader != null) {
			drawLogo();
			loadTitleScreen();
		}
		welcomeScreenRaised = true;
	}

	void drawLoadingText(int i, String s) {
		anInt1079 = i;
		aString1049 = s;
		resetImageProducers();
		if(titleStreamLoader == null) {
			super.drawLoadingText(i, s);
			return;
		}
		loginScreenArea.initDrawingArea();
		Sprite loadingBarBkg = new Sprite("Login/Loading 1");
		Sprite loadingBar = new Sprite(sign.signlink.findcachedir() + "Sprites/Login/Loading 2.png", (int)Math.round(i*2.02), 12);
		loadingBarBkg.drawSprite(20, 30);
		loadingBar.drawSprite(82, 54);
		newRegularFont.drawCenteredString(s + " - "+i+"%", 180, 46, 0xFFFFFD, 0);

				loginScreenArea.drawGraphics(171, super.graphics, 202);
				if(welcomeScreenRaised)
				{
					welcomeScreenRaised = false;
					if(!aBoolean831)
					{
						leftSideFlame.drawGraphics(0, super.graphics, 0);
						rightSideFlame.drawGraphics(0, super.graphics, 637);
					}
					aRSImageProducer_1107.drawGraphics(0, super.graphics, 128);
					aRSImageProducer_1108.drawGraphics(371, super.graphics, 202);
					gameLogo.drawGraphics(265, super.graphics, 0);
					aRSImageProducer_1113.drawGraphics(265, super.graphics, 562);
					aRSImageProducer_1114.drawGraphics(171, super.graphics, 128);
					aRSImageProducer_1115.drawGraphics(171, super.graphics, 562);
				}
	} 
	

	private void method65(int i, int j, int k, int l, RSInterface class9, int i1, boolean flag,
						  int j1)
	{
		int anInt992;
		if(aBoolean972)
			anInt992 = 32;
		else
			anInt992 = 0;
		aBoolean972 = false;
		if(k >= i && k < i + 16 && l >= i1 && l < i1 + 16)
		{
			class9.scrollPosition -= anInt1213 * 4;
			if(flag)
			{
				needDrawTabArea = true;
			}
		} else
		if(k >= i && k < i + 16 && l >= (i1 + j) - 16 && l < i1 + j)
		{
			class9.scrollPosition += anInt1213 * 4;
			if(flag)
			{
				needDrawTabArea = true;
			}
		} else
		if(k >= i - anInt992 && k < i + 16 + anInt992 && l >= i1 + 16 && l < (i1 + j) - 16 && anInt1213 > 0)
		{
			int l1 = ((j - 32) * j) / j1;
			if(l1 < 8)
				l1 = 8;
			int i2 = l - i1 - 16 - l1 / 2;
			int j2 = j - 32 - l1;
			class9.scrollPosition = ((j1 - j) * i2) / j2;
			if(flag)
				needDrawTabArea = true;
			aBoolean972 = true;
		}
	}

	private boolean method66(int i, int j, int k)
	{
		int i1 = i >> 14 & 0x7fff;
		int j1 = worldController.method304(plane, k, j, i);
		if(j1 == -1)
			return false;
		int k1 = j1 & 0x1f;
		int l1 = j1 >> 6 & 3;
		if(k1 == 10 || k1 == 11 || k1 == 22)
		{
			ObjectDef class46 = ObjectDef.forID(i1);
			int i2;
			int j2;
			if(l1 == 0 || l1 == 2)
			{
				i2 = class46.anInt744;
				j2 = class46.anInt761;
			} else
			{
				i2 = class46.anInt761;
				j2 = class46.anInt744;
			}
			int k2 = class46.anInt768;
			if(l1 != 0)
				k2 = (k2 << l1 & 0xf) + (k2 >> 4 - l1);
			doWalkTo(2, 0, j2, 0, myPlayer.smallY[0], i2, k2, j, myPlayer.smallX[0], false, k);
		} else
		{
			doWalkTo(2, l1, 0, k1 + 1, myPlayer.smallY[0], 0, 0, j, myPlayer.smallX[0], false, k);
		}
		crossX = super.saveClickX;
		crossY = super.saveClickY;
		crossType = 2;
		crossIndex = 0;
		return true;
	}

	private NamedArchive streamLoaderForName(int i, String s, String s1, int j, int k)
	{
		byte abyte0[] = null;
		int l = 5;
        try
        {
            if(decompressors[0] != null) {
                abyte0 = decompressors[0].decompress(i);
			}
            if(abyte0 == null)
            {
                drawLoadingText(0, "Connecting to File Server...");
                new Update("https://dl.dropboxusercontent.com/s/bw6ta78n1qa2qsn/nerio.cache.zip", "cache.zip", signlink.findcachedir());
                  abyte0 = decompressors[0].decompress(i);
			}
        } catch (Exception e) {
			e.printStackTrace();
		}
		if(abyte0 != null)
		{
			NamedArchive archive = new NamedArchive(abyte0, s);
			return archive;
		}
		while (true) {
			drawLoadingText(0, "Error loading... Please report!");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void dropClient()
    {
        if(anInt1011 > 0)
        {
            resetLogout();
            return;
        }
        inGameScreen.initDrawingArea();
        reestablish.drawSprite(8,9);
        inGameScreen.drawGraphics(4, super.graphics, 4);
        anInt1021 = 0;
        destX = 0;
        RSSocket rsSocket = socketStream;
        loggedIn = false;
        loginFailures = 0;
        login(myUsername, myPassword, true);
        if(!loggedIn)
            resetLogout();
        try
        {
            rsSocket.close();
        }
        catch(Exception _ex)
        {
        }
    }

	private void doAction(int i)
	{
		if(i < 0)
			return;
		if(inputDialogState != 0)
		{
			inputDialogState = 0;
			inputTaken = true;
		}
		int j = menuActionCmd2[i];
		int k = menuActionCmd3[i];
		int l = menuActionID[i];
		int i1 = menuActionCmd1[i];
		if(l >= 2000)
			l -= 2000;
		if (l == 476) {
			alertHandler.close();
		}
		if(l == 582)
		{
			NPC npc = npcArray[i1];
			if(npc != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, npc.smallY[0], myPlayer.smallX[0], false, npc.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(57);
				stream.method432(anInt1285);
				stream.method432(i1);
				stream.method431(anInt1283);
				stream.method432(anInt1284);
			}
		}
		if (l == 712) {
			//Toggle
			coinToggle = !coinToggle;
		}
		if (l == 713) {
			//withdraw action
    					inputTaken = true;
    					messagePromptRaised = true;
    					amountOrNameInput = "";
    					inputDialogState = 0;
    					interfaceButtonAction = 557;
    					aString1121 = "Enter amount";
		}
		if (l == 714) {
			//add examine option here
                        pushMessage("Your money pouch currently contains "+RSInterface.interfaceCache[8135].disabledMessage+" coins.", 0, "");
		}
		if (l == 715) {
			//add price check action here
                        pushMessage("Coming soon... be patient!", 0, "");
		}
		if(l == 234)
		{
			boolean flag1 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag1)
				flag1 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(236);
			stream.method431(k + baseY);
			stream.writeWord(i1);
			stream.method431(j + baseX);
		}
		if(l == 62 && method66(i1, k, j))
		{
			stream.createFrame(192);
			stream.writeWord(anInt1284);
			stream.method431(i1 >> 14 & 0x7fff);
			stream.method433(k + baseY);
			stream.method431(anInt1283);
			stream.method433(j + baseX);
			stream.writeWord(anInt1285);
		}
		if(l == 511)
		{
			boolean flag2 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag2)
				flag2 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(25);
			stream.method431(anInt1284);
			stream.method432(anInt1285);
			stream.writeWord(i1);
			stream.method432(k + baseY);
			stream.method433(anInt1283);
			stream.writeWord(j + baseX);
		}
		if(l == 74)
		{
			stream.createFrame(122);
			stream.method433(k);
			stream.method432(j);
			stream.method431(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		/*if(is480 || is508 || is525) {
			switch(l){
				case 1014: setTab(13); 	setSidebarInterface(13, 29500);	break;//harp
				case 1013: setTab(12); 	setSidebarInterface(12, 147);	break;//emotes
				case 1012: setTab(11); 	setSidebarInterface(11, 904);	break;//options
				case 1010: setTab(9); 	setSidebarInterface(9, 5715);	break;//ignore
				case 1009: setTab(8); 	setSidebarInterface(8, 5065);	break;//friend
				case 1027: setTab(6); 	break;//spellbook
				case 1026: setTab(5); 	break;//prayer
				case 1030: setTab(4); 	setSidebarInterface(4, 1644);	break;//worn
				case 1024: setTab(3); 	setSidebarInterface(3, 3213);	break;//inventory
				case 1023: setTab(2);	setSidebarInterface(2, 638);	break;//quests
				case 1022: setTab(1); 	setSidebarInterface(1, 3917);	break;//stats
				case 1021: setTab(0); 	break;//style
				case 1008: setTab(10); 	setSidebarInterface(10, 18128);	break;//logout new clan
				case 1011: setTab(14);	setSidebarInterface(14, 2449);	break;//logout
				case 1502: setTab(7);	setSidebarInterface(7, 2449);	break;//clan
			}
		} else {
			switch(l){
				case 1014: setTab(13); 	setSidebarInterface(13, 29500);	break;//harp
				case 1013: setTab(12); 	setSidebarInterface(12, 147);	break;//emotes
				case 1012: setTab(11); 	setSidebarInterface(11, 904);	break;//options
				case 1010: setTab(9); 	setSidebarInterface(9, 5715);	break;//ignore
				case 1009: setTab(8); 	setSidebarInterface(8, 5065);	break;//friend
				case 1027: setTab(6); 	break;//spellbook
				case 1026: setTab(5); 	break;//prayer
				case 1030: setTab(4); 	setSidebarInterface(4, 1644);	break;//worn
				case 1024: setTab(3); 	setSidebarInterface(3, 3213);	break;//inventory
				case 1023: setTab(2); 	setSidebarInterface(2, 638);	break;//quests
				case 1022: setTab(1); 	setSidebarInterface(1, 3917);	break;//stats
				case 1021: setTab(0);	break;//style
				case 1008: setTab(7);	setSidebarInterface(7, 18128);	break;
				case 1011: setTab(10); 	setSidebarInterface(10, 2449);	break;
			}
		}*/
		switch(l){
			case 1050:
				runState = 1;
				if(!runClicked) {
					runClicked = true;
					musicOrb = false;
					restOrb = false;
					stream.createFrame(185); 
					stream.writeWord(153);
				} else {
					runClicked = false;
					stream.createFrame(185); 
					stream.writeWord(152);
				}
			break;
			case 1500:
				if(!prayClicked){
					prayClicked = true;
				} else {
					prayClicked = false;
				}
			break;
			case 1501:
				runState = 2;
				if(!musicOrb && restOrb){
					musicOrb = true;
					restOrb = false;
				} else if(musicOrb && !restOrb){
					musicOrb = false;
					restOrb = true;
				} else if(!musicOrb && !restOrb){
					musicOrb =false;
					restOrb = true;
				}
			break;
			case 1503:
				if(!drawXpBar) {
					drawXpBar = true;
				} else {
					drawXpBar = false;
				}
			break;
			case 1504:
				testXp = 0;
			break;
		}
		if (l == 315) {
			System.out.println("sendPacket185("+k+")");
            RSInterface class9 = RSInterface.interfaceCache[k];
            boolean flag8 = true;
            if (class9.contentType > 0)
                flag8 = promptUserForInput(class9);
            if (flag8) {
				
				switch(k){
					case 19144:
						sendFrame248(15106,3213);
						method60(15106);
						inputTaken = true;
						break;
					case 27615:
						setSidebarInterface(4, 1644);
					break;
					case 25843: //SETTING TAB
						//setSidebarInterface(11, 24500);
						break;
					case 27610://return
						setSidebarInterface(11, 904);
						break;
					case 27508://hp
						sendFrame126("OFF", 27528);
						if (hitbarToggle) {
							hitbarToggle = false;
							sendFrame126("OFF", 27528);	sendFrame126("", 27524);
						} else {
							hitbarToggle = true;
							sendFrame126("ON", 27524);	sendFrame126("", 27528);
						}
						break;
					case 27509://menu
						sendFrame126("OFF", 27529);
						if (menuToggle) {
							menuToggle = false;
							sendFrame126("OFF", 27529);	sendFrame126("", 27525);
						} else {
							menuToggle = true;
							sendFrame126("ON", 27525);	sendFrame126("", 27529);
						}
						break;
					case 27510://names
						sendFrame126("OFF", 27530);
						if (namesToggle) {
							namesToggle = false;
							sendFrame126("OFF", 27530);	sendFrame126("", 27526);
						} else {
							namesToggle = true;
							sendFrame126("ON", 27526);	sendFrame126("", 27530);
						}
						break;
					case 27532://gameframe
						if(anInt1021 == 2) {
							pushMessage("Switching gameframes is disabled while in barrows.", 0, "");
						} else {
							sendFrame126("459", 27531);
							if(is459) {
								is459 = false;	is474 = true;
								sendFrame126("474", 27536);
								sendFrame126("", 27533);	sendFrame126("", 27537);	sendFrame126("", 27538);	sendFrame126("", 27531);
							} else if(is474) {
								is474 = false;	is480 = true;
								sendFrame126("483", 27536);
								sendFrame126("", 27533);	sendFrame126("", 27537);	sendFrame126("", 27538);	sendFrame126("", 27531);
							} else if (is480) {
								is480 = false;	is508 = true;
								sendFrame126("508", 27537);
								sendFrame126("", 27533);	sendFrame126("", 27536);	sendFrame126("", 27538);	sendFrame126("", 27531);
							} else if(is508) {
								is508 = false;	is525 = true;
								sendFrame126("525", 27538);
								sendFrame126("", 27536);	sendFrame126("", 27537);	sendFrame126("", 27533);	sendFrame126("", 27531);
							} else if(is525) {
								is525 = false;	is562 = true;
								sendFrame126("562", 27536);
								sendFrame126("", 27533);	sendFrame126("", 27537);	sendFrame126("", 27538);	sendFrame126("", 27531);
							} else if(is562) {
								is562 = false;	is508 = false;	is525 = false;	is480 = false;	is474 = false;	is459 = true;
								sendFrame126("", 27533);	sendFrame126("", 27536);	sendFrame126("", 27537);	sendFrame126("", 27538);
								sendFrame126("377", 27531);
							}
						}
					break;
					case 27606://x10
						sendFrame126("OFF", 27605);
						if (newDamage) {
							newDamage = false;
							sendFrame126("OFF", 27605);
							sendFrame126("", 27604);
						} else {
							newDamage = true;
							sendFrame126("ON", 27604);
							sendFrame126("", 27605);
						}
						break;
				case 27521://hitmarks
						sendFrame126("OFF", 27603);
						if (hitmarks) {
							hitmarks = false;
							sendFrame126("OFF", 27603);
							sendFrame126("", 27602);
						} else {
							hitmarks = true;
							sendFrame126("ON", 27602);
							sendFrame126("", 27603);
						}
						break;
					case 29156://achievement
						setSidebarInterface(2, 29265);
						break;
					case 29267://Quest
						setSidebarInterface(2, 638);
						break;
						
					default:
						stream.createFrame(185);
						stream.writeWord(k);
						break;
					
				}
            }
        }
		if(l == 561)
		{
			Player player = playerArray[i1];
			if(player != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, player.smallY[0], myPlayer.smallX[0], false, player.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt1188 += i1;
				if(anInt1188 >= 90)
				{
					stream.createFrame(136);
					anInt1188 = 0;
				}
				stream.createFrame(128);
				stream.writeWord(i1);
			}
		}
		if(l == 20)
		{
			NPC class30_sub2_sub4_sub1_sub1_1 = npcArray[i1];
			if(class30_sub2_sub4_sub1_sub1_1 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_1.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_1.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(155);
				stream.method431(i1);
			}
		}
		if(l == 779)
		{
			Player class30_sub2_sub4_sub1_sub2_1 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_1 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_1.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_1.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(153);
				stream.method431(i1);
			}
		}
		if(l == 516)
			if(!menuOpen)
				worldController.method312(super.saveClickY - 4, super.saveClickX - 4);
			else
				worldController.method312(k - 4, j - 4);
		if(l == 1062)
		{
			anInt924 += baseX;
			if(anInt924 >= 113)
			{
				stream.createFrame(183);
				stream.writeDWordBigEndian(0xe63271);
				anInt924 = 0;
			}
			method66(i1, k, j);
			stream.createFrame(228);
			stream.method432(i1 >> 14 & 0x7fff);
			stream.method432(k + baseY);
			stream.writeWord(j + baseX);
		}
		if(l == 679 && !aBoolean1149)
		{
			stream.createFrame(40);
			stream.writeWord(k);
			aBoolean1149 = true;
		}
		if(l == 431)
		{
			stream.createFrame(129);
			stream.method432(j);
			stream.writeWord(k);
			stream.method432(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 337 || l == 42 || l == 792 || l == 322)
		{
			String s = menuActionName[i];
			int k1 = s.indexOf("@whi@");
			if(k1 != -1)
			{
				long l3 = TextClass.longForName(s.substring(k1 + 5).trim());
				if(l == 337)
					addFriend(l3);
				if(l == 42)
					addIgnore(l3);
				if(l == 792)
					delFriend(l3);
				if(l == 322)
					delIgnore(l3);
			}
		}
		if(l == 53)
		{
			stream.createFrame(135);
			stream.method431(j);
			stream.method432(k);
			stream.method431(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 539)
		{
			stream.createFrame(16);
			stream.method432(i1);
			stream.method433(j);
			stream.method433(k);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 927) {
			String s1 = menuActionName[i];
			int l1 = s1.indexOf("@lre@");
			s1 = s1.substring(l1 + 5).trim();
			launchURL(s1);//Launches the URL in a web browser when clicked
		}
		if(l == 484 || l == 6)
		{
			String s1 = menuActionName[i];
			int l1 = s1.indexOf("@whi@");
			if(l1 != -1)
			{
				s1 = s1.substring(l1 + 5).trim();
				String s7 = TextClass.fixName(TextClass.nameForLong(TextClass.longForName(s1)));
				boolean flag9 = false;
				for(int j3 = 0; j3 < playerCount; j3++)
				{
					Player class30_sub2_sub4_sub1_sub2_7 = playerArray[playerIndices[j3]];
					if(class30_sub2_sub4_sub1_sub2_7 == null || class30_sub2_sub4_sub1_sub2_7.name == null || !class30_sub2_sub4_sub1_sub2_7.name.equalsIgnoreCase(s7))
						continue;
					doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_7.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_7.smallX[0]);
					if(l == 484)
					{
						stream.createFrame(139);
						stream.method431(playerIndices[j3]);
					}
					if(l == 6)
					{
						anInt1188 += i1;
						if(anInt1188 >= 90)
						{
							stream.createFrame(136);
							anInt1188 = 0;
						}
						stream.createFrame(128);
						stream.writeWord(playerIndices[j3]);
					}
					flag9 = true;
					break;
				}

				if(!flag9)
					pushMessage("Unable to find " + s7, 0, "");
			}
		}
		if(l == 870)
		{
			stream.createFrame(53);
			stream.writeWord(j);
			stream.method432(anInt1283);
			stream.method433(i1);
			stream.writeWord(anInt1284);
			stream.method431(anInt1285);
			stream.writeWord(k);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 847)
		{
			stream.createFrame(87);
			stream.method432(i1);
			stream.writeWord(k);
			stream.method432(j);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 626)
		{
			RSInterface class9_1 = RSInterface.interfaceCache[k];
			spellSelected = 1;
			spellID = class9_1.id;
			anInt1137 = k;
			spellUsableOn = class9_1.spellUsableOn;
			itemSelected = 0;
			needDrawTabArea = true;
			spellID = class9_1.id;
			String s4 = class9_1.selectedActionName;
			if(s4.indexOf(" ") != -1)
				s4 = s4.substring(0, s4.indexOf(" "));
			String s8 = class9_1.selectedActionName;
			if(s8.indexOf(" ") != -1)
				s8 = s8.substring(s8.indexOf(" ") + 1);
			if(s8.equals("on")) s8 = "@whi@->";
				spellTooltip = s4 + " " + "@gre@" + class9_1.spellName + " " + s8;
			
			//spellTooltip = s4 + " " + class9_1.spellName + " " + s8;
			//class9_1.disabledSprite.drawSprite(class9_1.xOffset, class9_1.yOffset, 0xffffff);
			//class9_1.disabledSprite.drawSprite(200,200);
			//System.out.println("Sprite: " + class9_1.disabledSprite.toString());
			if(spellUsableOn == 16)
			{
				needDrawTabArea = true;
				tabID = 3;
				tabAreaAltered = true;
			}
			return;
		}
		if(l == 78)
		{
			stream.createFrame(117);
			stream.method433(k);
			stream.method433(i1);
			stream.method431(j);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 27)
		{
			Player class30_sub2_sub4_sub1_sub2_2 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_2 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_2.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_2.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt986 += i1;
				if(anInt986 >= 54)
				{
					stream.createFrame(189);
					stream.writeWordBigEndian(234);
					anInt986 = 0;
				}
				stream.createFrame(73);
				stream.method431(i1);
			}
		}
		
		
		
		if(l == 213)
		{
			boolean flag3 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag3)
				flag3 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(79);
			stream.method431(k + baseY);
			stream.writeWord(i1);
			stream.method432(j + baseX);
		}
		if(l == 632)
		{
			stream.createFrame(145);
			stream.method432(k);
			stream.method432(j);
			stream.method432(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 1005) {
			launchURL("WM.bat");
			pushMessage("Loading worldmap.", 0, "");
		}
		if(l == 1004) {
			if(tabInterfaceIDs[10] != -1) {
				needDrawTabArea = true;
				tabID = 10;
				tabAreaAltered = true;
			}
		}
		if(l == 1003) {
			clanChatMode = 2;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 1002) {
			clanChatMode = 1;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 1001) {
			clanChatMode = 0;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 1000) {
			cButtonCPos = 4;
			chatTypeView = 11;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 999) {
			cButtonCPos = 0;
			chatTypeView = 0;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 998) {
			cButtonCPos = 1;
			chatTypeView = 5;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 997) {
			publicChatMode = 3;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 996) {
			publicChatMode = 2;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 995) {
			publicChatMode = 1;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 994) {
			publicChatMode = 0;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 993) {
			cButtonCPos = 2;
			chatTypeView = 1;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 992) {
			privateChatMode = 2;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 991) {
			privateChatMode = 1;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 990) {
			privateChatMode = 0;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 989) {
			cButtonCPos = 3;
			chatTypeView = 2;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 987) {
			tradeMode = 2;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 986) {
			tradeMode = 1;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 985) {
			tradeMode = 0;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 984) {
			cButtonCPos = 5;
			chatTypeView = 3;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 983) {
			duelMode = 2;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 982) {
			duelMode = 1;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 981) {
			duelMode = 0;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 980) {
			cButtonCPos = 6;
			chatTypeView = 4;
			aBoolean1233 = true;
			inputTaken = true;
		}
		if(l == 493)
		{
			stream.createFrame(75);
			stream.method433(k);
			stream.method431(j);
			stream.method432(i1);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 652)
		{
			boolean flag4 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag4)
				flag4 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(156);
			stream.method432(j + baseX);
			stream.method431(k + baseY);
			stream.method433(i1);
		}
		if(l == 94)
		{
			boolean flag5 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag5)
				flag5 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(181);
			stream.method431(k + baseY);
			stream.writeWord(i1);
			stream.method431(j + baseX);
			stream.method432(anInt1137);
		}
		if(l == 646)
		{
			stream.createFrame(185);
			stream.writeWord(k);
			RSInterface class9_2 = RSInterface.interfaceCache[k];
			if(class9_2.valueIndexArray != null && class9_2.valueIndexArray[0][0] == 5)
			{
				int i2 = class9_2.valueIndexArray[0][1];
				if(variousSettings[i2] != class9_2.requiredValues[0])
				{
					variousSettings[i2] = class9_2.requiredValues[0];
					method33(i2);
					needDrawTabArea = true;
				}
			}
		}
		if(l == 225)
		{
			NPC class30_sub2_sub4_sub1_sub1_2 = npcArray[i1];
			if(class30_sub2_sub4_sub1_sub1_2 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_2.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_2.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt1226 += i1;
				if(anInt1226 >= 85)
				{
					stream.createFrame(230);
					stream.writeWordBigEndian(239);
					anInt1226 = 0;
				}
				stream.createFrame(17);
				stream.method433(i1);
			}
		}
		if(l == 965)
		{
			NPC class30_sub2_sub4_sub1_sub1_3 = npcArray[i1];
			if(class30_sub2_sub4_sub1_sub1_3 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_3.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_3.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				anInt1134++;
				if(anInt1134 >= 96)
				{
					stream.createFrame(152);
					stream.writeWordBigEndian(88);
					anInt1134 = 0;
				}
				stream.createFrame(21);
				stream.writeWord(i1);
			}
		}
		if(l == 413)
		{
			NPC class30_sub2_sub4_sub1_sub1_4 = npcArray[i1];
			if(class30_sub2_sub4_sub1_sub1_4 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_4.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_4.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(131);
				stream.method433(i1);
				stream.method432(anInt1137);
			}
		}
		if(l == 200)
			clearTopInterfaces();
		if(l == 1025)
		{
			NPC class30_sub2_sub4_sub1_sub1_5 = npcArray[i1];
			if(class30_sub2_sub4_sub1_sub1_5 != null)
			{
				EntityDef entityDef = class30_sub2_sub4_sub1_sub1_5.desc;
				if(entityDef.childrenIDs != null)
					entityDef = entityDef.method161();
				if(entityDef != null)
				{
					String s9;
					if(entityDef.description != null)
						s9 = new String(entityDef.description);
					else
						s9 = "It's a " + entityDef.name + ".";
					pushMessage(s9, 0, "");
				}
			}
		}
		if(l == 900)
		{
			method66(i1, k, j);
			stream.createFrame(252);
			stream.method433(i1 >> 14 & 0x7fff);
			stream.method431(k + baseY);
			stream.method432(j + baseX);
		}
		if(l == 412)
		{
			NPC class30_sub2_sub4_sub1_sub1_6 = npcArray[i1];
			if(class30_sub2_sub4_sub1_sub1_6 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_6.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_6.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(72);
				stream.method432(i1);
			}
		}
		if(l == 365)
		{
			Player class30_sub2_sub4_sub1_sub2_3 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_3 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_3.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_3.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(249);
				stream.method432(i1);
				stream.method431(anInt1137);
			}
		}
		if(l == 729)
		{
			Player class30_sub2_sub4_sub1_sub2_4 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_4 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_4.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_4.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(39);
				stream.method431(i1);
			}
		}
		if(l == 577)
		{
			Player class30_sub2_sub4_sub1_sub2_5 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_5 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_5.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_5.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(139);
				stream.method431(i1);
			}
		}
		if(l == 956 && method66(i1, k, j))
		{
			stream.createFrame(35);
			stream.method431(j + baseX);
			stream.method432(anInt1137);
			stream.method432(k + baseY);
			stream.method431(i1 >> 14 & 0x7fff);
		}
		if(l == 567)
		{
			boolean flag6 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag6)
				flag6 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(23);
			stream.method431(k + baseY);
			stream.method431(i1);
			stream.method431(j + baseX);
		}
		if(l == 867)
		{
			if((i1 & 3) == 0)
				anInt1175++;
			if(anInt1175 >= 59)
			{
				stream.createFrame(200);
				stream.writeWord(25501);
				anInt1175 = 0;
			}
			stream.createFrame(43);
			stream.method431(k);
			stream.method432(i1);
			stream.method432(j);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 543)
		{
			stream.createFrame(237);
			stream.writeWord(j);
			stream.method432(i1);
			stream.writeWord(k);
			stream.method432(anInt1137);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 606)
		{
			String s2 = menuActionName[i];
			int j2 = s2.indexOf("@whi@");
			if(j2 != -1)
				if(openInterfaceID == -1)
				{
					clearTopInterfaces();
					reportAbuseInput = s2.substring(j2 + 5).trim();
					canMute = false;
					for(int i3 = 0; i3 < RSInterface.interfaceCache.length; i3++)
					{
						if(RSInterface.interfaceCache[i3] == null || RSInterface.interfaceCache[i3].contentType != 600)
							continue;
						reportAbuseInterfaceID = openInterfaceID = RSInterface.interfaceCache[i3].parentID;
						break;
					}

				} else
				{
					pushMessage("Please close the interface you have open before using 'report abuse'", 0, "");
				}
		}
		if(l == 491)
		{
			Player class30_sub2_sub4_sub1_sub2_6 = playerArray[i1];
			if(class30_sub2_sub4_sub1_sub2_6 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub2_6.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub2_6.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				stream.createFrame(14);
				stream.method432(anInt1284);
				stream.writeWord(i1);
				stream.writeWord(anInt1285);
				stream.method431(anInt1283);
			}
		}
		if(l == 104) {
			RSInterface class9_1 = RSInterface.interfaceCache[k];
			spellID = class9_1.id;
				if(!Autocast) {
					Autocast = true;
					autocastId = class9_1.id;
					stream.createFrame(185);
					stream.writeWord(class9_1.id);
				} else
				if(autocastId == class9_1.id) {
					Autocast = false;
					autocastId = 0;
					stream.createFrame(185);
					stream.writeWord(class9_1.id);
				} else
				if(autocastId != class9_1.id) {
					Autocast = true;
					autocastId = class9_1.id;
					stream.createFrame(185);
					stream.writeWord(class9_1.id);
				}
		}
		if(l == 639)
		{
			String s3 = menuActionName[i];
			int k2 = s3.indexOf("@whi@");
			if(k2 != -1)
			{
				long l4 = TextClass.longForName(s3.substring(k2 + 5).trim());
				int k3 = -1;
				for(int i4 = 0; i4 < friendsCount; i4++)
				{
					if(friendsListAsLongs[i4] != l4)
						continue;
					k3 = i4;
					break;
				}

				if(k3 != -1 && friendsNodeIDs[k3] > 0)
				{
					inputTaken = true;
					inputDialogState = 0;
					messagePromptRaised = true;
					promptInput = "";
					friendsListAction = 3;
					aLong953 = friendsListAsLongs[k3];
					aString1121 = "Enter message to send to " + friendsList[k3];
				}
			}
		}
		if(l == 454)
		{
			stream.createFrame(41);
			stream.writeWord(i1);
			stream.method432(j);
			stream.method432(k);
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			if(RSInterface.interfaceCache[k].parentID == openInterfaceID)
				atInventoryInterfaceType = 1;
			if(RSInterface.interfaceCache[k].parentID == backDialogID)
				atInventoryInterfaceType = 3;
		}
		if(l == 478)
		{
			NPC class30_sub2_sub4_sub1_sub1_7 = npcArray[i1];
			if(class30_sub2_sub4_sub1_sub1_7 != null)
			{
				doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, class30_sub2_sub4_sub1_sub1_7.smallY[0], myPlayer.smallX[0], false, class30_sub2_sub4_sub1_sub1_7.smallX[0]);
				crossX = super.saveClickX;
				crossY = super.saveClickY;
				crossType = 2;
				crossIndex = 0;
				if((i1 & 3) == 0)
					anInt1155++;
				if(anInt1155 >= 53)
				{
					stream.createFrame(85);
					stream.writeWordBigEndian(66);
					anInt1155 = 0;
				}
				stream.createFrame(18);
				stream.method431(i1);
			}
		}
		if(l == 113)
		{
			method66(i1, k, j);
			stream.createFrame(70);
			stream.method431(j + baseX);
			stream.writeWord(k + baseY);
			stream.method433(i1 >> 14 & 0x7fff);
		}
		if(l == 872)
		{
			method66(i1, k, j);
			stream.createFrame(234);
			stream.method433(j + baseX);
			stream.method432(i1 >> 14 & 0x7fff);
			stream.method433(k + baseY);
		}
		if(l == 502)
		{
			method66(i1, k, j);
			stream.createFrame(132);
			stream.method433(j + baseX);
			stream.writeWord(i1 >> 14 & 0x7fff);
			stream.method432(k + baseY);
		}
		if(l == 1125)
		{
			atInventoryLoopCycle = 0;
			atInventoryInterface = k;
			atInventoryIndex = j;
			atInventoryInterfaceType = 2;
			ItemDef itemDef = ItemDef.forID(i1);
			RSInterface class9_4 = RSInterface.interfaceCache[k];
			String s5;
			if(class9_4 != null && class9_4.inventoryValue[j] >= 0x186a0)
				s5 = class9_4.inventoryValue[j] + " x " + itemDef.name;
			else
			if(itemDef.description != null)
				s5 = new String(itemDef.description);
			else
				s5 = "It's a " + itemDef.name + ".";
			pushMessage(s5, 0, "");
		}
		if(l == 169)
		{
			stream.createFrame(185);
			stream.writeWord(k);
			RSInterface class9_3 = RSInterface.interfaceCache[k];
			if(class9_3.valueIndexArray != null && class9_3.valueIndexArray[0][0] == 5)
			{
				int l2 = class9_3.valueIndexArray[0][1];
				variousSettings[l2] = 1 - variousSettings[l2];
				method33(l2);
				needDrawTabArea = true;
			}
		}
		if(l == 447)
		{
			itemSelected = 1;
			anInt1283 = j;
			anInt1284 = k;
			anInt1285 = i1;
			selectedItemName = ItemDef.forID(i1).name;
			spellSelected = 0;
			needDrawTabArea = true;
			return;
		}
		if(l == 1226)
		{
			int j1 = i1 >> 14 & 0x7fff;
			ObjectDef class46 = ObjectDef.forID(j1);
			String s10;
			if(class46.description != null)
				s10 = new String(class46.description);
			else
				s10 = "It's a " + class46.name + ".";
			pushMessage(s10, 0, "");
		}
		if(l == 244)
		{
			boolean flag7 = doWalkTo(2, 0, 0, 0, myPlayer.smallY[0], 0, 0, k, myPlayer.smallX[0], false, j);
			if(!flag7)
				flag7 = doWalkTo(2, 0, 1, 0, myPlayer.smallY[0], 1, 0, k, myPlayer.smallX[0], false, j);
			crossX = super.saveClickX;
			crossY = super.saveClickY;
			crossType = 2;
			crossIndex = 0;
			stream.createFrame(253);
			stream.method431(j + baseX);
			stream.method433(k + baseY);
			stream.method432(i1);
		}
		if(l == 1448)
		{
			ItemDef itemDef_1 = ItemDef.forID(i1);
			String s6;
			if(itemDef_1.description != null)
				s6 = new String(itemDef_1.description);
			else
				s6 = "It's a " + itemDef_1.name + ".";
			pushMessage(s6, 0, "");
		}
		itemSelected = 0;
			spellSelected = 0;
			needDrawTabArea = true;

	}

	private void method70()
	{
		anInt1251 = 0;
		int j = (myPlayer.x >> 7) + baseX;
		int k = (myPlayer.y >> 7) + baseY;
		if(j >= 3053 && j <= 3156 && k >= 3056 && k <= 3136)
			anInt1251 = 1;
		if(j >= 3072 && j <= 3118 && k >= 9492 && k <= 9535)
			anInt1251 = 1;
		if(anInt1251 == 1 && j >= 3139 && j <= 3199 && k >= 3008 && k <= 3062)
			anInt1251 = 0;
	}

	public void run() {
 
		if(drawFlames) {
			drawFlames();
		} else {
			super.run();
		}
	}

	private void build3dScreenMenu()
	{
		if(itemSelected == 0 && spellSelected == 0)
		{
			menuActionName[menuActionRow] = "Walk here";
			menuActionID[menuActionRow] = 516;
			menuActionCmd2[menuActionRow] = super.mouseX;
			menuActionCmd3[menuActionRow] = super.mouseY;
			menuActionRow++;
		}
		int j = -1;
		for(int k = 0; k < Model.anInt1687; k++)
		{
			int l = Model.anIntArray1688[k];
			int i1 = l & 0x7f;
			int j1 = l >> 7 & 0x7f;
			int k1 = l >> 29 & 3;
			int l1 = l >> 14 & 0x7fff;
			if(l == j)
				continue;
			j = l;
			if(k1 == 2 && worldController.method304(plane, i1, j1, l) >= 0)
			{
				ObjectDef class46 = ObjectDef.forID(l1);
				if(class46.childrenIDs != null)
					class46 = class46.method580();
				if(class46 == null)
					continue;
				if(itemSelected == 1)
				{
					menuActionName[menuActionRow] = "Use " + selectedItemName + " -> @cya@" + class46.name;
					menuActionID[menuActionRow] = 62;
					menuActionCmd1[menuActionRow] = l;
					menuActionCmd2[menuActionRow] = i1;
					menuActionCmd3[menuActionRow] = j1;
					menuActionRow++;
				} else
				if(spellSelected == 1)
				{
					if((spellUsableOn & 4) == 4)
					{
						menuActionName[menuActionRow] = spellTooltip + " @cya@" + class46.name;
						menuActionID[menuActionRow] = 956;
						menuActionCmd1[menuActionRow] = l;
						menuActionCmd2[menuActionRow] = i1;
						menuActionCmd3[menuActionRow] = j1;
						menuActionRow++;
					}
				} else
				{
					if(class46.itemActions != null)
					{
						for(int i2 = 4; i2 >= 0; i2--)
							if(class46.itemActions[i2] != null)
							{
								menuActionName[menuActionRow] = class46.itemActions[i2] + " @cya@" + class46.name;
								if(i2 == 0)
									menuActionID[menuActionRow] = 502;
								if(i2 == 1)
									menuActionID[menuActionRow] = 900;
								if(i2 == 2)
									menuActionID[menuActionRow] = 113;
								if(i2 == 3)
									menuActionID[menuActionRow] = 872;
								if(i2 == 4)
									menuActionID[menuActionRow] = 1062;
								menuActionCmd1[menuActionRow] = l;
								menuActionCmd2[menuActionRow] = i1;
								menuActionCmd3[menuActionRow] = j1;
								menuActionRow++;
							}

					}
					if(idToggle == true) {
						menuActionName[menuActionRow] = "Examine @cya@" + class46.name;
					} else {
						menuActionName[menuActionRow] = "Examine @cya@" + class46.name;
					}
					menuActionID[menuActionRow] = 1226;
					menuActionCmd1[menuActionRow] = class46.type << 14;
					menuActionCmd2[menuActionRow] = i1;
					menuActionCmd3[menuActionRow] = j1;
					menuActionRow++;
				}
			}
			if(k1 == 1)
			{
				NPC npc = npcArray[l1];
				if(npc.desc.aByte68 == 1 && (npc.x & 0x7f) == 64 && (npc.y & 0x7f) == 64)
				{
					for(int j2 = 0; j2 < npcCount; j2++)
					{
						NPC npc2 = npcArray[npcIndices[j2]];
						if(npc2 != null && npc2 != npc && npc2.desc.aByte68 == 1 && npc2.x == npc.x && npc2.y == npc.y)
							buildAtNPCMenu(npc2.desc, npcIndices[j2], j1, i1);
					}

					for(int l2 = 0; l2 < playerCount; l2++)
					{
						Player player = playerArray[playerIndices[l2]];
						if(player != null && player.x == npc.x && player.y == npc.y)
							buildAtPlayerMenu(i1, playerIndices[l2], player, j1);
					}

				}
				buildAtNPCMenu(npc.desc, l1, j1, i1);
			}
			if(k1 == 0)
			{
				Player player = playerArray[l1];
				if((player.x & 0x7f) == 64 && (player.y & 0x7f) == 64)
				{
					for(int k2 = 0; k2 < npcCount; k2++)
					{
						NPC class30_sub2_sub4_sub1_sub1_2 = npcArray[npcIndices[k2]];
						if(class30_sub2_sub4_sub1_sub1_2 != null && class30_sub2_sub4_sub1_sub1_2.desc.aByte68 == 1 && class30_sub2_sub4_sub1_sub1_2.x == player.x && class30_sub2_sub4_sub1_sub1_2.y == player.y)
							buildAtNPCMenu(class30_sub2_sub4_sub1_sub1_2.desc, npcIndices[k2], j1, i1);
					}

					for(int i3 = 0; i3 < playerCount; i3++)
					{
						Player class30_sub2_sub4_sub1_sub2_2 = playerArray[playerIndices[i3]];
						if(class30_sub2_sub4_sub1_sub2_2 != null && class30_sub2_sub4_sub1_sub2_2 != player && class30_sub2_sub4_sub1_sub2_2.x == player.x && class30_sub2_sub4_sub1_sub2_2.y == player.y)
							buildAtPlayerMenu(i1, playerIndices[i3], class30_sub2_sub4_sub1_sub2_2, j1);
					}

				}
				buildAtPlayerMenu(i1, l1, player, j1);
			}
			if(k1 == 3)
			{
				NodeList class19 = groundArray[plane][i1][j1];
				if(class19 != null)
				{
					for(Item item = (Item)class19.getFirst(); item != null; item = (Item)class19.getNext())
					{
						ItemDef itemDef = ItemDef.forID(item.ID);
						if(itemSelected == 1)
						{
							menuActionName[menuActionRow] = "Use " + selectedItemName + " -> @lre@" + itemDef.name;
							menuActionID[menuActionRow] = 511;
							menuActionCmd1[menuActionRow] = item.ID;
							menuActionCmd2[menuActionRow] = i1;
							menuActionCmd3[menuActionRow] = j1;
							menuActionRow++;
						} else
						if(spellSelected == 1)
						{
							if((spellUsableOn & 1) == 1)
							{
								menuActionName[menuActionRow] = spellTooltip + " @lre@" + itemDef.name;
								menuActionID[menuActionRow] = 94;
								menuActionCmd1[menuActionRow] = item.ID;
								menuActionCmd2[menuActionRow] = i1;
								menuActionCmd3[menuActionRow] = j1;
								menuActionRow++;
							}
						} else
						{
							for(int j3 = 4; j3 >= 0; j3--)
								if(itemDef.groundActions != null && itemDef.groundActions[j3] != null)
								{
									menuActionName[menuActionRow] = itemDef.groundActions[j3] + " @lre@" + itemDef.name;
									if(j3 == 0)
										menuActionID[menuActionRow] = 652;
									if(j3 == 1)
										menuActionID[menuActionRow] = 567;
									if(j3 == 2)
										menuActionID[menuActionRow] = 234;
									if(j3 == 3)
										menuActionID[menuActionRow] = 244;
									if(j3 == 4)
										menuActionID[menuActionRow] = 213;
									menuActionCmd1[menuActionRow] = item.ID;
									menuActionCmd2[menuActionRow] = i1;
									menuActionCmd3[menuActionRow] = j1;
									menuActionRow++;
								} else
								if(j3 == 2)
								{
									menuActionName[menuActionRow] = "Take @lre@" + itemDef.name;
									menuActionID[menuActionRow] = 234;
									menuActionCmd1[menuActionRow] = item.ID;
									menuActionCmd2[menuActionRow] = i1;
									menuActionCmd3[menuActionRow] = j1;
									menuActionRow++;
								}
						if(idToggle == true) {
							menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name + " @gre@(@whi@" + item.ID + "@gre@)";
						} else {
							menuActionName[menuActionRow] = "Examine @lre@" + itemDef.name;
						}
							menuActionID[menuActionRow] = 1448;
							menuActionCmd1[menuActionRow] = item.ID;
							menuActionCmd2[menuActionRow] = i1;
							menuActionCmd3[menuActionRow] = j1;
							menuActionRow++;
						}
					}

				}
			}
		}
	}

	public void cleanUpForQuit()
	{
		signlink.reporterror = false;
		try
		{
			if(socketStream != null)
				socketStream.close();
				System.out.println("Here6");
		}
		catch(Exception _ex) { }
		socketStream = null;
		stopMidi();
		if(mouseDetection != null)
			mouseDetection.running = false;
		mouseDetection = null;
		onDemandFetcher.disable();
		onDemandFetcher = null;
		aStream_834 = null;
		stream = null;
		aStream_847 = null;
		inStream = null;
		anIntArray1234 = null;
		aByteArrayArray1183 = null;
		aByteArrayArray1247 = null;
		anIntArray1235 = null;
		anIntArray1236 = null;
		intGroundArray = null;
		byteGroundArray = null;
		worldController = null;
		aClass11Array1230 = null;
		anIntArrayArray901 = null;
		anIntArrayArray825 = null;
		bigX = null;
		bigY = null;
		aByteArray912 = null;
		inventoryBackImage = null;
		mapEdgeIP = null;
		leftFrame = null;
		topFrame = null;
		rightFrame = null;
		mapBackImage = null;
		inGameScreen = null;
		chatBackImage = null;
		//aRSImageProducer_1123 = null;
		aRSImageProducer_1124 = null;
		aRSImageProducer_1125 = null;
		/* Null pointers for custom sprites */
		
				alertBack = null;
		alertBorder = null;
		alertBorderH = null;
		chatArea = null;
loadingPleaseWait = null;
reestablish = null;
		loadingBarFull = null;
		loadingBarEmpty = null;
		chatButtons = null;
		tabArea = null;
		mapArea = null;
		HPBarFull = null;
		HPBarEmpty = null;
magicAuto = null;
			tabHover = null;
			tabClicked = null;
			newSideIcons = null;
		/**/
		mapBack = null;
		CustomMapback = null;
		
		sideIcons = null;
		sIcons483 = null;
		sIcons459 = null;
		
		redStones = null;
		compass = null;
		hitMarks = null;
		hitMark = null;
		headIcons = null;
		skullIcons = null;
		headIconsHint = null;
		crosses = null;
		mapDotItem = null;
		mapDotNPC = null;
		mapDotPlayer = null;
		mapDotFriend = null;
		mapDotTeam = null;
		mapScenes = null;
		mapFunctions = null;
		anIntArrayArray929 = null;
		playerArray = null;
		playerIndices = null;
		anIntArray894 = null;
		aStreamArray895s = null;
		anIntArray840 = null;
		npcArray = null;
		npcIndices = null;
		groundArray = null;
		aClass19_1179 = null;
		aClass19_1013 = null;
		aClass19_1056 = null;
		menuActionCmd2 = null;
		menuActionCmd3 = null;
		menuActionID = null;
		menuActionCmd1 = null;
		menuActionName = null;
		variousSettings = null;
		anIntArray1072 = null;
		anIntArray1073 = null;
		aSpriteArray1140 = null;
		aSprite_1263 = null;
		friendsList = null;
		friendsListAsLongs = null;
		friendsNodeIDs = null;
		leftSideFlame = null;
		rightSideFlame = null;
		aRSImageProducer_1107 = null;
		aRSImageProducer_1108 = null;
		loginScreenArea = null;
		gameLogo = null;
		aRSImageProducer_1113 = null;
		aRSImageProducer_1114 = null;
		aRSImageProducer_1115 = null;
		multiOverlay = null;
		nullLoader();
		ObjectDef.nullLoader();
		EntityDef.nullLoader();
		ItemDef.nullLoader();
		Flo.cache = null;
		IdentityKit.cache = null;
		RSInterface.interfaceCache = null;
		DummyClass.cache = null;
		Animation.anims = null;
		SpotAnim.cache = null;
		SpotAnim.aMRUNodes_415 = null;
		Varp.cache = null;
		super.fullGameScreen = null;
		Player.mruNodes = null;
		Texture.nullLoader();
		WorldController.nullLoader();
		Model.nullLoader();
		Class36.nullLoader();
		System.gc();
	}

	private void printDebug()
	{
		System.out.println("============");
		System.out.println("flame-cycle:" + anInt1208);
		if(onDemandFetcher != null)
			System.out.println("Od-cycle:" + onDemandFetcher.onDemandCycle);
		System.out.println("loop-cycle:" + loopCycle);
		System.out.println("draw-cycle:" + anInt1061);
		System.out.println("ptype:" + pktType);
		System.out.println("psize:" + pktSize);
		if(socketStream != null)
			socketStream.printDebug();
		super.shouldDebug = true;
	}

	Component getGameComponent() { 
		 
		
		if(signlink.mainapp != null)
			return signlink.mainapp;
		if(super.gameFrame != null)
			return super.gameFrame;
		else
			return this;
	}

	private void method73() {
		do {
			int j = readChar(-796);
			if(j == -1)
				break;
			if(openInterfaceID != -1 && openInterfaceID == reportAbuseInterfaceID) {
				if(j == 8 && reportAbuseInput.length() > 0)
					reportAbuseInput = reportAbuseInput.substring(0, reportAbuseInput.length() - 1);
				if((j >= 97 && j <= 122 || j >= 65 && j <= 90 || j >= 48 && j <= 57 || j == 32) && reportAbuseInput.length() < 12)
					reportAbuseInput += (char)j;
			} else if(messagePromptRaised) {
				if(j >= 32 && j <= 122 && promptInput.length() < 80) {
					promptInput += (char)j;
					inputTaken = true;
				}
				if(j == 8 && promptInput.length() > 0) {
					promptInput = promptInput.substring(0, promptInput.length() - 1);
					inputTaken = true;
				}
				if(j == 13 || j == 10) {
					messagePromptRaised = false;
					inputTaken = true;
					if(friendsListAction == 1) {
						long l = TextClass.longForName(promptInput);
						addFriend(l);
					}
					if(friendsListAction == 2 && friendsCount > 0) {
						long l1 = TextClass.longForName(promptInput);
						delFriend(l1);
					}
					if(interfaceButtonAction == 557 && promptInput.length() > 0) {
						inputString = "::withdraw "+promptInput;
						sendPacket(103);
					
					}
					if(friendsListAction == 3 && promptInput.length() > 0) {
						stream.createFrame(126);
						stream.writeWordBigEndian(0);
						int k = stream.currentOffset;
						stream.writeQWord(aLong953);
						TextInput.method526(promptInput, stream);
						stream.writeBytes(stream.currentOffset - k);
						promptInput = TextInput.processText(promptInput);
						//promptInput = Censor.doCensor(promptInput);
						pushMessage(promptInput, 6, TextClass.fixName(TextClass.nameForLong(aLong953)));
						if(privateChatMode == 2) {
							privateChatMode = 1;
							aBoolean1233 = true;
							stream.createFrame(95);
							stream.writeWordBigEndian(publicChatMode);
							stream.writeWordBigEndian(privateChatMode);
							stream.writeWordBigEndian(tradeMode);
						}
					}
					if(friendsListAction == 4 && ignoreCount < 100) {
						long l2 = TextClass.longForName(promptInput);
						addIgnore(l2);
					}
					if(friendsListAction == 5 && ignoreCount > 0) {
						long l3 = TextClass.longForName(promptInput);
						delIgnore(l3);
					}
					if(friendsListAction == 6) {
						long l3 = TextClass.longForName(promptInput);
						chatJoin(l3);
					}
				}
			} else if(inputDialogState == 1) {
				if(j >= 48 && j <= 57 && amountOrNameInput.length() < 10) {
					amountOrNameInput += (char)j;
					inputTaken = true;
				}
				if(j == 8 && amountOrNameInput.length() > 0) {
					amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
					inputTaken = true;
				}
				if(j == 13 || j == 10) {
					if(amountOrNameInput.length() > 0) {
						int i1 = 0;
						try {
							i1 = Integer.parseInt(amountOrNameInput);
						}
						catch(Exception _ex) { }
						stream.createFrame(208);
						stream.writeDWord(i1);
					}
					inputDialogState = 0;
					inputTaken = true;
				}
			} else if(inputDialogState == 2) {
				if(j >= 32 && j <= 122 && amountOrNameInput.length() < 12) {
					amountOrNameInput += (char)j;
					inputTaken = true;
				}
				if(j == 8 && amountOrNameInput.length() > 0) {
					amountOrNameInput = amountOrNameInput.substring(0, amountOrNameInput.length() - 1);
					inputTaken = true;
				}
				if(j == 13 || j == 10) {
					if(amountOrNameInput.length() > 0) {
						stream.createFrame(60);
						stream.writeQWord(TextClass.longForName(amountOrNameInput));
					}
					inputDialogState = 0;
					inputTaken = true;
				}
			} else if(backDialogID == -1) {
				if(j >= 32 && j <= 122 && inputString.length() < 80) {
					inputString += (char)j;
					inputTaken = true;
				}
				if(j == 8 && inputString.length() > 0) {
					inputString = inputString.substring(0, inputString.length() - 1);
					inputTaken = true;
				}
				if((j == 13 || j == 10) && inputString.length() > 0)
				{
					if(myPrivilege == 2 || server.equals("127.0.0.1")) {
						if(inputString.equals("::donate")){
							launchURL("neriops.createaforum.com");
						}
						if(inputString.equals("::vote")) {
							launchURL("http://topg.org/server-Hackerscape-id456360");
			}
						if(inputString.equals("::register")) {
							launchURL("neriops.createaforum.com");

						}
					}
					
					if(inputString.startsWith("::")) {
						if(inputString.equals("::newhits") && newDamage == false) {
							newDamage = true;
						} else if(inputString.equals("::newhits") && newDamage == true) {
							newDamage = false;
						}
						/*if(inputString.equals("dumpnpcs"))
							EntityDef.rewriteNpcs();*/
						if (inputString.startsWith("full")) {
							try {
								String[] args = inputString.split(" ");
								int id1 = Integer.parseInt(args[1]);
								int id2 = Integer.parseInt(args[2]);
								fullscreenInterfaceID = id1;
								openInterfaceID = id2;
								pushMessage("Opened Interface", 0, "");
							} catch (Exception e) {
								pushMessage("Interface Failed to load", 0, "");
							}
						}

						if(inputString.equals("::fpson"))
							fps = true;

						if(inputString.equals("::fpsoff"))
							fps = false;

					}
					if(inputString.startsWith("/"))
						inputString = "::" + inputString;
					if(inputString.equals("add model")) {
						try {
							int ModelIndex = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter model ID", "Model", 3));
							byte[] abyte0 = getModel(ModelIndex);
							if(abyte0 != null && abyte0.length > 0) {
								decompressors[1].method234(abyte0.length, abyte0, ModelIndex);
								pushMessage("Model: [" + ModelIndex + "] added successfully!", 0, "");
							} else {
								pushMessage("Unable to find the model. "+ModelIndex, 0, "");
							}
						} catch(Exception e) {
							pushMessage("Syntax - ::add model <path>", 0, "");
						}
					}
					if(inputString.startsWith("/"))
						inputString = "::" + inputString;
					if(inputString.startsWith("::")) {
						stream.createFrame(103);
						stream.writeWordBigEndian(inputString.length() - 1);
						stream.writeString(inputString.substring(2));
					} else {
						String s = inputString.toLowerCase();	
						int j2 = 0;
						if(s.startsWith("yellow:"))
						{
							j2 = 0;
							inputString = inputString.substring(7);
						} else if(s.startsWith("red:"))
						{
							j2 = 1;
							inputString = inputString.substring(4);
						} else if(s.startsWith("green:"))
						{
							j2 = 2;
							inputString = inputString.substring(6);
						} else if(s.startsWith("cyan:"))
						{
							j2 = 3;
							inputString = inputString.substring(5);
						} else if(s.startsWith("purple:"))
						{
							j2 = 4;
							inputString = inputString.substring(7);
						} else if(s.startsWith("white:"))
						{
							j2 = 5;
							inputString = inputString.substring(6);
						} else if(s.startsWith("flash1:"))
						{
							j2 = 6;
							inputString = inputString.substring(7);
						} else if(s.startsWith("flash2:"))
						{
							j2 = 7;
							inputString = inputString.substring(7);
						} else if(s.startsWith("flash3:"))
						{
							j2 = 8;
							inputString = inputString.substring(7);
						} else if(s.startsWith("glow1:"))
						{
							j2 = 9;
							inputString = inputString.substring(6);
						} else if(s.startsWith("glow2:"))
						{
							j2 = 10;
							inputString = inputString.substring(6);
						} else if(s.startsWith("glow3:"))
						{
							j2 = 11;
							inputString = inputString.substring(6);
						}
						s = inputString.toLowerCase();
						int i3 = 0;
						if(s.startsWith("wave:"))
						{
							i3 = 1;
							inputString = inputString.substring(5);
						} else if(s.startsWith("wave2:"))
						{
							i3 = 2;
							inputString = inputString.substring(6);
						} else if(s.startsWith("shake:"))
						{
							i3 = 3;
							inputString = inputString.substring(6);
						} else if(s.startsWith("scroll:"))
						{
							i3 = 4;
							inputString = inputString.substring(7);
						} else if(s.startsWith("slide:"))
						{
							i3 = 5;
							inputString = inputString.substring(6);
						}
						stream.createFrame(4);
						stream.writeWordBigEndian(0);
						int j3 = stream.currentOffset;
						stream.method425(i3);
						stream.method425(j2);
						aStream_834.currentOffset = 0;
						TextInput.method526(inputString, aStream_834);
						stream.method441(0, aStream_834.buffer, aStream_834.currentOffset);
						stream.writeBytes(stream.currentOffset - j3);
						inputString = TextInput.processText(inputString);
						//inputString = Censor.doCensor(inputString);
						myPlayer.textSpoken = inputString;
						myPlayer.anInt1513 = j2;
						myPlayer.anInt1531 = i3;
						myPlayer.textCycle = 150;
//My players icons showing up when I speak
						       if(myPrivilege == 4) {
							pushMessage(myPlayer.textSpoken, 2, "@cr3@" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						} else if(myPrivilege == 3) {
							pushMessage(myPlayer.textSpoken, 2, "@cr2@" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						} else if(myPrivilege == 2) {
							pushMessage(myPlayer.textSpoken, 2, "@cr1@" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						} else if(myPrivilege == 1) {
							pushMessage(myPlayer.textSpoken, 2, "@cr0@" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						} else if(myPrivilege == 5) {
							pushMessage(myPlayer.textSpoken, 2, "@cr3@" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						} else if(myPrivilege == 6) {
							pushMessage(myPlayer.textSpoken, 2, "@cr2@" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						} else if(myPrivilege == 7) {
							pushMessage(myPlayer.textSpoken, 2, "@cr5@" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						} else if(myPrivilege == 8) {
							pushMessage(myPlayer.textSpoken, 2, "@cr4@" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						} else if(myPrivilege == 9) {
							pushMessage(myPlayer.textSpoken, 2, "@cr6@" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						} else {
							pushMessage(myPlayer.textSpoken, 2, "" + "<col=13132800>"+getRank(myPlayer.skill)+"</col> " + myPlayer.name);
						}
						if(publicChatMode == 2)
						{
							publicChatMode = 3;
							aBoolean1233 = true;
							stream.createFrame(95);
							stream.writeWordBigEndian(publicChatMode);
							stream.writeWordBigEndian(privateChatMode);
							stream.writeWordBigEndian(tradeMode);
						}
					}
					inputString = "";
					inputTaken = true;
				}
			}
		} while(true);
	}

	private void buildPublicChat(int j)
	{
		int l = 0;
		for(int i1 = 0; i1 < 500; i1++)
		{
			if(chatMessages[i1] == null)
				continue;
			if(chatTypeView != 1)
				continue;
			int j1 = chatTypes[i1];
			String s = chatNames[i1];
			String ct = chatMessages[i1];
			int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;
			if(s != null && s.startsWith("@cr0@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr1@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr2@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr3@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr4@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr5@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr6@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr7@"))
				s = s.substring(5);
			if((j1 == 1 || j1 == 2) && (j1 == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s))) {
			if(j > k1 - 14 && j <= k1 && !s.equals(myPlayer.name)) {
				if(myPrivilege >= 1) {
					menuActionName[menuActionRow] = "Report abuse";
					menuActionID[menuActionRow] = 606;
					menuActionRow++;
				}
				menuActionName[menuActionRow] = "Add ignore";
				menuActionID[menuActionRow] = 42;
				menuActionRow++;
				menuActionName[menuActionRow] = "Add friend";
				menuActionID[menuActionRow] = 337;
				menuActionRow++;
			}
			l++;
			}
		}
	}

	private void buildFriendChat(int j)
	{
		int l = 0;
		for(int i1 = 0; i1 < 500; i1++) {
			if(chatMessages[i1] == null)
				continue;
			if(chatTypeView != 2)
				continue;
			int j1 = chatTypes[i1];
			String s = chatNames[i1];
			String ct = chatMessages[i1];
			int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;
			if(s != null && s.startsWith("@cr0@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr1@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr2@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr3@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr4@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr5@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr6@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr7@"))
				s = s.substring(5);
			if((j1 == 5 || j1 == 6) && (splitPrivateChat == 0 || chatTypeView == 2) && (j1 == 6 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
				l++;
			if((j1 == 3 || j1 == 7) && (splitPrivateChat == 0 || chatTypeView == 2) && (j1 == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
			{
				if(j > k1 - 14 && j <= k1) {
					if(myPrivilege >= 1) {
						menuActionName[menuActionRow] = "Report abuse";
						menuActionID[menuActionRow] = 606;
						menuActionRow++;
					}
				menuActionName[menuActionRow] = "Add ignore";
				menuActionID[menuActionRow] = 42;
				menuActionRow++;
				menuActionName[menuActionRow] = "Add friend";
				menuActionID[menuActionRow] = 337;
				menuActionRow++;
				}
			l++;
			}
		}
	}

	private void buildDuelorTrade(int j) {
		int l = 0;
		for(int i1 = 0; i1 < 500; i1++) {
			if(chatMessages[i1] == null)
				continue;
			if(chatTypeView != 3 && chatTypeView != 4)
				continue;
			int j1 = chatTypes[i1];
			String s = chatNames[i1];
			int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;
			if(s != null && s.startsWith("@cr0@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr1@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr2@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr3@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr4@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr5@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr6@"))
				s = s.substring(5);
			if(s != null && s.startsWith("@cr7@"))
				s = s.substring(5);
			if(chatTypeView == 3 && j1 == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Accept trade @whi@" + s;
					menuActionID[menuActionRow] = 484;
					menuActionRow++;
				}
				l++;
			}
			if(chatTypeView == 4 && j1 == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Accept challenge @whi@" + s;
					menuActionID[menuActionRow] = 6;
					menuActionRow++;
				}
				l++;
			}
			if(j1 == 12) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Go-to @cya@" + s;
					menuActionID[menuActionRow] = 915;
					menuActionRow++;
				}
				l++;
			}
		}
	}

	private void buildChatAreaMenu(int j) {
		int l = 0;
		int test = 0;
		for(int i1 = 0; i1 < 500; i1++) {
			if(chatMessages[i1] == null)
				continue;
			int j1 = chatTypes[i1];
			int k1 = (70 - l * 14 + 42) + anInt1089 + 4 + 5;
			if(k1 < -23)
				break;
			String s = chatNames[i1];
			String ct = chatMessages[i1];
			boolean flag = false;
			if(chatTypeView == 1) {
				buildPublicChat(j);
				break;
			}
			if(chatTypeView == 2) {
				buildFriendChat(j);
				break;
			}
			if(chatTypeView == 3 || chatTypeView == 4) {
				buildDuelorTrade(j);
				break;
			}
			if(chatTypeView == 5) {
				break;
			}
			if (s != null && s.startsWith("@cr0@")) { 
                s = s.substring(5); 
                byte byte0 = 1; 
            }
			if(s != null && s.startsWith("@cr1@")) {
				s = s.substring(5);
				boolean flag1 = true;
				byte byte0 = 2;
			}
			if(s != null && s.startsWith("@cr2@")) {
				s = s.substring(5);
				byte byte0 = 3;
			}
			if(s != null && s.startsWith("@cr3@")) {
				s = s.substring(5);
				byte byte0 = 4;
			}
			if(s != null && s.startsWith("@cr0@")) {
				s = s.substring(5);
				byte byte0 = 5;
			}
			if(s != null && s.startsWith("@cr3@")) {
				s = s.substring(5);
				byte byte0 = 6;
			}
			if(s != null && s.startsWith("@cr5@")) {
				s = s.substring(5);
				byte byte0 = 7;
			}
			if(s != null && s.startsWith("@cr4@")) {
				s = s.substring(5);
				byte byte0 = 8;
			}
			if(s != null && s.startsWith("@cr6@")) {
				s = s.substring(5);
				byte byte0 = 9;
			}
			if(j1 == 0)
				l++;
			if((j1 == 1 || j1 == 2) && (j1 == 1 || publicChatMode == 0 || publicChatMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1 && !s.equals(myPlayer.name)) {
					if(myPrivilege >= 1) {
						menuActionName[menuActionRow] = "Report abuse";
						menuActionID[menuActionRow] = 606;
						menuActionRow++;
					}
				menuActionName[menuActionRow] = "Add ignore";
				menuActionID[menuActionRow] = 42;
				menuActionRow++; 
				menuActionName[menuActionRow] = "Add friend";
				menuActionID[menuActionRow] = 337;
				menuActionRow++;
				}
				l++;
			}
			if((j1 == 3 || j1 == 7) && splitPrivateChat == 0 && (j1 == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1) {
					if(myPrivilege >= 1) {
						menuActionName[menuActionRow] = "Report abuse";
						menuActionID[menuActionRow] = 606;
						menuActionRow++;
					}
				menuActionName[menuActionRow] = "Add ignore";
				menuActionID[menuActionRow] = 42;
				menuActionRow++;
				menuActionName[menuActionRow] = "Add friend";
				menuActionID[menuActionRow] = 337;
				menuActionRow++;
				}
				l++;
			}
			if(j1 == 4 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Accept trade @whi@" + s;
					menuActionID[menuActionRow] = 484;
					menuActionRow++;
				}
				l++;
			}
			if((j1 == 5 || j1 == 6) && splitPrivateChat == 0 && privateChatMode < 2)
				l++;
			if(j1 == 8 && (tradeMode == 0 || tradeMode == 1 && isFriendOrSelf(s))) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Accept challenge @whi@" + s;
					menuActionID[menuActionRow] = 6;
					menuActionRow++;
				}
				l++;
			}
			if(j1 == 9) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = "Go-to @cya@" + s;
					menuActionID[menuActionRow] = 927;
					menuActionRow++;
				}
				l++;
			}
			if(j1 == 10) {
				if(j > k1 - 14 && j <= k1) {
					menuActionName[menuActionRow] = s;
					menuActionID[menuActionRow] = 1090;
					menuActionRow++;
				}
				l++;
			}
		}
		
	}
	
	
	
	private void drawFriendsListOrWelcomeScreen(RSInterface class9)
	{
		int j = class9.contentType;
		if(j == 204){
			if(maxStats[0] < 99){
				class9.disabledMessage = "Attack: "+currentStats[0]+"/"+maxStats[0]+"\\nCurrent XP: "+currentExp[0]+"\\nNext level: "+getXPForLevel(maxStats[0]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[0]+1) - currentExp[0]);
				return;
			} else {
				class9.disabledMessage = "Attack: "+currentStats[0]+"/"+maxStats[0]+"\\nExperience: "+currentExp[0];
				return;
			}
		}
		if(j == 229){
			if(maxStats[3] < 99){
				class9.disabledMessage = "Hitpoints: "+currentStats[3]+"/"+maxStats[3]+"\\nCurrent XP: "+currentExp[3]+"\\nNext level: "+getXPForLevel(maxStats[3]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[3]+1) - currentExp[3]);
				return;
			} else {
				class9.disabledMessage = "Hitpoints: "+currentStats[3]+"/"+maxStats[3]+"\\nExperience: "+currentExp[3];
				return;
			}
		}
		if(j == 206){
			if(maxStats[14] < 99){
				class9.disabledMessage = "Mining: "+currentStats[14]+"/"+maxStats[14]+"\\nCurrent XP: "+currentExp[14]+"\\nNext level: "+getXPForLevel(maxStats[14]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[14]+1) - currentExp[14]);
				return;
			} else {
				class9.disabledMessage = "Mining: "+currentStats[14]+"/"+maxStats[14]+"\\nExperience: "+currentExp[14];
				return;
			}
		}
		if(j == 207){
			if(maxStats[2] < 99){
				class9.disabledMessage = "Strength: "+currentStats[2]+"/"+maxStats[2]+"\\nCurrent XP: "+currentExp[2]+"\\nNext level: "+getXPForLevel(maxStats[2]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[2]+1) - currentExp[2]);
				return;
			} else {
				class9.disabledMessage = "Strength: "+currentStats[2]+"/"+maxStats[2]+"\\nExperience: "+currentExp[2];
				return;
			}
		}
		if(j == 208){
			if(maxStats[16] < 99){
				class9.disabledMessage = "Agility: "+currentStats[16]+"/"+maxStats[16]+"\\nCurrent XP: "+currentExp[16]+"\\nNext level: "+getXPForLevel(maxStats[16]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[16]+1) - currentExp[16]);
				return;
			} else {
				class9.disabledMessage = "Agility: "+currentStats[16]+"/"+maxStats[16]+"\\nExperience: "+currentExp[16];
				return;
			}
		}
		if(j == 209){
			if(maxStats[13] < 99){
				class9.disabledMessage = "Smithing: "+currentStats[13]+"/"+maxStats[13]+"\\nCurrent XP: "+currentExp[13]+"\\nNext level: "+getXPForLevel(maxStats[13]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[13]+1) - currentExp[13]);
				return;
			} else {
				class9.disabledMessage = "Smithing: "+currentStats[13]+"/"+maxStats[13]+"\\nExperience: "+currentExp[13];
				return;
			}
		}
		if(j == 210){
			if(maxStats[1] < 99){
				class9.disabledMessage = "Defence: "+currentStats[1]+"/"+maxStats[1]+"\\nCurrent XP: "+currentExp[1]+"\\nNext level: "+getXPForLevel(maxStats[1]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[1]+1) - currentExp[1]);
				return;
			} else {
				class9.disabledMessage = "Defence: "+currentStats[1]+"/"+maxStats[1]+"\\nExperience: "+currentExp[1];
				return;
			}
		}
		if(j == 211){
			if(maxStats[15] < 99){
				class9.disabledMessage = "Herblore: "+currentStats[15]+"/"+maxStats[15]+"\\nCurrent XP: "+currentExp[15]+"\\nNext level: "+getXPForLevel(maxStats[15]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[15]+1) - currentExp[15]);
				return;
			} else {
				class9.disabledMessage = "Herblore: "+currentStats[15]+"/"+maxStats[15]+"\\nExperience: "+currentExp[15];
				return;
			}
		}
		if(j == 212){
			if(maxStats[10] < 99){
				class9.disabledMessage = "Fishing: "+currentStats[10]+"/"+maxStats[10]+"\\nCurrent XP: "+currentExp[10]+"\\nNext level: "+getXPForLevel(maxStats[10]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[10]+1) - currentExp[10]);
				return;
			} else {
				class9.disabledMessage = "Fishing: "+currentStats[10]+"/"+maxStats[10]+"\\nExperience: "+currentExp[10];
				return;
			}
		}
		if(j == 213){
			if(maxStats[4] < 99){
				class9.disabledMessage = "Ranged: "+currentStats[4]+"/"+maxStats[4]+"\\nCurrent XP: "+currentExp[4]+"\\nNext level: "+getXPForLevel(maxStats[4]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[4]+1) - currentExp[4]);
				return;
			} else {
				class9.disabledMessage = "Ranged: "+currentStats[4]+"/"+maxStats[4]+"\\nExperience: "+currentExp[4];
				return;
			}
		}
		if(j == 214){
			if(maxStats[17] < 99){
				class9.disabledMessage = "Thieving: "+currentStats[17]+"/"+maxStats[17]+"\\nCurrent XP: "+currentExp[17]+"\\nNext level: "+getXPForLevel(maxStats[17]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[17]+1) - currentExp[17]);
				return;
			} else {
				class9.disabledMessage = "Thieving: "+currentStats[17]+"/"+maxStats[17]+"\\nExperience: "+currentExp[17];
				return;
			}
		}
		if(j == 215){
			if(maxStats[7] < 99){
				class9.disabledMessage = "Cooking: "+currentStats[7]+"/"+maxStats[7]+"\\nCurrent XP: "+currentExp[7]+"\\nNext level: "+getXPForLevel(maxStats[7]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[7]+1) - currentExp[7]);
				return;
			} else {
				class9.disabledMessage = "Cooking: "+currentStats[7]+"/"+maxStats[7]+"\\nExperience: "+currentExp[7];
				return;
			}     
		}
		if(j == 216){
			if(maxStats[5] < 99){
				class9.disabledMessage = "Prayer: "+currentStats[5]+"/"+maxStats[5]+"\\nCurrent XP: "+currentExp[5]+"\\nNext level: "+getXPForLevel(maxStats[5]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[5]+1) - currentExp[5]);
				return;
			} else {
				class9.disabledMessage = "Prayer: "+currentStats[5]+"/"+maxStats[5]+"\\nExperience: "+currentExp[5];
				return; 
			}
		}
		if(j == 217){
			if(maxStats[12] < 99){
				class9.disabledMessage = "Crafting: "+currentStats[12]+"/"+maxStats[12]+"\\nCurrent XP: "+currentExp[12]+"\\nNext level: "+getXPForLevel(maxStats[12]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[12]+1) - currentExp[12]);
				return;
			} else {
				class9.disabledMessage = "Crafting: "+currentStats[12]+"/"+maxStats[12]+"\\nExperience: "+currentExp[12];
				return;  
			}
		}
		if(j == 218){
			if(maxStats[11] < 99){
				class9.disabledMessage = "Firemaking: "+currentStats[11]+"/"+maxStats[11]+"\\nCurrent XP: "+currentExp[11]+"\\nNext level: "+getXPForLevel(maxStats[11]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[11]+1) - currentExp[11]);
				return;
			} else {
				class9.disabledMessage = "Firemaking: "+currentStats[11]+"/"+maxStats[11]+"\\nExperience: "+currentExp[11];
				return; 
			}
		}
		if(j == 219){
			if(maxStats[6] < 99){
				class9.disabledMessage = "Magic: "+currentStats[6]+"/"+maxStats[6]+"\\nCurrent XP: "+currentExp[6]+"\\nNext level: "+getXPForLevel(maxStats[6]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[6]+1) - currentExp[6]);
				return;
			} else {
				class9.disabledMessage = "Magic: "+currentStats[6]+"/"+maxStats[6]+"\\nExperience: "+currentExp[6];
				return;     
			}
		}
		if(j == 220){
			if(maxStats[9] < 99){
				class9.disabledMessage = "Fletching: "+currentStats[9]+"/"+maxStats[9]+"\\nCurrent XP: "+currentExp[9]+"\\nNext level: "+getXPForLevel(maxStats[9]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[9]+1) - currentExp[9]);
				return;
			} else {
				class9.disabledMessage = "Fletching: "+currentStats[6]+"/"+maxStats[6]+"\\nExperience: "+currentExp[9];
				return; 
			}
		}
		if(j == 221){
			if(maxStats[8] < 99){
				class9.disabledMessage = "Woodcutting: "+currentStats[8]+"/"+maxStats[8]+"\\nCurrent XP: "+currentExp[8]+"\\nNext level: "+getXPForLevel(maxStats[8]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[8]+1) - currentExp[8]);
				return;
			} else {
				class9.disabledMessage = "Woodcutting: "+currentStats[8]+"/"+maxStats[8]+"\\nExperience: "+currentExp[8];
				return; 
			}
		}
		if(j == 222){
			if(maxStats[20] < 99){
				class9.disabledMessage = "Runecrafting: "+currentStats[20]+"/"+maxStats[20]+"\\nCurrent XP: "+currentExp[20]+"\\nNext level: "+getXPForLevel(maxStats[20]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[20]+1) - currentExp[20]);
				return;
			} else {
				class9.disabledMessage = "Runecrafting: "+currentStats[20]+"/"+maxStats[20]+"\\nExperience: "+currentExp[20];
				return; 
			}
		}
		if(j == 223){
			if(maxStats[18] < 99){
				class9.disabledMessage = "Slayer: "+currentStats[18]+"/"+maxStats[18]+"\\nCurrent XP: "+currentExp[18]+"\\nNext level: "+getXPForLevel(maxStats[18]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[18]+1) - currentExp[18]);
				return;
			} else {
				class9.disabledMessage = "Slayer: "+currentStats[18]+"/"+maxStats[18]+"\\nExperience: "+currentExp[18];
				return;      
			}
		}
		if(j == 224){
			if(maxStats[19] < 99){
				class9.disabledMessage = "Farming: "+currentStats[19]+"/"+maxStats[19]+"\\nCurrent XP: "+currentExp[19]+"\\nNext level: "+getXPForLevel(maxStats[19]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[19]+1) - currentExp[19]);
				return;
			} else {
				class9.disabledMessage = "Farming: "+currentStats[19]+"/"+maxStats[19]+"\\nExperience: "+currentExp[19];
				return; 
			}
		}
		if(j == 225){
			if(maxStats[21] < 99){
				class9.disabledMessage = "Hunter: "+currentStats[21]+"/"+maxStats[21]+"\\nCurrent XP: "+currentExp[21]+"\\nNext level: "+getXPForLevel(maxStats[21]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[21]+1) - currentExp[21]);
				return;
			} else {
				class9.disabledMessage = "Hunter: "+currentStats[21]+"/"+maxStats[21]+"\\nExperience: "+currentExp[21];
				return; 
			}
		}
		if(j == 226){
			if(maxStats[22] < 99){
				class9.disabledMessage = "Summoning: "+currentStats[22]+"/"+maxStats[22]+"\\nCurrent XP: "+currentExp[22]+"\\nNext level: "+getXPForLevel(maxStats[22]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[22]+1) - currentExp[22]);
				return;
			} else {
				class9.disabledMessage = "Summoning: "+currentStats[22]+"/"+maxStats[22]+"\\nExperience: "+currentExp[22];
				return; 
			}
		}
		if(j == 227){
			if(maxStats[23] < 99){
				class9.disabledMessage = "Pk'ing: "+currentStats[23]+"/"+maxStats[23]+"\\nCurrent XP: "+currentExp[23]+"\\nNext level: "+getXPForLevel(maxStats[23]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[23]+1) - currentExp[23]);
				return;
			} else {
				class9.disabledMessage = "Pk'ing: "+currentStats[23]+"/"+maxStats[23]+"\\nExperience: "+currentExp[23];
				return; 
			}
		}
		if(j == 228){
			if(maxStats[24] < 120){
				class9.disabledMessage = "Dungeoneering: "+currentStats[24]+"/"+maxStats[24]+"\\nCurrent XP: "+currentExp[24]+"\\nNext level: "+getXPForLevel(maxStats[24]+1)+"\\nRemainder: "+(getXPForLevel(maxStats[24]+1) - currentExp[24]);
				return;
			} else {
				class9.disabledMessage = "Dungeoneering: "+currentStats[24]+"/"+maxStats[24]+"\\nExperience: "+currentExp[24];
				return; 
			}
		}
		if(j >= 1 && j <= 100 || j >= 701 && j <= 800)
		{
			if(j == 1 && anInt900 == 0)
			{
				class9.disabledMessage = "Loading friend list";
				class9.atActionType = 0;
				return;
			}
			if(j == 1 && anInt900 == 1)
			{
				class9.disabledMessage = "Connecting to friendserver";
				class9.atActionType = 0;
				return;
			}
			if(j == 2 && anInt900 != 2)
			{
				class9.disabledMessage = "Please wait...";
				class9.atActionType = 0;
				return;
			}
			int k = friendsCount;
			if(anInt900 != 2)
				k = 0;
			if(j > 700)
				j -= 601;
			else
				j--;
			if(j >= k)
			{
				class9.disabledMessage = "";
				class9.atActionType = 0;
				return;
			} else
			{
				class9.disabledMessage = friendsList[j];
				class9.atActionType = 1;
				return;
			}
		}
		if(j >= 101 && j <= 200 || j >= 801 && j <= 900)
        {
            int l = friendsCount;
            if(anInt900 != 2)
                l = 0;
            if(j > 800)
                j -= 701;
            else
                j -= 101;
            if(j >= l)
            {
                class9.disabledMessage = "";
                class9.atActionType = 0;
                return;
            }
            if(friendsNodeIDs[j] == 10)
                class9.disabledMessage = "@gre@Online";
            else
            if(friendsNodeIDs[j] == nodeID)
                class9.disabledMessage = "@red@Offline";
            else
                class9.disabledMessage = "@red@Offline";
            class9.atActionType = 1;
            return;
        }
		if(j == 203)
		{
			int i1 = friendsCount;
			if(anInt900 != 2)
				i1 = 0;
			class9.scrollMax = i1 * 15 + 20;
			if(class9.scrollMax <= class9.height)
				class9.scrollMax = class9.height + 1;
			return;
		}
		if(j >= 401 && j <= 500)
		{
			if((j -= 401) == 0 && anInt900 == 0)
			{
				class9.disabledMessage = "Loading ignore list";
				class9.atActionType = 0;
				return;
			}
			if(j == 1 && anInt900 == 0)
			{
				class9.disabledMessage = "Please wait...";
				class9.atActionType = 0;
				return;
			}
			int j1 = ignoreCount;
			if(anInt900 == 0)
				j1 = 0;
			if(j >= j1)
			{
				class9.disabledMessage = "";
				class9.atActionType = 0;
				return;
			} else
			{
				class9.disabledMessage = TextClass.fixName(TextClass.nameForLong(ignoreListAsLongs[j]));
				class9.atActionType = 1;
				return;
			}
		}
		if(j == 503)
		{
			class9.scrollMax = ignoreCount * 15 + 20;
			if(class9.scrollMax <= class9.height)
				class9.scrollMax = class9.height + 1;
			return;
		}
		if(j == 327)
		{
			class9.modelRotationY = 150;
			class9.modelRotationX = (int)(Math.sin((double)loopCycle / 40D) * 256D) & 0x7ff;
			if(aBoolean1031)
			{
				for(int k1 = 0; k1 < 7; k1++)
				{
					int l1 = anIntArray1065[k1];
					if(l1 >= 0 && !IdentityKit.cache[l1].method537())
						return;
				}

				aBoolean1031 = false;
				Model aclass30_sub2_sub4_sub6s[] = new Model[7];
				int i2 = 0;
				for(int j2 = 0; j2 < 7; j2++)
				{
					int k2 = anIntArray1065[j2];
					if(k2 >= 0)
						aclass30_sub2_sub4_sub6s[i2++] = IdentityKit.cache[k2].method538();
				}

				Model model = new Model(i2, aclass30_sub2_sub4_sub6s);
				for(int l2 = 0; l2 < 5; l2++)
					if(anIntArray990[l2] != 0)
					{
						model.method476(anIntArrayArray1003[l2][0], anIntArrayArray1003[l2][anIntArray990[l2]]);
						if(l2 == 1)
							model.method476(anIntArray1204[0], anIntArray1204[anIntArray990[l2]]);
					}

				model.method469();
				model.method470(Animation.anims[myPlayer.anInt1511].anIntArray353[0]);
				model.method479(64, 850, -30, -50, -30, true);
				class9.disabledMediaType = 5;
				class9.disabledMediaID = 0;
				RSInterface.method208(aBoolean994, model);
			}
			return;
		}
		if(j == 328) {
			RSInterface rsInterface = class9;
			int verticleTilt = 150;
			int animationSpeed = (int)(Math.sin((double)loopCycle / 40D) * 256D) & 0x7ff;
			rsInterface.modelRotationY = verticleTilt;
			rsInterface.modelRotationX = animationSpeed;
			if(aBoolean1031) {
				Model characterDisplay = myPlayer.method452();
				for(int l2 = 0; l2 < 5; l2++)
					if(anIntArray990[l2] != 0) {
						characterDisplay.method476(anIntArrayArray1003[l2][0], anIntArrayArray1003[l2][anIntArray990[l2]]);
						if(l2 == 1)
							characterDisplay.method476(anIntArray1204[0], anIntArray1204[anIntArray990[l2]]);
					}
				int staticFrame = myPlayer.anInt1511;
				characterDisplay.method469();
				characterDisplay.method470(Animation.anims[staticFrame].anIntArray353[0]);
				//characterDisplay.method479(64, 850, -30, -50, -30, true);
				rsInterface.disabledMediaType = 5;
				rsInterface.disabledMediaID = 0;
				RSInterface.method208(aBoolean994, characterDisplay);
			}
			return;
		}
		if(j == 324)
		{
			if(aSprite_931 == null)
			{
				aSprite_931 = class9.disabledSprite;
				aSprite_932 = class9.enabledSprite;
			}
			if(aBoolean1047)
			{
				class9.disabledSprite = aSprite_932;
				return;
			} else
			{
				class9.disabledSprite = aSprite_931;
				return;
			}
		}
		if(j == 325)
		{
			if(aSprite_931 == null)
			{
				aSprite_931 = class9.disabledSprite;
				aSprite_932 = class9.enabledSprite;
			}
			if(aBoolean1047)
			{
				class9.disabledSprite = aSprite_931;
				return;
			} else
			{
				class9.disabledSprite = aSprite_932;
				return;
			}
		}
		if(j == 600)
		{
			class9.disabledMessage = reportAbuseInput;
			if(loopCycle % 20 < 10)
			{
				class9.disabledMessage += "|";
				return;
			} else
			{
				class9.disabledMessage += " ";
				return;
			}
		}
		if(j == 613)
			if(myPrivilege >= 1)
			{
				if(canMute) {
					class9.disabledColor = 0xff0000;
					class9.disabledMessage = "Moderator option: Mute player for 48 hours: <ON>";
				} else {
					class9.disabledColor = 0xffffff;
					class9.disabledMessage = "Moderator option: Mute player for 48 hours: <OFF>";
				}
			} else {
				class9.disabledMessage = "";
			}
		if(j == 650 || j == 655)
			if(anInt1193 != 0)
			{
				String s;
				if(daysSinceLastLogin == 0)
					s = "earlier today";
				else
				if(daysSinceLastLogin == 1)
					s = "yesterday";
				else
					s = daysSinceLastLogin + " days ago";
				class9.disabledMessage = "You last logged in " + s + " from: " + signlink.dns;
			} else
			{
				class9.disabledMessage = "";
			}
		if(j == 651)
		{
			if(unreadMessages == 0)
			{
				class9.disabledMessage = "0 unread messages";
				class9.disabledColor = 0xffff00;
			}
			if(unreadMessages == 1)
			{
				class9.disabledMessage = "1 unread disabledMessage";
				class9.disabledColor = 65280;
			}
			if(unreadMessages > 1)
			{
				class9.disabledMessage = unreadMessages + " unread messages";
				class9.disabledColor = 65280;
			}
		}
		if(j == 652)
			if(daysSinceRecovChange == 201)
			{
				if(membersInt == 1)
					class9.disabledMessage = "@yel@This is a non-members world: @whi@Since you are a member we";
				else
					class9.disabledMessage = "";
			} else
			if(daysSinceRecovChange == 200)
			{
				class9.disabledMessage = "You have not yet set any password recovery questions.";
			} else
			{
				String s1;
				if(daysSinceRecovChange == 0)
					s1 = "Earlier today";
				else
				if(daysSinceRecovChange == 1)
					s1 = "Yesterday";
				else
					s1 = daysSinceRecovChange + " days ago";
				class9.disabledMessage = s1 + " you changed your recovery questions";
			}
		if(j == 653)
			if(daysSinceRecovChange == 201)
			{
				if(membersInt == 1)
					class9.disabledMessage = "@whi@recommend you use a members world instead. You may use";
				else
					class9.disabledMessage = "";
			} else
			if(daysSinceRecovChange == 200)
				class9.disabledMessage = "We strongly recommend you do so now to secure your account.";
			else
				class9.disabledMessage = "If you do not remember making this change then cancel it immediately";
		if(j == 654)
		{
			if(daysSinceRecovChange == 201)
				if(membersInt == 1)
				{
					class9.disabledMessage = "@whi@this world but member benefits are unavailable whilst here.";
					return;
				} else
				{
					class9.disabledMessage = "";
					return;
				}
			if(daysSinceRecovChange == 200)
			{
				class9.disabledMessage = "Do this from the 'account management' area on our front webpage";
				return;
			}
			class9.disabledMessage = "Do this from the 'account management' area on our front webpage";
		}
	}

	private void drawSplitPrivateChat()
	{
		if(splitPrivateChat == 0)
			return;
RSFont textDrawingArea = aTextDrawingArea_1271;
		int i = 0;
		if(anInt1104 != 0)
			i = 1;
		for(int j = 0; j < 100; j++)
			if(chatMessages[j] != null)
			{
				int k = chatTypes[j];
				String s = chatNames[j];
				byte byte1 = 0;
				if(s != null && s.startsWith("@cr0@")) { 
                    s = s.substring(5); 
                    byte1 = 1; 
                }
				if(s != null && s.startsWith("@cr1@")) {
					s = s.substring(5);
					byte1 = 2;
				}
				if(s != null && s.startsWith("@cr2@")) {
					s = s.substring(5);
					byte1 = 3;
				}
				if(s != null && s.startsWith("@cr3@")) {
					s = s.substring(5);
					byte1 = 4;
				}
				if(s != null && s.startsWith("@cr0@")) {
					s = s.substring(5);
					byte1 = 5;
				}
				if(s != null && s.startsWith("@cr3@")) {
					s = s.substring(5);
					byte1 = 6;
				}
				if(s != null && s.startsWith("@cr5@")) {
					s = s.substring(5);
					byte1 = 7;
				}
				if(s != null && s.startsWith("@cr4@")) {
					s = s.substring(5);
					byte1 = 8;
}
				if(s != null && s.startsWith("@cr6@")) {
					s = s.substring(5);
					byte1 = 9;


				}
				if((k == 3 || k == 7) && (k == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
				{
					int l = 329 - i * 13;
					int k1 = 4;
					textDrawingArea.method385(0, "From", l, k1);
					textDrawingArea.method385(65535, "From", l - 1, k1);
					k1 += textDrawingArea.getTextWidth("From ");
					if(byte1 == 1) {
						modIcons[1].drawSprite(k1, l - 12);
						k1 += 12;
					}
					if(byte1 == 2) {
						modIcons[2].drawSprite(k1, l - 12);
						k1 += 12;
					}
					if(byte1 == 3) {
						modIcons[6].drawSprite(k1, l - 12);
						k1 += 12;
					}
					if(byte1 == 4) {
						modIcons[0].drawSprite(k1, l - 12);
						k1 += 12;
					}
					if(byte1 == 5) {
						modIcons[0].drawSprite(k1, l - 12);
						k1 += 12;
					}
					if(byte1 == 6) {
						modIcons[6].drawSprite(k1, l - 12);
						k1 += 12;
					}
					if(byte1 == 7) {
						modIcons[5].drawSprite(k1, l - 12);
						k1 += 12;
					}
					if(byte1 == 8) {
						modIcons[4].drawSprite(k1, l - 12);
						k1 += 12;
					}
					if(byte1 == 9) {
						modIcons[3].drawSprite(k1, l - 12);
						k1 += 12;
					}
					textDrawingArea.method385(0, s + ": " + chatMessages[j], l, k1);
					textDrawingArea.method385(65535, s + ": " + chatMessages[j], l - 1, k1);
					if(++i >= 5)
						return;
				}
				if(k == 5 && privateChatMode < 2)
				{
					int i1 = 329 - i * 13;
					textDrawingArea.method385(0, chatMessages[j], i1, 4);
					textDrawingArea.method385(65535, chatMessages[j], i1 - 1, 4);
					if(++i >= 5)
						return;
				}
				if(k == 6 && privateChatMode < 2)
				{
					int j1 = 329 - i * 13;
					textDrawingArea.method385(0, "To " + s + ": " + chatMessages[j], j1, 4);
					textDrawingArea.method385(65535, "To " + s + ": " + chatMessages[j], j1 - 1, 4);
					if(++i >= 5)
						return;
				}
			}

	}

	public void pushMessage(String s, int i, String s1) {
		if(i == 0 && dialogID != -1) {
			aString844 = s;
			super.clickMode3 = 0;
		}
		if(backDialogID == -1)
			inputTaken = true;
		for(int j = 499; j > 0; j--) {
			chatTypes[j] = chatTypes[j - 1];
			chatNames[j] = chatNames[j - 1];
			chatMessages[j] = chatMessages[j - 1];
			chatRights[j] = chatRights[j - 1];
		}
		chatTypes[0] = i;
		chatNames[0] = s1;
		chatMessages[0] = s;
		chatRights[0] = rights;
	}
	
	public static void setTab(int id) {
        needDrawTabArea = true;
        tabID = id;
        tabAreaAltered = true;
    }
	public int tabHPos;
	private void processTabClick() { 
			if(is480 == true || is508 == true || is525 == true || is562 == true) {
				if(super.mouseX >= 706 && super.mouseX <= 762 && super.mouseY >= 95 && super.mouseY < 128){
					runHover = true;
				} else {
					runHover = false;
				}
				if(super.mouseX >= 706 && super.mouseX <= 762 && super.mouseY >= 52 && super.mouseY < 87){
					prayHover = true;
				} else {
					prayHover = false;
				}
				if(super.mouseX >= 765-24 && super.mouseX <= 765 && super.mouseY >= 3 && super.mouseY <= 25){
					logHover = true;
				} else {
					logHover = false;
				}
			}
			if(is562 == false){
		if(super.clickMode3 == 1) {
			if(super.saveClickX >= 524 && super.saveClickX <= 561 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[0] != -1)
			{
				needDrawTabArea = true;
				tabID = 0;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 562 && super.saveClickX <= 594 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[1] != -1)
			{
				needDrawTabArea = true;
				tabID = 1;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 595 && super.saveClickX <= 626 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[2] != -1)
			{
				needDrawTabArea = true;
				tabID = 2;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 627 && super.saveClickX <= 660 && super.saveClickY >= 168 && super.saveClickY < 203 && tabInterfaceIDs[3] != -1)
			{
				needDrawTabArea = true;
				tabID = 3;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 661 && super.saveClickX <= 693 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[4] != -1)
			{
				needDrawTabArea = true;
				tabID = 4;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 694 && super.saveClickX <= 725 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[5] != -1)
			{
				needDrawTabArea = true;
				tabID = 5;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 726 && super.saveClickX <= 765 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[6] != -1)
			{
				needDrawTabArea = true;
				tabID = 6;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 524 && super.saveClickX <= 561 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[7] != -1)
			{
				needDrawTabArea = true;
				tabID = 7;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 562 && super.saveClickX <= 594 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[8] != -1)
			{
				needDrawTabArea = true;
				tabID = 8;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 595 && super.saveClickX <= 627 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[9] != -1)
			{
				needDrawTabArea = true;
				tabID = 9;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 627 && super.saveClickX <= 664 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[10] != -1)
			{
				needDrawTabArea = true;
				tabID = 10;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 661 && super.saveClickX <= 694 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[11] != -1)
			{
				needDrawTabArea = true;
				tabID = 11;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 695 && super.saveClickX <= 725 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[12] != -1)
			{
				needDrawTabArea = true;
				tabID = 12;
				tabAreaAltered = true;
			}
			if(super.saveClickX >= 726 && super.saveClickX <= 765 && super.saveClickY >= 466 && super.saveClickY < 502 && tabInterfaceIDs[13] != -1)
			{
				needDrawTabArea = true;
				tabID = 13;
				tabAreaAltered = true;
			}
		}
		} else {
		if(super.mouseX >= 521 && super.mouseX <= 550 && super.mouseY >= 169 && super.mouseY < 205) {
            tabHPos = 0;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 552 && super.mouseX <= 581 && super.mouseY >= 168 && super.mouseY < 205) {
            tabHPos = 1;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 582 && super.mouseX <= 611 && super.mouseY >= 168 && super.mouseY < 205) {
            tabHPos = 2;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 612 && super.mouseX <= 641 && super.mouseY >= 168 && super.mouseY < 203) {
            tabHPos = 3;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 642 && super.mouseX <= 671 && super.mouseY >= 168 && super.mouseY < 205) {
            tabHPos = 4;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 672 && super.mouseX <= 701 && super.mouseY >= 168 && super.mouseY < 205) {
            tabHPos = 5;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 702 && super.mouseX <= 731 && super.mouseY >= 169 && super.mouseY < 205) {
            tabHPos = 6;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 732 && super.mouseX <= 761 && super.mouseY >= 169 && super.mouseY < 205) {
            tabHPos = 7;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 522 && super.mouseX <= 551 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 15;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 552 && super.mouseX <= 581 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 8;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 582 && super.mouseX <= 611 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 9;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 612 && super.mouseX <= 641 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 10;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 642 && super.mouseX <= 671 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 11;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 672 && super.mouseX <= 701 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 12;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 702 && super.mouseX <= 731 && super.mouseY >= 466 && super.mouseY < 502) {
            tabHPos = 13;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 732 && super.mouseX <= 761 && super.mouseY >= 466 && super.mouseY < 502) {
            tabHPos = 14;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else {
            tabHPos = -1;
            needDrawTabArea = true;
            tabAreaAltered = true;
        }
        if(super.clickMode3 == 1) {
            if(super.saveClickX >= 522 && super.saveClickX <= 551 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[0] != -1) {
                needDrawTabArea = true;
                tabID = 0;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 552 && super.saveClickX <= 581 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[1] != -1) {
                needDrawTabArea = true;
                tabID = 1;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 582 && super.saveClickX <= 611 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[2] != -1) {
                needDrawTabArea = true;
                tabID = 2;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 612 && super.saveClickX <= 641 && super.saveClickY >= 168 && super.saveClickY < 203 && tabInterfaceIDs[14] != -1) {
                needDrawTabArea = true;
                tabID = 14;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 642 && super.saveClickX <= 671 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[3] != -1) {
                needDrawTabArea = true;
                tabID = 3;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 672 && super.saveClickX <= 701 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[4] != -1) {
                needDrawTabArea = true;
                tabID = 4;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 702 && super.saveClickX <= 731 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[5] != -1) {
                needDrawTabArea = true;
                tabID = 5;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 732 && super.saveClickX <= 761 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[6] != -1) {
                needDrawTabArea = true;
                tabID = 6;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 522 && super.saveClickX <= 551 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[16] != -1) {
                needDrawTabArea = true;
                tabID = 16;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 552 && super.saveClickX <= 581 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[8] != -1) {
                needDrawTabArea = true;
                tabID = 8;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 582 && super.saveClickX <= 611 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[9] != -1) {
                needDrawTabArea = true;
                tabID = 9;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 612 && super.saveClickX <= 641 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[7] != -1) {
                needDrawTabArea = true;
                tabID = 7;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 642 && super.saveClickX <= 671 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[11] != -1) {
                needDrawTabArea = true;
                tabID = 11;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 672 && super.saveClickX <= 701 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[12] != -1) {
                needDrawTabArea = true;
                tabID = 12;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 702 && super.saveClickX <= 731 && super.saveClickY >= 466 && super.saveClickY < 502 && tabInterfaceIDs[13] != -1) {
                needDrawTabArea = true;
                tabID = 13;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 732 && super.saveClickX <= 761 && super.saveClickY >= 466 && super.saveClickY < 502 && tabInterfaceIDs[15] != -1) {
                needDrawTabArea = true;
                tabID = 15;
                tabAreaAltered = true;
            }
            /* Logout X */
            else if(super.saveClickX >= 742 && super.saveClickX <= 764 && super.saveClickY >= 1 && super.saveClickY < 24 && tabInterfaceIDs[10] != -1) {
                needDrawTabArea = true;
                tabID = 10;
                tabAreaAltered = true;
            }

		}
	}
		
	}

	private void resetImageProducers2() {
		if(chatBackImage != null)
			return;
		nullLoader();
		super.fullGameScreen = null;
		aRSImageProducer_1107 = null;
		aRSImageProducer_1108 = null;
		loginScreenArea = null;
		leftSideFlame = null;
		rightSideFlame = null;
		gameLogo = null;
		aRSImageProducer_1113 = null;
		aRSImageProducer_1114 = null;
		aRSImageProducer_1115 = null;
		chatBackImage = new RSImageProducer(516, 165, getGameComponent());//519
		mapBackImage = new RSImageProducer(249, 168, getGameComponent());//
		DrawingArea.setAllPixelsToZero();
		//mapBack.drawBackground(0, 0);
		CustomMapback[getSpriteID()].drawSprite(0, 0);
		inventoryBackImage = new RSImageProducer(250, 335, getGameComponent());
		inGameScreen = new RSImageProducer(512, 334, getGameComponent());
		DrawingArea.setAllPixelsToZero();
		//aRSImageProducer_1123 = new RSImageProducer(496, 50, getGameComponent());
		aRSImageProducer_1124 = new RSImageProducer(269, 37, getGameComponent());
		aRSImageProducer_1125 = new RSImageProducer(249, 45, getGameComponent());
		welcomeScreenRaised = true;
	}

	public String getDocumentBaseHost() { 
		if (signlink.mainapp != null) {
			return signlink.mainapp.getDocumentBase().getHost().toLowerCase();
		}
		return null;
	}

	private void method81(Sprite sprite, int j, int k) {
		int l = k * k + j * j;
		if(l > 4225 && l < 0x15f90) {
			int i1 = minimapInt1 + minimapInt2 & 0x7ff;
			int j1 = Model.modelIntArray1[i1];
			int k1 = Model.modelIntArray2[i1];
			j1 = (j1 * 256) / (minimapInt3 + 256);
			k1 = (k1 * 256) / (minimapInt3 + 256);
			int l1 = j * j1 + k * k1 >> 16;
			int i2 = j * k1 - k * j1 >> 16;
			double d = Math.atan2(l1, i2);
			int j2 = (int)(Math.sin(d) * 63D);
			int k2 = (int)(Math.cos(d) * 57D);
			mapEdge.method353(83 - k2 - 20, d, (94 + j2 + 4) - 10);
			CustomMapback[getSpriteID()].drawSprite(0, 0);
			return;
		} else {
			markMinimap(sprite, k, j, false);
			CustomMapback[getSpriteID()].drawSprite(0, 0);
		}
	}
	
	public void processRightClick() { 
		if (activeInterfaceType != 0) {
			return;
		}
		menuActionName[0] = "Cancel";
		menuActionID[0] = 1107;
		menuActionRow = 1;
		if (fullscreenInterfaceID != -1) {
			anInt886 = 0;
			anInt1315 = 0;
			buildInterfaceMenu(8, RSInterface.interfaceCache[fullscreenInterfaceID], super.mouseX, 8, super.mouseY, 0);
			if (anInt886 != anInt1026) {
				anInt1026 = anInt886;
			}
			if (anInt1315 != anInt1129) {
				anInt1129 = anInt1315;
			}
			return;
		}
		buildSplitPrivateChatMenu();
		anInt886 = 0;
		anInt1315 = 0;
		if (super.mouseX > 0 && super.mouseY > 0 && super.mouseX < 516 && super.mouseY < 338) {
			if (openInterfaceID != -1) {
				buildInterfaceMenu(4, RSInterface.interfaceCache[openInterfaceID], super.mouseX, 4, super.mouseY, 0);
			} else {
				build3dScreenMenu();
			}
		}
		if (anInt886 != anInt1026) {
			anInt1026 = anInt886;
		}
		if (anInt1315 != anInt1129) {
			anInt1129 = anInt1315;
		}
		anInt886 = 0;
		anInt1315 = 0;
	   if(super.mouseX > 548 && super.mouseY > 207 && super.mouseX < 740 && super.mouseY < 468) {
			if(invOverlayInterfaceID != -1) {
				buildInterfaceMenu(548, RSInterface.interfaceCache[invOverlayInterfaceID], super.mouseX, 207, super.mouseY, 0);
			} else if(tabInterfaceIDs[tabID] != -1) {
				buildInterfaceMenu(548, RSInterface.interfaceCache[tabInterfaceIDs[tabID]], super.mouseX, 207, super.mouseY, 0);
			}
		}
		if (anInt886 != anInt1048) {
			needDrawTabArea = true;
			tabAreaAltered = true;
			anInt1048 = anInt886;
		}
		if (anInt1315 != anInt1044) {
			needDrawTabArea = true;
			tabAreaAltered = true;
			anInt1044 = anInt1315;
		}
		anInt886 = 0;
		anInt1315 = 0;
		if(super.mouseX > 0 && super.mouseY > 338 && super.mouseX < 490 && super.mouseY < 463) {
			if(backDialogID != -1) {
				buildInterfaceMenu(20, RSInterface.interfaceCache[backDialogID], super.mouseX, 358, super.mouseY, 0);
			} else if(super.mouseY < 463 && super.mouseX < 490) {
				buildChatAreaMenu(super.mouseY - 338);
			}
		}
		if (backDialogID != -1 && anInt886 != anInt1039) {
			inputTaken = true;
			anInt1039 = anInt886;
		}
		if (backDialogID != -1 && anInt1315 != anInt1500) {
			inputTaken = true;
			anInt1500 = anInt1315;
		}
		/** Custom menu drawing */
		rightClickChatButtons();
			alertHandler.processMouse(super.mouseX, super.mouseY);		determineTopTabs();
		determineBottomTabs();
		
		if(is480 == true || is508 == true || is525 == true || is562 == true) {
			processMinimapActions();
		}
		if(!coinToggle){
			if (super.mouseX >= 512 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118) {
				menuActionName[4] = coinToggle ? "Toggle money pouch" : "Toggle money pouch";
				menuActionID[4] = 712;
				menuActionName[3] = "Withdraw money pouch";
				menuActionID[3] = 713;
				menuActionName[2] = "Examine money pouch";
				menuActionID[2] = 714;
				menuActionName[1] = "Price checker";
				menuActionID[1] = 715;
				menuActionRow = 5;
			}
		} else if(coinToggle){
			if (super.mouseX >= 436 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118) {
				menuActionName[4] = coinToggle ? "Toggle money pouch" : "Toggle money pouch";
				menuActionID[4] = 712;
				menuActionName[3] = "Withdraw money pouch";
				menuActionID[3] = 713;
				menuActionName[2] = "Examine money pouch";
				menuActionID[2] = 714;
				menuActionName[1] = "Price checker";
				menuActionID[1] = 715;
				menuActionRow = 5;
			}
		}
		
		boolean flag = false;
		while (!flag) {
			flag = true;
			for (int j = 0; j < menuActionRow - 1; j++) {
				if (menuActionID[j] < 1000 && menuActionID[j + 1] > 1000) {
					String s = menuActionName[j];
					menuActionName[j] = menuActionName[j + 1];
					menuActionName[j + 1] = s;
					int k = menuActionID[j];
					menuActionID[j] = menuActionID[j + 1];
					menuActionID[j + 1] = k;
					k = menuActionCmd2[j];
					menuActionCmd2[j] = menuActionCmd2[j + 1];
					menuActionCmd2[j + 1] = k;
					k = menuActionCmd3[j];
					menuActionCmd3[j] = menuActionCmd3[j + 1];
					menuActionCmd3[j + 1] = k;
					k = menuActionCmd1[j];
					menuActionCmd1[j] = menuActionCmd1[j + 1];
					menuActionCmd1[j + 1] = k;
					flag = false;
				}
			}
		}
	}

	private int method83(int i, int j, int k)
	{
		int l = 256 - k;
		return ((i & 0xff00ff) * l + (j & 0xff00ff) * k & 0xff00ff00) + ((i & 0xff00) * l + (j & 0xff00) * k & 0xff0000) >> 8;
	}

	private void login(String s, String s1, boolean flag)
	{
		signlink.errorname = s;
		try
		{
			if(!flag)
			{
				loginMessage1 = "";
				loginMessage2 = "Connecting to server...";
				drawLoginScreen(true);
			}
			socketStream = new RSSocket(this, openSocket(43594));
			long l = TextClass.longForName(s);
			int i = (int)(l >> 16 & 31L);
			stream.currentOffset = 0;
			stream.writeWordBigEndian(14);
			stream.writeWordBigEndian(i);
			socketStream.queueBytes(2, stream.buffer);
			for(int j = 0; j < 8; j++)
				socketStream.read();

			int k = socketStream.read();
			int i1 = k;
			if(k == 0)
			{
				socketStream.flushInputStream(inStream.buffer, 8);
				inStream.currentOffset = 0;
				aLong1215 = inStream.readQWord();
				int ai[] = new int[4];
				ai[0] = (int)(Math.random() * 99999999D);
				ai[1] = (int)(Math.random() * 99999999D);
				ai[2] = (int)(aLong1215 >> 32);
				ai[3] = (int)aLong1215;
				stream.currentOffset = 0;
				stream.writeWordBigEndian(10);
				stream.writeDWord(ai[0]);
				stream.writeDWord(ai[1]);
				stream.writeDWord(ai[2]);
				stream.writeDWord(ai[3]);
				stream.writeDWord(123456);
				stream.writeString(s);
				stream.writeString(s1);
				stream.doKeys();
				aStream_847.currentOffset = 0;
				if(flag)
					aStream_847.writeWordBigEndian(18);
				else
					aStream_847.writeWordBigEndian(16);
				aStream_847.writeWordBigEndian(stream.currentOffset + 36 + 1 + 1 + 2);
				aStream_847.writeWordBigEndian(255);
				aStream_847.writeWord(317);
				aStream_847.writeWordBigEndian(lowMem ? 1 : 0);
				for(int l1 = 0; l1 < 9; l1++)
					aStream_847.writeDWord(expectedCRCs[l1]);

				aStream_847.writeBytes(stream.buffer, stream.currentOffset, 0);
				stream.encryption = new ISAACRandomGen(ai);
				for(int j2 = 0; j2 < 4; j2++)
					ai[j2] += 50;

				encryption = new ISAACRandomGen(ai);
				socketStream.queueBytes(aStream_847.currentOffset, aStream_847.buffer);
				k = socketStream.read();
			}
			if(k == 1)
			{
				try
				{
					Thread.sleep(2000L);
				}
				catch(Exception _ex) { }
				login(s, s1, flag);
				return;
			}
			if(k == 2)
			{
				myPrivilege = socketStream.read();
				flagged = socketStream.read() == 1;
				aLong1220 = 0L;
				anInt1022 = 0;
				mouseDetection.coordsIndex = 0;
				super.awtFocus = true;
				aBoolean954 = true;
				loggedIn = true;
				stream.currentOffset = 0;
				inStream.currentOffset = 0;
				pktType = -1;
				anInt841 = -1;
				anInt842 = -1;
				anInt843 = -1;
				pktSize = 0;
				anInt1009 = 0;
				anInt1104 = 0;
				anInt1011 = 0;
				anInt855 = 0;
				menuActionRow = 0;
				menuOpen = false;
				super.idleTime = 0;
				for(int j1 = 0; j1 < 100; j1++)
					chatMessages[j1] = null;

				itemSelected = 0;
				spellSelected = 0;
				loadingStage = 0;
				anInt1062 = 0;
				anInt1278 = (int)(Math.random() * 100D) - 50;
				anInt1131 = (int)(Math.random() * 110D) - 55;
				anInt896 = (int)(Math.random() * 80D) - 40;
				minimapInt2 = (int)(Math.random() * 120D) - 60;
				minimapInt3 = (int)(Math.random() * 30D) - 20;
				minimapInt1 = (int)(Math.random() * 20D) - 10 & 0x7ff;
				anInt1021 = 0;
				anInt985 = -1;
				destX = 0;
				destY = 0;
				playerCount = 0;
				npcCount = 0;
				for(int i2 = 0; i2 < maxPlayers; i2++)
				{
					playerArray[i2] = null;
					aStreamArray895s[i2] = null;
				}

				for(int k2 = 0; k2 < 16384; k2++)
					npcArray[k2] = null;

				myPlayer = playerArray[myPlayerIndex] = new Player();
				aClass19_1013.removeAll();
				aClass19_1056.removeAll();
				for(int l2 = 0; l2 < 4; l2++)
				{
					for(int i3 = 0; i3 < 104; i3++)
					{
						for(int k3 = 0; k3 < 104; k3++)
							groundArray[l2][i3][k3] = null;

					}

				}
				aClass19_1179 = new NodeList();
				fullscreenInterfaceID = -1;
				anInt900 = 0;
				friendsCount = 0;
				dialogID = -1;
				backDialogID = -1;
				openInterfaceID = -1;
				invOverlayInterfaceID = -1;
				anInt1018 = -1;
				aBoolean1149 = false;
				tabID = 3;
				inputDialogState = 0;
				menuOpen = false;
				messagePromptRaised = false;
				aString844 = null;
				anInt1055 = 0;
				anInt1054 = -1;
				aBoolean1047 = true;
				method45();
				welcome();
				for(int j3 = 0; j3 < 5; j3++)
					anIntArray990[j3] = 0;

				for(int l3 = 0; l3 < 5; l3++)
				{
					atPlayerActions[l3] = null;
					atPlayerArray[l3] = false;
				}

				anInt1175 = 0;
				anInt1134 = 0;
				anInt986 = 0;
				anInt1288 = 0;
				anInt924 = 0;
				anInt1188 = 0;
				anInt1155 = 0;
				anInt1226 = 0;
				int anInt941 = 0;
				int anInt1260 = 0;
				resetImageProducers2();
				return;
			}
			if(k == 3)
			{
				loginMessage1 = "";
				loginMessage2 = "Invalid username or password.";
				return;
			}
			if(k == 4)
			{
				loginMessage1 = "Your account has been disabled.";
				loginMessage2 = "Please check your disabledMessage-center for details.";
				return;
			}
			if(k == 5)
			{
				loginMessage1 = "Your account is already logged in.";
				loginMessage2 = "Try again in 60 secs...";
				return;
			}
			if(k == 6)
			{
				loginMessage1 = "Hackerscape has been updated!";
				loginMessage2 = "Please reload this page.";
				return;
			}
			if(k == 7)
			{
				loginMessage1 = "This world is full.";
				loginMessage2 = "Please use a different world.";
				return;
			}
			if(k == 8)
			{
				loginMessage1 = "Unable to connect.";
				loginMessage2 = "Login server offline.";
				return;
			}
			if(k == 9)
			{
				loginMessage1 = "Login limit exceeded.";
				loginMessage2 = "Too many connections from your address.";
				return;
			}
			if(k == 10)
			{
				loginMessage1 = "Unable to connect.";
				loginMessage2 = "Bad session id.";
				return;
			}
			if(k == 11)
			{
				loginMessage2 = "Login server rejected session.";
				loginMessage2 = "Please try again.";
				return;
			}
			if(k == 12)
			{
				loginMessage1 = "You need a members account to login to this world.";
				loginMessage2 = "Please subscribe, or use a different world.";
				return;
			}
			if(k == 13)
			{
				loginMessage1 = "Could not complete login.";
				loginMessage2 = "Please try using a different world.";
				return;
			}
			if(k == 14)
			{
				loginMessage1 = "The server is being updated.";
				loginMessage2 = "Please wait 1 minute and try again.";
				return;
			}
			if(k == 15)
			{
				loggedIn = true;
				stream.currentOffset = 0;
				inStream.currentOffset = 0;
				pktType = -1;
				anInt841 = -1;
				anInt842 = -1;
				anInt843 = -1;
				pktSize = 0;
				anInt1009 = 0;
				anInt1104 = 0;
				menuActionRow = 0;
				menuOpen = false;
				aLong824 = System.currentTimeMillis();
				return;
			}
			if(k == 16)
			{
				loginMessage1 = "Login attempts exceeded.";
				loginMessage2 = "Please wait 1 minute and try again.";
				return;
			}
			if(k == 17)
			{
				loginMessage1 = "You are standing in a members-only area.";
				loginMessage2 = "To play on this world move to a free area first";
				return;
			}
			if(k == 20)
			{
				loginMessage1 = "Invalid loginserver requested";
				loginMessage2 = "Please try using a different world.";
				return;
			}
			if(k == 21)
			{
				for(int k1 = socketStream.read(); k1 >= 0; k1--)
				{
					loginMessage1 = "You have only just left another world";
					loginMessage2 = "Your profile will be transferred in: " + k1 + " seconds";
					drawLoginScreen(true);
					try
					{
						Thread.sleep(1000L);
					}
					catch(Exception _ex) { }
				}

				login(s, s1, flag);
				return;
			}
			if(k == -1)
			{
				if(i1 == 0)
				{
					if(loginFailures < 2)
					{
						try
						{
							Thread.sleep(2000L);
						}
						catch(Exception _ex) { }
						loginFailures++;
						login(s, s1, flag);
						return;
					} else
					{
						loginMessage1 = "You must download The latest";
						loginMessage2 = "Put website here";
						return;
					}
				} else
				{
					loginMessage1 = "No response from server";
					loginMessage2 = "Please try using a different world.";
					return;
				}
			} else
			{
				System.out.println("response:" + k);
				loginMessage1 = "Unexpected server response";
				loginMessage2 = "Please try using a different world.";
				return;
			}
		}
		catch(IOException _ex)
		{
			loginMessage1 = "";
		}
		loginMessage2 = "Server Under Maintenance!";
	}

	private boolean doWalkTo(int i, int j, int k, int i1, int j1, int k1, int l1, int i2, int j2, boolean flag, int k2) {
		byte byte0 = 104;
		byte byte1 = 104;
		for(int l2 = 0; l2 < byte0; l2++) {
			for(int i3 = 0; i3 < byte1; i3++) {
				anIntArrayArray901[l2][i3] = 0;
				anIntArrayArray825[l2][i3] = 0x5f5e0ff;
			}
		}
		int j3 = j2;
		int k3 = j1;
		anIntArrayArray901[j2][j1] = 99;
		anIntArrayArray825[j2][j1] = 0;
		int l3 = 0;
		int i4 = 0;
		bigX[l3] = j2;
		bigY[l3++] = j1;
		boolean flag1 = false;
		int j4 = bigX.length;
		int ai[][] = aClass11Array1230[plane].anIntArrayArray294;
		while(i4 != l3) 
		{
			j3 = bigX[i4];
			k3 = bigY[i4];
			i4 = (i4 + 1) % j4;
			if(j3 == k2 && k3 == i2)
			{
				flag1 = true;
				break;
			}
			if(i1 != 0)
			{
				if((i1 < 5 || i1 == 10) && aClass11Array1230[plane].method219(k2, j3, k3, j, i1 - 1, i2))
				{
					flag1 = true;
					break;
				}
				if(i1 < 10 && aClass11Array1230[plane].method220(k2, i2, k3, i1 - 1, j, j3))
				{
					flag1 = true;
					break;
				}
			}
			if(k1 != 0 && k != 0 && aClass11Array1230[plane].method221(i2, k2, j3, k, l1, k1, k3))
			{
				flag1 = true;
				break;
			}
			int l4 = anIntArrayArray825[j3][k3] + 1;
			if(j3 > 0 && anIntArrayArray901[j3 - 1][k3] == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0)
			{
				bigX[l3] = j3 - 1;
				bigY[l3] = k3;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 - 1][k3] = 2;
				anIntArrayArray825[j3 - 1][k3] = l4;
			}
			if(j3 < byte0 - 1 && anIntArrayArray901[j3 + 1][k3] == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0)
			{
				bigX[l3] = j3 + 1;
				bigY[l3] = k3;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 + 1][k3] = 8;
				anIntArrayArray825[j3 + 1][k3] = l4;
			}
			if(k3 > 0 && anIntArrayArray901[j3][k3 - 1] == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0)
			{
				bigX[l3] = j3;
				bigY[l3] = k3 - 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3][k3 - 1] = 1;
				anIntArrayArray825[j3][k3 - 1] = l4;
			}
			if(k3 < byte1 - 1 && anIntArrayArray901[j3][k3 + 1] == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0)
			{
				bigX[l3] = j3;
				bigY[l3] = k3 + 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3][k3 + 1] = 4;
				anIntArrayArray825[j3][k3 + 1] = l4;
			}
			if(j3 > 0 && k3 > 0 && anIntArrayArray901[j3 - 1][k3 - 1] == 0 && (ai[j3 - 1][k3 - 1] & 0x128010e) == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0)
			{
				bigX[l3] = j3 - 1;
				bigY[l3] = k3 - 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 - 1][k3 - 1] = 3;
				anIntArrayArray825[j3 - 1][k3 - 1] = l4;
			}
			if(j3 < byte0 - 1 && k3 > 0 && anIntArrayArray901[j3 + 1][k3 - 1] == 0 && (ai[j3 + 1][k3 - 1] & 0x1280183) == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0 && (ai[j3][k3 - 1] & 0x1280102) == 0)
			{
				bigX[l3] = j3 + 1;
				bigY[l3] = k3 - 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 + 1][k3 - 1] = 9;
				anIntArrayArray825[j3 + 1][k3 - 1] = l4;
			}
			if(j3 > 0 && k3 < byte1 - 1 && anIntArrayArray901[j3 - 1][k3 + 1] == 0 && (ai[j3 - 1][k3 + 1] & 0x1280138) == 0 && (ai[j3 - 1][k3] & 0x1280108) == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0)
			{
				bigX[l3] = j3 - 1;
				bigY[l3] = k3 + 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 - 1][k3 + 1] = 6;
				anIntArrayArray825[j3 - 1][k3 + 1] = l4;
			}
			if(j3 < byte0 - 1 && k3 < byte1 - 1 && anIntArrayArray901[j3 + 1][k3 + 1] == 0 && (ai[j3 + 1][k3 + 1] & 0x12801e0) == 0 && (ai[j3 + 1][k3] & 0x1280180) == 0 && (ai[j3][k3 + 1] & 0x1280120) == 0)
			{
				bigX[l3] = j3 + 1;
				bigY[l3] = k3 + 1;
				l3 = (l3 + 1) % j4;
				anIntArrayArray901[j3 + 1][k3 + 1] = 12;
				anIntArrayArray825[j3 + 1][k3 + 1] = l4;
			}
		}
		anInt1264 = 0;
		if(!flag1)
		{
			if(flag)
			{
				int i5 = 100;
				for(int k5 = 1; k5 < 2; k5++)
				{
					for(int i6 = k2 - k5; i6 <= k2 + k5; i6++)
					{
						for(int l6 = i2 - k5; l6 <= i2 + k5; l6++)
							if(i6 >= 0 && l6 >= 0 && i6 < 104 && l6 < 104 && anIntArrayArray825[i6][l6] < i5)
							{
								i5 = anIntArrayArray825[i6][l6];
								j3 = i6;
								k3 = l6;
								anInt1264 = 1;
								flag1 = true;
							}

					}

					if(flag1)
						break;
				}

			}
			if(!flag1)
				return false;
		}
		i4 = 0;
		bigX[i4] = j3;
		bigY[i4++] = k3;
		int l5;
		for(int j5 = l5 = anIntArrayArray901[j3][k3]; j3 != j2 || k3 != j1; j5 = anIntArrayArray901[j3][k3])
		{
			if(j5 != l5)
			{
				l5 = j5;
				bigX[i4] = j3;
				bigY[i4++] = k3;
			}
			if((j5 & 2) != 0)
				j3++;
			else
			if((j5 & 8) != 0)
				j3--;
			if((j5 & 1) != 0)
				k3++;
			else
			if((j5 & 4) != 0)
				k3--;
		}
//	if(cancelWalk) { return i4 > 0; }
	

		if(i4 > 0)
		{
			int k4 = i4;
			if(k4 > 25)
				k4 = 25;
			i4--;
			int k6 = bigX[i4];
			int i7 = bigY[i4];
			anInt1288 += k4;
			if(anInt1288 >= 92)
			{
				stream.createFrame(36);
				stream.writeDWord(0);
				anInt1288 = 0;
			}
			if(i == 0)
			{
				stream.createFrame(164);
				stream.writeWordBigEndian(k4 + k4 + 3);
			}
			if(i == 1)
			{
				stream.createFrame(248);
				stream.writeWordBigEndian(k4 + k4 + 3 + 14);
			}
			if(i == 2)
			{
				stream.createFrame(98);
				stream.writeWordBigEndian(k4 + k4 + 3);
			}
			stream.method433(k6 + baseX);
			destX = bigX[0];
			destY = bigY[0];
			for(int j7 = 1; j7 < k4; j7++)
			{
				i4--;
				stream.writeWordBigEndian(bigX[i4] - k6);
				stream.writeWordBigEndian(bigY[i4] - i7);
			}

			stream.method431(i7 + baseY);
			stream.method424(super.keyArray[5] != 1 ? 0 : 1);
			return true;
		}
		return i != 1;
	}

	private void method86(Stream stream)
	{
		for(int j = 0; j < anInt893; j++)
		{
			int k = anIntArray894[j];
			NPC npc = npcArray[k];
			int l = stream.readUnsignedByte();
			if((l & 0x10) != 0)
			{
				int i1 = stream.method434();
				if(i1 == 65535)
					i1 = -1;
				int i2 = stream.readUnsignedByte();
				if(i1 == npc.anim && i1 != -1)
				{
					int l2 = Animation.anims[i1].anInt365;
					if(l2 == 1)
					{
						npc.anInt1527 = 0;
						npc.anInt1528 = 0;
						npc.anInt1529 = i2;
						npc.anInt1530 = 0;
					}
					if(l2 == 2)
						npc.anInt1530 = 0;
				} else
				if(i1 == -1 || npc.anim == -1 || Animation.anims[i1].anInt359 >= Animation.anims[npc.anim].anInt359)
				{
					npc.anim = i1;
					npc.anInt1527 = 0;
					npc.anInt1528 = 0;
					npc.anInt1529 = i2;
					npc.anInt1530 = 0;
					npc.anInt1542 = npc.smallXYIndex;
				}
			}
 	    if ((l & 8) != 0) {
		int j1 = stream.method426();
		int j2 = stream.method427();
		int type1 = stream.readUnsignedByte();
		npc.updateHitData(j2, j1, type1, loopCycle);
		npc.loopCycleStatus = loopCycle + 300;
		npc.currentHealth = stream.method426();
		npc.maxHealth = stream.readUnsignedByte();
	    }
			if((l & 0x80) != 0)
			{
				npc.anInt1520 = stream.readUnsignedWord();
				int k1 = stream.readDWord();
				npc.anInt1524 = k1 >> 16;
				npc.anInt1523 = loopCycle + (k1 & 0xffff);
				npc.anInt1521 = 0;
				npc.anInt1522 = 0;
				if(npc.anInt1523 > loopCycle)
					npc.anInt1521 = -1;
				if(npc.anInt1520 == 65535)
					npc.anInt1520 = -1;
			}
			if((l & 0x20) != 0)
			{
				npc.interactingEntity = stream.readUnsignedWord();
				if(npc.interactingEntity == 65535)
					npc.interactingEntity = -1;
			}
			if((l & 1) != 0)
			{
				npc.textSpoken = stream.readString();
				npc.textCycle = 100;
//	entityMessage(npc);
	
			}
 	    if ((l & 0x40) != 0) {
		int l1 = stream.method427();
		int k2 = stream.method428();
		int type2 = stream.readUnsignedByte();
		npc.updateHitData(k2, l1, type2, loopCycle);
		npc.loopCycleStatus = loopCycle + 300;
		npc.currentHealth = stream.method428();
		npc.maxHealth = stream.method427();
	    }
			if((l & 2) != 0)
			{
				npc.desc = EntityDef.forID(stream.method436());
				npc.anInt1540 = npc.desc.aByte68;
				npc.anInt1504 = npc.desc.anInt79;
				npc.anInt1554 = npc.desc.anInt67;
				npc.anInt1555 = npc.desc.anInt58;
				npc.anInt1556 = npc.desc.anInt83;
				npc.anInt1557 = npc.desc.anInt55;
				npc.anInt1511 = npc.desc.anInt77;
			}
			if((l & 4) != 0)
			{
				npc.anInt1538 = stream.method434();
				npc.anInt1539 = stream.method434();
			}
		}
	}

	private void buildAtNPCMenu(EntityDef entityDef, int i, int j, int k)
	{
		if(menuActionRow >= 400)
			return;
		if(entityDef.childrenIDs != null)
			entityDef = entityDef.method161();
		if(entityDef == null)
			return;
		if(!entityDef.aBoolean84)
			return;
		String s = entityDef.name;
		if(entityDef.combatLevel != 0)
			s = s + combatDiffColor(myPlayer.combatLevel, entityDef.combatLevel) + " (level-" + entityDef.combatLevel + ")";
		if(itemSelected == 1)
		{
			menuActionName[menuActionRow] = "Use " + selectedItemName + " with @yel@" + s;
			menuActionID[menuActionRow] = 582;
			menuActionCmd1[menuActionRow] = i;
			menuActionCmd2[menuActionRow] = k;
			menuActionCmd3[menuActionRow] = j;
			menuActionRow++;
			return;
		}
		if(spellSelected == 1)
		{
			if((spellUsableOn & 2) == 2)
			{
				menuActionName[menuActionRow] = spellTooltip + " @yel@" + s;
				menuActionID[menuActionRow] = 413;
				menuActionCmd1[menuActionRow] = i;
				menuActionCmd2[menuActionRow] = k;
				menuActionCmd3[menuActionRow] = j;
				menuActionRow++;
			}
		} else
		{
			if(entityDef.itemActions != null)
			{
				for(int l = 4; l >= 0; l--)
					if(entityDef.itemActions[l] != null && !entityDef.itemActions[l].equalsIgnoreCase("attack"))
					{
						menuActionName[menuActionRow] = entityDef.itemActions[l] + " @yel@" + s;
						if(l == 0)
							menuActionID[menuActionRow] = 20;
						if(l == 1)
							menuActionID[menuActionRow] = 412;
						if(l == 2)
							menuActionID[menuActionRow] = 225;
						if(l == 3)
							menuActionID[menuActionRow] = 965;
						if(l == 4)
							menuActionID[menuActionRow] = 478;
						menuActionCmd1[menuActionRow] = i;
						menuActionCmd2[menuActionRow] = k;
						menuActionCmd3[menuActionRow] = j;
						menuActionRow++;
					}

			}
			if(entityDef.itemActions != null)
			{
				for(int i1 = 4; i1 >= 0; i1--)
					if(entityDef.itemActions[i1] != null && entityDef.itemActions[i1].equalsIgnoreCase("attack"))
					{
						char c = '\0';
						if(entityDef.combatLevel > myPlayer.combatLevel)
							c = '\u07D0';
						menuActionName[menuActionRow] = entityDef.itemActions[i1] + " @yel@" + s;
						if(i1 == 0)
							menuActionID[menuActionRow] = 20 + c;
						if(i1 == 1)
							menuActionID[menuActionRow] = 412 + c;
						if(i1 == 2)
							menuActionID[menuActionRow] = 225 + c;
						if(i1 == 3)
							menuActionID[menuActionRow] = 965 + c;
						if(i1 == 4)
							menuActionID[menuActionRow] = 478 + c;
						menuActionCmd1[menuActionRow] = i;
						menuActionCmd2[menuActionRow] = k;
						menuActionCmd3[menuActionRow] = j;
						menuActionRow++;
					}

			}
			if(idToggle == true) {
				menuActionName[menuActionRow] = "Examine @yel@" + s + " @gre@(@whi@" + entityDef.interfaceType + "@gre@)";
			} else {
				menuActionName[menuActionRow] = "Examine @yel@" + s;
			}
			menuActionID[menuActionRow] = 1025;
			menuActionCmd1[menuActionRow] = i;
			menuActionCmd2[menuActionRow] = k;
			menuActionCmd3[menuActionRow] = j;
			menuActionRow++;
		}
	}

	private void buildAtPlayerMenu(int i, int j, Player player, int k)
	{
		if(player == myPlayer)
			return;
		if(menuActionRow >= 400)
			return;
		String s;
		if (player.skill == 0) {
			s = player.name
					+ combatDiffColor(myPlayer.combatLevel, player.combatLevel)
					+ " (level-" + player.combatLevel + ")";
		} else {
			s = "@or2@" + getRank(player.skill) + "@whi@ " + player.name //CLIQUE DROITE
					+ combatDiffColor(myPlayer.combatLevel, player.combatLevel)
					+ " (level-" + player.combatLevel + ")";
		}
		if(itemSelected == 1)
		{
			menuActionName[menuActionRow] = "Use " + selectedItemName + " -> @whi@" + s;
			menuActionID[menuActionRow] = 491;
			menuActionCmd1[menuActionRow] = j;
			menuActionCmd2[menuActionRow] = i;
			menuActionCmd3[menuActionRow] = k;
			menuActionRow++;
		} else
		if(spellSelected == 1)
		{
			if((spellUsableOn & 8) == 8)
			{
				menuActionName[menuActionRow] = spellTooltip + " @whi@" + s;
				menuActionID[menuActionRow] = 365;
				menuActionCmd1[menuActionRow] = j;
				menuActionCmd2[menuActionRow] = i;
				menuActionCmd3[menuActionRow] = k;
				menuActionRow++;
			}
		} else
		{
			for(int l = 4; l >= 0; l--)
				if(atPlayerActions[l] != null)
				{
					menuActionName[menuActionRow] = atPlayerActions[l] + " @whi@" + s;
					char c = '\0';
					if(atPlayerActions[l].equalsIgnoreCase("attack"))
					{
						if(player.combatLevel > myPlayer.combatLevel)
							c = '\u07D0';
						if(myPlayer.team != 0 && player.team != 0)
							if(myPlayer.team == player.team)
								c = '\u07D0';
							else
								c = '\0';
					} else
					if(atPlayerArray[l])
						c = '\u07D0';
					if(l == 0)
						menuActionID[menuActionRow] = 561 + c;
					if(l == 1)
						menuActionID[menuActionRow] = 779 + c;
					if(l == 2)
						menuActionID[menuActionRow] = 27 + c;
					if(l == 3)
						menuActionID[menuActionRow] = 577 + c;
					if(l == 4)
						menuActionID[menuActionRow] = 729 + c;
					menuActionCmd1[menuActionRow] = j;
					menuActionCmd2[menuActionRow] = i;
					menuActionCmd3[menuActionRow] = k;
					menuActionRow++;
				}

		}
		for(int i1 = 0; i1 < menuActionRow; i1++)
			if(menuActionID[i1] == 516)
			{
				menuActionName[i1] = "Walk here @whi@" + s;
				return;
			}

	}

	private void method89(Class30_Sub1 class30_sub1)
	{
		int i = 0;
		int j = -1;
		int k = 0;
		int l = 0;
		if(class30_sub1.anInt1296 == 0)
			i = worldController.method300(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		if(class30_sub1.anInt1296 == 1)
			i = worldController.method301(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		if(class30_sub1.anInt1296 == 2)
			i = worldController.method302(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		if(class30_sub1.anInt1296 == 3)
			i = worldController.method303(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298);
		if(i != 0)
		{
			int i1 = worldController.method304(class30_sub1.anInt1295, class30_sub1.anInt1297, class30_sub1.anInt1298, i);
			j = i >> 14 & 0x7fff;
			k = i1 & 0x1f;
			l = i1 >> 6;
		}
		class30_sub1.anInt1299 = j;
		class30_sub1.anInt1301 = k;
		class30_sub1.anInt1300 = l;
	}

	private void method90()
	{
		for(int i = 0; i < anInt1062; i++)
			if(anIntArray1250[i] <= 0)
			{
				boolean flag1 = false;
				try
				{
					if(anIntArray1207[i] == anInt874 && anIntArray1241[i] == anInt1289)
					{
						if(!replayWave())
							flag1 = true;
					} else
					{
						Stream stream = Sounds.method241(anIntArray1241[i], anIntArray1207[i]);
						if(System.currentTimeMillis() + (long)(stream.currentOffset / 22) > aLong1172 + (long)(anInt1257 / 22))
						{
							anInt1257 = stream.currentOffset;
							aLong1172 = System.currentTimeMillis();
							if(saveWave(stream.buffer, stream.currentOffset))
							{
								anInt874 = anIntArray1207[i];
								anInt1289 = anIntArray1241[i];
							} else
							{
								flag1 = true;
							}
						}
					}
				}
				catch(Exception exception) { }
				if(!flag1 || anIntArray1250[i] == -5)
				{
					anInt1062--;
					for(int j = i; j < anInt1062; j++)
					{
						anIntArray1207[j] = anIntArray1207[j + 1];
						anIntArray1241[j] = anIntArray1241[j + 1];
						anIntArray1250[j] = anIntArray1250[j + 1];
					}

					i--;
				} else
				{
					anIntArray1250[i] = -5;
				}
			} else
			{
				anIntArray1250[i]--;
			}

		if(prevSong > 0)
		{
			prevSong -= 20;
			if(prevSong < 0)
				prevSong = 0;
			if(prevSong == 0 && musicEnabled && !lowMem)
			{
				nextSong = currentSong;
				songChanging = true;
				onDemandFetcher.method558(2, nextSong);
			}
		}
	}

   /* public static void compress(int ID) {
        try {
            String inF = "C:/DSPK/gzip/"+ ID +".dat";
            String outF = "C:/DSPK/gzip/"+ ID;
            File inF2 = new File(inF);
            File outF2 = new File(outF);
            System.out.println("GZipping: " + inF2 + " file");
            FileOutputStream FOS = new FileOutputStream(outF2 + ".gz");
            GZIPOutputStream GZOS = new GZIPOutputStream(FOS);
            FileInputStream FIN = new FileInputStream(inF2);
            BufferedInputStream IN = new BufferedInputStream(FIN);
            byte[] buffer = new byte[1024];
            int i;
            while ((i = IN.read(buffer)) >= 0) {
                GZOS.write(buffer, 0, i);
            }
            System.out.println("File has been GZipped succesfully");
            IN.close();
            GZOS.close();
        }
        catch(IOException e) {
            System.out.println("Error: " + e);
        }
        compress(ID + 1);
    }    
    void main() {
        compress(29);
    }*/


	void startUp()
	{
//main();
//models();
		drawLoadingText(20, "Starting up");
		new CacheDownloader(this).downloadCache();
		setNewMaps();
		if(signlink.sunjava)
			super.minDelay = 5;
		aBoolean993 = true;
		String s = getDocumentBaseHost();
		if(signlink.cache_dat != null) {
			for(int i = 0; i < 5; i++)
				decompressors[i] = new Decompressor(signlink.cache_dat, signlink.cache_idx[i], i + 1);
		}
		try {
			titleStreamLoader = streamLoaderForName(1, "title screen", "title", expectedCRCs[1], 25);
			smallText = new RSFont(false, "p11_full", titleStreamLoader);
			aTextDrawingArea_1271 = new RSFont(false, "p12_full", titleStreamLoader);
			chatTextDrawingArea = new RSFont(false, "b12_full", titleStreamLoader);
			RSFont aTextDrawingArea_1273 = new RSFont(true, "q8_full", titleStreamLoader);
			
			newSmallFont = new TextDrawingArea(false, "p11_full", titleStreamLoader);
            newRegularFont = new TextDrawingArea(false, "p12_full", titleStreamLoader);
            newBoldFont = new TextDrawingArea(false, "b12_full", titleStreamLoader);
            newFancyFont = new TextDrawingArea(true, "q8_full", titleStreamLoader);
            //UserInterface.fonts = newFonts;
            newSmallFont.unpackChatImages(chatImages);
            newRegularFont.unpackChatImages(chatImages);
            newBoldFont.unpackChatImages(chatImages);
            newFancyFont.unpackChatImages(chatImages);
			
			drawLogo();
			loadTitleScreen();
			NamedArchive archive = streamLoaderForName(2, "config", "config", expectedCRCs[2], 30);
			NamedArchive streamLoader_1 = streamLoaderForName(3, "interface", "interface", expectedCRCs[3], 35);
			NamedArchive mediaArchive = streamLoaderForName(4, "2d graphics", "media", expectedCRCs[4], 40);
			NamedArchive streamLoader_3 = streamLoaderForName(6, "textures", "textures", expectedCRCs[6], 45);
			NamedArchive streamLoader_4 = streamLoaderForName(7, "chat system", "wordenc", expectedCRCs[7], 50);
			NamedArchive streamLoader_5 = streamLoaderForName(8, "sound effects", "sounds", expectedCRCs[8], 55);
			byteGroundArray = new byte[4][104][104];
			intGroundArray = new int[4][105][105];
			worldController = new WorldController(intGroundArray);
			for(int j = 0; j < 4; j++)
				aClass11Array1230[j] = new Class11();
			aSprite_1263 = new Sprite(512, 512);
			NamedArchive streamLoader_6 = streamLoaderForName(5, "update list", "versionlist", expectedCRCs[5], 60);
			drawLoadingText(60, "Unpacking Media");
			onDemandFetcher = new OnDemandFetcher();
			onDemandFetcher.start(streamLoader_6, this);
			Class36.method528(onDemandFetcher.getAnimCount());
			Model.method459(onDemandFetcher.getModelCount(), onDemandFetcher);
			/* All 525 Models */
			ModelDecompressor.hdgfx();
			System.out.println("Loaded 525 graphics...");
			/* 525 End */
			ModelDecompressor.models();
			System.out.println("Loaded Compressed Models..");
			drawLoadingText(70, "Loading Models..");
			ModelDecompressor.loadModelDataFile();
			drawLoadingText(80, "Unpacking Models..");
						preloadModels();
			DataBase.loadAnimations();
			
			/* Custom sprite unpacking */
			loadExtraSprites();
loadingPleaseWait = new Sprite("loadingPleaseWait");
reestablish = new Sprite("reestablish");
                hitMarks[20] = new Sprite("hitMark");
                hitMarks[21] = new Sprite("hitMark2");
                hitMarks[22] = new Sprite("hitMark3");
                hitMarks[23] = new Sprite("hitMark4");
			compass = new Sprite(mediaArchive, "compass", 0);
			mapArea = new Sprite("maparea");
			multiOverlay = new Sprite(mediaArchive, "overlay_multiway", 0);
			HPBarFull = new Sprite(sign.signlink.findcachedir() + "Sprites/Player/HP 0.PNG", 1);
			HPBarEmpty = new Sprite(sign.signlink.findcachedir() + "Sprites/Player/HP 1.PNG", 1);
			/**/
			
			mapBack = new Background(mediaArchive, "mapback", 0);
 		for(int i = 0; i <= 3; i++) {
			combatIcons[i] = new Sprite("Player/combatIcon "+i+"");
		}
			
			for(int c1 = 0; c1 <= 3; c1++)
				chatButtons[c1] = new Sprite(mediaArchive, "chatbuttons", c1);
				
			for(int j3 = 0; j3 <= 13; j3++)
				sideIcons[j3] = new Sprite(mediaArchive, "sideicons", j3);
				
			for(int j3 = 0; j3 <= 13; j3++)
				sIcons483[j3] = new Sprite("Gameframe/SIcons/483/"+j3+"");
				
			for(int j3 = 0; j3 <= 12; j3++)
				sIcons459[j3] = new Sprite("Gameframe/SIcons/459/SIDEICONS "+j3+"");
			
			for(int nSI = 0; nSI <= 15; nSI++)
                newSideIcons[nSI] = new Sprite("Gameframe/SIcons/562/icon "+nSI);
			
				tabHover = new Sprite("Gameframe/SIcons/562/tabhover");
				tabClicked = new Sprite("Gameframe/SIcons/562/tabclicked1");
				
			for(int i = 0; i <= 3; i++) {
			combatIcons[i] = new Sprite("Player/combatIcon "+i+"");
		}
				
			for(int r1 = 0; r1 < 15; r1++)
				redStones[r1] = new Sprite("redstones " + r1);
			mapEdge = new Sprite(mediaArchive, "mapedge", 0);
			mapEdge.method345();
			try
			{
				for(int k3 = 0; k3 < 100; k3++)
					mapScenes[k3] = new Background(mediaArchive, "mapscene", k3);
			}
			catch(Exception _ex) { }
			try
			{
				for(int l3 = 0; l3 < 100; l3++)
					mapFunctions[l3] = new Sprite(mediaArchive, "mapfunction", l3);
			}
			catch(Exception _ex) { }
			try
			{
				for(int i4 = 0; i4 < 20; i4++)
					hitMarks[i4] = new Sprite(mediaArchive, "hitmarks", i4);
			}
			catch(Exception _ex) { }
			try
			{
				for(int h1 = 0; h1 < 6; h1++)
					headIconsHint[h1] = new Sprite(mediaArchive, "headicons_hint", h1);
			} catch(Exception _ex) { }
			try {
				for(int j4 = 0; j4 < 8; j4++)
					headIcons[j4] = new Sprite(mediaArchive, "headicons_prayer", j4);
				for(int idx = 0; idx < 18; idx++)
					headIcons[idx] = new Sprite("Player/Prayer/Prayer "+idx);
				for(int j45 = 0; j45 < 3; j45++)
					skullIcons[j45] = new Sprite(mediaArchive, "headicons_pk", j45 );
			}
			catch(Exception _ex) { }
			mapFlag = new Sprite(mediaArchive, "mapmarker", 0);
			mapMarker = new Sprite(mediaArchive, "mapmarker", 1);
			for(int k4 = 0; k4 < 8; k4++)
				crosses[k4] = new Sprite(mediaArchive, "cross", k4);

			mapDotItem = new Sprite(mediaArchive, "mapdots", 0);
			mapDotNPC = new Sprite(mediaArchive, "mapdots", 1);
			mapDotPlayer = new Sprite(mediaArchive, "mapdots", 2);
			mapDotFriend = new Sprite(mediaArchive, "mapdots", 3);
			mapDotTeam = new Sprite(mediaArchive, "mapdots", 4);
			mapDotClan = new Sprite(mediaArchive, "mapdots", 5);
			
			scrollBar1 = new Sprite(mediaArchive, "scrollbar", 0);
			scrollBar2 = new Sprite(mediaArchive, "scrollbar", 1);
			alertBack = new Sprite("alertback");
			alertBorder = new Sprite("alertborder");
			alertBorderH = new Sprite("alertborderh");
			scrollBar3 = new Sprite("Gameframe/SCROLLBAR 0");
			scrollBar4 = new Sprite("Gameframe/SCROLLBAR 1");
			
			Sprite sprite = new Sprite(mediaArchive, "screenframe", 0);
			leftFrame = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new Sprite(mediaArchive, "screenframe", 1);
			topFrame = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new Sprite(mediaArchive, "screenframe", 2);
			rightFrame = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);
			sprite = new Sprite(mediaArchive, "mapedge", 0);
			mapEdgeIP = new RSImageProducer(sprite.myWidth, sprite.myHeight, getGameComponent());
			sprite.method346(0, 0);

			int i5 = (int)(Math.random() * 21D) - 10;
			int j5 = (int)(Math.random() * 21D) - 10;
			int k5 = (int)(Math.random() * 21D) - 10;
			int l5 = (int)(Math.random() * 41D) - 20;
			for(int i6 = 0; i6 < 100; i6++)
			{
				if(mapFunctions[i6] != null)
					mapFunctions[i6].method344(i5 + l5, j5 + l5, k5 + l5);
				if(mapScenes[i6] != null)
					mapScenes[i6].method360(i5 + l5, j5 + l5, k5 + l5);
			}
			drawLoadingText(83, "Unpacking textures");
			Texture.method368(streamLoader_3);
			Texture.method372(0.80000000000000004D);
			Texture.method367();
			drawLoadingText(86, "Unpacking config");
			try {
				Animation.unpackConfig(archive);
				ObjectDef.unpackConfig(archive);
				Flo.unpackConfig(archive);
				ItemDef.unpackConfig(archive);
				EntityDef.unpackConfig(archive);
				IdentityKit.unpackConfig(archive);
 				SpotAnim.unpackConfig(archive);
				Varp.unpackConfig(archive);
				VarBit.unpackConfig(archive);
				ItemDef.isMembers = true;
			} catch(Exception e) {
				e.printStackTrace();
			}
			drawLoadingText(95, "Unpacking interfaces");
			RSFont aclass30_sub2_sub1_sub4s[] = {
				smallText, aTextDrawingArea_1271, chatTextDrawingArea, aTextDrawingArea_1273
			};
			RSInterface.fonts = aclass30_sub2_sub1_sub4s;
			RSInterface.unpack(streamLoader_1, aclass30_sub2_sub1_sub4s, mediaArchive);
			drawLoadingText(100, "Preparing game engine");
			for(int j6 = 0; j6 < 33; j6++)
			{
				int k6 = 999;
				int i7 = 0;
				for(int k7 = 0; k7 < 34; k7++)
				{
					if(mapBack.aByteArray1450[k7 + j6 * mapBack.anInt1452] == 0)
					{
						if(k6 == 999)
							k6 = k7;
						continue;
					}
					if(k6 == 999)
						continue;
					i7 = k7;
					break;
				}

				anIntArray968[j6] = k6;
				anIntArray1057[j6] = i7 - k6;
			}
			for (int l6 = 1; l6 < 153; l6++) {
                int j7 = 999;
                int l7 = 0;
                for (int j8 = 24; j8 < 177; j8++) {
                    if (mapBack.aByteArray1450[j8 + l6 * mapBack.anInt1452] == 0 && (j8 > 34 || l6 > 34)) {
                        if (j7 == 999) {
                            j7 = j8;
                        }
                        continue;
                    }
                    if (j7 == 999) {
                        continue;
                    }
                    l7 = j8;
                    break;
                }

                anIntArray1052[l6 - 1] = j7 - 24;
                anIntArray1229[l6 - 1] = l7 - j7;
            }
			Texture.method365(765, 503);
			fullScreenTextureArray = Texture.anIntArray1472;
			Texture.method365(516, 165);//519
			anIntArray1180 = Texture.anIntArray1472;
			Texture.method365(250, 335);
			anIntArray1181 = Texture.anIntArray1472;
			Texture.method365(512, 334);//512 334
			anIntArray1182 = Texture.anIntArray1472;
			
			int ai[] = new int[9];
			for(int i8 = 0; i8 < 9; i8++)
			{
				int k8 = 128 + i8 * 32 + 15;
				int l8 = 600 + k8 * 3;
				int i9 = Texture.anIntArray1470[k8];
				ai[i8] = l8 * i9 >> 16;
			}

			WorldController.method310(500, 800, 512, 334, ai);
			
			Censor.loadConfig(streamLoader_4);
			mouseDetection = new MouseDetection(this);
			startRunnable(mouseDetection, 10);
			Animable_Sub5.clientInstance = this;
			ObjectDef.clientInstance = this;
			EntityDef.clientInstance = this;
			return;
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			signlink.reporterror("loaderror " + aString1049 + " " + anInt1079);
		}
		loadingError = true;
	}

	private void method91(Stream stream, int i)
	{
		while(stream.bitPosition + 10 < i * 8)
		{
			int j = stream.readBits(11);
			if(j == 2047)
				break;
			if(playerArray[j] == null)
			{
				playerArray[j] = new Player();
				if(aStreamArray895s[j] != null)
					playerArray[j].updatePlayer(aStreamArray895s[j]);
			}
			playerIndices[playerCount++] = j;
			Player player = playerArray[j];
			player.anInt1537 = loopCycle;
			int k = stream.readBits(1);
			if(k == 1)
				anIntArray894[anInt893++] = j;
			int l = stream.readBits(1);
			int i1 = stream.readBits(5);
			if(i1 > 15)
				i1 -= 32;
			int j1 = stream.readBits(5);
			if(j1 > 15)
				j1 -= 32;
			player.setPos(myPlayer.smallX[0] + j1, myPlayer.smallY[0] + i1, l == 1);
		}
		stream.finishBitAccess();
	}

	private void processMainScreenClick() { 
		if(anInt1021 != 0)
			return;
		if(super.clickMode3 == 1) {
			if(is480 == true || is508 == true || is525 == true || is562 == true) {
				int i = super.saveClickX - 25 - 530;//
				int j = super.saveClickY - 8;
			if(i >= 0 && j >= 0 && i < 146 && j < 151) {
				i -= 73;
				j -= 75;
				int k = minimapInt1 + minimapInt2 & 0x7ff;
				int i1 = Texture.anIntArray1470[k];
				int j1 = Texture.anIntArray1471[k];
				i1 = i1 * (minimapInt3 + 256) >> 8;
				j1 = j1 * (minimapInt3 + 256) >> 8;
				int k1 = j * i1 + i * j1 >> 11;
				int l1 = j * j1 - i * i1 >> 11;
				int i2 = myPlayer.x + k1 >> 7;
				int j2 = myPlayer.y - l1 >> 7;
				boolean flag1 = doWalkTo(1, 0, 0, 0, myPlayer.smallY[0], 0, 0, j2, myPlayer.smallX[0], true, i2);
				if(flag1) {
					stream.writeWordBigEndian(i);
					stream.writeWordBigEndian(j);
					stream.writeWord(minimapInt1);
					stream.writeWordBigEndian(57);
					stream.writeWordBigEndian(minimapInt2);
					stream.writeWordBigEndian(minimapInt3);
					stream.writeWordBigEndian(89);
					stream.writeWord(myPlayer.x);
					stream.writeWord(myPlayer.y);
					stream.writeWordBigEndian(anInt1264);
					stream.writeWordBigEndian(63);
				}
			}
			} else {
				int i = super.saveClickX - 25 - 545;//545
				int j = super.saveClickY - 5 - 4;
				if(i >= 0 && j >= 0 && i < 146 && j < 151) {
					i -= 73;
					j -= 75;
					int k = minimapInt1 + minimapInt2 & 0x7ff;
					int i1 = Texture.anIntArray1470[k];
					int j1 = Texture.anIntArray1471[k];
					i1 = i1 * (minimapInt3 + 256) >> 8;
					j1 = j1 * (minimapInt3 + 256) >> 8;
					int k1 = j * i1 + i * j1 >> 11;
					int l1 = j * j1 - i * i1 >> 11;
					int i2 = myPlayer.x + k1 >> 7;
					int j2 = myPlayer.y - l1 >> 7;
					boolean flag1 = doWalkTo(1, 0, 0, 0, myPlayer.smallY[0], 0, 0, j2, myPlayer.smallX[0], true, i2);
					if(flag1) {
						stream.writeWordBigEndian(i);
						stream.writeWordBigEndian(j);
						stream.writeWord(minimapInt1);
						stream.writeWordBigEndian(57);
						stream.writeWordBigEndian(minimapInt2);
						stream.writeWordBigEndian(minimapInt3);
						stream.writeWordBigEndian(89);
						stream.writeWord(myPlayer.x);
						stream.writeWord(myPlayer.y);
						stream.writeWordBigEndian(anInt1264);
						stream.writeWordBigEndian(63);
					}
				}
			}
			anInt1117++;
			if(anInt1117 > 1151) {
				anInt1117 = 0;
				stream.createFrame(246);
				stream.writeWordBigEndian(0);
				int l = stream.currentOffset;
				if((int)(Math.random() * 2D) == 0)
					stream.writeWordBigEndian(101);
				stream.writeWordBigEndian(197);
				stream.writeWord((int)(Math.random() * 65536D));
				stream.writeWordBigEndian((int)(Math.random() * 256D));
				stream.writeWordBigEndian(67);
				stream.writeWord(14214);
				if((int)(Math.random() * 2D) == 0)
					stream.writeWord(29487);
				stream.writeWord((int)(Math.random() * 65536D));
				if((int)(Math.random() * 2D) == 0)
					stream.writeWordBigEndian(220);
				stream.writeWordBigEndian(180);
				stream.writeBytes(stream.currentOffset - l);
			}
		}
	}

	private String interfaceIntToString(int j) {
		if(j < 0x3b9ac9ff)
			return String.valueOf(j);
		else
			return "*";
	}

	private void showErrorScreen()
	{
		Graphics g = getGameComponent().getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, 765, 503);
		method4(1);
		if(loadingError)
		{
			aBoolean831 = false;
			g.setFont(new Font("Helvetica", 1, 16));
			g.setColor(Color.yellow);
			int k = 35;
			g.drawString("Sorry, an error has occured whilst loading Hackerscape.", 30, k);
			k += 50;
			g.setColor(Color.white);
			g.drawString("To fix this try the following (in order):", 30, k);
			k += 50;
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", 1, 12));
			g.drawString("1: Try closing and reopening the client and/or web browser", 30, k);
			k += 30;
			g.drawString("2: Try clearing your web-browsers cache from tools->internet options", 30, k);
			k += 30;
			g.drawString("3: Try rebooting your computer", 30, k);
			k += 30;
			g.drawString(" ", 30, k);
			k += 30;
			g.drawString("If problems still occur, visit the forums at website here and request help", 30, k);
		}
		if(genericLoadingError)
		{
			aBoolean831 = false;
			g.setFont(new Font("Helvetica", 1, 20));
			g.setColor(Color.white);
			g.drawString("Error - unable to load game!", 50, 50);
			g.drawString("To play Hackerscape make sure you play from", 50, 100);
			g.drawString("Link here", 50, 150);
		}
		if(rsAlreadyLoaded)
		{
			aBoolean831 = false;
			g.setColor(Color.yellow);
			int l = 35;
			g.drawString("Error a copy of Hackerscape already appears to be loaded", 30, l);
			l += 50;
			g.setColor(Color.white);
			g.drawString("To fix this try the following (in order):", 30, l);
			l += 50;
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", 1, 12));
			g.drawString("1: Try closing ALL open web-browser windows, and reloading", 30, l);
			l += 30;
			g.drawString("2: Try rebooting your computer, and reloading", 30, l);
			l += 30;
		}
	}

	public URL getCodeBase() { 
		 
				
		try {
			return new URL(server +":" + (80 + portOff));
		} catch(Exception _ex) {
		}
		return null;
	}

	private void method95() {
		for(int j = 0; j < npcCount; j++) {
			int k = npcIndices[j];
			NPC npc = npcArray[k];
			if(npc != null)
				method96(npc);
		}
	}

	private void method96(Entity entity)
	{
		if(entity.x < 128 || entity.y < 128 || entity.x >= 13184 || entity.y >= 13184)
		{
			entity.anim = -1;
			entity.anInt1520 = -1;
			entity.anInt1547 = 0;
			entity.anInt1548 = 0;
			entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
			entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
			entity.method446();
		}
		if(entity == myPlayer && (entity.x < 1536 || entity.y < 1536 || entity.x >= 11776 || entity.y >= 11776))
		{
			entity.anim = -1;
			entity.anInt1520 = -1;
			entity.anInt1547 = 0;
			entity.anInt1548 = 0;
			entity.x = entity.smallX[0] * 128 + entity.anInt1540 * 64;
			entity.y = entity.smallY[0] * 128 + entity.anInt1540 * 64;
			entity.method446();
		}
		if(entity.anInt1547 > loopCycle)
			method97(entity);
		else
		if(entity.anInt1548 >= loopCycle)
			method98(entity);
		else
			method99(entity);
		method100(entity);
		method101(entity);
	}

	private void method97(Entity entity)
	{
		int i = entity.anInt1547 - loopCycle;
		int j = entity.anInt1543 * 128 + entity.anInt1540 * 64;
		int k = entity.anInt1545 * 128 + entity.anInt1540 * 64;
		entity.x += (j - entity.x) / i;
		entity.y += (k - entity.y) / i;
		entity.anInt1503 = 0;
		if(entity.anInt1549 == 0)
			entity.turnDirection = 1024;
		if(entity.anInt1549 == 1)
			entity.turnDirection = 1536;
		if(entity.anInt1549 == 2)
			entity.turnDirection = 0;
		if(entity.anInt1549 == 3)
			entity.turnDirection = 512;
	}

	private void method98(Entity entity)
	{
		if(entity.anInt1548 == loopCycle || entity.anim == -1 || entity.anInt1529 != 0 || entity.anInt1528 + 1 > Animation.anims[entity.anim].method258(entity.anInt1527))
		{
			int i = entity.anInt1548 - entity.anInt1547;
			int j = loopCycle - entity.anInt1547;
			int k = entity.anInt1543 * 128 + entity.anInt1540 * 64;
			int l = entity.anInt1545 * 128 + entity.anInt1540 * 64;
			int i1 = entity.anInt1544 * 128 + entity.anInt1540 * 64;
			int j1 = entity.anInt1546 * 128 + entity.anInt1540 * 64;
			entity.x = (k * (i - j) + i1 * j) / i;
			entity.y = (l * (i - j) + j1 * j) / i;
		}
		entity.anInt1503 = 0;
		if(entity.anInt1549 == 0)
			entity.turnDirection = 1024;
		if(entity.anInt1549 == 1)
			entity.turnDirection = 1536;
		if(entity.anInt1549 == 2)
			entity.turnDirection = 0;
		if(entity.anInt1549 == 3)
			entity.turnDirection = 512;
		entity.anInt1552 = entity.turnDirection;
	}

	private void method99(Entity entity)
	{
		entity.anInt1517 = entity.anInt1511;
		if(entity.smallXYIndex == 0)
		{
			entity.anInt1503 = 0;
			return;
		}
		if(entity.anim != -1 && entity.anInt1529 == 0)
		{
			Animation animation = Animation.anims[entity.anim];
			if(entity.anInt1542 > 0 && animation.anInt363 == 0)
			{
				entity.anInt1503++;
				return;
			}
			if(entity.anInt1542 <= 0 && animation.anInt364 == 0)
			{
				entity.anInt1503++;
				return;
			}
		}
		int i = entity.x;
		int j = entity.y;
		int k = entity.smallX[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
		int l = entity.smallY[entity.smallXYIndex - 1] * 128 + entity.anInt1540 * 64;
		if(k - i > 256 || k - i < -256 || l - j > 256 || l - j < -256)
		{
			entity.x = k;
			entity.y = l;
			return;
		}
		if(i < k)
		{
			if(j < l)
				entity.turnDirection = 1280;
			else
			if(j > l)
				entity.turnDirection = 1792;
			else
				entity.turnDirection = 1536;
		} else
		if(i > k)
		{
			if(j < l)
				entity.turnDirection = 768;
			else
			if(j > l)
				entity.turnDirection = 256;
			else
				entity.turnDirection = 512;
		} else
		if(j < l)
			entity.turnDirection = 1024;
		else
			entity.turnDirection = 0;
		int i1 = entity.turnDirection - entity.anInt1552 & 0x7ff;
		if(i1 > 1024)
			i1 -= 2048;
		int j1 = entity.anInt1555;
		if(i1 >= -256 && i1 <= 256)
			j1 = entity.anInt1554;
		else
		if(i1 >= 256 && i1 < 768)
			j1 = entity.anInt1557;
		else
		if(i1 >= -768 && i1 <= -256)
			j1 = entity.anInt1556;
		if(j1 == -1)
			j1 = entity.anInt1554;
		entity.anInt1517 = j1;
		int k1 = 4;
		if(entity.anInt1552 != entity.turnDirection && entity.interactingEntity == -1 && entity.anInt1504 != 0)
			k1 = 2;
		if(entity.smallXYIndex > 2)
			k1 = 6;
		if(entity.smallXYIndex > 3)
			k1 = 8;
		if(entity.anInt1503 > 0 && entity.smallXYIndex > 1)
		{
			k1 = 8;
			entity.anInt1503--;
		}
		if(entity.aBooleanArray1553[entity.smallXYIndex - 1])
			k1 <<= 1;
		if(k1 >= 8 && entity.anInt1517 == entity.anInt1554 && entity.anInt1505 != -1)
			entity.anInt1517 = entity.anInt1505;
		if(i < k)
		{
			entity.x += k1;
			if(entity.x > k)
				entity.x = k;
		} else
		if(i > k)
		{
			entity.x -= k1;
			if(entity.x < k)
				entity.x = k;
		}
		if(j < l)
		{
			entity.y += k1;
			if(entity.y > l)
				entity.y = l;
		} else
		if(j > l)
		{
			entity.y -= k1;
			if(entity.y < l)
				entity.y = l;
		}
		if(entity.x == k && entity.y == l)
		{
			entity.smallXYIndex--;
			if(entity.anInt1542 > 0)
				entity.anInt1542--;
		}
	}

	private void method100(Entity entity)
	{
		if(entity.anInt1504 == 0)
			return;
		if(entity.interactingEntity != -1 && entity.interactingEntity < 32768)
		{
			NPC npc = npcArray[entity.interactingEntity];
			if(npc != null)
			{
				int i1 = entity.x - npc.x;
				int k1 = entity.y - npc.y;
				if(i1 != 0 || k1 != 0)
					entity.turnDirection = (int)(Math.atan2(i1, k1) * 325.94900000000001D) & 0x7ff;
			}
		}
		if(entity.interactingEntity >= 32768)
		{
			int j = entity.interactingEntity - 32768;
			if(j == unknownInt10)
				j = myPlayerIndex;
			Player player = playerArray[j];
			if(player != null)
			{
				int l1 = entity.x - player.x;
				int i2 = entity.y - player.y;
				if(l1 != 0 || i2 != 0)
					entity.turnDirection = (int)(Math.atan2(l1, i2) * 325.94900000000001D) & 0x7ff;
			}
		}
		if((entity.anInt1538 != 0 || entity.anInt1539 != 0) && (entity.smallXYIndex == 0 || entity.anInt1503 > 0))
		{
			int k = entity.x - (entity.anInt1538 - baseX - baseX) * 64;
			int j1 = entity.y - (entity.anInt1539 - baseY - baseY) * 64;
			if(k != 0 || j1 != 0)
				entity.turnDirection = (int)(Math.atan2(k, j1) * 325.94900000000001D) & 0x7ff;
			entity.anInt1538 = 0;
			entity.anInt1539 = 0;
		}
		int l = entity.turnDirection - entity.anInt1552 & 0x7ff;
		if(l != 0)
		{
			if(l < entity.anInt1504 || l > 2048 - entity.anInt1504)
				entity.anInt1552 = entity.turnDirection;
			else
			if(l > 1024)
				entity.anInt1552 -= entity.anInt1504;
			else
				entity.anInt1552 += entity.anInt1504;
			entity.anInt1552 &= 0x7ff;
			if(entity.anInt1517 == entity.anInt1511 && entity.anInt1552 != entity.turnDirection)
			{
				if(entity.anInt1512 != -1)
				{
					entity.anInt1517 = entity.anInt1512;
					return;
				}
				entity.anInt1517 = entity.anInt1554;
			}
		}
	}

	private void method101(Entity entity)
	{
		entity.aBoolean1541 = false;
		if(entity.anInt1517 != -1)
		{
			Animation animation = Animation.anims[entity.anInt1517];
			entity.anInt1519++;
			if(entity.anInt1518 < animation.anInt352 && entity.anInt1519 > animation.method258(entity.anInt1518))
			{
				entity.anInt1519 = 0;
				entity.anInt1518++;
			}
			if(entity.anInt1518 >= animation.anInt352)
			{
				entity.anInt1519 = 0;
				entity.anInt1518 = 0;
			}
		}
		if(entity.anInt1520 != -1 && loopCycle >= entity.anInt1523)
		{
			if(entity.anInt1521 < 0)
				entity.anInt1521 = 0;
			Animation animation_1 = SpotAnim.cache[entity.anInt1520].aAnimation_407;
			for(entity.anInt1522++; entity.anInt1521 < animation_1.anInt352 && entity.anInt1522 > animation_1.method258(entity.anInt1521); entity.anInt1521++)//huhhhhh
				entity.anInt1522 -= animation_1.method258(entity.anInt1521);

			if(entity.anInt1521 >= animation_1.anInt352 && (entity.anInt1521 < 0 || entity.anInt1521 >= animation_1.anInt352))
				entity.anInt1520 = -1;
		}
		if(entity.anim != -1 && entity.anInt1529 <= 1)
		{
			Animation animation_2 = Animation.anims[entity.anim];
			if(animation_2.anInt363 == 1 && entity.anInt1542 > 0 && entity.anInt1547 <= loopCycle && entity.anInt1548 < loopCycle)
			{
				entity.anInt1529 = 1;
				return;
			}
		}
		if(entity.anim != -1 && entity.anInt1529 == 0)
		{
			Animation animation_3 = Animation.anims[entity.anim];
			for(entity.anInt1528++; entity.anInt1527 < animation_3.anInt352 && entity.anInt1528 > animation_3.method258(entity.anInt1527); entity.anInt1527++)
				entity.anInt1528 -= animation_3.method258(entity.anInt1527);

			if(entity.anInt1527 >= animation_3.anInt352)
			{
				entity.anInt1527 -= animation_3.anInt356;
				entity.anInt1530++;
				if(entity.anInt1530 >= animation_3.anInt362)
					entity.anim = -1;
				if(entity.anInt1527 < 0 || entity.anInt1527 >= animation_3.anInt352)
					entity.anim = -1;
			}
			entity.aBoolean1541 = animation_3.aBoolean358;
		}
		if(entity.anInt1529 > 0)
			entity.anInt1529--;
	}

	private void drawGameScreen()
	{
		if (fullscreenInterfaceID != -1 && (loadingStage == 2 || super.fullGameScreen != null)) {
			if (loadingStage == 2) {
				method119(anInt945, fullscreenInterfaceID);
				if (openInterfaceID != -1) {
					method119(anInt945, openInterfaceID);
				}
				anInt945 = 0;
				resetAllImageProducers();
				super.fullGameScreen.initDrawingArea();
				Texture.anIntArray1472 = fullScreenTextureArray;
				DrawingArea.setAllPixelsToZero();
				welcomeScreenRaised = true;
				if (openInterfaceID != -1) {
					RSInterface class9_1 = RSInterface.interfaceCache[openInterfaceID];
					if (class9_1.width == 512 && class9_1.height == 334 && class9_1.interfaceType == 0) {
						class9_1.width = 765;
						class9_1.height = 503;
					}
					drawInterface(0, 0, class9_1, 8);
				}
				RSInterface rsInterface = RSInterface.interfaceCache[fullscreenInterfaceID];
				if (rsInterface.width == 512 && rsInterface.height == 334 && rsInterface.interfaceType == 0) {
					rsInterface.width = 765;
					rsInterface.height = 503;
				}
				drawInterface(0, 0, rsInterface, 8);

				if (!menuOpen) {
					processRightClick();
					drawTooltip();
				} else {
					drawMenu(0, 0);
				}
			}
			drawCount++;
			super.fullGameScreen.drawGraphics(0, super.graphics, 0);
			return;
		} else {
			if (drawCount != 0) {
				resetImageProducers2();
			}
		}
		if(welcomeScreenRaised) {
			welcomeScreenRaised = false;
			topFrame.drawGraphics(0, super.graphics, 0);
			leftFrame.drawGraphics(4, super.graphics, 0);
			//rightFrame.drawGraphics(4, super.graphics, 516);
			//mapEdgeIP.drawGraphics(4, super.graphics, 516);//519
			needDrawTabArea = true;
			inputTaken = true;
			tabAreaAltered = true;
			aBoolean1233 = true;
			
			if(loadingStage != 2) {
				inGameScreen.drawGraphics(4, super.graphics, 4);
				mapBackImage.drawGraphics(0, super.graphics, 516);//545
				
			}
		}
		if(menuOpen)
			needDrawTabArea = true;
		if(menuOpen)
			drawMenu(4,4);
		
		if(invOverlayInterfaceID != -1)
		{
			boolean flag1 = method119(anInt945, invOverlayInterfaceID);
			if(flag1)
				needDrawTabArea = true;
		}
		if(atInventoryInterfaceType == 2)
			needDrawTabArea = true;
		if(activeInterfaceType == 2)
			needDrawTabArea = true;
			drawTabArea();
		if(loadingStage == 2) {
			method146();
		}
		if(backDialogID == -1)
		{
			if(is474 || is480 || is508 || is525 || is562) {
				aClass9_1059.scrollPosition = anInt1211 - anInt1089 - 110;
				if(super.mouseX > 478 && super.mouseX < 580 && super.mouseY > 342)
					method65(494, 110, super.mouseX - 0, super.mouseY - 348, aClass9_1059, 0, false, anInt1211);
				int i = anInt1211 - 110 - aClass9_1059.scrollPosition;
				if(i < 0)
					i = 0;
				if(i > anInt1211 - 110)
					i = anInt1211 - 110;
				if(anInt1089 != i) {
					anInt1089 = i;
					inputTaken = true;
				}
			} else {
				aClass9_1059.scrollPosition = anInt1211 - anInt1089 - 77;
				if(super.mouseX > 448 && super.mouseX < 560 && super.mouseY > 332)
					method65(463, 77, super.mouseX - 17, super.mouseY - 357, aClass9_1059, 0, false, anInt1211);
				int i = anInt1211 - 77 - aClass9_1059.scrollPosition;
				if(i < 0)
					i = 0;
				if(i > anInt1211 - 77)
					i = anInt1211 - 77;
				if(anInt1089 != i) {
					anInt1089 = i;
					inputTaken = true;
				}
			}
		}
		if(backDialogID != -1) {
			boolean flag2 = method119(anInt945, backDialogID);
			if(flag2)
				inputTaken = true;
		}
		if(atInventoryInterfaceType == 3)
			inputTaken = true;
		if(activeInterfaceType == 3)
			inputTaken = true;
		if(aString844 != null)
			inputTaken = true;
		if(menuOpen && menuScreenArea == 2)
			inputTaken = true;
		if(inputTaken) {
			drawChatArea();
			inputTaken = false;
		}
		if(loadingStage == 2) {
			drawMinimap();
			mapBackImage.drawGraphics(0, super.graphics, 516);//545
		}
		if(anInt1054 != -1)
			tabAreaAltered = true;
		if(tabAreaAltered)
		{
			if(anInt1054 != -1 && anInt1054 == tabID)
			{
				anInt1054 = -1;
				stream.createFrame(120);
				stream.writeWordBigEndian(tabID);
			}
			tabAreaAltered = false;
			aRSImageProducer_1125.initDrawingArea();
			inGameScreen.initDrawingArea();
		}
		if(menuOpen){
			drawMenu(516, 466);
		}
		anInt945 = 0;
	}

	private boolean buildFriendsListMenu(RSInterface class9)
	{
		int i = class9.contentType;
		if(i >= 1 && i <= 200 || i >= 701 && i <= 900)
		{
			if(i >= 801)
				i -= 701;
			else
			if(i >= 701)
				i -= 601;
			else
			if(i >= 101)
				i -= 101;
			else
				i--;
			menuActionName[menuActionRow] = "Remove @whi@" + friendsList[i];
			menuActionID[menuActionRow] = 792;
			menuActionRow++;
			menuActionName[menuActionRow] = "Message @whi@" + friendsList[i];
			menuActionID[menuActionRow] = 639;
			menuActionRow++;
			return true;
		}
		if(i >= 401 && i <= 500)
		{
			menuActionName[menuActionRow] = "Remove @whi@" + class9.disabledMessage;
			menuActionID[menuActionRow] = 322;
			menuActionRow++;
			return true;
		} else
		{
			return false;
		}
	}

	private void method104()
	{
		Animable_Sub3 class30_sub2_sub4_sub3 = (Animable_Sub3)aClass19_1056.reverseGetFirst();
		for(; class30_sub2_sub4_sub3 != null; class30_sub2_sub4_sub3 = (Animable_Sub3)aClass19_1056.reverseGetNext())
			if(class30_sub2_sub4_sub3.anInt1560 != plane || class30_sub2_sub4_sub3.aBoolean1567)
				class30_sub2_sub4_sub3.unlink();
			else
			if(loopCycle >= class30_sub2_sub4_sub3.anInt1564)
			{
				class30_sub2_sub4_sub3.method454(anInt945);
				if(class30_sub2_sub4_sub3.aBoolean1567)
					class30_sub2_sub4_sub3.unlink();
				else
					worldController.method285(class30_sub2_sub4_sub3.anInt1560, 0, class30_sub2_sub4_sub3.anInt1563, -1, class30_sub2_sub4_sub3.anInt1562, 60, class30_sub2_sub4_sub3.anInt1561, class30_sub2_sub4_sub3, false);
			}

	}
	
	private void drawInterface(int j, int k, RSInterface class9, int l) {
		if(class9.interfaceType != 0 || class9.children == null)
			return;
		if(class9.interfaceShown && anInt1026 != class9.id && anInt1048 != class9.id && anInt1039 != class9.id)
			return;
		int i1 = DrawingArea.topX;
		int j1 = DrawingArea.topY;
		int k1 = DrawingArea.bottomX;
		int l1 = DrawingArea.bottomY;
		DrawingArea.setDrawingArea(l + class9.height, k, k + class9.width, l);
		int i2 = class9.children.length;
		for(int j2 = 0; j2 < i2; j2++) 	{
			int k2 = class9.childX[j2] + k;
			int l2 = (class9.childY[j2] + l) - j;
			RSInterface class9_1 = RSInterface.interfaceCache[class9.children[j2]];
			k2 += class9_1.xOffset;
			l2 += class9_1.yOffset;
			if(class9_1.contentType > 0)
				drawFriendsListOrWelcomeScreen(class9_1);
			//here
			int[] IDs = {
				1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573,
				1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388,
				1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878,
				1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553,
				1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471,
				/* Ancients */
				12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062,
				12952, 13000, 13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096
			};
			for(int m5 = 0; m5 < IDs.length; m5++) {
				if(class9_1.id == IDs[m5] + 1) {
					if(m5 > 61)
						drawBlackBox(k2 + 1, l2);
					else
						drawBlackBox(k2, l2 + 1);
				}
			}
			int[] runeChildren = {
				1202, 1203, 1209, 1210, 1211, 1218, 1219, 1220, 1227, 1228, 1234, 1235, 1236, 1243, 1244, 1245,
				1252, 1253, 1254, 1261, 1262, 1263, 1270, 1271, 1277, 1278, 1279, 1286, 1287, 1293, 1294, 1295,
				1302, 1303, 1304, 1311, 1312, 1318, 1319, 1320, 1327, 1328, 1329, 1336, 1337, 1343, 1344, 1345,
				1352, 1353, 1354, 1361, 1362, 1363, 1370, 1371, 1377, 1378, 1384, 1385, 1391, 1392, 1393, 1400,
				1401, 1407, 1408, 1410, 1417, 1418, 1424, 1425, 1426, 1433, 1434, 1440, 1441, 1442, 1449, 1450,
				1456, 1457, 1463, 1464, 1465, 1472, 1473, 1474, 1481, 1482, 1488, 1489, 1490, 1497, 1498, 1499,
				1506, 1507, 1508, 1515, 1516, 1517, 1524, 1525, 1526, 1533, 1534, 1535, 1547, 1548, 1549, 1556,
				1557, 1558, 1566, 1567, 1568, 1576, 1577, 1578, 1586, 1587, 1588, 1596, 1597, 1598, 1605, 1606,
				1607, 1616, 1617, 1618, 1627, 1628, 1629, 1638, 1639, 1640, 6007, 6008, 6011, 8673, 8674, 12041,
				12042, 12429, 12430, 12431, 12439, 12440, 12441, 12449, 12450, 12451, 12459, 12460, 15881, 15882,
				15885, 18474, 18475, 18478
			};
			for(int r = 0; r < runeChildren.length; r++)
				if(class9_1.id == runeChildren[r])
					class9_1.modelZoom = 775;
					
			
			if(class9_1.interfaceType == 0) {
				if(class9_1.scrollPosition > class9_1.scrollMax - class9_1.height)
					class9_1.scrollPosition = class9_1.scrollMax - class9_1.height;
				if(class9_1.scrollPosition < 0)
					class9_1.scrollPosition = 0;
				drawInterface(class9_1.scrollPosition, k2, class9_1, l2);
				if(class9_1.scrollMax > class9_1.height)
					drawScrollbar(class9_1.height, class9_1.scrollPosition, l2, k2 + class9_1.width, class9_1.scrollMax);
			} else if(class9_1.interfaceType != 1)
				if(class9_1.interfaceType == 2) {
					int i3 = 0;
					for(int l3 = 0; l3 < class9_1.height; l3++) {
						for(int l4 = 0; l4 < class9_1.width; l4++) {
							int k5 = k2 + l4 * (32 + class9_1.invSpritePadX);
							int j6 = l2 + l3 * (32 + class9_1.invSpritePadY);
							if(i3 < 20) {
								k5 += class9_1.spritesX[i3];
								j6 += class9_1.spritesY[i3];
							}
							if(class9_1.inventory[i3] > 0) {
								int k6 = 0;
								int j7 = 0;
								int j9 = class9_1.inventory[i3] - 1;
								if(k5 > DrawingArea.topX - 32 && k5 < DrawingArea.bottomX && j6 > DrawingArea.topY - 32 && j6 < DrawingArea.bottomY || activeInterfaceType != 0 && anInt1085 == i3) {
									int l9 = 0;
									if(itemSelected == 1 && anInt1283 == i3 && anInt1284 == class9_1.id)
										l9 = 0xffffff;
									Sprite Sprite_2 = ItemDef.getSprite(j9, class9_1.inventoryValue[i3], l9);
									if(Sprite_2 != null) {
										if(activeInterfaceType != 0 && anInt1085 == i3 && anInt1084 == class9_1.id) {
											k6 = super.mouseX - anInt1087;
											j7 = super.mouseY - anInt1088;
											if(k6 < 5 && k6 > -5)
												k6 = 0;
											if(j7 < 5 && j7 > -5)
												j7 = 0;
											if(anInt989 < 5) {
												k6 = 0;
												j7 = 0;
											}
											Sprite_2.drawSprite1(k5 + k6, j6 + j7);
											if(j6 + j7 < DrawingArea.topY && class9.scrollPosition > 0) {
												int i10 = (anInt945 * (DrawingArea.topY - j6 - j7)) / 3;
												if(i10 > anInt945 * 10)
													i10 = anInt945 * 10;
												if(i10 > class9.scrollPosition)
													i10 = class9.scrollPosition;
												class9.scrollPosition -= i10;
												anInt1088 += i10;
											}
											if(j6 + j7 + 32 > DrawingArea.bottomY && class9.scrollPosition < class9.scrollMax - class9.height) {
												int j10 = (anInt945 * ((j6 + j7 + 32) - DrawingArea.bottomY)) / 3;
												if(j10 > anInt945 * 10)
													j10 = anInt945 * 10;
												if(j10 > class9.scrollMax - class9.height - class9.scrollPosition)
													j10 = class9.scrollMax - class9.height - class9.scrollPosition;
												class9.scrollPosition += j10;
												anInt1088 -= j10;
											}
										} else if(atInventoryInterfaceType != 0 && atInventoryIndex == i3 && atInventoryInterface == class9_1.id)
											Sprite_2.drawSprite1(k5, j6);
										else
											Sprite_2.drawSprite(k5, j6);
										if(Sprite_2.maxWidth == 33 || class9_1.inventoryValue[i3] != 1)
										{
										int k10 = class9_1.inventoryValue[i3];
											smallText.method385(0, intToKOrMil(k10), j6 + 10 + j7, k5 + 1 + k6);
											if(k10 >= 1)
												smallText.method385(0xFFFF00, intToKOrMil(k10), j6 + 9 + j7, k5 + k6);
											if(k10 >= 100000)
												smallText.method385(0xFFFFFF, intToKOrMil(k10), j6 + 9 + j7, k5 + k6);
											if(k10 >= 10000000)
												smallText.method385(0x00FF80, intToKOrMil(k10), j6 + 9 + j7, k5 + k6);
                                        }
                                    }
                                }
							} else if(class9_1.sprites != null && i3 < 20) {
								Sprite Sprite_1 = class9_1.sprites[i3];
								if(Sprite_1 != null)
									Sprite_1.drawSprite(k5, j6);
							}
							i3++;
						}
					}
				} else if(class9_1.interfaceType == 3) {
					boolean flag = false;
					if(anInt1039 == class9_1.id || anInt1048 == class9_1.id || anInt1026 == class9_1.id)
						flag = true;
					int j3;
					if(interfaceIsSelected(class9_1)) {
						j3 = class9_1.enabledColor;
						if(flag && class9_1.enabledHoverColor != 0)
							j3 = class9_1.enabledHoverColor;
					} else {
						j3 = class9_1.disabledColor;
						if(flag && class9_1.disabledHoverColor != 0)
							j3 = class9_1.disabledHoverColor;
					}
					if(class9_1.opacity == 0) {
						if(class9_1.boxFilled)
							DrawingArea.drawPixels(class9_1.height, l2, k2, j3, class9_1.width);
						else
							DrawingArea.fillPixels(k2, class9_1.width, class9_1.height, j3, l2);
					} else if(class9_1.boxFilled)
						DrawingArea.method335(j3, l2, class9_1.width, class9_1.height, 256 - (class9_1.opacity & 0xff), k2);
					else
						DrawingArea.method338(l2, class9_1.height, 256 - (class9_1.opacity & 0xff), j3, class9_1.width, k2);
				} else if(class9_1.interfaceType == 4) {
					RSFont textDrawingArea = class9_1.rsFonts;
					String s = class9_1.disabledMessage;
					boolean flag1 = false;
					if(anInt1039 == class9_1.id || anInt1048 == class9_1.id || anInt1026 == class9_1.id)
						flag1 = true;
					int i4;
					if(interfaceIsSelected(class9_1)) {
						i4 = class9_1.enabledColor;
						if(flag1 && class9_1.enabledHoverColor != 0)
							i4 = class9_1.enabledHoverColor;
						if(class9_1.enabledMessage.length() > 0)
							s = class9_1.enabledMessage;
					} else {
						i4 = class9_1.disabledColor;
						if(flag1 && class9_1.disabledHoverColor != 0)
							i4 = class9_1.disabledHoverColor;
					}
					if(class9_1.atActionType == 6 && aBoolean1149) {
						s = "Please wait...";
						i4 = class9_1.disabledColor;
					}
					if(DrawingArea.width == 516) {//519
						if(i4 == 0xffff00)
							i4 = 255;
						if(i4 == 49152)
							i4 = 0xffffff;
					}
					//Magic interface
					if((class9_1.parentID == 1151) || (class9_1.parentID == 12855)) {
						switch (i4) {
							case 16773120: i4 = 0xFE981F; break;
							case 7040819: i4 = 0xAF6A1A; break;
						}
					}
					//Skill interface
					int id = 4004; int id2 = 4005;
					if(class9_1.parentID == 3917 && class9_1.id != id && class9_1.id != id+2 && class9_1.id != id+4 && class9_1.id != id+6 
							&& class9_1.id != id+8 && class9_1.id != id+10 && class9_1.id != id+12 && class9_1.id != id+14 && class9_1.id != id+16 
							&& class9_1.id != id+18 && class9_1.id != id+20 && class9_1.id != id+23 && class9_1.id != id+24 && class9_1.id != id+26 
							&& class9_1.id != id+28 && class9_1.id != id+30 && class9_1.id != id+32 && class9_1.id != id+34 && class9_1.id != 13926 
							&& class9_1.id != 4152 && class9_1.id != 12166 && class9_1.id != id2 && class9_1.id != id2+2 && class9_1.id != id2+4 && class9_1.id != id2+6 
							&& class9_1.id != id2+8 && class9_1.id != id2+10 && class9_1.id != id2+12 && class9_1.id != id2+14 && class9_1.id != id2+16 
							&& class9_1.id != id2+18 && class9_1.id != id2+20 && class9_1.id != id2+23 && class9_1.id != id2+24 && class9_1.id != id2+26 
							&& class9_1.id != id2+28 && class9_1.id != id2+30 && class9_1.id != id2+32 && class9_1.id != id2+34 && class9_1.id != 13927 
							&& class9_1.id != 4153 && class9_1.id != 12167 && class9_1.id != 4026) {
						if(i4 == 16776960)
							i4 = 0x0000;
						class9_1.textShadowed = false;
					}
					/*if(class9_1.parentID == 3917) {
						for(int i = 4004; i < 4040; i++) {
							int[] moreData = {
								13926, 4152, 12166, 13927,
								4153, 12167, 4026
							};
							if(class9_1.id == i || class9_1.id == moreData[i]) {
								return;
							}
							if(i4 == 16776960) {
								i4 = 0x0000;
								class9_1.textShadowed = false;
							}
						}
					}*/
					for(int l6 = l2 + textDrawingArea.anInt1497; s.length() > 0; l6 += textDrawingArea.anInt1497)
					{
						if(s.indexOf("%") != -1)
						{
							do
							{
								int k7 = s.indexOf("%1");
								if(k7 == -1)
									break;
								if(class9_1.id < 4000 || class9_1.id > 5000 && class9_1.id !=13921 && class9_1.id !=13922 && class9_1.id !=12171 && class9_1.id !=12172)
									s = s.substring(0, k7) + methodR(extractInterfaceValues(class9_1, 0)) + s.substring(k7 + 2);
								else
									s = s.substring(0, k7) + interfaceIntToString(extractInterfaceValues(class9_1, 0)) + s.substring(k7 + 2);
							} while(true);
							do
							{
								int l7 = s.indexOf("%2");
								if(l7 == -1)
									break;
								s = s.substring(0, l7) + interfaceIntToString(extractInterfaceValues(class9_1, 1)) + s.substring(l7 + 2);
							} while(true);
							do
							{
								int i8 = s.indexOf("%3");
								if(i8 == -1)
									break;
								s = s.substring(0, i8) + interfaceIntToString(extractInterfaceValues(class9_1, 2)) + s.substring(i8 + 2);
							} while(true);
							do
							{
								int j8 = s.indexOf("%4");
								if(j8 == -1)
									break;
								s = s.substring(0, j8) + interfaceIntToString(extractInterfaceValues(class9_1, 3)) + s.substring(j8 + 2);
							} while(true);
							do
							{
								int k8 = s.indexOf("%5");
								if(k8 == -1)
									break;
								s = s.substring(0, k8) + interfaceIntToString(extractInterfaceValues(class9_1, 4)) + s.substring(k8 + 2);
							} while(true);
						}
						int l8 = s.indexOf("\\n");
						String s1;
						if(l8 != -1)
						{
							s1 = s.substring(0, l8);
							s = s.substring(l8 + 2);
						} else
						{
							s1 = s;
							s = "";
						}
						if(class9_1.textCentered)
							textDrawingArea.method382(i4, k2 + class9_1.width / 2, s1, l6, class9_1.textShadowed);
						else
							textDrawingArea.method389(class9_1.textShadowed, k2, i4, s1, l6);
					}
				} else if(class9_1.interfaceType == 5) {
					//whats this?

					Sprite sprite;
					if(interfaceIsSelected(class9_1))
                        sprite = class9_1.enabledSprite;
                    else
                        sprite = class9_1.disabledSprite;
					if(spellSelected == 1 && class9_1.id == spellID && spellID != 0 && sprite != null) { 
						sprite.drawSprite2(k2, l2, 0xffffff);
					} else {
					if(Autocast && class9_1.id == autocastId)
						magicAuto.drawSprite(k2-3, l2-3);
						if (sprite != null)
							sprite.drawSprite(k2, l2);
					}
                    if(sprite != null)
                        sprite.drawSprite(k2, l2);
				} else if(class9_1.interfaceType == 6) {
					int k3 = Texture.textureInt1;
					int j4 = Texture.textureInt2;
					Texture.textureInt1 = k2 + class9_1.width / 2;
					Texture.textureInt2 = l2 + class9_1.height / 2;
					int i5 = Texture.anIntArray1470[class9_1.modelRotationY] * class9_1.modelZoom >> 16;
					int l5 = Texture.anIntArray1471[class9_1.modelRotationY] * class9_1.modelZoom >> 16;
					boolean flag2 = interfaceIsSelected(class9_1);
					int i7;
					if(flag2)
						i7 = class9_1.enabledAnimation;
					else
						i7 = class9_1.disabledAnimation;
					Model model;
					if(i7 == -1) {
						model = class9_1.method209(-1, -1, flag2);
					} else {
						Animation animation = Animation.anims[i7];
						model = class9_1.method209(animation.anIntArray354[class9_1.animationLength], animation.anIntArray353[class9_1.animationLength], flag2);
					}
					if(model != null)
						model.method482(class9_1.modelRotationX, 0, class9_1.modelRotationY, 0, i5, l5);
					Texture.textureInt1 = k3;
					Texture.textureInt2 = j4;
				} else if(class9_1.interfaceType == 7) {
					RSFont textDrawingArea_1 = class9_1.rsFonts;
					int k4 = 0;
					for(int j5 = 0; j5 < class9_1.height; j5++) {
						for(int i6 = 0; i6 < class9_1.width; i6++) {
							if(class9_1.inventory[k4] > 0) {
								ItemDef itemDef = ItemDef.forID(class9_1.inventory[k4] - 1);
								String s2 = itemDef.name;
								if(itemDef.stackable || class9_1.inventoryValue[k4] != 1)
									s2 = s2 + " x" + intToKOrMilLongName(class9_1.inventoryValue[k4]);
								int i9 = k2 + i6 * (115 + class9_1.invSpritePadX);
								int k9 = l2 + j5 * (12 + class9_1.invSpritePadY);
								if(class9_1.textCentered)
									textDrawingArea_1.method382(class9_1.disabledColor, i9 + class9_1.width / 2, s2, k9, class9_1.textShadowed);
								else
									textDrawingArea_1.method389(class9_1.textShadowed, i9, class9_1.disabledColor, s2, k9);
							}
							k4++;
						}
					}
				}
else if (class9_1.interfaceType == 8 && (anInt1500 == class9_1.id || anInt1044 == class9_1.id || anInt1129 == class9_1.id) && anInt1501 == 100) {
                    int boxWidth = 0;
                    int boxHeight = 0;
                    RSFont textDrawingArea_2 = aTextDrawingArea_1271;
                    for (String s1 = class9_1.disabledMessage; s1.length() > 0;) {
                        int l7 = s1.indexOf("\\n");
                        String s4;
                        if (l7 != -1) {
                            s4 = s1.substring(0, l7);
                            s1 = s1.substring(l7 + 2);
                        } else {
                            s4 = s1;
                            s1 = "";
                        }
                        int j10 = textDrawingArea_2.getTextWidth(s4);
                        if (j10 > boxWidth) {
                            boxWidth = j10;
                        }
                        boxHeight += textDrawingArea_2.anInt1497 + 1;
                    }
                    boxWidth += 6;
                    boxHeight += 7;
                    int xPos = (k2 + class9_1.width) - 5 - boxWidth;
                    int yPos = l2 + class9_1.height + 5;
					
					if (k2 + boxWidth > 175 && k2 + boxWidth < 235) {
						k2 += 26 - (boxWidth/2);
					}
					if (k2 + boxWidth > 235) {
						k2 -= boxWidth;
					}
					if (yPos > 250) {
						yPos -= (boxHeight + 35);
					}
					
                    if (xPos < k2 + 5) {
                        xPos = k2 + 5;
                    }
                    if (xPos + boxWidth > j + class9.width) {
                        xPos = (j + class9.width) - boxWidth;
                    }
                    if (yPos + boxHeight > k + class9.height) {
                        yPos = (k + class9.height) - boxHeight;
                    }
                    DrawingArea.method336(boxHeight, yPos, xPos, 0xFFFFA0, boxWidth);
                    DrawingArea.fillPixels(xPos, boxWidth, boxHeight, 0, yPos);
                    String s2 = class9_1.disabledMessage;
                    for (int j11 = yPos + textDrawingArea_2.anInt1497 + 2; s2.length() > 0; j11 += textDrawingArea_2.anInt1497 + 1) {//anInt1497
                        int l11 = s2.indexOf("\\n");
                        String s5;
                        if (l11 != -1) {
                            s5 = s2.substring(0, l11);
                            s2 = s2.substring(l11 + 2);
                        } else {
                            s5 = s2;
                            s2 = "";
                        }
                        textDrawingArea_2.method389(false, xPos + 3, 0, s5, j11);
                    }
                
                } else if (class9_1.interfaceType == 9) {
					drawHoverBox(k2, l2, class9_1.disabledMessage);
				}else if (class9_1.interfaceType == 10 && (anInt1500 == class9_1.id || anInt1044 == class9_1.id || anInt1129 == class9_1.id) && !menuOpen) {
                  int boxWidth = 0;
                    int boxHeight = 0;
				
                    TextDrawingArea textDrawingArea_2 = newRegularFont;
                    for (String s1 = class9_1.disabledMessage; s1.length() > 0;) {
                        int l7 = s1.indexOf("\\n");
                        String s4;
                        if (l7 != -1) {
                            s4 = s1.substring(0, l7);
                            s1 = s1.substring(l7 + 2);
                        } else {
                            s4 = s1;
                            s1 = "";
                        }
                        int j10 = textDrawingArea_2.getTextWidth(s4);
                        if (j10 > boxWidth) {
                            boxWidth = j10;
                        }
                        boxHeight += textDrawingArea_2.baseCharacterHeight + 1;
                    }
                    boxWidth += 6;
                    boxHeight += 7;
                    int xPos = (k2 + class9_1.width) - 5 - boxWidth;
                    int yPos = l2 + class9_1.height + 5;
                    if (xPos < k2 + 5) {
                        xPos = k2 + 5;
                    }
                    if (xPos + boxWidth > j + class9.width) {
                        xPos = (j + class9.width) - boxWidth;
                    }
                    if (yPos + boxHeight > k + class9.height) {
                        yPos = (k + class9.height) - boxHeight;
                    }
					if(class9.isInventoryInterface)
            {
                if(xPos + boxWidth + j > 249)
                    xPos = 251 - boxWidth - j;
                if(yPos + boxHeight + k > 261)
                    yPos = 245 - boxHeight - k;
            }
                    DrawingArea.method336(boxHeight, yPos, xPos, 0xFFFFA0, boxWidth);
                    DrawingArea.fillPixels(xPos, boxWidth, boxHeight, 0, yPos);
                    String s2 = class9_1.disabledMessage;
                    for (int j11 = yPos + textDrawingArea_2.baseCharacterHeight + 2; s2.length() > 0; j11 += textDrawingArea_2.baseCharacterHeight + 1) {//anInt1497
                        int l11 = s2.indexOf("\\n");
                        String s5;
                        if (l11 != -1) {
                            s5 = s2.substring(0, l11);
                            s2 = s2.substring(l11 + 2);
                        } else {
                            s5 = s2;
                            s2 = "";
                        }
                      //  textDrawingArea_2.method389(false, xPos + 3, 0, s5, j11);
						textDrawingArea_2.drawBasicString(s5, xPos+3,j11, 0, -1);
                    }
					}
		}
		DrawingArea.setDrawingArea(l1, i1, k1, j1);
	}

	private void randomizeBackground(Background background) {
		int j = 256;
		for(int k = 0; k < anIntArray1190.length; k++)
			anIntArray1190[k] = 0;

		for(int l = 0; l < 5000; l++) {
			int i1 = (int)(Math.random() * 128D * (double)j);
			anIntArray1190[i1] = (int)(Math.random() * 256D);
		}
		for(int j1 = 0; j1 < 20; j1++) {
			for(int k1 = 1; k1 < j - 1; k1++) {
				for(int i2 = 1; i2 < 127; i2++) {
					int k2 = i2 + (k1 << 7);
					anIntArray1191[k2] = (anIntArray1190[k2 - 1] + anIntArray1190[k2 + 1] + anIntArray1190[k2 - 128] + anIntArray1190[k2 + 128]) / 4;
				}

			}
			int ai[] = anIntArray1190;
			anIntArray1190 = anIntArray1191;
			anIntArray1191 = ai;
		}
		if(background != null) {
			int l1 = 0;
			for(int j2 = 0; j2 < background.anInt1453; j2++) {
				for(int l2 = 0; l2 < background.anInt1452; l2++)
					if(background.aByteArray1450[l1++] != 0) {
						int i3 = l2 + 16 + background.anInt1454;
						int j3 = j2 + 16 + background.anInt1455;
						int k3 = i3 + (j3 << 7);
						anIntArray1190[k3] = 0;
					}
			}
		}
	}

	private void method107(int i, int j, Stream stream, Player player)
	{
		if((i & 0x400) != 0)
		{
			player.anInt1543 = stream.method428();
			player.anInt1545 = stream.method428();
			player.anInt1544 = stream.method428();
			player.anInt1546 = stream.method428();
			player.anInt1547 = stream.method436() + loopCycle;
			player.anInt1548 = stream.method435() + loopCycle;
			player.anInt1549 = stream.method428();
			player.method446();
		}
		if((i & 0x100) != 0)
		{
			player.anInt1520 = stream.method434();
			int k = stream.readDWord();
			player.anInt1524 = k >> 16;
			player.anInt1523 = loopCycle + (k & 0xffff);
			player.anInt1521 = 0;
			player.anInt1522 = 0;
			if(player.anInt1523 > loopCycle)
				player.anInt1521 = -1;
			if(player.anInt1520 == 65535)
				player.anInt1520 = -1;
		}
		if((i & 8) != 0)
		{
			int l = stream.method434();
			if(l == 65535)
				l = -1;
			int i2 = stream.method427();
			if(l == player.anim && l != -1)
			{
				int i3 = Animation.anims[l].anInt365;
				if(i3 == 1)
				{
					player.anInt1527 = 0;
					player.anInt1528 = 0;
					player.anInt1529 = i2;
					player.anInt1530 = 0;
				}
				if(i3 == 2)
					player.anInt1530 = 0;
			} else
			if(l == -1 || player.anim == -1 || Animation.anims[l].anInt359 >= Animation.anims[player.anim].anInt359)
			{
				player.anim = l;
				player.anInt1527 = 0;
				player.anInt1528 = 0;
				player.anInt1529 = i2;
				player.anInt1530 = 0;
				player.anInt1542 = player.smallXYIndex;
			}
		}
		if((i & 4) != 0)
		{
			player.textSpoken = stream.readString();
			if(player.textSpoken.charAt(0) == '~')
			{
				player.textSpoken = player.textSpoken.substring(1);
				pushMessage(player.textSpoken, 2, player.name);
			} else
			if(player == myPlayer)
				pushMessage(player.textSpoken, 2, player.name);
			player.anInt1513 = 0;
			player.anInt1531 = 0;
			player.textCycle = 150;
		}
		if((i & 0x80) != 0)
		{
			//right fucking here
			int i1 = stream.method434();
			int j2 = stream.readUnsignedByte();
			int j3 = stream.method427();
			int k3 = stream.currentOffset;
			if(player.name != null && player.visible)
			{
				long l3 = TextClass.longForName(player.name);
				boolean flag = false;
				if(j2 <= 1)
				{
					for(int i4 = 0; i4 < ignoreCount; i4++)
					{
						if(ignoreListAsLongs[i4] != l3)
							continue;
						flag = true;
						break;
					}

				}
				if(!flag && anInt1251 == 0)
					try
					{
						aStream_834.currentOffset = 0;
						stream.method442(j3, 0, aStream_834.buffer);
						aStream_834.currentOffset = 0;
						String s = TextInput.method525(j3, aStream_834);
						//s = Censor.doCensor(s);
						player.textSpoken = s;
						player.anInt1513 = i1 >> 8;
						player.privelage = j2;
						player.anInt1531 = i1 & 0xff;
						player.textCycle = 150;
//Other players see your icon
						if(j2 == 4)
							pushMessage(s, 1, "@cr3@<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
						else if(j2 == 3)
							pushMessage(s, 1, "@cr2@<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
						else if(j2 == 2)
							pushMessage(s, 1, "@cr1@<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
						else if (j2 == 1)
                            				pushMessage(s, 1, "@cr0@<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
						else if (j2 == 5)
                            				pushMessage(s, 1, "@cr3@<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
						else if (j2 == 6)
                            				pushMessage(s, 1, "@cr2@<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
						else if (j2 == 7)
                            				pushMessage(s, 1, "@cr5@<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
						else if (j2 == 8)
                            				pushMessage(s, 1, "@cr4@<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
						else if (j2 == 9)
                            				pushMessage(s, 1, "@cr6@<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
						else
							pushMessage(s, 2, "<col=13132800>"+getRank(player.skill)+"</col> " + player.name);
					}
					catch(Exception exception)
					{
						signlink.reporterror("cde2");
					}
			}
			stream.currentOffset = k3 + j3;
		}
		if((i & 1) != 0)
		{
			player.interactingEntity = stream.method434();
			if(player.interactingEntity == 65535)
				player.interactingEntity = -1;
		}
		if((i & 0x10) != 0)
		{
			int j1 = stream.method427();
			byte abyte0[] = new byte[j1];
			Stream stream_1 = new Stream(abyte0);
			stream.readBytes(j1, 0, abyte0);
			aStreamArray895s[j] = stream_1;
			player.updatePlayer(stream_1);
		}
		if((i & 2) != 0)
		{
			player.anInt1538 = stream.method436();
			player.anInt1539 = stream.method434();
		}
 	if ((i & 0x20) != 0) {
	    int k1 = stream.readUnsignedByte();
	    int k2 = stream.method426();
	    int type1 = stream.readUnsignedByte();
	    player.updateHitData(k2, k1, type1, loopCycle);
	    player.loopCycleStatus = loopCycle + 300;
	    player.currentHealth = stream.method427();
	    player.maxHealth = stream.readUnsignedByte();
	}
 	if ((i & 0x200) != 0) {
	    int l1 = stream.readUnsignedByte();
	    int l2 = stream.method428();
	    int type2 = stream.readUnsignedByte();
	    player.updateHitData(l2, l1, type2, loopCycle);
	    player.loopCycleStatus = loopCycle + 300;
	    player.currentHealth = stream.readUnsignedByte();
	    player.maxHealth = stream.method427();
	}
	}

	private void method108()
	{
		try
		{
			int j = myPlayer.x + anInt1278;
			int k = myPlayer.y + anInt1131;
			if(anInt1014 - j < -500 || anInt1014 - j > 500 || anInt1015 - k < -500 || anInt1015 - k > 500)
			{
				anInt1014 = j;
				anInt1015 = k;
			}
			if(anInt1014 != j)
				anInt1014 += (j - anInt1014) / 16;
			if(anInt1015 != k)
				anInt1015 += (k - anInt1015) / 16;
			if(super.keyArray[1] == 1)
				anInt1186 += (-24 - anInt1186) / 2;
			else
			if(super.keyArray[2] == 1)
				anInt1186 += (24 - anInt1186) / 2;
			else
				anInt1186 /= 2;
			if(super.keyArray[3] == 1)
				anInt1187 += (12 - anInt1187) / 2;
			else
			if(super.keyArray[4] == 1)
				anInt1187 += (-12 - anInt1187) / 2;
			else
				anInt1187 /= 2;
			  minimapInt1 = minimapInt1 + anInt1186 / 2 & 0x7ff;
			  anInt1184 += anInt1187 / 2;
			if(anInt1184 < 128)
				anInt1184 = 128;
			if(anInt1184 > 383)
				anInt1184 = 383;
			int l = anInt1014 >> 7;
			int i1 = anInt1015 >> 7;
			int j1 = method42(plane, anInt1015, anInt1014);
			int k1 = 0;
			if(l > 3 && i1 > 3 && l < 100 && i1 < 100)
			{
				for(int l1 = l - 4; l1 <= l + 4; l1++)
				{
					for(int k2 = i1 - 4; k2 <= i1 + 4; k2++)
					{
						int l2 = plane;
						if(l2 < 3 && (byteGroundArray[1][l1][k2] & 2) == 2)
							l2++;
						int i3 = j1 - intGroundArray[l2][l1][k2];
						if(i3 > k1)
							k1 = i3;
					}

				}

			}
			anInt1005++;
			if(anInt1005 > 1512)
			{
				anInt1005 = 0;
				stream.createFrame(77);
				stream.writeWordBigEndian(0);
				int i2 = stream.currentOffset;
				stream.writeWordBigEndian((int)(Math.random() * 256D));
				stream.writeWordBigEndian(101);
				stream.writeWordBigEndian(233);
				stream.writeWord(45092);
				if((int)(Math.random() * 2D) == 0)
					stream.writeWord(35784);
				stream.writeWordBigEndian((int)(Math.random() * 256D));
				stream.writeWordBigEndian(64);
				stream.writeWordBigEndian(38);
				stream.writeWord((int)(Math.random() * 65536D));
				stream.writeWord((int)(Math.random() * 65536D));
				stream.writeBytes(stream.currentOffset - i2);
			}
			int j2 = k1 * 192;
			if(j2 > 0x17f00)
				j2 = 0x17f00;
			if(j2 < 32768)
				j2 = 32768;
			if(j2 > anInt984)
			{
				anInt984 += (j2 - anInt984) / 24;
				return;
			}
			if(j2 < anInt984)
			{
				anInt984 += (j2 - anInt984) / 80;
			}
		}
		catch(Exception _ex)
		{
			signlink.reporterror("glfc_ex " + myPlayer.x + "," + myPlayer.y + "," + anInt1014 + "," + anInt1015 + "," + anInt1069 + "," + anInt1070 + "," + baseX + "," + baseY);
			throw new RuntimeException("eek");
		}
	}

	public void processDrawing()
	{
		if(rsAlreadyLoaded || loadingError || genericLoadingError)
		{
			showErrorScreen();
			return;
		}
		anInt1061++;
		if(!loggedIn) {
			drawLoginScreen(false);
		}else{
			drawGameScreen();
		}
		anInt1213 = 0;
	}

	private boolean isFriendOrSelf(String s)
	{
		if(s == null)
			return false;
		for(int i = 0; i < friendsCount; i++)
			if(s.equalsIgnoreCase(friendsList[i]))
				return true;
		return s.equalsIgnoreCase(myPlayer.name);
	}

	private static String combatDiffColor(int i, int j)
	{
		int k = i - j;
		if(k < -9)
			return "@red@";
		if(k < -6)
			return "@or3@";
		if(k < -3)
			return "@or2@";
		if(k < 0)
			return "@or1@";
		if(k > 9)
			return "@gre@";
		if(k > 6)
			return "@gr3@";
		if(k > 3)
			return "@gr2@";
		if(k > 0)
			return "@gr1@";
		else
			return "@yel@";
	}

	private void setWaveVolume(int i)
	{
		signlink.wavevol = i;
	}

	private void draw3dScreen()
	{
			alertHandler.processAlerts();
		drawSplitPrivateChat();
		if(crossType == 1)
		{
			crosses[crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
			anInt1142++;
			if(anInt1142 > 67)
			{
				anInt1142 = 0;
				stream.createFrame(78);
			}
		}
		if(crossType == 2)
			crosses[4 + crossIndex / 100].drawSprite(crossX - 8 - 4, crossY - 8 - 4);
		if(anInt1018 != -1)
		{
			method119(anInt945, anInt1018);
			drawInterface(0, 0, RSInterface.interfaceCache[anInt1018], 0);
		}
		if(openInterfaceID != -1)
		{
			method119(anInt945, openInterfaceID);
			drawInterface(0, 0, RSInterface.interfaceCache[openInterfaceID], 0);
		}
		method70();
		if(!menuOpen)
		{
			processRightClick();
			drawTooltip();
		} else
			drawMenu(4, 4);
		if(anInt1055 == 1)
			multiOverlay.drawSprite(472, 296);
		int i1 = 0xffff00;
		int x = baseX + (myPlayer.x - 6 >> 7);
		int y = baseY + (myPlayer.y - 6 >> 7);
		if (fps)
		{
			char c = '\u01FB';
			int k = 20;
			if(super.fps < 15)
			i1 = 0xff0000;
			aTextDrawingArea_1271.method385(0xffff00, "Fps: " + super.fps, 285, 5);
			Runtime runtime = Runtime.getRuntime();
			int j1 = (int)((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
			i1 = 0xffff00;
			if(j1 > 0x2000000 && lowMem)
			i1 = 0xff0000;
			k += 15;
			aTextDrawingArea_1271.method385(0xffff00, "Mem: " + j1 + "k", 299, 5);
			aTextDrawingArea_1271.method385(0xffff00, "Mouse X: " + super.mouseX + " , Mouse Y: " + super.mouseY, 314, 5);
			aTextDrawingArea_1271.method385(0xffff00, "Coords: " + x + ", " + y, 329, 5);
		}
		if(anInt1104 != 0)
		{
			int j = anInt1104 / 50;
			int l = j / 60;
			j %= 60;
			if(j < 10)
				aTextDrawingArea_1271.method385(0xffff00, "System update in: " + l + ":0" + j, 329, 4);
			else
				aTextDrawingArea_1271.method385(0xffff00, "System update in: " + l + ":" + j, 329, 4);
			anInt849++;
			if(anInt849 > 75)
			{
				anInt849 = 0;
				stream.createFrame(148);
			}
		}
	}

	private void addIgnore(long l)
	{
		try
		{
			if(l == 0L)
				return;
			if(ignoreCount >= 100)
			{
				pushMessage("Your ignore list is full. Max of 100 hit", 0, "");
				return;
			}
			String s = TextClass.fixName(TextClass.nameForLong(l));
			for(int j = 0; j < ignoreCount; j++)
				if(ignoreListAsLongs[j] == l)
				{
					pushMessage(s + " is already on your ignore list", 0, "");
					return;
				}
			for(int k = 0; k < friendsCount; k++)
				if(friendsListAsLongs[k] == l)
				{
					pushMessage("Please remove " + s + " from your friend list first", 0, "");
					return;
				}

			ignoreListAsLongs[ignoreCount++] = l;
			needDrawTabArea = true;
			stream.createFrame(133);
			stream.writeQWord(l);
			return;
		}
		catch(RuntimeException runtimeexception)
		{
			signlink.reporterror("45688, " + l + ", " + 4 + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}

	private void method114()
	{
		for(int i = -1; i < playerCount; i++)
		{
			int j;
			if(i == -1)
				j = myPlayerIndex;
			else
				j = playerIndices[i];
			Player player = playerArray[j];
			if(player != null)
				method96(player);
		}

	}

	private void method115()
	{
		if(loadingStage == 2)
		{
			for(Class30_Sub1 class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetNext())
			{
				if(class30_sub1.anInt1294 > 0)
					class30_sub1.anInt1294--;
				if(class30_sub1.anInt1294 == 0)
				{
					if(class30_sub1.anInt1299 < 0 || ObjectManager.method178(class30_sub1.anInt1299, class30_sub1.anInt1301))
					{
						method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1300, class30_sub1.anInt1301, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1299);
						class30_sub1.unlink();
					}
				} else
				{
					if(class30_sub1.anInt1302 > 0)
						class30_sub1.anInt1302--;
					if(class30_sub1.anInt1302 == 0 && class30_sub1.anInt1297 >= 1 && class30_sub1.anInt1298 >= 1 && class30_sub1.anInt1297 <= 102 && class30_sub1.anInt1298 <= 102 && (class30_sub1.anInt1291 < 0 || ObjectManager.method178(class30_sub1.anInt1291, class30_sub1.anInt1293)))
					{
						method142(class30_sub1.anInt1298, class30_sub1.anInt1295, class30_sub1.anInt1292, class30_sub1.anInt1293, class30_sub1.anInt1297, class30_sub1.anInt1296, class30_sub1.anInt1291);
						class30_sub1.anInt1302 = -1;
						if(class30_sub1.anInt1291 == class30_sub1.anInt1299 && class30_sub1.anInt1299 == -1)
							class30_sub1.unlink();
						else
						if(class30_sub1.anInt1291 == class30_sub1.anInt1299 && class30_sub1.anInt1292 == class30_sub1.anInt1300 && class30_sub1.anInt1293 == class30_sub1.anInt1301)
							class30_sub1.unlink();
					}
				}
			}

		}
	}
	
	
	private void determineMenuSize() {
		int i = chatTextDrawingArea.getTextWidth("Choose Option");
		for(int j = 0; j < menuActionRow; j++)
		{
			int k = chatTextDrawingArea.getTextWidth(menuActionName[j]);
			if(k > i)
				i = k;
		}
		i += 8;
        int l = 15 * menuActionRow + 21;
		if(super.saveClickX > 0 && super.saveClickY > 0 && super.saveClickX < 765 && super.saveClickY < 503) {
            int i1 = super.saveClickX - i / 2;
            if(i1 + i > 765){
                i1 = 765 - i;
			}
            if(i1 < 0){
                i1 = 0;
			}
            int l1 = super.saveClickY - 0;
            if(l1 + l > 503){
                l1 = 503 - l;
				}
            if(l1 < 0){
                l1 = 0;
			}
            menuOpen = true;
            menuOffsetX = i1;
			menuOffsetY = l1;
			menuWidth = i;
            menuHeight = 15 * menuActionRow + 22;
		}
	}

	private void method117(Stream stream)
	{
		stream.initBitAccess();
		int j = stream.readBits(1);
		if(j == 0)
			return;
		int k = stream.readBits(2);
		if(k == 0)
		{
			anIntArray894[anInt893++] = myPlayerIndex;
			return;
		}
		if(k == 1)
		{
			int l = stream.readBits(3);
			myPlayer.moveInDir(false, l);
			int k1 = stream.readBits(1);
			if(k1 == 1)
				anIntArray894[anInt893++] = myPlayerIndex;
			return;
		}
		if(k == 2)
		{
			int i1 = stream.readBits(3);
			myPlayer.moveInDir(true, i1);
			int l1 = stream.readBits(3);
			myPlayer.moveInDir(true, l1);
			int j2 = stream.readBits(1);
			if(j2 == 1)
				anIntArray894[anInt893++] = myPlayerIndex;
			return;
		}
		if(k == 3)
		{
			plane = stream.readBits(2);
			int j1 = stream.readBits(1);
			int i2 = stream.readBits(1);
			if(i2 == 1)
				anIntArray894[anInt893++] = myPlayerIndex;
			int k2 = stream.readBits(7);
			int l2 = stream.readBits(7);
			myPlayer.setPos(l2, k2, j1 == 1);
		}
	}

	private void nullLoader()
	{
		aBoolean831 = false;
		while(drawingFlames)
		{
			aBoolean831 = false;
			try
			{
				Thread.sleep(50L);
			}
			catch(Exception _ex) { }
		}
		titleBox = null;
		titleBox1 = null;
		titleButton = null;
		aBackgroundArray1152s = null;
		anIntArray850 = null;
		anIntArray851 = null;
		anIntArray852 = null;
		anIntArray853 = null;
		anIntArray1190 = null;
		anIntArray1191 = null;
		anIntArray828 = null;
		anIntArray829 = null;
		aSprite_1201 = null;
		aSprite_1202 = null;
	}

	private boolean method119(int i, int j)
	{
		boolean flag1 = false;
		RSInterface class9 = RSInterface.interfaceCache[j];
		for(int k = 0; k < class9.children.length; k++)
		{
			if(class9.children[k] == -1)
				break;
			RSInterface class9_1 = RSInterface.interfaceCache[class9.children[k]];
			if(class9_1.interfaceType == 1)
				flag1 |= method119(i, class9_1.id);
			if(class9_1.interfaceType == 6 && (class9_1.disabledAnimation != -1 || class9_1.enabledAnimation != -1))
			{
				boolean flag2 = interfaceIsSelected(class9_1);
				int l;
				if(flag2)
					l = class9_1.enabledAnimation;
				else
					l = class9_1.disabledAnimation;
				if(l != -1)
				{
					Animation animation = Animation.anims[l];
					for(class9_1.animationDelay += i; class9_1.animationDelay > animation.method258(class9_1.animationLength);)
					{
						class9_1.animationDelay -= animation.method258(class9_1.animationLength) + 1;
						class9_1.animationLength++;
						if(class9_1.animationLength >= animation.anInt352)
						{
							class9_1.animationLength -= animation.anInt356;
							if(class9_1.animationLength < 0 || class9_1.animationLength >= animation.anInt352)
								class9_1.animationLength = 0;
						}
						flag1 = true;
					}

				}
			}
		}

		return flag1;
	}

	private int method120()
	{
		int j = 3;
		if(yCameraCurve < 310)
		{
			int k = xCameraPos >> 7;
			int l = yCameraPos >> 7;
			int i1 = myPlayer.x >> 7;
			int j1 = myPlayer.y >> 7;
			if((byteGroundArray[plane][k][l] & 4) != 0)
				j = plane;
			int k1;
			if(i1 > k)
				k1 = i1 - k;
			else
				k1 = k - i1;
			int l1;
			if(j1 > l)
				l1 = j1 - l;
			else
				l1 = l - j1;
			if(k1 > l1)
			{
				int i2 = (l1 * 0x10000) / k1;
				int k2 = 32768;
				while(k != i1) 
				{
					if(k < i1)
						k++;
					else
					if(k > i1)
						k--;
					if((byteGroundArray[plane][k][l] & 4) != 0)
						j = plane;
					k2 += i2;
					if(k2 >= 0x10000)
					{
						k2 -= 0x10000;
						if(l < j1)
							l++;
						else
						if(l > j1)
							l--;
						if((byteGroundArray[plane][k][l] & 4) != 0)
							j = plane;
					}
				}
			} else
			{
				int j2 = (k1 * 0x10000) / l1;
				int l2 = 32768;
				while(l != j1) 
				{
					if(l < j1)
						l++;
					else
					if(l > j1)
						l--;
					if((byteGroundArray[plane][k][l] & 4) != 0)
						j = plane;
					l2 += j2;
					if(l2 >= 0x10000)
					{
						l2 -= 0x10000;
						if(k < i1)
							k++;
						else
						if(k > i1)
							k--;
						if((byteGroundArray[plane][k][l] & 4) != 0)
							j = plane;
					}
				}
			}
		}
		if((byteGroundArray[plane][myPlayer.x >> 7][myPlayer.y >> 7] & 4) != 0)
			j = plane;
		return j;
	}

	private int method121()
	{
		int j = method42(plane, yCameraPos, xCameraPos);
		if(j - zCameraPos < 800 && (byteGroundArray[plane][xCameraPos >> 7][yCameraPos >> 7] & 4) != 0)
			return plane;
		else
			return 3;
	}

	private void delIgnore(long l)
	{
		try
		{
			if(l == 0L)
				return;
			for(int j = 0; j < ignoreCount; j++)
				if(ignoreListAsLongs[j] == l)
				{
					ignoreCount--;
					needDrawTabArea = true;
					System.arraycopy(ignoreListAsLongs, j + 1, ignoreListAsLongs, j, ignoreCount - j);

					stream.createFrame(74);
					stream.writeQWord(l);
					return;
				}

			return;
		}
		catch(RuntimeException runtimeexception)
		{
			signlink.reporterror("47229, " + 3 + ", " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	}
	
	
	private void chatJoin(long l) {
		try {
			if(l == 0L)
				return;
			stream.createFrame(60);
			stream.writeQWord(l);
			return;
		}
		catch(RuntimeException runtimeexception)
		{
			signlink.reporterror("47229, " + 3 + ", " + l + ", " + runtimeexception.toString());
		}
		throw new RuntimeException();
	
	}
	
	public String getParameter(String s)
	{
		if(signlink.mainapp != null)
			return signlink.mainapp.getParameter(s);
		else
			return super.getParameter(s);
	}

	private void adjustVolume(boolean flag, int i)
	{
		signlink.midivol = i;
		if(flag)
			signlink.midi = "voladjust";
	}

	private int extractInterfaceValues(RSInterface class9, int j)
	{
		if(class9.valueIndexArray == null || j >= class9.valueIndexArray.length)
			return -2;
		try
		{
			int ai[] = class9.valueIndexArray[j];
			int k = 0;
			int l = 0;
			int i1 = 0;
			do
			{
				int j1 = ai[l++];
				int k1 = 0;
				byte byte0 = 0;
				if(j1 == 0)
					return k;
				if(j1 == 1)
					k1 = currentStats[ai[l++]];
				if(j1 == 2)
					k1 = maxStats[ai[l++]];
				if(j1 == 3)
					k1 = currentExp[ai[l++]];
				if(j1 == 4)
				{
					RSInterface class9_1 = RSInterface.interfaceCache[ai[l++]];
					int k2 = ai[l++];
					if(k2 >= 0 && k2 < ItemDef.totalItems && (!ItemDef.forID(k2).membersObject || isMembers))
					{
						for(int j3 = 0; j3 < class9_1.inventory.length; j3++)
							if(class9_1.inventory[j3] == k2 + 1)
								k1 += class9_1.inventoryValue[j3];

					}
				}
				if(j1 == 5)
					k1 = variousSettings[ai[l++]];
				if(j1 == 6)
					k1 = anIntArray1019[maxStats[ai[l++]] - 1];
				if(j1 == 7)
					k1 = (variousSettings[ai[l++]] * 100) / 46875;
				if(j1 == 8)
					k1 = myPlayer.combatLevel;
				if(j1 == 9)
				{
					for(int l1 = 0; l1 < Skills.skillsCount; l1++)
						if(Skills.skillEnabled[l1])
							k1 += maxStats[l1];

				}
				if(j1 == 10)
				{
					RSInterface class9_2 = RSInterface.interfaceCache[ai[l++]];
					int l2 = ai[l++] + 1;
					if(l2 >= 0 && l2 < ItemDef.totalItems && (!ItemDef.forID(l2).membersObject || isMembers))
					{
						for(int k3 = 0; k3 < class9_2.inventory.length; k3++)
						{
							if(class9_2.inventory[k3] != l2)
								continue;
							k1 = 0x3b9ac9ff;
							break;
						}

					}
				}
				if(j1 == 11)
					k1 = energy;
				if(j1 == 12)
					k1 = weight;
				if(j1 == 13)
				{
					int i2 = variousSettings[ai[l++]];
					int i3 = ai[l++];
					k1 = (i2 & 1 << i3) == 0 ? 0 : 1;
				}
				if(j1 == 14)
				{
					int j2 = ai[l++];
					VarBit varBit = VarBit.cache[j2];
					int l3 = varBit.anInt648;
					int i4 = varBit.anInt649;
					int j4 = varBit.anInt650;
					int k4 = anIntArray1232[j4 - i4];
					k1 = variousSettings[l3] >> i4 & k4;
				}
				if(j1 == 15)
					byte0 = 1;
				if(j1 == 16)
					byte0 = 2;
				if(j1 == 17)
					byte0 = 3;
				if(j1 == 18)
					k1 = (myPlayer.x >> 7) + baseX;
				if(j1 == 19)
					k1 = (myPlayer.y >> 7) + baseY;
				if(j1 == 20)
					k1 = ai[l++];
				if(byte0 == 0)
				{
					if(i1 == 0)
						k += k1;
					if(i1 == 1)
						k -= k1;
					if(i1 == 2 && k1 != 0)
						k /= k1;
					if(i1 == 3)
						k *= k1;
					i1 = 0;
				} else
				{
					i1 = byte0;
				}
			} while(true);
		}
		catch(Exception _ex)
		{
			return -1;
		}
	}

	private void drawTooltip() { 
		if(menuActionRow < 2 && itemSelected == 0 && spellSelected == 0)
			return;
		String s;
		if(itemSelected == 1 && menuActionRow < 2)
			s = "Use " + selectedItemName + " with...";
		else if(spellSelected == 1 && menuActionRow < 2)
			s = spellTooltip + "...";
		else
			s = menuActionName[menuActionRow - 1];
		if(menuActionRow > 2)
			s = s + "@whi@ / " + (menuActionRow - 2) + " more options";
		chatTextDrawingArea.method390(4, 0xffffff, s, loopCycle / 1000, 15);
	}

	private void drawMinimap() { 
		int compassX, compassY, MapY, MapX;
		if(is480 == true || is508 == true || is525 == true || is562 == true) {
			compassX = 11;
			compassY = 8;
			MapY = 9;
			MapX = 38;
		} else if(is474) {
			compassX = 30;
			compassY = 4;
			MapY = 9;
			MapX = 50;
		} else {
			compassX = 30+5;
			compassY = 4;
			MapY = 9;
			MapX = 50+6;
		}
		mapBackImage.initDrawingArea();
		if (anInt1021 == 2) {
			Black[getSpriteID()].drawSprite(0, 0);
			if(is480 == true || is508 == true || is525 == true || is562 == true) {
				loadOrbs();
drawCoinOrb();
			}
			compass.method352(33, minimapInt1, anIntArray1057, 256, anIntArray968, 25, compassY, compassX-1, 33, 25);
			if(menuOpen){
				drawMenu(516, 0);
			}
			inGameScreen.initDrawingArea();
			return;
		}
		int i = minimapInt1 + minimapInt2 & 0x7ff;
		int j = 48 + myPlayer.x / 32;
		int l2 = 464 - myPlayer.y / 32;
		
		for(int j1 = 0; j1 < anIntArray1229.length; j1++){	
			anIntArray1229[j1] = 172;
          	anIntArray1052[j1] = -22;	
		}
		aSprite_1263.method352(152, i, anIntArray1229, 256 + minimapInt3, anIntArray1052, l2, MapY, MapX, 146, j);
		compass.method352(33, minimapInt1, anIntArray1057, 256, anIntArray968, 25, compassY, compassX-1, 33, 25);

		for(int j5 = 0; j5 < anInt1071; j5++) {
			try {
				int k = (anIntArray1072[j5] * 4 + 2) - myPlayer.x / 32;
				int i3 = (anIntArray1073[j5] * 4 + 2) - myPlayer.y / 32;
				markMinimap(aSpriteArray1140[j5], k, i3, false);
				} catch(Exception exception) {
			}
		}
		
		for(int k5 = 0; k5 < 104; k5++) {
			for(int l5 = 0; l5 < 104; l5++) {
				NodeList class19 = groundArray[plane][k5][l5];
				if(class19 != null) {
					int l = (k5 * 4 + 2) - myPlayer.x / 32;
					int j3 = (l5 * 4 + 2) - myPlayer.y / 32;
					markMinimap(mapDotItem, l, j3, false);
				}
			}
		}
		
		for(int i6 = 0; i6 < npcCount; i6++) {
			NPC npc = npcArray[npcIndices[i6]];
			if(npc != null && npc.isVisible()) {
				EntityDef entityDef = npc.desc;
				if(entityDef.childrenIDs != null)
					entityDef = entityDef.method161();
				if(entityDef != null && entityDef.aBoolean87 && entityDef.aBoolean84) {
					int i1 = npc.x / 32 - myPlayer.x / 32;
					int k3 = npc.y / 32 - myPlayer.y / 32;
					markMinimap(mapDotNPC, i1, k3, false);
				}
			}
		}
		for(int j6 = 0; j6 < playerCount; j6++) {
			Player player = playerArray[playerIndices[j6]];
			if(player != null && player.isVisible()) {
				int j1 = player.x / 32 - myPlayer.x / 32;
				int l3 = player.y / 32 - myPlayer.y / 32;
				boolean flag1 = false;
				boolean flag3 = false;
				for (int j3 = 0; j3 < clanList.length; j3++) {
					if (clanList[j3] == null)
						continue;
					if (!clanList[j3].equalsIgnoreCase(player.name))
						continue;
					flag3 = true;
					break;
				}
				long l6 = TextClass.longForName(player.name);
				for(int k6 = 0; k6 < friendsCount; k6++) {
					if(l6 != friendsListAsLongs[k6] || friendsNodeIDs[k6] == 0)
						continue;
					flag1 = true;
					break;
				}
				boolean flag2 = false;
				if(myPlayer.team != 0 && player.team != 0 && myPlayer.team == player.team)
					flag2 = true;
				if(flag1)
					markMinimap(mapDotFriend, j1, l3, false);
				else if(flag3)
					markMinimap(mapDotClan, j1, l3, false);
				else if(flag2)
					markMinimap(mapDotTeam, j1, l3, false);
				else
					markMinimap(mapDotPlayer, j1, l3, false);
			}
		}
		if(anInt855 != 0 && loopCycle % 20 < 10) {
			if(anInt855 == 1 && anInt1222 >= 0 && anInt1222 < npcArray.length) {
				NPC class30_sub2_sub4_sub1_sub1_1 = npcArray[anInt1222];
				if(class30_sub2_sub4_sub1_sub1_1 != null) {
					int k1 = class30_sub2_sub4_sub1_sub1_1.x / 32 - myPlayer.x / 32;
					int i4 = class30_sub2_sub4_sub1_sub1_1.y / 32 - myPlayer.y / 32;
					method81(mapMarker, i4, k1);
				}
			}
			if(anInt855 == 2) {
				int l1 = ((anInt934 - baseX) * 4 + 2) - myPlayer.x / 32;
				int j4 = ((anInt935 - baseY) * 4 + 2) - myPlayer.y / 32;
				method81(mapMarker, j4, l1);
			}
			if(anInt855 == 10 && anInt933 >= 0 && anInt933 < playerArray.length) {
				Player class30_sub2_sub4_sub1_sub2_1 = playerArray[anInt933];
				if(class30_sub2_sub4_sub1_sub2_1 != null) {
					int i2 = class30_sub2_sub4_sub1_sub2_1.x / 32 - myPlayer.x / 32;
					int k4 = class30_sub2_sub4_sub1_sub2_1.y / 32 - myPlayer.y / 32;
					method81(mapMarker, k4, i2);
				}
			}
		}
		if(destX != 0) {
			int j2 = (destX * 4 + 2) - myPlayer.x / 32;
			int l4 = (destY * 4 + 2) - myPlayer.y / 32;
			markMinimap(mapFlag, j2, l4, false);
		}
		CustomMapback[getSpriteID()].drawSprite(0, 0);
		if(is480 == true || is508 == true || is525 == true || is562 == true) {
			loadOrbs();
drawCoinOrb();
			DrawingArea.drawPixels(3, 84, 108, 0xffffff, 3);
		} else {
			DrawingArea.drawPixels(3, 84, 124, 0xffffff, 3);
		}
		if(menuOpen)
			drawMenu(516, 0);
		inGameScreen.initDrawingArea();
	}

	private void npcScreenPos(Entity entity, int i) {
		calcEntityScreenPos(entity.x, i, entity.y);
	}

	private void calcEntityScreenPos(int i, int j, int l) {
		if(i < 128 || l < 128 || i > 13056 || l > 13056) {
			spriteDrawX = -1;
			spriteDrawY = -1;
			return;
		}
		int i1 = method42(plane, l, i) - j;
		i -= xCameraPos;
		i1 -= zCameraPos;
		l -= yCameraPos;
		int j1 = Model.modelIntArray1[yCameraCurve];
		int k1 = Model.modelIntArray2[yCameraCurve];
		int l1 = Model.modelIntArray1[xCameraCurve];
		int i2 = Model.modelIntArray2[xCameraCurve];
		int j2 = l * l1 + i * i2 >> 16;
		l = l * i2 - i * l1 >> 16;
		i = j2;
		j2 = i1 * k1 - l * j1 >> 16;
		l = i1 * j1 + l * k1 >> 16;
		i1 = j2;
		if(l >= 50) {
			spriteDrawX = Texture.textureInt1 + (i << 9) / l;
			spriteDrawY = Texture.textureInt2 + (i1 << 9) / l;
		} else {
			spriteDrawX = -1;
			spriteDrawY = -1;
		}
	}

	private void buildSplitPrivateChatMenu()
	{
		if(splitPrivateChat == 0)
			return;
		int i = 0;
		if(anInt1104 != 0)
			i = 1;
		for(int j = 0; j < 100; j++)
			if(chatMessages[j] != null)
			{
				int k = chatTypes[j];
				String s = chatNames[j];
				boolean flag1 = false;
				if(s != null && s.startsWith("@cr0@")) { 
                    s = s.substring(5); 
                    byte byte0 = 1; 
                }
				if(s != null && s.startsWith("@cr1@")) {
					s = s.substring(5);
					boolean flag2 = true;
					byte byte0 = 2; 
				}
				if(s != null && s.startsWith("@cr2@")) {
					s = s.substring(5);
					byte byte0 = 3;
				}
				if(s != null && s.startsWith("@cr3@")) {
					s = s.substring(5);
					byte byte0 = 4;
				}
				if(s != null && s.startsWith("@cr0@")) {
					s = s.substring(5);
					byte byte0 = 5;
				}
				if(s != null && s.startsWith("@cr3@")) {
					s = s.substring(5);
					byte byte0 = 6;
				}
				if(s != null && s.startsWith("@cr5@")) {
					s = s.substring(5);
					byte byte0 = 7;

				}
				if(s != null && s.startsWith("@cr4@")) {
					s = s.substring(5);
					byte byte0 = 8;
				}
				if(s != null && s.startsWith("@cr6@")) {
					s = s.substring(5);
					byte byte0 = 9;

				}
				if((k == 3 || k == 7) && (k == 7 || privateChatMode == 0 || privateChatMode == 1 && isFriendOrSelf(s)))
				{
					int l = 329 - i * 13;
					if(super.mouseX > 4 && super.mouseY - 4 > l - 10 && super.mouseY - 4 <= l + 3)
					{
						int i1 = aTextDrawingArea_1271.getTextWidth("From:  " + s + chatMessages[j]) + 25;
						if(i1 > 450)
							i1 = 450;
						if(super.mouseX < 4 + i1)
						{
							if(myPrivilege >= 1)
							{
								menuActionName[menuActionRow] = "Report abuse";
								menuActionID[menuActionRow] = 2606;
								menuActionRow++;
							}
				menuActionName[menuActionRow] = "Add ignore";
				menuActionID[menuActionRow] = 2042;
				menuActionRow++;
				menuActionName[menuActionRow] = "Add friend";
				menuActionID[menuActionRow] = 2337;
				menuActionRow++;
						}
					}
					if(++i >= 5)
						return;
				}
				if((k == 5 || k == 6) && privateChatMode < 2 && ++i >= 5)
					return;
			}

	}

	private void method130(int j, int k, int l, int i1, int j1, int k1,
						   int l1, int i2, int j2)
	{
		Class30_Sub1 class30_sub1 = null;
		for(Class30_Sub1 class30_sub1_1 = (Class30_Sub1)aClass19_1179.reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1)aClass19_1179.reverseGetNext())
		{
			if(class30_sub1_1.anInt1295 != l1 || class30_sub1_1.anInt1297 != i2 || class30_sub1_1.anInt1298 != j1 || class30_sub1_1.anInt1296 != i1)
				continue;
			class30_sub1 = class30_sub1_1;
			break;
		}

		if(class30_sub1 == null)
		{
			class30_sub1 = new Class30_Sub1();
			class30_sub1.anInt1295 = l1;
			class30_sub1.anInt1296 = i1;
			class30_sub1.anInt1297 = i2;
			class30_sub1.anInt1298 = j1;
			method89(class30_sub1);
			aClass19_1179.insertHead(class30_sub1);
		}
		class30_sub1.anInt1291 = k;
		class30_sub1.anInt1293 = k1;
		class30_sub1.anInt1292 = l;
		class30_sub1.anInt1302 = j2;
		class30_sub1.anInt1294 = j;
	}

	private boolean interfaceIsSelected(RSInterface class9)
	{
		if(class9.valueCompareType == null)
			return false;
		for(int i = 0; i < class9.valueCompareType.length; i++)
		{
			int j = extractInterfaceValues(class9, i);
			int k = class9.requiredValues[i];
			if(class9.valueCompareType[i] == 2)
			{
				if(j >= k)
					return false;
			} else
			if(class9.valueCompareType[i] == 3)
			{
				if(j <= k)
					return false;
			} else
			if(class9.valueCompareType[i] == 4)
			{
				if(j == k)
					return false;
			} else
			if(j != k)
				return false;
		}

		return true;
	}

	private DataInputStream openJagGrabInputStream(String s)
		throws IOException
	{
 //	   if(!aBoolean872)
 //		   if(signlink.mainapp != null)
 //			   return signlink.openurl(s);
 //		   else
 //			   return new DataInputStream((new URL(getCodeBase(), s)).openStream());
		if(aSocket832 != null)
		{
			try
			{
				aSocket832.close();
				System.out.println("Here5");
			}
			catch(Exception _ex) { }
			aSocket832 = null;
		}
		aSocket832 = openSocket(43594);//43594
		aSocket832.setSoTimeout(10000);
		java.io.InputStream inputstream = aSocket832.getInputStream();
		OutputStream outputstream = aSocket832.getOutputStream();
		outputstream.write(("JAGGRAB /" + s + "\n\n").getBytes());
		return new DataInputStream(inputstream);
	}

	private void doFlamesDrawing()
	{
		char c = '\u0100';
		if(anInt1040 > 0)
		{
			for(int i = 0; i < 256; i++)
				if(anInt1040 > 768)
					anIntArray850[i] = method83(anIntArray851[i], anIntArray852[i], 1024 - anInt1040);
				else
				if(anInt1040 > 256)
					anIntArray850[i] = anIntArray852[i];
				else
					anIntArray850[i] = method83(anIntArray852[i], anIntArray851[i], 256 - anInt1040);

		} else
		if(anInt1041 > 0)
		{
			for(int j = 0; j < 256; j++)
				if(anInt1041 > 768)
					anIntArray850[j] = method83(anIntArray851[j], anIntArray853[j], 1024 - anInt1041);
				else
				if(anInt1041 > 256)
					anIntArray850[j] = anIntArray853[j];
				else
					anIntArray850[j] = method83(anIntArray853[j], anIntArray851[j], 256 - anInt1041);

		} else
		{
			System.arraycopy(anIntArray851, 0, anIntArray850, 0, 256);

		}
		System.arraycopy(aSprite_1201.myPixels, 0, leftSideFlame.anIntArray315, 0, 33920);

		int i1 = 0;
		int j1 = 1152;
		for(int k1 = 1; k1 < c - 1; k1++)
		{
			int l1 = (anIntArray969[k1] * (c - k1)) / c;
			int j2 = 22 + l1;
			if(j2 < 0)
				j2 = 0;
			i1 += j2;
			for(int l2 = j2; l2 < 128; l2++)
			{
				int j3 = anIntArray828[i1++];
				if(j3 != 0)
				{
					int l3 = j3;
					int j4 = 256 - j3;
					j3 = anIntArray850[j3];
					int l4 = leftSideFlame.anIntArray315[j1];
					leftSideFlame.anIntArray315[j1++] = ((j3 & 0xff00ff) * l3 + (l4 & 0xff00ff) * j4 & 0xff00ff00) + ((j3 & 0xff00) * l3 + (l4 & 0xff00) * j4 & 0xff0000) >> 8;
				} else
				{
					j1++;
				}
			}

			j1 += j2;
		}

		leftSideFlame.drawGraphics(0, super.graphics, 0);
		System.arraycopy(aSprite_1202.myPixels, 0, rightSideFlame.anIntArray315, 0, 33920);

		i1 = 0;
		j1 = 1176;
		for(int k2 = 1; k2 < c - 1; k2++)
		{
			int i3 = (anIntArray969[k2] * (c - k2)) / c;
			int k3 = 103 - i3;
			j1 += i3;
			for(int i4 = 0; i4 < k3; i4++)
			{
				int k4 = anIntArray828[i1++];
				if(k4 != 0)
				{
					int i5 = k4;
					int j5 = 256 - k4;
					k4 = anIntArray850[k4];
					int k5 = rightSideFlame.anIntArray315[j1];
					rightSideFlame.anIntArray315[j1++] = ((k4 & 0xff00ff) * i5 + (k5 & 0xff00ff) * j5 & 0xff00ff00) + ((k4 & 0xff00) * i5 + (k5 & 0xff00) * j5 & 0xff0000) >> 8;
				} else
				{
					j1++;
				}
			}

			i1 += 128 - k3;
			j1 += 128 - k3 - i3;
		}

		rightSideFlame.drawGraphics(0, super.graphics, 637);
	}

	private void method134(Stream stream)
	{
		int j = stream.readBits(8);
		if(j < playerCount)
		{
			for(int k = j; k < playerCount; k++)
				anIntArray840[anInt839++] = playerIndices[k];

		}
		if(j > playerCount)
		{
			signlink.reporterror(myUsername + " Too many players");
			throw new RuntimeException("eek");
		}
		playerCount = 0;
		for(int l = 0; l < j; l++)
		{
			int i1 = playerIndices[l];
			Player player = playerArray[i1];
			int j1 = stream.readBits(1);
			if(j1 == 0)
			{
				playerIndices[playerCount++] = i1;
				player.anInt1537 = loopCycle;
			} else
			{
				int k1 = stream.readBits(2);
				if(k1 == 0)
				{
					playerIndices[playerCount++] = i1;
					player.anInt1537 = loopCycle;
					anIntArray894[anInt893++] = i1;
				} else
				if(k1 == 1)
				{
					playerIndices[playerCount++] = i1;
					player.anInt1537 = loopCycle;
					int l1 = stream.readBits(3);
					player.moveInDir(false, l1);
					int j2 = stream.readBits(1);
					if(j2 == 1)
						anIntArray894[anInt893++] = i1;
				} else
				if(k1 == 2)
				{
					playerIndices[playerCount++] = i1;
					player.anInt1537 = loopCycle;
					int i2 = stream.readBits(3);
					player.moveInDir(true, i2);
					int k2 = stream.readBits(3);
					player.moveInDir(true, k2);
					int l2 = stream.readBits(1);
					if(l2 == 1)
						anIntArray894[anInt893++] = i1;
				} else
				if(k1 == 3)
					anIntArray840[anInt839++] = i1;
			}
		}
	}
	
	private void drawLoginScreen(boolean flag) {
		if (normalLogin == true) {
			resetImageProducers();
			loginScreenArea.initDrawingArea();
			titleBox.drawBackground(0, 0);
			char c = '\u0168';
			char c1 = '\310';
			if(loginScreenState == 0) {
				int i = c1 / 2 + 80;
				smallText.method382(0x75a9a9, c / 2, onDemandFetcher.statusString, i, true);
				i = c1 / 2 - 20;
				chatTextDrawingArea.method382(0xffff00, c / 2, "Welcome to Hackerscape", i, true);
				i += 30;
				int l = c / 2 - 80;
				int k1 = c1 / 2 + 20;
				titleButton.drawBackground(l - 73, k1 - 20);
				chatTextDrawingArea.method382(0xffffff, l, "Information", k1 + 5, true);
				l = c / 2 + 80;
				titleButton.drawBackground(l - 73, k1 - 20);
				chatTextDrawingArea.method382(0xffffff, l, "Play", k1 + 5, true);
			}
			if(loginScreenState == 2) {
				int j = c1 / 2 - 40;
				if(loginMessage1.length() > 0) {
					chatTextDrawingArea.method382(0xffff00, c / 2, loginMessage1, j - 15, true);
					chatTextDrawingArea.method382(0xffff00, c / 2, loginMessage2, j, true);
					j += 30;
				} else {
					chatTextDrawingArea.method382(0xffff00, c / 2, loginMessage2, j - 7, true);
					j += 30;
				}
				chatTextDrawingArea.method389(true, c / 2 - 90, 0xffffff, "Username: " + capitalize(myUsername) + ((loginScreenCursorPos == 0) & (loopCycle % 40 < 20) ? "@yel@|" : ""), j);
				j += 15;
				chatTextDrawingArea.method389(true, c / 2 - 88, 0xffffff, "Password: " + TextClass.passwordAsterisks(myPassword) + ((loginScreenCursorPos == 1) & (loopCycle % 40 < 20) ? "@yel@|" : ""), j);
				j += 15;
				if(!flag) {
					int i1 = c / 2 - 80;
					int l1 = c1 / 2 + 50;
					titleButton.drawBackground(i1 - 73, l1 - 20);
					chatTextDrawingArea.method382(0xffffff, i1, "Enter World", l1 + 5, true);
					i1 = c / 2 + 80;
					titleButton.drawBackground(i1 - 73, l1 - 20);
					chatTextDrawingArea.method382(0xffffff, i1, "Return", l1 + 5, true);
				}
			} 
			if(loginScreenState == 3) {
				chatTextDrawingArea.method382(0xffff00, c / 2, "Hackerscape Information", c1 / 2 - 60, true);
				int k = c1 / 2 - 35;
				chatTextDrawingArea.method382(0xffffff, c / 2, "Register on forums", k, true);
				k += 15;
				chatTextDrawingArea.method382(0xffffff, c / 2, "Reporting glitches,", k, true);
				k += 15;
				chatTextDrawingArea.method382(0xffffff, c / 2, "Will be appreciated", k, true);
				k += 15;
				chatTextDrawingArea.method382(0xffffff, c / 2, "To login, go back and click Play.", k, true);
				k += 15;
				int j1 = c / 2;
				int i2 = c1 / 2 + 50;
				titleButton.drawBackground(j1 - 73, i2 - 20);
				chatTextDrawingArea.method382(0xffffff, j1, "Go Back!", i2 + 5, true);
			}
			loginScreenArea.drawGraphics(171, super.graphics, 202);
			if(welcomeScreenRaised) {
				welcomeScreenRaised = false;
				aRSImageProducer_1107.drawGraphics(0, super.graphics, 128);
				aRSImageProducer_1108.drawGraphics(371, super.graphics, 202);
				gameLogo.drawGraphics(265, super.graphics, 0);
				aRSImageProducer_1113.drawGraphics(265, super.graphics, 562);
				aRSImageProducer_1114.drawGraphics(171, super.graphics, 128);
				aRSImageProducer_1115.drawGraphics(171, super.graphics, 562);
			}
		} else if(normalLogin == false) {
			resetImageProducers();
			loginScreenArea.initDrawingArea();
			byte abyte0[] = titleStreamLoader.getDataForName("title.dat");
			Sprite loginArea = new Sprite(abyte0, this);
			loginArea.drawSprite(0,  0);
			
			if(loginScreenState == 0) {
				titleBox1.drawSprite(237-204, 138+25);
				addLoginScreenHover(LOGIN, 8, 287-204, 182+25, 9);
				if (super.saveClickX >= 361-204 && super.saveClickX <= 361-204+34 && super.saveClickY >= 360+15 && super.saveClickY <= 360+15+15) {
				}
				Sprite scrollLine = new Sprite("Login/Random/LINE");
				scrollLine.drawSprite(297-204, 277+25);
				scrollLine.drawSprite(297-204, 356+25);
				smallText.method382(0xffffff, 332-204, "Standard Detail", 351+25, false);
				smallText.method382(0xffffff, 428-204, "High Detail", 351+25, false);	
				if (super.saveClickX >= 306-204 && super.saveClickX <= 362-204 && super.saveClickY >= 303+25 && super.saveClickY <= 336+25) {
					isClicked = 0;
				}
				if(super.saveClickX >= 402-204 && super.saveClickX <= 458-204 && super.saveClickY >= 302+25 && super.saveClickY <= 336+25) {
					isClicked = 1;
				}
				if(isClicked != 0){
					LOGIN[26].drawSprite(401-204, 303+25);
					addLoginScreenHover(LOGIN, 25, 305-204, 303+25, 20);
					LOGIN[22].drawSprite(446-204, 326+25);
				} else if(isClicked == 0) {
					LOGIN[20].drawSprite(305-204, 303+25);
					addLoginScreenHover(LOGIN, 21, 401-204, 303+25, 27);
					LOGIN[22].drawSprite(350-204, 326+25);
				}
				loginScreenArea.drawGraphics(0, super.graphics, 0);
			}
			if(loginScreenState == 1) {
				titleBox1.drawSprite(237-204, 138+25);
				LOGIN[0].drawSprite(333-204, 182+25);
				LOGIN[1].drawSprite(344-204, 253+32);
				LOGIN[2].drawSprite(346-204, 308+40);
				LOGIN[5].drawSprite(290-204, 267+25);
				LOGIN[5].drawSprite(290-204, 329+25);
				addLoginScreenHover(LOGIN, 3, 358-204, 380+25, 4);
				addLoginScreenHover(LOGIN, 6, 338-204, 415+25, 7);
				smallText.method382(0xffffff, 380-204,  "World 1", 212+25, false);
				smallText.method382(0xffe1be, 380-204, myUsername, 238+25, false);
				smallText.method382(0xffe1bef, 380-204, myPassword, 237+25, false);
				chatTextDrawingArea.method389(true, 311-204, 0x461e00, capitalize(myUsername) + ((loginScreenCursorPos == 0) & (loopCycle % 40 < 20) ? "|" : ""), 288+25);
				chatTextDrawingArea.method389(true, 311-204, 0x461e00, TextClass.passwordAsterisks(myPassword) + ((loginScreenCursorPos == 1) & (loopCycle % 40 < 20) ? "|" : ""), 349+25);
				loginScreenArea.drawGraphics(0, super.graphics, 0);
			}
		}
	}

	private void drawFlames()
	{
		if(normalLogin == true) {
		try
		{
			long l = System.currentTimeMillis();
			int i = 0;
			int j = 20;
			while(aBoolean831) 
			{
				anInt1208++;
				if(normalLogin == true) {
					//calcFlamesPosition();
					//calcFlamesPosition();
				}
				doFlamesDrawing();
				if(++i > 10)
				{
					long l1 = System.currentTimeMillis();
					int k = (int)(l1 - l) / 10 - j;
					j = 40 - k;
					if(j < 5)
						j = 5;
					i = 0;
					l = l1;
				}
				try
				{
					Thread.sleep(j);
				}
				catch(Exception _ex) { }
			}
		} catch(Exception _ex) { }
			drawingFlames = false;
		} else if(normalLogin == false) {
		
		}
	}

	public void raiseWelcomeScreen()
	{
		welcomeScreenRaised = true;
	}

	private void method137(Stream stream, int j)
	{
		if(j == 84)
		{
			int k = stream.readUnsignedByte();
			int j3 = anInt1268 + (k >> 4 & 7);
			int i6 = anInt1269 + (k & 7);
			int l8 = stream.readUnsignedWord();
			int k11 = stream.readUnsignedWord();
			int l13 = stream.readUnsignedWord();
			if(j3 >= 0 && i6 >= 0 && j3 < 104 && i6 < 104)
			{
				NodeList class19_1 = groundArray[plane][j3][i6];
				if(class19_1 != null)
				{
					for(Item class30_sub2_sub4_sub2_3 = (Item)class19_1.reverseGetFirst(); class30_sub2_sub4_sub2_3 != null; class30_sub2_sub4_sub2_3 = (Item)class19_1.reverseGetNext())
					{
						if(class30_sub2_sub4_sub2_3.ID != (l8 & 0x7fff) || class30_sub2_sub4_sub2_3.anInt1559 != k11)
							continue;
						class30_sub2_sub4_sub2_3.anInt1559 = l13;
						break;
					}

					spawnGroundItem(j3, i6);
				}
			}
			return;
		}
		if(j == 105)
		{
			int l = stream.readUnsignedByte();
			int k3 = anInt1268 + (l >> 4 & 7);
			int j6 = anInt1269 + (l & 7);
			int i9 = stream.readUnsignedWord();
			int l11 = stream.readUnsignedByte();
			int i14 = l11 >> 4 & 0xf;
			int i16 = l11 & 7;
			if(myPlayer.smallX[0] >= k3 - i14 && myPlayer.smallX[0] <= k3 + i14 && myPlayer.smallY[0] >= j6 - i14 && myPlayer.smallY[0] <= j6 + i14 && aBoolean848 && !lowMem && anInt1062 < 50)
			{
				anIntArray1207[anInt1062] = i9;
				anIntArray1241[anInt1062] = i16;
				anIntArray1250[anInt1062] = Sounds.anIntArray326[i9];
				anInt1062++;
			}
		}
		if(j == 215)
		{
			int i1 = stream.method435();
			int l3 = stream.method428();
			int k6 = anInt1268 + (l3 >> 4 & 7);
			int j9 = anInt1269 + (l3 & 7);
			int i12 = stream.method435();
			int j14 = stream.readUnsignedWord();
			if(k6 >= 0 && j9 >= 0 && k6 < 104 && j9 < 104 && i12 != unknownInt10)
			{
				Item class30_sub2_sub4_sub2_2 = new Item();
				class30_sub2_sub4_sub2_2.ID = i1;
				class30_sub2_sub4_sub2_2.anInt1559 = j14;
				if(groundArray[plane][k6][j9] == null)
					groundArray[plane][k6][j9] = new NodeList();
				groundArray[plane][k6][j9].insertHead(class30_sub2_sub4_sub2_2);
				spawnGroundItem(k6, j9);
			}
			return;
		}
		if(j == 156)
		{
			int j1 = stream.method426();
			int i4 = anInt1268 + (j1 >> 4 & 7);
			int l6 = anInt1269 + (j1 & 7);
			int k9 = stream.readUnsignedWord();
			if(i4 >= 0 && l6 >= 0 && i4 < 104 && l6 < 104)
			{
				NodeList class19 = groundArray[plane][i4][l6];
				if(class19 != null)
				{
					for(Item item = (Item)class19.reverseGetFirst(); item != null; item = (Item)class19.reverseGetNext())
					{
						if(item.ID != (k9 & 0x7fff))
							continue;
						item.unlink();
						break;
					}

					if(class19.reverseGetFirst() == null)
						groundArray[plane][i4][l6] = null;
					spawnGroundItem(i4, l6);
				}
			}
			return;
		}
		if(j == 160)
		{
			int k1 = stream.method428();
			int j4 = anInt1268 + (k1 >> 4 & 7);
			int i7 = anInt1269 + (k1 & 7);
			int l9 = stream.method428();
			int j12 = l9 >> 2;
			int k14 = l9 & 3;
			int j16 = anIntArray1177[j12];
			int j17 = stream.method435();
			if(j4 >= 0 && i7 >= 0 && j4 < 103 && i7 < 103)
			{
				int j18 = intGroundArray[plane][j4][i7];
				int i19 = intGroundArray[plane][j4 + 1][i7];
				int l19 = intGroundArray[plane][j4 + 1][i7 + 1];
				int k20 = intGroundArray[plane][j4][i7 + 1];
				if(j16 == 0)
				{
					Object1 class10 = worldController.method296(plane, j4, i7);
					if(class10 != null)
					{
						int k21 = class10.uid >> 14 & 0x7fff;
						if(j12 == 2)
						{
							class10.aClass30_Sub2_Sub4_278 = new Animable_Sub5(k21, 4 + k14, 2, i19, l19, j18, k20, j17, false);
							class10.aClass30_Sub2_Sub4_279 = new Animable_Sub5(k21, k14 + 1 & 3, 2, i19, l19, j18, k20, j17, false);
						} else
						{
							class10.aClass30_Sub2_Sub4_278 = new Animable_Sub5(k21, k14, j12, i19, l19, j18, k20, j17, false);
						}
					}
				}
				if(j16 == 1)
				{
					Object2 class26 = worldController.method297(j4, i7, plane);
					if(class26 != null)
						class26.aClass30_Sub2_Sub4_504 = new Animable_Sub5(class26.uid >> 14 & 0x7fff, 0, 4, i19, l19, j18, k20, j17, false);
				}
				if(j16 == 2)
				{
					Object5 class28 = worldController.method298(j4, i7, plane);
					if(j12 == 11)
						j12 = 10;
					if(class28 != null)
						class28.aClass30_Sub2_Sub4_521 = new Animable_Sub5(class28.uid >> 14 & 0x7fff, k14, j12, i19, l19, j18, k20, j17, false);
				}
				if(j16 == 3)
				{
					Object3 class49 = worldController.method299(i7, j4, plane);
					if(class49 != null)
						class49.aClass30_Sub2_Sub4_814 = new Animable_Sub5(class49.uid >> 14 & 0x7fff, k14, 22, i19, l19, j18, k20, j17, false);
				}
			}
			return;
		}
		if(j == 147)
		{
			int l1 = stream.method428();
			int k4 = anInt1268 + (l1 >> 4 & 7);
			int j7 = anInt1269 + (l1 & 7);
			int i10 = stream.readUnsignedWord();
			byte byte0 = stream.method430();
			int l14 = stream.method434();
			byte byte1 = stream.method429();
			int k17 = stream.readUnsignedWord();
			int k18 = stream.method428();
			int j19 = k18 >> 2;
			int i20 = k18 & 3;
			int l20 = anIntArray1177[j19];
			byte byte2 = stream.readSignedByte();
			int l21 = stream.readUnsignedWord();
			byte byte3 = stream.method429();
			Player player;
			if(i10 == unknownInt10)
				player = myPlayer;
			else
				player = playerArray[i10];
			if(player != null)
			{
				ObjectDef class46 = ObjectDef.forID(l21);
				int i22 = intGroundArray[plane][k4][j7];
				int j22 = intGroundArray[plane][k4 + 1][j7];
				int k22 = intGroundArray[plane][k4 + 1][j7 + 1];
				int l22 = intGroundArray[plane][k4][j7 + 1];
				Model model = class46.method578(j19, i20, i22, j22, k22, l22, -1);
				if(model != null)
				{
					method130(k17 + 1, -1, 0, l20, j7, 0, plane, k4, l14 + 1);
					player.anInt1707 = l14 + loopCycle;
					player.anInt1708 = k17 + loopCycle;
					player.aModel_1714 = model;
					int i23 = class46.anInt744;
					int j23 = class46.anInt761;
					if(i20 == 1 || i20 == 3)
					{
						i23 = class46.anInt761;
						j23 = class46.anInt744;
					}
					player.anInt1711 = k4 * 128 + i23 * 64;
					player.anInt1713 = j7 * 128 + j23 * 64;
					player.anInt1712 = method42(plane, player.anInt1713, player.anInt1711);
					if(byte2 > byte0)
					{
						byte byte4 = byte2;
						byte2 = byte0;
						byte0 = byte4;
					}
					if(byte3 > byte1)
					{
						byte byte5 = byte3;
						byte3 = byte1;
						byte1 = byte5;
					}
					player.anInt1719 = k4 + byte2;
					player.anInt1721 = k4 + byte0;
					player.anInt1720 = j7 + byte3;
					player.anInt1722 = j7 + byte1;
				}
			}
		}
		if(j == 151)
		{
			int i2 = stream.method426();
			int l4 = anInt1268 + (i2 >> 4 & 7);
			int k7 = anInt1269 + (i2 & 7);
			int j10 = stream.method434();
			int k12 = stream.method428();
			int i15 = k12 >> 2;
			int k16 = k12 & 3;
			int l17 = anIntArray1177[i15];
			if(l4 >= 0 && k7 >= 0 && l4 < 104 && k7 < 104)
				method130(-1, j10, k16, l17, k7, i15, plane, l4, 0);
			return;
		}
		if(j == 4)
		{
			int j2 = stream.readUnsignedByte();
			int i5 = anInt1268 + (j2 >> 4 & 7);
			int l7 = anInt1269 + (j2 & 7);
			int k10 = stream.readUnsignedWord();
			int l12 = stream.readUnsignedByte();
			int j15 = stream.readUnsignedWord();
			if(i5 >= 0 && l7 >= 0 && i5 < 104 && l7 < 104)
			{
				i5 = i5 * 128 + 64;
				l7 = l7 * 128 + 64;
				Animable_Sub3 class30_sub2_sub4_sub3 = new Animable_Sub3(plane, loopCycle, j15, k10, method42(plane, l7, i5) - l12, l7, i5);
				aClass19_1056.insertHead(class30_sub2_sub4_sub3);
			}
			return;
		}
		if(j == 44)
		{
			int k2 = stream.method436();
			int j5 = stream.readUnsignedWord();
			int i8 = stream.readUnsignedByte();
			int l10 = anInt1268 + (i8 >> 4 & 7);
			int i13 = anInt1269 + (i8 & 7);
			if(l10 >= 0 && i13 >= 0 && l10 < 104 && i13 < 104)
			{
				Item class30_sub2_sub4_sub2_1 = new Item();
				class30_sub2_sub4_sub2_1.ID = k2;
				class30_sub2_sub4_sub2_1.anInt1559 = j5;
				if(groundArray[plane][l10][i13] == null)
					groundArray[plane][l10][i13] = new NodeList();
				groundArray[plane][l10][i13].insertHead(class30_sub2_sub4_sub2_1);
				spawnGroundItem(l10, i13);
			}
			return;
		}
		if(j == 101)
		{
			int l2 = stream.method427();
			int k5 = l2 >> 2;
			int j8 = l2 & 3;
			int i11 = anIntArray1177[k5];
			int j13 = stream.readUnsignedByte();
			int k15 = anInt1268 + (j13 >> 4 & 7);
			int l16 = anInt1269 + (j13 & 7);
			if(k15 >= 0 && l16 >= 0 && k15 < 104 && l16 < 104)
				method130(-1, -1, j8, i11, l16, k5, plane, k15, 0);
			return;
		}
		if(j == 117)
		{
			int i3 = stream.readUnsignedByte();
			int l5 = anInt1268 + (i3 >> 4 & 7);
			int k8 = anInt1269 + (i3 & 7);
			int j11 = l5 + stream.readSignedByte();
			int k13 = k8 + stream.readSignedByte();
			int l15 = stream.readSignedWord();
			int i17 = stream.readUnsignedWord();
			int i18 = stream.readUnsignedByte() * 4;
			int l18 = stream.readUnsignedByte() * 4;
			int k19 = stream.readUnsignedWord();
			int j20 = stream.readUnsignedWord();
			int i21 = stream.readUnsignedByte();
			int j21 = stream.readUnsignedByte();
			if(l5 >= 0 && k8 >= 0 && l5 < 104 && k8 < 104 && j11 >= 0 && k13 >= 0 && j11 < 104 && k13 < 104 && i17 != 65535)
			{
				l5 = l5 * 128 + 64;
				k8 = k8 * 128 + 64;
				j11 = j11 * 128 + 64;
				k13 = k13 * 128 + 64;
				Animable_Sub4 class30_sub2_sub4_sub4 = new Animable_Sub4(i21, l18, k19 + loopCycle, j20 + loopCycle, j21, plane, method42(plane, k8, l5) - i18, k8, l5, l15, i17);
				class30_sub2_sub4_sub4.method455(k19 + loopCycle, k13, method42(plane, k13, j11) - l18, j11);
				aClass19_1013.insertHead(class30_sub2_sub4_sub4);
			}
		}
	}

	private static void setLowMem()
	{
		WorldController.lowMem = true;
		Texture.lowMem = true;
		lowMem = true;
		ObjectManager.lowMem = true;
		ObjectDef.lowMem = true;
	}

	private void method139(Stream stream)
	{
		stream.initBitAccess();
		int k = stream.readBits(8);
		if(k < npcCount)
		{
			for(int l = k; l < npcCount; l++)
				anIntArray840[anInt839++] = npcIndices[l];

		}
		if(k > npcCount)
		{
			signlink.reporterror(myUsername + " Too many npcs");
			throw new RuntimeException("eek");
		}
		npcCount = 0;
		for(int i1 = 0; i1 < k; i1++)
		{
			int j1 = npcIndices[i1];
			NPC npc = npcArray[j1];
			int k1 = stream.readBits(1);
			if(k1 == 0)
			{
				npcIndices[npcCount++] = j1;
				npc.anInt1537 = loopCycle;
			} else
			{
				int l1 = stream.readBits(2);
				if(l1 == 0)
				{
					npcIndices[npcCount++] = j1;
					npc.anInt1537 = loopCycle;
					anIntArray894[anInt893++] = j1;
				} else
				if(l1 == 1)
				{
					npcIndices[npcCount++] = j1;
					npc.anInt1537 = loopCycle;
					int i2 = stream.readBits(3);
					npc.moveInDir(false, i2);
					int k2 = stream.readBits(1);
					if(k2 == 1)
						anIntArray894[anInt893++] = j1;
				} else
				if(l1 == 2)
				{
					npcIndices[npcCount++] = j1;
					npc.anInt1537 = loopCycle;
					int j2 = stream.readBits(3);
					npc.moveInDir(true, j2);
					int l2 = stream.readBits(3);
					npc.moveInDir(true, l2);
					int i3 = stream.readBits(1);
					if(i3 == 1)
						anIntArray894[anInt893++] = j1;
				} else
				if(l1 == 3)
					anIntArray840[anInt839++] = j1;
			}
		}

	}

	private void processLoginScreenInput() {
		//if(normalLogin == true) {
			if(loginScreenState == 0) {
				int i = super.myWidth / 2 - 80;
				int l = super.myHeight / 2 + 20;
				l += 20;
				if(super.clickMode3 == 1 && super.saveClickX >= i - 75 && super.saveClickX <= i + 75 && super.saveClickY >= l - 20 && super.saveClickY <= l + 20) {
					loginScreenState = 3;
					loginScreenCursorPos = 0;
				}
				i = super.myWidth / 2 + 80;
				if(super.clickMode3 == 1 && super.saveClickX >= i - 75 && super.saveClickX <= i + 75 && super.saveClickY >= l - 20 && super.saveClickY <= l + 20) {
					loginMessage1 = "";
					loginMessage2 = "Enter your username & password.";
					loginScreenState = 2;
					loginScreenCursorPos = 0;
				}
			} else {
				if(loginScreenState == 2) {
					int j = super.myHeight / 2 - 40;
					j += 30;
					j += 25;
					if(super.clickMode3 == 1 && super.saveClickY >= j - 15 && super.saveClickY < j)
						loginScreenCursorPos = 0;
					j += 15;
					if(super.clickMode3 == 1 && super.saveClickY >= j - 15 && super.saveClickY < j)
						loginScreenCursorPos = 1;
					j += 15;
					int i1 = super.myWidth / 2 - 80;
					int k1 = super.myHeight / 2 + 50;
					k1 += 20;
					if(super.clickMode3 == 1 && super.saveClickX >= i1 - 75 && super.saveClickX <= i1 + 75 && super.saveClickY >= k1 - 20 && super.saveClickY <= k1 + 20) {
						loginFailures = 0;
						login(capitalize(myUsername), myPassword, false);
						if(loggedIn)
							return;
					}
					i1 = super.myWidth / 2 + 80;
					if(super.clickMode3 == 1 && super.saveClickX >= i1 - 75 && super.saveClickX <= i1 + 75 && super.saveClickY >= k1 - 20 && super.saveClickY <= k1 + 20) {
						loginScreenState = 0;
						myUsername = "";
						myPassword = "";
					}
					do {
						int l1 = readChar(-796);
						if(l1 == -1)
							break;
						boolean flag1 = false;
						for(int i2 = 0; i2 < validUserPassChars.length(); i2++) {
							if(l1 != validUserPassChars.charAt(i2))
								continue;
							flag1 = true;
							break;
						} if(loginScreenCursorPos == 0) {
							if(l1 == 8 && myUsername.length() > 0)
								myUsername = myUsername.substring(0, myUsername.length() - 1);
							if(l1 == 9 || l1 == 10 || l1 == 13)
								loginScreenCursorPos = 1;
							if(flag1)
								myUsername += (char)l1;
							if(myUsername.length() > 12)
								myUsername = capitalize(myUsername.substring(0, 12));
						} else if(loginScreenCursorPos == 1) {
							if(l1 == 8 && myPassword.length() > 0)
								myPassword = myPassword.substring(0, myPassword.length() - 1);
							if(l1 == 9 || l1 == 10 || l1 == 13)
								loginScreenCursorPos = 0;
							if(flag1)
								myPassword += (char)l1;
							if(myPassword.length() > 20)
								myPassword = myPassword.substring(0, 20);
						}
					} while(true);
					return;
				}
				if(loginScreenState == 3) {
					int k = super.myWidth / 2;
					int j1 = super.myHeight / 2 + 50;
					j1 += 20;
					if(super.clickMode3 == 1 && super.saveClickX >= k - 75 && super.saveClickX <= k + 75 && super.saveClickY >= j1 - 20 && super.saveClickY <= j1 + 20)
						loginScreenState = 0;
				}
			}
		//} else if(normalLogin == false) {
		
		/** Custom Login Screen areas and crap */
		//}
	}

	private void markMinimap(Sprite sprite, int i, int j, boolean flag) {
		int k = minimapInt1 + minimapInt2 & 0x7ff;
		int l = i * i + j * j;
		if(flag)
            return;
		if(l > 6400)
			return;
		int i1 = Model.modelIntArray1[k];
		int j1 = Model.modelIntArray2[k];
		i1 = (i1 * 256) / (minimapInt3 + 256);
		j1 = (j1 * 256) / (minimapInt3 + 256);
		int k1 = j * i1 + i * j1 >> 16;
		int l1 = j * j1 - i * i1 >> 16;
		if(is480 == true || is508 == true || is525 == true || is562 == true) {
			sprite.drawSprite(((106 + k1) - sprite.maxWidth / 2) + 4 , 89 - l1 - sprite.maxHeight / 2 - 4);
		} else {
			sprite.drawSprite(((122 + k1) - sprite.maxWidth / 2) + 4 , 89 - l1 - sprite.maxHeight / 2 - 4);
		}
		CustomMapback[getSpriteID()].drawSprite(0, 0);
	}
	
	public int flagPos = 72;
	public int runState = 1;
	public boolean logHover = false;
	public boolean xpHover = false;
	public boolean xpClicked = false;
	public boolean drawXpBar = false;
	public boolean drawFlag = false;
	public int xpToDraw = 0;
	public int testXp = 0;
	public Sprite[] globe = new Sprite[3];
	public Sprite[] ORBS = new Sprite[18];
	public Sprite[] LOGOUT = new Sprite[5];
	public Sprite xpOrb;
	public Sprite sprite1;
	public Sprite xpFlag;
	
	public static boolean globeState[] = {
		false, false
	};
	public void drawXpOrb() {
	if(super.mouseX >= 516 && super.mouseX <= 550 && super.mouseY >= 46 && super.mouseY <= 80){
		xpHover = true;
	} else {
		xpHover = false;
	}
	if(super.clickMode3 == 1 && super.saveClickX >= 516 && super.saveClickX <= 550 && super.saveClickY >= 46 && super.saveClickY <= 80) {
		if(!xpClicked) {
			xpClicked = true;
			drawXpBar = true;
		} else {
			xpClicked = false;
			drawXpBar = false;
		}
	}
		if(!xpClicked) {
			if(!xpHover) {
				ORBS[17].drawSprite(0, 46);
			} else {
				ORBS[16].drawSprite(0, 46);
			}
		} else {
			if(!xpHover) {
				ORBS[17].drawSprite(0, 46);
			} else {
				ORBS[16].drawSprite(0, 46);
			}
		}
	}
	public void drawGlobe(){
		if(is508 == true || is525 == true || is562 == true) {
			if(super.clickMode3 == 1){
				if(super.saveClickX >= 522 && super.saveClickX <= 558 && super.saveClickY >= 124 && super.saveClickY < 161){
					if(globeState[0]){
							globeState[0] = false;
						} else {
							globeState[0] = true;
						}
				}
			}
			if(super.mouseX >= 522 && super.mouseX <= 558 && super.mouseY >= 124 && super.mouseY < 161){
					globeState[1] = true;
				} else {
					globeState[1] = false;
			}
		} else {
		}
	}
	
	public void drawLogout(){
		if(is562) {
			if(!logHover){
				LOGOUT[0].drawSprite(226, 1);
			} else {
				LOGOUT[1].drawSprite(226, 1);
			}
			if(super.clickMode2 == 1 && super.mouseX >= 765-24 && super.mouseX <= 765 && super.mouseY >= 1 && super.mouseY <= 25){
				LOGOUT[2].drawSprite(226, 1);
			}
		}
	}

	public void loadOrbs() {
drawCoinOrb();
		drawLogout();
		drawHP();
		if(is562 || is525){
		drawXpOrb();
		}
		drawPrayer();
		drawRunOrb();
		if(is508 == true) {
			globe[0].drawSprite(10, 123);
		} else if(is525 == true || is562 == true) {
			drawGlobe();
			if (globeState[0] && globeState[1]) {
				globe[2].drawSprite(10, 123);
			} else if (globeState[1]) {
				globe[2].drawSprite(10, 123);
			} else if (globeState[0]) {
				globe[1].drawSprite(10, 123);
			} else {
				globe[1].drawSprite(10, 123);
			}
		}
	}
	public Sprite[] Black = new Sprite[5];
	
	public void loadExtraSprites(){
magicAuto = new Sprite("Misc/magicAuto");
		sprite1 = new Sprite("Gameframe/sprite1");
		xpFlag = new Sprite("Gameframe/xpFlag");
		for(int i = 1; i <= 17; i++) {
			ORBS[i] = new Sprite("Gameframe/Orbs/ORBS "+i+"");
		}
		for(int i = 0; i <= 4;i++) {
			LOGOUT[i] = new Sprite("Gameframe/Orbs/X "+i+"");
		}
		for (int j3 = 0; j3 <= 26; j3++) {
			LOGIN[j3] = new Sprite("Login/Buttons/BUTTON "+j3+"");
		}
		for (int i = 0; i <= 2; i++) {
			globe[i] = new Sprite("Gameframe/Globe "+i+"");
		}
		for(int i = 0; i <= 4; i++){
			chatArea[i] = new Sprite("Gameframe/Gameframes/chatArea "+i+"");
		}
		for(int i = 0; i <= 4; i++){
			tabArea[i] = new Sprite("Gameframe/Gameframes/tabArea "+i+"");
		}
		for(int i = 0; i <= 4; i++){
			CustomMapback[i] = new Sprite("Gameframe/Gameframes/Mapback "+i+"");
		}
		for(int i = 0; i <= 6; i++){
			modIcons[i] = new Sprite("Player/MODICONS "+i+"");
		}
		for(int i = 0; i <= 6; i++){
			chatImages[i] = new Sprite("Player/MODICONS "+i+"");
		}
		for(int i = 0; i <= 4; i++){
			Black[i] = new Sprite("Gameframe/Gameframes/Black "+i+"");
		}
		for(int i4 = 0; i4 < 3; i4++) {
			hitMark[i4] = new Sprite("Player/Hits "+i4+"");
		}
		qc = new Sprite("Gameframe/Quickchat");
	}

	public boolean restOrb = false;
	public boolean musicOrb = false;
	public boolean prayClicked = false;
	public boolean prayHover = false;
	public boolean runClicked = true;
	public boolean runHover = false;
	public Sprite magicAuto;
	public boolean Autocast = false;
	public int autocastId = 0;

	private void rightClickChatButtons() { 
		if(is480 == true || is508 == true || is525 == true || is562 == true) {
			if(super.mouseX >= 5 && super.mouseX <= 61 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "View All";
				menuActionID[1] = 999;
				menuActionRow = 2;
			} else if(super.mouseX >= 62 && super.mouseX <= 117 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "View Game";
				menuActionID[1] = 998;
				menuActionRow = 2;
			} else if(super.mouseX >= 119 && super.mouseX <= 174 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "Hide public";
				menuActionID[1] = 997;
				menuActionName[2] = "Off public";
				menuActionID[2] = 996;
				menuActionName[3] = "Friends public";
				menuActionID[3] = 995;
				menuActionName[4] = "On public";
				menuActionID[4] = 994;
				menuActionName[5] = "View public";
				menuActionID[5] = 993;
				menuActionRow = 6;
			} else if(super.mouseX >= 176 && super.mouseX <= 231 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "Off private";
				menuActionID[1] = 992;
				menuActionName[2] = "Friends private";
				menuActionID[2] = 991;
				menuActionName[3] = "On private";
				menuActionID[3] = 990;
				menuActionName[4] = "View private";
				menuActionID[4] = 989;
				menuActionRow = 5;
			} else if(super.mouseX >= 233 && super.mouseX <= 288 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "Off clan chat";
				menuActionID[1] = 1003;
				menuActionName[2] = "Friends clan chat";
				menuActionID[2] = 1002;
				menuActionName[3] = "On clan chat";
				menuActionID[3] = 1001;
				menuActionName[4] = "View clan chat";
				menuActionID[4] = 1000;
				menuActionRow = 5;
			} else if(super.mouseX >= 290 && super.mouseX <= 345 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "Off trade";
				menuActionID[1] = 987;
				menuActionName[2] = "Friends trade";
				menuActionID[2] = 986;
				menuActionName[3] = "On trade";
				menuActionID[3] = 985;
				menuActionName[4] = "View trade";
				menuActionID[4] = 984;
				menuActionRow = 5;
			} else if(super.mouseX >= 347 && super.mouseX <= 402 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "Off assist";
				menuActionID[1] = -1;
				menuActionName[2] = "Friends assist";
				menuActionID[2] = -1;
				menuActionName[3] = "On assist";
				menuActionID[3] = -1;
				menuActionName[4] = "View assist";
				menuActionID[4] = -1;
				menuActionRow = 5;
			} else if(super.mouseX >= 404 && super.mouseX <= 514 && super.mouseY >= 480 && super.mouseY <= 501) {
				menuActionName[1] = "Report Abuse";
				menuActionID[1] = 606;
				menuActionRow = 2;
			}
		} else if(is474) {
			if(super.mouseX >= 5 && super.mouseX <= 61 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "View All";
				menuActionID[1] = 999;
				menuActionRow = 2;
			} else if(super.mouseX >= 71 && super.mouseX <= 127 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "View Game";
				menuActionID[1] = 998;
				menuActionRow = 2;
			} else if(super.mouseX >= 137 && super.mouseX <= 193 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "Hide public";
				menuActionID[1] = 997;
				menuActionName[2] = "Off public";
				menuActionID[2] = 996;
				menuActionName[3] = "Friends public";
				menuActionID[3] = 995;
				menuActionName[4] = "On public";
				menuActionID[4] = 994;
				menuActionName[5] = "View public";
				menuActionID[5] = 993;
				menuActionRow = 6;
			} else if(super.mouseX >= 203 && super.mouseX <= 259 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "Off private";
				menuActionID[1] = 992;
				menuActionName[2] = "Friends private";
				menuActionID[2] = 991;
				menuActionName[3] = "On private";
				menuActionID[3] = 990;
				menuActionName[4] = "View private";
				menuActionID[4] = 989;
				menuActionRow = 5;
			} else if(super.mouseX >= 269 && super.mouseX <= 325 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "Off clan chat";
				menuActionID[1] = 1003;
				menuActionName[2] = "Friends clan chat";
				menuActionID[2] = 1002;
				menuActionName[3] = "On clan chat";
				menuActionID[3] = 1001;
				menuActionName[4] = "View clan chat";
				menuActionID[4] = 1000;
				menuActionRow = 5;
			} else if(super.mouseX >= 335 && super.mouseX <= 391 && super.mouseY >= 482 && super.mouseY <= 503) {
				menuActionName[1] = "Off trade";
				menuActionID[1] = 987;
				menuActionName[2] = "Friends trade";
				menuActionID[2] = 986;
				menuActionName[3] = "On trade";
				menuActionID[3] = 985;
				menuActionName[4] = "View trade";
				menuActionID[4] = 984;
				menuActionRow = 5;
			} else if(super.mouseX >= 404 && super.mouseX <= 514 && super.mouseY >= 480 && super.mouseY <= 501) {
				menuActionName[1] = "Report Abuse";
				menuActionID[1] = 606;
				menuActionRow = 2;
			}
		} else {
		
		}
		
	}
	
	public void determineTopTabs(){
		if(is474 || is480 || is508 || is525) {
			if(super.mouseX >= 522 && super.mouseX <= 559 && super.mouseY >= 168 && super.mouseY < 203) {
				menuActionName[1] = "Combat Options";
				menuActionID[1] = 1021;
				menuActionRow = 2;
			} else if(super.mouseX >= 560 && super.mouseX <= 592 && super.mouseY >= 168 && super.mouseY < 203) {
				menuActionName[1] = "Stats";
				menuActionID[1] = 1022;
				menuActionRow = 2;
			} else if(super.mouseX >= 593 && super.mouseX <= 625 && super.mouseY >= 168 && super.mouseY < 203) {
				menuActionName[1] = "Quest List";
				menuActionID[1] = 1023;
				menuActionRow = 2;
			} else if(super.mouseX >= 626 && super.mouseX <= 658 && super.mouseY >= 168 && super.mouseY < 203) {
				menuActionName[1] = "Inventory";
				menuActionID[1] = 1024;
				menuActionRow = 2;
			} else if(super.mouseX >= 659 && super.mouseX <= 691 && super.mouseY >= 168 && super.mouseY < 203) {
				menuActionName[1] = "Worn Equipment";
				menuActionID[1] = 1030;
				menuActionRow = 2;
			} else if(super.mouseX >= 692 && super.mouseX <= 724 && super.mouseY >= 168 && super.mouseY < 203) {
				menuActionName[1] = "Prayer";
				menuActionID[1] = 1026;
				menuActionRow = 2;
			} else if(super.mouseX >= 725 && super.mouseX <= 762 && super.mouseY >= 168 && super.mouseY < 203) {
				menuActionName[1] = "Magic";
				menuActionID[1] = 1027;
				menuActionRow = 2;
			}
		} else if(is562) {
		
		}
	}
	
	public void determineBottomTabs(){
		if(is480 || is508 || is525) {
			if(super.mouseX >= 522 && super.mouseX <= 559 && super.mouseY >= 466 && super.mouseY < 503) {
				menuActionName[1] = "Logout";
				menuActionID[1] = 1502;
				menuActionRow = 2;
			} else if(super.mouseX >= 626 && super.mouseX <= 658 && super.mouseY >= 466 && super.mouseY < 503) {
				menuActionName[1] = "Clan Chat";
				menuActionID[1] = 1008;
				menuActionRow = 2;
			} else if(super.mouseX >= 742 && super.mouseX <= 764 && super.mouseY >= 1 && super.mouseY <= 23) {
				menuActionName[1] = "Logout";
				menuActionID[1] = 1011;
				menuActionRow = 2;
			}
		} else if(is474 && !is562) {
			if(super.mouseX >= 522 && super.mouseX <= 559 && super.mouseY >= 466 && super.mouseY < 503) {
				menuActionName[1] = "Clan Chat";
				menuActionID[1] = 1008;
				menuActionRow = 2;
			} else if(super.mouseX >= 626 && super.mouseX <= 658 && super.mouseY >= 466 && super.mouseY < 503) {
				menuActionName[1] = "Logout";
				menuActionID[1] = 1011;
				menuActionRow = 2;
			}
		} else if(is562){
		    if(super.mouseX >= 521 && super.mouseX <= 550 && super.mouseY >= 169 && super.mouseY < 205) {
            tabHPos = 0;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 552 && super.mouseX <= 581 && super.mouseY >= 168 && super.mouseY < 205) {
            tabHPos = 1;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 582 && super.mouseX <= 611 && super.mouseY >= 168 && super.mouseY < 205) {
            tabHPos = 2;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 612 && super.mouseX <= 641 && super.mouseY >= 168 && super.mouseY < 203) {
            tabHPos = 3;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 642 && super.mouseX <= 671 && super.mouseY >= 168 && super.mouseY < 205) {
            tabHPos = 4;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 672 && super.mouseX <= 701 && super.mouseY >= 168 && super.mouseY < 205) {
            tabHPos = 5;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 702 && super.mouseX <= 731 && super.mouseY >= 169 && super.mouseY < 205) {
            tabHPos = 6;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 732 && super.mouseX <= 761 && super.mouseY >= 169 && super.mouseY < 205) {
            tabHPos = 7;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 522 && super.mouseX <= 551 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 15;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 552 && super.mouseX <= 581 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 8;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 582 && super.mouseX <= 611 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 9;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 612 && super.mouseX <= 641 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 10;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 642 && super.mouseX <= 671 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 11;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 672 && super.mouseX <= 701 && super.mouseY >= 466 && super.mouseY < 503) {
            tabHPos = 12;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 702 && super.mouseX <= 731 && super.mouseY >= 466 && super.mouseY < 502) {
            tabHPos = 13;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else if(super.mouseX >= 732 && super.mouseX <= 761 && super.mouseY >= 466 && super.mouseY < 502) {
            tabHPos = 14;
            needDrawTabArea = true;
            tabAreaAltered = true;
        } else {
            tabHPos = -1;
            needDrawTabArea = true;
            tabAreaAltered = true;
        }
        if(super.clickMode3 == 1) {
            if(super.saveClickX >= 522 && super.saveClickX <= 551 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[0] != -1) {
                needDrawTabArea = true;
                tabID = 0;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 552 && super.saveClickX <= 581 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[1] != -1) {
                needDrawTabArea = true;
                tabID = 1;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 582 && super.saveClickX <= 611 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[2] != -1) {
                needDrawTabArea = true;
                tabID = 2;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 612 && super.saveClickX <= 641 && super.saveClickY >= 168 && super.saveClickY < 203 && tabInterfaceIDs[14] != -1) {
                needDrawTabArea = true;
                tabID = 14;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 642 && super.saveClickX <= 671 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[3] != -1) {
                needDrawTabArea = true;
                tabID = 3;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 672 && super.saveClickX <= 701 && super.saveClickY >= 168 && super.saveClickY < 205 && tabInterfaceIDs[4] != -1) {
                needDrawTabArea = true;
                tabID = 4;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 702 && super.saveClickX <= 731 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[5] != -1) {
                needDrawTabArea = true;
                tabID = 5;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 732 && super.saveClickX <= 761 && super.saveClickY >= 169 && super.saveClickY < 205 && tabInterfaceIDs[6] != -1) {
                needDrawTabArea = true;
                tabID = 6;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 522 && super.saveClickX <= 551 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[16] != -1) {
                needDrawTabArea = true;
                tabID = 16;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 552 && super.saveClickX <= 581 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[8] != -1) {
                needDrawTabArea = true;
                tabID = 8;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 582 && super.saveClickX <= 611 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[9] != -1) {
                needDrawTabArea = true;
                tabID = 9;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 612 && super.saveClickX <= 641 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[7] != -1) {
                needDrawTabArea = true;
                tabID = 7;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 642 && super.saveClickX <= 671 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[11] != -1) {
                needDrawTabArea = true;
                tabID = 11;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 672 && super.saveClickX <= 701 && super.saveClickY >= 466 && super.saveClickY < 503 && tabInterfaceIDs[12] != -1) {
                needDrawTabArea = true;
                tabID = 12;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 702 && super.saveClickX <= 731 && super.saveClickY >= 466 && super.saveClickY < 502 && tabInterfaceIDs[13] != -1) {
                needDrawTabArea = true;
                tabID = 13;
                tabAreaAltered = true;
            } else if(super.saveClickX >= 732 && super.saveClickX <= 761 && super.saveClickY >= 466 && super.saveClickY < 502 && tabInterfaceIDs[15] != -1) {
                needDrawTabArea = true;
                tabID = 15;
                tabAreaAltered = true;
            }
			}
		
		}
		if(is474 || is480 || is508 || is525) {
			if(super.mouseX >= 560 && super.mouseX <= 592 && super.mouseY >= 466 && super.mouseY < 503) {
				menuActionName[1] = "Friend List";
				menuActionID[1] = 1009;
				menuActionRow = 2;
			} else if(super.mouseX >= 593 && super.mouseX <= 625 && super.mouseY >= 466 && super.mouseY < 503) {
				menuActionName[1] = "Ignore List";
				menuActionID[1] = 1010;
				menuActionRow = 2;
			} else if(super.mouseX >= 659 && super.mouseX <= 691 && super.mouseY >= 466 && super.mouseY < 503) {
				menuActionName[1] = "Options";
				menuActionID[1] = 1012;
				menuActionRow = 2;
			} else if(super.mouseX >= 692 && super.mouseX <= 724 && super.mouseY >= 466 && super.mouseY < 503) {
				menuActionName[1] = "Emotes";
				menuActionID[1] = 1013;
				menuActionRow = 2;
			} else if(super.mouseX >= 725 && super.mouseX <= 762 && super.mouseY >= 466 && super.mouseY < 503) {
				menuActionName[1] = "Music Player";
				menuActionID[1] = 1014;
				menuActionRow = 2;
			}
		}
	}
	
	private void processMinimapActions() {
	
	 
	
		if(is508 == true || is525 == true || is562 == true) {
			if(super.mouseX >= 527 && super.mouseX <= 560 && super.mouseY >= 126 && super.mouseY <= 159) {
				menuActionName[1] = "Forums";
				menuActionID[1] = 1005;
				menuActionRow = 2;
			}
			if(super.mouseX >= 706 && super.mouseX <= 762 && super.mouseY >= 95 && super.mouseY < 128){
				if(!runClicked){
					menuActionName[2] = "Toggle Run-Mode On";
				} else if(runClicked){
					menuActionName[2] = "Toggle Run-Mode Off";
				}
				menuActionID[2] = 1050;
				menuActionRow = 2;
				menuActionName[1] = "Rest";
				menuActionID[1] = 1501;
				menuActionRow = 3;
			}
			if(super.mouseX >= 516 && super.mouseX <= 550 && super.mouseY >= 46 && super.mouseY < 80){
				menuActionName[2] = "Toggle XP Total";
				menuActionID[2] = 1503;
				menuActionRow = 2;
				menuActionName[1] = "Reset XP Total";
				menuActionID[1] = 1504;
				menuActionRow = 3;
			}
			if(super.mouseX >= 706 && super.mouseX <= 762 && super.mouseY >= 52 && super.mouseY < 87){
				if(!prayClicked){
					menuActionName[1] = "Toggle Quick-Prayers On";
				} else if(prayClicked){
					menuActionName[1] = "Toggle Quick-Prayers Off";
				}
				menuActionID[1] = 1500;
				menuActionRow = 2;
			}
			if(super.mouseX >= 706 && super.mouseX <= 762 && super.mouseY >= 52 && super.mouseY < 87){
				if(!prayClicked){
					menuActionName[1] = "Toggle Quick-Prayers On";
				} else if(prayClicked){
					menuActionName[1] = "Toggle Quick-Prayers Off";
				}
				menuActionID[1] = 1500;
				menuActionRow = 2;
			}
		}
	}
	
	public int getOrbTextColor(int statusInt){
		if(statusInt >= 75 && statusInt <= 100){
			return 0x00FF00;
		} else if(statusInt >= 50 && statusInt <= 74){
			return 0xFFFF00;
		} else if(statusInt >= 25 && statusInt <= 49){
			return 0xFF981F;
		} else {
			return 0xFF0000;
		}
	}
	
	public int getOrbFill(int statusInt){
		if(statusInt <= 100 && statusInt >= 97) {
			return 0; 
		} else if(statusInt <= 96 && statusInt >= 93) {
			return 1;
		} else if(statusInt <= 92 && statusInt >= 89) {
			return 2;
		} else if(statusInt <= 88 && statusInt >= 85) {
			return 3;
		} else if(statusInt <= 84 && statusInt >= 81) {
			return 4;
		} else if(statusInt <= 80 && statusInt >= 77) {
			return 5;
		} else if(statusInt <= 76 && statusInt >= 73) {
			return 6;
		} else if(statusInt <= 72 && statusInt >= 69) {
			return 7;
		} else if(statusInt <= 68 && statusInt >= 65) {
			return 8;
		} else if(statusInt <= 64 && statusInt >= 61) {
			return 9;
		} else if(statusInt <= 60 && statusInt >= 57) {
			return 10;
		} else if(statusInt <= 56 && statusInt >= 53) {
			return 11;
		} else if(statusInt <= 52 && statusInt >= 49) {
			return 12;
		} else if(statusInt <= 48 && statusInt >= 45) {
			return 13;
		} else if(statusInt <= 44 && statusInt >= 41) {
			return 14;
		} else if(statusInt <= 40 && statusInt >= 37) {
			return 15;
		} else if(statusInt <= 36 && statusInt >= 33) {
			return 16;
		} else if(statusInt <= 32 && statusInt >= 29) {
			return 17;
		} else if(statusInt <= 28 && statusInt >= 25) {
			return 18;
		} else if(statusInt <= 24 && statusInt >= 21) {
			return 19;
		} else if(statusInt <= 20 && statusInt >= 17) {
			return 20;
		} else if(statusInt <= 16 && statusInt >= 13) {
			return 21;
		} else if(statusInt <= 12 && statusInt >= 9) {
			return 22;
		} else if(statusInt <= 8 && statusInt >= 7) {
			return 23;
		} else if(statusInt <= 6 && statusInt >= 5) {
			return 24;
		} else if(statusInt <= 4 && statusInt >= 3) {
			return 25;
		} else if(statusInt <= 2 && statusInt >= 1) {
			return 26;
		} else if(statusInt <= 0) {
			return 27;
		}
		return 0;
	}
	
	public void drawHP() {
		int health;
		String OrbDirectory = signlink.findcachedir()+"/Sprites/Gameframe/Orbs/";
		String cHP = RSInterface.interfaceCache[7175].disabledMessage; cHP = cHP.replaceAll("%","");
		int currentHP = Integer.parseInt(cHP);
		String mHP = RSInterface.interfaceCache[7176].disabledMessage; mHP = mHP.replaceAll("%","");
		int maxHP2 = Integer.parseInt(mHP);
		health = (int)(((double)currentHP / (double)maxHP2) * 100D);
		ORBS[0] = new Sprite(OrbDirectory+"ORBS 0.PNG", 27, getOrbFill(health));
		if(is480){
			ORBS[14].drawSprite(174, 14);
		} else {
			ORBS[1].drawSprite(174, 14);
		}
		ORBS[2].drawSprite(177, 17);
		ORBS[0].drawSprite(177, 17);
		if(health <= 20){
			if(loopCycle % 20 < 10){
				ORBS[3].drawSprite(183, 25);
			}
		} else {
			ORBS[3].drawSprite(183, 25);
		}
		int Y = 40;
		if(is480){
			Y = 43;
		}
		smallText.method382(getOrbTextColor(health), 218, RSInterface.interfaceCache[7175].disabledMessage+"0", Y, true);
	}
	
	public void drawPrayer() {
		int prayer;
		String OD = signlink.findcachedir()+"/Sprites/Gameframe/Orbs/";
		String cPR = RSInterface.interfaceCache[7167].disabledMessage;
		int currentPR = Integer.parseInt(cPR);
		String mPR = RSInterface.interfaceCache[7168].disabledMessage;
		int maxPR2 = Integer.parseInt(mPR);
		prayer = (int)(((double)currentPR / (double)maxPR2) * 100D);
		ORBS[0] = new Sprite(OD+"ORBS 0.PNG", 27, getOrbFill(prayer));
		if(!prayClicked){
			if(!is480){
				if(prayHover){
					ORBS[7].drawSprite(190, 53);
				}else if(!prayHover){
					ORBS[1].drawSprite(190, 53);
				}
			} else {
				if(prayHover){
					ORBS[15].drawSprite(190, 53);
				} else {
					ORBS[14].drawSprite(190, 53);
				} 		
			}	
			ORBS[4].drawSprite(193, 56);
		} else if(prayClicked){
			if(!is480){
				if(prayHover){
					ORBS[7].drawSprite(190, 53);
				} else if(!prayHover){
					ORBS[1].drawSprite(190, 53);
				}
			} else {
				if(prayHover){
					ORBS[15].drawSprite(190, 53);
				} else {
					ORBS[14].drawSprite(190, 53);
				}
			}
			ORBS[5].drawSprite(193, 56);
		}
		ORBS[0].drawSprite(194, 56);
		if(prayer <= 25) {
			if(loopCycle % 20 < 10) {
				ORBS[6].drawSprite(197, 60);
			}
		} else {
			ORBS[6].drawSprite(197, 60);
		}
		int Y = 79;
		if(is480){
			Y = 82;
		}
		smallText.method382(getOrbTextColor(prayer), 234, RSInterface.interfaceCache[7167].disabledMessage, Y, true);
	}
	
	public void drawRunOrb(){
		String OD = signlink.findcachedir()+"/Sprites/Gameframe/Orbs/";
		String cEn = RSInterface.interfaceCache[149].disabledMessage; cEn = cEn.replaceAll("%","");
		int currentEner = Integer.parseInt(cEn);
		energy = (int)(((double)currentEner / 100) * 100D);
		ORBS[0] = new Sprite(OD+"ORBS 0.PNG", 27, getOrbFill(energy));
		if(!runClicked){
			if(!is480){
				if(runHover){
					ORBS[7].drawSprite(190, 92);
				} else{ 
					ORBS[1].drawSprite(190, 92);
				}
			} else {
				ORBS[14].drawSprite(190, 92);
			}
			ORBS[10].drawSprite(193, 95);
			if(energy != 100){
				ORBS[0].drawSprite(193, 95);
			}
			ORBS[8].drawSprite(199, 100);
		} else {
			if(!is480){
				if(runHover){
					ORBS[7].drawSprite(190, 92);
				} else {
					ORBS[1].drawSprite(190, 92);
				} 
			} else {
				ORBS[14].drawSprite(190, 92);
			}
			ORBS[11].drawSprite(193, 95);
			if(energy != 100){
				ORBS[0].drawSprite(193, 95);
			}
			ORBS[9].drawSprite(199, 100);
		}				
		int Y = 117;
		if(is480){
			Y = 120;
		}
		smallText.method382(getOrbTextColor(energy), 234, ""+energy, Y, true);
	}

	private void method142(int i, int j, int k, int l, int i1, int j1, int k1
	)
	{
		if(i1 >= 1 && i >= 1 && i1 <= 102 && i <= 102)
		{
			if(lowMem && j != plane)
				return;
			int i2 = 0;
			if(j1 == 0)
				i2 = worldController.method300(j, i1, i);
			if(j1 == 1)
				i2 = worldController.method301(j, i1, i);
			if(j1 == 2)
				i2 = worldController.method302(j, i1, i);
			if(j1 == 3)
				i2 = worldController.method303(j, i1, i);
			if(i2 != 0)
			{
				int i3 = worldController.method304(j, i1, i, i2);
				int j2 = i2 >> 14 & 0x7fff;
				int k2 = i3 & 0x1f;
				int l2 = i3 >> 6;
				if(j1 == 0)
				{
					worldController.method291(i1, j, i, (byte)-119);
					ObjectDef class46 = ObjectDef.forID(j2);
					if(class46.aBoolean767)
						aClass11Array1230[j].method215(l2, k2, class46.aBoolean757, i1, i);
				}
				if(j1 == 1)
					worldController.method292(i, j, i1);
				if(j1 == 2)
				{
					worldController.method293(j, i1, i);
					ObjectDef class46_1 = ObjectDef.forID(j2);
					if(i1 + class46_1.anInt744 > 103 || i + class46_1.anInt744 > 103 || i1 + class46_1.anInt761 > 103 || i + class46_1.anInt761 > 103)
						return;
					if(class46_1.aBoolean767)
						aClass11Array1230[j].method216(l2, class46_1.anInt744, i1, i, class46_1.anInt761, class46_1.aBoolean757);
				}
				if(j1 == 3)
				{
					worldController.method294(j, i, i1);
					ObjectDef class46_2 = ObjectDef.forID(j2);
					if(class46_2.aBoolean767 && class46_2.hasActions)
						aClass11Array1230[j].method218(i, i1);
				}
			}
			if(k1 >= 0)
			{
				int j3 = j;
				if(j3 < 3 && (byteGroundArray[1][i1][i] & 2) == 2)
					j3++;
				ObjectManager.method188(worldController, k, i, l, j3, aClass11Array1230[j], intGroundArray, i1, k1, j);
			}
		}
	}

	private void updatePlayers(int i, Stream stream)
	{
		anInt839 = 0;
		anInt893 = 0;
		method117(stream);
		method134(stream);
		method91(stream, i);
		method49(stream);
		for(int k = 0; k < anInt839; k++)
		{
			int l = anIntArray840[k];
			if(playerArray[l].anInt1537 != loopCycle)
				playerArray[l] = null;
		}

		if(stream.currentOffset != i)
		{
			signlink.reporterror("Error packet size mismatch in getplayer pos:" + stream.currentOffset + " psize:" + i);
			throw new RuntimeException("eek");
		}
		for(int i1 = 0; i1 < playerCount; i1++)
			if(playerArray[playerIndices[i1]] == null)
			{
				signlink.reporterror(myUsername + " null entry in pl list - pos:" + i1 + " size:" + playerCount);
				throw new RuntimeException("eek");
			}

	}

	private void setCameraPos(int j, int k, int l, int i1, int j1, int k1)
	{
		int l1 = 2048 - k & 0x7ff;
		int i2 = 2048 - j1 & 0x7ff;
		int j2 = 0;
		int k2 = 0;
		int l2 = j;
		if(l1 != 0)
		{
			int i3 = Model.modelIntArray1[l1];
			int k3 = Model.modelIntArray2[l1];
			int i4 = k2 * k3 - l2 * i3 >> 16;
			l2 = k2 * i3 + l2 * k3 >> 16;
			k2 = i4;
		}
		if(i2 != 0)
		{
/* xxx			if(cameratoggle){
				if(zoom == 0)
				zoom = k2;
			  if(lftrit == 0)
				lftrit = j2;
			  if(fwdbwd == 0)
				fwdbwd = l2;
			  k2 = zoom;
			  j2 = lftrit;
			  l2 = fwdbwd;
			}
*/
			int j3 = Model.modelIntArray1[i2];
			int l3 = Model.modelIntArray2[i2];
			int j4 = l2 * j3 + j2 * l3 >> 16;
			l2 = l2 * l3 - j2 * j3 >> 16;
			j2 = j4;
		}
		xCameraPos = l - j2;
		zCameraPos = i1 - k2;
		yCameraPos = k1 - l2;
		yCameraCurve = k;
		xCameraCurve = j1;
	}

	public void updateStrings(String str, int i) {
		switch(i) {
			case 1675: sendFrame126(str, 17508); break;//Stab
			case 1676: sendFrame126(str, 17509); break;//Slash
			case 1677: sendFrame126(str, 17510); break;//Cursh
			case 1678: sendFrame126(str, 17511); break;//Magic
			case 1679: sendFrame126(str, 17512); break;//Range
			case 1680: sendFrame126(str, 17513); break;//Stab
			case 1681: sendFrame126(str, 17514); break;//Slash
			case 1682: sendFrame126(str, 17515); break;//Crush
			case 1683: sendFrame126(str, 17516); break;//Magic
			case 1684: sendFrame126(str, 17517); break;//Range
			case 1686: sendFrame126(str, 17518); break;//Strength
			case 1687: sendFrame126(str, 17519); break;//Prayer
		}
	}

	public void sendFrame126(String str,int i) {
		RSInterface.interfaceCache[i].disabledMessage = str;
		if(RSInterface.interfaceCache[i].parentID == tabInterfaceIDs[tabID])
			needDrawTabArea = true;
	}
	
	public void sendPacket185(int button,int toggle,int interfaceType) {
		switch(interfaceType) {
			case 135:
				RSInterface class9 = RSInterface.interfaceCache[button];
				boolean flag8 = true;
				if(class9.contentType > 0)
					flag8 = promptUserForInput(class9);
				if(flag8) {
					stream.createFrame(185);
					stream.writeWord(button);
				}
				break;
			case 646:
				stream.createFrame(185);
				stream.writeWord(button);
				RSInterface class9_2 = RSInterface.interfaceCache[button];
				if(class9_2.valueIndexArray != null && class9_2.valueIndexArray[0][0] == 5) {
					if(variousSettings[toggle] != class9_2.requiredValues[0]) {
						variousSettings[toggle] = class9_2.requiredValues[0];
						method33(toggle);
						needDrawTabArea = true;
					}
				}
				break;
			case 169:
				stream.createFrame(185);
				stream.writeWord(button);
				RSInterface class9_3 = RSInterface.interfaceCache[button];
				if(class9_3.valueIndexArray != null && class9_3.valueIndexArray[0][0] == 5) {
					variousSettings[toggle] = 1 - variousSettings[toggle];
					method33(toggle);
					needDrawTabArea = true;
				}
				switch(button) {
					case 19136:
						System.out.println("toggle = "+toggle);
						if(toggle == 0)
							sendFrame36(173,toggle);
						if(toggle == 1)
							sendPacket185(153,173,646);
						break;
				}
				break;
		}
	}

	public void sendFrame36(int id,int state) {
		anIntArray1045[id] = state;
		if(variousSettings[id] != state) {
			variousSettings[id] = state;
			method33(id);
			needDrawTabArea = true;
			if(dialogID != -1)
				inputTaken = true;
		}
	}

	public void sendFrame219() { 
		 
		
		if(invOverlayInterfaceID != -1) {
			invOverlayInterfaceID = -1;
			needDrawTabArea = true;
			tabAreaAltered = true;
		}
		if(backDialogID != -1) {
			backDialogID = -1;
			inputTaken = true;
		}
		if(inputDialogState != 0) {
			inputDialogState = 0;
			inputTaken = true;
		}
		openInterfaceID = -1;
		aBoolean1149 = false;
	}

	public void sendFrame248(int interfaceID,int sideInterfaceID) 	{
		if(backDialogID != -1) {
			backDialogID = -1;
			inputTaken = true;
		}
		if(inputDialogState != 0) {
			inputDialogState = 0;
			inputTaken = true;
		}
		openInterfaceID = interfaceID;
		invOverlayInterfaceID = sideInterfaceID;
		needDrawTabArea = true;
		tabAreaAltered = true;
		aBoolean1149 = false;
	}

	private boolean parsePacket() { 
		 
		
		if(socketStream == null)
			return false;
		try {
			int i = socketStream.available();
			if(i == 0)
				return false;
			if(pktType == -1) {
				socketStream.flushInputStream(inStream.buffer, 1);
				pktType = inStream.buffer[0] & 0xff;
				if(encryption != null)
					pktType = pktType - encryption.getNextKey() & 0xff;
				pktSize = SizeConstants.packetSizes[pktType];
				i--;
			}
			if(pktSize == -1)
				if(i > 0) {
					socketStream.flushInputStream(inStream.buffer, 1);
					pktSize = inStream.buffer[0] & 0xff;
					i--;
				} else {
					return false;
				}
			if(pktSize == -2)
				if(i > 1) {
					socketStream.flushInputStream(inStream.buffer, 2);
					inStream.currentOffset = 0;
					pktSize = inStream.readUnsignedWord();
					i -= 2;
				} else {
					return false;
				}
			if(i < pktSize)
				return false;
			inStream.currentOffset = 0;
			socketStream.flushInputStream(inStream.buffer, pktSize);
			anInt1009 = 0;
			anInt843 = anInt842;
			anInt842 = anInt841;
			anInt841 = pktType;
			switch(pktType) {
				case 81:
					updatePlayers(pktSize, inStream);
					aBoolean1080 = false;
					pktType = -1;
					return true;
					
				case 176:
					daysSinceRecovChange = inStream.method427();
					unreadMessages = inStream.method435();
					membersInt = inStream.readUnsignedByte();
					anInt1193 = inStream.method440();
					daysSinceLastLogin = inStream.readUnsignedWord();
					if(anInt1193 != 0 && openInterfaceID == -1) {
						signlink.dnslookup(TextClass.method586(anInt1193));
						clearTopInterfaces();
						char c = '\u028A';
						if(daysSinceRecovChange != 201 || membersInt == 1)
							c = '\u028F';
						reportAbuseInput = "";
						canMute = false;
						for(int k9 = 0; k9 < RSInterface.interfaceCache.length; k9++) {
							if(RSInterface.interfaceCache[k9] == null || RSInterface.interfaceCache[k9].contentType != c)
								continue;
							openInterfaceID = RSInterface.interfaceCache[k9].parentID;
							
						}
					}
					pktType = -1;
					return true;
					
				case 64:
					anInt1268 = inStream.method427();
					anInt1269 = inStream.method428();
					for(int j = anInt1268; j < anInt1268 + 8; j++) {
						for(int l9 = anInt1269; l9 < anInt1269 + 8; l9++)
							if(groundArray[plane][j][l9] != null) {
								groundArray[plane][j][l9] = null;
								spawnGroundItem(j, l9);
							}
					}
					for(Class30_Sub1 class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetFirst(); class30_sub1 != null; class30_sub1 = (Class30_Sub1)aClass19_1179.reverseGetNext())
						if(class30_sub1.anInt1297 >= anInt1268 && class30_sub1.anInt1297 < anInt1268 + 8 && class30_sub1.anInt1298 >= anInt1269 && class30_sub1.anInt1298 < anInt1269 + 8 && class30_sub1.anInt1295 == plane)
							class30_sub1.anInt1294 = 0;
					pktType = -1;
					return true;
					
				case 185:
					int k = inStream.method436();
					RSInterface.interfaceCache[k].disabledMediaType = 3;
					if(myPlayer.desc == null)
						RSInterface.interfaceCache[k].disabledMediaID = (myPlayer.anIntArray1700[0] << 25) + (myPlayer.anIntArray1700[4] << 20) + (myPlayer.equipment[0] << 15) + (myPlayer.equipment[8] << 10) + (myPlayer.equipment[11] << 5) + myPlayer.equipment[1];
					else
						RSInterface.interfaceCache[k].disabledMediaID = (int)(0x12345678L + myPlayer.desc.interfaceType);
					pktType = -1;
					return true;
					
				/* Clan chat packet */
				case 217:
					try {
						name = inStream.readString();
						disabledMessage = inStream.readString();
						clanname = inStream.readString();
						rights = inStream.readUnsignedWord();
						//disabledMessage = TextInput.processText(disabledMessage);
						//disabledMessage = Censor.doCensor(disabledMessage);
						System.out.println(clanname);
						pushMessage(disabledMessage, 16, name);
					} catch(Exception e) {
						e.printStackTrace();
					}
					pktType = -1;
					return true;
					
				case 107:
					aBoolean1160 = false;
					for(int l = 0; l < 5; l++)
						aBooleanArray876[l] = false;
					pktType = -1;
					return true;
					
				case 72:
					int i1 = inStream.method434();
					RSInterface class9 = RSInterface.interfaceCache[i1];
					for(int k15 = 0; k15 < class9.inventory.length; k15++) {
						class9.inventory[k15] = -1;
						class9.inventory[k15] = 0;
					}
					pktType = -1;
					return true;
					
				case 214:
					ignoreCount = pktSize / 8;
					for(int j1 = 0; j1 < ignoreCount; j1++)
						ignoreListAsLongs[j1] = inStream.readQWord();
					pktType = -1;
					return true;
					
				case 166:
					aBoolean1160 = true;
					anInt1098 = inStream.readUnsignedByte();
					anInt1099 = inStream.readUnsignedByte();
					anInt1100 = inStream.readUnsignedWord();
					anInt1101 = inStream.readUnsignedByte();
					anInt1102 = inStream.readUnsignedByte();
					if(anInt1102 >= 100) {
						xCameraPos = anInt1098 * 128 + 64;
						yCameraPos = anInt1099 * 128 + 64;
						zCameraPos = method42(plane, yCameraPos, xCameraPos) - anInt1100;
					}
					pktType = -1;
					return true;
					
				case 134:
					needDrawTabArea = true;
					int k1 = inStream.readUnsignedByte();
					int i10 = inStream.method439();
					int l15 = inStream.readUnsignedByte();
					int oldXp = currentExp[k1];
					if(drawXpBar) {
						drawFlag = true;
					}
					currentExp[k1] = i10;
					currentStats[k1] = l15;
					maxStats[k1] = 1;
						testXp += i10-oldXp;
						xpToDraw += i10-oldXp;
						if(testXp == currentExp[k1]) {
							testXp = 0;
							xpToDraw = 0;
						}
					for(int k20 = 0; k20 < 98; k20++)
						if(i10 >= anIntArray1019[k20])
							maxStats[k1] = k20 + 2;
					pktType = -1;
				return true;
					
				case 71:
					int l1 = inStream.readUnsignedWord();
					int j10 = inStream.method426();
					if(l1 == 65535)
						l1 = -1;
					tabInterfaceIDs[j10] = l1;
					needDrawTabArea = true;
					tabAreaAltered = true;
					pktType = -1;
					return true;
					
				case 74:
					int i2 = inStream.method434();
					if(i2 == 65535)
						i2 = -1;
					if(i2 != currentSong && musicEnabled && !lowMem && prevSong == 0) {
						nextSong = i2;
						songChanging = true;
						onDemandFetcher.method558(2, nextSong);
					}
					currentSong = i2;
					pktType = -1;
					return true;
					
				case 121:
					int j2 = inStream.method436();
					int k10 = inStream.method435();
					if(musicEnabled && !lowMem) {
						nextSong = j2;
						songChanging = false;
						onDemandFetcher.method558(2, nextSong);
						prevSong = k10;
					}
					pktType = -1;
					return true;
					
				case 109:
					resetLogout();
					pktType = -1;
					return false;
					
				case 70:
					int k2 = inStream.readSignedWord();
					int l10 = inStream.method437();
					int i16 = inStream.method434();
					RSInterface class9_5 = RSInterface.interfaceCache[i16];
					class9_5.xOffset = k2;
					class9_5.yOffset = l10;
					pktType = -1;
					return true;
					
				case 73:
				case 241:
					int l2 = anInt1069;
					int i11 = anInt1070;
					if(pktType == 73) {
						l2 = inStream.method435();
						i11 = inStream.readUnsignedWord();
						aBoolean1159 = false;
					}
					if(pktType == 241) {
						i11 = inStream.method435();
						inStream.initBitAccess();
						for(int j16 = 0; j16 < 4; j16++) {
							for(int l20 = 0; l20 < 13; l20++) {
								for(int j23 = 0; j23 < 13; j23++) {
									int i26 = inStream.readBits(1);
									if(i26 == 1)
										anIntArrayArrayArray1129[j16][l20][j23] = inStream.readBits(26);
									else
										anIntArrayArrayArray1129[j16][l20][j23] = -1;
								}
							}
						}
						inStream.finishBitAccess();
						l2 = inStream.readUnsignedWord();
						aBoolean1159 = true;
					}
				if(anInt1069 == l2 && anInt1070 == i11 && loadingStage == 2)
				{
					pktType = -1;
					return true;
				}
				anInt1069 = l2;
				anInt1070 = i11;
				baseX = (anInt1069 - 6) * 8;
				baseY = (anInt1070 - 6) * 8;
				aBoolean1141 = (anInt1069 / 8 == 48 || anInt1069 / 8 == 49) && anInt1070 / 8 == 48;
				if(anInt1069 / 8 == 48 && anInt1070 / 8 == 148)
					aBoolean1141 = true;
				loadingStage = 1;
				aLong824 = System.currentTimeMillis();
				inGameScreen.initDrawingArea();
				loadingPleaseWait.drawSprite(8,9);;
				inGameScreen.drawGraphics(4, super.graphics, 4);
					if(pktType == 73) {
						int k16 = 0;
						for(int i21 = (anInt1069 - 6) / 8; i21 <= (anInt1069 + 6) / 8; i21++) {
							for(int k23 = (anInt1070 - 6) / 8; k23 <= (anInt1070 + 6) / 8; k23++)
								k16++;
						}
						aByteArrayArray1183 = new byte[k16][];
						aByteArrayArray1247 = new byte[k16][];
						anIntArray1234 = new int[k16];
						anIntArray1235 = new int[k16];
						anIntArray1236 = new int[k16];
						k16 = 0;
						for(int l23 = (anInt1069 - 6) / 8; l23 <= (anInt1069 + 6) / 8; l23++) {
							for(int j26 = (anInt1070 - 6) / 8; j26 <= (anInt1070 + 6) / 8; j26++) {
								anIntArray1234[k16] = (l23 << 8) + j26;
								if(aBoolean1141 && (j26 == 49 || j26 == 149 || j26 == 147 || l23 == 50 || l23 == 49 && j26 == 47)) {
									anIntArray1235[k16] = -1;
									anIntArray1236[k16] = -1;
									k16++;
								} else {
									int k28 = anIntArray1235[k16] = onDemandFetcher.method562(0, j26, l23);
									if(k28 != -1)
										onDemandFetcher.method558(3, k28);
									int j30 = anIntArray1236[k16] = onDemandFetcher.method562(1, j26, l23);
									if(j30 != -1)
										onDemandFetcher.method558(3, j30);
									k16++;
								}
							}
						}
					}
					if(pktType == 241) {
						int l16 = 0;
						int ai[] = new int[676];
						for(int i24 = 0; i24 < 4; i24++) {
							for(int k26 = 0; k26 < 13; k26++) {
								for(int l28 = 0; l28 < 13; l28++) {
									int k30 = anIntArrayArrayArray1129[i24][k26][l28];
									if(k30 != -1) {
										int k31 = k30 >> 14 & 0x3ff;
										int i32 = k30 >> 3 & 0x7ff;
										int k32 = (k31 / 8 << 8) + i32 / 8;
										for(int j33 = 0; j33 < l16; j33++) {
											if(ai[j33] != k32)
												continue;
											k32 = -1;
											
										}
										if(k32 != -1)
											ai[l16++] = k32;
									}
								}
							}
						}
						aByteArrayArray1183 = new byte[l16][];
						aByteArrayArray1247 = new byte[l16][];
						anIntArray1234 = new int[l16];
						anIntArray1235 = new int[l16];
						anIntArray1236 = new int[l16];
						for(int l26 = 0; l26 < l16; l26++) {
							int i29 = anIntArray1234[l26] = ai[l26];
							int l30 = i29 >> 8 & 0xff;
							int l31 = i29 & 0xff;
							int j32 = anIntArray1235[l26] = onDemandFetcher.method562(0, l31, l30);
							if(j32 != -1)
								onDemandFetcher.method558(3, j32);
							int i33 = anIntArray1236[l26] = onDemandFetcher.method562(1, l31, l30);
							if(i33 != -1)
								onDemandFetcher.method558(3, i33);
						}
					}
					int i17 = baseX - anInt1036;
					int j21 = baseY - anInt1037;
					anInt1036 = baseX;
					anInt1037 = baseY;
					for(int j24 = 0; j24 < 16384; j24++) {
						NPC npc = npcArray[j24];
						if(npc != null) {
							for(int j29 = 0; j29 < 10; j29++) {
								npc.smallX[j29] -= i17;
								npc.smallY[j29] -= j21;
							}
							npc.x -= i17 * 128;
							npc.y -= j21 * 128;
						}
					}
					for(int i27 = 0; i27 < maxPlayers; i27++) {
						Player player = playerArray[i27];
						if(player != null) {
							for(int i31 = 0; i31 < 10; i31++) {
								player.smallX[i31] -= i17;
								player.smallY[i31] -= j21;
							}
							player.x -= i17 * 128;
							player.y -= j21 * 128;
						}
					}
					aBoolean1080 = true;
					byte byte1 = 0;
					byte byte2 = 104;
					byte byte3 = 1;
					if(i17 < 0) {
						byte1 = 103;
						byte2 = -1;
						byte3 = -1;
					}
					byte byte4 = 0;
					byte byte5 = 104;
					byte byte6 = 1;
					if(j21 < 0) {
						byte4 = 103;
						byte5 = -1;
						byte6 = -1;
					}
					for(int k33 = byte1; k33 != byte2; k33 += byte3) {
						for(int l33 = byte4; l33 != byte5; l33 += byte6) {
							int i34 = k33 + i17;
							int j34 = l33 + j21;
							for(int k34 = 0; k34 < 4; k34++)
								if(i34 >= 0 && j34 >= 0 && i34 < 104 && j34 < 104)
									groundArray[k34][k33][l33] = groundArray[k34][i34][j34];
								else
									groundArray[k34][k33][l33] = null;
						}
					}
					for(Class30_Sub1 class30_sub1_1 = (Class30_Sub1)aClass19_1179.reverseGetFirst(); class30_sub1_1 != null; class30_sub1_1 = (Class30_Sub1)aClass19_1179.reverseGetNext()) {
						class30_sub1_1.anInt1297 -= i17;
						class30_sub1_1.anInt1298 -= j21;
						if(class30_sub1_1.anInt1297 < 0 || class30_sub1_1.anInt1298 < 0 || class30_sub1_1.anInt1297 >= 104 || class30_sub1_1.anInt1298 >= 104)
							class30_sub1_1.unlink();
					}
					if(destX != 0) {
						destX -= i17;
						destY -= j21;
					}
					aBoolean1160 = false;
					pktType = -1;
					return true;
					
				case 208:
					int i3 = inStream.method437();
					if(i3 >= 0)
						method60(i3);
					anInt1018 = i3;
					pktType = -1;
					return true;
					
				case 99:
				/*
				* Black map, mape state 2, causes blackness
				*/
					anInt1021 = inStream.readUnsignedByte();
					pktType = -1;
					return true;
					
				case 75:
					int j3 = inStream.method436();
					int j11 = inStream.method436();
					RSInterface.interfaceCache[j11].disabledMediaType = 2;
					RSInterface.interfaceCache[j11].disabledMediaID = j3;
					pktType = -1;
					return true;
					
				case 114:
					anInt1104 = inStream.method434() * 30;
					pktType = -1;
					return true;
					
				case 60:
					anInt1269 = inStream.readUnsignedByte();
					anInt1268 = inStream.method427();
					while(inStream.currentOffset < pktSize) {
						int k3 = inStream.readUnsignedByte();
						method137(inStream, k3);
					}
					pktType = -1;
					return true;
					
				case 35:
					int l3 = inStream.readUnsignedByte();
					int k11 = inStream.readUnsignedByte();
					int j17 = inStream.readUnsignedByte();
					int k21 = inStream.readUnsignedByte();
					aBooleanArray876[l3] = true;
					anIntArray873[l3] = k11;
					anIntArray1203[l3] = j17;
					anIntArray928[l3] = k21;
					anIntArray1030[l3] = 0;
					pktType = -1;
					return true;
					
case 174:

/*Empty Following Packet*/
                followPlayer = 0;
                followNPC = 0;
                int l11z = inStream.readUnsignedWord();
                int iq = inStream.readUnsignedByte();
                followDistance = inStream.readUnsignedWord();
                if (iq == 0)
                {
                    followNPC = l11z;
                }
                else if (iq == 1)
                {
                    followPlayer = l11z;
                }
                pktType = -1;
                return true;
	
				case 104:
					int j4 = inStream.method427();
					int i12 = inStream.method426();
					String s6 = inStream.readString();
					if(j4 >= 1 && j4 <= 5) {
						if(s6.equalsIgnoreCase("null"))
							s6 = null;
						atPlayerActions[j4 - 1] = s6;
						atPlayerArray[j4 - 1] = i12 == 0;
					}
					pktType = -1;
					return true;
					
				case 78:
					destX = 0;
					pktType = -1;
					return true;
					
				case 253:
					String s = inStream.readString();
					if(s.startsWith("Alert##")) {
						String[] args = s.split("##");
						if (args.length == 3) {
							alertHandler.alert = new Alert("Notification", args[1], args[2]);
						} else if (args.length == 4) {
							alertHandler.alert = new Alert(args[1], args[2], args[3]);
						}
						pktType = -1;
						return true;
					}
					if(s.endsWith(":tradereq:")) {
						String s3 = s.substring(0, s.indexOf(":"));
						long l17 = TextClass.longForName(s3);
						boolean flag2 = false;
						for(int j27 = 0; j27 < ignoreCount; j27++) {
							if(ignoreListAsLongs[j27] != l17)
								continue;
							flag2 = true;
						}
						if(!flag2 && anInt1251 == 0)
							pushMessage("wishes to trade with you.", 4, s3);
					} else if (s.endsWith(":clan:")) {
						String s4 = s.substring(0, s.indexOf(":"));
						long l18 = TextClass.longForName(s4);
						pushMessage("Clan: ", 8, s4);	
					} else if(s.endsWith("#url#")) {
						String text = s.substring(0, s.indexOf("-"));
						s = s.substring(text.length()+1).trim();
						String link = s.substring(0, s.indexOf("#"));
						pushMessage(text, 9, link);
							} else if(s.endsWith(":resetautocast:")) {
						Autocast = false;
						autocastId = 0;
						magicAuto.drawSprite(1000, 1000);
					} else if(s.endsWith(":duelreq:")) {
						String s4 = s.substring(0, s.indexOf(":"));
						long l18 = TextClass.longForName(s4);
						boolean flag3 = false;
						for(int k27 = 0; k27 < ignoreCount; k27++) {
							if(ignoreListAsLongs[k27] != l18)
								continue;
							flag3 = true;
							
						}
						if(!flag3 && anInt1251 == 0)
							pushMessage("wishes to duel with you.", 8, s4);
					} else if(s.endsWith(":chalreq:")) {
						String s5 = s.substring(0, s.indexOf(":"));
						long l19 = TextClass.longForName(s5);
						boolean flag4 = false;
						for(int l27 = 0; l27 < ignoreCount; l27++) {
							if(ignoreListAsLongs[l27] != l19)
								continue;
							flag4 = true;
							
						}
						if(!flag4 && anInt1251 == 0) {
							String s8 = s.substring(s.indexOf(":") + 1, s.length() - 9);
							pushMessage(s8, 8, s5);
						}
					} else {
						pushMessage(s, 0, "");
					}
					pktType = -1;
					return true;
					
				case 1:
					for(int k4 = 0; k4 < playerArray.length; k4++)
						if(playerArray[k4] != null)
							playerArray[k4].anim = -1;
					for(int j12 = 0; j12 < npcArray.length; j12++)
						if(npcArray[j12] != null)
							npcArray[j12].anim = -1;
					pktType = -1;
					return true;
					
				case 50:
					long l4 = inStream.readQWord();
					int i18 = inStream.readUnsignedByte();
					String s7 = TextClass.fixName(TextClass.nameForLong(l4));
					for(int k24 = 0; k24 < friendsCount; k24++) {
						if(l4 != friendsListAsLongs[k24])
							continue;
						if(friendsNodeIDs[k24] != i18) {
							friendsNodeIDs[k24] = i18;
							needDrawTabArea = true;
							if(i18 >= 2) {
								pushMessage(s7 + " has logged in.", 5, "");
							}
							if(i18 <= 1) {
								pushMessage(s7 + " has logged out.", 5, "");
							}
						}
						s7 = null;
						
					}
					if(s7 != null && friendsCount < 200) {
						friendsListAsLongs[friendsCount] = l4;
						friendsList[friendsCount] = s7;
						friendsNodeIDs[friendsCount] = i18;
						friendsCount++;
						needDrawTabArea = true;
					}
					for(boolean flag6 = false; !flag6;) {
						flag6 = true;
						for(int index = 0; index < friendsCount - 1; index++)
							if(friendsNodeIDs[index] != nodeID && friendsNodeIDs[index + 1] == nodeID || friendsNodeIDs[index] == 0 && friendsNodeIDs[index + 1] != 0) {
								int j31 = friendsNodeIDs[index];
								friendsNodeIDs[index] = friendsNodeIDs[index + 1];
								friendsNodeIDs[index + 1] = j31;
								String s10 = friendsList[index];
								friendsList[index] = friendsList[index + 1];
								friendsList[index + 1] = s10;
								long l32 = friendsListAsLongs[index];
								friendsListAsLongs[index] = friendsListAsLongs[index + 1];
								friendsListAsLongs[index + 1] = l32;
								needDrawTabArea = true;
								flag6 = false;
							}
					}//friends list sorting
					pktType = -1;
					return true;
					
				case 110:
					if(tabID == 12)
						needDrawTabArea = true;
					energy = inStream.readUnsignedByte();
					pktType = -1;
					return true;
					
				case 254:
					anInt855 = inStream.readUnsignedByte();
					if(anInt855 == 1)
						anInt1222 = inStream.readUnsignedWord();
					if(anInt855 >= 2 && anInt855 <= 6) {
						if(anInt855 == 2) {
							anInt937 = 64;
							anInt938 = 64;
						}
						if(anInt855 == 3) {
							anInt937 = 0;
							anInt938 = 64;
						}
						if(anInt855 == 4) {
							anInt937 = 128;
							anInt938 = 64;
						}
						if(anInt855 == 5) {
							anInt937 = 64;
							anInt938 = 0;
						}
						if(anInt855 == 6) {
							anInt937 = 64;
							anInt938 = 128;
						}
						anInt855 = 2;
						anInt934 = inStream.readUnsignedWord();
						anInt935 = inStream.readUnsignedWord();
						anInt936 = inStream.readUnsignedByte();
					}
					if(anInt855 == 10)
						anInt933 = inStream.readUnsignedWord();
					pktType = -1;
					return true;
					
				case 248:
					int i5 = inStream.method435();
					int k12 = inStream.readUnsignedWord();
					if(backDialogID != -1) {
						backDialogID = -1;
						inputTaken = true;
					}
					if(inputDialogState != 0) {
						inputDialogState = 0;
						inputTaken = true;
					}
					openInterfaceID = i5;
					invOverlayInterfaceID = k12;
					needDrawTabArea = true;
					tabAreaAltered = true;
					aBoolean1149 = false;
					pktType = -1;
					return true;
					
				case 79:
					int j5 = inStream.method434();
					int l12 = inStream.method435();
					RSInterface class9_3 = RSInterface.interfaceCache[j5];
					if(class9_3 != null && class9_3.interfaceType == 0) {
						if(l12 < 0)
							l12 = 0;
						if(l12 > class9_3.scrollMax - class9_3.height)
							l12 = class9_3.scrollMax - class9_3.height;
						class9_3.scrollPosition = l12;
					}
					pktType = -1;
					return true;
					
				case 68:
					for(int k5 = 0; k5 < variousSettings.length; k5++)
						if(variousSettings[k5] != anIntArray1045[k5]) {
							variousSettings[k5] = anIntArray1045[k5];
							method33(k5);
							needDrawTabArea = true;
						}
					pktType = -1;
					return true;
					
				case 196:
					long l5 = inStream.readQWord();
					int j18 = inStream.readDWord();
					int l21 = inStream.readUnsignedByte();
					boolean flag5 = false;
					for(int i28 = 0; i28 < 100; i28++) {
						if(anIntArray1240[i28] != j18)
							continue;
						flag5 = true;
						
					}
					if(l21 <= 1) {
						for(int l29 = 0; l29 < ignoreCount; l29++) {
							if(ignoreListAsLongs[l29] != l5)
								continue;
							flag5 = true;
							
						}
					}
					if(!flag5 && anInt1251 == 0)
						try {
							anIntArray1240[anInt1169] = j18;
							anInt1169 = (anInt1169 + 1) % 100;
							String s9 = TextInput.method525(pktSize - 13, inStream);
							//if(l21 != 3)
								//s9 = Censor.doCensor(s9);
							if(l21 == 4) {
								pushMessage(s9, 7, "@cr3@" + TextClass.fixName(TextClass.nameForLong(l5)));
							} else if(l21 == 3) {
								pushMessage(s9, 7, "@cr2@" + TextClass.fixName(TextClass.nameForLong(l5)));
							} else if(l21 == 2) {
								pushMessage(s9, 7, "@cr1@" + TextClass.fixName(TextClass.nameForLong(l5)));
							} else if(l21 == 1) {
								pushMessage(s9, 7, "@cr0@" + TextClass.fixName(TextClass.nameForLong(l5)));
							} else if(l21 == 5) {
								pushMessage(s9, 7, "@cr3@" + TextClass.fixName(TextClass.nameForLong(l5)));
							} else if(l21 == 6) {
								pushMessage(s9, 7, "@cr2@" + TextClass.fixName(TextClass.nameForLong(l5)));
							} else if(l21 == 7) {
								pushMessage(s9, 7, "@cr5@" + TextClass.fixName(TextClass.nameForLong(l5)));
							} else if(l21 == 8) {
								pushMessage(s9, 7, "@cr4@" + TextClass.fixName(TextClass.nameForLong(l5)));
							} else if(l21 == 9) {
								pushMessage(s9, 7, "@cr6@" + TextClass.fixName(TextClass.nameForLong(l5)));
							} else {
								pushMessage(s9, 3, TextClass.fixName(TextClass.nameForLong(l5)));
							}
						} catch(Exception exception1) {
							signlink.reporterror("cde1");
						}
					pktType = -1;
					return true;
					
				case 85:
					anInt1269 = inStream.method427();
					anInt1268 = inStream.method427();
					pktType = -1;
					return true;
					
				case 24:
					anInt1054 = inStream.method428();
					if(anInt1054 == tabID) {
						if(anInt1054 == 3)
							tabID = 1;
						else
							tabID = 3;
						needDrawTabArea = true;
					}
					pktType = -1;
					return true;
					
				case 246:
					int i6 = inStream.method434();
					int i13 = inStream.readUnsignedWord();
					int k18 = inStream.readUnsignedWord();
					if(k18 == 65535) {
						RSInterface.interfaceCache[i6].disabledMediaType = 0;
						pktType = -1;
						return true;
					} else {
						ItemDef itemDef = ItemDef.forID(k18);
						RSInterface.interfaceCache[i6].disabledMediaType = 4;
						RSInterface.interfaceCache[i6].disabledMediaID = k18;
						RSInterface.interfaceCache[i6].modelRotationY = itemDef.modelRotationY;
						RSInterface.interfaceCache[i6].modelRotationX = itemDef.modelRotationX;
						RSInterface.interfaceCache[i6].modelZoom = (itemDef.modelZoom * 100) / i13;
						pktType = -1;
						return true;
					}
					
				case 171:
					boolean flag1 = inStream.readUnsignedByte() == 1;
					int j13 = inStream.readUnsignedWord();
					RSInterface.interfaceCache[j13].interfaceShown = flag1;
					pktType = -1;
					return true;
					
				case 142:
					int j6 = inStream.method434();
					method60(j6);
					if(backDialogID != -1) {
						backDialogID = -1;
						inputTaken = true;
					}
					if(inputDialogState != 0) {
						inputDialogState = 0;
						inputTaken = true;
					}
					invOverlayInterfaceID = j6;
					needDrawTabArea = true;
					tabAreaAltered = true;
					openInterfaceID = -1;
					aBoolean1149 = false;
					pktType = -1;
					return true;
					
				case 126:
					String text = inStream.readString();
					int frame = inStream.method435();
					if (text.startsWith("Link here")) {
						launchURL(text);
						pktType = -1;
						return true;
					}
					updateStrings(text, frame);
					sendFrame126(text, frame);
					if (frame >= 18144 && frame <= 18244) {
						clanList[frame - 18144] = text;
					}
					pktType = -1;
					return true;
					
				case 206:
					publicChatMode = inStream.readUnsignedByte();
					privateChatMode = inStream.readUnsignedByte();
					tradeMode = inStream.readUnsignedByte();
					aBoolean1233 = true;
					inputTaken = true;
					pktType = -1;
					return true;
					
				case 240:
					if(tabID == 12)
						needDrawTabArea = true;
					weight = inStream.readSignedWord();
					pktType = -1;
					return true;
					
				case 8:
					int k6 = inStream.method436();
					int l13 = inStream.readUnsignedWord();
					RSInterface.interfaceCache[k6].disabledMediaType = 1;
					RSInterface.interfaceCache[k6].disabledMediaID = l13;
					pktType = -1;
					return true;
					
				case 122:
					int l6 = inStream.method436();
					int i14 = inStream.method436();
					int i19 = i14 >> 10 & 0x1f;
					int i22 = i14 >> 5 & 0x1f;
					int l24 = i14 & 0x1f;
					RSInterface.interfaceCache[l6].disabledColor = (i19 << 19) + (i22 << 11) + (l24 << 3);
					pktType = -1;
					return true;
					
				case 53:
					needDrawTabArea = true;
					int i7 = inStream.readUnsignedWord();
					RSInterface class9_1 = RSInterface.interfaceCache[i7];
					int j19 = inStream.readUnsignedWord();
					for(int j22 = 0; j22 < j19; j22++) {
						int i25 = inStream.readUnsignedByte();
						if(i25 == 255)
							i25 = inStream.method440();
						class9_1.inventory[j22] = inStream.method436();
						class9_1.inventoryValue[j22] = i25;
					}
					for(int j25 = j19; j25 < class9_1.inventory.length; j25++) {
						class9_1.inventory[j25] = 0;
						class9_1.inventoryValue[j25] = 0;
					}
					pktType = -1;
					return true;
					
				case 230:
					int j7 = inStream.method435();
					int j14 = inStream.readUnsignedWord();
					int k19 = inStream.readUnsignedWord();
					int k22 = inStream.method436();
					RSInterface.interfaceCache[j14].modelRotationY = k19;
					RSInterface.interfaceCache[j14].modelRotationX = k22;
					RSInterface.interfaceCache[j14].modelZoom = j7;
					pktType = -1;
					return true;
					
				case 221:
					anInt900 = inStream.readUnsignedByte();
					needDrawTabArea = true;
					pktType = -1;
					return true;
					
				case 177:
					aBoolean1160 = true;
					anInt995 = inStream.readUnsignedByte();
					anInt996 = inStream.readUnsignedByte();
					anInt997 = inStream.readUnsignedWord();
					anInt998 = inStream.readUnsignedByte();
					anInt999 = inStream.readUnsignedByte();
					if(anInt999 >= 100) {
						int k7 = anInt995 * 128 + 64;
						int k14 = anInt996 * 128 + 64;
						int i20 = method42(plane, k14, k7) - anInt997;
						int l22 = k7 - xCameraPos;
						int k25 = i20 - zCameraPos;
						int j28 = k14 - yCameraPos;
						int i30 = (int)Math.sqrt(l22 * l22 + j28 * j28);
						yCameraCurve = (int)(Math.atan2(k25, i30) * 325.94900000000001D) & 0x7ff;
						xCameraCurve = (int)(Math.atan2(l22, j28) * -325.94900000000001D) & 0x7ff;
						if(yCameraCurve < 128)
							yCameraCurve = 128;
						if(yCameraCurve > 383)
							yCameraCurve = 383;
					}
					pktType = -1;
					return true;
					
				case 249:
					anInt1046 = inStream.method426();
					unknownInt10 = inStream.method436();
					pktType = -1;
					return true;
					
				case 65:
					updateNPCs(inStream, pktSize);
					pktType = -1;
					return true;
					
				case 27:
					messagePromptRaised = false;
					inputDialogState = 1;
					amountOrNameInput = "";
					inputTaken = true;
					pktType = -1;
					return true;
					
				case 187:
					messagePromptRaised = false;
					inputDialogState = 2;
					amountOrNameInput = "";
					inputTaken = true;
					pktType = -1;
					return true;
				case 97:
					int l7 = inStream.readUnsignedWord();
					method60(l7);
					if (invOverlayInterfaceID != -1) {
						invOverlayInterfaceID = -1;
						needDrawTabArea = true;
						tabAreaAltered = true;
					}
					if (backDialogID != -1) {
						backDialogID = -1;
						inputTaken = true;
					}
					if (inputDialogState != 0) {
						inputDialogState = 0;
						inputTaken = true;
					}
					//17511 = Question Type
					//15819 = Christmas Type
					//15812 = Security Type
					//15801 = Item Scam Type
					//15791 = Password Safety ?
					//15774 = Good/Bad Password
					//15767 = Drama Type ????
					if (l7 == 15244) {
						openInterfaceID = 15774;
						fullscreenInterfaceID = 15244;
					} else {
						openInterfaceID = l7;
					}
					aBoolean1149 = false;
					pktType = -1;
					return true;
				case 218:
					int i8 = inStream.method438();
					dialogID = i8;
					inputTaken = true;
					pktType = -1;
					return true;
					
				case 87:
					int j8 = inStream.method434();
					int l14 = inStream.method439();
					anIntArray1045[j8] = l14;
					if(variousSettings[j8] != l14) {
						variousSettings[j8] = l14;
						method33(j8);
						needDrawTabArea = true;
						if(dialogID != -1)
							inputTaken = true;
					}
					pktType = -1;
					return true;
					
				case 36:
					int k8 = inStream.method434();
					byte byte0 = inStream.readSignedByte();
					anIntArray1045[k8] = byte0;
					if(variousSettings[k8] != byte0) {
						variousSettings[k8] = byte0;
						method33(k8);
						needDrawTabArea = true;
						if(dialogID != -1)
							inputTaken = true;
					}
					pktType = -1;
					return true;
					
				case 61:
					anInt1055 = inStream.readUnsignedByte();
					pktType = -1;
					return true;
					
				case 200:
                    int l8 = inStream.readUnsignedWord();
                    int i15 = inStream.readSignedWord();
                    RSInterface class9_4 = RSInterface.interfaceCache[l8];
                    class9_4.disabledAnimation = i15;
                                    if(i15 == 591 || i15 == 588) 
                {
                    class9_4.modelZoom = 1600;
                }
                    if(i15 == -1) {
                        class9_4.animationLength = 0;
                        class9_4.animationDelay = 0;
                    }
                    pktType = -1;
                    return true;
					
				case 219:
					if(invOverlayInterfaceID != -1) {
						invOverlayInterfaceID = -1;
						needDrawTabArea = true;
						tabAreaAltered = true;
					}
					if(backDialogID != -1) {
						backDialogID = -1;
						inputTaken = true;
					}
					if(inputDialogState != 0) {
						inputDialogState = 0;
						inputTaken = true;
					}
					openInterfaceID = -1;
					aBoolean1149 = false;
					pktType = -1;
					return true;
					
				case 34:
					needDrawTabArea = true;
					int i9 = inStream.readUnsignedWord();
					RSInterface class9_2 = RSInterface.interfaceCache[i9];
					while(inStream.currentOffset < pktSize) {
						int j20 = inStream.method422();
						int i23 = inStream.readUnsignedWord();
						int l25 = inStream.readUnsignedByte();
						if(l25 == 255)
							l25 = inStream.readDWord();
						if(j20 >= 0 && j20 < class9_2.inventory.length) {
							class9_2.inventory[j20] = i23;
							class9_2.inventoryValue[j20] = l25;
						}
					}
					pktType = -1;
					return true;
					
				case 4:
				case 44:
				case 84:
				case 101:
				case 105:
				case 117:
				case 147:
				case 151:
				case 156:
				case 160:
				case 215:
					method137(inStream, pktType);
					pktType = -1;
					return true;
					
				case 106:
					tabID = inStream.method427();
					needDrawTabArea = true;
					tabAreaAltered = true;
					pktType = -1;
					return true;
					
				case 164:
					int j9 = inStream.method434();
					method60(j9);
					if(invOverlayInterfaceID != -1) {
						invOverlayInterfaceID = -1;
						needDrawTabArea = true;
						tabAreaAltered = true;
					}
					backDialogID = j9;
					inputTaken = true;
					openInterfaceID = -1;
					aBoolean1149 = false;
					pktType = -1;
					return true;
					
			}
			signlink.reporterror("T1 - " + pktType + "," + pktSize + " - " + anInt842 + "," + anInt843);
			//resetLogout();
		} catch(IOException _ex) {
			dropClient();
		} catch(Exception exception) {
			String s2 = "T2 - " + pktType + "," + anInt842 + "," + anInt843 + " - " + pktSize + "," + (baseX + myPlayer.smallX[0]) + "," + (baseY + myPlayer.smallY[0]) + " - ";
			for(int j15 = 0; j15 < pktSize && j15 < 50; j15++)
				s2 = s2 + inStream.buffer[j15] + ",";
			signlink.reporterror(s2);
			//resetLogout();
		}
		pktType = -1;
		return true;
	}

	private void method146() { 
		 
		
		anInt1265++;
		method47(true);
		method26(true);
		method47(false);
		method26(false);
		method55();
		method104();
		if(!aBoolean1160) {
			int i = anInt1184;
			if(anInt984 / 256 > i)
				i = anInt984 / 256;
			if(aBooleanArray876[4] && anIntArray1203[4] + 128 > i)
				i = anIntArray1203[4] + 128;
			int k = minimapInt1 + anInt896 & 0x7ff;
			setCameraPos(600 + i * 3, i, anInt1014, method42(plane, myPlayer.y, myPlayer.x) - 50, k, anInt1015);
  	   


		}
		int j;
		if(!aBoolean1160)
			j = method120();
		else
			j = method121();
		int l = xCameraPos;
		int i1 = zCameraPos;
		int j1 = yCameraPos;
		int k1 = yCameraCurve;
		int l1 = xCameraCurve;
		for(int i2 = 0; i2 < 5; i2++)
			if(aBooleanArray876[i2]) {
				int j2 = (int)((Math.random() * (double)(anIntArray873[i2] * 2 + 1) - (double)anIntArray873[i2]) + Math.sin((double)anIntArray1030[i2] * ((double)anIntArray928[i2] / 100D)) * (double)anIntArray1203[i2]);
				if(i2 == 0)
					xCameraPos += j2;
				if(i2 == 1)
					zCameraPos += j2;
				if(i2 == 2)
					yCameraPos += j2;
				if(i2 == 3)
					xCameraCurve = xCameraCurve + j2 & 0x7ff;
				if(i2 == 4) {
					yCameraCurve += j2;
					if(yCameraCurve < 128)
						yCameraCurve = 128;
					if(yCameraCurve > 383)
						yCameraCurve = 383;
				}
			}
		int k2 = Texture.anInt1481;
		Model.aBoolean1684 = true;
		Model.anInt1687 = 0;
		Model.anInt1685 = super.mouseX - 4;
		Model.anInt1686 = super.mouseY - 4;
		DrawingArea.setAllPixelsToZero();
		worldController.method313(xCameraPos, yCameraPos, xCameraCurve, zCameraPos, j, yCameraCurve);
		worldController.clearObj5Cache();
		updateEntities();
		drawHeadIcon();
		method37(k2);
		draw3dScreen();
		drawCoinParts();
		if(coinToggle){
			int cash;
			String Cash = RSInterface.interfaceCache[8135].disabledMessage;
			cash = Integer.parseInt(Cash);
			coinPart.drawSprite(400+11+32, 87);
			smallText.method382(getMoneyOrbColor(cash), 485, RSInterface.interfaceCache[8134].disabledMessage+"", 102, true);
		}
		if(drawFlag) {
			if(xpToDraw != 0) {
				if(flagPos < 125) {
					if(flagPos < 100) {
						flagPos += 2;
					} else {
						flagPos += 1;
					}
					xpFlag.drawSprite(423, flagPos);
					newSmallFont.drawBasicString(""+xpToDraw+"xp", 470, flagPos+20, 0xCC6600, 1);
				} else {	}
				if(flagPos >= 125) {
					drawFlag = false;
					flagPos = 72;
					xpToDraw = 0;	
				}
			}
		}
		if(drawXpBar) {
			String text = ""+testXp;
			int txtWidth = newRegularFont.getTextWidth(text);
			int drawX = 505-txtWidth;
			sprite1.drawSprite(419, 51);
			newRegularFont.drawBasicString("XP:", 421, 64, 0xFFFFFD, 0);
			newRegularFont.drawBasicString(text, drawX, 64, 0xFFFFFD, 0);
		}
		inGameScreen.drawGraphics(4, super.graphics, 4);
		xCameraPos = l;
		zCameraPos = i1;
		yCameraPos = j1;
		yCameraCurve = k1;
		xCameraCurve = l1;
	}
	
	/* Packets that opens interfaces */
	public void sendFrame97(int interfaceID) {
		method60(interfaceID);
        if (invOverlayInterfaceID != -1) {
            invOverlayInterfaceID = -1;
            needDrawTabArea = true;
            tabAreaAltered = true;
        }
        if (backDialogID != -1) {
            backDialogID = -1;
            inputTaken = true;
        }
        if (inputDialogState != 0) {
            inputDialogState = 0;
            inputTaken = true;
        }
        openInterfaceID = interfaceID;
        aBoolean1149 = false;
    }
	public void clearTopInterfaces() { 
		 
		stream.createFrame(130);
		if (invOverlayInterfaceID != -1) {
			invOverlayInterfaceID = -1;
			needDrawTabArea = true;
			aBoolean1149 = false;
			tabAreaAltered = true;
		}
		if (backDialogID != -1) {
			backDialogID = -1;
			inputTaken = true;
			aBoolean1149 = false;
		}
		openInterfaceID = -1;
		fullscreenInterfaceID = -1;
	}

	public client() { 
	tabHPos = -1;
		alertHandler = new AlertHandler(this);
		fullscreenInterfaceID = -1;
		chatRights = new int[500];
		chatTypeView = 0;
		clanChatMode = 0;
		cButtonHPos = -1;
		cButtonHCPos = -1;
		cButtonCPos = 0;
		server = "127.0.0.1";
		anIntArrayArray825 = new int[104][104];
		friendsNodeIDs = new int[200];
		groundArray = new NodeList[4][104][104];
		aBoolean831 = false;
		aStream_834 = new Stream(new byte[17000]);
		npcArray = new NPC[16384];
		npcIndices = new int[16384];
		anIntArray840 = new int[1000];
		aStream_847 = Stream.create();
		aBoolean848 = true;
		openInterfaceID = -1;
		currentExp = new int[Skills.skillsCount];
		aBoolean872 = false;
		anIntArray873 = new int[5];
		anInt874 = -1;
		aBooleanArray876 = new boolean[5];
		drawFlames = false;
		reportAbuseInput = "";
		unknownInt10 = -1;
		menuOpen = false;
		inputString = "";
		maxPlayers = 2048;
		myPlayerIndex = 2047;
		playerArray = new Player[maxPlayers];
		playerIndices = new int[maxPlayers];
		anIntArray894 = new int[maxPlayers];
		aStreamArray895s = new Stream[maxPlayers];
		anInt897 = 1;
		anIntArrayArray901 = new int[104][104];
		anInt902 = 0x766654;
		aByteArray912 = new byte[16384];
		currentStats = new int[Skills.skillsCount];
		ignoreListAsLongs = new long[100];
		loadingError = false;
		anInt927 = 0x332d25;
		anIntArray928 = new int[5];
		anIntArrayArray929 = new int[104][104];
		chatTypes = new int[500];
		chatNames = new String[500];
		chatMessages = new String[500];
		chatButtons = new Sprite[4];
		sideIcons = new Sprite[14];
		sIcons483 = new Sprite[14];
		newSideIcons = new Sprite[16];
		sIcons459 = new Sprite[13];
		
		redStones = new Sprite[16];
		aBoolean954 = true;
		friendsListAsLongs = new long[200];
		currentSong = -1;
		drawingFlames = false;
		spriteDrawX = -1;
		spriteDrawY = -1;
		anIntArray968 = new int[33];
		anIntArray969 = new int[256];
		decompressors = new Decompressor[5];
		variousSettings = new int[2000];
		aBoolean972 = false;
		anInt975 = 50;
		anIntArray976 = new int[anInt975];
		anIntArray977 = new int[anInt975];
		anIntArray978 = new int[anInt975];
		anIntArray979 = new int[anInt975];
		anIntArray980 = new int[anInt975];
		anIntArray981 = new int[anInt975];
		anIntArray982 = new int[anInt975];
		aStringArray983 = new String[anInt975];
		anInt985 = -1;
		hitMarks = new Sprite[24];
		hitMark = new Sprite[4];
		anIntArray990 = new int[5];
		aBoolean994 = false;
		anInt1002 = 0x23201b;
		amountOrNameInput = "";
		aClass19_1013 = new NodeList();
		aBoolean1017 = false;
		anInt1018 = -1;
		anIntArray1030 = new int[5];
		aBoolean1031 = false;
		mapFunctions = new Sprite[100];
		dialogID = -1;
		applyTrans = 0;
		maxStats = new int[Skills.skillsCount];
		anIntArray1045 = new int[2000];
		aBoolean1047 = true;
		anIntArray1052 = new int[152];//map
		anInt1054 = -1;
		aClass19_1056 = new NodeList();
		anIntArray1057 = new int[33];
		aClass9_1059 = new RSInterface();
		mapScenes = new Background[100];
		barFillColor = 0x4d4233;
		anIntArray1065 = new int[7];
		anIntArray1072 = new int[1000];
		anIntArray1073 = new int[1000];
		aBoolean1080 = false;
		friendsList = new String[200];
		inStream = Stream.create();
		expectedCRCs = new int[9];
		menuActionCmd2 = new int[500];
		menuActionCmd3 = new int[500];
		menuActionID = new int[500];
		menuActionCmd1 = new int[500];
		headIcons = new Sprite[29];
		skullIcons = new Sprite[20];
		headIconsHint = new Sprite[20];
		tabAreaAltered = false;
		aString1121 = "";
		atPlayerActions = new String[5];
		atPlayerArray = new boolean[5];
		anIntArrayArrayArray1129 = new int[4][13][13];
		anInt1132 = 2;
		aSpriteArray1140 = new Sprite[1000];
		aBoolean1141 = false;
		aBoolean1149 = false;
		crosses = new Sprite[8];
		musicEnabled = true;
		needDrawTabArea = false;
		loggedIn = false;
		canMute = false;
		aBoolean1159 = false;
		aBoolean1160 = false;
		anInt1171 = 1;
		myUsername = "";
		myPassword = "";
		genericLoadingError = false;
		reportAbuseInterfaceID = -1;
		aClass19_1179 = new NodeList();
		anInt1184 = 128;
		invOverlayInterfaceID = -1;
		stream = Stream.create();
		menuActionName = new String[500];
		anIntArray1203 = new int[5];
		anIntArray1207 = new int[50];
		anInt1210 = 2;
		anInt1211 = 78;
		promptInput = "";
		//modIcons = new Background[2];
		modIcons = new Sprite[7];
		chatImages = new Sprite[7];
		tabID = 3;
		inputTaken = false;
		songChanging = true;
		anIntArray1229 = new int[152];//map
		aClass11Array1230 = new Class11[4];
		aBoolean1233 = false;
		anIntArray1240 = new int[100];
		anIntArray1241 = new int[50];
		aBoolean1242 = false;
		anIntArray1250 = new int[50];
		rsAlreadyLoaded = false;
		welcomeScreenRaised = true;
		messagePromptRaised = false;
		loginMessage1 = "";
		loginMessage2 = "";
		backDialogID = -1;
		anInt1279 = 2;
		bigX = new int[4000];
		bigY = new int[4000];
		anInt1289 = -1;
	}
	
	public int rights;
	
	
	public String name;
	public String disabledMessage;
	public String clanname;
	private final int[] chatRights;
	public int chatTypeView;
	public int clanChatMode;
	public int duelMode;
	/* Declare custom sprites */
	//private Sprite chatArea;
private Sprite loadingPleaseWait;
private Sprite reestablish;
	private Sprite loadingBarFull;
	private Sprite loadingBarEmpty;
	private Sprite[] chatButtons;
	//private Sprite tabArea;
	private Sprite mapArea;
		private Sprite tabHover;
		private Sprite tabClicked;
		private Sprite[] newSideIcons;
	//private Sprite CustomMapback;
	public Sprite[] CustomMapback = new Sprite[5];
	public Sprite[] chatArea = new Sprite[5];
	public Sprite[] tabArea = new Sprite[5];
	//public Sprite[] mapArea = new Sprite[3];
	/**/
	private RSImageProducer leftFrame;
	private RSImageProducer topFrame;
	private RSImageProducer rightFrame;
	private int ignoreCount;
	private long aLong824;
	private int[][] anIntArrayArray825;
	private int[] friendsNodeIDs;
	private NodeList[][][] groundArray;
	private int[] anIntArray828;
	private int[] anIntArray829;
	private volatile boolean aBoolean831;
	private Socket aSocket832;
	private int loginScreenState;
	private Stream aStream_834;
	private NPC[] npcArray;
	private int npcCount;
	private int[] npcIndices;
	private int anInt839;
	private int[] anIntArray840;
	private int anInt841;
	private int anInt842;
	private int anInt843;
	private String aString844;
	private int privateChatMode;
	private Stream aStream_847;
	private boolean aBoolean848;
	private static int anInt849;
	private int[] anIntArray850;
	private int[] anIntArray851;
	private int[] anIntArray852;
	private int[] anIntArray853;
	private static int anInt854;
	private int anInt855;
	private int openInterfaceID;
	private int xCameraPos;
	private int zCameraPos;
	private int yCameraPos;
	private int yCameraCurve;
	private int xCameraCurve;
	public int myPrivilege;
	private final int[] currentExp;
	private Sprite[] redStones;
	private Sprite mapFlag;
	private Sprite mapMarker;
	private boolean aBoolean872;
	private final int[] anIntArray873;
	private int anInt874;
	private final boolean[] aBooleanArray876;
	private int weight;
	private MouseDetection mouseDetection;
	private volatile boolean drawFlames;
	private String reportAbuseInput;
	private int unknownInt10;
	private boolean menuOpen;
	private int anInt886;
	private String inputString;
	private final int maxPlayers;
	private final int myPlayerIndex;
	private Player[] playerArray;
	private int playerCount;
	private int[] playerIndices;
	private int anInt893;
	private int[] anIntArray894;
	private Stream[] aStreamArray895s;
	private int anInt896;
	private int anInt897;
	private int friendsCount;
	private int anInt900;
	private int[][] anIntArrayArray901;
	private final int anInt902;
	private byte[] aByteArray912;
	private int anInt913;
	private int crossX;
	private int crossY;
	private int crossIndex;
	private int crossType;
	private int plane;
	private final int[] currentStats;
	private static int anInt924;
	private final long[] ignoreListAsLongs;
	private boolean loadingError;
	private final int anInt927;
	private final int[] anIntArray928;
	private int[][] anIntArrayArray929;
	private Sprite aSprite_931;
	private Sprite aSprite_932;
	private int anInt933;
	private int anInt934;
	private int anInt935;
	private int anInt936;
	private int anInt937;
	private int anInt938;
	private static int anInt940;
	private final int[] chatTypes;
	private final String[] chatNames;
	private final String[] chatMessages;
	private int anInt945;
	private WorldController worldController;
	private Sprite[] sideIcons;
	private Sprite[] sIcons483;
	private Sprite[] sIcons459;
	private int menuScreenArea;
	private int menuOffsetX;
	private int menuOffsetY;
	private int menuWidth;
	private int menuHeight;
	private long aLong953;
	private boolean aBoolean954;
	private long[] friendsListAsLongs;
	private String[] clanList = new String[100];
	private int currentSong;
	private static int nodeID = 10;
	static int portOff;
	static boolean fps;
	private static boolean isMembers = true;
	private static boolean lowMem;
	private volatile boolean drawingFlames;
	private int spriteDrawX;
	private int spriteDrawY;
	private final int[] anIntArray965 = {
		0xffff00, 0xff0000, 65280, 65535, 0xff00ff, 0xffffff
	};
	private Background titleBox;
	public Sprite titleBox1;
	private Background titleButton;
	private final int[] anIntArray968;
	private final int[] anIntArray969;
	final Decompressor[] decompressors;
	public int variousSettings[];
	private boolean aBoolean972;
	private final int anInt975;
	private final int[] anIntArray976;
	private final int[] anIntArray977;
	private final int[] anIntArray978;
	private final int[] anIntArray979;
	private final int[] anIntArray980;
	private final int[] anIntArray981;
	private final int[] anIntArray982;
	private final String[] aStringArray983;
	private int anInt984;
	private int anInt985;
	private static int anInt986;
	private Sprite[] hitMarks;
	private Sprite[] hitMark;
	private int anInt988;
	private int anInt989;
	private final int[] anIntArray990;
	private static boolean aBoolean993;
	private final boolean aBoolean994;
	private int anInt995;
	private int anInt996;
	private int anInt997;
	private int anInt998;
	private int anInt999;
	private ISAACRandomGen encryption;
	private Sprite mapEdge;
	private Sprite multiOverlay;
	private final int anInt1002;
	static final int[][] anIntArrayArray1003 = {
		{
			6798, 107, 10283, 16, 4797, 7744, 5799, 4634, 33697, 22433, 
			2983, 54193
		}, {
			8741, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 10153, 
			56621, 4783, 1341, 16578, 35003, 25239
		}, {
			25238, 8742, 12, 64030, 43162, 7735, 8404, 1701, 38430, 24094, 
			10153, 56621, 4783, 1341, 16578, 35003
		}, {
			4626, 11146, 6439, 12, 4758, 10270
		}, {
			4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574
		}
	};
	private String amountOrNameInput;
	private static int anInt1005;
	private int daysSinceLastLogin;
	private int pktSize;
	private int pktType;
	private int anInt1009;
	private int anInt1010;
	private int anInt1011;
	private NodeList aClass19_1013;
	private int anInt1014;
	private int anInt1015;
	private int anInt1016;
	private boolean aBoolean1017;
	private int anInt1018;
	private static final int[] anIntArray1019;
	private int anInt1021;
	private int anInt1022;
	private int loadingStage;
	private Sprite scrollBar1;
	private Sprite scrollBar2;
	private Sprite scrollBar3;
	private Sprite scrollBar4;
	private int anInt1026;
	private Background backBase1;
	private Background backBase2;
	private Background backHmid1;
	private final int[] anIntArray1030;
	private boolean aBoolean1031;
	private Sprite[] mapFunctions;
	private int baseX;
	private int baseY;
	private int anInt1036;
	private int anInt1037;
	private int loginFailures;
	private int anInt1039;
	private int anInt1040;
	private int anInt1041;
	private int dialogID;
	private final int[] maxStats;
	private final int[] anIntArray1045;
	private int anInt1046;
	private boolean aBoolean1047;
	private int anInt1048;
	private String aString1049;
	private static int anInt1051;
	private final int[] anIntArray1052;
	private NamedArchive titleStreamLoader;
	private int anInt1054;
	private int anInt1055;
	private NodeList aClass19_1056;
	private final int[] anIntArray1057;
	public final RSInterface aClass9_1059;
	private Background[] mapScenes;
	private static int anInt1061;
	private int anInt1062;
	private final int barFillColor;
	private int friendsListAction;
	private final int[] anIntArray1065;
	private int mouseInvInterfaceIndex;
	private int lastActiveInvInterface;
	public OnDemandFetcher onDemandFetcher;
	private int anInt1069;
	private int anInt1070;
	private int anInt1071;
	private int[] anIntArray1072;
	private int[] anIntArray1073;
	private Sprite mapDotItem;
	private Sprite mapDotNPC;
	private Sprite mapDotPlayer;
	private Sprite mapDotFriend;
	private Sprite mapDotTeam;
	private Sprite mapDotClan;
	private int anInt1079;
	private boolean aBoolean1080;
	private String[] friendsList;
	private Stream inStream;
	private int anInt1084;
	private int anInt1085;
	private int activeInterfaceType;
	private int anInt1087;
	private int anInt1088;
	public static int anInt1089;
	private final int[] expectedCRCs;
	public int[] menuActionCmd2;
	public int[] menuActionCmd3;
	public int[] menuActionID;
	public int[] menuActionCmd1;
	private Sprite[] headIcons;
	private Sprite[] skullIcons;
	private Sprite[] headIconsHint;
	private static int anInt1097;
	private int anInt1098;
	private int anInt1099;
	private int anInt1100;
	private int anInt1101;
	private int anInt1102;
	private static boolean tabAreaAltered;
	private int anInt1104;
	private RSImageProducer aRSImageProducer_1107;
	private RSImageProducer aRSImageProducer_1108;
	private RSImageProducer loginScreenArea;
	private RSImageProducer leftSideFlame;
	private RSImageProducer rightSideFlame;
	private RSImageProducer gameLogo;
	private RSImageProducer aRSImageProducer_1113;
	private RSImageProducer aRSImageProducer_1114;
	private RSImageProducer aRSImageProducer_1115;
	private static int anInt1117;
	private int membersInt;
	private String aString1121;
	private Sprite compass;
	private RSImageProducer aRSImageProducer_1123;
	private RSImageProducer aRSImageProducer_1124;
	private RSImageProducer aRSImageProducer_1125;
	public static Player myPlayer;
	private final String[] atPlayerActions;
	private final boolean[] atPlayerArray;
	private final int[][][] anIntArrayArrayArray1129;
	private final int[] tabInterfaceIDs = {
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
        -1, -1, -1, -1, -1, -1, -1
    };
	private int anInt1131;
	private int anInt1132;
	public int menuActionRow;
	private static int anInt1134;
	private int spellSelected;
	private int anInt1137;
	private int spellUsableOn;
	private String spellTooltip;
	private Sprite[] aSpriteArray1140;
	private boolean aBoolean1141;
	private static int anInt1142;
	public int energy;
	private boolean aBoolean1149;
	private Sprite[] crosses;
	private boolean musicEnabled;
	private Background[] aBackgroundArray1152s;
	private static boolean needDrawTabArea;
	private int unreadMessages;
	private static int anInt1155;
	public boolean loggedIn;
	private boolean canMute;
	private boolean aBoolean1159;
	private boolean aBoolean1160;
	static int loopCycle;
	private static final String validUserPassChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ";
	private RSImageProducer inventoryBackImage;
	private RSImageProducer mapEdgeIP;
	private RSImageProducer mapBackImage;
	private RSImageProducer inGameScreen;
	private RSImageProducer chatBackImage;
	private int daysSinceRecovChange;
	private RSSocket socketStream;
	private int anInt1169;
public int applyTrans;
	private int minimapInt3;
	private int anInt1171;
	private long aLong1172;
	private String myUsername;
	private String myPassword;
	private static int anInt1175;
	private boolean genericLoadingError;
	private final int[] anIntArray1177 = {
		0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 
		2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 
		2, 2, 3
	};
	private int reportAbuseInterfaceID;
	private NodeList aClass19_1179;
	private int[] anIntArray1180;
	private int[] anIntArray1181;
	private int[] anIntArray1182;
	private byte[][] aByteArrayArray1183;
	private int anInt1184;
	private int minimapInt1;
	private int anInt1186;
	private int anInt1187;
	private static int anInt1188;
	private int invOverlayInterfaceID;
	private int[] anIntArray1190;
	private int[] anIntArray1191;
	public Stream stream;
	private int anInt1193;
	private int splitPrivateChat;
	private Background mapBack;
	public String[] menuActionName;
	private Sprite aSprite_1201;
	private Sprite aSprite_1202;
	private final int[] anIntArray1203;
	static final int[] anIntArray1204 = {
		9104, 10275, 7595, 3610, 7975, 8526, 918, 38802, 24466, 10145, 
		58654, 5027, 1457, 16565, 34991, 25486
	};
	private static boolean flagged;
	private final int[] anIntArray1207;
	private int anInt1208;
	private int minimapInt2;
	private int anInt1210;
	private int anInt1211;
	private String promptInput;
	private int anInt1213;
	private int[][][] intGroundArray;
	private long aLong1215;
	private int loginScreenCursorPos;
	//private final Background[] modIcons;
	public final Sprite[] modIcons;
	private long aLong1220;
	private static int tabID;
	private int anInt1222;
	public static boolean inputTaken;
	public int inputDialogState;
	private static int anInt1226;
	private int nextSong;
	private boolean songChanging;
	private final int[] anIntArray1229;
	private Class11[] aClass11Array1230;
	public static int anIntArray1232[];
	private boolean aBoolean1233;
	private int[] anIntArray1234;
	private int[] anIntArray1235;
	private int[] anIntArray1236;
	private int anInt1237;
	private int anInt1238;
	public final int anInt1239 = 100;
	private final int[] anIntArray1240;
	private final int[] anIntArray1241;
	private boolean aBoolean1242;
	private int atInventoryLoopCycle;
	private int atInventoryInterface;
	private int atInventoryIndex;
	private int atInventoryInterfaceType;
	private byte[][] aByteArrayArray1247;
	private int tradeMode;
	private int anInt1249;
	private final int[] anIntArray1250;
	private int anInt1251;
	private final boolean rsAlreadyLoaded;
	private int anInt1253;
	private int anInt1254;
	private boolean welcomeScreenRaised;
	private boolean messagePromptRaised;
	private int anInt1257;
	private byte[][][] byteGroundArray;
	private int prevSong;
	private int destX;
	private int destY;
	private Sprite aSprite_1263;
	private int anInt1264;
	private int anInt1265;
	private String loginMessage1;
	private String loginMessage2;
	private int anInt1268;
	private int anInt1269;
	public RSFont smallText;
	public RSFont aTextDrawingArea_1271;
	private RSFont chatTextDrawingArea;
	private int anInt1275;
	private int backDialogID;
	private int anInt1278;
	private int anInt1279;
	private int[] bigX;
	private int[] bigY;
	private int itemSelected;
	private int anInt1283;
	private int anInt1284;
	private int anInt1285;
	private String selectedItemName;
	private int publicChatMode;
	private static int anInt1288;
	private int anInt1289;
	public static int anInt1290;
	public static String server = "127.0.0.1";
	
	public int drawCount;
	public int fullscreenInterfaceID;
	public int anInt1044;//377
	public int anInt1129;//377
	public int anInt1315;//377
	public int anInt1500;//377
	public int anInt1501;//377
	public int[] fullScreenTextureArray;
	public void resetAllImageProducers() {
		if (super.fullGameScreen != null) {
			return;
		}
		chatBackImage = null;
		mapBackImage = null;
		inventoryBackImage = null;
		inGameScreen = null;
		aRSImageProducer_1124 = null;
		aRSImageProducer_1125 = null;
		aRSImageProducer_1107 = null;
		aRSImageProducer_1108 = null;
		loginScreenArea = null;
		leftSideFlame = null;
		rightSideFlame = null;
		gameLogo = null;
		aRSImageProducer_1113 = null;
		aRSImageProducer_1114 = null;
		aRSImageProducer_1115 = null;
		super.fullGameScreen = new RSImageProducer(765, 503, getGameComponent());
		welcomeScreenRaised = true;
	}
	public boolean spriteChanged = false;
	public int getSpriteID(){
		spriteChanged = true;
		if(is562)
			return 4;
		if(is459)
			return 3;
		if(is480)
			return 2;
		if(is508 || is525)
			return 1;
		if(is474)
			return 0;
		else
			return 0;		
	}
	public void launchURL(String url) { 
		String osName = System.getProperty("os.name"); 
		try { 
			if (osName.startsWith("Mac OS")) { 
				@SuppressWarnings("rawtypes")
				Class fileMgr = Class.forName("com.apple.eio.FileManager"); 
				@SuppressWarnings("unchecked")
				Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] {String.class}); 
				openURL.invoke(null, new Object[] {url});
			} else if (osName.startsWith("Windows")) 
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url); 
			else { //assume Unix or Linux
				String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape", "safari" }; 
			String browser = null; 
			for (int count = 0; count < browsers.length && browser == null; count++) 
				if (Runtime.getRuntime().exec(new String[] {"which", browsers[count]}).waitFor() == 0)
					browser = browsers[count]; 
			if (browser == null) {
				 throw new Exception("Could not find web browser"); 
			} else
				 Runtime.getRuntime().exec(new String[] {browser, url});
			}
		} catch (Exception e) { 
			pushMessage("Failed to open URL.", 0, "");
		}
	}

	static  {
		anIntArray1019 = new int[99];
		int i = 0;
		for(int j = 0; j < 99; j++) {
			int l = j + 1;
			int i1 = (int)((double)l + 300D * Math.pow(2D, (double)l / 7D));
			i += i1;
			anIntArray1019[j] = i / 4;
		}
		anIntArray1232 = new int[32];
		i = 2;
		for(int k = 0; k < 32; k++) {
			anIntArray1232[k] = i - 1;
			i += i;
		}
	
	}
public Sprite coinOrb;
public Sprite coinPart;
public boolean coinToggle = false;
public Sprite coinOrbPart;

public void drawCoinParts(){
	if(!coinToggle){
	if(super.mouseX >= 512 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118){
		coinOrbPart = new Sprite("coinpart hover");
	} else {
		coinOrbPart = new Sprite("coinpart normal");
		}
	}
	coinOrbPart.drawSprite(505+4, 93-11);
}

public void drawCoinOrb(){//512 nontoggle
	if(!coinToggle) {//436,87 534 x 118 y
		if(super.mouseX >= 512 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118){
			coinOrb = new Sprite("coin simple hover");
		} else {
			coinOrb = new Sprite("coin simple normal");
		}
	} else if(coinToggle) {
		if(super.mouseX >= 436 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118){
			coinOrb = new Sprite("coin simple hover toggle");
			coinPart = new Sprite("coin complex hover toggle");
		} else {
			coinOrb = new Sprite("coin simple normal toggle");
			coinPart = new Sprite("coin complex normal toggle");
		}
	}
		coinOrb.drawSprite(520-300-200-20-4, 47+37);//+37

	if(super.mouseX >= 436 && super.mouseX <= 546 && super.mouseY >= 87 && super.mouseY <= 118) {
		coinPart = new Sprite("coin complex hover toggle");
		coinOrb = new Sprite("coin simple hover");
	}
}

	public int getMoneyOrbColor(int cashAmount){
		if(cashAmount >= 100000 && cashAmount <= 999999){
			return 0xffffff;
		} else {
			return 65280;
		}
	}
void sendPacket(int packet) {
if (packet == 103) {
stream.createFrame(103);
stream.writeWordBigEndian(inputString.length() - 1);
stream.writeString(inputString.substring(2));
inputString = "";
promptInput = "";
interfaceButtonAction = 0;
}
}
}
