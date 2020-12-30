package firsttest.test.shoppingkuang.presenter.my;



import java.util.Map;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.my.IUser;
import firsttest.test.shoppingkuang.model.my.UserInfoBean;
import firsttest.test.shoppingkuang.model.my.UserModel;

public class UserPresenter extends BasePresenter<IUser.View> implements IUser.Presenter {

    IUser.Model model;

    public UserPresenter(){
        model = new UserModel();
    }

    @Override
    public void updateUserInfo(Map<String, String> map) {
       model.updateUserInfo(map, new CallBack() {
           @Override
           public void success(Object data) {
                if (mView!=null){
                    mView.updateUserInfoReturn((UserInfoBean) data);
                }
           }

           @Override
           public void fail(String error) {

           }
       });
    }

}
