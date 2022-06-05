package me.zdziszkee.desyntheticinator;

import org.objectweb.asm.*;

public class DesyntheticinatorVisitor extends ClassVisitor {
    public DesyntheticinatorVisitor( ClassVisitor classVisitor) {
        
        super(Opcodes.ASM4, classVisitor);
    }
    
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        boolean isSynthetic = (access & Opcodes.ACC_SYNTHETIC) != 0;
        if (isSynthetic) {
            access &= ~Opcodes.ACC_SYNTHETIC;
        }
        boolean isBridge = (access & Opcodes.ACC_BRIDGE) != 0;
        if (isBridge) {
            access &= ~Opcodes.ACC_BRIDGE;
        }
        super.visit(version, access, name, signature, superName, interfaces);
    }
    
    @Override
    public void visitSource(String source, String debug) {
        
        super.visitSource(source, debug);
    }
    
    @Override
    public ModuleVisitor visitModule(String name, int access, String version) {
        boolean isSynthetic = (access & Opcodes.ACC_SYNTHETIC) != 0;
        if (isSynthetic) {
            access &= ~Opcodes.ACC_SYNTHETIC;
        }
        boolean isBridge = (access & Opcodes.ACC_BRIDGE) != 0;
        if (isBridge) {
            access &= ~Opcodes.ACC_BRIDGE;
        }
        return super.visitModule(name, access, version);
    }
    
    @Override
    public void visitNestHost(String nestHost) {
        
        super.visitNestHost(nestHost);
    }
    
    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        
        super.visitOuterClass(owner, name, descriptor);
    }
    
    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        
        return super.visitAnnotation(descriptor, visible);
    }
    
    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        
        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }
    
    @Override
    public void visitAttribute(Attribute attribute) {
        
        super.visitAttribute(attribute);
    }
    
    @Override
    public void visitNestMember(String nestMember) {
        
        super.visitNestMember(nestMember);
    }
    
    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        boolean isSynthetic = (access & Opcodes.ACC_SYNTHETIC) != 0;
        if (isSynthetic) {
            access &= ~Opcodes.ACC_SYNTHETIC;
        }
        boolean isBridge = (access & Opcodes.ACC_BRIDGE) != 0;
        if (isBridge) {
            access &= ~Opcodes.ACC_BRIDGE;
        }
        super.visitInnerClass(name, outerName, innerName, access);
    }
    
    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        boolean isSynthetic = (access & Opcodes.ACC_SYNTHETIC) != 0;
        if (isSynthetic) {
            access &= ~Opcodes.ACC_SYNTHETIC;
        }
        boolean isBridge = (access & Opcodes.ACC_BRIDGE) != 0;
        if (isBridge) {
            access &= ~Opcodes.ACC_BRIDGE;
        }
        return super.visitField(access, name, descriptor, signature, value);
    }
    
    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        boolean isSynthetic = (access & Opcodes.ACC_SYNTHETIC) != 0;
        if (isSynthetic) {
            access &= ~Opcodes.ACC_SYNTHETIC;
        }
        boolean isBridge = (access & Opcodes.ACC_BRIDGE) != 0;
        if (isBridge) {
            access &= ~Opcodes.ACC_BRIDGE;
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
    
    @Override
    public void visitEnd() {
        
        super.visitEnd();
    }
}
