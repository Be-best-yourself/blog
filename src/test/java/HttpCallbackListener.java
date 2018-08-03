
/**
 * Created by zfw on 18-1-2.
 *
 *        用于HttpURLConnectionUtil获取数据后回调更新主线程UI
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
