package com.zfw.jsonp;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.aliyun.oss.OSSClient;

public class imageDownload {
	static int i=0;
	private static OSSClient ossClient = new OSSClient("http://oss-cn-beijing.aliyuncs.com", "LTAI0GiBUUs19jRn", "npk3NLqbo87KfQhQoM1OIdg9jDJlIe");
	private String bucketName = null;
	private String key = null;
	private File file = null;
	private InputStream in = null;
    public static void main(String[] args) {
    	for (i = 0; i < 10000; i++) {
    		String url = "https://picsum.photos/200/300/?image="+i;
    		downloadPicture(url);
    		System.out.println(i);
		}
    }
    //链接url下载图片
    private static void downloadPicture(String urlList) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            ossClient.putObject("blog-uploadfile-test", "system/drag-picture/"+i+".jpg", dataInputStream);
            dataInputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}