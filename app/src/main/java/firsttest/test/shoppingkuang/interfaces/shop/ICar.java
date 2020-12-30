package firsttest.test.shoppingkuang.interfaces.shop;

import java.util.HashMap;
import java.util.Map;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.shop.Car.CarBean;
import firsttest.test.shoppingkuang.model.shop.Car.DeleteCarBean;
import firsttest.test.shoppingkuang.model.shop.Car.UpdateCarBean;
import firsttest.test.shoppingkuang.model.shop.GoodDetailBean;
import firsttest.test.shoppingkuang.model.shop.GoodRelatedBean;

public interface ICar {
    interface View extends IBaseView {
        void getCarReturn(CarBean carBean);

        //更新 购物车
        void updateCarReturn(UpdateCarBean result);

        //删除购物车
        void deleteCarReturn(DeleteCarBean result);
    }

    interface Presenter extends IBasePresenter<ICar.View> {
        void getCar();
        //更新购物车的数据
        void  updateCar(HashMap<String, String> map);

        //删除购物车列表
        void deleteCar(String pIds);
    }

    interface Model extends IBaseModel {
        void getCar(CallBack callBack);
        void updateCar(HashMap<String,String> map,CallBack callback);

        void deleteCar(String pIds,CallBack callback);
    }
}
