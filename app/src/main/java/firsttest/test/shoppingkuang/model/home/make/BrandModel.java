package firsttest.test.shoppingkuang.model.home.make;

import android.util.Log;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.make.IBrand;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class BrandModel extends BaseModel implements IBrand.Model {
    @Override
    public void getBrand(CallBack callBack) {
        HttpManager.getInstance().getService().getBrand()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandBean>(callBack) {
                    @Override
                    public void onNext(BrandBean brandBean) {
                        callBack.success(brandBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: "+t.toString());
                    }
                });
    }
}
