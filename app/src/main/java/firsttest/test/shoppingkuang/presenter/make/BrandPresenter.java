package firsttest.test.shoppingkuang.presenter.make;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.make.IBrand;
import firsttest.test.shoppingkuang.model.home.make.BrandBean;
import firsttest.test.shoppingkuang.model.home.make.BrandModel;

public class BrandPresenter extends BasePresenter<IBrand.View> implements IBrand.Presenter {

    IBrand.Model model;
    public BrandPresenter() {
        model = new BrandModel();
    }

    @Override
    public void getBrand() {
        model.getBrand(new CallBack() {
            @Override
            public void success(Object data) {
                mView.getGoodlistReturn((BrandBean) data);
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
