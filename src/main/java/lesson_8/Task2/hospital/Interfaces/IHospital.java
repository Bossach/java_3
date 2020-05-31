package lesson_8.Task2.hospital.Interfaces;

import java.util.Set;

public interface IHospital {
    IReception getReception();

    Set<ICabinet> getCabinets();
}
