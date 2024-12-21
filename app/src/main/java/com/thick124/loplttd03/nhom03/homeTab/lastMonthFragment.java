package com.thick124.loplttd03.nhom03.homeTab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ute.moneybuddyp2q.API.Histories.Model.Histories;
import com.ute.moneybuddyp2q.API.Histories.Model.HistoryResponse;
import com.ute.moneybuddyp2q.API.Histories.RetrofitClient.ApiClient;
import com.ute.moneybuddyp2q.API.Histories.ServiceAPI.APIGetHistories;
import com.ute.moneybuddyp2q.API.Login.Model.User;
import com.ute.moneybuddyp2q.API.Login.MyApp;
import com.ute.moneybuddyp2q.AdapterGridHome;
import com.ute.moneybuddyp2q.R;
import com.ute.moneybuddyp2q.detailBill;
import com.ute.moneybuddyp2q.itemGrid;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lastMonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lastMonthFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public lastMonthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lastMonthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static lastMonthFragment newInstance(String param1, String param2) {
        lastMonthFragment fragment = new lastMonthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    GridView gv;
    ArrayList<itemGrid> myGridItem;
    AdapterGridHome adapterGridHome;
    TextView txtsdd;
    TextView txtsdc;
    TextView txttong;
    double sdd;
    double sdc;
    double tong;
    DecimalFormat decimalFormat = new DecimalFormat("#");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_last_month, container, false);
        gv = view.findViewById(R.id.gird_view_last_month);
        txtsdd = view.findViewById(R.id.textviewSDD);
        txtsdc = view.findViewById(R.id.textviewSDC);
        txttong = view.findViewById(R.id.textviewTongchitieu);
        User user = MyApp.getInstance().getCurrentUser();
        myGridItem = new ArrayList<>();
        APIGetHistories apiService = ApiClient.getRetrofitInstance().create(APIGetHistories.class);
        apiService.getPosts(user.getId()).enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Histories> histories = response.body().getHistories();

                    // Lấy tháng và năm hiện tại
                    Calendar calendar = Calendar.getInstance();
                    int currentMonth = calendar.get(Calendar.MONTH); // Tháng bắt đầu từ 0
                    int currentYear = calendar.get(Calendar.YEAR);

                    // Định dạng ngày trong dữ liệu API
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a", Locale.getDefault());

                    for (Histories history : histories) {
                        String ngayChi = history.getDate();
                        try {
                            Date date = sdf.parse(ngayChi);
                            if (date != null) {
                                Calendar itemCalendar = Calendar.getInstance();
                                itemCalendar.setTime(date);

                                int itemMonth = itemCalendar.get(Calendar.MONTH);
                                int itemYear = itemCalendar.get(Calendar.YEAR);

                                // Kiểm tra nếu tháng và năm của item là tháng trước
                                if (itemYear == currentYear && itemMonth == currentMonth - 1) {
                                    if (!history.getTypeOfExpenditure()) {
                                        sdc += history.getMoney();
                                    } else {
                                        sdd += history.getMoney();
                                    }
                                    myGridItem.add(new itemGrid(
                                            history.getId(),
                                            String.valueOf(history.getMoney()),
                                            ngayChi,
                                            history.getNote(),
                                            history.getAddress(),
                                            history.getTitle(),
                                            history.getImage(),
                                            history.getTypeOfExpenditure()
                                    ));
                                } else if (itemYear == currentYear - 1 && currentMonth == 0 && itemMonth == 11) {
                                    // Nếu tháng hiện tại là tháng 1, kiểm tra tháng 12 năm trước
                                    if (!history.getTypeOfExpenditure()) {
                                        sdc += history.getMoney();
                                    } else {
                                        sdd += history.getMoney();
                                    }
                                    myGridItem.add(new itemGrid(
                                            history.getId(),
                                            String.valueOf(history.getMoney()),
                                            ngayChi,
                                            history.getNote(),
                                            history.getAddress(),
                                            history.getTitle(),
                                            history.getImage(),
                                            history.getTypeOfExpenditure()
                                    ));
                                }
                            }
                        } catch (ParseException e) {
                            Log.e("DATE_PARSE_ERROR", "Error parsing date: " + ngayChi, e);
                        }
                    }

                    // Tính toán tổng
                    tong = sdd - sdc;
                    txttong.setText(String.valueOf(decimalFormat.format(tong)));
                    txtsdd.setText(String.valueOf(decimalFormat.format(sdd)));
                    txtsdc.setText(String.valueOf(decimalFormat.format(sdc)));

                    adapterGridHome.notifyDataSetChanged(); // Cập nhật GridView sau khi có dữ liệu mới
                } else {
                    Log.e("API_ERROR", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                Log.e("API_FAILURE", t.getMessage());
            }
        });

        adapterGridHome = new AdapterGridHome(requireContext(), R.layout.layout_item_gird_view, myGridItem);
        gv.setAdapter(adapterGridHome);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemGrid selectedItem = myGridItem.get(i);

                // Tạo Intent và truyền dữ liệu sang detailBill
                Intent intent = new Intent(requireContext(), detailBill.class);
                intent.putExtra("image", selectedItem.getImage());
                intent.putExtra("price", selectedItem.getSotien());
                intent.putExtra("date", selectedItem.getNgaychi());
                intent.putExtra("location", selectedItem.getDiadiem());
                intent.putExtra("description", selectedItem.getGhichu());

                // Chuyển sang màn hình detailBill
                startActivity(intent);
            }
        });

        return view;
    }

}