package firsttest.test.shoppingkuang.ui.home.make;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.home.make.HotGoodListBean;

public class HotGoodListAdapter extends BaseAdapter<HotGoodListBean.DataBeanX.DataBean> {

    public HotGoodListAdapter(Context context, List<HotGoodListBean.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.hotgoot_list_holder0;
    }

    @Override
    protected void bindData(HotGoodListBean.DataBeanX.DataBean data, VH vh) {
        ImageView Img = (ImageView) vh.getViewById(R.id.image);
        TextView title = (TextView) vh.getViewById(R.id.txt_hot_goodlist_title);
        TextView pirce = (TextView) vh.getViewById(R.id.hot_goodlist_floor_price);
        Glide.with(context).load(data.getList_pic_url()).into(Img);
        title.setText(data.getName());
        pirce.setText("￥"+data.getRetail_price());
    }
}
