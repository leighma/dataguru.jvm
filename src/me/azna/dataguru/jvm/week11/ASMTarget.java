package me.azna.dataguru.jvm.week11;

import java.lang.reflect.InvocationTargetException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ASMTarget extends ClassLoader implements Opcodes {

	public static void main(String[] args) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		String className = "ASMTarget";

		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS
				| ClassWriter.COMPUTE_FRAMES);
		cw.visit(V1_6, ACC_PUBLIC, className, null, "java/lang/Object", null);
		MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,
				null);
		mw.visitVarInsn(ALOAD, 0);
		mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		mw.visitInsn(RETURN);
		mw.visitMaxs(0, 0);
		mw.visitEnd();
		
		mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
				"([Ljava/lang/String;)V", null, null);
		mw.visitVarInsn(BIPUSH, 6);
		mw.visitVarInsn(ISTORE, 1);
		mw.visitVarInsn(BIPUSH, 7);
		mw.visitVarInsn(ISTORE, 2);
		mw.visitVarInsn(ILOAD, 1);
		mw.visitVarInsn(ILOAD, 2);
		mw.visitInsn(IADD);
		mw.visitInsn(ICONST_3);
		mw.visitInsn(IMUL);
		mw.visitVarInsn(ISTORE, 3);
		mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
				"Ljava/io/PrintStream;");
		mw.visitVarInsn(ILOAD, 3);
		mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
				"(I)V");
		mw.visitInsn(RETURN);
		mw.visitMaxs(0, 0);
		mw.visitEnd();
		
		byte[] code = cw.toByteArray();
		ASMTarget loader = new ASMTarget();

		Class<?> exampleClass = loader.defineClass(className, code, 0,
				code.length);
		exampleClass.getMethods()[0].invoke(null, new Object[] { null });

	}

}
