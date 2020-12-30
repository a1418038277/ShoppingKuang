package firsttest.test.shoppingkuang.interfaces.sort;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.interfaces.home.IHome;
import firsttest.test.shoppingkuang.model.home.HomeBean;
import firsttest.test.shoppingkuang.model.sort.SortNavBean;

public interface ISortNav {
    interface View extends IBaseView {
        void getSortNavReturn(SortNavBean sortNavBean);
    }

    interface Presenter extends IBasePresenter<ISortNav.View> {
        void getSortNav();
    }

    interface Model extends IBaseModel {
        void getSortNav(CallBack callBack);
    }
}
