package firsttest.test.shoppingkuang.interfaces.home.detail;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.home.detail.CategoryBean;
import firsttest.test.shoppingkuang.model.home.detail.GoodListBean;

public interface IGoodlist {
    interface View extends IBaseView {
        void getGoodlist(GoodListBean goodListBean);
    }

    interface Presenter extends IBasePresenter<IGoodlist.View> {
        void getGoodlist(int id,int page,int size);
    }

    interface Model extends IBaseModel {
        void getGoodlist(int id,int page,int size, CallBack callBack);
    }
}
