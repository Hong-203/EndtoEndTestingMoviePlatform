# 🎬 Selenium Automation Framework

Dự án kiểm thử tự động (Automation Testing) cho hệ thống đặt vé xem phim CyberSoft. Framework được xây dựng dựa trên tiêu chuẩn chuyên nghiệp, dễ bảo trì và mở rộng.

---

## 🛠 Tech Stack & Kiến trúc

- **Ngôn ngữ:** Java
- **Build Tool:** Gradle
- **Framework Test:** TestNG
- **Thư viện chính:** Selenium WebDriver
- **Design Pattern:** Page Object Model (POM)
- **Reporting:** TestNG Report & Screenshot capturing on failure

---
## 📊 Google Sheet Link
- **Link:** https://vnshort.com/6tKa

## 📊 Automation Coverage

Framework đã bao phủ các luồng nghiệp vụ quan trọng (Critical Path) để đảm bảo hệ thống vận hành ổn định:

| STT | Test Case                 | Phân tích & Kỹ thuật xử lý                                                                                                                                |
| :-- | :------------------------ | :-------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1   | **Register Successfully** | Tự động tạo dữ liệu ngẫu nhiên (UUID) để tránh trùng lặp tài khoản khi chạy test nhiều lần.                                                               |
| 2   | **Login Successfully**    | Kiểm tra luồng xác thực và trạng thái hiển thị của User Profile sau khi đăng nhập.                                                                        |
| 3   | **View Cinema Clusters**  | Xử lý **In-page Navigation**. Sử dụng `JavascriptExecutor` để cuộn trang đến đúng Session mục tiêu.                                                       |
| 4   | **View Movie Detail**     | Kiểm tra tính đúng đắn của URL và nội dung tiêu đề phim bằng **Explicit Wait**.                                                                           |
| 5   | **Booking Ticket**        | **Luồng End-to-End phức tạp:** Chọn nhiều ghế, xác thực trạng thái đổi màu ghế qua thuộc tính `style`, và tương tác với các Popup xác nhận (SweetAlert2). |

---

## 💡 Các giải pháp kỹ thuật nổi bật

### 1. Xử lý Dynamic Elements (Class động)

Website sử dụng Material UI với các class sinh tự động (ví dụ: `jss1610`). Framework xử lý bằng cách:

- Ưu tiên định vị bằng **Relative XPath** kết hợp hàm `text()` và `contains()`.
- Ví dụ: `//button[span[text()='34']]` để tìm ghế chính xác bất kể class thay đổi.

### 2. Đồng bộ hóa (Synchronization)

- Không sử dụng "Hard Sleep" (`Thread.sleep`).
- Sử dụng **WebDriverWait** để đợi các điều kiện cụ thể: `elementToBeClickable`, `visibilityOfElementLocated`, `attributeContains`.

---

🚀 Hướng dẫn chạy Test
Clone dự án về máy.

Mở dự án bằng IntelliJ IDEA.

Chạy các kịch bản test thông qua file testng.xml hoặc terminal:

Bash

./gradlew clean test
Kiểm tra báo cáo tại: build/reports/tests/test/index.html
