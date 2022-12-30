package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberView;

/*
 * Controller : View를 통해서 요청한 기능을 담당
 * 			    해당 메서드로 전달된 데이터들을 가공처리(vo객체 담아주기) 한 후 Dao 메서드 호출시 vo객체를 전달해준다
 * 	            Dao로부터 반환받은 결과에 따라 사용자가 보게될 화면을 지정해준다.
 */
public class MemberController {

	public void insertMember(String userId, String userPwd, String userName, String gender, int age, String email,
			String phone, String address, String hobby) {

		// 1. 전달된 데이터들을 Member객체에 담기 => 가공처리
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);

		// 2. Dao의 insertMember 메서드 호출
		int result = new MemberService().insertMember(m);

		// 3. result 결과값에 따라서 사용자가 보게될 화면 지정
		if (result > 0) { // 삽입된 행의 갯수가 1개이상 -> 성공
			// 성공메세지 출력
			System.out.println("회원가입 성공");
		} else {// 삽입된 행의갯수가 0개 --> 실패
				// 실패메세지 출력
			System.out.println("회원가입 실패");

		}

	}

	/**
	 * 사용자의 회원 전체 조회 요청을 처리해주는 메서드
	 */
	public void selectAll() {

		// 결과값을 담을 변수
		// SELECT -> ResultSet -> ArrayList<Member>

		// ArrayList<Member> list = new ArrayList<>();
		ArrayList<Member> list = new MemberService().selectAll();

		// 조회결과가 있는지 없는지 판단 후 사용자가 보게될 view 화면 지정.
		if (list.isEmpty()) { // 텅빈 리스트 반환 -> 조회결과가 없다.
			new MemberView().displayNodata("조회결과가 없습니다.");
		} else {
			// 조회결과가 있을 경우 보게 될 화면
			new MemberView().displayList(list);
		}

	}

	/**
	 * 사용자의 아이디로 검색요청을 하는 메서드
	 * 
	 * @param userId : 사용자가 검색하고자하는 아이디
	 */
	public void selectByUserId(String userId) {
		// 결과값을 담을 변수
		// SELECT -> ResultSet -> Member
		Member member = new MemberService().selectByUserId(userId);
		if (member == null) { // 텅빈 리스트 반환 -> 조회결과가 없다.
			new MemberView().displayNodata(userId + "에 해당하는 검색결과가 없습니다.");
		} else {
			// 조회결과가 있을 경우 보게 될 화면
			System.out.println(member);
		}
	}

	public void selectByUserName(String keyword) {
		// 결과값을 담을 변수
		// SELECT -> ResultSet -> ArrayList<Member>

		// ArrayList<Member> list = new ArrayList<>();
		ArrayList<Member> list = new MemberService().selectByUserName(keyword);

		// 조회결과가 있는지 없는지 판단 후 사용자가 보게될 view 화면 지정.
		if (list.isEmpty()) { // 텅빈 리스트 반환 -> 조회결과가 없다.
			new MemberView().displayNodata("조회결과가 없습니다.");
		} else {
			// 조회결과가 있을 경우 보게 될 화면
			new MemberView().displayList(list);
		}
	}
	
	/**
	 * 사용자의 회원정보 변경요청을 처리해주는 메서드
	 * @param userId : 변경하고자하는 회원의 아이디(구분자)
	 * @param newPwd : 변경할 패스워드
	 * @param newPhone : 변경할 핸드폰번호
	 * @param newAddress : 변경할 주소
	 */
	public void updateMember(String userId, String newPwd, String newEmail, String newPhone, String newAddress) {
		//vo객체로 입력받은 값을 가공처리 해주기
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(newPwd);
		member.setEmail(newEmail);
		member.setPhone(newPhone);
		member.setAddress(newAddress);
		int result = new MemberService().updateMember(member);
		
		
		if (result > 0) { // 삽입된 행의 갯수가 1개이상 -> 성공			
			System.out.println("정보수정 성공");
		} else {// 삽입된 행의갯수가 0개 --> 실패
			System.out.println("정보수정 실패");
		}
	}
	
	/**
	 * 사용자가 회원탈퇴 요청시 처리해주는 메서드
	 * @param userId -> 사용자가 입력한 회원의 아이디값.
	 */
	public void deleteMember(String userId) {
		
		int result = new MemberService().deleteMember(userId);
		
		if (result > 0) { // 삽입된 행의 갯수가 1개이상 -> 성공			
			System.out.println("회원탈퇴 성공");
		} else {// 삽입된 행의갯수가 0개 --> 실패
			System.out.println("회원탈퇴 실패");
		}
		
	}
}
