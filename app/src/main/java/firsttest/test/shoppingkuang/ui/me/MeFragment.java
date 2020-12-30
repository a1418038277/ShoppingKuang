package firsttest.test.shoppingkuang.ui.me;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseFragment;
import firsttest.test.shoppingkuang.interfaces.IBasePresenter;
import firsttest.test.shoppingkuang.interfaces.my.IUser;
import firsttest.test.shoppingkuang.model.my.UserInfoBean;
import firsttest.test.shoppingkuang.presenter.my.UserPresenter;
import firsttest.test.shoppingkuang.ui.login.LoginActivity;
import firsttest.test.shoppingkuang.ui.me.collect.CollectActivity;
import firsttest.test.shoppingkuang.utils.SpUtils;

public class MeFragment extends BaseFragment{

    public static final int LOGIN_ME = 10001; //登录成功的回传值
    public static final int LOGINOUT_ME = 10002;//退出登录的回传


    @BindView(R.id.img_youhui)
    ImageView imgYouhui;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.txt_mark)
    TextView txtMark;
    @BindView(R.id.img_head)
    ImageView imgHead;

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String token = SpUtils.getInstance().getString("token");
        if (!TextUtils.isEmpty(token)) {
            isLogin(true);
//            presenter.updateUserInfo();
        } else {
            isLogin(false);
        }
    }


    /**
     * 登录成功
     */
    public void loginSuccess(){
        isLogin(true);
    }

    private void isLogin(boolean b) {
        if (b) {
            txtLogin.setVisibility(View.GONE);
            txtNickname.setVisibility(View.VISIBLE);
            txtMark.setVisibility(View.VISIBLE);
            String username = SpUtils.getInstance().getString("username");
            String nickname = SpUtils.getInstance().getString("nickname");
            String mark = SpUtils.getInstance().getString("mark");
            String avatar = SpUtils.getInstance().getString("avatar");
            if (!TextUtils.isEmpty(nickname)) {
                txtNickname.setText(nickname);
            } else {
                txtNickname.setText(username);
            }
//            Glide.with(mContext).load(avatar).into(imgHead);
            String img = SpUtils.getInstance().getString("img");
            Glide.with(this).load(img).into(imgHead);
            txtMark.setText(mark);
        }else {
            txtLogin.setVisibility(View.VISIBLE);
            txtNickname.setVisibility(View.GONE);
            txtMark.setVisibility(View.GONE);
        }
    }

    /**
     * 打开登录页面
     */
    private void openLogin(){
        Intent intent = new Intent(mContext, LoginActivity.class);
        getActivity().startActivityForResult(intent,LOGIN_ME);
    }

    private void openUserInfoDetail(){
        Intent intent = new Intent(mContext,UserInfoDetailActivity.class);
        startActivityForResult(intent,100);
    }

    /**
     * 退出登录
     */
    private void loginOut(){

    }

    @OnClick({R.id.img_youhui, R.id.img_collect, R.id.img_head})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_youhui:
                break;
            case R.id.img_head:
                String token = SpUtils.getInstance().getString("token");
                if(!TextUtils.isEmpty(token)){
                    openUserInfoDetail();
                }else{
                    openLogin();
                }
                break;
            case R.id.img_collect:
                startActivity(new Intent(getContext(), CollectActivity.class));
                break;
        }
    }


}
