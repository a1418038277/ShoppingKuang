package firsttest.test.shoppingkuang.ui.shop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.shop.GoodRelatedBean;

public class GoodRetaledAdapter extends BaseAdapter<GoodRelatedBean.DataBean.GoodsListBean> {

    public GoodRetaledAdapter(Context context, List<GoodRelatedBean.DataBean.GoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.retaled_holder0;
    }

    @Override
    protected void bindData(GoodRelatedBean.DataBean.GoodsListBean data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView txt_retaled_title = (TextView) vh.getViewById(R.id.txt_retaled_title);
        TextView retaled_price = (TextView) vh.getViewById(R.id.retaled_price);
        Glide.with(context).load(data.getList_pic_url()).into(image);
        txt_retaled_title.setText(data.getName());
        retaled_price.setText("￥"+data.getRetail_price());
    }
}
