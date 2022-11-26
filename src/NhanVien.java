/* Mục đích: Quản lý các nghiệp vụ liên quan đến lớp con Nhan Vien
 *  Người tạo: Java20 - Nguyễn Anh Tú
 *  Ngày tạo: 26/11/2022
 *  Version: 1.00
 * */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class NhanVien extends NhanSu{
    // 1. Attributes
    final private byte LUONG_NGAY = 100;
    private TruongPhong truongPhong;

    // 2. Get, set
    public TruongPhong getTruongPhong() {
        return truongPhong;
    }

    public void setTruongPhong(TruongPhong truongPhong) {
        this.truongPhong = truongPhong;
    }

    // 3. Constructors
    public NhanVien() {
        super();
        this.truongPhong = null;
    }

    public NhanVien(String maSo, String hoTen, String soDThoai, float soNgayLV) {
        super(maSo, hoTen, soDThoai, soNgayLV);
        this.truongPhong = null;
    }

    // 4. Input, output

    @Override
    public void nhap(Scanner scan, ArrayList<NhanSu> dsNhanSu) {
        super.nhap(scan,dsNhanSu);
    }

    @Override
    public void xuat(int stt) {
        super.xuat(stt);

        String pattern = "$###,###.##";
        DecimalFormat dcf = new DecimalFormat(pattern);

        String row = "";
        row += String.format("%-16s","Lương: "+dcf.format(this.luongThang))+" | ";
        if(this.truongPhong != null){
            row += String.format("%-30s","Trưởng phòng: "+this.truongPhong.getHoTen()
                                            +"(MS:"+this.truongPhong.getMaSo()+")");
        } else{
            row += String.format("%-25s","Trưởng phòng: Không có");
        }

        System.out.println(row);
    }

    // 5. Businesses
    @Override
    public void tinhLuong() {
        super.tinhLuong();
        this.luongThang = this.soNgayLV * this.LUONG_NGAY;
    }

    @Override
    public void xuatMaVaTen() {
        super.xuatMaVaTen();

        String row = "";
        if(this.truongPhong != null){
            row += String.format("%-30s","Trưởng phòng: "+this.truongPhong.getHoTen()
                    +"(MS:"+this.truongPhong.getMaSo()+")");
        } else{
            row += String.format("%-25s","Trưởng phòng: Không có");
        }

        System.out.println(row);
    }
}
