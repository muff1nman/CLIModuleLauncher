package com.austindiviness.cltest;

import java.rmi.RemoteException;

import edu.mines.acmX.exhibit.module_management.modules.CommandlineModule;

public class Launch extends CommandlineModule {
	char choice;
	boolean canRun = true;

	public Launch() {
		super();
		choice = ' ';
	}


	@Override
	public void run() {
		canRun = true;
		try {
			while (canRun) {
				printMenu();
				getChoice();
				switch(choice) {
				case 'L': 
					listModules();
					break;
				case 'S':
					setModule();
//					break;
					throw new RuntimeException();

				case 'R':
					runModule();
					break;
				case 'Q':
					System.out.println("Goodbye!");
					canRun = false;
					break;
				default:
					System.out.println("Invalid option");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("PANIC! ABORTING!");
			canRun = false;
			throw new RuntimeException();
		}

	}

	private void listModules() throws Exception {
		String[] modules = getAllAvailableModules();
		for(String name: modules) {
			System.out.println(name);
		}
	}

	private void setModule() throws Exception {
		String[] modules = getAllAvailableModules();
		for (int i = 0; i < modules.length; ++i) {
			System.out.println(i + ". " + modules[i]);
		}
		
		int selection = nextInt();
		if (selection >= 0 && selection < modules.length) {
			boolean wasSet = setNextModule(modules[selection]);
			if (wasSet) {
				System.out.println("Module set!");
			}
			else {
				System.out.println("Module was rejected :(");
			}
		}
		else {
			System.out.println("Invalid Selection");
		}
		
	}

	private void runModule() throws Exception {
		setModule();
		System.out.println("Goodbye!");
		canRun = false;
	}

	public void printMenu() {
		System.out.println("Options:");
		System.out.println("L  List all available modules");
		System.out.println("S  Set next module to run");
		System.out.println("R  Run a module");
		System.out.println("Q  Quit this module");
	}

	public void getChoice() throws RemoteException {
		choice = next().toUpperCase().charAt(0);
	}
}
