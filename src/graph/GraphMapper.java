package graph;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import graph.Transaction;

public class GraphMapper extends Mapper<LongWritable, Text, IntWritable, Transaction> {

	IntWritable outkey = new IntWritable();
	Transaction outValue = new Transaction();

	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] tokens = value.toString().split(",");
		String date[] = tokens[0].split("-");
		int Year = 0;
		if (tokens.length > 5) {
			Year = Integer.parseInt(date[2]);
			outkey.set(Year);
			outValue.setAmount(Integer.parseInt(tokens[2]));
			outValue.setTransactionDate(tokens[0]);
			outValue.setType(tokens[5]);
			// System.out.println(outValue.getValues());
			context.write(outkey, outValue);
		}

	}

}
