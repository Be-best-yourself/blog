package com.zfw.jsonp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TIKU {
	static String sql = "INSERT INTO ti " + "(ti_type, ti_timu, ti_xuanxiang, ti_level, "
			+ "ti_banben, ti_xueke, ti_banji, ti_banji_cemu, ti_zhangjie, ti_dazhangjie,ti_xiaozhangjie, "
			+ "ti_from, ti_from_id) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	static String selctsql="SELECT ti_a FROM ti_all_a where ti_a_title='高中数学'";
	static String selctcountsql="SELECT COUNT(1),id FROM ti_all_a where ti_a_title='高中数学'";
	static Map<Integer, String> tixing = new HashMap<>();
	static int i=0;
	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, MalformedURLException, IOException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zfw?useSSL=false", "root", "123");
		PreparedStatement ps = conn.prepareStatement(sql);
		PreparedStatement statement = conn.prepareStatement(selctsql);
		ResultSet countquery = conn.prepareStatement(selctcountsql).executeQuery();
		ResultSet query = statement.executeQuery();
		int count=0;
		if (countquery.next()) {
			count= countquery.getInt(1);
		}
		while (query.next()) {
			i++;
			String string = query.getString(1);
			paTiKu(string, ps);
			System.err.println("一共"+count+"个，执行到"+i);
		}
	}

	public static void paTiKu(String url, PreparedStatement ps) throws IOException {
		int page = 200;
		for (int i = 1; i <= page; i++) {
			url += "&page=" + i;
			String typeid = url.substring(url.indexOf("typeid=") + 7, url.indexOf("typeid=") + 13);
			String unitid = url.substring(url.indexOf("unitid=") + 7, url.indexOf("unitid=") + 13);
			String thrknow = url.substring(url.indexOf("thrknowid=") + 10, url.indexOf("thrknowid=") + 16);
			String chapterid = url.substring(url.indexOf("chapterid=") + 10, url.indexOf("chapterid=") + 16);
			String vid = url.substring(url.indexOf("vid=") + 4, url.indexOf("vid=") + 10);
			String bid = url.substring(url.indexOf("bid=") + 4, url.indexOf("bid=") + 10);

			Document parse = Jsoup.parse(new URL(url), 3000);
			String tixing = parse.getElementsByAttributeValue("id", "qty" + typeid).text();
			String zhangjie = parse.getElementsByAttributeValue("id", "unitid" + unitid).text();
			String dazhangjie = parse.getElementsByAttributeValue("id", "twoknow" + chapterid).get(0)
					.getElementsByAttributeValue("class", "dl-open").text();
			String xiaozhangjie = parse.getElementsByAttributeValue("id", "thrknow" + thrknow).text();
			String banben = parse.getElementsByAttributeValue("id", "version" + vid).text();
			String banji = parse.getElementsByAttributeValue("id", "book" + bid).text();
			String xueke = parse.getElementsByAttributeValue("class", "ml-2 course lead").text().split("：")[1];
			Elements TIs = parse.getElementsByAttributeValue("class", "card mb-3 q-detail rounded-0");
			if (TIs.text().equals("")) {
				return;
			}
			for (Element ti : TIs) {
				Element idElement = ti.getElementsByAttributeValue("class", "q-analysis text-l").get(0);
				Element levelElement = ti
						.getElementsByAttributeValue("class", "align-self-center text-muted text-nowrap").get(0);
				String timu = ti.getElementsByTag("section").get(0).html().replaceAll("\n", "")
						.replaceAll("<br><div align=\"right\">", "").replaceAll("</div>", "").replaceAll("<div>", "");
				String daan = "";
				try {
					if (tixing.indexOf("选题") != -1) {
						Elements daanElemets = ti.getElementsByAttributeValue("class", "row");
						daan = daanElemets.get(0).html().replaceAll("\n", "").replaceAll("<div class=\"col-lg-6\">",
								"");
					}
				} catch (IndexOutOfBoundsException e1) {
					daan="网页答案不规范，答案在题目中";
				}
				String id = idElement.attr("id").replaceAll("ana", "");
				String level = levelElement.text();
				try {
					ps.setString(1, tixing);
					ps.setString(2, timu);
					ps.setString(3, daan);
					ps.setString(4, level);
					ps.setString(5, banben);
					ps.setString(6, xueke);
					ps.setString(7, banji);
					ps.setString(8, banji);
					ps.setString(9, zhangjie);
					ps.setString(10, dazhangjie);
					ps.setString(11, xiaozhangjie);
					ps.setString(12, url);
					ps.setString(13, id);
					ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

	public static void jsoupPost() {
		Map<String, String> cookies = new HashMap<>();

		cookies.put("token", "0ddac7a32f7c89a0b00f67e46cb87100");
		cookies.put("userId", "14813");
		cookies.put("isLogin", "1");
		cookies.put("PHPSESSID", "8be1skkps9ipaj0t706ug95k0a");
		cookies.put("Hm_lvt_02f32149c7ea90d0cd47ed89025e457c", "1532048713,1532097305,1532151530,1532160965");
		cookies.put("Hm_lpvt_02f32149c7ea90d0cd47ed89025e457c", "1532161212");
		cookies.put("HMACCOUNT", "51BAF2D85612490B");
		Map<String, String> data = new HashMap<String, String>();
		data.put("id", "3025442");
		org.jsoup.Connection con = Jsoup.connect("http://www.tiku.cn/api/question/regetq/3025480")
				.ignoreContentType(true).cookies(cookies)
				.userAgent(
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
				.referrer("http://www.tiku.cn/index/index/questions?cid=14&cno=1&typeid=600126").ignoreHttpErrors(false)
				.data(data);
		// con.header("Accept", "application/json, text/javascript, */*;
		// q=0.01");
		// con.header("Referer",
		// "http://www.tiku.cn/index/index/questions?cid=14&cno=1&typeid=600126");
		// con.header("Accept-Encoding", "gzip, deflate");
		// con.header("Accept-Language", "zh-CN,zh;q=0.9");
		// con.header("Connection", "keep-alive");
		// con.header("Content-Type", "application/x-www-form-urlencoded;
		// charset=UTF-8");
		// con.header("Referer",
		// "http://www.tiku.cn/index/index/questions?cid=14&cno=1&typeid=600126");
		// con.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)
		// AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99
		// Safari/537.36");
		con.header("X-Requested-With", "XMLHttpRequest");
		// con.header("Host", "www.tiku.cn");
		// con.header("Origin", "http://www.tiku.cn");
		Document post;
		try {
			post = con.post();
			System.out.println(post);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
