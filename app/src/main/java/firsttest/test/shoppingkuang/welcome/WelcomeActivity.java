package firsttest.test.shoppingkuang.welcome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import firsttest.test.shoppingkuang.MainActivity;
import firsttest.test.shoppingkuang.R;

public class WelcomeActivity extends AppCompatActivity {


    private ViewPager mVp;
    private ImageView mDot1;
    private ImageView mDot2;
    private ImageView mDot3;
    private int num = 3;
    public static boolean isUpdate;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                txtNum.setText("倒计时:"+num);
                num--;
                if (num==0){
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }else {
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        }
    };
    private TextView txtNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();

    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mDot1 = (ImageView) findViewById(R.id.dot_1);
        mDot2 = (ImageView) findViewById(R.id.dot_2);
        mDot3 = (ImageView) findViewById(R.id.dot_3);
        View view1 = View.inflate(this, R.layout.page1, null);
        View view2 = View.inflate(this, R.layout.page2, null);
        View view3 = View.inflate(this, R.layout.page3, null);
        Button txtJump = view3.findViewById(R.id.txt_jump);
        txtNum = view3.findViewById(R.id.txt_num);
        txtJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        });

        ArrayList<View> list = new ArrayList<>();
        list.add(view1);
        list.add(view2);
        list.add(view3);
        WelcomeVpAdapter adapter = new WelcomeVpAdapter(list, this);
        mVp.setAdapter(adapter);
        mDot1.setBackgroundResource(R.drawable.put_on);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setDot(true,false,false);
                        break;
                    case 1:
                        setDot(false,true,false);
                        break;
                    case 2:
                        setDot(false,false,true);
                        handler.sendEmptyMessageDelayed(1,1000);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });







    }


    //小点的变化方法，为true时背景设亮，为false时背景设暗
    private void setDot(boolean a,boolean b,boolean c){
        if (a){
            mDot1.setBackgroundResource(R.drawable.put_on);
        }else {
            mDot1.setBackgroundResource(R.drawable.put_off);
        }

        if (b){
            mDot2.setBackgroundResource(R.drawable.put_on);
        }else {
            mDot2.setBackgroundResource(R.drawable.put_off);
        }

        if (c){
            mDot3.setBackgroundResource(R.drawable.put_on);
        }else {
            mDot3.setBackgroundResource(R.drawable.put_off);
        }
    }
}