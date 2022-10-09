package cn.fengyu.class08project.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.fengyu.class08project.R;
import cn.fengyu.class08project.entity.Music;
import cn.fengyu.class08project.media.MusicPlayActivity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * The type Fruit recycler adapter.
 */
public class MusicRecyclerAdapter extends RecyclerView.Adapter<MusicRecyclerAdapter.ViewHolder> {
    private final List<Music> mMusics;

    /**
     * Instantiates a new Fruit recycler adapter.
     *
     * @param objects the objects
     */
    public MusicRecyclerAdapter(List<Music> objects) {
        mMusics = objects;
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
    public MusicRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
                                                              int viewType) {
        View musicItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(musicItem);
        viewHolder.musicItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getBindingAdapterPosition();
                Toast.makeText(musicItem.getContext(),
                                "您点击View：" + mMusics.get(position).getName(),
                                Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(parent.getContext(), MusicPlayActivity.class);
                intent.putExtra("position", position);
                parent.getContext().startActivity(intent);
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
    public void onBindViewHolder(@NonNull @NotNull MusicRecyclerAdapter.ViewHolder holder, int position) {
        Music music = mMusics.get(position);
        holder.name.setText(music.getName());
        holder.artist.setText(music.getArtist());
    }

    @Override
    public int getItemCount() {
        return mMusics.size();
    }

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * The Fruit item.
         */
        View musicItem;
        /**
         * The Fruit name.
         */
        TextView name;
        /**
         * The Fruit price.
         */
        TextView artist;

        /**
         * View holder.构造方法
         *
         * @param musicItem 子视图（单个音乐Item）
         */
        public ViewHolder(@NonNull @NotNull View musicItem) {
            super(musicItem);
            this.musicItem = musicItem;
            name = musicItem.findViewById(R.id.name);
            artist = musicItem.findViewById(R.id.artist);
        }
    }
}
