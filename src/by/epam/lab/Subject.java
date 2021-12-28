package by.epam.lab;

public class Subject {
    private String name;
    private Material material;
    private double volume;

    public Subject() {

    }

    public Subject(String name, Material material, double volume) {
        this.name = name;
        this.material = material;
        this.volume = volume;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material1) {
        this.material = material1;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getMass() {
        return material.getDensity() * volume;
    }

    @Override
    public String toString() {
        return name + ";" + material + ";" + volume + ";" + getMass();
    }
}
