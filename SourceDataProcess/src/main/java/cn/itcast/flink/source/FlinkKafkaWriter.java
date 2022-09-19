package cn.itcast.flink.source;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FlinkKafkaWriter {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //2）设置应应用程序按照事件时间处理数据
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        //3）加载原始数据目录中的所有文件，返回dataStream对象
        DataStreamSource<String> source = env.readTextFile("");
        //4）创建kafka的生产者实例，将数据写入到kafka集群
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"node01:9092,node02:9092");
        FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<>(
                "vehicledata",
                new KafkaSerializationSchema<String>() {
                    @Override
                    public ProducerRecord<byte[], byte[]> serialize(String element, @Nullable Long timestamp) {
                        return new ProducerRecord<>(
                                "vehicledata",
                                element.getBytes()
                        );
                    }
                },
                props,
                FlinkKafkaProducer.Semantic.EXACTLY_ONCE
        );
        source.print();
        source.addSink(producer);
        env.execute();

    }
}
