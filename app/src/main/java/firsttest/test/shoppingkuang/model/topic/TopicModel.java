package firsttest.test.shoppingkuang.model.topic;

import android.util.Log;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.topic.ITopic;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class TopicModel extends BaseModel implements ITopic.Model {
    @Override
    public void getTopic(int page,int size,CallBack callBack) {
        CommonSubscriber<TopicBean> commonSubscriber = HttpManager.getInstance().getService().getTopic(page,size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicBean>(callBack) {
                    @Override
                    public void onNext(TopicBean topicBean) {
                        callBack.success(topicBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: " + t.toString());
                    }
                });
        addDisposible(commonSubscriber);
    }
}
