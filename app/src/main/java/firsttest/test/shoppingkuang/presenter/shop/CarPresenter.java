package firsttest.test.shoppingkuang.presenter.shop;

import android.util.Log;


import java.util.HashMap;
import java.util.Map;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.shop.ICar;
import firsttest.test.shoppingkuang.model.shop.Car.CarBean;
import firsttest.test.shoppingkuang.model.shop.Car.CarModel;
import firsttest.test.shoppingkuang.model.shop.Car.DeleteCarBean;
import firsttest.test.shoppingkuang.model.shop.Car.UpdateCarBean;

public class CarPresenter extends BasePresenter<ICar.View> implements ICar.Presenter {

    ICar.Model model;
    public CarPresenter(){
        model = new CarModel();
    }


    @Override
    public void getCar() {
        model.getCar(new CallBack() {
            @Override
            public void success(Object data) {
                if(mView != null){
                    mView.getCarReturn((CarBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    //更新购物车
    @Override
    public void updateCar(HashMap<String, String> map) {
        model.updateCar(map,new CallBack<UpdateCarBean>() {
            @Override
            public void success(UpdateCarBean data) {
                if(mView != null){
                    mView.updateCarReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    // 删除购物车列表
    @Override
    public void deleteCar(String pIds) {
        model.deleteCar(pIds,new CallBack<DeleteCarBean>() {
            @Override
            public void success(DeleteCarBean data) {
                if(mView != null){
                    Log.i("TAG","CarPresenter delete return:");
                    mView.deleteCarReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });}
}
