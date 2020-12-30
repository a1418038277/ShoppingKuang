package firsttest.test.shoppingkuang.model.shop.Car;

import java.util.HashMap;
import java.util.Map;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.shop.ICar;
import firsttest.test.shoppingkuang.model.shop.GoodRelatedBean;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class CarModel extends BaseModel implements ICar.Model {
    @Override
    public void getCar(CallBack callBack) {
        addDisposible(HttpManager.getInstance().getService().getCar()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CarBean>(callBack) {
                    @Override
                    public void onNext(CarBean carBean) {
                        callBack.success(carBean);
                    }
                }));
    }

    @Override
    public void updateCar(HashMap<String, String> map, CallBack callback) {
        addDisposible(HttpManager.getInstance().getService().updateCar(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UpdateCarBean>(callback) {
                    @Override
                    public void onNext(UpdateCarBean updateCarBean) {
                        callback.success(updateCarBean);
                    }
                }));
    }

    @Override
    public void deleteCar(String pIds, CallBack callback) {
        addDisposible(HttpManager.getInstance().getService().deleteCar(pIds).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCarBean>(callback) {
                    @Override
                    public void onNext(DeleteCarBean deleteCarBean) {
                        callback.success(deleteCarBean);
                    }
                }));
    }
}
