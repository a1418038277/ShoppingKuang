package firsttest.test.shoppingkuang.ui.sort.SortDetail;

import android.content.Context;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.util.List;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.base.BaseAdapter;
import firsttest.test.shoppingkuang.utils.ImageLoader;

public class CategoryBigImageAdapter extends BaseAdapter {

    public CategoryBigImageAdapter(Context context, List Data) {
        super(context, Data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_category_bigimage_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        String bean = (String) data;

        ImageView img= (ImageView) vh.getViewById(R.id.iv_bigimage_img);
        Glide.with(context).load(bean).into(img);

    }
}
