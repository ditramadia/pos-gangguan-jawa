package com.example.if2210_tb2_nge.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class PluginLoader {
    /**
     * Returns an arraylist of class names in a JarInputStream
     *
     * @param jarFile        JarInputStream of the jar file
     * @return classNames    arraylist of class names
     */
    private static ArrayList<String> getClassNamesFromJar(JarInputStream jarFile) throws Exception {
        ArrayList<String> classNames = new ArrayList<>();
        try {
            JarEntry jar;

            //Iterate through the contents of the jar file
            while (true) {
                jar = jarFile.getNextJarEntry();
                if (jar == null) {
                    break;
                }
                //Pick file that has the extension of .class
                if ((jar.getName().endsWith(".class"))) {
                    String className = jar.getName().replaceAll("/", "\\.");
                    String myClass = className.substring(0, className.lastIndexOf('.'));
                    classNames.add(myClass);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while getting class names from jar", e);
        }
        return classNames;
    }

    /**
     * Returns an arraylist of class names in a JarInputStream
     * Calls the above function by converting the jar path to a stream
     *
     * @param jarPath               path to the jar file
     * @return className            main plugin class (implements Plugin and has override run method
     */
    private static String getPluginClass(String jarPath) throws Exception {
        ArrayList<String> classNames = getClassNamesFromJar(new JarInputStream(new FileInputStream(jarPath)));
        for (String className : classNames) {
            Class<?> cc = Class.forName(className);
            if (cc.isAnnotationPresent(PluginMainClass.class)) {
                return className;
            }
        }
        return null;
    }

    // get an arraylist of all the loaded classes in a jar file
    public static void loadJarFile(String filePath) throws Exception {
        String pluginClass = getPluginClass(filePath);
        if (pluginClass == null) {
            throw new Exception("Plugin class not found or not annotated with @PluginMainClass");
        }
        File f = new File(filePath);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{f.toURI().toURL()});
        try {
            Class<?> cc = classLoader.loadClass(pluginClass);
            BasePlugin basePlugin = (BasePlugin) cc.getDeclaredConstructor().newInstance();
            PluginFactory.addPlugin(basePlugin);
        } catch (ClassNotFoundException e) {
            throw new Exception("Class not found", e);
        }
    }


//    public static void main(String[] args) {
//        Reflection r = new Reflection();
//        try {
//            ArrayList<Class<?>> classes = r.loadJarFile("myJar.jar");
//            for (Class<?> c : classes) {
//                System.out.println("Class:  " + c.getName());
//                for (Method m : c.getDeclaredMethods()) {
//                    m.invoke(c.getDeclaredConstructor().newInstance());
//                }
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Error: " + e);
//        }
//    }
}
