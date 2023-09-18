package org.jsp.userproductapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userproductapp.dao.ProductDao;
import org.jsp.userproductapp.dao.UserDao;
import org.jsp.userproductapp.dto.Product;
import org.jsp.userproductapp.dto.User;

public class UserProductController {
	static Scanner sc = new Scanner(System.in);
	static UserDao userDao = new UserDao();
	static ProductDao productDao = new ProductDao();

	public static void main(String[] args) {
		System.out.println("1:-Save User !!!");
		System.out.println("2:-Update User!!!");
		System.out.println("3:-Verify User By phone And password!!");
		System.out.println("4:-Verify user By email and Password!!");
		System.out.println("5:-Add Product for User !!!!");
		System.out.println("6:-Update Product by User !!!");
		System.out.println("7:-find Product By User Id!!!");
		System.out.println("8:-find Product By Brands!!!");
		System.out.println("9:-Delete User !!!!");
		System.out.println("10:-Delete Product!!!!");
		System.out.println("=====================================");
		System.out.println("Enter your Choice !!!!!");
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			saveUser();
			break;
		}
		case 2: {
			updateUser();
			break;
		}
		case 3: {
			verifyUserByphoneAndPass();
			break;
		}
		case 4: {
			verifyUserByEmailandPass();
			break;
		}
		case 5: {
			AddProduct();
			break;
		}
		case 6: {
			UpdateProduct();
			break;
		}
		case 7: {
			findProductBYUserId();
			break;
		}
		case 8: {
			findProductBYBreand();
			break;
		}
		case 9: {
			deleteUser();
			break;
		}
		case 10: {
			deleteProduct();
			break;
		}
		default: {
			System.err.println("You are Enter Wrrong Choice!!!!!");
		}
		}
	}

	public static void saveUser() {
		System.out.println("Enter User Name,Phone, Email,password!!!");
		User u = new User();
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		u = userDao.saveUser(u);
		System.err.println("User Saved with id no:-" + u.getId());
	}

	public static void updateUser() {
		System.out.println("Enter User Id To Update the User !!");
		int id = sc.nextInt();
		System.out.println("Enter User Name,Phone, Email,password!!!");
		User u = new User();
		u.setId(id);
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		u = userDao.updateUser(u);
		System.err.println("User Saved with id no:-" + u.getId());
	}

	public static void verifyUserByphoneAndPass() {
		System.out.println("Enter User Phone And Password!!!!");
		long phone = sc.nextLong();
		String password = sc.next();
		User u = new User();
		u = userDao.verfiyUserByPhoneandPassword(phone, password);
		if (u != null) {
			System.out.println("User Id:=" + u.getId());
			System.out.println("User NAme:=" + u.getName());
			System.out.println("User Phone:=" + u.getPhone());
			System.out.println("User Email:=" + u.getEmail());
			System.out.println("User Password:=" + u.getPassword());
			System.out.println("======================================");
		} else {
			System.err.println("Invalied phone and password!!!");
		}
	}

	public static void verifyUserByEmailandPass() {
		System.out.println("Enter User Email And Password!!!!");
		String email = sc.next();
		String password = sc.next();
		User u = new User();
		u = userDao.verfiyUserByEmailandPassword(email, password);
		if (u != null) {
			System.out.println("User Id:=" + u.getId());
			System.out.println("User NAme:=" + u.getName());
			System.out.println("User Phone:=" + u.getPhone());
			System.out.println("User Email:=" + u.getEmail());
			System.out.println("User Password:=" + u.getPassword());
			System.out.println("======================================");
		} else {
			System.err.println("Invalied Email and password!!!");
		}
	}

	public static void AddProduct() {
		System.out.println("Enter User Id To Add the Product!!");
		int user_id = sc.nextInt();
		System.out.println("Enter Product Name,Brand,category,cost,imageurl!!!!");
		Product p = new Product();
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setCost(sc.nextLong());
		p.setImageurl(sc.next());
		p = productDao.saveProduct(p, user_id);
		System.out.println("Product Are Added In user :" + user_id);
	}

	public static void UpdateProduct() {
		System.out.println("Enter User Id To Add the Product!!");
		int user_id = sc.nextInt();
		System.out.println("Enter Product Id To Update the product!!1");
		int id = sc.nextInt();
		System.out.println("Enter Product Name,Brand,category,cost,imageurl!!!!");
		Product p = new Product();
		p.setId(id);
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setCost(sc.nextLong());
		p.setImageurl(sc.next());
		p = productDao.updateProduct(p, id);
		System.out.println("Product Are Added In user :" + user_id);
	}

	public static void findProductBYUserId() {
		System.out.println("Enter User Id To Find The Product!!!");
		int id = sc.nextInt();
		List<Product> ps;
		ps = productDao.findProductByUser(id);
		if (ps != null) {
			for (Product p : ps) {
				System.out.println("Product Id:=" + p.getId());
				System.out.println("Product NAme:=" + p.getName());
				System.out.println("Product Brand:=" + p.getBrand());
				System.out.println("Product Category:=" + p.getCategory());
				System.out.println("Produtc Cost:=" + p.getCost());
				System.out.println("Product Imageurl" + p.getImageurl());
				System.out.println("======================================");
			}
		}
	}

	public static void findProductBYBreand() {
		System.out.println("Enter Brand To Find the Product!!!");
		String brand = sc.next();
		List<Product> ps;
		ps = productDao.findProductByBrand(brand);
		if (ps != null) {
			for (Product p : ps) {
				System.out.println("Product Id:=" + p.getId());
				System.out.println("Product NAme:=" + p.getName());
				System.out.println("Product Brand:=" + p.getBrand());
				System.out.println("Product Category:=" + p.getCategory());
				System.out.println("Produtc Cost:=" + p.getCost());
				System.out.println("Product Imageurl" + p.getImageurl());
				System.out.println("======================================");
			}
		}
	}
	
	public static void deleteUser() {
		System.out.println("Enter user Id to Delete User!!!");
		int id=sc.nextInt();
		boolean delete=userDao.deleteUser(id);
		if(delete) {
			System.err.println("User Deletet for Id "+id);
		}else {
			System.out.println("User Not Delete Something Is Wrrong!!!!");
		}
	}
	public static void deleteProduct() {
		System.out.println("Enter USer Id !!!");
		int user_id=sc.nextInt();
		System.out.println("enter Product Id To Delete!! ");
		int id=sc.nextInt();
		boolean delete=productDao.deleteproduct(user_id, id);
		if(delete) {
			System.out.println("Product Are Deletet");
		}else {
			System.err.println("Product Not delete something is Wrrong!!!");
		}
	}

}
