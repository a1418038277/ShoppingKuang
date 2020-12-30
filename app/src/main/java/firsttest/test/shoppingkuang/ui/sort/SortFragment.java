package firsttest.test.shoppingkuang.ui.sort;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseFragment;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.sort.ISortNav;
import firsttest.test.shoppingkuang.model.sort.SortNavBean;
import firsttest.test.shoppingkuang.presenter.sort.CategoryTabFragment;
import firsttest.test.shoppingkuang.presenter.sort.FragTabAdapter;
import firsttest.test.shoppingkuang.presenter.sort.SortNavPresenter;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class SortFragment extends BaseFragment<ISortNav.Presenter> implements ISortNav.View {
    @BindView(R.id.vTab)
    VerticalTabLayout vTab;
    @BindView(R.id.vp_sort)
    ViewPager vpSort;


    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected ISortNav.Presenter createPrenter() {
        return new SortNavPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getSortNav();
    }

    @Override
    public void getSortNavReturn(SortNavBean sortNavBean) {
        List<SortNavBean.DataBean.CategoryListBean> categoryList = sortNavBean.getData().getCategoryList();
        final ArrayList<CategoryTabFragment> fs = new ArrayList<>();
        for (int i = 0; i <categoryList.size(); i++) {
            int id = categoryList.get(i).getId();
            CategoryTabFragment f = new CategoryTabFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            f.setArguments(bundle);
            fs.add(f);
        }

        FragTabAdapter fragTabAdapter = new FragTabAdapter(getChildFragmentManager(),fs,categoryList);
        vpSort.setAdapter(fragTabAdapter);
        vTab.setupWithViewPager(vpSort);
    }
}
