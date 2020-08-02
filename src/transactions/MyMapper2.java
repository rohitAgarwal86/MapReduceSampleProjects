package transactions;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper2 extends Mapper<LongWritable, Text, Text, IntWritable> {
	Text outkey = new Text();
	IntWritable outvalue = new IntWritable();

	@Override
	public void map(LongWritable Key, Text value, Context context) throws IOException, InterruptedException {
		String[] parts = value.toString().split(",");
		// System.out.println(parts[5]);
		if (parts.length > 5) {
			outkey.set(parts[5]);
			outvalue.set(Integer.parseInt(parts[2]));
			context.write(outkey, outvalue);
		}

		// System.out.println(context.toString());
	}

}
