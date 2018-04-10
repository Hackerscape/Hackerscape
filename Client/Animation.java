// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
import sign.signlink;
public final class Animation {

	public static String loc = signlink.findcachedir()+"Data/Animation/";
	public static byte[] getData(String s) {
		return FileOperations.ReadFile(s);
	}
public static int[] lol1 = {97910860,97910798,97910886,97910826,97910880,97910787,97910793,97910873,97910876,97910814,97910848,97910861,97910900,97910803,97910862,97910905,97910895,97910843,97910825,97910840,97910878,97910853,97910818,97910891,97910799,97910799,97910828,97910850,97910850,97910832,97910813,97910824,97910835,97910785,97910821,97910801,97910810,97910822,97910872,97910805,97910792,97910868,97910906,97910858,97910864,97910839,97910893,97910841,97910869,97910786,97910875,97910823,97910807,97910797,97910819,97910904,97910877,97910866,97910890,97910827,97910837,97910796,97910898,97910811,97910902,97910827,97910837,97910796,97910898,97910811,97910902,97910889,97910817,97910802,97910809,97910885,97910879,97910845,97910892,97910863,97910812,97910830,97910881,97910831,97910784,97910847,97910884,97910851,97910867,97910896,97910894,97910836,97910874};
public static int[] lol2 = {7,5,5,5,3,5,5,5,5,5,5,5,5,5,5,8,5,7,5,5,5,5,5,5,10,30,15,7,15,3,8,5,5,5,5,3,8,5,5,5,5,6,5,4,5,5,5,5,5,6,5,5,5,6,5,5,5,5,5,6,5,5,5,5,5,6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,1,5,1,5,1,5,1,5};
    public static void unpackConfig(NamedArchive archive)
    {
       	Stream stream = new Stream(getData(loc+"seq.dat"));
        int length = stream.readUnsignedWord();
        if(anims == null)
            anims = new Animation[length];
        for(int j = 0; j < length; j++)
        {
            if(anims[j] == null)
                anims[j] = new Animation();
            anims[j].readValues(stream);
			if(j == 115)
			{
				anims[j].anInt352 = lol1.length;
			  anims[j].anIntArray353 = lol1;
			  anims[j].anIntArray355 = lol2;
			}
			for(int c : UNWALKABLE) {
					 if(j == c) {
						anims[j].anInt363 = 0;
						anims[j].anInt364 = 0;
					}
			}
			for(int b : NO_WEAPON) {
					if(j == b) {
						anims[j].anInt360 = 0+512;
						anims[j].anInt361 = 0+512;
					}
				}
			  /*for(int a : ATTACK_ANIMS) {
			 		 if(j == a) {
			  			anims[j].anInt360 = 0;
						anims[j].anInt361 = 0;
			 		 }
			  	}*/
        }
    }

public static final int[] ATTACK_ANIMS = {829, 883,624,827,791,881,832,12565,
					  8939,8941,9599,4959,4981,4971,4979,4939,4951,4975,4977,4965,4967,4947,9597,
					  8525,855,856,857,858,859,860,861,862,863,864,865,866,2105,2106,2107,2108,2109,2110,
					  2111,2112,2113,0x558,0x46B,0x469,0x46A,0x468,0x84F,0x850,2836,3544,3543,4276,4278,4280,
					  4275,7272,6111,7531,2414,8770,9990,10530
					 
};
public static final int[] UNWALKABLE = {
		12565, 12567, 12589, 12575, 12573, 4410, 4411, 10961,426, 8939, 9606, 9599, 8941, 714,
	};
public static final int[] NO_WEAPON = {
		12565, 12567, 12589, 12575, 829, 827,
	};
    public int method258(int i)
    {
        int j = anIntArray355[i];
        if(j == 0)
        {
            Class36 class36 = Class36.method531(anIntArray353[i]);
            if(class36 != null)
                j = anIntArray355[i] = class36.anInt636;
        }
        if(j == 0)
            j = 1;
        return j;
    }

    private void readValues(Stream stream)
    {
                        anInt360 = stream.readUnsignedWord();
                            anInt361 = stream.readUnsignedWord();
                        anInt359 = stream.readUnsignedByte();
                        anInt356 = stream.readUnsignedWord();
                        anInt352 = stream.readUnsignedWord();
                        anIntArray353 = new int[anInt352];
                        anIntArray354 = new int[anInt352];
                        anIntArray355 = new int[anInt352];
                            if (anInt360 == 65535)
                                anInt360 = -1;
                        if (anInt360 > 0)
                                    anInt360 += 512;
                        if (anInt361 == 65535)
                                    anInt361 = -1;
                            if (anInt361 > 0)
                                anInt361 += 512;
                        for(int i=0; i < anInt352; i++)
                                anIntArray353[i] = stream.readDWord();
                        for(int i=0; i < anInt352; i++)
                                anIntArray354[i] = -1;
                        for(int i=0; i < anInt352; i++)
                                    anIntArray355[i] = stream.readUnsignedByte();
    }

    private Animation()
    {
        anInt356 = -1;
        aBoolean358 = false;
        anInt359 = 5;
        anInt360 = -1;
        anInt361 = -1;
        anInt362 = 99;
        anInt363 = -1;
        anInt364 = -1;
        anInt365 = 2;
    }

    public static Animation anims[];
    public int anInt352;
    public int anIntArray353[];
    public int anIntArray354[];
    private int[] anIntArray355;
    public int anInt356;
    public int anIntArray357[];
    public boolean aBoolean358;
    public int anInt359;
    public int anInt360;
    public int anInt361;
    public int anInt362;
    public int anInt363;
    public int anInt364;
    public int anInt365;
    public static int anInt367;
}