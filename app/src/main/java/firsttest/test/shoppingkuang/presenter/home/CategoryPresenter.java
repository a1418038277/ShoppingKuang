package firsttest.test.shoppingkuang.presenter.home;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.IHome;
import firsttest.test.shoppingkuang.interfaces.home.detail.ICategory;
import firsttest.test.shoppingkuang.model.home.HomeModel;
import firsttest.test.shoppingkuang.model.home.detail.CategoryBean;
import firsttest.test.shoppingkuang.model.home.detail.CategoryModel;

public class CategoryPresenter extends BasePresenter<ICategory.View> implements ICategory.Presenter {
    ICategory.Model model;

    public CategoryPresenter() {
       model = new CategoryModel();
    }


    @Override
    public void getCategory(int id) {
        model.getCategory(id, new CallBack() {
            @Override
            public void success(Object data) {
                mView.getCategoryReturn((CategoryBean) data);
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
