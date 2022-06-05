package me.zdziszkee.desyntheticinator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Desyntheticinator {
    public static void main(String[] args)
            throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        
        
        final Path path = Paths.get("C:/Users/Zdziszkee/Desktop/decompiler/blazingpack/extracted");
        final List<Path> filesFromDirectory = getFilesFromDirectory(path);
        for (Path classFile : filesFromDirectory) {
            final InputStream inputStream = Files.newInputStream(classFile);
            final byte[] readStream = readStream(inputStream, false);
         //   Arrays.stream(ClassReader.class.getDeclaredConstructors()).forEach(constructor1-> System.out.println(constructor1.toString()));
            final Constructor<ClassReader> constructor = ClassReader.class.getDeclaredConstructor(byte[].class, int.class, boolean.class);
            constructor.setAccessible(true);
            final ClassReader classReader = constructor.newInstance(readStream, 0, /* checkClassVersion = */ false);//new ClassReader(inputStream);
            final ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES);
            final DesyntheticinatorVisitor desyntheticinatorVisitor = new DesyntheticinatorVisitor( classWriter);
            classReader.accept(desyntheticinatorVisitor, 0);
            final byte[] bytes = classWriter.toByteArray();
            Files.write(classFile, bytes);
        }
        
    }
    
    private static List<Path> getFilesFromDirectory(Path path) throws IOException {
        
        final List<Path> list = Files.list(path).collect(Collectors.toList());
        final List<Path> targets = new ArrayList<>();
        for (Path element : list) {
            if (Files.isDirectory(element)) {
                targets.addAll(getFilesFromDirectory(element));
                continue;
            }
            final String string = element.toString();
            if (string.endsWith(".class")) {
                targets.add(element);
            }
        }
        return targets;
    }
    private static byte[] readStream(final InputStream inputStream, final boolean close)
            throws IOException {
        if (inputStream == null) {
            throw new IOException("Class not found");
        }
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] data = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, bytesRead);
            }
            outputStream.flush();
            return outputStream.toByteArray();
        } finally {
            if (close) {
                inputStream.close();
            }
        }
    }
}
