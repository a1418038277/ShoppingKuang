package firsttest.test.shoppingkuang.interfaces.sort.SortDetail;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortDetailBean;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortTopBean;

public interface ISortDetail {
    interface View extends IBaseView {
        void getSortDetailReturn(SortDetailBean sortDetailBean);
    }

    interface Presenter extends IBasePresenter<ISortDetail.View> {
        void getSortDetail(int id,int page,int size);
    }

    interface Model extends IBaseModel {
        void getSortDetail(int id, int page,int size,CallBack callBack);
    }
}
