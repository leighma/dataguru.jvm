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
import me.azna.dataguru.jvm.week10.FieldsInfo.Field;
import me.azna.dataguru.jvm.week10.MethodsInfo.Method;

public class ClassParser2 {
	private FileChannel channel;

	public static void main(String[] args) throws IOException {
		ClassParser2 parser = new ClassParser2();
		ClassInfo classInfo = parser.parse(new File(ClassParser2.class
				.getResource("TClass.class").getFile()));
		parser.showClass(classInfo);

	}

	@SuppressWarnings("resource")
	private ClassInfo parse(File file) throws IOException {
		ClassInfo classInfo = new ClassInfo();
		channel = new FileInputStream(file).getChannel();
		parseHeader(classInfo);
		parseCP(classInfo);
		parseClasses(classInfo);
		parseInterfaces(classInfo);
		parseFields(classInfo);
		parseMethods(classInfo);
		channel.close();
		return classInfo;
	}

	/**
	 * 解析方法
	 * 
	 * @param classInfo
	 * @throws IOException
	 */
	private void parseMethods(ClassInfo classInfo) throws IOException {
		// methods count
		int methodsCount = getIntValue(2);
		Method[] methods = new Method[methodsCount];
		classInfo.setMethods(methods);
		// methods
		for (int i = 0; i < methodsCount; i++) {
			Method method = classInfo.getMethodsInfo().new Method();
			methods[i] = method;
			method.accessFlag = getIntValue(2);
			method.name = getIntValue(2);
			method.descriptor = getIntValue(2);
			method.attributes = parseAttributes();
		}
	}

	/**
	 * 解析字段
	 * 
	 * @param classInfo
	 * @throws IOException
	 */
	private void parseFields(ClassInfo classInfo) throws IOException {
		// fields count
		int fieldsCount = getIntValue(2);
		Field[] fields = new Field[fieldsCount];
		classInfo.setFields(fields);
		// fields
		for (int i = 0; i < fieldsCount; i++) {
			Field field = classInfo.getFieldsInfo().new Field();
			fields[i] = field;
			field.accessFlag = getIntValue(2);
			field.name = getIntValue(2);
			field.descriptor = getIntValue(2);
			field.attributes = parseAttributes();
		}

	}

	/**
	 * 解析属性,有涉及属性解析的可共用此方法
	 * 
	 * @return
	 * @throws IOException
	 */
	private int[] parseAttributes() throws IOException {
		int attributesCount = getIntValue(2);
		int[] attributes = new int[attributesCount];
		for (int j = 0; j < attributesCount; j++) {
			attributes[j] = getIntValue(2);
			// TODO pass handle attribute,only hand name index
			readBytes(getIntValue(4));
		}
		return attributes;
	}

	/**
	 * 解析接口
	 * 
	 * @param classInfo
	 * @throws IOException
	 */
	private void parseInterfaces(ClassInfo classInfo) throws IOException {
		// interface count
		int interfacesCount = getIntValue(2);
		int[] interfaces = new int[interfacesCount];
		classInfo.setInterfaces(interfaces);
		for (int i = 0; i < interfacesCount; i++) {
			// set interface
			interfaces[i] = getIntValue(2);
		}
	}

	/**
	 * 解析类信息，包含访问控制符，本类、超类
	 * 
	 * @param classInfo
	 * @throws IOException
	 */
	private void parseClasses(ClassInfo classInfo) throws IOException {
		// access flag
		classInfo.setAccessFlags(getIntValue(2));
		// this class
		classInfo.setThisClass(getIntValue(2));
		// super class
		classInfo.setSuperClass(getIntValue(2));
	}

	/**
	 * 解析常量池
	 * 
	 * @param classInfo
	 * @throws IOException
	 */
	private void parseCP(ClassInfo classInfo) throws IOException {
		// cp
		ConstantPoolInfo cpInfo = new ConstantPoolInfo();
		classInfo.setConstantPool(cpInfo);
		// constants count ,TODO study must - 1
		cpInfo.setCount(getIntValue(2) - 1);
		Constant[] constants = new Constant[cpInfo.getCount()];
		cpInfo.setConstants(constants);
		for (int i = 0; i < cpInfo.getCount(); i++) {
			// set constant ,tag u1
			constants[i] = getConstant(cpInfo, getIntValue(1));
		}
	}

	/**
	 * 解析class文件头，包含魔数和小大版本
	 * 
	 * @param classInfo
	 * @throws IOException
	 */
	private void parseHeader(ClassInfo classInfo) throws IOException {
		// magic number
		classInfo.setMagicNumber(getIntValue(4));
		// minor version
		classInfo.setMinorVersion(getIntValue(2));
		// major version
		classInfo.setMajorVersion(getIntValue(2));
	}

	/**
	 * 获得常量，只完成了部分
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
			// Integer TODO
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

	/**
	 * 显示类信息
	 * 
	 * @param classInfo
	 */
	private void showClass(ClassInfo classInfo) {
		ConstantPoolInfo cpInfo = classInfo.getConstantPool();
		// print Constants
		// for (Constant constant : cpInfo.getConstants()) {
		// System.out.println(constant);
		// }
		System.out.println("......");
		System.out.println("this class : "
				+ cpInfo.getConstant(classInfo.getThisClass()));
		System.out.println("super class : "
				+ cpInfo.getConstant(classInfo.getSuperClass()));
		System.out.println("interface count : "
				+ classInfo.getInterfacesCount());
		int[] interfaces = classInfo.getInterfaces();
		for (int indexInterface : interfaces) {
			System.out.println("\t" + cpInfo.getConstant(indexInterface));
		}
		System.out.println("fields count : " + classInfo.getFieldsCount());
		Field[] fields = classInfo.getFields();
		for (Field field : fields) {
			System.out.println("\t" + field.accessFlag + " "
					+ cpInfo.getConstant(field.descriptor) + " "
					+ cpInfo.getConstant(field.name));
		}
		System.out.println("methods count : " + classInfo.getMethodsCount());
		Method[] methods = classInfo.getMethods();
		for (Method method : methods) {
			System.out.println("\t" + method.accessFlag + " "
					+ cpInfo.getConstant(method.descriptor) + " "
					+ cpInfo.getConstant(method.name));
		}

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

	/**
	 * 获得指定字节的int值
	 * 
	 * @param byteCount
	 * @return
	 * @throws IOException
	 */
	private int getIntValue(int byteCount) throws IOException {
		return bytes2Int(readBytes(byteCount));
	}

	private String getHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(Integer.toHexString(0xFF & b).toUpperCase()).append(" ");
		}
		return sb.toString();
	}

	/**
	 * 获得hex
	 * 
	 * @param intV
	 * @return
	 */
	private String getHexString(int intV) {
		return Integer.toHexString(intV).toUpperCase();
	}

}
