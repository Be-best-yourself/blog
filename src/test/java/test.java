import java.util.HashMap;
import java.util.Map;

public class test {
	public static void main(String[] args) {
		String address = "http://www.tiku.cn/api/question/anylysis";
		String method = "post";
		Map<String, String> param = new HashMap<>();
		param.put("id", "3025442");
		
		param.put("Hm_lpvt_02f32149c7ea90d0cd47ed89025e457c", "1532053831");
		param.put("Hm_lvt_02f32149c7ea90d0cd47ed89025e457c", "1531752596,1531754406,1531974572,1532048713");
		param.put("PHPSESSID", "g8m3020c7ojrosppat3ii0nl8b");
		param.put("isLogin", "1");
		param.put("token", "0ddac7a32f7c89a0b00f67e46cb87100");
		param.put("userId", "14813");
		param.put("X-Requested-With", "XMLHttpRequest");
		HttpURLConnectionUtil.sendHttpURLConnection(address, method, param, new HttpCallbackListener() {
			
			@Override
			public void onFinish(String response) {
				System.out.println(response);
			}
			
			@Override
			public void onError(Exception e) {
				System.err.println(e);
			}
		});;
	}
}
