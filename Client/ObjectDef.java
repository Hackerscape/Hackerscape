// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.io.*;
import sign.signlink;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

@SuppressWarnings("unused")
public final class ObjectDef
{
	public static ObjectDef forID(int i) {
		for(int j = 0; j < 20; j++)
			if(cache[j].type == i)
				return cache[j];
			cacheIndex = (cacheIndex + 1) % 20;
			ObjectDef objectDef = cache[cacheIndex];
			stream.currentOffset = streamIndices[i];
			objectDef.type = i;
			objectDef.setDefaults();
			objectDef.readValues(stream);
			if (i == 1503) {
				objectDef.anIntArray773 = new int[1];
				objectDef.anIntArray773[0] = 28124;
				objectDef.hasActions = false;
			}
			if (i == 26392) {
				objectDef.anIntArray773 = new int[2];
				objectDef.anIntArray773[0] = 27816;
				objectDef.anIntArray773[1] = 27836;
				objectDef.hasActions = false;
			}
			if (i == 6788) {
				objectDef.anIntArray773 = new int[1];
				objectDef.anIntArray773[0] = 28124;
				objectDef.hasActions = false;
			}
			if (i == 8935) {
				objectDef.anIntArray773 = new int[1];
				objectDef.anIntArray773[0] = 28124;
				objectDef.hasActions = false;
			}
			if (i == 1460) {
				objectDef.anIntArray773 = new int[1];
				objectDef.anIntArray773[0] = 28124;
				objectDef.hasActions = false;
			}
			if (i == 9383) {
				objectDef.anIntArray773 = new int[1];
				objectDef.anIntArray773[0] = 28124;
				objectDef.hasActions = false;
			}
			if (i == 1434) {
				objectDef.anIntArray773 = new int[1];
				objectDef.anIntArray773[0] = 28124;
				objectDef.hasActions = false;
			}
		return objectDef;
	}
	
    private void setDefaults()
    {
        anIntArray773 = null;
        anIntArray776 = null;
        name = null;
        description = null;
        modifiedModelColors = null;
        originalModelColors = null;
        anInt744 = 1;
        anInt761 = 1;
        aBoolean767 = true;
        aBoolean757 = true;
        hasActions = false;
        aBoolean762 = false;
        aBoolean769 = false;
        aBoolean764 = false;
        anInt781 = -1;
        anInt775 = 16;
        aByte737 = 0;
        aByte742 = 0;
        itemActions = null;
        anInt746 = -1;
        anInt758 = -1;
        aBoolean751 = false;
        aBoolean779 = true;
        anInt748 = 128;
        anInt772 = 128;
        anInt740 = 128;
        anInt768 = 0;
        anInt738 = 0;
        anInt745 = 0;
        anInt783 = 0;
        aBoolean736 = false;
        aBoolean766 = false;
        anInt760 = -1;
        anInt774 = -1;
        anInt749 = -1;
        childrenIDs = null;
    }

    public void method574(OnDemandFetcher class42_sub1)
    {
        if(anIntArray773 == null)
            return;
        for(int j = 0; j < anIntArray773.length; j++)
            class42_sub1.method560(anIntArray773[j] & 0xffff, 0);
    }

    public static void nullLoader()
    {
        mruNodes1 = null;
        mruNodes2 = null;
        streamIndices = null;
        cache = null;
		stream = null;
    }

    public static void unpackConfig(NamedArchive archive)
    {
        stream = new Stream(archive.getDataForName("loc.dat"));
        Stream stream = new Stream(archive.getDataForName("loc.idx"));
        int totalObjects = stream.readUnsignedWord();
        streamIndices = new int[totalObjects+35000];
        int i = 2;
        for(int j = 0; j < totalObjects; j++)
        {
            streamIndices[j] = i;
            i += stream.readUnsignedWord();
        }
        cache = new ObjectDef[20];
        for(int k = 0; k < 20; k++)
            cache[k] = new ObjectDef();
    }

    public boolean method577(int i)
    {
        if(anIntArray776 == null)
        {
            if(anIntArray773 == null)
                return true;
            if(i != 10)
                return true;
            boolean flag1 = true;
            for(int k = 0; k < anIntArray773.length; k++)
                flag1 &= Model.method463(anIntArray773[k] & 0xffff);

            return flag1;
        }
        for(int j = 0; j < anIntArray776.length; j++)
            if(anIntArray776[j] == i)
                return Model.method463(anIntArray773[j] & 0xffff);

        return true;
    }

    public Model method578(int i, int j, int k, int l, int i1, int j1, int k1)
    {
        Model model = method581(i, k1, j);
        if(model == null)
            return null;
        if(aBoolean762 || aBoolean769)
            model = new Model(aBoolean762, aBoolean769, model);
        if(aBoolean762)
        {
            int l1 = (k + l + i1 + j1) / 4;
            for(int i2 = 0; i2 < model.anInt1626; i2++)
            {
                int j2 = model.anIntArray1627[i2];
                int k2 = model.anIntArray1629[i2];
                int l2 = k + ((l - k) * (j2 + 64)) / 128;
                int i3 = j1 + ((i1 - j1) * (j2 + 64)) / 128;
                int j3 = l2 + ((i3 - l2) * (k2 + 64)) / 128;
                model.anIntArray1628[i2] += j3 - l1;
            }

            model.method467();
        }
        return model;
    }

    public boolean method579()
    {
        if(anIntArray773 == null)
            return true;
        boolean flag1 = true;
        for(int i = 0; i < anIntArray773.length; i++)
            flag1 &= Model.method463(anIntArray773[i] & 0xffff);
            return flag1;
    }

    public ObjectDef method580()
    {
        int i = -1;
        if(anInt774 != -1)
        {
            VarBit varBit = VarBit.cache[anInt774];
            int j = varBit.anInt648;
            int k = varBit.anInt649;
            int l = varBit.anInt650;
            int i1 = client.anIntArray1232[l - k];
            i = clientInstance.variousSettings[j] >> k & i1;
        } else
        if(anInt749 != -1)
            i = clientInstance.variousSettings[anInt749];
        if(i < 0 || i >= childrenIDs.length || childrenIDs[i] == -1)
            return null;
        else
            return forID(childrenIDs[i]);
    }

    private Model method581(int j, int k, int l)
    {
        Model model = null;
        long l1;
        if(anIntArray776 == null)
        {
            if(j != 10)
                return null;
            l1 = (long)((type << 6) + l) + ((long)(k + 1) << 32);
            Model model_1 = (Model) mruNodes2.insertFromCache(l1);
            if(model_1 != null)
                return model_1;
            if(anIntArray773 == null)
                return null;
            boolean flag1 = aBoolean751 ^ (l > 3);
            int k1 = anIntArray773.length;
            for(int i2 = 0; i2 < k1; i2++)
            {
                int l2 = anIntArray773[i2];
                if(flag1)
                    l2 += 0x10000;
                model = (Model) mruNodes1.insertFromCache(l2);
                if(model == null)
                {
                    model = Model.method462(l2 & 0xffff);
                    if(model == null)
                        return null;
                    if(flag1)
                        model.method477();
                    mruNodes1.removeFromCache(model, l2);
                }
                if(k1 > 1)
                    aModelArray741s[i2] = model;
            }

            if(k1 > 1)
                model = new Model(k1, aModelArray741s);
        } else
        {
            int i1 = -1;
            for(int j1 = 0; j1 < anIntArray776.length; j1++)
            {
                if(anIntArray776[j1] != j)
                    continue;
                i1 = j1;
                break;
            }

            if(i1 == -1)
                return null;
            l1 = (long)((type << 6) + (i1 << 3) + l) + ((long)(k + 1) << 32);
            Model model_2 = (Model) mruNodes2.insertFromCache(l1);
            if(model_2 != null)
                return model_2;
            int j2 = anIntArray773[i1];
            boolean flag3 = aBoolean751 ^ (l > 3);
            if(flag3)
                j2 += 0x10000;
            model = (Model) mruNodes1.insertFromCache(j2);
            if(model == null)
            {
                model = Model.method462(j2 & 0xffff);
                if(model == null)
                    return null;
                if(flag3)
                    model.method477();
                mruNodes1.removeFromCache(model, j2);
            }
        }
        boolean flag;
        flag = anInt748 != 128 || anInt772 != 128 || anInt740 != 128;
        boolean flag2;
        flag2 = anInt738 != 0 || anInt745 != 0 || anInt783 != 0;
        Model model_3 = new Model(modifiedModelColors == null, Class36.method532(k), l == 0 && k == -1 && !flag && !flag2, model);
        if(k != -1)
        {
            model_3.method469();
            model_3.method470(k);
            model_3.anIntArrayArray1658 = null;
            model_3.anIntArrayArray1657 = null;
        }
        while(l-- > 0) 
            model_3.method473();
        if(modifiedModelColors != null)
        {
            for(int k2 = 0; k2 < modifiedModelColors.length; k2++)
                model_3.method476(modifiedModelColors[k2], originalModelColors[k2]);

        }
        if(flag)
            model_3.method478(anInt748, anInt740, anInt772);
        if(flag2)
            model_3.method475(anInt738, anInt745, anInt783);
        model_3.method479(74, 1000, -90, -580, -90, !aBoolean769);
        if(anInt760 == 1)
            model_3.anInt1654 = model_3.modelHeight;
        mruNodes2.removeFromCache(model_3, l1);
        return model_3;
    }

    private void readValues(Stream stream)
    {
        int flag = -1;
	do {
		int type = stream.readUnsignedByte();
		if(type == 0)
			break;
		if(type == 1)
		{
			int len = stream.readUnsignedByte();
			if(len > 0)
			{
				if(anIntArray773 == null || lowMem)
				{
					anIntArray776 = new int[len];
                            		anIntArray773 = new int[len];
                            		for(int k1 = 0; k1 < len; k1++)
                            		{
                                		anIntArray773[k1] = stream.readUnsignedWord();
                                		anIntArray776[k1] = stream.readUnsignedByte();
                            		}
                        	} else
                        	{
                            		stream.currentOffset += len * 3;
                        	}
       			}
		} else
		if(type == 2)
			name = stream.readNewString();
		else
		if(type == 3)
			description = stream.readBytes();
		else
		if(type == 5)
		{
			int len = stream.readUnsignedByte();
			if(len > 0)
			{
				if(anIntArray773 == null || lowMem)
				{
					anIntArray776 = null;
                            		anIntArray773 = new int[len];
                            		for(int l1 = 0; l1 < len; l1++)
                                		anIntArray773[l1] = stream.readUnsignedWord();
				} else
                        	{
                            		stream.currentOffset += len * 2;
                        	}
			}
		} else
		if(type == 14)
			anInt744 = stream.readUnsignedByte();
		else
		if(type == 15)
			anInt761 = stream.readUnsignedByte();
		else
                if(type == 17)
                    aBoolean767 = false;
                else
                if(type == 18)
                    aBoolean757 = false;
                else
				
                //if(type == 19)
                //    hasActions = (stream.readUnsignedByte() == 1);//ENABLE TO FIND OBJECT IDs with BAD MODELS!!!!!
				if(type == 19) {
					flag = stream.readUnsignedByte();
					if(flag == 1)
						hasActions = true;
				}//Enable to have no names with null and whatever	
                else
                if(type == 21)
                    aBoolean762 = true;
                else
                if(type == 22)
                    aBoolean769 = true;
                else
                if(type == 23)
                    aBoolean764 = true;
                else
                if(type == 24)
                {
                    anInt781 = stream.readUnsignedWord();
                    if(anInt781 == 65535)
                        anInt781 = -1;
                } else
                if(type == 28)
                    anInt775 = stream.readUnsignedByte();
                else
                if(type == 29)
                    aByte737 = stream.readSignedByte();
                else
                if(type == 39)
                    aByte742 = stream.readSignedByte();
                else
                if(type >= 30 && type < 39)
                {
                    if(itemActions == null)
                        itemActions = new String[5];
                    itemActions[type - 30] = stream.readNewString();
                    if(itemActions[type - 30].equalsIgnoreCase("hidden"))
                        itemActions[type - 30] = null;
                } else
		if(type == 40)
                {
                    int i1 = stream.readUnsignedByte();
                    modifiedModelColors = new int[i1];
                    originalModelColors = new int[i1];
                    for(int i2 = 0; i2 < i1; i2++)
                    {
                        modifiedModelColors[i2] = stream.readUnsignedWord();
                        originalModelColors[i2] = stream.readUnsignedWord();
                    }

                } else
                if(type == 60)
                    anInt746 = stream.readUnsignedWord();
                else
                if(type == 62)
                    aBoolean751 = true;
                else
                if(type == 64)
                    aBoolean779 = false;
                else
                if(type == 65)
                    anInt748 = stream.readUnsignedWord();
                else
                if(type == 66)
                    anInt772 = stream.readUnsignedWord();
                else
                if(type == 67)
                    anInt740 = stream.readUnsignedWord();
                else
                if(type == 68)
                    anInt758 = stream.readUnsignedWord();
                else
                if(type == 69)
                    anInt768 = stream.readUnsignedByte();
                else
                if(type == 70)
                    anInt738 = stream.readSignedWord();
                else
                if(type == 71)
                    anInt745 = stream.readSignedWord();
                else
                if(type == 72)
                    anInt783 = stream.readSignedWord();
                else
		if(type == 73)
                    aBoolean736 = true;
                else
                if(type == 74)
                    aBoolean766 = true;
                else
		if(type == 75)
		    anInt760 = stream.readUnsignedByte();
		else
		if(type == 77)
		{
			anInt774 = stream.readUnsignedWord();
            		if(anInt774 == 65535)
                		anInt774 = -1;
            		anInt749 = stream.readUnsignedWord();
            		if(anInt749 == 65535)
                		anInt749 = -1;
            		int j1 = stream.readUnsignedByte();
            		childrenIDs = new int[j1 + 1];
            		for(int j2 = 0; j2 <= j1; j2++)
            		{
                		childrenIDs[j2] = stream.readUnsignedWord();
                		if(childrenIDs[j2] == 65535)
                    			childrenIDs[j2] = -1;
            		}
		}
	} while(true);
	if(flag == -1)
        {
            hasActions = anIntArray773 != null && (anIntArray776 == null || anIntArray776[0] == 10);
            if(itemActions != null)
                hasActions = true;
        }
        if(aBoolean766)
        {
            aBoolean767 = false;
            aBoolean757 = false;
        }
        if(anInt760 == -1)
            anInt760 = aBoolean767 ? 1 : 0;
    }

    private ObjectDef()
    {
        type = -1;
    }

    public boolean aBoolean736;
    private byte aByte737;
    private int anInt738;
    public String name;
    private int anInt740;
    private static final Model[] aModelArray741s = new Model[4];
    private byte aByte742;
    public int anInt744;
    private int anInt745;
    public int anInt746;
    private int[] originalModelColors;
    private int anInt748;
    public int anInt749;
    private boolean aBoolean751;
    public static boolean lowMem;
    private static Stream stream;
    public int type;
    private static int[] streamIndices;
    public boolean aBoolean757;
    public int anInt758;
    public int childrenIDs[];
    private int anInt760;
    public int anInt761;
    public boolean aBoolean762;
    public boolean aBoolean764;
    public static client clientInstance;
    private boolean aBoolean766;
    public boolean aBoolean767;
    public int anInt768;
    private boolean aBoolean769;
    private static int cacheIndex;
    private int anInt772;
    private int[] anIntArray773;
    public int anInt774;
    public int anInt775;
    private int[] anIntArray776;
    public byte description[];
    public boolean hasActions;
    public boolean aBoolean779;
    public static MRUNodes mruNodes2 = new MRUNodes(30);
    public int anInt781;
    private static ObjectDef[] cache;
    private int anInt783;
    private int[] modifiedModelColors;
    public static MRUNodes mruNodes1 = new MRUNodes(500);
    public String itemActions[];

}
