package firsttest.test.shoppingkuang.model.home.detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import firsttest.test.shoppingkuang.ui.home.details.fragments.JuHomeFragment;

public class CategoryFragmentAdapter extends FragmentPagerAdapter {
    private List<CategoryBean.DataBean.BrotherCategoryBean> mDataBeans;
    private ArrayList<JuHomeFragment> fs;

    public CategoryFragmentAdapter(@NonNull FragmentManager fm, List<CategoryBean.DataBean.BrotherCategoryBean> mDataBeans, ArrayList<JuHomeFragment> fs) {
        super(fm);
        this.mDataBeans = mDataBeans;
        this.fs = fs;
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
        return mDataBeans.get(position).getName();
    }
}
