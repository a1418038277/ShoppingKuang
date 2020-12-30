package firsttest.test.shoppingkuang.model.sort;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.sort.ISortNav;
import firsttest.test.shoppingkuang.model.shop.GoodDetailBean;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class SortNavModel extends BaseModel implements ISortNav.Model {
    @Override
    public void getSortNav(CallBack callBack) {
        addDisposible(HttpManager.getInstance().getService().getSortNav().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SortNavBean>(callBack) {
                    @Override
                    public void onNext(SortNavBean sortNavBean) {
                        callBack.success(sortNavBean);
                    }
                }));
    }
}
