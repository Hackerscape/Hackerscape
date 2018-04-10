// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Update.java

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Update extends Thread
{

    public static void main(String args[])
    {
    }

    public Update(String s, String s1, String s2)
    {
        homeDir = s2;
        saveAs = (new StringBuilder()).append(s2).append(s1).toString();
        urlLoc = s;
         if(!(new File(s2)).exists())
            (new File(s2)).mkdir();
        if(downloadFile())
        {
            unZipFile();
            deleteFile();
        } else
        {
            System.out.println("error");
            deleteFile();
        }
    }

    private void writeStream(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        byte abyte0[] = new byte[1024];
        int i;
        while((i = inputstream.read(abyte0)) >= 0) 
            outputstream.write(abyte0, 0, i);
        inputstream.close();
        outputstream.close();
    }

    private void unZipFile()
    {
        try
        {
            ZipFile zipfile = new ZipFile(saveAs);
            for(@SuppressWarnings("rawtypes")
			Enumeration enumeration = zipfile.entries(); enumeration.hasMoreElements();)
            {
                ZipEntry zipentry = (ZipEntry)enumeration.nextElement();
                if(zipentry.isDirectory())
                {
                    if(checkProgress)
                        System.out.println((new StringBuilder()).append("Creating Directory: ").append(zipentry.getName()).toString());
                    (new File((new StringBuilder()).append(homeDir).append(zipentry.getName()).toString())).mkdir();
                } else
                {
                    if(checkProgress)
                        System.out.println((new StringBuilder()).append("Extracting File: ").append(zipentry.getName()).toString());
                    writeStream(zipfile.getInputStream(zipentry), new BufferedOutputStream(new FileOutputStream((new StringBuilder()).append(homeDir).append(zipentry.getName()).toString())));
                }
            }

            zipfile.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void deleteFile()
    {
        try
        {
            File file = new File(saveAs);
            file.delete();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private boolean downloadFile()
    {
 try
        {


        if(checkProgress)
            System.out.println((new StringBuilder()).append("Downloading ").append(saveAs).append("...").toString());
        HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(urlLoc)).openConnection();
     
        HttpURLConnection.setFollowRedirects(true);
        httpurlconnection.setInstanceFollowRedirects(false);
        httpurlconnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.0.3705;)");
        long l = httpurlconnection.getContentLength();
        new ProgressChecker(saveAs, l);
	
        FileOutputStream fileoutputstream = new FileOutputStream(saveAs);
        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(fileoutputstream);
        writeStream(httpurlconnection.getInputStream(), bufferedoutputstream);
        fileoutputstream.close();
        bufferedoutputstream.close();
        @SuppressWarnings("unused")
		File file = new File(saveAs);

            if(checkProgress)
                System.out.println((new StringBuilder()).append(saveAs).append(" downloaded...").toString());
            return true;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return false;
    }

    private String homeDir;
    private String saveAs;
    private String urlLoc;
    private boolean checkProgress;
}
