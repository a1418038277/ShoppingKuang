package firsttest.test.shoppingkuang.ui.me.collect;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.realm.MyUser;

public class CollectAdapter extends BaseAdapter<MyUser> {
    public CollectAdapter(Context context, List<MyUser> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.collect_item;
    }

    @Override
    protected void bindData(MyUser data, VH vh) {
        ImageView img_collect = (ImageView) vh.getViewById(R.id.img_collect);
        TextView txt_desc = (TextView) vh.getViewById(R.id.txt_desc);
        TextView txt_title = (TextView) vh.getViewById(R.id.txt_title);
        TextView txt_price = (TextView) vh.getViewById(R.id.txt_price);
        Glide.with(context).load(data.getImgPath()).into(img_collect);
        txt_desc.setText(data.getDesc());
        txt_price.setText("￥"+data.getPrice());
        txt_title.setText(data.getTitle());
    }
}
