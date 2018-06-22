package com.blog.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64Util {
	public static File base64ToImgFile(String base64Str,String fileName){
		File file=new File(fileName);
		sun.misc.BASE64Decoder base64Decoder=new sun.misc.BASE64Decoder();
		try{
            //解密
			   byte[] b = base64Decoder.decodeBuffer(base64Str);
            //处理数据
            for (int i = 0;i<b.length;++i){
                if(b[i]<0){
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(file);
            out.write(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return file;
	}
}
