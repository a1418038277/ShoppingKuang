package firsttest.test.shoppingkuang.ui.shop;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.model.shop.GoodDetailBean;

public class QuestionAdapter extends BaseAdapter<GoodDetailBean.DataBean.IssueBean> {

    public QuestionAdapter(Context context, List<GoodDetailBean.DataBean.IssueBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.requestion_holder0;
    }

    @Override
    protected void bindData(GoodDetailBean.DataBean.IssueBean data, VH vh) {
        TextView question = (TextView) vh.getViewById(R.id.question);
        TextView answer = (TextView) vh.getViewById(R.id.answer);
        question.setText(data.getQuestion());
        answer.setText(data.getAnswer());
    }
}
