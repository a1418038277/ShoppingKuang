package firsttest.test.shoppingkuang.interfaces.topic;

import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.IBaseModel;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.IBaseView;
import firsttest.test.shoppingkuang.interfaces.home.IHome;
import firsttest.test.shoppingkuang.model.topic.TopicBean;

public interface ITopic {
    interface View extends IBaseView {
        void getTopicReturn(TopicBean topicBean);
    }

    interface Presenter extends IBasePresenter<ITopic.View> {
        void getTopic(int page,int size);
    }

    interface Model extends IBaseModel {
        void getTopic(int page,int size,CallBack callBack);
    }
}
