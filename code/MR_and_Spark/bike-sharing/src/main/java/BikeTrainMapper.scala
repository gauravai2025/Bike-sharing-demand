import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapreduce.Mapper


class BikeTrainMapper extends Mapper[Text, Text, Text, IntWritable] {
  private val one = new IntWritable(1)

  override
  def map(key: Text, value: Text, context: Mapper[Text, Text, Text, IntWritable]#Context): Unit = {
    val fields = value.toString.split(",")
    //Missing value
    if (fields.length == 12) {
      //Outliers
      if (fields(1).toInt >= 1 && fields(1).toInt <= 4 && fields(2).toInt >= 0 && fields(2).toInt <= 1 && fields(3).toInt >= 0 && fields(3).toInt <= 1 && fields(4).toInt >= 1 && fields(4).toInt <= 4) {
        if (fields(5).toInt > -1 && fields(6).toInt > -1 && fields(7).toInt > -1 && fields(8).toInt > -1 && fields(9).toInt > -1 && fields(10).toInt > -1 && fields(11).toInt > -1) {
          context.write(new Text(fields(0)), one)
        }
      }
    }
  }
}
