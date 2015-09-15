import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PopularityLeague extends Configured implements Tool {
	public static final Log LOG = LogFactory.getLog(PopularityLeague.class);
	
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new PopularityLeague(), args);
        System.exit(res);
    }
    
    public static class IntArrayWritable extends ArrayWritable {
        public IntArrayWritable() {
            super(IntWritable.class);
        }

        public IntArrayWritable(Integer[] numbers) {
            super(IntWritable.class);
            IntWritable[] ints = new IntWritable[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                ints[i] = new IntWritable(numbers[i]);
            }
            set(ints);
        }
    }
    
    @Override
    public int run(String[] args) throws Exception {
        // TODO
    	Configuration conf = this.getConf();
        FileSystem fs = FileSystem.get(conf);
        Path tmpPath = new Path("/mp2/tmp");
        fs.delete(tmpPath, true);
        
    	Job jobA = Job.getInstance(this.getConf(), "Link Count");
    	jobA.setOutputKeyClass(IntWritable.class);
        jobA.setOutputValueClass(IntWritable.class);

        jobA.setMapperClass(LinkCountMap.class);
        jobA.setReducerClass(LinkCountReduce.class);

        FileInputFormat.setInputPaths(jobA, new Path(args[0]));
        FileOutputFormat.setOutputPath(jobA, tmpPath);

        jobA.setJarByClass(PopularityLeague.class);
        jobA.waitForCompletion(true);
        
        Job jobB = Job.getInstance(conf, "Top Links");
        jobB.setOutputKeyClass(IntWritable.class);
        jobB.setOutputValueClass(IntWritable.class);

        jobB.setMapOutputKeyClass(NullWritable.class);
        jobB.setMapOutputValueClass(IntArrayWritable.class);

        jobB.setMapperClass(TopLinksMap.class);
        jobB.setReducerClass(TopLinksReduce.class);
        jobB.setNumReduceTasks(1);

        FileInputFormat.setInputPaths(jobB, tmpPath);
        FileOutputFormat.setOutputPath(jobB, new Path(args[1]));

        jobB.setInputFormatClass(KeyValueTextInputFormat.class);
        jobB.setOutputFormatClass(TextOutputFormat.class);

        jobB.setJarByClass(PopularityLeague.class);
        return jobB.waitForCompletion(true) ? 0 : 1;
    }
    
    public static String readHDFSFile(String path, Configuration conf) throws IOException{
        Path pt=new Path(path);
        FileSystem fs = FileSystem.get(pt.toUri(), conf);
        FSDataInputStream file = fs.open(pt);
        BufferedReader buffIn=new BufferedReader(new InputStreamReader(file));

        StringBuilder everything = new StringBuilder();
        String line;
        while( (line = buffIn.readLine()) != null) {
            everything.append(line);
            everything.append("\n");
        }
        return everything.toString();
    }
    // TODO
    public static class LinkCountMap extends Mapper<Object, Text, IntWritable, IntWritable> {
        // TODO
    	List<Integer> stopWords = new ArrayList<Integer>();
    	
    	@Override
        protected void setup(Context context) throws IOException,InterruptedException {
            Configuration conf = context.getConfiguration();
            String stopWordsPath = conf.get("league");

            List<String> stopWordsTmp = Arrays.asList(readHDFSFile(stopWordsPath, conf).split("\n"));
            for (String str : stopWordsTmp) {
            	stopWords.add(Integer.parseInt(str));
            }
        }
    	
    	@Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            //TODO
        	String line = value.toString();
        	StringTokenizer tokenizer = new StringTokenizer(line, ": ");
        	boolean header = true;
        	int step = 0;
        	while (tokenizer.hasMoreTokens()) {
        		Integer nextToken = Integer.parseInt(tokenizer.nextToken().trim());
        		if (header) {
        			step = 0;
        			header = false;
        		} else {
        			step = 1;
        		}
        		if (stopWords.contains(nextToken)) {
    				context.write(new IntWritable(nextToken), new IntWritable(step));
    			}
            }
        }
    }

    public static class LinkCountReduce extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
        // TODO
    	@Override
        public void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            //TODO
        	int sum = 0;
        	for (IntWritable val : values) {
        		sum += val.get();
        	}
        	context.write(key, new IntWritable(sum));
        }
    }
    
    public static class TopLinksMap extends Mapper<Text, Text, NullWritable, IntArrayWritable> {
        private TreeSet<Pair<Integer, Integer>> map = new TreeSet<Pair<Integer, Integer>>();

        // TODO
        @Override
        public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            // TODO
        	Integer count = Integer.parseInt(value.toString());
        	Integer head = Integer.parseInt(key.toString());
        	map.add(new Pair<Integer, Integer>(count, head));
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            // TODO
        	for (Pair<Integer, Integer> item : map) {
        		Integer[] nums = {item.second, item.first};
                IntArrayWritable val = new IntArrayWritable(nums);
                context.write(NullWritable.get(), val);
        	}
        }
    }

    public static class TopLinksReduce extends Reducer<NullWritable, IntArrayWritable, IntWritable, IntWritable> {
        private TreeSet<Pair<Integer, Integer>> map = new TreeSet<Pair<Integer, Integer>>();

        // TODO
        @Override
        public void reduce(NullWritable key, Iterable<IntArrayWritable> values, Context context) throws IOException, InterruptedException {
            // TODO
        	for (IntArrayWritable val: values) {
                IntWritable[] pair= (IntWritable[]) val.toArray();

                Integer head = pair[0].get();
                Integer count = pair[1].get();

                map.add(new Pair<Integer, Integer>(count, head));
            }
        	
        	int globalid = 0;
        	int prevId = 0;
        	int prevCount = 0;
            for (Pair<Integer, Integer> item: map) {
                IntWritable head = new IntWritable(item.second);
                IntWritable value = new IntWritable(item.first);
                int valueNum = value.get();
                if (globalid == 0) {
                	context.write(head, new IntWritable(globalid));
			prevId = globalid;
                } else {
                	if (valueNum == prevCount) {
                		context.write(head, new IntWritable(prevId));
                	} else {
                		context.write(head, new IntWritable(globalid));
				prevId = globalid;
                	}
                }
                prevCount = valueNum;
                globalid++;
            }
        }
    }
}

class Pair<A extends Comparable<? super A>,
B extends Comparable<? super B>>
implements Comparable<Pair<A, B>> {

public final A first;
public final B second;

public Pair(A first, B second) {
this.first = first;
this.second = second;
}

public static <A extends Comparable<? super A>,
    B extends Comparable<? super B>>
Pair<A, B> of(A first, B second) {
return new Pair<A, B>(first, second);
}

@Override
public int compareTo(Pair<A, B> o) {
int cmp = o == null ? 1 : (this.first).compareTo(o.first);
return cmp == 0 ? (this.second).compareTo(o.second) : cmp;
}

@Override
public int hashCode() {
return 31 * hashcode(first) + hashcode(second);
}

private static int hashcode(Object o) {
return o == null ? 0 : o.hashCode();
}

@Override
public boolean equals(Object obj) {
if (!(obj instanceof Pair))
    return false;
if (this == obj)
    return true;
return equal(first, ((Pair<?, ?>) obj).first)
        && equal(second, ((Pair<?, ?>) obj).second);
}

private boolean equal(Object o1, Object o2) {
return o1 == o2 || (o1 != null && o1.equals(o2));
}

@Override
public String toString() {
return "(" + first + ", " + second + ')';
}
}