package com.example.appbandienthoai.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Fragment_Adapter extends FragmentPagerAdapter {
    //Định nghĩa lại các fragment
    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
    //Hiển thị tên của tab
    private ArrayList<String> arrayTitle = new ArrayList<>();
    public Fragment_Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {//Muốn trả về bao nhiêu fragmet
        return arrayFragment.get(position);//Trả về vị trí thg đó
    }

    @Override
    public int getCount() {//Có bao nhiêu fragment
        return arrayFragment.size();//trả về mảng
    }
    public void addFragment(Fragment fragment, String title){
        //ép dữ liệu vào mảng
        arrayFragment.add(fragment);
        arrayTitle.add(title);

    }
    //Ép tên của mỗi fragment trong viewpage

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}
