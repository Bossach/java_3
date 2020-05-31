package lesson_8.Task2.hospital;

import lesson_8.Task2.hospital.Interfaces.*;

public class Therapist implements IDoctor {

    private String name;

    public Therapist(String name) {
        this.name = name;
    }

    @Override
    public void doExamination(IPatient patient) {
        System.out.println("Терапевт " + name + " обследует пациента " + patient.getName() + ".");
    }

    @Override
    public void doTreatment(IPatient patient) {
        System.out.println("Терапевт " + name + " лечит пациента " + patient.getName() + ".");
    }

    @Override
    public String getReceipt(IPatient patient) {
        System.out.println(patient.getName() + " получил рецепт");
        return "Рецепт для " + patient.getName();
    }
}
