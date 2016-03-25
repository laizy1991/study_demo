package net.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import utils.FileUtils;

public class GitScan {
	private static final String SEARCH_URL = "https://github.com/search?p=${page}&q=${key}&type=Code&utf8=%E2%9C%93";
    public static void invoke(String targetURL, List<String> keys) {
        HttpURLConnection conn = null;
        try {
            // Create connection
            URL url = new URL(targetURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod( "GET");
            conn.setRequestProperty("Content-Type", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
            conn.setRequestProperty("Cookie", "_octo=GH1.1.1694828836.1457060335; logged_in=no; _gh_sess=eyJyZXR1cm5fdG8iOiIvbGFpenkxOTkxIiwibGFzdF93cml0ZSI6MTQ1ODcwODEzNjA1Miwic2Vzc2lvbl9pZCI6ImUxNjhlYTA2NjM1MTNiYTY0YTdlMWZlODgzY2Q1NDQ2Iiwic3B5X3JlcG8iOiJqczIyM2t6LzFkdjYwOF9yZWdpc3RlciIsInNweV9yZXBvX2F0IjoxNDU4NzA4MjYxLCJyZWZlcnJhbF9jb2RlIjoiaHR0cHM6Ly9naXRodWIuY29tL2xhaXp5MTk5MS9hcXVhbGF1ZGVyL3NlYXJjaD91dGY4PSVFMiU5QyU5MyZxPXBhc3N3b3JkIiwiX2NzcmZfdG9rZW4iOiJSU1VCK0lsbUZaMUJ1SHgwbml2U2F5cTdzYmV6QmxaY1dCYy9raFE0SmN3PSJ9--a05cbe9e215c43efd8e58d19ec4e8a81ad863823; tz=Asia%2FShanghai; _ga=GA1.2.1144533908.1457060335");
            conn.setRequestProperty("Host", "github.com");
            conn.setRequestProperty("Referer", "https://github.com/search?utf8=%E2%9C%93&q=pass&type=Code&ref=searchresults");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
            conn.setRequestProperty("Cache-Control", "max-age=0");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
            // Get Response
            InputStream is = conn.getInputStream();
            GZIPInputStream gzin = new GZIPInputStream(is);  
            
            BufferedReader bin = new BufferedReader(new InputStreamReader(gzin, "UTF8"));
            String line;
            while ((line = bin.readLine()) != null) {
            	line = decode(line);
            	if(!line.trim().isEmpty())
	            	for(String key : keys) {
	            		if(line.indexOf(key) > -1) {
	            			System.err.println(line.trim());
	            			FileUtils.write("qq_password.txt", line.trim() + "\n");
	            			break;
	            		}
	            	}
            }
        } catch (Exception e) {
            System.err.println(targetURL + e.toString());
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }


    public static void main(String args[]) throws MalformedURLException, InterruptedException {
   
    	/*String content = "&#39;host&#39; =&gt; &#39;smtp.qq.com&#39;";

        String regEx_ts = "\\&[^;]+;"; 
        java.util.regex.Pattern p_ts;  
        java.util.regex.Matcher m_ts;  
        p_ts = Pattern.compile(regEx_ts, Pattern.CASE_INSENSITIVE);  
        m_ts = p_ts.matcher(content);  
        content = m_ts.replaceAll("");  
        System.out.println(content);*/
    	for(int i=50; i<=100; i++) {
    		String key = "%22qq.com%22+%22password%22";
    		String url = SEARCH_URL.replace("${page}", ""+i);
    		url = url.replace("${key}", key);
    		List<String> keys = new ArrayList<String>();
    		keys.add("qq.com");
    		keys.add("password");
    		System.err.println(url);
    		invoke(url, keys);
    		Thread.sleep(1000);
    	}
        
    }
    public static String decode(String content) {
    	try {
			content = URLDecoder.decode(content, "utf8");
		} catch (Exception e) {
		}
    	java.util.regex.Pattern p_script;  
        java.util.regex.Matcher m_script;  
        java.util.regex.Pattern p_style;  
        java.util.regex.Matcher m_style;  
        java.util.regex.Pattern p_html;  
        java.util.regex.Matcher m_html;  
        java.util.regex.Pattern p_ts;  
        java.util.regex.Matcher m_ts;  
    	 //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>  
        String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";   
        //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>  
        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";   
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式   
        String regEx_ts = "\\&[^;]+;"; 
        p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        m_script = p_script.matcher(content);  
        content = m_script.replaceAll(""); // 过滤script标签  
        p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        m_style = p_style.matcher(content);  
        content = m_style.replaceAll(""); // 过滤style标签  
        p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        m_html = p_html.matcher(content);  
        content = m_html.replaceAll(""); // 过滤html标签  
        p_ts = Pattern.compile(regEx_ts, Pattern.CASE_INSENSITIVE);  
        m_ts = p_ts.matcher(content);  
        content = m_ts.replaceAll("");  
        return content;
    }
}
