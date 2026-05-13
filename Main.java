import java.util.Scanner;
import java.util.ArrayList;

class Student{
    private static int nextId = 260101;

    private int id;
    private String name;
    private int age;
    private String gender;
    private String contact;
    private double[] grade;

    Student(String input_name){
        this.name = input_name;
        this.id = nextId++;
        this.age = 0;
        this.gender = "미입력";
        this.contact = "미입력";
        this.grade = new double[4];
    }

    public void setAge(int input_age){
        this.age = input_age;
    }
    public void setGender(String input_gender){
        this.gender = input_gender;
    }
    public void setContact(String input_contact){
        this.contact = input_contact;
    }
    public void setGrade(int input_semester, double input_grade){
        if(input_semester>0 && input_semester<=4)
        {
            this.grade[input_semester-1] = input_grade;
        }

    }

    public int getId(){
        return this.id;
    }

    public void showInfo(){
        System.out.println("----------------------------------");
        System.out.println("학번 : " + this.id);
        System.out.println("이름 : " + this.name);
        System.out.println("성별 : " + this.gender);
        System.out.println("연락처 : " + this.contact);
        System.out.println("나이 : " + this.age);
        System.out.println("학기별 성적 : " );
        for (int i = 0; i<4; i++)
        {
            System.out.println( (i+1) + "학기 [" + this.grade[i] + "]");
        }
        System.out.println("----------------------------------");
    }

}

class GraduateStudent extends Student{
    private String labName; //연구실
    private String advisor; //지도교수

    public GraduateStudent(String name, String labName, String advisor){
        super(name);
        this.labName = labName;
        this.advisor = advisor;
    }

    @Override
    public void showInfo(){
        super.showInfo();
        System.out.println("연구실 : " + labName);
        System.out.println("지도교수 : " + advisor);
        System.out.println("구분 : 대학원생");
        System.out.println("----------------------------------");
    }
}







public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Student> studentList = new ArrayList<>();



        while(true){
            System.out.println("\n=== 폴리텍 학적 관리 시스템 ===");
            System.out.println("1. 학생 등록 | 2. 성적/정보 수정 | 3. 전체 명단 출력 | 4. 종료");
            System.out.println("메뉴 선택 : ");
            String menu = sc.nextLine();
            if (menu.equals("1")){
                System.out.print("(1)대학생  (2)대학원생  >>  ");
                String input_str = sc.nextLine();
                if(input_str.equals("1")){
                    System.out.print("등록할 학생의 이름을 입력 : ");
                    String name = sc.nextLine();
                    Student newStudent = new Student(name);

                    studentList.add(newStudent);
                    System.out.println("학생이 등록되었습니다.");
                }
                else if(input_str.equals("2")){
                    System.out.print("등록할 학생의 이름을 입력 : ");
                    String name = sc.nextLine();
                    System.out.print("연구실 입력 : ");
                    String labName = sc.nextLine();
                    System.out.print("지도교수님 입력 : ");
                    String advisor = sc.nextLine();
                    Student newStudent = new GraduateStudent(name,labName,advisor);

                    studentList.add(newStudent);
                    System.out.println("학생이 등록되었습니다.");
                }
            }else if(menu.equals("2")){
                boolean goto_main = false;
                while(!goto_main) {


                /*
                1. 정보를 수정할 학생의 학번을 입력받기
                2. 입력받은 학번이 학생 리스트에 있는지 확인할 것
                   >> 반복문과 조건문 사용
                3 - 1. 없는 경우 1번으로 돌아가기
                3 - 2. 있는 경우 어떤 정보를 수정할 것인지 입력받기
                4. 학생 객체의 set메서드를 사용하여 해당 값 수정(최신화)
                5. 수정이 끝나면 다시 메인메뉴로 돌아갈 방법 마련
                */
                    System.out.println("정보를 수정할 학생의 학번을 입력하세요. ");
                    System.out.print("메인메뉴로 이동하시려면 0을 입력하세요  >>  ");
                    int input_int = sc.nextInt();
                    sc.nextLine();

                    if(input_int == 0)
                    {
                        System.out.println("메인메뉴로 이동합니다.");
                        break;
                    }

                    Student std = null;

                    for (Student s : studentList) {

                        if (s.getId() == input_int) {
                            //입력한 학번이 학생 리스트에 있는 경우
                            std = s;
                            System.out.println("학번 입력 OK");
                            break;
                        }
                    }
                    if(std == null){
                        System.out.println("학번을 잘못 입력하셨습니다.");
                        continue;
                    }
                    while(true) {
                        System.out.println("변경하고 싶은 항목번호를 입력하세요.");
                        System.out.println("(1)성별  (2)나이  (3)연락처  (4)성적  etc.이전 메뉴로");
                        String input_str = sc.nextLine();
                        if (input_str.equals("1")) {
                            //성별 수정하는 코드
                            System.out.println("성별에 해당하는 숫자를 입력하세요. (1)남자  (2)여자");
                            input_str = sc.nextLine();
                            if (input_str.equals("1")) {
                                std.setGender("남");
                            } else if (input_str.equals("2")) {
                                std.setGender("여");
                            }
                        }
                        else if(input_str.equals("2"))
                        {
                            //나이 수정하는 코드
                            System.out.print("변경하고자 하는 나이를 입력하세요 >> ");
                            input_int = sc.nextInt();
                            sc.nextLine();
                            std.setAge(input_int);
                        }
                        else if(input_str.equals("3"))
                        {
                            //연락처 수정하는 코드
                            System.out.print("연락처를 입력하세요 >> ");
                            input_str = sc.nextLine();
                            std.setContact(input_str);
                        }
                        else if(input_str.equals("4"))
                        {
                            //성적 수정하는 코드
                            System.out.print("변경하고자 하는 학기를 입력하세요 >> ");
                            input_int = sc.nextInt();
                            sc.nextLine();
                            System.out.print("학점을 입력하세요 >> ");
                            double input_double = sc.nextDouble();
                            sc.nextLine();
                            std.setGrade(input_int,input_double);
                        }
                        else{
                            //이전 메뉴로 돌아가는 코드
                            break;
                        }
                    }
                    //System.out.println("학번 : " + input_int);
                }
            }else if(menu.equals("3")){
                for(Student s : studentList){
                    s.showInfo();
                }
            }else if(menu.equals("4")){
                System.out.println("프로그램을 종료합니다.");
                break;
            }else{
                System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요.");
            }

        }


        sc.close();
    }
}