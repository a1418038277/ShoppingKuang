package firsttest.test.shoppingkuang.interfaces.home.make;

import java.util.HashMap;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.home.make.HotGoodBean;
import firsttest.test.shoppingkuang.model.home.make.HotGoodListBean;

public interface IHotGood {
    interface View extends IBaseView {
        void getHotGoodReturn(HotGoodListBean hotGoodListBean);
        void getHotgood(HotGoodBean hotGoodBean);
    }

    interface Presenter extends IBasePresenter<IHotGood.View> {
        void getHotGood(HashMap<String,String> map);
        void getHot();
    }

    interface Model extends IBaseModel {
        void getHotGood(HashMap<String,String> map,CallBack callBack);

        void getHot(CallBack callBack);
    }
}
