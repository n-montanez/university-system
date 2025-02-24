package model.teacher;

public class PartTimeTeacher extends Teacher {
    private int activeHours;

    public PartTimeTeacher(String name, double baseSalary, int activeHours) {
        super(name, baseSalary);
        this.activeHours = activeHours;
    }

    public int getActiveHours() {
        return activeHours;
    }

    public void setActiveHours(int activeHours) {
        this.activeHours = activeHours;
    }

    @Override
    public double calculateSalary() {
        return Math.round((this.getBaseSalary() / 30 / 5 / 8) * this.getActiveHours() * 4);
    }
}
