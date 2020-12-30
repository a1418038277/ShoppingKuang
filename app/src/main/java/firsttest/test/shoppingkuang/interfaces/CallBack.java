package firsttest.test.shoppingkuang.interfaces;


public interface CallBack<T> {
    void success(T data);
    void fail(String error);


}
