package cn.itcast.flink.source;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

public class FlinkKafkaReader {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        Properties properties = new Properties();
        //集群地址
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"node01:9092,node02:9092,node03:9092");
        //消费者组
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"_vehicle_data_consumer");
        //todo 创建flinkkafkacosumer，用于读取kafka消费者
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(
                "vehicledata",
                new SimpleStringSchema(),
                properties
        );
        //consumer参数
        consumer.setStartFromEarliest();
        //将消费的数据添加到数据源
        DataStreamSource<String> source = env.addSource(consumer);
        source.printToErr();
        env.execute();
    }
}
