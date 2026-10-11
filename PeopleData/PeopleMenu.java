
// Mengimpor komponen antarmuka Swing, seperti JFrame, JButton, JTable, dan JOptionPane
import javax.swing.*;

// Mengimpor model data yang digunakan untuk mengisi JTable
import javax.swing.table.DefaultTableModel;

// Mengimpor kelas AWT; di file ini Font digunakan untuk mengatur tampilan judul
import java.awt.*;

// Mengimpor informasi tentang aksi yang terjadi, misalnya saat tombol diklik
import java.awt.event.ActionEvent;

// Mengimpor listener untuk menangani aksi pada tombol
import java.awt.event.ActionListener;

// Mengimpor kelas pembantu untuk menangani interaksi mouse
import java.awt.event.MouseAdapter;

// Mengimpor informasi tentang kejadian mouse, seperti klik pada baris tabel
import java.awt.event.MouseEvent;

// Mengimpor ArrayList untuk menyimpan daftar objek Person
import java.util.ArrayList;

public class PeopleMenu extends JFrame {
    // Panel utama yang menampung seluruh komponen tampilan
    private JPanel mainPanel;

    // Label judul aplikasi
    private JLabel titleLabel;

    // Field untuk memasukkan ID, nama, dan tahun lahir
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;

    // Pilihan kategori orang
    private JComboBox<String> kategoriComboBox;

    // Panel dan tombol pilihan status keaktifan
    private JPanel statusRadioPanel;
    private JRadioButton aktifRadioButton;
    private JRadioButton nonAktifRadioButton;

    // Mengelompokkan tombol status agar hanya satu yang dapat dipilih
    private ButtonGroup statusButtonGroup;

    // Tombol untuk menambah atau memperbarui data
    private JButton addUpdateButton;

    // Tombol untuk membatalkan atau mengosongkan form
    private JButton cancelButton;

    // Tombol untuk menghapus data yang dipilih
    private JButton deleteButton;

    // Tabel untuk menampilkan daftar orang
    private JTable peopleTable;

    // Panel gulir untuk tabel
    private JScrollPane tableScrollPane;

    // Daftar data orang yang dikelola aplikasi
    private ArrayList<Person> listPerson;

    // Indeks data yang dipilih; -1 berarti belum ada data yang dipilih
    private int selectedIndex = -1;

    public PeopleMenu() {
        // Siapkan sumber data sebelum membangun tampilan tabel (Inisialisasi ArrayList
        // dan isi dengan data awal)
        listPerson = new ArrayList<>();
        populateList();

        // Setup awal pilihan Kategori (Sivitas Akademika)
        String[] kategoriData = { "--- Pilih Kategori ---", "S1 Reguler", "S2 Magister", "D3 Vokasi", "Dosen",
                "Tenaga Kependidikan" };
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // Grouping Radio Button untuk Status Keaktifan (Bonus Component)
        statusButtonGroup = new ButtonGroup();
        statusButtonGroup.add(aktifRadioButton);
        statusButtonGroup.add(nonAktifRadioButton);
        aktifRadioButton.setSelected(true); // Default

        // Set model tabel dari listPerson
        peopleTable.setModel(setTable());

        // Styling Title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // Sembunyikan button delete di awal
        deleteButton.setVisible(false);

        // Listener: Tombol Add / Update
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aksi tombol mengikuti mode form: tambah saat belum memilih baris,
                // atau perbarui data saat sebuah baris sedang dipilih.
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // Listener: Tombol Delete (dengan Pop-up Konfirmasi)
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });

        // Listener: Tombol Cancel
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Listener: Klik Baris JTable
        peopleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Indeks baris tabel sama dengan indeks data di listPerson
                selectedIndex = peopleTable.getSelectedRow();

                if (selectedIndex != -1) {
                    // Ambil nilai dari tabel
                    String curId = peopleTable.getModel().getValueAt(selectedIndex, 1).toString();
                    String curNama = peopleTable.getModel().getValueAt(selectedIndex, 2).toString();
                    String curTahun = peopleTable.getModel().getValueAt(selectedIndex, 3).toString();
                    String curKategori = peopleTable.getModel().getValueAt(selectedIndex, 4).toString();
                    String curStatus = peopleTable.getModel().getValueAt(selectedIndex, 5).toString();

                    // Isi form input secara otomatis
                    idField.setText(curId);
                    namaField.setText(curNama);
                    tahunLahirField.setText(curTahun);
                    kategoriComboBox.setSelectedItem(curKategori);

                    if (curStatus.equalsIgnoreCase("Aktif")) {
                        aktifRadioButton.setSelected(true);
                    } else {
                        nonAktifRadioButton.setSelected(true);
                    }

                    // Ubah tombol "Add" menjadi "Update" dan tampilkan "Delete"
                    addUpdateButton.setText("Update");
                    deleteButton.setVisible(true);
                }
            }
        });
    }

    // Mengisi 5-10 data awal (Aplikasi Sivitas Akademika UPI)
    private void populateList() {
        listPerson.add(new Person("2201001", "Ahmad Fauzi", 2003, "S1 Reguler", "Aktif"));
        listPerson.add(new Person("2201002", "Siti Nurhaliza", 2002, "S1 Reguler", "Aktif"));
        listPerson.add(new Person("2302001", "Budi Santoso", 1999, "S2 Magister", "Aktif"));
        listPerson.add(new Person("2103001", "Dewi Lestari", 2003, "D3 Vokasi", "Non-Aktif"));
        listPerson.add(new Person("19850101", "Dr. Eng. Riza", 1985, "Dosen", "Aktif"));
        listPerson.add(new Person("19900215", "Hendra Wijaya", 1990, "Tenaga Kependidikan", "Aktif"));
        listPerson.add(new Person("2201005", "Rian Pratama", 2004, "S1 Reguler", "Non-Aktif"));
    }

    // Mengubah ArrayList menjadi DefaultTableModel untuk JTable
    public final DefaultTableModel setTable() {
        Object[] cols = { "No", "ID / NIM / NIP", "Nama", "Tahun Lahir", "Kategori", "Status Keaktifan" };
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        // Nomor yang tampil dimulai dari 1, sedangkan indeks ArrayList dimulai dari 0.
        for (int i = 0; i < listPerson.size(); i++) {
            Object[] row = {
                    i + 1,
                    listPerson.get(i).getId(),
                    listPerson.get(i).getNama(),
                    listPerson.get(i).getTahunLahir(),
                    listPerson.get(i).getKategori(),
                    listPerson.get(i).getStatusKeaktifan()
            };
            tmp.addRow(row);
        }
        return tmp;
    }

    // Fungsi Tambah Data
    public void insertData() {
        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            String tahunStr = tahunLahirField.getText().trim();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String status = aktifRadioButton.isSelected() ? "Aktif" : "Non-Aktif";

            // Tolak input yang belum lengkap atau masih memakai pilihan kategori awal.
            if (id.isEmpty() || nama.isEmpty() || tahunStr.isEmpty() || kategori.equals("--- Pilih Kategori ---")) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi dengan benar!", "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Parsing juga memastikan tahun lahir benar-benar diisi dengan angka.
            int tahunLahir = Integer.parseInt(tahunStr);

            // Batasi tahun lahir pada rentang yang diterima aplikasi.
            if (tahunLahir < 1900 || tahunLahir > 2026) {
                JOptionPane.showMessageDialog(
                        this,
                        "Tahun lahir harus antara 1900 dan 2026!",
                        "Input Tidak Valid",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            listPerson.add(new Person(id, nama, tahunLahir, kategori, status));
            // Bangun ulang model agar perubahan sumber data langsung terlihat di tabel.
            peopleTable.setModel(setTable());
            clearForm();

            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!", "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tahun Lahir harus berupa angka!", "Error Validasi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fungsi Update Data
    public void updateData() {
        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            String tahunStr = tahunLahirField.getText().trim();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String status = aktifRadioButton.isSelected() ? "Aktif" : "Non-Aktif";

            if (id.isEmpty() || nama.isEmpty() || tahunStr.isEmpty() || kategori.equals("--- Pilih Kategori ---")) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi dengan benar!", "Peringatan",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int tahunLahir = Integer.parseInt(tahunStr);

            // Gunakan batas tahun yang sama seperti saat menambahkan data.
            if (tahunLahir < 1900 || tahunLahir > 2026) {
                JOptionPane.showMessageDialog(
                        this,
                        "Tahun lahir harus antara 1900 dan 2026!",
                        "Input Tidak Valid",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            listPerson.get(selectedIndex).setId(id);
            listPerson.get(selectedIndex).setNama(nama);
            listPerson.get(selectedIndex).setTahunLahir(tahunLahir);
            listPerson.get(selectedIndex).setKategori(kategori);
            listPerson.get(selectedIndex).setStatusKeaktifan(status);

            // Tampilkan nilai terbaru, lalu kembalikan form ke mode tambah.
            peopleTable.setModel(setTable());
            clearForm();

            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tahun Lahir harus berupa angka!", "Error Validasi",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fungsi Delete Data dengan Pop-up Konfirmasi
    public void deleteData() {
        if (selectedIndex != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Apakah Anda yakin ingin menghapus data " + listPerson.get(selectedIndex).getNama() + "?",
                    "Konfirmasi Hapus Data",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                // Hapus data dari sumber utama, lalu sinkronkan ulang tampilan tabel.
                listPerson.remove(selectedIndex);
                peopleTable.setModel(setTable());
                clearForm();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Reset Form Input ke State Awal
    public void clearForm() {
        // Kosongkan input dan pulihkan pilihan default.
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        aktifRadioButton.setSelected(true);

        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
        // Hapus sorotan agar tabel tidak lagi menunjukkan data dalam mode edit.
        peopleTable.clearSelection();
    }

    // Main Method
    public static void main(String[] args) {
        PeopleMenu frame = new PeopleMenu();
        // Atur jendela utama sebelum menampilkannya ke pengguna.
        frame.setTitle("Sistem Data Sivitas Akademika - PeopleData");
        frame.setSize(800, 650);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(frame.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}