package com.gwssi.struct.model.style.celldisplayformat;

public class CellDisplayFormatOptions {
	
	/**
	 * 若分段。第一段。
	 * 
	 * @author xingye 2016
	 *
	 */
	public class FirstSegment {
		
		/**
		 * 最小长度，使用 0 补齐。
		 */
		protected int minLength = 0;

		public int getMinLength() {
			return minLength;
		}

		public void setMinLength(int minLength) {
			this.minLength = minLength;
		}
		
	}
	
	
	/**
	 * 若不分段，整数部分。
	 * 
	 * @author xingye 2016
	 *
	 */
	public class IntegerPart {
		
		/**
		 * 最小长度，使用 0 补齐。
		 */
		protected int minLength = 0;

		public int getMinLength() {
			return minLength;
		}

		public void setMinLength(int minLength) {
			this.minLength = minLength;
		}
		
	}
	
	
	/**
	 * 小数部分
	 * 
	 * @author xingye 2016
	 *
	 */
	public class DecimalPart {
		
		/**
		 * 精度。四舍五入。-1 表示不控制精度。
		 */
		protected int precision = -1;
		
		/**
		 * 最小长度，使用 0 补齐。
		 */
		protected int minLength = 0;

		public int getMinLength() {
			return minLength;
		}

		public void setMinLength(int minLength) {
			this.minLength = minLength;
		}

		public int getPrecision() {
			return precision;
		}

		public void setPrecision(int precision) {
			this.precision = precision;
		}
		
	}

	
	protected FirstSegment firstSegment = null;
	
	protected IntegerPart integerPart = null;
	
	protected DecimalPart decimalPart = null;


	public FirstSegment getFirstSegment() {
		return firstSegment;
	}

	public void setFirstSegment(FirstSegment firstSegment) {
		this.firstSegment = firstSegment;
	}

	public IntegerPart getIntegerPart() {
		return integerPart;
	}

	public void setIntegerPart(IntegerPart integerPart) {
		this.integerPart = integerPart;
	}

	public DecimalPart getDecimalPart() {
		return decimalPart;
	}

	public void setDecimalPart(DecimalPart decimalPart) {
		this.decimalPart = decimalPart;
	}
	
}

