package model.teacher;

public class FullTimeTeacher extends Teacher{
    private int experienceYears;

    public FullTimeTeacher(String name, double baseSalary) {
        super(name, baseSalary);
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public double calculateSalary() {
        return this.getBaseSalary() * (1 + 0.1 * this.experienceYears);
    }
}
