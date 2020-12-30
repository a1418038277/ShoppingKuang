package firsttest.test.shoppingkuang.model.sort.sortDetail;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.sort.SortDetail.ISortDetail;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class SortDetailModel extends BaseModel implements ISortDetail.Model {
    @Override
    public void getSortDetail(int id, int page, int size, CallBack callBack) {
        addDisposible(HttpManager.getInstance().getService().getSortDetail(id, page, size).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SortDetailBean>(callBack) {
                    @Override
                    public void onNext(SortDetailBean sortDetailBean) {
                        callBack.success(sortDetailBean);
                    }
                }));
    }
}
