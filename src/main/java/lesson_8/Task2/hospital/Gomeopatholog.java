package lesson_8.Task2.hospital;

import lesson_8.Task2.hospital.Interfaces.IDoctor;
import lesson_8.Task2.hospital.Interfaces.IPatient;

public class Gomeopatholog implements IDoctor {
    @Override
    public void doExamination(IPatient patient) {
        System.out.println("У вас болезнь");
    }

    @Override
    public void doTreatment(IPatient patient) {
        System.out.println("Купи лекарство");
    }

    @Override
    public String getReceipt(IPatient patient) {
        return "Водичка";
    }
}
