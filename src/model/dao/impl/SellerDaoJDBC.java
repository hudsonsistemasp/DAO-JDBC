package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entites.Department;
import model.entites.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	//Precisamos de uma conexão e faremos uma injeção de dependência no construtor
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
			/*Pela regra de negócio, todo Seller tem um Department. 
			 Logo, na criação da classe Seller temos uma associação com a classe Department
			 e essa associação foi feita no construtor da classe Seller passando um objeto Department.
			 Então temos que criar os dois objetos aqui */
			
			//Passar para o placeHolder'?' o parâmetro para realizar o filtro
			pst.setInt(1, id);//id do parâmetro da assinatura
			rs = pst.executeQuery();//ResultSet vai receber em formato de tabela, tipo resposta no banco de dados
			
			//Aqui eu mexo o ponteiro da posição 0 para a 1 e identifico se veio algum registro do banco, caso contrário retorno null
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

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE DepartmentId = ? " + 
					"ORDER BY Name");
			
			pst.setInt(1, department.getId());//Setando o valor no parâmetro da consulta
			rs = pst.executeQuery();
			
			/*Como retornará uma lista temos que percorrer com a estrutura de repetição para ver se retornou valores.
			 * rs.next() faz o ponteiro do resultset mover da posição 0 para a 1 e retorna TRUE se tiver registros à partir de então*/
			List<Seller> listSeller = new ArrayList<>();//Para guardarmos o retorno dos dados do banco
			
			/*Criar um map para controlar a criação do objeto Department, pois temos vários Seller vinculados a um 
			  objeto Dep e deixaremos uma referência de memória: 1 .* sellers    */ 
			Map<Integer,Department> mapDep = new HashMap<>();
			
			while(rs.next()) {
				//Vamos controlar pelo código do departamento
				Department dep = mapDep.get(rs.getInt("DepartmentId"));//get traz o valor dessa chave, se não tem vem null
				if (dep == null) {
					dep = instantiateDepartment(rs);
					mapDep.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller seller = instantiateSeller(rs, dep);
				listSeller.add(seller);
			}
			return listSeller;
			
		}
		catch(SQLException e) {
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

}





















