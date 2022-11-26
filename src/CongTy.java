/* Mục đích: Quản lý các nghiệp vụ liên quan đến lớp Cong Ty
 *  Người tạo: Java20 - Nguyễn Anh Tú
 *  Ngày tạo: 26/11/2022
 *  Version: 1.00
 * */

import java.text.DecimalFormat;
import java.util.Scanner;

public class CongTy {
    // 1. Attributes
    private String tenCongTy;
    private String maSoThue;
    private long doanhThu;
    private DanhSachNhanSu objDSNhanSu;

    // 2. Get, set
    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public long getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(long doanhThu) {
        this.doanhThu = doanhThu;
    }

    public DanhSachNhanSu getObjDSNhanSu() {
        return objDSNhanSu;
    }

    public void setObjDSNhanSu(DanhSachNhanSu objDSNhanSu) {
        this.objDSNhanSu = objDSNhanSu;
    }

    // 3. Constructors
    public CongTy() {
        this.objDSNhanSu = new DanhSachNhanSu();
    }

    public CongTy(String tenCongTy, String maSoThue, long doanhThu) {
        this.tenCongTy = tenCongTy;
        this.maSoThue = maSoThue;
        this.doanhThu = doanhThu;
        this.objDSNhanSu = new DanhSachNhanSu();
    }

    // 4. Input, output
    public void nhapThongTinCTy(Scanner scan){
        System.out.print("Nhập tên công ty:");
        this.tenCongTy = scan.nextLine();

        System.out.print("Nhập mã số thuế:");
        this.maSoThue = scan.nextLine();

        System.out.print("Nhập doanh thu tháng($):");
        this.doanhThu = Long.parseLong(scan.nextLine());
    }

    public void xuatThongTinCTy(){
        String pattern = "$###,###.##";
        DecimalFormat dcf = new DecimalFormat(pattern);

        String row = "";
        row += String.format("%-27s","Tên công ty: "+this.tenCongTy)+" | ";
        row += String.format("%-25s","Mã số thuế: "+this.maSoThue)+" | ";
        row += String.format("%-30s","Tổng doanh thu: "+dcf.format(this.doanhThu)) ;
        System.out.println(row);

        this.objDSNhanSu.xuatNhanSu();
    }

    // 5. Businesses
    public void themNhanSu(Scanner scan){
        this.objDSNhanSu.themNhanSu(scan);
    }

    public void xoaNhanSu(Scanner scan){
        this.objDSNhanSu.xoaNhanSu(scan);
    }

    public void tinhTongLuongCTy(){
        this.objDSNhanSu.tinhTongLuongCTy(true);
    }

    public void phanBoNVVaoTP(Scanner scan){
        byte chon;
        do{
            System.out.print("Nhấp 1 để tiếp tục, 2 để thoát: ");
            chon = Byte.parseByte(scan.nextLine());
            if(chon == 1){
                this.objDSNhanSu.phanBoNVVaoTP(scan,null,null);
            }
        } while(chon != 2);

    }

    public void timNVLuongCaoNhat(){
        this.objDSNhanSu.timNVLuongCaoNhat();
    }

    public void timTPCoNVNhieuNhat(){
        this.objDSNhanSu.timTPCoNVNhieuNhat();
    }

    public void timGDCoCPhanNhieuNhat(){
        this.objDSNhanSu.timGDCoCPhanNhieuNhat();
    }

    public void sapXepTenNSTheoAbc(){
        this.objDSNhanSu.sapXepTenNSTheoAbc();
    }

    public void sapXepNSLuongGiamDan(){
        this.objDSNhanSu.sapXepNSLuongGiamDan();
    }

    public void tinhXuatThuNhapGD(){
        this.objDSNhanSu.tinhXuatThuNhapGD(this.doanhThu);
    }

    public void taiDummyData(){
        this.objDSNhanSu.taiDummyData();
    }
}
