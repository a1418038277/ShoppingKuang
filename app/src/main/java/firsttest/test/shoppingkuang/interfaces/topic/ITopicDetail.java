package firsttest.test.shoppingkuang.interfaces.topic;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.model.topic.TopicBean;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicCommentBean;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicDetailBean;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicRelatedBean;

public interface ITopicDetail {
    interface View extends IBaseView {
        void getTopicDetailReturn(TopicDetailBean topicDetailBean);
        void getTopicRelatedReturn(TopicRelatedBean relatedBean);
        void getTopicCommentReturn(TopicCommentBean topicCommentBean);
    }

    interface Presenter extends IBasePresenter<ITopicDetail.View> {
        void getTopicDetail(int id);
        void getTopicRelated(int id);
        void getTopicComment(int valueId,int typeId,int size);
    }

    interface Model extends IBaseModel {
        void getTopicDetail(int id, CallBack callBack);
        void getTopicRelated(int id,CallBack callBack);
        void getTopicComment(int valueId,int typeId,int size,CallBack callBack);
    }
}
