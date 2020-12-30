package firsttest.test.shoppingkuang.ui.topic;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.base.BaseFragment;
import firsttest.test.shoppingkuang.interfaces.topic.ITopic;
import firsttest.test.shoppingkuang.model.topic.TopicBean;
import firsttest.test.shoppingkuang.presenter.topic.TopicPresenter;
import firsttest.test.shoppingkuang.ui.topic.topicDetail.TopicDetailActivity;

public class TopicFragment extends BaseFragment<ITopic.Presenter> implements ITopic.View {

    @BindView(R.id.rlv_topic)
    RecyclerView rlvTopic;
    int page = 1;
    int size = 10;
    @BindView(R.id.btn_top)
    Button btnTop;
    @BindView(R.id.btn_bottom)
    Button btnBottom;
    private ArrayList<TopicBean.DataBean.DataBean1> list;

    @Override
    protected int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected ITopic.Presenter createPrenter() {
        return new TopicPresenter();
    }

    @Override
    protected void initView() {
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                presenter.getTopic(page, size);
            }
        });
        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                if (list!=null){
                    presenter.getTopic(page+1, size);
                }
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getTopic(page, size);
    }

    @Override
    public void getTopicReturn(TopicBean topicBean) {
        if (topicBean != null) {
            initTopic(topicBean.getData().getTopicData());
        }
    }

    private void initTopic(List<TopicBean.DataBean.DataBean1> topicData) {
        rlvTopic.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.addAll(topicData);
        TopicProjectAdapter topicProjectAdapter = new TopicProjectAdapter(getContext(), list);
        rlvTopic.setAdapter(topicProjectAdapter);
        topicProjectAdapter.notifyDataSetChanged();

        topicProjectAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getContext(), TopicDetailActivity.class);
                intent.putExtra("topicid", topicData.get(pos).getId());
                startActivity(intent);
            }
        });
    }


}
