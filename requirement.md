---
layout: page
title: Uthus Interview Requirement
subtitle: AleBeer requirement
---

**Uthus Interview Requirement**
==================

## **AleBeer** là ứng dụng cho phép user xem danh sách, ghi chú về các loại bia.


### Ứng dụng gồm 2 tab:

- Tab “Beer”

``` Show danh sách các loại bia, data lấy ở:
https://apps.uthus.vn/api/api-testing/sample-data?page=1&limit=20
Mỗi item gồm: ivImage, tvName, Price, etNote, btnSave. Như ảnh:
User có thể ghi chú cảm nhận về từng loại bia, khi nhấn nút save lưu item xuống local (dùng
room database), file ảnh lưu vào device. Khi đang tiến hành lưu update text của btnSave thành
Saving:
Khi lưu thành công, item đã có trong database, Ẩn btnSave, không cho user edit Note:
Tương tự khi load data từ API về, nếu item đã có trong database, show như trên (Ẩn btnSave,
không cho user edit Note)
```
- Tab “Favorite”:

```
Show danh sách bia đã lưu, item như sau:
btnDelete: xoá row hiện tại khỏi database
btnUpdate: Update theo note user nhập.
```

### Bonus:
- Với tab “Beer” mỗi item show countdown đếm ngược từng giây đến ngày sale off (dùng
  field sale_off_time)
* Nếu chưa tới time sale off show: Sale Off In: HH:mm:ss dd-MM
* Nếu quá hoặc bằng time sale off show: Sale Off
- Loadmore, pull to refresh

### BẮT BUỘC:
#### RecyclerView Adapter implement theo libs: https://github.com/DevAhamed/MultiViewAdapter

### CÁCH SUBMIT:
- Public source code lên github, dẫn link vào group telegram.
- Đính kèm file APK lên group telegram.
- Tổng thời gian đã spend cho project.
  Ví dụ:
- https://github.com/my-name/uthus-interview.
- File release apk.
- Time spend: 20h.