package com.farmec.project.domain.type.account.name;

public class FullName {
    
    private String value;
    
    public FullName() {}

    public FullName(FirstName firstName, LastName lastName) {
        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(firstName.toString());
        nameBuilder.append(" ");
        nameBuilder.append(lastName.toString());

        value = nameBuilder.toString();
    }

    @Override
    public String toString() {
        return value;
    }
}
