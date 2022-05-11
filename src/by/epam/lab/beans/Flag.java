package by.epam.lab.beans;

public class Flag {

    private boolean isProd = true;

    public boolean isProducing() {
        return isProd;
    }

    public void stopProducing() {
        isProd = false;
    }
}
