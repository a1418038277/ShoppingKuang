package firsttest.test.shoppingkuang.ui.sort;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.sort.SortDataBean;

public class SortDataAdapter extends BaseAdapter<SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> {

    public SortDataAdapter(Context context, List<SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.sort_data_holder0;
    }

    @Override
    protected void bindData(SortDataBean.DataBean.CurrentCategoryBean.SubCategoryListBean data, VH vh) {
        ImageView img = (ImageView) vh.getViewById(R.id.image);
        TextView name = (TextView) vh.getViewById(R.id.sort_name);
        Glide.with(context).load(data.getWap_banner_url()).into(img);
        name.setText(data.getName());

    }
}
