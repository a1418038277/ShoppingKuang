package firsttest.test.shoppingkuang.ui.me;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.app.Constants;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.interfaces.my.IUser;
import firsttest.test.shoppingkuang.model.my.UserInfoBean;
import firsttest.test.shoppingkuang.presenter.my.UserPresenter;
import firsttest.test.shoppingkuang.utils.BitmapUtils;
import firsttest.test.shoppingkuang.utils.GlideEngine;
import firsttest.test.shoppingkuang.utils.SpUtils;
import firsttest.test.shoppingkuang.utils.SystemUtils;

public class UserInfoDetailActivity extends BaseAcitvity<IUser.Presenter> implements IUser.View {


    @BindView(R.id.layout_avatar)
    ConstraintLayout layoutAvatar;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.layout_nickname)
    ConstraintLayout layoutNickname;

    String bucketName = "jiyun2002b";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";
    String key = "LTAI4GKWQtegZgtrAByCvvTe";
    String secret = "8XmmZpiLG8VEgPFhD9R1brDpyiZdrv";
    @BindView(R.id.txt_input)
    EditText txtInput;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.layout_input)
    ConstraintLayout layoutInput;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    private OSS ossClient;


    @Override
    protected int getLayout() {
        return R.layout.activity_user_info_detail;
    }

    @Override
    protected IUser.Presenter createPrenter() {
        return new UserPresenter();
    }

    @Override
    protected void initView() {
        layoutInput.setVisibility(View.GONE);
        //获取图片
        String img = SpUtils.getInstance().getString("img");
        if (!TextUtils.isEmpty(img)) {
            Glide.with(this).load(img).apply(new RequestOptions().circleCrop()).into(imgAvatar);
        }
    }

    @Override
    protected void initData() {
        initOss();
    }

    private void initOss() {
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(key, secret, "");
        // 配置类如果不设置，会有默认配置。
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。
        ossClient = new OSSClient(getApplicationContext(), ossPoint, credentialProvider);
    }


    @Override
    public void updateUserInfoReturn(UserInfoBean result) {
        if (result.getErrno() == 0) {
            SystemUtils.closeSoftKeyBoard(this);
            layoutInput.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.layout_avatar, R.id.layout_nickname})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_avatar:
                openPhoto();
                break;
            case R.id.layout_nickname:
                //打开输入的状态
                showInput();
                break;
            case R.id.btn_save:
                String nickname = txtInput.getText().toString();
                if (!TextUtils.isEmpty(nickname)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("nickname", nickname);
                    presenter.updateUserInfo(map);
                }
                break;
        }
}


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // onResult Callback
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表
                try {
                    for (int i = 0; i < selectList.size(); i++) {
                        Bitmap scaleBitmp = BitmapUtils.getScaleBitmap(selectList.get(0).getPath(), Constants.HEAD_WIDTH, Constants.HEAD_HEIGHT);

                        // 上传图片
                        byte[] bytes = BitmapUtils.getBytesByBitmap(scaleBitmp);
                        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), scaleBitmp, null, null));
                        String path = getRealPathFromUri(UserInfoDetailActivity.this, uri);
                        uploadHead(path);
//                    uploadAvatar();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private String getRealPathFromUri(UserInfoDetailActivity context, Uri uri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(uri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    private void openPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }


    /**
     * oss上传
     */
    private void uploadHead(String path) {
        String uid = SpUtils.getInstance().getString("uid");
        String fileName = uid + "/" + System.currentTimeMillis() + Math.random() * 10000 + ".png";
        PutObjectRequest put = new PutObjectRequest(bucketName, fileName, path);
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                //上次进度
                Log.i("oss_upload", currentSize + "/" + totalSize);
                // 进度百分比的计算
                // int p = (int) (currentSize/totalSize*100);
                if (currentSize == totalSize) {
                    //完成
                    String headUrl = request.getUploadFilePath();
                    //
                    Log.i("HeadUrl", headUrl);
                    //request.getUploadFilePath()
                }

            }
        });
        OSSAsyncTask task = ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                //成功的回调中读取相关的上传文件的信息  生成一个url地址
                String url = ossClient.presignPublicObjectURL(request.getBucketName(), request.getObjectKey());
                uploadAvatar(url);
                //调用服务器接口 把url上传到服务器的接口
                Log.e("TAG", "onSuccess: "+url);
                SpUtils.getInstance().setValue("img", url);
                Map<String, String> map = new HashMap<>();
                map.put("avatar", url);
                presenter.updateUserInfo(map);


            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }






    private void uploadAvatar(String url) {
        imgAvatar.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(imgAvatar).load(url).apply(new RequestOptions().circleCrop()).into(imgAvatar);
            }
        });

    }

    private void showInput() {
        layoutInput.setVisibility(View.VISIBLE);
        txtInput.setFocusable(true);
        SystemUtils.openSoftKeyBoard(this);
    }
}