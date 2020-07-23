package com.example.nestedrv;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class child_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<child_model> mChildModel;
    Context context;
    private MediaPlayer mp;
    private ImageView iVPlay;

    public child_adapter(List<child_model> childModel, Context context) {
        mChildModel = childModel;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mChildModel.get(position).getType()) {
            case 0:
                return child_model.IMAGE_TYPE;
            case 1:
                return child_model.VIDEO_TYPE;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        switch (viewType) {
            case child_model.IMAGE_TYPE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rv, parent, false);
                return new ImageHolder(v);
            case child_model.VIDEO_TYPE:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rv1, parent, false);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mp.isPlaying()) {
                            iVPlay.setVisibility(View.VISIBLE);
                            mp.pause();
                        } else {
                            mp.start();
                            iVPlay.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                return new VideoHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == child_model.IMAGE_TYPE) {
            ImageHolder ih = (ImageHolder) holder;
            String image = mChildModel.get(position).getUrl();
            ih.setData(image);
            myAsync myAsync = new myAsync(((ImageHolder) holder).iV, context);
            myAsync.execute(mChildModel.get(position).getUrl());
        } else if (holder.getItemViewType() == child_model.VIDEO_TYPE) {
            VideoHolder vh = (VideoHolder) holder;
            String video = mChildModel.get(position).getUrl();
            vh.setData(video);
        }
    }

    @Override
    public int getItemCount() {
        if (mChildModel == null)
            return 0;
        return mChildModel.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {
        private ImageView iV;
        private String path;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            iV = itemView.findViewById(R.id.child_iV);
        }

        private void setData(String image) {
            path = image;
        }
    }

    class VideoHolder extends RecyclerView.ViewHolder implements SurfaceHolder.Callback {
        private SurfaceView sV;
        private SurfaceHolder mSurfaceHolder;
        private String path;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            sV = itemView.findViewById(R.id.child_sV);
            mSurfaceHolder = sV.getHolder();
            mSurfaceHolder.addCallback(this);
            iVPlay = itemView.findViewById(R.id.playbtn);
        }

        private void setData(String url) {
            path = url;
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            mp = new MediaPlayer();
            mp.setDisplay(surfaceHolder);
            try {
                mp.setDataSource(path);
                mp.prepareAsync();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
//                        mp.stop();
//                        mp.reset();
//                        mp.release();
                        iVPlay.setVisibility(View.VISIBLE);
                    }
                });
                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                    }
                });
                mp.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mp.seekTo(1);
                    }
                });
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            mp.stop();
        }
    }
}
