package com.example.signup_login;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.signup_login.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private ProgressBar progressBar;
    private Button startProgress;
    private TextView intakeText;
    private Spinner intakeSpinner;

    private int currentIntake = 0; // Jumlah air yang diminum
    private int maxIntake = 2100; // Target harian
    private int intakeStep = 20; // Default intake step

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "HydrateMePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_progresbarr);
//        setContentView(binding.getRoot());

//        binding.bottomNavigationView.setOnItemSelectedListener(item ->){
//
//            switch()
//
//
//        });

//        loadFragment(new HomeFragment());
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.button_nav_menu);
//
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            Fragment selectedFragment = null;
//
//            // Pilih Fragment berdasarkan menu yang ditekan
//            switch (item.getItemId()) {
//                case R.id.home:
//                    selectedFragment = new HomeFragment();
//                    break;
//                case R.id.history:
//                    selectedFragment = new HistoryFragment();
//                    break;
//                case R.id.artikel:
//                    selectedFragment = new ArticleFragment();
//                    break;
//                case R.id.settings:
//                    selectedFragment = new SettingsFragment();
//                    break;
//            }
//
//            if (selectedFragment != null) {
//                loadFragment(selectedFragment);
//            }
//            return true;
//        });
        //

        // Inisialisasi komponen
        progressBar = findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.start_progress);
        intakeText = findViewById(R.id.intake_text);
        intakeSpinner = findViewById(R.id.intake_spinner);

        // Atur Spinner untuk intake step
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.intake_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        intakeSpinner.setAdapter(adapter);

        intakeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Ambil nilai intake step dari Spinner
                String selectedItem = parent.getItemAtPosition(position).toString();
                intakeStep = Integer.parseInt(selectedItem.replace(" ml", ""));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Default nilai intake step
                intakeStep = 20;
            }
        });

        // Tombol untuk menambah progres
        startProgress.setOnClickListener(v -> {
            if (currentIntake < maxIntake) {
                currentIntake += intakeStep; // Tambahkan intake
                if (currentIntake > maxIntake) {
                    currentIntake = maxIntake; // Batasi agar tidak lebih dari target
                }

                // Perbarui progress bar
                int progress = (int) (((float) currentIntake / maxIntake) * 100);
                animateProgress(progressBar, progressBar.getProgress(), progress);

                // Perbarui teks
                updateIntakeText();

                // Jika sudah mencapai target
                if (currentIntake == maxIntake) {
                    Toast.makeText(this, "Target harian tercapai!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inisialisasi teks awal
        updateIntakeText();

        // Set progress bar awal ke 0
        progressBar.setProgress(0);
    }


    // Fungsi animasi progres
    private void animateProgress(ProgressBar progressBar, int startProgress, int endProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(progressBar, "progress", startProgress, endProgress);
        animator.setDuration(500); // Durasi animasi dalam milidetik
        animator.start();
    }

    // Perbarui teks intake
    private void updateIntakeText() {
        intakeText.setText(currentIntake + " ml / " + maxIntake + " ml");
    }
}
