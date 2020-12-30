package firsttest.test.shoppingkuang.presenter.make;

import java.util.HashMap;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.make.IHotGood;
import firsttest.test.shoppingkuang.model.home.make.HotGoodBean;
import firsttest.test.shoppingkuang.model.home.make.HotGoodListBean;
import firsttest.test.shoppingkuang.model.home.make.HotGoodModel;

public class HotGoodPresenter  extends BasePresenter<IHotGood.View> implements IHotGood.Presenter {

    IHotGood.Model model;
    public HotGoodPresenter() {
        model = new HotGoodModel();
    }

    @Override
    public void getHotGood(HashMap<String, String> map) {
        model.getHotGood(map, new CallBack() {
            @Override
            public void success(Object data) {
                mView.getHotGoodReturn((HotGoodListBean) data);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void getHot() {
       model.getHot(new CallBack() {
           @Override
           public void success(Object data) {
               mView.getHotgood((HotGoodBean) data);
           }

           @Override
           public void fail(String error) {

           }
       });
    }
}
