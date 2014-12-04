package me.azna.dataguru.jvm.week10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import me.azna.dataguru.jvm.week10.ConstantPoolInfo.CLASSConstant;
import me.azna.dataguru.jvm.week10.ConstantPoolInfo.Constant;
import me.azna.dataguru.jvm.week10.ConstantPoolInfo.FieldRefConstant;
import me.azna.dataguru.jvm.week10.ConstantPoolInfo.MethodRefConstant;
import me.azna.dataguru.jvm.week10.ConstantPoolInfo.NameAndTypeConstant;
import me.azna.dataguru.jvm.week10.ConstantPoolInfo.StringConstant;
import me.azna.dataguru.jvm.week10.ConstantPoolInfo.UTF8Constant;

public class ClassParser2 {
	private FileChannel channel;

	public static void main(String[] args) throws IOException {
		ClassParser2 parser = new ClassParser2();

		parser.parse(new File(ClassParser2.class.getResource("TClass.class")
				.getFile()));
	}

	@SuppressWarnings("resource")
	private void parse(File file) throws IOException {
		ClassGeneralInfo classInfo = new ClassGeneralInfo();

		channel = new FileInputStream(file).getChannel();

		// 魔数
		getHexString(readBytes(4));
		// minor version
		classInfo.setMinorVersion(getIntValue(2));
		// major version
		classInfo.setMajorVersion(getIntValue(2));
		// cp
		ConstantPoolInfo cpInfo = new ConstantPoolInfo();
		// constants count ,TODO study must - 1
		cpInfo.setCount(getIntValue(2) - 1);
		Constant[] constants = new Constant[cpInfo.getCount()];
		cpInfo.setConstants(constants);
		for (int i = 0; i < cpInfo.getCount(); i++) {
			// set constant ,tag u1
			constants[i] = getConstant(cpInfo, getIntValue(1));
		}
		// access flag
		classInfo.setAccessFlags(getIntValue(2));
		// this class
		classInfo.setThisClass(getIntValue(2));
		// super class
		classInfo.setSuperClass(getIntValue(2));
		// interface count
		int interfacesCount = getIntValue(2);
		int[] interfaces = new int[interfacesCount];
		for (int i = 0; i < interfacesCount; i++) {
			// set interface
			interfaces[i] = getIntValue(2);
		}
		// fields count
		// int fieldsCount = getIntValue(2);
		// fields

		// methods count
		// int methodsCount = getIntValue(2);
		// methods

		channel.close();
		// print Constants
		for (Constant constant : cpInfo.getConstants()) {
			System.out.println(constant);
		}
		System.out.println("......");
		System.out.println(cpInfo.getConstants()[classInfo.getThisClass() - 1]);
		System.out
				.println(cpInfo.getConstants()[classInfo.getSuperClass() - 1]);
		System.out.println(interfacesCount);
		for (int inter : interfaces) {
			System.out.println(cpInfo.getConstants()[inter - 1]);
		}

	}

	/**
	 * 获得常量
	 * 
	 * @param cpInfo
	 * @param tag
	 * @return
	 * @throws IOException
	 */
	private Constant getConstant(ConstantPoolInfo cpInfo, int tag)
			throws IOException {
		Constant constant = null;
		switch (tag) {
		case 1: {
			// UTF8
			int length = getIntValue(2);
			UTF8Constant utf8C = cpInfo.new UTF8Constant(tag, length,
					readBytes(length));
			constant = utf8C;
			break;
		}
		case 3: {
			// Integer
			break;

		}
		case 7: {
			// Class
			CLASSConstant classC = cpInfo.new CLASSConstant(tag, getIntValue(2));
			constant = classC;
			break;

		}
		case 8: {
			// String
			StringConstant stringC = cpInfo.new StringConstant(tag,
					getIntValue(2));
			constant = stringC;
			break;

		}
		case 9: {
			// Fieldref
			FieldRefConstant fieldRefC = cpInfo.new FieldRefConstant(tag,
					getIntValue(2), getIntValue(2));
			constant = fieldRefC;
			break;

		}
		case 10: {
			// Methodref
			MethodRefConstant methodRefC = cpInfo.new MethodRefConstant(tag,
					getIntValue(2), getIntValue(2));
			constant = methodRefC;
			break;

		}
		case 12: {
			// NameAndType
			NameAndTypeConstant nameAndTypeC = cpInfo.new NameAndTypeConstant(
					tag, getIntValue(2), getIntValue(2));
			constant = nameAndTypeC;
			break;

		}
		}
		return constant;
	}

	private int getIntValue(int byteCount) throws IOException {
		return bytes2Int(readBytes(byteCount));
	}

	/**
	 * 读取指定字节
	 * 
	 * @param byteCount
	 * @return
	 * @throws IOException
	 */
	private byte[] readBytes(int byteCount) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(byteCount);
		channel.read(buffer);
		return buffer.array();
	}

	/**
	 * 获得字节表示的数
	 * 
	 * @param bytes
	 * @return
	 */
	private int bytes2Int(byte[] bytes) {
		int mask = 0xff;
		int n = 0;
		for (int i = 0; i < bytes.length; i++) {
			n <<= 8;
			n |= bytes[i] & mask;
		}
		return n;
	}

	private String getHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(Integer.toHexString(0xFF & b).toUpperCase()).append(" ");
		}
		return sb.toString();
	}

}
