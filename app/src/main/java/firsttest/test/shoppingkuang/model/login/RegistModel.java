package firsttest.test.shoppingkuang.model.login;

import android.util.Log;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.login.IRegist;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class RegistModel extends BaseModel implements IRegist.Model {
    @Override
    public void regist(String username, String pw, CallBack callBack) {
        addDisposible(HttpManager.getInstance().getService().reister(username, pw)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<RegisterBean>(callBack) {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        callBack.success(registerBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: "+t.toString());
                    }
                }));
    }
}
