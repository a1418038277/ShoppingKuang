package firsttest.test.shoppingkuang.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.home.HomeBean;

public class HotGoodAdapter extends BaseAdapter<HomeBean.DataBean.HotGoodsListBean> {

    public HotGoodAdapter(Context context, List<HomeBean.DataBean.HotGoodsListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.hotgood_holder0;
    }

    @Override
    protected void bindData(HomeBean.DataBean.HotGoodsListBean data, VH vh) {
        ImageView hotGoodImg = (ImageView) vh.getViewById(R.id.hotgood_img);
        TextView hotGoodName = (TextView) vh.getViewById(R.id.hotgood_name);
        TextView hotGoodPrice = (TextView) vh.getViewById(R.id.hotgood_price);
        TextView hotGoodBrief = (TextView) vh.getViewById(R.id.hotgood_brieF);
        Glide.with(context).load(data.getList_pic_url()).into(hotGoodImg);
        hotGoodName.setText(data.getName());
        hotGoodPrice.setText("￥"+data.getRetail_price());
        hotGoodBrief.setText(data.getGoods_brief());
    }
}
