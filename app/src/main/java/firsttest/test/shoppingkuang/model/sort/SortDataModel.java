package firsttest.test.shoppingkuang.model.sort;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.sort.ISortData;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class SortDataModel extends BaseModel implements ISortData.Model {
    @Override
    public void getSortData(int id, CallBack callBack) {
        addDisposible(HttpManager.getInstance().getService().getSortData(id).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SortDataBean>(callBack) {
                    @Override
                    public void onNext(SortDataBean sortDataBean) {
                        callBack.success(sortDataBean);
                    }
                }));
    }
}
