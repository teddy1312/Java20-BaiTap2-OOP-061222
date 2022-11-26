/* Mục đích: Quản lý các nghiệp vụ liên quan đến lớp con Truong Phong
 *  Người tạo: Java20 - Nguyễn Anh Tú
 *  Ngày tạo: 26/11/2022
 *  Version: 1.00
 * */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class TruongPhong extends NhanSu {
    // 1. Attributes
    final private int LUONG_NGAY = 200;
    final private byte LUONG_PHU_CAP = 100;
    private int soLuongNV;

    // 2. Get, set
    public int getSoLuongNV() {
        return soLuongNV;
    }

    // 3. Constructors

    public TruongPhong() {
        super();
        this.soLuongNV = 0;
    }

    public TruongPhong(String maSo, String hoTen, String soDThoai, float soNgayLV) {
        super(maSo, hoTen, soDThoai, soNgayLV);
        this.soLuongNV = 0;
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
        row += String.format("%-30s","SL NV dưới quyền: "+this.soLuongNV);

        System.out.println(row);
    }

    // 5. Businesses
    @Override
    public void tinhLuong() {
        super.tinhLuong();
        this.luongThang = this.soNgayLV * this.LUONG_NGAY + this.soLuongNV * this.LUONG_PHU_CAP;
    }

    @Override
    public void xuatMaVaTen() {
        super.xuatMaVaTen();

        String row = "";
        row += String.format("%-30s","SL NV dưới quyền: "+this.soLuongNV);

        System.out.println(row);
    }

    public void tangSLNVDuoiQuyen(){
        this.soLuongNV++;
    }

    public void giamSLNVDuoiQuyen(){
        if(this.soLuongNV>0) this.soLuongNV--;
    }
}
