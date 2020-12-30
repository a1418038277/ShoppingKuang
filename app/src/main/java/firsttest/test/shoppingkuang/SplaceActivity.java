package firsttest.test.shoppingkuang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;

public class SplaceActivity extends BaseAcitvity {


    @Override
    protected int getLayout() {
        return R.layout.activity_splace;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        Intent intent = new Intent(SplaceActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initData() {

    }
}