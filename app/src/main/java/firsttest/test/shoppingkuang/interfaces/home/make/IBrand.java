package firsttest.test.shoppingkuang.interfaces.home.make;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.interfaces.home.detail.IGoodlist;
import firsttest.test.shoppingkuang.model.home.detail.GoodListBean;
import firsttest.test.shoppingkuang.model.home.make.BrandBean;

public interface IBrand {
    interface View extends IBaseView {
        void getGoodlistReturn(BrandBean brandBean);
    }

    interface Presenter extends IBasePresenter<IBrand.View> {
        void getBrand();
    }

    interface Model extends IBaseModel {
        void getBrand(CallBack callBack);
    }
}
