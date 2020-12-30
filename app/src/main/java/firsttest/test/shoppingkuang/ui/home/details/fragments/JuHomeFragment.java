package firsttest.test.shoppingkuang.ui.home.details.fragments;

import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseFragment;
import firsttest.test.shoppingkuang.interfaces.home.detail.IGoodlist;
import firsttest.test.shoppingkuang.model.home.detail.GoodListBean;
import firsttest.test.shoppingkuang.presenter.home.GoodlistPresenter;
import firsttest.test.shoppingkuang.ui.home.details.Adapter.JuHometAdapter;

public class JuHomeFragment extends BaseFragment<IGoodlist.Presenter> implements IGoodlist.View {



    @BindView(R.id.detail_name)
    TextView detailName;
    @BindView(R.id.detail_desc)
    TextView detailDesc;
    @BindView(R.id.rlv_juhome)
    RecyclerView rlvJuhome;
    private JuHometAdapter mAdapter;
    private int key;
    private String name;
    private String title;


    @Override
    protected int getLayout() {
        return R.layout.fragment_juhome;
    }

    @Override
    protected IGoodlist.Presenter createPrenter() {
        return new GoodlistPresenter();
    }

    @Override
    protected void initView() {
        key = getArguments().getInt("key");
        name = getArguments().getString("front");
        title = getArguments().getString("name");
        detailName.setText(name);
        detailDesc.setText(title);

    }

    @Override
    protected void initData() {
        presenter.getGoodlist(key, 1, 100);
    }

    @Override
    public void getGoodlist(GoodListBean goodListBean) {
        List<GoodListBean.DataBeanX.GoodsListBean> data = goodListBean.getData().getGoodsList();
        rlvJuhome.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new JuHometAdapter(getContext(), data);
        rlvJuhome.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
