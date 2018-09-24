package ro.msg.car.ui.controller;

import java.util.List;

public interface ICarMigrationController {

	void saveInputEntries(List<String[]> lines);
	
	List<String[]> findAllMigratedEntries();
}
