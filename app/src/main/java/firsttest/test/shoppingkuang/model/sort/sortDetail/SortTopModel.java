package firsttest.test.shoppingkuang.model.sort.sortDetail;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.sort.SortDetail.ISortTop;
import firsttest.test.shoppingkuang.model.sort.SortDataBean;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class SortTopModel extends BaseModel implements ISortTop.Model {
    @Override
    public void getSortTop(int id, CallBack callBack) {
        addDisposible(HttpManager.getInstance().getService().getSortDetailTop(id).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SortTopBean>(callBack) {
                    @Override
                    public void onNext(SortTopBean sortTopBean) {
                        callBack.success(sortTopBean);
                    }
                }));
    }
}
