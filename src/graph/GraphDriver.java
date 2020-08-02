package graph;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.*;
import graph.Transaction;
import graph.GraphMapper;
//import graph.GraphReducer;

public class GraphDriver {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		// System.out.println("Entry");
		Configuration conf = new Configuration();
		Job job = new Job(conf, "the transaction Program");

		// Job job2=Job.getInstance(conf);

		job.setJarByClass(GraphDriver.class);
		job.setMapperClass(GraphMapper.class);
		job.setReducerClass(GraphReducer.class);

		// job.setNumReduceTasks(0);

		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Transaction.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
