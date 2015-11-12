package net.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Download {

    public static void invoke(String targetURL, String urlParameters) {
        HttpURLConnection conn = null;
        try {
            // Create connection
            URL url = new URL(targetURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization",
                    "Basic d3d3LmxpbnV4aWRjLmNvbTp3d3cubGludXhpZGMuY29t");

            // Get Response
            InputStream is = conn.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            urlDetector(response.toString());
        } catch (Exception e) {
            System.err.println(targetURL + e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static void downloadNet(String filePath) throws MalformedURLException {
        // 下载网络文件
        int byteread = 0;

        URL url = new URL(filePath);
        FileOutputStream fs = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization",
                    "Basic d3d3LmxpbnV4aWRjLmNvbTp3d3cubGludXhpZGMuY29t");
            InputStream inStream = conn.getInputStream();
            
            String savePath = getSavePath(filePath);
            System.err.println(savePath);
            fs = new FileOutputStream(savePath);

            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    System.err.println(e.toString());
                }
            }
        }
    }

    private static String getSavePath(String filePath) {
        try {
            filePath = URLDecoder.decode(filePath, "utf-8");
            if(filePath.length() < 26) {
                return null;
            }
            
            String subString = filePath.substring(26);
            String[] splits = subString.split("/");
            if(splits.length < 1) {
                return null;
            }
            
            String retPath = "D:/myspace/download/";
            for(int i=0; i<splits.length-1; i++) {
                retPath += splits[i] + "/";
                File file = new File(retPath);
                if(!file.exists() || file.isFile()) {
                    file.mkdir();
                }
            }
            
            return retPath + splits[splits.length-1];
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.toString());
        }
        return null;
    }

    public static void main(String args[]) throws MalformedURLException {
        allURL.add("http://linux.linuxidc.com/");
        anlyURL.add("http://linux.linuxidc.com/");
        while (anlyURL.size() > 0) {
            Object[] urls = anlyURL.toArray();
            for (Object url : urls) {
                invoke((String) url, "");
                anlyURL.remove(url);
            }
        }

        System.err.println("解析完所有url,需要下载的文件有" + downloadURL.size() + "个");
        Iterator<String> ite = downloadURL.iterator();
        while(ite.hasNext()) {
            String url = ite.next();
            downloadNet(url);
        }
        
    }

    private static Set<String> downloadURL = new HashSet<String>();
    private static Set<String> anlyURL = new HashSet<String>();
    private static Set<String> allURL = new HashSet<String>();

    public static void urlDetector(String htmlDoc) {
        final String patternString = "<[a|A]\\s+href=([^>]*\\s*>)";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlDoc);
        String tempURL; // 初次匹配到的url是形如：<a href="http://bbs.life.xxx.com.cn/" target="_blank">
        // 为此，需要进行下一步的处理，把真正的url抽取出来，
        // 可以对于前两个"之间的部分进行记录得到url
        while (matcher.find()) {
            try {
                tempURL = matcher.group();
                if(tempURL.contains("href=")) {
                    tempURL = tempURL.split("href=\"")[1];
                } else {
                    tempURL = tempURL.split("HREF=\"")[1];
                }
                tempURL = tempURL.substring(0, tempURL.indexOf("\""));

                if(tempURL.startsWith("/")) {
                    tempURL = "http://linux.linuxidc.com" + tempURL;
                }
                if (allURL.contains(tempURL)) {
                    continue;
                }

                allURL.add(tempURL);
                if (tempURL.endsWith("/")) {
                    anlyURL.add(tempURL);
                } else {
                    String[] split = tempURL.split("/");
                    String endSub = split[split.length - 1];
                    if (endSub.contains(".")) {
                        downloadURL.add(tempURL);
                    } else {
                        anlyURL.add(tempURL);
                    }
                }

            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }
}
