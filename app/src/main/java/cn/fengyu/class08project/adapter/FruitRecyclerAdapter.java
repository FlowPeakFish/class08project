package cn.fengyu.class08project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.fengyu.class08project.R;
import cn.fengyu.class08project.entity.Fruit;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The type Fruit recycler adapter.
 */
public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.ViewHolder> {
    private final List<Fruit> mFruits;

    /**
     * Instantiates a new Fruit recycler adapter.
     *
     * @param objects the objects
     */
    public FruitRecyclerAdapter(List<Fruit> objects) {
        mFruits = objects;
    }

    /**
     * 创建ViewHolder对象，将加载出来的布局传入构造函数，最后将ViewHolder对象实例返回
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return ViewHolder对象实例
     */
    @Override
    public FruitRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
                                                              int viewType) {
        View fruitItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(fruitItem);
        // 图片监听事件
        viewHolder.fruitImage.setOnClickListener`(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getBindingAdapterPosition();
                Toast.makeText(fruitItem.getContext(),
                                "您点击图片：" + mFruits.get(position).getName(),
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });
        viewHolder.fruitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getBindingAdapterPosition();
                Toast.makeText(fruitItem.getContext(),
                                "您点击View：" + mFruits.get(position).getName(),
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });
        return viewHolder;
    }

    /**
     * 滚到到屏幕中间的子项，通过position获取对应的对象，设置图片和文字
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull @NotNull FruitRecyclerAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruits.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
        holder.fruitPrice.setText(fruit.getPrice());
    }

    @Override
    public int getItemCount() {
        return mFruits.size();
    }

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Fruit item.
         */
        View fruitItem;
        /**
         * The Fruit image.
         */
        ImageView fruitImage;
        /**
         * The Fruit name.
         */
        TextView fruitName;
        /**
         * The Fruit price.
         */
        TextView fruitPrice;

        /**
         * View holder.构造方法
         *
         * @param fruitItem 子视图（单个水果Item）
         */
        public ViewHolder(@NonNull @NotNull View fruitItem) {
            super(fruitItem);
            this.fruitItem = fruitItem;
            fruitImage = fruitItem.findViewById(R.id.fruit_image_view);
            fruitName = fruitItem.findViewById(R.id.fruit_name);
            fruitPrice = fruitItem.findViewById(R.id.fruit_price);
        }
    }
}
