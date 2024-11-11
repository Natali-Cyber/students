package org.example.students;

import org.example.students.exceptions.RecordNotFoundException;

import java.util.Collection;
import java.util.Set;

// Интерфейс определяет методы для управления оценками студентов
public interface Examination {

    // Метод для получения оценки студента по имени
    // Вызывает RecordNotFoundException, если запись не найдена
    void addRecord(Record score);

    // Метод для получения оценки студента по имени и предмету
    Record getRecord(String name) throws RecordNotFoundException;

    // Метод для вычисления и возврата среднего балла по указанному предмету
    double getAverageForSubject(String subject);

    // Метод для получения набора имен студентов, сделавших несколько попыток сдачи экзамена
    // используя коллекцию `Set` для уникальности имен.
    Set<String> multipleSubmissionsStudentNames();

    // Метод для получения имен последних пяти студентов с отличными оценками по любому предмету
    Set<String> lastFiveStudentsWithExcellentMarkOnAnySubject();

    // Метод для получения коллекции всех оценок, добавленных в систему
    Collection<Record> getAllScores();
}
