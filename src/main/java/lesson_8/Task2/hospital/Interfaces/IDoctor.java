package lesson_8.Task2.hospital.Interfaces;

public interface IDoctor {
    void doExamination(IPatient patient);

    void doTreatment(IPatient patient);

    String getReceipt(IPatient patient);
}
