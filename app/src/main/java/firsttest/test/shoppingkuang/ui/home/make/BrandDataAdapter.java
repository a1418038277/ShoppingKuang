package firsttest.test.shoppingkuang.ui.home.make;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.home.make.BrandBean;

public class BrandDataAdapter extends BaseAdapter<BrandBean.DataBean.BrandDataBean> {

    public BrandDataAdapter(Context context, List<BrandBean.DataBean.BrandDataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brand_data_holder0;
    }

    @Override
    protected void bindData(BrandBean.DataBean.BrandDataBean data, VH vh) {
        TextView brandName = (TextView) vh.getViewById(R.id.brnad_data_name);
        TextView brandPrice = (TextView) vh.getViewById(R.id.brand_data_price);
        ImageView brandImg = (ImageView) vh.getViewById(R.id.branddata_img);
        Glide.with(context).load(data.getApp_list_pic_url()).into(brandImg);
        brandName.setText(data.getName());
        brandPrice.setText(data.getFloor_price()+"元起");
    }



}
