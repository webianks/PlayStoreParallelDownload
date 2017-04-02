package com.webianks.task.playstoreparalleldownload;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.lzyzsd.circleprogress.DonutProgress;

import java.util.List;

/**
 * Created by R Ankit on 30-03-2017.
 */

public class PlayRecyclerAdapter extends RecyclerView.Adapter<PlayRecyclerAdapter.VH> {

    private List<App> appsList;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public PlayRecyclerAdapter(Context context, List<App> appsList) {
        this.appsList = appsList;
        this.context = context;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_app_layout, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

        String icon_url = appsList.get(position).getAppIcon();

        Glide.with(context).load(icon_url).into(holder.appImage);

        holder.appName.setText(appsList.get(position).getAppName());
        holder.developer.setText(appsList.get(position).getDeveloper());
        holder.rating.setText(String.valueOf(appsList.get(position).getStars()));

        if (appsList.get(position).isDownloading()) {
            holder.download.setVisibility(View.GONE);
            holder.progressBar.setVisibility(View.VISIBLE);
        } else {
            holder.download.setVisibility(View.VISIBLE);
            holder.progressBar.setVisibility(View.GONE);
        }


    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return (appsList == null) ? 0 : appsList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView appImage;
        ImageView download;
        TextView appName;
        TextView developer;
        TextView rating;
        DonutProgress progressBar;


        VH(View itemView) {
            super(itemView);

            appImage = (ImageView) itemView.findViewById(R.id.app_image);
            appName = (TextView) itemView.findViewById(R.id.app_name);
            developer = (TextView) itemView.findViewById(R.id.developer);
            rating = (TextView) itemView.findViewById(R.id.rating);
            progressBar = (DonutProgress) itemView.findViewById(R.id.download_progress);
            download = (ImageView) itemView.findViewById(R.id.download);

            download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (onItemClickListener != null) {
                        onItemClickListener.downloadButtonClicked(appsList.get(getAdapterPosition()).getDownloadLink());
                        appsList.get(getAdapterPosition()).setDownloading(true);
                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.setProgress(0f);
                        download.setVisibility(View.GONE);
                    }

                }
            });

        }
    }

    interface OnItemClickListener {

        void downloadButtonClicked(String url);
    }

}
