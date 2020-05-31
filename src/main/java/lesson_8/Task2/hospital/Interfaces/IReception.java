package lesson_8.Task2.hospital.Interfaces;


public interface IReception {
    ICabinet getCabinet(Class<? extends IDoctor> doctor);
}
