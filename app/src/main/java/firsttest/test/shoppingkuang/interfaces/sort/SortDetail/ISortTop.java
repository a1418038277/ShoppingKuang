package firsttest.test.shoppingkuang.interfaces.sort.SortDetail;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.interfaces.sort.ISortData;
import firsttest.test.shoppingkuang.model.sort.SortDataBean;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortTopBean;

public interface ISortTop {

    interface View extends IBaseView {
        void getSortTopReturn(SortTopBean sortTopBean);
    }

    interface Presenter extends IBasePresenter<ISortTop.View> {
        void getSortTop(int id);
    }

    interface Model extends IBaseModel {
        void getSortTop(int id, CallBack callBack);
    }
}
