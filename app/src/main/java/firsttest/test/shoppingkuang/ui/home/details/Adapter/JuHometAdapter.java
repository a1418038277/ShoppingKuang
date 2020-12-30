package firsttest.test.shoppingkuang.ui.home.details.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.home.detail.GoodListBean;

public class JuHometAdapter extends BaseAdapter<GoodListBean.DataBeanX.GoodsListBean> {


    public JuHometAdapter(Context context, List<GoodListBean.DataBeanX.GoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.goodlist_holder0;
    }

    @Override
    protected void bindData(GoodListBean.DataBeanX.GoodsListBean data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView goodlistTitle = (TextView) vh.getViewById(R.id.txt_goodlist_title);
        TextView goodlistPrice = (TextView) vh.getViewById(R.id.floor_price);
        Glide.with(context).load(data.getList_pic_url()).into(image);
        goodlistPrice.setText(data.getRetail_price()+"");
        goodlistTitle.setText(data.getName());
    }
}
