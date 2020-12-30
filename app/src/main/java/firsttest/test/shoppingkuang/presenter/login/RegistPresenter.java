package firsttest.test.shoppingkuang.presenter.login;

import android.util.Log;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.login.IRegist;
import firsttest.test.shoppingkuang.model.login.RegistModel;
import firsttest.test.shoppingkuang.model.login.RegisterBean;

public class RegistPresenter extends BasePresenter<IRegist.View> implements IRegist.Presenter {

    IRegist.Model model;
    public RegistPresenter() {
        model = new RegistModel();
    }

    @Override
    public void regist(String username, String pw) {
        model.regist(username, pw, new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.registReturn((RegisterBean) data);
                }
            }

            @Override
            public void fail(String error) {
                Log.e("TAG", "fail: "+error );
            }
        });
    }
}
