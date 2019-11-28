package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entites.Department;

public class DepartmentDaoJDBC implements DepartmentDao{

	private Connection conn;

	public DepartmentDaoJDBC(Connection connection) {
		this.conn = connection;
	}

	@Override
	public void insert(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO "
					+ "department "
					+ "(Id, Name) "
					+ "VALUES (? , ?)",Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, department.getId());
			st.setString(2, department.getName());
			
			Integer depInserted = st.executeUpdate();
			if (depInserted > 0) {
				rs = st.getGeneratedKeys();
				while(rs.next()) {
					System.out.println("Department created! Id: " + rs.getInt(1));
				}
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void update(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("UPDATE department "
					+ "SET Id = ?, Name = ? "
					+ "WHERE Id = ? ", Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, department.getId());
			st.setString(2, department.getName());
			conn.commit();
			int qtdeUpdated = st.executeUpdate();
			
			if(qtdeUpdated > 0) {
				rs = st.getGeneratedKeys();
				System.out.println("Department id " + rs.getInt(1) + " atualizado!");
				System.out.println("");
			}
			
		} 
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException(e.getMessage());
			}
			catch(SQLException e1) {
				System.out.println("Danger: Rollback falhou! Causa do falha: " + e1.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("DELETE FROM department WHERE id = ? ", Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, id);
			
			
			int rowDeleted = pst.executeUpdate();
			
			if (rowDeleted > 0 ) {
				System.out.println("Código deleted! ");
			}
			else {
				System.out.println("Nenhum dado deletado!");
			}
			
		}
		catch(SQLException ex) {
			throw new DbException(ex.getMessage());
		}
		finally {
			DB.closeStatement(pst);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
		pst = conn.prepareStatement("SELECT department.* " + 
				"FROM department " + 
				"WHERE Id = ?");
		pst.setInt(1, id);
		
		rs = pst.executeQuery();
		
		if(rs.next()) {
			Department department = new Department(id, rs.getString("Name"));
			return department;
		}
		
		}
		catch(SQLException err) {
			throw new DbException(err.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
		pst = conn.prepareStatement("SELECT department.* " + 
				"FROM department "
				+ "ORDER BY department.Id");
		rs = pst.executeQuery();
		List<Department> listDep = new ArrayList<>();
		
		while(rs.next()) {
			Department dep = new Department(rs.getInt("Id"), rs.getString("Name"));
			listDep.add(dep);
		}
		return listDep;
		}
		catch(SQLException err) {
			System.out.println("Erro na busca dos dados! Causa: " + err.getMessage());
		}
		finally {
			DB.closeStatement(pst);
			DB.closeResultSet(rs);
		}
		return null;
	}

}
