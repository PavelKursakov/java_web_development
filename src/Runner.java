import by.epam.lab.Material;
import by.epam.lab.Subject;

public class Runner {
    public static void main(String[] args) {
        Subject subject = new Subject("wire", new Material("steel",7850.0),0.03);
        System.out.println(subject);
        subject.setMaterial(new Material("cooper",8500.0));
        System.out.println("The wire mass is: " + subject.getMass());
    }
}
