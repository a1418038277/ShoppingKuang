package firsttest.test.shoppingkuang.presenter.topic;

import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.base.BasePresenter;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.topic.ITopic;
import firsttest.test.shoppingkuang.model.topic.TopicBean;
import firsttest.test.shoppingkuang.model.topic.TopicModel;

public class TopicPresenter extends BasePresenter<ITopic.View>  implements ITopic.Presenter{
    ITopic.Model model;

    public TopicPresenter() {
        model = new TopicModel();
    }

    @Override
    public void getTopic(int page,int size) {
        model.getTopic(page,size,new CallBack() {
            @Override
            public void success(Object data) {
                if (mView!=null){
                    mView.getTopicReturn((TopicBean) data);
                }
            }

            @Override
            public void fail(String error) {

            }
        });
    }
}
