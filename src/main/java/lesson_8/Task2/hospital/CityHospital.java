package lesson_8.Task2.hospital;

import lesson_8.Task2.hospital.Interfaces.*;

import java.util.Iterator;
import java.util.Set;

public class CityHospital implements IHospital {
    private IReception reception;
    private Set<IDoctor> doctorList;
    private Set<ICabinet> cabinets;

    public CityHospital(Set<IDoctor> doctorList, Set<ICabinet> cabinets) {
        this.doctorList = doctorList;
        this.cabinets = cabinets;
        fillCabinetsWithDoctors();
        reception = new Reception(this);
    }

    @Override
    public IReception getReception() {
        return reception;
    }

    public Set<ICabinet> getCabinets() {
        return cabinets;
    }


    private void fillCabinetsWithDoctors() {
        Iterator<IDoctor> doctorIterator = doctorList.iterator();

        for (ICabinet cabinet : cabinets) {
            if (doctorIterator.hasNext()) {
                cabinet.setDoctor(doctorIterator.next());
            } else break;
        }
    }
}
