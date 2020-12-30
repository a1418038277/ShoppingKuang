package firsttest.test.shoppingkuang.ui.topic.topicDetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.topic.ITopicDetail;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicCommentBean;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicDetailBean;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicRelatedBean;
import firsttest.test.shoppingkuang.presenter.topic.TopicDetailPresenter;
import firsttest.test.shoppingkuang.ui.sort.SortDetail.CategoryBigImageAdapter;

public class TopicDetailActivity extends BaseAcitvity<TopicDetailPresenter> implements ITopicDetail.View {

    @BindView(R.id.rlv_topicDetail)
    RecyclerView rlvTopicDetail;
    @BindView(R.id.rlv_topicRetaled)
    RecyclerView rlvTopicRetaled;
    @BindView(R.id.rlv_comment)
    RecyclerView rlvComment;


    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";

    @Override
    protected int getLayout() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected TopicDetailPresenter createPrenter() {
        return new TopicDetailPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra("topicid")) {
            int id = getIntent().getIntExtra("topicid", 0);
            if (id > 0) {
                presenter.getTopicDetail(id);
                presenter.getTopicRelated(id);
                presenter.getTopicComment(id, 1, 5);
            } else {
                showToast(getString(R.string.tips_error_goodid));
            }
        }
    }

    @Override
    public void getTopicDetailReturn(TopicDetailBean topicDetailBean) {
        if (topicDetailBean != null) {
            initTopicDetail(topicDetailBean.getData().getContent());
        }
    }

    @Override
    public void getTopicRelatedReturn(TopicRelatedBean relatedBean) {
        if (relatedBean != null) {
            initRetaled(relatedBean.getData());
        }
    }

    @Override
    public void getTopicCommentReturn(TopicCommentBean topicCommentBean) {
        if (topicCommentBean != null) {
            initComment(topicCommentBean.getData().getData());
        }
    }

    private void initComment(List<TopicCommentBean.DataBeanX.DataBean> data) {
        rlvComment.setLayoutManager(new LinearLayoutManager(this));
        rlvComment.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        TopicCommentAdapter adapter = new TopicCommentAdapter(this, data);
        rlvComment.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initRetaled(List<TopicRelatedBean.DataBean> data) {
        rlvTopicRetaled.setLayoutManager(new LinearLayoutManager(this));
        TopicRetaledAdapter topicRetaledAdapter = new TopicRetaledAdapter(this, data);
        rlvTopicRetaled.setAdapter(topicRetaledAdapter);
        topicRetaledAdapter.notifyDataSetChanged();
    }

    private void initTopicDetail(String content) {
        getHtmlImgs(content);
        String word = h5.replace("word", content);
        Log.i("TAG", word);
    }


    private void getHtmlImgs(String content) {
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(content);
        ArrayList<String> list = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            if (end > 0) {//如果是jpg格式的就截取jpg
                String url = word.substring(start, end);
                if (url != null) {
                    url = url + ".jpg";
                    list.add("http:" + url);
                    Log.i("TAG", "getHtmlImgs: " + url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");//如果是png格式的就截取png
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    list.add("http" + url);
                } else {
                    return;
                }
            }
        }

        rlvTopicDetail.setLayoutManager(new LinearLayoutManager(this));
        CategoryBigImageAdapter categoryBigImageAdapter = new CategoryBigImageAdapter(this, list);
        rlvTopicDetail.setAdapter(categoryBigImageAdapter);
        categoryBigImageAdapter.notifyDataSetChanged();

       /* //点击条目跳转
        categoryBigImageAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(ShopCarActivity.this, BigImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("image", list);
                intent.putExtra("postion", pos);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}