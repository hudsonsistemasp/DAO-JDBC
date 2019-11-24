package model.dao;

import java.util.List;

import model.entites.Department;
import model.entites.Seller;

public interface DepartmentDao {
	void insert(Department department);
	void update(Department department);
	void delete(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
}
