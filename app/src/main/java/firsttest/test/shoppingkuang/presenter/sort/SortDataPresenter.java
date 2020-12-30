package firsttest.test.shoppingkuang.presenter.sort;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.sort.ISortData;
import firsttest.test.shoppingkuang.model.sort.SortDataBean;
import firsttest.test.shoppingkuang.model.sort.SortDataModel;
import firsttest.test.shoppingkuang.model.sort.SortNavBean;

public class SortDataPresenter extends BasePresenter<ISortData.View> implements ISortData.Presenter {

    ISortData.Model model;

    public SortDataPresenter() {
        model = new SortDataModel();
    }

    @Override
    public void getSortData(int id) {
        model.getSortData(id, new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getSortDataReturn((SortDataBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
