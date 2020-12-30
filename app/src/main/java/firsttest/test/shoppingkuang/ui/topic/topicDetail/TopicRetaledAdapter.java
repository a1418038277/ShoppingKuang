package firsttest.test.shoppingkuang.ui.topic.topicDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicRelatedBean;

public class TopicRetaledAdapter extends BaseAdapter<TopicRelatedBean.DataBean> {

    public TopicRetaledAdapter(Context context, List<TopicRelatedBean.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topic_detaled_holder0;
    }

    @Override
    protected void bindData(TopicRelatedBean.DataBean data, VH vh) {
        ImageView retaled_pic_url = (ImageView) vh.getViewById(R.id.retaled_pic_url);
        TextView txt_title = (TextView) vh.getViewById(R.id.txt_title);
        Glide.with(context).load(data.getScene_pic_url()).into(retaled_pic_url);
        txt_title.setText(data.getTitle());
    }
}
