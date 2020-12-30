package firsttest.test.shoppingkuang.presenter.sort;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.base.BaseFragment;
import firsttest.test.shoppingkuang.interfaces.sort.ISortData;
import firsttest.test.shoppingkuang.model.sort.SortDataBean;
import firsttest.test.shoppingkuang.ui.sort.SortDataAdapter;
import firsttest.test.shoppingkuang.ui.sort.SortDetail.SortTopActivity;

public class CategoryTabFragment extends BaseFragment<ISortData.Presenter> implements ISortData.View {

    @BindView(R.id.sortData_img)
    ImageView sortDataImg;
    @BindView(R.id.sort_desc)
    TextView sortDesc;
    @BindView(R.id.fenleiname)
    TextView fenleiname;
    @BindView(R.id.rlv_sort)
    RecyclerView rlvSort;
    private int id;

    @Override
    protected int getLayout() {
        return R.layout.sort_data_item;
    }

    @Override
    protected ISortData.Presenter createPrenter() {
        return new SortDataPresenter();
    }

    @Override
    protected void initView() {
        id = getArguments().getInt("id");

    }

    @Override
    protected void initData() {
        presenter.getSortData(id);
    }

    @Override
    public void getSortDataReturn(SortDataBean sortDataBean) {
        Glide.with(getContext()).load(sortDataBean.getData().getCurrentCategory().getWap_banner_url()).into(sortDataImg);
        sortDesc.setText(sortDataBean.getData().getCurrentCategory().getFront_desc());
        fenleiname.setText("————"+sortDataBean.getData().getCurrentCategory().getName()+"分类————");
        rlvSort.setLayoutManager(new GridLayoutManager(getContext(), 3));
        SortDataAdapter sortDataAdapter = new SortDataAdapter(getContext(),sortDataBean.getData().getCurrentCategory().getSubCategoryList());
        rlvSort.setAdapter(sortDataAdapter);
        sortDataAdapter.notifyDataSetChanged();

        sortDataAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                int id = sortDataBean.getData().getCurrentCategory().getSubCategoryList().get(pos).getId();
                Intent intent = new Intent(getContext(), SortTopActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("name",sortDataBean.getData().getCurrentCategory().getSubCategoryList().get(pos).getName() );
                startActivityForResult(intent,100);
            }
        });
    }
}
