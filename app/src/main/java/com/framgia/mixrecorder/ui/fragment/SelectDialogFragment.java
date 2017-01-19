package com.framgia.mixrecorder.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framgia.mixrecorder.R;
import com.framgia.mixrecorder.data.model.Song;
import com.framgia.mixrecorder.ui.adapter.SelectAdapter;

import java.util.List;

/**
 * Created by hoang on 1/19/2017.
 */
public class SelectDialogFragment extends DialogFragment {
    private static final String TITLE_SELECT_AUDIO = "Select an audio";
    private SelectDialogListener mSelectDialogListener;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SelectAdapter mAdapter;
    private List<Song> mListSong;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mListSong = Song.getSongs(getActivity());
        mSelectDialogListener = (SelectDialogFragment.SelectDialogListener) getTargetFragment();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_select, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_select);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SelectAdapter(getActivity(), mListSong, mSelectDialogListener, this);
        mRecyclerView.setAdapter(mAdapter);
        builder.setTitle(TITLE_SELECT_AUDIO).setView(view);
        return builder.create();
    }

    public interface SelectDialogListener {
        void onDialogSelectClick(String audioName, String audioPath);
    }
}
