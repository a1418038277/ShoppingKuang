package firsttest.test.shoppingkuang.interfaces.login;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.login.LoginBean;
import firsttest.test.shoppingkuang.model.login.RegisterBean;

public interface IRegist {
    interface View extends IBaseView {
        void registReturn(RegisterBean registerBean);
    }

    interface Presenter extends IBasePresenter<IRegist.View> {
        void regist(String username,String pw);
    }


    interface Model extends IBaseModel {
        void regist(String username, String pw, CallBack callBack);
    }
}
