package by.epam.lub.beans;

import by.epam.lub.Constants;
import by.epam.lub.enums.RoundMethod;

public class Service implements Priceable {
    private String serviceName;
    private Byn serviceCost;
    private int numberOfUsers;

    public Service() {
    }

    public Service(String serviceName, Byn serviceCost, int numberOfUsers) {
        this.serviceName = serviceName;
        this.serviceCost = serviceCost;
        this.numberOfUsers = numberOfUsers;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Byn getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Byn serviceCost) {
        this.serviceCost = serviceCost;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    @Override
    public Byn getPrice() {
        return serviceCost.mul(1.0 / numberOfUsers, RoundMethod.CEIL, 0);
    }

    @Override
    public String toString() {
        return serviceName + Constants.DELIMITER + serviceCost + Constants.DELIMITER +
                numberOfUsers + Constants.DELIMITER + getPrice();
    }
}
