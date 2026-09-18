# Laptop Shop

Ứng dụng bán laptop xây dựng bằng Spring Boot, Spring MVC/JSP, Spring Data JPA,
Spring Security và MySQL.

## Giới thiệu đồ án

Laptop Shop là website bán laptop có hai khu vực chính:

- Khu vực khách hàng: xem sản phẩm, xem chi tiết, đăng ký, đăng nhập, thêm sản phẩm
  vào giỏ hàng và đặt hàng.
- Khu vực quản trị: xem dashboard thống kê, quản lý người dùng, sản phẩm và đơn hàng.

## Công nghệ sử dụng

- **Java 17** và **Spring Boot 3.2.2**.
- **Spring MVC** để xây dựng các controller và xử lý request/response.
- **JSP/JSTL** kết hợp Spring MVC View Resolver để dựng giao diện từ thư mục
  `src/main/webapp/WEB-INF/view`.
- **Spring Data JPA/Hibernate** để ánh xạ các entity và thao tác dữ liệu.
- **MySQL 8** làm hệ quản trị cơ sở dữ liệu.
- **Spring Security** cho đăng nhập, phân quyền `USER`/`ADMIN`, mã hóa mật khẩu BCrypt,
  quản lý session và bảo vệ CSRF.
- **Maven** để quản lý dependency và build ứng dụng dạng WAR.
- **Docker/Docker Compose** để chạy ứng dụng và MySQL trong môi trường container.
- **Bootstrap, JavaScript và CSS** cho giao diện người dùng và trang quản trị.

## Chức năng chính

### Người dùng/khách hàng

- Xem trang chủ và danh sách sản phẩm.
- Xem thông tin chi tiết sản phẩm.
- Đăng ký tài khoản và đăng nhập bằng email/mật khẩu.
- Đăng xuất và duy trì phiên đăng nhập.
- Thêm sản phẩm vào giỏ hàng, cập nhật số lượng và xóa sản phẩm khỏi giỏ.
- Đặt hàng và xem thông tin đơn hàng của tài khoản.
- Nhận thông báo khi truy cập chức năng không có quyền.

### Quản trị viên

- Xem dashboard với số lượng người dùng, sản phẩm và đơn hàng.
- Quản lý người dùng: xem danh sách, xem chi tiết, tạo, cập nhật và xóa.
- Quản lý sản phẩm: xem danh sách, xem chi tiết, tạo, cập nhật và xóa.
- Quản lý đơn hàng: xem danh sách, xem chi tiết và xóa đơn hàng.
- Chỉ tài khoản có role `ADMIN` mới truy cập được các URL `/admin/**`.

## Kiến trúc dự án

Dự án sử dụng kiến trúc MVC kết hợp mô hình phân lớp:

```text
Browser
	|
	v
Controller (client/admin)
	|
	v
Service (business logic)
	|
	v
Repository (Spring Data JPA)
	|
	v
MySQL Database

Controller --> JSP/JSTL Views (WEB-INF/view)
SecurityConfiguration --> Authentication + Authorization
WebMvcConfig --> JSP View Resolver + Static Resources
```

Các thành phần chính:

- `controller/client`: xử lý luồng mua hàng, xác thực và chức năng dành cho khách hàng.
- `controller/admin`: xử lý dashboard và các chức năng quản trị.
- `service`: chứa nghiệp vụ ứng dụng như người dùng, sản phẩm, giỏ hàng và đơn hàng.
- `repository`: các interface truy cập dữ liệu thông qua Spring Data JPA.
- `domain`: các entity như `User`, `Role`, `Product`, `Cart`, `Order` và các entity liên quan.
- `config`: cấu hình Spring Security, MVC, view resolver và xử lý chuyển hướng sau đăng nhập.
- `src/main/webapp/WEB-INF/view`: các trang JSP cho client và admin.
- `src/main/resources`: cấu hình ứng dụng, kết nối database và session.

Luồng xử lý điển hình là: người dùng gửi request đến controller, controller gọi service,
service sử dụng repository để đọc/ghi MySQL, sau đó controller trả về trang JSP tương ứng.
Spring Security kiểm tra xác thực và role trước khi cho phép truy cập các chức năng được bảo vệ.

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

. Dự án hiện có 2 tài khoản kiểm thử:

```Role-admin
username: admin@gmail.com
password: 123456
```
```Role-user
username: user@gmail.com
password: 123456
```


. Để tạo tài khoản người dùng:

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


