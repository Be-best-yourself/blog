
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Map;

public class HttpURLConnectionUtil {

    private static final String POST="POST";
    private static final String GET="GET";
    private static final String[] CHECKMETHOD={POST,GET};
    private static String METHOD=GET;
    /**
     * @param address  			请求地址
     * @param method			请求方式 GET	POST	(默认GET)
     * @param param				POST请求时，key=参数名		value=参数值
     * 							GET请求时无效，参数拼接
     * @param listener			请求成功或失败时回调
     */
    public static void sendHttpURLConnection(final String address,final String method, final Map<String,String> param, final HttpCallbackListener listener){

        if (Arrays.asList(CHECKMETHOD).contains(method.toUpperCase())) {
            METHOD=method.toUpperCase();
        }else{
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(METHOD);
                    connection.setConnectTimeout(2000);
                    connection.setReadTimeout(2000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    String analyticParam = analyticParam(param);
                    if ("POST".equals(METHOD)&& analyticParam !=null) {
                        DataOutputStream dos=new DataOutputStream(connection.getOutputStream());
                        dos.writeBytes(analyticParam);
                    }

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 解析map参数
     *
     * @param param
     * @return
     */
    private static String analyticParam(Map<String, String> param) {
        if (param==null){
            return null;
        }

        StringBuffer sb = new StringBuffer();
        try {
            for (Map.Entry<String, String> m : param.entrySet()) {
                sb.append(m.getKey());
                sb.append("=");
                sb.append(URLEncoder.encode(m.getValue(), "UTF-8"));
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}