package firsttest.test.shoppingkuang.presenter.shop;

import java.util.HashMap;
import java.util.Map;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.shop.IShop;
import firsttest.test.shoppingkuang.model.home.HomeBean;
import firsttest.test.shoppingkuang.model.shop.AddCarBean;
import firsttest.test.shoppingkuang.model.shop.GoodDetailBean;
import firsttest.test.shoppingkuang.model.shop.GoodRelatedBean;
import firsttest.test.shoppingkuang.model.shop.ShopModel;

public class ShopPresenter extends BasePresenter<IShop.View> implements IShop.Presenter {

    IShop.Model model;
    public ShopPresenter() {
        model = new ShopModel();
    }

    @Override
    public void getGoodDetail(int id) {
        model.getGoodDetail(id, new CallBack() {
            @Override
            public void success(Object data) {
                mView.getGoodDetailReturn((GoodDetailBean) data);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void getGoodRelated(int id) {
        model.getGoodRelated(id, new CallBack() {
            @Override
            public void success(Object data) {
                mView.getGoodRelatedReturn((GoodRelatedBean) data);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void addGoodCar(HashMap<String, String> map) {
        model.addGoodCar(map, new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.addGoodCarReturn((AddCarBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }


}
