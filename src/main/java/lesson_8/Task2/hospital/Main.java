package lesson_8.Task2.hospital;

import lesson_8.Task2.hospital.Interfaces.IHospital;
import lesson_8.Task2.hospital.Interfaces.IPatient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("lesson_8.Task2.hospital");

        IHospital hospital = context.getBean(IHospital.class);

        IPatient patient = context.getBean(ManPatient.class);

        patient.gotoHospital(hospital, Therapist.class);

    }
}
