package firsttest.test.shoppingkuang.model.home.make;

import android.util.Log;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.make.IBrandDetail;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class BrandDetailModel extends BaseModel implements IBrandDetail.Model {
    @Override
    public void getBrandDetail(int id, CallBack callBack) {
        HttpManager.getInstance().getService().getBrandDetail(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandDetailBean>(callBack) {
                    @Override
                    public void onNext(BrandDetailBean brandDetailBean) {
                        callBack.success(brandDetailBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: "+t.toString());
                    }
                });
    }

    @Override
    public void getBrandGoodList(int brandId,int page,int size,CallBack callBack) {
        HttpManager.getInstance().getService().getBrandGoodList(brandId,page,size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandGoodListBean>(callBack) {
                    @Override
                    public void onNext(BrandGoodListBean brandGoodListBean) {
                        callBack.success(brandGoodListBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: "+t.toString());
                    }
                });
    }
}
