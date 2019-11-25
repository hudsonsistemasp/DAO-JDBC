package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entites.Seller;

public class Program {

	public static void main(String[] args) {
	
		/*Department dep = new Department(3, "Finanças");
		System.out.println(dep);
		
		//Associando o objeto dep ao Seller, por que na regra todo Seller tem um departament
		Seller seller = new Seller(1, "REgis", "email@email.com", new Date(), 100000.0, dep);
		System.out.println(seller);*/

		System.out.println("------Seller findById --------");
		//Instanciando um objeto da Factory. É uma das formas de fazer a injeção de dependência sem mostrar a implementação
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller2 = sellerDao.findById(4);
		System.out.println(seller2);
		
		

	}

	
}
