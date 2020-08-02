package transactions;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyReducer2 extends Reducer<Text, IntWritable, Text, DoubleWritable> {
	DoubleWritable Avg = new DoubleWritable();

	@Override
	public void reduce(Text key, Iterable<IntWritable> value, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		int count = 0;
		double avg = 0;
		// System.out.println("hello");
		for (IntWritable val : value) {
			sum = sum + val.get();
			// System.out.println(sum);
			count++;
			// System.out.println(count);
		}
		avg = sum / count;
		Avg.set(avg);
		context.write(key, Avg);

	}

}
