package com.ljt.study.agent.sentinel;

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
 * @date 2021-08-19 14:18
 */
public class PrintCpu {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("JVM 载入 agent：" + PrintCpu.class);
        inst.addTransformer(new PrintReturnValueTransformer());
    }

    private static class PrintReturnValueTransformer implements ClassFileTransformer {

        private static final Map<String, Set<String>> METHOD_MAP = new HashMap<>();
        private static final ClassPool CLASS_POLL = ClassPool.getDefault();

        static {
            addMethod("com.alibaba.csp.sentinel.slots.system.SystemStatusListener", "run");
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
                    ctMethod.insertAt(78, "System.out.println(\"currentCpuUsage=\" + currentCpuUsage + \" | processCpuUsage=\" + processCpuUsage + " +
                            "\" | systemCpuUsage=\" + systemCpuUsage);");
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
