package firsttest.test.shoppingkuang.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.home.HomeBean;

public class TopicAdapter extends BaseAdapter<HomeBean.DataBean.TopicListBean> {

    public TopicAdapter(Context context, List<HomeBean.DataBean.TopicListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topic_holder0;
    }

    @Override
    protected void bindData(HomeBean.DataBean.TopicListBean data, VH vh) {
        ImageView topicImg = (ImageView) vh.getViewById(R.id.topic_img);
        TextView topicTitle = (TextView) vh.getViewById(R.id.topic_title);
        TextView topSubitile = (TextView) vh.getViewById(R.id.topic_subtitle);
        TextView topicPrice = (TextView) vh.getViewById(R.id.topic_price);
        Glide.with(context).load(data.getItem_pic_url()).into(topicImg);
        topicTitle.setText(data.getTitle());
        topSubitile.setText(data.getSubtitle());
        topicPrice.setText("￥"+data.getPrice_info()+"元起");
    }
}
