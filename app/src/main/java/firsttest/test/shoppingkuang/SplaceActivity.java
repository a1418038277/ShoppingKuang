package firsttest.test.shoppingkuang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.luck.picture.lib.tools.ToastUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import firsttest.test.shoppingkuang.app.Constants;
import firsttest.test.shoppingkuang.app.MyApp;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.app.IApp;
import firsttest.test.shoppingkuang.model.app.AppBean;
import firsttest.test.shoppingkuang.utils.DownUtils;
import firsttest.test.shoppingkuang.utils.SystemUtils;
import firsttest.test.shoppingkuang.welcome.WelcomeActivity;

public class SplaceActivity extends BaseAcitvity<IApp.Presenter> implements IApp.View {

    @BindView(R.id.txt_loading)
    TextView txtLoading;
    private boolean isUpdate;

    @Override
    protected int getLayout() {
        return R.layout.activity_splace;
    }

    @Override
    protected IApp.Presenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        Intent intent = new Intent(SplaceActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void getAppInfoReturn(AppBean appBean) {

        long versionCode = SystemUtils.getApkVersionCodeByPg(MyApp.app, appBean.getData().get(0).getPg());
        if (versionCode == -1) {
            ToastUtils.s(this, "未找到对应的apk");
        } else {
            if (versionCode < appBean.getData().get(0).getVcode()) {
                //下载更新apk
                isUpdate = true;
                WelcomeActivity.isUpdate = true; //当前需要更新新版本
                downApk(appBean);
            } else {

            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    private void downApk(AppBean appBean) {
        String apkPath = Constants.APK_PATH + appBean.getData().get(0).getName();
        File file = new File(apkPath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            DownUtils.downApk(appBean.getData().get(0).getUrl(), apkPath, new DownUtils.Callback() {
                @Override
                public void success() {
                    //安装apk
                    SystemUtils.installNewApk(apkPath);
                }

                @Override
                public void progress(String loading) {
                    txtLoading.post(new Runnable() {
                        @Override
                        public void run() {
                            txtLoading.setText(loading);
                        }
                    });
                }

                @Override
                public void fail(String err) {

                }
            });
        } catch ( IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}