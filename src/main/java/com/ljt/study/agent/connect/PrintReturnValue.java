package com.ljt.study.agent.connect;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.*;

/**
 * @author LiJingTang
 * @date 2021-06-23 20:09
 */
public class PrintReturnValue {

    private PrintReturnValue() {
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("JVM 载入 agent：" + PrintReturnValue.class.toString());
        inst.addTransformer(new PrintReturnValueTransformer());
    }

    private static class PrintReturnValueTransformer implements ClassFileTransformer {

        private static final Map<String, Set<String>> METHOD_MAP = new HashMap<>();
        private static final ClassPool CLASS_POLL = ClassPool.getDefault();

        static {
            addMethod("org.springframework.jdbc.datasource.DataSourceUtils", "getConnection");
        }

        private static void addMethod(String classFullName, String methodName) {
            Set<String> methodSet = METHOD_MAP.getOrDefault(classFullName, new HashSet<>());
            methodSet.add(methodName);
            METHOD_MAP.putIfAbsent(classFullName, methodSet);
        }

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classFileBuffer) throws IllegalClassFormatException {
            className = className.replace("/", ".");
            Set<String> methods = METHOD_MAP.get(className);
            if (Objects.isNull(methods)) {
                return null;
            }

            CtClass ctClass = null;

            try {
                ctClass = CLASS_POLL.getCtClass(className);

                for (String methodName : methods) {
                    CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
                    ctMethod.insertAfter("System.out.println($_);");
                }

                return ctClass.toBytecode();
            } catch (Exception e) {
                throw new IllegalClassFormatException(e.getMessage());
            } finally {
                if (Objects.nonNull(ctClass)) {
                    ctClass.detach();
                }
            }
        }
    }

}
