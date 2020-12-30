package firsttest.test.shoppingkuang.ui.home.make;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.home.make.BrandDetailBean;
import firsttest.test.shoppingkuang.model.home.make.BrandGoodListBean;

public class BrandDetailAdapter extends BaseAdapter<BrandGoodListBean.DataBeanX.DataBean>  {


    public BrandDetailAdapter(Context context, List<BrandGoodListBean.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brand_detail_holder0;
    }

    @Override
    protected void bindData(BrandGoodListBean.DataBeanX.DataBean data, VH vh) {
        ImageView brandDetailImg = (ImageView) vh.getViewById(R.id.image);
        TextView brandDetailTitle = (TextView) vh.getViewById(R.id.txt_brand_detail_title);
        TextView brandDetailprice = (TextView) vh.getViewById(R.id.brand_detail_floor_price);
        Glide.with(context).load(data.getList_pic_url()).into(brandDetailImg);
        brandDetailTitle.setText(data.getName());
        brandDetailprice.setText("￥"+data.getRetail_price());
    }


}
