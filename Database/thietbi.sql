-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 07, 2023 lúc 07:38 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `thietbi`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `idd` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `anhsanpham` varchar(100) NOT NULL,
  `tensanpham` varchar(50) NOT NULL,
  `giasanpham` int(20) NOT NULL,
  `soluongsanpham` int(20) NOT NULL,
  `tongtien` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`idd`, `madonhang`, `masanpham`, `anhsanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`, `tongtien`) VALUES
(1, 1, 22, 'https://cdn.tgdd.vn/Products/Images/42/242439/Galaxy-S22-Plus-White-600x600.jpg', 'Điện Thoại Samsung Galaxy S22+ 5G 128GB ', 19990000, 1, 19990000),
(2, 2, 19, 'https://cdn.tgdd.vn/Products/Images/44/228526/asus-rog-zephyrus-ga502iu-r7-al007t-228526-600x600.jpg', 'LapTop Asus ROG Zephyrus G15', 28890000, 2, 57780000),
(3, 2, 21, 'https://cdn.tgdd.vn/Products/Images/44/292926/lenovo-thinkpad-x1-carbon-gen-10-i7-21cb00a8vn-2-1.jpg', 'LapTop Lenovo ThinkPad X1 Carbon', 47010000, 1, 47010000),
(4, 3, 22, 'https://cdn.tgdd.vn/Products/Images/42/242439/Galaxy-S22-Plus-White-600x600.jpg', 'Điện Thoại Samsung Galaxy S22+ 5G 128GB ', 19990000, 10, 199900000),
(5, 3, 23, 'https://cdn.tgdd.vn/Products/Images/42/234621/Xiaomi-12-xam-thumb-mau-600x600.jpg', 'Điện Thoại Xiaomi 12', 15990000, 1, 15990000),
(6, 4, 20, 'https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-tim-thumb-600x600.jpg', 'Điện thoại iPhone 14 Pro Max ', 33990000, 10, 339900000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dangnhap`
--

CREATE TABLE `dangnhap` (
  `id` int(11) NOT NULL,
  `tennguoidung` varchar(20) NOT NULL,
  `sodienthoai` int(11) NOT NULL,
  `matkhau` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dangnhap`
--

INSERT INTO `dangnhap` (`id`, `tennguoidung`, `sodienthoai`, `matkhau`) VALUES
(1, 'Trần Văn Trường', 984677725, 'truong'),
(2, 'Trần Thanh Thiện ', 984677726, 'thien');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(50) NOT NULL,
  `sodienthoai` int(11) NOT NULL,
  `diachi` varchar(100) NOT NULL,
  `phuongthucthanhtoan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `diachi`, `phuongthucthanhtoan`) VALUES
(1, 'Trần Văn Trường', 984677725, 'Hà Nam', 'Tiền Mặt'),
(2, 'Nguyễn Thị Anh Thư', 984375246, 'Hà Nam', 'Chuyển Khoản'),
(3, 'Trần Thanh Thiện', 984677726, 'Hà Nội', 'Chuyển Khoản'),
(4, 'trần văn trun', 984677725, 'hà nam', 'hzzhhz');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenLoaiSP` varchar(50) NOT NULL,
  `anhSP` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenLoaiSP`, `anhSP`) VALUES
(1, 'Điện Thoại', 'https://cdn-icons-png.flaticon.com/512/2586/2586488.png'),
(2, 'LapTop', 'https://cdn-icons-png.flaticon.com/512/2248/2248656.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tenSP` varchar(50) NOT NULL,
  `giaSP` int(20) NOT NULL,
  `anhSP` varchar(200) NOT NULL,
  `moTaSP` varchar(10000) NOT NULL,
  `idSP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tenSP`, `giaSP`, `anhSP`, `moTaSP`, `idSP`) VALUES
(1, 'Điện Thoại iPhone 13 Pro Max 128GB ', 28490000, 'https://cdn.tgdd.vn/Products/Images/42/230529/iphone-13-pro-max-sierra-blue-600x600.jpg', 'iPhone 13 Pro Max 128GB nổi bật với thiết kế cụm 3 camera và cấu hình được nâng cấp. iPhone 13 mang vẻ ngoài sang trọng, tối giản, vuông vắn tương tự như iPhone 12 Pro Max. Màn hình 6.7 inch, kết hợp sử dụng tấm nền OLED, giúp hiển thị hình ảnh rõ ràng, sáng đẹp tự nhiên.\r\nSản phẩm sử dụng chip A15 Bionic cho cấu hình mạnh mẽ, cải thiện tốt khả năng lọc máy. CPU vượt trội đi kèm với RAM 6 GB, có thể đáp ứng tối đa các tác vụ và đặc biệt máy sở hữu bộ nhớ trong 128 GB cho không gian lưu trữ lớn.\r\nNgoài ra, phần camera được nâng cấp lớn nhất từ trước đến nay với con chip xử lý hình ảnh ISP giúp giảm nhiễu, tăng cường độ chi tiết vượt trội. Pin điện thoại có thời lượng sử dụng nhiều hơn iPhone 12 Pro Max 1.5 tiếng, đáp ứng tốt nhu cầu người dùng. Hơn nữa, máy còn được hỗ trợ chuẩn kháng nước và bụi IP68.\r\n', 1),
(2, 'Điện Thoại iPhone 12 128GB ', 18990000, 'https://cdn.tgdd.vn/Products/Images/42/228736/iphone-12-xanh-la-600x600.jpg', 'iPhone 12 128GB là kết hợp vẻ hoài cổ của iPhone 4 với nét hiện đại của iPhone 11 tạo nên vẻ ngoài hoàn hảo. Toàn bộ thân máy được gói gọn trong khung viền nhôm cao cấp được vát thẳng, ít bo tròn.\r\nMàn hình tràn viền rộng 6.1 inch và áp dụng tấm nền OLED với công nghệ Super Retina XDR cho khả năng hiển thị hình ảnh rõ ràng, sắc nét. Thiết kế mặt kính được trang bị lớp kính cường lực Ceramic Shield, xong áp dụng quá trình trao đổi ion kép trên mặt kính sau, hạn chế máy khỏi bị vết nứt, trầy xước.\r\niPhone 12 được tích hợp chip A14 Bionic cho phép bạn thực hiện 11 nghìn tỷ phép tính mỗi giây, tăng tới 10 lần tốc độ tính toán máy học. Và cụm camera trước TrueDepth 12 MP sở hữu sức mạnh của chip, một loạt các tính năng ưu việt, nâng cao đã xuất hiện trên iPhone 12 bao gồm: Chụp ảnh selfie và quay video time-lapse ở chế độ Night Mode, Deep Fusion, Smart HDR 3,...\r\nNgoài ra, chiếc điện thoại này còn có khả năng kháng nước chuẩn IP68, giúp bạn yên tâm hơn khi đi ngoài trời mưa hoặc những lúc đi bơi, tắm', 1),
(3, 'Điện Thoại OPPO A95', 6190000, 'https://cdn.tgdd.vn/Products/Images/42/251703/oppo-a95-4g-bac-2-600x600.jpg', 'OPPO A95 mang phong cách thiết kế OPPO Glow mỏng nhẹ, sang trọng, kết hợp với họa tiết nhám được hoàn thiện tỉ mỉ thu hút mọi ánh nhìn. Toàn thân máy đều được làm cong 2.5D mượt mà và có độ mỏng chỉ 7.95 mm.\r\nMàn hình lớn 6.43 inch có độ phân giải Full HD+ cùng công nghệ AMOLED cao cấp, cho không gian hiển hình ảnh chân thực, sống động, để bạn thỏa sức tận hưởng những nội dung yêu thích như xem phim, lướt web, vào mạng xã hội,...\r\nSản phẩm có bộ nhớ trong lên đến 128 GB mang đến không gian lưu trữ lớn, đồng thời nâng cao hiệu suất và tăng tốc độ cài đặt dữ liệu. Và bộ 3 camera 48 MP hỗ trợ công nghệ AI giúp bạn dễ dàng chụp được những bức hình sắc nét và đẹp đến bất ngờ. Đặc biệt, máy sở hữu dung lượng pin lớn, kèm sạc nhanh cho bạn sử dụng thoải mái trong suốt ngày dài.', 1),
(4, 'Máy Tính xách tay HP 348 G7 i3', 22980000, 'https://cdn.tgdd.vn/Products/Images/44/225549/hp-348-g7-i3-1a0z1pa-1-225549-600x600.jpg', 'HP 348 G7 i3 thuộc sản phẩm tầng trung với cấu hình ổn định. Mặc dù sở hữu bộ nhớ RAM 4GB và Intel Core i3 thế hệ 8 nhưng sản phẩm này vẫn đảm bảo các chức năng. Cụ thể như nhập liệu, soạn thảo, học online, làm báo cáo hoặc xem phim trên các trang mạng.\r\nKhi người dùng có nhu cầu thực hiện thêm nhiều tính năng hơn với tốc độ nhanh hơn thì hoàn toàn có thể nâng cấp RAM lên tới 32GB. Bên cạnh đó, việc khởi động máy và tốc độ phản hồi tác vụ còn rất nhanh với SSD 256GB.\"\r\n\"Nếu bạn có nhu cầu phải di chuyển thường xuyên thì HP 348 G7 i3 cũng hoàn toàn phù hợp. Bởi vì trọng lượng của nó chỉ khoảng 1,5kg với màn hình 14 inch gọn nhẹ. Màn hình của thiết bị là sự kết hợp giữa công nghệ Anti-Glare với tấm nền IPS. Từ đó giúp màu sắc hình ảnh rõ nét và tươi sáng hơn.\r\nMột điều được xem như vừa là điểm cộng vừa là nhược điểm của sản phẩm là tính năng cảm biến bằng vân tay. Điều này sẽ giúp chủ nhân bảo mật được thiết bị một cách tối đa. Tuy nhiên, khi tay bạn bị trơn hoặc dính nước thì cảm ứng sẽ gặp khó khăn.\r\n', 2),
(5, 'Điện Thoại Xiaomi 11T 5G 128GB ', 9990000, 'https://cdn.tgdd.vn/Products/Images/42/249945/oppo-a16k-thumb1-600x600-1-600x600.jpg', 'Xiaomi 11T 5G 128GB đã được trang bị hàng loạt tính năng đỉnh cao như tần số quét màn hình 120 Hz, vi xử lý Dimensity 1200,... chiếc smartphone này nhất định sẽ mang đến sự hài lòng dành cho bạn. Màn hình AMOLED cao cấp 6.67 inch có độ sáng tối đa 1000 nits cho góc nhìn rộng rãi, đi kèm chất lượng hình ảnh hiển thị tốt.\r\nHệ thống 3 camera sau, kết hợp với phần cứng hiện đại được tích hợp bên trong, cho khả năng chụp ảnh cực kì ấn tượng và sẽ không khiến bạn thất vọng. Cấu hình có chip Dimensity 1200 8 nhân có tốc độ xử lý nhanh chóng, tiết kiệm điện năng tiêu thụ hiệu quả.\r\nSản phẩm được trang bị viên pin 5000 mAh đáp ứng tốt nhu cầu sử dụng của dùng trong thời gian dài, đặc biệt máy còn tích hợp thêm công nghệ sạc pin nhanh 67 W tiện lợi\r\n', 1),
(6, 'Máy Tính Macbook Pro 13 inch (Apple M1)', 35980000, 'https://tuvanmuasam.com/wp-content/uploads/2020/09/Macbook-Pro-13-inch-Apple-M1-300x169.jpg', 'Apple Macbook Pro M1 sở hữu thiết kế sang trọng từ kim loại nguyên khối được kế thừa từ các thế hệ trước nhưng bên trong là một cấu hình cực kỳ đáng gờm. Với chip M1 lần đầu tiên xuất hiện trên MacBook Pro, hiệu năng CPU của máy tăng đến 2.8 lần, hiệu năng GPU tăng 5 lần.\r\nVẫn là thiết kế kim loại nguyên khối sang trọng thường thấy ở các thế hệ trước, phiên bản MacBook Pro lần này mỏng nhẹ chỉ 15.6 mm, trọng lượng 1.4 kg có thể tự tin đồng hành cùng bạn đến bất cứ đâu.\r\nApple Macbook Pro M1 mang trong mình thiết kế độc đáo, di động hiệu năng mạnh mẽ, xử lí nhanh gọn mọi tác vụ, đây chắc chắn là chiếc máy tính xách tay sang trọng và đẳng cấp đáng sở hữu.\r\n', 2),
(7, 'Máy Tính xách tay Dell XPS 13', 46990000, 'https://cdn.tgdd.vn/Products/Images/44/292594/dell-xps-13-9320-i5-70295789-1.jpg', 'Với ưu điểm dễ thấy nhất là có thiết kế mỏng, nhẹ và cấu hình ổn thì chiếc Dell XPS 13 quả là sự lựa chọn không tồi. Sản phẩm này vô cùng phù hợp với những người thường xuyên phải di chuyển nhiều như doanh nhân, nhân viên sale, bất động sản…\r\nKích thước màn hình của sản phẩm chỉ 13.3 inch, thời lượng pin khỏe, hiệu năng tốt. Với thiết kế này, Dell đã thay đổi vị trí đặt webcam tại trên màn hình thay vì đặt tại bàn phím ở thế hệ trước đó. Ngoài ra, hình ảnh vô cùng sắc nét, âm thanh sống động sẽ giúp bạn có thêm nhiều trải nghiệm thú vị.\"\r\n\"Ở phiên bản model 4K thì thương hiệu này đã nâng cấp lên Core i7, SSD 256GB, RAM 8GB. Điều này cho phép người dùng có thể thực hiện nhiều chức năng cùng lúc như truy cập web, thiết kế hoặc chơi game… khi sử dụng. Đồng thời đảm bảo bộ nhớ trong lưu trữ được nhiều thông tin hơn.\r\n', 2),
(8, 'Điện Thoại OPPO A16K ', 3090000, 'https://cdn.tgdd.vn/Products/Images/42/249945/oppo-a16k-thumb1-600x600-1-600x600.jpg', 'OPPO A16K thiết kế cong 3D, khung viền được tạo dáng từ nhựa bền nhẹ và độ dày chỉ 7.85 mm tạo nên 1 chiếc điện thoại thông minh thanh mảnh. Màn hình 6.52 inch sử dụng công nghệ màn hình IPS LCD tạo nên khung hình rộng với màu sắc đa dạng và hình ảnh hiển thị tươi tắn, sống động.\r\nSản phẩm sử dụng chip đồ họa xử lý MediaTek Helio G35, giúp cải thiện hình ảnh khi chơi game, cũng như giảm tình trạng giật lag. Bộ nhớ trong 32 GB khá khiêm tốn nhưng bù lại sản phẩm hỗ trợ thẻ dùng MicroSD có thể mở rộng dung lượng lên đến 1 TB.\r\n OPPO A16K có thiết kế camera sau đơn giản, bao gồm 1 máy ảnh độ phân giải 13 MP và 1 đèn flash LED hỗ trợ bạn chụp ảnh tốt hơn trong điều kiện thiếu sáng. Dung lượng pin Li-Po lớn 4230 mAh cho bạn thời gian sử dụng trong cả ngày dài.\r\n', 1),
(9, 'Điện Thoại Vivo V23e ', 7490000, 'https://cdn.tgdd.vn/Products/Images/42/245607/Vivo-V23e-1-2-600x600.jpg\r\n', 'Vivo V23e lấy cảm hứng từ những phiên bản tiền nhiệm thuộc Vivo V Series để mang đến một thiết kế hoàn hảo hơn, vuông vắn cùng độ mỏng thân máy được tối ưu hơn. Màn hình AMOLED sở hữu kích thước 6.44 inch cho không gian hiển thị lớn và khả năng tái tạo hình ảnh chân thực.\r\nĐiện thoại chạy bằng con chip gaming MediaTek Helio G96 có thể chơi tốt những tựa game như Liên Quân Mobile, PUBG,... Và với bộ nhớ trong 128 GB có không gian lưu trữ lớn, giúp bạn lưu trữ được nhiều hình ảnh hay những bộ phim yêu thích.\r\nĐặc biệt ở chiếc điện thoại này là được trang bị nhiều tính năng chụp ảnh bao gồm: Tự động lấy nét, chụp ảnh xóa phông, chế độ chụp đêm,... Kết hợp với thời lượng pin sử dụng lâu dài khoảng từ 6 tiếng.\r\n', 1),
(10, 'Máy Tính Dell Inspiron 7306 2-in-1 2021', 24350000, 'https://cdn.tgdd.vn/Products/Images/44/284308/dell…on-16-5620-i5-n6i5003w1-060722-063545-600x600.jpg', 'Dell Inspiron 7306 2-in-1 là một trong những laptop 2-in-1 tốt nhất mà bạn có thể mua với mức giá không quá cao. Điều đó không có gì đáng ngạc nhiên. CPU Tiger Lake thế hệ thứ 11 của Intel với Đồ họa Iris Xe mang lại hiệu suất ổn định và tăng cường đồ họa so với các chip trước đó. Đây là một sự lựa chọn tuyệt vời cho những ai đang tìm kiếm sự linh hoạt cùng với hiệu suất và độ bền và quan trọng là mức giá rất tốt.\r\n Dell Inspiron 7306 được trang bị cấu hình mạnh mẽ so với các sản phẩm cùng phân khúc. Cụ thể, laptop với con chip Intel Core i5-1135G7 cùng con chip Intel Iris Xe Graphics, ổ cứng thể rắn M.2 PCIe NVMe 512GB cùng dung lượng RAM 8GB.Tất cả mang lại cho Inspiron 7306 một hiệu năng mượt mà, tốc độ truyền tải cao cùng khả năng đa nhiệm tốt.\r\n', 2),
(11, 'Máy Tính Asus ZenBook 13 UX325EA', 2990000, 'https://cdn.tgdd.vn/Products/Images/44/201243/asus-zenbook-13-ux333fa-a4016t-1-600x600.jpg ', 'Asus ZenBook 13 UX325EA nằm giữa ranh giới doanh nhân và cao cấp. Bạn nhận được một hệ thống làm rung chuyển vẻ ngoài đẹp đẽ của các laptop hàng đầu của Asus với một số tính năng bảo mật và độ bền của ExpertBook, dòng kinh doanh mới của Asus. Thêm vào đó, bạn nhận được thêm sức mạnh của chip Thế hệ thứ 11 mới của Intel và tất cả các tính năng mà sáng kiến &ZeroWidthSpace;&ZeroWidthSpace;Evo của Intel mang lại, bao gồm cả thời lượng pin hơn 13 giờ. Và bạn thậm chí có thể chơi game (chỉ cần không ở cài đặt cao nhất).\r\nLaptop này đã vượt qua các đối thủ cạnh tranh một cách dễ dàng trong các bài kiểm tra điểm chuẩn khắt khe của chúng tôi, khiến nó trở thành một lựa chọn tuyệt vời cho công việc và giải trí. Thêm vào đó, đây là một trong số ít laptop có webcam tích hợp tốt nhờ mô-đun máy ảnh mới và thuật toán máy ảnh của Asus.\r\n', 2),
(12, 'Xiaomi Redmi 10 (4GB/64GB) ', 3990000, 'https://cdn.tgdd.vn/Products/Images/42/249080/redmi-10-blue-600x600.jpg', 'Xiaomi Redmi 10 (4GB/64GB) là chiếc điện thoại thông minh lấy lòng người dùng bởi thiết kế sang trọng, độc đáo, sở hữu cụm camera sau có độ phân giải cao và việc sử dụng đến 4 camera, Redmi 10 cho phép bạn có thể chụp những bức ảnh từ khung hình cực rộng đến lấy nét ở cự ly siêu gần.\r\nMàn hình có kích thước lớn 6.5 inch và độ phân giải Full HD+ mang lại không gian lớn để bạn thỏa sức trải nghiệm với chất lượng hình ảnh hiển thị sắc nét. Đặc biệt, cụm loa kép stereo phát ra âm thanh lớn, to rõ và sống động.\r\nChiếc điện thoại này có hiệu năng làm việc, giải trí ổn định nhờ sở hữu sức mạnh của bộ vi xử lý Helio G88 từ MediaTek, đây là phiên bản cải tiến của Helio G85. Ngoài ra, máy còn đi kèm bộ nhớ trong 64 GB tạo không gian lưu trữ lớn.\r\n', 1),
(13, 'Điện Thoại iPhone XR 64GB ', 17890000, 'https://cdn.tgdd.vn/Products/Images/42/190325/iphone-xr-hopmoi-den-600x600-2-600x600.jpg', 'iPhone XR 64GB sở hữu màn hình rộng OLED cao cấp 6.1 inch với khả năng hiển thị hình ảnh có độ sắc nét cao và vô cùng ấn tượng. Điện thoại trang bị chip mới của Apple là A12 Bionic có ưu điểm xử lý nhanh chóng và mượt mà mọi tác vụ kể các các tựa game có đồ họa khủng hiện nay.\r\nSản phẩm chỉ sở hữu 1 camera sau nhưng có độ phân giải 12 MP, cùng công nghệ chụp ảnh Smart HDR sẽ không làm bạn thất vọng với khả năng chụp ảnh sống ảo của máy. Hệ thống âm thanh chất lượng sẽ mang đến cho bạn trải nghiệm sống động và chân thật nhất.\r\nCuối cùng là hệ thống bảo mật an toàn với Face ID bạn chỉ cần nhìn vào máy, hệ thống sẽ tự động quét khuôn mặt 3D, giúp cho việc mở khóa đơn giản và nhanh chóng hơn.\r\n', 1),
(14, 'Điện Thoại Realme 7 Pro ', 13450000, 'https://cdn.tgdd.vn/Products/Images/42/227689/realme-7-pro-bac-600x600.jpg', 'Realme 7 Pro lấy cảm hứng từ thiết kế không gian gương trong tự nhiên, kết hợp với sự chia cắt táo bạo mang lại vẻ đẹp độc đáo, sang trọng. Màn hình tràn viền 6.4 inch được trang bị tấm nền Super AMOLED mang lại chất lượng hình ảnh hiển thị sắc nét và góc nhìn rộng hơn.\r\nCụm 4 camera được trang bị nổi bật ở mặt lưng, hỗ trợ các tính năng chụp nâng cao như camera xóa phông, Panorama, HDR,... hiệu quả. Hiệu suất làm việc nhanh chóng đến từ con chip Snapdragon 720G, đi cùng dung lượng RAM 8 GB giúp mọi thao tác trên thiết bị diễn ra ổn định, mượt mà.\r\nBên cạnh đó, điện thoại còn được trang bị pin khủng với dung lượng 4500 mAh cho bạn trải nghiệm làm việc, giải trí thoải mái trong thời gian dài.', 1),
(15, 'Điện Thoại iPhone 12 Pro Max 128GB ', 27790000, 'https://cdn.tgdd.vn/Products/Images/42/213033/iphone-12-pro-max-xanh-duong-new-600x600-600x600.jpg', 'iPhone 12 Pro Max 128GB ấn tượng với thiết kế phong cách đầy lịch lãm và toát ra vẻ sang trọng ngay từ những ánh nhìn đầu tiên. Khung viền làm từ thép không gỉ cao cấp, mặt kính và mặt lưng đều được phủ lớp kính cường lực Ceramic Shield, hạn chế xước sát trước những va đập thông thường hiệu quả.\r\nKích thước màn hình 6.7 inch, sử dụng tấm nền của OLED có độ sáng tối đa 1200 nits (HDR) giúp hiển thị hình ảnh sắc nét, chân thật nhất. Điện thoại trang bị chip A14 đem đến tốc độ xử lý nhanh hơn 50% so với những vi xử lý khác trên thị trường.\r\nThêm nữa, chiếc iPhone được trang bị hệ thống 3 camera có cùng một độ phân giải là 12 MP, cũng như sở hữu một số tính năng chụp ảnh nổi bật như Smart HDR 3, cảm biến LiDAR,... hỗ trợ bạn chinh phục mọi không gian sống ảo.\r\n', 1),
(16, 'Điện Thoại Samsung Galaxy Z Fold 5G 256GB', 31990000, 'https://cdn.tgdd.vn/Products/Images/42/226935/samsung-galaxy-z-fold-3-silver-1-600x600.jpg', 'Có thể thấy mẫu smartphone Galaxy Z Fold3 lần này vẫn giữ nguyên ngoại hình cùng cơ chế màn hình gập mở dạng quyển sách như của tiền nhiệm, biến chiếc smartphone thành một chiếc máy tính bảng mini một cách dễ dàng và ngược lại.\r\nVới cấu tạo chắc chắn của khung viền hợp kim nhôm sẽ giúp bạn yên tâm tận hưởng các hoạt động yêu thích một cách trọn vẹn nhất.\r\nNgoài ra, Galaxy Z Fold3 5G cũng là thiết bị màn hình gập đầu tiên trên thế giới sở hữu công nghệ kháng nước chuẩn IPX8 ở mức cao nhất trong thang đo từ 1 - 8 giúp chúng ta yên tâm sử dụng hằng ngày.\r\nVới cảm biến vân tay ở cạnh bên, việc mở khóa màn hình trên Z Fold3 5G giờ đây đã được thực hiện một cách nhanh chóng và an toàn chỉ trong một nốt nhạc.\r\n', 1),
(17, 'Điện Thoại Samsung Galaxy S22 Ultra 5G 128GB ', 25990000, 'https://cdn.tgdd.vn/Products/Images/42/235838/Galaxy-S22-Ultra-Burgundy-600x600.jpg', 'Samsung Galaxy S22 Ultra 5G 128GB sở hữu thiết kế sang trọng với mặt lưng nhám chống trượt. Đặc biệt, máy được tích hợp bút S - Pen vô cùng tiện lợi.\r\nĐiện thoại sở hữu màn hình lớn 6.8 inch đạt độ phân giải 2K +, cùng với đó là tần số quét 120 Hz cho ra những khung hình sắc nét, thao tác mượt mà nhất.\r\nMáy ảnh của Samsung Galaxy S22 Ultra có khả năng chụp đêm vô cùng ấn tượng. Khả năng zoom camera tới 100x cùng hỗ trợ quay phim 8K là những điểm nổi bật khác.\r\n', 1),
(18, 'LapTop HP Envy x360 13', 29290000, 'https://cdn.tgdd.vn/Products/Images/44/292595/hp-envy-x360-13-bf0095tu-i5-76b15pa-a-13.jpg', 'Envy x360 13 đang làm khuynh đảo thị trường phổ thông. Thay vì xem xét những tính năng nào cần phải loại bỏ để có mức giá dưới 1.000 USD, HP đã nghĩ về những khía cạnh nào đó của các mẫu Spectre hàng đầu có thể được đưa lên Envy. Do đó, Envy x360 13 chia sẻ phần lớn DNA giống với Spectre. Nó có màn hình cảm ứng 13 inch tuyệt vời và bàn phím thoải mái. HP đã lựa chọn một cách thông minh các CPU dòng 4000 mới của AMD, mang lại hiệu suất chưa từng thấy với mức giá dưới 1.000 USD. Bạn thậm chí có được một sự lựa chọn tốt về các cổng.\r\nEnvy x360 13 là chiếc laptop dưới 1.000 đô la tốt nhất trên thị trường. Chúng tôi vẫn đang cố gắng tìm ra cách HP giữ mức giá thấp như vậy. Nó có một màn hình sáng, sống động với khung nhôm sang trọng và hiệu suất nhanh. Cho dù bạn là sinh viên đang tìm kiếm laptop tốt nhất hay chỉ muốn một laptop 13 inch di động, không có lựa chọn nào tốt hơn Envy x360 13.\r\n', 2),
(19, 'LapTop Asus ROG Zephyrus G15', 28890000, 'https://cdn.tgdd.vn/Products/Images/44/228526/asus-rog-zephyrus-ga502iu-r7-al007t-228526-600x600.jpg', 'Dell XPS 15 tiếp tục đứng đầu ngai vàng bằng cách thực hiện các chỉnh sửa nhỏ cho đến những thay đổi lớn. Công ty đã cố gắng làm cho laptop nhỏ hơn 5,5% so với mô hình trước đó trong khi làm cho các phím và bàn di chuột lớn hơn đáng kể. Và mặc dù điều đó có vẻ nhỏ, nhưng khung bezel InfinityEdge bốn viền đã biến nó thành một trong những màn hình có độ phân giải cao nhất trên thị trường.\r\nVà trong khi những thay đổi về thể chất là rất lớn, nội thất của chiếc laptop được xếp hạng hàng đầu này cũng thú vị không kém. Được trang bị bộ vi xử lý thế hệ thứ 10 và GPU Nvidia GeForce. Nó cũng có ổ SSD tốc độ cao và âm thanh tuyệt vời nhờ bộ loa quad mạnh mẽ. Thêm vào đó, nó kéo dài hơn 8 giờ trong bài kiểm tra pin của chúng tôi, điều này tốt cho một laptop 4K.\r\nNếu bạn muốn một cỗ máy mạnh mẽ với màn hình tuyệt đẹp, âm thanh xuất sắc và một loạt các tính năng cao cấp khác, Dell XPS 15 là lựa chọn tuyệt vời.\r\n', 2),
(20, 'Điện thoại iPhone 14 Pro Max ', 33990000, 'https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-tim-thumb-600x600.jpg', 'Điện thoại iPhone 14 Pro Max 128GB là chiếc smartphone hỗ trợ 5G sở hữu màn hình OLED rộng 6.7 inch. Độ sang trọng được thể hiện qua chất liệu thép không gỉ sáng bóng.\r\nMặt trên điện thoại được phủ Ceramic Shield, cho khả năng chống va đập tốt hơn bình thường. Tính năng True Tone và độ sáng 1200 nits giúp máy hiển thị rõ ngay cả ngoài trời nắng.\r\nCamera được trang bị loạt công nghệ như zoom kỹ thuật số, quay video HDR, chụp ảnh ProRAW hay tính năng Deep Fusion.\r\n', 1),
(21, 'LapTop Lenovo ThinkPad X1 Carbon', 47010000, 'https://cdn.tgdd.vn/Products/Images/44/292926/lenovo-thinkpad-x1-carbon-gen-10-i7-21cb00a8vn-2-1.jpg', 'Lenovo đã làm cho laptop doanh nhân yêu thích của chúng tôi trở nên tốt hơn bằng cách cải thiện loa của nó, tạo cho nó một kết cấu sợi carbon mảnh mai và thêm một số tính năng bảo mật hữu ích, bao gồm màn trập webcam và camera IR. Bạn thậm chí còn nhận được micrô trường xa trong trường hợp bạn muốn sử dụng trợ lý kỹ thuật số.\r\nTrên hết, bạn sẽ có được hiệu suất mạnh mẽ và ổ SSD nhanh. Điều đó có nghĩa là nếu bạn đang làm việc trên các bảng tính lớn với nhiều phép tính, X1 Carbon sẽ xử lý chúng một cách linh hoạt. Bạn cũng có thể mong đợi tuổi thọ pin dài (trên mô hình 1080p) và hai tùy chọn hiển thị tuyệt đẹp, 1080p và 4K, tất cả đều nằm trong một khung máy siêu nhẹ.\r\nNhưng chính những tính năng ThinkPad X1 cổ điển đó — khung máy bền bỉ (đã được thử nghiệm trong quân đội), bàn phím tốt nhất trong phân khúc và tính thẩm mỹ màu đen / đỏ thời trang — đã mang X1 Carbon đến sự tuyệt vời.', 2),
(22, 'Điện Thoại Samsung Galaxy S22+ 5G 128GB ', 19990000, 'https://cdn.tgdd.vn/Products/Images/42/242439/Galaxy-S22-Plus-White-600x600.jpg', 'Samsung Galaxy S22+ 5G 128GB là mẫu điện thoại có khung viền được chế tạo từ hợp kim nhôm, đi kèm kính cường lực Gorilla Glass Victus+.\r\nMáy sở hữu thiết kế màn hình tràn viền rộng 6.6 inch, đạt độ phân giải Full HD+. Tần số quét 120 Hz đảm bảo sự mượt mà cho mọi tác vụ.\r\nĐặc biệt, điện thoại được trang bị chip Snapdragon 8 Gen 1, có khả năng xử lý các tác vụ game nặng dễ dàng, đi kèm các chế độ chụp ảnh ưu việt.', 1),
(23, 'Điện Thoại Xiaomi 12', 15990000, 'https://cdn.tgdd.vn/Products/Images/42/234621/Xiaomi-12-xam-thumb-mau-600x600.jpg', 'Xiaomi 12 là mẫu flagship có thiết kế gọn gàng, tinh tế đi kèm hiệu năng mạnh mẽ và khả năng chụp ảnh siêu việt.\r\nMáy sở hữu màn hình AMOLED tràn viền kích thước 6.28 inch, kết hợp cùng công nghệ True Color, cũng như đạt độ bao phủ màu DCI-P3.\r\nHiệu năng khủng của điện thoại đến từ chính con chip Snapdragon 8 Gen 1, cùng với đó là 8 GB RAM và 256 GB bộ nhớ trong.\r\n', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`idd`);

--
-- Chỉ mục cho bảng `dangnhap`
--
ALTER TABLE `dangnhap`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `idd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `dangnhap`
--
ALTER TABLE `dangnhap`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
