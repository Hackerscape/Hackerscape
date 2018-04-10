public final class RSInterface {

        public void swapInventoryItems(int i, int j) {
                int k = inventory[i];
                inventory[i] = inventory[j];
                inventory[j] = k;
                k = inventoryValue[i];
                inventoryValue[i] = inventoryValue[j];
                inventoryValue[j] = k;
        }

	public static void unpack(NamedArchive archive, RSFont rsFonts[], NamedArchive streamLoader_1) {
		spriteNodes = new MRUNodes(50000);
		Stream stream = new Stream(archive.getDataForName("data"));
		int i = -1;
		int j = stream.readUnsignedWord();
		interfaceCache = new RSInterface[j + 31000];
		while(stream.currentOffset < stream.buffer.length) {
			int k = stream.readUnsignedWord();
			if(k == 65535) {
				i = stream.readUnsignedWord();
				k = stream.readUnsignedWord();
			}
			RSInterface rsInterface = interfaceCache[k] = new RSInterface();
			rsInterface.id = k;
			rsInterface.parentID = i;
			rsInterface.interfaceType = stream.readUnsignedByte();
			rsInterface.atActionType = stream.readUnsignedByte();
			rsInterface.contentType = stream.readUnsignedWord();
			rsInterface.width = stream.readUnsignedWord();
			rsInterface.height = stream.readUnsignedWord();
			rsInterface.opacity = (byte) stream.readUnsignedByte();
			rsInterface.hoverType = stream.readUnsignedByte();
			if(rsInterface.hoverType != 0)
				rsInterface.hoverType = (rsInterface.hoverType - 1 << 8) + stream.readUnsignedByte();
			else
				rsInterface.hoverType = -1;
			int i1 = stream.readUnsignedByte();
			if(i1 > 0) {
				rsInterface.valueCompareType = new int[i1];
				rsInterface.requiredValues = new int[i1];
				for(int j1 = 0; j1 < i1; j1++) {
					rsInterface.valueCompareType[j1] = stream.readUnsignedByte();
					rsInterface.requiredValues[j1] = stream.readUnsignedWord();
				}
			}
			int k1 = stream.readUnsignedByte();
			if(k1 > 0) {
				rsInterface.valueIndexArray = new int[k1][];
				for(int l1 = 0; l1 < k1; l1++) {
					int i3 = stream.readUnsignedWord();
					rsInterface.valueIndexArray[l1] = new int[i3];
					for(int l4 = 0; l4 < i3; l4++)
						rsInterface.valueIndexArray[l1][l4] = stream.readUnsignedWord();
				}
			}
			if(rsInterface.interfaceType == 0) {
				rsInterface.drawsTransparent = false;
				rsInterface.scrollMax = stream.readUnsignedWord();
				rsInterface.interfaceShown = stream.readUnsignedByte() == 1;
				int i2 = stream.readUnsignedWord();
				rsInterface.children = new int[i2];
				rsInterface.childX = new int[i2];
				rsInterface.childY = new int[i2];
				for(int j3 = 0; j3 < i2; j3++) {
					rsInterface.children[j3] = stream.readUnsignedWord();
					rsInterface.childX[j3] = stream.readSignedWord();
					rsInterface.childY[j3] = stream.readSignedWord();
				}
			}
			if(rsInterface.interfaceType == 1) {
				stream.readUnsignedWord();
				stream.readUnsignedByte();
			}
			if(rsInterface.interfaceType == 2) {
				rsInterface.inventory = new int[rsInterface.width * rsInterface.height];
				rsInterface.inventoryValue = new int[rsInterface.width * rsInterface.height];
				rsInterface.allowSwapItems = stream.readUnsignedByte() == 1;
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.usableItemInterface = stream.readUnsignedByte() == 1;
				rsInterface.deletesTargetSlot = stream.readUnsignedByte() == 1;
				rsInterface.invSpritePadX = stream.readUnsignedByte();
				rsInterface.invSpritePadY = stream.readUnsignedByte();
				rsInterface.spritesX = new int[20];
				rsInterface.spritesY = new int[20];
				rsInterface.sprites = new Sprite[20];
				for(int j2 = 0; j2 < 20; j2++) {
					int k3 = stream.readUnsignedByte();
					if(k3 == 1) {
						rsInterface.spritesX[j2] = stream.readSignedWord();
						rsInterface.spritesY[j2] = stream.readSignedWord();
						String s1 = stream.readString();
						if(streamLoader_1 != null && s1.length() > 0) {
							int i5 = s1.lastIndexOf(",");
							rsInterface.sprites[j2] = method207(Integer.parseInt(s1.substring(i5 + 1)), streamLoader_1, s1.substring(0, i5));
						}
					}
				}
				rsInterface.itemActions = new String[5];
				for(int l3 = 0; l3 < 5; l3++) {
					rsInterface.itemActions[l3] = stream.readString();
					if(rsInterface.itemActions[l3].length() == 0)
						rsInterface.itemActions[l3] = null;
					if(rsInterface.parentID == 3824)
                        rsInterface.itemActions[4] = "Buy 50";
					if(rsInterface.parentID == 1644)
						rsInterface.itemActions[2] = "Operate";
				}
			}
			if(rsInterface.interfaceType == 3)
				rsInterface.boxFilled = stream.readUnsignedByte() == 1;
			if(rsInterface.interfaceType == 4 || rsInterface.interfaceType == 1) {
				rsInterface.textCentered = stream.readUnsignedByte() == 1;
				int k2 = stream.readUnsignedByte();
				if(rsFonts != null)
					rsInterface.rsFonts = rsFonts[k2];
				rsInterface.textShadowed = stream.readUnsignedByte() == 1;
			}
			if(rsInterface.interfaceType == 4) {
				rsInterface.disabledMessage = stream.readString().replaceAll("RuneScape", "Hackerscape");
				rsInterface.enabledMessage = stream.readString();
			}
			if(rsInterface.interfaceType == 1 || rsInterface.interfaceType == 3 || rsInterface.interfaceType == 4)
				rsInterface.disabledColor = stream.readDWord();
			if(rsInterface.interfaceType == 3 || rsInterface.interfaceType == 4) {
				rsInterface.enabledColor = stream.readDWord();
				rsInterface.disabledHoverColor = stream.readDWord();
				rsInterface.enabledHoverColor = stream.readDWord();
			}
			if(rsInterface.interfaceType == 5) {
				rsInterface.drawsTransparent = false;
				String s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int i4 = s.lastIndexOf(",");
					rsInterface.disabledSprite = method207(Integer.parseInt(s.substring(i4 + 1)), streamLoader_1, s.substring(0, i4));
				}
				s = stream.readString();
				if(streamLoader_1 != null && s.length() > 0) {
					int j4 = s.lastIndexOf(",");
					rsInterface.enabledSprite = method207(Integer.parseInt(s.substring(j4 + 1)), streamLoader_1, s.substring(0, j4));
				}
			}
			if(rsInterface.interfaceType == 6) {
				int l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.disabledMediaType = 1;
					rsInterface.disabledMediaID = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0) {
					rsInterface.enabledMediaType = 1;
					rsInterface.enabledMediaID = (l - 1 << 8) + stream.readUnsignedByte();
				}
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.disabledAnimation = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.disabledAnimation = -1;
				l = stream.readUnsignedByte();
				if(l != 0)
					rsInterface.enabledAnimation = (l - 1 << 8) + stream.readUnsignedByte();
				else
					rsInterface.enabledAnimation = -1;
				rsInterface.modelZoom = stream.readUnsignedWord();
				rsInterface.modelRotationY = stream.readUnsignedWord();
				rsInterface.modelRotationX = stream.readUnsignedWord();
			}
			if(rsInterface.interfaceType == 7) {
				rsInterface.inventory = new int[rsInterface.width * rsInterface.height];
				rsInterface.inventoryValue = new int[rsInterface.width * rsInterface.height];
				rsInterface.textCentered = stream.readUnsignedByte() == 1;
				int l2 = stream.readUnsignedByte();
				if(rsFonts != null)
					rsInterface.rsFonts = rsFonts[l2];
				rsInterface.textShadowed = stream.readUnsignedByte() == 1;
				rsInterface.disabledColor = stream.readDWord();
				rsInterface.invSpritePadX = stream.readSignedWord();
				rsInterface.invSpritePadY = stream.readSignedWord();
				rsInterface.isInventoryInterface = stream.readUnsignedByte() == 1;
				rsInterface.itemActions = new String[5];
				for(int k4 = 0; k4 < 5; k4++) {
					rsInterface.itemActions[k4] = stream.readString();
					if(rsInterface.itemActions[k4].length() == 0)
						rsInterface.itemActions[k4] = null;
				}

			}
			if(rsInterface.atActionType == 2 || rsInterface.interfaceType == 2) {
				rsInterface.selectedActionName = stream.readString();
				rsInterface.spellName = stream.readString();
				rsInterface.spellUsableOn = stream.readUnsignedWord();
			}

			if(rsInterface.interfaceType == 8)
				rsInterface.disabledMessage = stream.readString();

			if(rsInterface.atActionType == 1 || rsInterface.atActionType == 4 || rsInterface.atActionType == 5 || rsInterface.atActionType == 6) {
				rsInterface.tooltip = stream.readString();
				if(rsInterface.tooltip.length() == 0) {
					if(rsInterface.atActionType == 1)
						rsInterface.tooltip = "Ok";
					if(rsInterface.atActionType == 4)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 5)
						rsInterface.tooltip = "Select";
					if(rsInterface.atActionType == 6)
						rsInterface.tooltip = "Continue";
				}
			}
		}
		aClass44 = archive;
			extraEquipment(rsFonts);
		PVPInterface(rsFonts);
		PVPInterface2(rsFonts);
			//editShopMain(rsFonts);
			//FriendList(rsFonts);
			//IgnoreList(rsFonts);
				friendsTab(rsFonts);
				skillTab(rsFonts);
				Hovers(rsFonts);
				ignoreTab(rsFonts);
			configureBank(rsFonts);

			SummonTab(rsFonts);
			Rules(rsFonts);
			configureCharDesign(rsFonts);
			newTrade(rsFonts);
			//skillInterface(rsFonts);
			equipmentScreen(rsFonts);
			godwarsMenu(rsFonts);
			musicTab(rsFonts);
			itemsOnDeathDATA(rsFonts);
			itemsOnDeath(rsFonts);
			EquipmentTab(rsFonts);
			questTab(rsFonts);
			StaffInterfaces(rsFonts);
			Logout(rsFonts);
			choosespell(rsFonts);
			emoteTab();
			optionTab(rsFonts);
			clanChatTab(rsFonts);
			Sidebar0(rsFonts);
			Pestpanel(rsFonts);
			Pestpanel2(rsFonts);
			magicTab(rsFonts);
			ancientMagicTab(rsFonts);
			KKK(rsFonts);
			Classes(rsFonts);
			configureLunar(rsFonts);
			Dungeoneering(rsFonts);
			teleport(rsFonts);
			constructLunar();
			Curses(rsFonts);
			minigame(rsFonts);
			boss(rsFonts);
			wilderness(rsFonts);
			prayerMenu(rsFonts);
			extraOptions(rsFonts);
			GodWars(rsFonts);
		spriteNodes = null;
	}
	@SuppressWarnings("unused")
	private static Sprite LoadLunarSprite(int i, String s) {
		Sprite sprite = imageLoader(i,"Interfaces/Lunar/" + s);
		return sprite;
	}
	private static Sprite CustomSpriteLoader(int id, String s) {
        long l = (TextClass.method585(s) << 8) + (long)id;
        Sprite sprite = (Sprite)spriteNodes.insertFromCache(l);
        if(sprite != null) { return sprite; }
        try {
            sprite = new Sprite("/Interfaces/Attack/"+id + s);
            spriteNodes.removeFromCache(sprite, l);
        } catch(Exception exception) { 
			return null; }
        return sprite;
    }
	private static Sprite imageLoader(int i, String s) {
		long l = (TextClass.method585(s) << 8) + (long)i;
		Sprite sprite = (Sprite) spriteNodes.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(s+" "+i);
			spriteNodes.removeFromCache(sprite, l);
		} catch(Exception exception) {
			return null;
		}
		return sprite;
	}
	private static Sprite imageLoader(String s) {
		long l = (TextClass.method585(s) << 8);
		Sprite sprite = (Sprite) spriteNodes.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(s);
			spriteNodes.removeFromCache(sprite, l);
		} catch(Exception exception) {
			return null;
		}
		return sprite;
	}
	public static void drawBlackBox(int xPos, int yPos) {
		DrawingArea.drawPixels(71, yPos - 1, xPos - 2, 0x726451, 1);
		DrawingArea.drawPixels(69, yPos, xPos + 174, 0x726451, 1);
		DrawingArea.drawPixels(1, yPos - 2, xPos - 2, 0x726451, 178);
		DrawingArea.drawPixels(1, yPos + 68, xPos, 0x726451, 174);
		DrawingArea.drawPixels(71, yPos - 1, xPos - 1, 0x2E2B23, 1);
		DrawingArea.drawPixels(71, yPos - 1, xPos + 175, 0x2E2B23, 1);
		DrawingArea.drawPixels(1, yPos - 1, xPos, 0x2E2B23, 175);
		DrawingArea.drawPixels(1, yPos + 69, xPos, 0x2E2B23, 175);
        DrawingArea.method335(0x000000, yPos, 174, 68, 220, xPos);
    }
	public static void Classes(RSFont[] rsFonts) {
		RSInterface tab = addTabInterface(17050);
		addSprite(17051, 1, "Interfaces/Classes/CHOOSE");
		addHover(17052, 1, 0, 19151, 1, "Interfaces/Classes/MAGIC", 134, 180, "Choose Mage As A Class");
		addHover(17053, 1, 0, 19151, 3, "Interfaces/Classes/MELEE", 134, 180, "Choose Melee As A Class");
		addHover(17054, 1, 0, 19151, 2, "Interfaces/Classes/RANGE", 134, 180, "Choose Range As A Class");
		tab.totalChildren(4);
		tab.child(0, 17051, 10, 15);
		tab.child(1, 17052, 30, 78);
		tab.child(2, 17053, 182, 78);
		tab.child(3, 17054, 334, 78);
		}
	public static void teleport(RSFont[] rsFonts) {
      RSInterface localRSInterface = addInterface(17650);
    addSprite(17651, 10, "CLICK");
    addHoverButton(17652, "CLICK", 2, 200, 30, "Which Zone?", -1, 17653, 1);
    addHoveredButton(17653, "CLICK", 2, 200, 30, 17654);
    addHoverButton(17655, "CLICK", 3, 200, 30, "Which Zone?", -1, 17656, 1);
    addHoveredButton(17656, "CLICK", 3, 200, 30, 17657);
    addHoverButton(17658, "CLICK", 3, 200, 30, "Which Zone?", -1, 17659, 1);
    addHoveredButton(17659, "CLICK", 3, 200, 30, 17660);
    addHoverButton(17661, "CLICK", 3, 200, 30, "Which Zone?", -1, 17662, 1);
    addHoveredButton(17662, "CLICK", 3, 200, 30, 17663);
    addHoverButton(17664, "CLICK", 3, 200, 30, "Which Zone?", -1, 17665, 1);
    addHoveredButton(17665, "CLICK", 3, 200, 30, 17666);
    addHoverButton(17667, "CLICK", 3, 200, 30, "Which Zone?", -1, 17668, 1);
    addHoveredButton(17668, "CLICK", 3, 200, 30, 17669);
    addHoverButton(17670, "CLICK", 3, 200, 30, "Which Zone?", -1, 17671, 1);
    addHoveredButton(17671, "CLICK", 3, 200, 30, 17672);
    addHoverButton(17673, "CLICK", 1, 200, 30, "Stop Viewing", -1, 17674, 1);
    addHoveredButton(17674, "CLICK", 1, 200, 30, 17675);
    addText(22804, "Rock Crabs", rsFonts, 0, 16750623, false, true);
    addText(22808, "Taverly Dungeon", rsFonts, 0, 16750623, false, true);
    addText(22812, "Slayer Tower", rsFonts, 0, 16750623, false, true);
    addText(22816, "Brimhaven Dungeon", rsFonts, 0, 16750623, false, true);
    addText(22820, "Hill Giants", rsFonts, 0, 16750623, false, true);
    addText(22824, "Dark Beasts", rsFonts, 0, 16750623, false, true);
    addText(22828, "Strykeworms", rsFonts, 0, 16750623, false, true);
    localRSInterface.totalChildren(24);
    localRSInterface.child(0, 17651, 0, 0);
    localRSInterface.child(1, 17652, 12, 40);
    localRSInterface.child(2, 17653, 11, 40);
    localRSInterface.child(3, 17655, 12, 65);
    localRSInterface.child(4, 17656, 11, 65);
    localRSInterface.child(5, 17658, 12, 90);
    localRSInterface.child(6, 17659, 11, 90);
    localRSInterface.child(7, 17661, 12, 115);
    localRSInterface.child(8, 17662, 11, 115);
    localRSInterface.child(9, 17664, 12, 143);
    localRSInterface.child(10, 17665, 11, 143);
    localRSInterface.child(11, 17667, 12, 168);
    localRSInterface.child(12, 17668, 11, 168);
    localRSInterface.child(13, 17670, 12, 193);
    localRSInterface.child(14, 17671, 11, 193);
    localRSInterface.child(15, 17673, 38, 236);
    localRSInterface.child(16, 17674, 38, 236);
    localRSInterface.child(17, 22804, 38, 45);
    localRSInterface.child(18, 22808, 38, 70);
    localRSInterface.child(19, 22812, 38, 95);
    localRSInterface.child(20, 22816, 38, 120);
    localRSInterface.child(21, 22820, 38, 147);
    localRSInterface.child(22, 22824, 38, 174);
    localRSInterface.child(23, 22828, 38, 201);
    localRSInterface = addTabInterface(14000);
    localRSInterface.width = 474;
    localRSInterface.height = 213;
    localRSInterface.scrollMax = 305;
    for (int i = 14001; i <= 14030; ++i) {
      addText(i, "", rsFonts, 1, 16777215, false, true);
    }
    localRSInterface.totalChildren(30);
    int i = 0;
    int j = 5;
    for (int k = 14001; k <= 14030; ++k) {
      localRSInterface.child(i, k, 248, j);
      ++i;
      j += 13;
    }
  }
  public static void KKK(RSFont[] rsFonts) {
      RSInterface localRSInterface = addInterface(30650);
    addSprite(30651, 10, "CLICK");
    addHoverButton(30652, "CLICK", 2, 200, 30, "Which Zone?", -1, 30653, 1);
    addHoveredButton(30653, "CLICK", 2, 200, 30, 30654);
    addHoverButton(30655, "CLICK", 3, 200, 30, "Which Zone?", -1, 30656, 1);
    addHoveredButton(30656, "CLICK", 3, 200, 30, 30657);
    addHoverButton(30658, "CLICK", 3, 200, 30, "Which Zone?", -1, 30659, 1);
    addHoveredButton(30659, "CLICK", 3, 200, 30, 30660);
    addHoverButton(30661, "CLICK", 3, 200, 30, "Which Zone?", -1, 30662, 1);
    addHoveredButton(30662, "CLICK", 3, 200, 30, 30663);
    addHoverButton(30664, "CLICK", 3, 200, 30, "Which Zone?", -1, 30665, 1);
    addHoveredButton(30665, "CLICK", 3, 200, 30, 30666);
    addHoverButton(30667, "CLICK", 3, 200, 30, "Which Zone?", -1, 30668, 1);
    addHoveredButton(30668, "CLICK", 3, 200, 30, 30669);
    addHoverButton(30670, "CLICK", 3, 200, 30, "Which Zone?", -1, 30671, 1);
    addHoveredButton(30671, "CLICK", 3, 200, 30, 30672);
    addHoverButton(30673, "CLICK", 1, 200, 30, "Stop Viewing", -1, 30674, 1);
    addHoveredButton(30674, "CLICK", 1, 200, 30, 30675);
    addText(22804, "B", rsFonts, 0, 16750623, false, true);
    addText(22808, "C", rsFonts, 0, 16750623, false, true);
    addText(22812, "A", rsFonts, 0, 16750623, false, true);
    addText(22816, "C", rsFonts, 0, 16750623, false, true);
    addText(22820, "Q", rsFonts, 0, 16750623, false, true);
    addText(22824, "R", rsFonts, 0, 16750623, false, true);
    addText(22828, "T", rsFonts, 0, 16750623, false, true);
    localRSInterface.totalChildren(24);
    localRSInterface.child(0, 30651, 0, 0);
    localRSInterface.child(1, 30652, 12, 40);
    localRSInterface.child(2, 30653, 11, 40);
    localRSInterface.child(3, 30655, 12, 65);
    localRSInterface.child(4, 30656, 11, 65);
    localRSInterface.child(5, 30658, 12, 90);
    localRSInterface.child(6, 30659, 11, 90);
    localRSInterface.child(7, 30661, 12, 115);
    localRSInterface.child(8, 30662, 11, 115);
    localRSInterface.child(9, 30664, 12, 143);
    localRSInterface.child(10, 30665, 11, 143);
    localRSInterface.child(11, 30667, 12, 168);
    localRSInterface.child(12, 30668, 11, 168);
    localRSInterface.child(13, 30670, 12, 193);
    localRSInterface.child(14, 30671, 11, 193);
    localRSInterface.child(15, 30673, 38, 236);
    localRSInterface.child(16, 30674, 38, 236);
    localRSInterface.child(17, 30804, 38, 45);
    localRSInterface.child(18, 30808, 38, 70);
    localRSInterface.child(19, 30812, 38, 95);
    localRSInterface.child(20, 30816, 38, 120);
    localRSInterface.child(21, 30820, 38, 147);
    localRSInterface.child(22, 30824, 38, 174);
    localRSInterface.child(23, 30828, 38, 201);
    localRSInterface = addTabInterface(14000);
    localRSInterface.width = 474;
    localRSInterface.height = 213;
    localRSInterface.scrollMax = 305;
    for (int i = 14001; i <= 14030; ++i) {
      addText(i, "", rsFonts, 1, 16777215, false, true);
    }
    localRSInterface.totalChildren(30);
    int i = 0;
    int j = 5;
    for (int k = 14001; k <= 14030; ++k) {
      localRSInterface.child(i, k, 248, j);
      ++i;
      j += 13;
    }
  }

  public static void StaffInterfaces(RSFont[] rsFonts) {
	RSInterface Interface = addInterface(5000);
	setChildren(9, Interface);
	addSprite(5001, 1, "Interfaces/StaffInterfaces/BG");
	addHover(5002, 3, 0, 5003, 1, "Interfaces/StaffInterfaces/CLOSE", 17, 17, "Exit");
	addHovered(5003, 2, "Interfaces/StaffInterfaces/CLOSE", 17, 17, 4504);
	addButton(5004, 1, "Interfaces/StaffInterfaces/BUTTON", 64, 64, "Open Up Your Bank", 1);
	addButton(5005, 2, "Interfaces/StaffInterfaces/BUTTON", 64, 64, "Receive Max Cash", 1);
	addButton(5006, 3, "Interfaces/StaffInterfaces/BUTTON", 64, 64, "Teleport To Home", 1);
	addButton(5007, 4, "Interfaces/StaffInterfaces/BUTTON", 64, 64, "Teleport To Staff Zone", 1);
	addButton(5008, 5, "Interfaces/StaffInterfaces/BUTTON", 64, 64, "Boost Your Stats", 1);
	addButton(5009, 6, "Interfaces/StaffInterfaces/BUTTON", 64, 64, "Get Unlimit Special Attack", 1);
	setBounds(5001, 15, 145, 0, Interface);
	setBounds(5002, 476, 148, 1, Interface);
	setBounds(5003, 476, 148, 2, Interface);
	setBounds(5004, 65, 184, 3, Interface);
	setBounds(5005, 131, 184, 4, Interface);
	setBounds(5006, 197, 184, 5, Interface);
	setBounds(5007, 262, 184, 6, Interface);
	setBounds(5008, 329, 184, 7, Interface);
	setBounds(5009, 395, 184, 8, Interface);
	}

  public static void Dungeoneering(RSFont[] rsFonts) {
		RSInterface tab = addTabInterface(18070);
		addSprite(18051, 1, "Interfaces/Dungeoneering/DONATOR");
		addHover(18052, 1, 0, 19151, 1, "Interfaces/Dungeoneering/CROSSBOW", 46, 50, "Buy 1 Ring of Vigour");
		addHover(18053, 1, 0, 19151, 2, "Interfaces/Dungeoneering/SHIELD", 46, 50, "Buy 1 Chaotic Shield");
		addHover(18054, 1, 0, 19151, 3, "Interfaces/Dungeoneering/STAFF", 46, 50, "Buy 1 Pernix Body");
		addHover(18055, 1, 0, 19151, 4, "Interfaces/Dungeoneering/MAUL", 46, 50, "Buy 1 Pernix Chaps");
		addHover(18056, 1, 0, 19151, 5, "Interfaces/Dungeoneering/LONGSWORD", 46, 50, "Buy 1 Virtus Robe Bottom");
		addHover(18057, 1, 0, 19151, 6, "Interfaces/Dungeoneering/RAPIER", 46, 50, "Buy 1 Virtus Robe Top");
		addHover(18058, 1, 0, 19151, 7, "Interfaces/Dungeoneering/EXPERIENCE", 46, 50, "Buy Dungeoneering Experience");
		addHover(18059, 1, 0, 19151, 8, "Interfaces/Dungeoneering/STREAM", 46, 50, "Buy 1 Virtus Mask");
		addHover(18060, 1, 0, 19151, 9, "Interfaces/Dungeoneering/RIGOUR", 46, 50, "Buy 1 Pernix Cowl");
		addHover(18061, 1, 0, 19151, 10, "Interfaces/Dungeoneering/GRAPIER", 46, 50, "Buy 1 Guthan's Set");
		addHover(18062, 1, 0, 19151, 11, "Interfaces/Dungeoneering/GHSWORD", 46, 50, "Buy 1 Verac's Set");
		addHover(18063, 1, 0, 19151, 12, "Interfaces/Dungeoneering/GSTAFF", 46, 50, "Buy 1 Ahrim's Set");
		addHover(18064, 1, 0, 19151, 13, "Interfaces/Dungeoneering/GSHORT", 46, 50, "Buy 1 Dharok's Set");
		addHover(18065, 1, 0, 19151, 14, "Interfaces/Dungeoneering/GSHORT", 46, 50, "Buy 1 Karil's Set");
		addHover(18066, 1, 0, 19151, 15, "Interfaces/Dungeoneering/BUY", 132, 25, "Buy");
		addHover(18067, 1, 0, 19151, 16, "Interfaces/Dungeoneering/EAGLE", 46, 50, "Buy 1 Eagle-Eye Shield");
		addHover(18068, 1, 0, 19151, 17, "Interfaces/Dungeoneering/FARSEER", 46, 50, "Buy 1 Farseer Shield");
		addHover(18069, 3, 0, 19151, 18, "Interfaces/Dungeoneering/CLOSE", 16, 16, "Close");
		addText(18071, "", 0xFF981F, false, true, 52, rsFonts, 2);
			
		tab.totalChildren(20);
		tab.child(0, 18051, 4, 4);
		tab.child(1, 18052, 17, 234);
		tab.child(2, 18053, 78, 234);
		tab.child(3, 18054, 257, 167);
		tab.child(4, 18055, 196, 167);
		tab.child(5, 18056, 136, 167);
		tab.child(6, 18057, 79, 167);
		tab.child(7, 18058, 258, 234);
		tab.child(8, 18059, 18, 167);
		tab.child(9, 18060, 256, 100);
		tab.child(10, 18061, 19, 32);
		tab.child(11, 18062, 139, 34);
		tab.child(12, 18063, 199, 33);
		tab.child(13, 18064, 258, 34);
		tab.child(14, 18065, 79, 34);
		tab.child(15, 18066, 347, 280);
		tab.child(16, 18067, 136, 235);
		tab.child(17, 18068, 195, 234);
		tab.child(18, 18069, 482, 6);
		tab.child(19, 18071, 134, 300);

	}
	public static final void wilderness(RSFont[] tda)
	{
		RSInterface rsinterface = addTabInterface(45600);
		addText(45601, "P'King Teleport", 0xff9b00, false, true, 52, tda, 2);
		addHoverButton(45602, "Interfaces/Minigame/Hover", 0, 172, 24, "Mage Bank", -1, 45603, 1);
		addHoveredButton(45603, "Interfaces/Minigame/Hover", 2, 172, 24, 45604);
		addHoverButton(45618, "Interfaces/Minigame/Hover", 0, 172, 24, "Varrock PK (Multi)", -1, 45619, 1);
		addHoveredButton(45619, "Interfaces/Minigame/Hover", 2, 172, 24, 45620);
		addHoverButton(45621, "Interfaces/Minigame/Hover", 0, 172, 24, "Lava Crossing", -1, 45622, 1);
		addHoveredButton(45622, "Interfaces/Minigame/Hover", 2, 172, 24, 45623);
		addHoverButton(45624, "Interfaces/Minigame/Hover", 0, 172, 24, "Edgeville", -1, 45625, 1);
		addHoveredButton(45625, "Interfaces/Minigame/Hover", 2, 172, 24, 45626);
		addHoverButton(45627, "Interfaces/Minigame/Hover", 0, 172, 24, "Green Dragons", -1, 45628, 1);
		addHoveredButton(45628, "Interfaces/Minigame/Hover", 2, 172, 24, 45629);
		addHoverButton(45633, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 45634, 1);
		addHoveredButton(45634, "Interfaces/Minigame/Back", 1, 16, 16, 45635);
		addSprite(45605, 1, "Interfaces/Minigame/Pk");
		addSprite(45606, 1, "Interfaces/Minigame/Pk");
		addSprite(45607, 1, "Interfaces/Minigame/Pk");
		addSprite(45608, 1, "Interfaces/Minigame/Pk");
		addSprite(45609, 1, "Interfaces/Minigame/Pk");
		addSprite(45611, 1, "Interfaces/Minigame/Background");
		addText(45612, "Mage Bank", 0xd67b29, true, true, 52, tda, 2);
		addText(45613, "Varrock PK (Multi)", 0xd67b29, true, true, 52, tda, 2);
		addText(45614, "Lava Crossing", 0xd67b29, true, true, 52, tda, 2);
		addText(45615, "Edgeville", 0xd67b29, true, true, 52, tda, 2);
		addText(45616, "Green Dragons", 0xd67b29, true, true, 52, tda, 2);
		byte childAmount = 24;
		int indexChild = 0;
		setChildren(childAmount, rsinterface);
		setBounds(45611, -1, 26, indexChild, rsinterface);
		indexChild++;
		setBounds(45601, 33, 7, indexChild, rsinterface);
		indexChild++;
		setBounds(45602, 8, 35, indexChild, rsinterface);
		indexChild++;
		setBounds(45603, 8, 35, indexChild, rsinterface);
		indexChild++;
		setBounds(45612, 80, 39, indexChild, rsinterface);
		indexChild++;
		setBounds(45618, 8, 72, indexChild, rsinterface);
		indexChild++;
		setBounds(45619, 8, 72, indexChild, rsinterface);
		indexChild++;
		setBounds(45613, 80, 76, indexChild, rsinterface);
		indexChild++;
		setBounds(45621, 8, 109, indexChild, rsinterface);
		indexChild++;
		setBounds(45622, 8, 109, indexChild, rsinterface);
		indexChild++;
		setBounds(45614, 80, 113, indexChild, rsinterface);
		indexChild++;
		setBounds(45624, 8, 146, indexChild, rsinterface);
		indexChild++;
		setBounds(45625, 8, 146, indexChild, rsinterface);
		indexChild++;
		setBounds(45615, 80, 150, indexChild, rsinterface);
		indexChild++;
		setBounds(45627, 8, 183, indexChild, rsinterface);
		indexChild++;
		setBounds(45628, 8, 183, indexChild, rsinterface);
		indexChild++;
		setBounds(45616, 80, 187, indexChild, rsinterface);
		indexChild++;
		setBounds(45605, 148, 34, indexChild, rsinterface);
		indexChild++;
		setBounds(45606, 148, 71, indexChild, rsinterface);
		indexChild++;
		setBounds(45607, 148, 108, indexChild, rsinterface);
		indexChild++;
		setBounds(45608, 148, 146, indexChild, rsinterface);
		indexChild++;
		setBounds(45609, 148, 183, indexChild, rsinterface);
		indexChild++;
		setBounds(45633, 10, 6, indexChild, rsinterface);
		indexChild++;
		setBounds(45634, 10, 6, indexChild, rsinterface);
		indexChild++;
	}
	
		public static final void boss(RSFont[] tda)
	{
		RSInterface rsinterface = addTabInterface(45500);
		addText(45501, "Boss Teleport", 0xff9b00, false, true, 52, tda, 2);
		addHoverButton(45502, "Interfaces/Minigame/Hover", 0, 172, 24, "God Wars", -1, 45503, 1);
		addHoveredButton(45503, "Interfaces/Minigame/Hover", 3, 172, 24, 45504);
		addHoverButton(45518, "Interfaces/Minigame/Hover", 0, 172, 24, "King Black Dragon", -1, 45519, 1);
		addHoveredButton(45519, "Interfaces/Minigame/Hover", 3, 172, 24, 45520);
		addHoverButton(45521, "Interfaces/Minigame/Hover", 0, 172, 24, "Dagannoth Kings", -1, 45522, 1);
		addHoveredButton(45522, "Interfaces/Minigame/Hover", 3, 172, 24, 45523);
		addHoverButton(45524, "Interfaces/Minigame/Hover", 0, 172, 24, "Tormented Demons", -1, 45525, 1);
		addHoveredButton(45525, "Interfaces/Minigame/Hover", 3, 172, 24, 45526);
		addHoverButton(45527, "Interfaces/Minigame/Hover", 0, 172, 24, "Corporal Beast", -1, 45528, 1);
		addHoveredButton(45528, "Interfaces/Minigame/Hover", 3, 172, 24, 45529);
		addHoverButton(45533, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 45534, 1);
		addHoveredButton(45534, "Interfaces/Minigame/Back", 1, 16, 16, 45535);
		addSprite(45505, 1, "Interfaces/Minigame/Godwars");
		addSprite(45506, 1, "Interfaces/Minigame/Kbd");
		addSprite(45507, 1, "Interfaces/Minigame/Dagganoths");
		addSprite(45508, 1, "Interfaces/Minigame/Chaos");
		addSprite(45509, 1, "Interfaces/Minigame/Corporeal");
		addSprite(45511, 1, "Interfaces/Minigame/Background");
		addText(45512, "Godwars", 0xd67b29, true, true, 52, tda, 2);
		addText(45513, "King Black Dragon", 0xd67b29, true, true, 52, tda, 2);
		addText(45514, "Dagannoth Kings", 0xd67b29, true, true, 52, tda, 2);
		addText(45515, "Tormented Demons", 0xd67b29, true, true, 52, tda, 2);
		addText(45516, "Corporal Beast", 0xd67b29, true, true, 52, tda, 2);
		byte childAmount = 24;
		int indexChild = 0;
		setChildren(childAmount, rsinterface);
		setBounds(45511, -1, 26, indexChild, rsinterface);
		indexChild++;
		setBounds(45501, 33, 7, indexChild, rsinterface);
		indexChild++;
		setBounds(45502, 8, 35, indexChild, rsinterface);
		indexChild++;
		setBounds(45503, 8, 35, indexChild, rsinterface);
		indexChild++;
		setBounds(45512, 80, 39, indexChild, rsinterface);
		indexChild++;
		setBounds(45518, 8, 72, indexChild, rsinterface);
		indexChild++;
		setBounds(45519, 8, 72, indexChild, rsinterface);
		indexChild++;
		setBounds(45513, 80, 76, indexChild, rsinterface);
		indexChild++;
		setBounds(45521, 8, 109, indexChild, rsinterface);
		indexChild++;
		setBounds(45522, 8, 109, indexChild, rsinterface);
		indexChild++;
		setBounds(45514, 80, 113, indexChild, rsinterface);
		indexChild++;
		setBounds(45524, 8, 146, indexChild, rsinterface);
		indexChild++;
		setBounds(45525, 8, 146, indexChild, rsinterface);
		indexChild++;
		setBounds(45515, 80, 150, indexChild, rsinterface);
		indexChild++;
		setBounds(45527, 8, 183, indexChild, rsinterface);
		indexChild++;
		setBounds(45528, 8, 183, indexChild, rsinterface);
		indexChild++;
		setBounds(45516, 80, 187, indexChild, rsinterface);
		indexChild++;
		setBounds(45505, 148, 33, indexChild, rsinterface);
		indexChild++;
		setBounds(45506, 148, 70, indexChild, rsinterface);
		indexChild++;
		setBounds(45507, 148, 104, indexChild, rsinterface);
		indexChild++;
		setBounds(45508, 148, 144, indexChild, rsinterface);
		indexChild++;
		setBounds(45509, 148, 179, indexChild, rsinterface);
		indexChild++;
		setBounds(45533, 10, 6, indexChild, rsinterface);
		indexChild++;
		setBounds(45534, 10, 6, indexChild, rsinterface);
		indexChild++;
	}
	
		public static final void minigame(RSFont[] tda)
	{
		RSInterface rsinterface = addTabInterface(45200);
		addText(45201, "Minigames Teleport", 0xff9b00, false, true, 52, tda, 2);
		addHoverButton(45202, "Interfaces/Minigame/Hover", 0, 172, 24, "Duel Arena", -1, 45203, 1);
		addHoveredButton(45203, "Interfaces/Minigame/Hover", 4, 172, 24, 45204);
		addHoverButton(45218, "Interfaces/Minigame/Hover", 0, 172, 24, "Barrows", -1, 45219, 1);
		addHoveredButton(45219, "Interfaces/Minigame/Hover", 4, 172, 24, 45220);
		addHoverButton(45221, "Interfaces/Minigame/Hover", 0, 172, 24, "Pest Control", -1, 45222, 1);
		addHoveredButton(45222, "Interfaces/Minigame/Hover", 4, 172, 24, 45223);
		addHoverButton(45224, "Interfaces/Minigame/Hover", 0, 172, 24, "Tzhaar", -1, 45225, 1);
		addHoveredButton(45225, "Interfaces/Minigame/Hover", 4, 172, 24, 45226);
		addHoverButton(45227, "Interfaces/Minigame/Hover", 0, 172, 24, "Warriors Guild", -1, 45228, 1);
		addHoveredButton(45228, "Interfaces/Minigame/Hover", 4, 172, 24, 45229);
		addHoverButton(45233, "Interfaces/Minigame/Back", 0, 16, 16, "Back", -1, 45234, 1);
		addHoveredButton(45234, "Interfaces/Minigame/Back", 1, 16, 16, 45235);
		addSprite(45205, 1, "Interfaces/Minigame/DuelArena");
		addSprite(45206, 1, "Interfaces/Minigame/Barrows");
		addSprite(45207, 1, "Interfaces/Minigame/PestControl");
		addSprite(45208, 1, "Interfaces/Minigame/Tzhaar");
		addSprite(45209, 1, "Interfaces/Minigame/Warriors");
		addSprite(45211, 1, "Interfaces/Minigame/Background");
		addText(45212, "Duel Arena", 0xd67b29, true, true, 52, tda, 2);
		addText(45213, "Barrows", 0xd67b29, true, true, 52, tda, 2);
		addText(45214, "Pest Control", 0xd67b29, true, true, 52, tda, 2);
		addText(45215, "Tzhaar", 0xd67b29, true, true, 52, tda, 2);
		addText(45216, "Warriors Guild", 0xd67b29, true, true, 52, tda, 2);
		byte childAmount = 24;
		int indexChild = 0;
		setChildren(childAmount, rsinterface);
		setBounds(45211, 0, 26, indexChild, rsinterface);
		indexChild++;
		setBounds(45201, 33, 7, indexChild, rsinterface);
		indexChild++;
		setBounds(45202, 8, 35, indexChild, rsinterface);
		indexChild++;
		setBounds(45203, 8, 35, indexChild, rsinterface);
		indexChild++;
		setBounds(45212, 80, 39, indexChild, rsinterface);
		indexChild++;
		setBounds(45218, 8, 72, indexChild, rsinterface);
		indexChild++;
		setBounds(45219, 8, 72, indexChild, rsinterface);
		indexChild++;
		setBounds(45213, 80, 76, indexChild, rsinterface);
		indexChild++;
		setBounds(45221, 8, 109, indexChild, rsinterface);
		indexChild++;
		setBounds(45222, 8, 109, indexChild, rsinterface);
		indexChild++;
		setBounds(45214, 80, 113, indexChild, rsinterface);
		indexChild++;
		setBounds(45224, 8, 146, indexChild, rsinterface);
		indexChild++;
		setBounds(45225, 8, 146, indexChild, rsinterface);
		indexChild++;
		setBounds(45215, 80, 150, indexChild, rsinterface);
		indexChild++;
		setBounds(45227, 8, 183, indexChild, rsinterface);
		indexChild++;
		setBounds(45228, 8, 183, indexChild, rsinterface);
		indexChild++;
		setBounds(45216, 80, 187, indexChild, rsinterface);
		indexChild++;
		setBounds(45205, 148, 33, indexChild, rsinterface);
		indexChild++;
		setBounds(45206, 148, 70, indexChild, rsinterface);
		indexChild++;
		setBounds(45207, 148, 104, indexChild, rsinterface);
		indexChild++;
		setBounds(45208, 148, 140, indexChild, rsinterface);
		indexChild++;
		setBounds(45209, 148, 179, indexChild, rsinterface);
		indexChild++;
		setBounds(45233, 10, 6, indexChild, rsinterface);
		indexChild++;
		setBounds(45234, 10, 6, indexChild, rsinterface);
		indexChild++;
	}
  
  
	
	public void child(int id, int interID, int x, int y) {
		children[id] = interID;
		childX[id] = x;
		childY[id] = y;
	}
	public void totalChildren(int t) {
		children = new int[t];
		childX = new int[t];
		childY = new int[t];
	}
	public void totalChildren(int id, int x, int y) {
		children = new int[id];
		childX = new int[x];
		childY = new int[y];
	}
	public static void setChildren(int total, RSInterface rsinterface) {
		rsinterface.children = new int[total];
		rsinterface.childX = new int[total];
		rsinterface.childY = new int[total];
	}
	public static void setBounds(int ID, int X, int Y, int frame, RSInterface RSinterface){
		RSinterface.children[frame] = ID;
		RSinterface.childX[frame] = X;
		RSinterface.childY[frame] = Y;
	}
	public static void setBoundry(int frame, int ID, int X, int Y, RSInterface RSInterface) {
		RSInterface.children[frame] = ID;
		RSInterface.childX[frame] = X;
		RSInterface.childY[frame] = Y;
	}
	public static void removeSomething(int id) {
		@SuppressWarnings("unused")
		RSInterface rsi = interfaceCache[id] = new RSInterface();
	}
	public static void disabledSprite(int id, int sprite) {
		RSInterface class9 = interfaceCache[id];
        class9.disabledSprite = CustomSpriteLoader(sprite, "");
    }
	public static void disabledColor(int id, int color) {
		RSInterface rsi = interfaceCache[id]; rsi.disabledColor = color;
	}
    public static void textSize(int id, RSFont tda[], int idx) {
		RSInterface rsi = interfaceCache[id]; rsi.rsFonts = tda[idx];
	}
	
	public static void addHoverBox(int id, String text) {
        RSInterface rsi = interfaceCache[id];
        rsi.id = id;
        rsi.parentID = id;
		rsi.interfaceShown = true;
        rsi.interfaceType = 8;
        rsi.hoverText = text;
    }
	public static void addTooltipBox(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.parentID = id;
		rsi.interfaceType = 9;
		rsi.disabledMessage = text;
	}
	public static void addTooltip(int id, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.interfaceType = 0;
		rsi.interfaceShown = true;
		rsi.hoverType = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
	}
	public static void drawTooltip(int id, String text) {
		RSInterface rsinterface = addTabInterface(id);
		rsinterface.parentID = id;
		rsinterface.interfaceType = 0;
		rsinterface.interfaceShown = true;
		rsinterface.hoverType = -1;
		addTooltipBox(id + 1, text);
		rsinterface.totalChildren(1);
		rsinterface.child(0, id + 1, 0, 0);
	}
	public static void addTooltip(int id, int frame, int X, int Y, String text) {
		RSInterface rsi = addInterface(id);
		rsi.id = id;
		rsi.interfaceType = 0;
		rsi.interfaceShown = true;
		rsi.hoverType = -1;
		addTooltipBox(id + 1, text);
		rsi.totalChildren(1);
		rsi.child(0, id + 1, 0, 0);
		rsi.children[frame] = id;
		rsi.childX[frame] = X;
		rsi.childY[frame] = Y;
	}
	public static RSInterface addInterface(int id) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.id = id;
        rsi.parentID = id;
        rsi.width = 512;
        rsi.height = 334;
        return rsi;
    }
	public static RSInterface addTabInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 0;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 700;
		tab.opacity = (byte)0;
		tab.hoverType = -1;
		return tab;
	}
	public static RSInterface addScreenInterface(int id) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 0;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.opacity = (byte)0;
		tab.hoverType = 0;
		return tab;
	}
	
	public static RSInterface addTab(int i) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.interfaceType = 0;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 512;
		rsinterface.height = 334;
		rsinterface.opacity = 0;
		rsinterface.hoverType = 0;
		return rsinterface;
	}
	public static void AddInterfaceButton(int id, int sid, String spriteName, String tooltip) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = tab.disabledSprite.myWidth;
		tab.height = tab.enabledSprite.myHeight;
		tab.tooltip = tooltip;
	}
	public static void AddInterfaceButton(int i, int j, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.interfaceType = 5;
		RSInterface.atActionType = AT;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 52;
		RSInterface.disabledSprite = imageLoader(j,name);
		RSInterface.enabledSprite = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}
	public static void AddInterfaceButton(int id, int sid, String spriteName, String tooltip, int w, int h) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = w;
		tab.height = h;
		tab.tooltip = tooltip;
	}
	public static void AddInterfaceButton(int i, int j, int hoverId, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.interfaceType = 5;
		RSInterface.atActionType = AT;
		RSInterface.opacity = 0;
		RSInterface.hoverType = hoverId;
		RSInterface.disabledSprite = imageLoader(j,name);
		RSInterface.enabledSprite = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}
	public static void AddInterfaceButton(int id, int sid, String spriteName, String tooltip, int mOver, int atAction, int width, int height) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = atAction;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.hoverType = mOver;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = tooltip;
		tab.inventoryhover = true;
	}
	private static void AddInterfaceButton(int ID, int type, int hoverID, int dS, int eS, String NAME, int W, int H, String text, int configFrame, int configId) {
		RSInterface rsinterface = addInterface(ID);
		rsinterface.id = ID;
		rsinterface.parentID = ID;
		rsinterface.interfaceType = 5;
		rsinterface.atActionType = type;
		rsinterface.opacity = 0;
		rsinterface.hoverType = hoverID;
		rsinterface.disabledSprite = imageLoader(dS, NAME);
		rsinterface.enabledSprite = imageLoader(eS, NAME);
		rsinterface.width = W;
		rsinterface.height = H;
		rsinterface.valueCompareType = new int[1];
		rsinterface.requiredValues = new int[1];
		rsinterface.valueCompareType[0] = 1;
		rsinterface.requiredValues[0] = configId;
		rsinterface.valueIndexArray = new int[1][3];
		rsinterface.valueIndexArray[0][0] = 5;
		rsinterface.valueIndexArray[0][1] = configFrame;
		rsinterface.valueIndexArray[0][2] = 0;
		rsinterface.tooltip = text;
	}
	public static void AddInterfaceButtons(int id, int sid, String spriteName, String tooltip, int mOver, int atAction) {
		RSInterface rsinterface = interfaceCache[id] = new RSInterface();
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.interfaceType = 5;
		rsinterface.atActionType = atAction;
		rsinterface.contentType = 0;
		rsinterface.opacity = (byte)0;
		rsinterface.hoverType = mOver;
		rsinterface.disabledSprite = imageLoader(sid, spriteName);
		rsinterface.enabledSprite = imageLoader(sid, spriteName);
		rsinterface.width = rsinterface.disabledSprite.myWidth;
		rsinterface.height = rsinterface.enabledSprite.myHeight;
		rsinterface.tooltip = tooltip;
		rsinterface.inventoryhover = true;
	}
	public static void addToggleButton(int id, int sprite, int setconfig, int width, int height, String tooltip, int mOver) {
        RSInterface rsi = addInterface(id);
        rsi.disabledSprite = CustomSpriteLoader(sprite, "");
        rsi.enabledSprite = CustomSpriteLoader(sprite, "a");
        rsi.requiredValues = new int[1];
        rsi.requiredValues[0] = 1;
        rsi.valueCompareType = new int[1];
        rsi.valueCompareType[0] = 1;
        rsi.valueIndexArray = new int[1][3];
        rsi.valueIndexArray[0][0] = 5;
        rsi.valueIndexArray[0][1] = setconfig;
        rsi.valueIndexArray[0][2] = 0;
        rsi.atActionType = 4;
        rsi.width = width;
        rsi.hoverType = mOver;
        rsi.parentID = id;
        rsi.id = id;
        rsi.interfaceType = 5;
        rsi.height = height;
        rsi.tooltip = tooltip;
    }
	public static void addToggleButton(int id, int sprite, int setconfig, int width, int height, String s) {
        RSInterface rsi = addInterface(id);
        rsi.disabledSprite = CustomSpriteLoader(sprite, "");
        rsi.enabledSprite = CustomSpriteLoader(sprite, "a");
        rsi.requiredValues = new int[1];
        rsi.requiredValues[0] = 1;
        rsi.valueCompareType = new int[1];
        rsi.valueCompareType[0] = 1;
        rsi.valueIndexArray = new int[1][3];
        rsi.valueIndexArray[0][0] = 5;
        rsi.valueIndexArray[0][1] = setconfig;
        rsi.valueIndexArray[0][2] = 0;
        rsi.atActionType = 4;
        rsi.width = width;
        rsi.hoverType = -1;
        rsi.parentID = id;
        rsi.id = id;
        rsi.interfaceType = 5;
        rsi.height = height;
        rsi.tooltip = s;
    }
	public static void addActionButton(int id, int sprite, int enabledSprite, int width, int height, String s) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.disabledSprite = CustomSpriteLoader(sprite, "");
        if (enabledSprite == sprite)
          rsi.enabledSprite = CustomSpriteLoader(sprite, "a");
        else
          rsi.enabledSprite = CustomSpriteLoader(enabledSprite, "");
        rsi.tooltip = s;
        rsi.contentType = 0;
        rsi.atActionType = 1;
        rsi.width = width;
        rsi.hoverType = 52;
        rsi.parentID = id;
        rsi.id = id;
        rsi.interfaceType = 5;
        rsi.height = height;
    }
    public static void addActionButton(int id, int sprite, int enabledSprite, int width, int height, String s, int mOver) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.disabledSprite = CustomSpriteLoader(sprite, "");
        if (enabledSprite == sprite)
          rsi.enabledSprite = CustomSpriteLoader(sprite, "a");
        else
          rsi.enabledSprite = CustomSpriteLoader(enabledSprite, "");
        rsi.tooltip = s;
        rsi.contentType = 0;
        rsi.atActionType = 1;
        rsi.width = width;
        rsi.hoverType = mOver;
        rsi.parentID = id;
        rsi.id = id;
        rsi.interfaceType = 5;
        rsi.height = height;
    }
	public static void addActionButton1(int i, int j, int k, int width, int height, String s, int mOver) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.interfaceType = 5;
		rsinterface.atActionType = 1;
		rsinterface.contentType = 0;
		rsinterface.width = width;
		rsinterface.height = height;
		rsinterface.opacity = 0;
		rsinterface.hoverType = 52;
		rsinterface.disabledSprite = imageLoader(j, "Interfaces/Equipment/BOX");
		rsinterface.enabledSprite = imageLoader(k, "Interfaces/Equipment/BOX");
		rsinterface.tooltip = s;
	}
	public static void addConfigButton(int ID, int pID, int bID, int bID2, String bName, int width, int height, String tT, int configID, int aT, int configFrame) {
        RSInterface Tab = addTabInterface(ID);
        Tab.parentID = pID;
        Tab.id = ID;
        Tab.interfaceType = 5;
        Tab.atActionType = aT;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.opacity = 0;
        Tab.hoverType = -1;
        Tab.valueCompareType = new int[1];
        Tab.requiredValues = new int[1];
        Tab.valueCompareType[0] = 1;
        Tab.requiredValues[0] = configID;
        Tab.valueIndexArray = new int[1][3];
        Tab.valueIndexArray[0][0] = 5;
        Tab.valueIndexArray[0][1] = configFrame;
        Tab.valueIndexArray[0][2] = 0;
        Tab.disabledSprite = imageLoader(bID, bName);
        Tab.enabledSprite = imageLoader(bID2, bName);
        Tab.tooltip = tT;
    }
	public static void addHoverButton(int i, String imageName, int j, int width, int height, String text, int contentType, int hoverOver, int aT) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.interfaceType = 5;
		tab.atActionType = aT;
		tab.contentType = contentType;
		tab.opacity = 0;
		tab.hoverType = hoverOver;
		tab.disabledSprite = imageLoader(j, imageName);
		tab.enabledSprite = imageLoader(j, imageName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = text;
	}
	public static void addHoveredButton(int i, String imageName, int j, int w, int h, int IMAGEID) {
		RSInterface tab = addTabInterface(i);
		tab.parentID = i;
		tab.id = i;
		tab.interfaceType = 0;
		tab.atActionType = 0;
		tab.width = w;
		tab.height = h;
		tab.interfaceShown = true;
		tab.opacity = 0;
		tab.hoverType = -1;
		tab.scrollMax = 0;
		addHoverImage(IMAGEID, j, j, imageName);
		tab.totalChildren(1);
		tab.child(0, IMAGEID, 0, 0);
	}
	public static void addHover(int i, int aT, int cT, int hoverid,int sId, String NAME, int W, int H, String tip) { 
		RSInterface rsinterfaceHover = addInterface(i);
		rsinterfaceHover.id = i;
		rsinterfaceHover.parentID = i;
		rsinterfaceHover.interfaceType = 5;
		rsinterfaceHover.atActionType = aT;
		rsinterfaceHover.contentType = cT;
		rsinterfaceHover.hoverType = hoverid;
		rsinterfaceHover.disabledSprite = imageLoader(sId, NAME);
		rsinterfaceHover.enabledSprite = imageLoader(sId, NAME);
		rsinterfaceHover.width = W;
		rsinterfaceHover.height = H;
		rsinterfaceHover.tooltip = tip;
	}
	public static void addHovered(int i, int j, String imageName, int w, int h, int IMAGEID) {
		RSInterface rsinterfaceHover = addInterface(i);
		rsinterfaceHover.parentID = i;
		rsinterfaceHover.id = i;
		rsinterfaceHover.interfaceType = 0;
		rsinterfaceHover.atActionType = 0;
		rsinterfaceHover.width = w;
		rsinterfaceHover.height = h;
		rsinterfaceHover.interfaceShown = true;
		rsinterfaceHover.hoverType = -1;
		addSprite(IMAGEID, j, imageName);
		setChildren(1, rsinterfaceHover);
		setBounds(IMAGEID, 0, 0, 0, rsinterfaceHover);
	}
	
	
	@SuppressWarnings("static-access")
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,TextDrawingArea[] TDA, int j) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.parentID = i;
		RSInterface.id = i;
		RSInterface.interfaceType = 4;
		RSInterface.atActionType = 0;
		RSInterface.width = 0;
		RSInterface.height = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = a;
		RSInterface.textCentered = l;
		RSInterface.textShadowed = m;
		RSInterface.rsFonts = RSInterface.fonts[j];
		RSInterface.disabledMessage = s;
		RSInterface.enabledMessage = "";
		RSInterface.disabledColor = k;
	}
	public static void addText(int i, String s, int k, boolean l, boolean m, int a, int j) {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.interfaceType = 4;
        rsinterface.atActionType = 0;
        rsinterface.width = 0;
        rsinterface.height = 0;
        rsinterface.contentType = 0;
        rsinterface.opacity = 0;
        rsinterface.hoverType = a;
        rsinterface.textCentered = l;
        rsinterface.textShadowed = m;
        rsinterface.rsFonts = RSInterface.fonts[j];
        rsinterface.disabledMessage = s;
        rsinterface.disabledColor = k;
    }
	public static void addText(int id, String text, RSFont wid[], int idx, int color) {
		RSInterface rsinterface = addTab(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.interfaceType = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = -1;
		rsinterface.textCentered = false;
		rsinterface.textShadowed = true;
		rsinterface.rsFonts = wid[idx];
		rsinterface.disabledMessage = text;
		rsinterface.enabledMessage = "";
		rsinterface.disabledColor = color;
		rsinterface.enabledColor = 0;
		rsinterface.disabledHoverColor = 0;
		rsinterface.enabledHoverColor = 0;	
	}
	public static void addText(int id, String text, RSFont tda[], int idx, int color, boolean center) {
		RSInterface rsinterface = addInterface(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.interfaceType = 4;
		rsinterface.atActionType = 0;
		rsinterface.width = 0;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = -1;
		rsinterface.textCentered = center;
		rsinterface.textShadowed = true;
		rsinterface.rsFonts = tda[idx];
		rsinterface.disabledMessage = text;
		rsinterface.enabledMessage = "";
		rsinterface.disabledColor = color;
		rsinterface.enabledColor = 0;
		rsinterface.disabledHoverColor = 0;
		rsinterface.enabledHoverColor = 0;	
	}
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,RSFont[] TDA, int j) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.parentID = i;
		RSInterface.id = i;
		RSInterface.interfaceType = 4;
		RSInterface.atActionType = 0;
		RSInterface.width = 0;
		RSInterface.height = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = a;
		RSInterface.textCentered = l;
		RSInterface.textShadowed = m;
		RSInterface.rsFonts = TDA[j];
		RSInterface.disabledMessage = s;
		RSInterface.enabledMessage = "";
		RSInterface.disabledColor = k;
	}
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,RSFont[] TDA, int j, int dsc) {
		RSInterface rsinterface = addInterface(i);
		rsinterface.parentID = i;
		rsinterface.id = i;
		rsinterface.interfaceType = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = a;
		rsinterface.textCentered = l;
		rsinterface.textShadowed = m;
		rsinterface.rsFonts = TDA[j];
		rsinterface.disabledMessage = s;
		rsinterface.enabledMessage = "";
		rsinterface.enabledColor = 0;
		rsinterface.disabledColor = k;
		rsinterface.enabledHoverColor = dsc;
		rsinterface.tooltip = s;
	}
	public static void addText(int id, String text, RSFont tda[], int idx, int color, boolean centered, boolean textShadowed) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        if(centered)
          rsi.textCentered = true;
			rsi.textShadowed = textShadowed;
			rsi.rsFonts = tda[idx];
			rsi.disabledMessage = text;
			rsi.disabledColor = color;
			rsi.id = id;
			rsi.interfaceType = 4;
    }
	public static void addText(int i, String s, String tooltip, int k, boolean l, boolean m, int a,RSFont[] TDA, int j, int dsc) {
		RSInterface rsinterface = addInterface(i);
		rsinterface.parentID = i;
		rsinterface.id = i;
		rsinterface.interfaceType = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = 174;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = a;
		rsinterface.textCentered = l;
		rsinterface.textShadowed = m;
		rsinterface.rsFonts = TDA[j];
		rsinterface.disabledMessage = s;
		rsinterface.enabledMessage = "";
		rsinterface.enabledColor = 0;
		rsinterface.disabledHoverColor = k;
		rsinterface.enabledHoverColor = dsc;
		rsinterface.tooltip = tooltip;
	}
	public static void addHoverText(int id, String text, String tooltip, RSFont tda[], int idx, int color, boolean center, boolean textShadowed, int width) {
		RSInterface rsinterface = addInterface(id);
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.interfaceType = 4;
		rsinterface.atActionType = 1;
		rsinterface.width = width;
		rsinterface.height = 11;
		rsinterface.contentType = 0;
		rsinterface.opacity = 0;
		rsinterface.hoverType = -1;
		rsinterface.textCentered = center;
		rsinterface.textShadowed = textShadowed;
		rsinterface.rsFonts = tda[idx];
		rsinterface.disabledMessage = text;
		rsinterface.enabledMessage = "";
		rsinterface.disabledColor = color;
		rsinterface.enabledColor = 0;
		rsinterface.disabledHoverColor = 0xffffff;
		rsinterface.enabledHoverColor = 0;
		rsinterface.tooltip = tooltip;
	}
	public static void addText(int i, String s,int k, boolean l, boolean m, int a,TextDrawingArea[] TDA, int j, int dsc) {
        RSInterface rsinterface = addTabInterface(i);
        rsinterface.parentID = i;
        rsinterface.id = i;
        rsinterface.type = 4;
        rsinterface.atActionType = 1;
        rsinterface.width = 174;
        rsinterface.height = 11;
        rsinterface.contentType = 0;
        rsinterface.aByte254 = 0;
        rsinterface.mOverInterToTrigger = a;
        rsinterface.centerText = l;
        rsinterface.textShadow = m;
        rsinterface.textDrawingAreas = TDA[j];
        rsinterface.message = s;
        rsinterface.aString228 = "";
		rsinterface.anInt219 = 0;
        rsinterface.textColor = k;
		rsinterface.anInt21so6 = dsc;
		rsinterface.tooltip = s;
    }
	public static void addSprite(int id, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte) 0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(spriteName);
		tab.enabledSprite = imageLoader(spriteName);
		tab.width = 512;
		tab.height = 334;
	}
	public static void addSprite(int i, int j, int k) {
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.interfaceType = 5;
		rsinterface.atActionType = 1;
		rsinterface.contentType = 0;
		rsinterface.width = 20;
		rsinterface.height = 20;
		rsinterface.opacity = 0;
		rsinterface.hoverType = 52;
		rsinterface.disabledSprite = imageLoader(j, "Interfaces/Equipment/SPRITE");
		rsinterface.enabledSprite = imageLoader(k, "Interfaces/Equipment/SPRITE");
	}
	
	public static void addSprite(int id, String spriteName, String T, int H, int X, int Y) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = 1;
		tab.contentType = -1;
		tab.opacity = (byte) 0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(spriteName);
		tab.enabledSprite = imageLoader(spriteName);
		tab.tooltip = T;
		tab.hoverType = H;
		tab.width = X;
		tab.height = Y;
	}
	public static void addSprite(int id, int spriteId, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(spriteId, spriteName);
		tab.enabledSprite = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
	}
	public static void addCacheSprite(int id, int disabledSprite, int enabledSprite, String sprites) {
        RSInterface rsi = interfaceCache[id] = new RSInterface();
        rsi.disabledSprite = method207(disabledSprite, aClass44, sprites);
        rsi.enabledSprite = method207(enabledSprite, aClass44, sprites);
        rsi.parentID = id;
        rsi.id = id;
        rsi.interfaceType = 5;
    }
	public static void addTransparentSprite(int id, int spriteId, String spriteName) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(spriteId, spriteName);
		tab.enabledSprite = imageLoader(spriteId, spriteName); 
		tab.width = 512;
		tab.height = 334;
		tab.drawsTransparent = true;
	}
	public static void addPrayerWithTooltip(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID, int Hover, String tooltip) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 5608;
		Interface.interfaceType = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.opacity = 0;
		Interface.hoverType = Hover;
		Interface.disabledSprite = imageLoader(0, "Interfaces/PrayerTab/PRAYERGLOW");
		Interface.enabledSprite = imageLoader(1, "Interfaces/PrayerTab/PRAYERGLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 1;
		Interface.requiredValues[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = tooltip;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 5608;
		Interface.interfaceType = 5;
		Interface.atActionType = 0;
		Interface.contentType  = 0;
		Interface.opacity = 0;
		Interface.disabledSprite = imageLoader(prayerSpriteID, "Interfaces/PrayerTab/PRAYERON");
		Interface.enabledSprite = imageLoader(prayerSpriteID, "Interfaces/PrayerTab/PRAYEROFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 2;
		Interface.requiredValues[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}
	public static void addPrayer(int i, int configId, int configFrame, int requiredValues, int prayerSpriteID, String PrayerName, int Hover) {
		RSInterface Interface = addTabInterface(i);
		Interface.id = i;
		Interface.parentID = 22500;
		Interface.interfaceType = 5;
		Interface.atActionType = 4;
		Interface.contentType = 0;
		Interface.opacity = 0;
		Interface.hoverType = Hover;
		Interface.disabledSprite = imageLoader(0, "Interfaces/CurseTab/GLOW");
		Interface.enabledSprite = imageLoader(1, "Interfaces/CurseTab/GLOW");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 1;
		Interface.requiredValues[0] = configId;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 5;
		Interface.valueIndexArray[0][1] = configFrame;
		Interface.valueIndexArray[0][2] = 0;
		Interface.tooltip = "Activate@lre@ " + PrayerName;
		Interface = addTabInterface(i + 1);
		Interface.id = i + 1;
		Interface.parentID = 22500;
		Interface.interfaceType = 5;
		Interface.atActionType = 0;
		Interface.contentType  = 0;
		Interface.opacity = 0;
		Interface.disabledSprite = imageLoader(prayerSpriteID, "Interfaces/CurseTab/PRAYON");
		Interface.enabledSprite = imageLoader(prayerSpriteID, "Interfaces/CurseTab/PRAYOFF");
		Interface.width = 34;
		Interface.height = 34;
		Interface.valueCompareType = new int[1];
		Interface.requiredValues = new int[1];
		Interface.valueCompareType[0] = 2;
		Interface.requiredValues[0] = requiredValues + 1;
		Interface.valueIndexArray = new int[1][3];
		Interface.valueIndexArray[0][0] = 2;
		Interface.valueIndexArray[0][1] = 5;
		Interface.valueIndexArray[0][2] = 0;
	}
	/*public static void skillInterface(int i, int j){
		RSInterface rsinterface = interfaceCache[i] = new RSInterface();
		rsinterface.id = i;
		rsinterface.parentID = i;
		rsinterface.interfaceType = 5;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 26;
		rsinterface.height = 34;
		rsinterface.opacity = 0;
		rsinterface.hoverType = 0;
		rsinterface.disabledSprite = imageLoader(j, "Interfaces/Skill602");
		rsinterface.enabledSprite = imageLoader(j, "Interfaces/Skill602");
	}*/
	public static void addHoverImage(int i, int j, int k, String name) {
		RSInterface tab = addTabInterface(i);
		tab.id = i;
		tab.parentID = i;
		tab.interfaceType = 5;
		tab.atActionType = 0;
		tab.contentType = 0;
		tab.width = 512;
		tab.height = 334;
		tab.opacity = 0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(j, name);
		tab.enabledSprite = imageLoader(k, name);
	}
	public static void addChar(int ID) { 
		RSInterface t = interfaceCache[ID] = new RSInterface(); 
		t.id = ID; 
		t.parentID = ID; 
		t.interfaceType = 6;
		t.atActionType = 0; 
		t.contentType = 328; 
		t.width = 136; 
		t.height = 168; 
		t.opacity = 0;
		t.hoverType = 0;
		t.modelZoom = 560;
		t.modelRotationY = 150;
		t.modelRotationX = 0; 
		t.disabledAnimation = -1; 
		t.enabledAnimation = -1; 
	}
	public static void addLunarSprite(int i, int j, String name) {
		RSInterface RSInterface = addTabInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.interfaceType = 5;
		RSInterface.atActionType = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 52;
		RSInterface.disabledSprite = imageLoader(j, name);
		RSInterface.width = 500;
		RSInterface.height = 500;
		RSInterface.tooltip = "";
	}
	public static void drawRune(int i, int id, String runeName) {
		RSInterface RSInterface = addTabInterface(i);
		RSInterface.interfaceType = 5;
		RSInterface.atActionType = 0;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 52;
		RSInterface.disabledSprite = imageLoader(id, "Interfaces/Lunar/Rune");
		RSInterface.width = 500;
		RSInterface.height = 500;
	}
	public static void addRuneText(int ID, int runeAmount, int RuneID,
			RSFont[] tda) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.interfaceType = 4;
		rsInterface.atActionType = 0;
		rsInterface.contentType = 0;
		rsInterface.width = 0;
		rsInterface.height = 14;
		rsInterface.opacity = 0;
		rsInterface.hoverType = -1;
		rsInterface.valueCompareType = new int[1];
		rsInterface.requiredValues = new int[1];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = runeAmount;
		rsInterface.valueIndexArray = new int[1][4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = RuneID;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.textCentered = true;
		rsInterface.rsFonts = tda[0];
		rsInterface.textShadowed = true;
		rsInterface.disabledMessage = "%1/" + runeAmount + "";
		rsInterface.enabledMessage = "";
		rsInterface.disabledColor = 12582912;
		rsInterface.enabledColor = 49152;
	}
	
	public static void addLunar2RunesSmallBox(int ID, int r1, int r2, int ra1,
			int ra2, int rune1, int lvl, String name, String descr,
			RSFont[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.interfaceType = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast On";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[3];
		rsInterface.requiredValues = new int[3];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = lvl;
		rsInterface.valueIndexArray = new int[3][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[3];
		rsInterface.valueIndexArray[2][0] = 1;
		rsInterface.valueIndexArray[2][1] = 6;
		rsInterface.valueIndexArray[2][2] = 0;
		rsInterface.enabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.disabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(7, hover);
		addLunarSprite(ID + 2, 0, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true,
				true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(30016, 37, 35, 3, hover);// Rune
		setBounds(rune1, 112, 35, 4, hover);// Rune
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 50, 66, 5, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 123, 66, 6, hover);

	}
	public static void addLunar3RunesSmallBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, RSFont[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.interfaceType = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.enabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.disabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 0, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true,
				true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 19, 2, hover);
		setBounds(30016, 14, 35, 3, hover);
		setBounds(rune1, 74, 35, 4, hover);
		setBounds(rune2, 130, 35, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 66, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 66, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 66, 8, hover);
	}
	public static void addLunar3RunesBigBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, RSFont[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.interfaceType = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.enabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.disabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 1, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true,
				true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 21, 2, hover);
		setBounds(30016, 14, 48, 3, hover);
		setBounds(rune1, 74, 48, 4, hover);
		setBounds(rune2, 130, 48, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 79, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 79, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 79, 8, hover);
	}
	public static void addLunar3RunesLargeBox(int ID, int r1, int r2, int r3,
			int ra1, int ra2, int ra3, int rune1, int rune2, int lvl,
			String name, String descr, RSFont[] TDA, int sid, int suo, int type) {
		RSInterface rsInterface = addTabInterface(ID);
		rsInterface.id = ID;
		rsInterface.parentID = 1151;
		rsInterface.interfaceType = 5;
		rsInterface.atActionType = type;
		rsInterface.contentType = 0;
		rsInterface.hoverType = ID + 1;
		rsInterface.spellUsableOn = suo;
		rsInterface.selectedActionName = "Cast on";
		rsInterface.width = 20;
		rsInterface.height = 20;
		rsInterface.tooltip = "Cast @gre@" + name;
		rsInterface.spellName = name;
		rsInterface.valueCompareType = new int[4];
		rsInterface.requiredValues = new int[4];
		rsInterface.valueCompareType[0] = 3;
		rsInterface.requiredValues[0] = ra1;
		rsInterface.valueCompareType[1] = 3;
		rsInterface.requiredValues[1] = ra2;
		rsInterface.valueCompareType[2] = 3;
		rsInterface.requiredValues[2] = ra3;
		rsInterface.valueCompareType[3] = 3;
		rsInterface.requiredValues[3] = lvl;
		rsInterface.valueIndexArray = new int[4][];
		rsInterface.valueIndexArray[0] = new int[4];
		rsInterface.valueIndexArray[0][0] = 4;
		rsInterface.valueIndexArray[0][1] = 3214;
		rsInterface.valueIndexArray[0][2] = r1;
		rsInterface.valueIndexArray[0][3] = 0;
		rsInterface.valueIndexArray[1] = new int[4];
		rsInterface.valueIndexArray[1][0] = 4;
		rsInterface.valueIndexArray[1][1] = 3214;
		rsInterface.valueIndexArray[1][2] = r2;
		rsInterface.valueIndexArray[1][3] = 0;
		rsInterface.valueIndexArray[2] = new int[4];
		rsInterface.valueIndexArray[2][0] = 4;
		rsInterface.valueIndexArray[2][1] = 3214;
		rsInterface.valueIndexArray[2][2] = r3;
		rsInterface.valueIndexArray[2][3] = 0;
		rsInterface.valueIndexArray[3] = new int[3];
		rsInterface.valueIndexArray[3][0] = 1;
		rsInterface.valueIndexArray[3][1] = 6;
		rsInterface.valueIndexArray[3][2] = 0;
		rsInterface.enabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNARON");
		rsInterface.disabledSprite = imageLoader(sid, "Interfaces/Lunar/LUNAROFF");
		RSInterface hover = addTabInterface(ID + 1);
		hover.interfaceShown = true;
		hover.hoverType = -1;
		setChildren(9, hover);
		addLunarSprite(ID + 2, 2, "Interfaces/Lunar/BOX");
		setBounds(ID + 2, 0, 0, 0, hover);
		addText(ID + 3, "Level " + (lvl + 1) + ": " + name, 0xFF981F, true,
				true, 52, 1);
		setBounds(ID + 3, 90, 4, 1, hover);
		addText(ID + 4, descr, 0xAF6A1A, true, true, 52, 0);
		setBounds(ID + 4, 90, 34, 2, hover);
		setBounds(30016, 14, 61, 3, hover);
		setBounds(rune1, 74, 61, 4, hover);
		setBounds(rune2, 130, 61, 5, hover);
		addRuneText(ID + 5, ra1 + 1, r1, TDA);
		setBounds(ID + 5, 26, 92, 6, hover);
		addRuneText(ID + 6, ra2 + 1, r2, TDA);
		setBounds(ID + 6, 87, 92, 7, hover);
		addRuneText(ID + 7, ra3 + 1, r3, TDA);
		setBounds(ID + 7, 142, 92, 8, hover);
	}
	public static void homeTeleport() {
		RSInterface RSInterface = addTabInterface(30000);
		RSInterface.tooltip = "Cast @gre@Lunar Home Teleport";
		RSInterface.id = 30000;
		RSInterface.parentID = 30000;
		RSInterface.interfaceType = 5;
		RSInterface.atActionType = 5;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 30001;
		RSInterface.disabledSprite = imageLoader(1, "Interfaces/Lunar/SPRITE");
		RSInterface.width = 20;
		RSInterface.height = 20;
		RSInterface hover = addTabInterface(30001);
		hover.hoverType = -1;
		hover.interfaceShown = true;
		setChildren(1, hover);
		addLunarSprite(30002, 0, "Interfaces/Lunar/SPRITE");
		setBounds(30002, 0, 0, 0, hover);
	}
	
	public static void GodWars(RSFont[] TDA) {
		RSInterface rsinterface = addTab(16210);
		addText(16211, "Dungeoneering Killcount", TDA, 0, 0xff9040);
		addText(16215, "NPC Kills", TDA, 0, 0xff9040);
		addText(16219, "0", TDA, 0, 0x66FFFF);//zamorak
		rsinterface.scrollMax = 0;
		rsinterface.children = new int[9];
		rsinterface.childX = new int[9];
		rsinterface.childY = new int[9];
		rsinterface.children[0] = 16211;
			rsinterface.childX[0] = -52+375+30;		rsinterface.childY[0] = 7;
		rsinterface.children[1] = 16212;
			rsinterface.childX[1] = -52+375+30;		rsinterface.childY[1] = 30;
		rsinterface.children[2] = 16213;
			rsinterface.childX[2] = -52+375+30;		rsinterface.childY[2] = 44;
		rsinterface.children[3] = 16214;
		rsinterface.childX[3] = -52+375+30;		rsinterface.childY[3] = 58;
			rsinterface.children[4] = 16215;
			rsinterface.childX[4] = -52+375+30;		rsinterface.childY[4] = 73;
		
		rsinterface.children[5] = 16216;
			rsinterface.childX[5] = -52+460+60;		rsinterface.childY[5] = 31;
		rsinterface.children[6] = 16217;
			rsinterface.childX[6] = -52+460+60;		rsinterface.childY[6] = 45;
		rsinterface.children[7] = 16218;
			rsinterface.childX[7] = -52+460+60;		rsinterface.childY[7] = 59;
		rsinterface.children[8] = 16219;
			rsinterface.childX[8] = -52+460+60;		rsinterface.childY[8] = 74;
	}
	public static void magicTab(RSFont[] tda) {
		RSInterface tab = addTabInterface(1151);
		RSInterface homeHover = addTabInterface(1196);
		RSInterface spellButtons = interfaceCache[12424];
		spellButtons.scrollMax = 0; spellButtons.height = 260; spellButtons.width = 190;
		int[] spellButton = {
			1196, 1199, 1206, 1215, 1224, 1231, 1240, 1249, 1258, 1267, 1274, 1283, 1573,
			1290, 1299, 1308, 1315, 1324, 1333, 1340, 1349, 1358, 1367, 1374, 1381, 1388,
			1397, 1404, 1583, 12038, 1414, 1421, 1430, 1437, 1446, 1453, 1460, 1469, 15878,
			1602, 1613, 1624, 7456, 1478, 1485, 1494, 1503, 1512, 1521, 1530, 1544, 1553,
			1563, 1593, 1635, 12426, 12436, 12446, 12456, 6004, 18471
		};
		tab.totalChildren(63);
		tab.child(0, 12424, 13, 24);
		for(int i1 = 0; i1 < spellButton.length; i1++) {
			int yPos = i1 > 34 ? 8 : 183;
			tab.child(1, 1195, 13, 24);
			tab.child(i1 + 2, spellButton[i1], 5, yPos);
			AddInterfaceButton(1195, 1, "Interfaces/Magic/Home", "Cast @gre@Home Teleport");
			RSInterface homeButton = interfaceCache[1195];
			homeButton.hoverType = 1196;
		}
		for(int i2 = 0; i2 < spellButton.length; i2++) {
			if(i2 < 60)
				spellButtons.childX[i2] = spellButtons.childX[i2] + 24;
			if(i2 == 6 || i2 == 12 || i2 == 19 || i2 == 35 || i2 == 41 || i2 == 44 || i2 == 49 || i2 == 51)
				spellButtons.childX[i2] = 0;
			spellButtons.childY[6] = 24; spellButtons.childY[12] = 48;
			spellButtons.childY[19] = 72; spellButtons.childY[49] = 96;
			spellButtons.childY[44] = 120; spellButtons.childY[51] = 144;
			spellButtons.childY[35] = 170; spellButtons.childY[41] = 192;
		}
		homeHover.interfaceShown = true;
		addText(1197, "Level 0: Home Teleport", tda, 1, 0xFE981F, true, true);
		RSInterface homeLevel = interfaceCache[1197]; homeLevel.width = 174; homeLevel.height = 68;
		addText(1198, "Requires no runes - recharge time", 0xAF6A1A, true, true, 52, 0);
		addText(18998, "30mins. Warning: This spell takes a", 0xAF6A1A, true, true, 52, 0);
		addText(18999, "long time to cast and will be", 0xAF6A1A, true, true, 52, 0);
		addText(19000, "interupted by combat.", 0xAF6A1A, true, true, 52, 0);
		homeHover.totalChildren(5);
		homeHover.child(0, 1197, 3, 4);
		homeHover.child(1, 1198, 91, 23);
		homeHover.child(2, 18998, 90, 34);
		homeHover.child(3, 18999, 91, 45);
		homeHover.child(4, 19000, 91, 56);
	}
	public static void ancientMagicTab(RSFont[] tda) {
		RSInterface tab = addInterface(12855);
		AddInterfaceButton(12856, 1, "Interfaces/Magic/Home", "Cast @gre@Home Teleport");
		RSInterface homeButton = interfaceCache[12856]; 
		homeButton.hoverType = 1196;
		int[] itfChildren = {
			12856, 12939, 12987, 13035, 12901, 12861, 13045, 12963, 13011, 13053, 12919, 12881, 13061,
			12951, 12999, 13069, 12911, 12871, 13079, 12975, 13023, 13087, 12929, 12891, 13095, 1196,
			12940, 12988, 13036, 12902, 12862, 13046, 12964, 13012, 13054, 12920, 12882, 13062, 12952,
			13000, 13070, 12912, 12872, 13080, 12976, 13024, 13088, 12930, 12892, 13096
		};
		tab.totalChildren(itfChildren.length);
		for(int i1 = 0, xPos = 18, yPos = 8; i1 < itfChildren.length; i1++, xPos += 45) {
			if(xPos > 175) {
				xPos = 18; yPos += 28;
			}
			if(i1 < 25)
				tab.child(i1, itfChildren[i1], xPos, yPos);
			if(i1 > 24) {
				yPos = i1 < 41 ? 181 : 1;
				tab.child(i1, itfChildren[i1], 4, yPos);
			}
		}
	}
   public static void prayerMenu(RSFont arsfont[])
    {
        RSInterface rsinterface = addInterface(5608);
        int i = 0;
        int j = 0;
        byte byte0 = 10;
        byte byte1 = 50;
        byte byte2 = 10;
        byte byte3 = 86;
        byte byte4 = 10;
        byte byte5 = 122;
        byte byte6 = 10;
        char c = '\237';
        byte byte7 = 10;
        byte byte8 = 86;
        int k = 1;
        byte byte9 = 52;
        addText(687, "", 0xff981f, false, true, -1, arsfont, 1);
        addSprite(25105, 0, "Interfaces/PrayerTab/PRAYERICON");
        addPrayerWithTooltip(25000, 0, 83, 0, j, 25052, "Activate @lre@Thick Skin");
        j++;
        addPrayerWithTooltip(25002, 0, 84, 3, j, 25054, "Activate @lre@Burst of Strength");
        j++;
        addPrayerWithTooltip(25004, 0, 85, 6, j, 25056, "Activate @lre@Clarity of Thought");
        j++;
        addPrayerWithTooltip(25006, 0, 601, 7, j, 25058, "Activate @lre@Sharp Eye");
        j++;
        addPrayerWithTooltip(25008, 0, 602, 8, j, 25060, "Activate @lre@Mystic Will");
        j++;
        addPrayerWithTooltip(25010, 0, 86, 9, j, 25062, "Activate @lre@Rock Skin");
        j++;
        addPrayerWithTooltip(25012, 0, 87, 12, j, 25064, "Activate @lre@Superhuman Strength");
        j++;
        addPrayerWithTooltip(25014, 0, 88, 15, j, 25066, "Activate @lre@Improved Reflexes");
        j++;
        addPrayerWithTooltip(25016, 0, 89, 18, j, 25068, "Activate @lre@Rapid Restore");
        j++;
        addPrayerWithTooltip(25018, 0, 90, 21, j, 25070, "Activate @lre@Rapid Heal");
        j++;
        addPrayerWithTooltip(25020, 0, 91, 24, j, 25072, "Activate @lre@Protect Item");
        j++;
        addPrayerWithTooltip(25022, 0, 603, 25, j, 25074, "Activate @lre@Hawk Eye");
        j++;
        addPrayerWithTooltip(25024, 0, 604, 26, j, 25076, "Activate @lre@Mystic Lore");
        j++;
        addPrayerWithTooltip(25026, 0, 92, 27, j, 25078, "Activate @lre@Steel Skin");
        j++;
        addPrayerWithTooltip(25028, 0, 93, 30, j, 25080, "Activate @lre@Ultimate Strength");
        j++;
        addPrayerWithTooltip(25030, 0, 94, 33, j, 25082, "Activate @lre@Incredible Reflexes");
        j++;
        addPrayerWithTooltip(25032, 0, 95, 36, j, 25084, "Activate @lre@Protect from Magic");
        j++;
        addPrayerWithTooltip(25034, 0, 96, 39, j, 25086, "Activate @lre@Protect from Missles");
        j++;
        addPrayerWithTooltip(25036, 0, 97, 42, j, 25088, "Activate @lre@Protect from Melee");
        j++;
        addPrayerWithTooltip(25038, 0, 605, 43, j, 25090, "Activate @lre@Eagle Eye");
        j++;
        addPrayerWithTooltip(25040, 0, 606, 44, j, 25092, "Activate @lre@Mystic Might");
        j++;
        addPrayerWithTooltip(25042, 0, 98, 45, j, 25094, "Activate @lre@Retribution");
        j++;
        addPrayerWithTooltip(25044, 0, 99, 48, j, 25096, "Activate @lre@Redemption");
        j++;
        addPrayerWithTooltip(25046, 0, 100, 51, j, 25098, "Activate @lre@Smite");
        j++;
        addPrayerWithTooltip(25048, 0, 607, 59, j, 25100, "Activate @lre@Chivalry");
        j++;
        addPrayerWithTooltip(25050, 0, 608, 69, j, 25102, "Activate @lre@Piety");
        j++;
        drawTooltip(25052, "Level 01\nThick Skin\nIncreases your Defence by 5%");
        drawTooltip(25054, "Level 04\nBurst of Strength\nIncreases your Strength by 5%");
        drawTooltip(25056, "Level 07\nClarity of Thought\nIncreases your Attack by 5%");
        drawTooltip(25058, "Level 08\nSharp Eye\nIncreases your Ranged by 5%");
        drawTooltip(25060, "Level 09\nMystic Will\nIncreases your Magic by 5%");
        drawTooltip(25062, "Level 10\nRock Skin\nIncreases your Defence by 10%");
        drawTooltip(25064, "Level 13\nSuperhuman Strength\nIncreases your Strength by 10%");
        drawTooltip(25066, "Level 16\nImproved Reflexes\nIncreases your Attack by 10%");
        drawTooltip(25068, "Level 19\nRapid Restore\n2x restore rate for all stats\nexcept Hitpoints, Summon" +
"ing\nand Prayer"
);
        drawTooltip(25070, "Level 22\nRapid Heal\n2x restore rate for the\nHitpoints stat");
        drawTooltip(25072, "Level 25\nProtect Item\nKeep 1 extra item if you die");
        drawTooltip(25074, "Level 26\nHawk Eye\nIncreases your Ranged by 10%");
        drawTooltip(25076, "Level 27\nMystic Lore\nIncreases your Magic by 10%");
        drawTooltip(25078, "Level 28\nSteel Skin\nIncreases your Defence by 15%");
        drawTooltip(25080, "Level 31\nUltimate Strength\nIncreases your Strength by 15%");
        drawTooltip(25082, "Level 34\nIncredible Reflexes\nIncreases your Attack by 15%");
        drawTooltip(25084, "Level 37\nProtect from Magic\nProtection from magical attacks");
        drawTooltip(25086, "Level 40\nProtect from Missles\nProtection from ranged attacks");
        drawTooltip(25088, "Level 43\nProtect from Melee\nProtection from melee attacks");
        drawTooltip(25090, "Level 44\nEagle Eye\nIncreases your Ranged by 15%");
        drawTooltip(25092, "Level 45\nMystic Might\nIncreases your Magic by 15%");
        drawTooltip(25094, "Level 46\nRetribution\nInflicts damage to nearby\ntargets if you die");
        drawTooltip(25096, "Level 49\nRedemption\nHeals you when damaged\nand Hitpoints falls\nbelow 10%");
        drawTooltip(25098, "Level 52\nSmite\n1/4 of damage dealt is\nalso removed from\nopponent's Prayer");
        drawTooltip(25100, "Level 60\nChivalry\nIncreases your Defence by 20%,\nStrength by 18%, and Attack " +
"by\n15%"
);
        drawTooltip(25102, "Level 70\nPiety\nIncreases your Defence by 25%,\nStrength by 23%, and Attack by\n" +
"20%"
);
        setChildren(80, rsinterface);
        setBounds(687, 85, 241, i, rsinterface);
        i++;
        setBounds(25105, 65, 241, i, rsinterface);
        i++;
        setBounds(25000, 2, 5, i, rsinterface);
        i++;
        setBounds(25001, 5, 8, i, rsinterface);
        i++;
        setBounds(25002, 40, 5, i, rsinterface);
        i++;
        setBounds(25003, 44, 8, i, rsinterface);
        i++;
        setBounds(25004, 76, 5, i, rsinterface);
        i++;
        setBounds(25005, 79, 11, i, rsinterface);
        i++;
        setBounds(25006, 113, 5, i, rsinterface);
        i++;
        setBounds(25007, 116, 10, i, rsinterface);
        i++;
        setBounds(25008, 150, 5, i, rsinterface);
        i++;
        setBounds(25009, 153, 9, i, rsinterface);
        i++;
        setBounds(25010, 2, 45, i, rsinterface);
        i++;
        setBounds(25011, 5, 48, i, rsinterface);
        i++;
        setBounds(25012, 39, 45, i, rsinterface);
        i++;
        setBounds(25013, 44, 47, i, rsinterface);
        i++;
        setBounds(25014, 76, 45, i, rsinterface);
        i++;
        setBounds(25015, 79, 49, i, rsinterface);
        i++;
        setBounds(25016, 113, 45, i, rsinterface);
        i++;
        setBounds(25017, 116, 50, i, rsinterface);
        i++;
        setBounds(25018, 151, 45, i, rsinterface);
        i++;
        setBounds(25019, 154, 50, i, rsinterface);
        i++;
        setBounds(25020, 2, 82, i, rsinterface);
        i++;
        setBounds(25021, 4, 84, i, rsinterface);
        i++;
        setBounds(25022, 40, 82, i, rsinterface);
        i++;
        setBounds(25023, 44, 87, i, rsinterface);
        i++;
        setBounds(25024, 77, 82, i, rsinterface);
        i++;
        setBounds(25025, 81, 85, i, rsinterface);
        i++;
        setBounds(25026, 114, 83, i, rsinterface);
        i++;
        setBounds(25027, 117, 85, i, rsinterface);
        i++;
        setBounds(25028, 153, 83, i, rsinterface);
        i++;
        setBounds(25029, 156, 87, i, rsinterface);
        i++;
        setBounds(25030, 2, 120, i, rsinterface);
        i++;
        setBounds(25031, 5, 125, i, rsinterface);
        i++;
        setBounds(25032, 40, 120, i, rsinterface);
        i++;
        setBounds(25033, 43, 124, i, rsinterface);
        i++;
        setBounds(25034, 78, 120, i, rsinterface);
        i++;
        setBounds(25035, 83, 124, i, rsinterface);
        i++;
        setBounds(25036, 114, 120, i, rsinterface);
        i++;
        setBounds(25037, 115, 121, i, rsinterface);
        i++;
        setBounds(25038, 151, 120, i, rsinterface);
        i++;
        setBounds(25039, 154, 124, i, rsinterface);
        i++;
        setBounds(25040, 2, 158, i, rsinterface);
        i++;
        setBounds(25041, 5, 160, i, rsinterface);
        i++;
        setBounds(25042, 39, 158, i, rsinterface);
        i++;
        setBounds(25043, 41, 158, i, rsinterface);
        i++;
        setBounds(25044, 76, 158, i, rsinterface);
        i++;
        setBounds(25045, 79, 163, i, rsinterface);
        i++;
        setBounds(25046, 114, 158, i, rsinterface);
        i++;
        setBounds(25047, 116, 158, i, rsinterface);
        i++;
        setBounds(25048, 153, 158, i, rsinterface);
        i++;
        setBounds(25049, 161, 160, i, rsinterface);
        i++;
        setBounds(25050, 2, 196, i, rsinterface);
        i++;
        setBounds(25051, 4, 207, i, rsinterface);
        setBoundry(++i, 25052, byte0 - 2, byte1, rsinterface);
        setBoundry(++i, 25054, byte0 - 5, byte1, rsinterface);
        setBoundry(++i, 25056, byte0, byte1, rsinterface);
        setBoundry(++i, 25058, byte0, byte1, rsinterface);
        setBoundry(++i, 25060, byte0, byte1, rsinterface);
        setBoundry(++i, 25062, byte2 - 9, byte3, rsinterface);
        setBoundry(++i, 25064, byte2 - 11, byte3, rsinterface);
        setBoundry(++i, 25066, byte2, byte3, rsinterface);
        setBoundry(++i, 25068, byte2, byte3, rsinterface);
        setBoundry(++i, 25070, byte2 + 25, byte3, rsinterface);
        setBoundry(++i, 25072, byte4, byte5, rsinterface);
        setBoundry(++i, 25074, byte4 - 2, byte5, rsinterface);
        setBoundry(++i, 25076, byte4, byte5, rsinterface);
        setBoundry(++i, 25078, byte4 - 7, byte5, rsinterface);
        setBoundry(++i, 25080, byte4 - 10, byte5, rsinterface);
        setBoundry(++i, 25082, byte6, c, rsinterface);
        setBoundry(++i, 25084, byte6 - 8, c, rsinterface);
        setBoundry(++i, 25086, byte6 - 7, c, rsinterface);
        setBoundry(++i, 25088, byte6 - 2, c, rsinterface);
        setBoundry(++i, 25090, byte6 - 2, c, rsinterface);
        setBoundry(++i, 25092, byte7, byte8, rsinterface);
        setBoundry(++i, 25094, byte7, byte8 - 20, rsinterface);
        setBoundry(++i, 25096, byte7, byte8 - 25, rsinterface);
        setBoundry(++i, 25098, byte7 + 15, byte8 - 25, rsinterface);
        setBoundry(++i, 25100, byte7 - 12, byte8 - 20, rsinterface);
        setBoundry(++i, 25102, k - 2, byte9, rsinterface);
        i++;
    }
	
	public static void extraOptions(RSFont[] TDA) {
		RSInterface Interface = addTabInterface(24500);
		int index = 0;
		addSprite(27520, 0, "Interfaces/Options/LINE");
		addText(27501, "Toggle Ingame", 0xFF981F, false, true, -1, TDA, 3);
		addText(27527, "Settings:", 0xFF981F, false, true, -1, TDA, 3);
		addText(27502, "Hitpoint Bar:", 0xFF981F, true, true, -1, TDA, 1);
		addText(27503, "Game Menu:", 0xFF981F, true, true, -1, TDA, 1);
		addText(27504, "Names:", 0xFF981F, true, true, -1, TDA, 1);
		addText(27535, "Gameframe:", 0xFF981F, true, true, -1, TDA, 1);
		addText(27600, "Hitmarks:", 0xFF981F, true, true, -1, TDA, 1);
		addText(27601, "Damage x10:", 0xFF981F, true, true, -1, TDA, 1);
		addText(27505, "Back", 0xFF981F, true, true, -1, TDA, 1);
		addText(27524, "", 0x66ff00, true, true, -1, TDA, 1);
		addText(27525, "", 0x66ff00, true, true, -1, TDA, 1);
		addText(27526, "", 0x66ff00, true, true, -1, TDA, 1);
		addText(27528, "@gre@On", 0xff0000, true, true, -1, TDA, 1);
		addText(27529, "@gre@On", 0xff0000, true, true, -1, TDA, 1);
		addText(27530, "Off", 0xff0000, true, true, -1, TDA, 1);
		addText(27531, "562", 0xffffff, true, true, -1, TDA, 1);
		addText(27536, "", 0xffffff, true, true, -1, TDA, 1);
		addText(27537, "", 0xffffff, true, true, -1, TDA, 1);
		addText(27538, "", 0xffffff, true, true, -1, TDA, 1);
		addText(27602, "", 0x66ff00, true, true, -1, TDA, 1);
		addText(27603, "@gre@On", 0xff0000, true, true, -1, TDA, 1);
		addText(27604, "", 0x66ff00, true, true, -1, TDA, 1);
		addText(27605, "Off", 0xff0000, true, true, -1, TDA, 1);
		AddInterfaceButton(27508, 0, 27511, "Interfaces/Options/BUTTON", 108, 20, "Toggle", 1);
		AddInterfaceButton(27509, 0, 27513, "Interfaces/Options/BUTTON", 108, 20, "Toggle", 1);
		AddInterfaceButton(27510, 0, 27515, "Interfaces/Options/BUTTON", 108, 20, "Toggle", 1);
		AddInterfaceButton(27521, 0, 27522, "Interfaces/Options/BUTTON", 108, 20, "Toggle", 1);
		AddInterfaceButton(27532, 0, 27533, "Interfaces/Options/BUTTON", 108, 20, "Toggle", 1);
		AddInterfaceButton(27606, 0, 27608, "Interfaces/Options/BUTTON", 108, 20, "Toggle", 1);
		AddInterfaceButton(27607, 3, 27610, "Interfaces/Options/BUTTON", 62, 20, "Toggle", 1);
		drawTooltip(27511, "Toggle ON/OFF the new\nhitpoint bar");
		drawTooltip(27513, "Toggle ON/OFF the new\ngame menu");
		drawTooltip(27515, "Toggle ON/OFF names\nabove head");
		drawTooltip(27522, "Toggle ON/OFF the\nnewhitmarks");
		drawTooltip(27533, "Toggle the current\ngameframe");
		drawTooltip(27608, "Toggle ON/OFF the\nx10 multiplier");
		drawTooltip(27610, "Return back to the\nregular option tab");
		setChildren(28+10, Interface);
		setBounds(27520, 0, 50, index, Interface);index++;
		setBounds(27501, 45, 5, index, Interface);index++;
		setBounds(27527, 70, 25, index, Interface);index++;
		setBounds(27508, 45, 60, index, Interface);index++;
		setBounds(27509, 45, 90, index, Interface);index++;
		setBounds(27510, 45, 120, index, Interface);index++;
		setBounds(27532, 45, 150, index, Interface);index++;
		setBounds(27521, 45, 180, index, Interface);index++;
		setBounds(27606, 45, 210, index, Interface);index++;
		setBounds(27607, 70, 240, index, Interface);index++;
		setBounds(27502, 84, 63, index, Interface);index++;
		setBounds(27503, 83, 93, index, Interface);index++;
		setBounds(27504, 70, 123, index, Interface);index++;
		setBounds(27535, 83, 153, index, Interface);index++;
		setBounds(27600, 75, 183, index, Interface);index++;
		setBounds(27601, 84, 213, index, Interface);index++;
		setBounds(27505, 101, 243, index, Interface);index++;
		setBounds(27524, 135, 62, index, Interface);index++;
		setBounds(27525, 135, 92, index, Interface);index++;
		setBounds(27526, 135, 123, index, Interface);index++;
		setBounds(27528, 135, 62, index, Interface);index++;
		setBounds(27529, 135, 92, index, Interface);index++;
		setBounds(27530, 135, 123, index, Interface);index++;
		setBounds(27531, 135, 153, index, Interface);index++;
		setBounds(27536, 135, 153, index, Interface);index++;
		setBounds(27537, 135, 153, index, Interface);index++;
		setBounds(27538, 135, 153, index, Interface);index++;
		setBounds(27602, 135, 183, index, Interface);index++;
		setBounds(27603, 135, 183, index, Interface);index++;
		setBounds(27604, 135, 213, index, Interface);index++;
		setBounds(27605, 135, 213, index, Interface);index++;
		setBounds(27511, 40, 20, index, Interface);index++;
		setBounds(27513, 35, 50, index, Interface);index++;
		setBounds(27515, 35, 80, index, Interface);index++;
		setBounds(27522, 43, 140, index, Interface);index++;
		setBounds(27533, 43, 95, index, Interface);index++;
		setBounds(27608, 43, 170, index, Interface);index++;
		setBounds(27610, 43, 200, index, Interface);index++;
	}
	
public static void Curses(RSFont[] TDA) {
		RSInterface Interface = addTabInterface(22500);
		int index = 0;
		addText(687, "99/99", 0xFF981F, false, false, -1, TDA, 1);
		addSprite(22502, 0, "Interfaces/CurseTab/ICON");
		addPrayer(22503, 0, 610, 49, 7, "Protect Item", 22582);//1
		addPrayer(22505, 0, 611, 49, 4, "Sap Warrior", 22544);//2
		addPrayer(22507, 0, 612, 51, 5, "Sap Ranger", 22546);//3
		addPrayer(22509, 0, 613, 53, 3, "Sap Mage", 22548);//4
		addPrayer(22511, 0, 614, 55, 2, "Sap Spirit", 22550);//5
		addPrayer(22513, 0, 615, 58, 18, "Berserker", 22552);//6
		addPrayer(22515, 0, 616, 61, 15, "Deflect Summoning", 22554);///7
		addPrayer(22517, 0, 617, 64, 17, "Deflect Magic", 22556);///8
		addPrayer(22519, 0, 618, 67, 16, "Deflect Missiles", 22558);///9
		addPrayer(22521, 0, 619, 70, 6, "Deflect Melee", 22560);///10
		addPrayer(22523, 0, 620, 73, 9, "Leech Attack", 22562);//11
		addPrayer(22525, 0, 621, 75, 10, "Leech Ranged", 22564);//12
		addPrayer(22527, 0, 622, 77, 11, "Leech Magic", 22566);//13
		addPrayer(22529, 0, 623, 79, 12, "Leech Defence", 22568);//14
		addPrayer(22531, 0, 624, 81, 13, "Leech Strength", 22570);//15
		addPrayer(22533, 0, 625, 83, 14, "Leech Energy", 22572);//16
		addPrayer(22535, 0, 626, 85, 19, "Leech Special Attack", 22574);//17
		addPrayer(22537, 0, 627, 88, 1, "Wrath", 22576);///18
		addPrayer(22539, 0, 628, 91, 8, "Soul Split", 22578);///19
		addPrayer(22541, 0, 629, 94, 20, "Turmoil", 22580);//20
		drawTooltip(22582, "Level 50\nProtect Item\nKeep 1 extra item if you die");
		drawTooltip(22544, "Level 50\nSap Warrior\nDrains 10% of enemy Attack,\nStrength and Defence,\nincreasing to 20% over time");
		drawTooltip(22546, "Level 52\nSap Ranger\nDrains 10% of enemy Ranged\nand Defence, increasing to 20%\nover time");
		drawTooltip(22548, "Level 54\nSap Mage\nDrains 10% of enemy Magic\nand Defence, increasing to 20%\nover time");
		drawTooltip(22550, "Level 56\nSap Spirit\nDrains enenmy special attack\nenergy");
		drawTooltip(22552, "Level 59\nBerserker\nBoosted stats last 15% longer");
		drawTooltip(22554, "Level 62\nDeflect Summoning\nReduces damage dealt from\nSummoning scrolls, prevents the\nuse of a familiar's special\nattack, and can deflect some of\ndamage back to the attacker");
		drawTooltip(22556, "Level 65\nDeflect Magic\nProtects against magical attacks\nand can deflect some of the\ndamage back to the attacker");
		drawTooltip(22558, "Level 68\nDeflect Missiles\nProtects against ranged attacks\nand can deflect some of the\ndamage back to the attacker");
		drawTooltip(22560, "Level 71\nDeflect Melee\nProtects against melee attacks\nand can deflect some of the\ndamage back to the attacker");
		drawTooltip(22562, "Level 74\nLeech Attack\nBoosts Attack by 5%, increasing\nto 10% over time, while draining\nenemy Attack by 10%, increasing\nto 25% over time");
		drawTooltip(22564, "Level 76\nLeech Ranged\nBoosts Ranged by 5%, increasing\nto 10% over time, while draining\nenemy Ranged by 10%,\nincreasing to 25% over\ntime");
		drawTooltip(22566, "Level 78\nLeech Magic\nBoosts Magic by 5%, increasing\nto 10% over time, while draining\nenemy Magic by 10%, increasing\nto 25% over time");
		drawTooltip(22568, "Level 80\nLeech Defence\nBoosts Defence by 5%, increasing\nto 10% over time, while draining\n enemy Defence by10%,\nincreasing to 25% over\ntime");
		drawTooltip(22570, "Level 82\nLeech Strength\nBoosts Strength by 5%, increasing\nto 10% over time, while draining\nenemy Strength by 10%, increasing\n to 25% over time");
		drawTooltip(22572, "Level 84\nLeech Energy\nDrains enemy run energy, while\nincreasing your own");
		drawTooltip(22574, "Level 86\nLeech Special Attack\nDrains enemy special attack\nenergy, while increasing your\nown");
		drawTooltip(22576, "Level 89\nWrath\nInflicts damage to nearby\ntargets if you die");
		drawTooltip(22578, "Level 92\nSoul Split\n1/4 of damage dealt is also removed\nfrom opponent's Prayer and\nadded to your Hitpoints");
		drawTooltip(22580, "Level 95\nTurmoil\nIncreases Attack and Defence\nby 15%, plus 15% of enemy's\nlevel, and Strength by 23% plus\n10% of enemy's level");
		setChildren(62, Interface);

		setBounds(687, 85, 241, index, Interface);index++;
		setBounds(22502, 65, 241, index, Interface);index++;
		setBounds(22503, 2, 5, index, Interface);index++;
		setBounds(22504, 8, 8, index, Interface);index++;
		setBounds(22505, 40, 5, index, Interface);index++;
		setBounds(22506, 47, 12, index, Interface);index++;
		setBounds(22507, 76, 5, index, Interface);index++;
		setBounds(22508, 82, 11, index, Interface);index++;
		setBounds(22509, 113, 5, index, Interface);index++;
		setBounds(22510, 116, 8, index, Interface);index++;
		setBounds(22511, 150, 5, index, Interface);index++;
		setBounds(22512, 155, 10, index, Interface);index++;
		setBounds(22513, 2, 45, index, Interface);index++;
		setBounds(22514, 9, 48, index, Interface);index++;
		setBounds(22515, 39, 45, index, Interface);index++;
		setBounds(22516, 42, 47, index, Interface);index++;
		setBounds(22517, 76, 45, index, Interface);index++;
		setBounds(22518, 79, 48, index, Interface);index++;
		setBounds(22519, 113, 45, index, Interface);index++;
		setBounds(22520, 116, 48, index, Interface);index++;
		setBounds(22521, 151, 45, index, Interface);index++;
		setBounds(22522, 154, 48, index, Interface);index++;
		setBounds(22523, 2, 82, index, Interface);index++;
		setBounds(22524, 6, 86, index, Interface);index++;
		setBounds(22525, 40, 82, index, Interface);index++;
		setBounds(22526, 42, 86, index, Interface);index++;
		setBounds(22527, 77, 82, index, Interface);index++;
		setBounds(22528, 79, 86, index, Interface);index++;
		setBounds(22529, 114, 83, index, Interface);index++;
		setBounds(22530, 119, 87, index, Interface);index++;
		setBounds(22531, 153, 83, index, Interface);index++;
		setBounds(22532, 156, 86, index, Interface);index++;
		setBounds(22533, 2, 120, index, Interface);index++;
		setBounds(22534, 7, 125, index, Interface);index++;
		setBounds(22535, 40, 120, index, Interface);index++;
		setBounds(22536, 45, 124, index, Interface);index++;
		setBounds(22537, 78, 120, index, Interface);index++;
		setBounds(22538, 86, 124, index, Interface);index++;
		setBounds(22539, 114, 120, index, Interface);index++;
		setBounds(22540, 120, 125, index, Interface);index++;
		setBounds(22541, 151, 120, index, Interface);index++;
		setBounds(22542, 153, 127, index, Interface);index++;
		setBounds(22582, 10, 40, index, Interface);index++;
		setBounds(22544, 20, 40, index, Interface);index++;
		setBounds(22546, 20, 40, index, Interface);index++;
		setBounds(22548, 20, 40, index, Interface);index++;
		setBounds(22550, 20, 40, index, Interface);index++;
		setBounds(22552, 10, 80, index, Interface);index++;
		setBounds(22554, 10, 80, index, Interface);index++;
		setBounds(22556, 10, 80, index, Interface);index++;
		setBounds(22558, 10, 80, index, Interface);index++;
		setBounds(22560, 10, 80, index, Interface);index++;
		setBounds(22562, 10, 120, index, Interface);index++;
		setBounds(22564, 10, 120, index, Interface);index++;
		setBounds(22566, 10, 120, index, Interface);index++;
		setBounds(22568, 5, 120, index, Interface);index++;
		setBounds(22570, 5, 120, index, Interface);index++;
		setBounds(22572, 10, 160, index, Interface);index++;
		setBounds(22574, 10, 160, index, Interface);index++;
		setBounds(22576, 10, 160, index, Interface);index++;
		setBounds(22578, 10, 160, index, Interface);index++;
		setBounds(22580, 10, 160, index, Interface);index++;
	}
	
	
	public static void Logout(RSFont[] wid) {
		RSInterface rsinterface = interfaceCache[2449];
		rsinterface.childY[0] = 68;
		rsinterface.childY[1] = 86;
		rsinterface.childX[2] = 12;
		rsinterface.childY[2] = 104;
		rsinterface.childX[8] = 31;
		rsinterface.childY[8] = 164;
		rsinterface = interfaceCache[2450];
		rsinterface.disabledColor = 0xff981f;
		rsinterface = interfaceCache[2451];
		rsinterface.disabledColor = 0xff981f;
		rsinterface = interfaceCache[2452];
		rsinterface.disabledColor = 0xff981f;
	}
	
	public void specialBar(int id) {//7599
        /*addActionButton(ID, SpriteOFF, SpriteON, Width, Height, "SpriteText");*/
		addActionButton(id-12, 7587, -1, 150, 26, "Use @gre@Special Attack", 20003);
        /*removeSomething(ID);*/
        for (int i = id-11; i < id; i++)
			removeSomething(i);
        RSInterface rsi = interfaceCache[id-12];
        rsi.width = 150;
        rsi.height = 26;
        rsi = interfaceCache[id];
		rsi.width = 150;
		rsi.height = 26;
		rsi.child(0, id-12, 0, 0);
		rsi.child(12, id+1, 3, 7);
		rsi.child(23, id+12, 16, 8);
        for (int i = 13; i < 23; i++) {
            rsi.childY[i] -= 1;
        }
        rsi = interfaceCache[id+1];
		rsi.interfaceType = 5;
		rsi.disabledSprite = CustomSpriteLoader(7600, "");
        for (int i = id+2; i < id+12; i++) {
			rsi = interfaceCache[i];
            rsi.interfaceType = 5;
        }
        disabledSprite(id+2, 7601);disabledSprite(id+3, 7602);
        disabledSprite(id+4, 7603);disabledSprite(id+5, 7604);
        disabledSprite(id+6, 7605);disabledSprite(id+7, 7606);
        disabledSprite(id+8, 7607);disabledSprite(id+9, 7608);
        disabledSprite(id+10, 7609);disabledSprite(id+11, 7610);
    }

    public static void Sidebar0(RSFont[] tda)
    {
RSInterface rsi = addInterface(19300);
		addToggleButton(150, 150, 172, 150, 44, "Auto Retaliate", 20001);
		addTooltip(20001, "When active your player\n will automatically\n fight back if attacked.");
		addText(3983, "Combat level: ", tda, 0, 0xFF981F);
		rsi.totalChildren(3, 3, 3);
		rsi.child(0, 3983, 52, 25);
		rsi.child(1, 150, 21, 153);
		rsi.child(2, 20001, 23+4, 203);
		/*
		addFourSelect(id, id2, id3, "text1", "text2", "text3", "text4",
							str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y,
							img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y,
							tda);
		*/
		addFourSelect(1698, 1701, 7499, "Chop", "Hack", "Smash", "Block",
							42, 75, 127, 75, 39, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(2276, 2279, 7574, "Stab", "Lunge", "Slash", "Block",
							43, 75, 124, 75, 41, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(2423, 2426, 7599, "Chop", "Slash", "Lunge", "Block",
							42, 75, 125, 75, 40, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 20007, 20009, 20011, 20013);
		addFourSelect(3796, 3799, 7624, "Pound", "Pummel", "Spike", "Block",
							39, 75, 121, 75, 41, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(4679, 4682, 7674, "Lunge", "Swipe", "Pound", "Block",
							40, 75, 124, 75, 39, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(4705, 4708, 7699, "Chop", "Slash", "Smash", "Block", 
							42, 75, 125, 75, 39, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(5570, 5573, 7724, "Spike", "Impale", "Smash", "Block",
							41, 75, 123, 75, 39, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		addFourSelect(7762, 7765, 7800, "Chop", "Slash", "Lunge", "Block",
							42, 75, 125, 75, 40, 128, 125, 128,
							122, 103, 40, 50, 122, 50, 40, 103,
							tda, 4706, 4707, 4708, 4709);
		/*
		addFourSelectNoSpec(id, id2, "text1", "text2", "text3", "text4",
							str1x, str1y, str2x, str2y, str3x, str3y, str4x, str4y,
							img1x, img1y, img2x, img2y, img3x, img3y, img4x, img4y,
							tda);
		*/
		addFourSelectNoSpec(776, 779, "Reap", "Chop", "Jab", "Block", 42,
							75, 126, 75, 46, 128, 125, 128, 122,
							103, 122, 50, 40, 103, 40, 50,
							tda);
		/*
		addThreeSelect(id, id2, id3, "text1", "text2", "text3",
							str1x, str1y, str2x, str2y, str3x, str3y,
							img1x, img1y, img2x, img2y, img3x, img3y,
							tda);
		*/
		addThreeSelect(425, 428, 7474, "Pound", "Pummel", "Block",
							39, 75, 121, 75, 42, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(1749, 1752, 7524, "Accurate", "Rapid", "Longrange",
							33, 75, 125, 75, 29, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(1764, 1767, 7549, "Accurate", "Rapid", "Longrange",
							33, 75, 125, 75, 29, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(4446, 4449, 7649, "Accurate", "Rapid", "Longrange",
							33, 75, 125, 75, 29, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(5855, 5857, 7749, "Punch", "Kick", "Block",
							40, 75, 129, 75, 42, 128,
							40, 50, 122, 50, 40, 103,
							tda);
		addThreeSelect(6103, 6132, 6117, "Bash", "Pound", "Block",
							43, 75, 124, 75, 42, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(8460, 8463, 8493, "Jab", "Swipe", "Fend",
							46, 75, 124, 75, 43, 128,
							40, 103, 40, 50, 122, 50,
							tda);
		addThreeSelect(12290, 12293, 12323, "Flick", "Lash", "Deflect",
							44, 75, 127, 75, 36, 128,
							40, 50, 40, 103, 122, 50,
							tda);
		/*
		addThreeSelectNoSpec(id, id2, "text1", "text2", "text3",
								str1x, str1y, str2x, str2y, str3x, str3y,
								img1x, img1y, img2x, img2y, img3x, img3y,
								tda);
		*/
		addThreeSelectNoSpec(328, 331, "Bash", "Pound", "Focus",
								42, 66, 39, 101, 41, 136,
								40, 120, 40, 50, 40, 85,
								tda);
    }

    public static void addFourSelect
		(int id, int id2, int id3, String text1, String text2, String text3, String text4,
		int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
		int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y,
		RSFont[] tda, int hover, int hover2, int hover3, int hover4) //4button spec
    {
        RSInterface rsi = addInterface(id); //2423
			/*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);
			
			addTooltip(hover, "(Accurate)\n(Slash)\n(Attack XP)");
			addTooltip(hover2, "(Agressive)\n(Slash)\n(Strength XP)");
			addTooltip(hover3, "(Agressive)\n(Crush)\n(Strength XP)");
			addTooltip(hover4, "(Defensive)\n(Slash)\n(Defence XP)");
			/*specialBar(ID);*/
            rsi.specialBar(id3); //7599
            rsi.width = 190;
            rsi.height = 261;
        int last = 19; int frame = 0;
        rsi.totalChildren(last, last, last);
            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432
            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436
            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599
			
			rsi.child(frame, hover, 21, 46); frame++; //Retaliate+cb lvl
			rsi.child(frame, hover2, 105, 46); frame++; //Retaliate+cb lvl
			rsi.child(frame, hover3, 21, 99); frame++; //Retaliate+cb lvl
			rsi.child(frame, hover4, 105, 99); frame++; //Retaliate+cb lvl

		
			rsi.child(frame, 19300, 0, 0); frame++; //Retaliate+cb lvl
			
			
        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
			rsi = interfaceCache[i];
				rsi.disabledSprite = CustomSpriteLoader(19301, "");
				rsi.enabledSprite = CustomSpriteLoader(19301, "a");
				rsi.width = 68; rsi.height = 44;
        }
    }

    public static void addFourSelectNoSpec
		(int id, int id2, String text1, String text2, String text3, String text4,
		int str1x, int str1y, int str2x, int str2y, int str3x, int str3y, int str4x, int str4y,
		int img1x, int img1y, int img2x, int img2y, int img3x, int img3y, int img4x, int img4y,
		RSFont[] tda) //4button nospec
    {
        RSInterface rsi = addInterface(id); //2423
			/*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+11, text1, tda, 0, 0xff981f, false);
            addText(id2+12, text2, tda, 0, 0xff981f, false);
            addText(id2+13, text3, tda, 0, 0xff981f, false);
            addText(id2+14, text4, tda, 0, 0xff981f, false);
            rsi.width = 190;
            rsi.height = 261;
        int last = 14; int frame = 0;
        rsi.totalChildren(last, last, last);
            rsi.child(frame, id2+3, 21, 46); frame++; //2429
            rsi.child(frame, id2+4, 104, 99); frame++; //2430
            rsi.child(frame, id2+5, 21, 99); frame++; //2431
            rsi.child(frame, id2+6, 105, 46); frame++; //2432
            rsi.child(frame, id2+7, img1x, img1y); frame++; //bottomright 2433
            rsi.child(frame, id2+8, img2x, img2y); frame++; //topleft 2434
            rsi.child(frame, id2+9, img3x, img3y); frame++; //bottomleft 2435
            rsi.child(frame, id2+10, img4x, img4y); frame++; //topright 2436
            rsi.child(frame, id2+11, str1x, str1y); frame++; //chop 2437
            rsi.child(frame, id2+12, str2x, str2y); frame++; //slash 2438
            rsi.child(frame, id2+13, str3x, str3y); frame++; //lunge 2439
            rsi.child(frame, id2+14, str4x, str4y); frame++; //block 2440
            rsi.child(frame, id2, 94, 4); frame++; //weapon 2426
			rsi.child(frame, 19300, 0, 0); frame++; //Retaliate+cb lvl
        for (int i = id2+3; i < id2+7; i++) { //2429 - 2433
			rsi = interfaceCache[i];
				rsi.disabledSprite = CustomSpriteLoader(19301, "");
				rsi.enabledSprite = CustomSpriteLoader(19301, "a");
				rsi.width = 68; rsi.height = 44;
        }
    }

    public static void addThreeSelect
		(int id, int id2, int id3, String text1, String text2, String text3,
		int str1x, int str1y, int str2x, int str2y, int str3x, int str3y,
		int img1x, int img1y, int img2x, int img2y, int img3x, int img3y,
		RSFont[] tda) //3button spec
    {
        RSInterface rsi = addInterface(id); //2423
			/*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+9, text1, tda, 0, 0xff981f, false);
            addText(id2+10, text2, tda, 0, 0xff981f, false);
            addText(id2+11, text3, tda, 0, 0xff981f, false);
			/*specialBar(ID);*/
            rsi.specialBar(id3); //7599
            rsi.width = 190;
            rsi.height = 261;
        int last = 12; int frame = 0;
        rsi.totalChildren(last, last, last);
            rsi.child(frame, id2+3, 21, 99); frame++;
            rsi.child(frame, id2+4, 105, 46); frame++;
            rsi.child(frame, id2+5, 21, 46); frame++;
            rsi.child(frame, id2+6, img1x, img1y); frame++; //topleft
            rsi.child(frame, id2+7, img2x, img2y); frame++; //bottomleft
            rsi.child(frame, id2+8, img3x, img3y); frame++; //topright
            rsi.child(frame, id2+9, str1x, str1y); frame++; //chop
            rsi.child(frame, id2+10, str2x, str2y); frame++; //slash
            rsi.child(frame, id2+11, str3x, str3y); frame++; //lunge
            rsi.child(frame, id2, 94, 4); frame++; //weapon
            rsi.child(frame, id3, 21, 205); frame++; //special attack 7599
			rsi.child(frame, 19300, 0, 0); frame++; //Retaliate+cb lvl
        for (int i = id2+3; i < id2+6; i++) {
			rsi = interfaceCache[i];
				rsi.disabledSprite = CustomSpriteLoader(19301, "");
				rsi.enabledSprite = CustomSpriteLoader(19301, "a");
				rsi.width = 68; rsi.height = 44;
        }
    }

    public static void addThreeSelectNoSpec
		(int id, int id2, String text1, String text2, String text3,
		int str1x, int str1y, int str2x, int str2y, int str3x, int str3y,
		int img1x, int img1y, int img2x, int img2y, int img3x, int img3y,
		RSFont[] tda) //3button nospec (magic intf)
    {
        RSInterface rsi = addInterface(id); //2423
			/*addText(ID, "Text", tda, Size, Colour, Centered);*/
            addText(id2, "-2", tda, 3, 0xff981f, true); //2426
            addText(id2+9, text1, tda, 0, 0xff981f, false);
            addText(id2+10, text2, tda, 0, 0xff981f, false);
            addText(id2+11, text3, tda, 0, 0xff981f, false);
            //addText(353, "Spell", tda, 0, 0xff981f, false);
			removeSomething(353);
            addText(354, "Spell", tda, 0, 0xff981f, false);
            addCacheSprite(337, 19, 0, "combaticons");
            addCacheSprite(338, 13, 0, "combaticons2");
            addCacheSprite(339, 14, 0, "combaticons2");
			/*addToggleButton(id, sprite, config, width, height, tooltip);*/
            //addToggleButton(349, 349, 108, 68, 44, "Select");
			removeSomething(349);
            addToggleButton(350, 350, 108, 68, 44, "Select");
            rsi.width = 190;
            rsi.height = 261;
			int last = 15; int frame = 0;
			rsi.totalChildren(last, last, last);
            rsi.child(frame, id2+3, 20, 115); frame++;
            rsi.child(frame, id2+4, 20, 80); frame++;
            rsi.child(frame, id2+5, 20, 45); frame++;
            rsi.child(frame, id2+6, img1x, img1y); frame++; //topleft
            rsi.child(frame, id2+7, img2x, img2y); frame++; //bottomleft
            rsi.child(frame, id2+8, img3x, img3y); frame++; //topright
            rsi.child(frame, id2+9, str1x, str1y); frame++; //bash
            rsi.child(frame, id2+10, str2x, str2y); frame++; //pound
            rsi.child(frame, id2+11, str3x, str3y); frame++; //focus
            rsi.child(frame, 349, 105, 46); frame++; //spell1
            rsi.child(frame, 350, 104, 106); frame++; //spell2
            rsi.child(frame, 353, 125, 74); frame++; //spell
            rsi.child(frame, 354, 125, 134); frame++; //spell
            rsi.child(frame, 19300, 0, 0); frame++; //Retaliate+cb lvl
            rsi.child(frame, id2, 94, 4); frame++; //weapon
    }
	
	public static void emoteTab() {
        RSInterface tab = addTabInterface(147);
        RSInterface scroll = addTabInterface(148);
        tab.totalChildren(1);
        tab.child(0, 148, 0, 3);
        AddInterfaceButton(168, 0, "Interfaces/Emotes/EMOTE", "Yes", 15113, 1, 41,47);				addTooltip(15113, "Yes");
        AddInterfaceButton(169, 1, "Interfaces/Emotes/EMOTE", "No", 15115, 1, 41,47);				addTooltip(15115, "No");
        AddInterfaceButton(164, 2, "Interfaces/Emotes/EMOTE", "Bow", 15117, 1, 41,47);				addTooltip(15117, "Bow");
        AddInterfaceButton(165, 3, "Interfaces/Emotes/EMOTE", "Angry", 15119, 1, 41,47);			addTooltip(15119, "Angry");
        AddInterfaceButton(162, 4, "Interfaces/Emotes/EMOTE", "Think", 15121, 1, 41,47);			addTooltip(15121, "Think");
        AddInterfaceButton(163, 5, "Interfaces/Emotes/EMOTE", "Wave", 15123, 1, 41,47);			addTooltip(15123, "Wave");
        AddInterfaceButton(13370, 6, "Interfaces/Emotes/EMOTE", "Shrug", 15125, 1, 41,47);			addTooltip(15125, "Shrug");
        AddInterfaceButton(171, 7, "Interfaces/Emotes/EMOTE", "Cheer", 15127, 1, 41,47);			addTooltip(15127, "Cheer");
        AddInterfaceButton(167, 8, "Interfaces/Emotes/EMOTE", "Beckon", 15129, 1, 41,47);			addTooltip(15129, "Beckon");
        AddInterfaceButton(170, 9, "Interfaces/Emotes/EMOTE", "Laugh", 15131, 1, 41,47);			addTooltip(15131, "Laugh");
        AddInterfaceButton(13366, 10, "Interfaces/Emotes/EMOTE", "Jump for Joy", 15133, 1, 41,47);	addTooltip(15133, "Jump for joy");
        AddInterfaceButton(13368, 11, "Interfaces/Emotes/EMOTE", "Yawn", 15135, 1, 41,47);			addTooltip(15135, "Yawn");
        AddInterfaceButton(166, 12, "Interfaces/Emotes/EMOTE", "Dance", 15137, 1, 41,47);			addTooltip(15137, "Dance");
        AddInterfaceButton(13363, 13, "Interfaces/Emotes/EMOTE", "Jig", 15139, 1, 41,47);			addTooltip(15139, "Jig");
        AddInterfaceButton(13364, 14, "Interfaces/Emotes/EMOTE", "Spin", 15141, 1, 41,47);			addTooltip(15141, "Spin");
        AddInterfaceButton(13365, 15, "Interfaces/Emotes/EMOTE", "Headbang", 15143, 1, 41,47);		addTooltip(15143, "Headbang");
        AddInterfaceButton(161, 16, "Interfaces/Emotes/EMOTE", "Cry", 15145, 1, 41,47);			addTooltip(15145, "Cry");
        AddInterfaceButton(11100, 17, "Interfaces/Emotes/EMOTE", "Blow kiss", 15147, 1, 41,47);	addTooltip(15147, "Blow kiss");
        AddInterfaceButton(13362, 18, "Interfaces/Emotes/EMOTE", "Panic", 15149, 1, 41,47);		addTooltip(15149, "Panic");
        AddInterfaceButton(13367, 19, "Interfaces/Emotes/EMOTE", "Raspberry", 15151, 1, 41,47);	addTooltip(15151, "Raspberry");
        AddInterfaceButton(172, 20, "Interfaces/Emotes/EMOTE", "Clap", 15153, 1, 41,47);			addTooltip(15153, "Clap");
        AddInterfaceButton(13369, 21, "Interfaces/Emotes/EMOTE", "Salute", 15155, 1, 41,47);		addTooltip(15155, "Salute");
        AddInterfaceButton(13383, 22, "Interfaces/Emotes/EMOTE", "Goblin Bow", 15157, 1, 41,47);	addTooltip(15157, "Goblin bow");
        AddInterfaceButton(13384, 23, "Interfaces/Emotes/EMOTE", "Goblin Salute", 15159, 1, 41,47);addTooltip(15159, "Goblin salute");
        AddInterfaceButton(667, 24, "Interfaces/Emotes/EMOTE", "Freeze", 15161, 1, 41,47);		addTooltip(15161, "Freeze");
        AddInterfaceButton(6503, 25, "Interfaces/Emotes/EMOTE", "Trick", 15163, 1, 41,47);	addTooltip(15163, "Trick");
        AddInterfaceButton(6506, 26, "Interfaces/Emotes/EMOTE", "Safety First", 15189, 1, 41,47);	addTooltip(15189, "Safety First");
        AddInterfaceButton(666, 27, "Interfaces/Emotes/EMOTE", "Snowman Dance", 15167, 1, 41,47);		addTooltip(15167, "Snowman Dance");
		AddInterfaceButton(18464, 33, "Interfaces/Emotes/EMOTE", "Explore", 18000, 1, 41,47);			addTooltip(18000, "Explore");
        AddInterfaceButton(18465, 34, "Interfaces/Emotes/EMOTE", "Stomp", 17181, 1, 41,47);		addTooltip(17181, "Stomp");
        AddInterfaceButton(15166, 35, "Interfaces/Emotes/EMOTE", "Flap", 15183, 1, 41,47);			addTooltip(15183, "Flap");
        AddInterfaceButton(18686, 36, "Interfaces/Emotes/EMOTE", "FacePalm", 15185, 1, 43,47);	addTooltip(15185, "Slap head");
		AddInterfaceButton(18689, 28, "Interfaces/Emotes/EMOTE", "Zombie Walk", 15169, 1, 41,47);	addTooltip(15169, "Zombie walk");
        AddInterfaceButton(18688, 29, "Interfaces/Emotes/EMOTE", "Zombie Dance", 15171, 1, 41,47);	addTooltip(15171, "Zombie dance");
        AddInterfaceButton(18691, 30, "Interfaces/Emotes/EMOTE", "Bunny Hop", 15173, 1, 41,47);		addTooltip(15173, "Bunny Hop");
        AddInterfaceButton(18692, 37, "Interfaces/Emotes/EMOTE", "Zombie Hand", 15175, 1, 41,47);	addTooltip(15175, "Zombie hand");
		AddInterfaceButton(18687, 31, "Interfaces/Emotes/EMOTE", "Air Guitar", 15177, 1, 41,47);	addTooltip(15177, "Air Guitar");
		AddInterfaceButton(154, 32, "Interfaces/Emotes/EMOTE", "SkillCape", 15187, 1, 41,47);		addTooltip(15187, "Skillcape");
        scroll.totalChildren(76);
		/** Emotes **/
		scroll.child(0, 168, 7+3, 6);		scroll.child(1, 169, 51+3, 6);		scroll.child(2, 164, 95+3, 13);		scroll.child(3, 165, 134+3, 6);//Row 1
		scroll.child(4, 162, 6+3, 55);		scroll.child(5, 163, 45+3, 55);		scroll.child(6, 13370, 92+3, 55);	scroll.child(7, 171, 134+3, 55);//Row 2
		scroll.child(8, 167, 4+3, 104);		scroll.child(9, 170, 48+3, 104);	scroll.child(10, 13366, 92+3, 103);	scroll.child(11, 13368, 136+3, 104);//Row 3
		scroll.child(12, 166, 3+3, 153);	scroll.child(13, 13363, 47+3, 153);	scroll.child(14, 13364, 87+3, 153);	scroll.child(15, 13365, 132+3, 153);//Row 4
		scroll.child(16, 161, 5+3, 203);	scroll.child(17, 11100, 48+3, 202);	scroll.child(18, 13362, 96+3, 203);	scroll.child(19, 13367, 134+3, 202);//Row 5
		scroll.child(20, 172, 7+3, 250);	scroll.child(21, 13369, 50+3, 250);	scroll.child(22, 13383, 85+3, 255);	scroll.child(23, 13384, 135+3, 249);//Row 6
		scroll.child(24, 667, 0+3, 300);	scroll.child(25, 6503, 49+3, 299);	scroll.child(26, 6506, 93+3, 299);	scroll.child(27, 666, 138+3, 299);//Row 7
		scroll.child(28, 18464, 2+3, 349);	scroll.child(29, 18465, 50+3, 350);	scroll.child(30, 15166, 85+3, 352);	scroll.child(31, 18686, 139+3, 350);//Row 8
		scroll.child(32, 18687, 9+3, 452);	scroll.child(33, 18689, 7+3, 401);	scroll.child(34, 18688, 48+3, 402);	scroll.child(35, 18692, 85+3, 402);//Row 9
		scroll.child(36, 18691, 136+3, 406);scroll.child(37, 154, 46+3, 450);//Row 10
		/** ToolTips **/
		scroll.child(38, 15113, 0, 58);		scroll.child(39, 15115, 44, 58);	scroll.child(40, 15117, 88, 58);	scroll.child(41, 15119, 132, 58);//Row 1
		scroll.child(42, 15121, 0, 107);	scroll.child(43, 15123, 44, 107);	scroll.child(44, 15125, 88, 107);	scroll.child(45, 15127, 132, 107);//Row 2
		scroll.child(46, 15129, 0, 156);	scroll.child(47, 15131, 44, 156);	scroll.child(48, 15133, 88, 156);	scroll.child(49, 15135, 132, 156);//Row 3
		scroll.child(50, 15137, 0, 205);	scroll.child(51, 15139, 44, 205);	scroll.child(52, 15141, 88, 205);	scroll.child(53, 15143, 113, 205);//Row 4
		scroll.child(54, 15145, 2, 178);	scroll.child(55, 15147, 44, 179);	scroll.child(56, 15149, 88, 179);	scroll.child(57, 15151, 107, 179);//Row 5
		scroll.child(58, 15153, 0, 227);	scroll.child(59, 15155, 44, 227);	scroll.child(60, 15157, 88, 227);	scroll.child(61, 15159, 93, 227);//Row 6
		scroll.child(62, 15161, 2, 276);	scroll.child(63, 15163, 44, 276);	scroll.child(64, 15189, 88, 276);	scroll.child(65, 15167, 112, 276);//Row 7
		scroll.child(66, 15169, 0, 374);	scroll.child(67, 15171, 44, 374);	scroll.child(68, 15175, 88, 374);	scroll.child(69, 15173, 129, 374);//Row 8
		scroll.child(70, 18000, 0, 325);	scroll.child(71, 17181, 44, 325);	scroll.child(72, 15183, 88, 325);	scroll.child(73, 15185, 112, 325);//Row 9
		scroll.child(74, 15177, 0, 423);	scroll.child(75, 15187, 44, 423);//Row 10
        scroll.width = 173;
		scroll.height = 255;
		scroll.scrollMax = 500;
    }

	public static void optionTab(RSFont[] TDA) {
        RSInterface Interface = addTabInterface(904);
		setChildren(46,Interface);
		addSprite(25801, 0,"Interfaces/OptionTab/OPTION");
		addSprite(25802, 1,"Interfaces/OptionTab/OPTION");
		addSprite(25803, 1,"Interfaces/OptionTab/OPTION");
		addSprite(25804, 1,"Interfaces/OptionTab/OPTION");
		setBounds(25801, 49, 17, 0, Interface);
		setBounds(25802, 49, 54, 1, Interface);
		setBounds(25803, 49, 90, 2, Interface);
		setBounds(25804, 49, 127, 3, Interface);
		AddInterfaceButton(25805, 5, -1, 2, 2, "Interfaces/OptionTab/OPTION", 32, 32, "Adjust Brightness", 166, 1);
		setBounds(25805, 9, 8, 4, Interface);
		AddInterfaceButton(25806, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 166, 1);
		AddInterfaceButton(25807, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 166, 2);
		AddInterfaceButton(25808, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 166, 3);
		AddInterfaceButton(25809, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 166, 4);
		setBounds(25806, 57, 16, 5, Interface);
		setBounds(25807, 88, 16, 6, Interface);
		setBounds(25808, 119, 16, 7, Interface);
		setBounds(25809, 153, 16, 8, Interface);
		AddInterfaceButton(25810, 5, -1, 3, 4, "Interfaces/OptionTab/OPTION", 32, 32, "Adjust Music Level", 168, 4);
		setBounds(25810, 9, 45, 9, Interface);
		AddInterfaceButton(25811, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 4);
		AddInterfaceButton(25812, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 3);
		AddInterfaceButton(25813, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 2);
		AddInterfaceButton(25814, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 1);
		AddInterfaceButton(25815, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 168, 0);
		setBounds(25811, 54, 53, 10, Interface);
		setBounds(25812, 78, 53, 11, Interface);
		setBounds(25813, 105, 53, 12, Interface);
		setBounds(25814, 131, 53, 13, Interface);
		setBounds(25815, 156, 53, 14, Interface);
		AddInterfaceButton(25816, 5, -1, 5, 6, "Interfaces/OptionTab/OPTION", 32, 32, "Adjust Sounds", 169, 4);
		setBounds(25816, 9, 81, 15, Interface);
		AddInterfaceButton(25817, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 4);
		AddInterfaceButton(25818, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 3);
		AddInterfaceButton(25819, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 2);
		AddInterfaceButton(25820, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 1);
		AddInterfaceButton(25821, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 169, 0);
		setBounds(25817, 54, 89, 16, Interface);
		setBounds(25818, 78, 89, 17, Interface);
		setBounds(25819, 105, 89, 18, Interface);
		setBounds(25820, 131, 89, 19, Interface);
		setBounds(25821, 156, 89, 20, Interface);
		AddInterfaceButton(25822, 5, -1, 7, 8, "Interfaces/OptionTab/OPTION", 32, 32, "Adjust Sound Effects", 400, 0);
		setBounds(25822, 10, 119, 21, Interface);
		AddInterfaceButton(25823, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 0);
		AddInterfaceButton(25824, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 1);
		AddInterfaceButton(25825, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 2);
		AddInterfaceButton(25826, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 3);
		AddInterfaceButton(25827, 5, -1, -1, 18, "Interfaces/OptionTab/OPTION", 16, 16, "Select", 400, 4);
		setBounds(25823, 54, 126, 22, Interface);
		setBounds(25824, 78, 126, 23, Interface);
		setBounds(25825, 105, 126, 24, Interface);
		setBounds(25826, 131, 126, 25, Interface);
		setBounds(25827, 156, 126, 26, Interface);		
		AddInterfaceButton(25828, 4, 25829, 10, 9, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Mouse Buttons", 170, 1);
		drawTooltip(25829, "Toggle Mouse Buttons");
		AddInterfaceButton(25831, 4, 25832, 10, 9, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Chat Effects", 171, 1);
		drawTooltip(25832, "Toggle Chat Effects");
		AddInterfaceButton(25834, 4, 25835, 10, 9, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Split-Level Chat", 287, 1);
		drawTooltip(25835, "Toggle Split-Level Chat");
		AddInterfaceButton(25837, 4, 25838, 10, 9, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Accept Aid", 427, 0);
		drawTooltip(25838, "Toggle Accept Aid");
		AddInterfaceButton(152, 4, 25841, 10, 9, "Interfaces/OptionTab/OPTION", 40, 40, "Toggle Run Mode", 173, 0);
		drawTooltip(25841, "Toggle Run-Mode");
		AddInterfaceButton(25843, 10, 25844, "Interfaces/OptionTab/OPTION", 40, 40, "", 1);
		drawTooltip(25844, "View more options");
		setBounds(25828, 19, 152, 27, Interface);
		setBounds(25831, 75, 152, 28, Interface);
		setBounds(25834, 131, 152, 29, Interface);
		setBounds(25837, 19, 206, 30, Interface);
		setBounds(152, 75, 206, 31, Interface);
		setBounds(25843, 131, 206, 32, Interface);
		setBounds(25857, 78, 159, 33, Interface);
		setBounds(25858, 136, 158, 34, Interface);
		setBounds(25859, 23, 212, 35, Interface);
		setBounds(25860, 86, 210, 36, Interface);
		setBounds(25861, 136, 211, 37, Interface);
		setBounds(25856, 23, 159, 38, Interface);
		setBounds(25829, 19, 125, 40, Interface);
		setBounds(25832, 78, 125, 41, Interface);
		setBounds(25835, 71, 125, 42, Interface);
		setBounds(25838, 19, 185, 43, Interface);
		setBounds(25841, 78, 185, 44, Interface);
		setBounds(25844, 85, 185, 39, Interface);
		addSprite(25856, 11, "Interfaces/OptionTab/OPTION");
		addSprite(25857, 12, "Interfaces/OptionTab/OPTION"); 
		addSprite(25858, 13, "Interfaces/OptionTab/OPTION"); 
		addSprite(25859, 14, "Interfaces/OptionTab/OPTION");
		addSprite(25860, 15, "Interfaces/OptionTab/OPTION");
		addSprite(25861, 17, "Interfaces/OptionTab/OPTION");
		addText(149, "100%", 0xFF9800, true, true, 52, TDA, 1);
		setBounds(149, 94, 230, 45, Interface);
    }
	
	public static void questTab(RSFont[] TDA){
		RSInterface Interface = addInterface(638);
		setChildren(4, Interface);
		addText(29155, "Quests", 0xFF981F, false, true, 52, TDA, 2);
		AddInterfaceButton(29156, 1, "Interfaces/QuestTab/QUEST", 18, 18, "archievement tab", 1);
		addSprite(29157, 0, "Interfaces/QuestTab/QUEST");
		setBounds(29155, 10, 5, 0, Interface);
		setBounds(29156, 165, 5, 1, Interface);
		setBounds(29157, 3, 24, 2, Interface);
		setBounds(29160, 5, 29, 3, Interface);
		Interface = addInterface(29160);
		Interface.height = 214;
		Interface.width = 165;
		Interface.scrollMax = 1700;
		Interface.newScroller = false;
		setChildren(105, Interface);
		addText(29161, "Owner :", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29162, "Rex", "Owners", TDA, 0, 0xff0000, false, true, 150);
		addText(29163, "Register on :", 0xFF981F, false, true, 52, TDA, 2);
		addHoverText(29164, "Link Soon..", "Register", TDA, 0, 0xff0000, false, true, 150);
		setBounds(29161, 4, 4, 0, Interface);
		setBounds(29162, 8, 22, 1, Interface);
		setBounds(29163, 4, 35, 2, Interface);
		setBounds(29164, 8, 53, 3, Interface);
		setBounds(663, 4, 67, 4, Interface);
		int Ypos = 83;
		int frameID = 5;
		for(int iD = 29165; iD <= 29264;iD++){
			addHoverText(iD, "", "View Quest"/*"View Quest Journal, "+iD*/, TDA, 0, 0xff0000, false, true, 150);
			setBounds(iD, 8, Ypos, frameID, Interface);
			frameID++;
			Ypos += 15;
			Ypos++;
		}
		Interface = addInterface(29265);
		try {
			setChildren(4, Interface);
			addText(29266, " ", 0xFF981F, false, true, -1, TDA, 2);
			AddInterfaceButton(29267, 2, "Interfaces/QuestTab/QUEST", 18, 18, "Swap to Quest Diary", 1);
			addSprite(29269, 0, "Interfaces/QuestTab/QUEST");
			setBounds(29266, 10, 5, 0, Interface);
			setBounds(29267, 165, 5, 1, Interface);
			setBounds(29269, 3, 24, 2, Interface);
			setBounds(29268, 5, 29, 3, Interface);
			Interface = addInterface(29268);
			Interface.height = 214;
			Interface.width = 165;
			Interface.scrollMax = 1700;
			Interface.newScroller = false;
			setChildren(20, Interface);
			setBounds(29295, 8, 4, 0, Interface);
			setBounds(29296, 8, 16, 1, Interface);
			setBounds(29297, 8, 29, 2, Interface);
			setBounds(29298, 8, 42, 3, Interface);
			setBounds(29299, 8, 54, 4, Interface);
			setBounds(29300, 8, 66, 5, Interface);
			setBounds(29301, 8, 78, 6, Interface);
			setBounds(29302, 8, 90, 7, Interface);
			setBounds(29303, 8, 102, 8, Interface);
			setBounds(29304, 8, 114, 9, Interface);
			setBounds(29305, 8, 126, 10, Interface);
			setBounds(29306, 8, 138, 11, Interface);
			setBounds(29307, 8, 150, 12, Interface);
			setBounds(29308, 8, 162, 13, Interface);
			setBounds(29309, 8, 174, 14, Interface);
			setBounds(29310, 8, 186, 15, Interface);
			setBounds(29311, 8, 198, 16, Interface);
			setBounds(29312, 8, 210, 17, Interface);
			setBounds(29313, 8, 222, 18, Interface);
			setBounds(29314, 8, 234, 19, Interface);
			addHoverText(29295, "", "", TDA, 0, 0xFF981F, false, true, 150);
			addHoverText(29296, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29297, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29298, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29299, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29300, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29301, "", "", TDA, 0, 0xFF981F, false, true, 150);
			addHoverText(29302, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29303, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29304, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29305, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29306, "", "", TDA, 0, 0xFF981F, false, true, 150);
			addHoverText(29307, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29308, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29309, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29310, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29311, "", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29312, " ", " ", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29313, "", "", TDA, 0, 0xff0000, false, true, 150);
			addHoverText(29314, "", "", TDA, 0, 0xff0000, false, true, 150);
			} catch(Exception e){
				e.printStackTrace();
		}	
	}
	public static void clanChatTab(RSFont[] tda) {
        RSInterface tab = addTabInterface(18128);
        addHoverButton(18129, "Interfaces/Clan Chat/SPRITE", 6, 72, 32, "Join Chat", 550, 18130, 1);
		addHoveredButton(18130, "Interfaces/Clan Chat/SPRITE", 7, 72, 32, 18131);
        addHoverButton(18132, "Interfaces/Clan Chat/SPRITE", 6, 72, 32, "Leave Chat", -1, 18133, 5);
        addHoveredButton(18133, "Interfaces/Clan Chat/SPRITE", 7, 72, 32, 18134);
		AddInterfaceButtons(18250, 0, "Interfaces/Clan Chat/Lootshare", "Toggle LS/CS", 18251, 1);
		drawTooltip(18251, "Toggle-LS/CS");
		addText(18135, "Join Chat", tda, 0, 0xff9b00, true, true);
        addText(18136, "Leave Chat", tda, 0, 0xff9b00, true, true);
        addSprite(18137, 37, "Interfaces/Clan Chat/SPRITE");
        addText(18138, "Clan Chat", tda, 1, 0xff9b00, true, true);
        addText(18139, "Talking in: Not in chat", tda, 0, 0xff9b00, false, true);
        addText(18140, "Owner: None", tda, 0, 0xff9b00, false, true);
        tab.totalChildren(13);
        tab.child(0, 18137, 3, 57);
        tab.child(1, 18143, 7, 62);
        tab.child(2, 18129, 15, 227);
        tab.child(3, 18130, 15, 227);
        tab.child(4, 18132, 103, 226);
        tab.child(5, 18133, 103, 226);
        tab.child(6, 18135, 51, 238);
        tab.child(7, 18136, 139, 238);
        tab.child(8, 18138, 95, 3);
        tab.child(9, 18139, 10, 23);
        tab.child(10, 18140, 25, 38);
		tab.child(11, 18250, 148, 24);
		tab.child(12, 18251, 74, 60);
        /* Text area */
        RSInterface list = addTabInterface(18143);
        list.totalChildren(100);
        for(int i = 18144; i <= 18244; i++) {
            addText(i, "", tda, 0, 0xffffff, false, true);
        }
        for(int id = 18144, i = 0; id <= 18243 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 5;
            for(int id2 = 18144, i2 = 1; id2 <= 18243 && i2<= 99; id2++, i2++) {
                list.childY[0] = 2;
                list.childY[i2] = list.childY[i2 - 1] + 14;
            }
        }
        list.height = 152;
		list.width = 164;
        list.scrollMax = 1405;
    }
	
	public static void Pestpanel(RSFont[] tda) {
		RSInterface RSinterface = addTab(21005);
		addText(21006, "Next Departure:", 0xCCCBCB, false, true, 52, tda, 1);
		addText(21007, "Players Ready:", 0x5BD230, false, true, 52, tda, 1);
		addText(21008, "(Need 5 to 25 players)", 0xDED36A, false, true, 52, tda, 1);
		addText(21009, "Pest Points:", 0x99FFFF, false, true, 52, tda, 1);
		int last = 4;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21006, 15, 12, 0,RSinterface);
		setBounds(21007, 15, 30, 1,RSinterface);
		setBounds(21008, 15, 48, 2,RSinterface);
		setBounds(21009, 15, 66, 3,RSinterface);
	}
		
	public static void Pestpanel2(RSFont[] tda) {
		RSInterface RSinterface = addInterface(21100);
		addSprite(21101, 0, "Interfaces/Pest Control/PEST1");
		addSprite(21102, 1, "Interfaces/Pest Control/PEST1");
		addSprite(21103, 2, "Interfaces/Pest Control/PEST1");
		addSprite(21104, 3, "Interfaces/Pest Control/PEST1");
		addSprite(21105, 4, "Interfaces/Pest Control/PEST1");
		addSprite(21106, 5, "Interfaces/Pest Control/PEST1");
		addText(21107, "", 0xCC00CC, false, true, 52, tda, 1);
		addText(21108, "", 0x0000FF, false, true, 52, tda, 1);
		addText(21109, "", 0xFFFF44, false, true, 52, tda, 1);
		addText(21110, "", 0xCC0000, false, true, 52, tda, 1);
		addText(21111, "", 0x99FF33, false, true, 52, tda, 1);//w purp
		addText(21112, "", 0x99FF33, false, true, 52, tda, 1);//e blue
		addText(21113, "", 0x99FF33, false, true, 52, tda, 1);//se yel
		addText(21114, "", 0x99FF33, false, true, 52, tda, 1);//sw red
		addText(21115, "200", 0x99FF33, false, true, 52, tda, 1);//attacks
		addText(21116, "", 0x99FF33, false, true, 52, tda, 1);//knights hp
		addText(21117, "Time Remaining:", 0xFFFFFF, false, true, 52, tda, 0);
		addText(21118, "", 0xFFFFFF, false, true, 52, tda, 0);
		int last = 18;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21101, 361, 26, 0,RSinterface);
		setBounds(21102, 396, 26, 1,RSinterface);
		setBounds(21103, 436, 26, 2,RSinterface);
		setBounds(21104, 474, 26, 3,RSinterface);
		setBounds(21105, 3, 21, 4,RSinterface);
		setBounds(21106, 3, 50, 5,RSinterface);
		setBounds(21107, 371, 60, 6,RSinterface);
		setBounds(21108, 409, 60, 7,RSinterface);
		setBounds(21109, 443, 60, 8,RSinterface);
		setBounds(21110, 479, 60, 9,RSinterface);
		setBounds(21111, 362, 10, 10,RSinterface);
		setBounds(21112, 398, 10, 11,RSinterface);
		setBounds(21113, 436, 10, 12,RSinterface);
		setBounds(21114, 475, 10, 13,RSinterface);
		setBounds(21115, 32, 32, 14,RSinterface);
		setBounds(21116, 32, 62, 15,RSinterface);
		setBounds(21117, 8, 88, 16,RSinterface);
		setBounds(21118, 87, 88, 17,RSinterface);
	}
	
	int textLines = 162;
	int[] musicText = {4287, 4288, 4289, 4290, 4291, 4292, 4293, 4294, 4295, 4296, 4297, 4298, 4299, 4300, 4301, 4302, 4303, 4304, 8971, 4306, 4307, 4308, 4309, 4310, 4311, 8972, 4313, 4314, 4315, 4316, 4317,  8973, 4319, 4320, 4321, 8974, 4323, 4324, 4325, 4326, 4327, 4328, 4329, 4330, 4331, 4332, 4333, 4334, 4335, 4336, 4337, 4338, 4339, 4340, 4341, 4342, 4343, 4344, 4345, 4346, 4347, 4348, 4349, 4350, 4351, 4352, 4353, 4354, 4355, 4356, 4357, 4358, 4359, 4360, 4361, 4362, 8975, 4364, 4365, 4366, 4367, 4368, 4369, 4370, 4371, 4372, 4373, 4374, 4375, 4376, 4377, 4378, 4379, 4380, 8976, 4382, 4383, 4384, 4385, 4386, 4837, 4388, 12840, 8977, 8978, 4392, 4393, 4394, 4395, 4396, 4397, 4398, 4399, 8979, 4401, 4402, 4403, 4404, 4405, 4406, 4407, 4408, 4409, 4410, 4411, 4412, 4413, 4414, 4415, 4418, 7382, 4420, 4421, 4422, 4423, 4424, 4425, 4426, 13713, 4427, 4428, 4429, 4430, 4431, 4432, 4433, 4434, 4435, 4436, 4437, 5449, 4441, 5988, 5989, 5990, 6185, 6297, 961, 6842, 6843, 1883, 1890};
	public static void musicTab(RSFont[] TDA) {
		RSInterface Interface = addInterface(29500);
		addText(29501, "Playing:", 0xFF981F, false, true, 52,TDA, 1);
		addText(29502, "Attack", 0x33ff00, false, true, 52,TDA, 1);
		setChildren(3, Interface);
		setBounds(29501, 8, 13, 0, Interface);
		setBounds(29502, 8, 33, 1, Interface);
		setBounds(962, 0, 0, 2, Interface);
		Interface = interfaceCache[962];
		addText(963, "", 0xff9b00, false, true, 52,TDA, 1);
		addText(8934, "", 0xFF981F, false, true, 52,TDA, 1);
		addText(6272, "AUTO", 0xFF981F, false, true, 52,TDA, 1);
		addText(6271, "MAN", 0xFF981F, false, true, 52,TDA, 1);
		addText(9926, "LOOP", 0xFF981F, false, true, 52,TDA, 1);
		addText(5450, "", 0x33ff00, false, true, 52,TDA, 1);
		addText(4439, "", 0xFF981F, false, true, 52,TDA, 1);
		addText(3206, "Click the tune to play", 0xFF981F, false, true, 52,TDA, 1);
	}
	
	public static int boxIds[] = {
		4041, 4077, 4113, 4047, 4083, 4119, 4053, 4089, 4125, 4059, 4095,
		4131, 4065, 4101, 4137, 4071, 4107, 4143, 4154, 12168, 13918
	};
	
    // OLD WITHOUT HUNTER AND SUMMONING
	
	/*public static void skillInterface(RSFont[] wid) {
        skillInterface(19746, 255);
        skillInterface(19749, 53);
        addText(13984, "Total", wid, 0, 0xFFEE33);
        //addText(13984, "Total", wid, 0, 0xFFEE33, false, true);
        addText(3985, "", wid, 0, 0xFFEE33);
        addText(13983, "Level: 2277", wid, 0, 0xFFEE33, true, true);
        for(int k = 0; k < boxIds.length; k++) {
            skillInterface(boxIds[k], 256);
        }
        RSInterface rsinterface = addTab(3917);
        rsinterface.children = new int[57];        rsinterface.childX = new int[57];    rsinterface.childY = new int[57];
        rsinterface.children[0] = 3918;            rsinterface.childX[0] = 0;            rsinterface.childY[0] = 0;
        rsinterface.children[1] = 3925;            rsinterface.childX[1] = 0;            rsinterface.childY[1] = 31;
        rsinterface.children[2] = 3932;            rsinterface.childX[2] = 0;            rsinterface.childY[2] = 62;
        rsinterface.children[3] = 3939;            rsinterface.childX[3] = 0;            rsinterface.childY[3] = 93;
        rsinterface.children[4] = 3946;            rsinterface.childX[4] = 0;            rsinterface.childY[4] = 124;
        rsinterface.children[5] = 3953;            rsinterface.childX[5] = 0;            rsinterface.childY[5] = 155;
        rsinterface.children[6] = 4148;            rsinterface.childX[6] = 0;            rsinterface.childY[6] = 186;
        rsinterface.children[7] = 19746;        rsinterface.childX[7] = 70;            rsinterface.childY[7] = 69;
        //rsinterface.children[8] = 19748;        rsinterface.childX[8] = 1;            rsinterface.childY[8] = 219;
        //rsinterface.children[9] = 19747;        rsinterface.childX[9] = 64;            rsinterface.childY[9] = 219;
        rsinterface.children[8] = 14000;        rsinterface.childX[8] = 10;            rsinterface.childY[8] = 219;
        rsinterface.children[9] = 19749;        rsinterface.childX[9] = 33;            rsinterface.childY[9] = 223;
        rsinterface.children[10] = 13983;         rsinterface.childX[10] = 95;          rsinterface.childY[10] = 240;
        rsinterface.children[11] = 3984;        rsinterface.childX[11] = 300;        rsinterface.childY[11] = 225;
        rsinterface.children[12] = 3985;        rsinterface.childX[12] = 130;        rsinterface.childY[12] = 238;
        //rsinterface.children[13] = 29800;        rsinterface.childX[13] = 98;          rsinterface.childY[13] = 220;
        //rsinterface.children[14] = 29800;        rsinterface.childX[14] = 107;          rsinterface.childY[14] = 235;
        //rsinterface.children[15] = 29801;        rsinterface.childX[15] = 36;        rsinterface.childY[15] = 220;
        //rsinterface.children[16] = 29801;        rsinterface.childX[16] = 45;        rsinterface.childY[16] = 235;
        rsinterface.children[13] = 4040;        rsinterface.childX[13] = 5;            rsinterface.childY[13] = 20;
        rsinterface.children[14] = 8654;        rsinterface.childX[14] = 0;            rsinterface.childY[14] = 2;
        rsinterface.children[15] = 8655;        rsinterface.childX[15] = 64;        rsinterface.childY[15] = 2;
        rsinterface.children[16] = 4076;        rsinterface.childX[16] = 20;        rsinterface.childY[16] = 20;
        rsinterface.children[17] = 8656;        rsinterface.childX[17] = 128;        rsinterface.childY[17] = 2;
        rsinterface.children[18] = 4112;        rsinterface.childX[18] = 20;        rsinterface.childY[18] = 20;
        rsinterface.children[19] = 8657;        rsinterface.childX[19] = 0;            rsinterface.childY[19] = 33;
        rsinterface.children[20] = 4046;        rsinterface.childX[20] = 20;        rsinterface.childY[20] = 50;
        rsinterface.children[21] = 8658;        rsinterface.childX[21] = 64;        rsinterface.childY[21] = 33;
        rsinterface.children[22] = 4082;        rsinterface.childX[22] = 20;        rsinterface.childY[22] = 50;
        rsinterface.children[23] = 8659;        rsinterface.childX[23] = 128;        rsinterface.childY[23] = 33;
        rsinterface.children[24] = 4118;        rsinterface.childX[24] = 20;        rsinterface.childY[24] = 50;
        rsinterface.children[25] = 8660;        rsinterface.childX[25] = 0;            rsinterface.childY[25] = 60+10;
        rsinterface.children[26] = 4052;        rsinterface.childX[26] = 20;        rsinterface.childY[26] = 83;
        rsinterface.children[27] = 8661;        rsinterface.childX[27] = 65;        rsinterface.childY[27] = 60+10;
        rsinterface.children[28] = 4088;        rsinterface.childX[28] = 20;        rsinterface.childY[28] = 83;
        rsinterface.children[29] = 8662;        rsinterface.childX[29] = 130;        rsinterface.childY[29] = 60+10;
        rsinterface.children[30] = 4124;        rsinterface.childX[30] = 20;        rsinterface.childY[30] = 83;
        rsinterface.children[31] = 8663;        rsinterface.childX[31] = 0;            rsinterface.childY[31] = 90+10;
        rsinterface.children[32] = 4058;        rsinterface.childX[32] = 20;        rsinterface.childY[32] = 120;
        rsinterface.children[33] = 8664;        rsinterface.childX[33] = 65;        rsinterface.childY[33] = 90+10;
        rsinterface.children[34] = 4094;        rsinterface.childX[34] = 20;        rsinterface.childY[34] = 120;
        rsinterface.children[35] = 8665;        rsinterface.childX[35] = 130;        rsinterface.childY[35] = 90+10;
        rsinterface.children[36] = 4130;        rsinterface.childX[36] = 20;        rsinterface.childY[36] = 120;
        rsinterface.children[37] = 8666;        rsinterface.childX[37] = 0;            rsinterface.childY[37] = 130;
        rsinterface.children[38] = 4064;        rsinterface.childX[38] = 20;        rsinterface.childY[38] = 150;
        rsinterface.children[39] = 8667;        rsinterface.childX[39] = 65;        rsinterface.childY[39] = 130;
        rsinterface.children[40] = 4100;        rsinterface.childX[40] = 20;        rsinterface.childY[40] = 150;
        rsinterface.children[41] = 8668;        rsinterface.childX[41] = 130;        rsinterface.childY[41] = 130;
        rsinterface.children[42] = 4136;        rsinterface.childX[42] = 20;        rsinterface.childY[42] = 150;
        rsinterface.children[43] = 8669;        rsinterface.childX[43] = 0;            rsinterface.childY[43] = 160;
        rsinterface.children[44] = 4070;        rsinterface.childX[44] = 20;        rsinterface.childY[44] = 180;
        rsinterface.children[45] = 8670;        rsinterface.childX[45] = 65;        rsinterface.childY[45] = 160;
        rsinterface.children[46] = 4106;        rsinterface.childX[46] = 20;        rsinterface.childY[46] = 180;
        rsinterface.children[47] = 8671;        rsinterface.childX[47] = 130;        rsinterface.childY[47] = 160;
        rsinterface.children[48] = 4142;        rsinterface.childX[48] = 20;        rsinterface.childY[48] = 180;
        rsinterface.children[49] = 8672;        rsinterface.childX[49] = 0;            rsinterface.childY[49] = 190;
        rsinterface.children[50] = 4160;        rsinterface.childX[50] = 20;        rsinterface.childY[50] = 150;
        rsinterface.children[51] = 4160;        rsinterface.childX[51] = 20;        rsinterface.childY[51] = 150;
        rsinterface.children[52] = 12162;        rsinterface.childX[52] = 65;        rsinterface.childY[52] = 190;
        rsinterface.children[53] = 2832;        rsinterface.childX[53] = 20;        rsinterface.childY[53] = 150;
        rsinterface.children[54] = 13928;        rsinterface.childX[54] = 130;        rsinterface.childY[54] = 190;
        rsinterface.children[55] = 13917;        rsinterface.childX[55] = 20;        rsinterface.childY[55] = 150;
        rsinterface.children[56] = 13984;        rsinterface.childX[56] = 82;        rsinterface.childY[56] = 227;
    }
	
	
	
	/*public static void skillInterface(RSFont[] wid) {
	//RSInterface Interface = addTab(3917);
	int index = 0;
		skillInterface(19746, 255);
		//skillInterface(19747, 51);
		//skillInterface(19748, 50); 
		skillInterface(19749, 52);
		addText(29801, "1", wid, 0, 0xFFEE33); //Summoning
		addText(29800, "1", wid, 0, 0xFFEE33); //Hunter
		
		AddInterfaceButton(19747, 51, 27700, "Interfaces/Skill/Skill", 62, 32, "View @or1@Hunter @whi@guide", 1);
		AddInterfaceButton(19748, 50, 27701, "Interfaces/Skill/Skill", 62, 32, "View @or1@Summoning @whi@guide", 1);
		/*
		drawTooltip(27700, "Toggle the current\ngameframe");
		drawTooltip(27701, "Toggle the current\ngameframe");
		
		setChildren(3, Interface);
		setBounds(27700, 43, 140, index, Interface);index++;
		setBounds(27701, 43, 140, index, Interface);index++;
		
		
		addText(13984, "Total", wid, 0, 0xFFEE33);
		//addText(13984, "Total", wid, 0, 0xFFEE33, false, true);
		addText(3985, "", wid, 0, 0xFFEE33);
		addText(13983, "Level: 2277", wid, 0, 0xFFEE33, true, true);
		for(int k = 0; k < boxIds.length; k++) {
			skillInterface(boxIds[k], 256);
		}
		RSInterface rsinterface = addTab(3917);
		rsinterface.children = new int[63];		rsinterface.childX = new int[63];	rsinterface.childY = new int[63];
		rsinterface.children[0] = 3918;			rsinterface.childX[0] = 0;			rsinterface.childY[0] = 0;
		rsinterface.children[1] = 3925;			rsinterface.childX[1] = 0;			rsinterface.childY[1] = 31;
		rsinterface.children[2] = 3932;			rsinterface.childX[2] = 0;			rsinterface.childY[2] = 62;
		rsinterface.children[3] = 3939;			rsinterface.childX[3] = 0;			rsinterface.childY[3] = 93;
		rsinterface.children[4] = 3946;			rsinterface.childX[4] = 0;			rsinterface.childY[4] = 124;
		rsinterface.children[5] = 3953;			rsinterface.childX[5] = 0;			rsinterface.childY[5] = 155;
		rsinterface.children[6] = 4148;			rsinterface.childX[6] = 0;			rsinterface.childY[6] = 186;
		rsinterface.children[7] = 19746;		rsinterface.childX[7] = 70;			rsinterface.childY[7] = 69;
		rsinterface.children[8] = 19748;		rsinterface.childX[8] = 1;			rsinterface.childY[8] = 219;
		rsinterface.children[9] = 19747;		rsinterface.childX[9] = 64;			rsinterface.childY[9] = 219;
		rsinterface.children[10] = 14000;		rsinterface.childX[10] = 10;		rsinterface.childY[10] = 219;
		rsinterface.children[11] = 19749;		rsinterface.childX[11] = 128;		rsinterface.childY[11] = 220;
		rsinterface.children[12] = 13983; 		rsinterface.childX[12] = 158;  		rsinterface.childY[12] = 238;
		rsinterface.children[13] = 3984;		rsinterface.childX[13] = 300;		rsinterface.childY[13] = 225;
		rsinterface.children[14] = 3985;		rsinterface.childX[14] = 130;		rsinterface.childY[14] = 238;
		rsinterface.children[15] = 29800;		rsinterface.childX[15] = 98;  		rsinterface.childY[15] = 220;
		rsinterface.children[16] = 29800;		rsinterface.childX[16] = 107;  		rsinterface.childY[16] = 235;
		rsinterface.children[17] = 29801;		rsinterface.childX[17] = 36;		rsinterface.childY[17] = 220;
		rsinterface.children[18] = 29801;		rsinterface.childX[18] = 45;		rsinterface.childY[18] = 235;
		rsinterface.children[19] = 4040;		rsinterface.childX[19] = 5;			rsinterface.childY[19] = 20;
		rsinterface.children[20] = 8654;		rsinterface.childX[20] = 0;			rsinterface.childY[20] = 2;
		rsinterface.children[21] = 8655;		rsinterface.childX[21] = 64;		rsinterface.childY[21] = 2;
		rsinterface.children[22] = 4076;		rsinterface.childX[22] = 20;		rsinterface.childY[22] = 20;
		rsinterface.children[23] = 8656;		rsinterface.childX[23] = 128;		rsinterface.childY[23] = 2;
		rsinterface.children[24] = 4112;		rsinterface.childX[24] = 20;		rsinterface.childY[24] = 20;
		rsinterface.children[25] = 8657;		rsinterface.childX[25] = 0;			rsinterface.childY[25] = 33;
		rsinterface.children[26] = 4046;		rsinterface.childX[26] = 20;		rsinterface.childY[26] = 50;
		rsinterface.children[27] = 8658;		rsinterface.childX[27] = 64;		rsinterface.childY[27] = 33;
		rsinterface.children[28] = 4082;		rsinterface.childX[28] = 20;		rsinterface.childY[28] = 50;
		rsinterface.children[29] = 8659;		rsinterface.childX[29] = 128;		rsinterface.childY[29] = 33;
		rsinterface.children[30] = 4118;		rsinterface.childX[30] = 20;		rsinterface.childY[30] = 50;
		rsinterface.children[31] = 8660;		rsinterface.childX[31] = 0;			rsinterface.childY[31] = 60+10;
		rsinterface.children[32] = 4052;		rsinterface.childX[32] = 20;		rsinterface.childY[32] = 83;
		rsinterface.children[33] = 8661;		rsinterface.childX[33] = 65;		rsinterface.childY[33] = 60+10;
		rsinterface.children[34] = 4088;		rsinterface.childX[34] = 20;		rsinterface.childY[34] = 83;
		rsinterface.children[35] = 8662;		rsinterface.childX[35] = 130;		rsinterface.childY[35] = 60+10;
		rsinterface.children[36] = 4124;		rsinterface.childX[36] = 20;		rsinterface.childY[36] = 83;
		rsinterface.children[37] = 8663;		rsinterface.childX[37] = 0;			rsinterface.childY[37] = 90+10;
		rsinterface.children[38] = 4058;		rsinterface.childX[38] = 20;		rsinterface.childY[38] = 120;
		rsinterface.children[39] = 8664;		rsinterface.childX[39] = 65;		rsinterface.childY[39] = 90+10;
		rsinterface.children[40] = 4094;		rsinterface.childX[40] = 20;		rsinterface.childY[40] = 120;
		rsinterface.children[41] = 8665;		rsinterface.childX[41] = 130;		rsinterface.childY[41] = 90+10;
		rsinterface.children[42] = 4130;		rsinterface.childX[42] = 20;		rsinterface.childY[42] = 120;
		rsinterface.children[43] = 8666;		rsinterface.childX[43] = 0;			rsinterface.childY[43] = 130;
		rsinterface.children[44] = 4064;		rsinterface.childX[44] = 20;		rsinterface.childY[44] = 150;
		rsinterface.children[45] = 8667;		rsinterface.childX[45] = 65;		rsinterface.childY[45] = 130;
		rsinterface.children[46] = 4100;		rsinterface.childX[46] = 20;		rsinterface.childY[46] = 150;
		rsinterface.children[47] = 8668;		rsinterface.childX[47] = 130;		rsinterface.childY[47] = 130;
		rsinterface.children[48] = 4136;		rsinterface.childX[48] = 20;		rsinterface.childY[48] = 150;
		rsinterface.children[49] = 8669;		rsinterface.childX[49] = 0;			rsinterface.childY[49] = 160;
		rsinterface.children[50] = 4070;		rsinterface.childX[50] = 20;		rsinterface.childY[50] = 180;
		rsinterface.children[51] = 8670;		rsinterface.childX[51] = 65;		rsinterface.childY[51] = 160;
		rsinterface.children[52] = 4106;		rsinterface.childX[52] = 20;		rsinterface.childY[52] = 180;
		rsinterface.children[53] = 8671;		rsinterface.childX[53] = 130;		rsinterface.childY[53] = 160;
		rsinterface.children[54] = 4142;		rsinterface.childX[54] = 20;		rsinterface.childY[54] = 180;
		rsinterface.children[55] = 8672;		rsinterface.childX[55] = 0;			rsinterface.childY[55] = 190;
		rsinterface.children[56] = 4160;		rsinterface.childX[56] = 20;		rsinterface.childY[56] = 150;
		rsinterface.children[57] = 4160;		rsinterface.childX[57] = 20;		rsinterface.childY[57] = 150;
		rsinterface.children[58] = 12162;		rsinterface.childX[58] = 65;		rsinterface.childY[58] = 190;
		rsinterface.children[59] = 2832;		rsinterface.childX[59] = 20;		rsinterface.childY[59] = 150;
		rsinterface.children[60] = 13928;		rsinterface.childX[60] = 130;		rsinterface.childY[60] = 190;
		rsinterface.children[61] = 13917;		rsinterface.childX[61] = 20;		rsinterface.childY[61] = 150;
		rsinterface.children[62] = 13984;		rsinterface.childX[62] = 145;		rsinterface.childY[62] = 225;
	}*/
	public static void extraEquipment(RSFont[] TDA) {
		RSInterface rsi = addInterface(27620);
		AddInterfaceButton(27621, 3, "Interfaces/Equipment/BOX", "Slayer", 27640, 1, 40, 39);
		addTooltip(27640, "Instant slayer Master");
		AddInterfaceButton(27622, 4, "Interfaces/Equipment/BOX", "Change your spellbook", 27642, 1, 40, 39);
		addTooltip(27642, "Switch spellbook");
		AddInterfaceButton(27623, 5, "Interfaces/Equipment/BOX", "Donator Island", 27644, 1, 40, 39);
		addTooltip(27644, "Teleport to Donator Island!");
		addText(27624, "Information:", 0xFF981F, false, true, 52, TDA, 2);
		addSprite(27634, 0, "Interfaces/Options/LINE");
		addText(27625, "Instant slayer master:", 0xFF981F, false, true, 52, TDA, 2);
		addText(27647, "Get Instant access ", 0xFF981F, false, true, 52, TDA, 0);
		addText(27626, "To the slayer master", 0xFF981F, false, true, 52, TDA, 0);
		addText(27627, "for INSTANT Tasks!", 0xFF981F, false, true, 52, TDA, 0);
		addText(27628, "Spellbook change:", 0xFF981F, false, true, 52, TDA, 2);
		addText(27629, "Change your Spellbook", 0xFF981F, false, true, 52, TDA, 0);
		addText(27630, "anywhere ingame", 0xFF981F, false, true, 52, TDA, 0);
		addText(27631, "but in the wilderness.", 0xFF981F, false, true, 52, TDA, 0);
		addText(27632, "Donator Island:", 0xFF981F, false, true, 52, TDA, 2);
		addText(27633, "Has lots of good benefits.", 0xFF981F, false, true, 52, TDA, 0);
		addText(27636, "Go Back", 0xFF981F, false, true, 52, TDA, 0);
		AddInterfaceButton(27635, 7, "Interfaces/Equipment/BOX", "Back", -1, 1, 71, 23);
		addTooltip(27648, "Go back");
		int last = 21;
		int frame = 0;
		setChildren(last, rsi);
		/** Buttons + text */
		setBounds(27621, 5, 5, frame, rsi);frame++;
		setBounds(27622, 75, 5, frame, rsi);frame++;
		setBounds(27623, 145, 5, frame, rsi);frame++;
		setBounds(27635, 55, 235, frame, rsi);frame++;
		setBounds(27636, 70, 240, frame, rsi);frame++;		
		setBounds(27634, 0, 62, frame, rsi);frame++;
		setBounds(27624, 55, 46, frame, rsi);frame++;
		setBounds(27625, 15, 65, frame, rsi);frame++;
		setBounds(27647, 20, 80, frame, rsi);frame++;
		setBounds(27626, 20, 95, frame, rsi); frame++;
		setBounds(27627, 20, 110, frame, rsi); frame++;
		setBounds(27628, 15, 125, frame, rsi); frame++;
		setBounds(27629, 20, 140, frame, rsi); frame++;
		setBounds(27630, 20, 155, frame, rsi); frame++;
		setBounds(27631, 20, 170, frame, rsi); frame++;
		setBounds(27632, 15, 185, frame, rsi); frame++;
		setBounds(27633, 20, 200, frame, rsi); frame++;
		/** Tooltips */
		setBounds(27640, 10, 50, frame, rsi); frame++;
		setBounds(27642, 30, 50, frame, rsi); frame++;
		setBounds(27644, 35, 50, frame, rsi); frame++;
		setBounds(27648, 40, 240, frame, rsi); frame++;
	}
	public static void EquipmentTab(RSFont[] wid) {
		RSInterface Interface = interfaceCache[1644];
		addSprite(15101, 0, "Interfaces/Equipment/bl");//cheap hax
		addSprite(15102, 1, "Interfaces/Equipment/bl");//cheap hax
		addSprite(15109, 2, "Interfaces/Equipment/bl");//cheap hax
		removeSomething(15103);	removeSomething(15104);
		Interface.children[23] = 15101;	Interface.childX[23] = 40;			Interface.childY[23] = 205;
		Interface.children[24] = 15102;	Interface.childX[24] = 110;			Interface.childY[24] = 205;
		Interface.children[25] = 15109;	Interface.childX[25] = 39;			Interface.childY[25] = 240;
		Interface.children[26] = 27650; Interface.childX[26] = 0;			Interface.childY[26] = 0;
		Interface = addInterface(27650);
			AddInterfaceButton(27651, 6, "Interfaces/Equipment/BOX", "More options", 27659, 1, 26, 33);
			addTooltip(27659, "Show donator options");
			AddInterfaceButton(27653, 1, "Interfaces/Equipment/BOX", "Show Equipment Stats", 27655, 1, 40, 39);
			addTooltip(27655, "Show Equipment Stats");
			AddInterfaceButton(27654, 2, "Interfaces/Equipment/BOX", "Xp Lock", 27657, 1, 40, 39);
			addTooltip(27657, "Locks your experiance.");
			setChildren(6, Interface);
			setBounds(27651, 84, 215, 0, Interface);
			setBounds(27653, 29, 205, 1, Interface);
			setBounds(27654, 124, 205, 2, Interface);
			setBounds(27659, 39, 240, 3, Interface);
			setBounds(27655, 39, 240, 4, Interface);
			setBounds(27657, 39, 240, 5, Interface);
	}
	
	public static void godwarsMenu(RSFont[] wid) {
		RSInterface tab = addTabInterface(14040);
		addSprite(14041, 7, "godwarsMenu/CUSTOM");
		addHoverButton(14042, "godwarsMenu/CUSTOM", 8, 21, 21, "Close", 250, 14043, 3);
		addHoveredButton(14043, "godwarsMenu/CUSTOM", 9, 21, 21, 14044);
		addHoverButton(14045, "godwarsMenu/CUSTOM", 18, 119, 171, "@red@Bandos", 0, 14046, 1);
		addHoveredButton(14046, "godwarsMenu/CUSTOM", 17, 119, 171, 14047);
		//addHoverButton(14048, "godwarsMenur/CUSTOM", 20, 238, 159, "@red@Armadyll", 0, 14049, 1);
		//addHoveredButton(14049, "godwarsMenu/CUSTOM", 19, 238, 159, 14050);
        addText(14051, "Choose which god...", wid, 2, 0xFF9900, false, true);
		addHoverButton(14052, "godwarsMenu/CUSTOM", 20, 170, 205, "@red@Armadyl", 0, 14053, 1);
		addHoveredButton(14053, "godwarsMenu/CUSTOM", 19, 170, 205, 14054);
		addHoverButton(14055, "godwarsMenu/CUSTOM", 24, 83, 97, "@red@Saradomin", 0, 14056, 1);
		addHoveredButton(14056, "godwarsMenu/CUSTOM", 23, 83, 97, 14057);
		tab.totalChildren(12);
		tab.child(0, 14041, 4, 20);
		tab.child(1, 14045, 365, 150);
		tab.child(2, 14046, 365, 150);
		tab.child(3, 14048, 257, 21);
		tab.child(4, 14049, 257, 21);
		tab.child(5, 14051, 200, 162);
		tab.child(6, 14052, 5, 21);
		tab.child(7, 14053, 5, 21);
		tab.child(8, 14055, 71, 219);
		tab.child(9, 14056, 71, 219);
		tab.child(10, 14042, 476, 29);
		tab.child(11, 14043, 476, 29);
	}

	public static void equipmentScreen(RSFont[] TDA) {
		RSInterface Interface = addInterface(19148);
		addSprite(19149, 0, "Interfaces/Equipment/CHAR");
		addHover(19150, 3, 0, 19151, 3, "Interfaces/Equipment/CHAR", 21, 21, "Close");
		addHovered(19151, 2, "Interfaces/Equipment/CHAR", 21, 21, 19152);
		addText(19154, "Equip Your Character...", 0xFF981F, false, true, 52, TDA, 2);
		addText(1673, "Attack bonus", 0xFF981F, false, true, 52, TDA, 2);
		addText(1674, "Defense bonus", 0xFF981F, false, true, 52, TDA, 2);
		addText(1685, "Other bonuses", 0xFF981F, false, true, 52, TDA, 2);	
		addText(1675, "Stab:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1676, "Slash:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1677, "Crush:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1678, "Magic:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1679, "Ranged:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1680, "Stab:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1681, "Slash:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1682, "Crush:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1683, "Magic:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1684, "Ranged:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1686, "Strength:", 0xFF981F, false, true, 52, TDA, 1);
		addText(1687, "Prayer:", 0xFF981F, false, true, 52, TDA, 1);
		addText(19155, "0%", 0xFF981F, false, true, 52, TDA, 1);
		addChar(19153);
		int last = 45;
		int frame = 0;
		setChildren(last, Interface);
		setBounds(19149, 12, 20, frame, Interface);frame++;
		setBounds(19150, 472, 27, frame, Interface);frame++;
		setBounds(19151, 472, 27, frame, Interface);frame++;
		setBounds(19153, 193, 190, frame, Interface);frame++;
		setBounds(19154, 23, 29, frame, Interface);frame++;
		setBounds(1673, 365, 71, frame, Interface); frame++;
		setBounds(1674, 365, 163, frame, Interface); frame++;
		setBounds(1675, 372, 85, frame, Interface); frame++;
		setBounds(1676, 372, 99, frame, Interface); frame++;
		setBounds(1677, 372, 113, frame, Interface); frame++;
		setBounds(1678, 372, 127, frame, Interface); frame++;
		setBounds(1679, 372, 141, frame, Interface); frame++;
		setBounds(1680, 372, 177, frame, Interface); frame++;
		setBounds(1681, 372, 191, frame, Interface); frame++;
		setBounds(1682, 372, 205, frame, Interface); frame++;
		setBounds(1683, 372, 219, frame, Interface); frame++;
		setBounds(1684, 372, 233, frame, Interface); frame++;
		setBounds(1685, 365, 253, frame, Interface); frame++;
		setBounds(1686, 372, 267, frame, Interface); frame++;
		setBounds(1687, 372, 281, frame, Interface); frame++;
		setBounds(19155, 94, 286, frame, Interface); frame++;
		setBounds(1645, 83, 106, frame, Interface); frame++;
		setBounds(1646, 83, 135, frame, Interface); frame++;
		setBounds(1647, 83, 172, frame, Interface); frame++;
		setBounds(1648, 83, 213, frame, Interface); frame++;
		setBounds(1649, 27, 185, frame, Interface); frame++;
		setBounds(1650, 27, 221, frame, Interface); frame++;
		setBounds(1651, 139, 185, frame, Interface); frame++;
		setBounds(1652, 139, 221, frame, Interface); frame++;
		setBounds(1653, 53, 148, frame, Interface); frame++;
		setBounds(1654, 112, 148, frame, Interface); frame++;
		setBounds(1655, 63, 109, frame, Interface); frame++;
		setBounds(1656, 117, 108, frame, Interface); frame++;
		setBounds(1657, 83, 71, frame, Interface); frame++;
		setBounds(1658, 42, 110, frame, Interface); frame++;
		setBounds(1659, 83, 110, frame, Interface); frame++;
		setBounds(1660, 124, 110, frame, Interface); frame++;
		setBounds(1661, 27, 149, frame, Interface); frame++;
		setBounds(1662, 83, 149, frame, Interface); frame++;
		setBounds(1663, 139, 149, frame, Interface); frame++;
		setBounds(1664, 83, 189, frame, Interface); frame++;
		setBounds(1665, 83, 229, frame, Interface); frame++;
		setBounds(1666, 27, 229, frame, Interface); frame++;
		setBounds(1667, 139, 229, frame, Interface); frame++;
		setBounds(1688, 29, 111, frame, Interface); frame++;
	}
		
	public static void itemsOnDeath(RSFont[] wid) {
		RSInterface rsinterface = addInterface(17100);
		addSprite(17101, 2, 2);
		addHover(17102, 3, 0, 10601, 1, "Interfaces/Equipment/SPRITE", 17, 17, "Close Window");
		addHovered(10601, 3, "Interfaces/Equipment/SPRITE", 17, 17, 10602);
		addText(17103, "Items Kept On Death", wid, 2, 0xff981f);
		addText(17104, "Items you will keep on death (if not skulled):", wid, 1, 0xff981f);
		addText(17105, "Items you will lose on death (if not skulled):", wid, 1, 0xff981f);
		addText(17106, "Information", wid, 1, 0xff981f);
		addText(17107, "Max items kept on death:", wid, 1, 0xffcc33);
		addText(17108, "~ 3 ~", wid, 1, 0xffcc33);
		rsinterface.scrollMax = 0;
		rsinterface.interfaceShown = false;
		rsinterface.children = new int[12];		rsinterface.childX = new int[12];	rsinterface.childY = new int[12];
		
		rsinterface.children[0] = 17101;		rsinterface.childX[0] = 7;			rsinterface.childY[0] = 8;
		rsinterface.children[1] = 17102;		rsinterface.childX[1] = 480;		rsinterface.childY[1] = 17;        
		rsinterface.children[2] = 17103;		rsinterface.childX[2] = 185;		rsinterface.childY[2] = 18;
		rsinterface.children[3] = 17104;		rsinterface.childX[3] = 22;			rsinterface.childY[3] = 50;
		rsinterface.children[4] = 17105;		rsinterface.childX[4] = 22;			rsinterface.childY[4] = 110;
		rsinterface.children[5] = 17106;		rsinterface.childX[5] = 347;		rsinterface.childY[5] = 47;
		rsinterface.children[6] = 17107;		rsinterface.childX[6] = 349;		rsinterface.childY[6] = 270;
		rsinterface.children[7] = 17108;		rsinterface.childX[7] = 398;		rsinterface.childY[7] = 298;
		rsinterface.children[8] = 17115;		rsinterface.childX[8] = 348;		rsinterface.childY[8] = 64;
		rsinterface.children[9] = 10494;		rsinterface.childX[9] = 26;			rsinterface.childY[9] = 74;
		rsinterface.children[10] = 10600;		rsinterface.childX[10] = 26;		rsinterface.childY[10] = 133;
		rsinterface.children[11] = 10601;		rsinterface.childX[11] = 480;		rsinterface.childY[11] = 17; 
	}
	
	public static void itemsOnDeathDATA(RSFont[] wid) {
		RSInterface rsinterface = addInterface(17115);
		addText(17109, "", wid, 0, 0xff981f);		addText(17110, "The normal amount of", wid, 0, 0xff981f);		addText(17111, "items kept is three.", wid, 0, 0xff981f);
		addText(17112, "", wid, 0, 0xff981f);		addText(17113, "If you are skulled,", wid, 0, 0xff981f);		addText(17114, "you will lose all your", wid, 0, 0xff981f);
		addText(17117, "items, unless an item", wid, 0, 0xff981f);		addText(17118, "protecting prayer is", wid, 0, 0xff981f);		addText(17119, "used.", wid, 0, 0xff981f);
		addText(17120, "", wid, 0, 0xff981f);		addText(17121, "Item protecting prayers", wid, 0, 0xff981f);		addText(17122, "will allow you to keep", wid, 0, 0xff981f);
		addText(17123, "one extra item.", wid, 0, 0xff981f);		addText(17124, "", wid, 0, 0xff981f);		addText(17125, "The items kept are", wid, 0, 0xff981f);
		addText(17126, "selected by the server", wid, 0, 0xff981f);		addText(17127, "and include the most", wid, 0, 0xff981f);		addText(17128, "expensive items you're", wid, 0, 0xff981f);
		addText(17129, "carrying.", wid, 0, 0xff981f);		addText(17130, "", wid, 0, 0xff981f);
		rsinterface.parentID = 17115;
		rsinterface.id = 17115;
		rsinterface.interfaceType = 0;
		rsinterface.atActionType = 0;
		rsinterface.contentType = 0;
		rsinterface.width = 130;
		rsinterface.height = 197;
		rsinterface.opacity = 0;
		rsinterface.hoverType = -1;
		rsinterface.scrollMax = 280;
		rsinterface.children = new int[20];		rsinterface.childX = new int[20];	rsinterface.childY = new int[20];
		rsinterface.children[0] = 17109;		rsinterface.childX[0] = 0;			rsinterface.childY[0] = 0;
		rsinterface.children[1] = 17110;		rsinterface.childX[1] = 0;			rsinterface.childY[1] = 12;
		rsinterface.children[2] = 17111;		rsinterface.childX[2] = 0;			rsinterface.childY[2] = 24;
		rsinterface.children[3] = 17112;		rsinterface.childX[3] = 0;			rsinterface.childY[3] = 36;
		rsinterface.children[4] = 17113;		rsinterface.childX[4] = 0;			rsinterface.childY[4] = 48;
		rsinterface.children[5] = 17114;		rsinterface.childX[5] = 0;			rsinterface.childY[5] = 60;
		rsinterface.children[6] = 17117;		rsinterface.childX[6] = 0;			rsinterface.childY[6] = 72;
		rsinterface.children[7] = 17118;		rsinterface.childX[7] = 0;			rsinterface.childY[7] = 84;
		rsinterface.children[8] = 17119;		rsinterface.childX[8] = 0;			rsinterface.childY[8] = 96;
		rsinterface.children[9] = 17120;		rsinterface.childX[9] = 0;			rsinterface.childY[9] = 108;
		rsinterface.children[10] = 17121;		rsinterface.childX[10] = 0;			rsinterface.childY[10] = 120;
		rsinterface.children[11] = 17122;		rsinterface.childX[11] = 0;			rsinterface.childY[11] = 132;
		rsinterface.children[12] = 17123;		rsinterface.childX[12] = 0;			rsinterface.childY[12] = 144;
		rsinterface.children[13] = 17124;		rsinterface.childX[13] = 0;			rsinterface.childY[13] = 156;
		rsinterface.children[14] = 17125;		rsinterface.childX[14] = 0;			rsinterface.childY[14] = 168;
		rsinterface.children[15] = 17126;		rsinterface.childX[15] = 0;			rsinterface.childY[15] = 180;
		rsinterface.children[16] = 17127;		rsinterface.childX[16] = 0;			rsinterface.childY[16] = 192;
		rsinterface.children[17] = 17128;		rsinterface.childX[17] = 0;			rsinterface.childY[17] = 204;
		rsinterface.children[18] = 17129;		rsinterface.childX[18] = 0;			rsinterface.childY[18] = 216;
		rsinterface.children[19] = 17130;		rsinterface.childX[19] = 0;			rsinterface.childY[19] = 228;
	}
	public static void PVPInterface(RSFont[] tda) {
		RSInterface RSinterface = addInterface(21200);
		addSprite(21201, 0, "Other/NOTINWILD1");
		addText(21202, "126 - 138", tda, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21201, 400, 285, 0,RSinterface);
		setBounds(21202, 444, 318, 1,RSinterface);
	}
	
	public static void PVPInterface2(RSFont[] tda) {
		RSInterface RSinterface = addInterface(21300);
		addSprite(21301, 0, "Other/INWILD1");
		addText(21302, "126 - 138", tda, 1, 0xff9040, true, true);
		int last = 2;
		RSinterface.children = new int[last];
		RSinterface.childX = new int[last];
		RSinterface.childY = new int[last];
		setBounds(21301, 400, 285, 0,RSinterface);
		setBounds(21302, 444, 318, 1,RSinterface);
	}
public static void choosespell(RSFont[] TDA) {
RSInterface Interface = addInterface(16000);
Interface.children = new int[6];
Interface.childX = new int[6];
Interface.childY = new int[6];
//Main interface
Interface.children[0] = 16001;
Interface.childX[0] = 4;
Interface.childY[0] = 16;
//No main interface image.
//Control 1 of main interface (Image)
Interface.children[1] = 16002;
Interface.childX[1] = 69;
Interface.childY[1] = 71;
addSprite(16002, 0, "Interfaces/Choosespell/Sprite");
//Control 2 of main interface (Button)
Interface.children[2] = 16003;
Interface.childX[2] = 218;
Interface.childY[2] = 137;
AddInterfaceButton(16003, 1, "Interfaces/Choosespell/MAGIC", "Ancient Spells");
//Control 3 of main interface (Button)
Interface.children[3] = 16004;
Interface.childX[3] = 107;
Interface.childY[3] = 137;
AddInterfaceButton(16004, 0, "Interfaces/Choosespell/MAGIC", "Normal Spells");
//Control 4 of main interface (Button)
Interface.children[4] = 16005;
Interface.childX[4] = 339;
Interface.childY[4] = 137;
AddInterfaceButton(16005, 2, "Interfaces/Choosespell/MAGIC", "Lunar Spells");
//Control 5 of main interface (Label)
Interface.children[5] = 16006;
Interface.childX[5] = 147;
Interface.childY[5] = 78;
addText(16006, "Select the magic you would like to cast. ", TDA, 1, 0xFFA500);
}

public static void Rules(RSFont[] tda)
  {
                RSInterface tab = addTabInterface(17250);
    addSprite(17251, 0, "Interfaces/Rules/WELCOME");
    addHoverButton(17252, "Interfaces/Rules/SPRITE", 6, 78, 39, "Accept", -1, 17253, 1);
    addHoveredButton(17253, "Interfaces/Rules/SPRITE", 7, 78, 39, 17254);
    addHoverButton(17255, "Interfaces/Rules/SPRITE", 6, 78, 39, "Decline", -1, 17256, 1);
    addHoveredButton(17256, "Interfaces/Rules/SPRITE", 7, 78, 39, 17257);
                addText(17800, "Accept", tda, 0, 0x33ff00, false, true);
                addText(17801, "Decline", tda, 0, 0xff0000, false, true);
                addText(17802, "Rules of Your server:", tda, 2, 0xff981f, false, true);
                addText(17803, "Failure to agree with the rules will get you banned", tda, 0, 0xff981f, false, true);
                addText(17804, "", tda, 2, 0xff981f, false, true);
                addText(17805, "", tda, 0, 0xff981f, false, true);
                addText(17806, "", tda, 2, 0xff981f, false, true);
                addText(17807, "", tda, 0, 0xff981f, false, true);
                addText(17808, "", tda, 2, 0xff981f, false, true);
                addText(17809, "", tda, 0, 0xff981f, false, true);
                addText(17810, "", tda, 2, 0xff981f, false, true);
                addText(17811, "", tda, 0, 0xff981f, false, true);
                tab.totalChildren(18);
                tab.child(0, 17251, 0, 0);
                tab.child(1, 17252, 140, 276);
                tab.child(2, 17253, 140, 276);
                tab.child(3, 17255, 280, 276);
                tab.child(4, 17256, 280, 276);
                tab.child(5, 17800, 159, 285);
                tab.child(6, 17801, 297, 285);
                tab.child(7, 17802, 22, 37);
                tab.child(8, 17803, 210, 40);
                tab.child(9, 17804, 26, 64);
                tab.child(10, 17805, 26, 79);
                tab.child(11, 17806, 26, 91);
                tab.child(12, 17807, 26, 106);
                tab.child(13, 17808, 26, 118);
                tab.child(14, 17809, 26, 133);
                tab.child(15, 17810, 26, 145);
                tab.child(16, 17811, 26, 160);
                tab.child(17, 14000, 0, 58);
                tab = addTabInterface(14000);
                tab.width = 476;
                tab.height = 213;
                tab.scrollMax = 405;
                for(int i = 14001; i <= 14030; i++){
                addText(i, "", tda, 1, 0xffffff, false, true);
                addText(14008, "Offensive Language:", tda, 2, 0xff981f, true, true);
                addText(14009, "Offensive language including racism, sexism is not tolerated.", tda, 0, 0xff981f, true, true);
                addText(14011, "Staff Impersonation:", tda, 2, 0xff981f, true, true);
                addText(14012, "Pretending to act like a member of the staff is not allowed.", tda, 0, 0xff981f, true, true);
                addText(14014, "Scamming", tda, 2, 0xff981f, true, true);
                addText(14015, "Scamming player's items is not allowed.", tda, 0, 0xff981f, true, true);
                addText(14017, "Bug Abuse:", tda, 2, 0xff981f, true, true);
                addText(14018, "Abusing a glitch is not allowed, you must report it immediately", tda, 0, 0xff981f, true, true);
                addText(14020, "Advertising/Spamming:", tda, 2, 0xff981f, true, true);
                addText(14021, "Advertising servers and spamming will not be tolerated.", tda, 0, 0xff981f, true, true);
                addText(14023, "Encouraging Rule Breaking:", tda, 2, 0xff981f, true, true);
                addText(14024, "Encouraging others to break rules is strictly forbidden", tda, 0, 0xff981f, true, true);
                addText(14026, "Pking:", tda, 2, 0xff981f, true, true);
                addText(14027, "No Pkpoints farming, (Gbs fights are your own risk)", tda, 0, 0xff981f, true, true);
                }
                tab.totalChildren(30);
                int Child = 0;
                int Y = 5;
                for(int i = 14001; i <= 14030; i++){
                tab.child(Child, i, 248, Y);
                Child++;
                Y += 13;
 }
  }

public static void SummonTab(RSFont[] wid) {
		RSInterface Tab = addTabInterface(17011);
		addSprite(17012, 6, "SummonTab/SUMMON");
		addButton(17013, 7, "/SummonTab/SUMMON", "Click");
		//addHover(17013, 7,"/SummonTab/SUMMON", "Click");
		addSprite(17014, 6, "SummonTab/SUMMON");
		addConfigButton(17015, 17032, 14, 8, "/SummonTab/SUMMON", 20, 30, "Familiar Special", 1, 5, 300);
		addHoverButton(17018, "/SummonTab/SUMMON", 2, 38, 36, "Beast of burden Inventory", -1, 17028, 1);
		addHoveredButton(17028, "/SummonTab/SUMMON", 12, 38, 36, 17029);
		addHoverButton(17022, "/SummonTab/SUMMON", 1, 38, 36, "Familiar Special Attck", -1, 17030, 1);//addHoverButton(int i, String imageName, int j, int width, int height, String text, int 
		addHoveredButton(17030, "/SummonTab/SUMMON", 13, 38, 36, 17031);
		addHoverButton(17023, "/SummonTab/SUMMON", 3, 38, 36, "Dismiss Familiar", -1, 17033, 1);//addHoverButton(int i, String imageName, int j, int width, int height, String text, int 
		addHoveredButton(17033, "/SummonTab/SUMMON", 15, 38, 36, 17034);
		addSprite(17016, 5, "SummonTab/SUMMON");
		addText(17017, "", wid, 2, 0xDAA520, false, true);
		addSprite(17019, 9, "SummonTab/SUMMON");
		addText(17021, "6.00", wid, 0, 0xFFA500, false, true);
		addSprite(17020, 10, "SummonTab/SUMMON");
		addSprite(17024, 11, "SummonTab/SUMMON");
		addText(17025, "49/50", wid, 0, 0xFFA500, false, true);
		addText(17026, "10", wid, 0, 0xFFA500, false, true);
		addHead2(17027, 75, 55, 800);
		Tab.totalChildren(19);
		Tab.child(0, 17012, 10, 25);
		Tab.child(1, 17013, 24, 7);
		Tab.child(2, 17014, 10, 25);
		Tab.child(3, 17015, 11, 25);
		Tab.child(4, 17016, 15, 140);
		Tab.child(5, 17017, 45, 143);
		Tab.child(6, 17018, 20, 170);
		Tab.child(7, 17019, 115, 167);
		Tab.child(8, 17020, 143, 170);
		Tab.child(9, 17021, 135, 197);
		Tab.child(10, 17022, 20, 213);
		Tab.child(11, 17023, 67, 193);
		Tab.child(12, 17024, 135, 214);
		Tab.child(13, 17025, 135, 240);
		Tab.child(14, 17026, 21, 59);
		Tab.child(15, 17027, 75, 55);
		Tab.child(16, 17028, 20, 170);
		Tab.child(17, 17030, 20, 213);
		Tab.child(18, 17033, 67, 193);
	}
	public static void addButton(int id, int sid, String spriteName, String tooltip) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = tab.disabledSprite.myWidth;
		tab.height = tab.enabledSprite.myHeight;
		tab.tooltip = tooltip;
	}
	public static void addButton(int i, int j, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.interfaceType = 5;
		RSInterface.atActionType = AT;
		RSInterface.contentType = 0;
		RSInterface.opacity = 0;
		RSInterface.hoverType = 52;
		RSInterface.disabledSprite = imageLoader(j,name);
		RSInterface.enabledSprite = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}
	public static void addButton(int id, int sid, String spriteName, String tooltip, int w, int h) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = 1;
		tab.contentType = 0;
		tab.opacity = (byte)0;
		tab.hoverType = 52;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = w;
		tab.height = h;
		tab.tooltip = tooltip;
	}
	public static void addButton(int i, int j, int hoverId, String name, int W, int H, String S, int AT) {
		RSInterface RSInterface = addInterface(i);
		RSInterface.id = i;
		RSInterface.parentID = i;
		RSInterface.interfaceType = 5;
		RSInterface.atActionType = AT;
		RSInterface.opacity = 0;
		RSInterface.hoverType = hoverId;
		RSInterface.disabledSprite = imageLoader(j,name);
		RSInterface.enabledSprite = imageLoader(j,name);
		RSInterface.width = W;
		RSInterface.height = H;
		RSInterface.tooltip = S;
	}
	public static void addButton(int id, int sid, String spriteName, String tooltip, int mOver, int atAction, int width, int height) {
		RSInterface tab = interfaceCache[id] = new RSInterface();
		tab.id = id;
		tab.parentID = id;
		tab.interfaceType = 5;
		tab.atActionType = atAction;
		tab.contentType = 0;
		tab.opacity = 0;
		tab.hoverType = mOver;
		tab.disabledSprite = imageLoader(sid, spriteName);
		tab.enabledSprite = imageLoader(sid, spriteName);
		tab.width = width;
		tab.height = height;
		tab.tooltip = tooltip;
		tab.inventoryhover = true;
	}
	@SuppressWarnings("unused")
	private static void addButton(int ID, int type, int hoverID, int dS, int eS, String NAME, int W, int H, String text, int configFrame, int configId) {
		RSInterface rsinterface = addInterface(ID);
		rsinterface.id = ID;
		rsinterface.parentID = ID;
		rsinterface.interfaceType = 5;
		rsinterface.atActionType = type;
		rsinterface.opacity = 0;
		rsinterface.hoverType = hoverID;
		rsinterface.disabledSprite = imageLoader(dS, NAME);
		rsinterface.enabledSprite = imageLoader(eS, NAME);
		rsinterface.width = W;
		rsinterface.height = H;
		rsinterface.valueCompareType = new int[1];
		rsinterface.requiredValues = new int[1];
		rsinterface.valueCompareType[0] = 1;
		rsinterface.requiredValues[0] = configId;
		rsinterface.valueIndexArray = new int[1][3];
		rsinterface.valueIndexArray[0][0] = 5;
		rsinterface.valueIndexArray[0][1] = configFrame;
		rsinterface.valueIndexArray[0][2] = 0;
		rsinterface.tooltip = text;
	}
	public static void addButtons(int id, int sid, String spriteName, String tooltip, int mOver, int atAction) {
		RSInterface rsinterface = interfaceCache[id] = new RSInterface();
		rsinterface.id = id;
		rsinterface.parentID = id;
		rsinterface.interfaceType = 5;
		rsinterface.atActionType = atAction;
		rsinterface.contentType = 0;
		rsinterface.opacity = (byte)0;
		rsinterface.hoverType = mOver;
		rsinterface.disabledSprite = imageLoader(sid, spriteName);
		rsinterface.enabledSprite = imageLoader(sid, spriteName);
		rsinterface.width = rsinterface.disabledSprite.myWidth;
		rsinterface.height = rsinterface.enabledSprite.myHeight;
		rsinterface.tooltip = tooltip;
		rsinterface.inventoryhover = true;
	}
	    public static void addBob(int i)
    {
        RSInterface rsinterface = interfaceCache[i] = new RSInterface();
        rsinterface.itemActions = new String[5];
        rsinterface.spritesX = new int[20];
        rsinterface. inventoryValue = new int[30];
        rsinterface.inventory = new int[30];
        rsinterface.spritesY = new int[20];
        rsinterface.children = new int[0];
        rsinterface.childX = new int[0];
        rsinterface.childY = new int[0];
        rsinterface.spritesY[0] = 0;
        rsinterface.spritesY[1] = 0;
        rsinterface.spritesY[2] = 0;
        rsinterface.spritesY[3] = 0;
        rsinterface.spritesY[4] = 0;
        rsinterface.spritesY[5] = 0;
        rsinterface.spritesY[6] = 0;
        rsinterface.spritesY[7] = 0;
        rsinterface.spritesY[8] = 0;
        rsinterface.spritesY[9] = 0;
        rsinterface.spritesY[10] = 0;
        rsinterface.spritesY[11] = 0;
        rsinterface.spritesY[12] = 0;
        rsinterface.spritesY[13] = 0;
        rsinterface.spritesY[14] = 0;
        rsinterface.spritesY[15] = 0;
        rsinterface.spritesY[16] = 0;
        rsinterface.spritesY[17] = 0;
        rsinterface.spritesY[18] = 0;
        rsinterface.spritesY[19] = 0;
        rsinterface. inventoryValue[0] = 0;
        rsinterface. inventoryValue[1] = 0;
        rsinterface. inventoryValue[2] = 0;
        rsinterface. inventoryValue[3] = 0;
        rsinterface. inventoryValue[4] = 0;
        rsinterface. inventoryValue[5] = 0;
        rsinterface. inventoryValue[6] = 0;
        rsinterface. inventoryValue[7] = 0;
        rsinterface. inventoryValue[8] = 0;
        rsinterface. inventoryValue[9] = 0;
        rsinterface. inventoryValue[10] = 0;
        rsinterface. inventoryValue[11] = 0;
        rsinterface. inventoryValue[12] = 0;
        rsinterface. inventoryValue[13] = 0;
        rsinterface. inventoryValue[14] = 0;
        rsinterface. inventoryValue[15] = 0;
        rsinterface. inventoryValue[16] = 0;
        rsinterface. inventoryValue[17] = 0;
        rsinterface. inventoryValue[18] = 0;
        rsinterface. inventoryValue[19] = 0;
        rsinterface. inventoryValue[20] = 0;
        rsinterface. inventoryValue[21] = 0;
        rsinterface. inventoryValue[22] = 0;
        rsinterface. inventoryValue[23] = 0;
        rsinterface. inventoryValue[24] = 0;
        rsinterface. inventoryValue[25] = 0;
        rsinterface. inventoryValue[26] = 0;
        rsinterface. inventoryValue[27] = 0;
        rsinterface. inventoryValue[28] = 0;
        rsinterface. inventoryValue[29] = 0;
        rsinterface.inventory[0] = 0;
        rsinterface.inventory[1] = 0;
        rsinterface.inventory[2] = 0;
        rsinterface.inventory[3] = 0;
        rsinterface.inventory[4] = 0;
        rsinterface.inventory[5] = 0;
        rsinterface.inventory[6] = 0;
        rsinterface.inventory[7] = 0;
        rsinterface.inventory[8] = 0;
        rsinterface.inventory[9] = 0;
        rsinterface.inventory[10] = 0;
        rsinterface.inventory[11] = 0;
        rsinterface.inventory[12] = 0;
        rsinterface.inventory[13] = 0;
        rsinterface.inventory[14] = 0;
        rsinterface.inventory[15] = 0;
        rsinterface.inventory[16] = 0;
        rsinterface.inventory[17] = 0;
        rsinterface.inventory[18] = 0;
        rsinterface.inventory[19] = 0;
        rsinterface.inventory[20] = 0;
        rsinterface.inventory[21] = 0;
        rsinterface.inventory[22] = 0;
        rsinterface.inventory[23] = 0;
        rsinterface.inventory[24] = 0;
        rsinterface.inventory[25] = 0;
        rsinterface.inventory[26] = 0;
        rsinterface.inventory[27] = 0;
        rsinterface.inventory[28] = 0;
        rsinterface.inventory[29] = 0;
        rsinterface.spritesX[0] = 0;
        rsinterface.spritesX[1] = 0;
        rsinterface.spritesX[2] = 0;
        rsinterface.spritesX[3] = 0;
        rsinterface.spritesX[4] = 0;
        rsinterface.spritesX[5] = 0;
        rsinterface.spritesX[6] = 0;
        rsinterface.spritesX[7] = 0;
        rsinterface.spritesX[8] = 0;
        rsinterface.spritesX[9] = 0;
        rsinterface.spritesX[10] = 0;
        rsinterface.spritesX[11] = 0;
        rsinterface.spritesX[12] = 0;
        rsinterface.spritesX[13] = 0;
        rsinterface.spritesX[14] = 0;
        rsinterface.spritesX[15] = 0;
        rsinterface.spritesX[16] = 0;
        rsinterface.spritesX[17] = 0;
        rsinterface.spritesX[18] = 0;
        rsinterface.spritesX[19] = 0;
        rsinterface.itemActions[0] = "Take 1";
        rsinterface.itemActions[1] = "Take 5";
        rsinterface.itemActions[2] = "Take 10";
        rsinterface.itemActions[3] = "Take All";
        rsinterface.itemActions[4] = "Take X";
        //rsinterface.centerText = false;
        rsinterface.aBoolean227 = false;
        rsinterface.aBoolean235 = false;
        rsinterface.usableItemInterface = false;
        rsinterface.isInventoryInterface = false;
        rsinterface.aBoolean251 = false;
        rsinterface.aBoolean259 = true;
        rsinterface.interfaceShown = false;
       // rsinterface.textShadow = false;
        rsinterface.width = 6;
        rsinterface.hoverType = -1;
        rsinterface.invSpritePadX = 24;
        rsinterface.parentID = 26702;
        rsinterface.invSpritePadY = 24;
        rsinterface.id = 26700;
        rsinterface.interfaceType = 2;
        rsinterface.height = 5;
    }

    public static void sumInv(RSFont[] TDA)
    {
        RSInterface rsinterface = addTabInterface(26700);
        addSprite(26701, 0, "invt/CUSTOM");
        addBob(26702);
        //addActionButton(26703, 431, 25, "Close Window", "Bank/BANK_3");
		addHover(26703, 3, 0, 3826, 1, "Interfaces/Bank/BANK", 17, 17, "Close Window");
        rsinterface.totalChildren(3);
        rsinterface.child(0, 26701, 90, 14);
        rsinterface.child(1, 26702, 100, 56);
        rsinterface.child(2, 26703, 431, 25);
    }
		    private static void addHead2(int i, int j, int k, int l)
    {
        RSInterface rsinterface = addTab(i);
        rsinterface.interfaceType = 6;
        rsinterface.modelZoom = l;
        rsinterface.modelRotationY = 40;
        rsinterface.modelRotationX = 1900;
        rsinterface.height = k;
        rsinterface.width = j;
    }
   	public static void newTrade(RSFont[] TDA) {
		RSInterface Interface = addInterface(3323);
		setChildren(19, Interface);
		addSprite(3324, 6, "Interfaces/TradeTab/TRADE");
		addHover(3442, 3, 0, 3325, 1, "Interfaces/Bank/BANK", 17, 17, "Close Window");
		addHovered(3325, 2, "Interfaces/Bank/BANK", 17, 17, 3326);
		addText(3417, "Trading With:", 0xFF981F, true, true, 52,TDA, 2);
		addText(3418, "Trader's Offer", 0xFF981F, false, true, 52,TDA, 1);
		addText(3419, "Your Offer", 0xFF981F, false, true, 52,TDA, 1);
		addText(3421, "Accept", 0x00C000, true, true, 52,TDA, 1);
		addText(3423, "Decline", 0xC00000, true, true, 52,TDA, 1);

		addText(3431, "Waiting For Other Player", 0xFFFFFF, true, true, 52,TDA, 1);
		addText(23504, "Wealth transfer:", 0xB9B855, true, true, -1,TDA, 0);
		addText(23505, "Player has\\n -- free\\n inventory slots.", 0xFF981F, true, true, -1,TDA, 0);

		addText(23506, "0.00", 0xB9B855, false, true, -1,TDA, 0);
		addText(23507, "0.00", 0xB9B855, false, true, -1,TDA, 0);

		addHover(3420, 1, 0, 3327, 5, "Interfaces/TradeTab/TRADE", 65, 32, "Accept");
		addHovered(3327, 2, "Interfaces/TradeTab/TRADE", 65, 32, 3328);
		addHover(3422, 3, 0, 3329, 5, "Interfaces//TradeTab/TRADE", 65, 32, "Decline");
		addHovered(3329, 2, "Interfaces/TradeTab/TRADE", 65, 32, 3330);
		setBounds(3324, 0, 16, 0, Interface);
		setBounds(3442, 485, 24, 1, Interface);
		setBounds(3325, 485, 24, 2, Interface);
		setBounds(3417, 258, 25, 3, Interface);
		setBounds(3418, 355, 51, 4, Interface);
		setBounds(3419, 68, 51, 5, Interface);
		setBounds(3420, 223, 120, 6, Interface);
		setBounds(3327, 223, 120, 7, Interface);
		setBounds(3422, 223, 160, 8, Interface);
		setBounds(3329, 223, 160, 9, Interface);
		setBounds(3421, 256, 127, 10, Interface);
		setBounds(3423, 256, 167, 11, Interface);
		setBounds(3431, 256, 272, 12, Interface);
		setBounds(3415, 12, 64, 13, Interface);
		setBounds(3416, 321, 67, 14, Interface);

		setBounds(23505, 256, 67, 16, Interface);

		setBounds(23504, 255, 310, 15, Interface);
		setBounds(23506, 20, 310, 17, Interface);
		setBounds(23507, 380, 310, 18, Interface);

		Interface = addInterface(3443);
		setChildren(15, Interface);
		addSprite(3444, 3, "Interfaces/TradeTab/TRADE");
		AddInterfaceButton(3546, 2, "Interfaces/ShopTab/SHOP 1.png", 63, 24, "Accept", 1);
		AddInterfaceButton(3548, 2, "Interfaces/ShopTab/SHOP 1.png", 63, 24, "Decline", 3);
		addText(3547, "Accept", 0x00C000, true, true, 52,TDA, 1);
		addText(3549, "Decline", 0xC00000, true, true, 52,TDA, 1);
		addText(3450, "Trading With:", 0x00FFFF, true, true, 52,TDA, 2);
		addText(3451, "", 0x00FFFF, true, true, 52,TDA, 2);
		setBounds(3444, 12, 20, 0, Interface);
		setBounds(3442, 470, 32, 1, Interface);
		setBounds(3325, 470, 32, 2, Interface);
		setBounds(3535, 130, 28, 3, Interface);
		setBounds(3536, 105, 47, 4, Interface);
		setBounds(3546, 189, 295, 5, Interface);
		setBounds(3548, 258, 295, 6, Interface);
		setBounds(3547, 220, 299, 7, Interface);
		setBounds(3549, 288, 299, 8, Interface);
		setBounds(3557, 71, 87, 9, Interface);
		setBounds(3558, 315, 87, 10, Interface);
		setBounds(3533, 64, 70, 11, Interface);
		setBounds(3534, 297, 70, 12, Interface);
		setBounds(3450, 95, 289, 13, Interface);
		setBounds(3451, 95, 304, 14, Interface);
	}
	
	public static void editShopMain(RSFont[] TDA) {
		RSInterface Interface = addInterface(3824);
		setChildren(8, Interface);
		addSprite(3825, 0, "Interfaces/ShopTab/SHOP_1.png");
		addHover(3902, 3, 0, 3826, 1, "Interfaces/Bank/BANK", 17, 17, "Close Window");
		addHovered(3826, 2, "Interfaces/Bank/BANK", 17, 17, 3827);
		addText(19679, "Main Stock", 0xFF981F, false, true, 52,TDA, 1);
		addText(19680, "Player Stock", 0xBF751D, false, true, 52,TDA, 1);
		AddInterfaceButton(19681, 1, "Interfaces/ShopTab/SHOP_1.png", 95, 19, "Player Stock", 1);
		setBounds(3825, 12, 12, 0, Interface);
		setBounds(3902, 471, 22, 1, Interface);
		setBounds(3826, 471, 22, 2, Interface);
		setBounds(3900, 60, 85, 3, Interface);
		setBounds(3901, 240, 21, 4, Interface);
		setBounds(19679, 42, 54, 5, Interface);
		setBounds(19680, 150, 54, 6, Interface);
		setBounds(19681, 129, 50, 7, Interface);
		Interface = interfaceCache[3900];
		Interface.invSpritePadX = 8;
		Interface.width = 10;
		Interface.height = 4;
		Interface = addInterface(19682);
		addSprite(19683, 1, "Interfaces/ShopTab/SHOPSHOP_1.png");
		addText(19684, "Main Stock", 0xBF751D, false, true, 52,TDA, 1);
		addText(19685, "Store Info", 0xFF981F, false, true, 52,TDA, 1);
		AddInterfaceButton(19686, 2, "Interfaces/ShopTab/SHOPSHOP_1.png", 95, 19, "Main Stock", 1);
		setChildren(7, Interface);
		setBounds(19683, 12, 12, 0, Interface);
		setBounds(3901, 240, 21, 1, Interface);
		setBounds(19684, 42, 54, 2, Interface);
		setBounds(19685, 150, 54, 3, Interface);
		setBounds(19686, 23, 50, 4, Interface);
		setBounds(3902, 471, 22, 5, Interface);
		setBounds(3826, 60, 85, 6, Interface);
	}
	 public static void addImage(int i, String s)
    {
        RSInterface rsinterface = addInterface(i);
        rsinterface.interfaceType = 5;
        rsinterface.atActionType = 0;
        rsinterface.contentType = 0;
        rsinterface.width = 100;
        rsinterface.height = 100;
        rsinterface.disabledSprite = getSprite(s);
    }
	/*
	public static void skillTab602(RSFont[] tda) 
    {
        RSInterface rsinterface = addInterface(3917);
        String as[] = {
            "Attack", "HP", "Mine", "Strength", "Agility", "Smith", "Defence", "Herblore", "Fish", "Range", 
            "Thief", "Cook", "Prayer", "Craft", "Fire", "Mage", "Fletch", "Wood", "Rune", "Slay", 
            "Farm", "Construction", "Hunter", "Summon", "Dungeon"
        };
        int ai[] = {
            8654, 8655, 8656, 8657, 8658, 8659, 8660, 8861, 8662, 8663, 
            8664, 8665, 8666, 8667, 8668, 8669, 8670, 8671, 8672, 12162, 
            13928, 18177, 18178, 18179, 18180
        };
        int ai1[] = {
            4040, 4076, 4112, 4046, 4082, 4118, 4052, 4088, 4124, 4058, 
            4094, 4130, 4064, 4100, 4136, 4070, 4106, 4142, 4160, 2832, 
            13917, 18173, 18174, 18175, 18176
        };
        int ai2[][] = {
            {
                4004, 4005
            }, {
                4016, 4017
            }, {
                4028, 4029
            }, {
                4006, 4007
            }, {
                4018, 4019
            }, {
                4030, 4031
            }, {
                4008, 4009
            }, {
                4020, 4021
            }, {
                4032, 4033
            }, {
                4010, 4011
            }, {
                4022, 4023
            }, {
                4034, 4035
            }, {
                4012, 4013
            }, {
                4024, 4025
            }, {
                4036, 4037
            }, {
                4014, 4015
            }, {
                4026, 4027
            }, {
                4038, 4039
            }, {
                4152, 4153
            }, {
                12166, 12167
            }, {
                13926, 13927
            }, {
                18165, 18169
            }, {
                18166, 18170
            }, {
                18167, 18171
            }, {
                18168, 18172
            }
        };
        int ai3[] = {
            3965, 3966, 3967, 3968, 3969, 3970, 3971, 3972, 3973, 3974, 
            3975, 3976, 3977, 3978, 3979, 3980, 3981, 3982, 4151, 12165, 
            13925, 18181, 18182, 18183, 18184
        };
        int ai4[][] = {
            {
                4, 4
            }, {
                66, 4
            }, {
                128, 4
            }, {
                4, 32
            }, {
                66, 32
            }, {
                128, 32
            }, {
                4, 60
            }, {
                66, 60
            }, {
                128, 60
            }, {
                4, 88
            }, {
                66, 88
            }, {
                128, 88
            }, {
                4, 116
            }, {
                66, 116
            }, {
                128, 116
            }, {
                4, 144
            }, {
                66, 144
            }, {
                128, 144
            }, {
                4, 172
            }, {
                66, 172
            }, {
                128, 172
            }, {
                4, 200
            }, {
                66, 200
            }, {
                128, 200
            }, {
                4, 229
            }
        };
        int ai5[][] = {
            {
                6, 6
            }, {
                69, 7
            }, {
                131, 6
            }, {
                9, 34
            }, {
                68, 33
            }, {
                131, 36
            }, {
                9, 64
            }, {
                67, 63
            }, {
                131, 61
            }, {
                7, 91
            }, {
                68, 94
            }, {
                133, 90
            }, {
                6, 118
            }, {
                70, 120
            }, {
                130, 118
            }, {
                6, 147
            }, {
                69, 146
            }, {
                132, 146
            }, {
                6, 173
            }, {
                69, 173
            }, {
                130, 174
            }, {
                6, 202
            }, {
                69, 201
            }, {
                131, 202
            }, {
                6, 230
            }
        };
        int ai6[][] = {
            {
                31, 7, 44, 18
            }, {
                93, 7, 106, 18
            }, {
                155, 7, 168, 18
            }, {
                31, 35, 44, 46
            }, {
                93, 35, 106, 46
            }, {
                155, 35, 168, 46
            }, {
                31, 63, 44, 74
            }, {
                93, 63, 106, 74
            }, {
                155, 63, 168, 74
            }, {
                31, 91, 44, 102
            }, {
                93, 91, 106, 102
            }, {
                155, 91, 168, 102
            }, {
                31, 119, 44, 130
            }, {
                93, 119, 106, 130
            }, {
                155, 119, 168, 130
            }, {
                31, 149, 44, 158
            }, {
                93, 147, 106, 158
            }, {
                155, 147, 168, 158
            }, {
                31, 175, 44, 186
            }, {
                93, 175, 106, 186
            }, {
                155, 175, 168, 186
            }, {
                31, 203, 44, 214
            }, {
                93, 203, 106, 214
            }, {
                155, 203, 168, 214
            }, {
                31, 231, 44, 242
            }
        };
        int ai7[][] = {
            {
                18165, 18166, 18167, 18168//these are the values that deplete so like (89/99) this sets tehe 89
            }, {
                18169, 18170, 18171, 18172//this sets the actual skill level
				
            }
			//Its bad to use sendFrame126 to set skill levels though
			 //theres alredy a packet in the client that sends it.
        };
        for(int i = 0; i < ai1.length; i++)
        {
            createSkillHover(ai1[i], 214 + i);
            addSkillButton(ai[i]);
            addImage(ai3[i], as[i]);
        }

        for(int j = 0; j < 4; j++)
        {
            addSkillText(ai7[0][j], false, j + 21);
            addSkillText(ai7[1][j], true, j + 21);
        }

        rsinterface.children(ai3.length + ai2.length * 2 + ai1.length + ai.length + 1);
        int k = 0;
        RSInterface rsinterface1 = interfaceCache[3984];
        rsinterface1.disabledMessage = "Total level: %1";
        rsinterface1.rsFonts = RSInterface.fonts[2];
		rsinterface1.disabledColor = 336699;
		rsinterface1.enabledColor = 336699;		
		RSInterface rsinterface2 = interfaceCache[3984];
        rsinterface2.id = 3984;
        rsinterface2.parentID = 3984;
        rsinterface2.disabledMessage = "Total level: %1";
        rsinterface2.interfaceType = 4;
        rsinterface2.atActionType = 0;
        rsinterface2.width = 15;
        rsinterface2.height = 12;
        rsinterface2.rsFonts = RSInterface.fonts[2];
        rsinterface2.textShadowed = true;
        rsinterface2.textCentered = false;
        rsinterface2.disabledColor = 0xCC3300;
        rsinterface.child(k, 3984, 74, 237);
        k++;
        for(int l = 0; l < ai.length; l++)
        {
            rsinterface.child(k, ai[l], ai4[l][0], ai4[l][1]);
            k++;
        }

        for(int i1 = 0; i1 < ai3.length; i1++)
        {
            rsinterface.child(k, ai3[i1], ai5[i1][0], ai5[i1][1]);
            k++;
        }

        for(int j1 = 0; j1 < ai2.length; j1++)
        {
            rsinterface.child(k, ai2[j1][0], ai6[j1][0], ai6[j1][1]);
            k++;
        }

        for(int k1 = 0; k1 < ai2.length; k1++)
        {
            rsinterface.child(k, ai2[k1][1], ai6[k1][2], ai6[k1][3]);
            k++;
        }

        for(int l1 = 0; l1 < ai1.length; l1++)
        {
            rsinterface.child(k, ai1[l1], ai4[l1][0], ai4[l1][1]);
            k++;
        }

    }*/
	
	public static void addSkillHovers(int i, int CT) {
        RSInterface hover = addTabInterface(i);
        hover.interfaceType = 8;
        hover.contentType = CT;
        hover.disabledMessage = null;
        hover.height = 27;
        hover.width = 60;
    }
	
	public static void Hovers(RSFont[] TDA) {
	
		/**
		  *    Skill Tab Hovers
		**/
        RSInterface Interface = interfaceCache[3984];
        Interface.rsFonts = TDA[2];
        Interface.disabledMessage = "Total level %1";
        addSkillHovers(9215, 204);
        addSkillHovers(9216, 229);
        addSkillHovers(9217, 206);
        addSkillHovers(9218, 207);
        addSkillHovers(9219, 208);
        addSkillHovers(9220, 209);
        addSkillHovers(9221, 210);
        addSkillHovers(9222, 211);
        addSkillHovers(9223, 212);
        addSkillHovers(9224, 213);
        addSkillHovers(9225, 214);
        addSkillHovers(9226, 215);
        addSkillHovers(9227, 216);
        addSkillHovers(9228, 217);
        addSkillHovers(9229, 218);
        addSkillHovers(9230, 219);
        addSkillHovers(9231, 220);
        addSkillHovers(9232, 221);
        addSkillHovers(9233, 222);
        addSkillHovers(9234, 223);
        addSkillHovers(9235, 224);
        addSkillHovers(9236, 225);
        addSkillHovers(9237, 226);
        addSkillHovers(9238, 227);
        addSkillHovers(9239, 228);
		
		
		 
    }
	
	
	
	
	
	public static void skillTab(RSFont[] TDA) {
        RSInterface sT = addTabInterface(7101);

        addSprite(7102, "Skill/Button", "Open Attack Guide", 9215, 60, 27);
		addSprite(7103, "Skill/Button", "Open Hitpoints Guide", 9216, 60, 27);
		addSprite(7104, "Skill/Button", "Open Mining Guide", 9217, 60, 27);
		addSprite(7105, "Skill/Button", "Open Strength Guide", 9218, 60, 27);
		addSprite(7106, "Skill/Button", "Open Agility Guide", 9219, 60, 27);
		addSprite(7107, "Skill/Button", "Open Smithing Guide", 9220, 60, 27);
		addSprite(7108, "Skill/Button", "Open Defence Guide", 9221, 60, 27);
		addSprite(7109, "Skill/Button", "Open Herblore Guide", 9222, 60, 27);
		addSprite(7110, "Skill/Button", "Open Fishing Guide", 9223, 60, 27);
		addSprite(7111, "Skill/Button", "Open Ranging Guide", 9224, 60, 27);
		addSprite(7112, "Skill/Button", "Open Thieving Guide", 9225, 60, 27);
		addSprite(7113, "Skill/Button", "Open Cooking Guide", 9226, 60, 27);
		addSprite(7114, "Skill/Button", "Open Prayer Guide", 9227, 60, 27);
		addSprite(7115, "Skill/Button", "Open Crafting Guide", 9228, 60, 27);
		addSprite(7116, "Skill/Button", "Open Firemaking Guide", 9229, 60, 27);
		addSprite(7117, "Skill/Button", "Open Magic Guide", 9230, 60, 27);
		addSprite(7118, "Skill/Button", "Open Fletching Guide", 9231, 60, 27);
		addSprite(7119, "Skill/Button", "Open Woodcutting Guide", 9232, 60, 27);
		addSprite(7120, "Skill/Button", "Open Runecrafting Guide", 9233, 60, 27);
		addSprite(7121, "Skill/Button", "Open Slaying Guide", 9234, 60, 27);
		addSprite(7122, "Skill/Button", "Open Farming Guide", 9235, 60, 27);
		addSprite(7123, "Skill/Button", "Open Hunter Guide", 9236, 60, 27);
		addSprite(7124, "Skill/Button", "Open Summoning Guide", 9237, 60, 27);
		addSprite(7125, "Skill/Button", "Open PK'ing Guide", 9238, 60, 27);
		addSprite(7126, "Skill/Button", "Open Dungeoneering Guide", 9239, 60, 27);
		addText(7127, "Total Level: 2475", 0xCC3300, false, true, 52, TDA, 2);
		addSprite(7128, "Skill/Dungeon");
		addSprite(7129, "Skill/Attack");
		addSprite(7130, "Skill/Strength");
		addSprite(7131, "Skill/Defence");
		addSprite(7132, "Skill/Range");
		addSprite(7133, "Skill/Prayer");
		addSprite(7134, "Skill/Mage");
		addSprite(7135, "Skill/Rune");
		addSprite(7136, "Skill/Construction");
		addSprite(7137, "Skill/HP");
		addSprite(7138, "Skill/Agility");
		addSprite(7139, "Skill/Herblore");
		addSprite(7140, "Skill/Thief");
		addSprite(7141, "Skill/Craft");
		addSprite(7142, "Skill/Fletch");
		addSprite(7143, "Skill/Slay");
		addSprite(7144, "Skill/Hunter");
		addSprite(7145, "Skill/Mine");
		addSprite(7146, "Skill/Smith");
		addSprite(7147, "Skill/Fish");
		addSprite(7148, "Skill/Cook");
		addSprite(7149, "Skill/Fire");
		addSprite(7150, "Skill/Wood");
		addSprite(7151, "Skill/Farm");
		addSprite(7152, "Skill/Summon");
		addText(7157, "99", 0xCC3300, false, true, -1, TDA, 0);//dung
		addText(7158, "99", 0xCC3300, false, true, -1, TDA, 0);//dung
		addText(7159, "99", 0xCC3300, false, true, -1, TDA, 0);//attack
		addText(7160, "99", 0xCC3300, false, true, -1, TDA, 0);//attack
		addText(7161, "99", 0xCC3300, false, true, -1, TDA, 0);//str
		addText(7162, "99", 0xCC3300, false, true, -1, TDA, 0);//str
		addText(7163, "99", 0xCC3300, false, true, -1, TDA, 0);//def
		addText(7164, "99", 0xCC3300, false, true, -1, TDA, 0);//def
		addText(7165, "99", 0xCC3300, false, true, -1, TDA, 0);//range
		addText(7166, "99", 0xCC3300, false, true, -1, TDA, 0);//range
		addText(7167, "99", 0xCC3300, false, true, -1, TDA, 0);//prayer
		addText(7168, "99", 0xCC3300, false, true, -1, TDA, 0);//prayer
		addText(7169, "99", 0xCC3300, false, true, -1, TDA, 0);//mage
		addText(7170, "99", 0xCC3300, false, true, -1, TDA, 0);//rcin
		addText(7171, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7172, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7173, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7174, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7175, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7176, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7177, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7178, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7179, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7180, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7181, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7182, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7183, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7184, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7185, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7186, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7187, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7188, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7189, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7190, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7191, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7192, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7193, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7194, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7195, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7196, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7197, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7198, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7199, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7200, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7201, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7202, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7203, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7204, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7205, "99", 0xCC3300, false, true, -1, TDA, 0);
		addText(7206, "99", 0xCC3300, false, true, -1, TDA, 0);
		sT.totalChildren(126);
		/*Buttons*/
		sT.child(0, 7102, 3, 5);
		sT.child(1, 7103, 66, 5);
		sT.child(2, 7104, 128, 5);
		sT.child(3, 7105, 3, 33);
		sT.child(4, 7106, 66, 33);
		sT.child(5, 7107, 128, 33);
		sT.child(6, 7108, 3, 61);
		sT.child(7, 7109, 66, 61);
		sT.child(8, 7110, 128, 61);
		sT.child(9, 7111, 3, 89);
		sT.child(10, 7112, 66, 89);
		sT.child(11, 7113, 128, 89);
		sT.child(12, 7114, 3, 117);
		sT.child(13, 7115, 66, 117);
		sT.child(14, 7116, 128, 117);
		sT.child(15, 7117, 3, 145);
		sT.child(16, 7118, 66, 145);
		sT.child(17, 7119, 128, 145);
		sT.child(18, 7120, 3, 173);
		sT.child(19, 7121, 66, 173);
		sT.child(20, 7122, 128, 173);
		sT.child(21, 7123, 3, 201);
		sT.child(22, 7124, 66, 201);
		sT.child(23, 7125, 128, 201);
		sT.child(24, 7126, 3, 229);
		/*Total Level Text*/
		sT.child(25, 7127, 70, 235);
		/*Icons*/
		sT.child(26, 7129, 3, 5);
		sT.child(27, 7137, 66, 5);
		sT.child(28, 7145, 128, 5);

		sT.child(29, 7130, 3, 33);
		sT.child(30, 7138, 66, 33);
		sT.child(31, 7146, 128, 33);

		sT.child(32, 7131, 3, 61);
		sT.child(33, 7139, 66, 61);
		sT.child(34, 7147, 128, 61);

		sT.child(35, 7132, 3, 89);
		sT.child(36, 7140, 66, 89);
		sT.child(37, 7148, 128, 89);

		sT.child(38, 7133, 3, 117);
		sT.child(39, 7141, 66, 117);
		sT.child(40, 7149, 128, 117);

		sT.child(41, 7134, 3, 145);
		sT.child(42, 7142, 66, 145);
		sT.child(43, 7150, 128, 145);

		sT.child(44, 7135, 3, 173);
		sT.child(45, 7143, 66, 173);
		sT.child(46, 7151, 128, 173);

		sT.child(47, 7136, 3, 201);
		sT.child(48, 7144, 66, 201);
		sT.child(49, 7152, 128, 201);

		sT.child(50, 7128, 3, 229);
		/*##/## Text*/
		sT.child(51, 7157, 32, 231);
		sT.child(52, 7158, 45, 242);
		sT.child(53, 7159, 32, 7);
		sT.child(54, 7160, 45, 18);
		sT.child(55, 7161, 32, 35);
		sT.child(56, 7162, 45, 46);
		sT.child(57, 7163, 32, 63);
		sT.child(58, 7164, 45, 74);
		sT.child(59, 7165, 32, 91);
		sT.child(60, 7166, 45, 102);
		sT.child(61, 7167, 32, 119);
		sT.child(62, 7168, 45, 130);
		sT.child(63, 7169, 32, 147);
		sT.child(64, 7170, 45, 158);
		sT.child(65, 7171, 32, 175);
		sT.child(66, 7172, 45, 186);
		sT.child(67, 7173, 32, 203);
		sT.child(68, 7174, 45, 214);
		sT.child(69, 7175, 95, 7);
		sT.child(70, 7176, 108, 18);
		sT.child(71, 7177, 95, 35);
		sT.child(72, 7178, 108, 46);
		sT.child(73, 7179, 95, 63);
		sT.child(74, 7180, 108, 74);
		sT.child(75, 7181, 95, 91);
		sT.child(76, 7182, 108, 102);
		sT.child(77, 7183, 95, 119);
		sT.child(78, 7184, 108, 130);
		sT.child(79, 7185, 95, 147);
		sT.child(80, 7186, 108, 158);
		sT.child(81, 7187, 95, 175);
		sT.child(82, 7188, 108, 186);
		sT.child(83, 7189, 95, 203);
		sT.child(84, 7190, 108, 214);
		sT.child(85, 7191, 158, 7);
		sT.child(86, 7192, 171, 18);
		sT.child(87, 7193, 158, 35);
		sT.child(88, 7194, 171, 46);
		sT.child(89, 7195, 158, 63);
		sT.child(90, 7196, 171, 74);
		sT.child(91, 7197, 158, 91);
		sT.child(92, 7198, 171, 102);
		sT.child(93, 7199, 158, 119);
		sT.child(94, 7200, 171, 130);
		sT.child(95, 7201, 158, 147);
		sT.child(96, 7202, 171, 158);
		sT.child(97, 7203, 158, 175);
		sT.child(98, 7204, 171, 186);
		sT.child(99, 7205, 158, 203);
		sT.child(100, 7206, 171, 214);
		/*Hovers*/
		sT.child(101, 9215, 3, 5);
		sT.child(102, 9216, 66, 5);
		sT.child(103, 9217, 128, 5);
		sT.child(104, 9218, 3, 33);
		sT.child(105, 9219, 66, 33);
		sT.child(106, 9220, 128, 33);
		sT.child(107, 9221, 3, 61);
		sT.child(108, 9222, 66, 61);
		sT.child(109, 9223, 128, 61);
		sT.child(110, 9224, 3, 89);
		sT.child(111, 9225, 66, 89);
		sT.child(112, 9226, 128, 89);
		sT.child(113, 9227, 3, 117);
		sT.child(114, 9228, 66, 117);
		sT.child(115, 9229, 128, 117);
		sT.child(116, 9230, 3, 145);
		sT.child(117, 9231, 66, 145);
		sT.child(118, 9232, 128, 145);
		sT.child(119, 9233, 3, 173);
		sT.child(120, 9234, 66, 173);
		sT.child(121, 9235, 128, 173);
		sT.child(122, 9236, 3, 201);
		sT.child(123, 9237, 66, 201);
		sT.child(124, 9238, 128, 201);
		sT.child(125, 9239, 3, 229);
    }

	  @SuppressWarnings("unused")
	public static Sprite getSprite(String s)
    {
        Sprite sprite = new Sprite(s);
        Exception exception;
        if(sprite != null)
            return sprite;
        else
            return sprite;
    }
    public void children(int i)
    {
        children = new int[i];
        childX = new int[i];
        childY = new int[i];
    }

    public static void createSkillHover(int i, int j)
    {
        RSInterface rsinterface = addInterface(i);
        rsinterface.interfaceType = 10;
        rsinterface.contentType = j;
        rsinterface.disabledMessage = null;
        rsinterface.width = 60;
        rsinterface.height = 28;
    }

    public static void addSkillText(int i, boolean flag, int j)
    {
        RSInterface rsinterface = addInterface(i);
        rsinterface.id = i;
        rsinterface.parentID = i;
        rsinterface.interfaceType = 4;
        rsinterface.atActionType = 0;
        rsinterface.width = 15;
        rsinterface.height = 12;
        rsinterface.rsFonts = RSInterface.fonts[0];
        rsinterface.textShadowed = true;
        rsinterface.textCentered = true;
        rsinterface.disabledColor = 0xCC3300;
        if(!flag)
        {
            rsinterface.valueIndexArray = new int[1][];
            rsinterface.valueIndexArray[0] = new int[3];
            rsinterface.valueIndexArray[0][0] = 1;
            rsinterface.valueIndexArray[0][1] = j;
            rsinterface.valueIndexArray[0][2] = 0;
        } else
        {
            rsinterface.valueIndexArray = new int[2][];
            rsinterface.valueIndexArray[0] = new int[3];
            rsinterface.valueIndexArray[0][0] = 1;
            rsinterface.valueIndexArray[0][1] = j;
            rsinterface.valueIndexArray[0][2] = 0;
            rsinterface.valueIndexArray[1] = new int[1];
            rsinterface.valueIndexArray[1][0] = 0;
        }
        rsinterface.disabledMessage = "%1";
    }

    public static void addSkillButton(int i)
    {
        RSInterface rsinterface = addInterface(i);
        rsinterface.interfaceType = 5;
        rsinterface.atActionType = 5;
        rsinterface.contentType = 0;
        rsinterface.width = 60;
        rsinterface.height = 27;
        rsinterface.disabledSprite = getSprite("Button");
        rsinterface.tooltip = "Quick Chat";
    }
	public static void friendsTab(RSFont[] tda) {
        RSInterface tab = addTabInterface(5065);
        RSInterface list = interfaceCache[5066];
        addText(5067, "Friends List", tda, 1, 0xff9933, true, true);
        addText(5070, "Add Friend", tda, 0, 0xff9933, false, true);
        addText(5071, "Delete Friend", tda, 0, 0xff9933, false, true);
        addSprite(16126, 4, "Interfaces/Friends/SPRITE");
        addSprite(16127, 8, "Interfaces/Friends/SPRITE");
        addHoverButton(5068, "Interfaces/Friends/SPRITE", 6, 72, 32, "Add Friend", 201, 5072, 1);
        addHoveredButton(5072, "Interfaces/Friends/SPRITE", 7, 72, 32, 5073);
        addHoverButton(5069, "Interfaces/Friends/SPRITE", 6, 72, 32, "Delete Friend", 202, 5074, 1);
        addHoveredButton(5074, "Interfaces/Friends/SPRITE", 7, 72, 32, 5075);
        tab.totalChildren(11);
        tab.child(0, 5067, 95, 4);
        tab.child(1, 16127, 0, 25);
        tab.child(2, 16126, 0, 221);
        tab.child(3, 5066, 0, 24);
        tab.child(4, 16126, 0, 22);
        tab.child(5, 5068, 15, 226);
        tab.child(6, 5072, 15, 226);
        tab.child(7, 5069, 103, 226);
        tab.child(8, 5074, 103, 226);
        tab.child(9, 5070, 25, 237);
        tab.child(10, 5071, 106, 237);
        list.height = 196; list.width = 174;
        for(int id = 5092, i = 0; id <= 5191 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 3; list.childY[i] = list.childY[i] - 7;
        } for(int id = 5192, i = 100; id <= 5291 && i <= 199; id++, i++) {
            list.children[i] = id; list.childX[i] = 131; list.childY[i] = list.childY[i] - 7;
        }
    }
	public static void ignoreTab(RSFont[] tda) {
        RSInterface tab = addTabInterface(5715);
        RSInterface list = interfaceCache[5716];
        addText(5717, "Ignore List", tda, 1, 0xff9933, true, true);
        addText(5720, "Add Name", tda, 0, 0xff9933, false, true);
        addText(5721, "Delete Name", tda, 0, 0xff9933, false, true);
        addHoverButton(5718, "Interfaces/Friends/SPRITE", 6, 72, 32, "Add Name", 501, 5722, 1);
        addHoveredButton(5722, "Interfaces/Friends/SPRITE", 7, 72, 32, 5723);
        addHoverButton(5719, "Interfaces/Friends/SPRITE", 6, 72, 32, "Delete Name", 502, 5724, 1);
        addHoveredButton(5724, "Interfaces/Friends/SPRITE", 7, 72, 32, 5725);
        tab.totalChildren(11);
        tab.child(0, 5717, 95, 4);
        tab.child(1, 16127, 0, 25);
        tab.child(2, 16126, 0, 221);
        tab.child(3, 5716, 0, 24);
        tab.child(4, 16126, 0, 22);
        tab.child(5, 5718, 15, 226);
        tab.child(6, 5722, 15, 226);
        tab.child(7, 5719, 103, 226);
        tab.child(8, 5724, 103, 226);
        tab.child(9, 5720, 27, 237);
        tab.child(10, 5721, 108, 237);
        list.height = 196;
        list.width = 174;
        for(int id = 5742, i = 0; id <= 5841 && i <= 99; id++, i++) {
            list.children[i] = id; list.childX[i] = 3; list.childY[i] = list.childY[i] - 7;
        }
    }

	/*
	public static void FriendList(RSFont[] TDA) {
		RSInterface Interface = addInterface(5065);
		addSprite(5072, 6, "Interfaces/Clan Chat/LIST");
		addHover(5068, 1, 201, 5073, 8, "Interfaces/Clan Chat/LIST", 75, 32, "Add Friend");
		addHovered(5073, 2, "Interfaces/Clan Chat/LIST", 75, 32, 5074);
		addHover(5069, 1, 202, 5075, 8, "Interfaces/Clan Chat/LIST", 75, 32, "Delete Friend");
		addHovered(5075, 2, "Interfaces/Clan Chat/LIST", 75, 32, 5076);
		addText(5067, "Friend List", 0xff9933, false, true, 52,TDA, 2);
		addText(5070, " Add Friend", 0xff9933, false, true, 52,TDA, 0);
		addText(5071, " Del Friend", 0xff9933, false, true, 52,TDA, 0);
		setChildren(7, Interface);
		setBounds(5072, 4, 20, 0, Interface);
		setBounds(5068, 15, 226, 1, Interface);
		setBounds(5069, 105, 226, 2, Interface);
		setBounds(5066, 4, 24, 3, Interface);
		setBounds(5067, 55, 4, 4, Interface);
		setBounds(5070, 21, 236, 5, Interface);
		setBounds(5071, 112, 236, 6, Interface);
		Interface = Interface.interfaceCache[5066];
		Interface.height = 194;		
		Interface.width = 167;
		Interface.newScroller = false;
	}
	
	
	public static void IgnoreList(RSFont[] TDA) {
		RSInterface Interface = addInterface(5715);
		addSprite(5726, 6, "Interfaces/Clan Chat/LIST");
		addHover(5718, 1, 501, 5722, 7, "Interfaces/Clan Chat/LIST", 75, 32, "Add Name");
		addHovered(5722, 2, "Interfaces/Clan Chat/LIST", 75, 32, 5723);
		
		addHover(5719, 1, 502, 5724, 7, "Interfaces/Clan Chat/LIST", 75, 32, "Delete Name");
		addHovered(5724, 2, "Interfaces/Clan Chat/LIST", 75, 32, 5725);
		addText(5717, "Ignore List", 0xff9933, false, true, 52,TDA, 2);
		addText(5720, "Add Name", 0xff9933, false, true, 52,TDA, 0);
		addText(5721, "Del Name", 0xff9933, false, true, 52,TDA, 0);
		setChildren(7, Interface);
		setBounds(5726, 4, 20, 0, Interface);
		setBounds(5718, 15, 226, 1, Interface);
		setBounds(5719, 105, 226, 2, Interface);
		setBounds(5716, 4, 24, 3, Interface);
		setBounds(5717, 55, 4, 4, Interface);
		setBounds(5720, 23, 236, 5, Interface);
		setBounds(5721, 115, 236, 6, Interface);
		Interface = Interface.interfaceCache[5716];
		Interface.height = 194;		
		Interface.width = 167;
		Interface.newScroller = false;
	}
	*/
	
	public static void configureLunar(RSFont[] TDA) {
		homeTeleport();
		drawRune(30003, 1, "Fire");
		drawRune(30004, 2, "Water");
		drawRune(30005, 3, "Air");
		drawRune(30006, 4, "Earth");
		drawRune(30007, 5, "Mind");
		drawRune(30008, 6, "Body");
		drawRune(30009, 7, "Death");
		drawRune(30010, 8, "Nature");
		drawRune(30011, 9, "Chaos");
		drawRune(30012, 10, "Law");
		drawRune(30013, 11, "Cosmic");
		drawRune(30014, 12, "Blood");
		drawRune(30015, 13, "Soul");
		drawRune(30016, 14, "Astral");
		addLunar3RunesSmallBox(30017, 9075, 554, 555, 0, 4, 3, 30003, 30004, 64, 
			"Bake Pie",
			"Bake pies without a stove",
			TDA, 0, 16, 2);
		addLunar2RunesSmallBox(30025, 9075, 557, 0, 7, 30006, 65,
			"Cure Plant",
			"Cure disease on farming patch",
			TDA, 1, 4, 2);
		addLunar3RunesBigBox(30032, 9075, 564, 558, 0, 0, 0, 30013, 30007, 65,
			"Monster Examine",
			"Detect the combat statistics of a\\nmonster",
			TDA, 2, 2, 2);
		addLunar3RunesSmallBox(30040, 9075, 564, 556, 0, 0, 1, 30013, 30005, 66,
			"NPC Contact",
			"Speak with varied NPCs",
			TDA, 3, 0, 2);
		addLunar3RunesSmallBox(30048, 9075, 563, 557, 0, 0, 9, 30012, 30006, 67,
			"Cure Other",
			"Cure poisoned players",
			TDA, 4, 8, 2);
		addLunar3RunesSmallBox(30056, 9075, 555, 554, 0, 2, 0, 30004, 30003, 67,
			"Humidify",
			"Fills certain vessels with water",
			TDA, 5, 0, 5);
		addLunar3RunesSmallBox(30064, 9075, 563, 557, 1, 0, 1, 30012, 30006, 68,
			"Monster tele",
			"A varius selection of monster teleports",
			TDA, 6, 0, 5);
		addLunar3RunesBigBox(30075, 9075, 563, 557, 1, 0, 3, 30012, 30006, 69,
			"Minigame's Tele",
			"A varius selection of minigame teleports",
			TDA, 7, 0, 5);
		addLunar3RunesSmallBox(30083, 9075, 563, 557, 1, 0, 5, 30012, 30006, 70,
			"Boss's",
			"Kill them all!",
			TDA, 8, 0, 5);
		addLunar3RunesSmallBox(30091, 9075, 564, 563, 1, 1, 0, 30013, 30012, 70,
			"Cure Me",
			"Cures Poison",
			TDA, 9, 0, 5);
		addLunar2RunesSmallBox(30099, 9075, 557, 1, 1, 30006, 70,
			"Hunter Kit",
			"Get a kit of hunting gear",
			TDA, 10, 0, 5);
		addLunar3RunesSmallBox(30106, 9075, 563, 555, 1, 0, 0, 30012, 30004, 71,
			"Pk'ing",
			"Kill them all!",
			TDA, 11, 0, 5);
		addLunar3RunesBigBox(30114, 9075, 563, 555, 1, 0, 4, 30012, 30004, 72,
			"Skilling",
			"Place to skill",
			TDA, 12, 0, 5);
		addLunar3RunesSmallBox(30122, 9075, 564, 563, 1, 1, 1, 30013, 30012, 73,
			"Cure Group",
			"Cures Poison on players",
			TDA, 13, 0, 5);
		addLunar3RunesBigBox(30130, 9075, 564, 559, 1, 1, 4, 30013, 30008, 74,
			"Stat Spy",
			"Cast on another player to see their\\nskill levels",
			TDA, 14, 8, 2);
		addLunar3RunesBigBox(30138, 9075, 563, 554, 1, 1, 2, 30012, 30003, 74,
			"Advanced Bosses",
			"Bosses that only the mightest can kill!",
			TDA, 15, 0, 5);
		addLunar3RunesBigBox(30146, 9075, 563, 554, 1, 1, 5, 30012, 30003, 75,
			"Tele Group Barbarian",
			"Teleports players to the Barbarian\\noutpost",
			TDA, 16, 0, 5);
		addLunar3RunesSmallBox(30154, 9075, 554, 556, 1, 5, 9, 30003, 30005, 76,
			"Superglass Make",
			"Make glass without a furnace",
			TDA, 17, 16, 2);
		addLunar3RunesSmallBox(30162, 9075, 563, 555, 1, 1, 3, 30012, 30004, 77,
			"Khazard Teleport",
			"Teleports you to Port khazard",
			TDA, 18, 0, 5);
		addLunar3RunesSmallBox(30170, 9075, 563, 555, 1, 1, 7, 30012, 30004, 78,
			"Tele Group Khazard",
			"Teleports players to Port khazard",
			TDA, 19, 0, 5);
		addLunar3RunesBigBox(30178, 9075, 564, 559, 1, 0, 4, 30013, 30008, 78,
			"Dream",
			"Take a rest and restore hitpoints 3\\n times faster",
			TDA, 20, 0, 5);
		addLunar3RunesSmallBox(30186, 9075, 557, 555, 1, 9, 4, 30006, 30004, 79,
			"String Jewellery",
			"String amulets without wool",
			TDA, 21, 0, 5);
		addLunar3RunesLargeBox(30194, 9075, 557, 555, 1, 9, 9, 30006, 30004, 80,
			"Stat Restore Pot\\nShare",
			"Share a potion with up to 4 nearby\\nplayers",
			TDA, 22, 0, 5);
		addLunar3RunesSmallBox(30202, 9075, 554, 555, 1, 6, 6, 30003, 30004, 81,
			"Magic Imbue",
			"Combine runes without a talisman",
			TDA, 23, 0, 5);
		addLunar3RunesBigBox(30210, 9075, 561, 557, 2, 1, 14, 30010, 30006, 82,
			"Fertile Soil",
			"Fertilise a farming patch with super\\ncompost",
			TDA, 24, 4, 2);
		addLunar3RunesBigBox(30218, 9075, 557, 555, 2, 11, 9, 30006, 30004, 83,
			"Boost Potion Share",
			"Shares a potion with up to 4 nearby\\nplayers",
			TDA, 25, 0, 5);
		addLunar3RunesSmallBox(30226, 9075, 563, 555, 2, 2, 9, 30012, 30004, 84,
			"Fishing Guild Teleport",
			"Teleports you to the fishing guild",
			TDA, 26, 0, 5);
		addLunar3RunesLargeBox(30234, 9075, 563, 555, 1, 2, 13, 30012, 30004, 85, 
			"Tele Group Fishing Guild",
			"Teleports players to the Fishing\\nGuild",
			TDA, 27, 0, 5);
		addLunar3RunesSmallBox(30242, 9075, 557, 561, 2, 14, 0, 30006, 30010, 85,
			"Plank Make",
			"Turn Logs into planks",
			TDA, 28, 16, 5);
		addLunar3RunesSmallBox(30250, 9075, 563, 555, 2, 2, 9, 30012, 30004, 86, 
			"Catherby Teleport",
			"Teleports you to Catherby",
			TDA, 29, 0, 5);
		addLunar3RunesSmallBox(30258, 9075, 563, 555, 2, 2, 14, 30012, 30004, 87, 
			"Tele Group Catherby",
			"Teleports players to Catherby",
			TDA, 30, 0, 5);
		addLunar3RunesSmallBox(30266, 9075, 563, 555, 2, 2, 7, 30012, 30004, 88,
			"Ice Plateau Teleport",
			"Teleports you to Ice Plateau",
			TDA, 31, 0, 5);
		addLunar3RunesLargeBox(30274, 9075, 563, 555, 2, 2, 15, 30012, 30004, 89, 
			"Tele Group Ice Plateau",
			"Teleports players to Ice Plateau",
			TDA, 32, 0, 5);
		addLunar3RunesBigBox(30282, 9075, 563, 561, 2, 1, 0, 30012, 30010, 90,
			"Energy Transfer",
			"Spend HP and SA energy to\\n give another SA and run energy",
			TDA, 33, 8, 2);
		addLunar3RunesBigBox(30290, 9075, 563, 565, 2, 2, 0, 30012, 30014, 91,
			"Heal Other",
			"Transfer up to 75% of hitpoints\\n to another player",
			TDA, 34, 8, 2);
		addLunar3RunesBigBox(30298, 9075, 560, 557, 2, 1, 9, 30009, 30006, 92,
			"Vengeance Other",
			"Allows another player to rebound\\ndamage to an opponent",
			TDA, 35, 8, 2);
		addLunar3RunesSmallBox(30306, 9075, 560, 557, 3, 1, 9, 30009, 30006, 93,
			"Vengeance",
			"Rebound damage to an opponent", 
			TDA, 36, 0, 5);
		addLunar3RunesBigBox(30314, 9075, 565, 563, 3, 2, 5, 30014, 30012, 94,
			"Heal Group",
			"Transfer up to 75% of hitpoints\\n to a group",
			TDA, 37, 0, 5);
		addLunar3RunesBigBox(30322, 9075, 564, 563, 2, 1, 0, 30013, 30012, 95,
			"Spellbook Swap",
			"Change to another spellbook for 1\\nspell cast", 
			TDA, 38, 0, 5);
	}
	public static void constructLunar() {
		RSInterface Interface = addTabInterface(16640);
		setChildren(80, Interface);
		setBounds(30000, 11, 10, 0, Interface);
		setBounds(30017, 40, 9, 1, Interface);
		setBounds(30025, 71, 12, 2, Interface);
		setBounds(30032, 103, 10, 3, Interface);
		setBounds(30040, 135, 12, 4, Interface);
		setBounds(30048, 165, 10, 5, Interface);
		setBounds(30056, 8, 38, 6, Interface);
		setBounds(30064, 39, 39, 7, Interface);
		setBounds(30075, 71, 39, 8, Interface);
		setBounds(30083, 103, 39, 9, Interface);
		setBounds(30091, 135, 39, 10, Interface);
		setBounds(30099, 165, 37, 11, Interface);
		setBounds(30106, 12, 68, 12, Interface);
		setBounds(30114, 42, 68, 13, Interface);
		setBounds(30122, 71, 68, 14, Interface);
		setBounds(30130, 103, 68, 15, Interface);
		setBounds(30138, 135, 68, 16, Interface);
		setBounds(30146, 165, 68, 17, Interface);
		setBounds(30154, 14, 97, 18, Interface);
		setBounds(30162, 42, 97, 19, Interface);
		setBounds(30170, 71, 97, 20, Interface);
		setBounds(30178, 101, 97, 21, Interface);
		setBounds(30186, 135, 98, 22, Interface);
		setBounds(30194, 168, 98, 23, Interface);
		setBounds(30202, 11, 125, 24, Interface);
		setBounds(30210, 42, 124, 25, Interface);
		setBounds(30218, 74, 125, 26, Interface);
		setBounds(30226, 103, 125, 27, Interface);
		setBounds(30234, 135, 125, 28, Interface);
		setBounds(30242, 164, 126, 29, Interface);
		setBounds(30250, 10, 155, 30, Interface);
		setBounds(30258, 42, 155, 31, Interface);
		setBounds(30266, 71, 155, 32, Interface);
		setBounds(30274, 103, 155, 33, Interface);
		setBounds(30282, 136, 155, 34, Interface);
		setBounds(30290, 165, 155, 35, Interface);
		setBounds(30298, 13, 185, 36, Interface);
		setBounds(30306, 42, 185, 37, Interface);
		setBounds(30314, 71, 184, 38, Interface);
		setBounds(30322, 104, 184, 39, Interface);
		setBounds(30001, 6, 184, 40, Interface);// hover
		setBounds(30018, 5, 176, 41, Interface);// hover
		setBounds(30026, 5, 176, 42, Interface);// hover
		setBounds(30033, 5, 163, 43, Interface);// hover
		setBounds(30041, 5, 176, 44, Interface);// hover
		setBounds(30049, 5, 176, 45, Interface);// hover
		setBounds(30057, 5, 176, 46, Interface);// hover
		setBounds(30065, 5, 176, 47, Interface);// hover
		setBounds(30076, 5, 163, 48, Interface);// hover
		setBounds(30084, 5, 176, 49, Interface);// hover
		setBounds(30092, 5, 176, 50, Interface);// hover
		setBounds(30100, 5, 176, 51, Interface);// hover
		setBounds(30107, 5, 176, 52, Interface);// hover
		setBounds(30115, 5, 163, 53, Interface);// hover
		setBounds(30123, 5, 176, 54, Interface);// hover
		setBounds(30131, 5, 163, 55, Interface);// hover
		setBounds(30139, 5, 163, 56, Interface);// hover
		setBounds(30147, 5, 163, 57, Interface);// hover
		setBounds(30155, 5, 176, 58, Interface);// hover
		setBounds(30163, 5, 176, 59, Interface);// hover
		setBounds(30171, 5, 176, 60, Interface);// hover
		setBounds(30179, 5, 163, 61, Interface);// hover
		setBounds(30187, 5, 176, 62, Interface);// hover
		setBounds(30195, 5, 149, 63, Interface);// hover
		setBounds(30203, 5, 176, 64, Interface);// hover
		setBounds(30211, 5, 163, 65, Interface);// hover
		setBounds(30219, 5, 163, 66, Interface);// hover
		setBounds(30227, 5, 176, 67, Interface);// hover
		setBounds(30235, 5, 149, 68, Interface);// hover
		setBounds(30243, 5, 176, 69, Interface);// hover
		setBounds(30251, 5, 5, 70, Interface);// hover
		setBounds(30259, 5, 5, 71, Interface);// hover
		setBounds(30267, 5, 5, 72, Interface);// hover
		setBounds(30275, 5, 5, 73, Interface);// hover
		setBounds(30283, 5, 5, 74, Interface);// hover
		setBounds(30291, 5, 5, 75, Interface);// hover
		setBounds(30299, 5, 5, 76, Interface);// hover
		setBounds(30307, 5, 5, 77, Interface);// hover
		setBounds(30323, 5, 5, 78, Interface);// hover
		setBounds(30315, 5, 5, 79, Interface);// hover
	}
	public static void configureBank(RSFont[] TDA) {
		RSInterface Tab = addTabInterface(23000);
		addSprite(23002, 0, "Interfaces/Bank/BANK_0");
		addText(23003, "", TDA, 0, 0xFFB000);
		addHover(23004, 3, 0, 23005, 3, "Interfaces/Equipment/CHAR", 21, 21, "Close");
		addHovered(23005, 2, "Interfaces/Equipment/CHAR", 21, 21, 23006);
		addHover(23007, 4, 0, 23008, 5, "Interfaces/Bank/BANK", 35, 25, "Deposit carried items");
		addHovered(23008, 5, "Interfaces/Bank/BANK", 35, 25, 23009);
		//addText(19999, "Combat Level: ", TDA, 0, 0xFF981F);
		Tab.children = new int[7];
		Tab.childX = new int[7];
		Tab.childY = new int[7];
		Tab.children[0] = 5292;
		Tab.childX[0] = 0;
		Tab.childY[0] = 0;
		Tab.children[1] = 23003;
		Tab.childX[1] = 410;
		Tab.childY[1] = 30;
		Tab.children[2] = 23004;
		Tab.childX[2] = 472;
		Tab.childY[2] = 27;
		Tab.children[3] = 23005;
		Tab.childX[3] = 472;
		Tab.childY[3] = 27;
		Tab.children[4] = 23007;
		Tab.childX[4] = 450;
		Tab.childY[4] = 288;
		Tab.children[5] = 23008;
		Tab.childX[5] = 450;
		Tab.childY[5] = 288;
		
		Tab.children[6] = 23008;
		Tab.childX[6] = 3000;
		Tab.childY[6] = 5000;
		RSInterface rsi = interfaceCache[5292];
		addText(5384, "", TDA, 0, 0xFFB000);//cheap hax
		rsi.childX[90] = 410;
		rsi.childY[90] = 288;
	}

	public static void configureCharDesign(RSFont[] TDA) {
		RSInterface rsInterface = interfaceCache[3559] = new RSInterface();
		rsInterface.id = 3559;
		rsInterface.parentID = 3559;
		rsInterface.interfaceType = 0;
		rsInterface.atActionType = 0;
		rsInterface.contentType = 0;
		rsInterface.width = 512;
		rsInterface.height = 334;
		rsInterface.opacity = 0;
		rsInterface.hoverType = -1;
		rsInterface.scrollMax = 0;
		rsInterface.aBoolean266 = false;
		//addText(int id, String Text, tda, int FontSize, int color);
		addText(3649, "Welcome to Hackerscape - Use the buttons below to design your character", TDA, 2, 0xFF9442);
		addText(3658, "Torso", TDA, 1, 0xFF9442);
		addText(3673, "Arms", TDA, 1, 0xFF9442);
		addText(3674, "Legs", TDA, 1, 0xFF9442);
		addText(3675, "Head", TDA, 1, 0xFF9442);
		addText(3676, "Hands", TDA, 1, 0xFF9442);
		addText(3677, "Feet", TDA, 1, 0xFF9442);
		addText(3678, "Jaw", TDA, 1, 0xFF9442);
		addText(3690, "Hair", TDA, 1, 0xFF9442);
		addText(3691, "Torso", TDA, 1, 0xFF9442);
		addText(3692, "Legs", TDA, 1, 0xFF9442);
		addText(3693, "Skin", TDA, 1, 0xFF9442);
		addText(3694, "Feet", TDA, 1, 0xFF9442);
		addText(3700, "Male", TDA, 1, 0xFF9442);
		addText(3701, "Female", TDA, 1, 0xFF9442);
		/*AddInterfaceButton(int id, int sid, String spriteName, String tooltip); */
		/*AddInterfaceButton(3659, 0, "Interfaces/CharDesign/Arrow", "Change head");
		AddInterfaceButton(3660, 0, "Interfaces/CharDesign/Arrow", "Change jaw");
		AddInterfaceButton(3661, 0, "Interfaces/CharDesign/Arrow", "Change torso");
		AddInterfaceButton(3662, 0, "Interfaces/CharDesign/Arrow", "Change arms");
		AddInterfaceButton(3663, 0, "Interfaces/CharDesign/Arrow", "Change hands");
		AddInterfaceButton(3664, 0, "Interfaces/CharDesign/Arrow", "Change legs");
		AddInterfaceButton(3665, 0, "Interfaces/CharDesign/Arrow", "Change feet");
		AddInterfaceButton(3666, 1, "Interfaces/CharDesign/Arrow", "Change head");
		AddInterfaceButton(3667, 1, "Interfaces/CharDesign/Arrow", "Change jaw");
		AddInterfaceButton(3668, 1, "Interfaces/CharDesign/Arrow", "Change torso");
		AddInterfaceButton(3669, 1, "Interfaces/CharDesign/Arrow", "Change arms");
		AddInterfaceButton(3670, 1, "Interfaces/CharDesign/Arrow", "Change hands");
		AddInterfaceButton(3671, 1, "Interfaces/CharDesign/Arrow", "Change legs");
		AddInterfaceButton(3672, 1, "Interfaces/CharDesign/Arrow", "Change feet");
		AddInterfaceButton(3657, 0, "Interfaces/CharDesign/Arrow", "Recolour hair");
		AddInterfaceButton(3681, 0, "Interfaces/CharDesign/Arrow", "Recolour torso");
		AddInterfaceButton(3680, 0, "Interfaces/CharDesign/Arrow", "Recolour legs");
		AddInterfaceButton(3682, 0, "Interfaces/CharDesign/Arrow", "Recolour feet");
		AddInterfaceButton(3683, 0, "Interfaces/CharDesign/Arrow", "Recolour skin");
		AddInterfaceButton(3679, 1, "Interfaces/CharDesign/Arrow", "Recolour hair");
		AddInterfaceButton(3685, 1, "Interfaces/CharDesign/Arrow", "Recolour torso");
		AddInterfaceButton(3687, 1, "Interfaces/CharDesign/Arrow", "Recolour legs");
		AddInterfaceButton(3688, 1, "Interfaces/CharDesign/Arrow", "Recolour feet");
		AddInterfaceButton(3689, 1, "Interfaces/CharDesign/Arrow", "Recolour skin");*/
		rsInterface.children = new int[142];	rsInterface.childX = new int[142];	rsInterface.childY = new int[142];
		rsInterface.children[0] = 3560;			rsInterface.childX[0] = 12;			rsInterface.childY[0] = 20;
		rsInterface.children[1] = 3561;			rsInterface.childX[1] = 12;			rsInterface.childY[1] = 80;
		rsInterface.children[2] = 3562;			rsInterface.childX[2] = 12;			rsInterface.childY[2] = 140;
		rsInterface.children[3] = 3563;			rsInterface.childX[3] = 12;			rsInterface.childY[3] = 200;
		rsInterface.children[4] = 3564;			rsInterface.childX[4] = 12;			rsInterface.childY[4] = 260;
		rsInterface.children[5] = 3565;			rsInterface.childX[5] = 100;		rsInterface.childY[5] = 20;
		rsInterface.children[6] = 3566;			rsInterface.childX[6] = 100;		rsInterface.childY[6] = 80;
		rsInterface.children[7] = 3567;			rsInterface.childX[7] = 100;		rsInterface.childY[7] = 140;
		rsInterface.children[8] = 3568;			rsInterface.childX[8] = 100;		rsInterface.childY[8] = 200;
		rsInterface.children[9] = 3569;			rsInterface.childX[9] = 188;		rsInterface.childY[9] = 20;
		rsInterface.children[10] = 3570;		rsInterface.childX[10] = 100;		rsInterface.childY[10] = 260;
		rsInterface.children[11] = 3571;		rsInterface.childX[11] = 188;		rsInterface.childY[11] = 80;
		rsInterface.children[12] = 3572;		rsInterface.childX[12] = 188;		rsInterface.childY[12] = 140;
		rsInterface.children[13] = 3573;		rsInterface.childX[13] = 188;		rsInterface.childY[13] = 200;
		rsInterface.children[14] = 3574;		rsInterface.childX[14] = 188;		rsInterface.childY[14] = 260;
		rsInterface.children[15] = 3575;		rsInterface.childX[15] = 276;		rsInterface.childY[15] = 20;
		rsInterface.children[16] = 3576;		rsInterface.childX[16] = 364;		rsInterface.childY[16] = 80;
		rsInterface.children[17] = 3577;		rsInterface.childX[17] = 276;		rsInterface.childY[17] = 80;
		rsInterface.children[18] = 3578;		rsInterface.childX[18] = 276;		rsInterface.childY[18] = 140;
		rsInterface.children[19] = 3579;		rsInterface.childX[19] = 276;		rsInterface.childY[19] = 200;
		rsInterface.children[20] = 3580;		rsInterface.childX[20] = 276;		rsInterface.childY[20] = 260;
		rsInterface.children[21] = 3581;		rsInterface.childX[21] = 364;		rsInterface.childY[21] = 140;
		rsInterface.children[22] = 3582;		rsInterface.childX[22] = 364;		rsInterface.childY[22] = 20;
		rsInterface.children[23] = 3583;		rsInterface.childX[23] = 364;		rsInterface.childY[23] = 200;
		rsInterface.children[24] = 3584;		rsInterface.childX[24] = 364;		rsInterface.childY[24] = 260;
		rsInterface.children[25] = 3585;		rsInterface.childX[25] = 412;		rsInterface.childY[25] = 20;
		rsInterface.children[26] = 3586;		rsInterface.childX[26] = 412;		rsInterface.childY[26] = 80;
		rsInterface.children[27] = 3587;		rsInterface.childX[27] = 412;		rsInterface.childY[27] = 140;
		rsInterface.children[28] = 3588;		rsInterface.childX[28] = 412;		rsInterface.childY[28] = 200;
		rsInterface.children[29] = 3589;		rsInterface.childX[29] = 412;		rsInterface.childY[29] = 260;
		rsInterface.children[30] = 3590;		rsInterface.childX[30] = 12;		rsInterface.childY[30] = 20;
		rsInterface.children[31] = 3591;		rsInterface.childX[31] = -3;		rsInterface.childY[31] = 49;
		rsInterface.children[32] = 3592;		rsInterface.childX[32] = -3;		rsInterface.childY[32] = 85;
		rsInterface.children[33] = 3593;		rsInterface.childX[33] = -3;		rsInterface.childY[33] = 121;
		rsInterface.children[34] = 3594;		rsInterface.childX[34] = -3;		rsInterface.childY[34] = 157;
		rsInterface.children[35] = 3595;		rsInterface.childX[35] = -3;		rsInterface.childY[35] = 193;
		rsInterface.children[36] = 3596;		rsInterface.childX[36] = -3;		rsInterface.childY[36] = 229;
		rsInterface.children[37] = 3597;		rsInterface.childX[37] = -3;		rsInterface.childY[37] = 265;
		rsInterface.children[38] = 3598;		rsInterface.childX[38] = 479;		rsInterface.childY[38] = 50;
		rsInterface.children[39] = 3599;		rsInterface.childX[39] = 479;		rsInterface.childY[39] = 86;
		rsInterface.children[40] = 3600;		rsInterface.childX[40] = 479;		rsInterface.childY[40] = 122;
		rsInterface.children[41] = 3601;		rsInterface.childX[41] = 479;		rsInterface.childY[41] = 158;
		rsInterface.children[42] = 3602;		rsInterface.childX[42] = 479;		rsInterface.childY[42] = 194;
		rsInterface.children[43] = 3603;		rsInterface.childX[43] = 479;		rsInterface.childY[43] = 230;
		rsInterface.children[44] = 3604;		rsInterface.childX[44] = 479;		rsInterface.childY[44] = 266;
		rsInterface.children[45] = 3605;		rsInterface.childX[45] = 73;		rsInterface.childY[45] = 299;
		rsInterface.children[46] = 3606;		rsInterface.childX[46] = 109;		rsInterface.childY[46] = 299;
		rsInterface.children[47] = 3607;		rsInterface.childX[47] = 145;		rsInterface.childY[47] = 299;
		rsInterface.children[48] = 3608;		rsInterface.childX[48] = 181;		rsInterface.childY[48] = 299;
		rsInterface.children[49] = 3609;		rsInterface.childX[49] = 217;		rsInterface.childY[49] = 299;
		rsInterface.children[50] = 3610;		rsInterface.childX[50] = 253;		rsInterface.childY[50] = 299;
		rsInterface.children[51] = 3611;		rsInterface.childX[51] = 289;		rsInterface.childY[51] = 299;
		rsInterface.children[52] = 3612;		rsInterface.childX[52] = 325;		rsInterface.childY[52] = 299;
		rsInterface.children[53] = 3613;		rsInterface.childX[53] = 361;		rsInterface.childY[53] = 299;
		rsInterface.children[54] = 3614;		rsInterface.childX[54] = 397;		rsInterface.childY[54] = 299;
		rsInterface.children[55] = 3615;		rsInterface.childX[55] = 433;		rsInterface.childY[55] = 299;
		rsInterface.children[56] = 3616;		rsInterface.childX[56] = 72;		rsInterface.childY[56] = 5;
		rsInterface.children[57] = 3617;		rsInterface.childX[57] = 108;		rsInterface.childY[57] = 5;
		rsInterface.children[58] = 3618;		rsInterface.childX[58] = 144;		rsInterface.childY[58] = 5;
		rsInterface.children[59] = 3619;		rsInterface.childX[59] = 180;		rsInterface.childY[59] = 5;
		rsInterface.children[60] = 3620;		rsInterface.childX[60] = 216;		rsInterface.childY[60] = 5;
		rsInterface.children[61] = 3621;		rsInterface.childX[61] = 252;		rsInterface.childY[61] = 5;
		rsInterface.children[62] = 3622;		rsInterface.childX[62] = 288;		rsInterface.childY[62] = 5;
		rsInterface.children[63] = 3623;		rsInterface.childX[63] = 324;		rsInterface.childY[63] = 5;
		rsInterface.children[64] = 3624;		rsInterface.childX[64] = 360;		rsInterface.childY[64] = 5;
		rsInterface.children[65] = 3625;		rsInterface.childX[65] = 396;		rsInterface.childY[65] = 5;
		rsInterface.children[66] = 3626;		rsInterface.childX[66] = 432;		rsInterface.childY[66] = 5;
		rsInterface.children[67] = 3627;		rsInterface.childX[67] = 252;		rsInterface.childY[67] = 34;
		rsInterface.children[68] = 3628;		rsInterface.childX[68] = 37;		rsInterface.childY[68] = 5;
		rsInterface.children[69] = 3629;		rsInterface.childX[69] = 37;		rsInterface.childY[69] = 299;
		rsInterface.children[70] = 3630;		rsInterface.childX[70] = 12;		rsInterface.childY[70] = 290;
		rsInterface.children[71] = 3631;		rsInterface.childX[71] = 475;		rsInterface.childY[71] = 20;
		rsInterface.children[72] = 3632;		rsInterface.childX[72] = 439;		rsInterface.childY[72] = 5;
		rsInterface.children[73] = 3633;		rsInterface.childX[73] = 475;		rsInterface.childY[73] = 290;
		rsInterface.children[74] = 3634;		rsInterface.childX[74] = 439;		rsInterface.childY[74] = 299;
		rsInterface.children[75] = 3635;		rsInterface.childX[75] = 288;		rsInterface.childY[75] = 34;
		rsInterface.children[76] = 3636;		rsInterface.childX[76] = 324;		rsInterface.childY[76] = 34;
		rsInterface.children[77] = 3637;		rsInterface.childX[77] = 360;		rsInterface.childY[77] = 34;
		rsInterface.children[78] = 3638;		rsInterface.childX[78] = 396;		rsInterface.childY[78] = 34;
		rsInterface.children[79] = 3639;		rsInterface.childX[79] = 432;		rsInterface.childY[79] = 34;
		rsInterface.children[80] = 3640;		rsInterface.childX[80] = 459;		rsInterface.childY[80] = 34;
		rsInterface.children[81] = 3641;		rsInterface.childX[81] = 216;		rsInterface.childY[81] = 34;
		rsInterface.children[82] = 3642;		rsInterface.childX[82] = 180;		rsInterface.childY[82] = 34;
		rsInterface.children[83] = 3643;		rsInterface.childX[83] = 144;		rsInterface.childY[83] = 34;
		rsInterface.children[84] = 3644;		rsInterface.childX[84] = 108;		rsInterface.childY[84] = 34;
		rsInterface.children[85] = 3645;		rsInterface.childX[85] = 72;		rsInterface.childY[85] = 34;
		rsInterface.children[86] = 3646;		rsInterface.childX[86] = 36;		rsInterface.childY[86] = 34;
		rsInterface.children[87] = 3647;		rsInterface.childX[87] = 17;		rsInterface.childY[87] = 34;
		rsInterface.children[88] = 3648;		rsInterface.childX[88] = 36;		rsInterface.childY[88] = 20;
		rsInterface.children[89] = 3649;		rsInterface.childX[89] = 28;		rsInterface.childY[89] = 29;
		rsInterface.children[90] = 3650;		rsInterface.childX[90] = 190;		rsInterface.childY[90] = 155;
		rsInterface.children[91] = 3651;		rsInterface.childX[91] = 223;		rsInterface.childY[91] = 266;
		rsInterface.children[92] = 3652;		rsInterface.childX[92] = 237;		rsInterface.childY[92] = 274;
		rsInterface.children[93] = 3653;		rsInterface.childX[93] = 217;		rsInterface.childY[93] = 260;
		rsInterface.children[94] = 3654;		rsInterface.childX[94] = 270;		rsInterface.childY[94] = 260;
		rsInterface.children[95] = 3655;		rsInterface.childX[95] = 217;		rsInterface.childY[95] = 273;
		rsInterface.children[96] = 3656;		rsInterface.childX[96] = 270;		rsInterface.childY[96] = 273;
		rsInterface.children[97] = 3657;		rsInterface.childX[97] = 326;		rsInterface.childY[97] = 74;
		rsInterface.children[98] = 3658;		rsInterface.childX[98] = 90;		rsInterface.childY[98] = 151;
		rsInterface.children[99] = 3659;		rsInterface.childX[99] = 27;		rsInterface.childY[99] = 68;
		rsInterface.children[100] = 3660;		rsInterface.childX[100] = 27;		rsInterface.childY[100] = 103;
		rsInterface.children[101] = 3661;		rsInterface.childX[101] = 27;		rsInterface.childY[101] = 138;
		rsInterface.children[102] = 3662;		rsInterface.childX[102] = 27;		rsInterface.childY[102] = 173;
		rsInterface.children[103] = 3663;		rsInterface.childX[103] = 27;		rsInterface.childY[103] = 208;
		rsInterface.children[104] = 3664;		rsInterface.childX[104] = 27;		rsInterface.childY[104] = 243;
		rsInterface.children[105] = 3665;		rsInterface.childX[105] = 27;		rsInterface.childY[105] = 278;
		rsInterface.children[106] = 3666;		rsInterface.childX[106] = 140;		rsInterface.childY[106] = 68;
		rsInterface.children[107] = 3667;		rsInterface.childX[107] = 140;		rsInterface.childY[107] = 103;
		rsInterface.children[108] = 3668;		rsInterface.childX[108] = 140;		rsInterface.childY[108] = 138;
		rsInterface.children[109] = 3669;		rsInterface.childX[109] = 140;		rsInterface.childY[109] = 173;
		rsInterface.children[110] = 3670;		rsInterface.childX[110] = 140;		rsInterface.childY[110] = 208;
		rsInterface.children[111] = 3671;		rsInterface.childX[111] = 140;		rsInterface.childY[111] = 243;
		rsInterface.children[112] = 3672;		rsInterface.childX[112] = 140;		rsInterface.childY[112] = 278;
		rsInterface.children[113] = 3673;		rsInterface.childX[113] = 90;		rsInterface.childY[113] = 184;
		rsInterface.children[114] = 3674;		rsInterface.childX[114] = 91;		rsInterface.childY[114] = 254;
		rsInterface.children[115] = 3675;		rsInterface.childX[115] = 91;		rsInterface.childY[115] = 79;
		rsInterface.children[116] = 3676;		rsInterface.childX[116] = 88;		rsInterface.childY[116] = 219;
		rsInterface.children[117] = 3677;		rsInterface.childX[117] = 91;		rsInterface.childY[117] = 288;
		rsInterface.children[118] = 3678;		rsInterface.childX[118] = 94;		rsInterface.childY[118] = 114;
		rsInterface.children[119] = 3679;		rsInterface.childX[119] = 440;		rsInterface.childY[119] = 74;
		rsInterface.children[120] = 3680;		rsInterface.childX[120] = 326;		rsInterface.childY[120] = 144;
		rsInterface.children[121] = 3681;		rsInterface.childX[121] = 326;		rsInterface.childY[121] = 109;
		rsInterface.children[122] = 3682;		rsInterface.childX[122] = 326;		rsInterface.childY[122] = 179;
		rsInterface.children[123] = 3683;		rsInterface.childX[123] = 326;		rsInterface.childY[123] = 214;
		rsInterface.children[124] = 3684;		rsInterface.childX[124] = 87;		rsInterface.childY[124] = 55;
		rsInterface.children[125] = 3685;		rsInterface.childX[125] = 440;		rsInterface.childY[125] = 109;
		rsInterface.children[126] = 3686;		rsInterface.childX[126] = 391;		rsInterface.childY[126] = 55;
		rsInterface.children[127] = 3687;		rsInterface.childX[127] = 440;		rsInterface.childY[127] = 144;
		rsInterface.children[128] = 3688;		rsInterface.childX[128] = 440;		rsInterface.childY[128] = 179;
		rsInterface.children[129] = 3689;		rsInterface.childX[129] = 440;		rsInterface.childY[129] = 214;
		rsInterface.children[130] = 3690;		rsInterface.childX[130] = 394;		rsInterface.childY[130] = 86;
		rsInterface.children[131] = 3691;		rsInterface.childX[131] = 390;		rsInterface.childY[131] = 121;
		rsInterface.children[132] = 3692;		rsInterface.childX[132] = 392;		rsInterface.childY[132] = 155;
		rsInterface.children[133] = 3693;		rsInterface.childX[133] = 393;		rsInterface.childY[133] = 225;
		rsInterface.children[134] = 3694;		rsInterface.childX[134] = 393;		rsInterface.childY[134] = 191;
		rsInterface.children[135] = 3695;		rsInterface.childX[135] = 235;		rsInterface.childY[135] = 245;
		rsInterface.children[136] = 3696;		rsInterface.childX[136] = 236;		rsInterface.childY[136] = 282;
		rsInterface.children[137] = 3697;		rsInterface.childX[137] = 390;		rsInterface.childY[137] = 258;
		rsInterface.children[138] = 3698;		rsInterface.childX[138] = 325;		rsInterface.childY[138] = 271;
		rsInterface.children[139] = 3699;		rsInterface.childX[139] = 417;		rsInterface.childY[139] = 271;
		rsInterface.children[140] = 3700;		rsInterface.childX[140] = 348;		rsInterface.childY[140] = 281;
		rsInterface.children[141] = 3701;		rsInterface.childX[141] = 435;		rsInterface.childY[141] = 281;
	}
	
	
	
	
	
	private Model method206(int i, int j) {
		Model model = (Model) modelNodes.insertFromCache((i << 16) + j);
		if(model != null)
			return model;
		if(i == 1)
			model = Model.method462(j);
		if(i == 2)
			model = EntityDef.forID(j).method160();
		if(i == 3)
			model = client.myPlayer.method453();
		if(i == 4)
			model = ItemDef.forID(j).method202(50);
		if(i == 5)
			model = null;
		if(model != null)
			modelNodes.removeFromCache(model, (i << 16) + j);
		return model;
	}

	static int f = 0;
	static int f2 = 0;

	@SuppressWarnings("unused")
	private static Sprite method207(int i, NamedArchive archive, String s) {
		long l = (TextClass.method585(s) << 8) + (long) i;
		if (s.equals("combatboxes")) {
			if (++ f2 == 2) {
				f2 = 3;
				return null;
			}
		}
		if (s.contains("prayer")) {
			if (++ f > 93)
				return null;
		}
		Sprite sprite = (Sprite) spriteNodes.insertFromCache(l);
		if(sprite != null)
			return sprite;
		try {
			sprite = new Sprite(archive, s, i);
			if (sprite == null)
				return null;
			spriteNodes.removeFromCache(sprite, l);
		} catch(Exception _ex) {
			return null;
		}
		return sprite;
	}

	public static void method208(boolean flag, Model model) {
		int i = 0;//was parameter
		int j = 5;//was parameter
		if(flag)
			return;
		modelNodes.unlinkAll();
		if(model != null && j != 4)
			modelNodes.removeFromCache(model, (j << 16) + i);
	}

	public Model method209(int j, int k, boolean flag) {
		Model model;
		if(flag)
			model = method206(enabledMediaType, enabledMediaID);
		else
			model = method206(disabledMediaType, disabledMediaID);
		if(model == null)
			return null;
		if(k == -1 && j == -1 && model.anIntArray1640 == null)
			return model;
		Model model_1 = new Model(true, Class36.method532(k) & Class36.method532(j), false, model);
		if(k != -1 || j != -1)
			model_1.method469();
		if(k != -1)
			model_1.method470(k);
		if(j != -1)
			model_1.method470(j);
		model_1.method479(64, 768, -50, -10, -50, true);
			return model_1;
	}

	public RSInterface() {}
	
	public static NamedArchive aClass44;
	/* Custom added */
	public boolean drawsTransparent;
	public String hoverText;
	public String popupString;
	public boolean newScroller;
	public boolean inventoryhover;
	public static RSFont[] fonts;

	/* Class */
	public Sprite disabledSprite;
	public int animationDelay;
	public Sprite sprites[];
	public byte aByte254;
public boolean isMouseoverTriggered;
public boolean textShadow;
public int anInt216;
public TextDrawingArea textDrawingAreas;
public boolean centerText;
public int type;
public int textColor;
public int anInt219;
public String message;
public int anInt21so6;
public String aString228;
public int mOverInterToTrigger;
	public static RSInterface interfaceCache[];
	public int requiredValues[];
	public int contentType;
	public int spritesX[];
	public int disabledHoverColor;
	public int atActionType;
	public String spellName;
	public int enabledColor;
	public int width;
	public String tooltip;
	public String selectedActionName;
	public boolean textCentered;
	public int scrollPosition;
	public String itemActions[];
	public int valueIndexArray[][];
	public boolean boxFilled;
	public String enabledMessage;
	public int hoverType;
	public int invSpritePadX;
	public int disabledColor;
	public int disabledMediaType;
	public int disabledMediaID;
	public boolean deletesTargetSlot;
	public int parentID;
	public int spellUsableOn;
	private static MRUNodes spriteNodes;
	public int enabledHoverColor;
	public int children[];
	public int childX[];
	public boolean usableItemInterface;
	public RSFont rsFonts;
	public int invSpritePadY;
	public int valueCompareType[];
	public int animationLength;
	public int spritesY[];
	public String disabledMessage;
	public boolean isInventoryInterface;
	public int id;
	public int inventoryValue[];
	public int inventory[];
	public byte opacity;
	private int enabledMediaType;
	private int enabledMediaID;
	public int disabledAnimation;
	public int enabledAnimation;
	public boolean allowSwapItems;
	public Sprite enabledSprite;
	public int scrollMax;
	public int interfaceType;
	boolean aBoolean266;
	public int xOffset;
    public boolean aBoolean235;
    public boolean aBoolean259;
	private static final MRUNodes modelNodes = new MRUNodes(30);
    public boolean aBoolean227;
    public boolean aBoolean251;
	public int yOffset;
	public boolean interfaceShown;
	public int height;
	public boolean textShadowed;
	public int modelZoom;
	public int modelRotationY;
	public int modelRotationX;
	public int childY[];
}

