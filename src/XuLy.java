/*  Mục đích: OOP - Bài tập 2: Quản lý nhân sự
 *  Người tạo: Java20 - Nguyễn Anh Tú
 *  Ngày tạo: 26/11/2022
 *  Version: 1.00
 * */

import java.util.Scanner;

public class XuLy {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        thucHienMenu(scan);
    }

    private static void inMenu(){
        System.out.println("   ");
        System.out.println("Vui lòng chọn: ");
        System.out.println("   1. Nhập thông tin công ty");
        System.out.println("   2. Thêm nhân sự");
        System.out.println("   3. Xóa nhân sự");
        System.out.println("   4. Phân bổ nhân viên vào trưởng phòng");
        System.out.println("   5. Xuất toàn bộ thông tin người trong công ty");
        System.out.println("   6. Tính và xuất tổng lương toàn công ty");
        System.out.println("   7. Tìm nhân viên có lương cao nhất");
        System.out.println("   8. Tìm trưởng phòng có số lượng nhân viên dưới quyền nhiều nhất");
        System.out.println("   9. Tìm giám đốc có số lượng cổ phần nhiều nhất ");
        System.out.println("   10. Tính và xuất thu nhập của từng giám đốc ");
        System.out.println("   11. Sắp xếp tên nhân sự theo thứ tự abc ");
        System.out.println("   12. Sắp xếp nhân sự theo thứ tự lương giảm dần ");
        System.out.println("   13. Thoát ");
    }

    private static void thucHienMenu(Scanner scan){
        CongTy congTy = new CongTy("Stormwind WoW","13121993",53500);
        congTy.taiDummyData();
        byte luaChon;
        do{
            inMenu();
            System.out.print("Lựa chọn của bạn là: ");
            luaChon = Byte.parseByte(scan.nextLine());
            switch (luaChon){
                case 1:
                    congTy.nhapThongTinCTy(scan);
                    break;
                case 2:
                    congTy.themNhanSu(scan);
                    break;
                case 3:
                    congTy.xoaNhanSu(scan);
                    break;
                case 4:
                    congTy.phanBoNVVaoTP(scan);
                    break;
                case 5:
                    congTy.xuatThongTinCTy();
                    break;
                case 6:
                    congTy.tinhTongLuongCTy();
                    break;
                case 7:
                    congTy.timNVLuongCaoNhat();
                    break;
                case 8:
                    congTy.timTPCoNVNhieuNhat();
                    break;
                case 9:
                    congTy.timGDCoCPhanNhieuNhat();
                    break;
                case 10:
                    congTy.tinhXuatThuNhapGD();
                    break;
                case 11:
                    congTy.sapXepTenNSTheoAbc();
                    break;
                case 12:
                    congTy.sapXepNSLuongGiamDan();
                    break;
                case 13:
                    break;
                default:
                    System.out.println("Vui lòng chỉ chọn từ 1 đến 13");
                    break;
            }
        } while(luaChon != 13);

    }
}
