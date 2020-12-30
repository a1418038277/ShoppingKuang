package firsttest.test.shoppingkuang.ui.sort.SortDetail;

import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseFragment;
import firsttest.test.shoppingkuang.interfaces.sort.SortDetail.ISortDetail;
import firsttest.test.shoppingkuang.model.sort.sortDetail.SortDetailBean;
import firsttest.test.shoppingkuang.presenter.sort.SortDetail.SortDetailPresenter;

public class SortDetailFragment extends BaseFragment<ISortDetail.Presenter> implements ISortDetail.View {


    @BindView(R.id.sortDetail_name)
    TextView sortDetailName;
    @BindView(R.id.sortDetail_desc)
    TextView sortDetailDesc;
    @BindView(R.id.rlv_sortDetail)
    RecyclerView rlvSortDetail;
    private int id;
    private String name;
    private String frontname;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort_detail;
    }

    @Override
    protected ISortDetail.Presenter createPrenter() {
        return new SortDetailPresenter();
    }

    @Override
    protected void initView() {
        id = getArguments().getInt("id");
        name = getArguments().getString("name");
        frontname = getArguments().getString("frontname");
        sortDetailName.setText(name);
        sortDetailDesc.setText(frontname);
    }

    @Override
    protected void initData() {
        presenter.getSortDetail(id, 1, 100);
    }

    @Override
    public void getSortDetailReturn(SortDetailBean sortDetailBean) {
        rlvSortDetail.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rlvSortDetail.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        rlvSortDetail.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        SortDetailAdapter sortDetailAdapter = new SortDetailAdapter(getContext(), sortDetailBean.getData().getData());
        rlvSortDetail.setAdapter(sortDetailAdapter);
        sortDetailAdapter.notifyDataSetChanged();
    }
}
