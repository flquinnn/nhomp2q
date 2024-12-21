package com.thick124.loplttd03.nhom03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class managerAccount extends AppCompatActivity {
    LinearLayout home, spending, addSpending;
    LinearLayout account_settings_item;
    LinearLayout change_password_item;
    LinearLayout story_item;
    ImageView avatar;
    TextView username;
    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manager_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        User user = MyApp.getInstance().getCurrentUser();
        avatar = findViewById(R.id.user_profile_image);
        username = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        home =  findViewById(R.id.home);
        Picasso.get()
                .load(user.getAvatar()) // URL ảnh
                .placeholder(R.drawable.cafe) // Ảnh hiển thị tạm khi tải
                .error(R.drawable.new_pd) // Ảnh hiển thị nếu tải thất bại
                .into(avatar);
        username.setText(user.getUsername());
        email.setText(user.getEmail());
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(managerAccount.this,MainActivity.class);
                startActivity(intent);
            }
        });
        account_settings_item  = findViewById(R.id.account_settings_item);
        account_settings_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(managerAccount.this,detailAccount.class);
                startActivity(intent);
            }
        });
        change_password_item = findViewById(R.id.change_password_item);
        change_password_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(managerAccount.this, resetPasswordOldPassword.class);
                startActivity(intent);
            }
        });
        story_item = findViewById(R.id.story_item);
        story_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(managerAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });
        spending = findViewById(R.id.spending_layout);
        spending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển tới managerAccount Activity
                Intent intent = new Intent(managerAccount.this, SpendingActivity.class);
                startActivity(intent);
            }
        });

        addSpending = findViewById(R.id.add_spending);
        addSpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển tới managerAccount Activity
                Intent intent = new Intent(managerAccount.this, AddSpendingActivity.class);
                startActivity(intent);
            }
        });

        Button logout = findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển tới managerAccount Activity
                Intent intent = new Intent(managerAccount.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout calendar = findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển tới managerAccount Activity
                Intent intent = new Intent(managerAccount.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout changeMoney = findViewById(R.id.change_money_item);
        changeMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển tới managerAccount Activity
                Intent intent = new Intent(managerAccount.this, ChangeCurrencyActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout exportFile = findViewById(R.id.export_file_item);
        exportFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo Intent để chuyển tới managerAccount Activity
                Intent intent = new Intent(managerAccount.this, ExportFileActivity.class);
                startActivity(intent);
            }
        });
    }
}