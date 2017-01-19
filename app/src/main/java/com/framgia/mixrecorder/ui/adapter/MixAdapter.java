package com.framgia.mixrecorder.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.framgia.mixrecorder.R;
import com.framgia.mixrecorder.data.model.AudioInfo;

import java.util.List;

/**
 * Created by hoang on 1/18/2017.
 */
public class MixAdapter extends RecyclerView.Adapter<MixAdapter.ViewHolder> {
    private static final int DEFAULT_OFFSET = 0;
    private static final float DEFAULT_VOLUME = 1;
    private LayoutInflater mInflater;
    private List<AudioInfo> mListAudio;

    public MixAdapter(Context context, List<AudioInfo> listAudio) {
        mInflater = LayoutInflater.from(context);
        mListAudio = listAudio;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.card_item_mix, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return mListAudio != null ? mListAudio.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button mButtonClose;
        private TextView mTextAudioName;
        private EditText mEditOffset;
        private EditText mEditVolume;

        public ViewHolder(View itemView) {
            super(itemView);
            mButtonClose = (Button) itemView.findViewById(R.id.button_close_mix);
            mTextAudioName = (TextView) itemView.findViewById(R.id.text_audio_name_mix);
            mEditOffset = (EditText) itemView.findViewById(R.id.text_offset);
            mEditVolume = (EditText) itemView.findViewById(R.id.text_volume);
            mButtonClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListAudio.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
            mEditOffset.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.toString().equals("")) return;
                    mListAudio.get(getAdapterPosition())
                        .setOffset(Integer.parseInt(editable.toString()));
                }
            });
            mEditVolume.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (editable.toString().equals("")) return;
                    mListAudio.get(getAdapterPosition())
                        .setVolume(Float.parseFloat(editable.toString()));
                }
            });
        }

        public void bindData() {
            if (mListAudio == null) return;
            mTextAudioName.setText(mListAudio.get(getAdapterPosition()).getAudioName());
            mEditOffset.setText(DEFAULT_OFFSET + "");
            mEditVolume.setText(DEFAULT_VOLUME + "");
        }
    }
}
