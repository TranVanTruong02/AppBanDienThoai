package com.example.appbandienthoai.ultil;

public class sever_link {
    //Phân giải tên localhost ra để thiết bị hiểu được địa chỉ ip
    //Địa chỉ ip được lấy thông tin từ thiết bị kết nối, vì khi kết nốit cả 2 thiết bị phải chung 1 địa chỉ mạng
    public static String localhost = "10.40.8.158"; //Đây chính là địa chỉ localhost được phân giải

    //Đường dẫn loại sản phẩm <Trang Chính>
    public static String duongdanLoaisp = "http://" + localhost + "/server/AppBanDienThoai/loaisanpham.php";

    //Đường dẫn sản phẩm mới nhất
    public static String duongdanSanpham = "http://" + localhost + "/server/AppBanDienThoai/sanphammoinhat.php";

    //Đường dẫn tất cả sản phẩm
    public static String duongdanTatCaSanPham = "http://" + localhost + "/server/AppBanDienThoai/tatcasanpham.php?page=";

    public static String duongdanSanphamDienThoai = "http://" + localhost + "/server/AppBanDienThoai/sanpham.php?page=";

    //Đường dẫn thông tin mua hàng
    public static String duongdanThongTinMuaHang = "http://" + localhost + "/server/AppBanDienThoai/thongtinmuahang.php";

    //Đường dẫn thể get dữ liệu chi tiết đơn hàng từ app về
    public static String duongdanChiTietDonHang= "http://" + localhost + "/server/AppBanDienThoai/chitietdonhang.php";

    //Đường dẫn get dữ liệu đăng kí
    public static String duongdanDangKi = "http://" + localhost + "/server/AppBanDienThoai/dangki.php";

    //Đường dẫn kiểm tra đăng nhập
    public static String duongdanDangnhap = "http://" + localhost + "/server/AppBanDienThoai/dangnhap.php";

    //Đường dẫn thông tin tài khoản
    public static String duongdanThongtintaikhoan = "http://" + localhost + "/server/AppBanDienThoai/thongtintaikhoan.php?page=";

    //Đường dẫn thay đổi thông tin tài khoản
    public static String duongdanSuathongtintaikhoan = "http://" + localhost + "/server/AppBanDienThoai/suathongtintaikhoan.php?page=";

    //Đường dẫn lịch sử đơn hàng
    public static String duongdanLichSuDonHang = "http://" + localhost + "/server/AppBanDienThoai/lichsumuahang.php?page=";

    //Đường dẫn xoá đơn hàng
    public static String duongdanXoaDonHang = "http://" + localhost + "/server/AppBanDienThoai/xoadonhang.php";

    //Đường dẫn xoá chi tiet mua hang
    public static String duongdanXoaChiTietDonHang = "http://" + localhost + "/server/AppBanDienThoai/xoachitietdonhang.php";

    //Đường dẫn đếm madonhang
    public static String duongdanDemMaDonHang = "http://" + localhost + "/server/AppBanDienThoai/demchitietdonhang.php";

    //Đường dẫn Tìm Kiếm
    public static String duongdanTimKiem = "http://" + localhost + "/server/AppBanDienThoai/timkiem.php";


    //Lưu ý địa chỉ ipv4 thay đổi liên tục-phiền ghê
    //http://192.168.146.10/server/AppBanDienThoai/dangnhap.php
    //http://192.168.146.10/server/AppBanDienThoai/thongtintaikhoan.php?page=0984677725
    //http://192.168.65.10/server/AppBanDienThoai/tatcasanpham.php
    //http://192.168.65.10/server/AppBanDienThoai/demchitietdonhang.php

}
