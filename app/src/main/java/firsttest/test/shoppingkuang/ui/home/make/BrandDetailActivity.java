package firsttest.test.shoppingkuang.ui.home.make;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.home.make.IBrandDetail;
import firsttest.test.shoppingkuang.model.home.make.BrandDetailBean;
import firsttest.test.shoppingkuang.model.home.make.BrandGoodListBean;
import firsttest.test.shoppingkuang.presenter.make.BrandDetailPresenter;

public class BrandDetailActivity extends BaseAcitvity<BrandDetailPresenter> implements IBrandDetail.View {

    @BindView(R.id.branddetail_img)
    ImageView branddetailImg;
    @BindView(R.id.brnad_detail_name)
    TextView brnadDetailName;
    @BindView(R.id.rlv_branddetail)
    RecyclerView rlvBranddetail;
    @BindView(R.id.simple_desc)
    TextView simpleDesc;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_detail;
    }

    @Override
    protected BrandDetailPresenter createPrenter() {
        return new BrandDetailPresenter();
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra("id", 0);


    }

    @Override
    protected void initData() {
        presenter.getBrandDetail(id);
        presenter.getBrandGoodList(id,1,1000);
    }

    @Override
    public void getBrandDeatailReturn(BrandDetailBean brandDetailBean) {
        BrandDetailBean.DataBean.BrandBean brand = brandDetailBean.getData().getBrand();
        String name = brand.getName();
        brnadDetailName.setText(name);
        simpleDesc.setText(brand.getSimple_desc());
        Glide.with(this).load(brand.getList_pic_url()).into(branddetailImg);
    }

    @Override
    public void getBrandGoodListReturn(BrandGoodListBean brandGoodListBean) {
        List<BrandGoodListBean.DataBeanX.DataBean> goodsList = brandGoodListBean.getData().getData();
        rlvBranddetail.setLayoutManager(new GridLayoutManager(this, 2));
        BrandDetailAdapter brandDetailAdapter = new BrandDetailAdapter(this, goodsList);
        rlvBranddetail.setAdapter(brandDetailAdapter);
        brandDetailAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);
        ButterKnife.bind(this);
        initView();
    }
}
