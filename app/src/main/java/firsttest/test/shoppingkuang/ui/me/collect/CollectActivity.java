package firsttest.test.shoppingkuang.ui.me.collect;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.realm.MyUser;
import firsttest.test.shoppingkuang.realm.Realms;
import io.realm.Realm;
import io.realm.RealmResults;

public class CollectActivity extends BaseAcitvity {
    @BindView(R.id.rlv_collect)
    RecyclerView rlvCollect;
    private ArrayList<MyUser> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        rlvCollect.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        rlvCollect.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        rlvCollect.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        CollectAdapter collectAdapter = new CollectAdapter(this,list);
        rlvCollect.setAdapter(collectAdapter);

        RealmResults<MyUser> users = Realms.getRealm(this).where(MyUser.class)
                .findAll();
       list.addAll(users);
        collectAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
