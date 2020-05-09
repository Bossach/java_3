package lesson_2.NetworkClient.controller;

@FunctionalInterface
public interface AuthEvent {
    void authIsSuccessful(String nickname);
}
