package firsttest.test.shoppingkuang.model.home.make;

import android.util.Log;

import java.util.HashMap;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.make.IHotGood;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class HotGoodModel extends BaseModel implements IHotGood.Model {
    @Override
    public void getHotGood(HashMap<String, String> map, CallBack callBack) {
        CommonSubscriber<HotGoodListBean> subscriber = HttpManager.getInstance().getService().getHotGoodList(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotGoodListBean>(callBack) {
                    @Override
                    public void onNext(HotGoodListBean hotGoodListBean) {
                        callBack.success(hotGoodListBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: " + t.toString());
                    }
                });
        addDisposible(subscriber);
    }

    @Override
    public void getHot(CallBack callBack) {
        HttpManager.getInstance().getService().getHotgood()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotGoodBean>(callBack) {
                    @Override
                    public void onNext(HotGoodBean hotGoodBean) {
                        callBack.success(hotGoodBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: "+t.toString());
                    }
                });
    }
}
