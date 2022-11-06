package com.example.Services;

import com.google.common.reflect.ClassPath;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlTester {

    List<ClassPath.ClassInfo> testClassesList;

    public SlTester(List<ClassPath.ClassInfo> testClassesList) {
        this.testClassesList=testClassesList;
    }

    public List<Boolean> runnerTest() {
        return testClassesList.stream().flatMap(item -> {
            try {
                SlTestOperationImplementor implementor = new SlTestOperationImplementor();
                return implementor.checkInstance(item.getName()).stream();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
}
