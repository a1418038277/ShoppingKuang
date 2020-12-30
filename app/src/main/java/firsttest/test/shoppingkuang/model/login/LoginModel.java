package firsttest.test.shoppingkuang.model.login;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.login.ILogin;
import firsttest.test.shoppingkuang.model.shop.GoodRelatedBean;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class LoginModel extends BaseModel implements ILogin.Model {
    @Override
    public void login(String username, String pw, CallBack callBack) {
        addDisposible(HttpManager.getInstance().getService().getLogin(username, pw)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginBean>(callBack) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callBack.success(loginBean);
                    }
                }));
    }
}
