package firsttest.test.shoppingkuang.ui.shop;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.shop.GoodDetailBean;

public class CommentAdapter extends BaseAdapter<GoodDetailBean.DataBean.CommentBean> {

    public CommentAdapter(Context context, List<GoodDetailBean.DataBean.CommentBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.comment_holder0;
    }

    @Override
    protected void bindData(GoodDetailBean.DataBean.CommentBean data, VH vh) {
        TextView nicename = (TextView) vh.getViewById(R.id.nicename);
        TextView add_time = (TextView) vh.getViewById(R.id.add_time);
        TextView content = (TextView) vh.getViewById(R.id.content);
        ImageView pic_url = (ImageView) vh.getViewById(R.id.pic_url);
//        nicename.setText(data);
    }


}
