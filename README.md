# TP4 DPBO 2025/2026

## Data Diri

| Keterangan  | Data                                      |
| ----------- | ----------------------------------------- |
| Nama        | Fakhri Fauzan                             |
| NIM         | 2501536                                   |
| Kelas       | C2                                        |
| Mata Kuliah | Desain dan Pemrograman Berorientasi Objek |

# Janji

Saya Fakhri Fauzan dengan NIM 2501536 mengerjakan Tugas Praktikum 4 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain dan Fungsi Program

Program ini merupakan aplikasi pengelolaan data orang (_People Data_) berbasis GUI menggunakan Java Swing. Program menerapkan konsep Pemrograman Berorientasi Objek dengan memisahkan representasi data ke dalam kelas `Person` dan pengelolaan antarmuka serta operasi data ke dalam kelas `PeopleMenu`.

### 1. Kelas `Person`

Kelas `Person` digunakan untuk merepresentasikan data seseorang. Kelas ini memiliki atribut sebagai berikut:

| Atribut           | Tipe Data | Keterangan                                             |
| ----------------- | --------- | ------------------------------------------------------ |
| `id`              | `String`  | Identitas atau nomor ID seseorang                      |
| `nama`            | `String`  | Nama lengkap seseorang                                 |
| `tahunLahir`      | `int`     | Tahun lahir seseorang                                  |
| `kategori`        | `String`  | Kategori seseorang                                     |
| `statusKeaktifan` | `String`  | Status keaktifan seseorang, yaitu Aktif atau Non-Aktif |

Atribut dibuat menggunakan access modifier `private` untuk menerapkan enkapsulasi. Pengaksesan dan perubahan nilai atribut dilakukan melalui getter dan setter yang tersedia. Kelas ini juga memiliki constructor tanpa parameter dan constructor berparameter untuk membuat objek `Person`.

### 2. Kelas `PeopleMenu`

Kelas `PeopleMenu` digunakan untuk menampilkan antarmuka aplikasi sekaligus mengelola data orang. Data disimpan dalam `ArrayList` sehingga jumlah data dapat bertambah atau berkurang selama program berjalan.

Antarmuka program menyediakan komponen berikut:

- **ID, Nama Lengkap, dan Tahun Lahir:** untuk memasukkan data seseorang.
- **Kategori:** untuk memilih kategori seseorang.
- **Status Keaktifan:** menggunakan radio button Aktif dan Non-Aktif.
- **Tabel Data:** untuk menampilkan seluruh data yang tersimpan.
- **Tombol Add/Update:** untuk menambahkan data baru atau memperbarui data yang dipilih.
- **Tombol Delete:** untuk menghapus data yang dipilih setelah pengguna mengonfirmasi penghapusan.
- **Tombol Cancel:** untuk membatalkan pengisian atau mengosongkan kembali input.

### 3. Fungsi Utama Program

Program menyediakan seluruh operasi CRUD (_Create, Read, Update, Delete_) sebagai berikut:

- **Create:** menambahkan data orang baru berdasarkan input pengguna.
- **Read:** menampilkan data yang tersimpan dalam tabel.
- **Update:** memperbarui data orang yang dipilih pada tabel.
- **Delete:** menghapus data orang yang dipilih setelah pengguna memberikan konfirmasi.

Program juga menyediakan validasi input untuk mencegah data yang tidak sesuai, seperti ID atau nama kosong, tahun lahir yang tidak valid, serta ID duplikat. Tahun lahir dibatasi pada rentang 1900–2026. Validasi dilakukan sebelum data ditambahkan atau diperbarui agar data yang tersimpan tetap sesuai ketentuan.

# Alur Program

1. Saat program dijalankan, aplikasi menampilkan antarmuka People Data dan data awal yang telah disediakan.
2. Pengguna dapat melihat data yang tersedia melalui tabel.
3. Untuk menambahkan data, pengguna mengisi seluruh input yang diperlukan, memilih kategori dan status keaktifan, kemudian menekan tombol Add/Update.
4. Program memvalidasi input. Jika data tidak valid, program menampilkan pesan kesalahan. Jika valid, data ditambahkan ke daftar dan tabel diperbarui.
5. Untuk memperbarui data, pengguna memilih baris pada tabel. Data dari baris tersebut ditampilkan pada komponen input. Pengguna kemudian mengubah data yang diperlukan dan menekan tombol Add/Update.
6. Untuk menghapus data, pengguna memilih baris yang diinginkan dan menekan tombol Delete. Program menampilkan konfirmasi penghapusan. Data hanya dihapus apabila pengguna menyetujui konfirmasi tersebut.
7. Tombol Cancel dapat digunakan untuk membatalkan pengisian atau mengosongkan input. Tabel akan menampilkan data terbaru setelah operasi yang berhasil dilakukan.

# Dokumentasi Program

Dokumentasi berikut menunjukkan aplikasi ketika dijalankan dan membuktikan bahwa seluruh operasi CRUD dapat digunakan.

| Operasi                     | Dokumentasi                                                     |
| --------------------------- | --------------------------------------------------------------- |
| Read                        | [Lihat dokumentasi Read](Dokumentasi/01_Read.mp4)               |
| Create                      | [Lihat dokumentasi Create](Dokumentasi/02_Create.mp4)           |
| Update                      | [Lihat dokumentasi Update](Dokumentasi/03_Update.mp4)           |
| Delete                      | [Lihat dokumentasi Delete](Dokumentasi/04_Delete.mp4)           |
| Validasi dan fitur tambahan | [Lihat dokumentasi validasi](Dokumentasi/05_Validasi_Bonus.mp4) |

# Struktur Repository

```text
TP4DPBO2526C2/
├── PeopleData/
│   ├── Person.java
│   ├── PeopleMenu.java
│   └── PeopleMenu.form
├── Dokumentasi/
│   └── File video dokumentasi
└── README.md
```

# Kesimpulan

Aplikasi People Data merupakan program pengelolaan data berbasis Java Swing yang menerapkan konsep OOP melalui kelas `Person` dan `PeopleMenu`. Program mendukung operasi CRUD, validasi input, pemilihan status keaktifan, serta konfirmasi sebelum penghapusan data.
