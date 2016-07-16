package io.github.hisaichi5518.recyclerviewitemdecoration;

import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int margin = getResources().getDimensionPixelSize(R.dimen.margin);

        RecyclerView listView = (RecyclerView) findViewById(R.id.activity_main__list);
        listView.setAdapter(new ItemAdapter());
        listView.setLayoutManager(new GridLayoutManager(this, 2));
        listView.addItemDecoration(new Decoration(margin, margin, margin, margin));
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private static final int ITEM_COUNT = 20;

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(new TextView(MainActivity.this));
        }

        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            holder.render();
        }

        @Override
        public int getItemCount() {
            return ITEM_COUNT;
        }
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ItemViewHolder(View itemView) {
            super(itemView);
        }

        void render() {
            ((TextView) itemView).setText("こんにちはこんにちは");
            itemView.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RecyclerView listView = (RecyclerView) findViewById(R.id.activity_main__list);
                    listView.getAdapter().notifyItemRemoved(0);
                }
            });
        }
    }

    private class Decoration extends RecyclerView.ItemDecoration {
        private int mLeft, mTop, mRight, mBottom;

        Decoration(int left, int top, int right, int bottom) {
            mLeft = left;
            mTop = top;
            mRight = right;
            mBottom = bottom;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();

            int position = layoutParams.getViewAdapterPosition(); // 全体でのポジション
            if (position == RecyclerView.NO_POSITION) {
                outRect.set(0, 0, 0, 0);
                return;
            }

            outRect.left = mLeft;
            outRect.top = mTop;
            outRect.right = mRight;
            outRect.bottom = mBottom;
        }
    }
}
