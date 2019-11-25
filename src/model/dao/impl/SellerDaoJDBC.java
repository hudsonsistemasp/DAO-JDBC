package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	//Precisamos de uma conex�o e faremos uma inje��o de depend�ncia no construtor
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(
					"select seller.*, department.Name as DepName " + 
					"from seller inner join department " + 
					"on seller.DepartmentId = department.Id " + 
					"where seller.Id = ?");
			/*Pela regra de neg�cio, todo Seller tem um Department. 
			 Logo, na cria��o da classe Seller temos uma associa��o com a classe Department
			 e essa associa��o foi feita no construtor da classe Seller passando um objeto Department.
			 Ent�o temos que criar os dois objetos aqui */
			
			//Passar para o placeHolder'?' o par�metro para realizar o filtro
			pst.setInt(1, id);//id do par�metro da assinatura
			rs = pst.executeQuery();//ResultSet vai receber em formato de tabela, tipo resposta no banco de dados
			
			//Aqui eu mexo o ponteiro da posi��o 0 para a 1 e identifico se veio algum registro do banco, caso contr�rio retorno null
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs, dep);
				return seller;
			}
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException{
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
