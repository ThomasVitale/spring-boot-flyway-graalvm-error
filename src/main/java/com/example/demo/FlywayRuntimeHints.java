package com.example.demo;

import org.flywaydb.core.internal.exception.FlywaySqlException;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class FlywayRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        var scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(FlywaySqlException.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("org.flywaydb")) {
            try {
                Class<?> clazz = Class.forName(bd.getBeanClassName(), false, classLoader);
                hints.reflection().registerType(clazz,
                    MemberCategory.INVOKE_PUBLIC_METHODS,
                    MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS
                );
            } catch (ClassNotFoundException ignored) {
            }
        }
    }
    
}
