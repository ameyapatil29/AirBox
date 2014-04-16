package com.AirBox.rest;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbConnection db = new DbConnection();
		User user = new User();
		user.setFirstName("Chetan");
		user.setUserName("ChetanBhau");
		user.setPassword("cb");
		user.setLastName("Burande");
		//db.insertUser(user);
		if(db.loginCheck("ChetanBhau", "cb"))
			System.out.println("Access granted");
		else
			System.out.println("Access denied");
		File file =new File();
		
		String str = "chetan bro";

		for (int i = str.length()-1; i >=0 ; i--) {

		System.out.print(str.charAt(i));

		}
		
	}

}
