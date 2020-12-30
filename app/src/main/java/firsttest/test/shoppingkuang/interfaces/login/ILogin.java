package firsttest.test.shoppingkuang.interfaces.login;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.login.LoginBean;

public interface ILogin {
    interface View extends IBaseView {
        void loginReturn(LoginBean loginBean);
    }

    interface Presenter extends IBasePresenter<ILogin.View> {
        void login(String username,String pw);
    }


    interface Model extends IBaseModel {
        void login(String username, String pw, CallBack callBack);
    }
}
