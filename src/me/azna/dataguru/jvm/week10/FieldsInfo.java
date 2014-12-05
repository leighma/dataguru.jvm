package me.azna.dataguru.jvm.week10;

public class FieldsInfo {	
	private Field[] fields;
	
	public int getFilesCount() {
		return fields.length;
	}	

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	class Field{
		int accessFlag;
		int name;
		int descriptor;
		int[] attributes;		
	}

}
