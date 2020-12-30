package firsttest.test.shoppingkuang.model.home.detail;

import android.util.Log;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.detail.ICategory;
import firsttest.test.shoppingkuang.model.home.HomeBean;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class CategoryModel extends BaseModel implements ICategory.Model {
    @Override
    public void getCategory(int id,CallBack callBack) {
        CommonSubscriber<CategoryBean> subscriber = HttpManager.getInstance().getService().getCategory(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CategoryBean>(callBack) {
                    @Override
                    public void onNext(CategoryBean categoryBean) {
                        callBack.success(categoryBean);
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
