# Laptop Shop

Ứng dụng bán laptop xây dựng bằng Spring Boot, Spring MVC/JSP, Spring Data JPA,
Spring Security và MySQL.

## Yêu cầu môi trường

- Java 17 trở lên
- Maven 3.8 trở lên (hoặc sử dụng `mvnw.cmd` có sẵn)
- MySQL 8 trở lên
- Docker Desktop nếu chạy bằng Docker Compose

## Cách chạy bằng Docker Compose

1. Mở Docker Desktop.
2. Tại thư mục gốc của dự án, chạy:

```powershell
docker compose up --build
```

Docker Compose sẽ tạo:

- Database MySQL `huy` tại cổng `3307`.
- Ứng dụng tại cổng `8080`.

Mở trình duyệt tại: <http://localhost:8080>

Để dừng ứng dụng:

```powershell
docker compose down
```

## Cách chạy trực tiếp bằng Maven

### 1. Chuẩn bị MySQL

Tạo database và bảo đảm MySQL đang chạy tại cổng `3306`:

```sql
CREATE DATABASE laptopshop;
```

Cấu hình mặc định trong `src/main/resources/application.properties`:

| Thông tin | Giá trị |
| --- | --- |
| Database | `laptopshop` |
| Username | `root` |
| Password | `123456` |
| Host/Port | `localhost:3306` |

Nếu thông tin MySQL của bạn khác, hãy cập nhật file cấu hình trước khi chạy.

### 2. Khởi động ứng dụng

Trên Windows:

```powershell
./mvnw.cmd spring-boot:run
```

Hoặc nếu đã cài Maven:

```powershell
mvn spring-boot:run
```

Mở trình duyệt tại: <http://localhost:8080>

## Tài khoản kiểm thử

Dự án hiện **không có tài khoản mẫu được tạo sẵn**. Để tạo tài khoản người dùng:

1. Truy cập <http://localhost:8080/register>.
2. Đăng ký với thông tin kiểm thử sau:

| Trường | Giá trị đề xuất |
| --- | --- |
| Email | `test@example.com` |
| Password | `123456` |
| Confirm password | `123456` |

3. Truy cập <http://localhost:8080/login> và đăng nhập bằng email cùng mật khẩu vừa đăng ký.

Tài khoản đăng ký mới mặc định có role `USER`. Không nên sử dụng mật khẩu database
`123456` để đăng nhập ứng dụng; đó chỉ là mật khẩu MySQL dùng trong cấu hình phát triển.

## Tạo quyền ADMIN để kiểm thử

Sau khi đăng ký tài khoản `test@example.com`, chạy các câu lệnh sau trong database
đang dùng:

```sql
INSERT INTO roles (name, description)
SELECT 'ADMIN', 'Administrator'
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');

UPDATE users
SET role_id = (SELECT id FROM roles WHERE name = 'ADMIN')
WHERE email = 'test@example.com';
```

Đăng xuất rồi đăng nhập lại, sau đó truy cập <http://localhost:8080/admin> để kiểm tra
các chức năng quản trị.

## Một số địa chỉ hữu ích

- Trang chủ: <http://localhost:8080>
- Đăng ký: <http://localhost:8080/register>
- Đăng nhập: <http://localhost:8080/login>
- Trang quản trị: <http://localhost:8080/admin>


