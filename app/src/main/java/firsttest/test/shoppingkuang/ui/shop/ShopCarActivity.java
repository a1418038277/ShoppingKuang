package firsttest.test.shoppingkuang.ui.shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAcitvity;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.interfaces.shop.IShop;
import firsttest.test.shoppingkuang.model.shop.AddCarBean;
import firsttest.test.shoppingkuang.model.shop.GoodDetailBean;
import firsttest.test.shoppingkuang.model.shop.GoodRelatedBean;
import firsttest.test.shoppingkuang.presenter.shop.ShopPresenter;
import firsttest.test.shoppingkuang.realm.MyUser;
import firsttest.test.shoppingkuang.realm.Realms;
import firsttest.test.shoppingkuang.ui.login.LoginActivity;
import firsttest.test.shoppingkuang.ui.sort.SortDetail.BigImageActivity;
import firsttest.test.shoppingkuang.ui.sort.SortDetail.CategoryBigImageAdapter;
import firsttest.test.shoppingkuang.utils.SpUtils;
import io.realm.Realm;

public class ShopCarActivity extends BaseAcitvity<IShop.Presenter> implements IShop.View {
    //    @BindView(R.id.webView)
//    WebView webView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_goods_brief)
    TextView txtGoodsBrief;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_name1)
    TextView txtName1;
    @BindView(R.id.txt_value1)
    TextView txtValue1;
    @BindView(R.id.txt_name2)
    TextView txtName2;
    @BindView(R.id.txt_value2)
    TextView txtValue2;
    @BindView(R.id.txt_name3)
    TextView txtName3;
    @BindView(R.id.txt_value3)
    TextView txtValue3;
    @BindView(R.id.txt_name4)
    TextView txtName4;
    @BindView(R.id.txt_value4)
    TextView txtValue4;
    @BindView(R.id.rlv_comment)
    RecyclerView rlvComment;
    @BindView(R.id.rlv_question)
    RecyclerView rlvQuestion;
    @BindView(R.id.rlv_retaled)
    RecyclerView rlvRetaled;
    @BindView(R.id.rlv_bigImg)
    RecyclerView rlvBigImg;

    public static final int RECOMMEND_CAR = 1000; //打开购物车的指令
    int buyNum = 1;

    @BindView(R.id.layout_collect)
    FrameLayout layoutCollect;
    @BindView(R.id.layout_car)
    FrameLayout layoutCar;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.txt_addCar)
    TextView txtAddCar;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    private GoodDetailBean goodDetailBean;
    private boolean isCarSelect = false;

    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";
    private GoodDetailBean.DataBean.InfoBean info;
    private int count;
    private PopupWindow popupWindow;


    @Override
    protected int getLayout() {
        return R.layout.activity_shop_car;
    }

    @Override
    protected IShop.Presenter createPrenter() {
        return new ShopPresenter();
    }

    @Override
    protected void initView() {
        //判断购物车选中状态
        if (isCarSelect == false) {
            isCarSelect = true;
        } else {
            isCarSelect = false;
        }
    }

    @OnClick({R.id.layout_collect, R.id.layout_car, R.id.txt_buy, R.id.txt_addCar,R.id.img_collect})
    public void onClick(View view) {
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))) {
            switch (view.getId()) {
                case R.id.layout_collect:
                    break;
                case R.id.img_collect:
                    String token = SpUtils.getInstance().getString("token");
                    if (!TextUtils.isEmpty(token)){
                        Realms.getRealm(ShopCarActivity.this).executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                MyUser user = realm.createObject(MyUser.class);
                                user.setDesc(info.getName());
                                user.setImgPath(info.getList_pic_url());
                                user.setPrice(info.getRetail_price()+"");
                                user.setTitle(info.getGoods_brief());
                                Log.e("TAG", "execute: "+info.getName()+info.getList_pic_url() );
                                Toast.makeText(ShopCarActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        startActivity(new Intent(this,LoginActivity.class));
                    }

                    break;
                case R.id.layout_car:
                    openGoodCar();
                    break;
                case R.id.txt_buy:
                    break;
                case R.id.txt_addCar:
                    if (isCarSelect) {
                        popuCar();
                    } else {
                        popucar_ok();
                    }
                    break;
            }
        } else {
            Intent intent = new Intent(ShopCarActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void popucar_ok() {
        popupWindow.dismiss();

        View join_view = LayoutInflater.from(ShopCarActivity.this).inflate(R.layout.layout_shoppingcar_popu_ok, null);
        PopupWindow popupWindow1 = new PopupWindow(join_view, 200, 200);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.5f;
        getWindow().setAttributes(attributes);

        popupWindow1.showAtLocation(txtAddCar, Gravity.CENTER, 0, 0);

        //两秒自动关闭
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        popupWindow1.dismiss();
                        WindowManager.LayoutParams attributes = getWindow().getAttributes();
                        attributes.alpha = 1f;
                        getWindow().setAttributes(attributes);
                    }
                });
            }
        }, 1000, 2000);

        isCarSelect = true;
    }

    private void popuCar() {
        View view = View.inflate(this, R.layout.popu_car, null);
        popupWindow = new PopupWindow(view, GridLayout.LayoutParams.MATCH_PARENT, 550);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        TextView carPrice = view.findViewById(R.id.txt_car_price);
        ImageView imgCar = view.findViewById(R.id.img_car);
        TextView txtReduce = view.findViewById(R.id.txt_reduce);
        TextView txtCar = view.findViewById(R.id.txt_car);
        TextView txtNum = view.findViewById(R.id.txt_number);
        TextView txtAdd = view.findViewById(R.id.txt_add);
        carPrice.setText("￥" + info.getRetail_price());
        Glide.with(this).load(info.getList_pic_url()).into(imgCar);
        txtCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        count = 1;
        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count > 0) {
                    txtNum.setText(String.valueOf(count));
                }
            }
        });
        txtReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if (count > 0) {
                    txtNum.setText(String.valueOf(count));
                }
            }
        });
        popupWindow.showAtLocation(txtAddCar, Gravity.BOTTOM, 0, 220);
        isCarSelect = false;
        if (count <= 0) {
            Toast.makeText(this, R.string.tips_buynumber, Toast.LENGTH_SHORT).show();
            return;
        }
        if (goodDetailBean.getData().getProductList().size() > 0) {
            int goodsId = this.goodDetailBean.getData().getProductList().get(0).getGoods_id();
            int productid = this.goodDetailBean.getData().getProductList().get(0).getId();
            HashMap<String, String> map = new HashMap<>();
            map.put("goodsId", String.valueOf(goodsId));
            map.put("number", String.valueOf(count));
            map.put("productId", String.valueOf(productid));
            presenter.addGoodCar(map);
        }
    }


    private void openGoodCar() {
        setResult(RECOMMEND_CAR);
        finish();
    }

    private void addCar() {
        if (buyNum <= 0) {
            Toast.makeText(this, R.string.tips_buynumber, Toast.LENGTH_SHORT).show();
            return;
        }
        if (goodDetailBean.getData().getProductList().size() > 0) {
            int goodsId = this.goodDetailBean.getData().getProductList().get(0).getGoods_id();
            int productid = this.goodDetailBean.getData().getProductList().get(0).getId();
            HashMap<String, String> map = new HashMap<>();
            map.put("goodsId", String.valueOf(goodsId));
            map.put("number", String.valueOf(buyNum));
            map.put("productId", String.valueOf(productid));
            presenter.addGoodCar(map);
        }
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra("goodid")) {
            int id = getIntent().getIntExtra("goodid", 0);
            if (id > 0) {
                presenter.getGoodDetail(id);
                presenter.getGoodRelated(id);
            } else {
                showToast(getString(R.string.tips_error_goodid));
            }
        }
    }

    @Override
    public void getGoodDetailReturn(GoodDetailBean goodDetailBean) {
        this.goodDetailBean = goodDetailBean;
        if (goodDetailBean != null) {
            initGoodDetail(goodDetailBean.getData().getInfo().getGoods_desc());
            initBanner(goodDetailBean.getData().getGallery());
            initTxt(goodDetailBean.getData().getInfo());
            initShopcanshu(goodDetailBean.getData().getAttribute());
            initComment(goodDetailBean.getData().getComment());
            initIssue(goodDetailBean.getData().getIssue());
        }
        if (goodDetailBean.getData().getProductList().size() > 0) {
            int num = goodDetailBean.getData().getProductList().get(0).getGoods_number();
            txtNumber.setText(String.valueOf(num));
            txtNumber.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addGoodCarReturn(AddCarBean addCarBean) {
        //添加成功以后跟新数量显示
        int number = addCarBean.getData().getCartTotal().getGoodsCount();
        txtNumber.setText(String.valueOf(number));
        txtNumber.setVisibility(View.VISIBLE);
    }


    private void initIssue(List<GoodDetailBean.DataBean.IssueBean> issue) {
        rlvQuestion.setLayoutManager(new LinearLayoutManager(this));
        QuestionAdapter questionAdapter = new QuestionAdapter(this, issue);
        rlvQuestion.setAdapter(questionAdapter);
        questionAdapter.notifyDataSetChanged();
    }

    private void initComment(GoodDetailBean.DataBean.CommentBean data) {
        rlvComment.setLayoutManager(new LinearLayoutManager(this));
//        new CommentAdapter(this,data);
    }


    private void initTxt(GoodDetailBean.DataBean.InfoBean info) {
        this.info = info;
        txtGoodsBrief.setText(info.getGoods_brief());
        txtName.setText(info.getName());
        txtPrice.setText("￥" + info.getRetail_price());
    }


    @SuppressLint("JavascriptInterface")
    private void initGoodDetail(String webData) {
        getHtmlImgs(webData);
        String content = h5.replace("word", webData);
        Log.i("TAG", content);
//        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }

    private void getHtmlImgs(String content) {
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(content);
        ArrayList<String> list = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf(".jpg");
            if (end > 0) {//如果是jpg格式的就截取jpg
                String url = word.substring(start, end);
                if (url != null) {
                    url = url + ".jpg";
                    list.add(url);
                } else {
                    return;
                }
            } else {
                int end1 = word.indexOf(".png");//如果是png格式的就截取png
                String url = word.substring(start, end1);
                if (url != null) {
                    url = url + ".png";
                    list.add(url);
                } else {
                    return;
                }
            }
        }
        rlvBigImg.setLayoutManager(new LinearLayoutManager(this));
        CategoryBigImageAdapter categoryBigImageAdapter = new CategoryBigImageAdapter(this, list);
        rlvBigImg.setAdapter(categoryBigImageAdapter);

        //点击条目跳转
        categoryBigImageAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(ShopCarActivity.this, BigImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("image", list);
                intent.putExtra("postion", pos);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }

    private void initBanner(List<GoodDetailBean.DataBean.GalleryBean> gallery) {
        banner.setImages(gallery).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                GoodDetailBean.DataBean.GalleryBean bean = (GoodDetailBean.DataBean.GalleryBean) path;
                Glide.with(context).load(bean.getImg_url()).into(imageView);
            }
        }).start();
    }

    private void initShopcanshu(List<GoodDetailBean.DataBean.AttributeBean> attribute) {
        /*if (attribute!=null){
            txtName1.setText(attribute.get(0).getName());
            txtValue1.setText(attribute.get(0).getValue());
            txtName2.setText(attribute.get(1).getName());
            txtValue2.setText(attribute.get(1).getValue());
            txtName3.setText(attribute.get(2).getName());
            txtValue3.setText(attribute.get(2).getValue());
            txtName4.setText(attribute.get(3).getName());
            txtValue4.setText(attribute.get(3).getValue());
        }*/
        for (int i = 0; i < attribute.size(); i++) {
            txtName1.setText(attribute.get(0).getName());
            txtValue1.setText(attribute.get(0).getValue());
            txtName2.setText(attribute.get(1).getName());
            txtValue2.setText(attribute.get(1).getValue());
            txtName3.setText(attribute.get(2).getName());
            txtValue3.setText(attribute.get(2).getValue());
            txtName4.setText(attribute.get(3).getName());
            txtValue4.setText(attribute.get(3).getValue());
        }
    }


    @Override
    public void getGoodRelatedReturn(GoodRelatedBean goodRelatedBean) {
        rlvRetaled.setLayoutManager(new GridLayoutManager(this, 2));
        rlvRetaled.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        rlvRetaled.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        GoodRetaledAdapter goodRetaledAdapter = new GoodRetaledAdapter(this, goodRelatedBean.getData().getGoodsList());
        rlvRetaled.setAdapter(goodRetaledAdapter);
        goodRetaledAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


}