package com.r4sh33d.tourister;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.r4sh33d.tourister.base.BaseActivity;
import com.r4sh33d.tourister.home.LandingPageFragment;
import com.r4sh33d.tourister.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar_root)
    ConstraintLayout progressBarRoot;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.progress_message)
    TextView progressMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        replaceFragment(new LandingPageFragment(), false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        showLoading("Please wait");
    }

    @Override
    public void showLoading(int resId) {
        showLoading(getString(resId));
    }

    @Override
    public void showLoading(String message) {
        ViewUtils.show(progressBarRoot, progressBar, progressMessage);
        progressMessage.setText(message);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void dismissLoading() {
        ViewUtils.hide(progressBarRoot);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void hideToolbar() {
        getSupportActionBar().hide();
    }

    public void showToolbar() {
        getSupportActionBar().show();
    }
}

