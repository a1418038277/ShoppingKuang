package firsttest.test.shoppingkuang.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.base.BaseFragment;
import firsttest.test.shoppingkuang.interfaces.home.IHome;
import firsttest.test.shoppingkuang.model.home.HomeBean;
import firsttest.test.shoppingkuang.presenter.home.HomePresenter;
import firsttest.test.shoppingkuang.ui.home.details.DetailActivity;
import firsttest.test.shoppingkuang.ui.home.details.fragments.CategoryAdapter;
import firsttest.test.shoppingkuang.ui.home.make.BrandActivity;
import firsttest.test.shoppingkuang.ui.home.make.BrandDetailActivity;
import firsttest.test.shoppingkuang.ui.home.make.HotGoodListActivity;
import firsttest.test.shoppingkuang.ui.shop.ShopCarActivity;
import firsttest.test.shoppingkuang.ui.topic.TopicFragment;


public class HomeFragment extends BaseFragment<IHome.Presenter> implements IHome.View {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    @BindView(R.id.recy_brand)
    RecyclerView recyBrand;
    @BindView(R.id.recy_newgood)
    RecyclerView recyNewgood;
    @BindView(R.id.recy_hotgood)
    RecyclerView recyHotgood;
    @BindView(R.id.recy_topic)
    RecyclerView recyTopic;
    @BindView(R.id.linesr)
    LinearLayout linesr;
    @BindView(R.id.btn_jumpsearch)
    Button btnJumpsearch;
    @BindView(R.id.txt_brand_title)
    TextView txtBrandTitle;
    @BindView(R.id.txt_newgood_title)
    TextView txtNewgoodTitle;
    @BindView(R.id.txt_topic_title)
    TextView txtTopicTitle;
    private CategoryAdapter categoryAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected IHome.Presenter createPrenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        txtBrandTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), BrandActivity.class));
            }
        });
        txtNewgoodTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), HotGoodListActivity.class));
            }
        });
        txtTopicTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ll_qie,new TopicFragment());
                transaction.commit();*/
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getHome();
    }

    @Override
    public void getHomeReturn(HomeBean homeBean) {
        if (homeBean.getData() != null) {
            initBanner(homeBean.getData().getBanner());
            initChannel(homeBean.getData().getChannel());
            initBrand(homeBean.getData().getBrandList());
            initnewGood(homeBean.getData().getNewGoodsList());
            initHotGood(homeBean.getData().getHotGoodsList());
            initTopic(homeBean.getData().getTopicList());
            initCategoryList(homeBean.getData().getCategoryList());
        }
    }


    private void initBanner(List<HomeBean.DataBean.BannerBean> list) {
        banner.setImages(list).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.DataBean.BannerBean bean = (HomeBean.DataBean.BannerBean) path;
                Glide.with(context).load(bean.getImage_url()).into(imageView);
            }
        }).start();
    }


    private void initChannel(List<HomeBean.DataBean.ChannelBean> list) {
        layoutTab.removeAllViews();
        for (HomeBean.DataBean.ChannelBean item : list) {
            View view = View.inflate(getContext(), R.layout.layout_channel_item, null);
            LinearLayout ll1 = view.findViewById(R.id.ll1);
            ImageView imgChannel = view.findViewById(R.id.img_channel);
            TextView txtChannel = view.findViewById(R.id.txt_channel);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            ll1.setLayoutParams(layoutParams);
            txtChannel.setText(item.getName());
            Glide.with(getActivity()).load(item.getIcon_url()).into(imgChannel);
            layoutTab.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra("key", item.getId());
                    intent.putExtra("name", item.getName());
                    startActivityForResult(intent, 100);
                }
            });
        }

    }


    private void initBrand(List<HomeBean.DataBean.BrandListBean> brandList) {
        recyBrand.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ArrayList<HomeBean.DataBean.BrandListBean> list = new ArrayList<>();
        list.addAll(brandList);
        BrandAdapter homeAdapter = new BrandAdapter(getContext(), list);
        recyBrand.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setMyItemClickListen(new BrandAdapter.MyItemClickListen() {
            @Override
            public void onItemClick(int pos) {
                int id = list.get(pos).getId();
                Intent intent = new Intent(getContext(), BrandDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }


    private void initnewGood(List<HomeBean.DataBean.NewGoodsListBean> newGoodsList) {
        recyNewgood.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ArrayList<HomeBean.DataBean.NewGoodsListBean> list = new ArrayList<>();
        list.addAll(newGoodsList);
        newGoodAdapter newGoodAdapter = new newGoodAdapter(mContext, list);
        recyNewgood.setAdapter(newGoodAdapter);
        newGoodAdapter.notifyDataSetChanged();
        newGoodAdapter.setMyItemClickListen(new newGoodAdapter.MyItemClickListen() {
            @Override
            public void onItemClick(int pos) {
                Intent intent = new Intent(getContext(), ShopCarActivity.class);
                intent.putExtra("goodid", list.get(pos).getId());
                startActivity(intent);
            }
        });
    }

    private void initHotGood(List<HomeBean.DataBean.HotGoodsListBean> hotGoodsList) {
        recyHotgood.setLayoutManager(new LinearLayoutManager(getContext()));
        recyHotgood.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        ArrayList<HomeBean.DataBean.HotGoodsListBean> list = new ArrayList<>();
        list.addAll(hotGoodsList);
        HotGoodAdapter hotGoodAdapter = new HotGoodAdapter(getContext(), list);
        recyHotgood.setAdapter(hotGoodAdapter);
        hotGoodAdapter.notifyDataSetChanged();

        hotGoodAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(getContext(), ShopCarActivity.class);
                intent.putExtra("goodid", list.get(pos).getId());
                startActivity(intent);
            }
        });
    }

    private void initTopic(List<HomeBean.DataBean.TopicListBean> topicList) {
        recyTopic.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        ArrayList<HomeBean.DataBean.TopicListBean> list = new ArrayList<>();
        list.addAll(topicList);
        TopicAdapter topicAdapter = new TopicAdapter(getContext(), list);
        recyTopic.setAdapter(topicAdapter);
        topicAdapter.notifyDataSetChanged();


    }

    private void initCategoryList(List<HomeBean.DataBean.CategoryListBean> categoryList) {
        for (int i = 0; i < categoryList.size(); i++) {
            View view = View.inflate(getContext(), R.layout.category_item, null);
            TextView txtHomeTitle = view.findViewById(R.id.txt_home_title);
            txtHomeTitle.setText(categoryList.get(i).getName());
            RecyclerView mCateRlv = view.findViewById(R.id.recy_category);
            mCateRlv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            List<HomeBean.DataBean.CategoryListBean.GoodsListBean> list = categoryList.get(i).getGoodsList();
            categoryAdapter = new CategoryAdapter(getContext(), list,getActivity());
            mCateRlv.setAdapter(categoryAdapter);
            linesr.addView(view);
            categoryAdapter.addListClick(new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    Intent intent = new Intent(getContext(), ShopCarActivity.class);
                    intent.putExtra("goodid", list.get(pos).getId());
                    getActivity().startActivityForResult(intent,100);
                }
            });
        }

    }


    @OnClick(R.id.btn_jumpsearch)
    public void onClick() {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        startActivity(intent);
    }
}
