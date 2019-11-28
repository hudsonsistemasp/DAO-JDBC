package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entites.Department;

public class Program {

	public static void main(String[] args) {
	
		/*Department dep = new Department(3, "Finanças");
		System.out.println(dep);
		
		//Associando o objeto dep ao Seller, por que na regra todo Seller tem um departament
		Seller seller = new Seller(1, "REgis", "email@email.com", new Date(), 100000.0, dep);
		System.out.println(seller);*/

		//Instanciando um objeto da Factory. É uma das formas de fazer a injeção de dependência sem mostrar a implementação
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		/*
		System.out.println("------Seller findById --------");
		Seller seller2 = sellerDao.findById(4);
		System.out.println(seller2);
		*/
		
		/*
		System.out.println("\n------Seller findByDepartment --------");
		Department department = new Department(1, null);
		List<Seller> listSeller = sellerDao.findByDepartment(department);

		for(Seller obj : listSeller) {
			System.out.println(obj);
		}
		
		System.out.println("\n------Seller findAll --------");
		List<Seller> listAllSeller = sellerDao.findAll();
		for(Seller obj : listAllSeller) {
			System.out.println(obj);
		}
	*/
		/*
		System.out.println("\n------Seller Insert --------");
		Seller newSeller = new Seller(0, "Rouls", "Rousl@gmail.com", new Date(), 5000.0, new Department(6,"D2"));
		sellerDao.insert(newSeller);
		System.out.println(newSeller.getId());
		*/
		
		/*
		System.out.println("\n------Seller Update --------");
		newSeller.setName("Browns");
		sellerDao.update(newSeller);
		*/
		
		/*
		System.out.println("\n------Seller Delete --------");
		sellerDao.deleteById(22);
		*/
		
		
		//Instanciando um objeto da Factory. É uma das formas de fazer a injeção de dependência sem mostrar a implementação
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		System.out.println();
		
		/*
		System.out.println("\n------Department Insert --------");
		Department depCreate = new Department(10, "TESTE");
		departmentDao.insert(depCreate);
		*/
		
		/*
		System.out.println("\n------Department Update --------");
		System.out.println("Antigo Department: " + depCreate.getName());
		depCreate.setName("RH");
		departmentDao.update(depCreate);
		*/
		
		/*
		System.out.println("\n------Department DeleteById --------");
		departmentDao.deleteById(10);
		*/
		
		/*
		System.out.println("\n------Department findById --------");
		Department dep = departmentDao.findById(4);
		System.out.println(dep);
		*/
		/*
		System.out.println("\n------Department findAll --------");
		List<Department> listDep = departmentDao.findAll();
		for(Department obj : listDep) {
			System.out.println(obj);
		}
		*/
		
	}

	
	
}













