package firsttest.test.shoppingkuang.ui.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.login.IRegist;
import firsttest.test.shoppingkuang.model.login.RegisterBean;
import firsttest.test.shoppingkuang.presenter.login.RegistPresenter;
import firsttest.test.shoppingkuang.utils.CodeUtils;
import firsttest.test.shoppingkuang.utils.SpUtils;

public class RegisterActivity extends BaseAcitvity<RegistPresenter> implements IRegist.View {


    @BindView(R.id.et_register_name)
    EditText etRegisterName;
    @BindView(R.id.et_register_pw)
    EditText etRegisterPw;
    @BindView(R.id.et_register_repwd)
    EditText etRegisterRepwd;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.iv_register_code_img)
    ImageView ivRegisterCodeImg;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private String uname;
    private String pwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected RegistPresenter createPrenter() {
        return new RegistPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //调用验证码工具类 实现验证码
        Bitmap bitmap = CodeUtils.getInstance().createBitmap();
        ivRegisterCodeImg.setImageBitmap(bitmap);
    }

    @Override
    public void registReturn(RegisterBean registerBean) {
        if (registerBean.getData().getToken()!=null){
            SpUtils.getInstance().setValue("registToken", registerBean.getData().getToken());
            SpUtils.getInstance().setValue("registUid", registerBean.getData().getUserInfo().getUid());
            Intent intent = new Intent();
            intent.putExtra("name", uname);
            intent.putExtra("pwd",pwd);
            setResult(100,intent);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_register_code_img, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_register_code_img:
                //点击更换验证码
                Bitmap bitmap = CodeUtils.getInstance().createBitmap();
                ivRegisterCodeImg.setImageBitmap(bitmap);
                break;
            case R.id.btn_register:
                initRegist();
                finish();
                break;
        }
    }

    private void initRegist() {
        uname = etRegisterName.getText().toString();
        pwd = etRegisterPw.getText().toString();
        String repwd = etRegisterRepwd.getText().toString();
        String code = etRegisterCode.getText().toString();
        if (!TextUtils.isEmpty(uname) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(repwd) ){
            if (pwd.equals(repwd)){
                if (pwd.length()>=6){
                    if (code.equals("") || code.length()!=0){
                       /* String name = SpUtils.getInstance().getString(uname);
                        Log.e("TAG", "initRegist: "+name);
                        if (!TextUtils.isEmpty(name)){
                            Toast.makeText(this, "用户已注册", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            presenter.regist(uname, pwd);
                        }*/
                        presenter.regist(uname, pwd);
                    }else {
                        Toast.makeText(this, "未输入验证码", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "你的密码不合符长度", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "你的账号为空或者密码为空", Toast.LENGTH_SHORT).show();
        }
    }
}