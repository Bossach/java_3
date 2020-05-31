package lesson_8.Task2.hospital;

import lesson_8.Task2.hospital.Interfaces.*;

import java.util.Set;

public class Reception implements IReception {

    private IHospital hospital;

    public Reception(IHospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public ICabinet getCabinet(Class<? extends IDoctor> doctorType) {
        Set<ICabinet> cabinets = hospital.getCabinets();
        for (ICabinet cabinet : cabinets) {
            if (cabinet.getDoctor().getClass().equals(doctorType)) {
                return cabinet;
            }
        }
        return null;
    }
}
