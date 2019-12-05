package com.example.letitgoat.ui.home.buy_recycler;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.letitgoat.R;

import java.util.HashMap;

class BuyViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ItemClickListener mClickListener;
    private Context mContext;

    BuyViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    // Provide a reference to the views for each data single_buy
    // Complex data items may need more than one view per single_buy, and
    // you provide access to all the views for a data single_buy in a view holder
    public class ItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView name;
        private TextView price;
        private TextView date;

        ItemsViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.itemImage);
            name = v.findViewById(R.id.name);
            price = v.findViewById(R.id.price);
            date = v.findViewById(R.id.date);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
        private SliderLayout mDemoSlider;
        SliderViewHolder(View v) {
            super(v);
            mDemoSlider = v.findViewById(R.id.slider);


            HashMap<String,String> url_maps = new HashMap<String, String>();
            url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
            url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
            url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
            url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

            HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
            file_maps.put("Hannibal",R.drawable.wpi);
            file_maps.put("Big Bang Theory",R.drawable.wpi);
            file_maps.put("House of Cards",R.drawable.wpi);
            file_maps.put("Game of Thrones", R.drawable.wpi);

            for(String name : file_maps.keySet()){
                TextSliderView textSliderView = new TextSliderView(mContext);
                // initialize a SliderLayout
                textSliderView
                        .description(name)
                        .image(file_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",name);

                mDemoSlider.addSlider(textSliderView);
            }
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            mDemoSlider.startAutoCycle(5000, 5000, true);
            mDemoSlider.addOnPageChangeListener(this);
            mDemoSlider.stopAutoCycle();

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

        @Override
        public void onSliderClick(BaseSliderView slider) {
            Toast.makeText(mContext,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 0) {
            view = inflater.inflate(R.layout.slider, parent, false);
            viewHolder = new SliderViewHolder(view);
//            view = inflater.inflate(R.layout.single_buy, parent, false);
//            viewHolder = new ItemsViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.single_buy, parent, false);
            viewHolder = new ItemsViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position != 0) {
            ((ItemsViewHolder)holder).name.setText("Cool Item");
            ((ItemsViewHolder)holder).price.setText("$1,000,000");
            ((ItemsViewHolder)holder).date.setText("Today or 11/20/2019");
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }


    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}