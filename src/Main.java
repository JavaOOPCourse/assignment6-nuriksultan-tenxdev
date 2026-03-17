import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        students.put("S001", new Student("Alice", 3.8, 20));
        students.put("S002", new Student("Bob", 3.5, 21));
        students.put("S003", new Student("Charlie", 3.9, 19));
        students.put("S004", new Student("David", 3.5, 22));
        students.put("S005", new Student("Eve", 3.7, 20));
        students.put("S006", new Student("Frank", 3.8, 22)); // Добавим шестого для Task 3

        System.out.println("--- All Students ---");
        for (String id : students.keySet()) {
            System.out.println("ID: " + id + ", " + students.get(id));
        }

        System.out.println("\n--- Find Student S003 ---");
        System.out.println(students.get("S003"));

        System.out.println("\n--- Remove Student S002 ---");
        students.remove("S002");

        System.out.println("\n--- Update GPA for S005 ---");
        if (students.containsKey("S005")) {
            students.get("S005").setGpa(3.95);
        }
        System.out.println(students.get("S005"));

        java.util.ArrayList<Student> studentList = new java.util.ArrayList<>(students.values());

        System.out.println("\n--- Sorted by GPA (Comparable) ---");
        java.util.Collections.sort(studentList);
        studentList.forEach(System.out::println);

        System.out.println("\n--- Sorted by Name (Comparator) ---");
        studentList.sort(java.util.Comparator.comparing(Student::getName));
        studentList.forEach(System.out::println);

        System.out.println("\n=== Task 2: Top 3 by GPA ===");
        List<Student> topStudents = new ArrayList<>(students.values());
        topStudents.sort(Collections.reverseOrder());
        topStudents.stream().limit(3).forEach(System.out::println);

        System.out.println("\n=== Task 3: Students with same GPA ===");
        HashMap<Double, List<String>> gpaGroups = new HashMap<>();
        for (Student s : students.values()) {
            gpaGroups.computeIfAbsent(s.getGpa(), k -> new ArrayList<>()).add(s.getName());
        }
        for (Map.Entry<Double, List<String>> entry : gpaGroups.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("GPA " + entry.getKey() + " -> " + String.join(", ", entry.getValue()));
            }
        }

        System.out.println("\n=== Task 4: Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();
        Course javaCourse = new Course("Java");
        Course pythonCourse = new Course("Python");

        courseMap.put(javaCourse, new ArrayList<>(Arrays.asList(students.get("S001"), students.get("S003"))));
        courseMap.put(pythonCourse, new ArrayList<>(Arrays.asList(students.get("S004"), students.get("S005"))));

        for (Map.Entry<Course, List<Student>> entry : courseMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\n=== Task 5: GPA desc + Name ===");
        List<Student> task5List = new ArrayList<>(students.values());
        task5List.sort(Comparator.comparing(Student::getGpa).reversed()
                .thenComparing(Student::getName));
        task5List.forEach(System.out::println);
    }
}
