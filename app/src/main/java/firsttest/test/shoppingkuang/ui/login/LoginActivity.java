package firsttest.test.shoppingkuang.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.login.ILogin;
import firsttest.test.shoppingkuang.model.login.LoginBean;
import firsttest.test.shoppingkuang.presenter.login.LoginPresenter;
import firsttest.test.shoppingkuang.utils.SpUtils;

public class LoginActivity extends BaseAcitvity<LoginPresenter> implements ILogin.View {


    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_pw)
    EditText inputPw;
    @BindView(R.id.img_pw)
    ImageView imgPw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_regist)
    TextView txtRegist;
    @BindView(R.id.txt_forget)
    TextView txtForget;

    //密码必须是8位以上  字母+数字组合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPrenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initView() {
        imgPw.setTag(1);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void loginReturn(LoginBean loginBean) {
        if (loginBean.getData().getToken() != null) {
            SpUtils.getInstance().setValue("token", loginBean.getData().getToken());
            SpUtils.getInstance().setValue("uid", loginBean.getData().getUserInfo().getUid());
            SpUtils.getInstance().setValue("nickname",loginBean.getData().getUserInfo().getNickname());
            SpUtils.getInstance().setValue("avatar",loginBean.getData().getUserInfo().getAvatar());
            SpUtils.getInstance().setValue("username",loginBean.getData().getUserInfo().getUsername());
            SpUtils.getInstance().setValue("mark","");
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    @OnClick({R.id.img_pw, R.id.btn_login,R.id.txt_regist,R.id.txt_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.img_pw:
                showPwd();
                break;
            case R.id.txt_regist:
                startActivityForResult(new Intent(LoginActivity.this,RegisterActivity.class),100);
                break;
            case R.id.txt_forget:
                break;
        }
    }


    private void login() {
        String pwd = inputPw.getText().toString();
        String username = inputUsername.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
            presenter.login(username, pwd);

        } else {
            Toast.makeText(this, "你的账号或密码有误", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPwd() {
        int tag = (int) imgPw.getTag();
        if (tag == 1) {
            imgPw.setImageResource(R.mipmap.ic_pw_open);
            imgPw.setTag(2);
            inputPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            imgPw.setImageResource(R.mipmap.ic_pw_close);
            imgPw.setTag(1);
            inputPw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==100){
            String name = data.getStringExtra("name");
            String pwd = data.getStringExtra("pwd");
            inputUsername.setText(name);
            inputPw.setText(pwd);
        }
    }
}