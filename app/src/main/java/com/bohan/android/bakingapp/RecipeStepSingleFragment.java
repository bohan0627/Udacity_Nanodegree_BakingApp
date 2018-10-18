package com.bohan.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeStepSingleFragment extends Fragment implements Player.EventListener {

    private static final String EXTRA_DESCRIPTION_ID = "EXTRA_DESCRIPTION_ID";
    private static final String EXTRA_VIDEO_URL_ID = "EXTRA_VIDEO_URL_ID";
    private static final String EXTRA_IMAGE_URL_ID = "EXTRA_IMAGE_URL_ID";

    Unbinder unbinder;
    SimpleExoPlayer exoPlayer;
    private MediaSessionCompat media;
    private PlaybackStateCompat.Builder playBackState;

    public RecipeStepSingleFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View stepSingleView = inflater.inflate(R.layout.fragment_recipe_step_single, container, false);
        unbinder = ButterKnife.bind(this,stepSingleView);
        return stepSingleView;
    }

    public static RecipeStepSingleFragment newInstance(String description, String videoUrl,
                                                           String imageUrl) {

        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_DESCRIPTION_ID, description);
        arguments.putString(EXTRA_VIDEO_URL_ID, videoUrl);
        arguments.putString(EXTRA_IMAGE_URL_ID, imageUrl);
        RecipeStepSingleFragment fragment = new RecipeStepSingleFragment();
        fragment.setArguments(arguments);
        return fragment;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void stopPlayer(){
        if(exoPlayer != null){
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

        if(media != null)
            media.setActive(false);
    }

    @Override
    public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }
}
