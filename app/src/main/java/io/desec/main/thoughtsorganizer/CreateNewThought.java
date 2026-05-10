package io.desec.main.thoughtsorganizer;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import io.desec.main.thoughtsorganizer.enums.IntentEnums;

public class CreateNewThought extends AppCompatActivity {

    private TextInputEditText _newThoughtInput;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_new_thought);
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

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadElementsFromActivity();
    }

    private void loadElementsFromActivity(){
        _newThoughtInput = findViewById(R.id.new_thought_input);
    }
}