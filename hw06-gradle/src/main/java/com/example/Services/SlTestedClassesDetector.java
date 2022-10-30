package com.example.Services;

import com.example.Annotation.SlClassMark;
import com.example.Annotation.SlTest;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class SlTestedClassesDetector {
    @Autowired
    String wrappingPackageName;

    @Bean
    List<ClassPath.ClassInfo> TestClassesList() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassPath classPath = ClassPath.from(classLoader);
        ImmutableSet<ClassPath.ClassInfo> set = classPath.getTopLevelClassesRecursive(wrappingPackageName);

        Predicate<ClassPath.ClassInfo> p1 = item -> {
            try {
                return !Arrays.stream(Class.forName(item.getName()).getAnnotations()).filter(SlClassMark.class::isInstance).toList().isEmpty();
            } catch (Exception ignored) {
            }
            return false;
        };

        Predicate<ClassPath.ClassInfo> p2 = item -> {
            try {
                return Class.forName(item.getName()).getAnnotation(SlClassMark.class).TestClass();
            } catch (Exception ignored) {
            }
            return false;
        };

        Predicate<ClassPath.ClassInfo> p3 = item -> {
            try {
                // проверка на наличие метода проверки, помеченного @Test
                // @Before и @After не являются обязательными
                return !Arrays.stream(Class.forName(item.getName()).getDeclaredMethods()).filter(
                        m -> !Arrays.stream(m.getAnnotations()).filter(SlTest.class::isInstance).toList().isEmpty()
                ).toList().isEmpty();
            } catch (Exception ignored) {
            }
            return false;
        };

        return set.stream().filter(p1.and(p2).and(p3)).toList();
    }
}
