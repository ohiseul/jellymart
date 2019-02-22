package kr.co.ljy.jellyshop.product.controller;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.ljy.jellyshop.controller.Controller;
import kr.co.ljy.jellyshop.controller.Controllers;
import kr.co.ljy.jellyshop.dao.ProductMapper;
import kr.co.ljy.jellyshop.db.MyAppSqlConfig;


public class AdminProductMainController extends Controllers {

	public Scanner sc = new Scanner(System.in);
	private ProductMapper productMapper;
	private int selectnum;
	

	public AdminProductMainController(int selectnum) {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		productMapper = session.getMapper(ProductMapper.class);
		this.selectnum=selectnum;
	}


	private void exit() {
		System.out.println("시스템을 종료합니다.");
		System.exit(0);
	}
	
	private int choiceMenu() {
		System.out.println();
		printj("=");
		System.out.println("*****상품메인화면입니다*****");
		printj("=");
		System.out.println("1. 상품조회");
		System.out.println("2. 상품등록");
		System.out.println("3. 관리자 상품평 조회 및 등록");
		System.out.println("0. 종료");
		printj("=");
		System.out.print("번호를 입력하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
	
	public void service(){
			while (true) {
				Controller ctrl = null;
				switch (choiceMenu()) {
				case 1:  // 상품조회
					ctrl = new AdminSearchMain(selectnum);
					break;
				case 2:  // 상품등록
					ctrl = new AdminWriteProductController(selectnum);
					break;
				case 3:  // 관리자 상품평 조회 및 등록
					ctrl = new ListCommentController(selectnum);
					break;
				case 0:
					exit();
				default:
					System.out.println("잘못된 메뉴번호 입니다.");
					System.out.println("다시 선택해 주세요.");
				}
				
				if (ctrl != null) {
					try {
						ctrl.service();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

	}
}










