// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)
import sign.signlink;

public final class Class36
{
	public static byte[] getData(int i1, int i2) {
		if (i1 == 0)
			return FileOperations.ReadFile(signlink.findcachedir() + "/Data/Animation/Frame/" + i2 + ".dat");
		else
			return FileOperations.ReadFile(signlink.findcachedir() + "/Data/Animation/Skin/" + i2 + ".dat");
	}

    public static void method528(int i)
    {
                animationlist = new Class36[4000][0];
    }
   public static void load(int file){
    try {
	    Stream stream = new Stream(DataBase.allFrames[file]);
        Stream stream1 = new Stream(DataBase.allSkinlist[file]);
		Class18 class18 = new Class18(stream1, 0);
        int k1 = stream.readUnsignedWord();
		animationlist[file] = new Class36[(int)(k1*1.5)];
	    int ai[] = new int[500];
        int ai1[] = new int[500];
        int ai2[] = new int[500];
        int ai3[] = new int[500];
        for(int l1 = 0; l1 < k1; l1++)
        {
            int i2 = stream.readUnsignedWord();
            Class36 class36 = animationlist[file][i2] = new Class36();
            class36.aClass18_637 = class18;
            int j2 = stream.readUnsignedByte();
            int l2 = 0;
			int k2 = -1;
            for(int i3 = 0; i3 < j2; i3++)
            {
                int j3 = stream.readUnsignedByte();
	
                if(j3 > 0)
                {
                    if(class18.anIntArray342[i3] != 0)
                    {
                        for(int l3 = i3 - 1; l3 > k2; l3--)
                        {
                            if(class18.anIntArray342[l3] != 0)
                                continue;
                            ai[l2] = l3;
                            ai1[l2] = 0;
                            ai2[l2] = 0;
                            ai3[l2] = 0;
                            l2++;
                            break;
                        }

                    }
                    ai[l2] = i3;
                    short c = 0;
                    if(class18.anIntArray342[i3] == 3)
                        c = (short)128;

                    if((j3 & 1) != 0)
                        ai1[l2] = (short)stream.readShort2();
                    else
                        ai1[l2] = c;
                    if((j3 & 2) != 0)
                        ai2[l2] = stream.readShort2();
                    else
                        ai2[l2] = c;
                    if((j3 & 4) != 0)
                        ai3[l2] = stream.readShort2();
                    else
                        ai3[l2] = c;
                    k2 = i3;
                    l2++;
            	}
	}

            class36.anInt638 = l2;
            class36.anIntArray639 = new int[l2];
            class36.anIntArray640 = new int[l2];
            class36.anIntArray641 = new int[l2];
            class36.anIntArray642 = new int[l2];
            for(int k3 = 0; k3 < l2; k3++)
            {
                class36.anIntArray639[k3] = ai[k3];
                class36.anIntArray640[k3] = ai1[k3];
                class36.anIntArray641[k3] = ai2[k3];
                class36.anIntArray642[k3] = ai3[k3];
            }

        }
      }catch(Exception exception) { }
    }
    public static Class36 method531(int j)
    {
                if(animationlist == null)
                        return null;
                String hex = Integer.toHexString(j);
                int file = Integer.parseInt(hex.substring(0,(hex.length()-4)), 16);
                int frame = Integer.parseInt(hex.substring((hex.length()-4)), 16);
                if(animationlist[file].length == 0)
                        load(file);
                return animationlist[file][frame];

        }

public static void nullLoader()
	{
		animationlist = null;
	}

   
        public static boolean method532(int i)
        {
                return i == -1;
        }

        private Class36()
        {
        }
    private static Class36 animationlist[][];
        public int anInt636;
        public Class18 aClass18_637;
        public int anInt638;
        public int anIntArray639[];
        public int anIntArray640[];
        public int anIntArray641[];
        public int anIntArray642[];
        @SuppressWarnings("unused")
		private static boolean[] aBooleanArray643;

}