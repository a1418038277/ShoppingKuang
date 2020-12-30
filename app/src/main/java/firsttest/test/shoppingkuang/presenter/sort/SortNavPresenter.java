package firsttest.test.shoppingkuang.presenter.sort;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.sort.ISortNav;
import firsttest.test.shoppingkuang.model.sort.SortNavBean;
import firsttest.test.shoppingkuang.model.sort.SortNavModel;

public class SortNavPresenter extends BasePresenter<ISortNav.View> implements ISortNav.Presenter {

   ISortNav.Model model;

    public SortNavPresenter() {
       model = new SortNavModel();
    }

    @Override
    public void getSortNav() {
        model.getSortNav(new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getSortNavReturn((SortNavBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
