package com.zfw.jsonp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TIKULianjie {
	public static String host = "http://www.tiku.cn";
	static String sql = "INSERT INTO ti_all_a ( ti_a, ti_a_title) VALUES ( ?, ?)";

	
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException, SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zfw?useSSL=false", "root", "123");
		PreparedStatement ps = conn.prepareStatement(sql);
		Map<String,String> urls=new HashMap<>();
		urls.put("http://www.tiku.cn/index/index/questions?cid=15&cno=1", "高中化学");
		urls.put("http://www.tiku.cn/index/index/questions?cid=16&cno=1", "初中化学");
		
		urls.put("http://www.tiku.cn/index/index/questions?cid=12&cno=1", "初中物理");
		urls.put("http://www.tiku.cn/index/index/questions?cid=11&cno=1", "高中物理");
		
		urls.put("http://www.tiku.cn/index/index/questions?cid=18&cno=1", "初中历史");
		urls.put("http://www.tiku.cn/index/index/questions?cid=16&cno=1", "高中历史");
		
		urls.put("http://www.tiku.cn/index/index/questions?cid=7&cno=1", "初中生物");
		urls.put("http://www.tiku.cn/index/index/questions?cid=13&cno=1", "高中生物");
		
		urls.put("http://www.tiku.cn/index/index/questions?cid=5&cno=1", "初中地理");
		urls.put("http://www.tiku.cn/index/index/questions?cid=9&cno=1", "高中地理");
		
		urls.put("http://www.tiku.cn/index/index/questions?cid=6&cno=1", "初中思品");
		urls.put("http://www.tiku.cn/index/index/questions?cid=8&cno=1", "高中政治");
		Set<Entry<String,String>> entrySet = urls.entrySet();
		for (Entry<String, String> entry : entrySet) {
			String url = entry.getKey();
			String value = entry.getValue();
			List<String> typeIds=getTypeId(url);
			List<String> alls = getAllUnit(url);
			for (String typeId : typeIds) {
				for (String all : alls) {
					ps.setString(1, all+typeId);
					ps.setString(2, value);
					ps.executeUpdate();
				}
			}
			System.err.println(value+"执行完毕");
		}
		
		
	}

	// url http://www.tiku.cn/index/index/questions?cid=14&cno=1
	public static List<String> getAllUnit(String url) throws MalformedURLException, IOException, InterruptedException {
		List<String> alls = new ArrayList<>();
		List<String> vIds = getVIds(url);
		for (String vId : vIds) {
			List<String> bookIds = getBookIds(vId);
			for (String bookId : bookIds) {
				List<String> unitAndthrknowIds = getUnitAndthrknowId(bookId);
				for (String unitAndthrknowId : unitAndthrknowIds) {
					alls.add(unitAndthrknowId);
				}
			}
		}
		return alls;
	}

	// 根据cid学科 获取所有版本vids
	public static List<String> getVIds(String url) throws MalformedURLException, IOException {
		List<String> vidsLianjie = new ArrayList<>();
		Document doc = Jsoup.parse(new URL(url), 3000);
		Element bookElement = doc.getElementsByAttributeValue("class", "tk-tp-sub-list").get(1);
		Elements vATags = bookElement.getElementsByTag("a");
		for (Element vATag : vATags) {
			String vA = vATag.attr("href");
			vidsLianjie.add(host + vA);
		}
		return vidsLianjie;
	}

	// 根据cid学科，vid版本，获取所有的课本id
	public static List<String> getBookIds(String url) throws MalformedURLException, IOException {
		List<String> booksLianjie = new ArrayList<>();
		Document doc = Jsoup.parse(new URL(url), 3000);
		Element bookElement = doc.getElementsByAttributeValue("class", "tk-tp-sub-list").get(2);
		Elements bookATags = bookElement.getElementsByTag("a");
		for (Element bookATag : bookATags) {
			String bookA = bookATag.attr("href");
			booksLianjie.add(host + bookA);
		}
		return booksLianjie;
	}

	// 追加题型id
	public static List<String> getTypeId(String url) throws MalformedURLException, IOException {
		List<String> typeIds=new ArrayList<>();
		Document doc = Jsoup.parse(new URL(url), 3000);
		Element typeElement = doc.getElementsByAttributeValue("class", "tk-tp-sub-list").get(3);
		Elements typeElements = typeElement.getElementsByTag("a");
		for (Element type : typeElements) {
			StringBuffer typeId = new StringBuffer(type.attr("id").trim());
			if (!typeId.toString().equals("")) {
				String replaceAll = typeId.insert(3, "=").toString().replaceAll("qty", "typeid");
				typeIds.add(replaceAll);
			}
		}
		return typeIds;
	}

	// 根据cid学科，vid版本，bid课本来获取单元知识点连接
	public static List<String> getUnitAndthrknowId(String url)
			throws MalformedURLException, IOException, InterruptedException {
		List<String> unitTwoKnowThrknow = new ArrayList<>();
		Document doc = Jsoup.parse(new URL(url), 3000);
		Elements unitElements = doc.getElementsByAttributeValue("class", "ul-ques-li");
		for (Element unitElement : unitElements) {
			Element unitIdElement = unitElement.getElementsByAttributeValue("class", "tk-li-open").get(0);
			// 单元Id
			StringBuffer unitId = new StringBuffer(unitIdElement.attr("id"));
			unitId.insert(6, "=").append("&");
			// 单元名
			String unitText = unitElement.text().trim();
			Elements twoknowElements = unitElement.getElementsByTag("dl");
			for (Element twoknowElement : twoknowElements) {
				// 章节ID
				StringBuffer twoKnowId = new StringBuffer(twoknowElement.attr("id"));
				twoKnowId.insert(7, "=").append("&");
				Elements thrknowElements = twoknowElement.getElementsByTag("dd");
				for (Element thrknowElement : thrknowElements) {
					// 知识点id
					StringBuffer thrknowId = new StringBuffer(thrknowElement.attr("id"));
					thrknowId.insert(7, "id=").append("&");
					// 知识点名
					String thrKnowText = thrknowElement.text().trim();
					String unitTwoKnowThrknowurl = unitId.toString()
							+ twoKnowId.toString().replaceAll("twoknow", "chapterid") + thrknowId.toString();
					unitTwoKnowThrknow.add(url + "&" + unitTwoKnowThrknowurl);
				}
			}
		}
		return unitTwoKnowThrknow;
	}
}
