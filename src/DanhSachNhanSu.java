/* Mục đích: Quản lý các nghiệp vụ liên quan đến danh sách Nhan Su
 *  Người tạo: Java20 - Nguyễn Anh Tú
 *  Ngày tạo: 26/11/2022
 *  Version: 1.00
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DanhSachNhanSu {
    // 1. Attributes
    ArrayList<NhanSu> listNhanSu;
    private long tongLuongCTy;

    // 2. Get, set
    public ArrayList<NhanSu> getListNhanSu() {
        return listNhanSu;
    }

    public void setListNhanSu(ArrayList<NhanSu> listNhanSu) {
        this.listNhanSu = listNhanSu;
    }

    public long getTongLuongCTy() {
        return tongLuongCTy;
    }

    // 3. Constructors
    public DanhSachNhanSu() {
        this.listNhanSu = new ArrayList<NhanSu>();
    }

    // 4. Input, output
    public void themNhanSu(Scanner scan, int option){
        NhanSu nhanSu = null;
        byte phanBo;
        switch (option){
            case 1:
                nhanSu = nhapThemNhanSu(scan,option);
                break;
            case 2:
                nhanSu = nhapThemNhanSu(scan,option);
                do{
                    System.out.print("Nhấn 1 nếu muốn chọn nhân viên dưới quyền, 2 để thoát: ");
                    phanBo = Byte.parseByte(scan.nextLine());
                    if(phanBo == 1) phanBoNVVaoTP(scan,null,(TruongPhong) nhanSu);
                } while(phanBo != 2);
                break;
            case 3:
                nhanSu = nhapThemNhanSu(scan,option);
                do{
                    System.out.print("Nhấn 1 nếu muốn phân bổ vào trường phòng, 2 để thoát: ");
                    phanBo = Byte.parseByte(scan.nextLine());
                    if(phanBo == 1) phanBoNVVaoTP(scan,(NhanVien) nhanSu,null);
                } while(phanBo != 1 && phanBo != 2);
                break;
            case 4:
                break;
            default:
                System.out.println("Vui lòng chỉ chọn từ 1 đến 4");
                break;
        }
    }

    private NhanSu nhapThemNhanSu(Scanner scan,int option){
        NhanSu nhanSu = null;
        switch (option){
            case 1:
                nhanSu = new GiamDoc();
                break;
            case 2:
                nhanSu = new TruongPhong();
                break;
            case 3:
                nhanSu = new NhanVien();
                break;
        }
        nhanSu.nhap(scan,this.listNhanSu);
        this.listNhanSu.add(nhanSu);
        System.out.println("Thêm thành công");

        return nhanSu;
    }
    public void xoaNhanSu(Scanner scan){
        NhanSu nhanSu;
        if(!xuatDSMaTenNS()){
            System.out.println("Không có nhân sự nào trong dạnh sách");
            return;
        }
        nhanSu = timNSTheoMa(scan);

        // Nếu xóa trưởng phòng
        if(nhanSu instanceof TruongPhong){
            for(NhanSu nSu : this.listNhanSu){
                if(nSu instanceof NhanVien ){
                    if(((NhanVien)nSu).getTruongPhong() == nhanSu){
                        ((NhanVien)nSu).setTruongPhong(null);
                    }
                }
            }
        }
        // Nếu xóa nhân viên
        else if(nhanSu instanceof NhanVien){
            if(((NhanVien)nhanSu).getTruongPhong() != null){
                ((NhanVien)nhanSu).getTruongPhong().giamSLNVDuoiQuyen();
            }
        }

        this.listNhanSu.remove(nhanSu);
        System.out.println("Xóa thành công");
    }

    public void xuatNhanSu(){
        int count = 1;
        for(NhanSu nhanSu : this.listNhanSu){
            nhanSu.xuat(count++);
        }

    }

    private boolean xuatDSMaTenNS(){
        boolean dSachTrong = true;
        System.out.println("Danh sách tất cả nhân sự:");

        for(NhanSu nhanSu : this.listNhanSu){
            nhanSu.xuatMaVaTen();
            dSachTrong = false;
        }

        return !dSachTrong;
    }

    // 5. Businesses
    private NhanSu timNSTheoMa(Scanner scan){
        NhanSu nhanSu = null;
        String nhapMaSo;
        do{
            System.out.print("Nhập mã số của nhân sự muốn xóa: ");
            nhapMaSo = scan.nextLine();
            for(NhanSu nSu : this.listNhanSu){
                if(nSu.getMaSo().equalsIgnoreCase(nhapMaSo)){
                    nhanSu = nSu;
                    break;
                }
            }
            if(nhanSu == null){
                System.out.println("Mã số không tồn tại. Vui lòng nhập lại!");
            }
        } while(nhanSu == null);

        return nhanSu;
    }

    public void tinhTongLuongCTy(boolean inTongLuong){
        this.tongLuongCTy = 0;
        String pattern = "$###,###.##";
        DecimalFormat dcf = new DecimalFormat(pattern);

        for(NhanSu nhanSu : this.listNhanSu){
            nhanSu.tinhLuong();
            this.tongLuongCTy += nhanSu.luongThang;
        }

        if(inTongLuong){
            System.out.println("Tổng lương toàn công ty là: "+dcf.format(this.tongLuongCTy));
        }

    }

    public void phanBoNVVaoTP(Scanner scan, NhanVien nVien, TruongPhong trPhong){
        // Nếu nhân viên đưa vào là null
        if(nVien == null){
            if(!xuatDSMaTenDePhanBo(true)){
                System.out.println("Không có nhân viên nào trong danh sách");
                return;
            }
            nVien = (NhanVien) timNSTheoMaDePhanBo(scan,true);
        }
        // Nếu trưởng phòng đưa vào là null
        if(trPhong == null){
            if(!xuatDSMaTenDePhanBo(false)){
                System.out.println("Không có trưởng phòng nào trong danh sách");
                return;
            }
            trPhong = (TruongPhong) timNSTheoMaDePhanBo(scan,false);
        }
        // Giảm sl nhân viên quản lý của trưởng phòng cũ
        if(nVien.getTruongPhong() != null){
            nVien.getTruongPhong().giamSLNVDuoiQuyen();
        }
        // Phân bố trưởng phòng mới cho nhân viên
        nVien.setTruongPhong(trPhong);
        // Tăng sl nhân viên quản lý cho trưởng phòng mới này
        trPhong.tangSLNVDuoiQuyen();
        System.out.println("Phân bố thành công");
    }

    private boolean xuatDSMaTenDePhanBo(boolean timNV){
        boolean dSachTrong = true;
        if(timNV){
            System.out.println("Danh sách các nhân viên:");
        } else{
            System.out.println("Danh sách các trưởng phòng:");
        }

        for(NhanSu nhanSu : this.listNhanSu){
            if(timNV && nhanSu instanceof NhanVien){
                ((NhanVien)nhanSu).xuatMaVaTen();
                dSachTrong = false;
            } else if(!timNV && nhanSu instanceof TruongPhong){
                ((TruongPhong)nhanSu).xuatMaVaTen();
                dSachTrong = false;
            }
        }

        return !dSachTrong;
    }

    private NhanSu timNSTheoMaDePhanBo(Scanner scan, boolean timNV){
        NhanSu nhanSu = null;
        String nhapMaSo;
        do{
            if(timNV){
                System.out.print("Nhập mã số của nhân viên muốn phân bố: ");
            } else{
                System.out.print("Nhập mã số của trưởng phòng muốn quản lý: ");
            }

            nhapMaSo = scan.nextLine();
            for(NhanSu nSu : this.listNhanSu){
                if(timNV && nSu instanceof NhanVien){
                    if(nSu.getMaSo().equalsIgnoreCase(nhapMaSo)){
                        nhanSu = nSu;
                        break;
                    }
                } else if(!timNV && nSu instanceof TruongPhong){
                    if(nSu.getMaSo().equalsIgnoreCase(nhapMaSo)){
                        nhanSu = nSu;
                        break;
                    }
                }
            }
            if(nhanSu == null){
                System.out.println("Mã số không tồn tại. Vui lòng nhập lại!");
            }
        } while(nhanSu == null);

        return nhanSu;
    }

    public void timNVLuongCaoNhat(){
        NhanSu nhanSu = null;
        float luongMax = -1;

        for(NhanSu nSu : this.listNhanSu){
            if(nSu instanceof NhanVien){
                if(nSu.getLuongThang() > luongMax){
                    luongMax = nSu.getLuongThang();
                    nhanSu = nSu;
                }
            }
        }

        if(luongMax<0){
            System.out.println("Không có nhân viên nào trong danh sách");
        } else {
            System.out.println("Nhân viên có lương cao nhất là: ");
            nhanSu.xuat(1);
        }

    }

    public void timTPCoNVNhieuNhat(){
        NhanSu nhanSu = null;
        int soLuongNV = -1;

        for(NhanSu nSu : this.listNhanSu){
            if(nSu instanceof TruongPhong){
                if(((TruongPhong) nSu).getSoLuongNV() > soLuongNV){
                    soLuongNV = ((TruongPhong) nSu).getSoLuongNV();
                    nhanSu = nSu;
                }
            }
        }

        if(soLuongNV<0){
            System.out.println("Không có trưởng phòng nào trong danh sách");
        } else {
            System.out.println("Trưởng phòng có số lượng nhân viên nhiều nhất là: ");
            nhanSu.xuat(1);
        }

    }

    public void timGDCoCPhanNhieuNhat(){
        NhanSu nhanSu = null;
        float cpMax = -1;

        for(NhanSu nSu : this.listNhanSu){
            if(nSu instanceof GiamDoc){
                if(((GiamDoc) nSu).getSoCoPhan() > cpMax){
                    cpMax = ((GiamDoc) nSu).getSoCoPhan();
                    nhanSu = nSu;
                }
            }
        }

        if(cpMax<0){
            System.out.println("Không có giám đốc nào trong danh sách");
        } else {
            System.out.println("Giám đốc có số lượng cổ phần nhiều nhất là: ");
            nhanSu.xuat(1);
        }

    }

    public void sapXepTenNSTheoAbc(){
        String[] listTruoc;
        String[] listSau;

        for(int i=0;i<this.listNhanSu.size()-1;i++){
            for(int j=this.listNhanSu.size()-1;j>i;j--){
                listTruoc = this.listNhanSu.get(i).getHoTen().split(" ");
                listSau = this.listNhanSu.get(j).getHoTen().split(" ");
                if(listTruoc[listTruoc.length-1].compareToIgnoreCase(listSau[listSau.length-1])>0){
                    Collections.swap(this.listNhanSu,i,j);
                }
            }
        }

        System.out.println("Danh sách tên nhân sự theo thứ tự abc là: ");
        xuatNhanSu();

    }

    public void sapXepNSLuongGiamDan(){
        for(int i=0;i<this.listNhanSu.size()-1;i++){
            for(int j=i+1;j<this.listNhanSu.size();j++){
                if(this.listNhanSu.get(i).getLuongThang()<this.listNhanSu.get(j).getLuongThang()){
                    Collections.swap(this.listNhanSu,i,j);
                }
            }
        }

        System.out.println("Danh sách nhân sự theo thứ tự lương giảm dần là: ");
        xuatNhanSu();

    }

    public void tinhXuatThuNhapGD(long doanhThuCTy){
        boolean dSTrong = true;
        String pattern = "$###,###.##";
        DecimalFormat dcf = new DecimalFormat(pattern);

        tinhTongLuongCTy(false);
        long loiNhuanCTY = doanhThuCTy - this.tongLuongCTy;

        System.out.println("Lợi nhuận công ty: "+dcf.format(loiNhuanCTY));
        System.out.println("Danh sách thu nhập của từng giám đốc là: ");
        for(NhanSu nSu : this.listNhanSu){
            if(nSu instanceof GiamDoc){
                ((GiamDoc)nSu).tinhXuatThuNhap(loiNhuanCTY);
                dSTrong = false;
            }
        }
        if(dSTrong) System.out.println("Không có giám đốc nào trong danh sách");
    }

    public void taiDummyData(){
        NhanSu nhanSu;
        try{
            FileReader reader = new FileReader("src/DSNhanSu.txt");
            BufferedReader buffer = new BufferedReader(reader);
            String line;

            while ((line = buffer.readLine()) != null){
                String[] list = line.split(" # ");
                switch (Integer.parseInt(list[0])){
                    case 1:
                        nhanSu = new GiamDoc(list[1],list[2],list[3],
                                Float.parseFloat(list[4]),Float.parseFloat(list[5]));
                        this.listNhanSu.add(nhanSu);
                        break;
                    case 2:
                        nhanSu = new TruongPhong(list[1],list[2],list[3],Float.parseFloat(list[4]));
                        this.listNhanSu.add(nhanSu);
                        break;
                    case 3:
                        nhanSu = new NhanVien(list[1],list[2],list[3],Float.parseFloat(list[4]));
                        this.listNhanSu.add(nhanSu);
                        break;
                }
            }
            reader.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
