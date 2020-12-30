package firsttest.test.shoppingkuang.presenter.home;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.IHome;
import firsttest.test.shoppingkuang.model.home.HomeBean;
import firsttest.test.shoppingkuang.model.home.HomeModel;

public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Presenter {

    IHome.Model model;
    public HomePresenter() {
        model = new HomeModel();
    }

    @Override
    public void getHome() {
        model.getHome(new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getHomeReturn((HomeBean) data);
                }
            }

            @Override
            public void fail(String error) {
            }
        });
    }
}
