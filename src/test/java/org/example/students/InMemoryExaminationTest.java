package org.example.students;

import org.example.students.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


class InMemoryExaminationTest {

    private  Examination examination; // Объект для тестируемого класса

    @BeforeEach
        // Метод, который будет выполнен перед каждым тестом
    void setUp() {
        examination = new IExamination(); // Инициализация объекта
    }


    @Test // Тест для метода addRecord
    void addRecord() throws RecordNotFoundException {
        Record record = new Record("Иванов Иван", "Математика", 4); // Создаем запись
        examination.addRecord(record); // Добавляем запись в систему
        Record actual = examination.getRecord(record.name()); // Получаем запись по имени

        Assertions.assertEquals(actual, record); // Проверяем, что обе записи равны
    }

    @Test // Тест для метода getRecord
    void getRecord() {
        // Проверяем, что исключение выбрасывается
        Assertions.assertThrows(RecordNotFoundException.class, () -> examination.getRecord("Сидоров Вадим"));
    }

    @Test // Тест для метода getAverageForSubject
    void getAverageForSubject() {
        examination.addRecord(new Record("Петров Петр", "Физика", 3)); // Добавляем записи
        examination.addRecord(new Record("Смирнов Сергей", "Химия", 4));
        double average = examination.getAverageForSubject("Физика"); // Получаем среднее значение
        Assertions.assertTrue(Double.compare(average, 3.0) == 0); // Проверяем, что среднее значение верное
    }

    @Test // Тест для метода multipleSubmissionsStudentNames
    void multipleSubmissionsStudentNames() {
        // Добавляем записи
        examination.addRecord(new Record("Иванов Иван", "Математика", 4));
        examination.addRecord(new Record("Петров Петр", "Физика", 3));
        examination.addRecord(new Record("Иванов Иван", "Русский язык", 3));
        examination.addRecord(new Record("Иванов Иван", "Русский язык", 4));
        examination.addRecord(new Record("Смирнов Сергей", "Химия", 4));

        Set<String> expected = new HashSet<>(Collections.singletonList("Иванов Иван")); // Ожидаемое множество студентов
        Set<String> actual = examination.multipleSubmissionsStudentNames(); // Получаем множество студентов с несколькими оценками
        Assertions.assertEquals(expected, actual); // Проверяем совпадение с ожидаемым
    }

    @Test // Тест для метода lastFiveStudentsWithExcellentMarkOnAnySubject
    void lastFiveStudentsWithExcellentMarkOnAnySubject() {
        // Добавляем записи
        examination.addRecord(new Record("Иванов Иван", "Математика", 5));
        examination.addRecord(new Record("Петров Петр", "Математика", 4));
        examination.addRecord(new Record("Сидоров Сидор", "Физика", 5));
        examination.addRecord(new Record("Смирнов Сергей", "Химия", 5));
        examination.addRecord(new Record("Федоров Федор", "Литература", 3));
        examination.addRecord(new Record("Козлов Козла", "История", 5));
        examination.addRecord(new Record("Лебедева Лариса", "Химия", 5));

        // Получаем студентов с отличными оценками
        Set<String> result = examination.lastFiveStudentsWithExcellentMarkOnAnySubject();

        // Ожидаемое множество студентов
        Set<String> expected = new HashSet<>(Arrays.asList("Иванов Иван", "Сидоров Сидор", "Смирнов Сергей",
                "Козлов Козла", "Лебедева Лариса"));

        Assertions.assertEquals(expected, result); // Проверяем совпадение с ожидаемым
    }

    @Test // Тест для метода getAllScores
    void getAllScores() {
        // Добавляем несколько записей
        examination.addRecord(new Record("Иванов Иван", "Математика", 4));
        examination.addRecord(new Record("Петров Петр", "Физика", 3));
        examination.addRecord(new Record("Сидоров Сидор", "Химия", 5));

        // Получаем все записи
        Collection<Record> scores = examination.getAllScores();

        // Ожидаемое множество записей
        Set<Record> expectedRecords = new HashSet<>(Arrays.asList(
                new Record("Иванов Иван", "Математика", 4),
                new Record("Петров Петр", "Физика", 3),
                new Record("Сидоров Сидор", "Химия", 5)
        ));

        // Проверяем, что возвращаемое множество совпадает с ожидаемым
        Assertions.assertEquals(expectedRecords.size(), scores.size()); // Сравниваем размеры
        for (Record record : expectedRecords) {
            Assertions.assertTrue(scores.contains(record)); // Проверяем, что каждую ожидаемую запись можно найти в результатах
        }
    }
}