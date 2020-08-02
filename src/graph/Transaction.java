package graph;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class Transaction implements Writable {

	private String TransactionDate;
	private int Amount;
	private String Type;

	public Transaction() {
		this.TransactionDate = null;
		this.Amount = Integer.MIN_VALUE;
		this.Type = null;
	}

	public String getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		TransactionDate = in.readUTF();
		Amount = in.readInt();
		Type = in.readUTF();

	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(TransactionDate);
		out.writeInt(Amount);
		out.writeUTF(Type);

	}

	/*
	 * public void setValues(Transaction obj) { this.Amount=obj.Amount;
	 * this.TransactionDate=obj.TransactionDate; this.Type=obj.Type; }
	 */
	public String getValues() {
		String op = "the values in the current object Amount=" + this.Amount + "Date= " + this.TransactionDate
				+ "Type= " + this.Type;
		return op;
	}

}
