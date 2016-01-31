import java.sql.SQLException;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class TestDatabase {
	public static void main(String[] args){
		System.out.println("Running database test");
		
		Database db = new Database();
		
		
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		db.addPerson(new Person("Julius","builder",AgeCategory.adult,EmploymentCategory.selfEmployed,"777",true,Gender.male));
		db.addPerson(new Person("Julius 2 x","builder",AgeCategory.adult,EmploymentCategory.selfEmployed,"777",true,Gender.male));
		db.addPerson(new Person("Julius 3 x","builder",AgeCategory.adult,EmploymentCategory.selfEmployed,"777",true,Gender.male));
		db.addPerson(new Person("Julius 4 x","builder",AgeCategory.adult,EmploymentCategory.selfEmployed,"777",true,Gender.male));
		
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disconnect();
	}
}
