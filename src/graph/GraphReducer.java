package graph;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import graph.Transaction;

public class GraphReducer extends Reducer<IntWritable, Transaction, IntWritable, Text> {
	Text outValue = new Text();

	@Override
	public void reduce(IntWritable key, Iterable<Transaction> TransObj, Context context)
			throws IOException, InterruptedException {
		// System.out.println("Reducer Job is started");
		int CashSum = 0;
		int CreditSum = 0;
		int CashCounter = 0;
		int CreditCounter = 0;
		double cashAverage = 0;
		double creditAverage = 0;
		int Year = 0;

		for (Transaction obj : TransObj) {
			System.out.println(obj.getValues());
			String[] date = obj.getTransactionDate().split("-");
			if (date.length > 1)
				Year = Integer.parseInt(date[2]);
			if (key.get() == Year) {
				if (obj.getType().equalsIgnoreCase("cash")) {
					CashSum = CashSum + obj.getAmount();
					CashCounter++;
				} else {
					CreditSum = CreditSum + obj.getAmount();
					CreditCounter++;
				}
			}
		}
		cashAverage = CashSum / CashCounter;
		creditAverage = CreditSum / CreditCounter;
		outValue.set("Cash Average for the Year " + key.get() + " is $" + cashAverage);
		context.write(key, outValue);
		outValue.set("Credit Average for the Year " + key.get() + "  is $" + creditAverage);
		context.write(key, outValue);

	}

}
