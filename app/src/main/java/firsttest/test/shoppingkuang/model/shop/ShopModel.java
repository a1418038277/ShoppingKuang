package firsttest.test.shoppingkuang.model.shop;

import java.util.HashMap;
import java.util.Map;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.shop.IShop;
import firsttest.test.shoppingkuang.model.shop.Car.CarBean;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class ShopModel extends BaseModel implements IShop.Model {
    @Override
    public void getGoodDetail(int id, CallBack callBack) {
        addDisposible(HttpManager.getInstance().getService().getGoodDetail(id).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodDetailBean>(callBack) {
                    @Override
                    public void onNext(GoodDetailBean goodDetailBean) {
                        callBack.success(goodDetailBean);
                    }
                }));
    }

    @Override
    public void getGoodRelated(int id, CallBack callBack) {
        addDisposible(HttpManager.getInstance().getService().getGoodRelated(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodRelatedBean>(callBack) {
                    @Override
                    public void onNext(GoodRelatedBean goodRelatedBean) {
                        callBack.success(goodRelatedBean);
                    }
                }));
    }

    @Override
    public void addGoodCar(HashMap<String, String> map, CallBack callback) {
        addDisposible(HttpManager.getInstance().getService().addCar(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
                    @Override
                    public void onNext(AddCarBean addCarBean) {
                        callback.success(addCarBean);
                    }
                }));
    }
}
