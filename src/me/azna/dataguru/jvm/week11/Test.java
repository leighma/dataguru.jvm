package me.azna.dataguru.jvm.week11;

import java.lang.reflect.InvocationTargetException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Test extends ClassLoader implements Opcodes {

	public static void main(String[] args) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS
				| ClassWriter.COMPUTE_FRAMES);
		cw.visit(V1_6, ACC_PUBLIC, "Example", null, "java/lang/Object", null);
		MethodVisitor mw = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,
				null);
		mw.visitVarInsn(ALOAD, 0); // this 入栈
		mw.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		mw.visitInsn(RETURN);
		mw.visitMaxs(0, 0);
		mw.visitEnd();
		mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main",
				"([Ljava/lang/String;)V", null, null);
		mw.visitVarInsn(BIPUSH, 6);
		mw.visitVarInsn(ISTORE, 1);
		
		
		mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
				"Ljava/io/PrintStream;");
//		mw.visitLdcInsn("Hello world!");
		mw.visitVarInsn(ILOAD, 1);
		mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
				"(I)V");
		mw.visitInsn(RETURN);
		mw.visitMaxs(0, 0);
		mw.visitEnd();
		byte[] code = cw.toByteArray();
		Test loader = new Test();

		Class<?> exampleClass = loader.defineClass("Example", code, 0,
				code.length);
		exampleClass.getMethods()[0].invoke(null, new Object[] { null });

	}

}
