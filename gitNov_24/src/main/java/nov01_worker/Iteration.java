package nov01_worker;

import java.util.ArrayList;

// 자바의 반복문 : for, while, do-while
// +) 향상된 for
// 향상된 for의 적용분야 : 배열을 반복, 컬렉션 프레임워크의 반복

public class Iteration {
	public static void main(String[] args) {
		
		// ------ 배열 반복처리------	
		int[] a = {2,4,6,8,10,12,14};		
		
		//기존 for 문
		for(int i=0; i<a.length; i++) {
			System.out.println(a[i]);
		}		
		//향상된 for 문
		for(int value : a) {
			System.out.println(value);
		}
		
		// ====== 컬렉션 프레임워크 반복처리 ======
		NameAddress na1 = new NameAddress("홍길동", "서울");
		NameAddress na2 = new NameAddress("신길동", "인천");
		NameAddress na3 = new NameAddress("김길동", "경기");
		
		ArrayList<NameAddress> al = new ArrayList<NameAddress>();
		al.add(na1); al.add(na2); al.add(na3);
		
		for(NameAddress na : al) {
			System.out.println(na.name+", "+na.address);
		}
	}
}

class NameAddress {
	NameAddress(String name, String addr) {	this.name = name; address = addr; }
	String name; String address;
}
