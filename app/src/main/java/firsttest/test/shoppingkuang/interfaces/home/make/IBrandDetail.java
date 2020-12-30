package firsttest.test.shoppingkuang.interfaces.home.make;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.home.make.BrandBean;
import firsttest.test.shoppingkuang.model.home.make.BrandDetailBean;
import firsttest.test.shoppingkuang.model.home.make.BrandGoodListBean;

public interface IBrandDetail {
    interface View extends IBaseView {
        void getBrandDeatailReturn(BrandDetailBean brandDetailBean);
        void getBrandGoodListReturn(BrandGoodListBean brandGoodListBean);
    }

    interface Presenter extends IBasePresenter<IBrandDetail.View> {
        void getBrandDetail(int id);
        void getBrandGoodList(int brandId,int page,int size);
    }

    interface Model extends IBaseModel {
        void getBrandDetail(int id,CallBack callBack);
        void getBrandGoodList(int brandId,int page,int size,CallBack callBack);
    }
}
