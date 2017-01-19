package com.framgia.mixrecorder.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.framgia.mixrecorder.R;
import com.framgia.mixrecorder.data.model.AudioInfo;
import com.framgia.mixrecorder.ui.adapter.MixAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang on 1/18/2017.
 */
public class MixFragment extends Fragment
    implements View.OnClickListener, SelectDialogFragment.SelectDialogListener {
    private static final String TAG = "SelectDialogFragment";
    private Button mButtonAdd;
    private Button mButtonMix;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MixAdapter mAdapter;
    private FragmentManager mFragmentManager;
    private List<AudioInfo> mListAudio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mix, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);
        mFragmentManager = getFragmentManager();
        mListAudio = new ArrayList<>();
        mButtonAdd = (Button) getView().findViewById(R.id.button_add_mix);
        mButtonMix = (Button) getView().findViewById(R.id.button_complete_mix);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view_mix);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MixAdapter(getActivity(), mListAudio);
        mRecyclerView.setAdapter(mAdapter);
        mButtonAdd.setOnClickListener(this);
        mButtonMix.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_add_mix:
                SelectDialogFragment selectDialogFragment = new SelectDialogFragment();
                selectDialogFragment.setTargetFragment(this, 1);
                selectDialogFragment.show(mFragmentManager, TAG);
                break;
            case R.id.button_complete_mix:
                // TODO call mix() function
                break;
            default:
                break;
        }
    }

    @Override
    public void onDialogSelectClick(String audioName, String audioPath) {
        mListAudio.add(new AudioInfo(audioName, audioPath));
        mAdapter.notifyItemInserted(mListAudio.size());
    }

    public void mix(int numOfChannel, int bitrate, String output) {
        String command = "";
        for (AudioInfo audioInfo : mListAudio) {
            command += " -i " + audioInfo.getAudioPath();
        }
        command += " -filter_complex \"";
        for (int i = 0; i < mListAudio.size(); i++) {
            int offset = mListAudio.get(i).getOffset();
            float volume = mListAudio.get(i).getVolume();
            if (offset != 0 || volume != 1) command += "[" + i + ":a]";
            if (offset != 0) {
                command += "adelay=" + offset;
                if (numOfChannel > 1)
                    for (int j = 1; j < numOfChannel; j++) command += "|" + offset;
            }
            if (offset != 0 && volume != 1) command += ",";
            if (volume != 1) command += "volume=" + volume;
            if (offset != 0 || volume != 1) command += "[a" + i + "];";
        }
        for (int i = 0; i < mListAudio.size(); i++) {
            if (mListAudio.get(i).getOffset() != 0 || mListAudio.get(i).getVolume() != 1)
                command += "[a" + i + "]";
            else command += "[" + i + ":a]";
        }
        command += "amix,volume=2[ar]\" -map \"[ar]\" ";
        command += "-b:a " + bitrate + "k ";
        command += output;
        // TODO mix using command
    }
}
