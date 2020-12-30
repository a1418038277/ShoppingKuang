package firsttest.test.shoppingkuang.ui.sort.SortDetail;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.sort.SortDetail.ISortTop;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortTopBean;
import firsttest.test.shoppingkuang.presenter.sort.SortDetail.SortTopPresenter;

public class SortTopActivity extends BaseAcitvity<ISortTop.Presenter> implements ISortTop.View {


    @BindView(R.id.table)
    TabLayout table;
    @BindView(R.id.vp)
    ViewPager vp;
    private int id;
    private ArrayList<SortDetailFragment> fs;
    private String Tabname;

    @Override
    protected int getLayout() {
        return R.layout.activity_sort_top;
    }

    @Override
    protected ISortTop.Presenter createPrenter() {
        return new SortTopPresenter();
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra("id", 0);
        Tabname = getIntent().getStringExtra("name");
    }

    @Override
    protected void initData() {
        presenter.getSortTop(id);
    }

    @Override
    public void getSortTopReturn(SortTopBean sortTopBean) {
        List<SortTopBean.DataBean.BrotherCategoryBean> list = sortTopBean.getData().getBrotherCategory();
        fs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int id = list.get(i).getId();
            SortDetailFragment f = new SortDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            bundle.putString("name", list.get(i).getName());
            bundle.putString("frontname", list.get(i).getFront_name());
            f.setArguments(bundle);
            fs.add(f);
        }
        SortDetailFragmentAdapter sortDetailFragmentAdapter = new SortDetailFragmentAdapter(getSupportFragmentManager(), fs, list);
        vp.setAdapter(sortDetailFragmentAdapter);
        table.setupWithViewPager(vp);

        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            if (Tabname.equals(name)){
                vp.setCurrentItem(i);
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}