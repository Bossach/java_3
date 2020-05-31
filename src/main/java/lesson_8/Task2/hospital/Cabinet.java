package lesson_8.Task2.hospital;

import lesson_8.Task2.hospital.Interfaces.*;

public class Cabinet implements ICabinet {

    IDoctor doctor;
    int number;

    public Cabinet(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setDoctor(IDoctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public IDoctor getDoctor() {
        return doctor;
    }
}
