package lesson_8.Task2.hospital.Interfaces;

public interface IPatient {

    String getName();

    void gotoHospital(IHospital hospital, Class<? extends IDoctor> doctor);
}
