package firsttest.test.shoppingkuang.ui.shop;

import android.os.Bundle;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;

public class OrderActivity extends BaseAcitvity {


    @BindView(R.id.txt_counponNumber)
    TextView txtCounponNumber;
    @BindView(R.id.layout_total)
    ConstraintLayout layoutTotal;
    @BindView(R.id.layout_cost)
    ConstraintLayout layoutCost;
    @BindView(R.id.layout_coupon)
    ConstraintLayout layoutCoupon;

    private TextView txtTotalLt,txtTotalRt,txtCostLt,txtCostRt,txtCouponLt,txtCouponRt;

    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        txtTotalLt = layoutTotal.findViewById(R.id.txt_left);
        txtTotalRt = layoutTotal.findViewById(R.id.txt_right);
        txtCostLt = layoutCost.findViewById(R.id.txt_left);
        txtCostRt = layoutCost.findViewById(R.id.txt_right);
        txtCouponLt = layoutCoupon.findViewById(R.id.txt_left);
        txtCouponRt = layoutCoupon.findViewById(R.id.txt_right);
        txtTotalLt.setText("商品合计");
        txtCostLt.setText("运费");
        txtCouponLt.setText("优惠券");
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