import java.util.Scanner;
import java.util.ArrayList;

class Student {

    private static int nextId = 260101;

    private int id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private double[] grade;

    Student(String input_name) {
        this.name = input_name;
        this.id = nextId++;
        this.age = 0;
        this.gender = "미입력";
        this.contact = "미입력";
        this.grade = new double[4];
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int input_age) {
        this.age = input_age;
    }

    public void setGender(String input_gender) {
        this.gender = input_gender;
    }

    public void setContact(String input_contact) {
        this.contact = input_contact;
    }

    public void setGrade(int input_semester, double input_grade) {
        if (input_semester > 0 && input_semester <= 4) {
            this.grade[input_semester - 1] = input_grade;
        }
    }

    public void showInfo() {

        System.out.println("------------------------------------");
        System.out.println("학번: " + this.id);
        System.out.println("이름: " + this.name);
        System.out.println("성별: " + this.gender);
        System.out.println("연락처: " + this.contact);
        System.out.println("나이: " + this.age);

        System.out.println("학기별성적:");

        for (int i = 0; i < 4; i++) {
            System.out.print((i + 1) + "학기 [" + grade[i] + "] ");
        }

        System.out.println();
        System.out.println("------------------------------------");
    }
}

class GraduateStudent extends Student{
    private String labName;
    private String advisor;

    public GraduateStudent(String name,String labName,String advisor){
        super(name);
        this.labName=labName;
        this.advisor=advisor;
    }
    @Override
    public void showInfo(){
        super.showInfo();
        System.out.println("연구실 : "+labName);
        System.out.println("지도 교수 : "+advisor);
        System.out.println("구분 : 대학원생");
        System.out.println("-----------------------------------");
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        while (true) {

            System.out.println("\n===== 학적관리 시스템 =====");
            System.out.println("1. 학생 등록 | 2. 성적/정보 수정 | 3. 전체 명단 출력 | 4. 종료");
            System.out.print("메뉴선택 : ");

            String menu = sc.nextLine();

            if (menu.equals("1")) {
                System.out.print("(1)대학생  (2)대학원생 : ");
                String input_str = sc.nextLine();

                if(input_str.equals("1")){
                    System.out.print("등록할 학생 이름 입력 : ");
                    String name = sc.nextLine();

                    Student newStudent = new Student(name);
                    studentList.add(newStudent);

                    System.out.println("학생이 등록되었습니다");
                }

                else if(input_str.equals("2")) {
                    System.out.print("등록할 학생 이름 입력 : ");
                    String name = sc.nextLine();

                    System.out.print("연구실 입력 : ");
                    String labName=sc.nextLine();

                    System.out.print("지도 교수 입력 : ");
                    String advisor=sc.nextLine();

                    Student newStudent = new GraduateStudent(name,labName,advisor);

                    studentList.add(newStudent);
                    System.out.println("학생이 등록되었습니다");
                }
            }

            else if (menu.equals("2")) {

                if (studentList.isEmpty()) {
                    System.out.println("등록된 학생이 없습니다.");
                    continue;
                }

                System.out.println("\n===== 학생 목록 =====");
                for (Student s : studentList) {
                    System.out.println("학번: " + s.getId() + " | 이름: " + s.getName());
                }

                System.out.print("\n수정할 학생 학번 선택: ");
                int id = Integer.parseInt(sc.nextLine());

                for (Student s : studentList) {

                    if (s.getId() == id) {

                        System.out.println("\n===== 수정 메뉴 =====");
                        System.out.println("1. 나이 수정");
                        System.out.println("2. 성별 수정");
                        System.out.println("3. 연락처 수정");
                        System.out.println("4. 성적 수정");
                        System.out.println("5. 학생 구분 수정");
                        System.out.print("선택: ");

                        String choice = sc.nextLine();

                        if (choice.equals("1")) {
                            System.out.print("나이 입력: ");
                            s.setAge(Integer.parseInt(sc.nextLine()));
                        }

                        else if (choice.equals("2")) {
                            System.out.print("성별 선택 ( (1)남자  (2)여자 ): ");
                            choice = sc.nextLine();
                            if (choice.equals("1")) {
                                s.setGender("남");
                            } else if (choice.equals("2")) {
                                s.setGender("여");
                            }
                        }

                        else if (choice.equals("3")) {
                            System.out.print("연락처 입력: ");
                            s.setContact(sc.nextLine());
                        }

                        else if (choice.equals("4")) {
                            for (int i = 1; i <= 4; i++) {
                                System.out.print(i + "학기 성적 입력: ");
                                double gr = Double.parseDouble(sc.nextLine());
                                s.setGrade(i, gr);
                            }
                        }
                        System.out.println("입력 완료");
                        break;
                    }
                }
            }

            else if (menu.equals("3")) {

                if (studentList.isEmpty()) {
                    System.out.println("등록된 학생이 없습니다.");
                } else {
                    for (Student s : studentList) {
                        s.showInfo();
                    }
                }
            }

            else if (menu.equals("4")) {
                System.out.println("프로그램 종료");
                break;
            }

            else {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }

        sc.close();
    }
}