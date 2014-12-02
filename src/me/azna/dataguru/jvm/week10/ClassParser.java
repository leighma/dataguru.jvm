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
			
		System.out.println(getHexString(readBytes(4)));
		System.out.println(getHexString(readBytes(2)));
		System.out.println(getHexString(readBytes(2)));
		System.out.println(getHexString(readBytes(2)));

		
		channel.close();
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

}
