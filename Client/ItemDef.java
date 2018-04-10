import java.io.*;
import sign.signlink;


@SuppressWarnings("unused")
public final class ItemDef {

	public static ItemDef forID(int i) {
		for (int j = 0; j < 10; j++)
			if (cache[j].id == i)
				return cache[j];
		cacheIndex = (cacheIndex + 1) % 10;
		ItemDef itemDef = cache[cacheIndex];
		stream.currentOffset = streamIndices[i];
		itemDef.id = i;
		itemDef.setDefaults();
		itemDef.readValues(stream);

if (itemDef.editedModelColor != null) {
			for (int i2 = 0; i2 < itemDef.editedModelColor.length; i2++) {
				if (itemDef.newModelColor[i2] == 0) {
					itemDef.newModelColor[i2] = 1;
				}
			}
		}
		if (itemDef.certTemplateID != -1)
			itemDef.toNote();
		if (itemDef.lentItemID != -1)
			itemDef.toLend();
		if (!isMembers && itemDef.membersObject) {
			itemDef.name = "Members Object";
			itemDef.description = "Login to a members' server to use this object.";
			itemDef.groundActions = null;
			itemDef.itemActions = null;
			itemDef.team = 0;
		}
		switch (itemDef.id) {
		case 995:
			itemDef.name = "Coins";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[4] = "Drop";
			itemDef.itemActions[3] = "Add-to-pouch";
		break;
case 5733:       
                itemDef.name = "Staff Potato";
                itemDef.description = "A powerful staff object.";
                itemDef.itemActions = new String[5];
                itemDef.itemActions[3] = "Open Menu";
        break;

case 11698:
itemDef.name = "Saradomin godsword";
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wield";
itemDef.itemActions[2] = "Dismantle";
itemDef.itemActions[4] = "Drop";
itemDef.modelid = 71770;
itemDef.stackable = false;
itemDef.modelRotationX = 1958;
itemDef.modelRotationY = 222;
itemDef.modelZoom = 2050;
itemDef.anInt165 = 72130;
itemDef.anInt200 = 72130;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = -28;
itemDef.description = "A gracious, heavy sword.";
break;
case 275:
itemDef.name = "@red@[Teir 1] @whi@- @gre@Chest Key!";
itemDef.description = "This is a Tier 1 Chest Key!";
break;
case 11694:
itemDef.name = "Armadyl godsword";
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wield";
itemDef.itemActions[2] = "Dismantle";
itemDef.itemActions[4] = "Drop";
itemDef.modelid = 71769;
itemDef.stackable = false;
itemDef.modelRotationX = 1985;
itemDef.modelRotationY = 228;
itemDef.modelZoom = 2000;
itemDef.anInt165 = 72135;
itemDef.anInt200 = 72135;
itemDef.modelOffset1 = 5;
itemDef.modelOffset2 = -55;
itemDef.description = "A beautiful, heavy sword.";
break;
case 11700:
itemDef.name = "Zamorak godsword";
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wield";
itemDef.itemActions[2] = "Dismantle";
itemDef.itemActions[4] = "Drop";
itemDef.modelid = 71767;
itemDef.stackable = false;
itemDef.modelRotationX = 1965;
itemDef.modelRotationY = 243;
itemDef.modelZoom = 2128;
itemDef.anInt165 = 72138;
itemDef.anInt200 = 72138;
itemDef.modelOffset1 = 0;
itemDef.modelOffset2 = 0;
itemDef.description = "A terrifying, heavy sword.";
break;
case 11696:
itemDef.name = "Bandos godsword";
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wield";
itemDef.itemActions[2] = "Dismantle";
itemDef.itemActions[4] = "Drop";
itemDef.modelid = 71766;
itemDef.stackable = false;
itemDef.modelRotationX = 1161;
itemDef.modelRotationY = 192;
itemDef.modelZoom = 1616;
itemDef.anInt165 = 72137;
itemDef.anInt200 = 72137;
itemDef.modelOffset1 = -18;
itemDef.modelOffset2 = 75;
itemDef.description = "A brutally heavy sword.";
break;

case 15000:
itemDef.name = "Ragefire boots";
itemDef.modelid = 53897;
itemDef.modelZoom = 900;
itemDef.modelRotationY = 165;
itemDef.modelRotationX = 99;
itemDef.modelOffset1 = 3;
itemDef.modelOffset2 =-7;
itemDef.anInt165 = 53330;
itemDef.anInt200 = 53330;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.description = "Some Ragefire boots.";
break;

case 15001:
itemDef.name = "Steadfast Boots";
itemDef.modelid = 53835;
itemDef.modelZoom = 900;
itemDef.modelRotationY = 165;
itemDef.modelRotationX = 99;
itemDef.modelOffset1 = 3;
itemDef.modelOffset2 =-7;
itemDef.anInt165 = 53327;
itemDef.anInt200 = 53327;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.description = "Some Steadfast boots.";
break;

case 15002:
itemDef.name = "Glaiven boots";
itemDef.modelid = 53828;
itemDef.modelZoom = 900;
itemDef.modelRotationY = 165;
itemDef.modelRotationX = 99;
itemDef.modelOffset1 = 3;
itemDef.modelOffset2 =-7;
itemDef.anInt165 = 53309;
itemDef.anInt200 = 53309;
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.description = "Some Glaiven boots.";
break;

case 962:
itemDef.itemActions = new String[5];
itemDef.itemActions[0] = "Open";
itemDef.name = "Christmas Cracker";
itemDef.description = "A nice cracker to open.";
itemDef.stackable = false;
break;
		case 13362:
		itemDef.modelid = 62714;
		itemDef.name = "Torva full helm";
		itemDef.description = "Torva full helm.";
		itemDef.modelZoom = 672;
		itemDef.modelRotationY = 85;
		itemDef.modelRotationX = 1867;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -3;
		itemDef.anInt165 = 62738;
		itemDef.anInt200 = 62754;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
		break;	
case 13360:
		itemDef.modelid = 62701;
		itemDef.name = "Torva platelegs";
		itemDef.description = "Torva platelegs.";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 474;
		itemDef.modelRotationX = 2045;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 62743;
		itemDef.anInt200 = 62760;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;


	case 13358:
		itemDef.modelid = 62699;
		itemDef.name = "Torva platebody";
		itemDef.description = "Torva Platebody.";
		itemDef.modelZoom = 1506;
		itemDef.modelRotationY = 473;
		itemDef.modelRotationX = 2042;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 62746;
		itemDef.anInt200 = 62762;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;
		case 13355:
		itemDef.modelid = 62693;
		itemDef.name = "Pernix cowl";
		itemDef.description = "Pernix cowl";
		itemDef.modelZoom = 800;
		itemDef.modelRotationY = 532;
		itemDef.modelRotationX = 14;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 1;
		itemDef.anInt165 = 62739;
		itemDef.anInt200 = 62756;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt175 = 62731;
		itemDef.anInt197 = 62727;
	break;



case 897:
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.editedModelColor = new int[4];
itemDef.newModelColor = new int[4];
itemDef.editedModelColor[0] = 57280;
itemDef.editedModelColor[1] = 54503;
itemDef.editedModelColor[2] = 54183;
itemDef.editedModelColor[3] = 11200;
itemDef.newModelColor[0] = 9152;
itemDef.newModelColor[1] = 7104;
itemDef.newModelColor[2] = 7104;
itemDef.newModelColor[3] = 796;
itemDef.modelid = 32880;
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 500;
itemDef.modelRotationX = 0;
itemDef.anInt204 = 14;
itemDef.modelOffset1 = -6;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 32880;
itemDef.anInt200 = 32880;
itemDef.name = "Copper Hat";
itemDef.description = "Copper Hat Worn By owners";
break;
case 899:
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.editedModelColor = new int[4];
itemDef.newModelColor = new int[4];
itemDef.editedModelColor[0] = 57280;
itemDef.editedModelColor[1] = 54503;
itemDef.editedModelColor[2] = 54183;
itemDef.editedModelColor[3] = 11200;
itemDef.newModelColor[0] = 9152;
itemDef.newModelColor[1] = 7104;
itemDef.newModelColor[2] = 7104;
itemDef.newModelColor[3] = 796;
itemDef.modelid = 1980;
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 500;
itemDef.modelRotationX = 0;
itemDef.anInt204 = 14;
itemDef.modelOffset1 = -6;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 1980;
itemDef.anInt200 = 1980;
itemDef.name = "indiana jones hat";
itemDef.description = "indiana jones hat";
break;
case 900:
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.editedModelColor = new int[4];
itemDef.newModelColor = new int[4];
itemDef.editedModelColor[0] = 57280;
itemDef.editedModelColor[1] = 54503;
itemDef.editedModelColor[2] = 54183;
itemDef.editedModelColor[3] = 11200;
itemDef.newModelColor[0] = 9152;
itemDef.newModelColor[1] = 7104;
itemDef.newModelColor[2] = 7104;
itemDef.newModelColor[3] = 796;
itemDef.modelid = 32879;
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 500;
itemDef.modelRotationX = 0;
itemDef.anInt204 = 14;
itemDef.modelOffset1 = -6;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 32879;
itemDef.anInt200 = 32879;
itemDef.name = "Pking Cape";
itemDef.description = "Large winged Pking Cape";
break;
case 901:
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.editedModelColor = new int[4];
itemDef.newModelColor = new int[4];
itemDef.editedModelColor[0] = 57280;
itemDef.editedModelColor[1] = 54503;
itemDef.editedModelColor[2] = 54183;
itemDef.editedModelColor[3] = 11200;
itemDef.newModelColor[0] = 9152;
itemDef.newModelColor[1] = 7104;
itemDef.newModelColor[2] = 7104;
itemDef.newModelColor[3] = 796;
itemDef.modelid = 13879;
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 500;
itemDef.modelRotationX = 0;
itemDef.anInt204 = 14;
itemDef.modelOffset1 = -6;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 13874;
itemDef.anInt200 = 13874;
itemDef.name = "Donator Cape";
itemDef.description = "The cape worn by Donators.";
break;
case 902:
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.newModelColor = new int[4];
itemDef.modelid = 14128;
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 500;
itemDef.modelRotationX = 0;
itemDef.anInt204 = 14;
itemDef.modelOffset1 = -6;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 14128;
itemDef.anInt200 = 14128;
itemDef.name = "Mod Cape";
itemDef.description = "The cape worn by Mods.";
break;
case 903:
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.modelid = 14130;
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 500;
itemDef.modelRotationX = 0;
itemDef.anInt204 = 14;
itemDef.modelOffset1 = -6;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 14130;
itemDef.anInt200 = 14130;
itemDef.name = "Head Of Staff Cape";
itemDef.description = "The cape worn by Head Of Staff.";
break;
case 904:
itemDef.itemActions = new String[5];
itemDef.itemActions[1] = "Wear";
itemDef.modelid = 14132;
itemDef.modelZoom = 2000;
itemDef.modelRotationY = 500;
itemDef.modelRotationX = 0;
itemDef.anInt204 = 14;
itemDef.modelOffset1 = -6;
itemDef.modelOffset2 = 1;
itemDef.anInt165 = 14132;
itemDef.anInt200 = 14132;
itemDef.name = "Owner Cape";
itemDef.description = "The cape worn by Owners.";
break;
	case 13354:
		itemDef.modelid = 62709;
		itemDef.name = "Pernix body";
		itemDef.description = "Pernix body";
		itemDef.modelZoom = 1378;
		itemDef.modelRotationY = 485;
		itemDef.modelRotationX = 2042;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 7;
		itemDef.anInt165 = 62744;
		itemDef.anInt200 = 62765;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

	case 13352:
		itemDef.modelid = 62695;
		itemDef.name = "Pernix chaps";
		itemDef.description = "Pernix chaps";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 504;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = 4;
		itemDef.modelOffset2 = 3;
		itemDef.anInt165 = 62741;
		itemDef.anInt200 = 62757;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

	case 13350:
		itemDef.modelid = 62710;
		itemDef.name = "Virtus mask";
		itemDef.description = "Virtus mask";
		itemDef.modelZoom = 928;
		itemDef.modelRotationY = 406;
		itemDef.modelRotationX = 2041;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 62736;
		itemDef.anInt200 = 62755;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt175 = 62728;
		itemDef.anInt197 = 62728;
	break;

	case 13348:
		itemDef.modelid = 62704;
		itemDef.name = "Virtus robe top";
		itemDef.description = "Virtus robe top";
		itemDef.modelZoom = 1122;
		itemDef.modelRotationY = 488;
		itemDef.modelRotationX = 3;
		itemDef.modelOffset1 = 1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 62748;
		itemDef.anInt200 = 62764;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;

	case 13346:
		itemDef.modelid = 62700;
		itemDef.name = "Virtus robe legs";
		itemDef.description = "Virtus robe legs";
		itemDef.modelZoom = 1740;
		itemDef.modelRotationY = 498;
		itemDef.modelRotationX = 2045;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 4;
		itemDef.anInt165 = 62742;
		itemDef.anInt200 = 62758;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[2] = "Check-charges";
		itemDef.itemActions[4] = "Drop";
	break;
	case 13344:
		itemDef.modelid = 62694;
		itemDef.name = "Ancient ceremonial hood";
		itemDef.modelZoom = 980;
		itemDef.modelRotationY = 208;
		itemDef.modelRotationX = 220;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -18;
		itemDef.anInt165 = 62737;
		itemDef.anInt200 = 62753;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt175 = 62730;
		itemDef.anInt197 = 62730;
	break;

	case 13342:
		itemDef.modelid = 62705;
		itemDef.name = "Ancient ceremonial top";
		itemDef.modelZoom = 1316;
		itemDef.modelRotationY = 477;
		itemDef.modelRotationX = 9;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 13;
		itemDef.anInt165 = 62745;
		itemDef.anInt200 = 62763;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt204 = 54;
	break;

	case 13340:
		itemDef.modelid = 62707;
		itemDef.name = "Ancient ceremonial legs";
		itemDef.modelZoom = 1828;
		itemDef.modelRotationY = 539;
		itemDef.modelRotationX = 0;
		itemDef.modelOffset1 = -1;
		itemDef.modelOffset2 = 0;
		itemDef.anInt165 = 62740;
		itemDef.anInt200 = 62759;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
		itemDef.anInt204 = 40;
		itemDef.anInt196 = 30;
		itemDef.anInt184 = 100;
	break;

	case 13370:
		itemDef.modelid = 62697;
		itemDef.name = "Ancient ceremonial gloves";
		itemDef.modelZoom = 548;
		itemDef.modelRotationY = 618;
		itemDef.modelRotationX = 1143;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = -5;
		itemDef.anInt165 = 62735;
		itemDef.anInt200 = 62752;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;

	case 13336:
		itemDef.modelid = 62696;
		itemDef.name = "Ancient ceremonial boots";
		itemDef.modelZoom = 676;
		itemDef.modelRotationY = 63;
		itemDef.modelRotationX = 106;
		itemDef.modelOffset1 = 5;
		itemDef.modelOffset2 = -1;
		itemDef.anInt165 = 62734;
		itemDef.anInt200 = 62751;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";
		itemDef.itemActions = new String[5];
		itemDef.itemActions[1] = "Wear";
		itemDef.itemActions[4] = "Drop";
	break;

}
		return itemDef;
	}
	
	public static void unpackConfig(NamedArchive streamLoader) {
		stream = new Stream(FileOperations.ReadFile(signlink.findcachedir() + "obj.dat"));
		Stream stream = new Stream(FileOperations.ReadFile(signlink.findcachedir() + "obj.idx"));
		totalItems = stream.readUnsignedWord();
		streamIndices = new int[totalItems + 5000];
		int i = 2;
		for (int j = 0; j < totalItems; j++) {
			streamIndices[j] = i;
			i += stream.readUnsignedWord();
		}
		cache = new ItemDef[10];
		for (int k = 0; k < 10; k++)
			cache[k] = new ItemDef();
	}

	public void setDefaults() {
		modelid = 0;
		name = null;
		description = null;
		editedModelColor = null;
		newModelColor = null;
		modelZoom = 2000;
		modelRotationY = 0;
		modelRotationX = 0;
		anInt204 = 0;
		modelOffset1 = 0;
		modelOffset2 = 0;
		stackable = false;
		value = 1;
		membersObject = false;
		groundActions = null;
		itemActions = null;
		anInt165 = -1;
		anInt188 = -1;
		aByte205 = 0;
		anInt200 = -1;
		anInt164 = -1;
		aByte154 = 0;
		anInt185 = -1;
		anInt162 = -1;
		anInt175 = -1;
		anInt166 = -1;
		anInt197 = -1;
		anInt173 = -1;
		stackIDs = null;
		stackAmounts = null;
		certID = -1;
		certTemplateID = -1;
		anInt167 = 128;
		anInt192 = 128;
		anInt191 = 128;
		anInt196 = 0;
		anInt184 = 0;
		team = 0;
		lendID = -1;
		lentItemID = -1;
	}
	
	public void readValues(Stream stream) {
		do {
			int i = stream.readUnsignedByte();
			if (i == 0)
				return;
			if (i == 1) {
				modelid = stream.readUnsignedWord();
			} else if (i == 2)
				name = stream.readString();
			else if (i == 3)
				description = stream.readString();
			else if (i == 4)
				modelZoom = stream.readUnsignedWord();
			else if (i == 5)
				modelRotationY = stream.readUnsignedWord();
			else if (i == 6)
				modelRotationX = stream.readUnsignedWord();
			else if (i == 7) {
				modelOffset1 = stream.readUnsignedWord();
				if (modelOffset1 > 32767)
					modelOffset1 -= 0x10000;
			} else if (i == 8) {
				modelOffset2 = stream.readUnsignedWord();
				if (modelOffset2 > 32767)
					modelOffset2 -= 0x10000;
			} else if (i == 10)
				stream.readUnsignedWord();
			else if (i == 11)
				stackable = true;
			else if (i == 12)
				value = stream.readUnsignedWord();
			else if (i == 16)
				membersObject = true;
			else if (i == 23) {
				anInt165 = stream.readUnsignedWord();
				aByte205 = stream.readSignedByte();
			} else if (i == 24)
				anInt188 = stream.readUnsignedWord();
			else if (i == 25) {
				anInt200 = stream.readUnsignedWord();
				aByte154 = stream.readSignedByte();
			} else if (i == 26)
				anInt164 = stream.readUnsignedWord();
			else if (i >= 30 && i < 35) {
				if (groundActions == null)
					groundActions = new String[5];
				groundActions[i - 30] = stream.readString();
				if (groundActions[i - 30].equalsIgnoreCase("hidden"))
					groundActions[i - 30] = null;
			} else if (i >= 35 && i < 40) {
				if (itemActions == null)
					itemActions = new String[5];
				itemActions[i - 35] = stream.readString();
				if (itemActions[i - 35].equalsIgnoreCase("null"))
					itemActions[i - 35] = null;
			} else if (i == 40) {
				int j = stream.readUnsignedByte();
				editedModelColor = new int[j];
				newModelColor = new int[j];
				for (int k = 0; k < j; k++) {
					editedModelColor[k] = stream.readUnsignedWord();
					newModelColor[k] = stream.readUnsignedWord();
				}
			} else if (i == 78)
				anInt185 = stream.readUnsignedWord();
			else if (i == 79)
				anInt162 = stream.readUnsignedWord();
			else if (i == 90)
				anInt175 = stream.readUnsignedWord();
			else if (i == 91)
				anInt197 = stream.readUnsignedWord();
			else if (i == 92)
				anInt166 = stream.readUnsignedWord();
			else if (i == 93)
				anInt173 = stream.readUnsignedWord();
			else if (i == 95)
				anInt204 = stream.readUnsignedWord();
			else if (i == 97)
				certID = stream.readUnsignedWord();
			else if (i == 98)
				certTemplateID = stream.readUnsignedWord();
			else if (i >= 100 && i < 110) {
				if (stackIDs == null) {
					stackIDs = new int[10];
					stackAmounts = new int[10];
				}
				stackIDs[i - 100] = stream.readUnsignedWord();
				stackAmounts[i - 100] = stream.readUnsignedWord();
			} else if (i == 110)
				anInt167 = stream.readUnsignedWord();
			else if (i == 111)
				anInt192 = stream.readUnsignedWord();
			else if (i == 112)
				anInt191 = stream.readUnsignedWord();
			else if (i == 113)
				anInt196 = stream.readSignedByte();
			else if (i == 114)
				anInt184 = stream.readSignedByte() * 5;
			else if (i == 115)
				team = stream.readUnsignedByte();
			else if (i == 116)
				lendID = stream.readUnsignedWord();
			else if (i == 117)
				lentItemID = stream.readUnsignedWord();
		} while (true);
	}
	
	public static void nullLoader() {
		mruNodes2 = null;
		mruNodes1 = null;
		streamIndices = null;
		cache = null;
		stream = null;
	}

	public boolean method192(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.method463(k))
			flag = false;
		if (l != -1 && !Model.method463(l))
			flag = false;
		return flag;
	}

	public Model method194(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return null;
		Model model = Model.method462(k);
		if (l != -1) {
			Model model_1 = Model.method462(l);
			Model models[] = { model, model_1 };
			model = new Model(2, models);
		}
		if (editedModelColor != null) {
			for (int i1 = 0; i1 < editedModelColor.length; i1++)
				model.method476(editedModelColor[i1], newModelColor[i1]);
		}
		return model;
	}

	public boolean method195(int j) {
		int k = anInt165;
		int l = anInt188;
		int i1 = anInt185;
		if (j == 1) {
			k = anInt200;
			l = anInt164;
			i1 = anInt162;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.method463(k))
			flag = false;
		if (l != -1 && !Model.method463(l))
			flag = false;
		if (i1 != -1 && !Model.method463(i1))
			flag = false;
		return flag;
	}

	public Model method196(int i) {
		int j = anInt165;
		int k = anInt188;
		int l = anInt185;
		if (i == 1) {
			j = anInt200;
			k = anInt164;
			l = anInt162;
		}
		if (j == -1)
			return null;
		Model model = Model.method462(j);
		if (k != -1)
			if (l != -1) {
				Model model_1 = Model.method462(k);
				Model model_3 = Model.method462(l);
				Model model_1s[] = { model, model_1, model_3 };
				model = new Model(3, model_1s);
			} else {
				Model model_2 = Model.method462(k);
				Model models[] = { model, model_2 };
				model = new Model(2, models);
			}
		if (i == 0 && aByte205 != 0)
			model.method475(0, aByte205, 0);
		if (i == 1 && aByte154 != 0)
			model.method475(0, aByte154, 0);
		if (editedModelColor != null) {
			for (int i1 = 0; i1 < editedModelColor.length; i1++)
				model.method476(editedModelColor[i1], newModelColor[i1]);
		}
		return model;
	}
	
	public void toNote() {
		ItemDef itemDef = forID(certTemplateID);
		modelid = itemDef.modelid;
		modelZoom = itemDef.modelZoom;
		modelRotationY = itemDef.modelRotationY;
		modelRotationX = itemDef.modelRotationX;
		anInt204 = itemDef.anInt204;
		modelOffset1 = itemDef.modelOffset1;
		modelOffset2 = itemDef.modelOffset2;
		editedModelColor = itemDef.editedModelColor;
		newModelColor = itemDef.newModelColor;
		ItemDef itemDef_1 = forID(certID);
		name = itemDef_1.name;
		membersObject = itemDef_1.membersObject;
		value = itemDef_1.value;
		String s = "a";
		char c = itemDef_1.name.charAt(0);
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			s = "an";
		description = ("Swap this note at any bank for " + s + " " + itemDef_1.name + ".");
		stackable = true;
	}
	
	public void toLend() {
		ItemDef itemDef = forID(lentItemID);
		itemActions = new String[5];
		modelid = itemDef.modelid;
		modelOffset1 = itemDef.modelOffset1;
		modelRotationX = itemDef.modelRotationX;
		modelOffset2 = itemDef.modelOffset2;
		modelZoom = itemDef.modelZoom;
		modelRotationY = itemDef.modelRotationY;
		anInt204 = itemDef.anInt204;
		value = 0;
		ItemDef itemDef_1 = forID(lendID);
		anInt166 = itemDef_1.anInt166;
		editedModelColor = itemDef_1.editedModelColor;
		anInt185 = itemDef_1.anInt185;
		anInt188 = itemDef_1.anInt188;
		anInt173 = itemDef_1.anInt173;
		anInt175 = itemDef_1.anInt175;
		groundActions = itemDef_1.groundActions;
		anInt165 = itemDef_1.anInt165;
		name = itemDef_1.name;
		anInt200 = itemDef_1.anInt200;
		membersObject = itemDef_1.membersObject;
		anInt197 = itemDef_1.anInt197;
		anInt164 = itemDef_1.anInt164;
		anInt162 = itemDef_1.anInt162;
		newModelColor = itemDef_1.newModelColor;
		team = itemDef_1.team;
		if (itemDef_1.itemActions != null) {
			for (int i_33_ = 0; i_33_ < 4; i_33_++)
				itemActions[i_33_] = itemDef_1.itemActions[i_33_];
		}
		itemActions[4] = "Discard";
	}

	public static Sprite getSprite(int i, int j, int k) {
		if (k == 0) {
			Sprite sprite = (Sprite) mruNodes1.insertFromCache(i);
			if (sprite != null && sprite.maxHeight != j && sprite.maxHeight != -1) {
				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}
		ItemDef itemDef = forID(i);
		if (itemDef.stackIDs == null)
			j = -1;
		if (j > 1) {
			int i1 = -1;
			for (int j1 = 0; j1 < 10; j1++)
				if (j >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];
			if (i1 != -1)
				itemDef = forID(i1);
		}
		Model model = itemDef.method201(1);
		if (model == null)
			return null;
		Sprite sprite = null;
		if (itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if (sprite == null)
				return null;
		}
		if (itemDef.lentItemID != -1) {
			sprite = getSprite(itemDef.lendID, 50, 0);
			if (sprite == null)
				return null;
		}
		Sprite sprite2 = new Sprite(32, 32);
		int k1 = Texture.textureInt1;
		int l1 = Texture.textureInt2;
		int ai[] = Texture.anIntArray1472;
		int ai1[] = DrawingArea.pixels;
		int i2 = DrawingArea.width;
		int j2 = DrawingArea.height;
		int k2 = DrawingArea.topX;
		int l2 = DrawingArea.bottomX;
		int i3 = DrawingArea.topY;
		int j3 = DrawingArea.bottomY;
		Texture.aBoolean1464 = false;
		DrawingArea.initDrawingArea(32, 32, sprite2.myPixels);
		DrawingArea.drawPixels(32, 0, 0, 0, 32);
		Texture.method364();
		int k3 = itemDef.modelZoom;
		if (k == -1)
			k3 = (int) ((double) k3 * 1.5D);
		if (k > 0)
			k3 = (int) ((double) k3 * 1.04D);
		int l3 = Texture.anIntArray1470[itemDef.modelRotationY] * k3 >> 16;
		int i4 = Texture.anIntArray1471[itemDef.modelRotationY] * k3 >> 16;
		model.method482(itemDef.modelRotationX, itemDef.anInt204, itemDef.modelRotationY, itemDef.modelOffset1, l3 + model.modelHeight / 2 + itemDef.modelOffset2, i4 + itemDef.modelOffset2);
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--)
				if (sprite2.myPixels[i5 + j4 * 32] == 0)
					if (i5 > 0 && sprite2.myPixels[(i5 - 1) + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (j4 > 0 && sprite2.myPixels[i5 + (j4 - 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (i5 < 31 && sprite2.myPixels[i5 + 1 + j4 * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
					else if (j4 < 31 && sprite2.myPixels[i5 + (j4 + 1) * 32] > 1)
						sprite2.myPixels[i5 + j4 * 32] = 1;
		}
		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--)
					if (sprite2.myPixels[j5 + k4 * 32] == 0)
						if (j5 > 0 && sprite2.myPixels[(j5 - 1) + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (k4 > 0 && sprite2.myPixels[j5 + (k4 - 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (j5 < 31 && sprite2.myPixels[j5 + 1 + k4 * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
						else if (k4 < 31 && sprite2.myPixels[j5 + (k4 + 1) * 32] == 1)
							sprite2.myPixels[j5 + k4 * 32] = k;
			}
		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (sprite2.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && sprite2.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0)
						sprite2.myPixels[k5 + l4 * 32] = 0x302020;
			}
		}
		if (itemDef.certTemplateID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (itemDef.lentItemID != -1) {
			int l5 = sprite.maxWidth;
			int j6 = sprite.maxHeight;
			sprite.maxWidth = 32;
			sprite.maxHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.maxWidth = l5;
			sprite.maxHeight = j6;
		}
		if (k == 0)
			mruNodes1.removeFromCache(sprite2, i);
		DrawingArea.initDrawingArea(j2, i2, ai1);
		DrawingArea.setDrawingArea(j3, k2, l2, i3);
		Texture.textureInt1 = k1;
		Texture.textureInt2 = l1;
		Texture.anIntArray1472 = ai;
		Texture.aBoolean1464 = true;
		if (itemDef.stackable)
			sprite2.maxWidth = 33;
		else
			sprite2.maxWidth = 32;
		sprite2.maxHeight = j;
		return sprite2;
	}

	public Model method201(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];
			if (j != -1)
				return forID(j).method201(1);
		}
		Model model = (Model) mruNodes2.insertFromCache(id);
		if (model != null)
			return model;
		model = Model.method462(modelid);
		if (model == null)
			return null;
		if (anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
			model.method478(anInt167, anInt191, anInt192);
		if (editedModelColor != null) {
			for (int l = 0; l < editedModelColor.length; l++)
				model.method476(editedModelColor[l], newModelColor[l]);
		}
		model.method479(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
		model.aBoolean1659 = true;
		mruNodes2.removeFromCache(model, id);
		return model;
	}

	public Model method202(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];
			if (j != -1)
				return forID(j).method202(1);
		}
		Model model = Model.method462(modelid);
		if (model == null)
			return null;
		if (editedModelColor != null) {
			for (int l = 0; l < editedModelColor.length; l++)
				model.method476(editedModelColor[l], newModelColor[l]);
		}
		return model;
	}

	public ItemDef() {
		id = -1;
	}

	public byte aByte154;
	public int value;
	public int[] editedModelColor;
	public int id;
	static MRUNodes mruNodes1 = new MRUNodes(100);
	public static MRUNodes mruNodes2 = new MRUNodes(50);
	public int[] newModelColor;
	public boolean membersObject;
	public int anInt162;
	public int certTemplateID;
	public int anInt164;
	public int anInt165;
	public int anInt166;
	public int anInt167;
	public String groundActions[];
	public int modelOffset1;
	public String name;
	public static ItemDef[] cache;
	public int anInt173;
	public int modelid;
	public int anInt175;
	public boolean stackable;
	public String description;
	public int certID;
	public static int cacheIndex;
	public int modelZoom;
	public static boolean isMembers = true;
	public static Stream stream;
	public int anInt184;
	public int anInt185;
	public int anInt188;
	public String itemActions[];
	public int modelRotationY;
	public int anInt191;
	public int anInt192;
	public int[] stackIDs;
	public int modelOffset2;
	public static int[] streamIndices;
	public int anInt196;
	public int anInt197;
	public int modelRotationX;
	public int anInt200;
	public int[] stackAmounts;
	public int team;
	public static int totalItems;
	public int anInt204;
	public byte aByte205;
	public int lendID;
	public int lentItemID;
}
