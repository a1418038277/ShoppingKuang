package firsttest.test.shoppingkuang.ui.home.make;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.interfaces.home.make.IBrand;
import firsttest.test.shoppingkuang.model.home.make.BrandBean;
import firsttest.test.shoppingkuang.presenter.make.BrandPresenter;

public class BrandActivity extends BaseAcitvity<BrandPresenter> implements IBrand.View {


    @BindView(R.id.rlv_brand)
    RecyclerView rlvBrand;
    private ArrayList<BrandBean.DataBean.BrandDataBean> brandDataBeans;
    private BrandDataAdapter brandDataAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand;
    }

    @Override
    protected BrandPresenter createPrenter() {
        return new BrandPresenter();
    }

    @Override
    protected void initView() {
        rlvBrand.setLayoutManager(new LinearLayoutManager(this));
        brandDataBeans = new ArrayList<>();
        brandDataAdapter = new BrandDataAdapter(this, brandDataBeans);
        rlvBrand.setAdapter(brandDataAdapter);

       brandDataAdapter.addListClick(new BaseAdapter.IListClick() {
           @Override
           public void itemClick(int pos) {
               BrandBean.DataBean.BrandDataBean bean = brandDataBeans.get(pos);
               Intent intent = new Intent(BrandActivity.this,BrandDetailActivity.class);
               intent.putExtra("id",bean.getId());
               startActivity(intent);
           }
       });
    }

    @Override
    protected void initData() {
        presenter.getBrand();
    }

    @Override
    public void getGoodlistReturn(BrandBean brandBean) {
        List<BrandBean.DataBean.BrandDataBean> data = brandBean.getData().getData();
        brandDataBeans.addAll(data);
        brandDataAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}