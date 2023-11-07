package com.example.appbandienthoai.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appbandienthoai.R;
import com.example.appbandienthoai.activity.ChiTietSanPhamActivity;
import com.example.appbandienthoai.adapter.TatCaSanPham_Adapter;
import com.example.appbandienthoai.adapter.TimKiem_Adapter;
import com.example.appbandienthoai.molder.SanPham;
import com.example.appbandienthoai.ultil.Chech_internet;
import com.example.appbandienthoai.ultil.sever_link;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimKiem_Fragment extends Fragment {

    View view;
    //Biến giao diện
    ImageView imageSearch;
    EditText edTimkiem;
    RecyclerView recyclerView;
    LinearLayout linearTimkiem;

    ArrayList<SanPham> arraySearch = null;
    TimKiem_Adapter timKiem_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        AnhXa_TimKiem();
        Fragment();
        Search_TimKiem();
        return view;
    }

    private void AnhXa_TimKiem(){
        imageSearch = (ImageView) view.findViewById(R.id.timkiem_search);
        edTimkiem = (EditText) view.findViewById(R.id.timkiem_edit);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_search);
        linearTimkiem = (LinearLayout) view.findViewById(R.id.timkiem_null);
        arraySearch = new ArrayList<>();
        timKiem_adapter = new TimKiem_Adapter(getActivity(), arraySearch);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setAdapter(timKiem_adapter);
    }
    private void Fragment(){
        linearTimkiem.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }


    private void Search_TimKiem(){
        /*
        Log.d("tim", sever_link.duongdanTimKiem + "");
        RequestQueue requestQueue1 = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(sever_link.duongdanTimKiem, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("tim1", response + "");
                if (response != null) {
                    int id = 0, idsp = 0;
                    String tensp = "", motasp = "", anhsanpham = "";
                    Integer giasp = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tensp = jsonObject.getString("tenSP");
                            giasp = jsonObject.getInt("giaSP");
                            anhsanpham = jsonObject.getString("anhSP");
                            motasp = jsonObject.getString("moTaSP");
                            idsp = jsonObject.getInt("idSP");
                            arraySearch.add(new SanPham(id, tensp, giasp, anhsanpham, motasp, idsp));
                            //Fragment();
                            timKiem_adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue1.add(jsonArrayRequest1);

         */

        imageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lấy text trên edittext
                final String search = edTimkiem.getText().toString().trim();
                if (search.length() > 0){
                    arraySearch.clear();
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, sever_link.duongdanTimKiem, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("tim", response + "");
                            if (response != null && response.length() != 2) {
                                int id = 0, idloaisp_DT = 0, giadt = 0;
                                String tendt = "", anhdt = "", motadt = "";
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        id = jsonObject.getInt("id");
                                        tendt = jsonObject.getString("tenSP");
                                        giadt = jsonObject.getInt("giaSP");
                                        anhdt = jsonObject.getString("anhSP");
                                        motadt = jsonObject.getString("moTaSP");
                                        idloaisp_DT = jsonObject.getInt("idSP");
                                        arraySearch.add(new SanPham(id, tendt, giadt, anhdt, motadt, idloaisp_DT));
                                        linearTimkiem.setVisibility(View.INVISIBLE);
                                        recyclerView.setVisibility(View.VISIBLE);
                                        timKiem_adapter.notifyDataSetChanged();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            else {
                                linearTimkiem.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.INVISIBLE);
                                timKiem_adapter.notifyDataSetChanged();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("ten", search);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                }
                else {
                    Chech_internet.ShowToast(getActivity(), "Bạn vui lòng nhập nội dung tìm kiếm");
                }


            }
        });

    }
}
