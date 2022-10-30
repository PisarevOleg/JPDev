package com.example.Services;

import com.google.common.reflect.ClassPath;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@AllArgsConstructor
public class SlTester {
    @Autowired
    List<ClassPath.ClassInfo> testClassesList;

    public List<Boolean> runnerTest() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        return testClassesList.stream().map(item -> {
            Class<?> clazz = null;
            try {
                clazz = Class.forName(item.getName());
                Constructor<?> constructor = clazz.getConstructor();
                Object instance = constructor.newInstance();

                SlTestOperationImplementor implementor = new SlTestOperationImplementor();
                return implementor.CheckInstance(instance);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }
}
