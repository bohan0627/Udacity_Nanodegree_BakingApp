package com.bohan.android.bakingapp.MVP.widget;

/**
 * Created by Bo Han.
 */

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bohan.android.bakingapp.MVP.BakingApp;
import com.bohan.android.bakingapp.R;

import java.util.Set;

import javax.inject.Inject;

import androidx.core.content.ContextCompat;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;
import androidx.appcompat.widget.AppCompatRadioButton;

public class WidgetConfigActivity extends AppCompatActivity {

    private int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private CompositeDisposable disposableList;
    @Inject
    WidgetHelper widgetHelper;

    @BindView(R.id.radioGroup)
    RadioGroup namesRadioGroup;
    @BindString(R.string.widget_config_no_data)
    String noDataErrorMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int curIndex = 0;
        Set<String> recipeNames = widgetHelper.getRecipeNamesFromPrefs();

        setResult(RESULT_CANCELED);
        setContentView(R.layout.widget_configuration);
        ButterKnife.bind(this);

        disposableList = new CompositeDisposable();

        DaggerWidgetHelperComponent.builder()
                .recipeRepoComponent(
                        ((BakingApp) getApplication()).getRecipeRepositoryComponent())
                .build()
                .inject(this);

        if (extras != null) {
            widgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);

            if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
                finish();
            }
        }

        if (recipeNames.size() == 0) {
            Toast.makeText(this, noDataErrorMessage, Toast.LENGTH_SHORT).show();
            finish();
        }

        for (String name : recipeNames) {
            AppCompatRadioButton button = new AppCompatRadioButton(this);
            button.setText(name);
            button.setId(curIndex++);
            setupRadioButtonColor(button);
            namesRadioGroup.addView(button);
        }

        if (namesRadioGroup.getChildCount() > 0) {
            ((AppCompatRadioButton) namesRadioGroup.getChildAt(0)).setChecked(true);
        }
    }

    @OnClick(R.id.button)
    public void onOkButtonClick() {

        int checkedItemId = namesRadioGroup.getCheckedRadioButtonId();
        String recipeName = ((AppCompatRadioButton) namesRadioGroup
                .getChildAt(checkedItemId)).getText().toString();

        widgetHelper.saveRecipeNameToPrefs(widgetId, recipeName);
        Context context = getApplicationContext();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        Disposable subscription = widgetHelper
                .getIngredientsList(recipeName)
                .subscribe(
                        ingredients ->
                                WidgetProvider
                                        .updateWidgetContent(context, appWidgetManager, widgetId, recipeName,
                                                ingredients),
                        throwable ->
                                Timber.d("Error: unable to load data to widget."));

        disposableList.add(subscription);
        Intent result = new Intent();
        result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposableList.clear();
    }

    private void setupRadioButtonColor(AppCompatRadioButton button) {

        int color = ContextCompat.getColor(this, R.color.colorPrimary);

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{
                        color, color
                }
        );
        button.setSupportButtonTintList(colorStateList);
    }
}
