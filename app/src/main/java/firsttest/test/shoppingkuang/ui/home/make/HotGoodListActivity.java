package firsttest.test.shoppingkuang.ui.home.make;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.home.make.IHotGood;
import firsttest.test.shoppingkuang.model.home.make.HotGoodBean;
import firsttest.test.shoppingkuang.model.home.make.HotGoodListBean;
import firsttest.test.shoppingkuang.presenter.make.HotGoodPresenter;

public class HotGoodListActivity extends BaseAcitvity<HotGoodPresenter> implements IHotGood.View {
    private static final String ASC = "asc";
    private static final String DESC = "desc";
    private static final String DEFAULT = "default";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    @BindView(R.id.img_hotgood)
    ImageView imgHotgood;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.txt_all)
    TextView txtAll;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.img_arrow_up)
    ImageView imgArrowUp;
    @BindView(R.id.img_arrow_down)
    ImageView imgArrowDown;
    @BindView(R.id.txt_sort)
    TextView txtSort;
    @BindView(R.id.layout_sort)
    ConstraintLayout layoutSort;
    @BindView(R.id.recy_goodList)
    RecyclerView recyGoodList;
    @BindView(R.id.layout_price)
    LinearLayout layoutPrice;


    //是否是新品
    private int isNew = 1;
    private int page = 1;
    private int size = 100;
    private String order;
    private String sort;
    private int categoryId;
    private HashMap<String, String> map;
    private List<HotGoodListBean.DataBeanX.DataBean> data;
    private List<HotGoodListBean.DataBeanX.FilterCategoryBean> filterCategory;

    @Override
    protected int getLayout() {
        return R.layout.activity_hotgood;
    }

    @Override
    protected HotGoodPresenter createPrenter() {
        return new HotGoodPresenter();
    }

    @SuppressLint("ResourceType")
    @Override
    protected void initView() {
        order = ASC;
        sort = DEFAULT;
        categoryId = 0;
        layoutPrice.setTag(0);  //
        txtAll.setTextColor(Color.parseColor(HotGoodListActivity.this.getString(R.color.red)));




    }

    @Override
    protected void initData() {
        presenter.getHot();
        presenter.getHotGood(getParam());

    }

    @SuppressLint("ResourceType")
    @OnClick({R.id.layout_price, R.id.txt_all, R.id.txt_sort})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.layout_price:
                int tag = (int) layoutPrice.getTag();
                if (tag == 0) {
                    resetPriceState();
                    priceStateUp();
                    layoutPrice.setTag(1);
                    order = ASC;
                } else if (tag == 1) {
                    resetPriceState();
                    priceStateDown();
                    layoutPrice.setTag(0);
                    order = DESC;
                }
                sort = PRICE;
                presenter.getHotGood(getParam());
                break;
            case R.id.txt_all:
                resetPriceState();
                txtAll.setTextColor(Color.parseColor(HotGoodListActivity.this.getString(R.color.red)));
                sort = DEFAULT;
                categoryId = 0;
                presenter.getHotGood(getParam());
                break;
            case R.id.txt_sort:
                resetPriceState();
                txtSort.setTextColor(Color.parseColor(HotGoodListActivity.this.getString(R.color.red)));
                sort = CATEGORY;
                presenter.getHotGood(getParam());

                setPopw();
                break;
        }
    }
    boolean aBoolean = true;
    private void setPopw() {
        Resources res = getResources();
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_hotgoodlist_layout, null);
        PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);

        LinearLayout li1 = inflate.findViewById(R.id.li1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        for (int i = 0; i < filterCategory.size(); i++) {
            View inflate1 = LayoutInflater.from(this).inflate(R.layout.popw_item, null);
            TextView tv = inflate1.findViewById(R.id.item_tv);
            tv.setText(filterCategory.get(i).getName());
            tv.setLayoutParams(layoutParams);
            tv.setGravity(Gravity.CENTER);
            int finalI = i;
            tv.setTag(i);
            if (!aBoolean){
                aBoolean=true;
            }

            inflate1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HotGoodListBean.DataBeanX.FilterCategoryBean filterCategoryBean = filterCategory.get(finalI);
                    categoryId=filterCategoryBean.getId();
                    HashMap<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put("categoryId",filterCategoryBean.getId()+"");
                    stringStringHashMap.put("isNew",1+"");
                    presenter.getHotGood(stringStringHashMap);
                    Drawable drawable = res.getDrawable(R.drawable.stroke_red);
                    tv.setTextColor(Color.RED);
                    tv.setBackground(drawable);
                    aBoolean=false;
                }
            });
            if (aBoolean){
                Drawable drawable = res.getDrawable(R.drawable.stroke_black);
                tv.setTextColor(Color.BLACK);
                tv.setBackground(drawable);
            }
            li1.addView(tv);

        }
        popupWindow.showAsDropDown(layoutPrice,0,10);
    }
    /**
     * 组装当前的接口参数
     *
     * @return
     */
    private  HashMap<String, String> getParam() {
        map = new HashMap<>();
        map.put("isNew", String.valueOf(isNew));
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        map.put("order", order);
        map.put("sort", sort);
        map.put("category", String.valueOf(categoryId));
        return map;
    }

    /**
     * 按价格升序排序
     */
    @SuppressLint("ResourceType")
    private void priceStateUp() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_select);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 价格的降序排列
     */
    @SuppressLint("ResourceType")
    private void priceStateDown() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_select);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.red)));
    }

    /**
     * 重置条件选择的所有状态
     */
    @SuppressLint("ResourceType")
    private void resetPriceState() {
        imgArrowUp.setImageResource(R.mipmap.ic_arrow_up_normal);
        imgArrowDown.setImageResource(R.mipmap.ic_arrow_down_normal);
        txtPrice.setTextColor(Color.parseColor(getString(R.color.black)));
        txtAll.setTextColor(Color.parseColor(getString(R.color.black)));
        txtSort.setTextColor(Color.parseColor(getString(R.color.black)));
        layoutPrice.setTag(0);
    }


    @Override
    public void getHotGoodReturn(HotGoodListBean hotGoodListBean) {
        data = hotGoodListBean.getData().getData();
        filterCategory = hotGoodListBean.getData().getFilterCategory();
        recyGoodList.setLayoutManager(new GridLayoutManager(this,2));
        HotGoodListAdapter hotGoodListAdapter = new HotGoodListAdapter(this, data);
        recyGoodList.setAdapter(hotGoodListAdapter);
        hotGoodListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getHotgood(HotGoodBean hotGoodBean) {
        HotGoodBean.DataBean.BannerInfoBean bannerInfo = hotGoodBean.getData().getBannerInfo();
        Glide.with(this).load(bannerInfo.getImg_url()).into(imgHotgood);
        txtInfo.setText(bannerInfo.getName());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
