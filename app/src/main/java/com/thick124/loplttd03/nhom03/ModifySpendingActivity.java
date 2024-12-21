package com.thick124.loplttd03.nhom03;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ModifySpendingActivity extends AppCompatActivity {

    private TextView locationText, friendText;
    private ImageView selectedImage;
    private TextView typeText;
    private TextView dateText, timeText;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;
    EditText inputMoney;
    String postId;
    private String selectedDate;
    private String selectedTime;

    // Hiển thị lựa chọn của "Loại"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modify_spending);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        Intent intent = getIntent();
        String price_itent = intent.getStringExtra("price");
        String id_intent = intent.getStringExtra("id");
        postId = id_intent;
        String date_intent = intent.getStringExtra("date");
        String description_intent = intent.getStringExtra("description");
        String image_intent = intent.getStringExtra("image");
        String location_intent = intent.getStringExtra("location");
        String title_intent = intent.getStringExtra("title");
        Boolean type_intent = intent.getBooleanExtra("type",false);
        Toast.makeText(ModifySpendingActivity.this,postId,Toast.LENGTH_SHORT).show();
//        Toast.makeText(ModifySpendingActivity.this,id_intent,Toast.LENGTH_SHORT).show();
        LinearLayout home = findViewById(R.id.home);
        LinearLayout calendar_layout = findViewById(R.id.calendar);
        LinearLayout spending_layout = findViewById(R.id.spending_layout);
        LinearLayout account = findViewById(R.id.account);



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifySpendingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        calendar_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifySpendingActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        spending_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifySpendingActivity.this, SpendingActivity.class);
                startActivity(intent);
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifySpendingActivity.this, managerAccount.class);
                startActivity(intent);
            }
        });

        ImageView close = findViewById(R.id.imageView);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifySpendingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView save = findViewById(R.id.textView);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleInsertExpenditure();
            }
        });

        // Switch thu- chi
        TextView textThu = findViewById(R.id.text_thu);
        TextView textChi = findViewById(R.id.text_chi);
        typeText = findViewById(R.id.type_text);
        typeText.setText(title_intent);



//        textChi.setSelected(true);
//        typeText.setText("Chi tiêu");

        textThu.setOnClickListener(v -> {
            textThu.setSelected(true);
            textChi.setSelected(false);
            typeText.setText(textThu.isSelected() ? "Thu nhập" : "Chi tiêu");
        });

        textChi.setOnClickListener(v -> {
            textChi.setSelected(true);
            textThu.setSelected(false);
            typeText.setText(textThu.isSelected() ? "Thu nhập" : "Chi tiêu");
        });
        // Liên kết các view
        LinearLayout locationSelection = findViewById(R.id.location_selection);
        LinearLayout friendSelection = findViewById(R.id.friend_selection);
        ImageButton selectImageButton = findViewById(R.id.select_image_button);
        ImageButton captureImageButton = findViewById(R.id.capture_image_button);
        selectedImage = findViewById(R.id.selected_image);
        Picasso.get()
                .load(image_intent) // URL ảnh
                .placeholder(R.drawable.cafe) // Ảnh hiển thị tạm khi tải
                .error(R.drawable.new_pd) // Ảnh hiển thị nếu tải thất bại
                .into(selectedImage);
        locationText = findViewById(R.id.location_text);
        friendText = findViewById(R.id.friend_text);
        // Liên kết view
        LinearLayout typeSelection = findViewById(R.id.type_selection);

        // Liên kết các view
        LinearLayout dateSelection = findViewById(R.id.date_selection);
        LinearLayout timeSelection = findViewById(R.id.time_selection);
        dateText = findViewById(R.id.date_text);
        dateText.setText(date_intent);
        timeText = findViewById(R.id.time_text);
        // Gán giờ hiện tại mặc định
        Calendar calendar = Calendar.getInstance();
        selectedDate = String.format("%02d/%02d/%04d", calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
        dateText.setText(selectedDate);
        selectedTime = String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        timeText.setText(selectedTime);
        inputMoney = findViewById(R.id.editTextSpending);
        inputMoney.setText(price_itent);
        TextView txtThu = findViewById(R.id.text_thu);
        if(type_intent){
            textChi.setSelected(false);
            textThu.setSelected(true);
        } else {
            textChi.setSelected(true);
            textThu.setSelected(false);
        }
        EditText notesInput = findViewById(R.id.note_input);
        notesInput.setText(description_intent);
        EditText location = findViewById(R.id.location_text);
        location.setText(location_intent);
        // Xử lý khi chọn ngày
        dateSelection.setOnClickListener(v -> showDatePicker());

        // Xử lý khi chọn giờ
        timeSelection.setOnClickListener(v -> showTimePicker());

        // Xử lý khi chọn loại
        typeSelection.setOnClickListener(v -> showTypeDialog());

        // Chọn bạn bè
        friendSelection.setOnClickListener(v -> showContactsDialog());

        // Chọn ảnh từ thư viện
        selectImageButton.setOnClickListener(v -> openGallery());

        // Chụp ảnh
        captureImageButton.setOnClickListener(v -> captureImage());
    }
    private void showTypeDialog() {
        TextView txtThu = findViewById(R.id.text_thu);

        List<String> thuTypes = new ArrayList<>();
        thuTypes.add("Lương");
        thuTypes.add("Kinh doanh");
        thuTypes.add("Thưởng");
        thuTypes.add("Quà tặng");

        List<String> chiTypes = new ArrayList<>();
        chiTypes.add("Ăn uống");
        chiTypes.add("Di chuyển");
        chiTypes.add("Mua sắm");
        chiTypes.add("Giải trí");
        chiTypes.add("Hóa đơn");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn hoặc thêm loại chi tiêu");

        List<String> expenseTypes = txtThu.isSelected() ? thuTypes : chiTypes;

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_expense_type, null);
        builder.setView(dialogView);

        ListView listView = dialogView.findViewById(R.id.expenseTypeListView);
        EditText inputNewType = dialogView.findViewById(R.id.inputNewExpenseType);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseTypes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            typeText.setText(expenseTypes.get(position));
            inputNewType.setText(expenseTypes.get(position));
        });

        builder.setPositiveButton("OK", (dialog, which) -> {
            String newType = inputNewType.getText().toString().trim();
            if (!newType.isEmpty()) {
                expenseTypes.add(newType);
                adapter.notifyDataSetChanged();
                typeText.setText(newType);
            }
            dialog.dismiss();});
        builder.create().show();
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month1 + 1, year1);
            dateText.setText(selectedDate);
        }, year, month, day);

        datePickerDialog.show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute1) -> {
            selectedTime = String.format("%02d:%02d", hourOfDay, minute1);
            timeText.setText(selectedTime);
        }, hour, minute, true);

        timePickerDialog.show();
    }


    // Hiển thị hộp thoại chọn bạn bè
//    private void showFriendDialog() {
//        String[] friends = {"Nguyễn Văn A", "Trần Thị B", "Lê Minh C"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Chọn bạn bè");
//        builder.setItems(friends, (dialog, which) -> friendText.setText(friends[which]));
//        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
//        builder.create().show();
//    }
    private void showContactsDialog() {
        List<String> contacts = getContacts(); // Lấy danh sách tên từ danh bạ
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn bạn bè");

        // Chuyển danh sách danh bạ thành mảng
        String[] contactsArray = contacts.toArray(new String[0]);
        builder.setItems(contactsArray, (dialog, which) -> {
            // Xử lý khi người dùng chọn tên bạn bè
            String selectedContact = contactsArray[which];
            Toast.makeText(this, "Bạn đã chọn: " + selectedContact, Toast.LENGTH_SHORT).show();
            // Lưu hoặc xử lý dữ liệu liên quan ở đây
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private List<String> getContacts() {
        List<String> contactsList = new ArrayList<>();

        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        // Truy vấn danh bạ
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                contactsList.add(name);
            }
            cursor.close();
        }

        return contactsList;
    }

    // Mở thư viện ảnh
    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                        selectedImage.setImageBitmap(bitmap);
                        selectedImage.setVisibility(View.VISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }

    // Chụp ảnh
    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                    selectedImage.setImageBitmap(bitmap);
                    selectedImage.setVisibility(View.VISIBLE);
                }
            });

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraLauncher.launch(intent);
    }
    private void handleInsertExpenditure() {
        inputMoney = findViewById(R.id.editTextSpending);
        TextView txtThu = findViewById(R.id.text_thu);
        EditText notesInput = findViewById(R.id.note_input);
        EditText location = findViewById(R.id.location_text);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date date;
        Date time;

        try {
            date = dateFormat.parse(selectedDate);
            time = timeFormat.parse(selectedTime);
            String tien = inputMoney.getText().toString();
            Double money = Double.parseDouble(tien);
            boolean spendingSelected = txtThu.isSelected();
            String typeOfSpending = (String) typeText.getText();
            String note = String.valueOf(notesInput.getText());
            String local = String.valueOf(location.getText());

            if (inputMoney.getText().toString().isEmpty()) {
                Toast.makeText(ModifySpendingActivity.this, "Vui lòng nhập số tiền!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (typeOfSpending.isEmpty()) {
                Toast.makeText(ModifySpendingActivity.this, "Vui lòng chọn loại chi tiêu!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (location.getText().toString().isEmpty()) {
                local = "";
            }

            if (notesInput.getText().toString().isEmpty()) {
                note = "";
            }

            UpdateRequest expenditure = new UpdateRequest(
                    typeOfSpending,
                    money,
                    note,
                    local,
                    spendingSelected
            );

            Log.d("Test", expenditure.getNote() + expenditure.getAddress());

            ExpenditureApi apiService = ApiClient.getRetrofitInstance().create(ExpenditureApi.class);
            Call<ExpenditureResponse> call = apiService.update(expenditure);
            call.enqueue(new Callback<ExpenditureResponse>() {
                @Override
                public void onResponse(Call<ExpenditureResponse> call, Response<ExpenditureResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(ModifySpendingActivity.this, "Cập nhật chi tiêu thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModifySpendingActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ModifySpendingActivity.this, "Cập nhật thất bại thất bại: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ExpenditureResponse> call, Throwable t) {
                    Toast.makeText(ModifySpendingActivity.this, "Không thể kết nối đến server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}