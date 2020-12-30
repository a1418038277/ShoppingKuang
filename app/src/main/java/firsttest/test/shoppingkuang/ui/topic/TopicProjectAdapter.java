package firsttest.test.shoppingkuang.ui.topic;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.topic.TopicBean;

public class TopicProjectAdapter extends BaseAdapter<TopicBean.DataBean.DataBean1> {

    public TopicProjectAdapter(Context context, List<TopicBean.DataBean.DataBean1> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topicproject_holder0;
    }

    @Override
    protected void bindData(TopicBean.DataBean.DataBean1 data, VH vh) {
        ImageView img = (ImageView) vh.getViewById(R.id.topicproject_img);
        TextView title = (TextView) vh.getViewById(R.id.topicproject_title);
        TextView price = (TextView) vh.getViewById(R.id.topicproject_price);
        TextView subTitle = (TextView) vh.getViewById(R.id.topicproject_subtitle);
        Glide.with(context).load(data.getScene_pic_url()).into(img);
        title.setText(data.getTitle());
        price.setText("￥"+data.getPrice_info()+"元起");
        subTitle.setText(data.getSubtitle());
    }
}
