package firsttest.test.shoppingkuang.interfaces.sort;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.sort.SortDataBean;
import firsttest.test.shoppingkuang.model.sort.SortNavBean;

public interface ISortData {
    interface View extends IBaseView {
        void getSortDataReturn(SortDataBean sortDataBean);
    }

    interface Presenter extends IBasePresenter<ISortData.View> {
        void getSortData(int id);
    }

    interface Model extends IBaseModel {
        void getSortData(int id,CallBack callBack);
    }
}
