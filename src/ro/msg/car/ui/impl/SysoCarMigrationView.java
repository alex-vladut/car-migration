package ro.msg.car.ui.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ro.msg.car.ui.ICarMigrationView;
import ro.msg.car.ui.controller.ICarMigrationController;

public class SysoCarMigrationView implements ICarMigrationView {

	private final ICarMigrationController controller;
	
	public SysoCarMigrationView(final ICarMigrationController controller) {
		this.controller = controller;
	}
	
	@Override
	public void start() {
		final List<String[]> lines = readAllInputEntries();
		
		controller.saveInputEntries(lines);
		
		final List<String[]> savedEntries = controller.findAllMigratedEntries();
		printAllEntries(savedEntries);
	}
	
	private List<String[]> readAllInputEntries() {
		final List<String[]> result = new ArrayList<String[]>();

		final Scanner scanner = new Scanner(System.in);
		System.out.println("Enter separator: ");
		final String separator = scanner.nextLine().trim();
		System.out.println("Selected separator: " + separator);
		System.out.println("Enter input text: (Make, Model, Fabrication year, Car type, Specification details)");
		String line;
		while (!(line = scanner.nextLine()).isEmpty()) {
			final String[] splits = line.split(separator);
			result.add(splits);
		}
		scanner.close();

		return result;
	}
	
	private void printAllEntries(final List<String[]> savedEntries) {
		System.out.println("All entries from database: ");
		System.out.println("---------------------------");
		for(final String[] entry : savedEntries) {
			System.out.println(entry[0] + ", " + entry[1] + ", " + 
						entry[2] + ", " + entry[3] + ", " + entry[4] + ", " + entry[5]);
		}
		System.out.println("---------------------------");
	}

}
