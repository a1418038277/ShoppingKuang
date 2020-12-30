package firsttest.test.shoppingkuang.model.home;

import android.util.Log;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.IHome;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class HomeModel extends BaseModel implements IHome.Model {
    @Override
    public void getHome(CallBack callBack) {
        CommonSubscriber<HomeBean> subscriber = HttpManager.getInstance().getService().getHome()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeBean>(callBack) {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        callBack.success(homeBean);

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: "+t.toString());
                    }
                });
        addDisposible(subscriber);
    }
}
