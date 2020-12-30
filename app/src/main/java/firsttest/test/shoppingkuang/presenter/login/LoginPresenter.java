package firsttest.test.shoppingkuang.presenter.login;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.login.ILogin;
import firsttest.test.shoppingkuang.model.login.LoginBean;
import firsttest.test.shoppingkuang.model.login.LoginModel;

public class LoginPresenter extends BasePresenter<ILogin.View> implements ILogin.Presenter {
    ILogin.Model model;

    public LoginPresenter() {
       model = new LoginModel();
    }

    @Override
    public void login(String username, String pw) {
        model.login(username, pw, new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.loginReturn((LoginBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
