package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	/* Padrão de projeto: Factory method - Esse padrão define uma interface para a criaçãode um objeto, 
	 * deixando que as subclasses fiquem responsáveis por decidir qual classe a instanciar*/
	
	/* Dessa forma, a minha classe DaoFactory vai expor um método que retorna o tipo da interface SellerDao, 
	  mas internamente vai instanciar uma implementação dessa interface. 
	  É um Maçete para não expor a implementação, deixar somente a interface */
	
	/*No programa principal, para chamar os métodos da interface, instancio um objeto do tipo DAO(que está na camada DAO),
	  recebendo um objeto da fábrica que contém o método que aponta para a ação desejada, ex: 
	                SellerDao sellerDao = DaoFactory.createSellerDao();
	   Assim, o programa não conhece a implementação, somente a interface. */
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
	
	
}
