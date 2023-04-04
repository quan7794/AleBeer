---
layout: page
title: AleBeer
subtitle: AleBeer requirement
---

**Requirement**
==================

## **AleBeer** là ứng dụng cho phép user xem danh sách, ghi chú về các loại bia.


### Ứng dụng gồm 2 tab:

#### Tab “Beer”

##### Show danh sách các loại bia, data lấy ở: [Api End-point](https://apps.uthus.vn/api/api-testing/sample-data?page=1&limit=20)
- Mỗi item gồm: ivImage, tvName, Price, etNote, btnSave. Như ảnh:

![1](/AleBeer/assets/img/1.png)

- User có thể ghi chú cảm nhận về từng loại bia, khi nhấn nút save lưu item xuống local (dùng room database), file ảnh lưu vào device.
- Khi đang tiến hành lưu update text của btnSave thành Saving:

![2](/AleBeer/assets/img/2.png)

- Khi lưu thành công, item đã có trong database, Ẩn btnSave, không cho user edit Note:

![3](/AleBeer/assets/img/3.png)

- Tương tự khi load data từ API về, nếu item đã có trong database, show như trên (Ẩn btnSave, không cho user edit Note)


#### Tab “Favorite”:


##### Show danh sách bia đã lưu, item như sau:

![4](/AleBeer/assets/img/4.png)

- btnDelete: xoá row hiện tại khỏi database
- btnUpdate: Update theo note user nhập.

### Bonus:
- Với tab “Beer” mỗi item show countdown đếm ngược từng giây đến ngày sale off (dùng field sale_off_time)
- Nếu chưa tới time sale off show: Sale Off In: HH:mm:ss dd-MM
- Nếu quá hoặc bằng time sale off show: Sale Off
- Loadmore, pull to refresh

### BẮT BUỘC:
#### RecyclerView Adapter implement theo libs: [MultiViewAdapter Lib](https://github.com/DevAhamed/MultiViewAdapter)

### CÁCH SUBMIT:
- Public source code lên github, dẫn link vào group telegram.
- Đính kèm file APK lên group telegram.
- Tổng thời gian đã spend cho project.
  Ví dụ:
- https://github.com/my-name/uthus-interview.
- File release apk.
- Time spend: 20h.