package firsttest.test.shoppingkuang.presenter.sort.SortDetail;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.sort.SortDetail.ISortDetail;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortDetailBean;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortDetailModel;

public class SortDetailPresenter extends BasePresenter<ISortDetail.View> implements ISortDetail.Presenter {

    ISortDetail.Model model;

    public SortDetailPresenter() {
       model = new SortDetailModel();
    }

    @Override
    public void getSortDetail(int id, int page, int size) {
        model.getSortDetail(id, page, size, new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getSortDetailReturn((SortDetailBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
