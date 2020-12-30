package firsttest.test.shoppingkuang.ui.sort.SortDetail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import firsttest.test.shoppingkuang.model.sort.sortDetail.SortDetailBean;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortTopBean;

public class SortDetailFragmentAdapter extends FragmentPagerAdapter {
    private List<SortDetailFragment> fs;
    private List<SortTopBean.DataBean.BrotherCategoryBean> mDataBean;


    public SortDetailFragmentAdapter(@NonNull FragmentManager fm, List<SortDetailFragment> fs, List<SortTopBean.DataBean.BrotherCategoryBean> mDataBean) {
        super(fm);
        this.fs = fs;
        this.mDataBean = mDataBean;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fs.get(position);
    }

    @Override
    public int getCount() {
        return fs.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDataBean.get(position).getName();
    }
}
