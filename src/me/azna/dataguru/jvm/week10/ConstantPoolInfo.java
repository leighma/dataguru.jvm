package me.azna.dataguru.jvm.week10;

public class ConstantPoolInfo {
	private int count;
	private Constant[] constants;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Constant[] getConstants() {
		return constants;
	}

	public void setConstants(Constant[] constants) {
		this.constants = constants;
	}

	class Constant {
		private int tag;

		public Constant(int tag) {
			super();
			this.tag = tag;
		}

		public int getTag() {
			return tag;
		}

		@Override
		public String toString() {
			return super.toString();
		}

	}

	class UTF8Constant extends Constant {
		private int length;
		private byte[] content;

		public UTF8Constant(int tag, int length, byte[] content) {
			super(tag);
			this.length = length;
			this.content = content;
		}

		public int getLength() {
			return length;
		}

		@Override
		public String toString() {
			return new String(content);
		}

	}

	class CLASSConstant extends Constant {
		private int className;

		public CLASSConstant(int tag, int className) {
			super(tag);
			this.className = className;
		}

		@Override
		public String toString() {
			return constants[className - 1].toString();
		}

	}

	class StringConstant extends Constant {
		private int content;

		public StringConstant(int tag, int content) {
			super(tag);
			this.content = content;
		}

		@Override
		public String toString() {
			return constants[content - 1].toString();
		}

	}

	class MethodRefConstant extends Constant {
		private int className;
		private int nameAndType;

		public MethodRefConstant(int tag, int className, int nameAndType) {
			super(tag);
			this.className = className;
			this.nameAndType = nameAndType;
		}

		@Override
		public String toString() {
			return constants[className - 1].toString() + " "
					+ constants[nameAndType - 1].toString();
		}

	}

	class NameAndTypeConstant extends Constant {
		private int name;
		private int discriptor;

		public NameAndTypeConstant(int tag, int name, int discriptor) {
			super(tag);
			this.name = name;
			this.discriptor = discriptor;
		}

		@Override
		public String toString() {
			return constants[name - 1].toString() + " "
					+ constants[discriptor - 1].toString();
		}

	}

	class FieldRefConstant extends Constant {
		private int name;
		private int nameAndType;

		public FieldRefConstant(int tag, int name, int nameAndType) {
			super(tag);
			this.name = name;
			this.nameAndType = nameAndType;
		}

		@Override
		public String toString() {
			return constants[name - 1].toString() + " "
					+ constants[nameAndType - 1].toString();
		}

	}

}
