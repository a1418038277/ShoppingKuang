package firsttest.test.shoppingkuang.model.topic.topicDetail;

import android.util.Log;

import firsttest.test.shoppingkuang.base.BaseModel;
import firsttest.test.shoppingkuang.interfaces.CallBack;
import firsttest.test.shoppingkuang.interfaces.topic.ITopicDetail;
import firsttest.test.shoppingkuang.model.topic.TopicBean;
import firsttest.test.shoppingkuang.net.CommonSubscriber;
import firsttest.test.shoppingkuang.net.HttpManager;
import firsttest.test.shoppingkuang.utils.RxUtils;

public class TopicDetailModel extends BaseModel implements ITopicDetail.Model {
    @Override
    public void getTopicDetail(int id, CallBack callBack) {
        CommonSubscriber<TopicDetailBean> commonSubscriber = HttpManager.getInstance().getService().getTopicDetail(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicDetailBean>(callBack) {
                    @Override
                    public void onNext(TopicDetailBean topicDetailBean) {
                        callBack.success(topicDetailBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: " + t.toString());
                    }
                });
        addDisposible(commonSubscriber);
    }

    @Override
    public void getTopicRelated(int id, CallBack callBack) {
        CommonSubscriber<TopicRelatedBean> commonSubscriber = HttpManager.getInstance().getService().getTopicRelated(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicRelatedBean>(callBack) {
                    @Override
                    public void onNext(TopicRelatedBean topicDetailBean) {
                        callBack.success(topicDetailBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Log.e("TAG", "onError: " + t.toString());
                    }
                });
        addDisposible(commonSubscriber);
    }

    @Override
    public void getTopicComment(int valueId, int typeId, int size, CallBack callBack) {
        CommonSubscriber<TopicCommentBean> commonSubscriber = HttpManager.getInstance().getService().getTopicComment(valueId, typeId, size)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<TopicCommentBean>(callBack) {
                    @Override
                    public void onNext(TopicCommentBean topicCommentBean) {
                        callBack.success(topicCommentBean);
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
