---
layout: page
title: Kết quả & Tính năng
subtitle: AleBeer app.
---

#### Tổng thời gian: 23 giờ

#### Tính năng


1. Xây dựng base project (1 giờ):

```kotlin
- Khởi tạo base project với các module Retrofit, RoomDB
- Sử dụng cấu trúc phổ biến với Repository, MVVM, DataBinding.
- Khởi tạo Injection sử dụng hilt:
    + Navigation
    + Room
    + Retrofit
    + Repository
    + ViewModel
- Giao diện: Sử dụng material design 3
- Hỗ trợ dark và light mode (tự động thay đổi theo cài đặt hệ thống android).
```

2. Thẻ Beer, hiển thị danh sách bia với các thông tin như yêu cầu đề ra (12 giờ)

![](/AleBeer/assets/img/result-beer-screen.png)

```kotlin

- Sử dụng thư viện MultiViewAdapter.
- Thiết kế layout cho thẻ Beer.
- Hiện thực logic đổ data cho beer list sử dụng retrofit.
- Đổ dữ liệu bất đồng bộ sử dụng coroutine (từng item một) để tránh hiện tượng giật lag khi thay đổi data 1 lúc quá nhiều.
- Lưu và khôi phục beer list state khi sự kiện onConfigurationChanged bị trigger (tự động scroll tới item đang hiện, đổ data vào beer list).
- Implement text watcher logic cho phần note
    - Lưu các thông tin đã note (chưa nhấn lưu) vào 1 map để tránh việc mất thông tin đã nhập khi scroll item ra khỏi phạm vi hiển thị.
    - Tự động khôi phục thông tin đã nhập khi trở lại item đó.
    - Tự động huỷ sự kiện lắng nghe text change khi scroll item ra khỏi màn hình và tự khôi phục lại khi scroll trở lại.
- Quản lý trạng thái nút Save, Note edit field.
    - Tạo thêm 1 attribute là "note" trong object Beer. Sử dụng object này để quản lý sự kiện cho nút Save.
    - Nếu noted data empty -> Cho phép hiện nút Save, cho phép sửa note edit field.
    - Nếu noted data != empty -> Dữ liệu đã được lưu vào RoomDB, không hiện nút Save và disable note edit field.
- Hình ảnh mỗi item:
    - Sử dụng view holder cho mỗi hình ảnh.
    - Load hình ảnh bằng Glide.
- Quản lý thông tin Name, Price, Sale off time, count down:
    - Name: Hiển thị tối đa 2 dòng, nếu số lượng từ vượt quá, sẽ dùng thuộc tính ellipside (..) để ẩn text dư đi.
    - Price: Hiển thị 1 dòng.
    - Sale off time: Chuyển đổi timestamp thành thời gian đã được định dạng và hiển thị.
    - Count down: + Sử dụng CountDownTimer có sẵn trong android để quản lý thời gian còn lại theo định dạng: "dd - hh:mm:ss to go".
    - Tự động huỷ việc đếm count down và khôi phục đếm khi user scroll ra khỏi phạm vi màn hình và quay trở lại nhằm tiết kiệm bộ nhớ.
    - Tự động cập nhật thông tin beer khi user xoá Beer đã lưu ở tab Favorite.
- Chức năng kéo để tải thêm:
    - Sử dụng InfiniteLoadingHelper trong thư viện MultiViewAdapter để handle cho tính năng này (tính năng này của thư viện không hoạt động ổn định trên tất cả các dòng
      device khác nhau, có máy lỗi bị cut-off bottom layout, nếu list k có item thì footer loading sẽ k biến mất,..)
    - Khi kéo tới dưới cùng, tiến hành kiểm tra còn có thể get thêm data không, nếu có gọi tới api cho trang tiếp theo để get data và đổ về beer list thông qua đối tượng
      BeerSection (Class BeerFragment).
    - Nếu hết dữ liệu để tải: Hiển thị Toast: "No more data."
- Tự động cập nhật lại Beer List khi các item trong thẻ Favorite bị sửa, xoá.
```

#### Thẻ Favorite ( 5 giờ )

![](/AleBeer/assets/img/result-favorite-screen.png)

```kotlin
- Hiển thị danh sách các sản phẩm Beer đã lưu.
  - Hiển thị text "Nothing here" nếu chưa lưu bất kì sản phẩm nào.
  - Các thuộc tính thông tin và note text watcher được hiện thực giống với thẻ Beer.
- Tự động lắng nghe khi user thêm bất kì sản phẩm nào vào DB để cập nhật giao diện.
- Update & Delete:
  - Chỉ cập nhật dữ liệu khi nhấn nút Update nếu có thay đổi ở field Note.
  - Xoá item ra khỏi favorite database (RoomDB) và refresh lại giao diện sử dụng DiffUtil (MultiViewAdapter tích hợp sẵn).
- Sửa lỗi item cuối danh sách bị cut-off trên android 12 trở lên.
```

#### Khác (5 giờ )

```kotlin
- Sửa các lỗi liên quan khi sử dụng thư viện MultiViewAdapter.
- Tối ưu trải nghiệm trên recyclerview với implement tự động enable/disable listener/watcher
- Apply dark mode
- Website.

```
