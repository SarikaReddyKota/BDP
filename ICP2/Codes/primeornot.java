import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PrimeNumberNew {

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, Text> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
                String[] array = value.toString().split("\\r?\n");
                for(int i=0;i<array.length;i++)
                {

                        context.write(value,new Text(array[i]));
                }
         }
    }

    public static class IntSumReducer
            extends Reducer<Text, Text, Text, Text> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<Text> values,
                           Context context
        ) throws IOException, InterruptedException {
            int k,r = 0;
                 for(Text value:values){

                   //logic from here

                         int i,m,flag=0;
                         k = Integer.parseInt(value.toString());
                         m=k/2;
                         if(k==0||k==1){
                                 r=1;
                         }else{
                                 for(i=2;i<=m;i++){
                                         if(k%i==0){
                                                 r=1;
                                                 flag=1;
                                                 break;
                                         }
                                 }
                         }

                         if(flag == 0){
                                 r=0;
                         }

                     context.write(new Text(Integer.toString(k)),new
Text(Integer.toString(r)));
                 }

        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "prime Number");
        job.setJarByClass(PrimeNumberNew.class);
        job.setMapperClass(TokenizerMapper.class);
      //  job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
