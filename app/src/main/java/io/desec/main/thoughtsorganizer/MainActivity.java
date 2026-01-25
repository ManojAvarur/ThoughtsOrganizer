package io.desec.main.thoughtsorganizer;

import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private MaterialCardView _dropdownMenu, _mainCardView;
    private Button _refreshBtn, _filterBtn, _listViewBtn, _cardViewBtn;
    private ScrollView _cardViewScroll;
    private TextView _cardTextView;
    private ImageView _scrollDownIcon1, _scrollDownIcon2;
    private boolean _isMenuVisible = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout());

            v.setPadding(
                    Math.max(systemBars.left, displayCutout.left),
                    Math.max(systemBars.top, displayCutout.top),
                    Math.max(systemBars.right, displayCutout.right),
                    Math.max(systemBars.bottom, displayCutout.bottom)
            );

            return insets;
        });

        loadElementsFromActivity();
        addAnimationToCardTopBarIcons();
        GestureDetector gesture = gestureDetectorToToggleMenubar();

        _cardTextView.setOnTouchListener((v, event) -> {
            gesture.onTouchEvent(event);
            return true;
        });

        _refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Testing Toast", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void showMenu() {
        _dropdownMenu.setVisibility(View.VISIBLE);
        Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        _dropdownMenu.startAnimation(slideDown);
        _isMenuVisible = true;
    }

    private void hideMenu() {
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);
        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                _dropdownMenu.setVisibility(View.GONE);
            }
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
        });
        _dropdownMenu.startAnimation(slideUp);
        _isMenuVisible = false;
    }

    private void loadElementsFromActivity(){
        _mainCardView = findViewById(R.id.card_view);
        _dropdownMenu = findViewById(R.id.menu_bar_card);
        _refreshBtn = findViewById(R.id.refresh_btn);
        _filterBtn = findViewById(R.id.filter_btn);
        _listViewBtn = findViewById(R.id.list_view_btn);
        _cardViewBtn = findViewById(R.id.card_view_btn);
        _cardViewScroll = findViewById(R.id.card_view_scroll);
        _cardTextView = findViewById(R.id.card_view_text);
        _scrollDownIcon1 = findViewById(R.id.ic_scroll_down_1);
        _scrollDownIcon2 = findViewById(R.id.ic_scroll_down_2);
    }

    private void addAnimationToCardTopBarIcons(){
        Animation icon2Animator = AnimationUtils.loadAnimation(this, R.anim.up_down_long_duration);
        Animation icon1Animator = AnimationUtils.loadAnimation(this, R.anim.up_down_short_duration);
        _scrollDownIcon2.setAnimation(icon2Animator);
        _scrollDownIcon1.setAnimation(icon1Animator);
    }

    private GestureDetector gestureDetectorToToggleMenubar(){
        return new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                float diffY = e2.getY() - e1.getY();

                // Swipe down detected
                if (diffY > 100 && Math.abs(velocityY) > 100) {
                    if (!_isMenuVisible) {
                        showMenu();
                    }
                    return true;
                }
                // Swipe up detected
                else if (diffY < -100 && Math.abs(velocityY) > 100) {
                    if (_isMenuVisible) {
                        hideMenu();
                    }
                    return true;
                }
                return false;
            }
        });
    }
}