package firsttest.test.shoppingkuang.interfaces.shop;

import java.util.HashMap;
import java.util.Map;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.interfaces.home.IHome;
import firsttest.test.shoppingkuang.model.home.HomeBean;
import firsttest.test.shoppingkuang.model.shop.AddCarBean;
import firsttest.test.shoppingkuang.model.shop.GoodDetailBean;
import firsttest.test.shoppingkuang.model.shop.GoodRelatedBean;

public interface IShop {
    interface View extends IBaseView {
        void getGoodDetailReturn(GoodDetailBean goodDetailBean);
        void addGoodCarReturn(AddCarBean addCarBean);

        void getGoodRelatedReturn(GoodRelatedBean goodRelatedBean);

    }

    interface Presenter extends IBasePresenter<IShop.View> {
        void getGoodDetail(int id);
        void getGoodRelated(int id);

        //添加进购物车
        void addGoodCar(HashMap<String,String> map);
    }

    interface Model extends IBaseModel {
        void getGoodDetail(int id,CallBack callBack);
        void getGoodRelated(int id,CallBack callBack);

        //添加进购物车
        void addGoodCar(HashMap<String, String> map, CallBack callback);
    }
}
