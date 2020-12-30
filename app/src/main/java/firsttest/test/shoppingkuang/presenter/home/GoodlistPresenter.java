package firsttest.test.shoppingkuang.presenter.home;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.detail.IGoodlist;
import firsttest.test.shoppingkuang.model.home.detail.GoodListBean;
import firsttest.test.shoppingkuang.model.home.detail.GoodlistModel;

public class GoodlistPresenter extends BasePresenter<IGoodlist.View> implements IGoodlist.Presenter {
    IGoodlist.Model model;
    public GoodlistPresenter() {
        model = new GoodlistModel();
    }

    @Override
    public void getGoodlist(int id, int page, int size) {
        model.getGoodlist(id, page, size, new CallBack() {
            @Override
            public void success(Object data) {
                mView.getGoodlist((GoodListBean) data);
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
