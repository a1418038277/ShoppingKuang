package firsttest.test.shoppingkuang.presenter.make;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.home.make.IBrandDetail;
import firsttest.test.shoppingkuang.model.home.make.BrandDetailBean;
import firsttest.test.shoppingkuang.model.home.make.BrandDetailModel;
import firsttest.test.shoppingkuang.model.home.make.BrandGoodListBean;

public class BrandDetailPresenter extends BasePresenter<IBrandDetail.View> implements  IBrandDetail.Presenter{

    IBrandDetail.Model model;
    public BrandDetailPresenter() {
        model = new BrandDetailModel();
    }

    @Override
    public void getBrandDetail(int id) {
        model.getBrandDetail(id, new CallBack() {
            @Override
            public void success(Object data) {
                mView.getBrandDeatailReturn((BrandDetailBean) data);
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void getBrandGoodList(int BrandId,int page,int size) {
        model.getBrandGoodList(BrandId,page,size,new CallBack() {
            @Override
            public void success(Object data) {
               if (mView!=null){
                   mView.getBrandGoodListReturn((BrandGoodListBean) data);
               }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
