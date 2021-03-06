package com.bohan.android.bakingapp.MVP.Recipes.RecipeSteps;

/**
 * Created by Bo Han.
 */

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.annotation.Nullable;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bohan.android.bakingapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.C;

import butterknife.BindBool;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RecipeStepSingleFragment extends Fragment implements Player.EventListener {

    private static final String EXTRA_DESCRIPTION_ID = "EXTRA_DESCRIPTION_ID";
    private static final String EXTRA_VIDEO_URL_ID = "EXTRA_VIDEO_URL_ID";
    private static final String EXTRA_IMAGE_URL_ID = "EXTRA_IMAGE_URL_ID";
    private static final String EXTRA_PLAYER_POSITION = "extra_player_position";

    @BindView(R.id.recipe_step_desc_card)
    CardView descriptionCard;
    @BindView(R.id.recipe_step_image)
    ImageView stepThumbnail;
    @BindView(R.id.recipe_step_desc)
    TextView descTextView;
    @BindView(R.id.recipe_step_video)
    PlayerView exoPlayerView;

    @BindBool(R.bool.two_pane_mode)
    boolean isTwoPane;

    SimpleExoPlayer exoPlayer;
    private MediaSessionCompat mediaSession;
    private PlaybackStateCompat.Builder stateBuilder;

    Unbinder unbinder;

    private long position;
    //String video = video = getArguments().getString(EXTRA_VIDEO_URL_ID);

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

    public RecipeStepSingleFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        position = C.TIME_UNSET;
        if (savedInstanceState != null) {
            //...your code...
            position = savedInstanceState.getLong(EXTRA_PLAYER_POSITION, C.TIME_UNSET);
        }
        View view = inflater.inflate(R.layout.fragment_recipe_step_single, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            position = savedInstanceState.getLong(EXTRA_PLAYER_POSITION);
        }

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(EXTRA_PLAYER_POSITION,position);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String description = getArguments().getString(EXTRA_DESCRIPTION_ID);
        descTextView.setText(description);

        String imageUrl = getArguments().getString(EXTRA_IMAGE_URL_ID);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            // Load and show Image

            Glide.clear(stepThumbnail);
            Glide.with(getContext())
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(stepThumbnail);

            setViewVisibility(stepThumbnail, true);
        } else {
            // Hide image view
            setViewVisibility(stepThumbnail, false);
        }

        int orientation = getResources().getConfiguration().orientation;
        String video = getArguments().getString(EXTRA_VIDEO_URL_ID);

        if (video != null && !video.isEmpty()) {

            // Init and show video view
            setViewVisibility(exoPlayerView, true);
            initializeMediaSession();
            initializePlayer(Uri.parse(video));

            if (orientation == Configuration.ORIENTATION_LANDSCAPE && !isTwoPane) {
                // Expand video, hide description, hide system UI
                expandVideoView(exoPlayerView);
                setViewVisibility(descriptionCard, false);
                hideSystemUI();
            }

        } else {
            // Hide video view
            setViewVisibility(exoPlayerView, false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        String video = getArguments().getString(EXTRA_VIDEO_URL_ID);
        initializePlayer(Uri.parse(video));
        */
        /*
        String video = getArguments().getString(EXTRA_VIDEO_URL_ID);

        if (video != null)
            initializePlayer(Uri.parse(video));
            */

    }

    @Override
    public void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            position = exoPlayer.getCurrentPosition();
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setViewVisibility(View view, boolean show) {
        if (show) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    private void expandVideoView(PlayerView exoPlayer) {
        exoPlayer.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        exoPlayer.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
    }

    // Src: https://developer.android.com/training/system-ui/immersive.html
    private void hideSystemUI() {
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    // EXO PLAYER METHODS

    private void initializeMediaSession() {

        mediaSession = new MediaSessionCompat(getContext(), "RecipeStepSinglePageFragment");

        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        mediaSession.setMediaButtonReceiver(null);
        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MediaSessionCompat.Callback() {
            @Override
            public void onPlay() {
                exoPlayer.setPlayWhenReady(true);
            }

            @Override
            public void onPause() {
                exoPlayer.setPlayWhenReady(false);
            }

            @Override
            public void onSkipToPrevious() {
                exoPlayer.seekTo(0);
            }
        });
        mediaSession.setActive(true);
    }

    private void initializePlayer(Uri mediaUri) {

        if (exoPlayer == null) {

            TrackSelector trackSelector = new DefaultTrackSelector();
            exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.addListener(this);

            String userAgent = Util.getUserAgent(getContext(), "StepVideo");
            /*
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
                    */

            MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(
                    getContext(), userAgent)).setExtractorsFactory(new DefaultExtractorsFactory()).createMediaSource(mediaUri);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
            exoPlayer.seekTo(position);

        }
        else{
            String userAgent = Util.getUserAgent(getActivity(), getString(R.string.app_name));
            MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(
                    getContext(), userAgent)).setExtractorsFactory(new DefaultExtractorsFactory()).createMediaSource(mediaUri);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);

            exoPlayer.seekTo(position);
        }

    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }

        if (mediaSession != null) {
            mediaSession.setActive(false);
        }
    }



    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if ((playbackState == Player.STATE_READY) && playWhenReady) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING, exoPlayer.getCurrentPosition(), 1f);
        } else if ((playbackState == Player.STATE_READY)) {
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED, exoPlayer.getCurrentPosition(), 1f);
        }
        mediaSession.setPlaybackState(stateBuilder.build());
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }


    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }
}
