package com.framgia.mixrecorder.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.mixrecorder.R;
import com.framgia.mixrecorder.data.model.Song;
import com.framgia.mixrecorder.ui.fragment.SelectDialogFragment;

import java.util.List;

/**
 * Created by hoang on 1/19/2017.
 */
public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Song> mListSong;
    private SelectDialogFragment.SelectDialogListener mSelectDialogListener;
    private SelectDialogFragment mSelectDialogFragment;

    public SelectAdapter(Context context, List<Song> listSong,
                         SelectDialogFragment.SelectDialogListener selectDialogListener,
                         SelectDialogFragment selectDialogFragment) {
        mInflater = LayoutInflater.from(context);
        mListSong = listSong;
        mSelectDialogListener = selectDialogListener;
        mSelectDialogFragment = selectDialogFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_select, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return mListSong != null ? mListSong.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text_select_audio);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Song song = mListSong.get(getAdapterPosition());
                    mSelectDialogListener.onDialogSelectClick(song.getName(), song.getPath());
                    mSelectDialogFragment.dismiss();
                }
            });
        }

        public void bindData() {
            if (mListSong == null) return;
            mTextView.setText(mListSong.get(getAdapterPosition()).getName());
        }
    }
}
