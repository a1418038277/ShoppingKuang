package firsttest.test.shoppingkuang.interfaces.home;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.home.HomeBean;

public interface IHome {
    interface View extends IBaseView{
        void getHomeReturn(HomeBean homeBean);
    }

    interface Presenter extends IBasePresenter<View>{
        void getHome();
    }

    interface Model extends IBaseModel{
        void getHome(CallBack callBack);
    }
}
