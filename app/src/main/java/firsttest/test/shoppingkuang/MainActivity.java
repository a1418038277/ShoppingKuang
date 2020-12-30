package firsttest.test.shoppingkuang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import firsttest.test.shoppingkuang.ui.home.HomeFragment;
import firsttest.test.shoppingkuang.ui.me.MeFragment;
import firsttest.test.shoppingkuang.ui.shop.ShopCarActivity;
import firsttest.test.shoppingkuang.ui.shop.ShopFragment;
import firsttest.test.shoppingkuang.ui.sort.SortFragment;
import firsttest.test.shoppingkuang.ui.topic.TopicFragment;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ViewPager mVp;
    private ArrayList<Fragment> fragments;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.nav_view);
        mVp = (ViewPager) findViewById(R.id.vp);
        initFragment();
       /* AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_topic,
                R.id.navigation_sort,R.id.navigation_shop,R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);*/
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(myFragmentPagerAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        item.setIcon(R.mipmap.ic_menu_choice_pressed);
                        mVp.setCurrentItem(0);
                        return true;
                    case R.id.navigation_topic:
                        item.setIcon(R.mipmap.ic_menu_topic_pressed);
                        mVp.setCurrentItem(1);
                        return true;
                    case R.id.navigation_sort:
                        item.setIcon(R.mipmap.ic_menu_sort_pressed);
                        mVp.setCurrentItem(2);
                        return true;
                    case R.id.navigation_shop:
                        item.setIcon(R.mipmap.ic_menu_shoping_pressed);
                        mVp.setCurrentItem(3);
                        return true;
                    case R.id.navigation_me:
                        item.setIcon(R.mipmap.ic_menu_me_pressed);
                        mVp.setCurrentItem(4);
                        return true;
                }
                return false;
            }
        });




        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TopicFragment());
        fragments.add(new SortFragment());
        fragments.add(new ShopFragment());
        fragments.add(new MeFragment());
    }

    private int INDEX = 3;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //回调打开购物车
        if (requestCode == ShopCarActivity.RECOMMEND_CAR){
            bottomNavigationView.getMenu().getItem(INDEX).setChecked(true);
            mVp.setCurrentItem(INDEX);
        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragments;
        public MyFragmentPagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}