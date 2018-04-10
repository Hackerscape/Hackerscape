import java.io.PrintStream;
import sign.signlink;

@SuppressWarnings("unused")
public final class EntityDef
{

    public static int NPCAMOUNT = 11599;
    public int anInt55;
    public static int anInt56;
    public int anInt57;
    public int anInt58;
    public int anInt59;
    public static Stream stream;
    public int combatLevel;
    public final int anInt64 = 1834;
    public String name;
    public String itemActions[];
    public int anInt67;
    public byte aByte68;
    public int anIntArray70[];
    public static int streamIndices[];
    public int anIntArray73[];
    public int anInt75;
    public int anIntArray76[];
    public int anInt77;
    public long interfaceType;
    public int anInt79;
    public static EntityDef cache[];
    public static client clientInstance;
    public int anInt83;
    public boolean aBoolean84;
    public int anInt85;
    public int anInt86;
    public boolean aBoolean87;
    public int childrenIDs[];
    public byte description[];
    public int anInt91;
    public int anInt92;
    public boolean aBoolean93;
    public int anIntArray94[];
    public static MRUNodes mruNodes = new MRUNodes(30);

    public static EntityDef forID(int i)
    {
        for(int j = 0; j < 20; j++)
        {
            if(cache[j].interfaceType == (long)i)
            {
                return cache[j];
            }
        }

        anInt56 = (anInt56 + 1) % 20;
        EntityDef entitydef = cache[anInt56] = new EntityDef();
        stream.currentOffset = streamIndices[i];
        entitydef.interfaceType = i;
        entitydef.readValues(stream);
        return entitydef;
    }

    public Model method160()
    {
        if(childrenIDs != null)
        {
            EntityDef entitydef = method161();
            if(entitydef == null)
            {
                return null;
            } else
            {
                return entitydef.method160();
            }
        }
        if(anIntArray73 == null)
        {
            return null;
        }
        boolean flag = false;
        for(int i = 0; i < anIntArray73.length; i++)
        {
            if(!Model.method463(anIntArray73[i]))
            {
                flag = true;
            }
        }

        if(flag)
        {
            return null;
        }
        Model amodel[] = new Model[anIntArray73.length];
        for(int j = 0; j < anIntArray73.length; j++)
        {
            amodel[j] = Model.method462(anIntArray73[j]);
        }

        Model model;
        if(amodel.length == 1)
        {
            model = amodel[0];
        } else
        {
            model = new Model(amodel.length, amodel);
        }
        if(anIntArray76 != null)
        {
            for(int k = 0; k < anIntArray76.length; k++)
            {
                model.method476(anIntArray76[k], anIntArray70[k]);
            }

        }
        return model;
    }

    public EntityDef method161() {
		try {
			int j = -1;
			if(anInt57 != -1)
			{
				VarBit varBit = VarBit.cache[anInt57];
				int k = varBit.anInt648;
				int l = varBit.anInt649;
				int i1 = varBit.anInt650;
				int j1 = client.anIntArray1232[i1 - l];
				j = clientInstance.variousSettings[k] >> l & j1;
			} else
			if(anInt59 != -1) {
				j = clientInstance.variousSettings[anInt59];
			}
			if(j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
				return null;
			} else {
				return forID(childrenIDs[j]);
			}
		} catch (Exception e) {
			return null;
		}
	}

    public static byte[] getData(String s)
    {
        return FileOperations.ReadFile(s);
    }

    public static void unpackConfig(NamedArchive namedarchive)
    {
        stream = new Stream(getData((new StringBuilder()).append(signlink.findcachedir()).append("npc.dat").toString()));
        Stream bytebuffer = new Stream(getData((new StringBuilder()).append(signlink.findcachedir()).append("npc.idx").toString()));
        int i = bytebuffer.readUnsignedWord();
        System.out.println((new StringBuilder()).append("602 NPC Amount: ").append(i).toString());
        streamIndices = new int[i];
        int j = 2;
        for(int k = 0; k < i; k++)
        {
            streamIndices[k] = j;
            j += bytebuffer.readUnsignedWord();
        }

        cache = new EntityDef[20];
        for(int l = 0; l < 20; l++)
        {
            cache[l] = new EntityDef();
        }

    }

    public static void nullLoader()
    {
        mruNodes = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    public Model method164(int i, int j, int ai[])
    {
        if(childrenIDs != null)
        {
            EntityDef entitydef = method161();
            if(entitydef == null)
            {
                return null;
            } else
            {
                return entitydef.method164(i, j, ai);
            }
        }
        Model model = (Model)mruNodes.insertFromCache(interfaceType);
        if(model == null)
        {
            boolean flag = false;
            for(int k = 0; k < anIntArray94.length; k++)
            {
                if(!Model.method463(anIntArray94[k]))
                {
                    flag = true;
                }
            }

            if(flag)
            {
                return null;
            }
            Model amodel[] = new Model[anIntArray94.length];
            for(int l = 0; l < anIntArray94.length; l++)
            {
                amodel[l] = Model.method462(anIntArray94[l]);
            }

            if(amodel.length == 1)
            {
                model = amodel[0];
            } else
            {
                model = new Model(amodel.length, amodel);
            }
            if(anIntArray76 != null)
            {
                for(int i1 = 0; i1 < anIntArray76.length; i1++)
                {
                    model.method476(anIntArray76[i1], anIntArray70[i1]);
                }

            }
            model.method469();
            model.method479(84 + anInt85, 1000 + anInt92, -90, -580, -90, true);
            mruNodes.removeFromCache(model, interfaceType);
        }
        Model model1 = Model.aModel_1621;
        model1.method464(model, Class36.method532(j) & Class36.method532(i));
        if(j != -1 && i != -1)
        {
            model1.method471(ai, i, j);
        } else
        if(j != -1)
        {
            model1.method470(j);
        }
        if(anInt91 != 128 || anInt86 != 128)
        {
            model1.method478(anInt91, anInt91, anInt86);
        }
        model1.method466();
        model1.anIntArrayArray1658 = (int[][])null;
        model1.anIntArrayArray1657 = (int[][])null;
        if(aByte68 == 1)
        {
            model1.aBoolean1659 = true;
        }
        return model1;
    }

    public void readValues(Stream bytebuffer)
    {
        do
        {
            int i = bytebuffer.readUnsignedByte();
            if(i == 0)
            {
                return;
            }
            if(i == 1)
            {
                int j = bytebuffer.readUnsignedByte();
                anIntArray94 = new int[j];
                int j1 = 0;
                while(j1 < j) 
                {
                    anIntArray94[j1] = bytebuffer.readUnsignedWord();
                    j1++;
                }
            } else
            if(i == 2)
            {
                name = bytebuffer.readString();
            } else
            if(i == 3)
            {
                description = bytebuffer.readBytes();
            } else
            if(i == 12)
            {
                aByte68 = bytebuffer.readSignedByte();
            } else
            if(i == 13)
            {
                anInt77 = bytebuffer.readUnsignedWord();
            } else
            if(i == 14)
            {
                anInt67 = bytebuffer.readUnsignedWord();
            } else
            if(i == 17)
            {
                anInt67 = bytebuffer.readUnsignedWord();
                anInt58 = bytebuffer.readUnsignedWord();
                anInt83 = bytebuffer.readUnsignedWord();
                anInt55 = bytebuffer.readUnsignedWord();
                if(anInt67 == 65535)
                {
                    anInt67 = -1;
                }
                if(anInt58 == 65535)
                {
                    anInt58 = -1;
                }
                if(anInt83 == 65535)
                {
                    anInt83 = -1;
                }
                if(anInt55 == 65535)
                {
                    anInt55 = -1;
                }
            } else
            if(i >= 30 && i < 40)
            {
                if(itemActions == null)
                {
                    itemActions = new String[5];
                }
                itemActions[i - 30] = bytebuffer.readString();
                if(itemActions[i - 30].equalsIgnoreCase("hidden"))
                {
                    itemActions[i - 30] = null;
                }
            } else
            if(i == 40)
            {
                int k = bytebuffer.readUnsignedByte();
                anIntArray70 = new int[k];
                anIntArray76 = new int[k];
                int k1 = 0;
                while(k1 < k) 
                {
                    anIntArray76[k1] = bytebuffer.readUnsignedWord();
                    anIntArray70[k1] = bytebuffer.readUnsignedWord();
                    k1++;
                }
            } else
            if(i == 60)
            {
                int l = bytebuffer.readUnsignedByte();
                anIntArray73 = new int[l];
                int l1 = 0;
                while(l1 < l) 
                {
                    anIntArray73[l1] = bytebuffer.readUnsignedWord();
                    l1++;
                }
            } else
            if(i == 90)
            {
                bytebuffer.readUnsignedWord();
            } else
            if(i == 91)
            {
                bytebuffer.readUnsignedWord();
            } else
            if(i == 92)
            {
                bytebuffer.readUnsignedWord();
            } else
            if(i == 93)
            {
                aBoolean87 = false;
            } else
            if(i == 95)
            {
                combatLevel = bytebuffer.readUnsignedWord();
            } else
            if(i == 97)
            {
                anInt91 = bytebuffer.readUnsignedWord();
            } else
            if(i == 98)
            {
                anInt86 = bytebuffer.readUnsignedWord();
            } else
            if(i == 99)
            {
                aBoolean93 = true;
            } else
            if(i == 100)
            {
                anInt85 = bytebuffer.readSignedByte();
            } else
            if(i == 101)
            {
                anInt92 = bytebuffer.readSignedByte() * 5;
            } else
            if(i == 102)
            {
                anInt75 = bytebuffer.readUnsignedWord();
            } else
            if(i == 103)
            {
                anInt79 = bytebuffer.readUnsignedWord();
            } else
            if(i == 106)
            {
                anInt57 = bytebuffer.readUnsignedWord();
                if(anInt57 == 65535)
                {
                    anInt57 = -1;
                }
                anInt59 = bytebuffer.readUnsignedWord();
                if(anInt59 == 65535)
                {
                    anInt59 = -1;
                }
                int i1 = bytebuffer.readUnsignedByte();
                childrenIDs = new int[i1 + 1];
                int i2 = 0;
                while(i2 <= i1) 
                {
                    childrenIDs[i2] = bytebuffer.readUnsignedWord();
                    if(childrenIDs[i2] == 65535)
                    {
                        childrenIDs[i2] = -1;
                    }
                    i2++;
                }
            } else
            if(i == 107)
            {
                aBoolean84 = false;
            }
        } while(true);
    }

    public EntityDef()
    {
        anInt55 = -1;
        anInt57 = -1;
        anInt58 = -1;
        anInt59 = -1;
        combatLevel = -1;
        anInt67 = -1;
        aByte68 = 1;
        anInt75 = -1;
        anInt77 = -1;
        interfaceType = -1L;
        anInt79 = 32;
        anInt83 = -1;
        aBoolean84 = true;
        anInt86 = 128;
        aBoolean87 = true;
        anInt91 = 128;
        aBoolean93 = false;
    }

}