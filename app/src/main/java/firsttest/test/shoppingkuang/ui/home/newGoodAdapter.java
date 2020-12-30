package firsttest.test.shoppingkuang.ui.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import firsttest.test.shoppingkuang.R;
import firsttest.test.shoppingkuang.model.home.HomeBean;

public class newGoodAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<HomeBean.DataBean.NewGoodsListBean> mDataBeans;

    public newGoodAdapter(Context mContext, ArrayList<HomeBean.DataBean.NewGoodsListBean> mDataBeans) {
        this.mContext = mContext;
        this.mDataBeans = mDataBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.newgood_holder0, null);

        return new ViewHolder0(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeBean.DataBean.NewGoodsListBean listBean = mDataBeans.get(position);
        ViewHolder0 holder0 = (ViewHolder0) holder;
        Glide.with(mContext).load(listBean.getList_pic_url()).into(holder0.img);
        holder0.name.setText(listBean.getName());
        holder0.price.setText("￥"+listBean.getRetail_price()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myItemClickListen.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    public class ViewHolder0 extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.newgood_img);
            name = itemView.findViewById(R.id.newgood_name);
            price = itemView.findViewById(R.id.newgood_price);
        }
    }

     public interface MyItemClickListen{
             void onItemClick(int pos);
         }

         MyItemClickListen myItemClickListen;

         public void setMyItemClickListen(MyItemClickListen myItemClickListen) {
             this.myItemClickListen = myItemClickListen;
         }
}
