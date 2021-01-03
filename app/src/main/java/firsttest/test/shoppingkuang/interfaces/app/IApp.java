package firsttest.test.shoppingkuang.interfaces.app;


import java.util.Map;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.app.AppBean;

public interface IApp {
    interface View extends IBaseView {
        void getAppInfoReturn(AppBean appBean);
    }

    interface Presenter extends IBasePresenter<IApp.View> {
        void getAppInfo();
    }


    interface Model extends IBaseModel {
        void getAppInfo(CallBack callback);
    }
}
