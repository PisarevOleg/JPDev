package com.example;

import com.example.Services.SlTestedClassesDetector;
import com.example.Services.SlTester;
import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

//@SpringBootApplication
public class Main {
    // т.к. в требованиях не было указано какие эксепшены на каких методах "НЕ игнорировать", то было принято решение
    //  брать "неуспехом" любые эксепшены на @Before && @Test и иногрировать эксепшены на методах @After
    //  (все методы @After будут выполнены, но ошибка работы процедуры НЕ будет зачтена как "ошибка" т.к. процедура освобождения ресурсов не может и не является целево-проверяемой)

    // чуть усложнил задание и забор идет тех классов, которые помечены @SlClassMark c пропертисом TestClass=true

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        //ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        //List<Boolean> list = context.getBean(SlTester.class).runnerTest();

        class GetPackageName {}
        List<ClassPath.ClassInfo> listTargets = (new SlTestedClassesDetector((new GetPackageName()).getClass().getPackageName())).testClassesList();
        List<Boolean> listResults = (new SlTester(listTargets)).runnerTest();

        System.out.println("----------------------------------------- >");
        System.out.println("всего тестов: " + listResults.size());
        System.out.println(" успешно пройдено тестов: " + listResults.stream().filter(t -> (boolean) t).toList().size());
        System.out.println(" НЕ успешно пройденных тестов: " + listResults.stream().filter(t -> !(boolean) t).toList().size());
        System.out.println("----------------------------------------- <");
    }

    String wrappingPackageName() {
        return this.getClass().getPackageName();
    }
}
