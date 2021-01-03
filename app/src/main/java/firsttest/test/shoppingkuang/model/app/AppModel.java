package firsttest.test.shoppingkuang.model.app;

import android.util.Log;



import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.app.IApp;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class AppModel extends BaseModel implements IApp.Model {
    @Override
    public void getAppInfo(CallBack callback) {
        addDisposible(HttpManager.getInstance().getService().getAppInfo().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AppBean>(callback) {
                    @Override
                    public void onNext(AppBean appBean) {
                        Log.i("TAG","model onNext register");
                        callback.success(appBean);
                    }
                }));
    }
}
