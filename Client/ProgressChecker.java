import java.io.File;

@SuppressWarnings("serial")
class ProgressChecker extends RSApplet {
    public static int per = 0;
    public static int per2 = 0;
    public String filetocheck;
    public long filelength;

    public ProgressChecker(String s, long l) {
        filetocheck = s;
        filelength = l;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        long l = 0L;
        @SuppressWarnings("unused")
		long l1 = 0L;
        per = 0;
        File file = new File(filetocheck);
        do {
            if(l > filelength) {
                break;
            }
            try {
                Thread.sleep(5L);
            } catch(Exception ex) {
				ex.printStackTrace();
			}
            @SuppressWarnings("unused")
			long l2 = l;
            l = file.length();
            per = (int)(((double)l / (double)filelength) * 100D);
            if(per > per2) {
                per2 = per;
                try {
					client.instance.drawLoadingText(per, per != 100 ? "Loading Cache Library " + per + "%" : "Decompressing Code Library...");
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        } while(true);
    }
}