package firsttest.test.shoppingkuang.ui.topic.topicDetail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicCommentBean;

public class TopicCommentAdapter extends BaseAdapter<TopicCommentBean.DataBeanX.DataBean> {

    public TopicCommentAdapter(Context context, List<TopicCommentBean.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.topiccomment_holder0;
    }

    @Override
    protected void bindData(TopicCommentBean.DataBeanX.DataBean data, VH vh) {
        TextView txtName = (TextView) vh.getViewById(R.id.txt_nickname);
        TextView txtContent = (TextView) vh.getViewById(R.id.txt_content);
        TextView txtTime = (TextView) vh.getViewById(R.id.txt_time);
        txtName.setText(data.getUser_info().getNickname()+"");
        txtContent.setText(data.getContent());
        txtTime.setText(data.getAdd_time());
    }
}
