package firsttest.test.shoppingkuang.ui.sort.SortDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortDetailBean;

public class SortDetailAdapter extends BaseAdapter<SortDetailBean.DataBeanX.DataBean> {


    public SortDetailAdapter(Context context, List<SortDetailBean.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.sort_detail_holder0;
    }

    @Override
    protected void bindData(SortDetailBean.DataBeanX.DataBean data, VH vh) {
        ImageView image = (ImageView) vh.getViewById(R.id.image);
        TextView sort_detail_title = (TextView) vh.getViewById(R.id.sort_detail_title);
        TextView sortDetail_price = (TextView) vh.getViewById(R.id.sortDetail_price);
        Glide.with(context).load(data.getList_pic_url()).into(image);
        sort_detail_title.setText(data.getName());
        sortDetail_price.setText("￥"+data.getRetail_price());
    }
}
