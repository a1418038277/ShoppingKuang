package firsttest.test.shoppingkuang.presenter.topic;

import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.topic.ITopicDetail;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicCommentBean;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicDetailBean;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicDetailModel;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicRelatedBean;

public class TopicDetailPresenter extends BasePresenter<ITopicDetail.View> implements ITopicDetail.Presenter {

    ITopicDetail.Model model;

    public TopicDetailPresenter() {
        model = new TopicDetailModel();
    }

    @Override
    public void getTopicDetail(int id) {
        model.getTopicDetail(id, new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getTopicDetailReturn((TopicDetailBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void getTopicRelated(int id) {
        model.getTopicRelated(id, new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getTopicRelatedReturn((TopicRelatedBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }

    @Override
    public void getTopicComment(int valueId, int typeId, int size) {
        model.getTopicComment(valueId, typeId, size, new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getTopicCommentReturn((TopicCommentBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
