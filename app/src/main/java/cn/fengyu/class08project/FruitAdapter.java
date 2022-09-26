package cn.fengyu.class08project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.fengyu.class08project.entity.Fruit;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit = getItem(position);

        // FruitActivity(getContext()) -> ListView列表(parent) ->
        //      子项布局(R.layout.fruit_item) -> 多个控件(子项布局.findViewById)
        // 为每一个子项Item加载设定的布局
        View fruitItem = LayoutInflater.from(getContext()).inflate(R.layout.fruit_item,
                parent, false);

        // 子项布局中的多个控件设置参数
        ImageView itemImageView = fruitItem.findViewById(R.id.fruit_image_view);
        TextView itemName = fruitItem.findViewById(R.id.fruit_name);
        TextView itemPrice = fruitItem.findViewById(R.id.fruit_price);

        itemImageView.setImageResource(fruit.getImageId());
        itemName.setText(fruit.getName());
        itemPrice.setText(fruit.getPrice());

        return fruitItem;
    }
}
