package com.zfw.test.freemark;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class Test {
	public static void main(String[] args) throws Exception {
		 // 创建 Freemarker 配置实例
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        // 指定模板文件从何处加载的数据源,这里设置成一个文件目录。
        cfg.setDirectoryForTemplateLoading(new File("templates"));
        cfg.setDefaultEncoding("UTF-8");
        // 简单地重新抛出异常; 这应该在大多数生产系统中使用。
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 创建一个数据模型
        Map root = new HashMap();
        root.put("user", "Big Joe");
        Map latest = new HashMap();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        // 获取模板（使用内部缓存）
        Template temp = cfg.getTemplate("test2.ftl");

        // 合并数据模型模板
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
        out.flush();
        out.close();
        // 注意: ------------
        // 为了简单起见,这里压制了异常(在方法签名中声明了异常,译者注),而在正式运行的产品中不要这样做。
	}
}
