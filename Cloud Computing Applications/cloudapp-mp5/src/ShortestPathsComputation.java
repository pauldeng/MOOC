import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.conf.LongConfOption;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

/**
 * Compute shortest paths from a given source.
 */
public class ShortestPathsComputation extends BasicComputation<
    IntWritable, IntWritable, NullWritable, IntWritable> {
  /** The shortest paths id */
  public static final LongConfOption SOURCE_ID =
      new LongConfOption("SimpleShortestPathsVertex.sourceId", 1,
          "The shortest paths id");

  /**
   * Is this vertex the source id?
   *
   * @param vertex Vertex
   * @return True if the source id
   */
  private boolean isSource(Vertex<IntWritable, ?, ?> vertex) {
    return vertex.getId().get() == SOURCE_ID.get(getConf());
  }

  @Override
  public void compute(
      Vertex<IntWritable, IntWritable, NullWritable> vertex,
      Iterable<IntWritable> messages) throws IOException {
		  
		  // First superstep is special, because we can simply initialize it with the max value
		  if (getSuperstep() == 0) {
			  vertex.setValue(new IntWritable(Integer.MAX_VALUE));
			}
		  
		  // Determine the min received distance
		  Integer minDist = isSource(vertex) ? 0 : Integer.MAX_VALUE;
		  for (IntWritable message : messages) {
			  minDist = Math.min(minDist, message.get());
			  }
	
		  // Compute the min distance: original or received, 
	      // propagate the new value if changed

		  if (minDist < vertex.getValue().get()) {
			  vertex.setValue(new IntWritable(minDist));
			  for (Edge<IntWritable, NullWritable> edge : vertex.getEdges()) {
				  IntWritable neighbor = edge.getTargetVertexId();
				  Integer distance = minDist + 1;			// edge.getValue().get() === 1
				  sendMessage(neighbor, new IntWritable(distance));
				  }
			}
	
	vertex.voteToHalt();
	
  }
}
