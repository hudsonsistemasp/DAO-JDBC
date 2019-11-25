package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	/* Padr�o de projeto: Factory method - Esse padr�o define uma interface para a cria��ode um objeto, 
	 * deixando que as subclasses fiquem respons�veis por decidir qual classe a instanciar*/
	
	/* Dessa forma, a minha classe DaoFactory vai expor um m�todo que retorna o tipo da interface SellerDao, 
	  mas internamente vai instanciar uma implementa��o dessa interface. 
	  � um Ma�ete para n�o expor a implementa��o, deixar somente a interface */
	
	/*No programa principal, para chamar os m�todos da interface, instancio um objeto do tipo DAO(que est� na camada DAO),
	  recebendo um objeto da f�brica que cont�m o m�todo que aponta para a a��o desejada, ex: 
	                SellerDao sellerDao = DaoFactory.createSellerDao();
	   Assim, o programa n�o conhece a implementa��o, somente a interface. */
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}
	
	
}
