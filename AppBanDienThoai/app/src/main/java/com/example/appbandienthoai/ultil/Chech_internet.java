package com.example.appbandienthoai.ultil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Chech_internet{
    //Sử dụng public để ở đâu cũng truy cập được và static là có thể truy cập nhiều màn hình
    public static boolean haveNetWorkConnection(Context context){
        //Khởi tạo 2 giá trị Wifi và Mobile với giá trị false
        boolean haveConnectdWifi = false;
        boolean haveConnectdMobile = false;

        //Xác định màn hình nào
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        //Sử dụng vòng lặp for lặp cho đến khi bắt được tính hiệu
        for (NetworkInfo ni : networkInfos){
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectdWifi = true; //Nếu bắt được wifi
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if(ni.isConnected())
                    haveConnectdMobile = true; //Nếu bắt được mobile
        }
        return haveConnectdWifi || haveConnectdMobile;
    }
    //Hàm thông báo
    public static void ShowToast(Context context, String thongbao){
        Toast.makeText(context, thongbao, Toast.LENGTH_SHORT).show();
    }

    //Lưu ý chỉ thực hiện được dòng code này khi cấp quyền kiểm tra
    //<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

}
