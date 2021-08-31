package com.georgioskachrimanis.javacourse;

import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Branch> branches;

    // Constructors
    public Bank(String name) {
        this.name = name;
        this.branches = new ArrayList<Branch>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    //Methods
    public boolean addBranch(String branchName) {

        if (findBranch(branchName) == null) {
            this.branches.add(new Branch(branchName));
            System.out.println("Branch, " + branchName + " has been added.");
            return true;
        }

        System.out.println("Error!!!\nBranch, " + branchName + " already exists.");
        return false;
    }

    public boolean addCustomer(String branchName, String customerName, double initialAmount) {

        Branch branch = findBranch(branchName);

        if (branch != null) {
            return branch.newCustomer(customerName, initialAmount);
        }
        return false;
    }

    public boolean addCustomerTransaction(String branchName, String customerName, double amount) {

        Branch branch = findBranch(branchName);

        if (branch != null) {
            return branch.addCustomerTransaction(customerName, amount);
        }

        return false;
    }

    private Branch findBranch(String branchName) {

        for (int i = 0; i < branches.size(); i++) {
            Branch checkedBranch = this.branches.get(i);
            if (checkedBranch.getName().equals(branchName)) {
                return checkedBranch;
            }
        }
        return null;
    }

    public boolean listOfCustomers(String branchName, boolean showTransactions) {
        Branch checkedBranch = findBranch(branchName);
        if (checkedBranch != null) {

            System.out.println("Customer detail for branch: " + checkedBranch.getName());

            ArrayList<Customer> branchCustomers = checkedBranch.getCustomers();

            for (int i = 0; i < branchCustomers.size(); i++) {
                System.out.println("Customer: " + branchCustomers.get(i).getName() + "[" + (i+1) + "]");

                if (showTransactions) {
                    System.out.println("Transactions");

                    ArrayList<Double> transactions = branchCustomers.get(i).getTransactions();
                    for (int j = 0; j < transactions.size(); j++) {
                        System.out.println("[" + (j + 1) + "]  Amount " + transactions.get(j));
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
