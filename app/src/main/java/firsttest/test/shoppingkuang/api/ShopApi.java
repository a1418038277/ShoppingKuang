package firsttest.test.shoppingkuang.api;



import java.util.HashMap;
import java.util.Map;

import firsttest.test.shoppingkuang.model.app.AppBean;
import firsttest.test.shoppingkuang.model.home.HomeBean;
import firsttest.test.shoppingkuang.model.home.detail.CategoryBean;
import firsttest.test.shoppingkuang.model.home.detail.GoodListBean;
import firsttest.test.shoppingkuang.model.home.make.BrandBean;
import firsttest.test.shoppingkuang.model.home.make.BrandDetailBean;
import firsttest.test.shoppingkuang.model.home.make.BrandGoodListBean;
import firsttest.test.shoppingkuang.model.home.make.HotGoodBean;
import firsttest.test.shoppingkuang.model.home.make.HotGoodListBean;
import firsttest.test.shoppingkuang.model.login.LoginBean;
import firsttest.test.shoppingkuang.model.login.LogoutBean;
import firsttest.test.shoppingkuang.model.login.RegisterBean;
import firsttest.test.shoppingkuang.model.my.UserInfoBean;
import firsttest.test.shoppingkuang.model.shop.AddCarBean;
import firsttest.test.shoppingkuang.model.shop.Car.CarBean;
import firsttest.test.shoppingkuang.model.shop.Car.DeleteCarBean;
import firsttest.test.shoppingkuang.model.shop.Car.UpdateCarBean;
import firsttest.test.shoppingkuang.model.shop.GoodDetailBean;
import firsttest.test.shoppingkuang.model.shop.GoodRelatedBean;
import firsttest.test.shoppingkuang.model.sort.SortDataBean;
import firsttest.test.shoppingkuang.model.sort.SortNavBean;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortDetailBean;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortTopBean;
import firsttest.test.shoppingkuang.model.topic.TopicBean;

import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicCommentBean;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicDetailBean;
import firsttest.test.shoppingkuang.model.topic.topicDetail.TopicRelatedBean;
import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShopApi {
    String BASE_URL = "https://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeBean> getHome();


    //专题方向 接口
    @GET("api/topic/list")
    Flowable<TopicBean> getTopic(@Query("page") int page,@Query("size") int size);

    //专题 详情 https://cdplay.cn/api/topic/detail?id=314
    @GET("api/topic/detail")
    Flowable<TopicDetailBean> getTopicDetail(@Query("id") int id);

    //专题详情页相关的专题推荐数据 https://cdplay.cn/api/topic/related?id=314
    @GET("topic/related")
    Flowable<TopicRelatedBean> getTopicRelated(@Query("id") int id);

    //专题详情 评论  https://cdplay.cn/api/comment/list?valueId=314&typeId=1&size=5
    @GET("api/comment/list")
    Flowable<TopicCommentBean> getTopicComment(@Query("valueId")int valueId,@Query("typeId")int typeId,@Query("size") int size);


    //第一个是分类，第二个是分类的数据
    @GET("goods/category")
    Flowable<CategoryBean> getCategory(@Query("id") int id);


    @GET("api/goods/list")
    Flowable<GoodListBean> getGoodlist(@Query("categoryId") int id,@Query("page") int page,@Query("size") int size);


    //品牌制造商接口
    @GET("api/brand/list?page=1&size=1000")
    Flowable<BrandBean> getBrand();

    //品牌制造商 详情页接口
    @GET("api/brand/detail")
    Flowable<BrandDetailBean>  getBrandDetail(@Query("id") int id);


    //品牌制造商 详情页 商品列表的数据接口
    @GET("api/goods/list")
    Flowable<BrandGoodListBean> getBrandGoodList(@Query("brandId") int brandId ,@Query("page") int page, @Query("size") int size);




    //新品发布的条件筛选数据接口

    @GET("api/goods/hot")
    Flowable<HotGoodBean> getHotgood();

    //https://cdplay.cn/api/goods/list?isNew=1&page=1&size%20=1000&order=asc&sort=default&categoryId=0
    @GET("api/goods/list")
    Flowable<HotGoodListBean> getHotGoodList(@QueryMap HashMap<String,String> map);




    //商品详情购买页  https://cdplay.cn/api/goods/detail?id=1009024
    @GET("api/goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);


    //商品购买详情页底部商品列表数据  https://cdplay.cn/
    @GET("api/goods/related")
    Flowable<GoodRelatedBean> getGoodRelated(@Query("id") int id);



    //https://cdplay.cn/api/catalog/index 分类竖着导航
    @GET("api/catalog/index")
    Flowable<SortNavBean> getSortNav();


    // https://cdplay.cn/api/  用来请求当前分类的列表数据
    @GET("catalog/current")
    Flowable<SortDataBean> getSortData(@Query("id") int id);


    //商品分类的顶部导航数据接口 http://cdplay.cn/
    @GET("goods/category")
    Flowable<SortTopBean> getSortDetailTop(@Query("id") int id);

    //商品详情列表数据   https://cdplay.cn/api/goods/list?categoryId=1008002&page=1&size=100
    @GET("api/goods/list")
    Flowable<SortDetailBean> getSortDetail(@Query("categoryId") int id, @Query("page") int page, @Query("size") int size);



    // 登录接口 https://cdplay.cn/api/auth/login
    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginBean> getLogin(@Field("username")String username , @Field("password") String password);

    //用户注册
    @POST("api/auth/register")
    @FormUrlEncoded
    Flowable<RegisterBean> reister(@Field("username") String username, @Field("password") String pw);


    // 添加购物车接口 https://cdplay.cn/api/cart/add
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@FieldMap HashMap<String,String> map);


    //购物车列表 https://cdplay.cn/api/cart/index
    @GET("api/cart/index")
    Flowable<CarBean> getCar();


    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map<String,String> map);

    //删除购物车数据
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);


    //用户信息更新
    @POST("api/user/updateUserInfo")
    Flowable<UserInfoBean> updateUserInfo(@FieldMap Map<String,String> map);


    //注册
    @POST("api/auth/registernew")
    @FormUrlEncoded
    Flowable<RegisterBean> register(@Field("username") String username,@Field("password") String password);

    //退出登录
    @POST("api/auth/logout")
    Flowable<LogoutBean> logout();


    //版本更新
    @GET("api/apk/appinfo")
    Flowable<AppBean> getAppInfo();
}
