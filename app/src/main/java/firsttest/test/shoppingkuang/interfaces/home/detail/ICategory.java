package firsttest.test.shoppingkuang.interfaces.home.detail;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.interfaces.home.IHome;
import firsttest.test.shoppingkuang.model.home.detail.CategoryBean;

public interface ICategory {
    interface View extends IBaseView {
        void getCategoryReturn(CategoryBean categoryBean);
    }

    interface Presenter extends IBasePresenter<ICategory.View> {
        void getCategory(int id);
    }

    interface Model extends IBaseModel {
        void getCategory(int id,CallBack callBack);
    }
}
