/* Mục đích: Quản lý các nghiệp vụ liên quan đến lớp con Giam Doc
 *  Người tạo: Java20 - Nguyễn Anh Tú
 *  Ngày tạo: 26/11/2022
 *  Version: 1.00
 * */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class GiamDoc extends NhanSu{
    // 1. Attributes
    final private int LUONG_NGAY = 300;
    private float soCoPhan;
    private int thuNhap;

    // 2. Get, set
    public float getSoCoPhan() {
        return soCoPhan;
    }

    public void setSoCoPhan(float soCoPhan) {
        this.soCoPhan = soCoPhan;
    }

    public int getThuNhap() {
        return thuNhap;
    }

    // 3. Constructors
    public GiamDoc() {
        super();
    }

    public GiamDoc(String maSo, String hoTen, String soDThoai, float soNgayLV, float soCP) {
        super(maSo, hoTen, soDThoai, soNgayLV);
        this.soCoPhan = soCP;
    }

    // 4. Input, output
    @Override
    public void nhap(Scanner scan, ArrayList<NhanSu> dsNhanSu) {
        super.nhap(scan,dsNhanSu);
        do{
            System.out.print("Nhập số cổ phần(0~100%):");
            this.soCoPhan = Float.parseFloat(scan.nextLine());
            if(this.soCoPhan<0 || this.soCoPhan>100){
                System.out.println("Số cổ phần nhập không hợp lệ. Vui lòng nhập lại!");
            }
        } while(this.soCoPhan<0 || this.soCoPhan>100);

    }

    @Override
    public void xuat(int stt) {
        super.xuat(stt);

        String pattern = "$###,###.##";
        DecimalFormat dcf = new DecimalFormat(pattern);

        String row = "";
        row += String.format("%-16s","Lương: "+dcf.format(this.luongThang))+" | ";
        row += String.format("%-30s","Số cổ phần: "+this.soCoPhan+"%");

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
        row += String.format("%-30s","Số cổ phần: "+this.soCoPhan+"%");

        System.out.println(row);
    }

    public void tinhXuatThuNhap(long loiNhuanCTY){
        String pattern = "$###,###.##";
        DecimalFormat dcf = new DecimalFormat(pattern);
        if(loiNhuanCTY < 0)     loiNhuanCTY = 0;
        this.thuNhap = (int)(this.luongThang + (loiNhuanCTY * this.soCoPhan/100.0f));

        String row = "";
        row += String.format("%-15s","Mã số: "+this.maSo)+" | ";
        row += String.format("%-20s","Họ tên: "+this.hoTen)+" | ";
        row += String.format("%-20s","Số cổ phần: "+this.soCoPhan+"%")+" | ";
        row += String.format("%-30s","Tổng thu nhập: "+dcf.format(this.thuNhap));

        System.out.println(row);
    }
}
