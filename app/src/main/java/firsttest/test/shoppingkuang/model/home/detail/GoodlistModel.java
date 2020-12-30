package firsttest.test.shoppingkuang.model.home.detail;

import android.util.Log;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.detail.IGoodlist;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class GoodlistModel extends BaseModel implements IGoodlist.Model {

    @Override
    public void getGoodlist(int id, int page, int size, CallBack callBack) {
        CommonSubscriber<GoodListBean> subscriber = HttpManager.getInstance().getService().getGoodlist(id, page, size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodListBean>(callBack) {
                    @Override
                    public void onNext(GoodListBean goodListBean) {
                        callBack.success(goodListBean);
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
