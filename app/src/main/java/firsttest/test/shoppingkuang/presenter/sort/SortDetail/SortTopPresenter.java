package firsttest.test.shoppingkuang.presenter.sort.SortDetail;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.sort.SortDetail.ISortTop;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortTopBean;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortTopModel;

public class SortTopPresenter extends BasePresenter<ISortTop.View> implements ISortTop.Presenter {

    ISortTop.Model model;

    public SortTopPresenter() {
        model = new SortTopModel();
    }

    @Override
    public void getSortTop(int id) {
        model.getSortTop(id, new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getSortTopReturn((SortTopBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
