package firsttest.test.shoppingkuang.ui.home.details.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.home.HomeBean;

public class CategoryAdapter extends BaseAdapter<HomeBean.DataBean.CategoryListBean.GoodsListBean> {


    public CategoryAdapter(Context context, List<HomeBean.DataBean.CategoryListBean.GoodsListBean> Data, Activity activity) {
        super(context, Data, activity);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.category_holder0;
    }

    @Override
    protected void bindData(HomeBean.DataBean.CategoryListBean.GoodsListBean data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView mTxtCateTitle = (TextView) vh.getViewById(R.id.txt_category_title);
        TextView mPrice = (TextView) vh.getViewById(R.id.floor_price);

        Glide.with(context).load(data.getList_pic_url()).into(image);
        mTxtCateTitle.setText(data.getName());
        mPrice.setText("￥"+data.getRetail_price());


    }


}
