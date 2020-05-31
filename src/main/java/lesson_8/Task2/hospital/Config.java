package lesson_8.Task2.hospital;

import lesson_8.Task2.hospital.Interfaces.ICabinet;
import lesson_8.Task2.hospital.Interfaces.IDoctor;
import lesson_8.Task2.hospital.Interfaces.IHospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class Config {

    @Bean
    public IHospital getHospital() {
        Set<ICabinet> cabinets = new HashSet<>();
        cabinets.add(new Cabinet(1));
        cabinets.add(new Cabinet(2));
        cabinets.add(new Cabinet(3));

        Set<IDoctor> doctors = new HashSet<>();
        doctors.add(new Therapist("Иванов"));
        doctors.add(new Therapist("Петров"));

        return new CityHospital(doctors, cabinets);
    }
}
