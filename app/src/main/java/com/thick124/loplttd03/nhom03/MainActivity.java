package com.thick124.loplttd03.nhom03;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.thick124.loplttd03.nhom03.homeFragment.ViewHomeAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //    ImageView shar;
    private ViewPager viewPager;
    LinearLayout account, spending, addSpending;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        viewPager= findViewById(R.id.viewpager);
        ViewHomeAdapter adapter = new ViewHomeAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        account = findViewById(R.id.account);
//        account.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Tạo Intent để chuyển tới managerAccount Activity
//                Intent intent = new Intent(MainActivity.this, managerAccount.class);
//                startActivity(intent);
//            }
//        });

        spending = findViewById(R.id.spending_layout);

        addSpending = findViewById(R.id.add_spending);
//        addSpending.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Tạo Intent để chuyển tới managerAccount Activity
//                Intent intent = new Intent(MainActivity.this, AddSpendingActivity.class);
//                startActivity(intent);
//            }
//        });

        LinearLayout calendar = findViewById(R.id.calendar);
//        calendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Tạo Intent để chuyển tới managerAccount Activity
//                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
//                startActivity(intent);
//            }
//        });

    }
}