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

import com.thick124.loplttd03.nhom03.API.Histories.Model.Histories;
import com.thick124.loplttd03.nhom03.API.Histories.Model.HistoryResponse;
import com.thick124.loplttd03.nhom03.API.Histories.RetrofitClient.ApiClient;
import com.thick124.loplttd03.nhom03.API.Histories.ServiceAPI.APIGetHistories;
import com.thick124.loplttd03.nhom03.API.Login.Model.User;
import com.thick124.loplttd03.nhom03.API.Login.MyApp;
import com.thick124.loplttd03.nhom03.AdapterGridHome;
import com.thick124.loplttd03.nhom03.R;
import com.thick124.loplttd03.nhom03.itemGrid;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link thisMonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class thisMonthFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public thisMonthFragment() {
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
    public static thisMonthFragment newInstance(String param1, String param2) {
        thisMonthFragment fragment = new thisMonthFragment();
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
    int[] image = {R.drawable.cafe,R.drawable.ic_user_profile,R.drawable.new_pd};
    String title[] = {"Ăn cơm","Xem phim","Ăn uống"};
    String description[] = {"Đi xả xì chét", "Đi vui chơi với bạn bè","Đi bar"};
    String date[]= {"01/01/2024","02/09/2024","11/10/2024"};
    String location[]= {"Cafe nhà tí","Rio Đà Nẵng","New Phương Đông"};
    String[] price = {"100000","260000","500000"};
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
        View view = inflater.inflate(R.layout.fragment_this_month, container, false);
        gv =view.findViewById(R.id.gird_view_this_month);
        myGridItem= new ArrayList<>();
        txtsdd = view.findViewById(R.id.textviewSDD);
        txtsdc = view.findViewById(R.id.textviewSDC);
        txttong = view.findViewById(R.id.textviewTongchitieu);
//        User user = MyApp.getInstance().getCurrentUser();
        APIGetHistories apiService = ApiClient.getRetrofitInstance().create(APIGetHistories.class);
        Call<HistoryResponse> call = apiService.getPosts("67590701ebdd3d23acf76aee");
        call.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Histories> histories = response.body().getHistories();
                    for (Histories history : histories) {
                        if(history.getTypeOfExpenditure()){
                            sdd=sdd+history.getMoney();
                        } else {
                            sdc= sdc+history.getMoney();
                        }
                        myGridItem.add(new itemGrid(history.getId(),String.valueOf(history.getMoney()),history.getDate(),history.getNote(),history.getAddress(),history.getTitle(),history.getImage(),history.getTypeOfExpenditure()));
                    }
                    // Cập nhật giá trị tổng sau khi xử lý xong
                    tong = sdd - sdc;

                    txttong.setText(String.valueOf(decimalFormat.format(tong)));
                    txtsdd.setText(String.valueOf(decimalFormat.format(sdd)));
                    txtsdc.setText(String.valueOf(decimalFormat.format(sdc)));

                    adapterGridHome.notifyDataSetChanged();  // Cập nhật GridView sau khi có dữ liệu mới
                } else {
                    Log.e("API_ERROR", "Response not successful");
                }
            }

            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
                Log.e("API_FAILURE", t.getMessage());
            }
        });
        adapterGridHome = new AdapterGridHome(requireContext(),R.layout.layout_item_gird_view, myGridItem);
        gv.setAdapter(adapterGridHome);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemGrid selectedItem = myGridItem.get(i);

                // Tạo Intent và truyền dữ liệu sang detailBill
//                Intent intent = new Intent(requireContext(), detailBill.class);
//                intent.putExtra("id",selectedItem.getId());
//                intent.putExtra("image", selectedItem.getImage());
//                intent.putExtra("price", selectedItem.getSotien());
//                intent.putExtra("date", selectedItem.getNgaychi());
//                intent.putExtra("location", selectedItem.getDiadiem());
//                intent.putExtra("description", selectedItem.getGhichu());
//                intent.putExtra("title",selectedItem.getTitle());
//                intent.putExtra("type",selectedItem.getType());
//
//                // Chuyển sang màn hình detailBill
//                startActivity(intent);
            }
        });


        return view;
    }
}