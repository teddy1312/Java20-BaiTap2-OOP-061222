/* Mục đích: Quản lý các nghiệp vụ liên quan đến lớp cha Nhan Su
 *  Người tạo: Java20 - Nguyễn Anh Tú
 *  Ngày tạo: 26/11/2022
 *  Version: 1.00
 * */

import java.util.ArrayList;
import java.util.Scanner;

abstract class NhanSu{
    // 1. Attributes
    protected String maSo;
    protected String hoTen;
    protected String soDThoai;
    protected float soNgayLV;
    protected float luongThang;

    // 2. Get, set
    public String getMaSo() {
        return maSo;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDThoai() {
        return soDThoai;
    }

    public void setSoDThoai(String soDThoai) {
        this.soDThoai = soDThoai;
    }

    public float getSoNgayLV() {
        return soNgayLV;
    }

    public void setSoNgayLV(float soNgayLV) {
        this.soNgayLV = soNgayLV;
    }

    public float getLuongThang() {
        return luongThang;
    }

    // 3. Constructors

    public NhanSu() {
    }

    public NhanSu(String maSo, String hoTen, String soDThoai, float soNgayLV) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.soDThoai = soDThoai;
        this.soNgayLV = soNgayLV;
    }

    // 4. Input, output
    public void nhap(Scanner scan, ArrayList<NhanSu> dsNhanSu){
        boolean trungMaSo = true;
        do{
            System.out.print("Nhập mã số nhân sự:");
            this.maSo = scan.nextLine();
            for(NhanSu nSu : dsNhanSu){
                if(nSu.getMaSo().equalsIgnoreCase(this.maSo)){
                    System.out.println("Mã số bị trùng lập. Vui lòng nhập lại!");
                    break;
                } else{
                    trungMaSo = false;
                }
            }
        } while(trungMaSo);

        System.out.print("Nhập họ tên:");
        this.hoTen = scan.nextLine();

        System.out.print("Nhập số điện thoại:");
        this.soDThoai = scan.nextLine();

        do{
            System.out.print("Nhập số ngày làm việc:");
            this.soNgayLV = Float.parseFloat(scan.nextLine());
            if(this.soNgayLV<0 || this.soNgayLV>31){
                System.out.println("Số ngày làm việc nhập không hợp lệ. Vui lòng nhập lại!");
            }
        } while(this.soNgayLV<0 || this.soNgayLV>31);

    }

    public void xuat(int stt){
        String row = "";
        row += String.format("%-9s","STT: "+String.valueOf(stt))+" | ";
        row += String.format("%-15s","Mã số: "+this.maSo)+" | ";
        row += String.format("%-25s","Họ tên: "+this.hoTen)+" | ";
        row += String.format("%-15s","SĐT: "+this.soDThoai)+" | ";
        row += String.format("%-19s","Số ngày LV: "+this.soNgayLV)+" | ";

        System.out.print(row);
    }

    // 5. Businesses
    public abstract void tinhLuong();

    public void xuatMaVaTen(){
        String row = "";
        row += String.format("%-15s","Mã số: "+this.maSo)+" | ";
        row += String.format("%-20s","Họ tên: "+this.hoTen)+" | ";

        System.out.print(row);
    }

}
