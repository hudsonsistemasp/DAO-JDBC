package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;

public class Program {

	public static void main(String[] args) {
		Department dep = new Department(3, "Finan�as");
		System.out.println(dep);
		
		Seller seller = new Seller(1, "REgis", "email@email.com", new Date(), 100000.0, dep);
		System.out.println(seller);
	}

	//Instanciando um objeto da Factory. � uma das formas de fazer a inje��o de depend�ncia sem mostrar a implementa��o
	SellerDao sellerDao = DaoFactory.createSellerDao();
	
	
}
