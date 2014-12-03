package me.azna.dataguru.jvm.week10;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ClassParser {
	FileChannel channel;

	public static void main(String[] args) throws IOException {
		ClassParser parser = new ClassParser();

		parser.parse(new File(ClassParser.class.getResource("TClass.class")
				.getFile()));
	}

	@SuppressWarnings("resource")
	private void parse(File file) throws IOException {
		channel = new FileInputStream(file).getChannel();
		// 魔数
		System.out.println(getHexString(readBytes(4)));
		// 小版本
		System.out.println(getHexString(readBytes(2)));
		// 大版本
		System.out.println(getHexString(readBytes(2)));

		ConstantsPool cp = getCP(channel);
		System.out.println(cp.count);

		channel.close();
	}

	private ConstantsPool getCP(FileChannel channel) throws IOException {
		return new ConstantsPool(channel);
	}

	private String getHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(Integer.toHexString(0xFF & b).toUpperCase()).append(" ");
		}
		return sb.toString();
	}

	private byte[] readBytes(int byteCount) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(byteCount);
		channel.read(buffer);
		return buffer.array();
	}

	private int bytes2Int(byte[] bytes) {
		int mask = 0xff;		
		int n = 0;
		for (int i = 0; i < bytes.length; i++) {
			n <<= 8;			
			n |= bytes[i] & mask;
		}
		return n;
	}

	class ConstantsPool {
		int count;
		Constant[] constants;

		ConstantsPool(FileChannel channel) throws IOException {
			super();
			count = bytes2Int(readBytes(2));
			for (int i = 0; i < count; i++) {
				constants[i] = getConstants(channel);

			}
		}

		private Constant getConstants(FileChannel channel) throws IOException {
			int tag = bytes2Int(readBytes(2));
			return getConstant(tag);
		}

		private Constant getConstant(int tag) throws IOException {
			Constant constant = null;
			switch (tag) {
			case 1: {
				// UTF8
				int length = bytes2Int(readBytes(2));
				constant = new UTF8(tag, length, readBytes(length));
			}
			case 3: {
				// Integer

			}
			case 7: {
				// Class

			}
			case 8: {
				// String

			}
			case 9: {
				// Fieldref

			}
			case 10: {
				// Methodref

			}
			case 12: {
				// NameAndType

			}
			}
			return constant;
		}

		class Constant {
			int tag;

			public Constant(int tag) {
				super();
				this.tag = tag;
			}
		}

		class UTF8 extends Constant {
			int length;
			byte[] content;

			public UTF8(int tag, int length, byte[] content) {
				super(tag);
				this.length = length;
				this.content = content;
			}

			String getContent(int tag) {
				return new String(content);
			}
		}

	}
}
