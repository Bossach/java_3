package lesson_8.Task2.hospital;

import lesson_8.Task2.hospital.Interfaces.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ManPatient implements IPatient {
    private String name;

    public ManPatient(@Value("Олег") String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void gotoHospital(IHospital hospital, Class<? extends IDoctor> doctor) {
        IReception reception = hospital.getReception();
        ICabinet cabinet = reception.getCabinet(doctor);
        IDoctor exactlyDoctor = cabinet.getDoctor();

        exactlyDoctor.doExamination(this);
        exactlyDoctor.doTreatment(this);
        String myReceipt = exactlyDoctor.getReceipt(this);
        System.out.println("Текст рецепта: " + myReceipt);
    }
}
