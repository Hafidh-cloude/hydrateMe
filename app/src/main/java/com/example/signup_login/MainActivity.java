package com.example.signup_login;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button startProgress;
    private int progress = 0; // Progres awal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progresbarr);

        progressBar = findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.start_progress);

        startProgress.setOnClickListener(v -> {
            if (progress < 100) {
                progress += 10; // Tambahkan progres sebesar 10
                animateProgress(progressBar, progressBar.getProgress(), progress);
            }
        });
    }

    // Fungsi animasi untuk progres
    private void animateProgress(ProgressBar progressBar, int startProgress, int endProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", startProgress, endProgress);
        animator.setDuration(500); // Durasi animasi dalam milidetik
        animator.start();
    }
}
