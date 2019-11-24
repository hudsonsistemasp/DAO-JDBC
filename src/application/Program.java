package application;

import java.util.Date;

import model.entites.Department;
import model.entites.Seller;

public class Program {

	public static void main(String[] args) {
		Department dep = new Department(3, "Finanças");
		System.out.println(dep);
		
		Seller seller = new Seller(1, "REgis", "email@email.com", new Date(), 100000.0, dep);
		System.out.println(seller);
	}

}
