package firsttest.test.shoppingkuang.ui.home.details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.home.detail.ICategory;
import firsttest.test.shoppingkuang.model.home.detail.CategoryBean;
import firsttest.test.shoppingkuang.model.home.detail.CategoryFragmentAdapter;
import firsttest.test.shoppingkuang.presenter.home.CategoryPresenter;
import firsttest.test.shoppingkuang.ui.home.details.fragments.JuHomeFragment;

public class DetailActivity extends BaseAcitvity<CategoryPresenter> implements ICategory.View {


    @BindView(R.id.table)
    TabLayout table;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    private ArrayList<JuHomeFragment> fs;
    private String homename;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected CategoryPresenter createPrenter() {
        return new CategoryPresenter();
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        homename = intent.getStringExtra("name");
    }

    @Override
    protected void initData() {
        table.setupWithViewPager(vp);
        presenter.getCategory(1005000);
    }

    @Override
    public void getCategoryReturn(CategoryBean categoryBean) {
        List<CategoryBean.DataBean.BrotherCategoryBean> list = categoryBean.getData().getBrotherCategory();


        fs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int id = list.get(i).getId();
            String name = list.get(i).getName();
            String front_desc = list.get(i).getFront_desc();
            JuHomeFragment f = new JuHomeFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("key", id);
            bundle.putString("front",front_desc);
            bundle.putString("name",name);
            f.setArguments(bundle);
            fs.add(f);
        }
        CategoryFragmentAdapter adapter = new CategoryFragmentAdapter(getSupportFragmentManager(), list, fs);
        vp.setAdapter(adapter);



        for (int i = 0; i < list.size(); i++) {
            if(homename.equals(list.get(i).getName())){
                vp.setCurrentItem(i);
                return;
            }
        }
    }


}